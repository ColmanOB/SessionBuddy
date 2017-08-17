package json_object_wrappers;

public class DiscussionComment 
	{
	//Purpose: A wrapper for the individual comments within a discussion
	public int id;		// The identifier for the particular comment in thesession.org database
	public String url;	// The URL of the particular comment
	public String subject;	// The subject line of the comment
	public String content;	// The actual text of the comment submitted by the user
	public User member;	// The user who submitted the comment
	public String date;	// The date on which the comment was submitted to thesession.org
	
	public DiscussionComment(int id, String url, String subject, String content, User member, String date)
		{
		this.id = id;
		this.url = url;
		this.subject = subject;
		this.content = content;
		this.member = member;
		this.date = date;
		}
	}

