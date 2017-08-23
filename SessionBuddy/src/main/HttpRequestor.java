package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Submits a HTTP(S) request to the https://thesession.org API
 * The API allows the caller to specify the reponse format as JSON, XML or RSS.
 * Here we choose to get the response as JSON.
 * For further information about using this API, see https://thesession.org/API
 * 
 * @author Colman O'B
 * @since 2017-01-28
 */
public class HttpRequestor
	{	
	private static final String dataFormat = "json";
	private static final String baseURL = "https://thesession.org/"; 
	private static final String searchOperator = "search?q=";
	private String apiResponse;
	
	/**
	 * Used to retrieve a list of items from thesession.org, such as recordings, sessions etc.
	 * 
	 * @param baseCategory The category we are interested in, e.g. tunes, sessions, recordings etc.
	 * @param itemIdentifier If requesting an individual item, this is its ID number. Otherwise it will be a keyword passed in the URL
	 * @param resultsPerPage The number of results per page to be returned
	 * @return a String containing the JSON returned from the API
	 * @throws RuntimeException If a HTTP error is encountered
	 */
	public String submitListRequest(String baseCategory, String itemIdentifier, int resultsPerPage) throws RuntimeException
		{
		try 
			{			
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL tuneSearchURL = new URL(baseURL + baseCategory + "/" + itemIdentifier + "?" + "format=" + dataFormat + "&perpage=" + resultsPerPage);
			
			System.out.println(tuneSearchURL);
			
			// Make the HTTP(S) connection to thesession.org
			HttpURLConnection connectionToURL = (HttpURLConnection) tuneSearchURL.openConnection();
			connectionToURL.setRequestMethod("GET");
			connectionToURL.setRequestProperty("Accept", "application/" + dataFormat);
	
			//Assuming anything other than 200 is a problem to be notified to the user
			if (connectionToURL.getResponseCode() != 200) 
				{	
				throw new RuntimeException("A problem has occurred - HTTP error " + connectionToURL.getResponseCode());
				}
	
			//Read the data returned from the API into a BufferedReader
			BufferedReader inputReader = new BufferedReader(new InputStreamReader((connectionToURL.getInputStream()),"utf-8"));
			
			//Use a StringBuilder to build a string from the data in the BufferedReader
			String searchResults;
			StringBuilder builder = new StringBuilder();
			
			while ((searchResults = inputReader.readLine()) != null) 
				{ 
				builder.append(searchResults);	
				}
	
			apiResponse = builder.toString();
			
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
	 * Used to retrieve a list of items from thesession.org, such as recordings, sessions etc.
	 * 
	 * @param baseCategory The category we are interested in, e.g. tunes, sessions, recordings etc.
	 * @param itemIdentifier If requesting an individual item, this is its ID number. Otherwise it will be a keyword passed in the URL
	 * @param resultsPerPage The number of results per page to be returned
	 * @param pageNumber Specifies a particular page within the response from the API
	 * @return a String containing the JSON returned from the API
	 * @throws RuntimeException If a HTTP error is encountered
	 */
	public String submitListRequest(String baseCategory, String itemIdentifier, int resultsPerPage, int pageNumber) throws RuntimeException
		{
		try 
			{
			// Build the URL with all necessary parameters to perform a search via thesession.org API, specifying the page number
			URL tuneSearchURL = new URL(baseURL + baseCategory + "/" + itemIdentifier + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage + "&page=" + pageNumber);
		
			// Make the HTTP(S) connection to thesession.org
			HttpURLConnection connectionToURL = (HttpURLConnection) tuneSearchURL.openConnection();
			connectionToURL.setRequestMethod("GET");
			connectionToURL.setRequestProperty("Accept", "application/" + dataFormat);
	
			//Assuming anything other than 200 is a problem to be notified to the user
			if (connectionToURL.getResponseCode() != 200) 
				{	
				throw new RuntimeException("A problem has occurred - HTTP error " + connectionToURL.getResponseCode());
				}
	
			//Read the data returned from the API into a BufferedReader
			BufferedReader inputReader = new BufferedReader(new InputStreamReader((connectionToURL.getInputStream()),"utf-8"));
		
			//Use a StringBuilder to build a string from the data in the BufferedReader
			String searchResults;
			StringBuilder builder = new StringBuilder();
		
			while ((searchResults = inputReader.readLine()) != null) 
				{ 
				builder.append(searchResults);	
				}
	
			apiResponse = builder.toString();
		
			// We have our search results.  Close the connection to https://thesession.org
			connectionToURL.disconnect();		
			} 
	
		catch (MalformedURLException e) 
			{
			e.printStackTrace();
			} 
	
		catch (IOException e) 
			{
			e.printStackTrace();
			}
	
		// Return the API response as one long string of JSON data
		return apiResponse;
		}
	
	
	/**
	 * Used to retrieve an individual item by ID from thesession.org, such as a recording, session etc.
	 * 
	 * @param baseCategory The category we are interested in, e.g. tunes, sessions, recordings etc.
	 * @param itemIdentifier If requesting an individual item, this is its ID number. Otherwise it will be a keyword passed in the URL
	 * @return a String containing the JSON returned from the API
	 * @throws RuntimeException If a HTTP error is encountered
	 */
	public String submitListRequest(String baseCategory, String itemIdentifier) throws RuntimeException
		{
		try 
			{
			// Build the URL with all necessary parameters to perform a search via thesession.org API, specifying the page number
			URL tuneSearchURL = new URL(baseURL + baseCategory + "/" + itemIdentifier + "?" + "format=" + dataFormat);
		
			// Make the HTTP(S) connection to thesession.org
			HttpURLConnection connectionToURL = (HttpURLConnection) tuneSearchURL.openConnection();
			connectionToURL.setRequestMethod("GET");
			connectionToURL.setRequestProperty("Accept", "application/" + dataFormat);
	
			//Assuming anything other than 200 is a problem to be notified to the user
			if (connectionToURL.getResponseCode() != 200) 
				{	
				throw new RuntimeException("A problem has occurred - HTTP error " + connectionToURL.getResponseCode());
				}
	
			//Read the data returned from the API into a BufferedReader
			BufferedReader inputReader = new BufferedReader(new InputStreamReader((connectionToURL.getInputStream()),"utf-8"));
		
			//Use a StringBuilder to build a string from the data in the BufferedReader
			String searchResults;
			StringBuilder builder = new StringBuilder();
		
			while ((searchResults = inputReader.readLine()) != null) 
				{ 
				builder.append(searchResults);	
				}
	
			apiResponse = builder.toString();
		
			// We have our search results.  Close the connection to https://thesession.org
			connectionToURL.disconnect();		
			} 
	
		catch (MalformedURLException e) 
			{
			e.printStackTrace();
			} 
	
		catch (IOException e) 
			{
			e.printStackTrace();
			}
	
		// Return the API response as one long string of JSON data
		return apiResponse;
		}
		

	/**
	 * Used for performing a keyword-based search against a specific category of information on thesession.org
	 * Builds and submits an API query to https://thesession.org, attempting to match the user's search terms
	 * 
	 * @param searchTermsInput The user-input search terms used to match against the chosen category in thesession.org database
	 * @param resultsPerPage Specify how many results should be returned per page in the response from the API
	 * @param baseCategory The root category of information at thesession.org to be queried, i.e. tunes, discussions etc.
	 * @return The JSON returned from the API as one big String
	 * @throws RuntimeException If a HTTP error is encountered when making the API request
	 */
	public String submitSearchRequest(String baseCategory, String searchTermsInput, int resultsPerPage) throws RuntimeException
		{		
		try 
			{
			// The session.org API requires the + character between search terms in the URL
			String searchTermsFormatted = searchTermsInput.replace(" ","+"); 
			
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL tuneSearchURL = new URL(baseURL + baseCategory + "/" + searchOperator + searchTermsFormatted + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage);
			
			// Make the HTTP(S) connection to thesession.org
			HttpURLConnection connectionToURL = (HttpURLConnection) tuneSearchURL.openConnection();
			connectionToURL.setRequestMethod("GET");
			connectionToURL.setRequestProperty("Accept", "application/" + dataFormat);
	
			//Assuming anything other than 200 is a problem to be notified to the user
			if (connectionToURL.getResponseCode() != 200) 
				{	
				throw new RuntimeException("A problem has occurred - HTTP error " + connectionToURL.getResponseCode());
				}
	
			//Read the data returned from the API into a BufferedReader
			BufferedReader inputReader = new BufferedReader(new InputStreamReader((connectionToURL.getInputStream()),"utf-8"));
			
			//Use a StringBuilder to build a string from the data in the BufferedReader
			String searchResults;
			StringBuilder builder = new StringBuilder();
			
			while ((searchResults = inputReader.readLine()) != null) 
				{ 
				builder.append(searchResults);	
				}
	
			apiResponse = builder.toString();
			
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
	 * Alternative version of this method to allow passing in a particular page number, which can be used to iterate through multiple pages of results
	 * 
	 * @param searchTermsInput The user-input search terms used to match against tune titles in thesession.org database
	 * @param resultsPerPage Specify how many results should be returned per page in the response from the API
	 * @param pageNumber Specify which page number of the response should be accessed
	 * @return The JSON returned from the API as one big String
	 * @throws RuntimeException If a HTTP error is encountered when making the API request
	 */
	public String submitSearchRequest(String baseCategory, String searchTermsInput, int resultsPerPage, int pageNumber) throws RuntimeException
		{
		try 
			{
			// The session.org API requires the + character between search terms in the URL
			String searchTermsFormatted = searchTermsInput.replace(" ","+"); 
		
			// Build the URL with all necessary parameters to perform a search via thesession.org API, specifying the page number
			URL tuneSearchURL = new URL(baseURL + baseCategory + "/" + searchTermsFormatted + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage + "&page=" + pageNumber);
		
			// Make the HTTP(S) connection to thesession.org
			HttpURLConnection connectionToURL = (HttpURLConnection) tuneSearchURL.openConnection();
			connectionToURL.setRequestMethod("GET");
			connectionToURL.setRequestProperty("Accept", "application/" + dataFormat);
	
			//Assuming anything other than 200 is a problem to be notified to the user
			if (connectionToURL.getResponseCode() != 200) 
				{	
				throw new RuntimeException("A problem has occurred - HTTP error " + connectionToURL.getResponseCode());
				}
	
			//Read the data returned from the API into a BufferedReader
			BufferedReader inputReader = new BufferedReader(new InputStreamReader((connectionToURL.getInputStream()),"utf-8"));
		
			//Use a StringBuilder to build a string from the data in the BufferedReader
			String searchResults;
			StringBuilder builder = new StringBuilder();
		
			while ((searchResults = inputReader.readLine()) != null) 
				{ 
				builder.append(searchResults);	
				}
	
			apiResponse = builder.toString();
		
			// We have our search results.  Close the connection to https://thesession.org
			connectionToURL.disconnect();		
			} 
	
		catch (MalformedURLException e) 
			{
			e.printStackTrace();
			} 
	
		catch (IOException e) 
			{
			e.printStackTrace();
			}
	
		// Return the API response as one long string of JSON data
		return apiResponse;
		}
	
	
	//TODO: Complete the submitLocationRequest methods
	public String submitLocationRequest(String baseCategory, String searchTermsInput, int resultsPerPage) throws RuntimeException
		{		
		try 
			{
			// The session.org API requires the + character between search terms in the URL
			String searchTermsFormatted = searchTermsInput.replace(" ","+"); 
			
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL tuneSearchURL = new URL(baseURL + baseCategory + "/" + searchTermsFormatted + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage);
			
			// Make the HTTP(S) connection to thesession.org
			HttpURLConnection connectionToURL = (HttpURLConnection) tuneSearchURL.openConnection();
			connectionToURL.setRequestMethod("GET");
			connectionToURL.setRequestProperty("Accept", "application/" + dataFormat);
	
			//Assuming anything other than 200 is a problem to be notified to the user
			if (connectionToURL.getResponseCode() != 200) 
				{	
				throw new RuntimeException("A problem has occurred - HTTP error " + connectionToURL.getResponseCode());
				}
	
			//Read the data returned from the API into a BufferedReader
			BufferedReader inputReader = new BufferedReader(new InputStreamReader((connectionToURL.getInputStream()),"utf-8"));
			
			//Use a StringBuilder to build a string from the data in the BufferedReader
			String searchResults;
			StringBuilder builder = new StringBuilder();
			
			while ((searchResults = inputReader.readLine()) != null) 
				{ 
				builder.append(searchResults);	
				}
	
			apiResponse = builder.toString();
			
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

	/*public String submitLocationRequest(String baseCategory, String subCategory, String searchTermsInput, int resultsPerPage, int pageNumber) throws RuntimeException
		{

		} */
	}
