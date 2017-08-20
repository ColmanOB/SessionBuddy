package white_box_testing;

import json_object_wrappers.SessionByIDResult;

import main.RetrieveItemByID;

public class Test_SessionByID 
{
	public static void main(String[] args)
		{
		// Set the parameters
		String category = "sessions";
		String sessionID = "2379";
		int resultsPerPage = 50;
		
		
		// Instantiate a RetrieveItem object
		RetrieveItemByID search = new RetrieveItemByID();
		
		// Pass in the parameters for the session we want to retrieve
		SessionByIDResult resultSet = search.getSessionByID(category, sessionID, resultsPerPage);
		
		System.out.println(resultSet.sessionDetails.sessionID);
		System.out.println(resultSet.sessionDetails.sessionURL);
		System.out.println(resultSet.sessionDetails.submittedDate);
	
		System.out.println(resultSet.member.userID);
		System.out.println(resultSet.member.userName);
		System.out.println(resultSet.member.userURL);
		
		System.out.println(resultSet.schedule.toString());
		

		// Loop through each comment on the session and print the details
		for (int i = 0; i < resultSet.comments.size(); i++)
			{
			System.out.println(resultSet.comments.get(i).id);
			System.out.println(resultSet.comments.get(i).url);
			System.out.println(resultSet.comments.get(i).date);

			System.out.println(resultSet.comments.get(i).member.userID);
			System.out.println(resultSet.comments.get(i).member.userName);
			System.out.println(resultSet.comments.get(i).member.userURL);
			System.out.println(resultSet.comments.get(i).content);
			
			System.out.println("\n");
			}
		}
}
