package sessionbuddy.wrappers.resultsets;

import sessionbuddy.wrappers.granularobjects.Artist;
import sessionbuddy.wrappers.granularobjects.RecordingDetails;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A structure to hold an individual recording listing from a set of search results
 * The search may be a keyword-based search or a search for recently-added recordings
 * 
 * @author Colman O'B
 * @since 2017-09-17
 */
public class SearchResultsRecordings 
	{
	public RecordingDetails recordingInfo;	// General details relating to the recording
	public User submitter;					// Details of the user who submitted the recording
	public Artist artist;					// Details of the recording artist/group

	/**
	 * Constructor
	 * 
	 * @param recordingInfo a RecordingDetails object already populated with the recording's details
	 * @param submitter an already-populated User object representing the recording submitter
	 * @param artist an Artist object populated with the details of the recording artist/group
	 */
	public SearchResultsRecordings(RecordingDetails recordingInfo, User submitter, Artist artist)
		{
		this.recordingInfo = recordingInfo;
		this.submitter = submitter;	
		this.artist = artist;
		}
	}