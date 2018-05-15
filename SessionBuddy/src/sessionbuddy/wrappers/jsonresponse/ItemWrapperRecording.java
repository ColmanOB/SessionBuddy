package sessionbuddy.wrappers.jsonresponse;


/**
 * A wrapper class for the result set when retrieving an individual recording by its ID. For an
 * example of the JSON response, see https://thesession.org/recordings/1222?format=json The fields
 * and nested structure follow the JSON structure of the response
 * 
 * @author Colman
 * @since 2018-02-01
 */
public class ItemWrapperRecording {
  /**
   * Format of the response, i.e. JSON
   */
  public String format;

  /**
   * ID of the recording in thesession.org database
   */
  public int id;

  /**
   * URL of the recording's page in thesession.org
   */
  public String url;

  /**
   * Title of the recording
   */
  public String name;

  /**
   * User who submitted the recording
   */
  public Submitter member;

  /**
   * Date the recording was submitted
   */
  public String date;

  /**
   * The recording artist
   */
  public Artist artist;

  /**
   * Track listing for the recording
   */
  public Track[] tracks;

  /**
   * User-added comments on the recording's page
   */
  public Comment[] comments;

  /**
   * A wrapper for each individual track listing on a recording, may contain numerous tunes
   * 
   * @author Colman
   * @since 2017-09-11
   */
  public class Track {
    /**
     * An array of tunes within a single track
     */
    public Tune[] tunes;

    /**
     * A wrapper for each individual tune within a single track listing
     * 
     * @author Colman
     * @since 2017-09-11
     */
    public class Tune {
      /**
       * Tune name
       */
      public String name;

      /**
       * ID of the tune in thesession.org
       */
      public int id;

      /**
       * URL of the tune page
       */
      public String url;
    }
  }

  /**
   * A wrapper for details relating to the recording artist
   * 
   * @author Colman
   * @since 2017-09-11
   */
  public class Artist {
    /**
     * ID of the artist in thesession.org
     */
    public int id;

    /**
     * Name of the tune
     */
    public String name;

    /**
     * URL of the artist page on thesession.org
     */
    public String url;
  }

  /**
   * A wrapper for the details of thesession.org user who submitted the recording
   * 
   * @author Colman
   * @since 2017-09-11
   */
  public class Submitter {
    /**
     * ID of the user within thesession.org database
     */
    public int id;

    /**
     * Username of the person who submitted the recording
     */
    public String name;

    /**
     * Profile page of thesession.org user who submitted the recording
     */
    public String url;
  }

  /**
   * A wrapper for a comment on a recording's page
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
     * URL of the particular comment
     */
    public String url;

    /**
     * Subject line of the comment
     */
    public String subject;

    /**
     * Text of the comment
     */
    public String content;

    /**
     * Comment submitter
     */
    public Submitter member;

    /**
     * Date of the comment
     */
    public String date;
  }
}
