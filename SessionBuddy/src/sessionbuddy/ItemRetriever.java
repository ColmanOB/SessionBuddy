package sessionbuddy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import sessionbuddy.utils.HttpRequestor;
import sessionbuddy.utils.JsonParser;
import sessionbuddy.utils.StringCleaner;
import sessionbuddy.utils.UrlBuilder;
import sessionbuddy.wrappers.granularobjects.Area;
import sessionbuddy.wrappers.granularobjects.Artist;
import sessionbuddy.wrappers.granularobjects.Comment;
import sessionbuddy.wrappers.granularobjects.Coordinates;
import sessionbuddy.wrappers.granularobjects.Country;
import sessionbuddy.wrappers.granularobjects.DiscussionDetails;
import sessionbuddy.wrappers.granularobjects.EventDetails;
import sessionbuddy.wrappers.granularobjects.EventSchedule;
import sessionbuddy.wrappers.granularobjects.RecordingDetails;
import sessionbuddy.wrappers.granularobjects.SessionDetails;
import sessionbuddy.wrappers.granularobjects.SettingDetails;
import sessionbuddy.wrappers.granularobjects.SettingDetailsWithAbc;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.TrackListing;
import sessionbuddy.wrappers.granularobjects.TuneDetails;
import sessionbuddy.wrappers.granularobjects.TuneRecord;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;
import sessionbuddy.wrappers.jsonresponse.ItemWrapperDiscussion;
import sessionbuddy.wrappers.jsonresponse.ItemWrapperEvent;
import sessionbuddy.wrappers.jsonresponse.ItemWrapperRecording;
import sessionbuddy.wrappers.jsonresponse.ItemWrapperSession;
import sessionbuddy.wrappers.jsonresponse.ItemWrapperTune;
import sessionbuddy.wrappers.resultsets.ItemResultDiscussion;
import sessionbuddy.wrappers.resultsets.ItemResultEvent;
import sessionbuddy.wrappers.resultsets.ItemResultRecording;
import sessionbuddy.wrappers.resultsets.ItemResultSession;
import sessionbuddy.wrappers.resultsets.ItemResultTune;


/**
 * Retrieves the data for a single item from the session.org.  The item may be a tune, discussion, recording, session or event.
 * 
 * @author Colman
 * @since 2018-04-01
 */
public class ItemRetriever 
	{
	/**
	 * The numeric identifier in thesession.org database for the item to be retrieved
	 */
	private int itemID;
	
	/**
	 * Constructor where pagination is not required and you only want to see the first page of the API response
	 * 
	 * @param itemID Specifies the numeric identifier in thesession.org database of the resource to be retrieved
	 */
	public ItemRetriever(int itemID)
		{
		this.itemID = itemID;
		}
	
	
	/**
	 * Gets the details of an individual recording based on its numeric ID in thesession.org.
	 * This gets details of the track listing, the tunes on each track, the comments on the recording's page on thesession.org and more.
	 * 
	 * @return an ItemResultRecording object with the details of the chosen recording
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-04-01
	 */
	public ItemResultRecording getRecording() throws IOException, URISyntaxException
		{
		try
			{
			// Construct the URL required to query the API, perform the API query and capture the response
			String response = HttpRequestor.submitRequest(composeURL("recordings"));
			
			// Parse the returned JSON into a wrapper class to allow access to all elements
			ItemWrapperRecording parsedResults = JsonParser.parseResponse(response, ItemWrapperRecording.class);
			
			// Use a private helper method to put the response into an easily usable object, and return that object
			return populateRecordingResult(parsedResults);
			} 
		
		catch (IOException | URISyntaxException ex)
			{
			throw ex;
			}
		}
	

	/**
	 * Gets the details of an individual discussion based on its numeric ID in thesession.org.
	 * This gets several items of metadata relating to the discussion, as well as the comments that make up the discussion.
	 * 
	 * @return an ItemResultDiscussion object with the details of the chosen discussion
	 * @throws IOException if there is a problem with the HTTPS request to the API
	 * @throws URISyntaxException 
	 * 
	 * @author Colman
	 * @since 2018-04-01
	 */
	public ItemResultDiscussion getDiscussion() throws IOException, URISyntaxException
		{
		try
			{
			// Construct the URL required to query the API, perform the API query and capture the response
			String response = HttpRequestor.submitRequest(composeURL("discussions"));
			
			// Parse the returned JSON into a pre-defined wrapper class to allow access to all elements
			ItemWrapperDiscussion parsedResults = JsonParser.parseResponse(response, ItemWrapperDiscussion.class);
			
			// Use a private helper method to put the response into an easily usable object, and return that object
			return populateDiscussionResult(parsedResults);
			}
		
		catch (IOException | URISyntaxException ex)
			{
			throw ex;
			}
		}
	

	/**
	 * Gets the details of an individual tune based on its numeric ID in thesession.org.
	 * This gets several items of metadata relating to the tune, as well as any alternative settings, and a list of comments on the tune's page.
	 * 
	 * @return an ItemResultTune object with the details of the chosen tune
	 * @throws IOException if there is a problem with the HTTPS request to the API
	 * @throws URISyntaxException 
	 */
	public ItemResultTune getTune() throws IOException, URISyntaxException
		{		
		try
			{
			// Construct the URL required to query the API, perform the API query and capture the response
			String response = HttpRequestor.submitRequest(composeURL("tunes"));
			
			// Parse the returned JSON into a wrapper class to allow access to all elements
			ItemWrapperTune parsedResults = JsonParser.parseResponse(response, ItemWrapperTune.class);
			
			// Use a private helper method to put the response into an easily usable object, and return that object
			return populateTuneResult(parsedResults);
			}
		
		catch (IOException | URISyntaxException ex)
			{
			throw ex;
			}
		}
	
	
	/**
	 * Gets the details of an individual session based on its numeric ID in thesession.org.
	 * This gets several items of metadata relating to the session, as well as the comments on the session's page.
	 * 
	 * @return an ItemResultSession object with the details of the chosen session
	 * @throws IOException if there is a problem with the HTTPS request to the API
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-04-01
	 */
	public ItemResultSession getSession() throws IOException, URISyntaxException
		{
		try
			{
			// Construct the URL required to query the API, perform the API query and capture the response
			String response = HttpRequestor.submitRequest(composeURL("sessions"));
			
			// Parse the returned JSON into a wrapper class to allow access to all elements
			ItemWrapperSession parsedResults = JsonParser.parseResponse(response, ItemWrapperSession.class);
			
			// Use a private helper method to put the response into an easily usable object, and return that object
			return populateSessionResult(parsedResults);
			}			
		
		catch (IOException | URISyntaxException ex)
			{
			throw ex;
			}
		}
	

	/**
	 * Gets the details of an individual event based on its numeric ID in thesession.org.
	 * This gets several items of metadata relating to the event, as well as the comments on the event's page.
	 * 
	 * @return an ItemResultRecording object with the details of the chosen recording
	 * @throws IOException if there is a problem with the HTTPS request to the API
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-04-01
	 */
	public ItemResultEvent getEvent() throws IOException, URISyntaxException
		{		
		try
			{
			// Construct the URL required to query the API, perform the API query and capture the response
			String response = HttpRequestor.submitRequest(composeURL("events"));
			
			// Parse the returned JSON into a wrapper class to allow access to all elements
			ItemWrapperEvent parsedResults = JsonParser.parseResponse(response, ItemWrapperEvent.class);
			
			// Use a private helper method to put the response into an easily usable object, and return that object
			return populateEventResult(parsedResults);
			}
			
		catch (IOException | URISyntaxException ex)
			{
			throw ex;
			}
		}
	
	
	/**
	 * A private helper method to gather & parse a response into an easily usable object
	 * 
	 * @param parsedResults An ItemWrapperRecording object that has already been populated
	 * @return A populated ItemResultRecording
	 */
	private ItemResultRecording populateRecordingResult(ItemWrapperRecording parsedResults)
		{
		// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
		RecordingDetails recordingDetails = new RecordingDetails(parsedResults.id, parsedResults.url, StringCleaner.cleanString(parsedResults.name) , parsedResults.date);
		
		// Get the details of the member who originally submitted the recording
		User member = new User(parsedResults.member.id, StringCleaner.cleanString(parsedResults.member.name), parsedResults.member.url);
		
		// Get the details of the recording artist(s)
		Artist artist = new Artist(parsedResults.artist.id, StringCleaner.cleanString(parsedResults.artist.name), parsedResults.artist.url);
		
		// Set up the structure needed to hold the track listing
		ArrayList<TrackListing> tracks = new ArrayList<TrackListing>();
		
		// Populate the track listing
		for(int i = 0; i < (parsedResults.tracks.length); i++)
			{
			// For each individual track, create an ArrayList of TuneRecord objects
			ArrayList<TuneRecord> tunesOnTrack = new ArrayList<TuneRecord>();
			
			// Populate the ArrayList of TuneRecord objects
			for (int j = 0; j < (parsedResults.tracks[i].tunes.length); j++)
				{		
				TuneRecord currentTune = new TuneRecord(parsedResults.tracks[i].tunes[j].id, StringCleaner.cleanString(parsedResults.tracks[i].tunes[j].name), parsedResults.tracks[i].tunes[j].url);
				tunesOnTrack.add(currentTune);
				}
			
			// Add the current track to the track listing
			TrackListing currentTrack = new TrackListing(tunesOnTrack);
			tracks.add(currentTrack);
			}
		
		// Initalise an ArrayList of Comment objects to hold each individual comment on the recording
		ArrayList<Comment> comments = new ArrayList<Comment>();
		
		// Populate the ArrayList of Comment objects by iterating through each comment in the JSON response
		for(int i = 0; i < (parsedResults.comments.length); i++)
			{
			// Populate the User object representing the person who submitted the comment
			User commentSubmitter = new User(parsedResults.comments[i].member.id, parsedResults.comments[i].member.name, parsedResults.comments[i].member.url);
			
			// Populate the Comment object with all information related to the comment, including the user set up above
			Comment currentComment = new Comment(Integer.parseInt(parsedResults.comments[i].id), parsedResults.comments[i].url, StringCleaner.cleanString(parsedResults.comments[i].subject), StringCleaner.cleanString(parsedResults.comments[i].content), commentSubmitter, parsedResults.comments[i].date);
			
			comments.add(currentComment);				
			} 
			
		// Instantiate a RecordingByIDResult object & populate it with the details captured above
		ItemResultRecording finalResult = new ItemResultRecording(recordingDetails, member, artist, tracks, comments);
			
		// Return the set of results that has been collected
		return finalResult;
		}
	
	
	/**
	 * @param parsedResults
	 * @return
	 */
	private ItemResultDiscussion populateDiscussionResult(ItemWrapperDiscussion parsedResults)
		{
		// Extract each element from the tune entry in the JSON response
		// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
		DiscussionDetails discussionDetails = new DiscussionDetails(parsedResults.id, parsedResults.name, parsedResults.url, parsedResults.date);
		
		// Get the details of the member who originally submitted the discussion
		User member = new User(parsedResults.member.id, StringCleaner.cleanString(parsedResults.member.name), parsedResults.member.url);
		
		// Initalise an ArrayList of Comment objects to hold each individual comment within the dicussion
		ArrayList<Comment> comments = new ArrayList<Comment>();
			
		// Populate the ArrayList of Comment objects by iterating through each comment in the JSON response
		for(int i = 0; i < (parsedResults.comments.length); i++)
			{
			// Populate the User object representing the person who submitted the comment
			User commentSubmitter = new User(parsedResults.comments[i].member.id, parsedResults.comments[i].member.name, parsedResults.comments[i].member.url);
			
			// Populate the Comment object with all information related to the comment, including the user set up above
			Comment currentComment = new Comment(Integer.parseInt(parsedResults.comments[i].id), parsedResults.comments[i].url, StringCleaner.cleanString(parsedResults.comments[i].subject), StringCleaner.cleanString(parsedResults.comments[i].content), commentSubmitter, parsedResults.comments[i].date);
			
			comments.add(currentComment);				
			}
			
		// Instantiate a DiscussionByIDResult object & populate it with the details captured above
		ItemResultDiscussion finalResult = new ItemResultDiscussion(discussionDetails, member, comments);
			
		// Return the set of results that has been collected
		return finalResult;
		}
	
	
	/**
	 * @param parsedResults
	 * @return
	 */
	private ItemResultTune populateTuneResult(ItemWrapperTune parsedResults)
		{
		// Extract each element from the tune entry in the JSON response
		// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
		TuneDetails tuneDetails = new TuneDetails(parsedResults.id, StringCleaner.cleanString(parsedResults.name), parsedResults.type, parsedResults.url, parsedResults.date);
	
		// Get the details of the member who originally submitted the tune
		User member = new User(parsedResults.member.id, StringCleaner.cleanString(parsedResults.member.name), parsedResults.member.url);
		
		String tunebooks = (parsedResults.tunebooks);
		String recordings = (parsedResults.recordings);
		
		// Initialize an ArrayList of Strings to store the list of alternative titles for the tune
		ArrayList<String> aliases = new ArrayList<String>();
		
		// Iterate through each of the alternative titles in the JSON and use them to populate the ArrayList
		for (int i = 0; i < parsedResults.aliases.length; i++)
			{
			aliases.add(parsedResults.aliases[i]);
			}
			
		// Initalise an ArrayList of TuneSetting objects to hold each individual setting of the tune
		ArrayList<SettingDetailsWithAbc> settings = new ArrayList<SettingDetailsWithAbc>();
			
		// Populate the ArrayList of TuneSetting objects by iterating through each setting in the JSON response
		for(int i = 0; i < (parsedResults.settings.length); i++)
			{
			// Populate the User object representing the person who submitted the particular setting
			User settingSubmitter = new User(parsedResults.settings[i].member.id, parsedResults.settings[i].member.name, parsedResults.settings[i].member.url);
			
			SettingDetails currentSettingDetails = new SettingDetails(parsedResults.settings[i].id, parsedResults.settings[i].url, parsedResults.settings[i].key, parsedResults.settings[i].date);
					
			// Populate the TuneSetting object representing information related to a specific setting, including the user set up above
			SettingDetailsWithAbc currentSetting = new SettingDetailsWithAbc(currentSettingDetails, parsedResults.settings[i].abc, settingSubmitter);
			
			settings.add(currentSetting);				
			}
		
		// Initalise an ArrayList of Comment objects to hold each individual comment within the dicussion
		ArrayList<Comment> comments = new ArrayList<Comment>();
			
		// Populate the ArrayList of Comment objects by iterating through each comment in the JSON response
		for(int i = 0; i < (parsedResults.comments.length); i++)
			{
			// Populate the User object representing the person who submitted the comment
			User commentSubmitter = new User(parsedResults.comments[i].member.id, parsedResults.comments[i].member.name, parsedResults.comments[i].member.url);
			
			// Populate the Comment object with all information related to the comment, including the user set up above
			Comment currentComment = new Comment(Integer.parseInt(parsedResults.comments[i].id), parsedResults.comments[i].url, StringCleaner.cleanString(parsedResults.comments[i].subject), StringCleaner.cleanString(parsedResults.comments[i].content), commentSubmitter, parsedResults.comments[i].date);
			
			comments.add(currentComment);				
			}
			
		// Instantiate a TuneByIDResult object & populate it with the details captured above
		ItemResultTune finalResult = new ItemResultTune(tuneDetails, member, tunebooks, recordings, aliases, settings, comments);
			
		// Return the set of results that has been collected
		return finalResult;
		}
	
	
	/**
	 * @param parsedResults
	 * @return
	 */
	private ItemResultSession populateSessionResult(ItemWrapperSession parsedResults)
		{
		// Extract each element from the tune entry in the JSON response
		// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
		SessionDetails sessionDetails = new SessionDetails(parsedResults.id, parsedResults.url, parsedResults.date);
		Coordinates coordinates = new Coordinates(parsedResults.latitude, parsedResults.longitude);
		User member = new User(parsedResults.member.id,StringCleaner.cleanString(parsedResults.member.name),parsedResults.member.url);	
		Venue venue = new Venue(parsedResults.venue.id, StringCleaner.cleanString(parsedResults.venue.name), parsedResults.venue.telephone, parsedResults.venue.email, parsedResults.venue.web);	
		Town town = new Town(parsedResults.town.id, StringCleaner.cleanString(parsedResults.town.name));
		Area area = new Area(parsedResults.area.id, StringCleaner.cleanString(parsedResults.area.name));
		Country country = new Country(parsedResults.country.id, StringCleaner.cleanString(parsedResults.country.name));		
	
		// Initialize an ArrayList of Strings to store the schedule, i.e. list of days when the session happens
		ArrayList<String> schedule = new ArrayList<String>();
		
		// Iterate through each of the alternative titles in the JSON and use them to populate the ArrayList
		for (int i = 0; i < parsedResults.schedule.length; i++)
			{
			schedule.add(parsedResults.schedule[i]);
			}
	
		// Initalise an ArrayList of Comment objects to hold each individual comment within the discussion
		ArrayList<Comment> comments = new ArrayList<Comment>();
			
		// Populate the ArrayList of Comment objects by iterating through each comment in the JSON response
		for(int i = 0; i < (parsedResults.comments.length); i++)
			{
			// Populate the User object representing the person who submitted the comment
			User commentSubmitter = new User(parsedResults.comments[i].member.id, StringCleaner.cleanString(parsedResults.comments[i].member.name), parsedResults.comments[i].member.url);
			
			// Populate the Comment object with all information related to the comment, including the user set up above
			Comment currentComment = new Comment(Integer.parseInt(parsedResults.comments[i].id), parsedResults.comments[i].url, StringCleaner.cleanString(parsedResults.comments[i].subject), StringCleaner.cleanString(parsedResults.comments[i].content), commentSubmitter, parsedResults.comments[i].date);
			
			comments.add(currentComment);				
			}
			
		// Instantiate a DiscussionByIDResult object & populate it with the details captured above
		ItemResultSession finalResult = new ItemResultSession(sessionDetails, coordinates, member, venue, town, area, country, schedule, comments);
			
		// Return the set of results that has been collected
		return finalResult;
		}
	
	
	/**
	 * @param parsedResults
	 * @return
	 */
	private ItemResultEvent populateEventResult(ItemWrapperEvent parsedResults)
		{
		// Extract each element from the tune entry in the JSON response
		// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
		EventDetails eventDetails = new EventDetails(parsedResults.id, StringCleaner.cleanString(parsedResults.name), parsedResults.url, parsedResults.date);
		User member = new User(parsedResults.member.id,StringCleaner.cleanString(parsedResults.member.name),parsedResults.member.url);	
		EventSchedule schedule = new EventSchedule(parsedResults.dtstart, parsedResults.dtend);
		Coordinates coordinates = new Coordinates(parsedResults.latitude, parsedResults.longitude);
		Venue venue = new Venue(parsedResults.venue.id, StringCleaner.cleanString(parsedResults.venue.name), parsedResults.venue.telephone, parsedResults.venue.email, parsedResults.venue.web);	
		Town town = new Town(parsedResults.town.id, StringCleaner.cleanString(parsedResults.town.name));
		Area area = new Area(parsedResults.area.id, StringCleaner.cleanString(parsedResults.area.name));
		Country country = new Country(parsedResults.country.id, StringCleaner.cleanString(parsedResults.country.name));		
		
		// Initalise an ArrayList of Comment objects to hold each individual comment within the event
		ArrayList<Comment> comments = new ArrayList<Comment>();
			
		// Populate the ArrayList of Comment objects by iterating through each comment in the JSON response
		for(int i = 0; i < (parsedResults.comments.length); i++)
			{
			// Populate the User object representing the person who submitted the comment
			User commentSubmitter = new User(parsedResults.comments[i].member.id, StringCleaner.cleanString(parsedResults.comments[i].member.name), parsedResults.comments[i].member.url);
			
			// Populate the Comment object with all information related to the comment, including the user set up above
			Comment currentComment = new Comment(Integer.parseInt(parsedResults.comments[i].id), parsedResults.comments[i].url, StringCleaner.cleanString(parsedResults.comments[i].subject), StringCleaner.cleanString(parsedResults.comments[i].content), commentSubmitter, parsedResults.comments[i].date);
			
			comments.add(currentComment);				
			}
			
		// Instantiate a DiscussionByIDResult object & populate it with the details captured above
		ItemResultEvent finalResult = new ItemResultEvent(eventDetails, member, schedule, coordinates, venue, town, area, country, comments);
			
		// Return the set of results that has been collected
		return finalResult;
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
		
		UrlBuilder builder = new UrlBuilder();
			
		requestURL = builder.new Builder()
				.path(dataCategory + "/" + itemID)
				.build();
		
		return requestURL;
		}
	}

