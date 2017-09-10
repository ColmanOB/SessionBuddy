package wrappers_json_response;

/**
 * A wrapper class for the result set when retrieving an individual event by its ID.
 * For an example of the JSON response, see https://thesession.org/events/6169?format=json
 * The fields and nested structure follow the JSON structure of the response
 * 
 * @author Colman O'B
 * @since 2017-09-10
 */
public class ItemWrapperEvent 
	{
	public String format;	// Format of the response, i.e. JSON
	public String id;		// Unique ID of the event in theevent.org database
	public String name;		// Event name
	public String url;		// The URL of the event's page in thesession.org
	public Submitter member;	// The user who submitted the event
	public String date;		// The date the event was submitted
	public String dtstart;	// Event start date
	public String dtend;	// Event end date
	public String latitude; 	// Latitude of the venue
	public String longitude;	// Longitude of the venue
	
	public VenueDetails venue;	// Details of the venue
	
	public TownDetails town;	// Details of the town where the event is located
	public AreaDetails area;	// Details of the geographic area in which the event is located
	public CountryDetails country;	// Details of the country in which the event is located
	
	public Comment[] comments; // User-added comments on the event's page
	
			
	/**
	 * A wrapper for the details of theevent.org user who submitted the event
	 * 
	 * @author Colman
	 * @since 2017-09-10
	 */
	public class Submitter	
		{
		public int id;		// ID of the user within theevent.org database
		public String name;	// Username of the person who submitted the event
		public String url;	// URL of submitter's profile page on thesession.org
		}
	
	/**
	 * A wrapper for the details of venue where the event is taking place
	 * 
	 * @author Colman
	 * @since 2017-09-10
	 */
	public class VenueDetails 
		{
		public int id;			// ID for the venue in thesession.org
		public String name;		// Venue name
		public String telephone;	// Venue's phone number
		public String email;	// Venue's email address
		public String web;		// Venue's website/social media URL		
		}

	/**
	 * A wrapper for the details of the "town" within each "event" in the result set
	 *  
	 * @author Colman O'B
	 * @since 2017-09-10
	 */
	public class TownDetails 
		{
		public int id;		// ID for the town in thesession.org
		public String name;	// Town name
		}
	
	/**
	 *  A wrapper for the details of the "area" within each "event" in the result set
	 *  
	 * @author Colman O'B
	 * @since 2017-09-10
	 */
	public class AreaDetails 
		{
		public int id;		// ID for the geographic area in thesession.org
		public String name;	// Name of the geographic area
		}
	
	/**
	 *  A wrapper for the details of the "country" within each "event" in the result set
	 *  
	 * @author Colman O'B
	 * @since 2017-09-10
	 */
	public class CountryDetails 
		{
		public int id;		// A unique ID for the country
		public String name;	// The country name
		}

	/**
	 *  A wrapper for an individual comment on the event's page
	 *  
	 * @author Colman O'B
	 * @since 2017-09-10
	 */
	public class Comment
		{
		public String id;		// Unique ID in thesession.org for the comment
		public String url;		// URL of the individual comment
		public String subject;	// Comment subject line
		public String content;	// The actual text of the comment
		public Submitter member;	// The user who submitted the comment
		public String date;		// Date of submission of the comment
		}
	}
