package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;

import sessionbuddy.IndividualItem;

public class Test_Get_Tune_Settings_As_Abc
{

    public static void main(String[] args)
    {

        try
        {
            System.out.println(IndividualItem.getTuneAsAbcTunebook(2));
        } 
        
        catch (IOException | URISyntaxException e)
        {
            e.printStackTrace();
        }
    }
}
