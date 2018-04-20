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
	 * The numeric ID of the tune as stored in thesession.org database
	 */
	public int tuneID;
	
	/**
	 * The tune's title
	 */
	public String tuneName;
	
	/**
	 * The URL of the tune's page on thesession.org
	 */
	public String tuneURL;

	/**
	 * Constructor that populates all fields
	 * 
	 * @param tuneID The numeric ID of the tune as stored in thesession.org database
	 * @param tuneName The tune's title
	 * @param tuneURL The URL of the tune's page on thesession.org
	 */
	public TuneRecord(int tuneID, String tuneName, String tuneURL)
		{
		this.tuneID = tuneID;
		this.tuneName = tuneName;
		this.tuneURL = tuneURL;
		}
	}
