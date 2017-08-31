package example_usage;

import java.net.MalformedURLException;
import java.util.ArrayList;

import json_object_wrappers.EventsByLocationResult;
import main.LocationSearch;


public class Test_SearchByLocation_Events
{
public static void main(String[] args) throws MalformedURLException, RuntimeException
   {
	// Set the search parameters
	String latitude= "53.3498";
	String longitude = "6.2603";
	String radius = "500";
	int resultsPerPage = 50;
	
	// Instantiate a TheSessionAPISearcher object
	LocationSearch search = new LocationSearch();
	
	// Pass in the search parameters
	ArrayList<EventsByLocationResult> resultSet = search.searchEventsByLocation(latitude, longitude, radius, resultsPerPage);
	
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

}
