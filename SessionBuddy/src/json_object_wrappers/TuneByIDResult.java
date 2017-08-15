package json_object_wrappers;


/**
 * @author Colman
 *
 */
public class TuneByIDResult 
	{
	public String id;		// The identifier for the particular setting in thesession.org database
	public String name;
	public String url;	// The URL of the particular setting of the tune
	
	public User member;
	
	public String date;
	public String type;	// The key of the particular setting of the tune
	public String tunebooks;
	public String recordings;
	
	public String[] aliases;
	
	public TuneSetting[] settings; // An array of different settings of the particular tune


	/**
	 * @param id
	 * @param name
	 * @param url
	 * @param member
	 * @param date
	 * @param type
	 * @param tunebooks
	 * @param recordings
	 * @param aliases
	 * @param settings
	 */
	public TuneByIDResult(String id, String name, String url, User member, String date, String type, String tunebooks, String recordings, String[] aliases, TuneSetting[] settings)
		{
		this.id = id;
		this.name = name;
		this.url = url;	
		this.member = member;
		this.date = date;
		this.type = type;
		this.tunebooks = tunebooks;
		this.recordings = recordings;
		this.aliases = aliases;
		this.settings = settings;
		}
}
