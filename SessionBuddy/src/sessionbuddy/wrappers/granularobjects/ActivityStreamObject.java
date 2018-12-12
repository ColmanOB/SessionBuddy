package sessionbuddy.wrappers.granularobjects;

public class ActivityStreamObject
{
    public String url;
    public String objectType;
    public String id;
    public String displayName;
    
    public ActivityStreamObject(String url, String objectType, String id, String displayName)
    {
        this.url = url;
        this.objectType = objectType;
        this.id = id;
        this.displayName = displayName;
    }
}
