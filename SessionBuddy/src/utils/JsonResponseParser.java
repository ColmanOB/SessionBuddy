package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;


/**
 * Uses GSON to parse a JSON response from thesession.org API into wrapper objects for ease of access to each element of the response.
 * Each type of API call (in terms of both data category and query type) has its own method, but the actual parsing is all done by the parseResponse() method.
 * 
 * @author Colman O'B
 * @since 2017-08-31
 */

public class JsonResponseParser 
	{
	private String jsonResponse; // A page of JSON data retrieved from thesession.org
	
	/**
	 * Constructor that takes a string of JSON data as its only argument
	 * 
	 * @param jsonResponse a string of JSON data retrieved from thesession.org API
	 */
	public	JsonResponseParser(String jsonResponse)
		{
		this.jsonResponse = jsonResponse;
		}
		
	
	/**
	 * Generic method using Gson to parse the JSON into the appropriate type of wrapper object
	 * 
	 * @param wrapperType
	 * @return
	 */
	public <T> T parseResponse(Class <T> wrapperType ) 
		{
		T listOfResults;
			
		Gson gson = new GsonBuilder().create();
			
		try
			// Populate the Gson object using the string of JSON returned from the API
			{
			listOfResults = gson.fromJson(jsonResponse, wrapperType);
			}
				
		catch (JsonSyntaxException e)
			// Catch a case where the API returns something that is not valid JSON that matches the structure of a DiscussionByIDWrapper
			{		
			throw new RuntimeException(e.getMessage());
			}
		
		// Return the wrapper object containing the response from the API
		return listOfResults;
		}
	}


