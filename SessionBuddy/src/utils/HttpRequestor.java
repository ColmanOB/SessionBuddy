package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// TODO: Complete Javadoc comments, especially those re: exceptions thrown by methods
		
/**
 * Submits a HTTPS GET request to the API at https://thesession.org, using a URL in the format required by the API.
 * The JSON response is captured as a String and returned to the caller
 * 
 * @author Colman O'B
 * @since 2017-08-31
 */
public class HttpRequestor
	{	
	// Define a few constants used in building the URLs for querying the API
	private static final String dataFormat = "json";
	private static final String baseURL = "https://thesession.org/"; 
	private static final String searchOperator = "search?q=";
	private static final String latestOperator = "new?";
	private static final String latLonOperator = "nearby?latlon=";
	private static final String radiusOperator = "&radius=";
	

	/**
	 * Retrieves details of an individual item using its unique ID from thesession.org.
	 * The item may be a tune, discussion, recording, session or event.
	 * 
	 * @param baseCategory the category of information to be retrieved, i.e. tunes, discussions, sessions, recordings or events
	 * @param itemID the unique numeric ID of the item to be retrieved, generally known from a previous search
	 * @return the JSON response from the API as a String
	 * @throws RuntimeException
	 * @throws MalformedURLException 
	 */
	public String submitItemByIDRequest(String baseCategory, String itemID) throws RuntimeException, MalformedURLException
		{
		URL tuneSearchURL; 	// The correctly-formatted URL for the API request
		String response;	// A string of JSON data returned from the API
		
		try 
			{
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			tuneSearchURL = new URL(baseURL + baseCategory + "/" + itemID + "?" + "format=" + dataFormat);
			
			// Call the API using a private helper method and store the response
			response = getAPIResponse(tuneSearchURL);
			} 
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			}
		
		catch(RuntimeException e)
			{
			throw new RuntimeException(e.getMessage());
			}
		
		// The JSON data returned by the API, stored as a single string
		return response;
		}
		
	
	/**
	 * Retrieves the most recently submitted entries in the chosen category thesession.org and stores the resulting JSON response.
	 * The data category may be tunes, discussions, recordings, sessions or events.
	 * 
	 * @param baseCategory the category of information to be retrieved, i.e. tunes, discussions, sessions, recordings or events
	 * @param resultsPerPage the number of search results to be returned per page, up to a maximum of 50
	 * @return the JSON response from the API as a String
	 * @throws RuntimeException
	 * @throws MalformedURLException 
	 */
	public String submitLatestRequest(String baseCategory, int resultsPerPage) throws RuntimeException, MalformedURLException
		{		
		URL tuneSearchURL; 	// The correctly-formatted URL for performing the tune search
		String response;	// A string of JSON data returned from the API
		
		try 
			{			
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			tuneSearchURL = new URL(baseURL + baseCategory + "/" + latestOperator + "format=" + dataFormat + "&perpage=" + resultsPerPage);
			
			// Call the API using a private helper method and store the response
			response = getAPIResponse(tuneSearchURL);
			
			return response;
			}
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (RuntimeException e) 
			{
			throw new RuntimeException(e.getMessage());
			}
		}
	
	
	/**
	 * Alternative version of submitLatestRequest, allows the caller to retrieve a specific page within a paginated JSON response
	 * 
	 * @param baseCategory the category of information to be retrieved, i.e. tunes, discussions, sessions, recordings or events
	 * @param resultsPerPage the number of search results to be returned per page, up to a maximum of 50
	 * @param pageNumber a specific page within the JSON response
	 * @return the JSON response from the API as a String
	 * @throws RuntimeException
	 * @throws MalformedURLException 
	 */
	public String submitLatestRequest(String baseCategory, int resultsPerPage, int pageNumber) throws RuntimeException, MalformedURLException
		{		
		URL tuneSearchURL; 	// The correctly-formatted URL for performing the tune search
		String response;	// A string of JSON data returned from the API
		
		try 
			{			
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			tuneSearchURL = new URL(baseURL + baseCategory + "/" + latestOperator + "format=" + dataFormat + "&perpage=" + resultsPerPage + "&page=" + pageNumber);
			
			// Call the API using a private helper method and store the response
			response = getAPIResponse(tuneSearchURL);
			
			return response;
			}
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (RuntimeException e) 
			{
			throw new RuntimeException(e.getMessage());
			}
		}
	
		
	/**
	 * An alternative to the submitSearchRequest(String baseCategory, String searchTermsInput, int resultsPerPage) method.
	 * Intended to be used when handling a result set with multiple pages.
	 * Allows a specific page number within the results to be specified.
	 * 
	 * @param baseCategory the category of information to be retrieved, i.e. tunes, discussions, sessions, recordings or events
	 * @param searchTermsInput searchTermsInput the search terms collected from the user, as a single string
	 * @param resultsPerPage the number of search results to be returned per page, up to a maximum of 50
	 * @param pageNumber an individual page number within a paginated JSON response
	 * @return the JSON response from the API as a String
	 * @throws RuntimeException
	 * @throws MalformedURLException 
	 */
	public String submitSearchRequest(String baseCategory, String searchTermsInput, int resultsPerPage, int pageNumber) throws RuntimeException, MalformedURLException
		{
		URL tuneSearchURL; 	// The correctly-formatted URL for performing the tune search
		String response;	// A string of JSON data returned from the API
		
		try 
			{
			// Clean up the search terms provided by the user using a private helper method
			String searchTermsFormatted = formatSearchTerms(searchTermsInput);
		
			// Build the URL with all necessary parameters to perform a search via thesession.org API, specifying the page number
			tuneSearchURL = new URL(baseURL + baseCategory + "/" + searchTermsFormatted + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage + "&page=" + pageNumber);
		
			// Call the API using a private helper method and store the response
			response = getAPIResponse(tuneSearchURL);
			
			// This is the full JSON response from the API, as a single string
			return response;
			}
	
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (RuntimeException e) 
			{
			throw new RuntimeException(e.getMessage());
			}
		}
	
	
	/**
	 * @param baseCategory
	 * @param searchTermsInput
	 * @param resultsPerPage
	 * @return
	 * @throws RuntimeException
	 * @throws MalformedURLException
	 */
	public String submitSearchRequest(String baseCategory, String searchTermsInput, int resultsPerPage) throws RuntimeException, MalformedURLException
		{		
		URL tuneSearchURL; 	// The correctly-formatted URL for performing the tune search
		String response;	// A string of JSON data returned from the API
		
		try 
			{
			// Clean up the search terms provided by the user
			String searchTermsFormatted = formatSearchTerms(searchTermsInput);
			
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			tuneSearchURL = new URL(baseURL + baseCategory + "/" + searchOperator + searchTermsFormatted + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage);
			
			// Call the API using a private helper method and store the response
			response = getAPIResponse(tuneSearchURL);
			
			return response;
			}
	
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (RuntimeException e) 
			{
			throw new RuntimeException(e.getMessage());
			}
		}
		
	
	/**
	 * Performs a location-based search for either sessions or events within a specific geographic area.
	 * 
	 * @param baseCategory the category of information to be retrieved, specifically sessions or events
	 * @param latitude A string representation of the target location's latitude 
	 * @param longitude A string representation of the target location's latitude
	 * @param radius The radius in kilometers around the coordinates that we wish to include in the search
	 * @param resultsPerPage the number of search results to be returned per page, up to a maximum of 50
	 * @return A string representation of the location's latitude
	 * @throws RuntimeException
	 * @throws MalformedURLException 
	 */
	public String submitLocationRequest(String baseCategory, String latitude, String longitude, String radius, int resultsPerPage) throws RuntimeException, MalformedURLException
		{		
		String response;	// A string of JSON data returned from the API
		
		try 
			{			
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL tuneSearchURL = new URL(baseURL + baseCategory + "/" + latLonOperator + latitude + "," + longitude + radiusOperator + radius + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage);
			
			// Use the URL.buildConnection() method to create the HTTPS connection
			HttpURLConnection connectionToURL =  buildConnection(tuneSearchURL);
			
			// Store the JSON response from the API
			response = captureResponse(connectionToURL);
			
			// We have our search results.  Close the connection to https://thesession.org
			connectionToURL.disconnect();		
			} 
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new RuntimeException(e.getMessage());
			}
		
		// Return the API response as one long string of JSON data
		return response;
		}

	
	/**
	 * Alternative version of submitLocationRequest, allowing a specific page within the JSON response to be retrieved
	 * 
	 * @param baseCategory the category of information to be retrieved, specifically sessions or events
	 * @param latitude A string representation of the target location's latitude 
	 * @param longitude A string representation of the target location's latitude
	 * @param radius The radius in kilometers around the coordinates that we wish to include in the search
	 * @param resultsPerPage the number of search results to be returned per page, up to a maximum of 50
	 * @param pageNumber A specific page number within a multi-page JSON response
	 * @return A string representation of the location's latitude
	 * @throws RuntimeException
	 * @throws MalformedURLException 
	 */
	public String submitLocationRequest(String baseCategory, String latitude, String longitude, String radius, int resultsPerPage, int pageNumber) throws RuntimeException, MalformedURLException
		{		
		String response;	// A string of JSON data returned from the API
		
		try 
			{
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL tuneSearchURL = new URL(baseURL + baseCategory + "/" + latLonOperator + latitude + "," + longitude + radiusOperator + radius + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage + "&page=" + pageNumber);

			// Use the buildConnection helper method to create the HTTPS connection
			HttpURLConnection connectionToURL =  buildConnection(tuneSearchURL);
			
			// Store the response from the API
			response = captureResponse(connectionToURL);
			} 
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new RuntimeException(e.getMessage());
			}
		
		// Return the API response as one long string of JSON data
		return response;
		}

	
	/**
	 * A helper method used by all the other core methods in this class to build a HTTPS connection
	 * 
	 * @param tuneSearchURL a complete URL in the format required by the API at https://thesession.org
	 * @return the HTTPS connection to the API, from which the API response can be read
	 * @throws IOException
	 * @throws RuntimeException
	 */
	private HttpURLConnection buildConnection(URL tuneSearchURL) throws IOException, RuntimeException
		{
		HttpURLConnection connectionToURL;
		
		try 
			{
			// Make the HTTP(S) connection to thesession.org
			connectionToURL = (HttpURLConnection) tuneSearchURL.openConnection();
			} 
		
		catch (IOException e) 
			{
			throw new IOException(e.getMessage());
			}
		
		// Set parameters for the HTTPS connection
		connectionToURL.setRequestMethod("GET");
		connectionToURL.setRequestProperty("Accept", "application/" + dataFormat);
	
		//Assuming anything other than 200 is a problem to be notified to the user
		if (connectionToURL.getResponseCode() != 200) 
			{	
			throw new RuntimeException("A problem has occurred - HTTP error " + connectionToURL.getResponseCode());
			}
		
		// Return the HTTPS connection to the caller
		return connectionToURL;
		}
	
	
	/**
	 * A helper method used to retrieve the JSON response from the API and return it as a string
	 * 
	 * @param connectionToURL a HttpURLConnection to API, built with a URL in the format required by the API
	 * @return a String containing either the entire JSON response from the API, or a specific page of the response
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	private String captureResponse(HttpURLConnection connectionToURL) throws UnsupportedEncodingException, IOException
		{
		BufferedReader inputReader = new BufferedReader(new InputStreamReader((connectionToURL.getInputStream()),"utf-8"));
		
		// Use a StringBuilder to build a string from the data in the BufferedReader
		String searchResults;
		StringBuilder builder = new StringBuilder();
		
		while ((searchResults = inputReader.readLine()) != null) 
			{ 
			builder.append(searchResults);	
			}
		
		// Create a String from the StringBuilder and return it to the caller
		return builder.toString();
		}
	
	
	/**
	 * A helper method to format the search terms so that any spaces are replaced with '+' characters, as required by the API
	 * 
	 * @param searchTerms a string containing the user's search terms, may contain spaces
	 * @return a String with any spaces replaced with '+' characters
	 */
	private String formatSearchTerms(String searchTerms)
		{
		// The session.org API requires the + character between search terms in the URL
		String formattedSearchTerms = searchTerms.replace(" ","+"); 
		
		return formattedSearchTerms;
		}
	
	
	/**
	 * Makes a HTTP connection to the API with the requested data in the URL, and get the response data
	 * 
	 * @param tuneSearchURL the URL used when querying the API
	 * @return the entire JSON response to the API query, returned as a single String
	 * @throws MalformedURLException 
	 */
	private String getAPIResponse(URL tuneSearchURL) throws RuntimeException, MalformedURLException
		{	
		String response;	// A string of JSON data returned from the API
		
		try
			{
			// Use the buildConnection method to create the HTTPS connection
			HttpURLConnection connectionToURL =  buildConnection(tuneSearchURL);
				
			// Store the response from the API
			response = captureResponse(connectionToURL);
			
			// We have our search results.  Close the connection to https://thesession.org
			connectionToURL.disconnect();		
			}
	
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new RuntimeException(e.getMessage());
			}
	
		// Return the API response as one long string of JSON data
		return response;
		}
	
	}
