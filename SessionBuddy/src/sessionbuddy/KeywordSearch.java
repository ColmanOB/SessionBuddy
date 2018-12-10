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
import sessionbuddy.wrappers.granularobjects.Artist;
import sessionbuddy.wrappers.granularobjects.Coordinates;
import sessionbuddy.wrappers.granularobjects.Country;
import sessionbuddy.wrappers.granularobjects.DiscussionDetails;
import sessionbuddy.wrappers.granularobjects.EventDetails;
import sessionbuddy.wrappers.granularobjects.EventSchedule;
import sessionbuddy.wrappers.granularobjects.RecordingDetails;
import sessionbuddy.wrappers.granularobjects.SessionDetails;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.TripDetails;
import sessionbuddy.wrappers.granularobjects.TuneDetails;
import sessionbuddy.wrappers.granularobjects.TuneDetailsWithDate;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperDiscussions;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperEvents;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperRecordings;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperSessions;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperTunes;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperTrips;
import sessionbuddy.wrappers.resultsets.SearchResultEvents;
import sessionbuddy.wrappers.resultsets.SearchResultSessions;
import sessionbuddy.wrappers.resultsets.SearchResultTrips;
import sessionbuddy.wrappers.resultsets.SearchResultTunes;
import sessionbuddy.wrappers.resultsets.SearchResultDiscussions;
import sessionbuddy.wrappers.resultsets.SearchResultRecordings;

/**
 * Queries the API at thesession.org for a chosen type of data, using search terms, 
 * and parses the response into an easily usable structure. 
 * 
 * To use this feature, first create a new KeywordSearch object, then call one of its
 * methods to perform the actual search.
 * 
 * @author Colman O'B
 * @since 2018-03-07
 *
 */
public class KeywordSearch extends Search
{
    /**
     * The search terms / keywords to use when performing a keyword-based search
     */
    private String searchTerms = null;

    /**
     * The number of search results to be returned per page
     */
    private int resultsPerPage = 0;

    /**
     * Specifies an individual page within a multi-page response
     */
    private int pageNumber = 0;

    /**
     * Constructor where pagination is not required,
     * and you only want to see the first page of the API response
     * 
     * @param searchTerms The search terms / keywords to use in the search
     * @param resultsPerPage The number of results per page in the JSON response from the API
     */
    public KeywordSearch(String searchTerms, int resultsPerPage)
    {
        this.searchTerms = searchTerms;
        this.resultsPerPage = resultsPerPage;
    }

    /**
     * Constructor where you need to specify a page within the API response
     * 
     * @param searchTerms The search terms / keywords to use in the search
     * @param resultsPerPage The number of search results per page in the JSON response from the API
     * @param pageNumber Specifies a particular page number within the JSON response
     */
    public KeywordSearch(String searchTerms, int resultsPerPage, int pageNumber)
    {
        this(searchTerms, resultsPerPage);
        this.pageNumber = pageNumber;
    }

    /**
     * Searches the API for a list of tunes matching a specific set of search terms
     * 
     * @return An ArrayList of TunesSearchResult objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public ArrayList<SearchResultTunes> searchTunes() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL("tunes"));
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

    /**
     * Queries the API for a list of discussions matching a set of search terms
     * 
     * @return An ArrayList of DiscussionsSearchResult objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public ArrayList<SearchResultDiscussions> searchDiscussions() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL("discussions"));
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
     * @return an ArrayList of EventsSearchResult objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public ArrayList<SearchResultEvents> searchEvents() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL("events"));
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

    /**
     * Queries the API for a list of recordings matching a set of search terms
     * 
     * @return an ArrayList of RecordingsSearchResult objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-03-07
     */
    public ArrayList<SearchResultRecordings> searchRecordings() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL("recordings"));
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
     * Queries the API for a list of sessions matching a set of search  terms
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
    public ArrayList<SearchResultSessions> searchSessions() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL("sessions"));
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
    
    /**
     * Queries the API for a list of trips matching a set of search  terms
     * 
     * @return an ArrayList of SearchResultTrips objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-12-10
     */
    public ArrayList<SearchResultTrips> searchTrips() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL("trips"));
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
     * @param parsedResults a TunesSearchResultWrapper object that has already been created an populated
     * @return an ArrayList of TunesSearchResult objects
     */
    private ArrayList<SearchResultTunes> populateTunesSearchResult( KeywordSearchWrapperTunes parsedResults)
    {
        ArrayList<SearchResultTunes> resultSet = new ArrayList<SearchResultTunes>();
        pageCount = Integer.parseInt(parsedResults.pages);

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

            SearchResultTunes currentResult = new SearchResultTunes(details, submitter);
            // Add the SearchResultTunes object to the ArrayList to be returned to the caller
            resultSet.add(currentResult);
        }
        return resultSet;
    }

    /**
     * Helper method to gather and parse the response to a keyword search for discussions
     * 
     * @param parsedResults an existing populated DiscussionsSearchResultWrapper object
     * @return an ArrayList of DiscussionsSearchResult objects
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

            // Instantiate and populate a structure to hold the search results
            SearchResultDiscussions currentResult = new SearchResultDiscussions(details, user);
            // Add the SearchResultDiscussions object to the ArrayList to be returned to the caller
            resultSet.add(currentResult);
        }
        return resultSet;
    }

    /**
     * Helper method to gather and parse the response to a keyword search for events
     * 
     * @param parsedResults an EventsSearchResultWrapper object that has already been populated
     * @return an ArrayList of EventSearchResult objects
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
            
            User user = new User(
                    parsedResults.events[i].member.id,
                    StringCleaner.cleanString(parsedResults.events[i].member.name),
                    parsedResults.events[i].member.url);
            
            EventSchedule schedule = new EventSchedule(
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

            // Instantiate and populate a SearchResultEvents object
            SearchResultEvents currentResult = new SearchResultEvents(details, user, schedule, coordinates, venue, town, area, country);
            // Add the EventsSearchResult object to the ArrayList to be returned to the caller
            resultSet.add(currentResult);
        }
        return resultSet;
    }

    /**
     * Helper method to gather and parse the response to a keyword search for recordings
     * 
     * @param parsedResults a RecordingsSearchResultWrapper object that has already been populated
     * @return an ArrayList of RecordingsSearchResult objects
     */
    private ArrayList<SearchResultRecordings> populateRecordingsSearchResult(KeywordSearchWrapperRecordings parsedResults)
    {
        ArrayList<SearchResultRecordings> resultSet = new ArrayList<SearchResultRecordings>();
        pageCount = Integer.parseInt(parsedResults.pages);

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

            // Instantiate and populate a SearchResultRecordings object
            SearchResultRecordings currentResult = new SearchResultRecordings(details, user, artist);
            // Add the object to the ArrayList to be returned to the caller
            resultSet.add(currentResult);
        }
        return resultSet;
    }

    /**
     * Helper method to gather and parse the response to a keyword-based search for sessions
     * 
     * @param parsedResults a SessionSearchResultWrapper object that has been populated
     * @return an ArrayList of SessionsSearchResult objects
     */
    private ArrayList<SearchResultSessions> populateSessionsSearchResult(KeywordSearchWrapperSessions parsedResults)
    {
        ArrayList<SearchResultSessions> resultSet = new ArrayList<SearchResultSessions>();
        pageCount = Integer.parseInt(parsedResults.pages);

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

            // Instantiate and populate a SearchResultSessions object
            SearchResultSessions currentResult = new SearchResultSessions( details, coordinates, user, venue, town, area, country);
            // Add the object to the ArrayList to be returned to the caller
            resultSet.add(currentResult);
        }
        return resultSet;
    }
    
    /**
     * Helper method to gather and parse the response to a search for trips by keyword
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
                    parsedResults.trips[i].date,
                    parsedResults.trips[i].dtstart,
                    parsedResults.trips[i].dtend);
            
            User submitter = new User(
                    parsedResults.trips[i].member.id,
                    StringCleaner.cleanString(parsedResults.trips[i].member.name),
                    parsedResults.trips[i].member.url);

            SearchResultTrips currentResult = new SearchResultTrips(details, submitter);
            resultSet.add(currentResult);
        }
        return resultSet;
    }

    /**
     * A helper method used to put the URL together to query the API
     * 
     * @param dataCategory The category of data to be queried, e.g. tunes, discussions, events etc.
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private URL composeURL(String dataCategory) throws MalformedURLException, URISyntaxException
    {
        // Parse the search terms provided by the user
        List<NameValuePair> queryParams = new ArrayList<>();
        queryParams.add(new BasicNameValuePair("q", searchTerms));

        URL requestURL;

        // If a particular page within the response from the API is specified:
        if (pageNumber > 0)
        {
            URLComposer builder = new URLComposer();
            requestURL = builder.new Builder()
                    .requestType(RequestType.SEARCH_BY_KEYWORD)
                    .path(dataCategory + "/" + "search")
                    .queryParameters(queryParams).itemsPerPage(resultsPerPage)
                    .pageNumber(pageNumber).build();
        }
        
        // If no page is specified
        else if (pageNumber == 0)
        {
            URLComposer builder = new URLComposer();
            requestURL = builder.new Builder()
                    .requestType(RequestType.SEARCH_BY_KEYWORD)
                    .path(dataCategory + "/" + "search")
                    .queryParameters(queryParams).itemsPerPage(resultsPerPage)
                    .build();
        }
        
        // If anything other than a positive integer was specified
        else
        {
            throw new IllegalArgumentException("Page number must be an integer value greater than zero");
        }

        return requestURL;
    }
}
