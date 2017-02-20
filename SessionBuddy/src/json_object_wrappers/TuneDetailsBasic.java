package json_object_wrappers;

/**
 * Represents the set of metadata associated with a tune from thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class TuneDetailsBasic 
	{
	public String tuneID;
	public String tuneTitle;
	public String tuneURL;

	/**
	 * @param tuneID
	 * @param tuneTitle
	 * @param tuneType
	 * @param tuneURL
	 * @param submittedDate
	 */
	public TuneDetailsBasic(String tuneID, String tuneTitle, String tuneURL)
		{
		this.tuneID = tuneID;
		this.tuneTitle = tuneTitle;
		this.tuneURL = tuneURL;
		}
	}
