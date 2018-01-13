package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.granularobjects.Area;
import sessionbuddy.wrappers.granularobjects.Comment;
import sessionbuddy.wrappers.granularobjects.Coordinates;
import sessionbuddy.wrappers.granularobjects.Country;
import sessionbuddy.wrappers.granularobjects.EventDetails;
import sessionbuddy.wrappers.granularobjects.EventSchedule;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;

/**
 * A wrapper for the result returned when retrieving an individual event by its ID.
 * Holds metadata relating to the event, its schedule, its location, and any comments on the event's page
 * 
 * @author Colman
 * @since 2017-12-29
 */
public class ItemResultEvent 
	{
	/**
	 * Metadata relating to the event
	 */
	public EventDetails eventDetails;
	
	/**
	 * The user who submitted the event
	 */
	public User member;
	
	/**
	 * The start and end dates of the event
	 */
	public EventSchedule schedule;
	
	/**
	 * The latitude and longitude of the venue
	 */
	public Coordinates coordinates;
	
	/**
	 * General details of the venue
	 */
	public Venue venue;
	
	/**
	 * Details of the town where the event will take place
	 */
	public Town town;
	
	/**
	 * 
	 */
	public Area area;					// Details of the geographic area where the event will take place
	/**
	 * 
	 */
	public Country country;				// Details of the country where the event will take place
	
	/**
	 * 
	 */
	public ArrayList<Comment> comments; // A list of comments on the event's page

	/**
	 * Constructor that populates all fields
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
