package response_parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import result_set_wrappers.EventByIDWrapper;


public class EventByIDParser 
{
/**
 * Uses GSON to parse an event listing from thesession.org API into a EventByIDWrapper object
 * 
 * @author Colman O'B
 * @since 2017-08-20
 */
	// Instantiate a wrapper object with fields that exactly match those in the search results JSON string
	private EventByIDWrapper listOfResults;	
		
		/**
		 * Uses Gson to parse the JSON into a EventByIDWrapper object
		 * 
		 * @param eventDetailsString Pass in a string containing the JSON returned from thesession.org API
		 * @return a EventByIDWrapper object allowing access to any individual element of the response
		 */
		public EventByIDWrapper parseResponse(String eventDetailsString) 
			{
			Gson gson = new GsonBuilder().create();
			
			try
			// Populate the Gson object using the string of JSON returned from the API
				{
				listOfResults = gson.fromJson(eventDetailsString, EventByIDWrapper.class);
				}
			
			catch (JsonSyntaxException e)
			// Catch a case where the API returns something that is not valid JSON that matches the structure of a EventByIDWrapper
				{		
				throw new IllegalArgumentException(e.getMessage());
				}
			
			// Return the EventByIDWrapper object
			return listOfResults;
			}
		}


