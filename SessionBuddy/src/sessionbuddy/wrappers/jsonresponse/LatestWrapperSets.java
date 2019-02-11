package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper for the response returned when searching for the most
 * recently added sets of tunes on thesession.org. 
 * 
 * The fields and structure follow the format of the JSON returned from the API.
 * 
 * This class is based on the existing LatestWrapperTunes class, 
 * with a couple of minor tweaks.
 * 
 * @author Colman O'B
 * @since 2018-02-16
 */
public class LatestWrapperSets
{
    /**
     * The format of the results (always be JSON in this project)
     */
    public String format;
    
    /**
     * Number of pages in the result set
     */
    public int pages;
    
    public int perpage;
    
    public int total;
    
    /**
     * The current page within the result set
     */
    public int page;
    
    /**
     * An array of the individual sets returned by the search
     */
    public SetDetails[] sets;

    /**
     * A wrapper for an individual set within the search results 
     * 
     * @author Colman O'B
     * @since 2018-02-16
     */
    public class SetDetails
    {
        /**
         * ID of the set in thesession.org database
         */
        public int id;
        
        /**
         * Name of the set
         */
        public String name;
        
        /**
         * URL of the set on thesession.org
         */
        public String url;
        
        /**
         * Date the set was added to thesession.org
         */
        public String date;
        
        /**
         * Details of the user who submitted the set
         */
        public SubmitterDetails member;

        /**
         * A wrapper for the details of the "member" within each "set" 
         * 
         * @author Colman O'B
         * @since 2018-02-16
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
