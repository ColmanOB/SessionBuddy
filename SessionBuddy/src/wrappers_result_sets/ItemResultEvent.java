package wrappers_result_sets;

import java.util.ArrayList;

import wrappers_granular_objects.Area;
import wrappers_granular_objects.Comment;
import wrappers_granular_objects.Coordinates;
import wrappers_granular_objects.Country;
import wrappers_granular_objects.EventDetails;
import wrappers_granular_objects.EventSchedule;
import wrappers_granular_objects.Town;
import wrappers_granular_objects.User;
import wrappers_granular_objects.Venue;

/**
 * @author Colman
 * @since 2017-08-19
 */
public class ItemResultEvent 
	{
	// Variables to hold data relating to the event
	public EventDetails eventDetails;
	public User member;
	public EventSchedule schedule;
	public Coordinates coordinates;

	public Venue venue;
	public Town town;
	public Area area;
	public Country country;
	
	public ArrayList<Comment> comments;

	/**
	 * Constructor method
	 * 
	 * @param eventDetails
	 * @param member
	 * @param schedule
	 * @param coordinates
	 * @param venue
	 * @param town
	 * @param area
	 * @param country
	 * @param comments
	 */
	public ItemResultEvent(EventDetails eventDetails, User member, EventSchedule schedule, Coordinates coordinates, Venue venue, Town town, Area area, Country country, ArrayList<Comment> comments)
		{
		this.eventDetails = eventDetails;
		this.member = member;
		this.schedule = schedule;
		this.coordinates = coordinates;
		this.venue = venue;
		this.town = town;
		this.area = area;
		this.country = country;
		this.comments = comments;
		}
}
