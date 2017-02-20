package response_parsers_keyword_search;

// TODO: Fix up comments here

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import result_wrappers_keyword_search.DiscussionsSearchResultWrapper;


// TODO: correct the field names below
/**
 * Uses GSON to parse a set of discussion search results from thesession.org API
 * 
 * You can access the following fields of the RecordingsSearchResultsWrapper.listOfResults object:
 * 
 * listOfResults.q		(the search terms used)
 * listOfResults.pages	(number of pages of results)
 * listOfResults.page	(current page within the results)
 * listOfResults.recordings 	(the array of recordings returned within the results)
 *
 * The following fields are accessible for each individual recording within the array of tunes:
 * 
 * listOfResults.recordings[].id		(unique ID for the tune within thesession.org database)
 * listOfResults.tunes[].name	(the tune name within thesession.org database)
 * listOfResults.tunes[].url	(the individual tune's unique URL on thesession.org website)
 * listOfResults.tunes[].date	(the date the tune was submitted to thesession.org)
 * listOfResults.tunes[].type	(the type of tune, e.g. jig, reel etc.)
 * listOfResults.tunes[].member	(details of thesession.org member who submitted the tune)
 *
 * The following fields are accessible for each member (i.e. tune submitter):
 * 
 * listOfResults.member.id		(the user's unique ID in thesession.org database)
 * listOfResults.member.name	(the user's username for thesession.org)
 * listOfResults.member.url		(the URL of the user's personal page on thesession.org)	
 * 
 * The following fields are accessible for each artist:
 * 
 * listOfResults.member.id		(the user's unique ID in thesession.org database)
 * listOfResults.member.name	(the user's username for thesession.org)
 * listOfResults.member.url		(the URL of the user's personal page on thesession.org)	
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class DiscussionsSearchParser 
	{
	// Instantiate a wrapper object with fields that exactly match those in the search results JSON string
	private DiscussionsSearchResultWrapper listOfResults;	
	
	/**
	 * Uses Gson to parse the JSON into a EventsSearchResultWrapper object
	 * 
	 * @param searchResultsString Pass in a string containing the JSON returned from a search of thesession.org API
	 * @return a DiscussionsSearchResultWrapper object allowing access to any individual element of the response
	 */
	public DiscussionsSearchResultWrapper parseResponse(String searchResultsString) 
		{
		// TODO: Add exception handling	
		Gson gson = new GsonBuilder().create();
		
		listOfResults = gson.fromJson(searchResultsString, DiscussionsSearchResultWrapper.class);
		
		// Return the TuneSearchResultWrapper object
		return listOfResults;
		}
	}
