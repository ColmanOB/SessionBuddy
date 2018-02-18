package sessionbuddy;

import java.io.IOException;
import java.util.ArrayList;

import sessionbuddy.utils.HttpRequestor;
import sessionbuddy.utils.JsonResponseParser;
import sessionbuddy.utils.StringCleaner;
import sessionbuddy.wrappers.granularobjects.Area;
import sessionbuddy.wrappers.granularobjects.Artist;
import sessionbuddy.wrappers.granularobjects.Coordinates;
import sessionbuddy.wrappers.granularobjects.Country;
import sessionbuddy.wrappers.granularobjects.EventDetails;
import sessionbuddy.wrappers.granularobjects.EventSchedule;
import sessionbuddy.wrappers.granularobjects.LatestSettingDetails;
import sessionbuddy.wrappers.granularobjects.LatestTuneDetails;
import sessionbuddy.wrappers.granularobjects.RecordingDetails;
import sessionbuddy.wrappers.granularobjects.SessionDetails;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperEvents;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperRecordings;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperSessions;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperTunes;
import sessionbuddy.wrappers.resultsets.LatestSearchTunes;
import sessionbuddy.wrappers.resultsets.SearchResultEvents;
import sessionbuddy.wrappers.resultsets.SearchResultSessions;
import sessionbuddy.wrappers.resultsets.SearchResultsRecordings;

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
	 * Retrieves a list of recordings added by a particular user on thesession.org, with the most recent results first
	 * 
	 * @param resultsPerPage the number of results you want to be returned per page in the JSON response
	 * @return an ArrayList of SearchResultsRecordings objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * 
	 * @author Colman
	 * @since 2018-02-18
	 */
	public ArrayList<SearchResultsRecordings> getRecordings(int resultsPerPage, int userID) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of most recently submitted recordings and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitMemberContributionRequest("recordings", userID, resultsPerPage);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			KeywordSearchWrapperRecordings parsedResults = jsonParser.parseResponse(KeywordSearchWrapperRecordings.class);
							
			// This will hold each individual search result entry
			ArrayList<SearchResultsRecordings> resultSet = new ArrayList<SearchResultsRecordings>();
			
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
		}
	
	
	/**
	 * An alternative version of the getRecordings method, allowing the caller to specify a particular page within the JSON response
	 * 
	 * @param resultsPerPage the number of results you want to be returned per page in the JSON response
	 * @param pageNumber allows you to specify an individual page within the JSON response
	 * @return an ArrayList of SearchResultsRecordings objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * 
	 * @author Colman
	 * @since 2018-02-18
	 */
	public ArrayList<SearchResultsRecordings> getRecordings(int resultsPerPage, int userID, int pageNumber) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of most recently submitted recordings and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitMemberContributionRequest("recordings", userID, resultsPerPage, pageNumber);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			KeywordSearchWrapperRecordings parsedResults = jsonParser.parseResponse(KeywordSearchWrapperRecordings.class);
					
			// This will hold each individual search result entry
			ArrayList<SearchResultsRecordings> resultSet = new ArrayList<SearchResultsRecordings>();
			
			resultSet = populateRecordingsSearchResult(parsedResults);
			
			return resultSet;
			}
		
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
	
		catch (IOException e)
			{
			throw new IOException (e.getMessage());
			}
		}
	
	
	/**
	 * Retrieves session listings added by a particular member
	 * 
	 * @param resultsPerPage the number of results you want to be returned per page in the JSON response
	 * @return an ArrayList of SearchResultSessions objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * 
	 * @author Colman
	 * @since 2018-02-18
	 */
	public ArrayList<SearchResultSessions> getSessions(int resultsPerPage, int userID) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of most recently submitted sessions and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitMemberContributionRequest("sessions", userID, resultsPerPage);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			KeywordSearchWrapperSessions parsedResults = jsonParser.parseResponse(KeywordSearchWrapperSessions.class);
							
			// This will hold each individual search result entry
			ArrayList<SearchResultSessions> resultSet = new ArrayList <SearchResultSessions>();
					
			resultSet = populateSessionsSearchResult(parsedResults);
			
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
	 * Retrieves a list of events added by a particular member on thesession.org
	 * 
	 * @param resultsPerPage the number of results you want to be returned per page in the JSON response
	 * @return an ArrayList of SearchResultEvents objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * 
	 * @author Colman
	 * @since 2018-02-18
	 */
	public ArrayList<SearchResultEvents> getEvents(int resultsPerPage, int userID) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of most recently submitted events and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitMemberContributionRequest("events", userID, resultsPerPage);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			KeywordSearchWrapperEvents parsedResults = jsonParser.parseResponse(KeywordSearchWrapperEvents.class);
							
			// This will hold each individual search result entry
			ArrayList<SearchResultEvents> resultSet = new ArrayList <SearchResultEvents>();
			
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
		}
	
	
	/**
	 * An alternative version of getLatestEvents that allows the caller to specify an idividual page within the JSON response
	 * 
	 * @param resultsPerPage the number of results you want to be returned per page in the JSON response
	 * @param pageNumber allows you to specify an individual page within the JSON response
	 * @return an ArrayList of SearchResultEvents objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * 
	 * @author Colman
	 * @since 2018-02-18
	 */
	public ArrayList<SearchResultEvents> getEvents(int resultsPerPage, int userID, int pageNumber) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of most recently submitted events and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitMemberContributionRequest("events", userID, resultsPerPage, pageNumber);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			KeywordSearchWrapperEvents parsedResults = jsonParser.parseResponse(KeywordSearchWrapperEvents.class);
							
			// This will hold each individual search result entry
			ArrayList<SearchResultEvents> resultSet = new ArrayList <SearchResultEvents>();
			
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
		}	
	
	/**
	 * An alternative version of getSessions that allows the caller to specify an individual page within the JSON response
	 * 
	 * @param resultsPerPage the number of results you want to be returned per page in the JSON response
	 * @param pageNumber allows you to specify an individual page within the JSON response
	 * @return an ArrayList of SearchResultSessions objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * 
	 * @author Colman
	 * @since 2018-02-18
	 */
	public ArrayList<SearchResultSessions> getSessions(int resultsPerPage, int userID, int pageNumber) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of most recently submitted sessions and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitMemberContributionRequest("sessions", userID, resultsPerPage, pageNumber);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			KeywordSearchWrapperSessions parsedResults = jsonParser.parseResponse(KeywordSearchWrapperSessions.class);
							
			// This will hold each individual search result entry
			ArrayList<SearchResultSessions> resultSet = new ArrayList <SearchResultSessions>();
					
			resultSet = populateSessionsSearchResult(parsedResults);
			
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
	
	}
