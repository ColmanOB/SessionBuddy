package sessionbuddy.wrappers.granularobjects;

/**
 * Represents an individual recording artist or group from thesession.org website
 * 
 * @author Colman O'B
 * @since 2017-12-25
 */
public class Artist 
	{
	/**
	 * A unique numeric identifier assigned to the recording artist / group in thesession.org database
	 */
	public int artistID;
	
	/**
	 * The recording artist/band name as stored in thesession.org database
	 */
	public String artistName;
	
	/**
	 * The URL of the artist's profile page on thesession.org
	 */
	public String artistPageURL;

	/**
	 * Constructor that populates all fields
	 * 
	 * @param artistID A unique numeric identifier assigned to each recording artist in thesession.org database
	 * @param artistName The recording artist/band name as stored in thesession.org database
	 * @param artistPageURL The URL of the artist's profile page on thesession.org
	 */
	public Artist(int artistID, String artistName, String artistPageURL)
		{
		this.artistID = artistID;
		this.artistName = artistName;
		this.artistPageURL = artistPageURL;
		}
	}
