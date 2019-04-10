package sessionbuddy.wrappers.individualresultitems;

import sessionbuddy.wrappers.granularobjects.SetDetails;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A structure to represent an individual result from a search 
 * for the latest tune sets submitted to thesession.org
 * 
 * @author Colman O'B
 * @since 2018-01-28
 *
 */
public class Set
{
    /**
     * Details of the tune set
     */
    public SetDetails setDetails;

    /**
     * Details of the user who submitted the set
     */
    public User user;

    /**
     * Constructor
     * 
     * @param setDetails a LatestSetDetails object that has already been populated
     * @param submitter a User object populated with the details of the submitter
     */
    public Set(SetDetails setDetails, User submitter)
    {
        this.setDetails = setDetails;
        this.user = submitter;
    }
}
