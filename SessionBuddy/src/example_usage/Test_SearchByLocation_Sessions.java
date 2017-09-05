package example_usage;

import java.io.IOException;
import java.util.ArrayList;

import json_object_wrappers.SessionsByLocationResult;
import main.LocationSearch;


public class Test_SearchByLocation_Sessions
	{
	public static void main(String[] args)
	   {
		// Set the search parameters
		String latitude= "53.3498";
		String longitude = "6.2603";
		String radius = "75";
		int resultsPerPage = 50;
		
		try
		{
		// Instantiate a TheSessionAPISearcher object
		LocationSearch search = new LocationSearch();
		
		// Pass in the search parameters
		ArrayList<SessionsByLocationResult> resultSet = search.searchSessionsByLocation(latitude, longitude, radius, resultsPerPage);
		
		// Loop through the results and print each attribute of each individual result in the set
		for (int i = 0; i < resultSet.size(); i++)
			{
			System.out.println(resultSet.get(i).details.sessionID);
			System.out.println(resultSet.get(i).details.sessionURL);
			System.out.println(resultSet.get(i).details.submittedDate);	
					
			System.out.println(resultSet.get(i).user.userID);
			System.out.println(resultSet.get(i).user.userName);
			System.out.println(resultSet.get(i).user.userURL);
			
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
			{e.printStackTrace();
			}
	   }
	}
