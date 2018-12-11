package sessionbuddy.wrappers.resultsets;

import sessionbuddy.wrappers.granularobjects.Schedule;
import sessionbuddy.wrappers.granularobjects.TripDetails;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A structure to hold an individual trip from a set of search results.
 * 
 * @author Colman O'B
 * @since 2018-12-08
 *
 */
public class SearchResultTrips
{
    /**
     * Attributes of the trip itself
     */
    public TripDetails tripDetails;
    
    /**
     * Start and End date of the trip
     */
    public Schedule tripSchedule;

    /**
     * Attributes of the user who submitted the tune
     */
    public User submitter;

    /**
     * Constructor
     * 
     * @param details an already-populated TripDetails object
     * @param tripSchedule an already-populated Schedule object
     * @param submitter an already-populated User object
     */
    public SearchResultTrips(TripDetails details, Schedule tripSchedule, User submitter)
    {
        this.tripDetails = details;
        this.tripSchedule = tripSchedule;
        this.submitter = submitter;
    }
}
