package example_usage;

import java.io.IOException;
import java.util.ArrayList;

import main.LatestSearch;
import wrappers_result_sets.LatestSearchTunes;

// TODO: the field names printed out are not 100% right - investigate and fix!

class Test_RetrieveLatest_Tunes
	{
	public static void main(String[] args)
	   {
		try
			{
			// Set the search parameters
			int resultsPerPage = 50;
			
			// Instantiate a LatestSearch object
			LatestSearch search = new LatestSearch();
			
			// Perform the search by calling the getLatestTunes method on the LatestSearch object
			ArrayList<LatestSearchTunes> resultSet = search.getLatestTunes(resultsPerPage);
			
			// Loop through the results and print each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				System.out.println("Tune ID: " + resultSet.get(i).details.tuneID);
				System.out.println("Tune URL: " + resultSet.get(i).details.tuneURL);
				System.out.println("Key: "+ resultSet.get(i).details.key);
				
				System.out.println("Setting ID: " + resultSet.get(i).settings.tuneID);
				System.out.println("Setting Name: " + resultSet.get(i).settings.tuneName);
				System.out.println("Setting URL: " + resultSet.get(i).settings.tuneURL);
				
				System.out.println("Key: "+ resultSet.get(i).details.date);
	
				System.out.println("User ID: " + resultSet.get(i).submitter.userID);
				System.out.println("User Name: " + resultSet.get(i).submitter.userName);
				System.out.println("User Profile Page: " + resultSet.get(i).submitter.userURL); 
				
				System.out.println("\n");
				}
		   	}
		
		catch (IllegalArgumentException e)
			{
			System.out.println(e.getMessage());
			}
		
		catch (IllegalStateException e)
			{
			System.out.println(e.getMessage());
			}
		
		catch (IOException e)
			{
			System.out.println(e.getMessage());
			}
	   }
	}
