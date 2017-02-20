package main;

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
import json_object_wrappers.TuneDetailsBasic;
import json_object_wrappers.SettingDetails;
import json_object_wrappers.NewTunesResult;
import json_object_wrappers.User;
import json_object_wrappers.Venue;
import response_parsers_keyword_search.DiscussionsSearchParser;
import response_parsers_keyword_search.EventsSearchParser;
import response_parsers_keyword_search.RecordingsSearchParser;
import response_parsers_keyword_search.SessionsSearchParser;
import response_parsers_new_listings.NewTunesParser;
import result_wrappers_keyword_search.DiscussionsSearchResultWrapper;
import result_wrappers_keyword_search.EventsSearchResultWrapper;
import result_wrappers_keyword_search.RecordingsSearchResultWrapper;
import result_wrappers_keyword_search.SessionsSearchResultWrapper;
import result_wrappers_keyword_search.TunesSearchResultWrapper;
import result_wrappers_new_listings.NewTunesResultWrapper;

public class NewListings 
	{
	private int pageCount = 0;
	
	public ArrayList<NewTunesResult> searchTunes(String baseCategory, String itemIdentifier, int resultsPerPage) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
	
		// Launch a search for a list of matching tunes and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitListRequest(baseCategory, itemIdentifier, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		NewTunesParser jsonParser = new NewTunesParser();
		NewTunesResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
			
		// This will hold each individual search result entry
		ArrayList<NewTunesResult> resultSet = new ArrayList <NewTunesResult>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of tunes in the result set:
		for(int i = 0; i < (parsedResults.settings.length)-1; i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response
			SettingDetails details = new SettingDetails(parsedResults.settings[i].id, StringEscapeUtils.unescapeXml(parsedResults.settings[i].name), parsedResults.settings[i].url, parsedResults.settings[i].date);
			User submitter = new User(Integer.toString(parsedResults.settings[i].member.id), StringEscapeUtils.unescapeXml(parsedResults.settings[i].member.name), parsedResults.settings[i].member.url);
			TuneDetailsBasic tuneDetails = new TuneDetailsBasic(Integer.toString(parsedResults.settings[i].tune.id), parsedResults.settings[i].tune.name, parsedResults.settings[i].tune.url );		
			// Instantiate a NewTunesResult object & populate it
			NewTunesResult currentResult = new NewTunesResult(details, submitter, tuneDetails);
			
			// Add the TuneSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}
	
	public ArrayList<NewTunesResult> searchTunes(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Launch a search for a list of matching tunes and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitRequest("tunes",searchTerms, resultsPerPage,pageNumber);
		
		// Parse the returned JSON into a wrapper class to allow access to all elements
		NewTunesParser jsonParser = new NewTunesParser();
		TunesSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
		
		// This will hold each individual search result
		ArrayList<NewTunesResult> resultSet = new ArrayList <NewTunesResult>();
			
		// Loop as many times as the count of tunes in the result set:
		for(int i = 0; i < (parsedResults.tunes.length)-1; i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			SettingDetails details = new SettingDetails(parsedResults.tunes[i].id, StringEscapeUtils.unescapeXml(parsedResults.tunes[i].name), parsedResults.tunes[i].type, parsedResults.tunes[i].url, parsedResults.tunes[i].date);
			User submitter = new User(Integer.toString(parsedResults.tunes[i].member.id), StringEscapeUtils.unescapeXml(parsedResults.tunes[i].member.name), parsedResults.tunes[i].member.url);
						
			// Instantiate a NewTunesResult object & populate it
			NewTunesResult currentResult = new NewTunesResult(details, submitter);
			// Add the TuneSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}

		// Return the set of results that has been collected
		return resultSet;
		}

	public ArrayList<DiscussionsSearchResult> searchDiscussions(String searchTerms, int resultsPerPage) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitListRequest("discussions", searchTerms, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		DiscussionsSearchParser jsonParser = new DiscussionsSearchParser();
		DiscussionsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
			
		// This will hold each individual search result entry
		ArrayList<DiscussionsSearchResult> resultSet = new ArrayList <DiscussionsSearchResult>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.discussions.length)-1; i++)
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
	
	public ArrayList<DiscussionsSearchResult> searchDiscussions(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitRequest("discussions",searchTerms, resultsPerPage,pageNumber);
		
		// Parse the returned JSON into a wrapper class to allow access to all elements
		DiscussionsSearchParser jsonParser = new DiscussionsSearchParser();
		DiscussionsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
		
		// This will hold each individual search result
		ArrayList<DiscussionsSearchResult> resultSet = new ArrayList <DiscussionsSearchResult>();
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.discussions.length)-1; i++)
			{
			// Extract the required elements from each individual search result in the JSON response
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
	
	public ArrayList<EventsSearchResult> searchEvents(String searchTerms, int resultsPerPage) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitListRequest("events", searchTerms, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		EventsSearchParser jsonParser = new EventsSearchParser();
		EventsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
			
		// This will hold each individual search result entry
		ArrayList<EventsSearchResult> resultSet = new ArrayList <EventsSearchResult>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.events.length)-1; i++)
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
	
	public ArrayList<EventsSearchResult> searchEvents(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitRequest("events",searchTerms, resultsPerPage,pageNumber);
		
		// Parse the returned JSON into a wrapper class to allow access to all elements
		EventsSearchParser jsonParser = new EventsSearchParser();
		EventsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
		
		// This will hold each individual search result
		ArrayList<EventsSearchResult> resultSet = new ArrayList <EventsSearchResult>();
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.events.length)-1; i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			EventDetails details = new EventDetails(parsedResults.events[i].id, parsedResults.events[i].name, parsedResults.events[i].url, parsedResults.events[i].date);
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
	
	public ArrayList<RecordingsSearchResult> searchRecordings(String searchTerms, int resultsPerPage) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitListRequest("recordings", searchTerms, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		RecordingsSearchParser jsonParser = new RecordingsSearchParser();
		RecordingsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
			
		// This will hold each individual search result entry
		ArrayList<RecordingsSearchResult> resultSet = new ArrayList <RecordingsSearchResult>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.recordings.length)-1; i++)
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
	
	public ArrayList<RecordingsSearchResult> searchRecordings(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitRequest("recordings",searchTerms, resultsPerPage,pageNumber);
		
		// Parse the returned JSON into a wrapper class to allow access to all elements
		RecordingsSearchParser jsonParser = new RecordingsSearchParser();
		RecordingsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
		
		// This will hold each individual search result
		ArrayList<RecordingsSearchResult> resultSet = new ArrayList <RecordingsSearchResult>();
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.recordings.length)-1; i++)
			{
			// Extract the required elements from each individual search result in the JSON response
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
	
	public ArrayList<SessionsSearchResult> searchSessions(String searchTerms, int resultsPerPage) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitListRequest("sessions", searchTerms, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		SessionsSearchParser jsonParser = new SessionsSearchParser();
		SessionsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
			
		// This will hold each individual search result entry
		ArrayList<SessionsSearchResult> resultSet = new ArrayList <SessionsSearchResult>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.sessions.length)-1; i++)
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
		
		// Return the set of results that has been collected
		return resultSet;
		}
	
	public ArrayList<SessionsSearchResult> searchSessions(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitRequest("tunes",searchTerms, resultsPerPage,pageNumber);
		
		// Parse the returned JSON into a wrapper class to allow access to all elements
		SessionsSearchParser jsonParser = new SessionsSearchParser();
		SessionsSearchResultWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
		
		// This will hold each individual search result
		ArrayList<SessionsSearchResult> resultSet = new ArrayList <SessionsSearchResult>();
			
		// Loop as many times as the count of recordings in the result set:
		for(int i = 0; i < (parsedResults.sessions.length)-1; i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			SessionDetails details = new SessionDetails(parsedResults.sessions[i].id, parsedResults.sessions[i].url, parsedResults.sessions[i].date);
			Coordinates coordinates = new Coordinates(parsedResults.sessions[i].latitude, parsedResults.sessions[i].longitude);
			User user = new User(Integer.toString(parsedResults.sessions[i].member.id),StringEscapeUtils.unescapeXml(parsedResults.sessions[i].member.name),parsedResults.sessions[i].member.url);
			Venue venue = new Venue(Integer.toString(parsedResults.sessions[i].venue.id), parsedResults.sessions[i].venue.name, parsedResults.sessions[i].venue.telephone, parsedResults.sessions[i].venue.email, parsedResults.sessions[i].venue.web );
			Town town = new Town(Integer.toString(parsedResults.sessions[i].town.id), parsedResults.sessions[i].town.name);
			Area area = new Area(Integer.toString(parsedResults.sessions[i].area.id), parsedResults.sessions[i].area.name);
			Country country = new Country(Integer.toString(parsedResults.sessions[i].country.id), parsedResults.sessions[i].country.name);		
			
			// Instantiate a SessionsSearchResult object & populate it
			SessionsSearchResult currentResult = new SessionsSearchResult(details, coordinates, user, venue, town, area, country);			
			
			// Add the SessionsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
	
		// Return the set of results that has been collected
		return resultSet;
		}
	
	public int getPageCount() throws IllegalStateException
		{
		if (pageCount == 0)
			{
			throw new IllegalStateException("Page counter has not been initialised");
			}
		else 
			return pageCount;
		}
	
	}
