package sessionbuddy.wrappers.jsonresponse;


/**
 * A wrapper for the response when searching by keyword for a recording. 
 * 
 * The fields and nested structure follow the format of the JSON response.
 * 
 * @author Colman O'B
 * @since 2017-09-12
 */
public class KeywordSearchWrapperRecordings
{
    /**
     * Search query submitted by the user
     */
    public String q;
    
    /**
     * Number of pages in the result set
     */
    public int pages;
    
    /**
     * Current page number within the result set
     */
    public int page;
    
    /**
     * Format of the results (always JSON for this project)
     */
    public String format;
    
    /**
     * The number of results per page in the response from the API
     */
    public int perpage;

    /**
     * The total number of results in the response from the API
     */
    public int total;
    
    /**
     * An array of the individual recordings returned by the search
     */
    public recordingsList[] recordings;

    /**
     * A wrapper for an individual recording within the search results
     * 
     * @author Colman O'B
     * @since 2017-01-28
     */
    public class recordingsList
    {
        /**
         * ID of the recording within thesession.org
         */
        public int id;
        
        /**
         * The recording title
         */
        public String name;
        
        /**
         * URL of the recording's page on thesession.org
         */
        public String url;
        
        /**
         * Date the recording was submitted
         */
        public String date;
        
        /**
         * The user who submitted the tune
         */
        public SubmitterDetails member;
        
        /**
         * Details of the recording artist
         */
        public ArtistDetails artist;

        /**
         * A wrapper for the details of the "member" within each "recording" 
         * 
         * @author Colman O'B
         * @since 2017-01-28
         *
         */
        public class SubmitterDetails
        {
            /**
             * ID of the user within thesession.org
             */
            public int id; 
            
            /**
             * Username of the member who submitted the recording
             */
            public String name;
            
            /**
             * URL of the user's profile on thesession.org
             */
            public String url;
        }

        /**
         * A wrapper for the details of the "artist" within each "recording" 
         * 
         * @author Colman O'B
         * @since 2017-01-28
         *
         */
        public class ArtistDetails
        {
            /**
             * ID of the recording artist within thesession.org
             */
            public int id;
            
            /**
             * The artist/group name
             */
            public String name;
            
            /**
             *  URL of the artist page on thesession.org
             */
            public String url;
        }
    }
}
