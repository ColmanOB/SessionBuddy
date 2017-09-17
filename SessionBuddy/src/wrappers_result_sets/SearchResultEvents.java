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
 * A structure to hold an individual event from a set of search results from thesession.org API.
 * The search may be a keyword-based search or a search for recently-added events
 * 
 * @author Colman O'B
 * @since 2017-09-16
 */
public class SearchResultEvents 
	{
	public EventDetails details;	// General details of the Event
	public User user;				// The event submitter
	public EventSchedule schedule;	// Event start and end date and time
	public Coordinates coordinates;	// Latitude and longitude of the venue
	public Venue venue;				// Details of the venue
	public Town town;				// Details of the town where the venue is located
	public Area area;				// Details of the geographic area where the town is located
	public Country country;			// Details of the country where the event is taking place

	/**
	 * Constructor
	 * 
	 * @param details a populated EventDetails object
	 * @param user a populated User object with the details of the event submitter
	 * @param schedule an EventSchedule object populated with the start and date date/time of the event
	 * @param coordinates a Coordinates object populated with the latitude and longitude of the venue
	 * @param venue a Venue object populated with the venue details
	 * @param town an already-populated Town object
	 * @param area an already-populated Area object
	 * @param country an already-populated Country object
	 */
	public SearchResultEvents(EventDetails details, User user, EventSchedule schedule, Coordinates coordinates, Venue venue, Town town, Area area, Country country)
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
