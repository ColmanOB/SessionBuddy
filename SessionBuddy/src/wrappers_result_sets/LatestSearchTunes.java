package wrappers_result_sets;

import wrappers_granular_objects.LatestSettingDetails;
import wrappers_granular_objects.LatestTuneDetails;
import wrappers_granular_objects.User;

/**
 * A structure to hold an individual tune from a set of search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-01-28
 *
 */
public class LatestSearchTunes 
	{
	public LatestTuneDetails details; // Attributes of the tune itself
	public User submitter; // Attributes of the user who submitted the tune to https://thesession.org
	public LatestSettingDetails settings;

	/**
	 * @param details
	 * @param submitter
	 */
	public LatestSearchTunes(LatestTuneDetails details, User submitter, LatestSettingDetails settings)
		{
		this.details = details;
		this.submitter = submitter;
		this.settings = settings;
		}

	}
