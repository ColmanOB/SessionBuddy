package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import sessionbuddy.PopularSearch;
import sessionbuddy.wrappers.resultsets.SearchResultTunesPopular;


class Test_RetrievePopular_Tunes
	{
	public static void main(String[] args) throws URISyntaxException
	   {
		try
			{
			// Set the search parameters
			int resultsPerPage = 50;
			int pageNumber = 2;
			
			// Instantiate a PopularSearch object
			PopularSearch search = new PopularSearch(resultsPerPage, pageNumber);
			
			// Perform the search by calling the getTunes method on the PopularSearch object
			ArrayList<SearchResultTunesPopular> resultSet = search.listTunes();
			
			// Loop through the results and print each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				System.out.println("Tune ID: " + resultSet.get(i).tuneDetails.generalTuneDetails.basicTuneDetails.tuneID);
				System.out.println("Tune Name: " + resultSet.get(i).tuneDetails.generalTuneDetails.basicTuneDetails.tuneName);
				System.out.println("Tune URL: " + resultSet.get(i).tuneDetails.generalTuneDetails.basicTuneDetails.tuneURL);
				System.out.println("No. of Tunebooks: "+ resultSet.get(i).tuneDetails.tunebooks);
				System.out.println("Date Submitted: "+ resultSet.get(i).tuneDetails.generalTuneDetails.submittedDate);
				System.out.println("Tune Type: "+ resultSet.get(i).tuneDetails.generalTuneDetails.tuneType);

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
