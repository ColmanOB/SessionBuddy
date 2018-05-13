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
import sessionbuddy.wrappers.resultsets.SearchResultRecordings;
import sessionbuddy.wrappers.resultsets.SearchResultSessions;
import sessionbuddy.wrappers.resultsets.SearchResultTunesLatest;

public class LatestSearchTest 
	{

	@Test
	public void testListTunesWithoutPagination() 
		{
		// Set the search parameters
		int resultsPerPage = 50;
		
		// Instantiate a LatestSearch object
		LatestSearch search = new LatestSearch(resultsPerPage);
		
		try
			{
			// Perform the search by calling the getLatestTunes method on the LatestSearch object
			ArrayList<SearchResultTunesLatest> resultSet = search.listTunes();
			
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).settingDetails.settingID, is(notNullValue()));
				assertThat(resultSet.get(i).settingDetails.settingURL, is(notNullValue()));
				assertThat(resultSet.get(i).settingDetails.key, is(notNullValue()));
				
				assertThat(resultSet.get(i).tuneDetails.tuneID, is(notNullValue()));
				assertThat(resultSet.get(i).tuneDetails.tuneName, is(notNullValue()));
				assertThat(resultSet.get(i).tuneDetails.tuneURL, is(notNullValue()));
				
				assertThat(resultSet.get(i).settingDetails.date, is(notNullValue()));
	
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
	public void testListTunesWithPagination() 
		{
		// Set the search parameters
		int resultsPerPage = 2;
		int pageNumber = 2;
		
		// Instantiate a LatestSearch object
		LatestSearch search = new LatestSearch(resultsPerPage, pageNumber);
		
		try
			{
			// Perform the search by calling the getLatestTunes method on the LatestSearch object
			ArrayList<SearchResultTunesLatest> resultSet = search.listTunes();
			
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).settingDetails.settingID, is(notNullValue()));
				assertThat(resultSet.get(i).settingDetails.settingURL, is(notNullValue()));
				assertThat(resultSet.get(i).settingDetails.key, is(notNullValue()));
				
				assertThat(resultSet.get(i).tuneDetails.tuneID, is(notNullValue()));
				assertThat(resultSet.get(i).tuneDetails.tuneName, is(notNullValue()));
				assertThat(resultSet.get(i).tuneDetails.tuneURL, is(notNullValue()));
				
				assertThat(resultSet.get(i).settingDetails.date, is(notNullValue()));
	
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
	public void testListDiscussionsWithoutPagination() 
		{
		// Set the search parameters
		int resultsPerPage = 2;

		// Instantiate a RetrieveLatest object
		LatestSearch search = new LatestSearch(resultsPerPage);
				
		try
			{
			// Pass in the search parameters
			ArrayList<SearchResultDiscussions> resultSet = search.listDiscussions();
					
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).discussionDetails.discussionID, is(notNullValue()));
				assertThat(resultSet.get(i).discussionDetails.discussionName, is(notNullValue()));
				assertThat(resultSet.get(i).discussionDetails.submittedDate, is(notNullValue()));
				assertThat(resultSet.get(i).discussionDetails.discussionURL, is(notNullValue()));
				assertThat(resultSet.get(i).discussionDetails.numberOfComments, is(notNullValue()));
								
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
	public void testListDiscussionsWithPagination() 
		{
		// Set the search parameters
		int resultsPerPage = 2;
		int pageNumber = 2;
		
		// Instantiate a RetrieveLatest object
		LatestSearch search = new LatestSearch(resultsPerPage, pageNumber);
		
		try
			{
			// Pass in the search parameters
			ArrayList<SearchResultDiscussions> resultSet = search.listDiscussions();
			
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).discussionDetails.discussionID, is(notNullValue()));
				assertThat(resultSet.get(i).discussionDetails.discussionName, is(notNullValue()));
				assertThat(resultSet.get(i).discussionDetails.submittedDate, is(notNullValue()));
				assertThat(resultSet.get(i).discussionDetails.discussionURL, is(notNullValue()));
				assertThat(resultSet.get(i).discussionDetails.numberOfComments, is(notNullValue()));
						
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
	public void testListRecordingsWithoutPagination() 
		{
		// Set the number of results to be returned per page
		int resultsPerPage = 50;

		// Instantiate a LatestSearch object
		LatestSearch search = new LatestSearch(resultsPerPage);
		
		try
			{
			// Call the listRecordings method on the LatestSearch object
			ArrayList<SearchResultRecordings> resultSet = search.listRecordings();
				
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).recordingDetails.recordingID, is(notNullValue()));
				assertThat(resultSet.get(i).recordingDetails.recordingName, is(notNullValue()));
				assertThat(resultSet.get(i).recordingDetails.recordingDate, is(notNullValue()));
				assertThat(resultSet.get(i).recordingDetails.recordingURL, is(notNullValue()));
							
				assertThat(resultSet.get(i).submitter.userID, is(notNullValue()));
				assertThat(resultSet.get(i).submitter.userName, is(notNullValue()));
				assertThat(resultSet.get(i).submitter.userURL, is(notNullValue()));

				assertThat(resultSet.get(i).artist.artistID, is(notNullValue()));
				assertThat(resultSet.get(i).artist.artistName, is(notNullValue()));
				assertThat(resultSet.get(i).artist.artistPageURL, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
			{
			System.out.println(e.getMessage());
			}	
		}
	
	@Test
	public void testListRecordingsWithPagination() 
		{
		// Set the number of results to be returned per page
		int resultsPerPage = 2;
		int pageNumber = 2;

		// Instantiate a LatestSearch object
		LatestSearch search = new LatestSearch(resultsPerPage, pageNumber);
		
		try
			{
			// Call the listRecordings method on the LatestSearch object
			ArrayList<SearchResultRecordings> resultSet = search.listRecordings();
				
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).recordingDetails.recordingID, is(notNullValue()));
				assertThat(resultSet.get(i).recordingDetails.recordingName, is(notNullValue()));
				assertThat(resultSet.get(i).recordingDetails.recordingDate, is(notNullValue()));
				assertThat(resultSet.get(i).recordingDetails.recordingURL, is(notNullValue()));
							
				assertThat(resultSet.get(i).submitter.userID, is(notNullValue()));
				assertThat(resultSet.get(i).submitter.userName, is(notNullValue()));
				assertThat(resultSet.get(i).submitter.userURL, is(notNullValue()));

				assertThat(resultSet.get(i).artist.artistID, is(notNullValue()));
				assertThat(resultSet.get(i).artist.artistName, is(notNullValue()));
				assertThat(resultSet.get(i).artist.artistPageURL, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
			{
			System.out.println(e.getMessage());
			}	
		}


	@Test
	public void testListSessionsWithoutPagination() 
		{
		// Specify the number of results to be returned per page
		int resultsPerPage = 50;
		
		// Instantiate a LatestSearch object
		LatestSearch search = new LatestSearch(resultsPerPage);
		
		try
			{				
			// Perform the search by calling the getLatestSessions method of the LatestSearch object
			ArrayList<SearchResultSessions> resultSet = search.listSessions();
			
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).sessionDetails.sessionID, is(notNullValue()));
				assertThat(resultSet.get(i).sessionDetails.sessionURL, is(notNullValue()));
				assertThat(resultSet.get(i).sessionDetails.submittedDate, is(notNullValue()));
	
				assertThat(resultSet.get(i).user.userID, is(notNullValue()));
				assertThat(resultSet.get(i).user.userName, is(notNullValue()));
				assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
				
				assertThat(resultSet.get(i).coordinates.latitude, is(notNullValue()));
				assertThat(resultSet.get(i).coordinates.longitude, is(notNullValue()));
				
				assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venueEmail, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venuePhone, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venueWebsite, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
			{
			fail(e.getMessage());
			}
		}
	
	@Test
	public void testListSessionsWithPagination() 
		{
		// Specify the number of results to be returned per page
		int resultsPerPage = 2;
		int pageNumber = 2;
		
		// Instantiate a LatestSearch object
		LatestSearch search = new LatestSearch(resultsPerPage, pageNumber);
		
		try
			{				
			// Perform the search by calling the getLatestSessions method of the LatestSearch object
			ArrayList<SearchResultSessions> resultSet = search.listSessions();
			
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).sessionDetails.sessionID, is(notNullValue()));
				assertThat(resultSet.get(i).sessionDetails.sessionURL, is(notNullValue()));
				assertThat(resultSet.get(i).sessionDetails.submittedDate, is(notNullValue()));
	
				assertThat(resultSet.get(i).user.userID, is(notNullValue()));
				assertThat(resultSet.get(i).user.userName, is(notNullValue()));
				assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
				
				assertThat(resultSet.get(i).coordinates.latitude, is(notNullValue()));
				assertThat(resultSet.get(i).coordinates.longitude, is(notNullValue()));
				
				assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venueEmail, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venuePhone, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venueWebsite, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
			{
			fail(e.getMessage());
			}
		}

	@Test
	public void testListEventsWithoutPagination() 
		{
		// Set the search parameters
		int resultsPerPage = 50;

		// Instantiate a LatestSearch object
		LatestSearch search = new LatestSearch(resultsPerPage);
		
		try
			{	
			// Call the listEvents() method on the LatestSearch object
			ArrayList<SearchResultEvents> resultSet = search.listEvents();
			
			// Loop through the results and test attributes of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).eventDetails.eventID, is(notNullValue()));
				assertThat(resultSet.get(i).eventDetails.eventName, is(notNullValue()));
				assertThat(resultSet.get(i).eventDetails.eventURL, is(notNullValue()));
				assertThat(resultSet.get(i).eventDetails.submittedDate, is(notNullValue()));
	
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
	public void testListEventsWithPagination() 
		{
		// Set the search parameters
		int resultsPerPage = 2;
		int pageNumber = 2;

		// Instantiate a LatestSearch object
		LatestSearch search = new LatestSearch(resultsPerPage, pageNumber);
		
		try
			{	
			// Call the listEvents() method on the LatestSearch object
			ArrayList<SearchResultEvents> resultSet = search.listEvents();
			
			// Loop through the results and test attributes of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).eventDetails.eventID, is(notNullValue()));
				assertThat(resultSet.get(i).eventDetails.eventName, is(notNullValue()));
				assertThat(resultSet.get(i).eventDetails.eventURL, is(notNullValue()));
				assertThat(resultSet.get(i).eventDetails.submittedDate, is(notNullValue()));
	
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

	}
