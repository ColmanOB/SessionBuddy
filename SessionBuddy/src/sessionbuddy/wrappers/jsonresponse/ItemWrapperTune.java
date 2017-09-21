package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper class for a tune entry, including the list of settings of one particular tune returned from thesession.org API
 * The fields and nested structure follow the format of the JSON structure of the search results
 * 
 * @author Colman
 * @since 2017-09-12
 */
public class ItemWrapperTune 
	{
	public String format;	// Format of the response, i.e. JSON
	public String id;		// ID of the tune in thesession.org database
	public String name;		// Name of the tune
	public String url;		// The URL of the tune's page on thesession.org
	public Submitter member; // User who originally submitted the tune
	public String date;		// Date of initial tune submission
	public String type;		// Type of the tune, e.g. jig, reel etc.
	public String tunebooks;	// Number of user's personal tunebooks on thesession.org to which the tune has been added
	public String recordings;	// The number of recordings in theession.org database with a tune of this name
	public String[]	aliases;	// Known alternative names for the tune
	public Setting[] settings; 	// Array of different settings of the particular tune
	public Comment[] comments;	// Array of comments on the tune's page

	/**
	 * A wrapper for an individual setting of the particular tune
	 * 
	 * @author Colman
	 * @since 2017-09-12
	 */
	public class Setting
		{
		public String id;	// ID of the particular setting in thesession.org
		public String url;	// URL of the particular setting of the tune
		public String key;	// Key of the particular setting of the tune
		public String abc;	// abc notation of the setting of the tune (i.e. the main body of the tune)
		public Submitter member; // User who submitted the setting
		public String date;	// Date on which the setting was submitted
		}
			
	/**
	 * A wrapper for the details a thesession.org user.
	 * This may be the user who submitted the tune itself, or who submitted a particular setting.
	 * 
	 * @author Colman
	 * @since 2017-09-12
	 */
	public class Submitter 
		{
		public int id;		// ID of the user within thesession.org database
		public String name;	// User's username on thesession.org
		public String url;	// URL for the user's profile page on thesession.org
		} 	
	
	/**
	 * A wrapper for a comment on a recording's page
	 * 
	 * @author Colman
	 * @since 2017-09-12
	 */
	public class Comment
		{
		public String id;		// ID number of the comment
		public String url;		// URL of the individual comment
		public String subject;	// Subject line of the comment
		public String content;	// The actual text of the comment
		public Submitter member;// The person who submitted the comment
		public String date;		// Date the comment was submitted
		}
	}
