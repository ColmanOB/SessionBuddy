package wrappers_result_sets;

import java.util.ArrayList;

import wrappers_granular_objects.Comment;
import wrappers_granular_objects.DiscussionDetails;
import wrappers_granular_objects.User;

/**
 * @author Colman
 * @since 2017-08-18
 */
public class ItemResultDiscussion 
	{
	// Variables to hold data relating to the discussion as a whole
	public DiscussionDetails discussionDetails;
	public User member;
		
	// An array of comments in the discussion
	public ArrayList<Comment> comments; 


	/**
	 * Constructor method
	 * 
	 * @param discussionDetails
	 * @param member
	 * @param comments
	 */
	public ItemResultDiscussion(DiscussionDetails discussionDetails, User member,ArrayList<Comment> comments)
		{
		this.discussionDetails = discussionDetails;
		this.member = member;
		this.comments = comments;
		}
}
