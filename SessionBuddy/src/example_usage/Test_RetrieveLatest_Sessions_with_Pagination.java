package example_usage;

import java.io.IOException;
import java.util.ArrayList;

import sessionbuddy.LatestSearch;
import sessionbuddy.wrappers.resultsets.SearchResultSessions;


class Test_RetrieveLatest_Sessions_with_Pagination
	{
	public static void main(String[] args)
	   {
		try
			{
			// Specify the number of results to be returned per page
			int resultsPerPage = 2;
			int pageNumber = 2;
			
			// Set up a structure to store the data in the response
			ArrayList<SearchResultSessions> resultSet;
			
			// Instantiate a LatestSearch object
			LatestSearch search = new LatestSearch();
				
			// Perform the search by calling the getLatestSessions method of the LatestSearch object
			resultSet = search.getLatestSessions(resultsPerPage, pageNumber);
			
			// Loop through the results and print each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				System.out.println("Session ID: " + resultSet.get(i).details.sessionID);
				System.out.println("Session URL: " + resultSet.get(i).details.sessionURL);
				System.out.println("Date Submitted: " + resultSet.get(i).details.submittedDate);
						
				System.out.println("Submitter User ID: " + resultSet.get(i).user.userID);
				System.out.println("Submitter Username: " + resultSet.get(i).user.userName);
				System.out.println("Submitter Profile Page: " + resultSet.get(i).user.userURL);
				
				System.out.println("Latitude: " + resultSet.get(i).coordinates.latitude);
				System.out.println("Longitude: " + resultSet.get(i).coordinates.longitude);
				
				System.out.println("Venue Name:" + resultSet.get(i).venue.venueName);
				System.out.println("Venue Email:" + resultSet.get(i).venue.venueEmail);
				System.out.println("Venue Phone No.:" + resultSet.get(i).venue.venuePhone);
				System.out.println("Venue Website:" + resultSet.get(i).venue.venueWebsite);
				
				System.out.println("\n");
				}
			}
		
		catch (IllegalArgumentException e)
			{
			System.out.println(e.getMessage());
			}	
		
		catch (IllegalStateException e)
			{
			System.out.println(e.getMessage());
			}	
		
		catch (IOException e)
			{
			System.out.println(e.getMessage());
			}	
	   	}
	}
