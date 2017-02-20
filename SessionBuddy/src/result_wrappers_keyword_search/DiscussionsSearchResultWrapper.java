package result_wrappers_keyword_search;
// TODO: Fix up the comments here, add JavaDoc comments, edit comments so they relate to Events, not Recordings
/**
 * A wrapper class for the response returned from the API at https://thesession.org
 * This is a utility class used by EventsSearchParser to facilitate access to any individual field within a set of JSON search results
 * The fields and nested structure follow the format of the JSON structure of the recordings search results from the API
 * * 
 * @author Colman O'B
 * @since 2017-02-01
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
		public String id;		// A unique ID for the discussion within thesession.org database
		public String name;	// The name of the discussion within thesession.org database
		public String url;	// The URL of the discussion's individual page on thesession.org website
		
		public SubmitterDetails member; // Details of thesession.org user who submitted the tune
		
		public String date;	// The date on which the discussion was submitted to thesession.org website
		public String comments; // The number of individual comments in the discussions
			
		public class SubmitterDetails 
			{
			public int id;		// A unique ID for the member of thesession.org who submitted the discussion
			public String name;	// The user name of the member of thesession.org who submitted the discussion
			public String url;	// The URL of the member's personal page on thesession.org website
			}
		
		}
	}