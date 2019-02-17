package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import sessionbuddy.wrappers.resultsets.RecentResultSets;

public class SetsTest
{
    @Rule
    public ExpectedException zeroItemsPerPageException = ExpectedException.none();

    @Rule
    public ExpectedException negativeItemsPerPageException = ExpectedException.none();

    @Rule
    public ExpectedException tooManyItemsPerPageException = ExpectedException.none();

    @Rule
    public ExpectedException NegativePageNumberException = ExpectedException.none();

    @Test
    public void testListSetsWithoutPaginationWithResultsPerPage() throws IllegalArgumentException, IOException, URISyntaxException
    {
        int resultsPerPage = 50;

        RecentResultSets resultSet = Sets.listSets(resultsPerPage);

        for (int i = 0; i < resultSet.searchResults.size(); i++)
        {
            assertThat(resultSet.searchResults.get(i).setDetails.setID, is(notNullValue()));
            assertThat(resultSet.searchResults.get(i).setDetails.setName, is(notNullValue()));
            assertThat(resultSet.searchResults.get(i).setDetails.setDate, is(notNullValue()));
            assertThat(resultSet.searchResults.get(i).setDetails.setURL, is(notNullValue()));

            assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
            assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
            assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));
        }
    }

    @Test
    public void testListSetsWithPaginationWithResultsPerPage() throws IllegalArgumentException, IOException, URISyntaxException
    {
        int resultsPerPage = 4;
        int pageNumber = 2;
        
        RecentResultSets resultSet = Sets.listSets(resultsPerPage, pageNumber);

        for (int i = 0; i < resultSet.searchResults.size(); i++)
        {
            assertThat(resultSet.searchResults.get(i).setDetails.setID, is(notNullValue()));
            assertThat(resultSet.searchResults.get(i).setDetails.setName, is(notNullValue()));
            assertThat(resultSet.searchResults.get(i).setDetails.setDate, is(notNullValue()));
            assertThat(resultSet.searchResults.get(i).setDetails.setURL, is(notNullValue()));

            assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
            assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
            assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));
        }
    }

    @Test
    public void testAttemptWithZeroResultsPerPage() throws IllegalArgumentException, IOException, URISyntaxException
    {
        zeroItemsPerPageException.expect(IllegalArgumentException.class);
        zeroItemsPerPageException.expectMessage("Number of results per page must be greater than zero");

        int resultsPerPage = 0;

        @SuppressWarnings("unused")
        RecentResultSets resultSet = Sets.listSets(resultsPerPage);     
    }

    @Test
    public void testAttemptWithLessThanZeroResultsPerPage() throws IllegalArgumentException, IOException, URISyntaxException
    {
        negativeItemsPerPageException.expect(IllegalArgumentException.class);
        negativeItemsPerPageException.expectMessage("Number of results per page must be greater than zero");

        int resultsPerPage = -1;

        @SuppressWarnings("unused")
        RecentResultSets resultSet = Sets.listSets(resultsPerPage);
    }

    @Test
    public void testAttemptWithMoreThanFiftyResultsPerPage() throws IllegalArgumentException, IOException, URISyntaxException
    {
        tooManyItemsPerPageException.expect(IllegalArgumentException.class);
        tooManyItemsPerPageException.expectMessage("Number of results per page cannot exceed 50");

        int resultsPerPage = 51;

        @SuppressWarnings("unused")
        RecentResultSets resultSet = Sets.listSets(resultsPerPage);
    }

    @Test
    public void testAttemptWitNegativePageNumber() throws IllegalArgumentException, IOException, URISyntaxException
    {
        NegativePageNumberException.expect(IllegalArgumentException.class);
        NegativePageNumberException.expectMessage("Page number must be an integer value greater than zero");

        int resultsPerPage = 5;
        int pageNumber = -1;

        @SuppressWarnings("unused")
        RecentResultSets resultSet = Sets.listSets(resultsPerPage, pageNumber);
    }

    @Test
    public void testAttemptWitNegativePageNumberAndNegativeItemsPerPage() throws IllegalArgumentException, IOException, URISyntaxException
    {
        NegativePageNumberException.expect(IllegalArgumentException.class);

        int resultsPerPage = -5;
        int pageNumber = -1;
        
        @SuppressWarnings("unused")
        RecentResultSets resultSet = Sets.listSets(resultsPerPage, pageNumber);
    }

    @Test
    public void testAttemptWithPositivePageNumberAndNegativeItemsPerPage() throws IllegalArgumentException, IOException, URISyntaxException
    {
        negativeItemsPerPageException.expect(IllegalArgumentException.class);

        int resultsPerPage = -5;
        int pageNumber = 1;

        @SuppressWarnings("unused")
        RecentResultSets resultSet = Sets.listSets(resultsPerPage, pageNumber);
    }
}
