package json_object_wrappers;

import java.util.ArrayList;

/**
 * @author Colman
 * @since 2017-08-17
 */
public class RecordingByIDResult 
	{
	// Variables to hold data relating to the recording as a whole
	public String id;	
	public String url;
	public String name;

	public User member; // The member who submitted the recording
	
	public String date; // The date the recording was submitted to thesession.org
	
	public Artist artist; // The recording artist
	
	public ArrayList<TrackListing> tracks; // The list of tracks on the recording
		
	public ArrayList<DiscussionComment> comments; // The comments on the recording

	/**
	 * Constructor method
	 *
	 * @param id
	 * @param name
	 * @param url
	 * @param member
	 * @param date
	 * @param artist
	 * @param tracks
	 * @param comments
	 */
	public RecordingByIDResult(String id, String name, String url, User member, String date, Artist artist, ArrayList<TrackListing> tracks, ArrayList<DiscussionComment> comments)
		{
		this.id = id;
		this.name = name;
		this.url = url;	
		this.member = member;
		this.date = date;
		this.artist = artist;
		this.tracks = tracks;
		this.comments = comments;
		}
}
