package sessionbuddy.wrappers.granularobjects;

/**
 * Represents the set of metadata associated with a session in thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-12-29
 */
public class SessionDetails {
  /**
   * Numeric ID of the session in thesession.org database
   */
  public int sessionID;

  /**
   * URL of the session's page on thesession.org
   */
  public String sessionURL;

  /**
   * Date the session was originally submitted
   */
  public String submittedDate;

  /**
   * Constructor that populates all fields
   * 
   * @param sessionID numeric ID of the session in thesession.org database
   * @param sessionURL URL of the session's page on thesession.org
   * @param submittedDate date the session was originally submitted
   */
  public SessionDetails(int sessionID, String sessionURL, String submittedDate) {
    this.sessionID = sessionID;
    this.sessionURL = sessionURL;
    this.submittedDate = submittedDate;
  }
}
