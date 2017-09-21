package example_usage;

import java.io.IOException;
import java.util.ArrayList;

import sessionbuddy.KeywordSearch;
import sessionbuddy.wrappers.resultsets.SearchResultEvents;

public class Test_KeywordSearch_Events
{
public static void main(String[] args)
   {
	// Set the search parameters
	String searchTerms = "Dublin";
	int resultsPerPage = 50;
	
	// Instantiate a KeywordSearch object
	KeywordSearch search = new KeywordSearch();
	
	// Create a structure to hold the response from the API
	ArrayList<SearchResultEvents> resultSet;
	
	try 
		{
		// Call the searchEvents method on the KeywordSearch object
		resultSet = search.searchEvents(searchTerms, resultsPerPage);
		
		// Loop through the results and print each attribute of each individual result in the set
		for (int i = 0; i < resultSet.size(); i++)
			{
			System.out.println("Event ID: " + resultSet.get(i).details.eventID);
			System.out.println("Event Name: " + resultSet.get(i).details.eventName);
			System.out.println("Date Submitted: " + resultSet.get(i).details.submittedDate);
			System.out.println("Event URL: " + resultSet.get(i).details.eventURL);	
			
			System.out.println("Venue ID: " + resultSet.get(i).venue.venueID);
			System.out.println("Venue Name: " + resultSet.get(i).venue.venueName);
			System.out.println("Venue Email: " + resultSet.get(i).venue.venueEmail);
			System.out.println("Venue Phone Number: " + resultSet.get(i).venue.venuePhone);
			System.out.println("Venue Website: " + resultSet.get(i).venue.venueWebsite);
			
			System.out.println("Town/City ID: " + resultSet.get(i).town.townID);
			System.out.println("Town/City Name: " + resultSet.get(i).town.townName);
			
			System.out.println("Area ID: " + resultSet.get(i).area.areaID);
			System.out.println("Area Name: " + resultSet.get(i).area.areaName);
			
			System.out.println("Country ID: " + resultSet.get(i).country.countryID);
			System.out.println("Country Name: " + resultSet.get(i).country.countryName);
			
			System.out.println("Latitude: " + resultSet.get(i).coordinates.latitude);
			System.out.println("Longitude: " + resultSet.get(i).coordinates.longitude);
			
			System.out.println("Event Start Date: " + resultSet.get(i).schedule.startDate);
			System.out.println("Event End Date: " + resultSet.get(i).schedule.endDate);
			
			System.out.println("Submitter User ID: " + resultSet.get(i).user.userID);
			System.out.println("Submitter Username: " +resultSet.get(i).user.userName);
			System.out.println("Submitter Profile Page: " +resultSet.get(i).user.userURL);
			
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
