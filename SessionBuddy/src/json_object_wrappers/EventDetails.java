package json_object_wrappers;

/**
 * Represents the set of metadata associated with an event from thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class EventDetails 
	{
	public String eventID;
	public String eventName;
	public String eventURL;
	public String submittedDate;	


	/**
	 * @param eventID
	 * @param eventName
	 * @param eventURL
	 * @param submittedDate
	 */
	public EventDetails(String eventID, String eventName, String eventURL, String submittedDate)
		{
		this.eventID = eventID;
		this.eventName = eventName;
		this.eventURL = eventURL;
		this.submittedDate = submittedDate;
		}
	}
