package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper class for the response returned from the API at https://thesession.org when searching
 * for a session by geographic location. The fields and nested structure follow the format of the
 * JSON structure of the session search results from the API
 * 
 * @author Colman O'B
 * @since 2017-08-22
 */
public class LocationSearchWrapperSessions {
  public String latlon; // Latitude and longitude specified in the request, separated by a comma
  public String radius; // No. of Km radius to include around the specified coordinates
  public String format; // Format of the results (should always be JSON for this project)
  public String pages; // Number of pages in the result set
  public String page; // Current page number within the result set
  public sessionsList[] sessions; // An array of the individual sessions returned

  /**
   * A wrapper for the individual sessions within the search results returned from thesession.org
   * API
   * 
   * @author Colman O'B
   * @since 2017-02-01
   */
  public class sessionsList {
    public int id; // ID for the session within thesession.org
    public String url; // URL of the sessions's page on thesession.org
    public SubmitterDetails member; // Details of thesession.org user who submitted the session
    public String date; // Date on which the session was submitted
    public String latitude; // Latitude of the venue
    public String longitude; // Longitude of the venue
    public VenueDetails venue; // Details of the recording artist
    public TownDetails town; // Details of the town where the session is located
    public AreaDetails area; // Details of the geographic area in which the session is located
    public CountryDetails country; // Details of the country in which the session is located

    /**
     * A wrapper for the details of the "member" within each "session" in the result set
     * 
     * @author Colman O'B
     * @since 2017-02-01
     */
    public class SubmitterDetails {
      public int id; // ID of the member of thesession.org
      public String name; // Username of the submitter
      public String url; // URL of the member's profile page on thesession.org
    }

    /**
     * A wrapper for the details of the "venue" within each "session" in the result set
     * 
     * @author Colman O'B
     * @since 2017-02-01
     */
    public class VenueDetails {
      public int id; // A unique ID for the venue
      public String name; // The venue name
      public String telephone; // The venue's phone number
      public String email; // The venue's email address
      public String web; // The venue's website/social media URL
    }

    /**
     * A wrapper for the details of the "town" within each "session" in the result set
     * 
     * @author Colman.O'Brien
     * @since 2017-02-01
     */
    public class TownDetails {
      public int id; // A unique ID for the town
      public String name; // The town name
    }

    /**
     * A wrapper for the details of the "area" within each "session" in the result set
     * 
     * @author Colman.O'Brien
     * @since 2017-02-01
     */
    public class AreaDetails {
      public int id; // A unique ID for the geographic area
      public String name; // The name of the geographic area
    }

    /**
     * A wrapper for the details of the "country" within each "session" in the result set
     * 
     * @author Colman.O'Brien
     * @since 2017-02-01
     */
    public class CountryDetails {
      public int id; // A unique ID for the country
      public String name; // The country name
    }
  }
}
