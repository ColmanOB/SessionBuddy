package sessionbuddy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import sessionbuddy.utils.HttpRequestor;
import sessionbuddy.utils.JsonParser;
import sessionbuddy.utils.RequestType;
import sessionbuddy.utils.StringCleaner;
import sessionbuddy.utils.URLComposer;
import sessionbuddy.wrappers.granularobjects.Area;
import sessionbuddy.wrappers.granularobjects.Artist;
import sessionbuddy.wrappers.granularobjects.Coordinates;
import sessionbuddy.wrappers.granularobjects.Country;
import sessionbuddy.wrappers.granularobjects.DiscussionDetails;
import sessionbuddy.wrappers.granularobjects.EventDetails;
import sessionbuddy.wrappers.granularobjects.EventSchedule;
import sessionbuddy.wrappers.granularobjects.SettingDetails;
import sessionbuddy.wrappers.granularobjects.TuneDetails;
import sessionbuddy.wrappers.granularobjects.RecordingDetails;
import sessionbuddy.wrappers.granularobjects.SessionDetails;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.TripDetails;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperDiscussions;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperEvents;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperRecordings;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperSessions;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperTrips;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperTunes;
import sessionbuddy.wrappers.resultsets.SearchResultTunesLatest;
import sessionbuddy.wrappers.resultsets.SearchResultEvents;
import sessionbuddy.wrappers.resultsets.SearchResultSessions;
import sessionbuddy.wrappers.resultsets.SearchResultTrips;
import sessionbuddy.wrappers.resultsets.SearchResultDiscussions;
import sessionbuddy.wrappers.resultsets.SearchResultRecordings;

/**
 * Retrieves a list of most-recently added entries in a chosen category; 
 * tunes, discussions, recordings, events or sessions.
 * 
 * @author Colman O'B
 * @since 2018-04-01
 */
public class LatestSearch extends Search
{
    /**
     * The number of individual search results that should be returned per page
     */
    private int resultsPerPage = 0;

    /**
     * Specifies a particular page within a multi-page response
     */
    private int pageNumber = 0;

    /**
     * Constructor for cases where pagination is not required
     * 
     * @param resultsPerPage Specifies how many search results should appear in each page of the JSON response from the API
     */
    public LatestSearch(int resultsPerPage)
    {
        this.resultsPerPage = resultsPerPage;
    }

    /**
     * Constructor for cases where you need to specify an individual page in the API response
     * 
     * @param resultsPerPage Specifies how many search results should appear in each page of the JSON response from the API
     * @param pageNumber Specifies a particular page number within the JSON response
     */
    public LatestSearch(int resultsPerPage, int pageNumber)
    {
        this(resultsPerPage);
        this.pageNumber = pageNumber;
    }

    /**
     * Retrieves the most recently added tunes/settings on thesession.org, most recent first
     * 
     * @return an ArrayList of SearchResultTunesLatest objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public ArrayList<SearchResultTunesLatest> listTunes() throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL("tunes"));
            // Parse the returned JSON into a wrapper class
            LatestWrapperTunes parsedResults = JsonParser.parseResponse(response, LatestWrapperTunes.class);
            // Return the data retrieved from the API
            return populateTunesSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * Retrieves the most recently added discussions on thesession.org, most recent first
     * 
     * @return an ArrayList of SearchResultsDiscussion objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public ArrayList<SearchResultDiscussions> listDiscussions() throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL("discussions"));
            // Parse the JSON response into a wrapper
            KeywordSearchWrapperDiscussions parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperDiscussions.class);
            // Return the data retrieved from the API
            return populateDiscussionsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * Retrieves a list of recently added recordings on thesession.org, 
     * with the most recent results first
     * 
     * @return an ArrayList of SearchResultRecordings objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public ArrayList<SearchResultRecordings> listRecordings() throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("recordings"));
            // Parse the returned JSON into a wrapper
            KeywordSearchWrapperRecordings parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperRecordings.class);
            // Return the data retrieved from the response
            return populateRecordingsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * Retrieves a list of recently added sessions on thesession.org,
     * with the most recent results first
     * 
     * @return an ArrayList of SearchResultSessions objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public ArrayList<SearchResultSessions> listSessions() throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("sessions"));
            // Parse the returned JSON into a wrapper
            KeywordSearchWrapperSessions parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperSessions.class);
            // Return the data retrieved from the API
            return populateSessionsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * Retrieves a list of recently added events on thesession.org,
     * with the most recent results first
     * 
     * @return an ArrayList of SearchResultEvents objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public ArrayList<SearchResultEvents> listEvents() throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("events"));
            // Parse the returned JSON into a wrapper
            KeywordSearchWrapperEvents parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperEvents.class);
            // Return the data retrieved from the API
            return populateEventsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    /**
     * Retrieves a list of recently added trips on thesession.org,
     * with the most recent results first
     * 
     * @return an ArrayList of SearchResultTrips objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public ArrayList<SearchResultTrips> listTrips() throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("trips"));
            // Parse the returned JSON into a wrapper
            LatestWrapperTrips parsedResults = JsonParser.parseResponse(response, LatestWrapperTrips.class);
            // Return the data retrieved from the API
            return populateTripsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * Helper method to gather and parse the response to a keyword search for a tune
     * 
     * @param parsedResults  a LatestWrapperTunes object that has already been created and populated
     * @return an ArrayList of SearchResultTunesLatest objects
     * 
     * @author Colman
     * @since 2018-02-10
     */
    private ArrayList<SearchResultTunesLatest> populateTunesSearchResult(LatestWrapperTunes parsedResults)
    {
        ArrayList<SearchResultTunesLatest> resultSet = new ArrayList<SearchResultTunesLatest>();
        pageCount = Integer.parseInt(parsedResults.pages);

        // Loop as many times as the count of tunes in the result set:
        for (int i = 0; i < parsedResults.settings.length; i++)
        {
            // Extract the required elements from each individual search result in the JSON response
            SettingDetails details = new SettingDetails(
                    parsedResults.settings[i].id, parsedResults.settings[i].url,
                    parsedResults.settings[i].key,
                    parsedResults.settings[i].date);
            
            User submitter = new User(
                    parsedResults.settings[i].member.id,
                    StringCleaner.cleanString(parsedResults.settings[i].member.name),
                    parsedResults.settings[i].member.url);
            
            TuneDetails settingDetails = new TuneDetails(
                    parsedResults.settings[i].tune.id,
                    StringCleaner.cleanString(parsedResults.settings[i].tune.name),
                    parsedResults.settings[i].tune.url);

            // Instantiate a TunesSearchResult object & populate it
            SearchResultTunesLatest currentResult = new SearchResultTunesLatest(details, submitter, settingDetails);
            // Add the object to the ArrayList to be returned
            resultSet.add(currentResult);
        }
        return resultSet;
    }

    /**
     * Helper method to gather and parse the response to a search for the latest discussions
     * 
     * @param parsedResults an existing populated KeywordSearchWrapperDiscussions object
     * @return an ArrayList of SearchResultDiscussions objects
     * 
     * @author Colman
     * @since 2018-02-10
     */
    private ArrayList<SearchResultDiscussions> populateDiscussionsSearchResult(KeywordSearchWrapperDiscussions parsedResults)
    {
        ArrayList<SearchResultDiscussions> resultSet = new ArrayList<SearchResultDiscussions>();
        pageCount = Integer.parseInt(parsedResults.pages);

        // Loop as many times as the count of recordings in the result set:
        for (int i = 0; i < (parsedResults.discussions.length); i++)
        {
            // Extract the elements from each individual search result in the JSON response
            DiscussionDetails details = new DiscussionDetails(
                    parsedResults.discussions[i].id,
                    StringCleaner.cleanString(parsedResults.discussions[i].name),
                    parsedResults.discussions[i].url,
                    parsedResults.discussions[i].date,
                    parsedResults.discussions[i].comments);
            
            User user = new User(
                    parsedResults.discussions[i].member.id,
                    StringCleaner.cleanString(parsedResults.discussions[i].member.name),
                    parsedResults.discussions[i].member.url);

            // Instantiate a DiscussionsSearchResult object & populate it
            SearchResultDiscussions currentResult = new SearchResultDiscussions(details, user);
            // Add the object to the ArrayList to be returned to the caller
            resultSet.add(currentResult);
        }
        return resultSet;
    }

    /**
     * Helper method to gather and parse the response to a search for the latest recordings
     * 
     * @param parsedResults an existing KeywordSearchWrapperRecordings object that has already been populated
     * @return an ArrayList of SearchResultRecordings objects
     * 
     * @author Colman
     * @since 2018-02-10
     */
    private ArrayList<SearchResultRecordings> populateRecordingsSearchResult(KeywordSearchWrapperRecordings parsedResults)
    {
        ArrayList<SearchResultRecordings> resultSet = new ArrayList<SearchResultRecordings>();
        pageCount = Integer.parseInt(parsedResults.pages);

        // Loop as many times as the count of recordings in the result set:
        for (int i = 0; i < (parsedResults.recordings.length); i++)
        {
            // Extract the required elements from each individual search result in the JSON response
            RecordingDetails details = new RecordingDetails(
                    parsedResults.recordings[i].id,
                    StringCleaner.cleanString(parsedResults.recordings[i].name),
                    parsedResults.recordings[i].url,
                    parsedResults.recordings[i].date);
            
            User submitter = new User(
                    parsedResults.recordings[i].member.id,
                    StringCleaner.cleanString(parsedResults.recordings[i].member.name),
                    parsedResults.recordings[i].member.url);
            
            Artist artist = new Artist(
                    parsedResults.recordings[i].artist.id,
                    StringCleaner.cleanString(parsedResults.recordings[i].artist.name),
                    parsedResults.recordings[i].url);

            // Instantiate and populate a SearchResultRecordings object
            SearchResultRecordings currentResult = new SearchResultRecordings(details, submitter, artist);
            // Add the object to the ArrayList to be returned to the caller
            resultSet.add(currentResult);
        }
        return resultSet;
    }

    /**
     * Helper method to gather and parse the response to a search for the latest sessions
     * 
     * @param parsedResults an existing KeywordSearchWrapperSessions object that has been populated
     * @return an ArrayList of SearchResultSessions objects
     * 
     * @author Colman
     * @since 2018-02-10
     */
    private ArrayList<SearchResultSessions> populateSessionsSearchResult(KeywordSearchWrapperSessions parsedResults)
    {
        ArrayList<SearchResultSessions> resultSet = new ArrayList<SearchResultSessions>();
        pageCount = Integer.parseInt(parsedResults.pages);

        // Loop as many times as the count of recordings in the result set:
        for (int i = 0; i < (parsedResults.sessions.length); i++)
        {
            // Extract the required elements from each individual search result in the JSON response
            SessionDetails details = new SessionDetails(
                    parsedResults.sessions[i].id, parsedResults.sessions[i].url,
                    parsedResults.sessions[i].date);
            
            Coordinates coordinates = new Coordinates(
                    parsedResults.sessions[i].latitude,
                    parsedResults.sessions[i].longitude);
            
            User submitter = new User(
                    parsedResults.sessions[i].member.id,
                    StringCleaner.cleanString(parsedResults.sessions[i].member.name),
                    parsedResults.sessions[i].member.url);
            
            Venue venue = new Venue(
                    parsedResults.sessions[i].venue.id,
                    StringCleaner.cleanString(parsedResults.sessions[i].venue.name),
                    parsedResults.sessions[i].venue.telephone,
                    parsedResults.sessions[i].venue.email,
                    parsedResults.sessions[i].venue.web);
            
            Town town = new Town(
                    parsedResults.sessions[i].town.id,
                    StringCleaner.cleanString(parsedResults.sessions[i].town.name));
            
            Area area = new Area(
                    parsedResults.sessions[i].area.id,
                    StringCleaner.cleanString(parsedResults.sessions[i].area.name));
            
            Country country = new Country(
                    parsedResults.sessions[i].country.id,
                    StringCleaner.cleanString(parsedResults.sessions[i].country.name));

            // Instantiate and populate a SearchResultSessions object 
            SearchResultSessions currentResult = new SearchResultSessions(details, coordinates, submitter, venue, town, area, country);
            // Add the object to the ArrayList to be returned to the caller
            resultSet.add(currentResult);
        }

        return resultSet;
    }

    /**
     * Helper method to gather and parse the response to a search for the latest events
     * 
     * @param parsedResults an existing KeywordSearchWrapperEvents object that has been populated
     * @return an ArrayList of SearchResultEvents objects
     * 
     * @author Colman
     * @since 2018-02-10
     */
    private ArrayList<SearchResultEvents> populateEventsSearchResult(KeywordSearchWrapperEvents parsedResults)
    {
        ArrayList<SearchResultEvents> resultSet = new ArrayList<SearchResultEvents>();
        pageCount = Integer.parseInt(parsedResults.pages);

        // Loop as many times as the count of events in the result set:
        for (int i = 0; i < (parsedResults.events.length); i++)
        {
            // Extract the required elements from each individual search result in the JSON response
            EventDetails details = new EventDetails(
                    parsedResults.events[i].id,
                    StringCleaner.cleanString(parsedResults.events[i].name),
                    parsedResults.events[i].url, parsedResults.events[i].date);
            
            Coordinates coordinates = new Coordinates(
                    parsedResults.events[i].latitude,
                    parsedResults.events[i].longitude);
            
            EventSchedule schedule = new EventSchedule(
                    parsedResults.events[i].dtstart,
                    parsedResults.events[i].dtend);
            
            User submitter = new User(
                    parsedResults.events[i].member.id,
                    StringCleaner.cleanString(parsedResults.events[i].member.name),
                    parsedResults.events[i].member.url);
            
            Venue venue = new Venue(
                    parsedResults.events[i].venue.id,
                    StringCleaner.cleanString(parsedResults.events[i].venue.name),
                    parsedResults.events[i].venue.telephone,
                    parsedResults.events[i].venue.email,
                    parsedResults.events[i].venue.web);
            
            Town town = new Town(
                    parsedResults.events[i].town.id, 
                    StringCleaner.cleanString(parsedResults.events[i].town.name));
            
            Area area = new Area(
                    parsedResults.events[i].area.id, 
                    StringCleaner.cleanString(parsedResults.events[i].area.name));
            
            Country country = new Country(
                    parsedResults.events[i].country.id,
                    StringCleaner.cleanString(parsedResults.events[i].country.name));

            // Instantiate and populate a SearchResultEvents object
            SearchResultEvents currentResult = new SearchResultEvents(details, submitter, schedule, coordinates, venue, town, area, country);
            // Add the object to the ArrayList to be returned to the caller
            resultSet.add(currentResult);
        }
        return resultSet;
    }
    
    /**
     * Helper method to gather and parse the response to a search for most recently-added trips
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
            URLComposer builder = new URLComposer();
            requestURL = builder.new Builder()
                    .requestType(RequestType.SEARCH_LATEST_ITEMS)
                    .path(dataCategory + "/" + "new")
                    .itemsPerPage(resultsPerPage).pageNumber(pageNumber)
                    .build();
        }
        
        // If no page is specified
        else if (pageNumber == 0)
        {
            URLComposer builder = new URLComposer();
            requestURL = builder.new Builder()
                    .requestType(RequestType.SEARCH_LATEST_ITEMS)
                    .path(dataCategory + "/" + "new")
                    .itemsPerPage(resultsPerPage).build();
        }
        
        // If anything other than a positive integer was specified as the page number
        else
        {
            throw new IllegalArgumentException("Page number must be an integer value greater than zero");
        }
        
        return requestURL;
    }
}
