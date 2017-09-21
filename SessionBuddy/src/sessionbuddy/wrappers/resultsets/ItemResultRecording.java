package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.granularobjects.Artist;
import sessionbuddy.wrappers.granularobjects.Comment;
import sessionbuddy.wrappers.granularobjects.RecordingDetails;
import sessionbuddy.wrappers.granularobjects.TrackListing;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A wrapper for the result returned when retrieving an individual recording by its ID.
 * 
 * @author Colman
 * @since 2017-09-13
 */
public class ItemResultRecording 
	{
	public RecordingDetails recordingDetails;	// Metadata relating to the recording
	public User member; 						// User who submitted the recording
	public Artist artist; 						// The recording artist or group
	public ArrayList<TrackListing> tracks; 		// List of tracks on the recording
	public ArrayList<Comment> comments; 		// Comments on the recording's page

	/**
	 * Constructor
	 * 
	 * @param recordingDetails a RecordingDetails object that has already been populated
	 * @param member a User object representing the recording submitter
	 * @param artist an Artist object representing the recording artist
	 * @param tracks an ArrayList of TrackListing objects that have already been populated
	 * @param comments an ArrayList of Comment objects that have already been populated
	 */
	public ItemResultRecording(RecordingDetails recordingDetails, User member, Artist artist, ArrayList<TrackListing> tracks, ArrayList<Comment> comments)
		{
		this.recordingDetails = recordingDetails;
		this.member = member;
		this.artist = artist;
		this.tracks = tracks;
		this.comments = comments;
		}
}
