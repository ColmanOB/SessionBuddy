package sessionbuddy.wrappers.jsonresponse;


/**
 * A wrapper class for the response returned from the API at https://thesession.org when searching for sessions by keyword.
 *  The fields and nested structure follow the format of the JSON structure of the search results from the API
 * * 
 * @author Colman O'B
 * @since 2017-09-12
 */
public class KeywordSearchWrapperSessions
	{
	public String q;		// Search query submitted by the user
	public String pages;	// Number of pages in the result set
	public String page;		// The current page number within the result set
	public String format;	// The format of the results (should always be JSON for this project)
	public sessionsList[] sessions; // An array of the individual sessions returned by the search

	/**
	 * A wrapper for an individual session within the search results
	 * 
	 * @author Colman O'B
	 * @since 2017-02-01
	 */
	public class sessionsList
		{
		public int id;		// A unique ID for the session within thesession.org
		public String url;		// The URL of the sessions's page on thesession.org
		public SubmitterDetails member; // Details of thesession.org user who submitted the session		
		public String date;			// Date on which the session was submitted
		public String latitude; 	// The latitude of the venue
		public String longitude;	// The longitude of the venue
		public VenueDetails venue; 	// Details of the recording artist	
		public TownDetails town; 	// Details of the town where the session is located
		public AreaDetails area; 	// Details of the geographic area in which the session is located
		public CountryDetails country;	// Details of the country in which the session is located

		/**
		 * A wrapper for the details of the "member" within each "session" in the result set
		 * 
		 * @author Colman O'B
		 * @since 2017-02-01
		 */
		public class SubmitterDetails 
			{
			public int id;		// ID of the user within thesession.org
			public String name;	// Username of the user who submitted the session
			public String url;	// URL of the user's profile page on thesession.org
			}
		
		/**
		 * A wrapper for the details of the "venue" within each "session" in the result set
		 * 
		 * @author Colman O'B
		 * @since 2017-02-01
		 */
		public class VenueDetails 
			{
			public int id;				// ID for the venue in thesession.org
			public String name;			// Venue name
			public String telephone;	// Venue's phone number
			public String email;		// Venue's email address
			public String web;			// Venue's website/social media URL		
			}
		
		/**
		 *  A wrapper for the details of the "town" within each "session" in the result set
		 *  
		 * @author Colman
		 * @since 2017-02-01
		 */
		public class TownDetails 
			{
			public int id;		// ID for the town in thesession.org
			public String name;	// The town name
			}
		
		/**
		 *  A wrapper for the details of the "area" within each "session" in the result set
		 *  
		 * @author Colman.O'Brien
		 * @since 2017-02-01
		 */
		public class AreaDetails 
			{
			public int id;		// ID for the geographic area in thesession.org
			public String name;	// The name of the geographic area
			}
		
		/**
		 *  A wrapper for the details of the "country" within each "session" in the result set
		 *  
		 * @author Colman.O'Brien
		 * @since 2017-02-01
		 */
		public class CountryDetails 
			{
			public int id;		// ID for the country in thesession.org
			public String name;	// The country name
			}
		}
	}