package sessionbuddy.wrappers.resultsets;

import sessionbuddy.wrappers.granularobjects.DiscussionDetails;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A structure to hold an individual discussion from a set of search results from thesession.org API
 * The search may be a keyword-based search or a location-based search
 * 
 * @author Colman O'B
 * @since 2017-09-16
 */
public class SearchResultsDiscussions 
	{
	public DiscussionDetails details;	// General details of the discussion
	public User user;					// Details of the user who submitted the discussion
	
	/**
	 * Constructor
	 * 
	 * @param details an already-populated DiscussionDetails object
	 * @param user a User object populated with details of the discussion submitter
	 * 
	 */
	public SearchResultsDiscussions(DiscussionDetails details, User user)
		{
		this.details = details;
		this.user = user;
		}
	}
