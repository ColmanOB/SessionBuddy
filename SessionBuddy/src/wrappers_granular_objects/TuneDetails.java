package wrappers_granular_objects;

/**
 * Represents the set of metadata associated with a tune from thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-09-20
 */
public class TuneDetails 
	{
	public String tuneID;			// Numeric identifier of the tune in thesession.org
	public String tuneTitle;		// Name of the tune
	public String tuneType;			// Tune type, i.e. jig, reel etc.
	public String tuneURL;			// URL of the tune's page on thesession.org
	public String submittedDate;	// Date of original submission of the tune

	/**
	 * Constructor method that populates all fields
	 * 
	 * @param tuneID Numeric identifier of the tune in thesession.org
	 * @param tuneTitle Name of the tune
	 * @param tuneType Tune type, i.e. jig, reel etc.
	 * @param tuneURL URL of the tune's page on thesession.org
	 * @param submittedDate Date of original submission of the tune
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
