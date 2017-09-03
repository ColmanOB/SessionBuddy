package main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

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
import result_set_wrappers.DiscussionsSearchResultWrapper;
import result_set_wrappers.EventsSearchResultWrapper;
import result_set_wrappers.RecordingsSearchResultWrapper;
import result_set_wrappers.SessionsSearchResultWrapper;
import result_set_wrappers.TunesSearchResultWrapper;
import utils.HttpRequestor;
import utils.JsonResponseParser;
import utils.StringCleaner;

// TODO: Fix up all of the comments, especially Javadoc ones

/**
 * @author Colman O'B
 * @since 2017-09-02
 */
public class LatestSearch extends Search 
	{

	/**
	 * @param resultsPerPage
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public ArrayList<TunesSearchResult> getLatestTunes(int resultsPerPage) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);

			// Launch a search for a list of most recently submitted tunes and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitLatestRequest("tunes", resultsPerPage);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			TunesSearchResultWrapper parsedResults = jsonParser.parseResponse(TunesSearchResultWrapper.class);
								
			// This will hold each individual search result entry
			ArrayList<TunesSearchResult> resultSet = new ArrayList<TunesSearchResult>();
			
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
	 * @param resultsPerPage
	 * @param pageNumber
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public ArrayList<TunesSearchResult> getLatestTunes(int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of most recently submitted tunes and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitLatestRequest("tunes", resultsPerPage, pageNumber);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			TunesSearchResultWrapper parsedResults = jsonParser.parseResponse(TunesSearchResultWrapper.class);
							
			// This will hold each individual search result entry
			ArrayList<TunesSearchResult> resultSet = new ArrayList<TunesSearchResult>();
			
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
	 * @param resultsPerPage
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public ArrayList<DiscussionsSearchResult> getLatestDiscussions(int resultsPerPage) throws IOException, MalformedURLException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of latest discussions and store the JSON response as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitLatestRequest("discussions", resultsPerPage);
				
			// Instantiate a DiscussionSearchParser and DiscussionSearchResultWrapper needed to handle the raw JSON
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			DiscussionsSearchResultWrapper parsedResults = jsonParser.parseResponse(DiscussionsSearchResultWrapper.class);

			// This will hold each individual search result entry
			ArrayList<DiscussionsSearchResult> resultSet = new ArrayList <DiscussionsSearchResult>();
			
			resultSet = populateDiscussionsSearchResult(parsedResults);
				
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
	 * @param resultsPerPage
	 * @param pageNumber
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public ArrayList<DiscussionsSearchResult> getLatestDiscussions(int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of latest discussions and store the JSON response as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitLatestRequest("discussions", resultsPerPage, pageNumber);
				
			// Instantiate a DiscussionSearchParser and DiscussionSearchResultWrapper needed to handle the raw JSON
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			DiscussionsSearchResultWrapper parsedResults = jsonParser.parseResponse(DiscussionsSearchResultWrapper.class);
			
			ArrayList<DiscussionsSearchResult> resultSet = new ArrayList <DiscussionsSearchResult>();
			
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
		}
	
	
	/**
	 * @param resultsPerPage
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public ArrayList<RecordingsSearchResult> getLatestRecordings(int resultsPerPage) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of most recently submitted recordings and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitLatestRequest("recordings", resultsPerPage);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			RecordingsSearchResultWrapper parsedResults = jsonParser.parseResponse(RecordingsSearchResultWrapper.class);
							
			// This will hold each individual search result entry
			ArrayList<RecordingsSearchResult> resultSet = new ArrayList<RecordingsSearchResult>();
			
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
	 * @param resultsPerPage
	 * @param pageNumber
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public ArrayList<RecordingsSearchResult> getLatestRecordings(int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of most recently submitted recordings and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitLatestRequest("recordings", resultsPerPage, pageNumber);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			RecordingsSearchResultWrapper parsedResults = jsonParser.parseResponse(RecordingsSearchResultWrapper.class);
					
			// This will hold each individual search result entry
			ArrayList<RecordingsSearchResult> resultSet = new ArrayList<RecordingsSearchResult>();
			
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
	 * @param resultsPerPage
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public ArrayList<SessionsSearchResult> getLatestSessions(int resultsPerPage) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of most recently submitted sessions and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitLatestRequest("sessions", resultsPerPage);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			SessionsSearchResultWrapper parsedResults = jsonParser.parseResponse(SessionsSearchResultWrapper.class);
							
			// This will hold each individual search result entry
			ArrayList<SessionsSearchResult> resultSet = new ArrayList <SessionsSearchResult>();
					
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
	 * @param resultsPerPage
	 * @param pageNumber
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public ArrayList<SessionsSearchResult> getLatestSessions(int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of most recently submitted sessions and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitLatestRequest("sessions", resultsPerPage, pageNumber);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			SessionsSearchResultWrapper parsedResults = jsonParser.parseResponse(SessionsSearchResultWrapper.class);
							
			// This will hold each individual search result entry
			ArrayList<SessionsSearchResult> resultSet = new ArrayList <SessionsSearchResult>();
					
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
	 * @param resultsPerPage
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public ArrayList<EventsSearchResult> getLatestEvents(int resultsPerPage) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of most recently submitted events and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitLatestRequest("events", resultsPerPage);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			EventsSearchResultWrapper parsedResults = jsonParser.parseResponse(EventsSearchResultWrapper.class);
							
			// This will hold each individual search result entry
			ArrayList<EventsSearchResult> resultSet = new ArrayList <EventsSearchResult>();
			
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
	 * @param resultsPerPage
	 * @param pageNumber
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public ArrayList<EventsSearchResult> getLatestEvents(int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);
			
			// Launch a search for a list of most recently submitted events and store the JSON that is returned as a String
			HttpRequestor searcher = new HttpRequestor();
			String response = searcher.submitLatestRequest("events", resultsPerPage, pageNumber);
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			EventsSearchResultWrapper parsedResults = jsonParser.parseResponse(EventsSearchResultWrapper.class);
							
			// This will hold each individual search result entry
			ArrayList<EventsSearchResult> resultSet = new ArrayList <EventsSearchResult>();
			
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
			// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
			TuneDetails details = new TuneDetails(parsedResults.tunes[i].id, StringCleaner.cleanString(parsedResults.tunes[i].name), parsedResults.tunes[i].type, parsedResults.tunes[i].url, parsedResults.tunes[i].date);
			User submitter = new User(Integer.toString(parsedResults.tunes[i].member.id), StringCleaner.cleanString(parsedResults.tunes[i].member.name), parsedResults.tunes[i].member.url);
						
			// Instantiate a TunesSearchResult object & populate it
			TunesSearchResult currentResult = new TunesSearchResult(details, submitter);
			
			// Add the TuneSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the fully populated ArrayList
		return resultSet;
		}
	
	
	/**
	 * Helper method to gather and parse the response to a search for the latest discussions
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
			// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response		
			DiscussionDetails details = new DiscussionDetails(parsedResults.discussions[i].id, StringCleaner.cleanString(parsedResults.discussions[i].name), parsedResults.discussions[i].url, parsedResults.discussions[i].date, parsedResults.discussions[i].comments);
			User user = new User(Integer.toString(parsedResults.discussions[i].member.id), StringCleaner.cleanString(parsedResults.discussions[i].member.name), parsedResults.discussions[i].member.url);
			
			// Instantiate a DiscussionsSearchResult object & populate it
			DiscussionsSearchResult currentResult = new DiscussionsSearchResult(details, user);
			
			// Add the DiscussionsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}
	
	
	/**
	 * Helper method to gather and parse the response to a search for the latest recordings
	 * 
	 * @param parsedResults
	 * @return
	 */
	private ArrayList<RecordingsSearchResult> populateRecordingsSearchResult(RecordingsSearchResultWrapper parsedResults)
		{
		// Use a TunesSearchParser to parse the raw JSON into a usable structure using Gson
		ArrayList<RecordingsSearchResult> resultSet = new ArrayList <RecordingsSearchResult>();
		
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
			RecordingsSearchResult currentResult = new RecordingsSearchResult(details, submitter, artist);
						
			// Add the RecordingsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}
	
	
	/**
	 * Helper method to gather and parse the response to a search for the latest sessions
	 * 
	 * @param parsedResults
	 * @return
	 */
	private ArrayList<SessionsSearchResult> populateSessionsSearchResult(SessionsSearchResultWrapper parsedResults)
		{
		// Use a TunesSearchParser to parse the raw JSON into a usable structure using Gson
		ArrayList<SessionsSearchResult> resultSet = new ArrayList <SessionsSearchResult>();
		
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
			SessionsSearchResult currentResult = new SessionsSearchResult(details, coordinates, submitter, venue, town, area, country);
						
			// Add the SessionsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}
	
	
	/**
	 * Helper method to gather and parse the response to a search for the latest events
	 * 
	 * @param parsedResults
	 * @return
	 */
	private ArrayList<EventsSearchResult> populateEventsSearchResult(EventsSearchResultWrapper parsedResults)
		{
		// Use a TunesSearchParser to parse the raw JSON into a usable structure using Gson
		ArrayList<EventsSearchResult> resultSet = new ArrayList <EventsSearchResult>();
		
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
			EventsSearchResult currentResult = new EventsSearchResult(details, submitter, schedule, coordinates, venue, town, area, country);
						
			// Add the EventsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}	
	}
