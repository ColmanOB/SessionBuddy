package sessionbuddy.wrappers.granularobjects;

/**
 * Represents a set of tunes, in the result set when retrieving the list of
 * latest sets
 * 
 * @author Colman O'B
 * @since 2018-12-11
 */
public class TripDetails
{
    /**
     * A numeric ID for the trip in thesession.org database
     */
    public int tripID;
    
    /**
     * The name of the trip in thesession.org database
     */
    public String tripName;
    
    /**
     * The URL of the trip page on thesession.org website
     */
    public String tripURL; 
    
    /**
     * The date the trip was added to thesession.org
     */
    public String submittedDate;

    /**
     * Constructor that populates all fields
     * 
     * @param tripID A numeric ID for the trip in thesession.org database
     * @param tripName The name of the trip in thesession.org database
     * @param tripURL The URL of the trip page on thesession.org website
     * @param submittedDate The date on which the trip was added to thesession.org
     */
    public TripDetails(int tripID, String tripName, String tripURL, String submittedDate)
    {
        this.tripID = tripID;
        this.tripName = tripName;
        this.tripURL = tripURL;
        this.submittedDate = submittedDate;
    }
}
