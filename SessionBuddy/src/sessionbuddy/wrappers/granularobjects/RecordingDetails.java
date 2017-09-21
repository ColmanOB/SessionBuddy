package sessionbuddy.wrappers.granularobjects;

/**
 * Represents the set of metadata associated with a recording in thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-09-20
 */
public class RecordingDetails 
	{
	public String recordingID;		// Numeric ID of the recording in thesession.org database
	public String recordingName;	// Title of the recording
	public String recordingURL;		// URL of the recording's page on thesession.org
	public String recordingDate;	// Date the recording was submitted to thesession.org

	/**
	 * Constructor that populates all fields
	 * 
	 * @param recordingID numeric ID of the recording in thesession.org database
	 * @param recordingName the title of the recording
	 * @param recordingURL the URL of the recording's page on thesession.org
	 * @param recordingDate	the date the recording was submitted to thesession.org
	 */
	public RecordingDetails(String recordingID, String recordingName, String recordingURL, String recordingDate)
		{
		this.recordingID = recordingID;
		this.recordingName = recordingName;
		this.recordingURL = recordingURL;
		this.recordingDate = recordingDate;
		}
	}
