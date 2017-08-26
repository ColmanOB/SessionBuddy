package main;

import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;

import json_object_wrappers.Area;
import json_object_wrappers.Coordinates;
import json_object_wrappers.Country;
import json_object_wrappers.SessionDetails;
import json_object_wrappers.SessionsByLocationResult;
import json_object_wrappers.Town;
import json_object_wrappers.User;
import json_object_wrappers.Venue;
import response_parsers.SessionsByLocationParser;
import result_set_wrappers.SessionsByLocationWrapper;


// TODO: Create a method to search for events by location
// TODO: Create a method to search for events by location, including the capability to specify a page number
// TODO: Create a helper method to handle the parsing of the event by location search results

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
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @param resultsPerPage
	 * @return
	 * @throws IllegalArgumentException
	 */
	public ArrayList<SessionsByLocationResult> searchSessionsByLocation(String latitude, String longitude, String radius, int resultsPerPage) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
	
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitLocationRequest("sessions", latitude, longitude, radius, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		SessionsByLocationParser jsonParser = new SessionsByLocationParser();
		SessionsByLocationWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
			
		// This will hold each individual search result entry
		ArrayList<SessionsByLocationResult> resultSet = new ArrayList <SessionsByLocationResult>();
		
		resultSet = populateSessionsByLocationResult(parsedResults);
		
		return resultSet;
		}
	
	
	/**
	 * Alternative version of searchSessionsByLocation(), allowing a page number to be specified within a paginated JSON response.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @param resultsPerPage
	 * @return
	 * @throws IllegalArgumentException
	 */
	public ArrayList<SessionsByLocationResult> searchSessionsByLocation(String latitude, String longitude, String radius, int resultsPerPage, int pageNumber) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
	
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitLocationRequest("sessions", latitude, longitude, radius, resultsPerPage, pageNumber);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		SessionsByLocationParser jsonParser = new SessionsByLocationParser();
		SessionsByLocationWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
			
		// This will hold each individual search result entry
		ArrayList<SessionsByLocationResult> resultSet = new ArrayList <SessionsByLocationResult>();
		
		resultSet = populateSessionsByLocationResult(parsedResults);
		
		return resultSet;
		}
	
	
	/**
	 * Helper method to gather and parse the response to a location-based search for sessions
	 * 
	 * @param parsedResults
	 * @return
	 */
	ArrayList<SessionsByLocationResult> populateSessionsByLocationResult(SessionsByLocationWrapper parsedResults)	
		{	
		// This will hold each individual search result entry
		ArrayList<SessionsByLocationResult> resultSet = new ArrayList <SessionsByLocationResult>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of sessions in the result set:
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
			
			
			// Instantiate a SessionsByLocationResult object & populate it
			SessionsByLocationResult currentResult = new SessionsByLocationResult(details, coordinates, user, venue, town, area, country);
			
			// Add the SessionsSearchResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}
	
	
	/**
	 * @return
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
}
