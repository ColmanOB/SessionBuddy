package wrappers_granular_objects;

/**
 * Represents the set of metadata associated with a tune from thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class LatestTuneDetails 
	{
	public String tuneID;
	public String tuneURL;
	public String key;
	public String date;

	/**
	 * Constructor method 
	 * 
	 * @param tuneID
	 * @param tuneTitle
	 * @param tuneType
	 * @param tuneURL
	 * @param submittedDate
	 */
	public LatestTuneDetails(String tuneID, String tuneURL, String key, String date)
		{
		this.tuneID = tuneID;
		this.tuneURL = tuneURL;
		this.key = key;
		this.date = date;
		}
	}
