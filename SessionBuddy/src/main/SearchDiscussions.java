package main;

import java.util.ArrayList;
import org.apache.commons.lang3.StringEscapeUtils;

import json_object_wrappers.DiscussionDetails;
import json_object_wrappers.DiscussionsSearchResult;
import json_object_wrappers.User;
import response_parsers.DiscussionsSearchParser;
import result_set_wrappers.DiscussionsSearchResultWrapper;

/**
 * Makes a call to the API at thesession.org to get a list of discussions matching a given set of search terms.
 * The number of results-per-page in the response can be specified, up to a maximum of 50.
 * An ArrayList of RecordingsSearchResults is returned in response to the executeSearch methods.
 * 
 * @author Colman O'B
 * @since 2017-01-26
 */
public class SearchDiscussions implements KeywordSearch <ArrayList<DiscussionsSearchResult>>
	{
	private int pageCount = 0;
	
	/**
	 * Launches a search for recordings matching a given set of search terms and returns the results as an ArrayList of RecordingsSearchReult objects
	 * 
	 * @param searchTerms Search terms are matched against recordings in thesession.org database
	 * @param resultsPerPage It is possible to specify up to 50 recording results per page in the API response
	 * @return An ArrayList of DiscussionsSearchResult objects
	 * @throws IllegalArgumentException The API at thesession.org can return a maximum of 50 results per page
	 */
	public ArrayList<DiscussionsSearchResult> executeSearch(String searchTerms, int resultsPerPage) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitSearchRequest("discussions", searchTerms, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		DiscussionsSearchParser jsonParser = new DiscussionsSearchParser();
		DiscussionsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
			
		// This will hold each individual search result entry
		ArrayList<DiscussionsSearchResult> resultSet = new ArrayList <DiscussionsSearchResult>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.discussions.length)-1; i++)
			{
			// Extract the elements from each individual search result in the JSON response
			// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response		
			DiscussionDetails details = new DiscussionDetails(parsedResults.discussions[i].id, StringEscapeUtils.unescapeXml(parsedResults.discussions[i].name), parsedResults.discussions[i].url, parsedResults.discussions[i].date, parsedResults.discussions[i].comments);
			User user = new User(Integer.toString(parsedResults.discussions[i].member.id), StringEscapeUtils.unescapeXml(parsedResults.discussions[i].member.name), parsedResults.discussions[i].member.url);
			
			// Instantiate a DiscussionsSearchResult object & populate it
			DiscussionsSearchResult currentResult = new DiscussionsSearchResult(details, user);
			
			// Add the DiscussionsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}
	
	// Alternative version of executeSearch() that accepts an additional argument to specify the page number in the result JSON
	/**
	 * Launches a search for recordings matching a given set of search terms and returns the results as an ArrayList of TunesSearchReult objects
	 * 
	 * @param searchTerms Search terms are matched against recordings in thesession.org database
	 * @param resultsPerPage It is possible to specify up to 50 recording results per page in the API response
	 * @param pageNumber Specifies an individual page number within the current set of results
	 * @return An ArrayList of DiscussionsSearchResult objects
	 * @throws IllegalArgumentException The API at thesession.org can return a maximum of 50 results per page
	 */
	public ArrayList<DiscussionsSearchResult> executeSearch(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitRequest("discussions",searchTerms, resultsPerPage,pageNumber);
		
		// Parse the returned JSON into a wrapper class to allow access to all elements
		DiscussionsSearchParser jsonParser = new DiscussionsSearchParser();
		DiscussionsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
		
		// This will hold each individual search result
		ArrayList<DiscussionsSearchResult> resultSet = new ArrayList <DiscussionsSearchResult>();
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.discussions.length)-1; i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			DiscussionDetails details = new DiscussionDetails(parsedResults.discussions[i].id, StringEscapeUtils.unescapeXml(parsedResults.discussions[i].name), parsedResults.discussions[i].url, parsedResults.discussions[i].date, parsedResults.discussions[i].comments);
			User user = new User(Integer.toString(parsedResults.discussions[i].member.id), StringEscapeUtils.unescapeXml(parsedResults.discussions[i].member.name), parsedResults.discussions[i].member.url);
			
			// Instantiate a DiscussionsSearchResult object & populate it
			DiscussionsSearchResult currentResult = new DiscussionsSearchResult(details, user);		
			// Add the DiscussionsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
	
		// Return the set of results that has been collected
		return resultSet;
		}
	
	
	/**
	 * Retrieves the number of pages of results returned from the API.
	 * This can be useful for looping through multiple pages of results.
	 * 
	 * @return The number of pages of results in the response
	 * @throws IllegalStateException Thrown if the pageCount variable has not been set
	 */
	public int getPageCount() throws IllegalStateException
		{
		if (pageCount == 0)
			{
			throw new IllegalStateException("Page counter has not been initialised");
			}
		else 
			return pageCount;
		}
}
