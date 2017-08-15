package main;

import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;

import json_object_wrappers.TuneByIDResult;
import json_object_wrappers.TuneSetting;
import json_object_wrappers.User;
import response_parsers.TuneByIDParser;
import result_set_wrappers.TuneByIDWrapper;


public class RetrieveItem 
{
private int pageCount = 0;

	public ArrayList<TuneByIDResult> getTuneByID(String searchTerms, String tuneID, int resultsPerPage) throws IllegalArgumentException
	{
	if (resultsPerPage > 50)
		{
		throw new IllegalArgumentException("Number of results per page must be 50 or less");
		}

	// Launch a search for a list of matching tunes and store the JSON that is returned as a String
	HttpRequestor searcher = new HttpRequestor();
	String apiQueryResults = searcher.submitListRequest("tunes", tuneID, resultsPerPage);
		
	// Parse the returned JSON into a wrapper class to allow access to all elements
	TuneByIDParser jsonParser = new TuneByIDParser();
	TuneByIDWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);

	// This will hold each individual search result entry
	ArrayList<TuneByIDResult> resultSet;
	
	try
		// Use a TunesSearchParser to parse the raw JSON into a usable structure using Gson
		{
		resultSet = new ArrayList <TuneByIDResult>();
		}
	
	catch (IllegalArgumentException e)
		// Catch any problem with Gson parsing the JSON input
		{
		throw new IllegalArgumentException(e.getMessage());
		}
		
	// Loop as many times as the count of tunes in the result set:
	for(int i = 0; i < (parsedResults.settings.length)-1; i++)
		{
		// Extract each element from the list of seetings in the JSON response
		// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response
		String id = (parsedResults.id); 
		String name = (parsedResults.name);
		String url = (parsedResults.url);
		
		User member = (parsedResults.member);
		
		String date = (parsedResults.date);
		String type = (parsedResults.type);
		String tunebooks = (parsedResults.tunebooks);
		String recordings = (parsedResults.recordings);
		String[] aliases = (parsedResults.aliases);
		
		TuneSetting[] settings = (parsedResults.settings);
		
		// Instantiate a TuneByIDResult object & populate it with the details captured above
		TuneByIDResult currentResult = new TuneByIDResult(id, name, url, member, date, type, tunebooks, recordings, aliases, settings);
		
		// Add the TuneSearchResult object to the ArrayList to be returned to the caller
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
