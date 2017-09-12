package wrappers_json_response;


/**
 * A wrapper class for the response returned when searching by keyword for a tune from the API at https://thesession.org
 * The fields and nested structure follow the format of the JSON structure of the tune search results from the API
 * 
 * @author Colman O'B
 * @since 2017-01-26
 */
public class KeywordSearchWrapperTunes 
	{
	public String q;			// The user's search terms
	public String pages;		// Number of pages in the result set
	public String page;			// The current page within the result set
	public String format;		// The format of the results (always be JSON in this implementation)
	public TuneDetails[] tunes; // An array of the individual tunes returned by the search

	/**
	 * A wrapper for the individual tunes within the search results returned from thesession.org API
	 * 
	 * @author Colman O'B
	 * @since 2017-01-26
	 */
	public class TuneDetails 
		{
		public String id;	// ID for the tune in thesession.org database
		public String name;	// The tune's name
		public String url;	// URL of the tune's page on thesession.org
		public String date;	// Date the tune was submitted
		public String type;	// Type of tune, e.g. jig, reel etc.
		public SubmitterDetails member; // Details of thesession.org user who submitted the tune

		/**
		 * A wrapper for the details of the "member" within each "tune" in the result set
		 * 
		 * @author Colman O'B
		 * @since 2017-01-26
		 *
		 */
		public class SubmitterDetails 
			{
			public int id;		// ID of the user within thesession.org
			public String name;	// The user's username
			public String url;	// URL of the submitter's profile page on thesession.org
			}
		}
	}