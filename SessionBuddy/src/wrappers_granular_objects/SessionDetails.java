package wrappers_granular_objects;

/**
 * Represents the set of metadata associated with a session in thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class SessionDetails 
	{
	public String sessionID; 		// Numeric ID of the session in thesession.org database
	public String sessionURL;		// URL of the session's page on thesession.org
	public String submittedDate;	// Date the session was originally submitted

	/**
	 * Constructor that populates all fields
	 * 
	 * @param sessionID numeric ID of the session in thesession.org database
	 * @param sessionURL URL of the session's page on thesession.org
	 * @param submittedDate date the session was originally submitted
	 */
	public SessionDetails(String sessionID, String sessionURL, String submittedDate)
		{
		this.sessionID = sessionID;
		this.sessionURL = sessionURL;
		this.submittedDate = submittedDate;
		}
	}
