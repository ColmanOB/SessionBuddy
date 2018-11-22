package sessionbuddy.wrappers.jsonresponse;


/**
 * A wrapper class for the response returned from the API when searching for events by keyword(s). 
 * The fields and nested structure here follow the structure of the JSON response.
 * 
 * @author Colman O'B
 * @since 2018-02-01
 */

public class KeywordSearchWrapperEvents
{
    /**
     * Search query provided by user
     */
    public String q;
    
    /**
     * Number of pages in the result set
     */
    public String pages; 
    
    /**
     * Current page number within the result set
     */
    public String page;
    
    /**
     * Format of the response data, always JSON in this project
     */
    public String format;
    
    /**
     * Array of the individual events returned by the search
     */
    public EventsList[] events;

    /**
     * A wrapper for an individual event within the result set
     * 
     * @author Colman O'B
     * @since 2017-02-01
     */
    public class EventsList
    {
        /**
         * ID for the event within thesession.org database
         */
        public int id;
        
        /**
         * Name of the event
         */
        public String name;
        
        /**
         * URL of the event's page on thesession.org
         */
        public String url;
        
        /**
         * Details of thesession.org user who submitted the event
         */
        public SubmitterDetails member; 

        /**
         * The date on which the event was submitted to thesession.org
         */
        public String date; 
                            
        /**
         * Start date/time of the event
         */
        public String dtstart;
        
        /**
         * End date/time of the event
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
         * Venue details
         */
        public VenueDetails venue;
        
        /**
         * Town where the event is happening
         */
        public TownDetails town;
        
        /**
         * Geographic area of the event (county, province etc.)
         */
        public AreaDetails area;
                                
        /**
         * Country where the event is happening
         */
        public CountryDetails country;

        /**
         * A wrapper for a session.org user who submitted an event
         * 
         * @author Colman
         * @since 2017-09-12
         */
        public class SubmitterDetails
        {
            /**
             * ID of the user in thesession.org database
             */
            public int id;
            
            /**
             * Username of the submitter
             */
            public String name;
            
            /**
             * URL of the user's profile page on thesession.org
             */
            public String url;
        }

        /**
         * A wrapper for the details of the venue where an event is taking place
         * 
         * @author Colman
         * @since 2017-09-12
         */
        public class VenueDetails
        {
            /**
             * A unique ID for the venue in thesession.org
             */
            public int id;
            
            /**
             * Venue name
             */
            public String name;
            
            /**
             * Venue phone number
             */
            public String telephone;
            
            /**
             * Venue email address
             */
            public String email;
            
            /**
             * Venue website/social media page
             */
            public String web;
        }

        /**
         * A wrapper for the details of the town where an event is taking place
         * 
         * @author Colman
         * @since 2017-09-12
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
         * A wrapper for the details of the area (county, province etc.) where
         * an event is taking place
         * 
         * @author Colman
         * @since 2017-09-12
         */
        public class AreaDetails
        {
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
         * A wrapper for the details of the country where an event is taking
         * place
         * 
         * @author Colman
         * @since 2017-09-12
         */
        public class CountryDetails
        {
            /**
             * ID for the country in thesession.org
             */
            public int id;
            
            /**
             * The country name
             */
            public String name;
        }
    }
}
