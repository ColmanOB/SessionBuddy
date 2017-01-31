package individual_result_representation;

/**
 * Represents an individual user from thesession.org website
 * 
 * @author Colman O'B
 * @since 2017-01-31
 */
public class User 
	{
	public String userID;
	public String userName;
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
