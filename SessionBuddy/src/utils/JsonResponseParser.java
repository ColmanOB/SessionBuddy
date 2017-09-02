package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;


/**
 * Uses GSON to parse a JSON response from thesession.org API into wrapper objects for ease of access to each element of the response.
 * 
 * @author Colman O'B
 * @since 2017-09-02
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
	 * Generic method using Gson to parse the JSON into a wrapper object of the type specified in the argument
	 * 
	 * @param wrapperType the type of wrapper object into which the JSON data should be parsed
	 * @return a wrapper object of the type specified in the argument
	 */
	public <T> T parseResponse(Class <T> wrapperType) throws IllegalStateException 
		{
		T listOfResults;
			
		Gson gson = new GsonBuilder().create();
			
		try
			// Use Gson to parse the JSON into a wrapper object of the type passed as the argument
			{
			listOfResults = gson.fromJson(jsonResponse, wrapperType);
			}
				
		catch (JsonSyntaxException e)
			// Catch a case where the API returns something that is not valid JSON/ does not match the structure of the chosen wrapper type
			{		
			throw new IllegalStateException(e.getMessage());
			}
		
		// Return the wrapper object containing the response from the API
		return listOfResults;
		}
	}


