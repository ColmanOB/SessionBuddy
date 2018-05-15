package sessionbuddy.wrappers.jsonresponse;


/**
 * A wrapper class for a session listing. The fields and nested structure follow the format of the
 * JSON structure of the API response
 * 
 * @author Colman
 * @since 2018-02-01
 */
public class ItemWrapperSession {
  /**
   * Format of the response, i.e. JSON
   */
  public String format;

  /**
   * ID of the session in thesession.org database
   */
  public int id;

  /**
   * URL of the session's page in thesession.org
   */
  public String url;

  /**
   * User who submitted the session
   */
  public Submitter member;

  /**
   * Date the session was submitted
   */
  public String date;

  /**
   * Latitude of the venue
   */
  public String latitude;

  /**
   * Longitude of the venue
   */
  public String longitude;

  /**
   * Details of the venue
   */
  public VenueDetails venue;

  /**
   * Details of the town where the session is located
   */
  public TownDetails town;

  /**
   * Details of the geographic area in which the session is located
   */
  public AreaDetails area;

  /**
   * Details of the country in which the session is located
   */
  public CountryDetails country;

  /**
   * The day(s) on which the session takes place
   */
  public String[] schedule;

  /**
   * User-added comments on the session's page
   */
  public Comment[] comments;


  /**
   * A wrapper for the details of thesession.org user who submitted the session
   * 
   * @author Colman
   * @since 2018-02-01
   */
  public class Submitter {
    /**
     * ID of the user within thesession.org database
     */
    public int id;

    /**
     * Username of the person who submitted the session
     */
    public String name;

    /**
     * Profile page of the user who submitted the session
     */
    public String url;
  }

  /**
   * A wrapper for the venue details
   * 
   * @author Colman
   * @since 2018-02-01
   */
  public class VenueDetails {
    /**
     * ID for the venue
     */
    public int id;

    /**
     * Venue name
     */
    public String name;

    /**
     * Venue's phone number
     */
    public String telephone;

    /**
     * Venue's email address
     */
    public String email;

    /**
     * Venue's website/social media URL
     */
    public String web;
  }

  /**
   * A wrapper for the details of the town within each session in the result set
   * 
   * @author Colman.O'Brien
   * @since 2018-02-01
   */
  public class TownDetails {
    /**
     * ID for the town in thesession.org
     */
    public int id;

    /**
     * The town name
     */
    public String name;
  }

  /**
   * A wrapper for the details of the "area" within each session in the result set
   * 
   * @author Colman.O'Brien
   * @since 2018-02-01
   */
  public class AreaDetails {
    /**
     * ID for the geographic area in thesession.org
     */
    public int id;

    /**
     * Name of the geographic area
     */
    public String name;
  }

  /**
   * A wrapper for the details of the country within each "session" in the result set
   * 
   * @author Colman.O'Brien
   * @since 2018-02-01
   */
  public class CountryDetails {
    /**
     * ID for the country in thesession.org
     */
    public int id;

    /**
     * Country name
     */
    public String name;
  }

  /**
   * A wrapper for an individual comment on a session's page
   * 
   * @author Colman
   * @since 2017-09-11
   */
  public class Comment {
    /**
     * ID of the comment within thesession.org
     */
    public String id;

    /**
     * URL of the individual comment
     */
    public String url;

    /**
     * Subject line of the comment
     */
    public String subject;

    /**
     * The actual text of the comment
     */
    public String content;

    /**
     * Submitter of the comment
     */
    public Submitter member;

    /**
     * Date the comment was submitted
     */
    public String date;
  }
}
