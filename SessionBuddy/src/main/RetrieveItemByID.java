package main;

import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;

import json_object_wrappers.Area;
import json_object_wrappers.Artist;
import json_object_wrappers.Coordinates;
import json_object_wrappers.Country;
import json_object_wrappers.DiscussionByIDResult;
import json_object_wrappers.DiscussionComment;
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
import response_parsers.DiscussionByIDParser;
import response_parsers.EventByIDParser;
import response_parsers.RecordingByIDParser;
import response_parsers.SessionByIDParser;
import response_parsers.TuneByIDParser;
import result_set_wrappers.DiscussionByIDWrapper;
import result_set_wrappers.EventByIDWrapper;
import result_set_wrappers.RecordingByIDWrapper;
import result_set_wrappers.SessionByIDWrapper;
import result_set_wrappers.TuneByIDWrapper;


public class RetrieveItemByID 
{
private int pageCount = 0;

	public RecordingByIDResult getRecordingByID(String itemCategory, String recordingID, int resultsPerPage) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}
		
		// Make the API call using the the recording ID and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitListRequest("recordings", recordingID, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		RecordingByIDParser jsonParser = new RecordingByIDParser();
		RecordingByIDWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
		
		// Extract each element from the recording entry in the JSON response
		// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response
		RecordingDetails recordingDetails = new RecordingDetails(parsedResults.id, parsedResults.url, StringEscapeUtils.unescapeXml(parsedResults.name) , parsedResults.date);
		
		// Get the details of the member who originally submitted the recording
		User member = new User(Integer.toString(parsedResults.member.id), StringEscapeUtils.unescapeXml(parsedResults.member.name), parsedResults.member.url);
		
		// Get the details of the recording artist(s)
		Artist artist = new Artist(parsedResults.artist.id, StringEscapeUtils.unescapeXml(parsedResults.artist.name), parsedResults.artist.url);
		
		// Set up the structure needed to hold the track listing
		ArrayList<TrackListing> tracks = new ArrayList<TrackListing>();
		
		// Populate the track listing
		for(int i = 0; i < (parsedResults.tracks.length)-1; i++)
			{
			// For each individual track, create an ArrayList of TuneRecord objects
			ArrayList<TuneRecord> tunesOnTrack = new ArrayList<TuneRecord>();
			
			// Populate the ArrayList of TuneRecord objects
			for (int j = 0; j < (parsedResults.tracks[i].tunes.length)-1; j++)
				{		
				TuneRecord currentTune = new TuneRecord(StringEscapeUtils.unescapeXml(parsedResults.tracks[i].tunes[j].name), parsedResults.tracks[i].tunes[j].id ,parsedResults.tracks[i].tunes[j].url);
				tunesOnTrack.add(currentTune);
				}
			
			// Add the current track to the track listing
			TrackListing currentTrack = new TrackListing(tunesOnTrack);
			tracks.add(currentTrack);
			}
		
		// Initalise an ArrayList of DiscussionComment objects to hold each individual comment on the recording
		ArrayList<DiscussionComment> comments = new ArrayList<DiscussionComment>();
			
		// Populate the ArrayList of DiscussionComment objects by iterating through each comment in the JSON response
		for(int i = 0; i < (parsedResults.comments.length); i++)
			{
			// Populate the User object representing the person who submitted the comment
			User commentSubmitter = new User(Integer.toString(parsedResults.comments[i].member.id), parsedResults.comments[i].member.name, parsedResults.comments[i].member.url);
			
			// Populate the DiscussionComment object with all information related to the comment, including the user set up above
			DiscussionComment currentComment = new DiscussionComment(Integer.parseInt(parsedResults.comments[i].id), parsedResults.comments[i].url, StringEscapeUtils.unescapeXml(parsedResults.comments[i].subject), StringEscapeUtils.unescapeXml(parsedResults.comments[i].content), commentSubmitter, parsedResults.comments[i].date);
			
			comments.add(currentComment);				
			}
			
		// Instantiate a RecordingByIDResult object & populate it with the details captured above
		RecordingByIDResult finalResult = new RecordingByIDResult(recordingDetails, member, artist, tracks, comments);
			
		// Return the set of results that has been collected
		return finalResult;
		}


	/**
	 * @param itemCategory
	 * @param tuneID
	 * @param resultsPerPage
	 * @return
	 * @throws IllegalArgumentException
	 */
	public DiscussionByIDResult getDiscussionByID(String itemCategory, String discussionID, int resultsPerPage) throws IllegalArgumentException
	{
	if (resultsPerPage > 50)
		{
		throw new IllegalArgumentException("Number of results per page must be 50 or less");
		}

	// Make the API call using the the discussion ID and store the JSON that is returned as a String
	HttpRequestor searcher = new HttpRequestor();
	String apiQueryResults = searcher.submitListRequest("discussions", discussionID, resultsPerPage);
		
	// Parse the returned JSON into a wrapper class to allow access to all elements
	DiscussionByIDParser jsonParser = new DiscussionByIDParser();
	DiscussionByIDWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);

	// Extract each element from the tune entry in the JSON response
	// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response
	DiscussionDetails discussionDetails = new DiscussionDetails(parsedResults.id, parsedResults.name, parsedResults.url, parsedResults.date);
	
	// Get the details of the member who originally submitted the discussion
	User member = new User(Integer.toString(parsedResults.member.id), StringEscapeUtils.unescapeXml(parsedResults.member.name), parsedResults.member.url);
	
	// Initalise an ArrayList of DiscussionComment objects to hold each individual comment within the dicussion
	ArrayList<DiscussionComment> comments = new ArrayList<DiscussionComment>();
		
	// Populate the ArrayList of DiscussionComment objects by iterating through each comment in the JSON response
	for(int i = 0; i < (parsedResults.comments.length); i++)
		{
		// Populate the User object representing the person who submitted the comment
		User commentSubmitter = new User(Integer.toString(parsedResults.comments[i].member.id), parsedResults.comments[i].member.name, parsedResults.comments[i].member.url);
		
		// Populate the DiscussionComment object with all information related to the comment, including the user set up above
		DiscussionComment currentComment = new DiscussionComment(Integer.parseInt(parsedResults.comments[i].id), parsedResults.comments[i].url, StringEscapeUtils.unescapeXml(parsedResults.comments[i].subject), StringEscapeUtils.unescapeXml(parsedResults.comments[i].content), commentSubmitter, parsedResults.comments[i].date);
		
		comments.add(currentComment);				
		}
		
	// Instantiate a DiscussionByIDResult object & populate it with the details captured above
	DiscussionByIDResult finalResult = new DiscussionByIDResult(discussionDetails, member, comments);
		
	// Return the set of results that has been collected
	return finalResult;
	}

	
	public TuneByIDResult getTuneByID(String itemCategory, String tuneID, int resultsPerPage) throws IllegalArgumentException
	{
	if (resultsPerPage > 50)
		{
		throw new IllegalArgumentException("Number of results per page must be 50 or less");
		}

	// Make the API call using the tune ID and store the JSON that is returned as a String
	HttpRequestor searcher = new HttpRequestor();
	String apiQueryResults = searcher.submitListRequest("tunes", tuneID, resultsPerPage);
		
	// Parse the returned JSON into a wrapper class to allow access to all elements
	TuneByIDParser jsonParser = new TuneByIDParser();
	TuneByIDWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);

	// Extract each element from the tune entry in the JSON response
	// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response
	TuneDetails tuneDetails = new TuneDetails(parsedResults.id, StringEscapeUtils.unescapeXml(parsedResults.name), parsedResults.type, parsedResults.url, parsedResults.date);

	// Get the details of the member who originally submitted the tune
	User member = new User(Integer.toString(parsedResults.member.id), StringEscapeUtils.unescapeXml(parsedResults.member.name), parsedResults.member.url);
	
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
		
	// Instantiate a TuneByIDResult object & populate it with the details captured above
	TuneByIDResult finalResult = new TuneByIDResult(tuneDetails, member, tunebooks, recordings, aliases, settings);
		
	// Return the set of results that has been collected
	return finalResult;
	}
	
	
	public SessionByIDResult getSessionByID(String itemCategory, String sessionID, int resultsPerPage) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}

		// Make the API call using the the discussion ID and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitListRequest("sessions", sessionID, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		SessionByIDParser jsonParser = new SessionByIDParser();
		SessionByIDWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
	
		// Extract each element from the tune entry in the JSON response
		// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response
		SessionDetails sessionDetails = new SessionDetails(parsedResults.id, parsedResults.url, parsedResults.date);
		Coordinates coordinates = new Coordinates(parsedResults.latitude, parsedResults.longitude);
		User member = new User(Integer.toString(parsedResults.member.id),StringEscapeUtils.unescapeXml(parsedResults.member.name),parsedResults.member.url);	
		Venue venue = new Venue(Integer.toString(parsedResults.venue.id), StringEscapeUtils.unescapeXml(parsedResults.venue.name), parsedResults.venue.telephone, parsedResults.venue.email, parsedResults.venue.web);	
		Town town = new Town(Integer.toString(parsedResults.town.id), StringEscapeUtils.unescapeXml(parsedResults.town.name));
		Area area = new Area(Integer.toString(parsedResults.area.id), StringEscapeUtils.unescapeXml(parsedResults.area.name));
		Country country = new Country(Integer.toString(parsedResults.country.id), StringEscapeUtils.unescapeXml(parsedResults.country.name));		

		// Initialize an ArrayList of Strings to store the schedule, i.e. list of days when the session happens
		ArrayList<String> schedule = new ArrayList<String>();
		
		// Iterate through each of the alternative titles in the JSON and use them to populate the ArrayList
		for (int i = 0; i < parsedResults.schedule.length; i++)
			{
			schedule.add(parsedResults.schedule[i]);
			}

		
		// Initalise an ArrayList of DiscussionComment objects to hold each individual comment within the dicussion
		ArrayList<DiscussionComment> comments = new ArrayList<DiscussionComment>();
			
		// Populate the ArrayList of DiscussionComment objects by iterating through each comment in the JSON response
		for(int i = 0; i < (parsedResults.comments.length); i++)
			{
			// Populate the User object representing the person who submitted the comment
			User commentSubmitter = new User(Integer.toString(parsedResults.comments[i].member.id), StringEscapeUtils.unescapeXml(parsedResults.comments[i].member.name), parsedResults.comments[i].member.url);
			
			// Populate the DiscussionComment object with all information related to the comment, including the user set up above
			DiscussionComment currentComment = new DiscussionComment(Integer.parseInt(parsedResults.comments[i].id), parsedResults.comments[i].url, StringEscapeUtils.unescapeXml(parsedResults.comments[i].subject), StringEscapeUtils.unescapeXml(parsedResults.comments[i].content), commentSubmitter, parsedResults.comments[i].date);
			
			comments.add(currentComment);				
			}
			
		// Instantiate a DiscussionByIDResult object & populate it with the details captured above
		SessionByIDResult finalResult = new SessionByIDResult(sessionDetails, coordinates, member, venue, town, area, country, schedule, comments);
			
		// Return the set of results that has been collected
		return finalResult;
		}

	public EventByIDResult getEventByID(String itemCategory, String eventID, int resultsPerPage) throws IllegalArgumentException
		{
		if (resultsPerPage > 50)
			{
			throw new IllegalArgumentException("Number of results per page must be 50 or less");
			}

		// Make the API call using the the event ID and store the JSON that is returned as a String
		HttpRequestor searcher = new HttpRequestor();
		String apiQueryResults = searcher.submitListRequest("events", eventID, resultsPerPage);
			
		// Parse the returned JSON into a wrapper class to allow access to all elements
		EventByIDParser jsonParser = new EventByIDParser();
		EventByIDWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);
	
		// Extract each element from the tune entry in the JSON response
		// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response
		EventDetails eventDetails = new EventDetails(parsedResults.id, StringEscapeUtils.unescapeXml(parsedResults.name), parsedResults.url, parsedResults.date);
		User member = new User(Integer.toString(parsedResults.member.id),StringEscapeUtils.unescapeXml(parsedResults.member.name),parsedResults.member.url);	
		EventSchedule schedule = new EventSchedule(parsedResults.dtstart, parsedResults.dtend);
		Coordinates coordinates = new Coordinates(parsedResults.latitude, parsedResults.longitude);
		Venue venue = new Venue(Integer.toString(parsedResults.venue.id), StringEscapeUtils.unescapeXml(parsedResults.venue.name), parsedResults.venue.telephone, parsedResults.venue.email, parsedResults.venue.web);	
		Town town = new Town(Integer.toString(parsedResults.town.id), StringEscapeUtils.unescapeXml(parsedResults.town.name));
		Area area = new Area(Integer.toString(parsedResults.area.id), StringEscapeUtils.unescapeXml(parsedResults.area.name));
		Country country = new Country(Integer.toString(parsedResults.country.id), StringEscapeUtils.unescapeXml(parsedResults.country.name));		
		
		// Initalise an ArrayList of DiscussionComment objects to hold each individual comment within the event
		ArrayList<DiscussionComment> comments = new ArrayList<DiscussionComment>();
			
		// Populate the ArrayList of DiscussionComment objects by iterating through each comment in the JSON response
		for(int i = 0; i < (parsedResults.comments.length); i++)
			{
			// Populate the User object representing the person who submitted the comment
			User commentSubmitter = new User(Integer.toString(parsedResults.comments[i].member.id), StringEscapeUtils.unescapeXml(parsedResults.comments[i].member.name), parsedResults.comments[i].member.url);
			
			// Populate the DiscussionComment object with all information related to the comment, including the user set up above
			DiscussionComment currentComment = new DiscussionComment(Integer.parseInt(parsedResults.comments[i].id), parsedResults.comments[i].url, StringEscapeUtils.unescapeXml(parsedResults.comments[i].subject), StringEscapeUtils.unescapeXml(parsedResults.comments[i].content), commentSubmitter, parsedResults.comments[i].date);
			
			comments.add(currentComment);				
			}
			
		// Instantiate a DiscussionByIDResult object & populate it with the details captured above
		EventByIDResult finalResult = new EventByIDResult(eventDetails, member, schedule, coordinates, venue, town, area, country, comments);
			
		// Return the set of results that has been collected
		return finalResult;
		}
	
	
	
	public int getPageCount() throws IllegalStateException
		{
		if (pageCount == 0)
			{
			throw new IllegalStateException("Page counter has not been initialised");
			}
		else 
			return pageCount;
		}
	

}
