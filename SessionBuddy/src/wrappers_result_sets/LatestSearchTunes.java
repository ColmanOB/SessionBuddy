package wrappers_result_sets;

import wrappers_granular_objects.LatestTuneDetails;
import wrappers_granular_objects.LatestSettingDetails;
import wrappers_granular_objects.User;

/**
 * A structure to represent an individual result from a search for the latest tunes submitted to thesession.org
 * 
 * @author Colman O'B
 * @since 2017-09-10
 *
 */
public class LatestSearchTunes 
	{
	public LatestSettingDetails details;	// Attributes of the tune setting
	public User submitter;	// Attributes of the user who submitted the setting
	public LatestTuneDetails settings; // Attributes of the tune to which the setting belongs


	public LatestSearchTunes(LatestSettingDetails details, User submitter, LatestTuneDetails settings)
		{
		this.details = details;
		this.submitter = submitter;
		this.settings = settings;
		}
	}
