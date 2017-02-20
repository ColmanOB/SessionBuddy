package white_box_testing;

import java.util.ArrayList;

import json_object_wrappers.NewTunesResult;
import json_object_wrappers.TunesSearchResult;
import main.KeywordSearch;
import main.NewListings;

public class Test_NewTunesListing
//Purpose: Search the session.org API for a set of search terms, and store the results
{
public static void main(String[] args)
   {
	// Set the search parameters
	int resultsPerPage = 50;
	
	// Instantiate a TheSessionAPISearcher object
	NewListings search = new NewListings();
	
	// Pass in the search parameters
	ArrayList<NewTunesResult> resultSet = search.searchTunes("tunes", resultsPerPage);
	
	// Loop through the results and print each attribute of each individual result in the set
	for (int i = 0; i < resultSet.size(); i++)
		{
		System.out.println(resultSet.get(i).settingDetails.settingID);
		System.out.println(resultSet.get(i).settingDetails.settingKey);
		System.out.println(resultSet.get(i).settingDetails.settingURL);
		System.out.println(resultSet.get(i).settingDetails.submittedDate);
		
		System.out.println(resultSet.get(i).submitter.userID);
		System.out.println(resultSet.get(i).submitter.userName);
		System.out.println(resultSet.get(i).submitter.userURL);
		
		System.out.println("\n");
		}
   	}

}
