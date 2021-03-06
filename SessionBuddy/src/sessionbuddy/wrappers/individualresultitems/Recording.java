package sessionbuddy.wrappers.individualresultitems;

import sessionbuddy.wrappers.granularobjects.Artist;
import sessionbuddy.wrappers.granularobjects.RecordingDetails;
import sessionbuddy.wrappers.granularobjects.User;

/**
 * A structure to hold an individual recording listing from a set of search
 * results 
 * 
 * The search may be a keyword-based search, or a search for
 * recently-added recordings
 * 
 * @author Colman O'B
 * @since 2019-04-10
 */
public class Recording
{
    /**
     * General details relating to the recording
     */
    public RecordingDetails recordingDetails;

    /**
     * Details of the user who submitted the recording
     */
    public User user;

    /**
     * Details of the recording artist/group
     */
    public Artist artist;

    /**
     * Constructor
     * 
     * @param recordingInfo a RecordingDetails object already populated with the recording's details
     * @param submitter an already-populated User object representing the recording submitter
     * @param artist an Artist object populated with the details of the recording artist/group
     */
    public Recording(RecordingDetails recordingInfo, User submitter, Artist artist)
    {
        this.recordingDetails = recordingInfo;
        this.user = submitter;
        this.artist = artist;
    }
}
