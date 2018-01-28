package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.granularobjects.Comment;
import sessionbuddy.wrappers.granularobjects.TuneDetails;
import sessionbuddy.wrappers.granularobjects.TuneSetting;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A wrapper for the result returned when retrieving an individual tune by its ID.
 * 
 * @author Colman
 * @since 2018-01-28
 */
public class ItemResultTune 
	{
	/**
	 * Metadata relating to the tune
	 */
	public TuneDetails tuneDetails;
	
	/**
	 * Details of the user who submitted the tune
	 */
	public User member;
	
	/**
	 * Number of user tunebooks to which this tune has been added
	 */
	public String tunebooks;
	
	/**
	 * Number of recordings with a tune of this name
	 */
	public String recordings;
	
	/**
	 * Alternative titles for the tune
	 */
	public ArrayList<String> aliases;
	
	/**
	 * A list of comments on the tune's page
	 */
	public ArrayList<Comment> comments;
	
	/**
	 * A list of different settings of the particular tune
	 */
	public ArrayList<TuneSetting> settings;

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
