package sessionbuddy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

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
 * @since 2018-03-07
 *
 */
public class KeywordSearch extends Search
	{
	/**
	 * A text string containing the search terms / keywords to use when performing a keyword-based search
	 */
	private String searchTerms = null;
	
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
	 * @param searchTerms A string containing the search terms / keywords to use in the search
	 * @param resultsPerPage Specifies how many search results should appear in each page of the JSON response from the API
	 */
	public KeywordSearch(String searchTerms, int resultsPerPage)
		{
		this.searchTerms = searchTerms;
		this.resultsPerPage = resultsPerPage;
		}
	
	
	/**
	 * Constructor for cases where you need to specify an individual page in the API response
	 * 
	 * @param searchTerms A string containing the search terms / keywords to use in the search
	 * @param resultsPerPage Specifies how many search results should appear in each page of the JSON response from the API
	 * @param pageNumber Specifies a particular page number within the JSON response
	 */
	public KeywordSearch(String searchTerms, int resultsPerPage, int pageNumber)
		{
		this(searchTerms, resultsPerPage);
		this.pageNumber= pageNumber;
		}
	
	
	/**
	 * Searches the API for a list of tunes matching a specific set of search terms
	 * 
	 * @return An ArrayList of TunesSearchResult objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-03-11
	 */
	public ArrayList<SearchResultTunes> searchTunes() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Perform the API query, using a helper method to create the URL, and capture the response
			String response = HttpRequestor.submitRequest(composeURL("tunes"));
			
			// Parse the returned JSON into a wrapper class to allow access to all elements
			KeywordSearchWrapperTunes parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperTunes.class);

			// Create an ArrayList to hold the parsed response from the API, and populate it with a helper method
			ArrayList<SearchResultTunes> resultSet = populateTunesSearchResult(parsedResults);
			
			return resultSet;
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
			{
			throw ex;
			}
		}
			
	
	/**
	 * Queries the API for a list of discussions matching a specific set of search terms
	 * 
	 * @return An ArrayList of DiscussionsSearchResult objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-03-07
	 */
	public ArrayList<SearchResultsDiscussions> searchDiscussions() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Perform the API query, using a helper method to create the URL, and capture the response
			String response = HttpRequestor.submitRequest(composeURL("discussions"));
			
			// Prepare the classes needed to parse the the JSON
			KeywordSearchWrapperDiscussions parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperDiscussions.class);
			
			// Create an ArrayList to hold the parsed response from the API, and populate it with a helper method
			ArrayList<SearchResultsDiscussions> resultSet = populateDiscussionsSearchResult(parsedResults);
			
			return resultSet;
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
			{
			throw ex;
			}
		}
	
	
	/**
	 * Queries the API for a list of events matching a specific set of search terms
	 * 
	 * @return an ArrayList of EventsSearchResult objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-03-11
	 */
	public ArrayList<SearchResultEvents> searchEvents() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);

			// Perform the API query, using a helper method to create the URL, and capture the response
			String response = HttpRequestor.submitRequest(composeURL("events"));
				
			// Parse the returned JSON into a wrapper class to allow access to all elements
			KeywordSearchWrapperEvents parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperEvents.class);
				
			// Create an ArrayList to hold the parsed response from the API, and populate it with a helper method
			ArrayList<SearchResultEvents> resultSet = populateEventsSearchResult(parsedResults);
			
			return resultSet;
			}
			
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
			{
			throw ex;
			}
		}
		
	
	/**
	 * Queries the API for a list of recordings matching a specific set of search terms
	 * 
	 * @return an ArrayList of RecordingsSearchResult objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-03-07
	 */
	public ArrayList<SearchResultsRecordings> searchRecordings() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Perform the API query, using a helper method to create the URL, and capture the response
			String response = HttpRequestor.submitRequest(composeURL("recordings"));
				
			// Parse the returned JSON into a wrapper class to allow access to all elements
			KeywordSearchWrapperRecordings parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperRecordings.class);
				
			// Create an ArrayList to hold the parsed response from the API, and populate it with a helper method
			ArrayList<SearchResultsRecordings> resultSet = populateRecordingsSearchResult(parsedResults);
			
			return resultSet;
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
			{
			throw ex;
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
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-03-04
	 */
	public ArrayList<SearchResultSessions> searchSessions() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);

			// Perform the API query, using a helper method to create the URL, and capture the response
			String response = HttpRequestor.submitRequest(composeURL("sessions"));
				
			// Parse the returned JSON into a wrapper class to allow access to all elements
			KeywordSearchWrapperSessions parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperSessions.class);
			
			// Create an ArrayList to hold the parsed response from the API, and populate it with a helper method
			ArrayList<SearchResultSessions> resultSet = populateSessionsSearchResult(parsedResults);
			
			// Return the set of results that has been collected
			return resultSet;
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
			{
			throw ex;
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
		// Parse the search terms provided by the user
		List<NameValuePair> queryParams = new ArrayList<>();
		queryParams.add(new BasicNameValuePair("q", searchTerms));
				
		URL requestURL;	
		
		// If a particular page within the response from the API is specified:
		if (pageNumber > 0)
			{
			UrlBuilder builder = new UrlBuilder();
			
			requestURL = builder.new Builder()
					.path(dataCategory + "/" + "search")
					.queryParameters(queryParams)
					.itemsPerPage(resultsPerPage)
					.pageNumber(pageNumber)
					.build();
			}
		
		// If no page is specified
		else if (pageNumber == 0)		
			{
			UrlBuilder builder = new UrlBuilder();
			
			requestURL = builder.new Builder()
					.path(dataCategory + "/" + "search")
					.queryParameters(queryParams)
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
