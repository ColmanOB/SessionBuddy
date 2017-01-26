package main;

import java.util.ArrayList;

public class Test_TuneSearch
//Purpose: Search the session.org API for a set of search terms, and store the results
{
public static void main(String[] args)
   {
	// Set the search parameters
	String searchTerms = "Kesh";
	int resultsPerPage = 50;
	
	// Instantiate a TheSessionAPISearcher object
	TuneSearch search = new TuneSearch();
	
	// Pass in the search parameters
	ArrayList<TunesSearchResult> resultSet = search.executeSearch(searchTerms, resultsPerPage);
	
	// Loop through the results and print each attribute of each individual result in the set
	for (int i = 0; i < resultSet.size(); i++)
		{
		System.out.println(resultSet.get(i).tuneTitle);
		System.out.println(resultSet.get(i).tuneID);
		System.out.println(resultSet.get(i).tuneType);
		System.out.println(resultSet.get(i).submittedBy);
		System.out.println(resultSet.get(i).submittedDate);
		System.out.println("\n");
		}
   	}

}
