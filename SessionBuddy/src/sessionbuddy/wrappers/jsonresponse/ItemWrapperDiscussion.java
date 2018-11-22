package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper class for the result set when retrieving an individual discussion by its ID. 
 * For an example of the response structure, see https://thesession.org/discussions/26115?format=json 
 * The fields and nested structure here follow the JSON structure of the response.
 * 
 * @author Colman O'B
 * @since 2018-01-28
 */
public class ItemWrapperDiscussion
{
    /**
     * Format of the response, i.e. JSON
     */
    public String format;

    /**
     * ID of the discussion in thesession.org database
     */
    public int id;

    /**
     * Subject line of the discussion
     */
    public String name;

    /**
     * URL of the discussion
     */
    public String url;

    /**
     * User who submitted the discussion
     */
    public Submitter member;

    /**
     * The date the discussion was started
     */
    public String date;

    /**
     * The array of comments that make up the discussion
     */
    public Comment[] comments;

    /**
     * A wrapper for the individual comments within the discussion
     * 
     * @author Colman O'B
     * @since 2018-01-28
     */
    public class Comment
    {
        /**
         * The identifier for the particular comment
         */
        public String id;

        /**
         * The URL of the particular comment
         */
        public String url;

        /**
         * The subject line of the comment
         */
        public String subject;

        /**
         * The actual text of the comment submitted by the user
         */
        public String content;

        /**
         * The user who made the comment
         */
        public Submitter member;

        /**
         * The date of the comment
         */
        public String date;
    }

    /**
     * A wrapper for the details of thesession.org user who submitted the
     * discussion or comment
     * 
     * @author Colman O'B
     * @since 2018-01-28
     */
    public class Submitter
    {
        /**
         * A numeric identifier for the particular user within thesession.org
         * database
         */
        public int id;

        /**
         * Username of the submitter
         */
        public String name;

        /**
         * Profile page URL of the submitter
         */
        public String url;
    }
}
