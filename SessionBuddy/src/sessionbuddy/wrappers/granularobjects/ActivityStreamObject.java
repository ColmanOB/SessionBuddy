package sessionbuddy.wrappers.granularobjects;

/**
 * @author Colman
 * @since 2018-12-13
 */
public class ActivityStreamObject
{
    /**
     * The URL of the object on thesession.org
     */
    public String url;
    
    /**
     * The type of object in thesession.org, e.g. person, tune, tunebook, session etc.
     */
    public String objectType;
    
    /**
     * A composite ID, including the object type and its numeric ID in thesession.org system
     */
    public String id;
    
    /**
     * The display name of the object, i.e. its title / description
     */
    public String displayName;
    
    /**
     * Constructor
     * 
     * @param url The URL of the object on thesession.org
     * @param objectType The type of object in thesession.org, e.g. person, tune, tunebook, session etc.
     * @param id A composite ID, including the object type and its numeric ID in thesession.org system
     * @param displayName The display name of the object, i.e. its title / description
     */
    public ActivityStreamObject(String url, String objectType, String id, String displayName)
    {
        this.url = url;
        this.objectType = objectType;
        this.id = id;
        this.displayName = displayName;
    }
}
