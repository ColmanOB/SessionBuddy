package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper class for a trip listing. The fields and nested structure follow
 * the format of the JSON structure of the API response
 * 
 * @author Colman
 * @since 2018-12-11
 */
public class ItemWrapperTrip
{
    /**
     * Format of the response, i.e. JSON
     */
    public String format;

    /**
     * ID of the trip in thesession.org database
     */
    public int id;

    /**
     * URL of the trip's page in thesession.org
     */
    public String url;
    
    /**
     * URL of the trip's page in thesession.org
     */
    public String name;

    /**
     * User who submitted the trip
     */
    public Submitter member;

    /**
     * Date the trip was submitted
     */
    public String date;

    /**
     * Date the trip was submitted
     */
    public String dtstart;
    
    /**
     * Date the trip was submitted
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
     * User-added comments on the trip's page
     */
    public Comment[] comments;

    /**
     * A wrapper for the details of thesession.org user who submitted the
     * trip
     * 
     * @author Colman
     * @since 2018-12-11
     */
    public class Submitter
    {
        /**
         * ID of the user within thesession.org database
         */
        public int id;

        /**
         * Username of the person who submitted the trip
         */
        public String name;

        /**
         * Profile page of the user who submitted the trip
         */
        public String url;
    }

    /**
     * A wrapper for an individual comment on a trip's page
     * 
     * @author Colman
     * @since 2018-12-11
     */
    public class Comment
    {
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
