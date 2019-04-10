package sessionbuddy.wrappers.individualresultitems;

import sessionbuddy.wrappers.granularobjects.TuneDetailsWithDateAndTunebooks;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A structure to represent an individual result from a search 
 * for the most popular tunes on thesession.org,
 * i.e. tunes which have been added to the largest number of tunebooks.
 * 
 * @author Colman O'B
 * @since 2019-04-10
 *
 */
public class TunePopular
{
    /**
     * Details of the tune
     */
    public TuneDetailsWithDateAndTunebooks tuneDetails;

    /**
     * Details of the user who submitted the setting
     */
    public User user;

    /**
     * Constructor
     * 
     * @param tuneDetails a PopularTuneDetails object that has already been populated
     * @param submitter a User object populated with the details of the submitter
     */
    public TunePopular(TuneDetailsWithDateAndTunebooks tuneDetails, User submitter)
    {
        this.tuneDetails = tuneDetails;
        this.user = submitter;
    }
}
