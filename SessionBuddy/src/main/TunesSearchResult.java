package main;

/**
 * A structure to hold an individual tune from a set of search results from thesession.org API
 * 
 * @author Colman O'B
 * @since 2017-01-26
 *
 */
public class TunesSearchResult 
	{
	// TODO: Add support for all elements in the response
	public String tuneID;
	public String tuneTitle;
	public String tuneType;
	public String submittedBy;
	public String submittedDate;
	
	// TODO: Add a new constructor to accept all elements as arguments

	/**
	 * Constructor used to populate the structure with the following parameters:
	 * 
	 * @param id The tune's unique ID number in thesession.org database
	 * @param title The tune's title in thesession.org database
	 * @param type The type of tune (jig, reel, hornpipe etc.)
	 * @param submitter The username of the session.org user who originally submitted the tune to the database
	 * @param date The date one which the tune was originally submitted to thesession.org
	 */
	public TunesSearchResult(String id, String title, String type, String submitter, String date)
		{
		tuneID = id;
		tuneTitle = title;
		tuneType = type;
		submittedBy = submitter;
		submittedDate = date;
		}
	}
