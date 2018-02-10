package sessionbuddy;

import java.io.IOException;
import java.util.ArrayList;

import sessionbuddy.utils.HttpRequestor;
import sessionbuddy.utils.JsonResponseParser;
import sessionbuddy.utils.StringCleaner;
import sessionbuddy.wrappers.granularobjects.PopularTuneDetails;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.jsonresponse.PopularWrapperTunes;
import sessionbuddy.wrappers.resultsets.PopularTunes;

// TODO: Complete the JavaDoc comments

/**
 * Retrieves the current most popular tunes, i.e. those that have been added to the largest number of user tunebooks on thesession.org
 * 
 * @author Colman
 * @since 2018-02-10
 */
public class PopularSearch extends Search
	{
	
	/**
	 * Retrieves a list of the most popular tunes on thesession.org
	 * 
	 * @param resultsPerPage
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public ArrayList<PopularTunes> getPopularTunes(int resultsPerPage) throws IllegalArgumentException, IOException
		{
		// TODO: Complete this method
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
	
			// Launch a search for a list of most recently submitted tunes and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			
			// TODO: Change the following line to use the correct method in HttpRequestor to retrieve popular tunes
			String response = searcher.submitPopularRequest(resultsPerPage);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			PopularWrapperTunes parsedResults = jsonParser.parseResponse(PopularWrapperTunes.class);

								
			// This will hold each individual search result entry
			ArrayList<PopularTunes> resultSet = new ArrayList<PopularTunes>();
			
			resultSet = populateTunesSearchResult(parsedResults);
			
			return resultSet;
			}
		
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
	
		catch(IOException e)
			{
			throw new IOException(e.getMessage());
			}
		}
	
	
	/**
	 * An alternative version of getPopularTunes, allowing the caller to specify a page number within the JSON reponse from the API
	 * 
	 * @param resultsPerPage
	 * @param pageNumber
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public ArrayList<PopularTunes> getPopularTunes(int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException
		{
		// TODO: Complete this method
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
	
			// Launch a search for a list of most recently submitted tunes and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			
			String response = searcher.submitPopularRequest(resultsPerPage, pageNumber);

							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			PopularWrapperTunes parsedResults = jsonParser.parseResponse(PopularWrapperTunes.class);
								
			// This will hold each individual search result entry
			ArrayList<PopularTunes> resultSet = new ArrayList<PopularTunes>();
			
			resultSet = populateTunesSearchResult(parsedResults);
			
			return resultSet;
			}
		
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
	
		catch(IOException e)
			{
			throw new IOException(e.getMessage());
			}
		}
	
	
	/**
	 * Helper method to gather and parse the response to a search for popular tunes
	 * 
	 * @param parsedResults a PopularWrapperTunes object that has already been created an populated
	 * @return an ArrayList of PopularTunes objects
	 */
	private ArrayList<PopularTunes> populateTunesSearchResult(PopularWrapperTunes parsedResults)
		{
		ArrayList <PopularTunes> resultSet = new ArrayList <PopularTunes>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of tunes in the result set:
		for(int i = 0; i < parsedResults.tunes.length; i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
			PopularTuneDetails details = new PopularTuneDetails(parsedResults.tunes[i].id, parsedResults.tunes[i].name, parsedResults.tunes[i].url, parsedResults.tunes[i].date, parsedResults.tunes[i].type, parsedResults.tunes[i].tunebooks);
			User submitter = new User(Integer.toString(parsedResults.tunes[i].member.id), StringCleaner.cleanString(parsedResults.tunes[i].member.name), parsedResults.tunes[i].member.url);
			
			// Instantiate a PopularTunes object & populate it
			PopularTunes currentResult = new PopularTunes(details, submitter);
			
			// Add the TuneSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the fully populated ArrayList
		return resultSet;
		}
	}
