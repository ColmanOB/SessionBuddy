package sessionbuddy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import sessionbuddy.utils.HttpRequestor;
import sessionbuddy.utils.JsonResponseParser;
import sessionbuddy.utils.StringCleaner;
import sessionbuddy.wrappers.granularobjects.Area;
import sessionbuddy.wrappers.granularobjects.Artist;
import sessionbuddy.wrappers.granularobjects.Coordinates;
import sessionbuddy.wrappers.granularobjects.Country;
import sessionbuddy.wrappers.granularobjects.DiscussionDetails;
import sessionbuddy.wrappers.granularobjects.EventDetails;
import sessionbuddy.wrappers.granularobjects.EventSchedule;
import sessionbuddy.wrappers.granularobjects.RecordingDetails;
import sessionbuddy.wrappers.granularobjects.SessionDetails;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.TuneDetails;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperDiscussions;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperEvents;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperRecordings;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperSessions;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperTunes;
import sessionbuddy.wrappers.resultsets.SearchResultEvents;
import sessionbuddy.wrappers.resultsets.SearchResultSessions;
import sessionbuddy.wrappers.resultsets.SearchResultTunes;
import sessionbuddy.wrappers.resultsets.SearchResultsDiscussions;
import sessionbuddy.wrappers.resultsets.SearchResultsRecordings;


/**
 * Queries the API at thesession.org for a chosen type of data with search terms, and parses the response into an easily usable structure. 
 * To use this feature, first create a new KeywordSearch object, then call one of its methods to perform the actual search.
 * 
 * @author Colman O'B
 * @since 2017-09-06
 *
 */
public class KeywordSearch extends Search
	{
	/**
	 * Searches the API for a list of tunes matching a specific set of search terms
	 * 
	 * @param searchTerms A string containing the search terms entered by the user
	 * @param resultsPerPage A number indicating how many discussions should be returned per page.  The maximum permitted by the API is 50.
	 * @return An ArrayList of TunesSearchResult objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException 
	 */
	public ArrayList<SearchResultTunes> searchTunes(String searchTerms, int resultsPerPage) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of matching tunes and store the JSON that is returned as a String
			String response = HttpRequestor.submitSearchRequest("tunes", searchTerms, resultsPerPage);
				
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			KeywordSearchWrapperTunes parsedResults = jsonParser.parseResponse(KeywordSearchWrapperTunes.class);
			
			// Set up the structure that will hold the parsed response from the API
			ArrayList<SearchResultTunes> resultSet = new ArrayList<SearchResultTunes>();
			
			// Use a private helper method to populate the ArrayList of TunesSearchResult objects
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
		
		catch (IllegalStateException e)
			{
			throw new IllegalStateException(e.getMessage());
			}
		
		catch (URISyntaxException e)
			{
			throw new URISyntaxException(e.getInput(), e.getReason(), e.getIndex());
			}
		}
	
	
	/**
	 * An alternative version of searchTunes(String searchTerms, int resultsPerPage) allowing the caller to specify an individual page number within a paginated JSON response.
	 * 
	 * @param searchTerms A string containing the search terms entered by the user
	 * @param resultsPerPage a number indicating how many discussions should be returned per page.  The maximum permitted by the API is 50.
	 * @param pageNumber a particular page number within the JSON search results
	 * @return An ArrayList of TunesSearchResult objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException 
	 */
	public ArrayList<SearchResultTunes> searchTunes(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of matching tunes and store the JSON that is returned as a String
			String response = HttpRequestor.submitSearchRequest("tunes", searchTerms, resultsPerPage, pageNumber);
			
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			KeywordSearchWrapperTunes parsedResults = jsonParser.parseResponse(KeywordSearchWrapperTunes.class);
			
			// Set up the structure that will hold the parsed response from the API
			ArrayList<SearchResultTunes> resultSet = new ArrayList<SearchResultTunes>();
			
			// Use a private helper method to populate the ArrayList of TunesSearchResult objects
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
		
		catch (IllegalStateException e)
			{
			throw new IllegalStateException(e.getMessage());
			}
		
		catch (URISyntaxException e)
			{
			throw new URISyntaxException(e.getInput(), e.getReason(), e.getIndex());
			}
		}

	/**
	 * Queries the API for a list of discussions matching a specific set of search terms
	 * 
	 * @param searchTerms A string containing the search terms entered by the user
	 * @param resultsPerPage A number indicating how many discussions should be returned per page.  The maximum permitted by the API is 50.
	 * @return An ArrayList of DiscussionsSearchResult objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException 
	 */
	public ArrayList<SearchResultsDiscussions> searchDiscussions(String searchTerms, int resultsPerPage) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of matching discussions and store the JSON that is returned as a String
			String response = HttpRequestor.submitSearchRequest("discussions", searchTerms, resultsPerPage);
				
			// Create a DiscussionSearchParser and DiscussionSearchResultWrapper to parse the raw JSON
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			KeywordSearchWrapperDiscussions parsedResults = jsonParser.parseResponse(KeywordSearchWrapperDiscussions.class);
			
			// This will hold each individual search result entry
			ArrayList<SearchResultsDiscussions> resultSet = new ArrayList <SearchResultsDiscussions>();
			
			// Use a private helper method to populate the ArrayList of DiscussionsSearchResult objects
			resultSet = populateDiscussionsSearchResult(parsedResults);
			
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
		
		catch (IllegalStateException e)
			{
			throw new IllegalStateException(e.getMessage());
			}
		
		catch (URISyntaxException e)
			{
			throw new URISyntaxException(e.getInput(), e.getReason(), e.getIndex());
			}
		}
			
	
	/**
	 * Search the API for a list of discussions matching a specific set of search terms, and specify the number of results that should be returned per page.
	 * This method also allows the caller to specify the particular page in the result set that should be returned.
	 * 
	 * @param searchTerms The search terms provided by the user
	 * @param resultsPerPage Specify how many results should be returned per page. The maximum is 50.
	 * @param pageNumber Specify a particular page within the search results
	 * @return An ArrayList of DiscussionsSearchResult objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException 
	 */
	public ArrayList<SearchResultsDiscussions> searchDiscussions(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of matching discussions, specifying the page number in the result set, and store the JSON that is returned as a String
			String response = HttpRequestor.submitSearchRequest("discussions", searchTerms, resultsPerPage, pageNumber);
			
			// Prepare the classes needed to parse the the JSON
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			KeywordSearchWrapperDiscussions parsedResults = jsonParser.parseResponse(KeywordSearchWrapperDiscussions.class);
			
			// This will hold each individual search result entry
			ArrayList<SearchResultsDiscussions> resultSet = new ArrayList <SearchResultsDiscussions>();
			
			// Use a private helper method to populate the ArrayList of DiscussionsSearchResult objects
			resultSet = populateDiscussionsSearchResult(parsedResults);
			
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
		
		catch (IllegalStateException e)
			{
			throw new IllegalStateException(e.getMessage());
			}
		
		catch (URISyntaxException e)
			{
			throw new URISyntaxException(e.getInput(), e.getReason(), e.getIndex());
			}
		}
	
	
	/**
	 * Queries the API for a list of events matching a specific set of search terms
	 * 
	 * @param searchTerms The search terms provided by the user
	 * @param resultsPerPage Specify how many results should be returned per page. The maximum is 50.
	 * @return an ArrayList of EventsSearchResult objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException 
	 */
	public ArrayList<SearchResultEvents> searchEvents(String searchTerms, int resultsPerPage) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of matching events and store the JSON that is returned as a String
			String response = HttpRequestor.submitSearchRequest("events", searchTerms, resultsPerPage);
				
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			KeywordSearchWrapperEvents parsedResults = jsonParser.parseResponse(KeywordSearchWrapperEvents.class);
				
			// This will hold each individual search result entry
			ArrayList<SearchResultEvents> resultSet = new ArrayList <SearchResultEvents>();
			
			// Use a private helper method to populate the ArrayList of EventsSearchResult objects
			resultSet = populateEventsSearchResult(parsedResults);
			
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
		
		catch (IllegalStateException e)
			{
			throw new IllegalStateException(e.getMessage());
			}
		
		catch (URISyntaxException e)
			{
			throw new URISyntaxException(e.getInput(), e.getReason(), e.getIndex());
			}
		}
	
	
	/**
	 * Alternative version of searchEvents(String searchTerms, int resultsPerPage), allowing a specific page number to be chosen within a paginated JSON response
	 * 
	 * @param searchTerms The search terms provided by the user
	 * @param resultsPerPage Specify how many results should be returned per page. The maximum is 50.
	 * @param pageNumber A specific page within a paginated JSON response
	 * @return an ArrayList of EventsSearchResult objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException 
	 */
	public ArrayList<SearchResultEvents> searchEvents(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of matching recordings and store the JSON that is returned as a String
			String response = HttpRequestor.submitSearchRequest("events",searchTerms, resultsPerPage,pageNumber);
			
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			KeywordSearchWrapperEvents parsedResults = jsonParser.parseResponse(KeywordSearchWrapperEvents.class);
			
			// This will hold each individual search result entry
			ArrayList<SearchResultEvents> resultSet = new ArrayList <SearchResultEvents>();
			
			// Use a private helper method to populate the ArrayList of EventsSearchResult objects
			resultSet = populateEventsSearchResult(parsedResults);
			
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
		
		catch (IllegalStateException e)
			{
			throw new IllegalStateException(e.getMessage());
			}
		
		catch (URISyntaxException e)
			{
			throw new URISyntaxException(e.getInput(), e.getReason(), e.getIndex());
			}
		}
	
	
	/**
	 * Queries the API for a list of recordings matching a specific set of search terms
	 * 
	 * @param searchTerms The search terms provided by the user
	 * @param resultsPerPage Specify how many results should be returned per page. The maximum is 50.
	 * @return an ArrayList of RecordingsSearchResult objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException 
	 */
	public ArrayList<SearchResultsRecordings> searchRecordings(String searchTerms, int resultsPerPage) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of matching recordings and store the JSON that is returned as a String
			String response = HttpRequestor.submitSearchRequest("recordings", searchTerms, resultsPerPage);
				
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			KeywordSearchWrapperRecordings parsedResults = jsonParser.parseResponse(KeywordSearchWrapperRecordings.class);
				
			// This will hold each individual search result entry
			ArrayList<SearchResultsRecordings> resultSet = new ArrayList <SearchResultsRecordings>();
			
			// Use a private helper method to populate the ArrayList of RecordingsSearchResult objects
			resultSet = populateRecordingsSearchResult(parsedResults);
			
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
		
		catch (IllegalStateException e)
			{
			throw new IllegalStateException(e.getMessage());
			}
		
		catch (URISyntaxException e)
			{
			throw new URISyntaxException(e.getInput(), e.getReason(), e.getIndex());
			}
		}
	
	
	/**
	 * Alternative version of searchRecordings(String searchTerms, int resultsPerPage), allowing the caller to specify an idividual page within a paginated JSON response
	 * 
	 * @param searchTerms The search terms provided by the user
	 * @param resultsPerPage Specify how many results should be returned per page. The maximum is 50.
	 * @param pageNumber a specific page within a paginated JSON response
	 * @return an ArrayList of RecordingsSearchResult objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException 
	 */
	public ArrayList<SearchResultsRecordings> searchRecordings(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of matching recordings and store the JSON that is returned as a String
			String response = HttpRequestor.submitSearchRequest("recordings",searchTerms, resultsPerPage,pageNumber);
			
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			KeywordSearchWrapperRecordings parsedResults = jsonParser.parseResponse(KeywordSearchWrapperRecordings.class);
				
			// This will hold each individual search result entry
			ArrayList<SearchResultsRecordings> resultSet = new ArrayList <SearchResultsRecordings>();
			
			// Use a private helper method to populate the ArrayList of RecordingsSearchResult objects
			resultSet = populateRecordingsSearchResult(parsedResults);
			
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
		
		catch (IllegalStateException e)
			{
			throw new IllegalStateException(e.getMessage());
			}	
		
		catch (URISyntaxException e)
			{
			throw new URISyntaxException(e.getInput(), e.getReason(), e.getIndex());
			}
		}
	
	
	/**
	 * Queries the API for a list of sessions matching a specific set of search terms
	 * 
	 * @param searchTerms The search terms provided by the user
	 * @param resultsPerPage Specify how many results should be returned per page. The maximum is 50.
	 * @return an ArrayList of SessionsSearchResult objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException 
	 */
	public ArrayList<SearchResultSessions> searchSessions(String searchTerms, int resultsPerPage) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of matching sessions and store the JSON that is returned as a String
			String response = HttpRequestor.submitSearchRequest("sessions", searchTerms, resultsPerPage);
				
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			KeywordSearchWrapperSessions parsedResults = jsonParser.parseResponse(KeywordSearchWrapperSessions.class);
			
			// This will hold each individual search result entry
			ArrayList<SearchResultSessions> resultSet = new ArrayList <SearchResultSessions>();
					
			// Use a private helper method to populate the ArrayList of SessionsSearchResult objects
			resultSet = populateSessionsSearchResult(parsedResults);

			// Return the set of results that has been collected
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
		
		catch (IllegalStateException e)
			{
			throw new IllegalStateException(e.getMessage());
			}	
		
		catch (URISyntaxException e)
			{
			throw new URISyntaxException(e.getInput(), e.getReason(), e.getIndex());
			}
		}
	
	
	/**
	 * Alternative version of searchSessions(String searchTerms, int resultsPerPage), allowing the caller to specify an individual page within a paginated JSON response
	 * 
	 * @param searchTerms The search terms provided by the user
	 * @param resultsPerPage Specify how many results should be returned per page. The maximum is 50.
	 * @param pageNumber a specific page within a paginated JSON response
	 * @return an ArrayList of SessionsSearchResult objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException 
	 */
	public ArrayList<SearchResultSessions> searchSessions(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of matching recordings and store the JSON that is returned as a String
			String response = HttpRequestor.submitSearchRequest("sessions",searchTerms, resultsPerPage,pageNumber);
			
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			KeywordSearchWrapperSessions parsedResults = jsonParser.parseResponse(KeywordSearchWrapperSessions.class);
			
			// This will hold each individual search result entry
			ArrayList<SearchResultSessions> resultSet = new ArrayList <SearchResultSessions>();
					
			// Use a private helper method to populate the ArrayList of SessionsSearchResult objects
			resultSet = populateSessionsSearchResult(parsedResults);

			// Return the set of results that has been collected
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
	
		catch (IllegalStateException e)
			{
			throw new IllegalStateException(e.getMessage());
			}
		
		catch (URISyntaxException e)
			{
			throw new URISyntaxException(e.getInput(), e.getReason(), e.getIndex());
			}
		}
	
	
	/**
	 * Helper method to gather and parse the response to a keyword search for a tune
	 * 
	 * @param parsedResults a TunesSearchResultWrapper object that has already been created an populated
	 * @return an ArrayList of TunesSearchResult objects
	 */
	private ArrayList<SearchResultTunes> populateTunesSearchResult(KeywordSearchWrapperTunes parsedResults)
		{
		ArrayList <SearchResultTunes> resultSet = new ArrayList <SearchResultTunes>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of tunes in the result set:
		for(int i = 0; i < (parsedResults.tunes.length); i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
			TuneDetails details = new TuneDetails(parsedResults.tunes[i].id, StringCleaner.cleanString(parsedResults.tunes[i].name), parsedResults.tunes[i].type, parsedResults.tunes[i].url, parsedResults.tunes[i].date);
			User submitter = new User(Integer.toString(parsedResults.tunes[i].member.id), StringCleaner.cleanString(parsedResults.tunes[i].member.name), parsedResults.tunes[i].member.url);
						
			// Instantiate a TunesSearchResult object & populate it
			SearchResultTunes currentResult = new SearchResultTunes(details, submitter);
			
			// Add the TuneSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the fully populated ArrayList
		return resultSet;
		}
	
	
	/**
	 * Helper method to gather and parse the response to a keyword search for discussions
	 * 
	 * @param parsedResults an existing populated DiscussionsSearchResultWrapper object
	 * @return an ArrayList of DiscussionsSearchResult objects
	 */
	private ArrayList<SearchResultsDiscussions> populateDiscussionsSearchResult(KeywordSearchWrapperDiscussions parsedResults)
		{
		// Use a TunesSearchParser to parse the raw JSON into a usable structure using Gson
		ArrayList<SearchResultsDiscussions> resultSet = new ArrayList <SearchResultsDiscussions>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages if needed
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.discussions.length); i++)
			{
			// Extract the elements from each individual search result in the JSON response
			// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response		
			DiscussionDetails details = new DiscussionDetails(parsedResults.discussions[i].id, StringCleaner.cleanString(parsedResults.discussions[i].name), parsedResults.discussions[i].url, parsedResults.discussions[i].date, parsedResults.discussions[i].comments);
			User user = new User(Integer.toString(parsedResults.discussions[i].member.id), StringCleaner.cleanString(parsedResults.discussions[i].member.name), parsedResults.discussions[i].member.url);
			
			// Instantiate a DiscussionsSearchResult object & populate it
			SearchResultsDiscussions currentResult = new SearchResultsDiscussions(details, user);
			
			// Add the DiscussionsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}
	
	
	/**
	 * Helper method to gather and parse the response to a keyword search for events
	 * 
	 * @param parsedResults an EventsSearchResultWrapper object that has already been populated
	 * @return an ArrayList of EventSearchResult objects
	 */
	private ArrayList<SearchResultEvents> populateEventsSearchResult(KeywordSearchWrapperEvents parsedResults)	
		{
		ArrayList<SearchResultEvents> resultSet = new ArrayList <SearchResultEvents>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.events.length); i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
			EventDetails details = new EventDetails(parsedResults.events[i].id, StringCleaner.cleanString(parsedResults.events[i].name), parsedResults.events[i].url, parsedResults.events[i].date);
			User user = new User(Integer.toString(parsedResults.events[i].member.id),StringCleaner.cleanString(parsedResults.events[i].member.name),parsedResults.events[i].member.url);
			EventSchedule schedule = new EventSchedule(parsedResults.events[i].dtstart, parsedResults.events[i].dtend);
			Coordinates coordinates = new Coordinates(parsedResults.events[i].latitude, parsedResults.events[i].longitude);
			Venue venue = new Venue(Integer.toString(parsedResults.events[i].venue.id), StringCleaner.cleanString(parsedResults.events[i].venue.name), parsedResults.events[i].venue.telephone, parsedResults.events[i].venue.email, parsedResults.events[i].venue.web );
			Town town = new Town(Integer.toString(parsedResults.events[i].town.id), StringCleaner.cleanString(parsedResults.events[i].town.name));
			Area area = new Area(Integer.toString(parsedResults.events[i].area.id), StringCleaner.cleanString(parsedResults.events[i].area.name));
			Country country = new Country(Integer.toString(parsedResults.events[i].country.id), StringCleaner.cleanString(parsedResults.events[i].country.name));
			
			
			// Instantiate a EventsSearchResult object & populate it
			SearchResultEvents currentResult = new SearchResultEvents(details, user, schedule, coordinates, venue, town, area, country);
			
			// Add the EventsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}
	
	
	/**
	 * Helper method to gather and parse the response to a keyword search for events
	 * 
	 * @param parsedResults a RecordingsSearchResultWrapper object that has already been populated
	 * @return an ArrayList of RecordingsSearchResult objects
	 */
	private ArrayList<SearchResultsRecordings> populateRecordingsSearchResult(KeywordSearchWrapperRecordings parsedResults)	
		{
		ArrayList<SearchResultsRecordings> resultSet = new ArrayList <SearchResultsRecordings>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.recordings.length); i++)
			{
			// Extract the elements from each individual search result in the JSON response
			// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response		
			RecordingDetails details = new RecordingDetails(parsedResults.recordings[i].id, StringCleaner.cleanString(parsedResults.recordings[i].name), parsedResults.recordings[i].url, parsedResults.recordings[i].date);
			User user = new User(Integer.toString(parsedResults.recordings[i].member.id), StringCleaner.cleanString(parsedResults.recordings[i].member.name), parsedResults.recordings[i].member.url);
			Artist artist = new Artist(Integer.toString(parsedResults.recordings[i].artist.id), StringCleaner.cleanString(parsedResults.recordings[i].artist.name), parsedResults.recordings[i].artist.url);
			
			// Instantiate a RecordingsSearchResult object & populate it
			SearchResultsRecordings currentResult = new SearchResultsRecordings(details, user, artist);
			
			// Add the RecordingsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}
	
	
	/**
	 * Helper method to gather and parse the response to a keyword-based search for sessions
	 * 
	 * @param parsedResults a SessionSearchResultWrapper object that has been populated
	 * @return an ArrayList of SessionsSearchResult objects
	 */
	private ArrayList<SearchResultSessions> populateSessionsSearchResult(KeywordSearchWrapperSessions parsedResults)	
		{
		ArrayList<SearchResultSessions> resultSet = new ArrayList <SearchResultSessions>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of sessions in the result set:
		for(int i = 0; i < (parsedResults.sessions.length); i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
			SessionDetails details = new SessionDetails(parsedResults.sessions[i].id, parsedResults.sessions[i].url, parsedResults.sessions[i].date);
			Coordinates coordinates = new Coordinates(parsedResults.sessions[i].latitude, parsedResults.sessions[i].longitude);
			User user = new User(Integer.toString(parsedResults.sessions[i].member.id),StringCleaner.cleanString(parsedResults.sessions[i].member.name),parsedResults.sessions[i].member.url);
			Venue venue = new Venue(Integer.toString(parsedResults.sessions[i].venue.id), StringCleaner.cleanString(parsedResults.sessions[i].venue.name), parsedResults.sessions[i].venue.telephone, parsedResults.sessions[i].venue.email, parsedResults.sessions[i].venue.web );
			Town town = new Town(Integer.toString(parsedResults.sessions[i].town.id), StringCleaner.cleanString(parsedResults.sessions[i].town.name));
			Area area = new Area(Integer.toString(parsedResults.sessions[i].area.id), StringCleaner.cleanString(parsedResults.sessions[i].area.name));
			Country country = new Country(Integer.toString(parsedResults.sessions[i].country.id), StringCleaner.cleanString(parsedResults.sessions[i].country.name));
			
			
			// Instantiate a SessionsSearchResult object & populate it
			SearchResultSessions currentResult = new SearchResultSessions(details, coordinates, user, venue, town, area, country);
			
			// Add the SessionsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
			
		return resultSet;
		}	
	}
