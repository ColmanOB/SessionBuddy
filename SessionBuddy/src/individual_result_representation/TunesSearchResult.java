package individual_result_representation;

/**
 * A structure to hold an individual tune from a set of search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-01-28
 *
 */
public class TunesSearchResult 
	{
	// Attributes of the tune itself
	public String tuneID;
	public String tuneTitle;
	public String tuneType;
	public String tuneURL;
	public String submittedDate;
	
	// Attributes of the user who submitted the tune to https://thesession.org
	public String submitterID;
	public String submitterUserName;
	public String submitterUserURL;

	/**
	 * Constructor used to populate all fields at once.
	 * 
	 * @param id The tune's unique ID number in thesession.org database
	 * @param title The tune's title in thesession.org database
	 * @param type The type of tune (jig, reel, hornpipe etc.)
	 * @param pageURL The URL for this tune's page on thesession.org/tunes
	 * @param date The date on which the tune was originally submitted to thesession.org
	 * @param userID The unique numeric identifier of thesession.org user who originally submitted the tune
	 * @param userName The user name of the session.org user who originally submitted the tune
	 * @param userURL The URL of the user's profile page on thesession.org
	 */
	public TunesSearchResult(String tuneID, String tuneTitle, String tuneType, String tuneURL, String submittedDate, String submitterID, String submitterUserName, String submitterUserURL)
		{
		this.tuneID = tuneID;
		this.tuneTitle = tuneTitle;
		this.tuneType = tuneType;
		this.tuneURL = tuneURL;
		this.submittedDate = submittedDate;
		
		this.submitterID = submitterID;
		this.submitterUserName = submitterUserName;
		this.submitterUserURL = submitterUserURL;
		}

	}
