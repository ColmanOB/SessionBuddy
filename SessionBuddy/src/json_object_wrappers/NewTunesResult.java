package json_object_wrappers;

/**
 * A structure to hold an individual tune from a set of search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-01-28
 *
 */
public class NewTunesResult 
	{
	public SettingDetails settingDetails; // Attributes of the tune itself
	public User submitter; // Attributes of the user who submitted the tune to https://thesession.org
	public TuneDetailsBasic tuneDetails;

	/**
	 * @param details
	 * @param submitter
	 */
	public NewTunesResult(SettingDetails settingDetails, User submitter, TuneDetailsBasic tuneDetails)
		{
		this.settingDetails = settingDetails;
		this.submitter = submitter;
		this.tuneDetails = tuneDetails;
		}

	}
