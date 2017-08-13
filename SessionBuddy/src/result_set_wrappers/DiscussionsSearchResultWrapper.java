package result_set_wrappers;
// TODO: Fix up the comments here, add JavaDoc comments
/**
 * A wrapper class for the JSON response returned from the API at https://thesession.org when searching for discussions.
 * This is a utility class used by DiscussionsSearchParser to facilitate access to any individual field within a set of JSON search results
 * The fields and nested structure follow the format of the JSON structure of the recordings search results from the API
 * * 
 * @author Colman O'B
 * @since 2017-08-13
 */
public class DiscussionsSearchResultWrapper
	{
	public String q;		// The search query submitted by the user
	public String pages;	// The number of pages in the result set
	public String page;		// The current page within the result set
	public String format;	// The format of the results (should always be JSON for this project)
	public DiscussionsList[] discussions; // An array of the individual discussions returned by the search

	/**
	 * A wrapper for the individual discussions within the search results returned from thesession.org API
	 * 
	 * @author Colman O'B
	 * @since 2017-02-01
	 */
	public class DiscussionsList
		{
		public String id;	// A unique ID for the discussion within thesession.org
		public String name;	// The username who submitted the discussion
		public String url;	// The URL of the discussion
		
		public SubmitterDetails member; // Details of thesession.org user who submitted the tune
		
		public String date;	// Date the discussion was submitted
		public String comments; // Number of comments in the discussion
			
		/**
		 * A 'sub-wrapper' representing the details of the user that submitted the discussion
		 * 
		 * @author Colman
		 * @since 2017-08-13
		 */
		public class SubmitterDetails 
			{
			public int id;		// Numeric ID no. of the submitter
			public String name;	// Username of the submitter
			public String url;	// URL of the member's profile page on thesession.org
			}
		
		}
	}