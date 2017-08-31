package response_parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import result_set_wrappers.DiscussionByIDWrapper;
import result_set_wrappers.EventByIDWrapper;
import result_set_wrappers.EventsByLocationWrapper;
import result_set_wrappers.RecordingByIDWrapper;
import result_set_wrappers.SessionByIDWrapper;
import result_set_wrappers.SessionsByLocationWrapper;
import result_set_wrappers.TuneByIDWrapper;

// TODO: Totally re-do the comments throughout this class
// TODO: Create a method in this class corresponding to each of the individual classes in response_parsers package
// TODO: Add exception handling to the methods below

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
	 * @return
	 */
	public DiscussionByIDWrapper parseItemDiscussion()
		{
		DiscussionByIDWrapper listOfResults = parseResponse(jsonResponse, DiscussionByIDWrapper.class);	
		return listOfResults;
		}
	

	/**
	 * @return
	 */
	public EventByIDWrapper parseItemEvent()
		{
		EventByIDWrapper listOfResults = parseResponse(jsonResponse, EventByIDWrapper.class);	
		return listOfResults;
		}
	
	
	/**
	 * @return
	 */
	public RecordingByIDWrapper parseItemRecording()
		{
		RecordingByIDWrapper listOfResults = parseResponse(jsonResponse, RecordingByIDWrapper.class);	
		return listOfResults;
		}
	
	
	/**
	 * @return
	 */
	public SessionByIDWrapper parseItemSession()
		{
		SessionByIDWrapper listOfResults = parseResponse(jsonResponse, SessionByIDWrapper.class);	
		return listOfResults;
		}
	
	
	/**
	 * @return
	 */
	public TuneByIDWrapper parseItemTune()
		{
		TuneByIDWrapper listOfResults = parseResponse(jsonResponse, TuneByIDWrapper.class);	
		return listOfResults;
		}
	
	
	/**
	 * @return
	 */
	public EventsByLocationWrapper parseLocationEvent()
		{
		EventsByLocationWrapper listOfResults = parseResponse(jsonResponse, EventsByLocationWrapper.class);	
		return listOfResults;
		}
	
	
	/**
	 * @return
	 */
	public SessionsByLocationWrapper parseLocationSession()
		{
		SessionsByLocationWrapper listOfResults = parseResponse(jsonResponse, SessionsByLocationWrapper.class);	
		return listOfResults;
		}
	
		
		/**
		 * Helper generic method using Gson to parse the JSON into the appropriate type of wrapper object
		 * 
		 * @param tuneDetailsString Pass in a string containing the JSON returned from thesession.org API
		 * @return a TuneByIDWrapper object allowing access to any individual element of the response
		 */
		private <T> T parseResponse(String jsonResponse, Class <T> wrapperType ) 
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
			
			// Return the DiscussionByIDWrapper object
			return listOfResults;
			}
		}


