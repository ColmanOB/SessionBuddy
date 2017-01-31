package individual_result_representation;

/**
 * A structure to hold an individual session from a set of search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-01-28
 */
public class SessionsSearchResult 
	{
	public String sessionID;
	public String sessionURL;
	public String submittedDate;
	
	public String latitude;
	public String longitude;
	
	public String submitterID;
	public String submitterUserName;
	public String submitterPageURL;
	
	public String venueID;
	public String venueName;
	public String venuePhone;
	public String venueEmail;
	public String venueWebsite;

	public String townID;
	public String townName;

	public String areaID;
	public String areaName;

	public String countryID;
	public String countryName;

	
	// TODO: Generate JavaDoc comment
	public SessionsSearchResult(String sessionID, String sessionURL, String submittedDate, String latitude, String longitude, String submitterID, String submitterUserName, String submitterPageURL, String artistID, String artistName, String artistPageURL)
		{
		this.sessionID = sessionID;
		this.sessionURL = sessionURL;
		this.submittedDate = submittedDate;
		
		this.latitude = latitude;
		this.longitude = longitude;
		
		this.submitterID = submitterID;
		this.submitterUserName = submitterUserName;
		this.submitterPageURL = submitterPageURL;
		
		// TODO: make whole new classes for venue, user, etc.
		this.venueID = venueID;
		this.venueName = venueName;
		this.venuePhone = venuePhone;
		this.venueEmail = venueEmail;
		this.venueWebsite = venueWebsite;

		this.townID = townID;
		this.townName = townName;

		this.areaID = areaID;
		this.areaName = areaName;

		this.countryID = countryID;
		this.countryName = countryName;
		}
	}
