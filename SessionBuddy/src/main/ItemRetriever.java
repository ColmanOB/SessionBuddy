package main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import json_object_wrappers.Area;
import json_object_wrappers.Artist;
import json_object_wrappers.Coordinates;
import json_object_wrappers.Country;
import json_object_wrappers.DiscussionByIDResult;
import json_object_wrappers.Comment;
import json_object_wrappers.DiscussionDetails;
import json_object_wrappers.EventByIDResult;
import json_object_wrappers.EventDetails;
import json_object_wrappers.EventSchedule;
import json_object_wrappers.RecordingByIDResult;
import json_object_wrappers.RecordingDetails;
import json_object_wrappers.SessionByIDResult;
import json_object_wrappers.SessionDetails;
import json_object_wrappers.Town;
import json_object_wrappers.TrackListing;
import json_object_wrappers.TuneByIDResult;
import json_object_wrappers.TuneDetails;
import json_object_wrappers.TuneRecord;
import json_object_wrappers.TuneSetting;
import json_object_wrappers.User;
import json_object_wrappers.Venue;
import result_set_wrappers.DiscussionByIDWrapper;
import result_set_wrappers.EventByIDWrapper;
import result_set_wrappers.RecordingByIDWrapper;
import result_set_wrappers.SessionByIDWrapper;
import result_set_wrappers.TuneByIDWrapper;
import utils.HttpRequestor;
import utils.JsonResponseParser;
import utils.StringCleaner;

// TODO: Refactor the methods in this class, extract some 'helper' methods
// TODO: Fix up all the comments in this class, including Javadoc comments


/**
 * @author Colman
 *
 */
public class ItemRetriever 
{

	/**
	 * @param recordingID
	 * @return
	 * @throws IOException
	 */
	public RecordingByIDResult getRecordingByID(String recordingID) throws IOException
		{
		// Make the API call using the the recording ID and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String response;
		
		try
			{
			response = searcher.submitItemByIDRequest("recordings", recordingID);
			}
		
		catch (MalformedURLException e)
			{
			throw new IOException(e.getMessage());
			}
		
		catch (IOException e)
			{
			throw new IOException(e.getMessage());
			}
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		JsonResponseParser jsonParser = new JsonResponseParser(response);
		RecordingByIDWrapper parsedResults = jsonParser.parseResponse(RecordingByIDWrapper.class);
		
		// Extract each element from the recording entry in the JSON response
		// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
		RecordingDetails recordingDetails = new RecordingDetails(parsedResults.id, parsedResults.url, StringCleaner.cleanString(parsedResults.name) , parsedResults.date);
		
		// Get the details of the member who originally submitted the recording
		User member = new User(Integer.toString(parsedResults.member.id), StringCleaner.cleanString(parsedResults.member.name), parsedResults.member.url);
		
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
				TuneRecord currentTune = new TuneRecord(StringCleaner.cleanString(parsedResults.tracks[i].tunes[j].name), parsedResults.tracks[i].tunes[j].id ,parsedResults.tracks[i].tunes[j].url);
				tunesOnTrack.add(currentTune);
				}
			
			// Add the current track to the track listing
			TrackListing currentTrack = new TrackListing(tunesOnTrack);
			tracks.add(currentTrack);
			}
		
		// Initalise an ArrayList of DiscussionComment objects to hold each individual comment on the recording
		ArrayList<Comment> comments = new ArrayList<Comment>();
			
		// Populate the ArrayList of DiscussionComment objects by iterating through each comment in the JSON response
		for(int i = 0; i < (parsedResults.comments.length); i++)
			{
			// Populate the User object representing the person who submitted the comment
			User commentSubmitter = new User(Integer.toString(parsedResults.comments[i].member.id), parsedResults.comments[i].member.name, parsedResults.comments[i].member.url);
			
			// Populate the DiscussionComment object with all information related to the comment, including the user set up above
			Comment currentComment = new Comment(Integer.parseInt(parsedResults.comments[i].id), parsedResults.comments[i].url, StringCleaner.cleanString(parsedResults.comments[i].subject), StringCleaner.cleanString(parsedResults.comments[i].content), commentSubmitter, parsedResults.comments[i].date);
			
			comments.add(currentComment);				
			}
			
		// Instantiate a RecordingByIDResult object & populate it with the details captured above
		RecordingByIDResult finalResult = new RecordingByIDResult(recordingDetails, member, artist, tracks, comments);
			
		// Return the set of results that has been collected
		return finalResult;
		}

	
	/**
	 * @param discussionID
	 * @return
	 * @throws IOException
	 */
	public DiscussionByIDResult getDiscussionByID(String discussionID) throws IOException
		{
		// Make the API call using the the discussion ID and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String response;
		
		try
			{
			response = searcher.submitItemByIDRequest("discussions", discussionID);
			}
		
			catch (MalformedURLException e)
			{
			throw new IOException(e.getMessage());
			}
		
		catch (IOException e)
			{
			throw new IOException(e.getMessage());
			}
		
		// Parse the returned JSON into a pre-defined wrapper class to allow access to all elements
		JsonResponseParser jsonParser = new JsonResponseParser(response);
		DiscussionByIDWrapper parsedResults = jsonParser.parseResponse(DiscussionByIDWrapper.class);
	
		// Extract each element from the tune entry in the JSON response
		// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
		DiscussionDetails discussionDetails = new DiscussionDetails(parsedResults.id, parsedResults.name, parsedResults.url, parsedResults.date);
		
		// Get the details of the member who originally submitted the discussion
		User member = new User(Integer.toString(parsedResults.member.id), StringCleaner.cleanString(parsedResults.member.name), parsedResults.member.url);
		
		// Initalise an ArrayList of DiscussionComment objects to hold each individual comment within the dicussion
		ArrayList<Comment> comments = new ArrayList<Comment>();
			
		// Populate the ArrayList of DiscussionComment objects by iterating through each comment in the JSON response
		for(int i = 0; i < (parsedResults.comments.length); i++)
			{
			// Populate the User object representing the person who submitted the comment
			User commentSubmitter = new User(Integer.toString(parsedResults.comments[i].member.id), parsedResults.comments[i].member.name, parsedResults.comments[i].member.url);
			
			// Populate the DiscussionComment object with all information related to the comment, including the user set up above
			Comment currentComment = new Comment(Integer.parseInt(parsedResults.comments[i].id), parsedResults.comments[i].url, StringCleaner.cleanString(parsedResults.comments[i].subject), StringCleaner.cleanString(parsedResults.comments[i].content), commentSubmitter, parsedResults.comments[i].date);
			
			comments.add(currentComment);				
			}
			
		// Instantiate a DiscussionByIDResult object & populate it with the details captured above
		DiscussionByIDResult finalResult = new DiscussionByIDResult(discussionDetails, member, comments);
			
		// Return the set of results that has been collected
		return finalResult;
		}
	

	/**
	 * @param tuneID
	 * @return
	 * @throws IOException
	 */
	public TuneByIDResult getTuneByID(String tuneID) throws IOException
		{
		// Make the API call using the tune ID and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String response;
		
		try
			{
			response = searcher.submitItemByIDRequest("tunes", tuneID);
			}
		
		catch (MalformedURLException e)
			{
			throw new IOException(e.getMessage());
			}
		
		catch (IOException e)
			{
			throw new IOException(e.getMessage());
			}
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		JsonResponseParser jsonParser = new JsonResponseParser(response);
		TuneByIDWrapper parsedResults = jsonParser.parseResponse(TuneByIDWrapper.class);
	
		// Extract each element from the tune entry in the JSON response
		// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
		TuneDetails tuneDetails = new TuneDetails(parsedResults.id, StringCleaner.cleanString(parsedResults.name), parsedResults.type, parsedResults.url, parsedResults.date);
	
		// Get the details of the member who originally submitted the tune
		User member = new User(Integer.toString(parsedResults.member.id), StringCleaner.cleanString(parsedResults.member.name), parsedResults.member.url);
		
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
		ArrayList<TuneSetting> settings = new ArrayList<TuneSetting>();
			
		// Populate the ArrayList of TuneSetting objects by iterating through each setting in the JSON response
		for(int i = 0; i < (parsedResults.settings.length); i++)
			{
			// Populate the User object representing the person who submitted the particular setting
			User settingSubmitter = new User(Integer.toString(parsedResults.settings[i].member.id), parsedResults.settings[i].member.name, parsedResults.settings[i].member.url);
			
			// Populate the TuneSetting object representing information related to a specific setting, including the user set up above
			TuneSetting currentSetting = new TuneSetting(Integer.parseInt(parsedResults.settings[i].id), parsedResults.settings[i].url, parsedResults.settings[i].key, parsedResults.settings[i].abc, settingSubmitter, parsedResults.settings[i].date);
			
			settings.add(currentSetting);				
			}
		
		// Initalise an ArrayList of DiscussionComment objects to hold each individual comment within the dicussion
		ArrayList<Comment> comments = new ArrayList<Comment>();
			
		// Populate the ArrayList of DiscussionComment objects by iterating through each comment in the JSON response
		for(int i = 0; i < (parsedResults.comments.length); i++)
			{
			// Populate the User object representing the person who submitted the comment
			User commentSubmitter = new User(Integer.toString(parsedResults.comments[i].member.id), parsedResults.comments[i].member.name, parsedResults.comments[i].member.url);
			
			// Populate the DiscussionComment object with all information related to the comment, including the user set up above
			Comment currentComment = new Comment(Integer.parseInt(parsedResults.comments[i].id), parsedResults.comments[i].url, StringCleaner.cleanString(parsedResults.comments[i].subject), StringCleaner.cleanString(parsedResults.comments[i].content), commentSubmitter, parsedResults.comments[i].date);
			
			comments.add(currentComment);				
			}
			
		// Instantiate a TuneByIDResult object & populate it with the details captured above
		TuneByIDResult finalResult = new TuneByIDResult(tuneDetails, member, tunebooks, recordings, aliases, settings, comments);
			
		// Return the set of results that has been collected
		return finalResult;
		}
	
	
	/**
	 * @param sessionID
	 * @return
	 * @throws IOException
	 */
	public SessionByIDResult getSessionByID(String sessionID) throws IOException
		{
		// Make the API call using the the discussion ID and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String response;
		
		try
			{
			response = searcher.submitItemByIDRequest("sessions", sessionID);
			}
		
		catch (MalformedURLException e)
			{
			throw new IOException(e.getMessage());
			}
		
		catch (IOException e)
			{
			throw new IOException(e.getMessage());
			}
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		JsonResponseParser jsonParser = new JsonResponseParser(response);
		SessionByIDWrapper parsedResults = jsonParser.parseResponse(SessionByIDWrapper.class);
	
		// Extract each element from the tune entry in the JSON response
		// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
		SessionDetails sessionDetails = new SessionDetails(parsedResults.id, parsedResults.url, parsedResults.date);
		Coordinates coordinates = new Coordinates(parsedResults.latitude, parsedResults.longitude);
		User member = new User(Integer.toString(parsedResults.member.id),StringCleaner.cleanString(parsedResults.member.name),parsedResults.member.url);	
		Venue venue = new Venue(Integer.toString(parsedResults.venue.id), StringCleaner.cleanString(parsedResults.venue.name), parsedResults.venue.telephone, parsedResults.venue.email, parsedResults.venue.web);	
		Town town = new Town(Integer.toString(parsedResults.town.id), StringCleaner.cleanString(parsedResults.town.name));
		Area area = new Area(Integer.toString(parsedResults.area.id), StringCleaner.cleanString(parsedResults.area.name));
		Country country = new Country(Integer.toString(parsedResults.country.id), StringCleaner.cleanString(parsedResults.country.name));		

		// Initialize an ArrayList of Strings to store the schedule, i.e. list of days when the session happens
		ArrayList<String> schedule = new ArrayList<String>();
		
		// Iterate through each of the alternative titles in the JSON and use them to populate the ArrayList
		for (int i = 0; i < parsedResults.schedule.length; i++)
			{
			schedule.add(parsedResults.schedule[i]);
			}

		
		// Initalise an ArrayList of DiscussionComment objects to hold each individual comment within the dicussion
		ArrayList<Comment> comments = new ArrayList<Comment>();
			
		// Populate the ArrayList of DiscussionComment objects by iterating through each comment in the JSON response
		for(int i = 0; i < (parsedResults.comments.length); i++)
			{
			// Populate the User object representing the person who submitted the comment
			User commentSubmitter = new User(Integer.toString(parsedResults.comments[i].member.id), StringCleaner.cleanString(parsedResults.comments[i].member.name), parsedResults.comments[i].member.url);
			
			// Populate the DiscussionComment object with all information related to the comment, including the user set up above
			Comment currentComment = new Comment(Integer.parseInt(parsedResults.comments[i].id), parsedResults.comments[i].url, StringCleaner.cleanString(parsedResults.comments[i].subject), StringCleaner.cleanString(parsedResults.comments[i].content), commentSubmitter, parsedResults.comments[i].date);
			
			comments.add(currentComment);				
			}
			
		// Instantiate a DiscussionByIDResult object & populate it with the details captured above
		SessionByIDResult finalResult = new SessionByIDResult(sessionDetails, coordinates, member, venue, town, area, country, schedule, comments);
			
		// Return the set of results that has been collected
		return finalResult;
		}
	
	
	/**
	 * @param eventID
	 * @return
	 * @throws RuntimeException 
	 * @throws MalformedURLException 
	 */
	public EventByIDResult getEventByID(String eventID) throws IOException
		{
		// Make the API call using the the event ID and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String response;
		
		try
			{
			response = searcher.submitItemByIDRequest("events", eventID);
			}
			
		catch (MalformedURLException e)
			{
			throw new IOException(e.getMessage());
			}
		
		catch (IOException e)
			{
			throw new IOException(e.getMessage());
			}
		// Parse the returned JSON into a wrapper class to allow access to all elements
		JsonResponseParser jsonParser = new JsonResponseParser(response);
		EventByIDWrapper parsedResults = jsonParser.parseResponse(EventByIDWrapper.class);
	
		// Extract each element from the tune entry in the JSON response
		// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
		EventDetails eventDetails = new EventDetails(parsedResults.id, StringCleaner.cleanString(parsedResults.name), parsedResults.url, parsedResults.date);
		User member = new User(Integer.toString(parsedResults.member.id),StringCleaner.cleanString(parsedResults.member.name),parsedResults.member.url);	
		EventSchedule schedule = new EventSchedule(parsedResults.dtstart, parsedResults.dtend);
		Coordinates coordinates = new Coordinates(parsedResults.latitude, parsedResults.longitude);
		Venue venue = new Venue(Integer.toString(parsedResults.venue.id), StringCleaner.cleanString(parsedResults.venue.name), parsedResults.venue.telephone, parsedResults.venue.email, parsedResults.venue.web);	
		Town town = new Town(Integer.toString(parsedResults.town.id), StringCleaner.cleanString(parsedResults.town.name));
		Area area = new Area(Integer.toString(parsedResults.area.id), StringCleaner.cleanString(parsedResults.area.name));
		Country country = new Country(Integer.toString(parsedResults.country.id), StringCleaner.cleanString(parsedResults.country.name));		
		
		// Initalise an ArrayList of DiscussionComment objects to hold each individual comment within the event
		ArrayList<Comment> comments = new ArrayList<Comment>();
			
		// Populate the ArrayList of DiscussionComment objects by iterating through each comment in the JSON response
		for(int i = 0; i < (parsedResults.comments.length); i++)
			{
			// Populate the User object representing the person who submitted the comment
			User commentSubmitter = new User(Integer.toString(parsedResults.comments[i].member.id), StringCleaner.cleanString(parsedResults.comments[i].member.name), parsedResults.comments[i].member.url);
			
			// Populate the DiscussionComment object with all information related to the comment, including the user set up above
			Comment currentComment = new Comment(Integer.parseInt(parsedResults.comments[i].id), parsedResults.comments[i].url, StringCleaner.cleanString(parsedResults.comments[i].subject), StringCleaner.cleanString(parsedResults.comments[i].content), commentSubmitter, parsedResults.comments[i].date);
			
			comments.add(currentComment);				
			}
			
		// Instantiate a DiscussionByIDResult object & populate it with the details captured above
		EventByIDResult finalResult = new EventByIDResult(eventDetails, member, schedule, coordinates, venue, town, area, country, comments);
			
		// Return the set of results that has been collected
		return finalResult;
		}
}
