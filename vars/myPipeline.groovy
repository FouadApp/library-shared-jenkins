import org.global.GlobalVars

def call(String pipe) {
    if (pipe == 'my_pipeline_1') {

        echo "The build specific for my_pipeline_1"
        echo ".............my_pipeline_1 ........."



    }
    else if (pipe == 'my_pipeline_2') {

        echo "The build specific for my_pipeline_2"
        echo ".............my_pipeline_2 ........."



    }
    else if (pipe == 'my_pipeline_3') {

        echo "The build specific for my_pipeline_3"
        echo ".............my_pipeline_3 ........."


    }
    else {
        currentBuild.result = 'ABORTED'
        echo('Error  cannot run this build ')
        echo('you must choose name of myPipeline in your JenkinsFile into your project')
        error('Aborting the build .....')


    }
}
