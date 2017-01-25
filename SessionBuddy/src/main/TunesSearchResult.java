package main;

public class TunesSearchResult 
{
	// Purpose: Structure to hold an individual search result from thesession.org API
	// These are the only attributes we care about in the search result
	public String tuneID;
	public String tuneTitle;
	public String tuneType;
	public String submittedBy;
	public String submittedDate;

	public TunesSearchResult(String id, String title, String type, String submitter, String date)
		{// Constructor that populates the entire structure in one go
		tuneID = id;
		tuneTitle = title;
		tuneType = type;
		submittedBy = submitter;
		submittedDate = date;
		}
}
