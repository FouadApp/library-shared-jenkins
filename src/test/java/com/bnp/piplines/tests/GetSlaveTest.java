package com.bnp.piplines.tests;


import com.bnp.piplines.GlobalVars;
import org.junit.Test;
import static org.mockito.Mockito.*;


public class GetSlaveTest {


    @Test
    public void GetSlaveTabWhenDeliverAction() {
        // prepare Get slave when deliver_local or deliver_oozie
        String mode = "tab_mode";
        String action = "deliver_local";
        String node_expecxted = "se_tab";
        String node ;

        GlobalVars msbuilder = new GlobalVars();

        // execute
        node = (String) msbuilder.test1(mode, action);
        System.out.println("result ===>"+node);

        // verify
        assert node.equals(node_expecxted);


    }




}
