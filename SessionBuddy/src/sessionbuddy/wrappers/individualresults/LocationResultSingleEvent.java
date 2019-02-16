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
 * A structure to hold an event listing returned from 
 * a location-based search for events
 * 
 * @author Colman O'B
 * @since 2018-01-28
 */
public class LocationResultSingleEvent
{
    /**
     * General details relating to the event
     */
    public EventDetails eventDetails;

    /**
     * Details of the user who submitted the event
     */
    public User user;

    /**
     * Start and end dates of the event
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
     * @param details an already-populated EventDetails object
     * @param user an already-populated User object representing the submitter of the event
     * @param schedule an Schedule object with the start and end date/time of the event
     * @param coordinates a Coordinates object with the latitude and longitude of the venue
     * @param venue a populated Venue object
     * @param town a populated Town object
     * @param area a populated Area object
     * @param country a populated country object
     */
    public LocationResultSingleEvent(EventDetails details, User user, Schedule schedule, Coordinates coordinates, Venue venue, Town town, Area area, Country country)
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
