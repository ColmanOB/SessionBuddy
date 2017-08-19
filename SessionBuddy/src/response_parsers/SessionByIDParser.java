package response_parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import result_set_wrappers.SessionByIDWrapper;


public class SessionByIDParser 
{
/**
 * Uses GSON to parse a set of search results from thesession.org API into a SessionByIDWrapper object
 * 
 * @author Colman O'B
 * @since 2017-08-13
 */
	// Instantiate a wrapper object with fields that exactly match those in the search results JSON string
	private SessionByIDWrapper listOfResults;	
		
		/**
		 * Uses Gson to parse the JSON into a SessionByIDWrapper object
		 * 
		 * @param sessionDetailsString Pass in a string containing the JSON returned from thesession.org API
		 * @return a SessionByIDWrapper object allowing access to any individual element of the response
		 */
		public SessionByIDWrapper parseResponse(String sessionDetailsString) 
			{
			Gson gson = new GsonBuilder().create();
			
			try
			// Populate the Gson object using the string of JSON returned from the API
				{
				listOfResults = gson.fromJson(sessionDetailsString, SessionByIDWrapper.class);
				}
			
			catch (JsonSyntaxException e)
			// Catch a case where the API returns something that is not valid JSON that matches the structure of a SessionByIDWrapper
				{		
				throw new IllegalArgumentException(e.getMessage());
				}
			
			// Return the SessionByIDWrapper object
			return listOfResults;
			}
		}


