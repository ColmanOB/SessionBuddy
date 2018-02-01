package sessionbuddy.wrappers.jsonresponse;
/**
 * A wrapper class for the JSON response returned from the API at https://thesession.org when searching for discussions by keyword(s).
 * The fields and nested structure follow the format of the JSON structure of the recordings search results from the API
 * 
 * @author Colman O'B
 * @since 2018-02-01
 */
public class KeywordSearchWrapperDiscussions
	{
	public String q;		// Search query provided by user
	public String pages;	// Number of pages in the result set
	public String page;		// Current page number within the result set
	public String format;	// Response format (always JSON in this project)
	public DiscussionsList[] discussions; // Array of the individual discussions returned by the search

	/**
	 * A wrapper for an individual discussion within the search results
	 * 
	 * @author Colman O'B
	 * @since 2017-09-12
	 */
	public class DiscussionsList
		{
		public String id;	// ID of the discussion within thesession.org
		public String name;	// Discussion's title
		public String url;	// URL of the discussion	
		public SubmitterDetails member; // Details of thesession.org user who submitted the discussion	
		public String date;		// Date the discussion was submitted
		public String comments; // Number of comments in the discussion
			
		/**
		 * Wrapper representing the details of the user that submitted the discussion
		 * 
		 * @author Colman
		 * @since 2017-09-12
		 */
		public class SubmitterDetails 
			{
			public int id;		// ID of the submitter in thesession.org database
			public String name;	// Username of the submitter
			public String url;	// URL of the user's profile page on thesession.org
			}	
		}
	}