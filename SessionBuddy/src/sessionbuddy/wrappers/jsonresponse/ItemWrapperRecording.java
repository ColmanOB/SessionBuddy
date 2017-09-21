package sessionbuddy.wrappers.jsonresponse;


/**
 * A wrapper class for the result set when retrieving an individual recording by its ID.
 * For an example of the JSON response, see https://thesession.org/recordings/1222?format=json
 * The fields and nested structure follow the JSON structure of the response
 * 
 * @author Colman
 * @since 2017-09-10
 */
public class ItemWrapperRecording 
	{
	public String format;	// Format of the response, i.e. JSON
	public String id;		// ID of the recording in thesession.org database
	public String url;		// URL of the recording's page in thesession.org
	public String name;		// Title of the recording
	public Submitter member; // User who submitted the recording
	public String date;		// Date the recording was submitted
	public Artist artist;	// The recording artist
	public Track[] tracks; 	// Track listing for the recording
	public Comment[] comments; // User-added comments on the recording's page

	/**
	 * A wrapper for each individual track listing on a recording, may contain numerous tunes
	 * 
	 * @author Colman
	 * @since 2017-09-11
	 */
	public class Track
		{		
		public Tune[] tunes; // An array of tunes within a single track
		
		/**
		 * A wrapper for each individual tune within a single track listing
		 * 
		 * @author Colman
		 * @since 2017-09-11
		 */
		public class Tune
			{
			public String name;	// Tune name
			public String id;	// ID of the tune in thesession.org
			public String url;	// URL of the tune page
			}
		}	
		
		/**
		 * A wrapper for details relating to the recording artist
		 * 
		 * @author Colman
		 * @since 2017-09-11
		 */
	public class Artist
		{
		public String id; 	// ID of the artist in thesession.org
		public String name;	// Name of the tune
		public String url;	// URL of the artist page on thesession.org
		}
			
	/**
	 * A wrapper for the details of thesession.org user who submitted the recording	
	 * 
	 * @author Colman
	 * @since 2017-09-11
	 */
	public class Submitter
		{
		public int id;		// ID of the user within thesession.org database
		public String name;	// Username of the person who submitted the recording
		public String url;	// Profile page of thesession.org user who submitted the recording
		}
		
	/**
	 * A wrapper for a comment on a recording's page
	 * 
	 * @author Colman
	 * @since 2017-09-11
	 */
	public class Comment
		{
		public String id;			// ID of the comment within thesession.org
		public String url;			// URL of the particular comment
		public String subject;		// Subject line of the comment
		public String content;		// Text of the comment
		public Submitter member;	// Comment submitter
		public String date;			// Date of the comment
		}
	}
