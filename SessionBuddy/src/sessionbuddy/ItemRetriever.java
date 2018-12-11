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
import sessionbuddy.wrappers.granularobjects.Comment;
import sessionbuddy.wrappers.granularobjects.Coordinates;
import sessionbuddy.wrappers.granularobjects.Country;
import sessionbuddy.wrappers.granularobjects.DiscussionDetails;
import sessionbuddy.wrappers.granularobjects.EventDetails;
import sessionbuddy.wrappers.granularobjects.Schedule;
import sessionbuddy.wrappers.granularobjects.RecordingDetails;
import sessionbuddy.wrappers.granularobjects.SessionDetails;
import sessionbuddy.wrappers.granularobjects.SettingDetails;
import sessionbuddy.wrappers.granularobjects.SettingDetailsWithAbc;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.TrackListing;
import sessionbuddy.wrappers.granularobjects.TripDetails;
import sessionbuddy.wrappers.granularobjects.TuneDetails;
import sessionbuddy.wrappers.granularobjects.TuneDetailsWithDate;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;
import sessionbuddy.wrappers.jsonresponse.ItemWrapperDiscussion;
import sessionbuddy.wrappers.jsonresponse.ItemWrapperEvent;
import sessionbuddy.wrappers.jsonresponse.ItemWrapperRecording;
import sessionbuddy.wrappers.jsonresponse.ItemWrapperSession;
import sessionbuddy.wrappers.jsonresponse.ItemWrapperTrip;
import sessionbuddy.wrappers.jsonresponse.ItemWrapperTune;
import sessionbuddy.wrappers.resultsets.ItemResultDiscussion;
import sessionbuddy.wrappers.resultsets.ItemResultEvent;
import sessionbuddy.wrappers.resultsets.ItemResultRecording;
import sessionbuddy.wrappers.resultsets.ItemResultSession;
import sessionbuddy.wrappers.resultsets.ItemResultTrip;
import sessionbuddy.wrappers.resultsets.ItemResultTune;

/**
 * Retrieves the data for a single item from the session.org. 
 * The item may be a tune, discussion, recording, session or event.
 * 
 * @author Colman
 * @since 2018-04-01
 */
public class ItemRetriever
{
    /**
     * The item's ID in thesession.org database
     */
    private int itemID;

    /**
     * Constructor
     * 
     * @param itemID The item's ID in thesession.org database
     */
    public ItemRetriever(int itemID)
    {
        this.itemID = itemID;
    }

    /**
     * Gets the details of a recording using its ID in thesession.org. 
     * Details include track listing, the tunes on each track, 
     * the comments on the recording's page on thesession.org, and more.
     * 
     * @return an ItemResultRecording object with the recording's details
     * @throws IOException if there was a problem setting up the HTTP connection or reading from it
     * @throws URISyntaxException if the UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public ItemResultRecording getRecording() throws IOException, URISyntaxException
    {
        try
        {
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("recordings"));
            // Parse the returned JSON into a wrapper
            ItemWrapperRecording parsedResults = JsonParser.parseResponse(response, ItemWrapperRecording.class);
            // Return the data retrieved from the API
            return populateRecordingResult(parsedResults);
        } 
        catch (IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * Gets the details of a discussion using its ID in thesession.org. 
     * Details include metadata relating to the discussion, and the discussion's comments.
     * 
     * @return an ItemResultDiscussion object with the discussion's details
     * @throws IOException if there was a problem setting up the HTTP connection or reading from it
     * @throws URISyntaxException if the UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public ItemResultDiscussion getDiscussion() throws IOException, URISyntaxException
    {
        try
        {
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("discussions"));
            // Parse the returned JSON into a wrapper
            ItemWrapperDiscussion parsedResults = JsonParser.parseResponse(response, ItemWrapperDiscussion.class);
            // Return the data retrieved from the API
            return populateDiscussionResult(parsedResults);
        } 
        catch (IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * Gets the details of a tune using its numeric ID in thesession.org.
     * Details include metadata about the tune, alternative settings, and comments.
     * 
     * @return an ItemResultTune object with the tune's details
     * @throws IOException if there is a problem with the HTTPS request to the API
     * @throws URISyntaxException if the UrlBuilder class throws a URISyntaxException
     */
    public ItemResultTune getTune() throws IOException, URISyntaxException
    {
        try
        {
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("tunes"));
            // Parse the returned JSON into a wrapper
            ItemWrapperTune parsedResults = JsonParser.parseResponse(response, ItemWrapperTune.class);
            // Return the data retrieved from the API
            return populateTuneResult(parsedResults);
        } 
        catch (IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * Gets the details of a session using its ID in thesession.org. 
     * Details include metadata about the session, and comments.
     * 
     * @return an ItemResultSession object with the session's details
     * @throws IOException if there is a problem with the HTTPS request to the API
     * @throws URISyntaxException if the UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public ItemResultSession getSession() throws IOException, URISyntaxException
    {
        try
        {
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("sessions"));
            // Parse the returned JSON into a wrapper
            ItemWrapperSession parsedResults = JsonParser.parseResponse(response, ItemWrapperSession.class);
            // Return the data retrieved from the API
            return populateSessionResult(parsedResults);
        } 
        catch (IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * Gets the details of an event using its ID in thesession.org. 
     * Details include metadata about the event, and comments.
     * 
     * @return an ItemResultRecording object with the event's details
     * @throws IOException if there is a problem with the HTTPS request to the API
     * @throws URISyntaxException if the UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public ItemResultEvent getEvent() throws IOException, URISyntaxException
    {
        try
        {
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("events"));
            // Parse the returned JSON into a wrapper
            ItemWrapperEvent parsedResults = JsonParser.parseResponse(response, ItemWrapperEvent.class);
            // Return the data retrieved from the API
            return populateEventResult(parsedResults);
        } 
        catch (IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    /**
     * Gets the details of a trip using its ID in thesession.org. 
     * Details include metadata about the trip, and comments.
     * 
     * @return an ItemResultRecording object with the trip's details
     * @throws IOException if there is a problem with the HTTPS request to the API
     * @throws URISyntaxException if the UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-12-11
     */
    public ItemResultTrip getTrip() throws IOException, URISyntaxException
    {
        try
        {
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL("trips"));
            // Parse the returned JSON into a wrapper
            ItemWrapperTrip parsedResults = JsonParser.parseResponse(response, ItemWrapperTrip.class);
            // Return the data retrieved from the API
            return populateTripResult(parsedResults);
        } 
        catch (IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * A helper method to parse a JSON list of recording details into a POJO.
     * 
     * @param parsedResults An already-populated ItemWrapperRecording object
     * @return A populated ItemResultRecording object
     */
    private ItemResultRecording populateRecordingResult(ItemWrapperRecording parsedResults)
    {
        // StringCleaner.cleanString() decodes the &039; etc. XML entities from the JSON response
        RecordingDetails recordingDetails = new RecordingDetails(
                parsedResults.id, parsedResults.url,
                StringCleaner.cleanString(parsedResults.name),
                parsedResults.date);
        
        // Get the details of the member who originally submitted the recording
        User member = new User(
                parsedResults.member.id,
                StringCleaner.cleanString(parsedResults.member.name),
                parsedResults.member.url);
        
        // Get the details of the recording artist(s)
        Artist artist = new Artist(
                parsedResults.artist.id,
                StringCleaner.cleanString(parsedResults.artist.name),
                parsedResults.artist.url);
        
        // Set up the structure needed to hold the track listing
        ArrayList<TrackListing> tracks = new ArrayList<TrackListing>();

        // Populate the track listing
        for (int i = 0; i < (parsedResults.tracks.length); i++)
        {
            // For each individual track, create an ArrayList of TuneRecord objects
            ArrayList<TuneDetails> tunesOnTrack = new ArrayList<TuneDetails>();
            
            // Populate the ArrayList of TuneRecord objects
            for (int j = 0; j < (parsedResults.tracks[i].tunes.length); j++)
            {
                TuneDetails currentTune = new TuneDetails(
                        parsedResults.tracks[i].tunes[j].id,
                        StringCleaner.cleanString(parsedResults.tracks[i].tunes[j].name),
                        parsedResults.tracks[i].tunes[j].url);
                tunesOnTrack.add(currentTune);
            }
            
            // Add the current track to the track listing
            TrackListing currentTrack = new TrackListing(tunesOnTrack);
            tracks.add(currentTrack);
        }

        // A structure to hold each comment on the recording
        ArrayList<Comment> comments = new ArrayList<Comment>();

        // Iterate through each comment in the JSON to populate the ArrayList
        for (int i = 0; i < (parsedResults.comments.length); i++)
        {
            // Get the details of the person who submitted the comment
            User commentSubmitter = new User(
                    parsedResults.comments[i].member.id,
                    parsedResults.comments[i].member.name,
                    parsedResults.comments[i].member.url);
            
            // Populate the Comment object with all relevant details
            Comment currentComment = new Comment(
                    Integer.parseInt(parsedResults.comments[i].id),
                    parsedResults.comments[i].url,
                    StringCleaner.cleanString(parsedResults.comments[i].subject),
                    StringCleaner.cleanString(parsedResults.comments[i].content),
                    commentSubmitter, parsedResults.comments[i].date);
            
            // Add the current comment to the ArrayList
            comments.add(currentComment);
        }

        // Instantiate a RecordingByIDResult object & populate it with the details captured above
        ItemResultRecording finalResult = new ItemResultRecording(recordingDetails, member, artist, tracks, comments);
        // Return the set of results that has been collected
        return finalResult;
    }

    /**
     * A helper method to parse a JSON list of discussion details into a POJO.
     * 
     * @param parsedResults An already-populated ItemWrapperDiscussion object
     * @return A populated ItemResultDiscussion object
     */
    private ItemResultDiscussion populateDiscussionResult(ItemWrapperDiscussion parsedResults)
    {
        // Extract each element from the discussion in the JSON response
        DiscussionDetails discussionDetails = new DiscussionDetails(
                parsedResults.id, 
                parsedResults.name, 
                parsedResults.url,
                parsedResults.date);

        // Get the details of the member who originally submitted the discussion
        User member = new User(
                parsedResults.member.id,
                StringCleaner.cleanString(parsedResults.member.name),
                parsedResults.member.url);

        // A structure to hold each comment within the discussion
        ArrayList<Comment> comments = new ArrayList<Comment>();

        // Iterate through each comment in the JSON response
        for (int i = 0; i < (parsedResults.comments.length); i++)
        {
            // Get the details of the user who submitted the comment
            User commentSubmitter = new User(
                    parsedResults.comments[i].member.id,
                    parsedResults.comments[i].member.name,
                    parsedResults.comments[i].member.url);

            // Populate the Comment object with all relevant information
            Comment currentComment = new Comment(
                    Integer.parseInt(parsedResults.comments[i].id),
                    parsedResults.comments[i].url,
                    StringCleaner.cleanString(parsedResults.comments[i].subject),
                    StringCleaner.cleanString(parsedResults.comments[i].content),
                    commentSubmitter, parsedResults.comments[i].date);

            // Add the current comment to the ArrayList
            comments.add(currentComment);
        }

        // Instantiate and populate an ItemResultDiscussion object
        ItemResultDiscussion finalResult = new ItemResultDiscussion(discussionDetails, member, comments);
        // Return the set of results that has been collected
        return finalResult;
    }

    /**
     * A helper method to parse a JSON list of tune details into a POJO.
     * 
     * @param parsedResults An already-populated ItemWrapperTune object
     * @return A populated ItemResultTune object
     */
    private ItemResultTune populateTuneResult(ItemWrapperTune parsedResults)
    {
        // Get the basic set of tune details
        TuneDetails tuneDetails = new TuneDetails(
                parsedResults.id,
                StringCleaner.cleanString(parsedResults.name),
                parsedResults.url);

        // Get the tune type and date of submission
        TuneDetailsWithDate tuneDetailsWithDate = new TuneDetailsWithDate(
                tuneDetails, 
                parsedResults.type, 
                parsedResults.date);

        // Get the details of the member who originally submitted the tune
        User member = new User(
                parsedResults.member.id,
                StringCleaner.cleanString(parsedResults.member.name),
                parsedResults.member.url);

        // Get the number of user tunebooks to which this tune has been added
        int tunebooks = (parsedResults.tunebooks);

        // Get the number of recordings containing a tune by this name
        int recordings = (parsedResults.recordings);

        // A structure to hold the list of alternative titles for the tune
        ArrayList<String> aliases = new ArrayList<String>();

        // Iterate through each of the alternative titles in the JSON
        for (int i = 0; i < parsedResults.aliases.length; i++)
        {
            aliases.add(parsedResults.aliases[i]);
        }

        // A structure to hold each individual setting of the tune
        ArrayList<SettingDetailsWithAbc> settings = new ArrayList<SettingDetailsWithAbc>();

        // Populate the list of tune settings
        for (int i = 0; i < (parsedResults.settings.length); i++)
        {
            // Populate the details of the user who submitted the setting
            User settingSubmitter = new User(
                    parsedResults.settings[i].member.id,
                    parsedResults.settings[i].member.name,
                    parsedResults.settings[i].member.url);

            // Populate the basic details of the setting
            SettingDetails currentSettingDetails = new SettingDetails(
                    parsedResults.settings[i].id, parsedResults.settings[i].url,
                    parsedResults.settings[i].key,
                    parsedResults.settings[i].date);

            // Put together the setting details, the user details, and the abc notation 
            SettingDetailsWithAbc currentSetting = new SettingDetailsWithAbc(
                    currentSettingDetails, parsedResults.settings[i].abc,
                    settingSubmitter);

            settings.add(currentSetting);
        }

        // A structure to hold the comments on the tune
        ArrayList<Comment> comments = new ArrayList<Comment>();

        // Iterate through each comment
        for (int i = 0; i < (parsedResults.comments.length); i++)
        {
            // Populate the User details
            User commentSubmitter = new User(
                    parsedResults.comments[i].member.id,
                    parsedResults.comments[i].member.name,
                    parsedResults.comments[i].member.url);

            // Populate the comment details
            Comment currentComment = new Comment(
                    Integer.parseInt(parsedResults.comments[i].id),
                    parsedResults.comments[i].url,
                    StringCleaner.cleanString(parsedResults.comments[i].subject),
                    StringCleaner.cleanString(parsedResults.comments[i].content),
                    commentSubmitter, parsedResults.comments[i].date);

            comments.add(currentComment);
        }

        // Instantiate and populate an ItemResultTune object TuneByIDResult object
        ItemResultTune finalResult = new ItemResultTune(tuneDetailsWithDate, member, tunebooks, recordings, aliases, settings, comments);
        // Return the set of results that has been collected
        return finalResult;
    }

    /**
     * A helper method to parse a JSON list of session details into a POJO.
     * 
     * @param parsedResults An already-populated ItemWrapperSession object
     * @return A populated ItemResultSession object
     */
    private ItemResultSession populateSessionResult(ItemWrapperSession parsedResults)
    {
        // Extract each element from the session entry in the JSON response
        SessionDetails sessionDetails = new SessionDetails(
                parsedResults.id,
                parsedResults.url, 
                parsedResults.date);
        
        Coordinates coordinates = new Coordinates(
                parsedResults.latitude,
                parsedResults.longitude);
        
        User member = new User(
                parsedResults.member.id,
                StringCleaner.cleanString(parsedResults.member.name),
                parsedResults.member.url);
        
        Venue venue = new Venue(
                parsedResults.venue.id,
                StringCleaner.cleanString(parsedResults.venue.name),
                parsedResults.venue.telephone, parsedResults.venue.email,
                parsedResults.venue.web);
        
        Town town = new Town(
                parsedResults.town.id,
                StringCleaner.cleanString(parsedResults.town.name));
        
        Area area = new Area(
                parsedResults.area.id,
                StringCleaner.cleanString(parsedResults.area.name));
        
        Country country = new Country(
                parsedResults.country.id,
                StringCleaner.cleanString(parsedResults.country.name));

        // A structure to store the schedule, i.e. the days when the session happens
        ArrayList<String> schedule = new ArrayList<String>();

        // Iterate through each of the schedule days
        for (int i = 0; i < parsedResults.schedule.length; i++)
        {
            schedule.add(parsedResults.schedule[i]);
        }

        // A structure to hold each comment on the session
        ArrayList<Comment> comments = new ArrayList<Comment>();

        // Iterate through each comment
        for (int i = 0; i < (parsedResults.comments.length); i++)
        {
            // Populate the User details
            User commentSubmitter = new User(
                    parsedResults.comments[i].member.id,
                    StringCleaner.cleanString(parsedResults.comments[i].member.name),
                    parsedResults.comments[i].member.url);

            // Populate the Comment object with all relevant information
            Comment currentComment = new Comment(
                    Integer.parseInt(parsedResults.comments[i].id),
                    parsedResults.comments[i].url,
                    StringCleaner.cleanString(parsedResults.comments[i].subject),
                    StringCleaner.cleanString(parsedResults.comments[i].content),
                    commentSubmitter, parsedResults.comments[i].date);

            comments.add(currentComment);
        }

        // Populate an ItemResultSession object
        ItemResultSession finalResult = new ItemResultSession(sessionDetails, coordinates, member, venue, town, area, country, schedule, comments);
        // Return the set of results that has been collected
        return finalResult;
    }

    /**
     * A helper method to parse a JSON list of event details into a POJO.
     * 
     * @param parsedResults An already-populated ItemWrapperEvent object
     * @return A populated ItemResultEvent object
     */
    private ItemResultEvent populateEventResult(ItemWrapperEvent parsedResults)
    {
        // Extract each element from the event entry in the JSON response
        EventDetails eventDetails = new EventDetails(
                parsedResults.id,
                StringCleaner.cleanString(parsedResults.name),
                parsedResults.url, parsedResults.date);
        
        User member = new User(
                parsedResults.member.id,
                StringCleaner.cleanString(parsedResults.member.name),
                parsedResults.member.url);
        
        Schedule schedule = new Schedule(
                parsedResults.dtstart,
                parsedResults.dtend);
        
        Coordinates coordinates = new Coordinates(
                parsedResults.latitude,
                parsedResults.longitude);
        
        Venue venue = new Venue(
                parsedResults.venue.id,
                StringCleaner.cleanString(parsedResults.venue.name),
                parsedResults.venue.telephone, 
                parsedResults.venue.email,
                parsedResults.venue.web);
        
        Town town = new Town(
                parsedResults.town.id,
                StringCleaner.cleanString(parsedResults.town.name));
        
        Area area = new Area(
                parsedResults.area.id,
                StringCleaner.cleanString(parsedResults.area.name));
        
        Country country = new Country(
                parsedResults.country.id,
                StringCleaner.cleanString(parsedResults.country.name));

        // A structure to hold each individual comment on the event
        ArrayList<Comment> comments = new ArrayList<Comment>();

        // Iterate through the list of comments
        for (int i = 0; i < (parsedResults.comments.length); i++)
        {
            // Populate details of the user who submitted the comment
            User commentSubmitter = new User(
                    parsedResults.comments[i].member.id,
                    StringCleaner.cleanString(parsedResults.comments[i].member.name),
                    parsedResults.comments[i].member.url);

            // Populate all of the comment details, including the user details
            Comment currentComment = new Comment(
                    Integer.parseInt(parsedResults.comments[i].id),
                    parsedResults.comments[i].url,
                    StringCleaner.cleanString(parsedResults.comments[i].subject),
                    StringCleaner.cleanString(parsedResults.comments[i].content),
                    commentSubmitter, 
                    parsedResults.comments[i].date);

            comments.add(currentComment);
        }

        // Instantiate and populate an ItemResultEvent object
        ItemResultEvent finalResult = new ItemResultEvent(eventDetails, member, schedule, coordinates, venue, town, area, country, comments);
        // Return the set of results that has been collected
        return finalResult;
    }
    
    /**
     * A helper method to parse a JSON list of trip details into a POJO.
     * 
     * @param parsedResults An already-populated ItemWrapperEvent object
     * @return A populated ItemResultEvent object
     */
    private ItemResultTrip populateTripResult(ItemWrapperTrip parsedResults)
    {
        // Extract each element from the event entry in the JSON response
        TripDetails tripDetails = new TripDetails(
                parsedResults.id,
                StringCleaner.cleanString(parsedResults.name),
                parsedResults.url, 
                parsedResults.date);
        
        User member = new User(
                parsedResults.member.id,
                StringCleaner.cleanString(parsedResults.member.name),
                parsedResults.member.url);
        
        Schedule tripSchedule = new Schedule(
                parsedResults.dtstart,
                parsedResults.dtend);

        Coordinates coordinates = new Coordinates(
                parsedResults.latitude,
                parsedResults.longitude);

        // A structure to hold each individual comment on the event
        ArrayList<Comment> comments = new ArrayList<Comment>();

        // Iterate through the list of comments
        for (int i = 0; i < (parsedResults.comments.length); i++)
        {
            // Populate details of the user who submitted the comment
            User commentSubmitter = new User(
                    parsedResults.comments[i].member.id,
                    StringCleaner.cleanString(parsedResults.comments[i].member.name),
                    parsedResults.comments[i].member.url);

            // Populate all of the comment details, including the user details
            Comment currentComment = new Comment(
                    Integer.parseInt(parsedResults.comments[i].id),
                    parsedResults.comments[i].url,
                    StringCleaner.cleanString(parsedResults.comments[i].subject),
                    StringCleaner.cleanString(parsedResults.comments[i].content),
                    commentSubmitter, 
                    parsedResults.comments[i].date);

            comments.add(currentComment);
        }

        // Instantiate and populate an ItemResultTrip object
        ItemResultTrip finalResult = new ItemResultTrip(tripDetails, tripSchedule, coordinates, member, comments);
        
        // Return the set of results that has been collected
        return finalResult;
    }

    /**
     * A helper method used to build the URL to query the API
     * 
     * @param dataCategory The category of data to be queried, e.g. tunes, discussions, events etc.
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private URL composeURL(String dataCategory) throws MalformedURLException, URISyntaxException
    {
        // Build the URL with all necessary parameters to perform an API query
        URL requestURL;
        URLComposer builder = new URLComposer();

        requestURL = builder
                .new Builder()
                .requestType(RequestType.SINGLE_ITEM)
                .path(dataCategory + "/" + itemID).build();

        return requestURL;
    }
}
