package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Test;

import sessionbuddy.wrappers.resultsets.SearchResultEvents;
import sessionbuddy.wrappers.resultsets.SearchResultSessions;
import sessionbuddy.wrappers.resultsets.SearchResultTunes;
import sessionbuddy.wrappers.resultsets.SearchResultDiscussions;
import sessionbuddy.wrappers.resultsets.SearchResultRecordings;

public class KeywordSearchTest 
	{
	
	@Test
	public void testSearchDiscussionsWithoutPagination() 
		{
		// Set the search parameters
		String searchTerms = "Wig Glue";
		int resultsPerPage = 10;
	
		try
			{
			// Instantiate a KeywordSearch object
			KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage);
			
			// Call the KeywordSearch.searchDiscussions() method and store the result
			ArrayList<SearchResultDiscussions> resultSet = search.searchDiscussions();
			
			// Loop through the results and ensure each field of each object is set to a non-null value
			for (int i = 0; i < resultSet.size(); i++)
				{
				// Discussion metadata
				assertThat(resultSet.get(i).details.discussionID, is(notNullValue()));
				assertThat(resultSet.get(i).details.discussionName, is(notNullValue()));
				assertThat(resultSet.get(i).details.submittedDate, is(notNullValue()));
				assertThat(resultSet.get(i).details.discussionURL, is(notNullValue()));
				assertThat(resultSet.get(i).details.numberOfComments, is(notNullValue()));
				
				// User details
				assertThat(resultSet.get(i).user.userID, is(notNullValue()));
				assertThat(resultSet.get(i).user.userName, is(notNullValue()));
				assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException e)
			{
			fail(e.getMessage());
			}	
		}
	
	@Test
	public void testSearchDiscussionsWithPagination() 
		{
		// Set the search parameters
		String searchTerms = "Wig Glue";
		int resultsPerPage = 10;
		int pageNumber = 1;
	
		try
			{
			// Instantiate a KeywordSearch object
			KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage, pageNumber);
			
			// Call the KeywordSearch.searchDiscussions() method and store the result
			ArrayList<SearchResultDiscussions> resultSet = search.searchDiscussions();
			
			// Loop through the results and ensure each field of each object is set to a non-null value
			for (int i = 0; i < resultSet.size(); i++)
				{
				// Discussion metadata
				assertThat(resultSet.get(i).details.discussionID, is(notNullValue()));
				assertThat(resultSet.get(i).details.discussionName, is(notNullValue()));
				assertThat(resultSet.get(i).details.submittedDate, is(notNullValue()));
				assertThat(resultSet.get(i).details.discussionURL, is(notNullValue()));
				assertThat(resultSet.get(i).details.numberOfComments, is(notNullValue()));
				
				// User details
				assertThat(resultSet.get(i).user.userID, is(notNullValue()));
				assertThat(resultSet.get(i).user.userName, is(notNullValue()));
				assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException e)
			{
			fail(e.getMessage());
			}	
		}
	
	@Test
	public void testSearchEventssWithoutPagination() 
		{
		// Set the search parameters
		String searchTerms = "Fiddle";
		int resultsPerPage = 5;
	
		try
			{
			// Instantiate a KeywordSearch object
			KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage);
			
			// Call the KeywordSearch.searchDiscussions() method and store the result
			ArrayList<SearchResultEvents> resultSet = search.searchEvents();
			
			// Loop through the results and ensure each field of each object is set to a non-null value
			for (int i = 0; i < resultSet.size(); i++)
				{
				// Discussion metadata
				assertThat(resultSet.get(i).details.eventID, is(notNullValue()));
				assertThat(resultSet.get(i).details.eventName, is(notNullValue()));
				assertThat(resultSet.get(i).details.eventURL, is(notNullValue()));
				assertThat(resultSet.get(i).details.submittedDate, is(notNullValue()));

				// User details
				assertThat(resultSet.get(i).user.userID, is(notNullValue()));
				assertThat(resultSet.get(i).user.userName, is(notNullValue()));
				assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
				
				// Schedule details
				assertThat(resultSet.get(i).schedule.startDate, is(notNullValue()));
				assertThat(resultSet.get(i).schedule.endDate, is(notNullValue()));
				
				// Coordinates details
				assertThat(resultSet.get(i).coordinates.latitude, is(notNullValue()));
				assertThat(resultSet.get(i).coordinates.longitude, is(notNullValue()));
				
				// Venue details
				assertThat(resultSet.get(i).venue.venueID, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));
				
				// Town/city details
				assertThat(resultSet.get(i).town.townID, is(notNullValue()));
				assertThat(resultSet.get(i).town.townName, is(notNullValue()));
				
				// Area details
				assertThat(resultSet.get(i).area.areaID, is(notNullValue()));
				assertThat(resultSet.get(i).area.areaName, is(notNullValue()));
				
				// Country details
				assertThat(resultSet.get(i).country.countryID, is(notNullValue()));
				assertThat(resultSet.get(i).country.countryName, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException e)
			{
			fail(e.getMessage());
			}	
		}
	
	@Test
	public void testSearchEventssWithPagination() 
		{
		// Set the search parameters
		String searchTerms = "Fiddle";
		int resultsPerPage = 5;
		int pageNumber = 1;
	
		try
			{
			// Instantiate a KeywordSearch object
			KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage, pageNumber);
			
			// Call the KeywordSearch.searchDiscussions() method and store the result
			ArrayList<SearchResultEvents> resultSet = search.searchEvents();
			
			// Loop through the results and ensure each field of each object is set to a non-null value
			for (int i = 0; i < resultSet.size(); i++)
				{
				// Discussion metadata
				assertThat(resultSet.get(i).details.eventID, is(notNullValue()));
				assertThat(resultSet.get(i).details.eventName, is(notNullValue()));
				assertThat(resultSet.get(i).details.eventURL, is(notNullValue()));
				assertThat(resultSet.get(i).details.submittedDate, is(notNullValue()));

				// User details
				assertThat(resultSet.get(i).user.userID, is(notNullValue()));
				assertThat(resultSet.get(i).user.userName, is(notNullValue()));
				assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
				
				// Schedule details
				assertThat(resultSet.get(i).schedule.startDate, is(notNullValue()));
				assertThat(resultSet.get(i).schedule.endDate, is(notNullValue()));
				
				// Coordinates details
				assertThat(resultSet.get(i).coordinates.latitude, is(notNullValue()));
				assertThat(resultSet.get(i).coordinates.longitude, is(notNullValue()));
				
				// Venue details
				assertThat(resultSet.get(i).venue.venueID, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));
				
				// Town/city details
				assertThat(resultSet.get(i).town.townID, is(notNullValue()));
				assertThat(resultSet.get(i).town.townName, is(notNullValue()));
				
				// Area details
				assertThat(resultSet.get(i).area.areaID, is(notNullValue()));
				assertThat(resultSet.get(i).area.areaName, is(notNullValue()));
				
				// Country details
				assertThat(resultSet.get(i).country.countryID, is(notNullValue()));
				assertThat(resultSet.get(i).country.countryName, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException e)
			{
			fail(e.getMessage());
			}	
		}
	
	@Test
	public void testSearchTunesWithoutPagination() 
		{
		// Set the search parameters
		String searchTerms = "Humours";
		int resultsPerPage = 10;
	
		try
			{
			// Instantiate a KeywordSearch object
			KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage);
			
			// Call the KeywordSearch.searchDiscussions() method and store the result
			ArrayList<SearchResultTunes> resultSet = search.searchTunes();
			
			// Loop through the results and ensure each field of each object is set to a non-null value
			for (int i = 0; i < resultSet.size(); i++)
				{
				// Tune metadata
				assertThat(resultSet.get(i).details.tuneID, is(notNullValue()));
				assertThat(resultSet.get(i).details.tuneTitle, is(notNullValue()));
				assertThat(resultSet.get(i).details.tuneType, is(notNullValue()));
				assertThat(resultSet.get(i).details.tuneURL, is(notNullValue()));
				assertThat(resultSet.get(i).details.submittedDate, is(notNullValue()));
				
				// User details
				assertThat(resultSet.get(i).submitter.userID, is(notNullValue()));
				assertThat(resultSet.get(i).submitter.userName, is(notNullValue()));
				assertThat(resultSet.get(i).submitter.userURL, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException e)
			{
			fail(e.getMessage());
			}	
		}
	
	@Test
	public void testSearchTunesWithPagination() 
		{
		// Set the search parameters
		String searchTerms = "Humours";
		int resultsPerPage = 10;
		int pageNumber = 1;
	
		try
			{
			// Instantiate a KeywordSearch object
			KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage, pageNumber);
			
			// Call the KeywordSearch.searchDiscussions() method and store the result
			ArrayList<SearchResultTunes> resultSet = search.searchTunes();
			
			// Loop through the results and ensure each field of each object is set to a non-null value
			for (int i = 0; i < resultSet.size(); i++)
				{
				// Tune metadata
				assertThat(resultSet.get(i).details.tuneID, is(notNullValue()));
				assertThat(resultSet.get(i).details.tuneTitle, is(notNullValue()));
				assertThat(resultSet.get(i).details.tuneType, is(notNullValue()));
				assertThat(resultSet.get(i).details.tuneURL, is(notNullValue()));
				assertThat(resultSet.get(i).details.submittedDate, is(notNullValue()));
				
				// User details
				assertThat(resultSet.get(i).submitter.userID, is(notNullValue()));
				assertThat(resultSet.get(i).submitter.userName, is(notNullValue()));
				assertThat(resultSet.get(i).submitter.userURL, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException e)
			{
			fail(e.getMessage());
			}	
		}
	
	@Test
	public void testSearchRecordingsWithoutPagination() 
		{
		// Set the search parameters
		String searchTerms = "Humours";
		int resultsPerPage = 10;
	
		try
			{
			// Instantiate a KeywordSearch object
			KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage);
			
			// Call the KeywordSearch.searchDiscussions() method and store the result
			ArrayList<SearchResultRecordings> resultSet = search.searchRecordings();
			
			// Loop through the results and ensure each field of each object is set to a non-null value
			for (int i = 0; i < resultSet.size(); i++)
				{
				// Tune metadata
				assertThat(resultSet.get(i).recordingInfo.recordingID, is(notNullValue()));
				assertThat(resultSet.get(i).recordingInfo.recordingName, is(notNullValue()));
				assertThat(resultSet.get(i).recordingInfo.recordingDate, is(notNullValue()));
				assertThat(resultSet.get(i).recordingInfo.recordingURL, is(notNullValue()));
				
				// Artist details
				assertThat(resultSet.get(i).artist.artistID, is(notNullValue()));
				assertThat(resultSet.get(i).artist.artistName, is(notNullValue()));
				assertThat(resultSet.get(i).artist.artistPageURL, is(notNullValue()));
				
				// User details
				assertThat(resultSet.get(i).submitter.userID, is(notNullValue()));
				assertThat(resultSet.get(i).submitter.userName, is(notNullValue()));
				assertThat(resultSet.get(i).submitter.userURL, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException e)
			{
			fail(e.getMessage());
			}	
		}
	
	@Test
	public void testSearchRecordingsWithPagination() 
		{
		// Set the search parameters
		String searchTerms = "Humours";
		int resultsPerPage = 3;
		int pageNumber = 2;
	
		try
			{
			// Instantiate a KeywordSearch object
			KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage, pageNumber);
			
			// Call the KeywordSearch.searchDiscussions() method and store the result
			ArrayList<SearchResultRecordings> resultSet = search.searchRecordings();
			
			// Loop through the results and ensure each field of each object is set to a non-null value
			for (int i = 0; i < resultSet.size(); i++)
				{
				// Tune metadata
				assertThat(resultSet.get(i).recordingInfo.recordingID, is(notNullValue()));
				assertThat(resultSet.get(i).recordingInfo.recordingName, is(notNullValue()));
				assertThat(resultSet.get(i).recordingInfo.recordingDate, is(notNullValue()));
				assertThat(resultSet.get(i).recordingInfo.recordingURL, is(notNullValue()));
				
				// Artist details
				assertThat(resultSet.get(i).artist.artistID, is(notNullValue()));
				assertThat(resultSet.get(i).artist.artistName, is(notNullValue()));
				assertThat(resultSet.get(i).artist.artistPageURL, is(notNullValue()));
				
				// User details
				assertThat(resultSet.get(i).submitter.userID, is(notNullValue()));
				assertThat(resultSet.get(i).submitter.userName, is(notNullValue()));
				assertThat(resultSet.get(i).submitter.userURL, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException e)
			{
			fail(e.getMessage());
			}	
		}
	
	@Test
	public void testSearchSessionsWithoutPagination() 
		{
		// Set the search parameters
		String searchTerms = "London";
		int resultsPerPage = 5;
	
		try
			{
			// Instantiate a KeywordSearch object
			KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage);
			
			// Call the KeywordSearch.searchDiscussions() method and store the result
			ArrayList<SearchResultSessions> resultSet = search.searchSessions();
			
			// Loop through the results and ensure each field of each object is set to a non-null value
			for (int i = 0; i < resultSet.size(); i++)
				{
				// Session metadata
				assertThat(resultSet.get(i).details.sessionID, is(notNullValue()));
				assertThat(resultSet.get(i).details.sessionURL, is(notNullValue()));
				assertThat(resultSet.get(i).details.submittedDate, is(notNullValue()));

				// User details
				assertThat(resultSet.get(i).user.userID, is(notNullValue()));
				assertThat(resultSet.get(i).user.userName, is(notNullValue()));
				assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
				
				// Coordinates details
				assertThat(resultSet.get(i).coordinates.latitude, is(notNullValue()));
				assertThat(resultSet.get(i).coordinates.longitude, is(notNullValue()));
				
				// Venue details
				assertThat(resultSet.get(i).venue.venueID, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));
				
				// Town/city details
				assertThat(resultSet.get(i).town.townID, is(notNullValue()));
				assertThat(resultSet.get(i).town.townName, is(notNullValue()));
				
				// Area details
				assertThat(resultSet.get(i).area.areaID, is(notNullValue()));
				assertThat(resultSet.get(i).area.areaName, is(notNullValue()));
				
				// Country details
				assertThat(resultSet.get(i).country.countryID, is(notNullValue()));
				assertThat(resultSet.get(i).country.countryName, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException e)
			{
			fail(e.getMessage());
			}	
		}
	
	@Test
	public void testSearchSessionsWithPagination() 
		{
		// Set the search parameters
		String searchTerms = "London";
		int resultsPerPage = 5;
		int pageNumber = 1;
	
		try
			{
			// Instantiate a KeywordSearch object
			KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage, pageNumber);
			
			// Call the KeywordSearch.searchDiscussions() method and store the result
			ArrayList<SearchResultSessions> resultSet = search.searchSessions();
			
			// Loop through the results and ensure each field of each object is set to a non-null value
			for (int i = 0; i < resultSet.size(); i++)
				{
				// Session metadata
				assertThat(resultSet.get(i).details.sessionID, is(notNullValue()));
				assertThat(resultSet.get(i).details.sessionURL, is(notNullValue()));
				assertThat(resultSet.get(i).details.submittedDate, is(notNullValue()));

				// User details
				assertThat(resultSet.get(i).user.userID, is(notNullValue()));
				assertThat(resultSet.get(i).user.userName, is(notNullValue()));
				assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
				
				// Coordinates details
				assertThat(resultSet.get(i).coordinates.latitude, is(notNullValue()));
				assertThat(resultSet.get(i).coordinates.longitude, is(notNullValue()));
				
				// Venue details
				assertThat(resultSet.get(i).venue.venueID, is(notNullValue()));
				assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));
				
				// Town/city details
				assertThat(resultSet.get(i).town.townID, is(notNullValue()));
				assertThat(resultSet.get(i).town.townName, is(notNullValue()));
				
				// Area details
				assertThat(resultSet.get(i).area.areaID, is(notNullValue()));
				assertThat(resultSet.get(i).area.areaName, is(notNullValue()));
				
				// Country details
				assertThat(resultSet.get(i).country.countryID, is(notNullValue()));
				assertThat(resultSet.get(i).country.countryName, is(notNullValue()));
				}
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException e)
			{
			fail(e.getMessage());
			}	
		}
	
	@Test
	public void testSearchSessionsWithPaginationWithNoResults() 
		{
		// Set the search parameters, using values that we know will produce no search results
		String searchTerms = "London";
		int resultsPerPage = 50;
		int pageNumber = 20;
	
		try
			{
			// Instantiate a KeywordSearch object
			KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage, pageNumber);
			
			// Call the KeywordSearch.searchDiscussions() method and store the result
			ArrayList<SearchResultSessions> resultSet = search.searchSessions();
			
			// Check that the ArrayList of SearchResultSessions objects is zero
			assertThat(resultSet.size(), is(0));
			}
		
		catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException e)
			{
			fail(e.getMessage());
			}	
		}
	}
