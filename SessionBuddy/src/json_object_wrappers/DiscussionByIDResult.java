package json_object_wrappers;

import java.util.ArrayList;

/**
 * @author Colman
 * @since 2017-08-17
 */
public class DiscussionByIDResult 
	{
	// Variables to hold data relating to the discussion as a whole
	public String id;	
	public String name;
	public String url;
	
	public User member;
	
	public String date;
		
	public ArrayList<DiscussionComment> comments; // An array of comments in the discussion

	/**
	 * Constructor method
	 * 
	 * @param id
	 * @param name
	 * @param url
	 * @param member
	 * @param date
	 * @param type
	 * @param tunebooks
	 * @param recordings
	 * @param aliases
	 * @param settings
	 */
	public DiscussionByIDResult(String id, String name, String url, User member, String date, ArrayList<DiscussionComment> comments)
		{
		this.id = id;
		this.name = name;
		this.url = url;	
		this.member = member;
		this.date = date;
		this.comments = comments;
		}
}
