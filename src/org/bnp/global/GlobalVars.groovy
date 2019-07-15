package org.bnp.global;



class GlobalVars {
    def getSlave(mode, action, gitUrl) {
        //add code for this method
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
        def isPackage = action_lower.contains('package')
        def isDeliver = action_lower.contains('deliver')
        def isProd = gitUrl.contains('france')
        def isQualif = gitUrl.contains('staging')
        def isDev = gitUrl.contains('dev')
        def slave_label = ''




        if (isTab ) {

                if (isProd){
                    if (isPackage) {
                        slave_label = node_sa_tab

                    } else {
                        slave_label = node_se_tab

                    }

                }



        } else {

//            if ((node_se.isEmpty() && isDeliverLocal) || (node_se.isEmpty() && isDeliverOozie) || (node_se_api.isEmpty() && isDeliverApi) || (node_sa.isEmpty() && isPackage)) {
//                System.exit(0)
//            }

            if (isDeliverLocal || isDeliverOozie) {
                slave_label = node_se
                if (! isProd){
                    slave_label = node_se
                }

            }

            if (isPackage) {
                slave_label = node_sa

            }

            if (isDeliverApi) {
                slave_label = node_se_api
                if (! isProd){
                    slave_label = node_se
                }

            }

        }

        return slave_label
    }

    def getListLabelSlaves() {

        def jenkins_nodes = jenkins.model.Jenkins.instance.nodes
        def nodes_label_list = jenkins_nodes.collect { node -> node.labelString }
//        def jenkins_nodes = ['sa', 'sa_tab', 'se', 'se_tab', 'se_api'].toString()
        return nodes_label_list.toString()

    }

}