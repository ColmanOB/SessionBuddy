package json_object_wrappers;

import java.util.ArrayList;

/**
 * @author Colman
 * @since 2017-08-17
 */
public class TuneByIDResult 
	{
	// Variables to hold data relating to the tune as a whole
	public TuneDetails tuneDetails;
	public User member;
	public String tunebooks;
	public String recordings;
	public ArrayList<String> aliases;	
	
	// An array of different settings of the particular tune
	public ArrayList<TuneSetting> settings; 

	public TuneByIDResult(TuneDetails tuneDetails, User member, String tunebooks, String recordings, ArrayList<String> aliases, ArrayList<TuneSetting> settings)
		{
		this.tuneDetails = tuneDetails;
		this.member = member;
		this.tunebooks = tunebooks;
		this.recordings = recordings;
		this.aliases = aliases;
		this.settings = settings;
		}
}
