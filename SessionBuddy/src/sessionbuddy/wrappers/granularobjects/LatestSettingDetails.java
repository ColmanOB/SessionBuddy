package sessionbuddy.wrappers.granularobjects;

/**
 * Represents an individual tune setting returned when searching for a list of newest tune settings.
 * 
 * It is important to note that this data is returned in a different format when compared to that returned when searching for a tune by name or ID etc.
 * 
 * @author Colman O'B
 * @since 2017-12-29
 */
public class LatestSettingDetails 
	{
	/**
	 * A numeric ID for the individual tune setting within thesession.org database
	 */
	public int settingID;
	
	/**
	 * The URL for the tune's page, focused on the particular setting in question
	 */
	public String settingURL;
	
	/**
	 * The key of the setting
	 */
	public String key;
	
	/**
	 * The date on which the setting was submitted to thesession.org
	 */
	public String date;


	public LatestSettingDetails(int settingID, String settingURL, String key, String date)
		{
		this.settingID = settingID;
		this.settingURL = settingURL;
		this.key = key;
		this.date = date;
		}
	}
