package sessionbuddy.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Submits a HTTPS GET request to the API at https://thesession.org, using a URL
 * in the format required by the API. The JSON response is captured as a String
 * and returned to the caller. A standard user of this library should never have
 * to instantiate an object of this class directly - it is essentially a
 * 'helper' class.
 * 
 * @author Colman O'B
 * @since 2018-03-30
 */
public class HttpRequestor
{
    // The API can return data as JSON, RSS or XML - we only use JSON
    private static final String dataFormat = "json";

    /**
     * Submits a request to thesession.org API, where the URL passed in as an
     * argument is the URL of the desired API resource
     * 
     * @param requestURL A URL for a specific resource on thesession.org as per https://thesession.org/api
     * @return The entire JSON response retrieved from the API, as a single String
     * @throws IOException If there is problem establishing the HTTPS request or reading the response
     */
    public static String submitRequest(URL requestURL) throws IOException
    {
        try
        {
            // Call the API and return the response
            return getAPIResponse(requestURL);
        }
        catch (IOException ex)
        {
            throw ex;
        }
    }

    /**
     * A helper method to build a HTTPS connection
     * 
     * @param apiQueryURL  a complete URL in the format required by the API at https://thesession.org
     * @return the HTTPS connection to the API, from which the API response can be read
     * @throws IOException if there is a problem setting up the connection or reading data from it
     */
    private static HttpURLConnection buildConnection(URL apiQueryURL) throws IOException
    {
        try
        {
            // Make the HTTP(S) connection to thesession.org
            HttpURLConnection connectionToURL = (HttpURLConnection) apiQueryURL.openConnection();

            // Set parameters for the HTTPS connection
            connectionToURL.setRequestMethod("GET");
            connectionToURL.setRequestProperty("Accept", "application/" + dataFormat);

            // Assuming any HTTP response code other than 200 is a problem to be notified to the caller
            if (connectionToURL.getResponseCode() != 200)
            {
                throw new IOException("HTTP error: " + connectionToURL.getResponseCode());
            }
            // Return the HTTPS connection to the caller, in order to read the data from it
            return connectionToURL;
        }
        catch (IOException ex)
        {
            throw ex;
        }
    }

    /**
     * A helper method used to retrieve the JSON response from the API 
     * and return it as a string
     * 
     * @param connectionToURL a HttpURLConnection to API, built with a URL in the format required by the API
     * @return a String containing either the entire JSON response from the API, or a specific page of the response
     * @throws IOException if there is a problem setting up the connection or reading data from it
     */
    private static String captureResponse(HttpURLConnection connectionToURL) throws IOException
    {
        try
        {
            // Create an input stream reader to read the API response
            BufferedReader inputReader = new BufferedReader(new InputStreamReader((connectionToURL.getInputStream()), "utf-8"));

            // Read the response line by line
            String resultCurrentLine;
            StringBuilder builder = new StringBuilder();

            while ((resultCurrentLine = inputReader.readLine()) != null)
            {
                builder.append(resultCurrentLine);
            }
            // Return a String containing the entire response
            return builder.toString();
        }
        catch (IOException ex)
        {
            throw ex;
        }
    }

    /**
     * Makes a HTTP connection to the API with the requested data in the URL,
     * and get the response data
     * 
     * @param tuneSearchURL the URL used when querying the API
     * @return the entire JSON response to the API query, returned as a single String
     * @throws IOException if there was a problem establishing the connection to the API or reading a response
     */
    private static String getAPIResponse(URL tuneSearchURL) throws IOException
    {
        try
        {
            // Create the HTTPS connection and store the response as a String
            HttpURLConnection connectionToURL = buildConnection(tuneSearchURL);
            String response = captureResponse(connectionToURL);
            connectionToURL.disconnect();
            return response;
        }

        catch (IOException ex)
        {
            throw ex;
        }
    }
}
