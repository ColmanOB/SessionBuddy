package response_parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import result_set_wrappers.TunesSearchResultWrapper;

/**
 * Uses GSON to parse a set of search results from thesession.org API into a Java TuneSearchResultsWrapper object
 * 
 * @author Colman O'B
 * @since 2017-08-13
 */
public class TunesSearchParser 
	{
	// Instantiate a wrapper object with fields that exactly match those in the search results JSON string
	private TunesSearchResultWrapper listOfResults;	
	
	/**
	 * Uses Gson to parse the JSON into a TunesSearchResultWrapper object
	 * 
	 * @param searchResultsString Pass in a string containing the JSON returned from a search of thesession.org API
	 * @return a TuneSearchResultWrapper object allowing access to any individual element of the response
	 */
	public TunesSearchResultWrapper parseResponse(String searchResultsString) 
		{
		Gson gson = new GsonBuilder().create();
		
		try
		// Populate the Gson object using the string of JSON returned from the API
			{
			listOfResults = gson.fromJson(searchResultsString, TunesSearchResultWrapper.class);
			}
		
		catch (JsonSyntaxException e)
		// Catch a case where the API returns something that is not valid JSON that matches the structure of a TunesSearchResultWrapper
			{		
			throw new IllegalArgumentException(e.getMessage());
			}
		
		// Return the TuneSearchResultWrapper object
		return listOfResults;
		}
	}
