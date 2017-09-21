package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper class for the response returned from the API at https://thesession.org when searching for an event by geographic location.
 * The fields and nested structure follow the format of the JSON structure of the session search results from the API
 * 
 * @author Colman O'B
 * @since 2017-09-12
 */
public class LocationSearchWrapperEvents
	{
	public String latlon;	// The latitude and longitude specified in the request, separated by a comma
	public String radius;	// No. of Km radius to include around the specified coordinates
	public String format;	// Format of the results (should always be JSON for this project)
	public String pages;	// Number of pages in the result set
	public String page;		// Current page number within the result set
	public eventsList[] events; // An array of the individual events returned by the search

	/**
	 * A wrapper for the individual events within the search results returned from thesession.org API
	 * 
	 * @author Colman O'B
	 * @since 2017-02-01
	 */
	public class eventsList
		{
		public String id;	// ID for the event within thesession.org
		public String name;	// Name of the event
		public String url;	// URL of the events's page on thesession.org
		public SubmitterDetails member; // Details of thesession.org user who submitted the session
		public String date;		// Date on which the session was submitted
		public String dtstart; 	// Start date/time for the event
		public String dtend;	// End date/time for the event
		public String latitude; 	// Latitude of the venue
		public String longitude;	// Longitude of the venue
		public VenueDetails venue; 	// Details of the venue
		public TownDetails town; 	// Details of the town where the event is located
		public AreaDetails area; 	// Details of the geographic area in which the event is located
		public CountryDetails country;	// Details of the country in which the event is located

		/**
		 * A wrapper for the details of the "member" within each "event" in the result set
		 * 
		 * @author Colman O'B
		 * @since 2017-02-01
		 */
		public class SubmitterDetails 
			{
			public int id;		// ID of the member of thesession.org who submitted the event
			public String name;	// Username of the submitter
			public String url;	// URL of the submitter's profile page on thesession.org
			}
		
		/**
		 * A wrapper for the details of the "venue" within each "session" in the result set
		 * 
		 * @author Colman O'B
		 * @since 2017-02-01
		 */
		public class VenueDetails 
			{
			public int id;				// ID of the venue within thesession.org
			public String name;			// Venue name
			public String telephone;	// Venue's phone number
			public String email;		// Venue's email address
			public String web;			// Venue's website/social media URL		
			}
		
		/**
		 *  A wrapper for the details of the "town" within each "session" in the result set
		 *  
		 * @author Colman.O'Brien
		 * @since 2017-02-01
		 */
		public class TownDetails 
			{
			public int id;		// ID for the town within thesession.org
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
			public int id;		// ID for the geographic area within thesession.org
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
			public int id;		// ID for the country within thesession.org
			public String name;	// The country name
			}
		}
	}