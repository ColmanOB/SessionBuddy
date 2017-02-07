package white_box_testing;

import java.util.ArrayList;

import json_object_wrappers.SessionsSearchResult;
import main.SearchSessions;

public class Test_SessionSearch
//Purpose: Search the session.org API for a set of search terms, and store the results
{
public static void main(String[] args)
   {
	// Set the search parameters
	String searchTerms = "London";
	int resultsPerPage = 50;
	
	// Instantiate a TheSessionAPISearcher object
	SearchSessions search = new SearchSessions();
	
	// Pass in the search parameters
	ArrayList<SessionsSearchResult> resultSet = search.executeSearch(searchTerms, resultsPerPage);
	
	// Loop through the results and print each attribute of each individual result in the set
	for (int i = 0; i < resultSet.size(); i++)
		{
		System.out.println(resultSet.get(i).details.sessionID);
		System.out.println(resultSet.get(i).details.submittedDate);
		System.out.println(resultSet.get(i).details.sessionURL);	
		
		System.out.println(resultSet.get(i).venue.venueID);
		System.out.println(resultSet.get(i).venue.venueName);
		System.out.println(resultSet.get(i).venue.venueEmail);
		System.out.println(resultSet.get(i).venue.venuePhone);
		System.out.println(resultSet.get(i).venue.venueWebsite);
		
		System.out.println(resultSet.get(i).town.townID);
		System.out.println(resultSet.get(i).town.townName);
		
		System.out.println(resultSet.get(i).area.areaID);
		System.out.println(resultSet.get(i).area.areaName);
		
		System.out.println(resultSet.get(i).country.countryID);
		System.out.println(resultSet.get(i).country.countryName);
		
		System.out.println(resultSet.get(i).coordinates.latitude);
		System.out.println(resultSet.get(i).coordinates.longitude);
		
		System.out.println(resultSet.get(i).user.userID);
		System.out.println(resultSet.get(i).user.userName);
		System.out.println(resultSet.get(i).user.userURL);
		
		System.out.println("\n");
		}
   	}

}
