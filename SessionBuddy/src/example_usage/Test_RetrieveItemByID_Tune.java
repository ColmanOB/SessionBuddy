package example_usage;

import java.net.MalformedURLException;

import json_object_wrappers.TuneByIDResult;
import main.ItemRetriever;

public class Test_RetrieveItemByID_Tune 
{
	public static void main(String[] args) throws MalformedURLException, RuntimeException
		{
		// Set the search parameters
		String tuneID = "2";		
		
		// Instantiate a RetrieveItem object
		ItemRetriever search = new ItemRetriever();
		
		// Pass in the parameters for the tune we want to retrieve
		TuneByIDResult resultSet = search.getTuneByID(tuneID);
		
		System.out.println(resultSet.tuneDetails.tuneID);
		System.out.println(resultSet.tuneDetails.tuneTitle);

	
		System.out.println(resultSet.member.userID);
		System.out.println(resultSet.member.userName);
		System.out.println(resultSet.member.userURL);
		

		// Loop through each individual setting of the tune and print the details
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
