package wrappers_json_response;


/**
 * A wrapper class for a session listing. The fields and nested structure follow the format of the JSON structure of the API response
 * 
 * @author Colman
 * @since 2017-09-11
 */
public class ItemWrapperSession 
	{
	public String format;		// Format of the response, i.e. JSON
	public String id;			// ID of the session in thesession.org database
	public String url;			// URL of the session's page in thesession.org
	public Submitter member; 	// User who submitted the session
	public String date;			// Date the session was submitted
	public String latitude; 	// Latitude of the venue
	public String longitude;	// Longitude of the venue
	
	public VenueDetails venue; 	// Details of the venue
	
	public TownDetails town; 	// Details of the town where the session is located
	public AreaDetails area; 	// Details of the geographic area in which the session is located
	public CountryDetails country;	// Details of the country in which the session is located
	
	public String[]	schedule; 	// The day(s) on which the session takes place
	public Comment[] comments; 	// User-added comments on the session's page
	
			
	/**
	 * A wrapper for the details of thesession.org user who submitted the session	
	 * 
	 * @author Colman
	 * @since 2017-09-11
	 */
	public class Submitter	
		{
		public int id;		// ID of the user within thesession.org database
		public String name;	// Username of the person who submitted the session
		public String url;	// Profile page of the user who submitted the session
		}
	
	/**
	 * A wrapper for the venue details
	 * 
	 * @author Colman
	 * @since 2017-09-11
	 */
	public class VenueDetails 
		{
		public int id;			// ID for the venue
		public String name;		// Venue name
		public String telephone;	// Venue's phone number
		public String email;	// Venue's email address
		public String web;		// Venue's website/social media URL		
		}

	/**
	 *  A wrapper for the details of the town within each session in the result set
	 *  
	 * @author Colman.O'Brien
	 * @since 2017-09-11
	 */
	public class TownDetails 
		{
		public int id;		// ID for the town in thesession.org
		public String name;	// The town name
		}
	
	/**
	 *  A wrapper for the details of the "area" within each session in the result set
	 *  
	 * @author Colman.O'Brien
	 * @since 2017-02-01
	 */
	public class AreaDetails 
		{
		public int id;		// ID for the geographic area in thesession.org
		public String name;	// Name of the geographic area
		}
	
	/**
	 *  A wrapper for the details of the country within each "session" in the result set
	 *  
	 * @author Colman.O'Brien
	 * @since 2017-02-01
	 */
	public class CountryDetails 
		{
		public int id;		// ID for the country in thesession.org
		public String name;	// Country name
		}

	/**
	 * A wrapper for an individual comment on a session's page
	 * 
	 * @author Colman
	 * @since 2017-09-11
	 */
	public class Comment
		{
		public String id;		// ID of the comment within thesession.org
		public String url;		// URL of the individual comment
		public String subject;	// Subject line of the comment
		public String content;	// The actual text of the comment
		public Submitter member;	// Submitter of the comment
		public String date;		// Date the comment was submitted
		}
	}
