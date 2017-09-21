package sessionbuddy.wrappers.granularobjects;

public class TuneSetting 
	{
	//Purpose: A wrapper for the individual settings of the particular tune
	public int id;		// The identifier for the particular setting in thesession.org database
	public String url;	// The URL of the particular setting of the tune
	public String key;	// The key of the particular setting of the tune
	public String abc;	// The abc notation of the setting of the tune (i.e. the main body of the tune)
	public User member;	// The user who submitted the particular setting of the tune
	public String date;	// The date on which the particular setting was submitted to thesession.org
	
	public TuneSetting(int id, String url, String key, String abc, User member, String date)
		{
		this.id = id;
		this.url = url;
		this.key = key;
		this.abc = abc;
		this.member = member;
		this.date = date;
		}
	}

