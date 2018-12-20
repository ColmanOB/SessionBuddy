package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper for the response returned when searching by keyword for a tune.
 * 
 * The fields and nested structure follow the format of the JSON response from the API.
 * 
 * @author Colman O'B
 * @since 2017-01-26
 */
public class KeywordSearchWrapperTunes
{
    /**
     * The user's search terms
     */
    public String q;
    
    /**
     * The number of results returned per page in the response
     */
    public int perpage;
    
    /**
     * Number of pages in the result set
     */
    public int pages;
    
    /**
     * The current page within the result set
     */
    public int page;
    
    /**
     * The total number of tunes in the result set
     */
    public int total;
    
    /**
     * The format of the results (always JSON in this project)
     */
    public String format;
    
    /**
     * An array of the individual tunes returned by the search
     */
    public TuneDetails[] tunes;

    /**
     * A wrapper for an individual tunes within the search results
     * 
     * @author Colman O'B
     * @since 2017-01-26
     */
    public class TuneDetails
    {
        /**
         * ID of the tune in thesession.org database
         */
        public int id;
        
        /**
         * The tune's name
         */
        public String name;
        
        /**
         * URL of the tune's page on thesession.org
         */
        public String url;
        
        /**
         * Date the tune was submitted
         */
        public String date;
        
        /**
         * Type of tune, e.g. jig, reel etc.
         */
        public String type;
        
        /**
         * Details of thesession.org user who submitted the tune
         */
        public SubmitterDetails member;

        /**
         * A wrapper for the details of the "member" within each "tune"
         * 
         * @author Colman O'B
         * @since 2017-01-26
         *
         */
        public class SubmitterDetails
        {
            /**
             * ID of the user within thesession.org
             */
            public int id;
            
            /**
             * The user's username
             */
            public String name;
            
            /**
             * URL of the submitter's profile page on thesession.org
             */
            public String url;
        }
    }
}
