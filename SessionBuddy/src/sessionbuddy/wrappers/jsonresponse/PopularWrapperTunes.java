package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper class for the response returned when searching for the most popular tunes on
 * thesession.org. As far as I know, the popularity of a tune is determined by the number of user
 * tunebooks on thesession.org to which it has been added. The fields and nested structure follow
 * the format of the JSON structure of the most popular tunes results from the API.
 * 
 * This class is based on the existing LatestWrapperTunes class, with a couple of minor tweaks.
 * 
 * @author Colman O'B
 * @since 2018-02-10
 */
public class PopularWrapperTunes {
  public String format; // The format of the results (always be JSON in this implementation)
  public String pages; // Number of pages in the result set
  public String page; // The current page within the result set

  public TuneDetails[] tunes; // An array of the individual tunes returned by the search

  /**
   * A wrapper for the individual tunes within the search results
   * 
   * @author Colman O'B
   * @since 2018-02-10
   */
  public class TuneDetails {
    public int id; // ID for the setting in thesession.org database
    public String name; // The tune's title
    public String url; // URL of the tune's page on thesession.org
    public SubmitterDetails member; // Details of user who submitted the setting
    public String date; // The date on which this setting was submitted
    public String type; // The type of tune, i.e. jig, reel etc.
    public int tunebooks; // The number of user tunebooks on thesession.org to which the tune has
                          // been added

    /**
     * A wrapper for the details of the "member" within each "setting" in the result set
     * 
     * @author Colman O'B
     * @since 2018-02-10
     *
     */
    public class SubmitterDetails {
      public int id; // ID for the tune submitter in thesession.org
      public String name; // Tune submitter's username
      public String url; // URL of the submitter's profile page on thesession.org
    }

  }
}
