package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Test;

import sessionbuddy.wrappers.resultsets.SearchResultsDiscussions;

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
			ArrayList<SearchResultsDiscussions> resultSet = search.searchDiscussions();
			
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
			ArrayList<SearchResultsDiscussions> resultSet = search.searchDiscussions();
			
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
		// TODO: change this to search for events rather than discussions
		// Set the search parameters
		String searchTerms = "Wig Glue";
		int resultsPerPage = 10;
	
		try
			{
			// Instantiate a KeywordSearch object
			KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage);
			
			// Call the KeywordSearch.searchDiscussions() method and store the result
			ArrayList<SearchResultsDiscussions> resultSet = search.searchDiscussions();
			
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
	}
