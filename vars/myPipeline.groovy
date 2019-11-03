import org.global.GlobalVars

def call(String pipe) {
    if (pipe == 'my_pipeline_1') {

        echo "The build specific for my_pipeline_1"
        echo ".............my_pipeline_1 ........."


        pipeline {

            agent {
                node {
                    label SLAVE
                    customWorkspace "${env.WORKSPACE}/${env.JOB_NAME}_${env.BUILD_ID}"

                }
            }

            stages {


                stage('BUILD') {
                    steps {
                        script{

                            def name = globalVars.getName(pipe)
                            echo "build name --->:"+name


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

                    deleteDir()
                    echo 'Delete workspace  was a success'
                }
            }

        }

    }
    else if (pipe == 'my_pipeline_2') {

        echo "The build specific for my_pipeline_2"
        echo ".............my_pipeline_2 ........."


        pipeline {

            agent {
                node {
                    label SLAVE
                    customWorkspace "${env.WORKSPACE}/${env.JOB_NAME}_${env.BUILD_ID}"

                }
            }

            stages {


                stage('BUILD') {
                    steps {
                        script{

                            def name = globalVars.getName(pipe)
                            echo "build name --->:"+name


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

                    deleteDir()
                    echo 'Delete workspace  was a success'
                }
            }

        }

    }
    else if (pipe == 'my_pipeline_3') {

        echo "The build specific for my_pipeline_3"
        echo ".............my_pipeline_3 ........."


        pipeline {

            agent {
                node {
                    label SLAVE
                    customWorkspace "${env.WORKSPACE}/${env.JOB_NAME}_${env.BUILD_ID}"

                }
            }

            stages {


                stage('BUILD') {
                    steps {
                        script{

                            def name = globalVars.getName(pipe)
                            echo "build name --->:"+name


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

                    deleteDir()
                    echo 'Delete workspace  was a success'
                }
            }

        }

    }
    else {
        currentBuild.result = 'ABORTED'
        echo('Error  cannot run this build ')
        echo('you must choose name of myPipeline in your JenkinsFile into your project')
        error('Aborting the build .....')


    }
}
