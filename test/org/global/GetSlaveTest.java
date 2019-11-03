package org.global;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import org.global.GlobalVars;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GetSlaveTest {



    @Test
    public void getNameTest() {

        //GIVEN


        String pipe = "my_name_pipeline";
        String expected = "my_name_pipeline" ;
        GlobalVars globalvars = new GlobalVars();



        // WHEN (execute)
        String name = (String) globalvars.getName(pipe);


        // THEN ( verify)


        assert name.equals(expected);

    }


}
