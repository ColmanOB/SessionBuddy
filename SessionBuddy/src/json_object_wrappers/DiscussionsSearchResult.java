package json_object_wrappers;

/**
 * A structure to hold an individual discussion from a set of search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class DiscussionsSearchResult 
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
	public DiscussionsSearchResult(DiscussionDetails details, User user)
		{
		this.details = details;
		this.user = user;
		}
	}
