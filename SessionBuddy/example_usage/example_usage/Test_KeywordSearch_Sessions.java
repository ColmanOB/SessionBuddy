package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import sessionbuddy.KeywordSearch;
import sessionbuddy.wrappers.resultsets.SearchResultSessions;


public class Test_KeywordSearch_Sessions
	{
	
	public static void main(String[] args) throws URISyntaxException
	   {
		// Set the search parameters
		String searchTerms = "London";
		int resultsPerPage = 50;
		
		// Instantiate a KeywordSearch object
		KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage);
		
		try
			{
			// Perform the search by calling the searchSessions method and passing in the search parameters
			ArrayList<SearchResultSessions> resultSet = search.searchSessions();
			
			// Loop through the results and print each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				System.out.println("Session ID: " + resultSet.get(i).details.sessionID);
				System.out.println("Session URL: " + resultSet.get(i).details.sessionURL);
				System.out.println("Submitted Date: " + resultSet.get(i).details.submittedDate);	
				
				System.out.println("Venue ID: " + resultSet.get(i).venue.venueID);
				System.out.println("Venue Name: " + resultSet.get(i).venue.venueName);
				System.out.println("Venue Email: " + resultSet.get(i).venue.venueEmail);
				System.out.println("Venue Phone No.: " + resultSet.get(i).venue.venuePhone);
				System.out.println("Venue Website: " + resultSet.get(i).venue.venueWebsite);
						
				System.out.println("Submitter User ID: " + resultSet.get(i).user.userID);
				System.out.println("Submitter User Name: " + resultSet.get(i).user.userName);
				System.out.println("Submitter Profile Page: " + resultSet.get(i).user.userURL);
				
				System.out.println("Latitude: " + resultSet.get(i).coordinates.latitude);
				System.out.println("Longitude: " + resultSet.get(i).coordinates.longitude);
				
				System.out.println("Town ID: " + resultSet.get(i).town.townID);
				System.out.println("Town Name: " + resultSet.get(i).town.townName);
				
				System.out.println("Area ID: " + resultSet.get(i).area.areaID);
				System.out.println("Area Name: " + resultSet.get(i).area.areaName);
				
				System.out.println("Area ID: " + resultSet.get(i).country.countryID);
				System.out.println("Area Name: " + resultSet.get(i).country.countryName);
				
				System.out.println("\n");
				}
			}
		
			catch (IllegalArgumentException e)
				{
				e.printStackTrace();
				}
		
			catch (IllegalStateException e)
				{
				e.printStackTrace();
				}
		
			catch (IOException e)
				{
				e.printStackTrace();
				}
	   	}
	}