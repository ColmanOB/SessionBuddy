package response_parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import result_set_wrappers.DiscussionsSearchResultWrapper;

/**
 * Uses GSON to parse a set of discussion search results from thesession.org API into a usable structure
 * 
 * You can access the following fields of the DiscussionsSearchResultsWrapper object:
 * 
 * q		(the search terms used)
 * pages	(number of pages of results)
 * page		(current page within the results)
 * discussions 	(the array of discussions returned within the results)
 *
 * The following fields are accessible for each individual discussion within the array of tunes:
 * 
 * discussions[].id		(unique ID for the tune within thesession.org database)
 * discussions[].name	(the discussion's title)
 * discussions[].url	(the individual tune's unique URL on thesession.org)
 * 
 * discussions[].date		(date the discussion was submitted to thesession.org)
 * discussions[].comments	(the number of comments in the thread)
 * discussions[].member		(details of thesession.org member who submitted the tune)
 *
 * The following fields are accessible for each member (i.e. discussion submitter):
 * 
 * discussions[].member.id		(the user's unique ID in thesession.org database)
 * discussions[].member.name	(the user's username for thesession.org)
 * discussions[].member.url		(the URL of the user's profile page on thesession.org)	
 * 
 * @author Colman O'B
 * @since 2017-08-13
 */
public class DiscussionsSearchParser 
	{
	// Instantiate a wrapper object with fields that exactly match those in the search results JSON string
	private DiscussionsSearchResultWrapper listOfResults;	
	
	/**
	 * Uses Gson to parse the JSON into a DiscussionsSearchResultWrapper object
	 * 
	 * @param searchResultsString Pass in a string containing the JSON returned from a search of thesession.org API
	 * @return a DiscussionsSearchResultWrapper object allowing access to any individual element of the response
	 * @exception IllegalArgumentException if the data retrieved from the API is not valid JSON matching the structure of a DiscussionsSearchResultWrapper
	 */
	public DiscussionsSearchResultWrapper parseResponse(String searchResultsString) throws IllegalArgumentException
		{	
		Gson gson = new GsonBuilder().create();
		
		try
		// Populate the Gson object using the string of JSON returned from the API
			{
			listOfResults = gson.fromJson(searchResultsString, DiscussionsSearchResultWrapper.class);
			}
		
		catch (JsonSyntaxException e)
		// Catch a case where the API returns something that is not valid JSON that matches the structure of a DiscussionsSearchResultWrapper
			{		
			throw new IllegalArgumentException(e.getMessage());
			}
		
		// Return the TuneSearchResultWrapper object
		return listOfResults;
		}
	}
