package result_set_wrappers;

/**
 * A wrapper class for the response returned when searching for the most recently added tunes at thesession.org
 * This is a utility class used by JsonSearchParser to facilitate access to any individual field within a set of JSON search results
 * The fields and nested structure follow the format of the JSON structure of the latest tunes results from the API
 * 
 * Note that this wrapper is an anomaly - for all other data categories the response is the same for a keyword search and for latest listings.
 * Latest tunes are presented quite differently from the results of a keyword search for tunes.
 * 
 * @author Colman O'B
 * @since 2017-09-06
 */
public class LatestWrapperTunes 
	{
	public String format;	// The format of the results (always be JSON in this implementation)
	public String pages;	// Number of pages in the result set
	public String page;		// The current page within the result set

	public SettingDetails[] settings; // An array of the individual tune settings returned by the search

	/**
	 * A wrapper for the individual tune settings within the search results
	 * 
	 * @author Colman O'B
	 * @since 2017-09-06
	 */
	public class SettingDetails 
		{
		public String id;	// Unique ID for the setting in thesession.org database
		public String url;	// The URL of the individual setting on thesession.org
		public String key; 	// The key of the setting
		public SubmitterDetails member; // Details of thesession.org user who submitted the setting
		public String date; // The date on which this setting was submitted
		public TuneDetails tune; // The 'parent' tune, of which this is a setting

		
		/**
		 * A wrapper for the details of the "member" within each "setting" in the result set
		 * 
		 * @author Colman O'B
		 * @since 2017-09-06
		 *
		 */
		public class SubmitterDetails 
			{
			public int id;		// Numeric ID for the tune submitter
			public String name;	// The tune submitter's username
			public String url;	// The URL of the submitter's profile page on thesession.org
			}
		
		
		/**
		 * A wrapper for the details of the "tune" to which each "setting" in the result set belongs
		 * 
		 * @author Colman O'B
		 * @since 2017-09-06
		 *
		 */
		public class TuneDetails 
			{
			public int id;		// Numeric ID for the tune
			public String name;	// The tune title
			public String url;	// The URL of the tune's page
			}
		}
	}