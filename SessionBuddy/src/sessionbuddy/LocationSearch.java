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
import sessionbuddy.utils.RequestType;
import sessionbuddy.utils.StringCleaner;
import sessionbuddy.utils.URLComposer;
import sessionbuddy.wrappers.granularobjects.Area;
import sessionbuddy.wrappers.granularobjects.Coordinates;
import sessionbuddy.wrappers.granularobjects.Country;
import sessionbuddy.wrappers.granularobjects.EventDetails;
import sessionbuddy.wrappers.granularobjects.EventSchedule;
import sessionbuddy.wrappers.granularobjects.SessionDetails;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.TripDetails;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperTrips;
import sessionbuddy.wrappers.jsonresponse.LocationSearchWrapperEvents;
import sessionbuddy.wrappers.jsonresponse.LocationSearchWrapperSessions;
import sessionbuddy.wrappers.resultsets.LocationResultEvents;
import sessionbuddy.wrappers.resultsets.LocationResultSessions;
import sessionbuddy.wrappers.resultsets.SearchResultTrips;

/**
 * Retrieves a list of sessions or events within a given radius (in Km) of a set of coordinates.
 * 
 * @author Colman
 * @since 2018-12-11
 */
public class LocationSearch extends Search
{
    /**
     * The number of results that should be returned per page
     */
    int resultsPerPage = 0;

    /**
     * Specifies a page number in the JSON reponse from the API
     */
    int pageNumber = 0;

    /**
     * Specifies a particular latitude coordinate
     */
    String latitude = null;

    /**
     * Specifies a particular longitude coordinate
     */
    String longitude = null;

    /**
     * Specifies a radius in kilometers around the latitude & longitude
     */
    String radius = null;

    /**
     * Constructor where pagination is not required
     *
     * @param latitude Specifies a particular latitude coordinate
     * @param longitude Specifies a particular longitude coordinate
     * @param radius Specifies a radius in Kilometres around a set of coordinates
     * @param resultsPerPage The number of results per page in the API response
     */
    public LocationSearch(String latitude, String longitude, String radius, int resultsPerPage)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.resultsPerPage = resultsPerPage;
    }

    /**
     * Constructor for cases where you need to specify a page in the API response
     * 
     * @param latitude Specifies a particular latitude coordinate
     * @param longitude Specifies a particular longitude coordinate
     * @param radius Specifies a radius in Kilometres around a set of coordinates
     * @param resultsPerPage The number of results per page in the API response
     * @param pageNumber Specifies a particular page number within the JSON response
     */
    public LocationSearch(String latitude, String longitude, String radius, int resultsPerPage, int pageNumber)
    {
        this(latitude, longitude, radius, resultsPerPage);
        this.pageNumber = pageNumber;
    }

    /**
     * Queries the API for a list of sessions within a specified radius of a set of coordinates
     * 
     * @return an ArrayList of LocationResultSessions objects
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if UrlBuilder throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-03-11
     */
    public ArrayList<LocationResultSessions> searchSessions() throws IllegalArgumentException, IOException, IllegalStateException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            validateCoordinates(latitude, longitude, radius);
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("sessions"));
            // Parse the JSON response
            LocationSearchWrapperSessions parsedResults = JsonParser.parseResponse(response, LocationSearchWrapperSessions.class);
            // Return the data retrieved from the API
            return populateSessionsByLocationResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * Queries the API for a list of events within a specified radius of a set of coordinates
     * 
     * @return an ArrayList of LocationResultEvents objects
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public ArrayList<LocationResultEvents> searchEvents() throws IllegalArgumentException, IOException, IllegalStateException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            validateCoordinates(latitude, longitude, radius);
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("events"));
            // Parse the JSON response
            LocationSearchWrapperEvents parsedResults = JsonParser.parseResponse(response, LocationSearchWrapperEvents.class);
            // Return the data retrieved from the API
            return populateEventsByLocationResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    /**
     * Queries the API for a list of trips within a specified radius of a set of coordinates
     * 
     * @return an ArrayList of LocationResultTrips objects
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-12-11
     */
    public ArrayList<SearchResultTrips> searchTrips() throws IllegalArgumentException, IOException, IllegalStateException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            validateCoordinates(latitude, longitude, radius);
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("trips"));
            // Parse the JSON response
            LatestWrapperTrips parsedResults = JsonParser.parseResponse(response, LatestWrapperTrips.class);
            // Return the data retrieved from the API
            return populateTripsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * Helper method to parse the response to a location-based search for sessions
     * 
     * @param parsedResults a LocationSearchWrapperSessions object that has already been created and populated
     * @return an ArrayList of LocationResultSession objects
     */
    private ArrayList<LocationResultSessions> populateSessionsByLocationResult(LocationSearchWrapperSessions parsedResults)
    {
        // Structure to hold each individual search result entry
        ArrayList<LocationResultSessions> resultSet = new ArrayList<LocationResultSessions>();
        pageCount = Integer.parseInt(parsedResults.pages);

        // Loop as many times as the count of sessions in the result set:
        for (int i = 0; i < (parsedResults.sessions.length); i++)
        {
            // Extract the required elements from each individual search result
            SessionDetails details = new SessionDetails(
                    parsedResults.sessions[i].id, parsedResults.sessions[i].url,
                    parsedResults.sessions[i].date);
            
            Coordinates coordinates = new Coordinates(
                    parsedResults.sessions[i].latitude,
                    parsedResults.sessions[i].longitude);
            
            User user = new User(parsedResults.sessions[i].member.id,
                    StringCleaner.cleanString(parsedResults.sessions[i].member.name),
                    parsedResults.sessions[i].member.url);
            
            Venue venue = new Venue(parsedResults.sessions[i].venue.id,
                    StringCleaner.cleanString(parsedResults.sessions[i].venue.name),
                    parsedResults.sessions[i].venue.telephone,
                    parsedResults.sessions[i].venue.email,
                    parsedResults.sessions[i].venue.web);
            
            Town town = new Town(parsedResults.sessions[i].town.id,
                    StringCleaner.cleanString(parsedResults.sessions[i].town.name));
            
            Area area = new Area(parsedResults.sessions[i].area.id,
                    StringCleaner.cleanString(parsedResults.sessions[i].area.name));
            
            Country country = new Country(parsedResults.sessions[i].country.id,
                    StringCleaner.cleanString(parsedResults.sessions[i].country.name));

            // Instantiate and populate a LocationResultSessions object and add it to the result set
            LocationResultSessions currentResult = new LocationResultSessions(details, coordinates, user, venue, town, area, country);
            resultSet.add(currentResult);
        }
        return resultSet;
    }

    /**
     * Helper method to gather and parse the response to a location-based search for sessions
     * 
     * @param parsedResults a LocationSearchWrapperEvents containing response data from the API
     * @return an ArrayList of LocationResultEvents objects
     */
    private ArrayList<LocationResultEvents> populateEventsByLocationResult(LocationSearchWrapperEvents parsedResults)
    {
        // Structure to hold each individual search result entry
        ArrayList<LocationResultEvents> resultSet = new ArrayList<LocationResultEvents>();
        pageCount = Integer.parseInt(parsedResults.pages);

        // Loop as many times as the count of events in the result set:
        for (int i = 0; i < (parsedResults.events.length); i++)
        {
            // Extract the required elements from each individual search result
            EventDetails details = new EventDetails(parsedResults.events[i].id,
                    StringCleaner.cleanString(parsedResults.events[i].name),
                    parsedResults.events[i].url, parsedResults.events[i].date);
            
            EventSchedule schedule = new EventSchedule(
                    parsedResults.events[i].dtstart,
                    parsedResults.events[i].dtend);
            
            Coordinates coordinates = new Coordinates(
                    parsedResults.events[i].latitude,
                    parsedResults.events[i].longitude);
            
            User user = new User(parsedResults.events[i].member.id,
                    StringCleaner.cleanString(parsedResults.events[i].member.name),
                    parsedResults.events[i].member.url);
            
            Venue venue = new Venue(parsedResults.events[i].venue.id,
                    StringCleaner.cleanString(parsedResults.events[i].venue.name),
                    parsedResults.events[i].venue.telephone,
                    parsedResults.events[i].venue.email,
                    parsedResults.events[i].venue.web);
            
            Town town = new Town(parsedResults.events[i].town.id, 
                    StringCleaner.cleanString(parsedResults.events[i].town.name));
            
            Area area = new Area(parsedResults.events[i].area.id, 
                    StringCleaner.cleanString(parsedResults.events[i].area.name));
            
            Country country = new Country(parsedResults.events[i].country.id,
                    StringCleaner .cleanString(parsedResults.events[i].country.name));

            // Instantiate and populate a LocationResultEvents object and add it to the result set
            LocationResultEvents currentResult = new LocationResultEvents(details, user, schedule, coordinates, venue, town, area, country);
            resultSet.add(currentResult);
        }
        return resultSet;
    }
    
    /**
     * Helper method to gather and parse the response to a search for trips by location
     * 
     * @param parsedResults  a LatestWrapperTrips object that has already been created and populated
     * @return an ArrayList of SearchResultTrips objects
     * 
     * @author Colman
     * @since 2018-12-08
     */
    private ArrayList<SearchResultTrips> populateTripsSearchResult(LatestWrapperTrips parsedResults)
    {
        ArrayList<SearchResultTrips> resultSet = new ArrayList<SearchResultTrips>();
        pageCount = parsedResults.pages;

        // Loop as many times as the count of trips in the result set:
        for (int i = 0; i < parsedResults.trips.length; i++)
        {
            // Extract the required elements from each individual search result in the JSON response
            TripDetails details = new TripDetails(
                    parsedResults.trips[i].id, 
                    parsedResults.trips[i].url,
                    parsedResults.trips[i].name,
                    parsedResults.trips[i].date);
            
            EventSchedule tripSchedule = new EventSchedule(
                    parsedResults.trips[i].dtstart,
                    parsedResults.trips[i].dtend);
            
            User submitter = new User(
                    parsedResults.trips[i].member.id,
                    StringCleaner.cleanString(parsedResults.trips[i].member.name),
                    parsedResults.trips[i].member.url);

            SearchResultTrips currentResult = new SearchResultTrips(details, tripSchedule, submitter);
            resultSet.add(currentResult);
        }
        return resultSet;
    }


    /**
     * Helper method to validate the latitude, longitude and radius provided by the user
     * 
     * @param latitude the latitude of the point at the centre of the search
     * @param longitude the longitude of the point at the centre of the search
     * @param radius a radius in Km around the coordinates, to be included in the search
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

        // Assuming a radius of up to 1000Km is more than adequate
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
        else
            return true;
    }
    
    /**
     * A helper method used to put the URL together to query the API
     * 
     * @param dataCategory The category of data to be queried, e.g. sessions, events etc.
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private URL composeURL(String dataCategory) throws MalformedURLException, URISyntaxException
    {
        // Build the URL with all necessary parameters to perform a search
        URL requestURL;

        // Assemble the query parameters for the URL
        List<NameValuePair> queryParams = new ArrayList<>();
        queryParams.add(new BasicNameValuePair("latlon", latitude + "," + longitude));
        queryParams.add(new BasicNameValuePair("radius", radius));

        // If a particular page within the response from the API is specified:
        if (pageNumber > 0)
        {
            URLComposer builder = new URLComposer();
            requestURL = builder.new Builder()
                    .requestType(RequestType.SEARCH_BY_LOCATION)
                    .path(dataCategory + "/" + "nearby")
                    .queryParameters(queryParams).itemsPerPage(resultsPerPage)
                    .pageNumber(pageNumber).build();
        }
        
        // If no page is specified
        else if (pageNumber == 0)
        {
            URLComposer builder = new URLComposer();
            requestURL = builder.new Builder()
                    .requestType(RequestType.SEARCH_BY_LOCATION)
                    .path(dataCategory + "/" + "nearby")
                    .queryParameters(queryParams).itemsPerPage(resultsPerPage)
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
