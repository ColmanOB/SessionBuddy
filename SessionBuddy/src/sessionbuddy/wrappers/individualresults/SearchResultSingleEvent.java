package sessionbuddy.wrappers.individualresults;

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
 * This can only be used for keyword-based searches - searches for recently added tunes
 * return results in a totally different format
 * 
 * @author Colman O'B
 * @since 2017-09-17
 *
 */
public class SearchResultSingleEvent
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
    
    /**
     * Constructor
     * 
     * @param details an already-populated TuneDetails object
     * @param submitter an already-populated User object
     */
    /**
     * Constructor
     * 
     * @param details a populated EventDetails object
     * @param user a populated User object with the details of the event submitter
     * @param schedule an Schedule object populated with the start and date date/time of the event
     * @param coordinates a Coordinates object populated with the latitude and longitude of the venue
     * @param venue a Venue object populated with the venue details
     * @param town an already-populated Town object
     * @param area an already-populated Area object
     * @param country an already-populated Country object
     */
    public SearchResultSingleEvent(EventDetails details, User user, Schedule schedule, Coordinates coordinates, Venue venue, Town town, Area area, Country country)
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
