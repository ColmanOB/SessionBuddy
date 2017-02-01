package white_box_testing;

import java.util.ArrayList;

import individual_result_representation.RecordingsSearchResult;
import main.SearchRecordings;

public class Test_RecordingSearch
//Purpose: Search the session.org API for a set of search terms, and store the results
{
public static void main(String[] args)
   {
	// Set the search parameters
	String searchTerms = "Horse";
	int resultsPerPage = 50;
	
	// Instantiate a TheSessionAPISearcher object
	SearchRecordings search = new SearchRecordings();
	
	// Pass in the search parameters
	ArrayList<RecordingsSearchResult> resultSet = search.executeSearch(searchTerms, resultsPerPage);
	
	// Loop through the results and print each attribute of each individual result in the set
	for (int i = 0; i < resultSet.size(); i++)
		{
		System.out.println(resultSet.get(i).recordingInfo.recordingID);
		System.out.println(resultSet.get(i).recordingInfo.recordingName);
		System.out.println(resultSet.get(i).recordingInfo.recordingURL);		
		System.out.println("\n");
		}
   	}

}
