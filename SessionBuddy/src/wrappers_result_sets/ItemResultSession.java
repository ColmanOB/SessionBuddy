package wrappers_result_sets;

import java.util.ArrayList;

import wrappers_granular_objects.Area;
import wrappers_granular_objects.Comment;
import wrappers_granular_objects.Coordinates;
import wrappers_granular_objects.Country;
import wrappers_granular_objects.SessionDetails;
import wrappers_granular_objects.Town;
import wrappers_granular_objects.User;
import wrappers_granular_objects.Venue;

/**
 * @author Colman
 * @since 2017-08-19
 */
public class ItemResultSession 
	{
	// Variables to hold data relating to the session
	public SessionDetails sessionDetails;
	public Coordinates coordinates;
	public User member;
	public Venue venue;
	public Town town;
	public Area area;
	public Country country;
	
	public ArrayList<String> schedule;
	public ArrayList<Comment> comments;


	/**
	 * Constructor method
	 * 
	 * @param sessionDetails
	 * @param coordinates
	 * @param member
	 * @param venue
	 * @param town
	 * @param area
	 * @param country
	 * @param schedule
	 * @param comments
	 */
	public ItemResultSession(SessionDetails sessionDetails, Coordinates coordinates, User member, Venue venue, Town town, Area area, Country country, ArrayList<String> schedule, ArrayList<Comment> comments)
		{
		this.sessionDetails = sessionDetails;
		this.coordinates = coordinates;
		this.member = member;
		this.venue = venue;
		this.town = town;
		this.area = area;
		this.country = country;
		this.schedule = schedule;
		this.comments = comments;
		}
}
