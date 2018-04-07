package sessionbuddy.wrappers.resultsets;

import sessionbuddy.wrappers.granularobjects.DiscussionDetails;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A structure to hold an individual discussion from a set of search results from thesession.org API
 * The search may be a keyword-based search or a location-based search
 * 
 * @author Colman O'B
 * @since 2017-01-28
 */
public class SearchResultDiscussions 
	{
	/**
	 * General details of the discussion
	 */
	public DiscussionDetails details;
	
	/**
	 * Details of the user who submitted the discussion
	 */
	public User user;
	
	/**
	 * Constructor
	 * 
	 * @param details an already-populated DiscussionDetails object
	 * @param user a User object populated with details of the discussion submitter
	 * 
	 */
	public SearchResultDiscussions(DiscussionDetails details, User user)
		{
		this.details = details;
		this.user = user;
		}
	}