package sessionbuddy.wrappers.resultsets;

import sessionbuddy.wrappers.granularobjects.ActivityStreamDetails;
import sessionbuddy.wrappers.granularobjects.ActivityStreamObject;

/**
 * A structure to hold an individual activity stream record.
 * 
 * @author Colman O'B
 * @since 2018-12-13
 *
 */
public class ActivityStreamResult
{
    /**
     * Meta data relating to the activity stream entry, incl. date and title
     */
    public ActivityStreamDetails details;
    
    /**
     * The user who performed the activity
     */
    public ActivityStreamObject actor;
    
    /**
     * The object being acted upon, e.g. a tune, discussion etc.
     */
    public ActivityStreamObject object;
    
    /**
     * The 'target' of the activity, e.g. if a tune is added to a tunebook, the tunebook is the target
     */
    public ActivityStreamObject target;
    
    /**
     * Constructor 
     * 
     * @param details Meta data relating to the activity stream entry, incl. date and title
     * @param actor The user who performed the activity
     * @param object The object being acted upon, e.g. a tune, discussion etc.
     * @param target The 'target' of the activity, e.g. if a tune is added to a tunebook, the tunebook is the target
     */
    public ActivityStreamResult(ActivityStreamDetails details, ActivityStreamObject actor, ActivityStreamObject object, ActivityStreamObject target)
    {
        this.details = details;
        this.actor = actor;
        this.object = object;
        this.target = target;
    }
}
