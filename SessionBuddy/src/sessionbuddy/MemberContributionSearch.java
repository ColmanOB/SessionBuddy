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
import sessionbuddy.wrappers.granularobjects.SetDetails;
import sessionbuddy.wrappers.granularobjects.SettingDetails;
import sessionbuddy.wrappers.granularobjects.TuneDetails;
import sessionbuddy.wrappers.granularobjects.RecordingDetails;
import sessionbuddy.wrappers.granularobjects.SessionDetails;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperDiscussions;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperEvents;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperRecordings;
import sessionbuddy.wrappers.jsonresponse.KeywordSearchWrapperSessions;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperSets;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperTunes;
import sessionbuddy.wrappers.resultsets.SearchResultSets;
import sessionbuddy.wrappers.resultsets.SearchResultTunesLatest;
import sessionbuddy.wrappers.resultsets.SearchResultEvents;
import sessionbuddy.wrappers.resultsets.SearchResultSessions;
import sessionbuddy.wrappers.resultsets.SearchResultDiscussions;
import sessionbuddy.wrappers.resultsets.SearchResultRecordings;

/**
 * Retrieves a list of member contributions in a chosen category;
 * tunes, discussions, recordings, events, sessions or sets.
 * 
 * @author Colman O'B
 * @since 2018-03-11
 */
public class MemberContributionSearch extends Search
{
    /**
     * The ID number of the user in question within thesession.org database
     */
    private int userID = 0;

    /**
     * The number of search results to be returned per page from the API
     */
    private int resultsPerPage = 0;

    /**
     * When dealing with a JSON response containing multiple pages, this
     * specifies a particular page
     */
    private int pageNumber = 0;

    /**
     * Constructor where pagination is not required,
     * i.e. only the first page of results is required.
     * 
     * @param userID The ID of the user in thesession.org database
     * @param resultsPerPage The number of results per page in the response from the API
     */
    public MemberContributionSearch(int userID, int resultsPerPage)
    {
        this.userID = userID;
        this.resultsPerPage = resultsPerPage;
    }

    /**
     * Constructor if you need to specify an individual page in the API response
     * 
     * @param userID The numeric ID of the user in question in thesession.org database
     * @param resultsPerPage Specifies how many search results should appear in each page of the JSON response from the API
     * @param pageNumber Specifies a particular page number within the JSON response
     */
    public MemberContributionSearch(int userID, int resultsPerPage, int pageNumber)
    {
        this(userID, resultsPerPage);
        this.pageNumber = pageNumber;
    }

    /**
     * Retrieves the tunes/settings added by a particular member, most recent first
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
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("tunes"));
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
    public ArrayList<SearchResultRecordings> listRecordings() throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("recordings"));
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
    public ArrayList<SearchResultSessions> listSessions() throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("sessions"));
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
    public ArrayList<SearchResultEvents> listEvents() throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("events"));
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
    public ArrayList<SearchResultDiscussions> listDiscussions() throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("discussions"));
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
     * @return an ArrayList of SearchResultSets objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per  page
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public ArrayList<SearchResultSets> listSets() throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("sets"));
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
     * @return an ArrayList of SearchResultTunesLatest objects
     * 
     * @author Colman
     * @since 2018-02-10
     */
    private ArrayList<SearchResultTunesLatest> populateTunesSearchResult(LatestWrapperTunes parsedResults)
    {
        ArrayList<SearchResultTunesLatest> resultSet = new ArrayList<SearchResultTunesLatest>();
        // Find out how many pages are in the response
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
                    parsedResults.settings[i].tune.name,
                    parsedResults.settings[i].tune.url);

            // Instantiate a TunesSearchResult object & populate it
            SearchResultTunesLatest currentResult = new SearchResultTunesLatest(details, submitter, settingDetails);
            // Add the TuneSearchResult object to the ArrayList to be returned
            resultSet.add(currentResult);
        }
        return resultSet;
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
    private ArrayList<SearchResultRecordings> populateRecordingsSearchResult(KeywordSearchWrapperRecordings parsedResults)
    {
        // Parse the raw JSON into a usable structure
        ArrayList<SearchResultRecordings> resultSet = new ArrayList<SearchResultRecordings>();
        // Find out how many pages are in the response
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

            // Instantiate a RecordingsSearchResult object & populate it
            SearchResultRecordings currentResult = new SearchResultRecordings(details, submitter, artist);
            // Add the RecordingsSearchResult object to the ArrayList to be returned
            resultSet.add(currentResult);
        }
        return resultSet;
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
    private ArrayList<SearchResultSessions> populateSessionsSearchResult(KeywordSearchWrapperSessions parsedResults)
    {
        // Parse the raw JSON into a usable structure
        ArrayList<SearchResultSessions> resultSet = new ArrayList<SearchResultSessions>();
        // Find out how many pages are in the response
        pageCount = Integer.parseInt(parsedResults.pages);

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

            // Instantiate a SessionsSearchResult object & populate it
            SearchResultSessions currentResult = new SearchResultSessions(details, coordinates, submitter, venue, town, area, country);
            // Add the SessionsSearchResult object to the ArrayList to be returned 
            resultSet.add(currentResult);
        }
        return resultSet;
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
    private ArrayList<SearchResultEvents> populateEventsSearchResult(KeywordSearchWrapperEvents parsedResults)
    {
        // Parse the JSON into a usable structure
        ArrayList<SearchResultEvents> resultSet = new ArrayList<SearchResultEvents>();
        // Find out how many pages are in the response
        pageCount = Integer.parseInt(parsedResults.pages);

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

            // Instantiate a EventsSearchResult object & populate it
            SearchResultEvents currentResult = new SearchResultEvents(details, submitter, schedule, coordinates, venue, town, area, country);
            // Add the EventsSearchResult object to the ArrayList to be returned
            resultSet.add(currentResult);
        }
        return resultSet;
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
    private ArrayList<SearchResultDiscussions> populateDiscussionsSearchResult(KeywordSearchWrapperDiscussions parsedResults)
    {
        // Parse the JSON into a usable structure
        ArrayList<SearchResultDiscussions> resultSet = new ArrayList<SearchResultDiscussions>();
        // Find out how many pages are in the response
        pageCount = Integer.parseInt(parsedResults.pages);

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

            // Instantiate a DiscussionsSearchResult object & populate it
            SearchResultDiscussions currentResult = new SearchResultDiscussions(details, user);
            // Add the DiscussionsSearchResult object to the ArrayList to be returned
            resultSet.add(currentResult);
        }
        return resultSet;
    }

    /**
     * Helper method to gather and parse the response to a search for user-added
     * sets of tunes
     * 
     * @param parsedResults a LatestWrapperSets object that has already been created and populated
     * @return an ArrayList of SearchResultSets objects
     * 
     * @author Colman
     * @since 2018-02-17
     */
    private ArrayList<SearchResultSets> populateSetSearchResult(LatestWrapperSets parsedResults)
    {
        ArrayList<SearchResultSets> resultSet = new ArrayList<SearchResultSets>();
        // Find out how many pages are in the response
        pageCount = Integer.parseInt(parsedResults.pages);

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

            // Instantiate a SearchResultSets object & populate it
            SearchResultSets currentResult = new SearchResultSets(details, submitter);
            // Add the SearchResultSets object to the ArrayList to be returned
            resultSet.add(currentResult);
        }
        return resultSet;
    }

    /**
     * A helper method used to put the URL together to query the API
     * 
     * @param dataCategory The category of data to be queried, e.g. tunes, discussions, events etc.
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a  MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private URL composeURL(String dataCategory) throws MalformedURLException, URISyntaxException
    {
        URL requestURL;

        // If a particular page within the response from the API is specified:
        if (pageNumber > 0)
        {
            URLComposer builder = new URLComposer();

            requestURL = builder.new Builder()
                    .requestType(RequestType.SEARCH_MEMBER_CONTRIBUTIONS)
                    .path("members" + "/" + userID + "/" + dataCategory)
                    .itemsPerPage(resultsPerPage).pageNumber(pageNumber)
                    .build();
        }
        // If no page is specified
        else if (pageNumber == 0)
        {
            URLComposer builder = new URLComposer();

            requestURL = builder.new Builder()
                    .requestType(RequestType.SEARCH_MEMBER_CONTRIBUTIONS)
                    .path("members" + "/" + userID + "/" + dataCategory)
                    .itemsPerPage(resultsPerPage).build();
        }
        // If anything other than a positive integer was specified as the page
        else
        {
            throw new IllegalArgumentException("Page number must be an integer value greater than zero");
        }
        return requestURL;
    }
}
