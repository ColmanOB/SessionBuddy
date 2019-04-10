package sessionbuddy.wrappers.individualresultitems;

import sessionbuddy.wrappers.granularobjects.Area;
import sessionbuddy.wrappers.granularobjects.Coordinates;
import sessionbuddy.wrappers.granularobjects.Country;
import sessionbuddy.wrappers.granularobjects.EventDetails;
import sessionbuddy.wrappers.granularobjects.Schedule;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;

/**
 * A structure to hold an individual event from a set of search results
 *
 * @author Colman O'B
 * @since 2018-04-10
 *
 */
public class Event
{
    /**
     * General details of the Event
     */
    public EventDetails eventDetails;
    
    /**
     * The event submitter
     */
    public User user;
    
    /**
     * Event start and end date and time
     */
    public Schedule schedule;
    
    /**
     * Latitude and longitude of the venue
     */
    public Coordinates coordinates;
    
    /**
     * Details of the venue
     */
    public Venue venue;
    
    /**
     * Details of the town where the venue is located
     */
    public Town town;
    
    /**
     * Details of the geographic area where the town is located
     */
    public Area area;
    
    /**
     * Details of the country where the event is taking place
     */
    public Country country;

    public Event(EventDetails details, User user, Schedule schedule, Coordinates coordinates, Venue venue, Town town, Area area, Country country)
    {
        this.eventDetails = details;
        this.user = user;
        this.schedule = schedule;
        this.coordinates = coordinates;
        this.venue = venue;
        this.town = town;
        this.area = area;
        this.country = country;
    }
}