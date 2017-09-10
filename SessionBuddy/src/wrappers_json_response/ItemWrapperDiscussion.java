package wrappers_json_response;


/**
 * A wrapper class for the result set when retrieving an individual discussion by its ID.
 * For an example of the response structure, see https://thesession.org/discussions/26115?format=json
 * The fields and nested structure follow the JSON structure of the response
 * 
 * @author Colman O'B
 * @since 2017-09-10
 */
public class ItemWrapperDiscussion 
	{
	public String format;	// Format of the response, i.e. JSON
	public String id;		// ID of the discussion in thesession.org database
	public String name;		// Subject line of the discussion
	public String url;		// URL of the discussion
	public Submitter member; // User who submitted the discussion
	public String date;		// The date the discussion was started
	public Comment[] comments; // The array of comments that make up the discussion

	/**
	 * A wrapper for the individual comments within the discussion
	 * 
	 * @author Colman O'B
	 * @since 2017-09-10
	 */
	public class Comment
		{
		public String id;	// The identifier for the particular comment
		public String url;	// The URL of the particular comment
		public String subject;	// The subject line of the comment
		public String content;	// The actual text of the comment submitted by the user
		public Submitter member; // The user who made the comment
		public String date;	// The date of the comment
		}
			
	/**
	 * A wrapper for the details of thesession.org user who submitted the discussion or comment	
	 * 
	 * @author Colman O'B
	 * @since 2017-09-10
	 */
	public class Submitter
		{
		public int id;		// A numeric identifier for the particular user within thesession.org database
		public String name;	// Username of the submitter
		public String url;	// Profile page URL of the submitter				
		}
	}
