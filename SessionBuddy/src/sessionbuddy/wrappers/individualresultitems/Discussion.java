package sessionbuddy.wrappers.individualresultitems;

import sessionbuddy.wrappers.granularobjects.DiscussionDetails;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A structure to hold an individual discussion from a set of search results
 *
 * @author Colman O'B
 * @since 2018-04-10
 *
 */
public class Discussion
{
    /**
     * Attributes of the discussion itself
     */
    public DiscussionDetails discussionDetails;

    /**
     * Attributes of the user who submitted the discussion
     */
    public User user;

    /**
     * Constructor
     * 
     * @param details an already-populated DiscussionDetails object
     * @param user an already-populated User object
     */
    public Discussion(DiscussionDetails details, User user)
    {
        this.discussionDetails = details;
        this.user = user;
    }
}
