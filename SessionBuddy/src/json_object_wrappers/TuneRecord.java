package json_object_wrappers;

/**
 * Represents an individual tune, in the context of tunes that make up a track listing
 * 
 * @author Colman O'B
 * @since 2017-08-18
 */
public class TuneRecord 
	{
	public String tuneName;
	public String tuneID;
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
