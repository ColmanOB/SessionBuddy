package sessionbuddy.wrappers.granularobjects;

/**
 * Represents the tune to which a setting belongs, in the result set when
 * retrieving the list of latest tunes (i.e. latest settings)
 * 
 * @author Colman O'B
 * @since 2017-12-29
 */
public class TuneDetails
{
    /**
     * A numeric ID for the tune in thesession.org database
     */
    public int tuneID;

    /**
     * The name of the tune in thesession.org database
     */
    public String tuneName;

    /**
     * The URL of the tune's page on thesession.org website
     */
    public String tuneURL;

    /**
     * Constructor that populates all fields
     * 
     * @param tuneID A numeric ID for the tune in thesession.org database
     * @param tuneName The name of the tune in thesession.org database
     * @param tuneURL The URL of the tune's page on thesession.org website
     */
    public TuneDetails(int tuneID, String tuneName, String tuneURL)
    {
        this.tuneID = tuneID;
        this.tuneName = tuneName;
        this.tuneURL = tuneURL;
    }
}
