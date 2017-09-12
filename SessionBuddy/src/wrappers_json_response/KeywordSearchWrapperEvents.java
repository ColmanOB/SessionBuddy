package wrappers_json_response;


/**
 * A wrapper class for the response returned from the API at https://thesession.org when searching for events by keyword(s).
 * The fields and nested structure follow the format of the JSON structure of the response.
 * 
 * @author Colman O'B
 * @since 2017-09-12
 */
public class KeywordSearchWrapperEvents
	{
	public String q;		// Search query provided by user
	public String pages;	// Number of pages in the result set
	public String page;		// Current page number within the result set
	public String format;	// Format of the results (always JSON for this project)
	public EventsList[] events; // Array of the individual events returned by the search

	/**
	 * A wrapper for an individual event within the result set
	 * 
	 * @author Colman O'B
	 * @since 2017-02-01
	 */
	public class EventsList
		{
		public String id;		// ID for the event within thesession.org database
		public String name;		// Name of the event
		public String url;		// URL of the event's page on thesession.org
		public SubmitterDetails member; // Details of thesession.org user who submitted the event
		public String date;		// The date on which the event was submitted to thesession.org website	
		public String dtstart;	// Start date/time of the event
		public String dtend;	// End date/time of the event
		public String latitude;		// Latitude of the venue
		public String longitude;	// Longitude of the venue
		public VenueDetails venue;	// Venue details	
		public TownDetails town;	// Town where the event is happening
		public AreaDetails area;	// Geographic area of the event (county, province etc.)
		public CountryDetails country; // Country where the event is happening

		/**
		 * A wrapper for a session.org user who submitted an event
		 * 
		 * @author Colman
		 * @since 2017-09-12
		 */
		public class SubmitterDetails 
			{
			public int id;		// ID of the user in thesession.org database
			public String name;	// Username of the submitter
			public String url;	// URL of the user's profile page on thesession.org
			}
		
		/**
		 * A wrapper for the details of the venue where an event is taking place
		 * 
		 * @author Colman
		 * @since 2017-09-12
		 */
		public class VenueDetails 
			{
			public int id;			// A unique ID for the venue in thesession.org
			public String name;		// Venue name
			public String telephone;// Venue phone number
			public String email;	// Venue email address
			public String web;		// Venue website/social media page
			}
		
		/**
		 * A wrapper for the details of the town where an event is taking place
		 * 
		 * @author Colman
		 * @since 2017-09-12
		 */
		public class TownDetails 
			{
			public int id;		// ID for the town in thesession.org
			public String name;	// The town name
			}
	
		/**
		 * A wrapper for the details of the area (county, province etc.) where an event is taking place
		 * 
		 * @author Colman
		 * @since 2017-09-12
		 */
		public class AreaDetails 
			{
			public int id;		// ID for the geographic area in thesession.org
			public String name;	// Name of the geographic area
			}
		
		/**
		 * A wrapper for the details of the country where an event is taking place
		 * 
		 * @author Colman
		 * @since 2017-09-12
		 */
		public class CountryDetails 
			{
			public int id;		// ID for the country in thesession.org
			public String name;	// The country name
			}
		}
	}