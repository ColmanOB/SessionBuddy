package json_object_wrappers;

/**
 * Represents the set of metadata associated with a discussion from thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class DiscussionDetails 
	{
	public String discussionID;
	public String discussionName;
	public String discussionURL;
	public String submittedDate;
	public String numberOfComments;


	/**
	 * @param dicussionID
	 * @param discussionName
	 * @param discussionURL
	 * @param submittedDate
	 * @param numberOfComments
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
	 * @param discussionID
	 * @param discussionName
	 * @param discussionURL
	 * @param submittedDate
	 */
	public DiscussionDetails(String discussionID, String discussionName, String discussionURL, String submittedDate)
		{
		this.discussionID = discussionID;
		this.discussionName = discussionName;
		this.discussionURL = discussionURL;
		this.submittedDate = submittedDate;
		}

	}
