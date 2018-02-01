package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper class for a tune entry, including the list of settings of one particular tune returned from thesession.org API
 * The fields and nested structure follow the format of the JSON structure of the search results
 * 
 * @author Colman
 * @since 2018-02-01
 */
public class ItemWrapperTune 
	{
	/**
	 * Format of the response, i.e. JSON
	 */
	public String format;
	
	/**
	 * ID of the tune in thesession.org database
	 */
	public String id;
	
	/**
	 * Name of the tune
	 */
	public String name;
	
	/**
	 * The URL of the tune's page on thesession.org
	 */
	public String url;
	
	/**
	 * User who originally submitted the tune
	 */
	public Submitter member;
	
	/**
	 * Date of initial tune submission
	 */
	public String date;
	
	/**
	 * Type of the tune, e.g. jig, reel etc.
	 */
	public String type;
	
	/**
	 * Number of user's personal tunebooks on thesession.org to which the tune has been added
	 */
	public String tunebooks;
	
	/**
	 * The number of recordings in theession.org database with a tune of this name
	 */
	public String recordings;
	
	/**
	 * Known alternative names for the tune
	 */
	public String[]	aliases;
	
	/**
	 * Array of different settings of the particular tune
	 */
	public Setting[] settings;
	
	/**
	 * Array of comments on the tune's page
	 */
	public Comment[] comments;

	/**
	 * A wrapper for an individual setting of the particular tune
	 * 
	 * @author Colman
	 * @since 2018-02-01
	 */
	public class Setting
		{
		/**
		 * ID of the particular setting in thesession.org
		 */
		public String id;
		
		/**
		 * URL of the particular setting of the tune
		 */
		public String url;
		
		/**
		 * Key of the particular setting of the tune
		 */
		public String key;
		
		/**
		 * abc notation of the setting of the tune (i.e. the main body of the tune)
		 */
		public String abc;
		
		/**
		 * User who submitted the setting
		 */
		public Submitter member;
		
		/**
		 * Date on which the setting was submitted
		 */
		public String date;
		}
			
	/**
	 * A wrapper for the details a thesession.org user.
	 * This may be the user who submitted the tune itself, or who submitted a particular setting.
	 * 
	 * @author Colman
	 * @since 2018-02-01
	 */
	public class Submitter 
		{
		/**
		 * ID of the user within thesession.org database
		 */
		public int id;
		
		/**
		 * User's username on thesession.org
		 */
		public String name;
		
		/**
		 * URL for the user's profile page on thesession.org
		 */
		public String url;
		} 	
	
	/**
	 * A wrapper for a comment on a recording's page
	 * 
	 * @author Colman
	 * @since 2018-02-01
	 */
	public class Comment
		{
		/**
		 * ID number of the comment
		 */
		public String id;
		
		/**
		 * URL of the individual comment
		 */
		public String url;
		
		/**
		 * Subject line of the comment
		 */
		public String subject;
		
		/**
		 * The actual text of the comment
		 */
		public String content;
		
		/**
		 * The person who submitted the comment
		 */
		public Submitter member;
		
		/**
		 * Date the comment was submitted
		 */
		public String date;
		}
	}
