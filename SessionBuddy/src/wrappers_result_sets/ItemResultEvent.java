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
 * A wrapper for the result returned when retrieving an individual event by its ID.
 * Holds metadata relating to the event, its schedule, its location, and any comments on the event's page
 * 
 * @author Colman
 * @since 2017-09-13
 */
public class ItemResultEvent 
	{
	public EventDetails eventDetails;	// Metadata relating to the event
	public User member;					// The user who submitted the event
	public EventSchedule schedule;		// The start and end dates of the event
	public Coordinates coordinates;		// The latitude and longitude of the venue
	public Venue venue;					// General details of the venue
	public Town town;					// Details of the town where the event will take place
	public Area area;					// Details of the geographic area where the event will take place
	public Country country;				// Details of the country where the event will take place
	
	public ArrayList<Comment> comments; // A list of comments on the event's page

	/**
	 * Constructor
	 * 
	 * @param eventDetails an EventDetails object that has already been populated
	 * @param member a User object representing the event submitter
	 * @param schedule an EventSchedule object populated with the start and end dates
	 * @param coordinates a Coordinates object populated with latitude and longitude
	 * @param venue a Venue object that has already been populated with any available venue details
	 * @param town a Town object that has already been populated
	 * @param area an Area object that has already been populated
	 * @param country a Country object that has already been populated
	 * @param comments an ArrayList of Comment objects that have already been populated
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
