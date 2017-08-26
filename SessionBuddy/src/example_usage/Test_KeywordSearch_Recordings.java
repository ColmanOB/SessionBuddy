package example_usage;

import java.net.MalformedURLException;
import java.util.ArrayList;

import json_object_wrappers.RecordingsSearchResult;
import main.KeywordSearch;


public class Test_KeywordSearch_Recordings
{
public static void main(String[] args) throws MalformedURLException, RuntimeException
   {
	// Set the search parameters
	String searchTerms = "Horse";
	int resultsPerPage = 50;
	
	// Instantiate a TheSessionAPISearcher object
	KeywordSearch search = new KeywordSearch();
	
	// Pass in the search parameters
	ArrayList<RecordingsSearchResult> resultSet = search.searchRecordings(searchTerms, resultsPerPage);
	
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
