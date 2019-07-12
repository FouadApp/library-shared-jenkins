package com.bnp.piplines

class GlobalVars {



    def getListLabelSlaves(){

//        def jenkins_nodes = jenkins.model.Jenkins.instance.nodes
//        def nodes_list = jenkins_nodes.collect { node -> node.labelString }.toString()
        def jenkins_nodes = ['sa','sa_tab','se', 'se_tab','se_api'].toString()
        return jenkins_nodes

    }

    def getSlave(mode, action){
        //add code for this method
        //jenkins_nodes = ['sa','sa_tab','se', 'se_tab','se_api']
        def nodes_list = getListLabelSlaves()
        println("------------------------------")
        def node_se_tab = nodes_list.find('se_tab')
        def node_sa = nodes_list.find('sa')
        def node_sa_tab = nodes_list.find('sa_tab')
        def node_se = nodes_list.find('se')
        def node_se_api = nodes_list.find('se_api')
        def isTab = mode.toString().toLowerCase().contains('tab')
        def action_lower = action.toString().toLowerCase()
        def isDeliverOozie = action_lower.contains('deliver_oozie')
        def isDeliverLocal = action_lower.contains('deliver_local')
        def isDeliverApi = action_lower.contains('deliver_api')
        def isPackage =  action_lower.contains('package')
        def isDeliver = action_lower.contains('deliver')
        def slave_label = ''


        if (isTab){
            if ((node_se_tab.isEmpty() && isDeliver)|| ((node_sa_tab.isEmpty()) && isPackage )){
                System.exit(0)
            }


            if (isPackage){
                println("node_sa_tab is => "+node_sa_tab)
                slave_label = node_sa_tab

            }else {

                println("node_se_tab is => "+node_se_tab)
                slave_label = node_se_tab
            }




        }else{

            if ((node_se.isEmpty() && isDeliverLocal) || (node_se.isEmpty() && isDeliverOozie) || (node_se_api.isEmpty() && isDeliverApi) || (node_sa.isEmpty() && isPackage)  ){
                System.exit(0)
            }

            if (isDeliverLocal || isDeliverOozie){
                println("node_se is => "+node_se)
                slave_label = node_se

            }

            if (isPackage){
                println("node_sa is => "+node_sa)
                slave_label = node_sa

            }

            if (isDeliverApi){
                println("node_se_api is => "+node_se_api)
                slave_label = node_se_api

            }



        }

        return slave_label
    }

}
