package sessionbuddy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import sessionbuddy.utils.DataCategory;
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
import sessionbuddy.wrappers.granularobjects.Schedule;
import sessionbuddy.wrappers.granularobjects.RecordingDetails;
import sessionbuddy.wrappers.granularobjects.SessionDetails;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.TripDetails;
import sessionbuddy.wrappers.granularobjects.TuneDetails;
import sessionbuddy.wrappers.granularobjects.TuneDetailsWithDate;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;
import sessionbuddy.wrappers.individualresults.SearchResultSingleDiscussion;
import sessionbuddy.wrappers.individualresults.SearchResultSingleEvent;
import sessionbuddy.wrappers.individualresults.SearchResultSingleRecording;
import sessionbuddy.wrappers.individualresults.SearchResultSingleSession;
import sessionbuddy.wrappers.individualresults.SearchResultSingleTrip;
import sessionbuddy.wrappers.individualresults.SearchResultSingleTune;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperDiscussions;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperEvents;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperRecordings;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperSessions;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperTunes;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperTrips;
import sessionbuddy.wrappers.responsemetadata.KeywordSearchResultHeaders;
import sessionbuddy.wrappers.resultsets.SearchResultTunes;
import sessionbuddy.wrappers.resultsets.SearchResultEvents;
import sessionbuddy.wrappers.resultsets.SearchResultDiscussions;
import sessionbuddy.wrappers.resultsets.SearchResultRecordings;
import sessionbuddy.wrappers.resultsets.SearchResultSessions;
import sessionbuddy.wrappers.resultsets.SearchResultTrips;

// TODO: Need to add Javadoc comments for all public methods

/**
 * Queries the API at thesession.org for a chosen type of data, using search terms, 
 * and parses the response into an easily usable structure. 
 * 
 * All public methods in this class are static, so there is no need for the caller to 
 * actually instantiate a KeywordSearch object.
 * 
 * @author Colman O'B
 * @since 2019-01-27
 *
 */
public class KeywordSearch extends Search
{   
    public static SearchResultTunes searchTunes(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {            
            validateResultsPerPageCount(resultsPerPage);
            DataCategory dataCategory = DataCategory.tunes;
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, searchTerms, resultsPerPage, pageNumber));
            // Parse the returned JSON into a wrapper
            KeywordSearchWrapperTunes parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperTunes.class);
            // Return the data retrieved from the API
            return populateTunesSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    } 
    
    public static SearchResultTunes searchTunes(String searchTerms, int resultsPerPage) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {            
            validateResultsPerPageCount(resultsPerPage);
            DataCategory dataCategory = DataCategory.tunes;
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, searchTerms, resultsPerPage));
            // Parse the returned JSON into a wrapper
            KeywordSearchWrapperTunes parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperTunes.class);
            // Return the data retrieved from the API
            return populateTunesSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static SearchResultDiscussions searchDiscussions(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            DataCategory dataCategory = DataCategory.discussions;
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, searchTerms, resultsPerPage, pageNumber));
            // Parse the returned JSON into a wrapper
            KeywordSearchWrapperDiscussions parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperDiscussions.class);
            // Return the data retrieved from the API
            return populateDiscussionsSearchResult(parsedResults);
        }
        
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static SearchResultDiscussions searchDiscussions(String searchTerms, int resultsPerPage) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            DataCategory dataCategory = DataCategory.discussions;
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, searchTerms, resultsPerPage));
            // Parse the returned JSON into a wrapper
            KeywordSearchWrapperDiscussions parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperDiscussions.class);
            // Return the data retrieved from the API
            return populateDiscussionsSearchResult(parsedResults);
        }
        
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * Queries the API for a list of events matching a set of search terms
     * 
     * @param searchTerms
     * @param resultsPerPage
     * @param pageNumber
     * @return an ArrayList of EventsSearchResult objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * @since 2018-12-27
     */
    public static SearchResultEvents searchEvents(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            DataCategory dataCategory = DataCategory.events;
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, searchTerms, resultsPerPage, pageNumber));
            // Parse the JSON reponse into a wrapper
            KeywordSearchWrapperEvents parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperEvents.class);
            // Return the data retrieved from the API
            return populateEventsSearchResult(parsedResults);
        }
        
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static SearchResultEvents searchEvents(String searchTerms, int resultsPerPage) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            DataCategory dataCategory = DataCategory.events;
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, searchTerms, resultsPerPage));
            // Parse the JSON reponse into a wrapper
            KeywordSearchWrapperEvents parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperEvents.class);
            // Return the data retrieved from the API
            return populateEventsSearchResult(parsedResults);
        }
        
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static SearchResultRecordings searchRecordings(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            DataCategory dataCategory = DataCategory.recordings;
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, searchTerms, resultsPerPage, pageNumber));
            // Parse the returned JSON into a wrapper
            KeywordSearchWrapperRecordings parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperRecordings.class);
            // Return the data retrieved from the API
            return populateRecordingsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static SearchResultRecordings searchRecordings(String searchTerms, int resultsPerPage) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            DataCategory dataCategory = DataCategory.recordings;
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, searchTerms, resultsPerPage));
            // Parse the returned JSON into a wrapper
            KeywordSearchWrapperRecordings parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperRecordings.class);
            // Return the data retrieved from the API
            return populateRecordingsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    /**
     * Queries the API for a list of sessions matching a set of search terms
     * 
     * @return an ArrayList of SessionsSearchResult objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-03-04
     */
    public static SearchResultSessions searchSessions(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            DataCategory dataCategory = DataCategory.sessions;
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, searchTerms, resultsPerPage, pageNumber));
            // Parse the JSON response into a wrapper
            KeywordSearchWrapperSessions parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperSessions.class);
            // Return the data retrieved from the API
            return populateSessionsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static SearchResultSessions searchSessions(String searchTerms, int resultsPerPage) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            DataCategory dataCategory = DataCategory.sessions;
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, searchTerms, resultsPerPage));
            // Parse the JSON response into a wrapper
            KeywordSearchWrapperSessions parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperSessions.class);
            // Return the data retrieved from the API
            return populateSessionsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
        
    public static SearchResultTrips searchTrips(String searchTerms, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            DataCategory dataCategory = DataCategory.trips;
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, searchTerms, resultsPerPage, pageNumber));
            // Parse the JSON response into a wrapper
            LatestWrapperTrips parsedResults = JsonParser.parseResponse(response, LatestWrapperTrips.class);
            // Return the data retrieved from the API
            return populateTripsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static SearchResultTrips searchTrips(String searchTerms, int resultsPerPage) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            DataCategory dataCategory = DataCategory.trips;
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, searchTerms, resultsPerPage));
            // Parse the JSON response into a wrapper
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
     * Helper method to gather and parse the response to a keyword search for a tune
     * 
     * @param parsedResults a KeywordSearchWrapperTunes object that has already been created an populated
     * @return an ArrayList of SearchResultTunes objects
     */
    private static SearchResultTunes populateTunesSearchResult(KeywordSearchWrapperTunes parsedResults)
    {
        // Capture the metadata for the search results
        KeywordSearchResultHeaders headers = new KeywordSearchResultHeaders(parsedResults.q, parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // This will hold the list of individual items in the result set
        ArrayList<SearchResultSingleTune> resultSet = new ArrayList<SearchResultSingleTune>();
        
        // Loop as many times as the count of tunes in the result set
        for (int i = 0; i < (parsedResults.tunes.length); i++)
        {
            // Extract the required elements from each individual search result in the JSON response
            TuneDetails tuneDetails = new TuneDetails(
                    parsedResults.tunes[i].id,
                    StringCleaner.cleanString(parsedResults.tunes[i].name),
                    parsedResults.tunes[i].url);
            
            TuneDetailsWithDate details = new TuneDetailsWithDate(
                    tuneDetails,
                    parsedResults.tunes[i].type, parsedResults.tunes[i].date);
            
            User submitter = new User(
                    parsedResults.tunes[i].member.id,
                    StringCleaner.cleanString(parsedResults.tunes[i].member.name),
                    parsedResults.tunes[i].member.url);

            // Put the individual search result into a wrapper object, and add to the larger result set
            SearchResultSingleTune currentResult = new SearchResultSingleTune(details, submitter);
            resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        SearchResultTunes searchResult = new SearchResultTunes(headers, resultSet);
        return searchResult;
    }

    /**
     * Helper method to gather and parse the response to a keyword search for discussions
     * 
     * @param parsedResults
     * @return
     */
    private static SearchResultDiscussions populateDiscussionsSearchResult(KeywordSearchWrapperDiscussions parsedResults)
    {
        // Capture the metadata for the search results
        KeywordSearchResultHeaders headers = new KeywordSearchResultHeaders(parsedResults.q, parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // This will hold the list of individual items in the result set
        ArrayList<SearchResultSingleDiscussion> resultSet = new ArrayList<SearchResultSingleDiscussion>();

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
            SearchResultSingleDiscussion currentResult = new SearchResultSingleDiscussion(details, user);
            resultSet.add(currentResult);
        }
        
        // Put the response metadata and individual results into a single object to be returned
        SearchResultDiscussions searchResult = new SearchResultDiscussions(headers, resultSet);
        return searchResult;
    }
    
    private static SearchResultEvents populateEventsSearchResult(KeywordSearchWrapperEvents parsedResults)
    {
        // Capture the metadata for the search results
        KeywordSearchResultHeaders headers = new KeywordSearchResultHeaders(parsedResults.q, parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // This will hold the list of individual items in the result set
        ArrayList<SearchResultSingleEvent> resultSet = new ArrayList<SearchResultSingleEvent>();


        // Loop as many times as the count of events in the result set:
        for (int i = 0; i < (parsedResults.events.length); i++)
        {
            // Extract the required elements from each individual search result in the JSON response
            EventDetails details = new EventDetails(
                    parsedResults.events[i].id,
                    StringCleaner.cleanString(parsedResults.events[i].name),
                    parsedResults.events[i].url, parsedResults.events[i].date);
            
            User user = new User(
                    parsedResults.events[i].member.id,
                    StringCleaner.cleanString(parsedResults.events[i].member.name),
                    parsedResults.events[i].member.url);
            
            Schedule schedule = new Schedule(
                    parsedResults.events[i].dtstart,
                    parsedResults.events[i].dtend);
            
            Coordinates coordinates = new Coordinates(
                    parsedResults.events[i].latitude,
                    parsedResults.events[i].longitude);
            
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
            SearchResultSingleEvent currentResult = new SearchResultSingleEvent(details, user, schedule, coordinates, venue, town, area, country);
            resultSet.add(currentResult);
        }
        
        // Put the response metadata and individual results into a single object to be returned
        SearchResultEvents searchResult = new SearchResultEvents(headers, resultSet);
        return searchResult;
    }
    
    // TODO: Needs Javadoc comment
    private static SearchResultRecordings populateRecordingsSearchResult(KeywordSearchWrapperRecordings parsedResults)
    {
        ArrayList<SearchResultSingleRecording> resultSet = new ArrayList<SearchResultSingleRecording>();
        // Capture the metadata for the search results
        KeywordSearchResultHeaders headers = new KeywordSearchResultHeaders(parsedResults.q, parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // Loop as many times as the count of recordings in the result set:
        for (int i = 0; i < (parsedResults.recordings.length); i++)
        {
            // Extract the elements from each individual search result in the JSON response
            RecordingDetails details = new RecordingDetails(
                    parsedResults.recordings[i].id,
                    StringCleaner.cleanString(parsedResults.recordings[i].name),
                    parsedResults.recordings[i].url,
                    parsedResults.recordings[i].date);
            
            User user = new User(
                    parsedResults.recordings[i].member.id,
                    StringCleaner.cleanString(parsedResults.recordings[i].member.name),
                    parsedResults.recordings[i].member.url);
            
            Artist artist = new Artist(
                    parsedResults.recordings[i].artist.id,
                    StringCleaner.cleanString(parsedResults.recordings[i].artist.name),
                    parsedResults.recordings[i].artist.url);
     
            // Put the individual search result into a wrapper object, and add to the larger result set
            SearchResultSingleRecording currentResult = new SearchResultSingleRecording(details, user, artist);
            resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        SearchResultRecordings searchResult = new SearchResultRecordings(headers, resultSet);
        return searchResult;
    }
    
    private static SearchResultSessions populateSessionsSearchResult(KeywordSearchWrapperSessions parsedResults)
    {
        ArrayList<SearchResultSingleSession> resultSet = new ArrayList<SearchResultSingleSession>();
        // Capture the metadata for the search results
        KeywordSearchResultHeaders headers = new KeywordSearchResultHeaders(parsedResults.q, parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // Loop as many times as the count of sessions in the result set:
        for (int i = 0; i < (parsedResults.sessions.length); i++)
        {
            // Extract the required elements from each individual search result in the JSON response
            SessionDetails details = new SessionDetails(
                    parsedResults.sessions[i].id, parsedResults.sessions[i].url,
                    parsedResults.sessions[i].date);
            
            Coordinates coordinates = new Coordinates(
                    parsedResults.sessions[i].latitude,
                    parsedResults.sessions[i].longitude);
            
            User user = new User(
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

            // Put the individual search result into a wrapper object, and add to the larger result set
            SearchResultSingleSession currentResult = new SearchResultSingleSession(details, coordinates, user, venue, town, area, country);
            resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        SearchResultSessions searchResult = new SearchResultSessions(headers, resultSet);
        return searchResult;
    }
    
    private static SearchResultTrips populateTripsSearchResult(LatestWrapperTrips parsedResults)
    {
        ArrayList<SearchResultSingleTrip> resultSet = new ArrayList<SearchResultSingleTrip>();
        // Capture the metadata for the search results
        KeywordSearchResultHeaders headers = new KeywordSearchResultHeaders(parsedResults.q, parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
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
                    parsedResults.trips[i].dtend
                    );
            
            User submitter = new User(
                    parsedResults.trips[i].member.id,
                    StringCleaner.cleanString(parsedResults.trips[i].member.name),
                    parsedResults.trips[i].member.url);

            SearchResultSingleTrip currentResult = new SearchResultSingleTrip(details, tripSchedule, submitter);
            resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        SearchResultTrips searchResult = new SearchResultTrips(headers, resultSet);
        return searchResult;
    }


    /**
     * A helper method used to put the URL together to query the API
     * 
     * @param dataCategory The category of data to be queried, e.g. tunes, discussions, events etc.
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private static URL composeURL(DataCategory dataCategory, String searchTerms, int resultsPerPage) throws MalformedURLException, URISyntaxException
    {
        // Parse the search terms provided by the user
        List<NameValuePair> queryParams = new ArrayList<>();
        queryParams.add(new BasicNameValuePair("q", searchTerms));

        URL requestURL;

        URLComposer builder = new URLComposer();
        requestURL = builder.new Builder()
                .requestType(RequestType.SEARCH_BY_KEYWORD)
                .path(dataCategory + "/" + "search")
                .queryParameters(queryParams)
                .itemsPerPage(resultsPerPage)
                .build();

        return requestURL;
    }
    
    private static URL composeURL(DataCategory dataCategory, String searchTerms, int resultsPerPage, int pageNumber) throws MalformedURLException, URISyntaxException
    {
        // If anything other than a positive integer was specified for results per page or page number
       if (resultsPerPage <= 0)
        {
            throw new IllegalArgumentException("Results per page number must be an integer value greater than zero");
        }
        
        else if (pageNumber <= 0)
        {
            throw new IllegalArgumentException("Page number must be an integer value greater than zero");
        }

        // Parse the search terms provided by the user
        List<NameValuePair> queryParams = new ArrayList<>();
        queryParams.add(new BasicNameValuePair("q", searchTerms));

        URL requestURL;

        URLComposer builder = new URLComposer();
        requestURL = builder.new Builder()
                    .requestType(RequestType.SEARCH_BY_KEYWORD)
                    .path(dataCategory + "/" + "search")
                    .queryParameters(queryParams)
                    .itemsPerPage(resultsPerPage)
                    .pageNumber(pageNumber)
                    .build();

        return requestURL;
    }
}
