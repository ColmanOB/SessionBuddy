package json_object_wrappers;

import java.util.ArrayList;

/**
 * Represents an individual track on a recording
 * 
 * @author Colman O'B
 * @since 2017-08-18
 */
public class TrackListing 
	{
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
