import org.bnp.global.GlobalVars

def call(String pipe) {
    if (pipe == 'scoring') {

        echo "The build specific for scoring"
        echo ".............Scoring ........."

        def RUN_ID
        def NAME_EXPERIMENT
        def URL_GIT
        def ACTION
        def MODE
        def HOST
        def UrlGitlab
        def SLAVE
        def ISDEV

        node('master') {
            globalVars = new GlobalVars()
            def mode = "${params.MODE}"
            def action = "${params.ACTION}"
            def gitUrl = scm.getUserRemoteConfigs()[0].getUrl()
            def isProd = gitUrl.contains('france')
            def isDev = gitUrl.contains('dev')
            ISDEV = isDev


            def list_actions =  ['PACKAGE' , 'DELIVER_OOZIE', 'DELIVER_LOCAL', 'DELIVER_API' ]
            def list_modes =  ['PROD_MODE' , 'TAB_MODE' ]


            properties ([
                    parameters([

                            string(name: 'RUN_ID', description: 'get run_id of model'),
                            string(name: 'NAME_EXPERIMENT', description: 'get name_experiment of model'),
                            choice(
                                    choices:list_actions,
                                    description: '',
                                    name: 'ACTION'
                            ),

                            choice(
                                    choices: list_modes,
                                    description: 'Choosing the  execution mode',
                                    name: 'MODE'
                            )

                    ])
            ])

            def isTab = mode.toString().toLowerCase().contains('tab')
            stage('check env ...') {

                if (! isProd && isTab) {
                    currentBuild.result = 'ABORTED'
                    echo('Error  cannot execute TAB_MODE in environment  Dev or Qualif')
                    echo('execute TAB_MODE available only environment  Prod')
                    error('Aborting the build .....')
                }
            }
            def slave_label = globalVars.getSlave(mode , action, gitUrl)
            SLAVE = slave_label
            println(" slave label is : "+slave_label)
        }

        pipeline {

            agent {
                node {
                    label SLAVE
                    customWorkspace "${env.WORKSPACE}/${env.JOB_NAME}_${env.BUILD_ID}"

                }
            }

            stages {

                stage('parametres') {
                    steps {
                        script{
                            RUN_ID = "${params.RUN_ID}"
                            MODE = "${params.MODE}"
                            //USER_ID = "${params.USER_ID}"
                            URL_GIT = "${params.URL_GIT}"
                            CLUSTER_NAME = "${params.CLUSTER_NAME}"
                            ACTION = "${params.ACTION }"
                            BRANCH = "${env.GIT_BRANCH}"
                            NAME_EXPERIMENT = "${params.NAME_EXPERIMENT}"

                        }
                    }
                }
                stage('get hot and port Git ') {
                    steps {
                        script{
                            def url = new URL ("${env.GIT_URL}")
                            def host = url.host
                            def port = url.port
                            HOST = host
                            if (ISDEV){
                                HOST = host+':'+port
                            }

                        }


                    }
                }
                stage('remove old projects ') {
                    steps {
                        echo "${env.GIT_BRANCH}";
                        echo "${env.GIT_URL}"
                        echo "${env.NODE_NAME}"
                        echo "${env.JOB_NAME}"
                    }
                }
                stage('create  ~/.git-credentials') {
                    steps {

                        script{

                            withCredentials([usernamePassword( credentialsId: 'jenkins-gitlab-credentials',usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                                sh 'git config credential.helper store'

                                UrlGitlab = 'http://'+USERNAME+':'+PASSWORD+'@'+HOST

                                if (ISDEV){
                                    UrlGitlab = 'http://'+USERNAME+':'+PASSWORD+'@'+HOST

                                }

                                env['ENV_UrlGitlab'] = UrlGitlab
                                sh 'echo ${ENV_UrlGitlab}  > ~/.git-credentials'

                            }
                        }

                    }
                }
                stage('INSTALL ENVIRONMENT') {
                    steps{
                        script{


                            if("${params.ACTION }" == "PACKAGE"  ){
                                echo '----------------- -------------PACKAGE ------------------------------'
                                script{
                                    def status = sh (returnStatus: true, script: "sh install.sh " )
                                    if (status != 0) {
                                        currentBuild.result = 'FAILURE'
                                        echo "RESULT: ${currentBuild.result}"
                                    }
                                }

                            }else{
                                script{ echo "------------------ ${params.ACTION } -----------------------------------"
                                    def status = sh (returnStatus: true, script: " source ~/.bash_profile && mkdir ./envs  && conda env update -f conda.yaml  -p ./envs/environment " )
                                    if (status != 0) {
                                        currentBuild.result = 'FAILURE'
                                        echo "RESULT: ${currentBuild.result}"
                                    }
                                }
                            }



                        }
                    }
                }
                stage('BUILD') {
                    steps{

                        script{
                            def status = sh (returnStatus: true, script: "MODE="+MODE+" BRANCH="+BRANCH+" ACTION="+ACTION+" RUN_ID="+RUN_ID+" NAME_EXPERIMENT="+NAME_EXPERIMENT+" sh build.sh" )
                            if (status != 0) {
                                currentBuild.result = 'FAILURE'
                                echo "RESULT: ${currentBuild.result}"
                            }
                        }
                    }
                }

            }

            post {

                always {

                    echo "------------------------------- THE END ------------------------------"
                }
                failure {
                    echo 'failure:  Error when executing : thank you to consult the logs on Jenkins  '
//                    deleteDir()

                }
                success {

                    echo 'Build was a success'
                    echo 'Delete the current directory from the workspace'
                    script{
                        if ("${params.ACTION}" == 'DELIVER_API') {
                            echo " path deliver  : ${env.ROOT_PATH_DELIVERY_API}/start_api.sh "
                        }
                        if ("${params.ACTION}" == 'DELIVER_LOCAL') {
                            echo " path deliver  : ${env.ROOT_PATH_DELIVERY_LOCAL}/start_local.sh "
                        }
                        if ("${params.ACTION}" == 'DELIVER_OOZIE') {
                            echo " path deliver  : ${env.ROOT_PATH_DELIVERY_OOZIE}/oozie.sh "
                        }
                    }

                    deleteDir()
                    echo 'The build was done on the slave = ${env.NODE_NAME}'
                    echo 'Delete workspace  was a success'
                }
            }

        }

    } else {
        currentBuild.result = 'ABORTED'
        echo('Error  cannot run this build ')
        echo('you must choose name of myPipeline in your JenkinsFile into your project')
        error('Aborting the build .....')


    }
}
