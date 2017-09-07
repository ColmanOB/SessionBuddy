package example_usage;

import java.io.IOException;
import java.util.ArrayList;

import main.KeywordSearch;
import wrappers_result_sets.SearchResultTunes;

public class Test_KeywordSearch_Tune
	{
	
	public static void main(String[] args)
	   {
		// Set the search parameters
		String searchTerms = "Bucks";
		int resultsPerPage = 50;
		
		// Instantiate a KeywordSearch object
		KeywordSearch search = new KeywordSearch();
		
		try
			{
			// Call the searchTunes method of KeywordSearch and pass in the search parameters
			ArrayList<SearchResultTunes> resultSet = search.searchTunes(searchTerms, resultsPerPage);
			
			// Loop through the results and print each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				System.out.println("Tune ID: " + resultSet.get(i).details.tuneID);
				System.out.println("Tune Title: " + resultSet.get(i).details.tuneTitle);
				System.out.println("Tune Type: " + resultSet.get(i).details.tuneType);
				System.out.println("Tune URL: " + resultSet.get(i).details.tuneURL);
				System.out.println("Date Submitted: " + resultSet.get(i).details.submittedDate);
				
				System.out.println("Submitted by User ID: " + resultSet.get(i).submitter.userID);
				System.out.println("Submitted by Username: " + resultSet.get(i).submitter.userName);
				System.out.println("Submitter's Profile Page: " + resultSet.get(i).submitter.userURL);
				
				System.out.println("\n");
				}
		   	}
		
		catch (IllegalArgumentException e)
			{
			e.printStackTrace();
			}
		
		catch (IOException e)
			{
			e.printStackTrace();
			}
		
		}
	}
