package result_set_wrappers;

public class TuneByIDWrapper 
{
	//Purpose: A wrapper class for a tune entry, including the list of settings of one particular tune returned from thesession.org API
	//The fields and nested structure follow the format of the JSON structure of the search results
	public String format;	// The format of the response, i.e. JSON
	public String id;		// The unique ID of the tune itself in thesession.org database
	public String name;		// The name of the tune in thesession.org database
	public String url;		// The URL of the particular tune's page on thesession.org website
	public Submitter member; // The user who originally submitted the tune
	public String date;		// The date of initial tune submission to thesession.org
	public String type;		// The type of the tune, e.g. jig, reel etc.
	public String tunebooks;	// The number of user's personal tunebooks on thesession.org to which the tune has been added
	public String recordings;	// The number of recordings in theession.org database with a tune of this name
	public String[]	aliases;	// Known alternative names for the tune
	public Setting[] settings; // An array of different settings of the particular tune
	public Comment[] comments;

		public class Setting
			//Purpose: A wrapper for the individual settings of the particular tune
			{
			public String id;		// The identifier for the particular setting in thesession.org database
			public String url;	// The URL of the particular setting of the tune
			public String key;	// The key of the particular setting of the tune
			public String abc;	// The abc notation of the setting of the tune (i.e. the main body of the tune)
			public Submitter member; // The user who submitted the particular setting of the tune
			public String date;	// The date on which the particular setting was submitted to thesession.org
			}
			
		public class Submitter
			//Purpose: A wrapper for the details of thesession.org user who submitted the tune itself, or the particular setting
			{
			public int id;		// A numeric identifier for the particular user within thesession.org database
			public String name;	// Thesession.org username of the person who submitted the tune or setting
			public String url;	// The URL for the personal page of thesession.org user who submitted the tune or setting
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
