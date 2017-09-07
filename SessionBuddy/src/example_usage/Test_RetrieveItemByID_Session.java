package example_usage;

import java.io.IOException;

import main.ItemRetriever;
import wrappers_result_sets.ItemResultSession;

public class Test_RetrieveItemByID_Session 
{
	public static void main(String[] args)
		{
		try
			{
			// Set the parameters, i.e. the ID number of the session we want to retrieve
			String sessionID = "6264";
	
			// Instantiate an ItemRetriever object
			ItemRetriever search = new ItemRetriever();
			
			// Perform the search, passing in the parameters for the session we want to retrieve
			ItemResultSession resultSet = search.getSessionByID(sessionID);
			
			System.out.println("Session ID: " + resultSet.sessionDetails.sessionID);
			System.out.println("Session URL: " + resultSet.sessionDetails.sessionURL);
			System.out.println("Submitted Date: " + resultSet.sessionDetails.submittedDate);
		
			System.out.println("Submitter User ID: " + resultSet.member.userID);
			System.out.println("Submitter Username: " + resultSet.member.userName);
			System.out.println("Submitter Profile Page: " + resultSet.member.userURL);
			
			System.out.println("Session Schedule: " + resultSet.schedule.toString());
			
			System.out.println("Venue: " + resultSet.venue.venueName);
	
			// Loop through each comment on the session and print the details
			for (int i = 0; i < resultSet.comments.size(); i++)
				{
				System.out.println("Comment ID: " + resultSet.comments.get(i).id);
				System.out.println("Comment URL: " + resultSet.comments.get(i).url);
				System.out.println("Comment Date: " + resultSet.comments.get(i).date);
	
				System.out.println("Comment Submitter User ID: " + resultSet.comments.get(i).member.userID);
				System.out.println("Comment Submitter Username: " + resultSet.comments.get(i).member.userName);
				System.out.println("Comment Submitter Profile Page: " + resultSet.comments.get(i).member.userURL);
				
				System.out.println("Comment Subject Line: " + resultSet.comments.get(i).subject);
				System.out.println("Comment Content: " + resultSet.comments.get(i).content);
				
				System.out.println("\n");
				}
			}
		
		catch(IllegalArgumentException e)
			{
			e.printStackTrace();
			}
		
		catch(IllegalStateException e)
			{
			e.printStackTrace();
			}
		
		catch(IOException e)
			{
			e.printStackTrace();
			}	
		}
	}
