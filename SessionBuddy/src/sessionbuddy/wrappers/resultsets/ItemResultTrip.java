package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;
import sessionbuddy.wrappers.granularobjects.Comment;
import sessionbuddy.wrappers.granularobjects.Coordinates;
import sessionbuddy.wrappers.granularobjects.Schedule;
import sessionbuddy.wrappers.granularobjects.TripDetails;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A wrapper for the result returned when retrieving an individual trip by its ID.
 * 
 * @author Colman
 * @since 2019-04-10
 */
public class ItemResultTrip
{
    /**
     * Metadata relating to the trip
     */
    public TripDetails tripDetails;
    
    /**
     * The start and end dates of the trip
     */
    public Schedule tripSchedule;

    /**
     * Latitude and longitude of the trip destination
     */
    public Coordinates coordinates;

    /**
     * The user who submitted the trip
     */
    public User user;

    /**
     * A list of comments on the trip's page on thesession.org
     */
    public ArrayList<Comment> comments;


    /**
     * Constructor method
     * 
     * @param tripDetails a TripDetails object that has already been populated
     * @param tripSchedule an already-populated Schedule object
     * @param coordinates a Coordinates object populated with latitude and longitude
     * @param user a User object representing the trip submitter
     * @param comments an ArrayList of Comment objects that have already been populated
     */
    public ItemResultTrip(TripDetails tripDetails, Schedule tripSchedule, Coordinates coordinates, User user, ArrayList<Comment> comments)
    {
        this.tripDetails = tripDetails;
        this.tripSchedule = tripSchedule;
        this.coordinates = coordinates;
        this.user = user;
        this.comments = comments;
    }
}
