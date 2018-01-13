package sessionbuddy.wrappers.granularobjects;

/**
 * A wrapper for an individual setting of a tune
 * 
 * @author Colman
 * @since 2017-12-29
 */
public class TuneSetting 
	{
	/**
	 * The numeric identifier for the particular setting in thesession.org database
	 */
	public int id;
	
	/**
	 * The URL of the particular setting of the tune
	 */
	public String url;
	
	/**
	 * The key of the particular setting of the tune
	 */
	public String key;
	
	/**
	 * The abc notation of the setting of the tune (i.e. the main body of the tune)
	 */
	public String abc;
	
	/**
	 * The user who submitted the particular setting of the tune
	 */
	public User member;
	
	/**
	 * The date on which the particular setting was submitted to thesession.org
	 */
	public String date;
	
	/** 
	 * Constructor that populates all fields
	 * 
	 * @param id The numeric identifier for the particular setting in thesession.org database
	 * @param url The URL of the particular setting of the tune
	 * @param key The key of the particular setting of the tune
	 * @param abc The abc notation of the setting of the tune (i.e. the main body of the tune)
	 * @param member The user who submitted the particular setting of the tune
	 * @param date The date on which the particular setting was submitted to thesession.org
	 */
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

