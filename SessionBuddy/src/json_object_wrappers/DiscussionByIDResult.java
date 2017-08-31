package json_object_wrappers;

import java.util.ArrayList;

/**
 * @author Colman
 * @since 2017-08-18
 */
public class DiscussionByIDResult 
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
	public DiscussionByIDResult(DiscussionDetails discussionDetails, User member,ArrayList<Comment> comments)
		{
		this.discussionDetails = discussionDetails;
		this.member = member;
		this.comments = comments;
		}
}
