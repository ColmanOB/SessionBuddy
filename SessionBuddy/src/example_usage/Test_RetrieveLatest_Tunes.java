package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import sessionbuddy.LatestSearch;
import sessionbuddy.wrappers.resultsets.LatestSearchTunes;


class Test_RetrieveLatest_Tunes
	{
	public static void main(String[] args) throws URISyntaxException
	   {
		try
			{
			// Set the search parameters
			int resultsPerPage = 50;
			
			// Instantiate a LatestSearch object
			LatestSearch search = new LatestSearch(resultsPerPage);
			
			// Perform the search by calling the getLatestTunes method on the LatestSearch object
			ArrayList<LatestSearchTunes> resultSet = search.listTunes();
			
			// Loop through the results and print each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				System.out.println("Tune ID: " + resultSet.get(i).settingDetails.settingID);
				System.out.println("Tune URL: " + resultSet.get(i).settingDetails.settingURL);
				System.out.println("Key: "+ resultSet.get(i).settingDetails.key);
				
				System.out.println("Setting ID: " + resultSet.get(i).tuneDetails.tuneID);
				System.out.println("Setting Name: " + resultSet.get(i).tuneDetails.tuneName);
				System.out.println("Setting URL: " + resultSet.get(i).tuneDetails.tuneURL);
				
				System.out.println("Date: "+ resultSet.get(i).settingDetails.date);
	
				System.out.println("User ID: " + resultSet.get(i).submitter.userID);
				System.out.println("User Name: " + resultSet.get(i).submitter.userName);
				System.out.println("User Profile Page: " + resultSet.get(i).submitter.userURL); 
				
				System.out.println("\n");
				}
		   	}
		
		catch (IllegalArgumentException | IllegalStateException | IOException e)
			{
			System.out.println(e.getMessage());
			}
	   }
	}
