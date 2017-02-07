package white_box_testing;

import java.util.ArrayList;

import json_object_wrappers.DiscussionsSearchResult;
import json_object_wrappers.TunesSearchResult;
import main.KeywordSearch;
import main.SearchDiscussions;


public class Test_KeywordSearch 
	{

	public static void main(String[] args)
		   {
			/*// Set the search parameters
			String searchTerms = "Kesh";
			int resultsPerPage = 50;
			
			// Instantiate a TheSessionAPISearcher object
			KeywordSearch search = new KeywordSearch(searchTerms,resultsPerPage);
			
			// Pass in the search parameters
			ArrayList<TunesSearchResult> resultSet = search.searchTunes();
			
			// Loop through the results and print each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				System.out.println(resultSet.get(i).details.tuneID);
				System.out.println(resultSet.get(i).details.tuneTitle);
				System.out.println(resultSet.get(i).details.tuneType);
				System.out.println(resultSet.get(i).details.tuneURL);
				System.out.println(resultSet.get(i).details.submittedDate);
				
				System.out.println(resultSet.get(i).submitter.userID);
				System.out.println(resultSet.get(i).submitter.userName);
				System.out.println(resultSet.get(i).submitter.userURL);
				
				System.out.println("\n");
				} */
		
		// Set the search parameters
		String searchTerms = "London";
		int resultsPerPage = 50;
		
		// Instantiate a TheSessionAPISearcher object
		KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage);
		
		// Pass in the search parameters
		ArrayList<DiscussionsSearchResult> resultSet = search.searchDiscussions();
		
		// Loop through the results and print each attribute of each individual result in the set
		for (int i = 0; i < resultSet.size(); i++)
			{
			System.out.println(resultSet.get(i).details.discussionID);
			System.out.println(resultSet.get(i).details.discussionName);
			System.out.println(resultSet.get(i).details.submittedDate);
			System.out.println(resultSet.get(i).details.discussionURL);	
			System.out.println(resultSet.get(i).details.numberOfComments);	
					
			System.out.println(resultSet.get(i).user.userID);
			System.out.println(resultSet.get(i).user.userName);
			System.out.println(resultSet.get(i).user.userURL);
			
			System.out.println("\n");
			}
		   	}

	}

