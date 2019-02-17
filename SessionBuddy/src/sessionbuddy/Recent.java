package sessionbuddy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import sessionbuddy.utils.DataCategory;
import sessionbuddy.utils.HttpRequestor;
import sessionbuddy.utils.JsonParser;
import sessionbuddy.utils.PageCountValidator;
import sessionbuddy.utils.RequestType;
import sessionbuddy.utils.StringCleaner;
import sessionbuddy.utils.URLComposer;
import sessionbuddy.wrappers.granularobjects.Area;
import sessionbuddy.wrappers.granularobjects.Artist;
import sessionbuddy.wrappers.granularobjects.Coordinates;
import sessionbuddy.wrappers.granularobjects.Country;
import sessionbuddy.wrappers.granularobjects.DiscussionDetails;
import sessionbuddy.wrappers.granularobjects.EventDetails;
import sessionbuddy.wrappers.granularobjects.Schedule;
import sessionbuddy.wrappers.granularobjects.SettingDetails;
import sessionbuddy.wrappers.granularobjects.TuneDetails;
import sessionbuddy.wrappers.granularobjects.RecordingDetails;
import sessionbuddy.wrappers.granularobjects.SessionDetails;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.TripDetails;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;
import sessionbuddy.wrappers.individualresults.Discussion;
import sessionbuddy.wrappers.individualresults.Event;
import sessionbuddy.wrappers.individualresults.Recording;
import sessionbuddy.wrappers.individualresults.Session;
import sessionbuddy.wrappers.individualresults.Trip;
import sessionbuddy.wrappers.individualresults.TuneLatest;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperDiscussions;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperEvents;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperRecordings;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperSessions;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperTrips;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperTunes;
import sessionbuddy.wrappers.responsemetadata.LatestSearchResultHeaders;
import sessionbuddy.wrappers.resultsets.SearchResultDiscussionsLatest;
import sessionbuddy.wrappers.resultsets.SearchResultEventsLatest;
import sessionbuddy.wrappers.resultsets.SearchResultRecordingsLatest;
import sessionbuddy.wrappers.resultsets.SearchResultSessionsLatest;
import sessionbuddy.wrappers.resultsets.SearchResultTripsLatest;
import sessionbuddy.wrappers.resultsets.SearchResultTunesLatest;

/**
 * Contains a number of static methods to retrieves a list of 
 * most-recently added entries in a chosen category; 
 * tunes, discussions, recordings, events or sessions.
 * 
 * @author Colman O'B
 * @since 2019-02-12
 */
// TODO: Fix up comments on all methods
public class Recent
{
    /**
     * Retrieves the most recently added tunes/settings on thesession.org, most recent first.
     * This form of the method retrieves a specified page from the API response.
     *
     * @param resultsPerPage the number of search results to be retrieved per page in the response from the API
     * @param pageNumber the page number to be retrieved, in a multi-page response from the API
     * @return a SearchResultTunesLatest object, containing all of the data returned in response to the API query
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     */
    public static SearchResultTunesLatest listTunes(int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.tunes;
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, resultsPerPage, pageNumber));
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
     * Retrieves the most recently added tunes/settings on thesession.org, most recent first.
     * This form of the method is used when no particular page is required, and simply returns 
     * the first page of the API response.
     *
     * @param resultsPerPage the number of search results to be retrieved per page in the response from the API
     * @return a SearchResultTunesLatest object, containing all of the data returned in response to the API query
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     */
    public static SearchResultTunesLatest listTunes(int resultsPerPage) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.tunes;
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, resultsPerPage));
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
     * @param resultsPerPage
     * @return
     * @throws IllegalArgumentException
     * @throws IOException
     * @throws URISyntaxException
     */
    public static SearchResultDiscussionsLatest listDiscussions(int resultsPerPage) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.discussions;
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, resultsPerPage));
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
    
    public static SearchResultDiscussionsLatest listDiscussions(int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.discussions;
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, resultsPerPage, pageNumber));
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
    public static SearchResultRecordingsLatest listRecordings(int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.recordings;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, resultsPerPage, pageNumber));
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
    
    public static SearchResultRecordingsLatest listRecordings(int resultsPerPage) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.recordings;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, resultsPerPage));
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
    public static SearchResultSessionsLatest listSessions(int resultsPerPage) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.sessions;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, resultsPerPage));
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

    public static SearchResultSessionsLatest listSessions(int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.sessions;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, resultsPerPage, pageNumber));
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

    public static SearchResultEventsLatest listEvents(int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.events;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, resultsPerPage, pageNumber));
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
    
    public static SearchResultEventsLatest listEvents(int resultsPerPage) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.events;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, resultsPerPage));
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
/*    public ArrayList<SearchResultTrips> listTrips() throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
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
    } */

    public static SearchResultTripsLatest listTrips(int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.trips;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, resultsPerPage, pageNumber));
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
    
    public static SearchResultTripsLatest listTrips(int resultsPerPage) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.trips;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, resultsPerPage));
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
     * @return an ArrayList of TuneLatest objects
     * 
     * @author Colman
     * @since 201-02-10
     */
    private static SearchResultTunesLatest populateTunesSearchResult(LatestWrapperTunes parsedResults)
    {
        // Capture the metadata for the search results
        LatestSearchResultHeaders headers = new LatestSearchResultHeaders(parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // This will hold the list of individual items in the result set
        ArrayList<TuneLatest> resultSet = new ArrayList<TuneLatest>();

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

            // Put the individual search result into a wrapper object, and add to the larger result set
            TuneLatest currentResult = new TuneLatest(details, submitter, settingDetails);
            resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        SearchResultTunesLatest searchResult = new SearchResultTunesLatest(headers, resultSet);
        return searchResult;
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
    private static SearchResultDiscussionsLatest populateDiscussionsSearchResult(KeywordSearchWrapperDiscussions parsedResults)
    {
        // Capture the metadata for the search results
        LatestSearchResultHeaders headers = new LatestSearchResultHeaders(parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // This will hold the list of individual items in the result set
        ArrayList<Discussion> resultSet = new ArrayList<Discussion>();

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

            // Put the individual search result into a wrapper object, and add to the larger result set
            Discussion currentResult = new Discussion(details, user);
            resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        SearchResultDiscussionsLatest searchResult = new SearchResultDiscussionsLatest(headers, resultSet);
        return searchResult;
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
    private static SearchResultRecordingsLatest populateRecordingsSearchResult(KeywordSearchWrapperRecordings parsedResults)
    {
        // Capture the metadata for the search results
        LatestSearchResultHeaders headers = new LatestSearchResultHeaders(parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // This will hold the list of individual items in the result set
        ArrayList<Recording> resultSet = new ArrayList<Recording>();
        
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

            Recording currentResult = new Recording(details, submitter, artist);
            resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        SearchResultRecordingsLatest searchResult = new SearchResultRecordingsLatest(headers, resultSet);
        return searchResult;
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
    private static SearchResultSessionsLatest populateSessionsSearchResult(KeywordSearchWrapperSessions parsedResults)
    {
        // Capture the metadata for the search results
        LatestSearchResultHeaders headers = new LatestSearchResultHeaders(parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // This will hold the list of individual items in the result set
        ArrayList<Session> resultSet = new ArrayList<Session>();

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
            
            Session currentResult = new Session(details, coordinates, submitter, venue, town, area, country);
            resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        SearchResultSessionsLatest searchResult = new SearchResultSessionsLatest(headers, resultSet);
        return searchResult;
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
    private static SearchResultEventsLatest populateEventsSearchResult(KeywordSearchWrapperEvents parsedResults)
    {
        // Capture the metadata for the search results
        LatestSearchResultHeaders headers = new LatestSearchResultHeaders(parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // This will hold the list of individual items in the result set
        ArrayList<Event> resultSet = new ArrayList<Event>();

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
            
            Schedule schedule = new Schedule(
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
            
            // Put the individual search result into a wrapper object, and add to the larger result set
            Event currentResult = new Event(details, submitter, schedule, coordinates, venue, town, area, country);
            resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        SearchResultEventsLatest searchResult = new SearchResultEventsLatest(headers, resultSet);
        return searchResult;
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
    private static SearchResultTripsLatest populateTripsSearchResult(LatestWrapperTrips parsedResults)
    {
        // Capture the metadata for the search results
        LatestSearchResultHeaders headers = new LatestSearchResultHeaders(parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // This will hold the list of individual items in the result set
        ArrayList<Trip> resultSet = new ArrayList<Trip>();

        // Loop as many times as the count of trips in the result set:
        for (int i = 0; i < parsedResults.trips.length; i++)
        {
            // Extract the required elements from each individual search result in the JSON response
            TripDetails details = new TripDetails(
                    parsedResults.trips[i].id, 
                    parsedResults.trips[i].url,
                    parsedResults.trips[i].name,
                    parsedResults.trips[i].date);
            
            Schedule tripSchedule = new Schedule(
                    parsedResults.trips[i].dtstart,
                    parsedResults.trips[i].dtend);
            
            User submitter = new User(
                    parsedResults.trips[i].member.id,
                    StringCleaner.cleanString(parsedResults.trips[i].member.name),
                    parsedResults.trips[i].member.url);
            
            // Put the individual search result into a wrapper object, and add to the larger result set
            Trip currentResult = new Trip(details, tripSchedule, submitter);
            resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        SearchResultTripsLatest searchResult = new SearchResultTripsLatest(headers, resultSet);
        return searchResult;
    }
    
    /**
     * A helper method used to put the URL together to query the API at thesession.org
     * 
     * @param dataCategory The category of data to be queried, e.g. tunes, discussions, events etc.
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private static URL composeURL(DataCategory dataCategory, int resultsPerPage) throws MalformedURLException, URISyntaxException
    {
        // Build the URL with all necessary parameters to perform a search via thesession.org API
        URL requestURL;

            URLComposer builder = new URLComposer();
            requestURL = builder.new Builder()
                    .requestType(RequestType.SEARCH_LATEST_ITEMS)
                    .path(dataCategory + "/" + "new")
                    .itemsPerPage(resultsPerPage).build();
                
        return requestURL;
    }

    /**
     * A helper method used to put the URL together to query the API at thesession.org
     * 
     * @param dataCategory The category of data to be queried, e.g. tunes, discussions, events etc.
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private static URL composeURL(DataCategory dataCategory, int resultsPerPage, int pageNumber) throws MalformedURLException, URISyntaxException
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
