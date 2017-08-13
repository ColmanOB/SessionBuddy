package result_set_wrappers;

/**
 * A wrapper class for the response returned from the API at https://thesession.org
 * This is a utility class used by SessionsSearchParser to facilitate access to any individual field within a set of JSON search results
 * The fields and nested structure follow the format of the JSON structure of the session search results from the API
 * * 
 * @author Colman O'B
 * @since 2017-08-13
 */
public class SessionsSearchResultWrapper
	{
	public String q;		// The search query submitted by the user
	public String pages;	// The number of pages in the result set
	public String page;		// The current page within the result set
	public String format;	// The format of the results (should always be JSON for this project)
	public sessionsList[] sessions; // An array of the individual sessions returned by the search

	/**
	 * A wrapper for the individual sessions within the search results returned from thesession.org API
	 * 
	 * @author Colman O'B
	 * @since 2017-02-01
	 */
	public class sessionsList
		{
		public String id;		// A unique ID for the session within thesession.org database
		public String url;	// The URL of the sessions's individual page on thesession.org website	

		public SubmitterDetails member; // Details of thesession.org user who submitted the session
		
		public String date;	// The date on which the session was submitted to thesession.org website
		public String latitude; // The latitude of the venue
		public String longitude;	// The longitude of the venue
		
		public VenueDetails venue; // Details of the recording artist
		
		public TownDetails town; // Details of the town where the session is located
		public AreaDetails area; // Details of the geographic area in which the session is located
		public CountryDetails country;	// Details of the country in which the session is located

		/**
		 * A wrapper for the details of the "member" within each "session" in the result set
		 * 
		 * @author Colman O'B
		 * @since 2017-02-01
		 */
		public class SubmitterDetails 
			{
			public int id;		// A unique ID for the member of thesession.org who submitted the session
			public String name;	// The user name of the member of thesession.org who submitted the session
			public String url;	// The URL of the member's personal page on thesession.org website
			}
		
		/**
		 * A wrapper for the details of the "venue" within each "session" in the result set
		 * 
		 * @author Colman O'B
		 * @since 2017-02-01
		 */
		public class VenueDetails 
			{
			public int id;		// A unique ID for the venue
			public String name;	// The venue name
			public String telephone;	// The venue's phone number
			public String email;	// The venue's email address
			public String web;	// The venue's website/social media URL		
			}
		
		/**
		 *  A wrapper for the details of the "town" within each "session" in the result set
		 *  
		 * @author Colman.O'Brien
		 * @since 2017-02-01
		 */
		public class TownDetails 
			{
			public int id;		// A unique ID for the town
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
			public int id;		// A unique ID for the geographic area
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
			public int id;		// A unique ID for the country
			public String name;	// The country name
			}
		}
	}