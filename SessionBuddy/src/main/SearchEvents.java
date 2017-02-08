package main;

import java.util.ArrayList;
import org.apache.commons.lang3.StringEscapeUtils;

import json_object_wrappers.Area;
import json_object_wrappers.Coordinates;
import json_object_wrappers.Country;
import json_object_wrappers.EventDetails;
import json_object_wrappers.EventSchedule;
import json_object_wrappers.EventsSearchResult;
import json_object_wrappers.Town;
import json_object_wrappers.User;
import json_object_wrappers.Venue;
import response_parsers.EventsSearchParser;
import result_set_wrappers.EventsSearchResultWrapper;

/**
 * Makes a call to the API at thesession.org to get a list of events matching a given set of search terms.
 * The number of results-per-page in the response can be specified, up to a maximum of 50.
 * An ArrayList of EventsSearchResults is returned in response to the executeSearch methods.
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class SearchEvents implements KeywordSearch <ArrayList<EventsSearchResult>>
	{
	private int pageCount = 0;
	
	/**
	 * Launches a search for sessions matching a given set of search terms and returns the results as an ArrayList of RecordingsSearchReult objects
	 * 
	 * @param searchTerms Search terms are matched against recordings in thesession.org database
	 * @param resultsPerPage It is possible to specify up to 50 recording results per page in the API response
	 * @return An ArrayList of EventsSearchResult objects
	 * @throws IllegalArgumentException The API at thesession.org can return a maximum of 50 results per page
	 */
	public ArrayList<EventsSearchResult> executeSearch(String searchTerms, int resultsPerPage) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Launch a search for a list of matching recordings and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitSearchRequest("events", searchTerms, resultsPerPage);
			
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
	
	// Alternative version of executeSearch() that accepts an additional argument to specify the page number in the result JSON
	/**
	 * Launches a search for recordings matching a given set of search terms and returns the results as an ArrayList of TunesSearchReult objects
	 * 
	 * @param searchTerms Search terms are matched against recordings in thesession.org database
	 * @param resultsPerPage It is possible to specify up to 50 recording results per page in the API response
	 * @param pageNumber Specifies an individual page number within the current set of results
	 * @return An ArrayList of EventsSearchResult objects
	 * @throws IllegalArgumentException The API at thesession.org can return a maximum of 50 results per page
	 */
	public ArrayList<EventsSearchResult> executeSearch(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException
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
	
	
	/**
	 * Retrieves the number of pages of results returned from the API.
	 * This can be useful for looping through multiple pages of results.
	 * 
	 * @return The number of pages of results in the response
	 * @throws IllegalStateException Thrown if the pageCount variable has not been set
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
