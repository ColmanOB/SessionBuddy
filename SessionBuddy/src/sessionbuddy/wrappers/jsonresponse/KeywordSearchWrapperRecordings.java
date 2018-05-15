package sessionbuddy.wrappers.jsonresponse;


/**
 * A wrapper for the response returned from the API at https://thesession.org when searching by
 * keyword for a recording. The fields and nested structure follow the format of the JSON structure
 * of the recordings search results from the API.
 * 
 * @author Colman O'B
 * @since 2017-09-12
 */
public class KeywordSearchWrapperRecordings {
  public String q; // Search query submitted by the user
  public String pages; // Number of pages in the result set
  public String page; // Current page number within the result set
  public String format; // Format of the results (should always be JSON for this project)
  public recordingsList[] recordings; // An array of the individual recordings returned by the
                                      // search

  /**
   * A wrapper for the individual recordings within the search results
   * 
   * @author Colman O'B
   * @since 2017-01-28
   */
  public class recordingsList {
    public int id; // ID of the recording within thesession.org
    public String name; // The recording title
    public String url; // URL of the recording's page on thesession.org
    public String date; // Date the recording was submitted
    public SubmitterDetails member; // The user who submitted the tune
    public ArtistDetails artist; // Details of the recording artist

    /**
     * A wrapper for the details of the "member" within each "recording" in the result set
     * 
     * @author Colman O'B
     * @since 2017-01-28
     *
     */
    public class SubmitterDetails {
      public int id; // ID of the user within thesession.org
      public String name; // Username of the member who submitted the recording
      public String url; // URL of the user's personal page on thesession.org
    }

    /**
     * A wrapper for the details of the "artist" within each "recording" in the result set
     * 
     * @author Colman O'B
     * @since 2017-01-28
     *
     */
    public class ArtistDetails {
      public int id; // ID of the recording artist within thesession.org
      public String name; // The artist/group name
      public String url; // URL of the artist page on thesession.org website
    }
  }
}
