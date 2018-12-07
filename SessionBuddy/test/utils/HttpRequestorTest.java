package utils;

import static org.junit.Assert.*;
import java.io.IOException;
import java.net.URL;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import sessionbuddy.utils.HttpRequestor;

/**
 * Ensure HttpRequestor returns a JSON response containing some expected text.
 * Requires an internet connection, or the API call will fail
 * 
 * This test is not concerned with constructing a URL - a 'known good' URL
 * is passed in. See URLComposerTest for constructing a URL
 * 
 * @author Colman
 * @since 2018-12-07
 */
public class HttpRequestorTest
{
    @Test
    public void testHttpResponse()
    {
        // A known good URL that should retrieve the details of the tune with ID 2, 
        // i.e. The Bucks of Oranmore
        String urlAsText = "https://thesession.org/tunes/2?format=json";

        try
        {
            // Create a URL type object to be passed to  HttpRequestor.submitRequest(url)
            URL url = new URL(urlAsText);

            // Invoke the method to be tested, passing in the 'known good' URL
            String responseFromAPI = HttpRequestor.submitRequest(url);

            // Tune ID 2 is The Bucks of Oranmore, so that should be in the JSON response
            Assert.assertThat(responseFromAPI, CoreMatchers.containsString("Bucks Of Oranmore"));
        }

        catch (IOException e)
        {
            // Catch a HTTP failure response, timeout, or not containing the text "Bucks Of Oranmore"
            fail(e.getMessage());
        }
    }
}
