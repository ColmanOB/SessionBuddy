package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import sessionbuddy.SetSearch;
import sessionbuddy.wrappers.resultsets.LatestSearchSets;


class Test_RetrieveLatest_Sets
	{
	public static void main(String[] args) throws URISyntaxException
	   {
		try
			{
			// Set the search parameters
			int resultsPerPage = 50;
			
			// Instantiate a SetSearch object
			SetSearch search = new SetSearch(resultsPerPage);
			
			// Perform the search by calling the listSets method on the SetSearch object
			ArrayList<LatestSearchSets> resultSet = search.listSets();
			
			// Loop through the results and print each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				System.out.println("Set ID: " + resultSet.get(i).setDetails.setID);
				System.out.println("Set name: " + resultSet.get(i).setDetails.setName);
				System.out.println("Date Added: "+ resultSet.get(i).setDetails.setDate);
				System.out.println("URL: "+ resultSet.get(i).setDetails.setURL);
				

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
