package sessionbuddy.wrappers.granularobjects;

/**
 * A wrapper for an individual comment from thesession.org. May be a comment on a discussion, a
 * tune, a session etc.
 * 
 * @author Colman
 * @since 2017-12-29
 */
public class Comment {
  /**
   * A numeric identifier for the particular comment in thesession.org database
   */
  public int id;

  /**
   * The URL of the particular comment
   */
  public String url;

  /**
   * The subject line of the comment
   */
  public String subject;

  /**
   * The actual text of the comment submitted by the user
   */
  public String content;

  /**
   * The user who submitted the comment
   */
  public User member;

  /**
   * The date on which the comment was submitted to thesession.org
   */
  public String date;

  /**
   * Constructor that populates all fields
   * 
   * @param id A numeric identifier for the particular comment within thesession.org database
   * @param url The URL of the individual comment
   * @param subject The subject line of the comment
   * @param content The main body of the comment
   * @param member The session.org user who submitted the comment
   * @param date The date on which the comment was submitted
   */
  public Comment(int id, String url, String subject, String content, User member, String date) {
    this.id = id;
    this.url = url;
    this.subject = subject;
    this.content = content;
    this.member = member;
    this.date = date;
  }
}

