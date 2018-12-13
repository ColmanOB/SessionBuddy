package sessionbuddy.wrappers.jsonresponse;

/**
 * A wrapper class for the result set when retrieving an activity stream. 
 * For an example of the response structure, see https://thesession.org/tunes/activity?format=json
 * The fields and nested structure here follow the JSON structure of the response.
 * 
 * @author Colman O'B
 * @since 2018-12-13
 */
public class ActivityStreamWrapper
{
    /**
     * An array of activity stream records
     */
    public Item[] items;

    /**
     * An individual activity stream record
     */
    public class Item
    {
        /**
         * The date and time of the activity
         */
        public String published;
        
        /**
         * The title / description of the activity
         */
        public String title;
        
        /**
         * The user who performed the activity
         */
        public Actor actor;
        
        /**
         * 
         */
        public String verb;
        
        /**
         * 
         */
        public Object object;
        
        /**
         * 
         */
        public Target target;
    }

    /**
     * An object representing the person who performed the activity
     */
    public class Actor
    {
        public String url;
        public String objectType;
        public String id;
        public String displayName;
    }

    /**
     * An object representing the object on which the activity is performed
     */
    public class Object
    {
        public String url;
        public String objectType;
        public String id;
        public String displayName;
    }

    /**
     * An object representing the 'target' of the activity. E.g. if a tune is added 
     * to a tunebook, the tunebook is the target
     */
    public class Target
    {
        public String url;
        public String objectType;
        public String id;
        public String displayName;
    }
}
