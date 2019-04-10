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
import sessionbuddy.wrappers.granularobjects.SetDetails;
import sessionbuddy.wrappers.granularobjects.SettingDetails;
import sessionbuddy.wrappers.granularobjects.TuneDetails;
import sessionbuddy.wrappers.granularobjects.RecordingDetails;
import sessionbuddy.wrappers.granularobjects.SessionDetails;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;
import sessionbuddy.wrappers.individualresultitems.Discussion;
import sessionbuddy.wrappers.individualresultitems.Event;
import sessionbuddy.wrappers.individualresultitems.Recording;
import sessionbuddy.wrappers.individualresultitems.Session;
import sessionbuddy.wrappers.individualresultitems.Set;
import sessionbuddy.wrappers.individualresultitems.TuneLatest;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperDiscussions;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperEvents;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperRecordings;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperSessions;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperSets;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperTunes;
import sessionbuddy.wrappers.responsemetadata.ResponseHeaders;
import sessionbuddy.wrappers.resultsets.RecentResultDiscussions;
import sessionbuddy.wrappers.resultsets.RecentResultEvents;
import sessionbuddy.wrappers.resultsets.RecentResultRecordings;
import sessionbuddy.wrappers.resultsets.RecentResultSessions;
import sessionbuddy.wrappers.resultsets.RecentResultSets;
import sessionbuddy.wrappers.resultsets.RecentResultTunes;

/**
 * Retrieves a list of member contributions in a chosen category;
 * tunes, discussions, recordings, events, sessions or sets.
 * 
 * @author Colman O'B
 * @since 2018-03-11
 */
public class MemberContribution
{
    /**
     * Retrieves the tunes/settings added by a particular member, most recent first
     * 
     * @return an ArrayList of TuneLatest objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public static RecentResultTunes listTunes(int userID, int resultsPerPage) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.tunes;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, userID, resultsPerPage));
            // Parse the JSON response
            LatestWrapperTunes parsedResults = JsonParser.parseResponse(response, LatestWrapperTunes.class);
            // Return the data retrieved from the API
            return populateTunesSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static RecentResultTunes listTunes(int userID, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.tunes;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, userID, resultsPerPage, pageNumber));
            // Parse the JSON response
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
     * Retrieves a list of recordings added by a particular user, most recent results first
     * 
     * @return an ArrayList of SearchResultRecordings objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IOException if a problem was encountered setting up the HTTP connection,or reading data from it
     * @throws URISyntaxException if the UrlBuilder class throws URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */

    public static RecentResultRecordings listRecordings(int userID, int resultsPerPage) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.recordings;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, userID, resultsPerPage));
            // Parse the JSON response
            KeywordSearchWrapperRecordings parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperRecordings.class);
            // Return the data retrieved from the API
            return populateRecordingsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static RecentResultRecordings listRecordings(int userID, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.recordings;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, userID, resultsPerPage, pageNumber));
            // Parse the JSON response
            KeywordSearchWrapperRecordings parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperRecordings.class);
            // Return the data retrieved from the API
            return populateRecordingsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    /**
     * Retrieves a list of sessions added by a particular user, most recent results first
     * 
     * @return an ArrayList of SearchResultSessions objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */   
    public static RecentResultSessions listSessions(int userID, int resultsPerPage) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.sessions;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, userID, resultsPerPage));
            // Parse the JSON response
            KeywordSearchWrapperSessions parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperSessions.class);
            // Return the data retrieved from the API
            return populateSessionsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static RecentResultSessions listSessions(int userID, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.sessions;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, userID, resultsPerPage, pageNumber));
            // Parse the JSON response
            KeywordSearchWrapperSessions parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperSessions.class);
            // Return the data retrieved from the API
            return populateSessionsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static RecentResultEvents listEvents(int userID, int resultsPerPage) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.events;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, userID, resultsPerPage));
            // Parse the JSON response
            KeywordSearchWrapperEvents parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperEvents.class);
            // Return the data retreived from the API
            return populateEventsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * Retrieves a list of events added by a particular member on thesession.org
     * 
     * @return an ArrayList of SearchResultEvents objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    
    
    public static RecentResultEvents listEvents(int userID, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.events;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, userID, resultsPerPage, pageNumber));
            // Parse the JSON response
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
     * Retrieves a list of discussions submitted by a particular user
     * 
     * @return an ArrayList of SearchResultsDiscussion objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results perpage
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public static RecentResultDiscussions listDiscussions(int userID, int resultsPerPage) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.discussions;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, userID, resultsPerPage));
            // Parse the JSON response
            KeywordSearchWrapperDiscussions parsedResults = JsonParser.parseResponse(response, KeywordSearchWrapperDiscussions.class);
            // Return the data retrieved from the API
            return populateDiscussionsSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static RecentResultDiscussions listDiscussions(int userID, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.discussions;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, userID, resultsPerPage, pageNumber));
            // Parse the JSON response
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
     * Retrieves a list of sets of tunes put together by the user
     * 
     * @return an ArrayList of Set objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per  page
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public static RecentResultSets listSets(int userID, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.sets;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, userID, resultsPerPage, pageNumber));
            // Parse the returned JSON response
            LatestWrapperSets parsedResults = JsonParser.parseResponse(response, LatestWrapperSets.class);
            // Return the data retrieved from the API
            return populateSetSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static RecentResultSets listSets(int userID, int resultsPerPage) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.sets;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, userID, resultsPerPage));
            // Parse the returned JSON response
            LatestWrapperSets parsedResults = JsonParser.parseResponse(response, LatestWrapperSets.class);
            // Return the data retrieved from the API
            return populateSetSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * Helper method to gather and parse the response to a keyword search for a
     * tune
     * 
     * @param parsedResults a LatestWrapperTunes object that has already been created and populated
     * @return an ArrayList of TuneLatest objects
     * 
     * @author Colman
     * @since 2018-02-10
     */
    private static RecentResultTunes populateTunesSearchResult(LatestWrapperTunes parsedResults)
    {
        // Capture the metadata for the search results
        ResponseHeaders headers = new ResponseHeaders(parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
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
                    parsedResults.settings[i].tune.name,
                    parsedResults.settings[i].tune.url);

            // Put the individual search result into a wrapper object, and add to the larger result set
            TuneLatest currentResult = new TuneLatest(details, submitter, settingDetails);
            resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        RecentResultTunes searchResult = new RecentResultTunes(headers, resultSet);
        return searchResult;
    }

    /**
     * Helper method to gather and parse the response to a search for recordings added by a particular user
     * 
     * @param parsedResults an existing KeywordSearchWrapperSessions object that has been populated
     * @return an ArrayList of SearchResultSessions objects
     * 
     * @author Colman
     * @since 2018-02-10
     */
    private static RecentResultRecordings populateRecordingsSearchResult(KeywordSearchWrapperRecordings parsedResults)
    {
        // Capture the metadata for the search results
        ResponseHeaders headers = new ResponseHeaders(parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
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

            // Put the individual search result into a wrapper object, and add to the larger result set
            Recording currentResult = new Recording(details, submitter, artist);
            resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        RecentResultRecordings searchResult = new RecentResultRecordings(headers, resultSet);
        return searchResult;
    }

    /**
     * Helper method to gather and parse the response to a search for sessions added by a particular user
     * 
     * @param parsedResults an existing KeywordSearchWrapperSessions object that has been populated
     * @return an ArrayList of SearchResultSessions objects
     * 
     * @author Colman
     * @since 2018-02-18
     */
    private static RecentResultSessions populateSessionsSearchResult(KeywordSearchWrapperSessions parsedResults)
    {
        // Capture the metadata for the search results
        ResponseHeaders headers = new ResponseHeaders(parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // This will hold the list of individual items in the result set
        ArrayList<Session> resultSet = new ArrayList<Session>();

        // Loop as many times as the count of recordings in the result set:
        for (int i = 0; i < (parsedResults.sessions.length); i++)
        {
            // Extract the required elements from each individual search result
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
            
            // Put the individual search result into a wrapper object, and add to the larger result set
            Session currentResult = new Session(details, coordinates, submitter, venue, town, area, country);
            resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        RecentResultSessions searchResult = new RecentResultSessions(headers, resultSet);
        return searchResult;
    }

    /**
     * Helper method to gather and parse the response to a search for events added by a particular user
     * 
     * @param parsedResults an existing KeywordSearchWrapperEvents object that has been populated
     * @return an ArrayList of SearchResultEvents objects
     * 
     * @author Colman
     * @since 2018-02-18
     */
    private static RecentResultEvents populateEventsSearchResult(KeywordSearchWrapperEvents parsedResults)
    {
        // Capture the metadata for the search results
        ResponseHeaders headers = new ResponseHeaders(parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // This will hold the list of individual items in the result set
        ArrayList<Event> resultSet = new ArrayList<Event>();

        // Loop as many times as the count of events in the result set:
        for (int i = 0; i < (parsedResults.events.length); i++)
        {
            // Extract the required elements from each individual search result
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
        RecentResultEvents searchResult = new RecentResultEvents(headers, resultSet);
        return searchResult;
    }

    /**
     * Helper method to gather and parse the response to a search for
     * discussions submitted by a particular user
     * 
     * @param parsedResults an existing populated KeywordSearchWrapperDiscussions object
     * @return an ArrayList of SearchResultDiscussions objects
     * 
     * @author Colman
     * @since 2018-02-23
     */
    private static RecentResultDiscussions populateDiscussionsSearchResult(KeywordSearchWrapperDiscussions parsedResults)
    {
        // Capture the metadata for the search results
        ResponseHeaders headers = new ResponseHeaders(parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // This will hold the list of individual items in the result set
        ArrayList<Discussion> resultSet = new ArrayList<Discussion>();

        // Loop as many times as the count of recordings in the result set:
        for (int i = 0; i < (parsedResults.discussions.length); i++)
        {
            // Extract the elements from each individual search result
            DiscussionDetails details = new DiscussionDetails(
                    parsedResults.discussions[i].id,
                    StringCleaner.cleanString(parsedResults.discussions[i].name),
                    parsedResults.discussions[i].url,
                    parsedResults.discussions[i].date);
            
            User user = new User(
                    parsedResults.discussions[i].member.id,
                    StringCleaner.cleanString(parsedResults.discussions[i].member.name),
                    parsedResults.discussions[i].member.url);
            
            // Put the individual search result into a wrapper object, and add to the larger result set
            Discussion currentResult = new Discussion(details, user);
            resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        RecentResultDiscussions searchResult = new RecentResultDiscussions(headers, resultSet);
        return searchResult;
    }

    /**
     * Helper method to gather and parse the response to a search for user-added
     * sets of tunes
     * 
     * @param parsedResults a LatestWrapperSets object that has already been created and populated
     * @return an ArrayList of Set objects
     * 
     * @author Colman
     * @since 2018-02-17
     */
    private static RecentResultSets populateSetSearchResult(LatestWrapperSets parsedResults)
    {
  /*      ArrayList<Set> resultSet = new ArrayList<Set>();
        // Find out how many pages are in the response
        pageCount = Integer.parseInt(parsedResults.pages); */

        // Capture the metadata for the search results
        ResponseHeaders headers = new ResponseHeaders(parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // This will hold the list of individual items in the result set
        ArrayList<Set> resultSet = new ArrayList<Set>();
        
        // Loop as many times as the count of tunes in the result set:
        for (int i = 0; i < parsedResults.sets.length; i++)
        {
            // Extract the elements from each individual search result
            SetDetails details = new SetDetails(
                    parsedResults.sets[i].id,
                    StringCleaner.cleanString(parsedResults.sets[i].name),
                    parsedResults.sets[i].url, parsedResults.sets[i].date);
            
            User submitter = new User(
                    parsedResults.sets[i].member.id,
                    StringCleaner.cleanString(parsedResults.sets[i].member.name),
                    parsedResults.sets[i].member.url);
            
            // Put the individual search result into a wrapper object, and add to the larger result set
            Set currentResult = new Set(details, submitter);
            resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        RecentResultSets searchResult = new RecentResultSets(headers, resultSet);
        return searchResult;
    }

    /**
     * A helper method used to put the URL together to query the API
     * 
     * @param dataCategory The category of data to be queried, e.g. tunes, discussions, events etc.
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a  MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private static URL composeURL(DataCategory dataCategory, int userID, int resultsPerPage) throws MalformedURLException, URISyntaxException
    {
        URL requestURL;

        URLComposer builder = new URLComposer();

        requestURL = builder.new Builder()
                .requestType(RequestType.SEARCH_MEMBER_CONTRIBUTIONS)
                .path("members" + "/" + userID + "/" + dataCategory)
                .itemsPerPage(resultsPerPage).build();

        return requestURL;
    }
    
    private static URL composeURL(DataCategory dataCategory, int userID, int resultsPerPage, int pageNumber) throws MalformedURLException, URISyntaxException
    {
        URL requestURL;

        URLComposer builder = new URLComposer();
         
        if (pageNumber > 0)
        {
            requestURL = builder.new Builder()
                    .requestType(RequestType.SEARCH_MEMBER_CONTRIBUTIONS)
                    .path("members" + "/" + userID + "/" + dataCategory)
                    .itemsPerPage(resultsPerPage).pageNumber(pageNumber)
                    .build();
        }

        // If anything other than a positive integer was specified as the page
        else
        {
            throw new IllegalArgumentException("Page number must be an integer value greater than zero");
        }
        return requestURL;
    }
}
