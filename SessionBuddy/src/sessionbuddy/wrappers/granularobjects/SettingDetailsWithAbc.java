package sessionbuddy.wrappers.granularobjects;

/**
 * A wrapper for an individual setting of a tune
 * 
 * @author Colman
 * @since 2018-04-22
 */
public class SettingDetailsWithAbc 
	{
	/**
	 * The basic details of the set, e.g. ID, name, tune type etc.
	 */
	public SettingDetails settingDetails;
	
	/**
	 * The abc notation of the setting of the tune (i.e. the main body of the tune)
	 */
	public String abc;
	
	/**
	 * The user who submitted the particular setting of the tune
	 */
	public User member;
	
	
	/** 
	 * Constructor that populates all fields
	 * 
	 * @param settingDetails The basic details of the setting, e.g. ID, name, tune type etc.
	 * @param abc The abc notation of the setting of the tune (i.e. the main body of the tune)
	 * @param member The user who submitted the particular setting of the tune
	 */
	public SettingDetailsWithAbc(SettingDetails settingDetails, String abc, User member)
		{
		this.settingDetails = settingDetails;
		this.abc = abc;
		this.member = member;
		}
	}

