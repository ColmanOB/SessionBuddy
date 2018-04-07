package sessionbuddy.wrappers.resultsets;

import sessionbuddy.wrappers.granularobjects.PopularTuneDetails;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A structure to represent an individual result from a search for the latest tunes submitted to thesession.org
 * 
 * @author Colman O'B
 * @since 2018-01-28
 *
 */
public class SearchResultTunesPopular 
	{
	/**
	 * Details of the tune
	 */
	public PopularTuneDetails tuneDetails;
	
	/**
	 * Details of the user who submitted the setting
	 */
	public User submitter;
	
	/**
	 * Constructor 
	 * 
	 * @param tuneDetails a PopularTuneDetails object that has already been populated
	 * @param submitter a User object populated with the details of the submitter
	 */
	public SearchResultTunesPopular(PopularTuneDetails tuneDetails, User submitter)
		{
		this.tuneDetails = tuneDetails;
		this.submitter = submitter;
		}
	}
