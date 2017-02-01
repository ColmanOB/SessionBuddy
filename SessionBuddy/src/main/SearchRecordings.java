package main;

import java.util.ArrayList;
import org.apache.commons.lang3.StringEscapeUtils;

import individual_result_representation.Artist;
import individual_result_representation.RecordingDetails;
import individual_result_representation.RecordingsSearchResult;
import individual_result_representation.User;
import response_parsers.RecordingsSearchParser;
import result_set_wrappers.RecordingsSearchResultWrapper;

/**
 * Makes a call to the API at thesession.org to get a list of recordings matching a given set of search terms.
 * The number of results-per-page in the response can be specified, up to a maximum of 50.
 * An ArrayList of RecordingsSearchResults is returned in response to the executeSearch methods.
 * 
 * @author Colman O'B
 * @since 2017-01-26
 */
public class SearchRecordings
	{
	private int pageCount = 0;
	
	/**
	 * Launches a search for recordings matching a given set of search terms and returns the results as an ArrayList of RecordingsSearchReult objects
	 * 
	 * @param searchTerms Search terms are matched against recordings in thesession.org database
	 * @param resultsPerPage It is possible to specify up to 50 recording results per page in the API response
	 * @return An ArrayList of RecordingsSearchResult objects
	 * @throws IllegalArgumentException The API at thesession.org can return a maximum of 50 results per page
	 */
	public ArrayList<RecordingsSearchResult> executeSearch(String searchTerms, int resultsPerPage) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitSearchRequest("recordings", searchTerms, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		RecordingsSearchParser jsonParser = new RecordingsSearchParser();
		RecordingsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
			
		// This will hold each individual search result entry
		ArrayList<RecordingsSearchResult> resultSet = new ArrayList <RecordingsSearchResult>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.recordings.length)-1; i++)
			{
			// Extract the elements from each individual search result in the JSON response
			// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response		
			RecordingDetails details = new RecordingDetails(parsedResults.recordings[i].id, StringEscapeUtils.unescapeXml(parsedResults.recordings[i].name), parsedResults.recordings[i].url, parsedResults.recordings[i].date);
			User user = new User(Integer.toString(parsedResults.recordings[i].member.id), StringEscapeUtils.unescapeXml(parsedResults.recordings[i].member.name), parsedResults.recordings[i].member.url);
			Artist artist = new Artist(Integer.toString(parsedResults.recordings[i].artist.id), StringEscapeUtils.unescapeXml(parsedResults.recordings[i].artist.name), parsedResults.recordings[i].artist.url);
			
			// Instantiate a RecordingsSearchResult object & populate it
			RecordingsSearchResult currentResult = new RecordingsSearchResult(details, user, artist);
			
			// Add the RecordingsSearchResult object to the ArrayList to be returned to the caller
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
	 * @return An ArrayList of RecordingsSearchResult objects
	 * @throws IllegalArgumentException The API at thesession.org can return a maximum of 50 results per page
	 */
	public ArrayList<RecordingsSearchResult> executeSearch(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitRequest("recordings",searchTerms, resultsPerPage,pageNumber);
		
		// Parse the returned JSON into a wrapper class to allow access to all elements
		RecordingsSearchParser jsonParser = new RecordingsSearchParser();
		RecordingsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
		
		// This will hold each individual search result
		ArrayList<RecordingsSearchResult> resultSet = new ArrayList <RecordingsSearchResult>();
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.recordings.length)-1; i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			RecordingDetails details = new RecordingDetails(parsedResults.recordings[i].id, StringEscapeUtils.unescapeXml(parsedResults.recordings[i].name), parsedResults.recordings[i].url, parsedResults.recordings[i].date);
			User user = new User(Integer.toString(parsedResults.recordings[i].member.id), StringEscapeUtils.unescapeXml(parsedResults.recordings[i].member.name), parsedResults.recordings[i].member.url);
			Artist artist = new Artist(Integer.toString(parsedResults.recordings[i].artist.id), StringEscapeUtils.unescapeXml(parsedResults.recordings[i].artist.name), parsedResults.recordings[i].artist.url);
			
			// Instantiate a RecordingsSearchResult object & populate it
			RecordingsSearchResult currentResult = new RecordingsSearchResult(details, user, artist);		
			// Add the RecordingsSearchResult object to the ArrayList to be returned to the caller
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
