package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Submits a HTTPS request to the API at https://thesession.org
 * The API allows the caller to specify the response format as JSON, XML or RSS.
 * This library only supports retrieving the response as JSON.
 * For further information about using this API, see https://thesession.org/API
 * 
 * @author Colman O'B
 * @since 2017-08-25
 */
public class HttpRequestor
	{	
	private static final String dataFormat = "json";
	private static final String baseURL = "https://thesession.org/"; 
	private static final String searchOperator = "search?q=";
	private static final String latLonOperator = "nearby?latlon=";
	private static final String radiusOperator = "&radius=";
	private String apiResponse;  /* Delete this variable after refactoring */
	

	/**
	 * Used to retrieve details of an individual item by ID from thesession.org.
	 * The item may be a tune, discussion, recording, session or event.
	 * 
	 * @param baseCategory
	 * @param itemIdentifier
	 * @return
	 * @throws RuntimeException
	 * @throws MalformedURLException 
	 */
	public String submitListRequest(String baseCategory, String itemIdentifier) throws RuntimeException, MalformedURLException
		{
		URL tuneSearchURL; 	// The correctly-formatted URL for performing the tune search
		String response;	// A string of JSON data returned from the API
		
		try 
			{
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			tuneSearchURL = new URL(baseURL + baseCategory + "/" + itemIdentifier + "?" + "format=" + dataFormat);
			} 
		
		catch (MalformedURLException e) 
			{
			throw new MalformedURLException();
			}
		
		try 
			{
			// Call the API and store the response
			response = getAPIResponse(tuneSearchURL);
			}
		
		catch(RuntimeException e)
			{
			throw new RuntimeException();
			}
		
		// The JSON data returned by the API, stored as a single string
		return response;
		}
		
	
	/**
	 * Used for performing a keyword-based search against a specific category of information on thesession.org
	 * 
	 * @param baseCategory
	 * @param searchTermsInput
	 * @param resultsPerPage
	 * @return
	 * @throws RuntimeException
	 */
	public String submitSearchRequest(String baseCategory, String searchTermsInput, int resultsPerPage) throws RuntimeException
		{		
		try 
			{
			// Clean up the search terms provided by the user
			String searchTermsFormatted = formatSearchTerms(searchTermsInput);
			
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL tuneSearchURL = new URL(baseURL + baseCategory + "/" + searchOperator + searchTermsFormatted + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage);
			
			// Use the buildConnection method to create the HTTPS connection
			HttpURLConnection connectionToURL =  buildConnection(tuneSearchURL);
	
			// Store the response from the API
			apiResponse = captureResponse(connectionToURL);
			
			// We have our search results.  Close the connection to https://thesession.org
			connectionToURL.disconnect();		
			} 
		
		catch (MalformedURLException e) 
			{
			throw new RuntimeException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new RuntimeException(e.getMessage());
			}
		
		// Return the API response as one long string of JSON data
		return apiResponse;
		}
	
		
	/**
	 * Used for performing a keyword-based search against a specific category of information on thesession.org, specifying a particular page within the result set
	 * 
	 * @param baseCategory
	 * @param searchTermsInput
	 * @param resultsPerPage
	 * @param pageNumber
	 * @return
	 * @throws RuntimeException
	 */
	public String submitSearchRequest(String baseCategory, String searchTermsInput, int resultsPerPage, int pageNumber) throws RuntimeException
		{
		try 
			{
			// Clean up the search terms provided by the user
			String searchTermsFormatted = formatSearchTerms(searchTermsInput);
		
			// Build the URL with all necessary parameters to perform a search via thesession.org API, specifying the page number
			URL tuneSearchURL = new URL(baseURL + baseCategory + "/" + searchTermsFormatted + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage + "&page=" + pageNumber);
		
			// Use the buildConnection method to create the HTTPS connection
			HttpURLConnection connectionToURL =  buildConnection(tuneSearchURL);
			
			// Store the response from the API
			apiResponse = captureResponse(connectionToURL);
		
			// We have our search results.  Close the connection to https://thesession.org
			connectionToURL.disconnect();		
			} 
	
		catch (MalformedURLException e) 
			{
			throw new RuntimeException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new RuntimeException(e.getMessage());
			}
	
		// Return the API response as one long string of JSON data
		return apiResponse;
		}
	
	
	/**
	 * @param baseCategory
	 * @param searchTermsInput
	 * @param resultsPerPage
	 * @return
	 * @throws RuntimeException
	 */
	public String submitLocationRequest(String baseCategory, String latitude, String longitude, String radius, int resultsPerPage) throws RuntimeException
		{		
		try 
			{			
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL tuneSearchURL = new URL(baseURL + baseCategory + "/" + latLonOperator + latitude + "," + longitude + radiusOperator + radius + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage);
			
			// Use the buildConnection method to create the HTTPS connection
			HttpURLConnection connectionToURL =  buildConnection(tuneSearchURL);
			
			// Store the response from the API
			apiResponse = captureResponse(connectionToURL);
			
			// We have our search results.  Close the connection to https://thesession.org
			connectionToURL.disconnect();		
			} 
		
		catch (MalformedURLException e) 
			{
			throw new RuntimeException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new RuntimeException(e.getMessage());
			}
		
		// Return the API response as one long string of JSON data
		return apiResponse;
		}

	
	/**
	 * @param baseCategory
	 * @param searchTermsInput
	 * @param resultsPerPage
	 * @param pageNumber
	 * @return
	 * @throws RuntimeException
	 */
	public String submitLocationRequest(String baseCategory, String latitude, String longitude, String radius, int resultsPerPage, int pageNumber) throws RuntimeException
		{		
		try 
			{
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL tuneSearchURL = new URL(baseURL + baseCategory + "/" + latLonOperator + latitude + "," + longitude + radiusOperator + radius + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage + "&page=" + pageNumber);

			// Use the buildConnection method to create the HTTPS connection
			HttpURLConnection connectionToURL =  buildConnection(tuneSearchURL);
			
			// Store the response from the API
			apiResponse = captureResponse(connectionToURL);
			
			// We have our search results.  Close the connection to https://thesession.org
			connectionToURL.disconnect();		
			} 
		
		catch (MalformedURLException e) 
			{
			throw new RuntimeException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new RuntimeException(e.getMessage());
			}
		
		// Return the API response as one long string of JSON data
		return apiResponse;
		}

	
	/**
	 * A helper method used by all the other core methods in this class to build a HTTPS connection
	 * 
	 * @param tuneSearchURL
	 * @return
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
		
		return connectionToURL;
		}
	
	
	/**
	 * A helper method used to retrieve the response from the API and return it as a string
	 * 
	 * @param connectionToURL
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	private String captureResponse(HttpURLConnection connectionToURL) throws UnsupportedEncodingException, IOException
		{
		BufferedReader inputReader = new BufferedReader(new InputStreamReader((connectionToURL.getInputStream()),"utf-8"));
		
		//Use a StringBuilder to build a string from the data in the BufferedReader
		String searchResults;
		StringBuilder builder = new StringBuilder();
		
		while ((searchResults = inputReader.readLine()) != null) 
			{ 
			builder.append(searchResults);	
			}
	
		return builder.toString();
		}
	
	
	/**
	 * @param searchTerms
	 * @return
	 */
	private String formatSearchTerms(String searchTerms)
		{
		// The session.org API requires the + character between search terms in the URL
		String formattedSearchTerms = searchTerms.replace(" ","+"); 
		
		return formattedSearchTerms;
		}
	
	
	/**
	 * @param tuneSearchURL
	 * @return
	 */
	private String getAPIResponse(URL tuneSearchURL) throws RuntimeException
		{	
		try
			{
			// Use the buildConnection method to create the HTTPS connection
			HttpURLConnection connectionToURL =  buildConnection(tuneSearchURL);
				
			// Store the response from the API
			apiResponse = captureResponse(connectionToURL);
			
			// We have our search results.  Close the connection to https://thesession.org
			connectionToURL.disconnect();		
			}
	
		catch (MalformedURLException e) 
			{
			throw new RuntimeException(e.getMessage());
			} 
	
		catch (IOException e) 
			{
			throw new RuntimeException(e.getMessage());
			}
	
		// Return the API response as one long string of JSON data
		return apiResponse;
		}
	
	}
