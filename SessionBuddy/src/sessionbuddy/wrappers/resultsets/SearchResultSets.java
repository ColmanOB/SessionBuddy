package sessionbuddy.wrappers.resultsets;

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
public class SearchResultSets
{
    /**
     * Details of the tune set
     */
    public SetDetails setDetails;

    /**
     * Details of the user who submitted the set
     */
    public User submitter;

    /**
     * Constructor
     * 
     * @param setDetails a LatestSetDetails object that has already been populated
     * @param submitter a User object populated with the details of the submitter
     */
    public SearchResultSets(SetDetails setDetails, User submitter)
    {
        this.setDetails = setDetails;
        this.submitter = submitter;
    }
}
