package sessionbuddy.wrappers.granularobjects;

/**
 * Represents a set of metadata associated with a discussion from thesession.org database.
 * Does not include actual discussion comments.
 * 
 * @author Colman O'B
 * @since 2017-12-24
 */
public class DiscussionDetails 
	{
	/**
	 * The numeric ID of the discussion in thesession.org database
	 */
	public String discussionID;
	
	/**
	 * The title / subject line of the discussion
	 */
	public String discussionName;
	
	/**
	 * The URL of the discussion's page on thesession.org
	 */
	public String discussionURL;
	
	/**
	 * The date on which the discussion was originally submitted
	 */
	public String submittedDate;
	
	/**
	 * The current number of comments that make up the discussion
	 */
	public String numberOfComments;

	
	/**
	 * Constructor method to use when the number of comments is unknown, e.g. when retrieving a discussion by ID
	 * or retrieving discussions submitted by a particular user
	 * 
	 * @param discussionID the numeric ID of the discussion in thesession.org database
	 * @param discussionName the subject line of the discussion
	 * @param discussionURL the URL of the discussion's page on thesession.org
	 * @param submittedDate	the date on which the discussion was originally submitted
	 */
	public DiscussionDetails(String discussionID, String discussionName, String discussionURL, String submittedDate)
		{
		this.discussionID = discussionID;
		this.discussionName = discussionName;
		this.discussionURL = discussionURL;
		this.submittedDate = submittedDate;
		}
	
	/**
	 * Constructor that populates all fields
	 * 
	 * @param discussionID the numeric ID of the discussion in thesession.org database
	 * @param discussionName the subject line of the discussion
	 * @param discussionURL the URL of the discussion's page on thesession.org
	 * @param submittedDate	the date on which the discussion was originally submitted
	 * @param numberOfComments the current number of comments that make up the discussion
	 */
	public DiscussionDetails(String discussionID, String discussionName, String discussionURL, String submittedDate, String numberOfComments)
		{
		this(discussionID, discussionName, discussionURL, submittedDate);
		this.numberOfComments = numberOfComments;
		}

	}
