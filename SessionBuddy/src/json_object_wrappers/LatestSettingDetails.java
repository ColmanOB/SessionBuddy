package json_object_wrappers;

/**
 * Represents the set of metadata associated with a tune from thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class LatestSettingDetails 
	{
	public String tuneID;
	public String tuneName;
	public String tuneURL;

	/**
	 * Constructor method 
	 * 
	 * @param tuneID
	 * @param tuneTitle
	 * @param tuneType
	 * @param tuneURL
	 * @param submittedDate
	 */
	public LatestSettingDetails(String tuneID, String tuneName, String tuneURL)
		{
		this.tuneID = tuneID;
		this.tuneName = tuneName;
		this.tuneURL = tuneURL;
		}
	}
