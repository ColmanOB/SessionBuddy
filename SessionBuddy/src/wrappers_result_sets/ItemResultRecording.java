package wrappers_result_sets;

import java.util.ArrayList;

import wrappers_granular_objects.Artist;
import wrappers_granular_objects.Comment;
import wrappers_granular_objects.RecordingDetails;
import wrappers_granular_objects.TrackListing;
import wrappers_granular_objects.User;

/**
 * @author Colman
 * @since 2017-08-17
 */
public class ItemResultRecording 
	{
	// Variables to hold data relating to the recording as a whole	
	public RecordingDetails recordingDetails; // recording ID, title, etc.
	public User member; // The member who submitted the recording
	public Artist artist; // The recording artist
	public ArrayList<TrackListing> tracks; // The list of tracks on the recording
	public ArrayList<Comment> comments; // The comments on the recording

	/**
	 * Constructor method
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
