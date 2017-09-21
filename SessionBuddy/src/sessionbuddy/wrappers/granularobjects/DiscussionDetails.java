package sessionbuddy.wrappers.granularobjects;

/**
 * Represents the set of metadata associated with a discussion from thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-09-20
 */
public class DiscussionDetails 
	{
	public String discussionID;		// Numeric ID of the discussion in thesession.org database
	public String discussionName;	// Subject line of the discussion
	public String discussionURL;	// URL of the discussion's page on thesession.org
	public String submittedDate;	// Date the discussion was originally submitted
	public String numberOfComments;	// The current number of comments that make up the disccusion


	/**
	 * Constructor that populates all fields
	 * 
	 * @param discussionID numeric ID of the discussion in thesession.org database
	 * @param discussionName subject line of the discussion
	 * @param discussionURL URL of the discussion's page on thesession.org
	 * @param submittedDate	date the discussion was originally submitted
	 * @param numberOfComments the current number of comments that make up the discussion
	 */
	public DiscussionDetails(String discussionID, String discussionName, String discussionURL, String submittedDate, String numberOfComments)
		{
		this.discussionID = discussionID;
		this.discussionName = discussionName;
		this.discussionURL = discussionURL;
		this.submittedDate = submittedDate;
		this.numberOfComments = numberOfComments;
		}
	
	/**
	 * Constructor method to use when the number of comments is unknown, e.g. when retrieving a discussion by ID
	 * 
	 * @param discussionID numeric ID of the discussion in thesession.org database
	 * @param discussionName subject line of the discussion
	 * @param discussionURL URL of the discussion's page on thesession.org
	 * @param submittedDate	date the discussion was originally submitted
	 */
	public DiscussionDetails(String discussionID, String discussionName, String discussionURL, String submittedDate)
		{
		this.discussionID = discussionID;
		this.discussionName = discussionName;
		this.discussionURL = discussionURL;
		this.submittedDate = submittedDate;
		}

	}
