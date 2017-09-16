package wrappers_result_sets;

import wrappers_granular_objects.Area;
import wrappers_granular_objects.Coordinates;
import wrappers_granular_objects.Country;
import wrappers_granular_objects.EventDetails;
import wrappers_granular_objects.EventSchedule;
import wrappers_granular_objects.Town;
import wrappers_granular_objects.User;
import wrappers_granular_objects.Venue;

/**
 * A structure to hold an event listing returned from a location-based search for events
 * 
 * @author Colman O'B
 * @since 2017-09-15
 */
public class LocationResultEvents 
	{
	public EventDetails details;	// General details relating to the event
	public User user;				// Details of the user who submitted the event
	public EventSchedule schedule;	// Start and end dates of the event
	public Coordinates coordinates; // Latitude and longitude of the venue
	public Venue venue;				// Details of the venue
	public Town town;				// Details of the town where the venue is located
	public Area area;				// Details of the geographic area where the town is located
	public Country country;			// Details of the country where the event is taking place

	/**
	 * Constructor
	 * 
	 * @param details an already-populated EventDetails object
	 * @param user an already-populated User object representing the submitter of the event
	 * @param schedule an EventSchedule object with the start and end date/time of the event
	 * @param coordinates a Coordinates object with the latitude and longitude of the venue
	 * @param venue a populated Venue object
	 * @param town a populated Town object
	 * @param area a populated Area object
	 * @param country a populated country object
	 */
	public LocationResultEvents(EventDetails details, User user, EventSchedule schedule, Coordinates coordinates, Venue venue, Town town, Area area, Country country)
		{
		this.details = details;
		this.user = user;
		this.schedule = schedule;
		this.coordinates = coordinates;	
		this.venue = venue;
		this.town = town;
		this.area = area;
		this.country = country;
		}
	}
