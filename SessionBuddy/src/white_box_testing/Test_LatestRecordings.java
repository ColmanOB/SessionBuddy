package white_box_testing;

import java.util.ArrayList;

import json_object_wrappers.RecordingsSearchResult;
import main.RetrieveLatest;

/**
 * Example usage of the searchDiscussions method of RetrieveLatest class to search thesession.org API for a recording based on a set of search terms, and store the results.
 * 
 * This class does not test the iteration through multiple pages of JSON search results
 * 
 * @author Colman
 * @since 2017-08-13
 */

class Test_LatestRecordings

{
public static void main(String[] args)
   {
	// Set the search parameters
	int resultsPerPage = 50;
	ArrayList<RecordingsSearchResult> resultSet;
	
	// Instantiate a RetrieveLatest object
	RetrieveLatest search = new RetrieveLatest();
	
	try
		{
		// Pass in the search parameters
		resultSet = search.getLatestRecordings(resultsPerPage);
		
		// Loop through the results and print each attribute of each individual result in the set
		for (int i = 0; i < resultSet.size(); i++)
			{
			System.out.println(resultSet.get(i).recordingInfo.recordingID);
			System.out.println(resultSet.get(i).recordingInfo.recordingName);
			System.out.println(resultSet.get(i).recordingInfo.recordingDate);
			System.out.println(resultSet.get(i).recordingInfo.recordingURL);

					
			System.out.println(resultSet.get(i).submitter.userID);
			System.out.println(resultSet.get(i).submitter.userName);
			System.out.println(resultSet.get(i).submitter.userURL);
			
			System.out.println(resultSet.get(i).artist.artistID);
			System.out.println(resultSet.get(i).artist.artistName);
			System.out.println(resultSet.get(i).artist.artistPageURL);
			
			System.out.println("\n");
			}
		}
	
	catch (IllegalArgumentException e)
		// Catch any cases where an invalid number of results per page has been specified
		{
		System.out.println(e.getMessage());
		}	

   	}
}
