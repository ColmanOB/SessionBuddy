package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper class for the JSON response returned from the API at
 * https://thesession.org when searching for discussions by keyword(s). 
 * The fields and nested structure follow the JSON structure of the
 * recordings search results from the API.
 * 
 * @author Colman O'B
 * @since 2018-02-01
 */
public class KeywordSearchWrapperDiscussions
{
    /**
     * Search query provided by user
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
     * The number of results to be returned per page in the API response
     */
    public int perpage;
    
    /**
     * The total number of results in the response
     */
    public int total;

    /**
     * Response format (always JSON in this project)
     */
    public String format;

    /**
     * Array of the individual discussions returned by the search
     */
    public DiscussionsList[] discussions;

    
    /**
     * A wrapper for an individual discussion within the search results
     * 
     * @author Colman O'B
     * @since 2018-02-01
     */
    public class DiscussionsList
    {
        /**
         * ID of the discussion within thesession.org
         */
        public int id;

        /**
         * Discussion's title
         */
        public String name;

        /**
         * URL of the discussion
         */
        public String url;

        /**
         * Details of thesession.org user who submitted the discussion
         */
        public SubmitterDetails member;

        /**
         * Date the discussion was submitted
         */
        public String date;

        /**
         * Number of comments in the discussion
         */
        public String comments;

        /**
         * Wrapper representing the details of the user that submitted the discussion
         * 
         * @author Colman
         * @since 2018-02-01
         */
        public class SubmitterDetails
        {
            /**
             * ID of the submitter in thesession.org database
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
    }
}
