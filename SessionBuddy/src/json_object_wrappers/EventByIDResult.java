package json_object_wrappers;

import java.util.ArrayList;

/**
 * @author Colman
 * @since 2017-08-19
 */
public class EventByIDResult 
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
	
	public ArrayList<DiscussionComment> comments;

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
	public EventByIDResult(EventDetails eventDetails, User member, EventSchedule schedule, Coordinates coordinates, Venue venue, Town town, Area area, Country country, ArrayList<DiscussionComment> comments)
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
