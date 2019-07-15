package org.bnp.global;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import org.bnp.global.GlobalVars;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GetSlaveTest {



    @Test
    public void getSlaveTabWhenDeliverLocalActionWhenProdENV() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "tab_mode";
        String action = "deliver_local";
        String node_expecxted = "se_tab";
        String gitUrl = "https://gitlab-sdi101ml.france.echonet";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action, gitUrl)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action, gitUrl);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }

    @Test
    public void getSlaveTabWhenDeliverOozieActionWhenProdENV() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "tab_mode";
        String action = "deliver_oozie";
        String node_expecxted = "se_tab";
        String gitUrl = "https://gitlab-sdi101ml.france.echonet";

        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action, gitUrl)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action, gitUrl);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }

    @Test
    public void getSlaveTabWhenDeliverApiActionWhenProdENV() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "tab_mode";
        String action = "deliver_api";
        String node_expecxted = "se_tab";
        String gitUrl = "https://gitlab-sdi101ml.france.echonet";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action, gitUrl)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action, gitUrl);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }

    @Test
    public void getSlaveTabWhenPackageActionWhenProdENV() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "tab_mode";
        String action = "package";
        String node_expecxted = "sa_tab";
        String gitUrl = "https://gitlab-sdi101ml.france.echonet";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action, gitUrl)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action, gitUrl);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }

    @Test
    public void getSlaveProdWhenPackageActionWhenProdENV() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "prod_mode";
        String action = "package";
        String node_expecxted = "sa";
        String node ;
        String gitUrl = "https://gitlab-sdi101ml.france.echonet";
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action, gitUrl)).thenCallRealMethod();


        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action, gitUrl);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }

    @Test
    public void getSlaveProdWhenDeliverOozieActionWhenProdENV() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "prod_mode";
        String action = "deliver_oozie";
        String node_expecxted = "se";
        String gitUrl = "https://gitlab-sdi101ml.france.echonet";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action, gitUrl)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action, gitUrl);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }

    @Test
    public void getSlaveProdWhenDeliverApiActionWhenProdENV() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "prod_mode";
        String action = "deliver_api";
        String node_expecxted = "se_api";
        String gitUrl = "https://gitlab-sdi101ml.france.echonet";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action, gitUrl)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action, gitUrl);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }

    @Test
    public void getSlaveProdWhenDeliverLocalActionWhenProdENV() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "prod_mode";
        String action = "deliver_local";
        String node_expecxted = "se";
        String gitUrl = "https://gitlab-sdi101ml.france.echonet";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action, gitUrl)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action, gitUrl);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }




    @Test
    public void getSlaveProdWhenPackageActionWhenQualifAndDevEnv() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "prod_mode";
        String action = "package";
        String node_expecxted = "sa";
        String node ;
        String gitUrl = "https://gitlab-sdi101ml.dev.echonet";
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action, gitUrl)).thenCallRealMethod();


        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action, gitUrl);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }

    @Test
    public void getSlaveProdWhenDeliverOozieActionWhenQualifAndDevEnv() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "prod_mode";
        String action = "deliver_oozie";
        String node_expecxted = "se";
        String gitUrl = "https://gitlab-sdi101ml.dev.echonet";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action, gitUrl)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action, gitUrl);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }

    @Test
    public void getSlaveProdWhenDeliverApiActionWhenQualifAndDevEnv() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "prod_mode";
        String action = "deliver_api";
        String node_expecxted = "se";
        String gitUrl = "https://gitlab-sdi101ml.dev.echonet";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action, gitUrl)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action, gitUrl);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }

    @Test
    public void getSlaveProdWhenDeliverLocalActionWhenQualifAndDevEnv() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "prod_mode";
        String action = "deliver_local";
        String node_expecxted = "se";
        String gitUrl = "https://gitlab-sdi101ml.dev.echonet";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action, gitUrl)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action, gitUrl);
        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }

}
