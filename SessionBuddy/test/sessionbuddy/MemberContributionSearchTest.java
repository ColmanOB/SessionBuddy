package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Test;

import sessionbuddy.wrappers.resultsets.SearchResultDiscussions;
import sessionbuddy.wrappers.resultsets.SearchResultEvents;
import sessionbuddy.wrappers.resultsets.SearchResultSets;

public class MemberContributionSearchTest 
	{

	@Test
	public void testListTunesWithPagination() 
		{
		fail("Not yet implemented");
		}
	
	@Test
	public void testListTunesWithoutPagination() 
		{
		fail("Not yet implemented");
		}

	@Test
	public void testListRecordingsWithPagination() 
		{
		fail("Not yet implemented");
		}
	
	@Test
	public void testListRecordingsWithoutPagination() 
		{
		fail("Not yet implemented");
		}

	@Test
	public void testListSessionsWithPagination() 
		{
		fail("Not yet implemented");
		}
	
	@Test
	public void testListSessionsWithoutPagination() 
		{
		fail("Not yet implemented");
		}

	@Test
	public void testListEventsWithPagination() 
		{
		fail("Not yet implemented");
		}

	@Test
	public void testListEventsWithoutPagination() 
		{
		// Set the search parameters
		int resultsPerPage = 50;
		int userID = 1;

		// Instantiate a MemberContributionSearch object
		MemberContributionSearch search = new MemberContributionSearch(userID, resultsPerPage);
		
		try
			{		
			// Call the listEvents() method on the MemberContributionSearch object
			ArrayList<SearchResultEvents> resultSet = search.listEvents();
			
			// Loop through the results and print attributes of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).details.eventID, is(notNullValue()));
				assertThat(resultSet.get(i).details.eventName, is(notNullValue()));
				assertThat(resultSet.get(i).details.eventURL, is(notNullValue()));
				assertThat(resultSet.get(i).details.submittedDate, is(notNullValue()));
				
				assertThat(resultSet.get(i).schedule.startDate, is(notNullValue()));
				assertThat(resultSet.get(i).schedule.endDate, is(notNullValue()));

				assertThat(resultSet.get(i).user.userID, is(notNullValue()));
				assertThat(resultSet.get(i).user.userName, is(notNullValue()));
				assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
				
				assertThat(resultSet.get(i).coordinates.latitude, is(notNullValue()));
				assertThat(resultSet.get(i).coordinates.longitude, is(notNullValue()));

				assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venueEmail, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venuePhone, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venueWebsite, is(notNullValue()));
				
				assertThat(resultSet.get(i).town.townName, is(notNullValue()));
				assertThat(resultSet.get(i).area.areaName, is(notNullValue()));
				assertThat(resultSet.get(i).country.countryName, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
			{
			fail(e.getMessage());
			}	
		}
	
	@Test
	public void testListDiscussionsWithPagination() 
		{
		// Set the search parameters
		int resultsPerPage = 4;
		int pageNumber = 2;
		int userID = 1;
		
		// Instantiate a RetrieveLatest object
		MemberContributionSearch search = new MemberContributionSearch(userID, resultsPerPage, pageNumber);
		
		try
			{
			// Pass in the search parameters
			ArrayList<SearchResultDiscussions> resultSet = search.listDiscussions();
			
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).details.discussionID, is(notNullValue()));
				assertThat(resultSet.get(i).details.discussionName, is(notNullValue()));
				assertThat(resultSet.get(i).details.submittedDate, is(notNullValue()));
				assertThat(resultSet.get(i).details.discussionURL, is(notNullValue()));
	
				assertThat(resultSet.get(i).user.userID, is(notNullValue()));
				assertThat(resultSet.get(i).user.userName, is(notNullValue()));
				assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IOException | URISyntaxException e)
			{
			fail(e.getMessage());
			}	
		}
	
	@Test
	public void testListDiscussionsWithoutPagination() 
		{
		// Set the search parameters
		int resultsPerPage = 50;
		int userID = 1;
		
		// Instantiate a RetrieveLatest object
		MemberContributionSearch search = new MemberContributionSearch(userID, resultsPerPage);
		
		try
			{
			// Pass in the search parameters
			ArrayList<SearchResultDiscussions> resultSet = search.listDiscussions();
			
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).details.discussionID, is(notNullValue()));
				assertThat(resultSet.get(i).details.discussionName, is(notNullValue()));
				assertThat(resultSet.get(i).details.submittedDate, is(notNullValue()));
				assertThat(resultSet.get(i).details.discussionURL, is(notNullValue()));
	
				assertThat(resultSet.get(i).user.userID, is(notNullValue()));
				assertThat(resultSet.get(i).user.userName, is(notNullValue()));
				assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IOException | URISyntaxException e)
			{
			fail(e.getMessage());
			}	
		}

	@Test
	public void testListSetsWithPagination() 
		{
		try
			{
			// Set the search parameters
			int userID = 1;
			int resultsPerPage = 4;
			int pageNumber = 2;
			
			// Instantiate a LatestSearch object
			MemberContributionSearch search = new MemberContributionSearch(userID, resultsPerPage, pageNumber);
			
			// Perform the search by calling the listSets method on the MemberContributionSearch object
			ArrayList<SearchResultSets> resultSet = search.listSets();
			
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{				
				assertThat(resultSet.get(i).setDetails.setID, is(notNullValue()));
				assertThat(resultSet.get(i).setDetails.setName, is(notNullValue()));
				assertThat(resultSet.get(i).setDetails.setURL, is(notNullValue()));
				assertThat(resultSet.get(i).setDetails.setDate, is(notNullValue()));
	
				assertThat(resultSet.get(i).submitter.userID, is(notNullValue()));
				assertThat(resultSet.get(i).submitter.userName, is(notNullValue()));
				assertThat(resultSet.get(i).submitter.userURL, is(notNullValue()));
				}
		   	}
		
		catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
			{
			fail(e.getMessage());
			}
		}
	
	@Test
	public void testListSetsWithoutPagination() 
		{
		try
			{
			// Set the search parameters
			int userID = 1;
			int resultsPerPage = 50;
			
			// Instantiate a LatestSearch object
			MemberContributionSearch search = new MemberContributionSearch(userID, resultsPerPage);
			
			// Perform the search by calling the listSets method on the MemberContributionSearch object
			ArrayList<SearchResultSets> resultSet = search.listSets();
			
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{				
				assertThat(resultSet.get(i).setDetails.setID, is(notNullValue()));
				assertThat(resultSet.get(i).setDetails.setName, is(notNullValue()));
				assertThat(resultSet.get(i).setDetails.setURL, is(notNullValue()));
				assertThat(resultSet.get(i).setDetails.setDate, is(notNullValue()));
	
				assertThat(resultSet.get(i).submitter.userID, is(notNullValue()));
				assertThat(resultSet.get(i).submitter.userName, is(notNullValue()));
				assertThat(resultSet.get(i).submitter.userURL, is(notNullValue()));
				}
		   	}
		
		catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
			{
			fail(e.getMessage());
			}
		}
	}
