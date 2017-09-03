package example_usage;

import java.io.IOException;
import json_object_wrappers.DiscussionByIDResult;

import main.ItemRetriever;

public class Test_RetrieveItemByID_Discussion 
{
	public static void main(String[] args)
		{
		try
			{
			// Set the search parameters
			String discussionID = "666";
			
			// Instantiate a RetrieveItem object
			ItemRetriever search = new ItemRetriever();
			
			// Pass in the parameters for the discussion we want to retrieve
			DiscussionByIDResult resultSet = search.getDiscussionByID(discussionID);
			
			// Print out attributes of the discussion as a whole:
			System.out.println("Discussion ID: " + resultSet.discussionDetails.discussionID);
			System.out.println("Discussion Title: " + resultSet.discussionDetails.discussionName);
			System.out.println("Discussion URL: " + resultSet.discussionDetails.discussionURL);
			System.out.println("Date: " + resultSet.discussionDetails.submittedDate);
			System.out.println("\n");
			
			// Print out attributes of the user who submitted the discussion
			System.out.println("Submitted by user: ");
			System.out.println("User ID: " + resultSet.member.userID);
			System.out.println("Username: " + resultSet.member.userName);
			System.out.println("User Profile Page: " + resultSet.member.userURL);
			System.out.println("\n");
	
			System.out.println("Comments: \n");
			
			// Loop through each individual comment in the discussion and print the details
			for (int i = 0; i < resultSet.comments.size(); i++)
				{
				System.out.println("Comment ID: " + resultSet.comments.get(i).id);
				System.out.println("Comment URL: " + resultSet.comments.get(i).url);
				System.out.println("Comment Date: " + resultSet.comments.get(i).date);
	
				System.out.println("User ID: " + resultSet.comments.get(i).member.userID);
				System.out.println("Username: " + resultSet.comments.get(i).member.userName);
				System.out.println("User Profile: " + resultSet.comments.get(i).member.userURL);
				
				System.out.println("Comment: \n" + resultSet.comments.get(i).content);
				
				System.out.println("\n");
				}
			}
		
		catch (IllegalArgumentException e)
			{
			e.printStackTrace();
			}
		
		catch (IllegalStateException e)
			{
			e.printStackTrace();
			}
		
		catch (IOException e)
			{
			e.printStackTrace();
			}
		}
	}
