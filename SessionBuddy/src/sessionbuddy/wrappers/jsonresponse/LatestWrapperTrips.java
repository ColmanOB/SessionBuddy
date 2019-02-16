package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper for the response returned when searching for 
 * the most recently added trips on thesession.org. 
 * 
 * The fields and  structure follow the format of the JSON response from the API.
 * 
 * @author Colman O'B
 * @since 2018-12-08
 */
public class LatestWrapperTrips
{
    /**
     * Search terms provided by the user
     */
    public String q; 
    /**
     * The format of the result set, always JSON in this project
     */
    public String format;
    
    /**
     * The number of pages in the result set
     */
    public int pages;
    
    /**
     * The current page in the result set
     */
    public int page;
    
    /**
     * The number of search results per page in the API response
     */
    public int perpage;
    
    /**
     * The total number of trips in the result set
     */
    public int total;
    
    /**
     * An array of the individual trips in the result set
     */
    public Trip[] trips;
    public String latlon;
    public String radius;

    /**
     * A wrapper for an individual trip within the search results
     * 
     * @author Colman O'B
     * @since 2018-12-08
     */
    public class Trip 
    {
        /**
         * Numeric ID of the trip on thesession.org
         */
        public int id;
        
        /**
         * The URL of the trip's page on thesession.org
         */
        public String url;
       
        /**
         * The title of the trip
         */
        public String name;
        
        /**
         * The details of the user who submitted the trip
         */
        public Member member;
        
        /**
         * The date on which the trip was submitted
         */
        public String date;
        
        /**
         * The start date of the trip
         */
        public String dtstart;
        
        /**
         * The end date of the trip
         */
        public String dtend;

        /**
         * A wrapper for the details of the "member" within each "trip" 
         * 
         * @author Colman O'B
         * @since 2018-12-08
         *
         */
        public class Member 
        {
            /**
             * Numeric ID of the user in thesession.org database
             */
            public int id;
            
            /**
             * The username of the user who has added the trip
             */
            public String name;
            
            /**
             * The URL of the user's profile page on thesession.org
             */
            public String url;
        }
    }
}
