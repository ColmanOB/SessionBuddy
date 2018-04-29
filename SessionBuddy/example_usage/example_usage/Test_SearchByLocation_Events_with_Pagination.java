package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import sessionbuddy.LocationSearch;
import sessionbuddy.wrappers.resultsets.LocationResultEvents;


public class Test_SearchByLocation_Events_with_Pagination
	{
	public static void main(String[] args) throws URISyntaxException
	   {
		// Set the search parameters
		String latitude= "53.3498";
		String longitude = "6.2603";
		String radius = "500";
		int resultsPerPage = 2;
		int pageNumber = 2;
		
		try
			{
			// Instantiate a LocationSearch object
			LocationSearch search = new LocationSearch(latitude, longitude, radius, resultsPerPage, pageNumber);
			
			// Call the searchEvents method
			ArrayList<LocationResultEvents> resultSet = search.searchEvents();
			
			// Loop through the results and print each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				System.out.println(resultSet.get(i).details.eventID);
				System.out.println(resultSet.get(i).details.eventName);
				System.out.println(resultSet.get(i).details.eventURL);	
						
				System.out.println(resultSet.get(i).user.userID);
				System.out.println(resultSet.get(i).user.userName);
				System.out.println(resultSet.get(i).user.userURL);
				
				System.out.println(resultSet.get(i).venue.venueID);
				System.out.println(resultSet.get(i).venue.venueName);
				System.out.println(resultSet.get(i).venue.venueEmail);
				System.out.println(resultSet.get(i).venue.venuePhone);
				
				System.out.println(resultSet.get(i).town.townName);
				
				System.out.println(resultSet.get(i).country.countryName);
				
				System.out.println("\n");
				}
		   	}
	
		catch (IllegalArgumentException | IllegalStateException | IOException e)
			{
			e.printStackTrace();
			}		
	   }
	}