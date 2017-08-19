package response_parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import result_set_wrappers.RecordingByIDWrapper;


public class RecordingByIDParser 
{
	/**
	 * Uses GSON to parse a set of details of a recording from thesession.org API into a "RecordingByIDWrapper" object
	 * 
	 * @author Colman O'B
	 * @since 2017-08-19
	 */
		// Instantiate a wrapper object with fields that exactly match those in the response JSON string
		private RecordingByIDWrapper listOfResults;	
		
		/**
		 * Uses Gson to parse the JSON into a RecordingByIDWrapper object
		 * 
		 * @param tuneDetailsString Pass in a string containing the JSON returned from thesession.org API
		 * @return a RecordingByIDWrapper object allowing access to any individual element of the response
		 */
		public RecordingByIDWrapper parseResponse(String recordingDetailsString) 
			{
			Gson gson = new GsonBuilder().create();
			
			try
			// Populate the Gson object using the string of JSON returned from the API
				{
				listOfResults = gson.fromJson(recordingDetailsString, RecordingByIDWrapper.class);
				}
			
			catch (JsonSyntaxException e)
			// Catch a case where the API returns something that is not valid JSON that matches the structure of a RecordingByIDWrapper
				{		
				throw new IllegalArgumentException(e.getMessage());
				}
			
			// Return the RecordingByIDWrapper object
			return listOfResults;
			}
		}


