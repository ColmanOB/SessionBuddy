package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import sessionbuddy.wrappers.resultsets.SearchResultTunesPopular;

public class PopularSearchTest 
	{
	@Rule 
	public ExpectedException zeroItemsPerPageException = ExpectedException.none();

	@Rule 
	public ExpectedException negativeItemsPerPageException = ExpectedException.none();
	
	@Rule 
	public ExpectedException tooManyItemsPerPageException = ExpectedException.none();
	
	@Test
	public void testGetTunesWithPageNumberAndResultsPerPage() throws IllegalArgumentException, IOException, URISyntaxException 
		{
		int resultsPerPage = 50;
		int pageNumber = 2;

		PopularSearch search = new PopularSearch(resultsPerPage, pageNumber);

		ArrayList<SearchResultTunesPopular> resultSet = search.listTunes();
			
		// Loop through the results and test each attribute of each individual result in the set
		for (int i = 0; i < resultSet.size(); i++)
			{
			assertThat(resultSet.get(i).tuneDetails.generalTuneDetails.basicTuneDetails.tuneID, is(notNullValue()));
			assertThat(resultSet.get(i).tuneDetails.generalTuneDetails.basicTuneDetails.tuneName, is(notNullValue()));
			assertThat(resultSet.get(i).tuneDetails.generalTuneDetails.basicTuneDetails.tuneURL, is(notNullValue()));
			assertThat(resultSet.get(i).tuneDetails.tunebooks, is(notNullValue()));
			assertThat(resultSet.get(i).tuneDetails.generalTuneDetails.submittedDate, is(notNullValue()));
			assertThat(resultSet.get(i).tuneDetails.generalTuneDetails.tuneType, is(notNullValue()));
			assertThat(resultSet.get(i).submitter.userID, is(notNullValue()));
			assertThat(resultSet.get(i).submitter.userName, is(notNullValue()));
			assertThat(resultSet.get(i).submitter.userURL, is(notNullValue()));
			}
		}
	
	@Test
	public void testGetTunesWithNoPageNumberButResultsPerPage() throws IllegalArgumentException, IOException, URISyntaxException 
		{
		int resultsPerPage = 50;

		PopularSearch search = new PopularSearch(resultsPerPage);

		ArrayList<SearchResultTunesPopular> resultSet = search.listTunes();
			
		// Loop through the results and test each attribute of each individual result in the set
		for (int i = 0; i < resultSet.size(); i++)
			{
			assertThat(resultSet.get(i).tuneDetails.generalTuneDetails.basicTuneDetails.tuneID, is(notNullValue()));
			assertThat(resultSet.get(i).tuneDetails.generalTuneDetails.basicTuneDetails.tuneName, is(notNullValue()));
			assertThat(resultSet.get(i).tuneDetails.generalTuneDetails.basicTuneDetails.tuneURL, is(notNullValue()));
			assertThat(resultSet.get(i).tuneDetails.tunebooks, is(notNullValue()));
			assertThat(resultSet.get(i).tuneDetails.generalTuneDetails.submittedDate, is(notNullValue()));
			assertThat(resultSet.get(i).tuneDetails.generalTuneDetails.tuneType, is(notNullValue()));
			assertThat(resultSet.get(i).submitter.userID, is(notNullValue()));
			assertThat(resultSet.get(i).submitter.userName, is(notNullValue()));
			assertThat(resultSet.get(i).submitter.userURL, is(notNullValue()));
			}
		}
	
	@Test
	public void testAttemptWithZeroResultsPerPage() throws IllegalArgumentException, IOException, URISyntaxException 
		{
		zeroItemsPerPageException.expect(IllegalArgumentException.class);
		zeroItemsPerPageException.expectMessage("Number of results per page must be greater than zero");
		
		int resultsPerPage = 0;

		PopularSearch search = new PopularSearch(resultsPerPage);
							
		@SuppressWarnings("unused")
		ArrayList<SearchResultTunesPopular> resultSet = search.listTunes();
		}
	
	@Test
	public void testAttemptWithNegativeResultsPerPage() throws IllegalArgumentException, IOException, URISyntaxException 
		{
		negativeItemsPerPageException.expect(IllegalArgumentException.class);
		negativeItemsPerPageException.expectMessage("Number of results per page must be greater than zero");
		
		int resultsPerPage = -1;

		PopularSearch search = new PopularSearch(resultsPerPage);
							
		@SuppressWarnings("unused")
		ArrayList<SearchResultTunesPopular> resultSet = search.listTunes();
		}
	
	@Test
	public void testAttemptWithTooManyResultsPerPage() throws IllegalArgumentException, IOException, URISyntaxException 
		{
		tooManyItemsPerPageException.expect(IllegalArgumentException.class);
		tooManyItemsPerPageException.expectMessage("Number of results per page cannot exceed 50");
		
		int resultsPerPage = 51;

		PopularSearch search = new PopularSearch(resultsPerPage);
							
		@SuppressWarnings("unused")
		ArrayList<SearchResultTunesPopular> resultSet = search.listTunes();
		}
	}
