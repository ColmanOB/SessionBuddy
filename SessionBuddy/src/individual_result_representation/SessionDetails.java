package individual_result_representation;

/**
 * Represents the set of metadata associated with a recording from thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class SessionDetails 
	{
	public String sessionID;
	public String sessionURL;
	public String submittedDate;

	/**
	 * @param sessionID
	 * @param sessionURL
	 * @param submittedDate
	 */
	public SessionDetails(String sessionID, String sessionURL, String submittedDate)
		{
		this.sessionID = sessionID;
		this.sessionURL = sessionURL;
		this.submittedDate = submittedDate;
		}
	}
