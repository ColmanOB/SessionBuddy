package result_set_wrappers;


public class RecordingByIDWrapper 
	{
	//Purpose: A wrapper class for a recording, including its metadata and track listing
	//The fields and nested structure follow the format of the JSON structure of the search results
	public String format;	// The format of the response, i.e. JSON
	public String id;		// The unique ID of the recording in thesession.org database
	public String url;		// The URL of the recording's page in thesession.org
	public String name;		// The title of the recording
	public Submitter member; // The user who submitted the recording
	public String date;		// The date the recording was submitted
	public Artist artist;
	public Track[] tracks; // The track listing for the recording
	public Comment[] comments; // User-added comments on the recording's page

	public class Track
		{		
		public Tune[] tunes;
		
		public class Tune
			//Purpose: A wrapper for each individual track listing on a recording
			{
			public String name;
			public String id;
			public String url;
			}
		}	
		
		public class Artist
			//Purpose: A wrapper for details relating to the recording artist
			{
			public String id;
			public String name;	
			public String url;
			}
			
		public class Submitter
			//Purpose: A wrapper for the details of thesession.org user who submitted the recording	
			{
			public int id;		// A numeric identifier for the particular user within thesession.org database
			public String name;	// Thesession.org username of the person who submitted the recording
			public String url;	// The URL for the personal page of thesession.org user who submitted the recording
			}
		
		public class Comment
			//Purpose: A wrapper for a comment on a recording's page
			{
			public String id;
			public String url;	
			public String subject;
			public String content;
			public Submitter member;
			public String date;
			}
	}
