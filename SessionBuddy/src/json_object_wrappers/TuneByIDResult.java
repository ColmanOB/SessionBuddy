package json_object_wrappers;

import result_set_wrappers.TuneByIDWrapper.TuneSubmitter;

public class TuneByIDResult 
	{
	public String id;		// The identifier for the particular setting in thesession.org database
	public String url;	// The URL of the particular setting of the tune
	public String key;	// The key of the particular setting of the tune
	public String abc;	// The abc notation of the setting of the tune (i.e. the main body of the tune)

	/**
	 * @param details
	 * @param coordinates
	 * @param user
	 * @param venue
	 * @param town
	 * @param area
	 * @param country
	 */
	public TuneByIDResult(String id, String url, String key, String abc)
		{
		this.id = id;
		this.url = url;	
		this.key = key;
		this.abc = abc;
		}
}
