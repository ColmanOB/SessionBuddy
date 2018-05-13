package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Test;

import sessionbuddy.wrappers.resultsets.LocationResultEvents;
import sessionbuddy.wrappers.resultsets.LocationResultSessions;

public class LocationSearchTest 
	{

	@Test
	public void testSearchSessionsWithPagination() 
		{
		// Set the search parameters
		String latitude= "53.3498";
		String longitude = "6.2603";
		String radius = "75";
		int resultsPerPage = 2;
		int pageNumber = 2;
		
		try 
			{
			// Instantiate a LocationSearch object
			LocationSearch search = new LocationSearch(latitude, longitude, radius, resultsPerPage, pageNumber);
			
			// Call the searchSessions method on the LocationSearch object
			ArrayList<LocationResultSessions> resultSet = search.searchSessions();
			
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).sessionDetails.sessionID, is(notNullValue()));
				assertThat(resultSet.get(i).sessionDetails.sessionURL, is(notNullValue()));
				assertThat(resultSet.get(i).sessionDetails.submittedDate, is(notNullValue()));
						
				assertThat(resultSet.get(i).user.userID, is(notNullValue()));
				assertThat(resultSet.get(i).user.userName, is(notNullValue()));
				assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
				}
			}
		
		catch(IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
			{	
			fail(e.getMessage());
			}
		}
	
	@Test
	public void testSearchSessionsWithoutPagination() 
		{
		// Set the search parameters
		String latitude= "53.3498";
		String longitude = "6.2603";
		String radius = "75";
		int resultsPerPage = 50;
		
		try 
			{
			// Instantiate a LocationSearch object
			LocationSearch search = new LocationSearch(latitude, longitude, radius, resultsPerPage);
			
			// Call the searchSessions method on the LocationSearch object
			ArrayList<LocationResultSessions> resultSet = search.searchSessions();
			
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).sessionDetails.sessionID, is(notNullValue()));
				assertThat(resultSet.get(i).sessionDetails.sessionURL, is(notNullValue()));
				assertThat(resultSet.get(i).sessionDetails.submittedDate, is(notNullValue()));
						
				assertThat(resultSet.get(i).user.userID, is(notNullValue()));
				assertThat(resultSet.get(i).user.userName, is(notNullValue()));
				assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
				}
			}
		
		catch(IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
			{	
			fail(e.getMessage());
			}
		}

	@Test
	public void testSearchEventsWithPagination() 
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
			
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).eventDetails.eventID, is(notNullValue()));
				assertThat(resultSet.get(i).eventDetails.eventName, is(notNullValue()));
				assertThat(resultSet.get(i).eventDetails.eventURL, is(notNullValue()));
					
				assertThat(resultSet.get(i).user.userID, is(notNullValue()));
				assertThat(resultSet.get(i).user.userName, is(notNullValue()));
				assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
				
				assertThat(resultSet.get(i).venue.venueID, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venueEmail, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venuePhone, is(notNullValue()));

				assertThat(resultSet.get(i).town.townName, is(notNullValue()));
				assertThat(resultSet.get(i).country.countryName, is(notNullValue()));
				}
		   	}
	
		catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
			{
			fail(e.getMessage());
			}	
		}
	
	@Test
	public void testSearchEventsWithoutPagination() 
		{
		// Set the search parameters
		String latitude= "53.3498";
		String longitude = "6.2603";
		String radius = "500";
		int resultsPerPage = 50;
		
		try
			{
			// Instantiate a LocationSearch object
			LocationSearch search = new LocationSearch(latitude, longitude, radius, resultsPerPage);
			
			// Call the searchEvents method
			ArrayList<LocationResultEvents> resultSet = search.searchEvents();
			
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).eventDetails.eventID, is(notNullValue()));
				assertThat(resultSet.get(i).eventDetails.eventName, is(notNullValue()));
				assertThat(resultSet.get(i).eventDetails.eventURL, is(notNullValue()));
					
				assertThat(resultSet.get(i).user.userID, is(notNullValue()));
				assertThat(resultSet.get(i).user.userName, is(notNullValue()));
				assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
				
				assertThat(resultSet.get(i).venue.venueID, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venueEmail, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venuePhone, is(notNullValue()));
	
				assertThat(resultSet.get(i).town.townName, is(notNullValue()));
				assertThat(resultSet.get(i).country.countryName, is(notNullValue()));
				}
		   	}
	
		catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
			{
			fail(e.getMessage());
			}	
		}
	}
