package sessionbuddy.wrappers.granularobjects;

/**
 * Represents a set of meta data relating to an activity stream
 * 
 * @author Colman
 * @since 2018-12-13
 */
public class ActivityStreamDetails
{
    /**
     * The date the activity was published
     */
    public String published;
    
    /**
     * The title of the activity
     */
    public String title;
    
    /**
     * The 'verb' for the activity, as per the JSON activity streams specification
     */
    public String verb;

    /**
     * Constructor
     * 
     * @param published The date the activity was published
     * @param title The title of the activity
     * @param verb The 'verb' for the activity, as per the JSON activity streams specification
     */
    public ActivityStreamDetails(String published, String title, String verb)
    {
        this.published = published;
        this.title = title;
        this.verb = verb;
    }
}
