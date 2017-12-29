package sessionbuddy.wrappers.granularobjects;

/**
 * Represents the set of metadata associated with an event from thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-12-29
 */
public class EventDetails 
	{
	/**
	 * Numeric ID of the event in thesession.org database
	 */
	public String eventID;
	
	/**
	 * Name of the event
	 */
	public String eventName;
	
	/**
	 * URL of the event's page on thesession.org
	 */
	public String eventURL;
	
	/**
	 * Date the event was submitted to thesession.org
	 */
	public String submittedDate;


	/**
	 * Constructor that populates all fields
	 * 
	 * @param eventID numeric ID of the event in thesession.org database
	 * @param eventName name of the event
	 * @param eventURL URL of the event's page on thesession.org
	 * @param submittedDate	date the event was submitted to thesession.org
	 */
	public EventDetails(String eventID, String eventName, String eventURL, String submittedDate)
		{
		this.eventID = eventID;
		this.eventName = eventName;
		this.eventURL = eventURL;
		this.submittedDate = submittedDate;
		}
	}
