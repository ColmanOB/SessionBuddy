package wrappers_granular_objects;

/**
 * Represents an individual tune setting returned when searching for a list of newest settings
 * 
 * @author Colman O'B
 * @since 2017-09-10
 */
public class LatestSettingDetails 
	{
	public String settingID;
	public String settingURL;
	public String key;
	public String date;


	public LatestSettingDetails(String settingID, String settingURL, String key, String date)
		{
		this.settingID = settingID;
		this.settingURL = settingURL;
		this.key = key;
		this.date = date;
		}
	}
