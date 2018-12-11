package sessionbuddy.wrappers.resultsets;

import sessionbuddy.wrappers.granularobjects.TuneDetailsWithDate;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A structure to hold an individual tune from a set of search results
 *
 * This can only be used for keyword-based searches - searches for recently added tunes
 * return results in a totally different format
 * 
 * @author Colman O'B
 * @since 2017-09-17
 *
 */
public class SearchResultTunes
{
    /**
     * Attributes of the tune itself
     */
    public TuneDetailsWithDate tuneDetails;

    /**
     * Attributes of the user who submitted the tune
     */
    public User submitter;

    /**
     * Constructor
     * 
     * @param details an already-populated TuneDetails object
     * @param submitter an already-populated User object
     */
    public SearchResultTunes(TuneDetailsWithDate details, User submitter)
    {
        this.tuneDetails = details;
        this.submitter = submitter;
    }
}
