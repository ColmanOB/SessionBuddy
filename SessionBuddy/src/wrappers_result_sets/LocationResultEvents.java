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
 * A structure to hold an individual event from a set of search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-08-27
 */
public class LocationResultEvents 
	{
	public EventDetails details;	
	public User user;
	public EventSchedule schedule;
	public Coordinates coordinates;
	public Venue venue;
	public Town town;
	public Area area;
	public Country country;

	/**
	 * @param details
	 * @param user
	 * @param schedule
	 * @param coordinates
	 * @param venue
	 * @param town
	 * @param area
	 * @param country
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
