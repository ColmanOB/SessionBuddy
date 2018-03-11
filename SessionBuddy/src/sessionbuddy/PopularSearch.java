package sessionbuddy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import sessionbuddy.utils.HttpRequestor;
import sessionbuddy.utils.JsonResponseParser;
import sessionbuddy.utils.StringCleaner;
import sessionbuddy.utils.UrlBuilder;
import sessionbuddy.wrappers.granularobjects.PopularTuneDetails;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.jsonresponse.PopularWrapperTunes;
import sessionbuddy.wrappers.resultsets.PopularTunes;


/**
 * Retrieves the current most popular tunes, i.e. those that have been added to the largest number of user tune books on thesession.org
 * 
 * @author Colman
 * @since 2018-03-11
 */
public class PopularSearch extends Search
	{
	/**
	 * Retrieves a list of the most popular tunes on thesession.org, i.e. those that have been added to the most user tunebooks.
	 * 
	 * @param resultsPerPage the number of results that should be returned per page in the JSON response
	 * @return an ArrayList of PopularTunes objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 */
	public ArrayList<PopularTunes> getPopularTunes(int resultsPerPage) throws IllegalArgumentException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
	
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL requestURL = UrlBuilder.buildURL("tunes", "popular", resultsPerPage);
			String response = HttpRequestor.submitRequest(requestURL);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			PopularWrapperTunes parsedResults = JsonResponseParser.parseResponse(response, PopularWrapperTunes.class);
								
			// This will hold each individual search result entry
			ArrayList<PopularTunes> resultSet = new ArrayList<PopularTunes>();
			
			resultSet = populateTunesSearchResult(parsedResults);
			
			return resultSet;
			}
		
		catch (IllegalArgumentException | IOException | URISyntaxException ex)
			{
			throw ex;
			}
		}
	
	/**
	 * An alternative version of getPopularTunes, allowing the caller to specify a page number within the JSON reponse from the API
	 * 
	 * @param resultsPerPage the number of results that should be returned per page in the JSON response
	 * @param pageNumber specifies the page to be retrieved, where a result set spans multiple pages
	 * @return an ArrayList of PopularTunes objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 */
	public ArrayList<PopularTunes> getPopularTunes(int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
	
			// Build the URL with all necessary parameters to perform a search via thesession.org API
			URL requestURL = UrlBuilder.buildURL("tunes", "popular", resultsPerPage, pageNumber);
			String response = HttpRequestor.submitRequest(requestURL);
			
			// Parse the returned JSON into a wrapper class to allow access to all elements
			PopularWrapperTunes parsedResults = JsonResponseParser.parseResponse(response, PopularWrapperTunes.class);
								
			// This will hold each individual search result entry
			ArrayList<PopularTunes> resultSet = new ArrayList<PopularTunes>();
			
			resultSet = populateTunesSearchResult(parsedResults);
			
			return resultSet;
			}
		
		catch (IllegalArgumentException | IOException | URISyntaxException ex)
			{
			throw ex;
			}
		}
	
	
	/**
	 * Helper method to gather and parse the response to a search for popular tunes
	 * 
	 * @param parsedResults a PopularWrapperTunes object that has already been created an populated
	 * @return an ArrayList of PopularTunes objects
	 */
	private ArrayList<PopularTunes> populateTunesSearchResult(PopularWrapperTunes parsedResults)
		{
		ArrayList <PopularTunes> resultSet = new ArrayList <PopularTunes>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of tunes in the result set:
		for(int i = 0; i < parsedResults.tunes.length; i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
			PopularTuneDetails details = new PopularTuneDetails(parsedResults.tunes[i].id, StringCleaner.cleanString(parsedResults.tunes[i].name), parsedResults.tunes[i].url, parsedResults.tunes[i].date, parsedResults.tunes[i].type, parsedResults.tunes[i].tunebooks);
			User submitter = new User(Integer.toString(parsedResults.tunes[i].member.id), StringCleaner.cleanString(parsedResults.tunes[i].member.name), parsedResults.tunes[i].member.url);
			
			// Instantiate a PopularTunes object & populate it
			PopularTunes currentResult = new PopularTunes(details, submitter);
			
			// Add the TuneSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the fully populated ArrayList
		return resultSet;
		}
	}
