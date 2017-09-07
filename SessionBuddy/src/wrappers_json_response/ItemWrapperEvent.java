package wrappers_json_response;

public class ItemWrapperEvent 
	{
	// TODO: Fix up comments
	// Purpose: A wrapper class for an event listing
	// The fields and nested structure follow the format of the JSON structure of the API response
	public String format;	// The format of the response, i.e. JSON
	public String id;		// The unique ID of the recording in theevent.org database
	public String name;
	public String url;		// The URL of the recording's page in theevent.org
	public Submitter member; // The user who submitted the recording
	public String date;		// The date the recording was submitted
	public String dtstart; 
	public String dtend;
	public String latitude; // The latitude of the venue
	public String longitude;	// The longitude of the venue
	
	public VenueDetails venue; // Details of the recording artist
	
	public TownDetails town; // Details of the town where the event is located
	public AreaDetails area; // Details of the geographic area in which the event is located
	public CountryDetails country;	// Details of the country in which the event is located
	
	public Comment[] comments; // User-added comments on the recording's page
	
			
	public class Submitter
		//Purpose: A wrapper for the details of theevent.org user who submitted the recording	
		{
		public int id;		// A numeric identifier for the particular user within theevent.org database
		public String name;	// Theevent.org username of the person who submitted the recording
		public String url;	// The URL for the personal page of theevent.org user who submitted the recording
		}
	
	public class VenueDetails 
		{
		public int id;		// A unique ID for the venue
		public String name;	// The venue name
		public String telephone;	// The venue's phone number
		public String email;	// The venue's email address
		public String web;	// The venue's website/social media URL		
		}

	/**
	 *  A wrapper for the details of the "town" within each "event" in the result set
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
	 *  A wrapper for the details of the "area" within each "event" in the result set
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
	 *  A wrapper for the details of the "country" within each "event" in the result set
	 *  
	 * @author Colman.O'Brien
	 * @since 2017-02-01
	 */
	public class CountryDetails 
		{
		public int id;		// A unique ID for the country
		public String name;	// The country name
		}

	public class Comment
		//Purpose: A wrapper for a comment on a recording's page
		{
		public String id;
		public String url;	
		public String subject;
		public String content;
		public Submitter member;
		public String date;
		}
	}
