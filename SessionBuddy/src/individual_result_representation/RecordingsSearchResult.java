package individual_result_representation;

/**
 * A structure to hold an individual recording from a set of search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-01-28
 */
public class RecordingsSearchResult 
	{
	public String recordingID;
	public String recordingName;
	public String recordingURL;
	public String recordingDate;
	
	public String submitterID;
	public String submitterUserName;
	public String submitterPageURL;
	
	public String artistID;
	public String artistName;
	public String artistPageURL;
	
	
	/**
	 * Constructor used to populate all fields at once.
	 * 
	 * @param recordingID 
	 * @param recordingName
	 * @param recordingURL
	 * @param recordingDate
	 * @param submitterID
	 * @param submitterUserName
	 * @param submitterPageURL
	 * @param artistID
	 * @param artistName
	 * @param artistPageURL
	 */
	public RecordingsSearchResult(String recordingID, String recordingName, String recordingURL, String recordingDate, String submitterID, String submitterUserName, String submitterPageURL, String artistID, String artistName, String artistPageURL)
		{
		this.recordingID = recordingID ;
		this.recordingName = recordingName;
		this.recordingURL = recordingURL;
		this.recordingDate = recordingDate;
		
		this.submitterID = submitterID;
		this.submitterUserName = submitterUserName;
		this.submitterPageURL = submitterPageURL;
		
		this.artistID = artistID;
		this.artistName = artistName;
		this.artistPageURL = artistPageURL;
		}
	}
