package json_object_wrappers;

/**
 * Represents the set of metadata associated with an event from thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class SettingDetails 
	{
	public String settingID;
	public String settingURL;
	public String settingKey;
	public String submittedDate;


	/**
	 * @param settingID
	 * @param settingKey
	 * @param settingURL
	 * @param submittedDate
	 */
	public SettingDetails(String settingID, String settingKey, String settingURL, String submittedDate)
		{
		this.settingID = settingID;
		this.settingURL = settingURL;
		this.settingKey = settingKey;
		this.submittedDate = submittedDate;
		}
	}
