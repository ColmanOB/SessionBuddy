package sessionbuddy.wrappers.resultsets;

import sessionbuddy.wrappers.granularobjects.Area;
import sessionbuddy.wrappers.granularobjects.Coordinates;
import sessionbuddy.wrappers.granularobjects.Country;
import sessionbuddy.wrappers.granularobjects.EventDetails;
import sessionbuddy.wrappers.granularobjects.EventSchedule;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;

/**
 * A structure to hold details of an individual event from a set of search results
 * 
 * The search may be a keyword-based search or a search for recently-added events
 * 
 * @author Colman O'B
 * @since 2018-01-28
 */
public class SearchResultEvents
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
    public EventSchedule schedule;

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
