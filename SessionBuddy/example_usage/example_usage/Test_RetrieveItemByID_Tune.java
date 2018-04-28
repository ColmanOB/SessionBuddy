package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;

import sessionbuddy.ItemRetriever;
import sessionbuddy.wrappers.resultsets.ItemResultTune;

public class Test_RetrieveItemByID_Tune 
	{
	public static void main(String[] args) throws URISyntaxException
		{	
		try
			{
			// Set the search parameters
			int tuneID = 2;		
			
			// Instantiate a RetrieveItem object
			ItemRetriever search = new ItemRetriever(tuneID);
			
			// Perform the search
			ItemResultTune resultSet = search.getTune();
			
			// Retrieve all the general tune details
			System.out.println("Tune ID: " + resultSet.tuneDetails.tuneDetails.tuneID);
			System.out.println("Tune Title: " + resultSet.tuneDetails.tuneDetails.tuneName);
			System.out.println("Tune Type: " + resultSet.tuneDetails.tuneType);
			System.out.println("Date Submitted: " + resultSet.tuneDetails.submittedDate);
			System.out.println("Date Submitted: " + resultSet.tuneDetails.tuneDetails.tuneURL);
	
		
			System.out.println("Tune Submitter ID: " + resultSet.member.userID);
			System.out.println("Tune Submitter Username: " + resultSet.member.userName);
			System.out.println("Tune Submitter User Profile" + resultSet.member.userURL);
			System.out.println("\n");
			
			// Loop through each setting of the tune and print it
			for (int i = 0; i < resultSet.settings.size(); i++)
				{
				System.out.println("Setting ID: " + resultSet.settings.get(i).settingDetails.settingID);
				System.out.println("Setting Date: " + resultSet.settings.get(i).settingDetails.date);
				System.out.println("Setting Key: " + resultSet.settings.get(i).settingDetails.key);
				System.out.println("Tune Body: " + resultSet.settings.get(i).abc);
				System.out.println("Setting URL: " + resultSet.settings.get(i).settingDetails.settingURL);
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
		
		catch (IllegalArgumentException | IllegalStateException | IOException e)
			{
			e.printStackTrace();
			}
		}
	}
