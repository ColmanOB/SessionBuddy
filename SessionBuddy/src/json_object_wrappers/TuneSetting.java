package json_object_wrappers;

public class TuneSetting 
	{
	//Purpose: A wrapper for the individual settings of the particular tune
	public int id;		// The identifier for the particular setting in thesession.org database
	public String url;	// The URL of the particular setting of the tune
	public String key;	// The key of the particular setting of the tune
	public String abc;	// The abc notation of the setting of the tune (i.e. the main body of the tune)
	public User member;	// The user who submitted the particular setting of the tune
	public String date;	// The date on which the particular setting was submitted to thesession.org
	}

