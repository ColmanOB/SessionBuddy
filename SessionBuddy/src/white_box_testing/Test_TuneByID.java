package white_box_testing;

import json_object_wrappers.TuneByIDResult;

import main.RetrieveItem;

public class Test_TuneByID 
{
	public static void main(String[] args)
		{
		// Set the search parameters
		String searchTerms = "tunes";
		String tuneID = "2";
		int resultsPerPage = 50;
		
		
		// Instantiate a RetrieveItem object
		RetrieveItem search = new RetrieveItem();
		
		// Pass in the parameters for the tune we want to retrieve
		TuneByIDResult resultSet = search.getTuneByID(searchTerms, tuneID, resultsPerPage);
		
		System.out.println(resultSet.id);
		System.out.println(resultSet.name);
		System.out.println(resultSet.url);
		
		System.out.println(resultSet.member.userID);
		System.out.println(resultSet.member.userName);
		System.out.println(resultSet.member.userURL);
		
		System.out.println(resultSet.date);
		System.out.println(resultSet.type);
		System.out.println(resultSet.tunebooks);
		System.out.println(resultSet.recordings);
		
		System.out.println(resultSet.aliases.toString());
		

		// Loop through each individual setting of the tune and print the details
		for (int i = 0; i < resultSet.settings.size(); i++)
			{
			System.out.println(resultSet.settings.get(i).id);
			System.out.println(resultSet.settings.get(i).url);
			System.out.println(resultSet.settings.get(i).date);
			System.out.println(resultSet.settings.get(i).key);
			System.out.println(resultSet.settings.get(i).abc);
			
			System.out.println(resultSet.settings.get(i).member.userID);
			System.out.println(resultSet.settings.get(i).member.userName);
			System.out.println(resultSet.settings.get(i).member.userURL);
			
			System.out.println("\n");
			}
		}

}
