package white_box_testing;

import java.util.ArrayList;

import json_object_wrappers.TunesSearchResult;
import main.SearchTunes;

public class Test_TuneSearch
//Purpose: Search the session.org API for a set of search terms, and store the results
{
public static void main(String[] args)
   {
	// Set the search parameters
	String searchTerms = "Kesh";
	int resultsPerPage = 50;
	
	// Instantiate a TheSessionAPISearcher object
	SearchTunes search = new SearchTunes();
	
	// Pass in the search parameters
	ArrayList<TunesSearchResult> resultSet = search.executeSearch(searchTerms, resultsPerPage);
	
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
		}
   	}

}