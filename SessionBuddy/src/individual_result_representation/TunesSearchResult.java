package individual_result_representation;

/**
 * A structure to hold an individual tune from a set of search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-01-28
 *
 */
public class TunesSearchResult 
	{
	public TuneDetails details; // Attributes of the tune itself
	public User submitter; // Attributes of the user who submitted the tune to https://thesession.org

	/**
	 * @param details
	 * @param submitter
	 */
	public TunesSearchResult(TuneDetails details, User submitter)
		{
		this.details = details;
		this.submitter = submitter;
		}

	}
