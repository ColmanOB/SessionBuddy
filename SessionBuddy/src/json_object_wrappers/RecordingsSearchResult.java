package json_object_wrappers;

/**
 * A structure to hold an individual recording from a set of search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-01-28
 */
public class RecordingsSearchResult 
	{
	public RecordingDetails recordingInfo;
	public User submitter;
	public Artist artist;
		

	/**
	 * @param recordingInfo
	 * @param submitter
	 * @param artist
	 */
	public RecordingsSearchResult(RecordingDetails recordingInfo, User submitter, Artist artist)
		{
		this.recordingInfo = recordingInfo;
		this.submitter = submitter;	
		this.artist = artist;
		}
	}
