package sessionbuddy.wrappers.resultsets;

import sessionbuddy.wrappers.granularobjects.ActivityStreamDetails;
import sessionbuddy.wrappers.granularobjects.ActivityStreamObject;

public class ActivityStreamResult
{
    public ActivityStreamDetails details;
    public ActivityStreamObject actor;
    public ActivityStreamObject object;
    public ActivityStreamObject target;
    
    public ActivityStreamResult(ActivityStreamDetails details, ActivityStreamObject actor, ActivityStreamObject object, ActivityStreamObject target)
    {
        this.details = details;
        this.actor = actor;
        this.object = object;
        this.target = target;
    }
}
