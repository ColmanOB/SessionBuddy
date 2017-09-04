package json_object_wrappers;

/**
 * A structure to hold an individual tune from a set of search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-01-28
 *
 */
public class TunesLatestResult 
	{
	public LatestTuneDetails details; // Attributes of the tune itself
	public User submitter; // Attributes of the user who submitted the tune to https://thesession.org
	public LatestSettingDetails settings;

	/**
	 * @param details
	 * @param submitter
	 */
	public TunesLatestResult(LatestTuneDetails details, User submitter, LatestSettingDetails settings)
		{
		this.details = details;
		this.submitter = submitter;
		this.settings = settings;
		}

	}
