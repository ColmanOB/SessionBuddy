package sessionbuddy.wrappers.granularobjects;

/**
 * Represents an individual tune, in the context of tunes that make up a track listing
 * 
 * @author Colman O'B
 * @since 2017-12-29
 */
public class TuneRecord 
	{
	/**
	 * The tune's title
	 */
	public String tuneName;
	
	/**
	 * The numeric ID of the tune as stored in thesession.org database
	 */
	public String tuneID;
	
	/**
	 * The URL of the tune's page on thesession.org
	 */
	public String tuneURL;

	/**
	 * Constructor that populates all fields
	 * 
	 * @param tuneName The tune's title
	 * @param tuneID The numeric ID of the tune as stored in thesession.org database
	 * @param tuneURL The URL of the tune's page on thesession.org
	 */
	public TuneRecord(String tuneName, String tuneID, String tuneURL)
		{
		this.tuneName = tuneName;
		this.tuneID = tuneID;
		this.tuneURL = tuneURL;
		}
	}
