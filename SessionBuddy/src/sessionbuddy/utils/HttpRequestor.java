package sessionbuddy.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

		
/**
 * Submits a HTTPS GET request to the API at https://thesession.org, using a URL in the format required by the API.
 * The JSON response is captured as a String and returned to the caller.
 * A standard user of this library should never have to instantiate an object of this class directly - it is essentially a 'helper' class used by several other classes in the 'main' package.
 * 
 * @author Colman O'B
 * @since 2017-09-05
 */
public class HttpRequestor
	{	
	// Define a few constants used in building the URLs for querying the API
	private static final String dataFormat = "json";
	private static final String baseURL = "https://thesession.org/"; 
	private static final String searchOperator = "search?q=";
	private static final String popularOperator = "popular?";
	private static final String memberOperator = "members";
	private static final String latLonOperator = "nearby?latlon=";
	private static final String radiusOperator = "&radius=";
	

	/**
	 * Retrieves details of an individual item using its unique ID from thesession.org.
	 * The item may be a tune, discussion, recording, session or event.
	 * 
	 * @param baseCategory the category of information to be retrieved, i.e. tunes, discussions, sessions, recordings or events
	 * @param itemID the unique numeric ID of the item to be retrieved, which will generally be known from a previous search
	 * @return the JSON response from the API as a String
	 * @throws IOException if a problem was encountered setting up the connection or reading the API response
	 * @throws MalformedURLException if an invalid URL has somehow been constructed
	 */
	public String submitItemByIDRequest(String baseCategory, String itemID) throws IOException, MalformedURLException
		{		
		try
			{			
			// Build the URL with all necessary parts to call the API
			URL requestURL = new URL(baseURL + baseCategory + "/" + itemID + "?" + "format=" + dataFormat);

			// Call the API using a private helper method and store the response
			String response = getAPIResponse(requestURL);
			
			return response;
			} 
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			}
		
		catch(IOException e)
			{
			throw new IOException(e.getMessage());
			}
		}
		
	
	/**
	 * Retrieves the most recently submitted entries in the chosen category thesession.org and stores the resulting JSON response.
	 * The data category may be tunes, discussions, recordings, sessions or events.
	 * 
	 * @param baseCategory the category of information to be retrieved, i.e. tunes, discussions, sessions, recordings or events
	 * @param resultsPerPage the number of search results to be returned per page, up to a maximum of 50
	 * @return the JSON response from the API as a String
	 * @throws IOException if a problem was encountered setting up the connection or reading the API response
	 * @throws MalformedURLException if an invalid URL has somehow been constructed
	 * @throws URISyntaxException 
	 */
	public static String submitLatestRequest(String baseCategory, int resultsPerPage) throws IOException, MalformedURLException, URISyntaxException
		{				
		try 
			{	
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL requestURL = UrlBuilder.buildURL(baseCategory, "new", resultsPerPage);
			
			// Call the API using a private helper method and store the response
			String response = getAPIResponse(requestURL);
			
			return response;
			}
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new IOException(e.getMessage());
			}
		
		catch (URISyntaxException e)
			{
			throw new URISyntaxException(e.getInput(), e.getReason(), e.getIndex());
			}
		}
	
	
	/**
	 * Alternative version of submitLatestRequest, allows the caller to retrieve a specific page within a paginated JSON response
	 * 
	 * @param baseCategory the category of information to be retrieved, i.e. tunes, discussions, sessions, recordings or events
	 * @param resultsPerPage the number of search results to be returned per page, up to a maximum of 50
	 * @param pageNumber a specific page within the JSON response
	 * @return the JSON response from the API as a String
	 * @throws IOException if a problem was encountered setting up the connection or reading the API response
	 * @throws MalformedURLException if an invalid URL has somehow been constructed
	 * @throws URISyntaxException 
	 */
	public static String submitLatestRequest(String baseCategory, int resultsPerPage, int pageNumber) throws IOException, MalformedURLException, URISyntaxException
		{		
		try 
			{
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL requestURL = UrlBuilder.buildURL(baseCategory, "new", resultsPerPage, pageNumber);
			
			// Call the API using a private helper method and store the response
			String response = getAPIResponse(requestURL);
			
			return response;
			}
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new IOException(e.getMessage());
			}
		
		catch (URISyntaxException e)
			{
			throw new URISyntaxException(e.getInput(), e.getReason(), e.getIndex());
			}
		}
	
	
	/**
	 * Performs a keyword-based search of thesession.org API against a chosen category of data.
	 * The category may be tunes, discussions, recordings, sessions or events.
	 * 
	 * @param baseCategory the category of information to be retrieved, i.e. tunes, discussions, sessions, recordings or events
	 * @param searchTermsInput searchTermsInput the search terms collected from the user, as a single string
	 * @param resultsPerPage the number of search results to be returned per page, up to a maximum of 50
	 * @return the JSON response from the API as a String
	 * @throws IOException if a problem was encountered setting up the connection or reading the API response
	 * @throws MalformedURLException if an invalid URL has somehow been constructed
	 */
	public String submitSearchRequest(String baseCategory, String searchTermsInput, int resultsPerPage) throws IOException, MalformedURLException
		{				
		try 
			{
			// Clean up the search terms provided by the user
			String searchTermsFormatted = formatSearchTerms(searchTermsInput);
			
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL requestURL = new URL(baseURL + baseCategory + "/" + searchOperator + searchTermsFormatted + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage);
			
			// Call the API using a private helper method and store the response
			String response = getAPIResponse(requestURL);
			
			return response;
			}
	
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new IOException(e.getMessage());
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
	 * @throws IOException if a problem was encountered setting up the connection or reading the API response
	 * @throws MalformedURLException if an invalid URL has somehow been constructed
	 */
	public String submitSearchRequest(String baseCategory, String searchTermsInput, int resultsPerPage, int pageNumber) throws IOException, MalformedURLException
		{		
		try 
			{
			// Clean up the search terms provided by the user using a private helper method
			String searchTermsFormatted = formatSearchTerms(searchTermsInput);
		
			// Build the URL with all necessary parameters to perform a search via thesession.org API, specifying the page number
			URL requestURL = new URL(baseURL + baseCategory  + "/" + searchOperator + searchTermsFormatted + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage + "&page=" + pageNumber);
			
			// Call the API using a private helper method and store the response
			String response = getAPIResponse(requestURL);
			
			// This is the full JSON response from the API, as a single string
			return response;
			}
	
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new IOException(e.getMessage());
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
	 * @return the JSON response from the API as a String
	 * @throws IOException if a problem was encountered setting up the connection or reading the API response
	 * @throws MalformedURLException if an invalid URL has somehow been constructed
	 */
	public String submitLocationRequest(String baseCategory, String latitude, String longitude, String radius, int resultsPerPage) throws RuntimeException, MalformedURLException
		{			
		try 
			{		
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL requestURL = new URL(baseURL + baseCategory + "/" + latLonOperator + latitude + "," + longitude + radiusOperator + radius + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage);
			
			String response = getAPIResponse(requestURL);
						
			return response;
			} 
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new RuntimeException(e.getMessage());
			}
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
	 * @return the JSON response from the API as a String
	 * @throws IOException if a problem was encountered setting up the connection or reading the API response
	 * @throws MalformedURLException if an invalid URL has somehow been constructed
	 */
	public String submitLocationRequest(String baseCategory, String latitude, String longitude, String radius, int resultsPerPage, int pageNumber) throws IOException, MalformedURLException
		{			
		try 
			{
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL requestURL = new URL(baseURL + baseCategory + "/" + latLonOperator + latitude + "," + longitude + radiusOperator + radius + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage + "&page=" + pageNumber);

			// Store the response from the API
			String response = getAPIResponse(requestURL);
						
			// Return the API response as one long string of JSON data
			return response;
			} 
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new IOException(e.getMessage());
			}
		}
	
	
	/**
	 * Retrieves the most popular tunes on thesession.org and stores the resulting JSON response.
	 * The only data category supported via the API at the time of writing is tunes
	 * 
	 * @param resultsPerPage the number of search results to be returned per page, up to a maximum of 50
	 * @return the JSON response from the API as a String
	 * @throws IOException if a problem was encountered setting up the connection or reading the API response
	 * @throws MalformedURLException if an invalid URL has somehow been constructed
	 */
	public String submitPopularRequest(int resultsPerPage) throws IOException, MalformedURLException
		{				
		try 
			{				
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL requestURL = new URL(baseURL + "tunes" + "/" + popularOperator + "format=" + dataFormat + "&perpage=" + resultsPerPage);
			
			String response = getAPIResponse(requestURL);
			
			return response;
			}
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new IOException(e.getMessage());
			}
		}
	
	
	/**
	 * Alternative version of submitPopularRequest, allows the caller to retrieve a specific page within a paginated JSON response
	 * 
	 * @param resultsPerPage the number of search results to be returned per page, up to a maximum of 50
	 * @param pageNumber a specific page within the JSON response
	 * @return the JSON response from the API as a String
	 * @throws IOException if a problem was encountered setting up the connection or reading the API response
	 * @throws MalformedURLException if an invalid URL has somehow been constructed
	 */
	public String submitPopularRequest(int resultsPerPage, int pageNumber) throws IOException, MalformedURLException
		{		
		try 
			{
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL requestURL = new URL(baseURL + "tunes" + "/" + popularOperator + "format=" + dataFormat + "&perpage=" + resultsPerPage + "&page=" + pageNumber);
			
			String response = getAPIResponse(requestURL);
			
			return response;
			}
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new IOException(e.getMessage());
			}
		}
	
	
	/**
	 * Retrieves the a list of sets that have been put together by users on thesession.org and stores the resulting JSON response.
	 * 
	 * @param resultsPerPage the number of search results to be returned per page, up to a maximum of 50
	 * @return the JSON response from the API as a String
	 * @throws IOException if a problem was encountered setting up the connection or reading the API response
	 * @throws MalformedURLException if an invalid URL has somehow been constructed
	 * @throws URISyntaxException 
	 */
	public static String submitSetRequest(int resultsPerPage) throws IOException, MalformedURLException, URISyntaxException
		{				
		try 
			{	
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL requestURL = UrlBuilder.buildURL("tunes", "sets", resultsPerPage);
					
			String response = getAPIResponse(requestURL);
			
			return response;
			}
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new IOException(e.getMessage());
			}
		
		catch (URISyntaxException e)
			{
			throw new URISyntaxException(e.getInput(), e.getReason(), e.getIndex());
			}
		}
	
	
	/**
	 * Alternative version of submitSetRequest, allows the caller to retrieve a specific page within a paginated JSON response
	 * 
	 * @param resultsPerPage the number of search results to be returned per page, up to a maximum of 50
	 * @param pageNumber a specific page within the JSON response
	 * @return the JSON response from the API as a String
	 * @throws IOException if a problem was encountered setting up the connection or reading the API response
	 * @throws MalformedURLException if an invalid URL has somehow been constructed
	 * @throws URISyntaxException 
	 */
	public static String submitSetRequest(int resultsPerPage, int pageNumber) throws IOException, MalformedURLException, URISyntaxException
		{		
		try 
			{			
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL requestURL = UrlBuilder.buildURL("tunes", "sets", resultsPerPage, pageNumber);
			
			String response = getAPIResponse(requestURL);
			
			return response;
			}
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new IOException(e.getMessage());
			}
		
		catch (URISyntaxException e)
			{
			throw new URISyntaxException(e.getInput(), e.getReason(), e.getIndex());
			}
		}
	
	
	/**
	 * Retrieves the a list of member contributions in the chosen category thesession.org and stores the resulting JSON response.
	 * The data category may be tunes, discussions, recordings, sessions, events or sets.
	 * 
	 * @param baseCategory the category of information to be retrieved, i.e. tunes, discussions, sessions, recordings or events
	 * @param resultsPerPage the number of search results to be returned per page, up to a maximum of 50
	 * @return the JSON response from the API as a String
	 * @throws IOException if a problem was encountered setting up the connection or reading the API response
	 * @throws MalformedURLException if an invalid URL has somehow been constructed
	 */
	public String submitMemberContributionRequest(String baseCategory, int userID, int resultsPerPage) throws IOException, MalformedURLException
		{				
		try 
			{	
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL requestURL = new URL(baseURL + memberOperator + "/" + userID + "/" + baseCategory + "?format=" + dataFormat + "&perpage=" + resultsPerPage);

			String response = getAPIResponse(requestURL);
			
			return response;
			}
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new IOException(e.getMessage());
			}
		}
		
		
	/**
	 * Alternative version of submitMemberContributionRequest, allows the caller to retrieve a specific page within a paginated JSON response
	 * 
	 * @param baseCategory the category of information to be retrieved, i.e. tunes, discussions, sessions, recordings or events
	 * @param resultsPerPage the number of search results to be returned per page, up to a maximum of 50
	 * @param pageNumber a specific page within the JSON response
	 * @return the JSON response from the API as a String
	 * @throws IOException if a problem was encountered setting up the connection or reading the API response
	 * @throws MalformedURLException if an invalid URL has somehow been constructed
	 */
	public String submitMemberContributionRequest(String baseCategory, int userID, int resultsPerPage, int pageNumber) throws IOException, MalformedURLException
		{		
		try 
			{			
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL requestURL = new URL(baseURL + memberOperator + "/" + userID + "/" + baseCategory + "?format=" + dataFormat + "&perpage=" + resultsPerPage + "&page=" + pageNumber);
			
			String response = getAPIResponse(requestURL);
			
			return response;
			}
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new IOException(e.getMessage());
			}
		}
	

	/**
	 * A helper method used by all the other core methods in this class to build a HTTPS connection
	 * 
	 * @param tuneSearchURL a complete URL in the format required by the API at https://thesession.org
	 * @return the HTTPS connection to the API, from which the API response can be read
	 * @throws IOException if there is a problem setting up the connection or reading data from it
	 */
	private static HttpURLConnection buildConnection(URL tuneSearchURL) throws IOException
		{		
		try 
			{
			// Make the HTTP(S) connection to thesession.org
			HttpURLConnection connectionToURL = (HttpURLConnection) tuneSearchURL.openConnection();
			
			// Set parameters for the HTTPS connection
			connectionToURL.setRequestMethod("GET");
			connectionToURL.setRequestProperty("Accept", "application/" + dataFormat);
			
			//Assuming any HTTP response code other than 200 is a problem to be notified to the user
			if (connectionToURL.getResponseCode() != 200) 
				{	
				throw new IOException("HTTP error " + connectionToURL.getResponseCode());
				}
			
			// Return the HTTPS connection to the caller, in order to read the data from it
			return connectionToURL;
			} 
		
		catch (IOException e) 
			{
			throw new IOException(e.getMessage());
			}
		}
	
	
	/**
	 * A helper method used to retrieve the JSON response from the API and return it as a string
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
			BufferedReader inputReader = new BufferedReader(new InputStreamReader((connectionToURL.getInputStream()),"utf-8"));
			
			// Read the response line by line, using a StringBuilder to put it all together
			String resultCurrentLine;
			StringBuilder builder = new StringBuilder();
			
			while ((resultCurrentLine = inputReader.readLine()) != null) 
				{ 
				builder.append(resultCurrentLine);	
				}
			
			// Return the completed String containing the entire response from the API to the caller
			return builder.toString();
			}
		
		catch (IOException e)
			{
			throw new IOException(e.getMessage());
			}		
		}
	
	
	/**
	 * A helper method to format the search terms so that any spaces are replaced with '+' characters, as required by the API
	 * 
	 * @param searchTerms a string containing the user's search terms, may contain spaces
	 * @return a String with any spaces replaced with '+' characters
	 * @throws UnsupportedEncodingException 
	 */
	private String formatSearchTerms(String searchTerms) throws UnsupportedEncodingException
		{
		// The session.org API requires the + character between search terms in the URL
		String formattedSearchTerms = URLEncoder.encode(searchTerms, "UTF-8");
		
		return formattedSearchTerms;
		}
	
	
	/**
	 * Makes a HTTP connection to the API with the requested data in the URL, and get the response data
	 * 
	 * @param tuneSearchURL the URL used when querying the API
	 * @return the entire JSON response to the API query, returned as a single String
	 * @throws IOException if there was a problem establishing the connection to the API or reading a response
	 */
	private static String getAPIResponse(URL tuneSearchURL) throws MalformedURLException, IOException
		{	
		try
			{			
			// Create the HTTPS connection
			HttpURLConnection connectionToURL =  buildConnection(tuneSearchURL);
				
			// Store the response from the API as a String
			String response = captureResponse(connectionToURL);
			
			// We have our search results and can close the connection to the API.
			connectionToURL.disconnect();		
			
			// Return the API response as one long string of JSON data
			return response;
			}
	
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new IOException(e.getMessage());
			}
		}	
	}
