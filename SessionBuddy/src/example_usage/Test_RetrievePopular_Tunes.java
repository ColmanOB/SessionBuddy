package example_usage;

import java.io.IOException;
import java.util.ArrayList;

import sessionbuddy.PopularSearch;
import sessionbuddy.wrappers.resultsets.PopularTunes;


class Test_RetrievePopular_Tunes
	{
	public static void main(String[] args)
	   {
		try
			{
			// Set the search parameters
			int resultsPerPage = 50;
			
			// Instantiate a PopularSearch object
			PopularSearch search = new PopularSearch();
			
			// Perform the search by calling the getLatestTunes method on the PopularSearch object
			ArrayList<PopularTunes> resultSet = search.getPopularTunes(resultsPerPage);
			
			// Loop through the results and print each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				System.out.println("Tune ID: " + resultSet.get(i).tuneDetails.tuneID);
				System.out.println("Tune Name: " + resultSet.get(i).tuneDetails.tuneName);
				System.out.println("No. of Tunebooks: "+ resultSet.get(i).tuneDetails.tunebooks);

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
