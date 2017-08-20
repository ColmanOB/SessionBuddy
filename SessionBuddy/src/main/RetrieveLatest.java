package main;

import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;

import json_object_wrappers.DiscussionDetails;
import json_object_wrappers.DiscussionsSearchResult;
import json_object_wrappers.TuneDetails;
import json_object_wrappers.TunesSearchResult;
import json_object_wrappers.User;
import response_parsers.DiscussionsSearchParser;
import response_parsers.TunesSearchParser;
import result_set_wrappers.DiscussionsSearchResultWrapper;
import result_set_wrappers.TunesSearchResultWrapper;

public class RetrieveLatest 
	{
	private int pageCount = 0;
	
	public ArrayList<TunesSearchResult> getLatestTunes(int resultsPerPage) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
	// Launch a search for a list of most recently submitted tunes and store the JSON that is returned as a String
	HttpRequestor searcher = new HttpRequestor();
	String apiQueryResults = searcher.submitSearchRequest("tunes", "new", resultsPerPage);
					
	// Parse the returned JSON into a wrapper class to allow access to all elements
	TunesSearchParser jsonParser = new TunesSearchParser();
	TunesSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
					
	// This will hold each individual search result entry
	ArrayList<TunesSearchResult> resultSet;
			
	try
		// Set up an ArrayList to hold the set of tune entries in the response
		{
		resultSet = new ArrayList <TunesSearchResult>();
		}
				
	catch (IllegalArgumentException e)
		// Catch any problem with Gson parsing the JSON input
		{
		throw new IllegalArgumentException(e.getMessage());
		}
				
	//Find out how many pages are in the response, to facilitate looping through multiple pages
	pageCount = Integer.parseInt(parsedResults.pages);
					
	// Loop as many times as the count of tunes in the result set:
	for(int i = 0; i < (parsedResults.tunes.length)-1; i++)
		{
		// Extract the required elements from each individual search result in the JSON response
		// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response
		TuneDetails details = new TuneDetails(parsedResults.tunes[i].id, StringEscapeUtils.unescapeXml(parsedResults.tunes[i].name), parsedResults.tunes[i].type, parsedResults.tunes[i].url, parsedResults.tunes[i].date);
		User submitter = new User(Integer.toString(parsedResults.tunes[i].member.id), StringEscapeUtils.unescapeXml(parsedResults.tunes[i].member.name), parsedResults.tunes[i].member.url);
								
		// Instantiate a TunesSearchResult object & populate it
		TunesSearchResult currentResult = new TunesSearchResult(details, submitter);
					
		// Add the TuneSearchResult object to the ArrayList to be returned to the caller
		resultSet.add(currentResult);
		}
				
	// Return the set of results that has been collected
	return resultSet;
	}

	
	public ArrayList<DiscussionsSearchResult> getLatestDiscussions(int resultsPerPage) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
	
		// Launch a search for a list of latest discussions and store the JSON response as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitSearchRequest("discussions", "new", resultsPerPage);
			
		// Instantiate a DiscussionSearchParser and DiscussionSearchResultWrapper needed to handle the raw JSON
		DiscussionsSearchParser jsonParser = new DiscussionsSearchParser();
		DiscussionsSearchResultWrapper parsedResults;
	
		try
			// Use a DiscussionSearchParser to parse the raw JSON into a usable structure using Gson
			{
			parsedResults = jsonParser.parseResponse(apiQueryResults);
			}
	
		catch (IllegalArgumentException e)
			// Catch any problem with Gson parsing the JSON input
			{
			throw new IllegalArgumentException(e.getMessage());
			}
	
	// This will hold each individual search result entry
		ArrayList<DiscussionsSearchResult> resultSet = new ArrayList <DiscussionsSearchResult>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages if needed
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
