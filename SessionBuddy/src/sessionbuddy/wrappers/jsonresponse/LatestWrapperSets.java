package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper class for the response returned when searching for the most recently added sets of
 * tunes on thesession.org. The fields and nested structure follow the format of the JSON structure
 * of the most recent sets results from the API.
 * 
 * This class is based on the existing LatestWrapperTunes class, with a couple of minor tweaks.
 * 
 * @author Colman O'B
 * @since 2018-02-16
 */
public class LatestWrapperSets {
  public String format; // The format of the results (always be JSON in this implementation)
  public String pages; // Number of pages in the result set
  public String page; // The current page within the result set
  public SetDetails[] sets; // An array of the individual sets returned by the search

  /**
   * A wrapper for the individual sets within the search results returned from thesession.org API
   * 
   * @author Colman O'B
   * @since 2018-02-16
   */
  public class SetDetails {
    public int id;
    public String name;
    public String url;
    public String date;
    public SubmitterDetails member;

    /**
     * A wrapper for the details of the "member" within each "set" in the result set
     * 
     * @author Colman O'B
     * @since 2018-02-16
     *
     */
    public class SubmitterDetails {
      public int id; // ID of the user within thesession.org
      public String name; // The user's username
      public String url; // URL of the submitter's profile page on thesession.org
    }
  }
}
