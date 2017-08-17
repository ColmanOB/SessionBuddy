package response_parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import result_set_wrappers.DiscussionByIDWrapper;


public class DiscussionByIDParser 
{
	//TODO: Fix up these comments
	/**
	 * Uses GSON to parse a set of search results from thesession.org API into a Java "TheSessionAPISearchResultsWrapper" object
	 * 
	 * You can access the following fields of the TunesSearchParser.listOfResults object:
	 * 
	 * q		(the search terms used)
	 * pages	(number of pages of results)
	 * page		(current page within the results)
	 * tunes 	(the array of tunes returned within the results)
	 *
	 * The following fields are accessible for each individual tune within the array of tunes:
	 * 
	 * tunes[].id		(unique ID for the tune within thesession.org database)
	 * tunes[].name		(the tune name within thesession.org database)
	 * tunes[].url		(the individual tune's unique URL on thesession.org)
	 * tunes[].date		(the date the tune was submitted)
	 * tunes[].type		(the type of tune, e.g. jig, reel etc.)
	 * tunes[].member	(details of thesession.org member who submitted the tune)
	 *
	 * The following fields are accessible for each member (i.e. tune submitter):
	 * 
	 * member.id	(Numeric ID of the user in thesession.org database)
	 * member.name	(the user's username for thesession.org)
	 * member.url	(the URL of the user's profile page on thesession.org)	
	 * 
	 * @author Colman O'B
	 * @since 2017-08-13
	 */
		// Instantiate a wrapper object with fields that exactly match those in the search results JSON string
		private DiscussionByIDWrapper listOfResults;	
		
		/**
		 * Uses Gson to parse the JSON into a TuneByIDWrapper object
		 * 
		 * @param tuneDetailsString Pass in a string containing the JSON returned from thesession.org API
		 * @return a TuneByIDWrapper object allowing access to any individual element of the response
		 */
		public DiscussionByIDWrapper parseResponse(String discussionDetailsString) 
			{
			Gson gson = new GsonBuilder().create();
			
			try
			// Populate the Gson object using the string of JSON returned from the API
				{
				listOfResults = gson.fromJson(discussionDetailsString, DiscussionByIDWrapper.class);
				}
			
			catch (JsonSyntaxException e)
			// Catch a case where the API returns something that is not valid JSON that matches the structure of a TuneByIDWrapper
				{		
				throw new IllegalArgumentException(e.getMessage());
				}
			
			// Return the TuneByIDWrapper object
			return listOfResults;
			}
		}


