package sessionbuddy.wrappers.granularobjects;

/**
 * Represents an individual venue record from thesession.org website
 * 
 * @author Colman O'B
 * @since 2017-12-29
 */
public class Venue
	{
	/**
	 * A unique numeric identifier given to each venue in thesession.org database
	 */
	public int venueID;
	
	/**
	 * The venue's name, as stored in thesession.org database
	 */
	public String venueName;
	
	/**
	 * The venue's phone number, if present
	 */
	public String venuePhone;
	
	/**
	 * The venue's email address, if present
	 */
	public String venueEmail;
	
	/**
	 * The venue's website / social media page, if present
	 */
	public String venueWebsite;

	/**
	 * Constructor that populates all fields at once
	 * 
	 * @param venueID A unique numeric identifier given to each venue in thesession.org database
	 * @param venueName The venue's name, as stored in thesession.org database
	 * @param venuePhone The venue's phone number, if present
	 * @param venueEmail The venue's email address, if present
	 * @param venueWebsite The venue's website / social media page, if present
	 */
	public Venue(int venueID, String venueName, String venuePhone, String venueEmail, String venueWebsite)
		{
		this.venueID = venueID;
		this.venueName = venueName;
		this.venuePhone = venuePhone;
		this.venueEmail = venueEmail;
		this.venueWebsite = venueWebsite;
		}
	}
