package sessionbuddy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import sessionbuddy.utils.HttpRequestor;
import sessionbuddy.utils.JsonParser;
import sessionbuddy.utils.StringCleaner;
import sessionbuddy.utils.UrlBuilder;
import sessionbuddy.wrappers.granularobjects.Area;
import sessionbuddy.wrappers.granularobjects.Artist;
import sessionbuddy.wrappers.granularobjects.Coordinates;
import sessionbuddy.wrappers.granularobjects.Country;
import sessionbuddy.wrappers.granularobjects.DiscussionDetails;
import sessionbuddy.wrappers.granularobjects.EventDetails;
import sessionbuddy.wrappers.granularobjects.EventSchedule;
import sessionbuddy.wrappers.granularobjects.LatestSettingDetails;
import sessionbuddy.wrappers.granularobjects.LatestTuneDetails;
import sessionbuddy.wrappers.granularobjects.RecordingDetails;
import sessionbuddy.wrappers.granularobjects.SessionDetails;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperDiscussions;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperEvents;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperRecordings;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperSessions;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperTunes;
import sessionbuddy.wrappers.resultsets.LatestSearchTunes;
import sessionbuddy.wrappers.resultsets.SearchResultEvents;
import sessionbuddy.wrappers.resultsets.SearchResultSessions;
import sessionbuddy.wrappers.resultsets.SearchResultsDiscussions;
import sessionbuddy.wrappers.resultsets.SearchResultsRecordings;


/**
 * Retrieves a list of member contributions in a chosen category - tunes, discussions, recordings, events, sessions or sets.
 * 
 * @author Colman O'B
 * @since 2018-03-11
 */
public class MemberContributionSearch extends Search 
	{
	/**
	 * The ID number of the user in question within thesession.org database
	 */
	private int userID = 0;
	
	/**
	 * The number of individual search results that should be returned per page in the JSON response from the API
	 */
	private int resultsPerPage = 0;
	
	/**
	 * When dealing with a JSON response containing multiple pages, this specifies a particular page
	 */
	private int pageNumber = 0;
	
	
	/**
	 * Constructor where pagination is not required and you only want to see the first page of the API response
	 * 
	 * @param userID The numeric ID of the user in question in thesession.org database
	 * @param resultsPerPage Specifies how many search results should appear in each page of the JSON response from the API
	 */
	public MemberContributionSearch(int userID, int resultsPerPage)
		{
		this.userID = userID;
		this.resultsPerPage = resultsPerPage;
		}
	
	
	/**
	 * Constructor for cases where you need to specify an individual page in the API response
	 * 
	 * @param userID The numeric ID of the user in question in thesession.org database
	 * @param resultsPerPage Specifies how many search results should appear in each page of the JSON response from the API
	 * @param pageNumber Specifies a particular page number within the JSON response
	 */
	public MemberContributionSearch(int userID, int resultsPerPage, int pageNumber)
		{
		this(userID, resultsPerPage);
		this.pageNumber= pageNumber;
		}
	
	
	/**
	 * Retrieves the tunes/settings added by a particular member on thesession.org, most recent first
	 * 
	 * @return an ArrayList of LatestSearchTunes objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-03-11
	 */
	public ArrayList<LatestSearchTunes> listTunes() throws IllegalArgumentException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
	
			// Compose the URL, perform the API query and capture the response
			String response = HttpRequestor.submitRequest(composeURL("tunes"));
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			LatestWrapperTunes parsedResults = JsonParser.parseResponse(response, LatestWrapperTunes.class);
								
			// This will hold each individual search result entry
			ArrayList<LatestSearchTunes> resultSet = populateTunesSearchResult(parsedResults);

			return resultSet;
			}
		
		catch (IllegalArgumentException | IOException | URISyntaxException ex)
			{
			throw ex;
			}	
		}
		
	
	/**
	 * Retrieves a list of recordings added by a particular user on thesession.org, with the most recent results first
	 * 
	 * @return an ArrayList of SearchResultsRecordings objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-03-04
	 */
	public ArrayList<SearchResultsRecordings> listRecordings() throws IllegalArgumentException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
						
			// Compose the URL, perform the API query and capture the response
			String response = HttpRequestor.submitRequest(composeURL("recordings"));
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			KeywordSearchWrapperRecordings parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperRecordings.class);
							
			// This holds each individual search result entry
			ArrayList<SearchResultsRecordings> resultSet = populateRecordingsSearchResult(parsedResults);
			
			return resultSet;
			}
		
		catch (IllegalArgumentException | IOException | URISyntaxException ex)
			{
			throw ex;
			}
		}
	
	
	/**
	 * Retrieves a list of sessions added by a particular user on thesession.org, with the most recent results first
	 * 
	 * @return an ArrayList of SearchResultSessions objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-03-11
	 */
	public ArrayList<SearchResultSessions> listSessions() throws IllegalArgumentException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Perform the API query and capture the response
			String response = HttpRequestor.submitRequest(composeURL("sessions"));
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			KeywordSearchWrapperSessions parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperSessions.class);
							
			// This will hold each individual search result entry
			ArrayList<SearchResultSessions> resultSet = populateSessionsSearchResult(parsedResults);
			
			return resultSet;
			}
		
		catch (IllegalArgumentException | IOException | URISyntaxException ex)
			{
			throw ex;
			}
		}
	
	
	/**
	 * Retrieves a list of events added by a particular member on thesession.org
	 * 
	 * @return an ArrayList of SearchResultEvents objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-03-11
	 */
	public ArrayList<SearchResultEvents> listEvents() throws IllegalArgumentException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
						
			// Perform the API query and capture the response
			String response = HttpRequestor.submitRequest(composeURL("events"));
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			KeywordSearchWrapperEvents parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperEvents.class);
							
			// This will hold each individual search result entry
			ArrayList<SearchResultEvents> resultSet = populateEventsSearchResult(parsedResults);
			
			return resultSet;
			}
		
		catch (IllegalArgumentException | IOException | URISyntaxException ex)
			{
			throw ex;
			}
		}
		
	
	/**
	 * Retrieves a list of discussions submitted by a particular user
	 * 
	 * @return an ArrayList of SearchResultsDiscussion objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-03-11
	 */
	public ArrayList<SearchResultsDiscussions> listDiscussions() throws IllegalArgumentException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Perform the API query and capture the response
			String response = HttpRequestor.submitRequest(composeURL("discussions"));
				
			// Instantiate a DiscussionSearchParser and DiscussionSearchResultWrapper needed to handle the raw JSON
			KeywordSearchWrapperDiscussions parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperDiscussions.class);

			// This will hold each individual search result entry
			ArrayList<SearchResultsDiscussions> resultSet = populateDiscussionsSearchResult(parsedResults);
				
			return resultSet;
			}
		
		catch (IllegalArgumentException | IOException | URISyntaxException ex)
			{
			throw ex;
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
	
	
	/**
	 * Helper method to gather and parse the response to a search for recordings that have been added by a particular user
	 * 
	 * @param parsedResults an existing KeywordSearchWrapperSessions object that has been populated
	 * @return an ArrayList of SearchResultSessions objects
	 * 
	 * @author Colman
	 * @since 2018-02-10
	 */
	private ArrayList<SearchResultsRecordings> populateRecordingsSearchResult(KeywordSearchWrapperRecordings parsedResults)
		{
		// Use a TunesSearchParser to parse the raw JSON into a usable structure using Gson
		ArrayList<SearchResultsRecordings> resultSet = new ArrayList <SearchResultsRecordings>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages if needed
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.recordings.length); i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
			RecordingDetails details = new RecordingDetails(parsedResults.recordings[i].id, StringCleaner.cleanString(parsedResults.recordings[i].name), parsedResults.recordings[i].url, parsedResults.recordings[i].date);
			User submitter = new User(Integer.toString(parsedResults.recordings[i].member.id), StringCleaner.cleanString(parsedResults.recordings[i].member.name), parsedResults.recordings[i].member.url);
			Artist artist = new Artist(Integer.toString(parsedResults.recordings[i].artist.id), StringCleaner.cleanString(parsedResults.recordings[i].artist.name), parsedResults.recordings[i].url);
			
			// Instantiate a RecordingsSearchResult object & populate it
			SearchResultsRecordings currentResult = new SearchResultsRecordings(details, submitter, artist);
						
			// Add the RecordingsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}
	
	
	/**
	 * Helper method to gather and parse the response to a search for sessions added by a particular user
	 * 
	 * @param parsedResults an existing KeywordSearchWrapperSessions object that has been populated
	 * @return an ArrayList of SearchResultSessions objects
	 * 
	 * @author Colman
	 * @since 2018-02-18
	 */
	private ArrayList<SearchResultSessions> populateSessionsSearchResult(KeywordSearchWrapperSessions parsedResults)
		{
		// Use a TunesSearchParser to parse the raw JSON into a usable structure using Gson
		ArrayList<SearchResultSessions> resultSet = new ArrayList <SearchResultSessions>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages if needed
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.sessions.length); i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
			SessionDetails details = new SessionDetails(parsedResults.sessions[i].id, parsedResults.sessions[i].url, parsedResults.sessions[i].date);
			Coordinates coordinates = new Coordinates(parsedResults.sessions[i].latitude, parsedResults.sessions[i].longitude);
			User submitter = new User(Integer.toString(parsedResults.sessions[i].member.id), StringCleaner.cleanString(parsedResults.sessions[i].member.name), parsedResults.sessions[i].member.url);
			Venue venue = new Venue(Integer.toString(parsedResults.sessions[i].venue.id), StringCleaner.cleanString(parsedResults.sessions[i].venue.name), parsedResults.sessions[i].venue.telephone, parsedResults.sessions[i].venue.email, parsedResults.sessions[i].venue.web);
			Town town = new Town(Integer.toString(parsedResults.sessions[i].town.id), StringCleaner.cleanString(parsedResults.sessions[i].town.name));
			Area area = new Area(Integer.toString(parsedResults.sessions[i].area.id), StringCleaner.cleanString(parsedResults.sessions[i].area.name));
			Country country = new Country(Integer.toString(parsedResults.sessions[i].country.id), StringCleaner.cleanString(parsedResults.sessions[i].country.name));
			
			// Instantiate a SessionsSearchResult object & populate it
			SearchResultSessions currentResult = new SearchResultSessions(details, coordinates, submitter, venue, town, area, country);
						
			// Add the SessionsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}
	
	
	
	/**
	 * Helper method to gather and parse the response to a search for events added by a particular user
	 * 
	 * @param parsedResults an existing KeywordSearchWrapperEvents object that has been populated
	 * @return an ArrayList of SearchResultEvents objects
	 * 
	 * @author Colman
	 * @since 2018-02-18
	 */
	private ArrayList<SearchResultEvents> populateEventsSearchResult(KeywordSearchWrapperEvents parsedResults)
		{
		// Use a TunesSearchParser to parse the raw JSON into a usable structure using Gson
		ArrayList<SearchResultEvents> resultSet = new ArrayList <SearchResultEvents>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages if needed
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of events in the result set:
		for(int i = 0; i < (parsedResults.events.length); i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
			EventDetails details = new EventDetails(parsedResults.events[i].id, StringCleaner.cleanString(parsedResults.events[i].name), parsedResults.events[i].url, parsedResults.events[i].date);
			Coordinates coordinates = new Coordinates(parsedResults.events[i].latitude, parsedResults.events[i].longitude);
			EventSchedule schedule = new EventSchedule(parsedResults.events[i].dtstart, parsedResults.events[i].dtend);
			User submitter = new User(Integer.toString(parsedResults.events[i].member.id), StringCleaner.cleanString(parsedResults.events[i].member.name), parsedResults.events[i].member.url);
			Venue venue = new Venue(Integer.toString(parsedResults.events[i].venue.id), StringCleaner.cleanString(parsedResults.events[i].venue.name), parsedResults.events[i].venue.telephone, parsedResults.events[i].venue.email, parsedResults.events[i].venue.web);
			Town town = new Town(Integer.toString(parsedResults.events[i].town.id), StringCleaner.cleanString(parsedResults.events[i].town.name));
			Area area = new Area(Integer.toString(parsedResults.events[i].area.id), StringCleaner.cleanString(parsedResults.events[i].area.name));
			Country country = new Country(Integer.toString(parsedResults.events[i].country.id), StringCleaner.cleanString(parsedResults.events[i].country.name));
			
			// Instantiate a EventsSearchResult object & populate it
			SearchResultEvents currentResult = new SearchResultEvents(details, submitter, schedule, coordinates, venue, town, area, country);
						
			// Add the EventsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}
	
	
	/**
	 * Helper method to gather and parse the response to a search for discussions submitted by a particular user
	 * 
	 * @param parsedResults an existing populated KeywordSearchWrapperDiscussions object
	 * @return an ArrayList of SearchResultsDiscussions objects
	 * 
	 * @author Colman
	 * @since 2018-02-23
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
			DiscussionDetails details = new DiscussionDetails(parsedResults.discussions[i].id, StringCleaner.cleanString(parsedResults.discussions[i].name), parsedResults.discussions[i].url, parsedResults.discussions[i].date);
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
	 * A helper method used to put the URL together to query the API at thesession.org
	 * 
	 * @param dataCategory The category of data to be queried, e.g. tunes, discussions, events etc.
	 * @return A URL specifying a particular resource from thesession.org API
	 * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
	 * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
	 */
	private URL composeURL(String dataCategory) throws MalformedURLException, URISyntaxException
		{
		// Build the URL with all necessary parameters to perform a search via thesession.org API
		URL requestURL;
		
		// If a particular page within the response from the API is specified:
		if (pageNumber > 0)
			{
			UrlBuilder builder = new UrlBuilder();
			
			requestURL = builder.new Builder()
					.path("members" + "/" + userID + "/" + dataCategory)
					.itemsPerPage(resultsPerPage)
					.pageNumber(pageNumber)
					.build();
			}
		
		// If no page is specified
		else if (pageNumber == 0)		
			{
			UrlBuilder builder = new UrlBuilder();
			
			requestURL = builder.new Builder()
					.path("members" + "/" + userID + "/" + dataCategory)
					.itemsPerPage(resultsPerPage)
					.build();
			}
		
		// If anything other than a positive integer was specified as the page number
		else
			{
			throw new IllegalArgumentException("Page number must be an integer value greater than zero");
			}
		
		return requestURL;
		}	
	}
