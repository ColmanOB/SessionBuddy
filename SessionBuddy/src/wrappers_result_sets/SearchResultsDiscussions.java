package wrappers_result_sets;

import wrappers_granular_objects.DiscussionDetails;
import wrappers_granular_objects.User;

/**
 * A structure to hold an individual discussion from a set of search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class SearchResultsDiscussions 
	{
	public DiscussionDetails details;	
	public User user;
	
	/**
	 * Constructor that takes a DiscussionDetails and User object as arguments
	 * 
	 * @param details
	 * @param user
	 * 
	 */
	public SearchResultsDiscussions(DiscussionDetails details, User user)
		{
		this.details = details;
		this.user = user;
		}
	}
