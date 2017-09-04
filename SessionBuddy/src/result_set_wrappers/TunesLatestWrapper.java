package result_set_wrappers;

/**
 * A wrapper class for the response returned when searching by keyword for a tune from the API at https://thesession.org
 * This is a utility class used by TuneSearchParser to facilitate access to any individual field within a set of JSON search results
 * The fields and nested structure follow the format of the JSON structure of the tune search results from the API
 * To count the number of tunes in the result set, call the .length method on the 'tunes' array
 * 
 * @author Colman O'B
 * @since 2017-01-26
 */
public class TunesLatestWrapper 
	{
	public String format;	// The format of the results (always be JSON in this implementation)
	public String pages;	// Number of pages in the result set
	public String page;		// The current page within the result set

	public SettingDetails[] settings; // An array of the individual tunes returned by the search

	/**
	 * A wrapper for the individual tunes within the search results returned from thesession.org API
	 * 
	 * @author Colman O'B
	 * @since 2017-01-26
	 */
	public class SettingDetails 
		{
		public String id;	// Unique ID for the tune in thesession.org database
		public String url;	// The URL of the tune's page on thesession.org
		public String key;
		public SubmitterDetails member; // Details of thesession.org user who submitted the tune
		public String date;
		public TuneDetails details;

		/**
		 * A wrapper for the details of the "member" within each "tune" in the result set
		 * 
		 * @author Colman O'B
		 * @since 2017-01-26
		 *
		 */
		public class SubmitterDetails 
			{
			public int id;		// Numeric ID for the tune submitter
			public String name;	// The tune submitter's username
			public String url;	// The URL of the submitter's profile page on thesession.org
			}
		
		public class TuneDetails 
			{
			public int id;		// Numeric ID for the tune submitter
			public String name;	// The tune submitter's username
			public String url;	// The URL of the submitter's profile page on thesession.org
			}
		}
	}