package json_object_wrappers;

import java.util.ArrayList;

/**
 * @author Colman
 * @since 2017-08-17
 */
public class RecordingByIDResult 
	{
	// Variables to hold data relating to the discussion as a whole
	public String id;	
	public String url;
	public String name;

	public User member;
	
	public String date;
	
	public Artist artist;
	
	public ArrayList<TrackListing> tracks;
		
	public ArrayList<DiscussionComment> comments; // An array of comments in the discussion

	/**
	 * Constructor method
	 * 
	 * @param id
	 * @param name
	 * @param url
	 * @param member
	 * @param date
	 * @param type
	 * @param tunebooks
	 * @param recordings
	 * @param aliases
	 * @param settings
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
