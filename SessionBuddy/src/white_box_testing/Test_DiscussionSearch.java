package white_box_testing;

import java.util.ArrayList;

import json_object_wrappers.DiscussionsSearchResult;
import main.SearchDiscussions;


public class Test_DiscussionSearch
//Purpose: Search the session.org API for a set of search terms, and store the results
{
public static void main(String[] args)
   {
	// Set the search parameters
	String searchTerms = "London";
	int resultsPerPage = 50;
	
	// Instantiate a TheSessionAPISearcher object
	SearchDiscussions search = new SearchDiscussions();
	
	// Pass in the search parameters
	ArrayList<DiscussionsSearchResult> resultSet = search.executeSearch(searchTerms, resultsPerPage);
	
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
