package wrappers_result_sets;

import wrappers_granular_objects.TuneDetails;
import wrappers_granular_objects.User;

/**
 * A structure to hold an individual tune from a set of search results.
 * This can only be used for keyword-based searches - searches for recently added tunes return results in a totally different format
 * 
 * @author Colman O'B
 * @since 2017-09-17
 *
 */
public class SearchResultTunes 
	{
	public TuneDetails details; // Attributes of the tune itself
	public User submitter; 		// Attributes of the user who submitted the tune
	
	/**
	 * Constructor
	 * 
	 * @param details an already-populated TuneDetails object
	 * @param submitter an already-populated User object
	 */
	public SearchResultTunes(TuneDetails details, User submitter)
		{
		this.details = details;
		this.submitter = submitter;
		}
	}
