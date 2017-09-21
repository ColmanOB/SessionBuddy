package sessionbuddy.wrappers.resultsets;

import sessionbuddy.wrappers.granularobjects.Area;
import sessionbuddy.wrappers.granularobjects.Coordinates;
import sessionbuddy.wrappers.granularobjects.Country;
import sessionbuddy.wrappers.granularobjects.SessionDetails;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;

/**
 * A structure to hold an individual event from a set of search results from thesession.org API
 * The search may be a keyword-based search or a search for recently-added sessions
 * 
 * @author Colman O'B
 * @since 2017-09-16
 */
public class SearchResultSessions 
	{
	public SessionDetails details;	// General details about the session
	public Coordinates coordinates;	// Latitude and longitude of the session venue
	public User user;				// Details of the user who submitted the session
	public Venue venue;				// Details of the session venue
	public Town town;				// The town where the venue is located
	public Area area;				// The geographic area where the town is located
	public Country country;			// The country where the session is located
	
	/**
	 * Constructor
	 * 
	 * @param details a SessionDetails object that has already been populated
	 * @param coordinates a Coordinates object populated with a latitude and longitude
	 * @param user a User object that has already been populated with the details of the session submitter
	 * @param venue an already-populated Venue object
	 * @param town an already-populated Town object
	 * @param area an already-populated Area object
	 * @param country an already-populated Country object
	 */
	public SearchResultSessions(SessionDetails details, Coordinates coordinates, User user, Venue venue, Town town, Area area, Country country)
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
