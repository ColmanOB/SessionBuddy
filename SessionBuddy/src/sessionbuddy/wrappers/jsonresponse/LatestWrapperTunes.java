package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper for the response returned when searching for 
 * the most recently added tunes on thesession.org. 
 * 
 * The fields and  structure follow the format of the JSON response from the API.
 * 
 * Note that this wrapper is an anomaly - for all other data categories 
 * the response is the same for a keyword search and for latest listings. 
 * 
 * Latest tunes are presented quite differently from the results of a keyword search
 * for tunes.
 * 
 * @author Colman O'B
 * @since 2017-09-06
 */
public class LatestWrapperTunes
{
    /**
     * Format of the results, always JSON in this project
     */
    public String format;

    /**
     * Number of pages in the result set
     */
    public String pages;
    
    /**
     * The current page within the result set
     */
    public String page;

    /**
     * An array of the individual tune settings returned by the search
     */
    public SettingDetails[] settings;

    /**
     * A wrapper for an individual tune setting within the search results
     * 
     * @author Colman O'B
     * @since 2017-09-06
     */
    public class SettingDetails
    {
        /**
         * ID for the setting in thesession.org database
         */
        public int id;
        
        /**
         * URL of the individual setting on thesession.org
         */
        public String url;
        
        /**
         * Key of the setting
         */
        public String key;
          
        /**
         * Details of the user who submitted the tune
         */
        public SubmitterDetails member;
        
        /**
         * The date on which this setting was submitted
         */
        public String date;
        
        /**
         * The 'parent' tune, of which this is a setting
         */
        public TuneDetails tune;

        /**
         * A wrapper for the details of the "member" within each "setting" 
         * 
         * @author Colman O'B
         * @since 2017-09-06
         *
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

        /**
         * A wrapper for the details of the "tune" to which a "setting" belongs
         * 
         * @author Colman O'B
         * @since 2017-09-06
         *
         */
        public class TuneDetails
        {
            /**
             * ID for the tune on thesession.org
             */
            public int id;
            
            /**
             * The tune title
             */
            public String name;
            
            /**
             * URL of the tune's page
             */
            public String url;
        }
    }
}
