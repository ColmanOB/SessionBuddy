package wrappers_result_sets;

import java.util.ArrayList;

import wrappers_granular_objects.Comment;
import wrappers_granular_objects.TuneDetails;
import wrappers_granular_objects.TuneSetting;
import wrappers_granular_objects.User;

/**
 * @author Colman
 * @since 2017-08-17
 */
public class ItemResultTune 
	{
	// Variables to hold data relating to the tune as a whole
	public TuneDetails tuneDetails;
	public User member;
	public String tunebooks;
	public String recordings;
	
	public ArrayList<String> aliases;	// Alternative titles for the tune
	public ArrayList<Comment> comments;	// Comments on the tune's page
	public ArrayList<TuneSetting> settings;	// Different settings of the particular tune

	public ItemResultTune(TuneDetails tuneDetails, User member, String tunebooks, String recordings, ArrayList<String> aliases, ArrayList<TuneSetting> settings, ArrayList<Comment> comments)
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
