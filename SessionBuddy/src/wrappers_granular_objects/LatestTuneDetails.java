package wrappers_granular_objects;

/**
 * Represents the tune to which a setting belongs, in the result set when retrieving the list of latest tunes (i.e. latest settings)
 * 
 * @author Colman O'B
 * @since 2017-09-10
 */
public class LatestTuneDetails 
	{
	public String tuneID;
	public String tuneName;
	public String tuneURL;


	public LatestTuneDetails(String tuneID, String tuneName, String tuneURL)
		{
		this.tuneID = tuneID;
		this.tuneName = tuneName;
		this.tuneURL = tuneURL;
		}
	}
