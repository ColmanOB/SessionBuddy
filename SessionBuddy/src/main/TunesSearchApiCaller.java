package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TunesSearchApiCaller
//Purpose: Search for a tune by querying the API at https://thesession.org for the tune's title
	//	Note: This API can return data as JSON, XML or RSS. I have chosen to use JSON
	//	For further information about using this API, see https://thesession.org/API
//-----*****-----*****-----*****-----*****-----*****-----*****-----*****-----//
//How to use:
	//	1.	Create a new TheSessionAPITuneSearch object
	//	2.	Call the searchForTune() to execute the API request
		// 2.1.	Pass in the search terms string, and an int (up to 50) specifying the number of results per page
	//	3.	Use a String variable to store the retrieved JSON string
	//	4.	In order to parse the JSON, create a new TheSessionAPISearchResultsParser object
		//	4.1.	Call the parseResultHeaders() on the Parser object, passing in the search results JSON string
		//	4.2.	You can now access any of the individual fields within the JSON structure by referencing the Parser's listOfResults object
		//	4.3.	To iterate through each individual tune, get a count of tunes in the result set by calling the Parser's listOfResults.tunes.length
//-----*****-----*****-----*****-----*****-----*****-----*****-----*****-----//
{	
String retrievedTuneList;
	
public String searchForTune(String searchTermsInput, int resultsPerPage)
	{
	final String dataFormat = "json"; 
	final String baseURL = "https://thesession.org/tunes/search?q="; 
	
	try 
		{
		// The session.org API requires the + character between search terms in the URL
		String searchTermsFormatted = searchTermsInput.replace(" ","+"); 
		
		// Build the URL with all necessary parameters to perform a search via thesession.org API
		URL tuneSearchURL = new URL(baseURL + searchTermsFormatted + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage);
		
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
		BufferedReader inputReader = new BufferedReader(new InputStreamReader((connectionToURL.getInputStream())));
		
		//Use a StringBuilder to build a string from the data in the BufferedReader
		String searchResults;
		StringBuilder builder = new StringBuilder();
		
		while ((searchResults = inputReader.readLine()) != null) 
			{ 
			builder.append(searchResults);	
			}

		retrievedTuneList = builder.toString();
		
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
	return retrievedTuneList;
	}
	
public String searchForTune(String searchTermsInput, int resultsPerPage, int pageNumber)
// Alternative version allowing the page number to be passed in, to support iterating through multiple pages of results
	{
	final String dataFormat = "json"; 
	final String baseURL = "https://thesession.org/tunes/search?q="; 

	try 
		{
		// The session.org API requires the + character between search terms in the URL
		String searchTermsFormatted = searchTermsInput.replace(" ","+"); 
	
		// Build the URL with all necessary parameters to perform a search via thesession.org API, specifying the page number
		URL tuneSearchURL = new URL(baseURL + searchTermsFormatted + "&" + "format=" + dataFormat + "&perpage=" + resultsPerPage + "&page=" + pageNumber);
	
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
		BufferedReader inputReader = new BufferedReader(new InputStreamReader((connectionToURL.getInputStream())));
	
		//Use a StringBuilder to build a string from the data in the BufferedReader
		String searchResults;
		StringBuilder builder = new StringBuilder();
	
		while ((searchResults = inputReader.readLine()) != null) 
			{ 
			builder.append(searchResults);	
			}

		retrievedTuneList = builder.toString();
	
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
	return retrievedTuneList;
	}
}
