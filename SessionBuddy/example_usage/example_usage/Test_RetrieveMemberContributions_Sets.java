package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import sessionbuddy.MemberContributionSearch;
import sessionbuddy.wrappers.resultsets.SearchResultSets;


class Test_RetrieveMemberContributions_Sets
	{
	public static void main(String[] args) throws URISyntaxException
	   {
		try
			{
			// Set the search parameters
			int userID = 1;
			int resultsPerPage = 50;
			
			// Instantiate a LatestSearch object
			MemberContributionSearch search = new MemberContributionSearch(userID, resultsPerPage);
			
			// Perform the search by calling the getLatestTunes method on the LatestSearch object
			ArrayList<SearchResultSets> resultSet = search.listSets();
			
			// Loop through the results and print each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{				
				System.out.println("Date: "+ resultSet.get(i).setDetails.setID);
				System.out.println("Date: "+ resultSet.get(i).setDetails.setName);
				System.out.println("Date: "+ resultSet.get(i).setDetails.setURL);
				System.out.println("Date: "+ resultSet.get(i).setDetails.setDate);
	
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
