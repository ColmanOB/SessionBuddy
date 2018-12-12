package sessionbuddy.wrappers.granularobjects;

public class ActivityStreamDetails
{
    public String published;
    public String title;
    public String verb;

    public ActivityStreamDetails(String published, String title, String verb)
    {
        this.published = published;
        this.title = title;
        this.verb = verb;
    }
}
