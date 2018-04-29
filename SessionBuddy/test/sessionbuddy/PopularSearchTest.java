package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Test;

import sessionbuddy.wrappers.resultsets.SearchResultTunesPopular;

public class PopularSearchTest 
	{

	@Test
	public void testGetTunes() 
		{
		try
			{
			// Set the search parameters
			int resultsPerPage = 50;
			int pageNumber = 2;
			
			// Instantiate a PopularSearch object
			PopularSearch search = new PopularSearch(resultsPerPage, pageNumber);
			
			// Perform the search by calling the getTunes method on the PopularSearch object
			ArrayList<SearchResultTunesPopular> resultSet = search.getTunes();
			
			// Loop through the results and test each attribute of each individual result in the set
			for (int i = 0; i < resultSet.size(); i++)
				{
				assertThat(resultSet.get(i).tuneDetails.tuneDetails.tuneDetails.tuneID, is(notNullValue()));
				assertThat(resultSet.get(i).tuneDetails.tuneDetails.tuneDetails.tuneName, is(notNullValue()));
				assertThat(resultSet.get(i).tuneDetails.tuneDetails.tuneDetails.tuneURL, is(notNullValue()));
				assertThat(resultSet.get(i).tuneDetails.tunebooks, is(notNullValue()));
				assertThat(resultSet.get(i).tuneDetails.tuneDetails.submittedDate, is(notNullValue()));
				assertThat(resultSet.get(i).tuneDetails.tuneDetails.tuneType, is(notNullValue()));

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
