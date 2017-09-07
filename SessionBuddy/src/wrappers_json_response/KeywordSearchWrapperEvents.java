package wrappers_json_response;
// TODO: Fix up the comments here, add JavaDoc comments, edit comments so they relate to Events, not Recordings
/**
 * A wrapper class for the response returned from the API at https://thesession.org
 * This is a utility class used by EventsSearchParser to facilitate access to any individual field within a set of JSON search results
 * The fields and nested structure follow the format of the JSON structure of the recordings search results from the API
 * * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class KeywordSearchWrapperEvents
	{
	public String q;		// The search query submitted by the user
	public String pages;	// The number of pages in the result set
	public String page;		// The current page within the result set
	public String format;	// The format of the results (should always be JSON for this project)
	public EventsList[] events; // An array of the individual recordings returned by the search

	/**
	 * A wrapper for the individual events within the search results returned from thesession.org API
	 * 
	 * @author Colman O'B
	 * @since 2017-02-01
	 */
	public class EventsList
		{
		public String id;		// A unique ID for the recording within thesession.org database
		public String name;	// The name of the recording within thesession.org database
		public String url;	// The URL of the recording's individual page on thesession.org website
		
		public SubmitterDetails member; // Details of thesession.org user who submitted the tune
		
		public String date;	// The date on which the recording was submitted to thesession.org website
			
		public String dtstart;
		public String dtend;
		
		public String latitude;
		public String longitude;
		
		public VenueDetails venue;		
		public TownDetails town;
		public AreaDetails area;
		public CountryDetails country;

		public class SubmitterDetails 
			{
			public int id;		// A unique ID for the member of thesession.org who submitted the recording
			public String name;	// The user name of the member of thesession.org who submitted the recording
			public String url;	// The URL of the member's personal page on thesession.org website
			}
		
		public class VenueDetails 
			{
			public int id;		// A unique ID for the recording artist
			public String name;	// The artist name
			public String telephone;
			public String email;
			public String web;	// The URL of the artist page on thesession.org website
			}
		
		public class TownDetails 
			{
			public int id;		// A unique ID for the town
			public String name;	// The town name
			}
	
		public class AreaDetails 
			{
			public int id;		// A unique ID for the geographic area
			public String name;	// The name of the geographic area
			}
		
		public class CountryDetails 
			{
			public int id;		// A unique ID for the country
			public String name;	// The country name
			}
		}
	}