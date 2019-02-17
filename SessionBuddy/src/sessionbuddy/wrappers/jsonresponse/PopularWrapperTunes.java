package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper class for the response returned when searching 
 * for the most popular tunes on thesession.org. 
 * 
 * As far as I know, the popularity of a tune is determined
 * by the number of user tunebooks on thesession.org to which 
 * it has been added. 
 * 
 * The fields and nested structure follow the format of the JSON
 * structure of the most popular tunes results from the API.
 * 
 * @author Colman O'B
 * @since 2018-02-10
 */
public class PopularWrapperTunes
{
    /**
     * The format of the results (always JSON in this implementation)
     */
    public String format;
    
    /**
     * Number of pages in the result set
     */
    public int pages;
    
    /**
     * The current page within the result set
     */
    public int page;

    public int perpage;
    
    public int total;
    /**
     * An array of the individual tunes returned by the search
     */
    public TuneDetails[] tunes;

    /**
     * A wrapper for the individual tunes within the search results
     * 
     * @author Colman O'B
     * @since 2018-02-10
     */
    public class TuneDetails
    {
        /**
         * ID for the tune in thesession.org database
         */
        public int id;
        
        /**
         * The tune's title
         */
        public String name;
        
        /**
         * URL of the tune's page on thesession.org
         */
        public String url; 
        
        /**
         * Details of user who submitted the setting
         */
        public SubmitterDetails member;
        
        /**
         * The date on which this setting was submitted
         */
        public String date;
        
        /**
         * The type of tune, i.e. jig, reel etc.
         */
        public String type;
        
        /**
         * The number of user tunebooks on thesession.org to which the tune has been added
         */
        public int tunebooks;

        /**
         * A wrapper for the details of the "member" within each "setting" in
         * the result set
         * 
         * @author Colman O'B
         * @since 2018-02-10
         */
        public class SubmitterDetails
        {
            /**
             * ID for the tune submitter in thesession.org
             */
            public int id;
            
            /**
             * Tune submitter's username
             */
            public String name;
            
            /**
             * URL of the submitter's profile page on thesession.org
             */
            public String url;
        }
    }
}
