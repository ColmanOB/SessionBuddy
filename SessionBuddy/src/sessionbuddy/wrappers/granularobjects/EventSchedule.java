package sessionbuddy.wrappers.granularobjects;

/**
 * Represents a schedule associated with an event from thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-12-29
 */
public class EventSchedule
{
    /**
     * Start date/time of the event
     */
    public String startDate;

    /**
     * End date/time of the event
     */
    public String endDate;

    /**
     * Constructor that populates all fields
     * 
     * @param startDate the start date/time of the event
     * @param endDate the end date/time of the event
     */
    public EventSchedule(String startDate, String endDate)
    {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
