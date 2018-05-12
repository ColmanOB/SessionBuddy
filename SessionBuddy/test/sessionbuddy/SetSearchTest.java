package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Test;

import sessionbuddy.wrappers.resultsets.SearchResultSets;

public class SetSearchTest 
	{

	@Test
	public void testListSetsWithoutPaginationWithResultsPerPage() 
		{
		try
			{
			// Set the search parameters
			int resultsPerPage = 50;
			
			// Instantiate a SetSearch object
			SetSearch search = new SetSearch(resultsPerPage);
			
			// Perform the search by calling the listSets method on the SetSearch object
			ArrayList<SearchResultSets> resultSet = search.listSets();
			
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).setDetails.setID, is(notNullValue()));
				assertThat(resultSet.get(i).setDetails.setName, is(notNullValue()));
				assertThat(resultSet.get(i).setDetails.setDate, is(notNullValue()));
				assertThat(resultSet.get(i).setDetails.setURL, is(notNullValue()));
				
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
	public void testListSetsWithPaginationWithResultsPerPage() 
		{
		try
			{
			// Set the search parameters
			int resultsPerPage = 4;
			int pageNumber = 2;
			
			// Instantiate a SetSearch object
			SetSearch search = new SetSearch(resultsPerPage, pageNumber);
			
			// Perform the search by calling the listSets method on the SetSearch object
			ArrayList<SearchResultSets> resultSet = search.listSets();
			
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).setDetails.setID, is(notNullValue()));
				assertThat(resultSet.get(i).setDetails.setName, is(notNullValue()));
				assertThat(resultSet.get(i).setDetails.setDate, is(notNullValue()));
				assertThat(resultSet.get(i).setDetails.setURL, is(notNullValue()));
				
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
	(expected = IllegalArgumentException.class)
	
	public void testAttemptWithZeroResultsPerPage() 
		{
		try
			{
			int resultsPerPage = 0;
			// Instantiate a SetSearch object
			SetSearch search = new SetSearch(resultsPerPage);
					
			// Perform the search by calling the listSets method on the SetSearch object
			@SuppressWarnings("unused")
			ArrayList<SearchResultSets> resultSet = search.listSets();
			}
		
		catch (IllegalStateException | IOException | URISyntaxException e)
			{
			fail(e.getMessage());
			}
		}

	@Test
	(expected = IllegalArgumentException.class)
	
	public void testAttemptWithTooManyResultsPerPage() 
		{
		try
			{
			int resultsPerPage = 51;
			// Instantiate a SetSearch object
			SetSearch search = new SetSearch(resultsPerPage);
					
			// Perform the search by calling the listSets method on the SetSearch object
			@SuppressWarnings("unused")
			ArrayList<SearchResultSets> resultSet = search.listSets();
			}
		
		catch (IllegalStateException | IOException | URISyntaxException e)
			{
			fail(e.getMessage());
			}
		}
	}
