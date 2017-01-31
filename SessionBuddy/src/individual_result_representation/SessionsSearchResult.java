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
	
	public Coordinates coordinates;
	public User user;
	public Venue venue;
	public Town town;
	public Area area;
	public Country country;

	
	// TODO: Generate JavaDoc comment
	public SessionsSearchResult(String sessionID, String sessionURL, String submittedDate, Coordinates coordinates, User user, Venue venue, Town town, Area area, Country country, String artistName, String artistPageURL)
		{
		this.sessionID = sessionID;
		this.sessionURL = sessionURL;
		this.submittedDate = submittedDate;
		
		this.coordinates = coordinates;	
		this.user = user;
		this.venue = venue;
		this.town = town;
		this.area = area;
		this.country = country;
		}
	}
