package response_parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import result_set_wrappers.RecordingsSearchResultWrapper;

/**
 * Uses GSON to parse a set of recordings search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-08-19
 */
public class RecordingsSearchParser 
	{
	// Instantiate a wrapper object with fields that exactly match those in the search results JSON string
	private RecordingsSearchResultWrapper resultSet;	
	
	/**
	 * Uses Gson to parse the JSON into RecordingsSearchResultWrapper object
	 * 
	 * @param apiResponseString Pass in a string containing the JSON returned from a search of thesession.org API
	 * @return a RecordingsSearchResultWrapper object allowing access to any individual element of the response
	 */
	public RecordingsSearchResultWrapper parseResponse(String apiResponseString) 
		{
		Gson gson = new GsonBuilder().create();
		
		try
		// Populate the Gson object using the string of JSON returned from the API
			{
			resultSet = gson.fromJson(apiResponseString, RecordingsSearchResultWrapper.class);
			}
		
		catch (JsonSyntaxException e)
		// Catch a case where the API returns something that is not valid JSON that matches the structure of a RecordingsSearchResultWrapper
			{		
			throw new IllegalArgumentException(e.getMessage());
			}
		
		resultSet = gson.fromJson(apiResponseString, RecordingsSearchResultWrapper.class);
		
		// Return the TuneSearchResultWrapper object
		return resultSet;
		}
	}
