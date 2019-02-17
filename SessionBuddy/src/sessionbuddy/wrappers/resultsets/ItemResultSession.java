package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;
import sessionbuddy.wrappers.granularobjects.Area;
import sessionbuddy.wrappers.granularobjects.Comment;
import sessionbuddy.wrappers.granularobjects.Coordinates;
import sessionbuddy.wrappers.granularobjects.Country;
import sessionbuddy.wrappers.granularobjects.SessionDetails;
import sessionbuddy.wrappers.granularobjects.Town;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.granularobjects.Venue;

/**
 * A wrapper for the result returned when retrieving an individual session by its ID.
 * 
 * @author Colman
 * @since 2018-01-28
 */
public class ItemResultSession
{
    /**
     * Metadata relating to the session
     */
    public SessionDetails sessionDetails;

    /**
     * Latitude and longitude of the venue
     */
    public Coordinates coordinates;

    /**
     * The user who submitted thesession
     */
    public User member;

    /**
     * General details of the venue
     */
    public Venue venue;

    /**
     * Details of the town where the session takes place
     */
    public Town town;

    /**
     * Details of the geographic area where the town is located
     */
    public Area area;

    /**
     * Details of the country where the session is located
     */
    public Country country;

    /**
     * Days of the week on which the session takes place
     */
    public ArrayList<String> schedule;

    /**
     * A list of comments on the session's page on thesession.org
     */
    public ArrayList<Comment> comments;


    /**
     * Constructor method
     * 
     * @param sessionDetails a SessionDetails object that has already been populated
     * @param coordinates a Coordinates object populated with latitude and longitude
     * @param member a User object representing the event submitter
     * @param venue a Venue object that has already been populated with any available venue details
     * @param town a Town object that has already been populated
     * @param area an Area object that has already been populated
     * @param country a Country object that has already been populated
     * @param schedule the days of the week that the session takes place
     * @param comments an ArrayList of Comment objects that have already been populated
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
