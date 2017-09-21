package sessionbuddy.wrappers.granularobjects;

/**
 * Represents a schedule associated with an event from thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-09-20
 */
public class EventSchedule 
	{
	public String startDate;	// Start date/time of the event
	public String endDate;		// End date/time of the event

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
