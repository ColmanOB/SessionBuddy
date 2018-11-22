package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper for the response returned from the API 
 * when searching for sessions by keyword. 
 * 
 * The fields and nested structure follow the format 
 * of the JSON response from the API.
 * 
 * @author Colman O'B
 * @since 2017-09-12
 */
public class KeywordSearchWrapperSessions
{
    /**
     * Search query submitted by the user
     */
    public String q;
    
    /**
     * Number of pages in the result set
     */
    public String pages; 
    
    /**
     * The current page number within the result set
     */
    public String page;
    
    /**
     * The format of the API response, always JSON in this project
     */
    public String format; 

    /**
     * An array of the individual sessions returned
     */
    public sessionsList[] sessions;

    /**
     * A wrapper for an individual session within the search results
     * 
     * @author Colman O'B
     * @since 2017-02-01
     */
    public class sessionsList
    {
        /**
         * A unique ID for the session within thesession.org
         */
        public int id;
        
        /**
         * The URL of the sessions's page on thesession.org
         */
        public String url;
        
        /**
         * The user of thesession.org who submitted the tune
         */
        public SubmitterDetails member;
        
        /**
         * Date on which the session was submitted
         */
        public String date;
        
        /**
         * The latitude of the venue
         */
        public String latitude;
        
        /**
         * The longitude of the venue
         */
        public String longitude;
        
        /**
         * Details of the venue where the session takes place
         */
        public VenueDetails venue;
        
        /**
         * Details of the town where the session is located
         */
        public TownDetails town; 

        /**
         * Details of the geographic area where the session takes place
         */
        public AreaDetails area; 

        /**
         * The country in which the session takes place
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
             * ID of the user in thesession.org database
             */
            public int id; // ID of the user within thesession.org
            
            /**
             * Username of the person who submitted the session
             */
            public String name; 
            
            /**
             * URL of the submitter's profile page on thesession.org
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
         * A wrapper for the details of the "town" within each "session" in the
         * result set
         * 
         * @author Colman
         * @since 2017-02-01
         */
        public class TownDetails
        {
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
         * A wrapper for the details of the "area" within each "session" in the
         * result set
         * 
         * @author Colman.O'Brien
         * @since 2017-02-01
         */
        public class AreaDetails
        {
            /**
             * ID for the geographic area in thesession.org
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
             * ID of the country in thesession.org
             */
            public int id;
            
            /**
             * The country name
             */
            public String name;
        }
    }
}
