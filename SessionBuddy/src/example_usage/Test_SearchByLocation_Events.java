package example_usage;

import java.io.IOException;
import java.util.ArrayList;

import sessionbuddy.LocationSearch;
import sessionbuddy.wrappers.resultsets.LocationResultEvents;


public class Test_SearchByLocation_Events
	{
	public static void main(String[] args)
	   {
		// Set the search parameters
		String latitude= "53.3498";
		String longitude = "6.2603";
		String radius = "500";
		int resultsPerPage = 50;
		
		try
		{
		// Instantiate a TheSessionAPISearcher object
		LocationSearch search = new LocationSearch();
		
		// Pass in the search parameters
		ArrayList<LocationResultEvents> resultSet = search.searchEventsByLocation(latitude, longitude, radius, resultsPerPage);
		
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
