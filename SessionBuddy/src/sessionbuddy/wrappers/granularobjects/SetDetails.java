package sessionbuddy.wrappers.granularobjects;

/**
 * Represents a set of tunes, in the result set when retrieving the list of
 * latest sets
 * 
 * @author Colman O'B
 * @since 2018-02-16
 */
public class SetDetails
{
    /**
     * A numeric ID for the tune in thesession.org database
     */
    public int setID;
    
    /**
     * The name of the set in thesession.org database
     */
    public String setName;
    
    /**
     * The URL of the set's page on thesession.org website
     */
    public String setURL; 
    
    /**
     * The date the set was added to thesession.org
     */
    public String setDate;

    /**
     * Constructor that populates all fields
     * 
     * @param setID A numeric ID for the set in thesession.org database
     * @param setName The name of the set in thesession.org database
     * @param setURL The URL of the set's page on thesession.org website
     * @param setDate The date on which the set was added to thesession.org
     */
    public SetDetails(int setID, String setName, String setURL, String setDate)
    {
        this.setID = setID;
        this.setName = setName;
        this.setURL = setURL;
        this.setDate = setDate;
    }
}
