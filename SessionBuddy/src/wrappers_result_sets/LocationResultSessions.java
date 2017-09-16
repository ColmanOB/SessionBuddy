package wrappers_result_sets;

import wrappers_granular_objects.Area;
import wrappers_granular_objects.Coordinates;
import wrappers_granular_objects.Country;
import wrappers_granular_objects.SessionDetails;
import wrappers_granular_objects.Town;
import wrappers_granular_objects.User;
import wrappers_granular_objects.Venue;

/**
 * A structure to hold an individual session listing from a location-based search
 * 
 * @author Colman O'B
 * @since 2017-09-16
 */
public class LocationResultSessions 
	{
	public SessionDetails details;	// General information about the session
	public Coordinates coordinates;	// Latitude and longitude of the venue
	public User user;				// The submitter of the session
	public Venue venue;				// Details of the session venue
	public Town town;				// Details of the town where the venue is located
	public Area area;				// Details of the geographic area
	public Country country;			// Details of the country
	
	/**
	 * Constructor 
	 * 
	 * @param details an already-populated SessionDetails object
	 * @param coordinates a Coordinates object populated with a latitude and longitude
	 * @param user a populated User object representing the submitter of the session
	 * @param venue a Venue object populated with the venue details
	 * @param town an already-populated Town object
	 * @param area an already-populated Area object
	 * @param country an already-populated Country object
	 */
	public LocationResultSessions(SessionDetails details, Coordinates coordinates, User user, Venue venue, Town town, Area area, Country country)
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
