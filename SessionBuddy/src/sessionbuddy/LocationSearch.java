package sessionbuddy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import sessionbuddy.utils.HttpRequestor;
import sessionbuddy.utils.JsonResponseParser;
import sessionbuddy.utils.StringCleaner;
import sessionbuddy.utils.UrlBuilder;
import sessionbuddy.wrappers.granularobjects.Area;
import sessionbuddy.wrappers.granularobjects.Coordinates;
import sessionbuddy.wrappers.granularobjects.Country;
import sessionbuddy.wrappers.granularobjects.EventDetails;
import sessionbuddy.wrappers.granularobjects.EventSchedule;
import sessionbuddy.wrappers.granularobjects.SessionDetails;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;
import sessionbuddy.wrappers.jsonresponse.LocationSearchWrapperEvents;
import sessionbuddy.wrappers.jsonresponse.LocationSearchWrapperSessions;
import sessionbuddy.wrappers.resultsets.LocationResultEvents;
import sessionbuddy.wrappers.resultsets.LocationResultSessions;


/**
 * Queries the API at thesession.org with coordinates and a radius, and parses the response into an easily usable structure. 
 * To use this feature, first create a new LocationSearch object, then call one of its methods to perform the actual search.
 * 
 * @author Colman
 * @since 2017-03-04
 */
public class LocationSearch extends Search
	{
	/**
	 * Queries the API for a list of sessions within a specified radius of a particular latitude and longitude
	 * 
	 * @param latitude a latitude value between -90 to 90
	 * @param longitude a longitude value between -180 to 180
	 * @param radius defines a radius (in kilometres) around the coordinates to be included in the search
	 * @param resultsPerPage the number of results you want returned per page in the JSON response
	 * @return an ArrayList of SessionsByLocationResult objects
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-03-04
	 */
	public ArrayList<LocationResultSessions> searchSessionsByLocation(String latitude, String longitude, String radius, int resultsPerPage) throws IllegalArgumentException, IOException, IllegalStateException, URISyntaxException
		{
		try
			{
			// Validate the user input
			validateResultsPerPageCount(resultsPerPage);
			validateCoordinates(latitude, longitude, radius);
		
			// Assemble the query parameters for the URL
			List<NameValuePair> queryParams = new ArrayList<>();
			queryParams.add(new BasicNameValuePair("latlon", latitude + "," + longitude));
			queryParams.add(new BasicNameValuePair("radius", radius));
		
			// Build the URL with all necessary parameters to perform a search via thesession.org API, specifying the page number
			URL requestURL = UrlBuilder.buildURL("sessions", "nearby", queryParams, resultsPerPage);
			
			// Call the API and capture the response
			String response = HttpRequestor.submitRequest(requestURL);
				
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			LocationSearchWrapperSessions parsedResults = jsonParser.parseResponse(LocationSearchWrapperSessions.class);
				
			// This will hold each individual search result entry
			ArrayList<LocationResultSessions> resultSet = new ArrayList <LocationResultSessions>();
			
			resultSet = populateSessionsByLocationResult(parsedResults);
			
			return resultSet;
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
			{
			throw ex;
			}
		}
	
	/** 
	 * Alternative version of searchSessionsByLocation(), allowing a page number to be specified within a paginated JSON response.
	 * 
	 * @param latitude a latitude value between -90 to 90
	 * @param longitude a longitude value between -180 to 180
	 * @param radius defines a radius (in kilometers) around the coordinates to be included in the search
	 * @param resultsPerPage the number of results you want returned per page in the JSON response
	 * @param pageNumber a specific page within a multi-page JSON response
	 * @return an ArrayList of SessionsByLocationResult objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-03-04
	 */
	public ArrayList<LocationResultSessions> searchSessionsByLocation(String latitude, String longitude, String radius, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, IllegalStateException, URISyntaxException
		{
		try
			{
			// Validate the user input
			validateResultsPerPageCount(resultsPerPage);
			validateCoordinates(latitude, longitude, radius);

			// Assemble the query parameters for the URL
			List<NameValuePair> queryParams = new ArrayList<>();
			queryParams.add(new BasicNameValuePair("latlon", latitude + "," + longitude));
			queryParams.add(new BasicNameValuePair("radius", radius));
		
			// Build the URL with all necessary parameters to perform a search via thesession.org API, specifying the page number
			URL requestURL = UrlBuilder.buildURL("sessions", "nearby", queryParams, resultsPerPage, pageNumber);
			
			// Call the API and capture the response
			String response = HttpRequestor.submitRequest(requestURL);
			
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			LocationSearchWrapperSessions parsedResults = jsonParser.parseResponse(LocationSearchWrapperSessions.class);
				
			// This will hold each individual search result entry
			ArrayList<LocationResultSessions> resultSet = new ArrayList <LocationResultSessions>();
			
			resultSet = populateSessionsByLocationResult(parsedResults);
			
			return resultSet;
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
			{
			throw ex;
			}
		}
	
	
	/**
	 * Queries the API for a list of events within a specified radius of a particular latitude and longitude
	 * 
	 * @param latitude a latitude value between -90 to 90
	 * @param longitude a longitude value between -180 to 180
	 * @param radius defines a radius (in kilometers) around the coordinates to be included in the search
	 * @param resultsPerPage the number of results you want returned per page in the JSON response
	 * @return an ArrayList of EventsByLocationResult objects
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-03-05
	 */
	public ArrayList<LocationResultEvents> searchEventsByLocation(String latitude, String longitude, String radius, int resultsPerPage) throws IllegalArgumentException, IOException, IllegalStateException, URISyntaxException
		{
		try
			{
			// Validate the user input
			validateResultsPerPageCount(resultsPerPage);
			validateCoordinates(latitude, longitude, radius);
	
			// Assemble the query parameters for the URL
			List<NameValuePair> queryParams = new ArrayList<>();
			queryParams.add(new BasicNameValuePair("latlon", latitude + "," + longitude));
			queryParams.add(new BasicNameValuePair("radius", radius));
		
			// Build the URL with all necessary parameters to perform a search via thesession.org API, specifying the page number
			URL requestURL = UrlBuilder.buildURL("events", "nearby", queryParams, resultsPerPage);
			
			// Call the API and capture the response
			String response = HttpRequestor.submitRequest(requestURL);
				
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			LocationSearchWrapperEvents parsedResults = jsonParser.parseResponse(LocationSearchWrapperEvents.class);
				
			// This will hold each individual search result entry
			ArrayList<LocationResultEvents> resultSet = new ArrayList <LocationResultEvents>();
			
			resultSet = populateEventsByLocationResult(parsedResults);
			
			return resultSet;
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
			{
			throw ex;
			}
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
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-03-04
	 */
	public ArrayList<LocationResultEvents> searchEventsByLocation(String latitude, String longitude, String radius, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, IllegalStateException, URISyntaxException
		{
		try
			{
			// Validate the user input
			validateResultsPerPageCount(resultsPerPage);
			validateCoordinates(latitude, longitude, radius);
	
			// Assemble the query parameters for the URL
			List<NameValuePair> queryParams = new ArrayList<>();
			queryParams.add(new BasicNameValuePair("latlon", latitude + "," + longitude));
			queryParams.add(new BasicNameValuePair("radius", radius));
		
			// Build the URL with all necessary parameters to perform a search via thesession.org API, specifying the page number
			URL requestURL = UrlBuilder.buildURL("events", "nearby", queryParams, resultsPerPage, pageNumber);
			
			// Call the API and capture the response
			String response = HttpRequestor.submitRequest(requestURL);
			
			// Parse the returned JSON into a wrapper class to allow access to all elements
			JsonResponseParser jsonParser = new JsonResponseParser(response);
			LocationSearchWrapperEvents parsedResults = jsonParser.parseResponse(LocationSearchWrapperEvents.class);
				
			// This will hold each individual search result entry
			ArrayList<LocationResultEvents> resultSet = new ArrayList <LocationResultEvents>();
			
			resultSet = populateEventsByLocationResult(parsedResults);
			
			return resultSet;
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
			{
			throw ex;
			}
		}
	
	
	/**
	 * Helper method to gather and parse the response to a location-based search for sessions
	 * 
	 * @param parsedResults a SessionsByLocationWrapper object that has already been created and populated
	 * @return an ArrayList of SessionsByLoctionResult objects
	 */
	private ArrayList<LocationResultSessions> populateSessionsByLocationResult(LocationSearchWrapperSessions parsedResults)	
		{	
		// This will hold each individual search result entry
		ArrayList<LocationResultSessions> resultSet = new ArrayList <LocationResultSessions>();
		
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
					
			// Instantiate a SessionsByLocationResult object & populate it
			LocationResultSessions currentResult = new LocationResultSessions(details, coordinates, user, venue, town, area, country);
			
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
	private ArrayList<LocationResultEvents> populateEventsByLocationResult(LocationSearchWrapperEvents parsedResults)	
		{	
		// This will hold each individual search result entry
		ArrayList<LocationResultEvents> resultSet = new ArrayList <LocationResultEvents>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of events in the result set:
		for(int i = 0; i < (parsedResults.events.length); i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
			EventDetails details = new EventDetails(parsedResults.events[i].id, StringCleaner.cleanString(parsedResults.events[i].name), parsedResults.events[i].url, parsedResults.events[i].date);
			EventSchedule schedule = new EventSchedule(parsedResults.events[i].dtstart, parsedResults.events[i].dtend);
			Coordinates coordinates = new Coordinates(parsedResults.events[i].latitude, parsedResults.events[i].longitude);
			User user = new User(Integer.toString(parsedResults.events[i].member.id),StringCleaner.cleanString(parsedResults.events[i].member.name),parsedResults.events[i].member.url);
			Venue venue = new Venue(Integer.toString(parsedResults.events[i].venue.id), StringCleaner.cleanString(parsedResults.events[i].venue.name), parsedResults.events[i].venue.telephone, parsedResults.events[i].venue.email, parsedResults.events[i].venue.web );
			Town town = new Town(Integer.toString(parsedResults.events[i].town.id), StringCleaner.cleanString(parsedResults.events[i].town.name));
			Area area = new Area(Integer.toString(parsedResults.events[i].area.id), StringCleaner.cleanString(parsedResults.events[i].area.name));
			Country country = new Country(Integer.toString(parsedResults.events[i].country.id), StringCleaner.cleanString(parsedResults.events[i].country.name));
			
			// Instantiate a EventsByLocationResult object & populate it
			LocationResultEvents currentResult = new LocationResultEvents(details, user, schedule, coordinates, venue, town, area, country);
			
			// Add the EventsByLocationResult object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the set of results that has been collected
		return resultSet;
		}
			
	
	/**
	 * Helper method to validate the latitude, longitude and radius provided by the user
	 * 
	 * @param latitude the latitude of the point at the centre of the search
	 * @param longitude the longitude of the point at the centre of the search
	 * @param radius a radius around the coordinates, to be included in the search
	 * @return true if the provided coordinates are a valid latitude and longitude, and the radius is less than 1000Km
	 * @throws IllegalArgumentException if an invalid number is provided for either the latitude, longitude or radius
	 */
	private static boolean validateCoordinates(String latitude, String longitude, String radius) throws IllegalArgumentException
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
