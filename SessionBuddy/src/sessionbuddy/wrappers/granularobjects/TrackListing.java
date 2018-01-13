package sessionbuddy.wrappers.granularobjects;

import java.util.ArrayList;

/**
 * Represents an individual track on a recording
 * 
 * @author Colman O'B
 * @since 2017-12-29
 */
public class TrackListing 
	{
	/**
	 * An ArrayList of TuneRecord objects, one for each tune on the track
	 */
	public ArrayList<TuneRecord> tunes;

	/**
	 * Constructor that populates all fields
	 * 
	 * @param tunes An ArrayList of TuneRecord objects
	 */
	public TrackListing(ArrayList<TuneRecord> tunes)
		{
		this.tunes = tunes;
		}

	}
