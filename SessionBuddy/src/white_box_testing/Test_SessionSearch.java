package white_box_testing;

import java.util.ArrayList;

import json_object_wrappers.SessionsSearchResult;
import main.KeywordSearch;


public class Test_SessionSearch
//Purpose: Search thesession.org API for a set of search terms, and store the results
{
public static void main(String[] args)
   {
	// Set the search parameters
	String searchTerms = "London";
	int resultsPerPage = 50;
	
	// Instantiate a TheSessionAPISearcher object
	KeywordSearch search = new KeywordSearch();
	
	// Pass in the search parameters
	ArrayList<SessionsSearchResult> resultSet = search.searchSessions(searchTerms, resultsPerPage);
	
	// Loop through the results and print each attribute of each individual result in the set
	for (int i = 0; i < resultSet.size(); i++)
		{
		System.out.println(resultSet.get(i).details.sessionID);
		System.out.println(resultSet.get(i).details.sessionURL);
		System.out.println(resultSet.get(i).details.submittedDate);	
				
		System.out.println(resultSet.get(i).user.userID);
		System.out.println(resultSet.get(i).user.userName);
		System.out.println(resultSet.get(i).user.userURL);
		
		System.out.println("\n");
		}
   	}

}
