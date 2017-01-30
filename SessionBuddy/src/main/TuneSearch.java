package main;

import java.util.ArrayList;
import org.apache.commons.lang3.StringEscapeUtils;

import individual_result_representation.TunesSearchResult;
import response_parsers.TunesSearchParser;
import result_set_wrappers.TunesSearchResultWrapper;

/**
 * Makes a call to the API at thesession.org to get a list of tunes matching a given set of search terms.
 * The number of results-per-page in the response can be specified, up to a maximum of 50.
 * An ArrayList of TunesSearchResults is returned in response to the executeSearch methods.
 * 
 * @author Colman O'B
 * @since 2017-01-26
 */
public class TuneSearch
	{
	private int pageCount = 0;
	
	/**
	 * Launches a search for tunes matching a given set of search terms and returns the results as an ArrayList of TunesSearchReult objects
	 * 
	 * @param searchTerms Search terms are matched against tune titles in thesession.org database
	 * @param resultsPerPage It is possible to specify up to 50 tune results per page in the API response
	 * @return An ArrayList of TunesSearchResult objects
	 * @throws IllegalArgumentException The API at thesession.org can return a maximum of 50 results per page
	 */
	public ArrayList<TunesSearchResult> executeSearch(String searchTerms, int resultsPerPage) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Launch a search for a list of matching tunes and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitSearchRequest("tunes", searchTerms, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		TunesSearchParser jsonParser = new TunesSearchParser();
		TunesSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
			
		// This will hold each individual search result entry
		ArrayList<TunesSearchResult> resultSet = new ArrayList <TunesSearchResult>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of tunes in the result set:
		for(int i = 0; i < (parsedResults.tunes.length)-1; i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response
			String tuneID = parsedResults.tunes[i].id;
			String title = StringEscapeUtils.unescapeXml(parsedResults.tunes[i].name);
			String type = parsedResults.tunes[i].type;
			String url = parsedResults.tunes[i].url;
			String date = parsedResults.tunes[i].date;
			
			String submitterID = Integer.toString(parsedResults.tunes[i].member.id);
			String submitterName = StringEscapeUtils.unescapeXml(parsedResults.tunes[i].member.name);
			String submitterProfile = parsedResults.tunes[i].member.url;
			
			
			// Instantiate a TunesSearchResult object & populate it
			TunesSearchResult currentResult = new TunesSearchResult(tuneID,title,type,url,date,submitterID,submitterName,submitterProfile);
			
			// Add the TuneSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}
	
	// Alternative version of executeSearch() that accepts an additional argument to specify the page number in the result JSON
	/**
	 * Launches a search for tunes matching a given set of search terms and returns the results as an ArrayList of TunesSearchReult objects
	 * 
	 * @param searchTerms Search terms are matched against tune titles in thesession.org database
	 * @param resultsPerPage It is possible to specify up to 50 tune results per page in the API response
	 * @param pageNumber Specifies an individual page number within the current set of results
	 * @return An ArrayList of TunesSearchResult objects
	 * @throws IllegalArgumentException The API at thesession.org can return a maximum of 50 results per page
	 */
	public ArrayList<TunesSearchResult> executeSearch(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Launch a search for a list of matching tunes and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitRequest("tunes",searchTerms, resultsPerPage,pageNumber);
		
		// Parse the returned JSON into a wrapper class to allow access to all elements
		TunesSearchParser jsonParser = new TunesSearchParser();
		TunesSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
		
		// This will hold each individual search result
		ArrayList<TunesSearchResult> resultSet = new ArrayList <TunesSearchResult>();
			
		// Loop as many times as the count of tunes in the result set:
		for(int i = 0; i < (parsedResults.tunes.length)-1; i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			String tuneID = parsedResults.tunes[i].id;
			String title = StringEscapeUtils.unescapeXml(parsedResults.tunes[i].name);
			String type = parsedResults.tunes[i].type;
			String url = parsedResults.tunes[i].url;
			String date = parsedResults.tunes[i].date;
			
			String submitterID = Integer.toString(parsedResults.tunes[i].member.id);
			String submitterName = StringEscapeUtils.unescapeXml(parsedResults.tunes[i].member.name);
			String submitterProfile = parsedResults.tunes[i].member.url;
			
			
			// Instantiate a TunesSearchResult object & populate it
			TunesSearchResult currentResult = new TunesSearchResult(tuneID,title,type,url,date,submitterID,submitterName,submitterProfile);
						
			// Add the TuneSearchResult object to the ArrayList to be returned to the caller
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