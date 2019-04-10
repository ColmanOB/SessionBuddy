package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;
import sessionbuddy.wrappers.granularobjects.Comment;
import sessionbuddy.wrappers.granularobjects.DiscussionDetails;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A wrapper for the result returned when retrieving an individual discussion 
 * by its ID.
 * 
 * Holds meta-details of the discussion, details of the user who submitted the
 * discussion, and the comments within the dicussion
 * 
 * @author Colman
 * @since 2017-12-29
 */
public class ItemResultDiscussion
{
    /**
     * Metadata relating to the discussion as a whole
     */
    public DiscussionDetails discussionDetails;

    /**
     * The user who submitted the discussion
     */
    public User user;

    /**
     * An array of comments in the discussion
     */
    public ArrayList<Comment> comments;

    /**
     * Constructor that populates all fields
     * 
     * @param discussionDetails A DiscussionDetails object that has already been populated
     * @param user A User object populated with the details of the user who submitted the discussion
     * @param comments A Comment object populated with the details of an individual comment within the dicussion
     */
    public ItemResultDiscussion(DiscussionDetails discussionDetails, User user, ArrayList<Comment> comments)
    {
        this.discussionDetails = discussionDetails;
        this.user = user;
        this.comments = comments;
    }
}
