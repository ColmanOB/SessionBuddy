package white_box_testing;

import java.util.ArrayList;

import json_object_wrappers.SessionsSearchResult;
import json_object_wrappers.TunesSearchResult;
import main.KeywordSearch;
import main.KeywordSearchFactory;
import main.SearchSessions;
import main.SearchTunes;

public class Test_SearchFactory
//Purpose: Search the session.org API for a set of search terms, and store the results
{
public static void main(String[] args)
   {
	// Set the search parameters
	String searchTerms = "Cock";
	int resultsPerPage = 50;
	
	// Instantiate a TheSessionAPISearcher object
	/*SearchFactory factory*/
	KeywordSearch<?> search = new KeywordSearchFactory().getSearch("Tunes");
	//Search<ArrayList<SessionsSearchResult>> search = (Search<ArrayList<SessionsSearchResult>>) factory.getSearch("Sessions");
	if (search instanceof SearchSessions)
		{
		// Pass in the search parameters
		ArrayList<SessionsSearchResult> resultSet =  (ArrayList<SessionsSearchResult>) search.executeSearch(searchTerms, resultsPerPage);
		
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
	else if (search instanceof SearchTunes)
		{
		ArrayList<TunesSearchResult> resultSet = (ArrayList<TunesSearchResult>) search.executeSearch(searchTerms, resultsPerPage);
	
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
}
