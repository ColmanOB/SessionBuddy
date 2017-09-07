package wrappers_result_sets;

import wrappers_granular_objects.Artist;
import wrappers_granular_objects.RecordingDetails;
import wrappers_granular_objects.User;

/**
 * A structure to hold an individual recording from a set of search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-01-28
 */
public class SearchResultsRecordings 
	{
	public RecordingDetails recordingInfo;
	public User submitter;
	public Artist artist;
		

	/**
	 * @param recordingInfo
	 * @param submitter
	 * @param artist
	 */
	public SearchResultsRecordings(RecordingDetails recordingInfo, User submitter, Artist artist)
		{
		this.recordingInfo = recordingInfo;
		this.submitter = submitter;	
		this.artist = artist;
		}
	}
