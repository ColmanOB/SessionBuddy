package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper class for the response returned from the API
 * when searching for a session by geographic location
 * 
 * The fields and nested structure follow the format 
 * of the JSON response from the API
 * 
 * @author Colman O'B
 * @since 2017-08-22
 */
public class LocationSearchWrapperSessions
{
    /**
     * Latitude and longitude specified in the request, separated by a comma
     */
    public String latlon;
    
    /**
     * No. of Km radius to include around the specified coordinates
     */
    public String radius;
    
    /**
     * Format of the results (should always be JSON for this project)
     */
    public String format;
    
    /**
     * Number of pages in the result set
     */
    public int pages;
    
    /**
     * Current page number within the result set
     */
    public int page;
    
    public int perpage;
    
    public int total;
    
    /**
     * An array of the individual sessions returned
     */
    public sessionsList[] sessions;

    /**
     * A wrapper for the individual sessions within the search results returned
     * from thesession.org API
     * 
     * @author Colman O'B
     * @since 2017-02-01
     */
    public class sessionsList
    {
        /**
         * ID for the session within thesession.org
         */
        public int id;
        
        /**
         * URL of the sessions's page on thesession.org
         */
        public String url;
        
        /**
         * Details of thesession.org user who submitted the session
         */
        public SubmitterDetails member;
        
        /**
         * Date on which the session was submitted
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
         * Details of the recording artist
         */
        public VenueDetails venue;
        
        /**
         * Details of the town where the session is located
         */
        public TownDetails town;
        
        /**
         * Details of the geographic area (county, province etc.) in which the session is located
         */
        public AreaDetails area;
        
        /**
         * Details of the country in which the session is located
         */
        public CountryDetails country;

        /**
         * A wrapper for the details of the "member" within each "session" in
         * the result set
         * 
         * @author Colman O'B
         * @since 2017-02-01
         */
        public class SubmitterDetails
        {
            /**
             * ID of the member of thesession.org
             */
            public int id; 
            
            /**
             * Username of the submitter
             */
            public String name; 
            
            /**
             * URL of the member's profile page on thesession.org
             */
            public String url;
        }

        /**
         * A wrapper for the details of the "venue" within each "session" in the
         * result set
         * 
         * @author Colman O'B
         * @since 2017-02-01
         */
        public class VenueDetails
        {
            /**
             * A unique ID for the venue
             */
            public int id;
            
            /**
             * The venue name
             */
            public String name;
            
            /**
             * The venue's phone number
             */
            public String telephone; 
            
            /**
             * The venue's email address
             */
            public String email;
            
            /**
             * The venue's website/social media URL
             */
            public String web;
        }

        /**
         * A wrapper for the details of the "town" within each "session" in the
         * result set
         * 
         * @author Colman.O'Brien
         * @since 2017-02-01
         */
        public class TownDetails
        {
            /**
             * A unique ID for the town
             */
            public int id;
            
            /**
             * The town name
             */
            public String name;
        }

        /**
         * A wrapper for the details of the "area" within each "session" in the
         * result set
         * 
         * @author Colman.O'Brien
         * @since 2017-02-01
         */
        public class AreaDetails
        {
            /**
             * A unique ID for the geographic area
             */
            public int id;
            
            /**
             * The name of the geographic area
             */
            public String name;
        }

        /**
         * A wrapper for the details of the "country" within each "session" in
         * the result set
         * 
         * @author Colman.O'Brien
         * @since 2017-02-01
         */
        public class CountryDetails
        {
            /**
             * A unique ID for the country
             */
            public int id;
            
            /**
             * The country name
             */
            public String name;
        }
    }
}
