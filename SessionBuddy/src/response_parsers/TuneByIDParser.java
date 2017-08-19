package response_parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import result_set_wrappers.TuneByIDWrapper;


public class TuneByIDParser 
{
	/**
	 * Uses GSON to parse a set of search results from thesession.org API into a Java "TheSessionAPISearchResultsWrapper" object
	 * 
	 * @author Colman O'B
	 * @since 2017-08-13
	 */
		// Instantiate a wrapper object with fields that exactly match those in the search results JSON string
		private TuneByIDWrapper listOfResults;	
		
		/**
		 * Uses Gson to parse the JSON into a TuneByIDWrapper object
		 * 
		 * @param tuneDetailsString Pass in a string containing the JSON returned from thesession.org API
		 * @return a TuneByIDWrapper object allowing access to any individual element of the response
		 */
		public TuneByIDWrapper parseResponse(String tuneDetailsString) 
			{
			Gson gson = new GsonBuilder().create();
			
			try
			// Populate the Gson object using the string of JSON returned from the API
				{
				listOfResults = gson.fromJson(tuneDetailsString, TuneByIDWrapper.class);
				}
			
			catch (JsonSyntaxException e)
			// Catch a case where the API returns something that is not valid JSON that matches the structure of a TuneByIDWrapper
				{		
				throw new IllegalArgumentException(e.getMessage());
				}
			
			// Return the TuneByIDWrapper object
			return listOfResults;
			}
		}


