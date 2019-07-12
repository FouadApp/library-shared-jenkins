package org.bnp.global;


import main.groovy.com.bnp.piplines.GlobalVars;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GetSlaveTest {



    @Test
    public void GetSlaveTabWhenDeliverLocalAction() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "tab_mode";
        String action = "deliver_local";
        String node_expecxted = "se_tab";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }



    @Test
    public void GetSlaveTabWhenDeliverOozieAction() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "tab_mode";
        String action = "deliver_oozie";
        String node_expecxted = "se_tab";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }




    @Test
    public void GetSlaveTabWhenDeliverApiAction() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "tab_mode";
        String action = "deliver_api";
        String node_expecxted = "se_tab";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }


    @Test
    public void GetSlaveTabWhenPackageAction() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "tab_mode";
        String action = "package";
        String node_expecxted = "sa_tab";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }


    @Test
    public void GetSlaveProdWhenPackageAction() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "prod_mode";
        String action = "package";
        String node_expecxted = "sa";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }



    @Test
    public void GetSlaveProdWhenDeliverOozieAction() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "prod_mode";
        String action = "deliver_oozie";
        String node_expecxted = "se";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }


    @Test
    public void GetSlaveProdWhenDeliverApiAction() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "prod_mode";
        String action = "deliver_api";
        String node_expecxted = "se_api";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }




    @Test
    public void GetSlaveProdWhenDeliverLocalAction() {

        //GIVEN

        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "prod_mode";
        String action = "deliver_local";
        String node_expecxted = "se";
        String node ;
        List<String> nodeLists = Arrays.asList("sa","sa_tab","se", "se_api","se_tab");
        GlobalVars globalvars = mock(GlobalVars.class);
        when(globalvars.getListLabelSlaves()).thenReturn(nodeLists.toString());
        when(globalvars.getSlave(mode, action)).thenCallRealMethod();

        // WHEN (execute)
        node = (String) globalvars.getSlave(mode, action);
//        System.out.println("node is :  "+node);

        // THEN ( verify)
        assert node.equals(node_expecxted);

    }


}
