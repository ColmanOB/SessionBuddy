package sessionbuddy.wrappers.granularobjects;

/**
 * Represents a tune in the result set when retrieving the list of most popular tunes
 * 
 * @author Colman O'B
 * @since 2018-02-10
 */
public class PopularTuneDetails 
	{
	/**
	 * A numeric ID for the tune in thesession.org database
	 */
	public int tuneID;
	
	/**
	 * The name of the tune in thesession.org database
	 */
	public String tuneName;
	
	/**
	 * The URL of the tune's page on thesession.org website
	 */
	public String tuneURL;

	/**
	 * The date on which the tune was first submitted to thesession.org
	 */
	public String date; 
	
	/**
	 * The type of tune, i.e. jig, reel etc.
	 */
	public String type;
	
	/**
	 * The number of user's tunebooks on thesession.org to which this tune belongs
	 */
	public String tunebooks;
	
	/**
	 * Constructor that populates all fields
	 * 
	 * @param tuneID A numeric ID for the tune in thesession.org database
	 * @param tuneName The name of the tune in thesession.org database
	 * @param tuneURL The URL of the tune's page on thesession.org website
	 * @param date The date on which the tune was first submitted to thesession.org
	 * @param type The type of tune, i.e. jig, reel etc.
	 * @param tunebooks The number of user's tunebooks on thesession.org to which this tune belongs
	 */
	public PopularTuneDetails(int tuneID, String tuneName, String tuneURL, String date, String type, String tunebooks)
		{
		this.tuneID = tuneID;
		this.tuneName = tuneName;
		this.tuneURL = tuneURL;
		this.date = date;
		this.type = type;
		this.tunebooks = tunebooks;
		}
	}
