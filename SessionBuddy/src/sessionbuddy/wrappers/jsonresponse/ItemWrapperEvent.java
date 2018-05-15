package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper class for the result set when retrieving an individual event by its ID. For an example
 * of the JSON response, see https://thesession.org/events/6169?format=json The fields and nested
 * structure follow the JSON structure of the response
 * 
 * @author Colman O'B
 * @since 2018-01-28
 */
public class ItemWrapperEvent {
  /**
   * Format of the response, i.e. JSON
   */
  public String format;

  /**
   * Unique ID of the event in theevent.org database
   */
  public int id;

  /**
   * Event name
   */
  public String name;

  /**
   * The URL of the event's page in thesession.org
   */
  public String url;

  /**
   * The user who submitted the event
   */
  public Submitter member;

  /**
   * The date the event was submitted
   */
  public String date;

  /**
   * Event start date
   */
  public String dtstart;

  /**
   * Event end date
   */
  public String dtend;

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
   * Details of the town where the event is located
   */
  public TownDetails town;

  /**
   * Details of the geographic area in which the event is located
   */
  public AreaDetails area;

  /**
   * Details of the country in which the event is located
   */
  public CountryDetails country;

  /**
   * User-added comments on the event's page
   */
  public Comment[] comments;


  /**
   * A wrapper for the details of theevent.org user who submitted the event
   * 
   * @author Colman
   * @since 2018-01-28
   */
  public class Submitter {
    /**
     * ID of the user within theevent.org database
     */
    public int id;

    /**
     * Username of the person who submitted the event
     */
    public String name;

    /**
     * URL of submitter's profile page on thesession.org
     */
    public String url;
  }

  /**
   * A wrapper for the details of venue where the event is taking place
   * 
   * @author Colman
   * @since 2018-01-28
   */
  public class VenueDetails {
    /**
     * ID for the venue in thesession.org
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
   * A wrapper for the details of the "town" within each "event" in the result set
   * 
   * @author Colman O'B
   * @since 2018-01-28
   */
  public class TownDetails {
    /**
     * ID for the town in thesession.org
     */
    public int id;

    /**
     * Town name
     */
    public String name;
  }

  /**
   * A wrapper for the details of the "area" within each "event" in the result set
   * 
   * @author Colman O'B
   * @since 2018-01-28
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
   * A wrapper for the details of the "country" within each "event" in the result set
   * 
   * @author Colman O'B
   * @since 2018-01-28
   */
  public class CountryDetails {
    /**
     * A unique ID for the country
     */
    public int id;

    /**
     * The country name
     */
    public String name;
  }

  /**
   * A wrapper for an individual comment on the event's page
   * 
   * @author Colman O'B
   * @since 2018-01-28
   */
  public class Comment {
    /**
     * Unique ID in thesession.org for the comment
     */
    public String id;

    /**
     * URL of the individual comment
     */
    public String url;

    /**
     * Comment subject line
     */
    public String subject;

    /**
     * The actual text of the comment
     */
    public String content;

    /**
     * The user who submitted the comment
     */
    public Submitter member;

    /**
     * Date of submission of the comment
     */
    public String date;
  }
}
