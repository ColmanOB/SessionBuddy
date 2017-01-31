package individual_result_representation;

/**
 * Represents an individual recording artist from thesession.org website
 * 
 * @author Colman O'B
 * @since 2017-01-31
 */
public class Artist 
	{
	public String artistID;
	public String artistName;
	public String artistPageURL;

	/**
	 * Constructor that populates all fields
	 * 
	 * @param artistID A unique numeric identifier assigned to each recording artist in thesession.org database
	 * @param artistName The recording artist/band name as stored in thesession.org database
	 * @param artistPageURL The URL of the artist's profile page on thesession.org
	 */
	public Artist(String artistID, String artistName, String artistPageURL)
		{
		this.artistID = artistID;
		this.artistName = artistName;
		this.artistPageURL = artistPageURL;
		}
	}
