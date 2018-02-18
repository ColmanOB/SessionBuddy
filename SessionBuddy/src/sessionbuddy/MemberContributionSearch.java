package sessionbuddy;

import java.io.IOException;
import java.util.ArrayList;

import sessionbuddy.utils.HttpRequestor;
import sessionbuddy.utils.JsonResponseParser;
import sessionbuddy.utils.StringCleaner;
import sessionbuddy.wrappers.granularobjects.LatestSettingDetails;
import sessionbuddy.wrappers.granularobjects.LatestTuneDetails;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperTunes;
import sessionbuddy.wrappers.resultsets.LatestSearchTunes;

/**
 * Retrieves a list of member contributions in a chosen category - tunes, discussions, recordings, events, sessions or sets.
 * 
 * @author Colman O'B
 * @since 2018-02-17
 */
public class MemberContributionSearch extends Search 
	{
	/**
	 * Retrieves the most recently added tunes/settings on thesession.org, most recent first
	 * 
	 * @param resultsPerPage the number of results that should be returned per page in the JSON response
	 * @return an ArrayList of LatestSearchTunes objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * 
	 * @author Colman
	 * @since 2018-02-10
	 */
	public ArrayList<LatestSearchTunes> getTunes(int userID, int resultsPerPage) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);

			// Launch a search for a list of most recently submitted tunes and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitMemberContributionRequest("tunes", userID, resultsPerPage);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			LatestWrapperTunes parsedResults = jsonParser.parseResponse(LatestWrapperTunes.class);
								
			// This will hold each individual search result entry
			ArrayList<LatestSearchTunes> resultSet = new ArrayList<LatestSearchTunes>();
			
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
	 * An alternative version of getLatestTunes(), allowing the caller to specify a particular page in the response
	 * 
	 * @param resultsPerPage the number of results that should be returned per page in the JSON response
	 * @param pageNumber a specific page within the JSON response
	 * @return an ArrayList of LatestSearchTunes objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * 
	 * @author Colman
	 * @since 2018-02-10
	 */
	public ArrayList<LatestSearchTunes> getTunes(int resultsPerPage, int userID, int pageNumber) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of most recently submitted tunes and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitMemberContributionRequest("tunes", userID, resultsPerPage, pageNumber);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			LatestWrapperTunes parsedResults = jsonParser.parseResponse(LatestWrapperTunes.class);
							
			// This will hold each individual search result entry
			ArrayList<LatestSearchTunes> resultSet = new ArrayList<LatestSearchTunes>();
			
			resultSet = populateTunesSearchResult(parsedResults);
			
			return resultSet;
			}
		
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
		
		catch (IOException e)
			{
			throw new IOException(e.getMessage());
			}
		}
	
	
	/**
	 * Helper method to gather and parse the response to a keyword search for a tune
	 * 
	 * @param parsedResults a LatestWrapperTunes object that has already been created and populated
	 * @return an ArrayList of LatestSearchTunes objects
	 * 
	 * @author Colman
	 * @since 2018-02-10
	 */
	private ArrayList<LatestSearchTunes> populateTunesSearchResult(LatestWrapperTunes parsedResults)
		{
		ArrayList <LatestSearchTunes> resultSet = new ArrayList <LatestSearchTunes>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of tunes in the result set:
		for(int i = 0; i < parsedResults.settings.length; i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
			LatestSettingDetails details = new LatestSettingDetails(parsedResults.settings[i].id, parsedResults.settings[i].url, parsedResults.settings[i].key, parsedResults.settings[i].date);
			User submitter = new User(Integer.toString(parsedResults.settings[i].member.id), StringCleaner.cleanString(parsedResults.settings[i].member.name), parsedResults.settings[i].member.url);
			LatestTuneDetails settingDetails = new LatestTuneDetails( Integer.toString(parsedResults.settings[i].tune.id), parsedResults.settings[i].tune.name, parsedResults.settings[i].tune.url );
			
			// Instantiate a TunesSearchResult object & populate it
			LatestSearchTunes currentResult = new LatestSearchTunes(details, submitter, settingDetails);
			
			// Add the TuneSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the fully populated ArrayList
		return resultSet;
		}
	}
