package response_parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import result_set_wrappers.RecordingsSearchResultWrapper;

// TODO: correct the field names below
/**
 * Uses GSON to parse a set of recordings search results from thesession.org API
 * 
 * You can access the following fields of the RecordingsSearchResultsWrapper.resultSet object:
 * 
 * resultSet.q		(the search terms used)
 * resultSet.pages	(number of pages of results)
 * resultSet.page	(current page within the results)
 * resultSet.recordings 	(the array of recordings returned within the results)
 *
 * The following fields are accessible for each individual recording within the array of tunes:
 * 
 * resultSet.recordings[].id		(unique ID for the tune within thesession.org database)
 * resultSet.tunes[].name	(the tune name within thesession.org database)
 * resultSet.tunes[].url	(the individual tune's unique URL on thesession.org website)
 * resultSet.tunes[].date	(the date the tune was submitted to thesession.org)
 * resultSet.tunes[].type	(the type of tune, e.g. jig, reel etc.)
 * resultSet.tunes[].member	(details of thesession.org member who submitted the tune)
 *
 * The following fields are accessible for each member (i.e. tune submitter):
 * 
 * resultSet.member.id		(the user's unique ID in thesession.org database)
 * resultSet.member.name	(the user's username for thesession.org)
 * resultSet.member.url		(the URL of the user's personal page on thesession.org)	
 * 
 * The following fields are accessible for each artist:
 * 
 * resultSet.member.id		(the user's unique ID in thesession.org database)
 * resultSet.member.name	(the user's username for thesession.org)
 * resultSet.member.url		(the URL of the user's personal page on thesession.org)	
 * 
 * @author Colman O'B
 * @since 2017-01-26
 */
public class RecordingsSearchParser 
	{
	// Instantiate a wrapper object with fields that exactly match those in the search results JSON string
	private RecordingsSearchResultWrapper resultSet;	
	
	/**
	 * Uses Gson to parse the JSON into a TunesSearchResultWrapper object
	 * 
	 * @param apiResponseString Pass in a string containing the JSON returned from a search of thesession.org API
	 * @return a TuneSearchResultWrapper object allowing access to any individual element of the response
	 */
	public RecordingsSearchResultWrapper parseResponse(String apiResponseString) 
		{
		// TODO: Add exception handling	
		Gson gson = new GsonBuilder().create();
		
		resultSet = gson.fromJson(apiResponseString, RecordingsSearchResultWrapper.class);
		
		// Return the TuneSearchResultWrapper object
		return resultSet;
		}
	}
