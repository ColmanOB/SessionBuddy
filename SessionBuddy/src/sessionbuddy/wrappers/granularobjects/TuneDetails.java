package sessionbuddy.wrappers.granularobjects;

/**
 * Represents the set of metadata associated with a tune from thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-12-29
 */
public class TuneDetails 
	{
	/**
	 * Numeric identifier of the tune in thesession.org
	 */
	public int tuneID;
	
	/**
	 * Name of the tune
	 */
	public String tuneTitle;
	
	/**
	 * Tune type, i.e. jig, reel etc.
	 */
	public String tuneType;
	
	/**
	 * URL of the tune's page on thesession.org
	 */
	public String tuneURL;
	
	/**
	 * Date of original submission of the tune
	 */
	public String submittedDate;

	/**
	 * Constructor method that populates all fields
	 * 
	 * @param tuneID Numeric identifier of the tune in thesession.org
	 * @param tuneTitle Name of the tune
	 * @param tuneType Tune type, i.e. jig, reel etc.
	 * @param tuneURL URL of the tune's page on thesession.org
	 * @param submittedDate Date of original submission of the tune
	 */
	public TuneDetails(int tuneID, String tuneTitle, String tuneType, String tuneURL, String submittedDate)
		{
		this.tuneID = tuneID;
		this.tuneTitle = tuneTitle;
		this.tuneType = tuneType;
		this.tuneURL = tuneURL;
		this.submittedDate = submittedDate;
		}
	}
