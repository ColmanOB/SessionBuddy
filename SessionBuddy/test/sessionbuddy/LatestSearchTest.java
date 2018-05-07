package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Test;

import sessionbuddy.wrappers.resultsets.SearchResultDiscussions;

public class LatestSearchTest {

	@Test
	public void testListTunesWithoutPagination() 
		{
		fail("Not yet implemented");
		}
	
	@Test
	public void testListTunesWithPagination() 
		{
		fail("Not yet implemented");
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
					
			// Loop through the results and print each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).details.discussionID, is(notNullValue()));
				assertThat(resultSet.get(i).details.discussionName, is(notNullValue()));
				assertThat(resultSet.get(i).details.submittedDate, is(notNullValue()));
				assertThat(resultSet.get(i).details.discussionURL, is(notNullValue()));
				assertThat(resultSet.get(i).details.numberOfComments, is(notNullValue()));
								
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
			
			// Loop through the results and print each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).details.discussionID, is(notNullValue()));
				assertThat(resultSet.get(i).details.discussionName, is(notNullValue()));
				assertThat(resultSet.get(i).details.submittedDate, is(notNullValue()));
				assertThat(resultSet.get(i).details.discussionURL, is(notNullValue()));
				assertThat(resultSet.get(i).details.numberOfComments, is(notNullValue()));
						
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
		fail("Not yet implemented");
		}
	
	@Test
	public void testListRecordingsWithPagination() 
		{
		fail("Not yet implemented");
		}


	@Test
	public void testListSessionsWithoutPagination() 
		{
		fail("Not yet implemented");
		}
	
	@Test
	public void testListSessionsWithPagination() 
		{
		fail("Not yet implemented");
		}

	@Test
	public void testListEventsWithoutPagination() 
		{
		fail("Not yet implemented");
		}

	@Test
	public void testListEventsWithPagination() 
		{
		fail("Not yet implemented");
		}

}
