package sessionbuddy.wrappers.individualresults;

import sessionbuddy.wrappers.granularobjects.TuneDetailsWithDateAndTunebooks;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A structure to represent an individual result from a search 
 * for the latest tunes submitted to thesession.org
 * 
 * @author Colman O'B
 * @since 2018-01-28
 *
 */
public class PopularResultSingleTune
{
    /**
     * Details of the tune
     */
    public TuneDetailsWithDateAndTunebooks tuneDetails;

    /**
     * Details of the user who submitted the setting
     */
    public User submitter;

    /**
     * Constructor
     * 
     * @param tuneDetails a PopularTuneDetails object that has already been populated
     * @param submitter a User object populated with the details of the submitter
     */
    public PopularResultSingleTune(TuneDetailsWithDateAndTunebooks tuneDetails, User submitter)
    {
        this.tuneDetails = tuneDetails;
        this.submitter = submitter;
    }
}
