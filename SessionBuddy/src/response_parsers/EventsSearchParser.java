package response_parsers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import result_set_wrappers.EventsSearchResultWrapper;

/**
 * Uses GSON to parse a set of recordings search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class EventsSearchParser 
	{
	// Instantiate a wrapper object with fields that exactly match those in the search results JSON string
	private EventsSearchResultWrapper listOfResults;	
	
	/**
	 * Uses Gson to parse the JSON into a EventsSearchResultWrapper object
	 * 
	 * @param searchResultsString Pass in a string containing the JSON returned from a search of thesession.org API
	 * @return an EventsSearchResultWrapper object allowing access to any individual element of the response
	 */
	public EventsSearchResultWrapper parseResponse(String searchResultsString) 
		{
		// TODO: Add exception handling	
		Gson gson = new GsonBuilder().create();
		
		listOfResults = gson.fromJson(searchResultsString, EventsSearchResultWrapper.class);
		
		// Return the TuneSearchResultWrapper object
		return listOfResults;
		}
	}
