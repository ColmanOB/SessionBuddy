package sessionbuddy.wrappers.granularobjects;

/**
 * Represents an individual venue from thesession.org website
 * 
 * @author Colman O'B
 * @since 2017-01-31
 */
public class Venue
	{
	public String venueID;
	public String venueName;
	public String venuePhone;
	public String venueEmail;
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
	public Venue(String venueID, String venueName, String venuePhone, String venueEmail, String venueWebsite)
		{
		this.venueID = venueID;
		this.venueName = venueName;
		this.venuePhone = venuePhone;
		this.venueEmail = venueEmail;
		this.venueWebsite = venueWebsite;
		}
	}
