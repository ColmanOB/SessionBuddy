package main;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;

import json_object_wrappers.Area;
import json_object_wrappers.Artist;
import json_object_wrappers.Coordinates;
import json_object_wrappers.Country;
import json_object_wrappers.DiscussionDetails;
import json_object_wrappers.DiscussionsSearchResult;
import json_object_wrappers.EventDetails;
import json_object_wrappers.EventSchedule;
import json_object_wrappers.EventsSearchResult;
import json_object_wrappers.RecordingDetails;
import json_object_wrappers.RecordingsSearchResult;
import json_object_wrappers.SessionDetails;
import json_object_wrappers.SessionsSearchResult;
import json_object_wrappers.Town;
import json_object_wrappers.TuneDetails;
import json_object_wrappers.TunesSearchResult;
import json_object_wrappers.User;
import json_object_wrappers.Venue;
import response_parsers.DiscussionsSearchParser;
import response_parsers.EventsSearchParser;
import response_parsers.RecordingsSearchParser;
import response_parsers.SessionsSearchParser;
import response_parsers.TunesSearchParser;
import result_set_wrappers.DiscussionsSearchResultWrapper;
import result_set_wrappers.EventsSearchResultWrapper;
import result_set_wrappers.RecordingsSearchResultWrapper;
import result_set_wrappers.SessionsSearchResultWrapper;
import result_set_wrappers.TunesSearchResultWrapper;

// TODO: Complete the Javadoc comments for all methods

/**
 * Queries the API at thesession.org for a chosen type of data with search terms, and parses the response into an easily usable structure. 
 * To use this feature, first create a new KeywordSearch object, then call one of its methods to perform the actual search.
 * 
 * @author Colman O'B
 * @since 2017-08-26
 *
 */
public class KeywordSearch 
	{
	private int pageCount = 0;
	
	/**
	 * Searches the API for a list of tunes matching a specific set of search terms
	 * 
	 * @param searchTerms A string containing the search terms entered by the user
	 * @param resultsPerPage A number indicating how many discussions should be returned per page.  The maximum permitted by the API is 50.
	 * @return An ArrayList of TunesSearchResult objects
	 * @throws RuntimeException 
	 * @throws MalformedURLException 
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 */
	public ArrayList<TunesSearchResult> searchTunes(String searchTerms, int resultsPerPage) throws MalformedURLException, RuntimeException, IllegalArgumentException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			}
		
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
	
		// Launch a search for a list of matching tunes and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitSearchRequest("tunes", searchTerms, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		TunesSearchParser jsonParser = new TunesSearchParser();
		TunesSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
		
		// Set up the structure that will hold the parsed response from the API
		ArrayList<TunesSearchResult> resultSet = new ArrayList<TunesSearchResult>();
		
		// Use a private helper method to populate the ArrayList of TunesSearchResult objects
		resultSet = populateTunesSearchResult(parsedResults);
		
		return resultSet;
		}
	
	
	/**
	 * An alternative version of searchTunes(String searchTerms, int resultsPerPage) allowing the caller to specify an individual page number within a paginated JSON response.
	 * 
	 * @param searchTerms A string containing the search terms entered by the user
	 * @param resultsPerPage a number indicating how many discussions should be returned per page.  The maximum permitted by the API is 50.
	 * @param pageNumber a particular page number within the JSON search results
	 * @return An ArrayList of TunesSearchResult objects
	 * @throws RuntimeException 
	 * @throws MalformedURLException 
	 */
	public ArrayList<TunesSearchResult> searchTunes(String searchTerms, int resultsPerPage, int pageNumber) throws MalformedURLException, RuntimeException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			}
		
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
		
		// Launch a search for a list of matching tunes and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitSearchRequest("tunes", searchTerms, resultsPerPage, pageNumber);
		
		// Parse the returned JSON into a wrapper class to allow access to all elements
		TunesSearchParser jsonParser = new TunesSearchParser();
		TunesSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
		
		// Set up the structure that will hold the parsed response from the API
		ArrayList<TunesSearchResult> resultSet = new ArrayList<TunesSearchResult>();
		
		// Use a private helper method to populate the ArrayList of TunesSearchResult objects
		resultSet = populateTunesSearchResult(parsedResults);
		
		return resultSet;
		}

	/**
	 * Queries the API for a list of discussions matching a specific set of search terms
	 * 
	 * @param searchTerms A string containing the search terms entered by the user
	 * @param resultsPerPage A number indicating how many discussions should be returned per page.  The maximum permitted by the API is 50.
	 * @return An ArrayList of DiscussionsSearchResult objects
	 * @throws RuntimeException 
	 * @throws MalformedURLException 
	 */
	public ArrayList<DiscussionsSearchResult> searchDiscussions(String searchTerms, int resultsPerPage) throws MalformedURLException, RuntimeException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			}
		
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
		
		// Launch a search for a list of matching discussions and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitSearchRequest("discussions", searchTerms, resultsPerPage);
			
		// Create a DiscussionSearchParser and DiscussionSearchResultWrapper to parse the raw JSON
		DiscussionsSearchParser jsonParser = new DiscussionsSearchParser();
		DiscussionsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
		
		// This will hold each individual search result entry
		ArrayList<DiscussionsSearchResult> resultSet = new ArrayList <DiscussionsSearchResult>();
		
		// Use a private helper method to populate the ArrayList of DiscussionsSearchResult objects
		resultSet = populateDiscussionsSearchResult(parsedResults);
		
		return resultSet;
		}
	
	
	/**
	 * Search the API for a list of discussions matching a specific set of search terms, and specify the number of results that should be returned per page.
	 * This method also allows the caller to specify the particular page in the result set that should be returned.
	 * 
	 * @param searchTerms The search terms provided by the user
	 * @param resultsPerPage Specify how many results should be returned per page. The maximum is 50.
	 * @param pageNumber Specify a particular page within the search results
	 * @return An ArrayList of DiscussionsSearchResult objects
	 * @throws RuntimeException 
	 * @throws MalformedURLException 
	 */
	public ArrayList<DiscussionsSearchResult> searchDiscussions(String searchTerms, int resultsPerPage, int pageNumber) throws MalformedURLException, RuntimeException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			}
		
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
		
		// Launch a search for a list of matching discussions, specifying the page number in the result set, and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitSearchRequest("discussions", searchTerms, resultsPerPage, pageNumber);
		
		// Prepare the classes needed to parse the the JSON
		DiscussionsSearchParser jsonParser = new DiscussionsSearchParser();
		DiscussionsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
		
		// This will hold each individual search result entry
		ArrayList<DiscussionsSearchResult> resultSet = new ArrayList <DiscussionsSearchResult>();
		
		// Use a private helper method to populate the ArrayList of DiscussionsSearchResult objects
		resultSet = populateDiscussionsSearchResult(parsedResults);
		
		return resultSet;
		}
	
	
	/**
	 * Queries the API for a list of events matching a specific set of search terms
	 * 
	 * @param searchTerms The search terms provided by the user
	 * @param resultsPerPage Specify how many results should be returned per page. The maximum is 50.
	 * @return an ArrayList of EventsSearchResult objects
	 * @throws RuntimeException 
	 * @throws MalformedURLException 
	 */
	public ArrayList<EventsSearchResult> searchEvents(String searchTerms, int resultsPerPage) throws MalformedURLException, RuntimeException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			}
		
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
		
		// Launch a search for a list of matching events and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitSearchRequest("events", searchTerms, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		EventsSearchParser jsonParser = new EventsSearchParser();
		EventsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
			
		// This will hold each individual search result entry
		ArrayList<EventsSearchResult> resultSet = new ArrayList <EventsSearchResult>();
		
		// Use a private helper method to populate the ArrayList of EventsSearchResult objects
		resultSet = populateEventsSearchResult(parsedResults);
		
		return resultSet;
		}
	
	
	/**
	 * Alternative version of searchEvents(String searchTerms, int resultsPerPage), allowing a specific page number to be chosen within a paginated JSON response
	 * 
	 * @param searchTerms The search terms provided by the user
	 * @param resultsPerPage Specify how many results should be returned per page. The maximum is 50.
	 * @param pageNumber A specific page within a paginated JSON response
	 * @return an ArrayList of EventsSearchResult objects
	 * @throws RuntimeException 
	 * @throws MalformedURLException 
	 */
	public ArrayList<EventsSearchResult> searchEvents(String searchTerms, int resultsPerPage, int pageNumber) throws MalformedURLException, RuntimeException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			}
		
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitSearchRequest("events",searchTerms, resultsPerPage,pageNumber);
		
		// Parse the returned JSON into a wrapper class to allow access to all elements
		EventsSearchParser jsonParser = new EventsSearchParser();
		EventsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
		
		// This will hold each individual search result entry
		ArrayList<EventsSearchResult> resultSet = new ArrayList <EventsSearchResult>();
		
		// Use a private helper method to populate the ArrayList of EventsSearchResult objects
		resultSet = populateEventsSearchResult(parsedResults);
		
		return resultSet;
		}
	
	
	/**
	 * Queries the API for a list of recordings matching a specific set of search terms
	 * 
	 * @param searchTerms The search terms provided by the user
	 * @param resultsPerPage Specify how many results should be returned per page. The maximum is 50.
	 * @return an ArrayList of RecordingsSearchResult objects
	 * @throws RuntimeException 
	 * @throws MalformedURLException 
	 */
	public ArrayList<RecordingsSearchResult> searchRecordings(String searchTerms, int resultsPerPage) throws MalformedURLException, RuntimeException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			}
		
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitSearchRequest("recordings", searchTerms, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		RecordingsSearchParser jsonParser = new RecordingsSearchParser();
		RecordingsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
			
		// This will hold each individual search result entry
		ArrayList<RecordingsSearchResult> resultSet = new ArrayList <RecordingsSearchResult>();
		
		// Use a private helper method to populate the ArrayList of RecordingsSearchResult objects
		resultSet = populateRecordingsSearchResult(parsedResults);
		
		return resultSet;
		}
	
	
	/**
	 * Alternative version of searchRecordings(String searchTerms, int resultsPerPage), allowing the caller to specify an idividual page within a paginated JSON response
	 * 
	 * @param searchTerms The search terms provided by the user
	 * @param resultsPerPage Specify how many results should be returned per page. The maximum is 50.
	 * @param pageNumber a specific page within a paginated JSON response
	 * @return an ArrayList of RecordingsSearchResult objects
	 * @throws RuntimeException 
	 * @throws MalformedURLException 
	 */
	public ArrayList<RecordingsSearchResult> searchRecordings(String searchTerms, int resultsPerPage, int pageNumber) throws MalformedURLException, RuntimeException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			}
		
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitSearchRequest("recordings",searchTerms, resultsPerPage,pageNumber);
		
		// Parse the returned JSON into a wrapper class to allow access to all elements
		RecordingsSearchParser jsonParser = new RecordingsSearchParser();
		RecordingsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
			
		// This will hold each individual search result entry
		ArrayList<RecordingsSearchResult> resultSet = new ArrayList <RecordingsSearchResult>();
		
		// Use a private helper method to populate the ArrayList of RecordingsSearchResult objects
		resultSet = populateRecordingsSearchResult(parsedResults);
		
		return resultSet;
		}
	
	
	/**
	 * Queries the API for a list of sessions matching a specific set of search terms
	 * 
	 * @param searchTerms The search terms provided by the user
	 * @param resultsPerPage Specify how many results should be returned per page. The maximum is 50.
	 * @return an ArrayList of SessionsSearchResult objects
	 * @throws RuntimeException 
	 * @throws MalformedURLException 
	 */
	public ArrayList<SessionsSearchResult> searchSessions(String searchTerms, int resultsPerPage) throws MalformedURLException, RuntimeException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			}
		
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
		
		// Launch a search for a list of matching sessions and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitSearchRequest("sessions", searchTerms, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		SessionsSearchParser jsonParser = new SessionsSearchParser();
		SessionsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
		
		// This will hold each individual search result entry
		ArrayList<SessionsSearchResult> resultSet = new ArrayList <SessionsSearchResult>();
				
		// Use a private helper method to populate the ArrayList of SessionsSearchResult objects
		resultSet = populateSessionsSearchResult(parsedResults);

		// Return the set of results that has been collected
		return resultSet;
		}
	
	
	/**
	 * Alternative version of searchSessions(String searchTerms, int resultsPerPage), allowing the caller to specify an individual page within a paginated JSON response
	 * 
	 * @param searchTerms The search terms provided by the user
	 * @param resultsPerPage Specify how many results should be returned per page. The maximum is 50.
	 * @param pageNumber a specific page within a paginated JSON response
	 * @return an ArrayList of SessionsSearchResult objects
	 * @throws RuntimeException 
	 * @throws MalformedURLException 
	 */
	public ArrayList<SessionsSearchResult> searchSessions(String searchTerms, int resultsPerPage, int pageNumber) throws MalformedURLException, RuntimeException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			}
		
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitSearchRequest("tunes",searchTerms, resultsPerPage,pageNumber);
		
		// Parse the returned JSON into a wrapper class to allow access to all elements
		SessionsSearchParser jsonParser = new SessionsSearchParser();
		SessionsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
		
		// This will hold each individual search result entry
		ArrayList<SessionsSearchResult> resultSet = new ArrayList <SessionsSearchResult>();
				
		// Use a private helper method to populate the ArrayList of SessionsSearchResult objects
		resultSet = populateSessionsSearchResult(parsedResults);

		// Return the set of results that has been collected
		return resultSet;
		}
	
	
	/**
	 * Helper method to keep track of how many pages are in a particular JSON response
	 * 
	 * @return the number of pages in the JSON response
	 * @throws IllegalStateException
	 */
	public int getPageCount() throws IllegalStateException
		{
		if (pageCount == 0)
			{
			throw new IllegalStateException("Page counter has not been initialised");
			}
		else 
			return pageCount;
		}
	
	
	/**
	 * Helper method to gather and parse the response to a keyword search for a tune
	 * 
	 * @param parsedResults a TunesSearchResultWrapper object that has already been created an populated
	 * @return an ArrayList of TunesSearchResult objects
	 */
	private ArrayList<TunesSearchResult> populateTunesSearchResult(TunesSearchResultWrapper parsedResults)
		{
		ArrayList <TunesSearchResult> resultSet = new ArrayList <TunesSearchResult>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of tunes in the result set:
		for(int i = 0; i < (parsedResults.tunes.length); i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response
			TuneDetails details = new TuneDetails(parsedResults.tunes[i].id, StringEscapeUtils.unescapeXml(parsedResults.tunes[i].name), parsedResults.tunes[i].type, parsedResults.tunes[i].url, parsedResults.tunes[i].date);
			User submitter = new User(Integer.toString(parsedResults.tunes[i].member.id), StringEscapeUtils.unescapeXml(parsedResults.tunes[i].member.name), parsedResults.tunes[i].member.url);
						
			// Instantiate a TunesSearchResult object & populate it
			TunesSearchResult currentResult = new TunesSearchResult(details, submitter);
			
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
	private ArrayList<DiscussionsSearchResult> populateDiscussionsSearchResult(DiscussionsSearchResultWrapper parsedResults)
		{
		// Use a TunesSearchParser to parse the raw JSON into a usable structure using Gson
		ArrayList<DiscussionsSearchResult> resultSet = new ArrayList <DiscussionsSearchResult>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages if needed
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.discussions.length); i++)
			{
			// Extract the elements from each individual search result in the JSON response
			// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response		
			DiscussionDetails details = new DiscussionDetails(parsedResults.discussions[i].id, StringEscapeUtils.unescapeXml(parsedResults.discussions[i].name), parsedResults.discussions[i].url, parsedResults.discussions[i].date, parsedResults.discussions[i].comments);
			User user = new User(Integer.toString(parsedResults.discussions[i].member.id), StringEscapeUtils.unescapeXml(parsedResults.discussions[i].member.name), parsedResults.discussions[i].member.url);
			
			// Instantiate a DiscussionsSearchResult object & populate it
			DiscussionsSearchResult currentResult = new DiscussionsSearchResult(details, user);
			
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
	private ArrayList<EventsSearchResult> populateEventsSearchResult(EventsSearchResultWrapper parsedResults)	
		{
		ArrayList<EventsSearchResult> resultSet = new ArrayList <EventsSearchResult>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.events.length); i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response
			EventDetails details = new EventDetails(parsedResults.events[i].id, StringEscapeUtils.unescapeXml(parsedResults.events[i].name), parsedResults.events[i].url, parsedResults.events[i].date);
			User user = new User(Integer.toString(parsedResults.events[i].member.id),StringEscapeUtils.unescapeXml(parsedResults.events[i].member.name),parsedResults.events[i].member.url);
			EventSchedule schedule = new EventSchedule(parsedResults.events[i].dtstart, parsedResults.events[i].dtend);
			Coordinates coordinates = new Coordinates(parsedResults.events[i].latitude, parsedResults.events[i].longitude);
			Venue venue = new Venue(Integer.toString(parsedResults.events[i].venue.id), StringEscapeUtils.unescapeXml(parsedResults.events[i].venue.name), parsedResults.events[i].venue.telephone, parsedResults.events[i].venue.email, parsedResults.events[i].venue.web );
			Town town = new Town(Integer.toString(parsedResults.events[i].town.id), StringEscapeUtils.unescapeXml(parsedResults.events[i].town.name));
			Area area = new Area(Integer.toString(parsedResults.events[i].area.id), StringEscapeUtils.unescapeXml(parsedResults.events[i].area.name));
			Country country = new Country(Integer.toString(parsedResults.events[i].country.id), StringEscapeUtils.unescapeXml(parsedResults.events[i].country.name));
			
			
			// Instantiate a EventsSearchResult object & populate it
			EventsSearchResult currentResult = new EventsSearchResult(details, user, schedule, coordinates, venue, town, area, country);
			
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
	private ArrayList<RecordingsSearchResult> populateRecordingsSearchResult(RecordingsSearchResultWrapper parsedResults)	
		{
		ArrayList<RecordingsSearchResult> resultSet = new ArrayList <RecordingsSearchResult>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.recordings.length); i++)
			{
			// Extract the elements from each individual search result in the JSON response
			// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response		
			RecordingDetails details = new RecordingDetails(parsedResults.recordings[i].id, StringEscapeUtils.unescapeXml(parsedResults.recordings[i].name), parsedResults.recordings[i].url, parsedResults.recordings[i].date);
			User user = new User(Integer.toString(parsedResults.recordings[i].member.id), StringEscapeUtils.unescapeXml(parsedResults.recordings[i].member.name), parsedResults.recordings[i].member.url);
			Artist artist = new Artist(Integer.toString(parsedResults.recordings[i].artist.id), StringEscapeUtils.unescapeXml(parsedResults.recordings[i].artist.name), parsedResults.recordings[i].artist.url);
			
			// Instantiate a RecordingsSearchResult object & populate it
			RecordingsSearchResult currentResult = new RecordingsSearchResult(details, user, artist);
			
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
	private ArrayList<SessionsSearchResult> populateSessionsSearchResult(SessionsSearchResultWrapper parsedResults)	
		{
		ArrayList<SessionsSearchResult> resultSet = new ArrayList <SessionsSearchResult>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of sessions in the result set:
		for(int i = 0; i < (parsedResults.sessions.length); i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response
			SessionDetails details = new SessionDetails(parsedResults.sessions[i].id, parsedResults.sessions[i].url, parsedResults.sessions[i].date);
			Coordinates coordinates = new Coordinates(parsedResults.sessions[i].latitude, parsedResults.sessions[i].longitude);
			User user = new User(Integer.toString(parsedResults.sessions[i].member.id),StringEscapeUtils.unescapeXml(parsedResults.sessions[i].member.name),parsedResults.sessions[i].member.url);
			Venue venue = new Venue(Integer.toString(parsedResults.sessions[i].venue.id), StringEscapeUtils.unescapeXml(parsedResults.sessions[i].venue.name), parsedResults.sessions[i].venue.telephone, parsedResults.sessions[i].venue.email, parsedResults.sessions[i].venue.web );
			Town town = new Town(Integer.toString(parsedResults.sessions[i].town.id), StringEscapeUtils.unescapeXml(parsedResults.sessions[i].town.name));
			Area area = new Area(Integer.toString(parsedResults.sessions[i].area.id), StringEscapeUtils.unescapeXml(parsedResults.sessions[i].area.name));
			Country country = new Country(Integer.toString(parsedResults.sessions[i].country.id), StringEscapeUtils.unescapeXml(parsedResults.sessions[i].country.name));
			
			
			// Instantiate a SessionsSearchResult object & populate it
			SessionsSearchResult currentResult = new SessionsSearchResult(details, coordinates, user, venue, town, area, country);
			
			// Add the SessionsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
			
		return resultSet;
		}
	
	
	/**
	 * Helper method to validate that the user has specified a value between 1-50 for the results per page
	 * 
	 * @param resultsPerPage the number of results to be returned per page in the JSON response from the API
	 * @return true if a valid number of results per page has been provided (i.e. 1 - 50)
	 * @throws IllegalArgumentException if a value of zero or less, or a value of more than 50 was provided
	 */
	private boolean validateResultsPerPageCount(int resultsPerPage) throws IllegalArgumentException
		{
		if (resultsPerPage <= 0)
			{
			// Specifying zero or a negative number of results per page should not be allowed
			throw new IllegalArgumentException("Number of results per page must be greater than zero");
			}
		
		if (resultsPerPage > 50)
			{
			// The API only allows a maximum of 50 results per page
			throw new IllegalArgumentException("Number of results per page cannot exceed 50");
			}
		
		// The value specified is in the range 1 - 50 and is valid
		else return true;
		}
	
	}
