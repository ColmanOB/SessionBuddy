package white_box_testing;

import java.net.MalformedURLException;
import java.util.ArrayList;

import json_object_wrappers.TunesSearchResult;
import main.RetrieveLatest;

class Test_LatestTunes
//Purpose: Search the session.org API for a set of search terms, and store the results
{
public static void main(String[] args) throws MalformedURLException, RuntimeException
   {
	// Set the search parameters
	int resultsPerPage = 50;
	
	// Instantiate a TheSessionAPISearcher object
	RetrieveLatest search = new RetrieveLatest();
	
	// Pass in the search parameters
	ArrayList<TunesSearchResult> resultSet = search.getLatestTunes(resultsPerPage);
	
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
