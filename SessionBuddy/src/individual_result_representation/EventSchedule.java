package individual_result_representation;

/**
 * Represents a schedule associated with an event from thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class EventSchedule 
	{
	public String startDate;
	public String endDate;

	/**
	 * @param startDate
	 * @param endDate
	 */
	public EventSchedule(String startDate, String endDate)
		{
		this.startDate = startDate;
		this.endDate = endDate;
		}
	}
