package main;

public class TunesSearchResultWrapper 
//Purpose: A wrapper class for the set of search results returned from thesession.org API
//This is a utility class used by TheSessionAPISearchResultsParser to facilitate access to any individual field within a set of JSON search results
//The fields and nested structure follow the format of the JSON structure of the search results from the API at https://thesession.org
//To count the number of tunes in the result set, call the .length method on the 'tunes' array
	{
	public String q;		// The search query submitted by the user
	public String pages;	// The number of pages in the result set
	public String page;		// The current page within the result set
	public String format;	// The format of the results (should always be JSON for this project)
	public TuneDetails[] tunes; // An array of the individual tunes returned by the search

	public class TuneDetails 
	//Purpose: A wrapper for the individual tunes within the search results returned from thesession.org API
		{
		public String id;		// A unique ID for the tune within thesession.org database
		public String name;	// The name of the tune within thesession.org database
		public String url;	// The URL of the tune's individual page on thesession.org website
		public String date;	// The date on which the tune was submitted to thesession.org website
		public String type;	// The type of tune, e.g. jig, reel etc.
		public SubmitterDetails member; // Details of thesession.org user who submitted the tune

		public class SubmitterDetails 
			//Purpose: A wrapper for the details of the "member" within each "tune" in the result set
			{
			public int id;		// A unique ID for the member of thesession.org who submitted the tune
			public String name;	// The user name of the member of thesession.org who submitted the tune
			public String url;	// The URL of the member's personal page on thesession.org website
			}
}
}