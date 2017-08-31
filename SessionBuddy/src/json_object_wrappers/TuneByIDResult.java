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
	// An ArrayList of comments on the tune's page
	public ArrayList<Comment> comments;
	// An ArrayList of different settings of the particular tune
	public ArrayList<TuneSetting> settings; 

	public TuneByIDResult(TuneDetails tuneDetails, User member, String tunebooks, String recordings, ArrayList<String> aliases, ArrayList<TuneSetting> settings, ArrayList<Comment> comments)
		{
		this.tuneDetails = tuneDetails;
		this.member = member;
		this.tunebooks = tunebooks;
		this.recordings = recordings;
		this.aliases = aliases;
		this.settings = settings;
		this.comments = comments;
		}
}
