package wrappers_result_sets;

import wrappers_granular_objects.TuneDetails;
import wrappers_granular_objects.User;

/**
 * A structure to hold an individual tune from a set of search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-01-28
 *
 */
public class SearchResultTunes 
	{
	public TuneDetails details; // Attributes of the tune itself
	public User submitter; // Attributes of the user who submitted the tune to https://thesession.org

	/**
	 * @param details
	 * @param submitter
	 */
	public SearchResultTunes(TuneDetails details, User submitter)
		{
		this.details = details;
		this.submitter = submitter;
		}

	}
