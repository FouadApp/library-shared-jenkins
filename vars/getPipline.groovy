import com.bnp.piplines.GlobalVars

def call(String pipline) {
    if (pipline == 'scoring') {
        pipeline {
            agent any
            stages {
                stage('Scorng ....') {
                    steps {
                        echo "The build specific for scoring"
                    }
                }
            }
        }
    } else {
        def RUN_ID
        def URL_GIT
        def ACTION
        def MODE
        def HOST
        def UrlGitlab



//        node('master') {
//            properties ([
//                    parameters([
//                            [
//                                    $class: 'NodeParameterDefinition',
//                                    allowedSlaves: ['ALL (no restriction)'],
//                                    defaultSlaves: ['master'],
//                                    description: 'What nodes to run the build on.',
//                                    name: 'NODE_NAME',
//                                    nodeEligibility: [$class: 'AllNodeEligibility'],
//                                    triggerIfResult: 'allowMultiSelectionForConcurrentBuilds'
//                            ]
//                    ])
//            ])
//
//        }




        node('master') {
            msbuilder = new GlobalVars()
            def mode = "${params.MODE}"
            def action = "${params.ACTION}"
            def slave_labl = msbuilder.test1(mode , action)

            println("test call =====>"+slave_labl)

        }









        pipeline {

            agent {
                node {
                    label "${NODE_NAME}"
                    customWorkspace "${env.WORKSPACE}/${env.JOB_NAME}_${env.BUILD_ID}"

                }
            }



            parameters {
                string(name: 'RUN_ID', description: 'get run_id of model')
                //string(name: 'USER_ID',description: 'get user_id')
                //string(name: 'URL_GIT',defaultValue:'master',  description: 'get url_git')
                //gitParameter branchFilter: 'origin/(.*)', defaultValue: 'master', name: 'BRANCH', type: 'PT_BRANCH'
                choice(
                        choices: ['PACKAGE' , 'DELIVER_OOZIE', 'DELIVER_LOCAL', 'DELIVER_API' ],
                        description: '',
                        name: 'ACTION'
                )

                choice(
                        choices: ['PROD_MODE' , 'TAB_MODE' ],
                        description: 'Choosing the  execution mode',
                        name: 'MODE'
                )

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



                        }
                    }
                }

                stage('get hot and port Git ') {
                    steps {
                        script{
                            def url = new URL ("${env.GIT_URL}")
                            def host = url.host
                            HOST = host
                            echo "result = "+host
                            def node = NODE_NAME.toLowerCase()
                            def mode = MODE.toLowerCase()
                            def isTab = (node.contains('_tab') == mode.contains('_tab'))




                        }


                    }
                }

                stage('remove old projects ') {
                    steps {
                        echo "dir : /${env.JOB_NAME} removed "
                        echo "${env.GIT_BRANCH}";
                        echo "${env.GIT_URL}"
                        echo "${env.NODE_NAME}"
                        echo "${env.JOB_NAME}"
                        echo "---host---->"+HOST
                    }
                }



                stage('create  ~/.git-credentials') {
                    steps {

                        script{

                            withCredentials([usernamePassword( credentialsId: 'jenkins-gitlab-credentials',usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                                sh 'git config credential.helper store'
                                UrlGitlab = 'https://'+USERNAME+':'+PASSWORD+'@'+HOST
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
                                    def status = sh (returnStatus: true, script: " source ~/.bash_profile && mkdir ./envs  && conda create -p ./envs/environment  pip -y  &&  source activate ./envs/environment && pip install  dsflow==1.0" )
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
                            def status = sh (returnStatus: true, script: "MODE="+MODE+" BRANCH="+BRANCH+" ACTION="+ACTION+" RUN_ID="+RUN_ID+" sh build.sh" )
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

                    //deleteDir()
                    echo 'Delete workspace  was a success'
                }
            }

        }
    }
}