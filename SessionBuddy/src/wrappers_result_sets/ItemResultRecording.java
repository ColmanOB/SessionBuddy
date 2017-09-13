package wrappers_result_sets;

import java.util.ArrayList;

import wrappers_granular_objects.Artist;
import wrappers_granular_objects.Comment;
import wrappers_granular_objects.RecordingDetails;
import wrappers_granular_objects.TrackListing;
import wrappers_granular_objects.User;

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
	 * @param recordingDetails
	 * @param member
	 * @param artist
	 * @param tracks
	 * @param comments
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
