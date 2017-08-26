package white_box_testing;

import java.net.MalformedURLException;
import java.util.ArrayList;

import json_object_wrappers.SessionsSearchResult;
import main.RetrieveLatest;

/**
 * Example usage of the searchDiscussions method of RetrieveLatest class to search thesession.org API for a discussion based on a set of search terms, and store the results.
 * 
 * This class does not test the iteration through multiple pages of JSON search results
 * 
 * @author Colman
 * @since 2017-08-13
 */

class Test_LatestSessions

{
public static void main(String[] args) throws MalformedURLException, RuntimeException
   {
	// Set the search parameters
	int resultsPerPage = 50;
	ArrayList<SessionsSearchResult> resultSet;
	
	// Instantiate a RetrieveLatest object
	RetrieveLatest search = new RetrieveLatest();
	
	try
		{
		// Pass in the search parameters
		resultSet = search.getLatestSessions(resultsPerPage);
		
		// Loop through the results and print each attribute of each individual result in the set
		for (int i = 0; i < resultSet.size(); i++)
			{
			System.out.println(resultSet.get(i).details.sessionID);
			System.out.println(resultSet.get(i).details.sessionURL);
			System.out.println(resultSet.get(i).details.submittedDate);
					
			System.out.println(resultSet.get(i).user.userID);
			System.out.println(resultSet.get(i).user.userName);
			System.out.println(resultSet.get(i).user.userURL);
			
			System.out.println(resultSet.get(i).coordinates.latitude);
			System.out.println(resultSet.get(i).coordinates.longitude);
			
			System.out.println(resultSet.get(i).venue.venueName);
			System.out.println(resultSet.get(i).venue.venueEmail);
			System.out.println(resultSet.get(i).venue.venuePhone);
			System.out.println(resultSet.get(i).venue.venueWebsite);
			
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
