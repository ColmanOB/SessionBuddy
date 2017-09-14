package wrappers_result_sets;

import java.util.ArrayList;

import wrappers_granular_objects.Comment;
import wrappers_granular_objects.TuneDetails;
import wrappers_granular_objects.TuneSetting;
import wrappers_granular_objects.User;

/**
 * A wrapper for the result returned when retrieving an individual tune by its ID.
 * 
 * @author Colman
 * @since 2017-09-14
 */
public class ItemResultTune 
	{
	public TuneDetails tuneDetails;			// Metadata relating to the tune
	public User member;						// Details of the user who submitted the tune
	public String tunebooks;				// Number of user tunebooks to which this tune has been added
	public String recordings;				// Number of recordings with a tune of this name
	
	public ArrayList<String> aliases;		// Alternative titles for the tune
	public ArrayList<Comment> comments;		// A list of comments on the tune's page
	public ArrayList<TuneSetting> settings;	// A list of different settings of the particular tune

	/**
	 * Constructor
	 * 
	 * @param tuneDetails a TuneDetails object that has already been populated
	 * @param member a User object representing the tune submitter
	 * @param tunebooks the number of user tunebooks to which this tune has been added
	 * @param recordings the number of recordings containing a tune of this name
	 * @param aliases a list of alternative titles for the tune
	 * @param settings a list of different settings of the tune
	 * @param comments a list of comments on the tune's page on thesession.org
	 */
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
