package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper for the the API response when searching for an event by geographic location.
 * 
 * The fields and nested structure follow the format of the JSON response from the API.
 * 
 * @author Colman O'B
 * @since 2017-09-12
 */
public class LocationSearchWrapperEvents
{
    /**
     * The latitude and longitude specified in the request, separated by a comma
     */
    public String latlon;
    
    /**
     * Radius in kilometres around the specified coordinates
     */
    public String radius; 
    
    /**
     * Format of the results (should always be JSON for this project)
     */
    public String format;
    
    /**
     * Number of pages in the result set
     */
    public String pages;
    
    /**
     * Current page number within the result set
     */
    public String page;
    
    /**
     * An array of the individual events returned by the search
     */
    public eventsList[] events;

    /**
     * A wrapper for an individual event within the search results
     * 
     * @author Colman O'B
     * @since 2017-02-01
     */
    public class eventsList
    {
        /**
         * ID for the event within thesession.org
         */
        public int id;
        
        /**
         * Name of the event
         */
        public String name;
        
        /**
         * URL of the events's page on thesession.org
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
         * Start date/time for the event
         */
        public String dtstart;
        
        /**
         * End date/time for the event
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
         * Details of the geographic area (county, province etc.) in which the event is located
         */
        public AreaDetails area;
        
        /**
         * Details of the country in which the event is located
         */
        public CountryDetails country;

        /**
         * A wrapper for the details of the "member" within each "event" in the
         * result set
         * 
         * @author Colman O'B
         * @since 2017-02-01
         */
        public class SubmitterDetails
        {
            /**
             * ID of the member of thesession.org who submitted the event
             */
            public int id;
            
            /**
             * Username of the submitter
             */
            public String name;
            
            /**
             * URL of the submitter's profile page on thesession.org
             */
            public String url;
        }

        /**
         * A wrapper for the details of the "venue" within each "event" in the
         * result set
         * 
         * @author Colman O'B
         * @since 2017-02-01
         */
        public class VenueDetails
        {
            /**
             * ID of the venue within thesession.org
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
         * A wrapper for the details of the "town" within each "event" in the
         * result set
         * 
         * @author Colman.O'Brien
         * @since 2017-02-01
         */
        public class TownDetails
        {
            /**
             * ID for the town within thesession.org
             */
            public int id;
            
            /**
             * The town name
             */
            public String name;
        }

        /**
         * A wrapper for the details of the "area" within each "event" in the
         * result set, e.g. a county, province etc.
         * 
         * @author Colman.O'Brien
         * @since 2017-02-01
         */
        public class AreaDetails
        {
            /**
             * ID for the geographic area within thesession.org
             */
            public int id;
            
            /**
             * The name of the geographic area
             */
            public String name;
        }

        /**
         * A wrapper for the details of the "country" within each "event" in
         * the result set
         * 
         * @author Colman.O'Brien
         * @since 2017-02-01
         */
        public class CountryDetails
        {
            /**
             * ID for the country within thesession.org
             */
            public int id;
            
            /**
             * The country name
             */
            public String name;
        }
    }
}
