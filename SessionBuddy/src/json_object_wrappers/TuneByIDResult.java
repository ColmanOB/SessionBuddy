package json_object_wrappers;



public class TuneByIDResult 
	{
	public String id;		// The identifier for the particular setting in thesession.org database
	public String url;	// The URL of the particular setting of the tune
	public String type;	// The key of the particular setting of the tune
	public TuneSetting[] settings; // An array of different settings of the particular tune

	/**
	 * @param details
	 * @param coordinates
	 * @param user
	 * @param venue
	 * @param town
	 * @param area
	 * @param country
	 */
	public TuneByIDResult(String id, String url, String type, TuneSetting[] settings)
		{
		this.id = id;
		this.url = url;	
		this.type = type;
		this.settings = settings;
		}
}
