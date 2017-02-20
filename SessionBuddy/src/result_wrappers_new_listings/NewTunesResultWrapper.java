package result_wrappers_new_listings;

/**
 * A wrapper class for the response returned from the API at https://thesession.org
 * This is a utility class used by TuneSearchParser to facilitate access to any individual field within a set of JSON search results
 * The fields and nested structure follow the format of the JSON structure of the tune search results from the API
 * To count the number of settings in the result set, call the .length method on the 'settings' array
 * 
 * @author Colman O'B
 * @since 2017-01-26
 */
public class NewTunesResultWrapper 
	{
	public String format;	// The format of the results (should always be JSON for this project)
	public String pages;	// The number of pages in the result set
	public String page;		// The current page within the result set
	public Settings[] settings; // An array of the individual settings returned by the search

	/**
	 * A wrapper for the individual settings within the search results returned from thesession.org API
	 * 
	 * @author Colman O'B
	 * @since 2017-01-26
	 */
	public class Settings 
		{
		public String id;		// A unique ID for the tune within thesession.org database
		public String url;	// The URL of the tune's individual page on thesession.org website
		public String key;
		public String name;	// The name of the tune within thesession.org database
		public SubmitterDetails member; // Details of thesession.org user who submitted the tune
		public String date;	// The date on which the tune was submitted to thesession.org website
		public TuneDetails tune;

		/**
		 * A wrapper for the details of the "member" within each "tune" in the result set
		 * 
		 * @author Colman O'B
		 * @since 2017-01-26
		 *
		 */
		public class SubmitterDetails 
			{
			public int id;		// A unique ID for the member of thesession.org who submitted the tune
			public String name;	// The user name of the member of thesession.org who submitted the tune
			public String url;	// The URL of the member's personal page on thesession.org website
			}
		
		public class TuneDetails 
			{
			public int id;		// A unique ID for the member of thesession.org who submitted the tune
			public String name;	// The user name of the member of thesession.org who submitted the tune
			public String url;	// The URL of the member's personal page on thesession.org website
			}
		}
	}