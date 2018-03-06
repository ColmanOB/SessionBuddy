package sessionbuddy.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;


/**
 * Uses Google's Gson library to parse a JSON response from thesession.org API into wrapper objects for ease of access to each element of the response.
 * 
 * @author Colman O'B
 * @since 2017-09-05
 */

public class JsonResponseParser 
	{
	// A page of JSON data retrieved from thesession.org
	private String jsonResponse; 
	
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
	 * @throws IllegalStateException if the JSON response is not valid JSON or does not match the expected structure defined in the corresponding wrapper class
	 */
	public <T> T parseResponse(Class <T> wrapperType) throws IllegalStateException 
		{			
		try
			// Use Gson to parse the JSON into a wrapper object of the type passed as the argument
			{
			T listOfResults;
			
			Gson gson = new GsonBuilder().create();
			
			listOfResults = gson.fromJson(jsonResponse, wrapperType);
			
			// Return the wrapper object containing the response from the API
			return listOfResults;
			}
				
		catch (JsonSyntaxException e)
			// Catch a case where the API returns something that is not valid JSON/ does not match the structure of the chosen wrapper type
			{		
			throw new IllegalStateException(e.getMessage());
			}
		}
	}


