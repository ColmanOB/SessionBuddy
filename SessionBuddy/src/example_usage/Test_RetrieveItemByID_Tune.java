package example_usage;

import java.io.IOException;
import json_object_wrappers.TuneByIDResult;
import main.ItemRetriever;

public class Test_RetrieveItemByID_Tune 
{
	public static void main(String[] args)
		{
		
		try
			{
			// Set the search parameters
			String tuneID = "2";		
			
			// Instantiate a RetrieveItem object
			ItemRetriever search = new ItemRetriever();
			
			// Pass in the parameters for the tune we want to retrieve
			TuneByIDResult resultSet = search.getTuneByID(tuneID);
			
			// Retrieve all the general tune details
			System.out.println("Tune ID: " + resultSet.tuneDetails.tuneID);
			System.out.println("Tune Title: " + resultSet.tuneDetails.tuneTitle);
			System.out.println("Tune Type: " + resultSet.tuneDetails.tuneType);
			System.out.println("Date Submitted: " + resultSet.tuneDetails.submittedDate);
			System.out.println("Date Submitted: " + resultSet.tuneDetails.tuneURL);
	
		
			System.out.println("Tune Submitter ID: " + resultSet.member.userID);
			System.out.println("Tune Submitter Username: " + resultSet.member.userName);
			System.out.println("Tune Submitter User Profile" + resultSet.member.userURL);
			System.out.println("\n");
			
			// Loop through each setting of the tune and print it
			for (int i = 0; i < resultSet.settings.size(); i++)
				{
				System.out.println("Setting ID: " + resultSet.settings.get(i).id);
				System.out.println("Setting Date: " + resultSet.settings.get(i).date);
				System.out.println("Setting Key: " + resultSet.settings.get(i).key);
				System.out.println("Tune Body: " + resultSet.settings.get(i).abc);
				System.out.println("Setting URL: " + resultSet.settings.get(i).url);
				System.out.println("\n");
				}
	
			// Loop through each individual comment on the tune's page and print the details
			for (int i = 0; i < resultSet.comments.size(); i++)
				{
				System.out.println("Comment ID: " + resultSet.comments.get(i).id);
				System.out.println("Comment Subject Line: " + resultSet.comments.get(i).subject);
				System.out.println("Comment URL: " + resultSet.comments.get(i).url);
				System.out.println("Comment Date: " + resultSet.comments.get(i).date);
	
				System.out.println("Commenter's User ID: " +resultSet.comments.get(i).member.userID);
				System.out.println("Commenter's Username: " +resultSet.comments.get(i).member.userName);
				System.out.println("Commenter's Profile Page: " +resultSet.comments.get(i).member.userURL);
			
				System.out.println("Comment: " + resultSet.comments.get(i).content);
				
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
