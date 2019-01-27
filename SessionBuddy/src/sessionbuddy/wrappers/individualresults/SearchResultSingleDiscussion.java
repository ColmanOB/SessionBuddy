package sessionbuddy.wrappers.individualresults;

import sessionbuddy.wrappers.granularobjects.DiscussionDetails;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A structure to hold an individual discussion from a set of search results
 *
 * This can only be used for keyword-based searches - searches for recently added tunes
 * return results in a totally different format
 * 
 * @author Colman O'B
 * @since 2017-09-17
 *
 */
public class SearchResultSingleDiscussion
{
    /**
     * Attributes of the tune itself
     */
    public DiscussionDetails discussionDetails;

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
    public SearchResultSingleDiscussion(DiscussionDetails details, User submitter)
    {
        this.discussionDetails = details;
        this.submitter = submitter;
    }
}
