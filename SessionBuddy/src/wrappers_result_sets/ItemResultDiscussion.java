package wrappers_result_sets;

import java.util.ArrayList;

import wrappers_granular_objects.Comment;
import wrappers_granular_objects.DiscussionDetails;
import wrappers_granular_objects.User;

/**
 * A wrapper for the result returned when retrieving an individual discussion by its ID.
 * Holds meta-details of the discussion, details of the user who submitted the discussion, and the comments within the dicussion
 * 
 * @author Colman
 * @since 2017-09-13
 */
public class ItemResultDiscussion 
	{
	public DiscussionDetails discussionDetails;	// Metadata relating to the discussion as a whole
	public User member;							// The user who submitted the discussion
	public ArrayList<Comment> comments;			// An array of comments in the discussion


	/**
	 * Constructor
	 * 
	 * @param discussionDetails A DiscussionDetails object that has already been populated
	 * @param member A User object populated with the details of the user who submitted the discussion
	 * @param comments A Comment object populated with the details of an individual comment within the dicussion
	 */
	public ItemResultDiscussion(DiscussionDetails discussionDetails, User member, ArrayList<Comment> comments)
		{
		this.discussionDetails = discussionDetails;
		this.member = member;
		this.comments = comments;
		}
	}
