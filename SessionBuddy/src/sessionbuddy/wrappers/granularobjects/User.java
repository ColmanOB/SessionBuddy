package sessionbuddy.wrappers.granularobjects;

/**
 * Represents an individual user from thesession.org website
 * 
 * @author Colman O'B
 * @since 2017-12-24
 */
public class User 
	{
	/**
	 * A numeric ID for the particular user within thesession.org database.
	 */
	public String userID;
	
	/**
	 * The person's username / handle on thesession.org, which may be changed by the user
	 */
	public String userName;
	
	/**
	 * The URL of the user's profile page on thesession.org.
	 * This may contain biographical information, original compositions etc.
	 */
	public String userURL;

	/**
	 * Constructor that populates all fields
	 * 
	 * @param userID A unique numeric identifier assigned to each user in thesession.org database
	 * @param userName The user's username in thesession.org database
	 * @param userURL The URL of the user's profile page on thesession.org
	 */
	public User(String userID, String userName, String userURL)
		{
		this.userID = userID;
		this.userName = userName;
		this.userURL = userURL;
		}
	}
