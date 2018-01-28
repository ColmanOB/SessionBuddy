package sessionbuddy.wrappers.resultsets;

import sessionbuddy.wrappers.granularobjects.LatestSettingDetails;
import sessionbuddy.wrappers.granularobjects.LatestTuneDetails;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A structure to represent an individual result from a search for the latest tunes submitted to thesession.org
 * 
 * @author Colman O'B
 * @since 2018-01-28
 *
 */
public class LatestSearchTunes 
	{
	/**
	 * Details of the tune setting
	 */
	public LatestSettingDetails settingDetails;
	
	/**
	 * Details of the user who submitted the setting
	 */
	public User submitter;
	
	/**
	 * Details of the tune to which the setting belongs
	 */
	public LatestTuneDetails tuneDetails;

	/**
	 * Constructor 
	 * 
	 * @param settingDetails a LatestSettingDetails object that has already been populated
	 * @param submitter a User object populated with the details of the submitter
	 * @param tuneDetails a LatestTuneDetails object that has already been populated
	 */
	public LatestSearchTunes(LatestSettingDetails settingDetails, User submitter, LatestTuneDetails tuneDetails)
		{
		this.settingDetails = settingDetails;
		this.submitter = submitter;
		this.tuneDetails = tuneDetails;
		}
	}
