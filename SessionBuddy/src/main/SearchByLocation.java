package main;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;

import json_object_wrappers.Area;
import json_object_wrappers.Coordinates;
import json_object_wrappers.Country;
import json_object_wrappers.EventDetails;
import json_object_wrappers.EventSchedule;
import json_object_wrappers.EventsByLocationResult;
import json_object_wrappers.SessionDetails;
import json_object_wrappers.SessionsByLocationResult;
import json_object_wrappers.Town;
import json_object_wrappers.User;
import json_object_wrappers.Venue;
import result_set_wrappers.EventsByLocationWrapper;
import result_set_wrappers.SessionsByLocationWrapper;
import utils.HttpRequestor;
import utils.JsonResponseParser;


/**
 * Queries the API at thesession.org with coordinates and a radius, and parses the response into an easily usable structure. 
 * To use this feature, first create a new SearchByLocation object, then call one of its methods to perform the actual search.
 * 
 * @author Colman
 * @since 2017-08-26
 */
public class SearchByLocation 
	{
	private int pageCount = 0;
	
	/**
	 * Queries the API for a list of sessions within a specified radius of a particular latitude and longitude
	 * 
	 * @param latitude a latitude value between -90 to 90
	 * @param longitude a longitude value between -180 to 180
	 * @param radius defines a radius (in kilometers) around the coordinates to be included in the search
	 * @param resultsPerPage the number of results you want returned per page in the JSON response
	 * @return an ArrayList of SessionsByLocationResult objects
	 * @throws RuntimeException 
	 * @throws MalformedURLException 
	 */
	public ArrayList<SessionsByLocationResult> searchSessionsByLocation(String latitude, String longitude, String radius, int resultsPerPage) throws MalformedURLException, RuntimeException
		{
		try
			{
			// Validate the user input
			validateResultsPerPageCount(resultsPerPage);
			validateCoordinates(latitude, longitude, radius);
			}
		
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
	
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitLocationRequest("sessions", latitude, longitude, radius, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		JsonResponseParser jsonParser = new JsonResponseParser(apiQueryResults);
		SessionsByLocationWrapper parsedResults = jsonParser.parseLocationSession();
			
		// This will hold each individual search result entry
		ArrayList<SessionsByLocationResult> resultSet = new ArrayList <SessionsByLocationResult>();
		
		resultSet = populateSessionsByLocationResult(parsedResults);
		
		return resultSet;
		}
	
	
	/** 
	 * Alternative version of searchSessionsByLocation(), allowing a page number to be specified within a paginated JSON response.
	 * 
	 * @param latitude a latitude value between -90 to 90
	 * @param longitude a longitude value between -180 to 180
	 * @param radius defines a radius (in kilometers) around the coordinates to be included in the search
	 * @param resultsPerPage the number of results you want returned per page in the JSON response
	 * @return an ArrayList of SessionsByLocationResult objects
	 * @throws RuntimeException 
	 * @throws MalformedURLException 
	 */
	public ArrayList<SessionsByLocationResult> searchSessionsByLocation(String latitude, String longitude, String radius, int resultsPerPage, int pageNumber) throws MalformedURLException, RuntimeException
		{
		try
			{
			// Validate the user input
			validateResultsPerPageCount(resultsPerPage);
			validateCoordinates(latitude, longitude, radius);
			}
		
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitLocationRequest("sessions", latitude, longitude, radius, resultsPerPage, pageNumber);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		JsonResponseParser jsonParser = new JsonResponseParser(apiQueryResults);
		SessionsByLocationWrapper parsedResults = jsonParser.parseLocationSession();
			
		// This will hold each individual search result entry
		ArrayList<SessionsByLocationResult> resultSet = new ArrayList <SessionsByLocationResult>();
		
		resultSet = populateSessionsByLocationResult(parsedResults);
		
		return resultSet;
		}
	
	
	/**
	 * Queries the API for a list of sessions within a specified radius of a particular latitude and longitude
	 * 
	 * @param latitude a latitude value between -90 to 90
	 * @param longitude a longitude value between -180 to 180
	 * @param radius defines a radius (in kilometers) around the coordinates to be included in the search
	 * @param resultsPerPage the number of results you want returned per page in the JSON response
	 * @return an ArrayList of EventsByLocationResult objects
	 * @throws RuntimeException 
	 * @throws MalformedURLException 
	 */
	public ArrayList<EventsByLocationResult> searchEventsByLocation(String latitude, String longitude, String radius, int resultsPerPage) throws MalformedURLException, RuntimeException
		{
		try
			{
			// Validate the user input
			validateResultsPerPageCount(resultsPerPage);
			validateCoordinates(latitude, longitude, radius);
			}
	
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
	
		// Launch a search for a list of matching events and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitLocationRequest("events", latitude, longitude, radius, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		JsonResponseParser jsonParser = new JsonResponseParser(apiQueryResults);
		EventsByLocationWrapper parsedResults = jsonParser.parseLocationEvent();
			
		// This will hold each individual search result entry
		ArrayList<EventsByLocationResult> resultSet = new ArrayList <EventsByLocationResult>();
		
		resultSet = populateEventsByLocationResult(parsedResults);
		
		return resultSet;
		}
	
	
	/**
	 * Alternative version of searchEventsByLocation, allowing an individual page to be specified within a paginated JSON response
	 * 
	 * @param latitude a latitude value between -90 to 90
	 * @param longitude a longitude value between -180 to 180
	 * @param radius defines a radius (in kilometers) around the coordinates to be included in the search
	 * @param resultsPerPage the number of results you want returned per page in the JSON response
	 * @param pageNumber specifies an individual page within a paginated JSON response
	 * @return an ArrayList of EventsByLocationResult objects
	 * @throws RuntimeException 
	 * @throws MalformedURLException 
	 */
	public ArrayList<EventsByLocationResult> searchEventsByLocation(String latitude, String longitude, String radius, int resultsPerPage, int pageNumber) throws MalformedURLException, RuntimeException
		{
		try
			{
			// Validate the user input
			validateResultsPerPageCount(resultsPerPage);
			validateCoordinates(latitude, longitude, radius);
			}
	
		catch (IllegalArgumentException e)
			{
			throw new IllegalArgumentException(e.getMessage());
			}
	
		// Launch a search for a list of matching events and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitLocationRequest("events", latitude, longitude, radius, resultsPerPage, pageNumber);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		JsonResponseParser jsonParser = new JsonResponseParser(apiQueryResults);
		EventsByLocationWrapper parsedResults = jsonParser.parseLocationEvent();
			
		// This will hold each individual search result entry
		ArrayList<EventsByLocationResult> resultSet = new ArrayList <EventsByLocationResult>();
		
		resultSet = populateEventsByLocationResult(parsedResults);
		
		return resultSet;
		}
	
	
	/**
	 * Helper method to gather and parse the response to a location-based search for sessions
	 * 
	 * @param parsedResults a SessionsByLocationWrapper object that has already been created and populated
	 * @return an ArrayList of SessionsByLoctionResult objects
	 */
	private ArrayList<SessionsByLocationResult> populateSessionsByLocationResult(SessionsByLocationWrapper parsedResults)	
		{	
		// This will hold each individual search result entry
		ArrayList<SessionsByLocationResult> resultSet = new ArrayList <SessionsByLocationResult>();
		
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
					
			// Instantiate a SessionsByLocationResult object & populate it
			SessionsByLocationResult currentResult = new SessionsByLocationResult(details, coordinates, user, venue, town, area, country);
			
			// Add the SessionsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}

	
	/**
	 * Helper method to gather and parse the response to a location-based search for sessions
	 * 
	 * @param parsedResults an EventsByLocationWrapper containing response data form the API
	 * @return an ArrayList of EventsByLocationResult objects
	 */
	private ArrayList<EventsByLocationResult> populateEventsByLocationResult(EventsByLocationWrapper parsedResults)	
		{	
		// This will hold each individual search result entry
		ArrayList<EventsByLocationResult> resultSet = new ArrayList <EventsByLocationResult>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of events in the result set:
		for(int i = 0; i < (parsedResults.events.length); i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response
			EventDetails details = new EventDetails(parsedResults.events[i].id, StringEscapeUtils.unescapeXml(parsedResults.events[i].name), parsedResults.events[i].url, parsedResults.events[i].date);
			EventSchedule schedule = new EventSchedule(parsedResults.events[i].dtstart, parsedResults.events[i].dtend);
			Coordinates coordinates = new Coordinates(parsedResults.events[i].latitude, parsedResults.events[i].longitude);
			User user = new User(Integer.toString(parsedResults.events[i].member.id),StringEscapeUtils.unescapeXml(parsedResults.events[i].member.name),parsedResults.events[i].member.url);
			Venue venue = new Venue(Integer.toString(parsedResults.events[i].venue.id), StringEscapeUtils.unescapeXml(parsedResults.events[i].venue.name), parsedResults.events[i].venue.telephone, parsedResults.events[i].venue.email, parsedResults.events[i].venue.web );
			Town town = new Town(Integer.toString(parsedResults.events[i].town.id), StringEscapeUtils.unescapeXml(parsedResults.events[i].town.name));
			Area area = new Area(Integer.toString(parsedResults.events[i].area.id), StringEscapeUtils.unescapeXml(parsedResults.events[i].area.name));
			Country country = new Country(Integer.toString(parsedResults.events[i].country.id), StringEscapeUtils.unescapeXml(parsedResults.events[i].country.name));
			
			// Instantiate a EventsByLocationResult object & populate it
			EventsByLocationResult currentResult = new EventsByLocationResult(details, user, schedule, coordinates, venue, town, area, country);
			
			// Add the EventsByLocationResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}
	
	
	/**
	 * Helper method to keep track of the number of pages in the JSON response form the API
	 * 
	 * @return the number of pages in the JSON reponse
	 * @throws IllegalStateException when the pageCount variable has not been initialised
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
	 * Helper method to validate that the user has specified a value between 1-50 for the results per page
	 * 
	 * @param resultsPerPage
	 * @return
	 * @throws IllegalArgumentException
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
	
	
	/**
	 * Helper method to validate the latitude, longitude and radius provided by the user
	 * 
	 * @param latitude the latitude of the point at the centre of the search
	 * @param longitude the longitude of the point at the centre of the search
	 * @param radius a radius around the coordinates, to be included in the search
	 * @return
	 * @throws IllegalArgumentException
	 */
	private boolean validateCoordinates(String latitude, String longitude, String radius) throws IllegalArgumentException
		{	
		// Specify a set of rules defining valid coordinates
		final float MIN_LATITUDE = Float.valueOf("-90.0000");
		final float MAX_LATITUDE = Float.valueOf("90.0000");
		final float MIN_LONGITUDE = Float.valueOf("-180.0000");
		final float MAX_LONGITUDE = Float.valueOf("180.0000");
		
		// I chose to allow a radius of up to 1000Km - assuming nobody is likely to want to search within a larger radius
		final float MIN_RADIUS = Float.valueOf("1.0000");
		final float MAX_RADIUS = Float.valueOf("1000.0000");
		
		// First check if an invalid latitude value was provided
		if (Float.valueOf(latitude) < MIN_LATITUDE || Float.valueOf(latitude) > MAX_LATITUDE) 
			{
			throw new IllegalArgumentException("Invalid latitude value - must be between -90 and 90");
			}
		
		// Next, check if an invalid longitude value was provided
		if (Float.valueOf(longitude) < MIN_LONGITUDE || Float.valueOf(longitude) > MAX_LONGITUDE) 
			{
			throw new IllegalArgumentException("Invalid longitude value - must be between -180 and 180");
			}
		
		// Finally, check if a radius of less than zero or more than 1000 Km was provided
		if (Float.valueOf(radius) < MIN_RADIUS || Float.valueOf(radius) > MAX_RADIUS) 
			{
			throw new IllegalArgumentException("Invalid radius value - must be between 1 and 1000");
			}
		
		// Otherwise conclude that the input coordinates are valid
		else return true;	
		}
	
}
