package json_object_wrappers;

/**
 * Represents the set of metadata associated with a tune from thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class TuneDetails 
	{
	public String tuneID;
	public String tuneTitle;
	public String tuneType;
	public String tuneURL;
	public String submittedDate;

	/**
	 * @param tuneID
	 * @param tuneTitle
	 * @param tuneType
	 * @param tuneURL
	 * @param submittedDate
	 */
	public TuneDetails(String tuneID, String tuneTitle, String tuneType, String tuneURL, String submittedDate)
		{
		this.tuneID = tuneID;
		this.tuneTitle = tuneTitle;
		this.tuneType = tuneType;
		this.tuneURL = tuneURL;
		this.submittedDate = submittedDate;
		}
	}
