package individual_result_representation;

/**
 * A structure to hold an individual session from a set of search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class SessionsSearchResult 
	{
	public SessionDetails details;	
	public Coordinates coordinates;
	public User user;
	public Venue venue;
	public Town town;
	public Area area;
	public Country country;

	/**
	 * @param details
	 * @param coordinates
	 * @param user
	 * @param venue
	 * @param town
	 * @param area
	 * @param country
	 */
	public SessionsSearchResult(SessionDetails details, Coordinates coordinates, User user, Venue venue, Town town, Area area, Country country)
		{
		this.details = details;
		this.coordinates = coordinates;	
		this.user = user;
		this.venue = venue;
		this.town = town;
		this.area = area;
		this.country = country;
		}
	}
