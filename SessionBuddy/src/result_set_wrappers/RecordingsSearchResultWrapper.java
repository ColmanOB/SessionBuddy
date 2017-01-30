package result_set_wrappers;

/**
 * A wrapper class for the response returned from the API at https://thesession.org
 * This is a utility class used by RecordingsSearchParser to facilitate access to any individual field within a set of JSON search results
 * The fields and nested structure follow the format of the JSON structure of the tune search results from the API
 * * 
 * @author Colman O'B
 * @since 2017-01-28
 */
public class RecordingsSearchResultWrapper
	{
	public String q;		// The search query submitted by the user
	public String pages;	// The number of pages in the result set
	public String page;		// The current page within the result set
	public String format;	// The format of the results (should always be JSON for this project)
	public recordingsList[] recordings; // An array of the individual recordings returned by the search

	/**
	 * A wrapper for the individual recordings within the search results returned from thesession.org API
	 * 
	 * @author Colman O'B
	 * @since 2017-01-28
	 */
	public class recordingsList
		{
		public String id;		// A unique ID for the recording within thesession.org database
		public String name;	// The name of the recording within thesession.org database
		public String url;	// The URL of the recording's individual page on thesession.org website
		public String date;	// The date on which the recording was submitted to thesession.org website
		public SubmitterDetails member; // Details of thesession.org user who submitted the tune
		public ArtistDetails artist; // Details of the recording artist

		/**
		 * A wrapper for the details of the "member" within each "recording" in the result set
		 * 
		 * @author Colman O'B
		 * @since 2017-01-28
		 *
		 */
		public class SubmitterDetails 
			{
			public int id;		// A unique ID for the member of thesession.org who submitted the tune
			public String name;	// The user name of the member of thesession.org who submitted the tune
			public String url;	// The URL of the member's personal page on thesession.org website
			}
		
		/**
		 * A wrapper for the details of the "artist" within each "recording" in the result set
		 * 
		 * @author Colman O'B
		 * @since 2017-01-28
		 *
		 */
		public class ArtistDetails 
			{
			public int id;		// A unique ID for the recording artist
			public String name;	// The artist name
			public String url;	// The URL of the artist page on thesession.org website
			}
		}
	}