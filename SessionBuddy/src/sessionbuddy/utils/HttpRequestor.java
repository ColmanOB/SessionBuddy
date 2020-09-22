package sessionbuddy.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Submits a HTTPS GET request to thesession.org API
 * The JSON response is captured as a String and returned to the caller. 
 * 
 * @author Colman O'B
 * @since 2020-09-22
 */
public class HttpRequestor {
  
  private static final String dataFormat = "json";

  /**
   * Submit a request to the API. Pass in the URL of the resource to be retrieved
   * 
   * @param resourceURL A URL for a specific resource on thesession.org as per https://thesession.org/api
   * @return The JSON returned by the API, formatted as a single String
   * @throws IOException If there is problem establishing the HTTPS request or reading the response
   */
  public static String submitRequest(URL resourceURL) throws IOException {
    try {
      // Call the API and return the response
      return getAPIResponse(resourceURL);
    }
    catch (IOException ex) {
      throw ex;
    }
  }

    /**
     * Makes a HTTP connection to the API and return the response
     * 
     * @param resourceURL the URL used when querying the API
     * @return the entire JSON response to the API query, returned as a single String
     * @throws IOException if there was a problem establishing the connection or reading the response
     */
    private static String getAPIResponse(URL resourceURL) throws IOException {
      try {
        // Create the HTTPS connection and store the response as a String
        HttpURLConnection conn = buildConnection(resourceURL);
        String response = captureResponse(conn);
        conn.disconnect();
        return response;
      }
      catch (IOException ex) {
        throw ex;
      }
    }
    
    /**
     * Helper method to build a HTTPS connection
     * 
     * @param resourceURL  A URL in the format required by the API at https://thesession.org
     * @return the HTTPS connection to the API, from which the API response can be read
     * @throws IOException if there is a problem setting up the connection or reading data from it
     */
    private static HttpURLConnection buildConnection(URL resourceURL) throws IOException {
      try {
        // Make the HTTP(S) connection to thesession.org
        HttpURLConnection conn = (HttpURLConnection) resourceURL.openConnection();

        // Set parameters for the HTTPS connection
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/" + dataFormat);

        // Assuming any HTTP response code other than 200 is a problem to be notified to the caller
        if (conn.getResponseCode() != 200) {
          throw new IOException("HTTP error: " + conn.getResponseCode());
        }
        // Return the HTTPS connection to the caller, in order to read the data from it
        return conn;
      }
      catch (IOException ex) {
        throw ex;
      }
    }

    /**
     * Helper method to retrieve the JSON response from the API and return it as a string
     * 
     * @param conn a HttpURLConnection to API, built with a URL in the format required by the API
     * @return a String containing either the entire JSON response from the API, or a specific page of the response
     * @throws IOException if there is a problem setting up the connection or reading data from it
     */
    private static String captureResponse(HttpURLConnection conn) throws IOException {
      try {
        // Create an input stream reader to read the API response
        BufferedReader inputReader = new BufferedReader(new InputStreamReader((conn.getInputStream()), "utf-8"));

        // Read the response line by line
        String resultCurrentLine;
        StringBuilder builder = new StringBuilder();

        // Append each line of the response to the string to be returned
        while ((resultCurrentLine = inputReader.readLine()) != null) {
          builder.append(resultCurrentLine);
        }
        // Return a String containing the entire response
        return builder.toString();
      }
      catch (IOException ex) {
        throw ex;
      }
    }
}
