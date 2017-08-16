package json_object_wrappers;

import java.util.ArrayList;

/**
 * @author Colman
 * @since 2017-08-17
 */
public class TuneByIDResult 
	{
	// Variables to hold data relating to the tune as a whole
	public String id;	
	public String name;
	public String url;
	
	public User member;
	
	public String date;
	public String type;
	public String tunebooks;
	public String recordings;
	
	public ArrayList<String> aliases;
	
	public ArrayList<TuneSetting> settings; // An array of different settings of the particular tune

	/**
	 * Constructor method
	 * 
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
	public TuneByIDResult(String id, String name, String url, User member, String date, String type, String tunebooks, String recordings, ArrayList<String> aliases, ArrayList<TuneSetting> settings)
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
