package wrappers_granular_objects;

/**
 * Represents the set of metadata associated with a recording from thesession.org database
 * 
 * @author Colman O'B
 * @since 2017-02-01
 */
public class RecordingDetails 
	{
	public String recordingID;
	public String recordingName;
	public String recordingURL;
	public String recordingDate;

	/**
	 * @param recordingID
	 * @param recordingName
	 * @param recordingURL
	 * @param recordingDate
	 */
	public RecordingDetails(String recordingID, String recordingName, String recordingURL, String recordingDate)
		{
		this.recordingID = recordingID;
		this.recordingName = recordingName;
		this.recordingURL = recordingURL;
		this.recordingDate = recordingDate;
		}
	}
