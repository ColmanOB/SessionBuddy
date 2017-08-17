package result_set_wrappers;


public class DiscussionByIDWrapper 
	{
	//Purpose: A wrapper class for a discussion, including the list of comments in the discussion from thesession.org API
	//The fields and nested structure follow the format of the JSON structure of the search results
	public String format;	// The format of the response, i.e. JSON
	public String id;		// The unique ID of the discussion in thesession.org database
	public String name;		// The subject line of the discussion
	public String url;		// The URL of the discussion
	public Submitter member; // The user who originally submitted the discussion
	public String date;		// The date the discussion was started
	public Comment[] comments; // The array of comments that make up the discussion

		public class Comment
			//Purpose: A wrapper for the individual comments within the discussion
			{
			public String id;		// The identifier for the particular comment
			public String url;	// The URL of the particular comment
			public String subject;	// The subject line of the comment
			public String content;	// The actual text of the comment submitted by the user
			public Submitter member; // The user who made the comment
			public String date;	// The date of the comment
			}
			
			public class Submitter
			//Purpose: A wrapper for the details of thesession.org user who submitted the discussion or comment	
				{
				public int id;		// A numeric identifier for the particular user within thesession.org database
				public String name;	// Thesession.org username of the person who submitted the discussion or comment
				public String url;	// The URL for the personal page of thesession.org user who submitted the discussion or comment				
				}
	}
