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
import sessionbuddy.wrappers.resultsets.SearchResultSets;

public class SetSearchTest
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

        SetSearch search = new SetSearch(resultsPerPage);

        ArrayList<SearchResultSets> resultSet = search.listSets();

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

    @Test
    public void testListSetsWithPaginationWithResultsPerPage() throws IllegalArgumentException, IOException, URISyntaxException
    {
        int resultsPerPage = 4;
        int pageNumber = 2;

        SetSearch search = new SetSearch(resultsPerPage, pageNumber);

        ArrayList<SearchResultSets> resultSet = search.listSets();

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

    @Test
    public void testAttemptWithZeroResultsPerPage() throws IllegalArgumentException, IOException, URISyntaxException
    {
        zeroItemsPerPageException.expect(IllegalArgumentException.class);
        zeroItemsPerPageException.expectMessage("Number of results per page must be greater than zero");

        int resultsPerPage = 0;

        SetSearch search = new SetSearch(resultsPerPage);

        @SuppressWarnings("unused")
        ArrayList<SearchResultSets> resultSet = search.listSets();
    }

    @Test
    public void testAttemptWithLessThanZeroResultsPerPage() throws IllegalArgumentException, IOException, URISyntaxException
    {
        negativeItemsPerPageException.expect(IllegalArgumentException.class);
        negativeItemsPerPageException.expectMessage("Number of results per page must be greater than zero");

        int resultsPerPage = -1;

        SetSearch search = new SetSearch(resultsPerPage);

        @SuppressWarnings("unused")
        ArrayList<SearchResultSets> resultSet = search.listSets();
    }

    @Test
    public void testAttemptWithMoreThanFiftyResultsPerPage() throws IllegalArgumentException, IOException, URISyntaxException
    {
        tooManyItemsPerPageException.expect(IllegalArgumentException.class);
        tooManyItemsPerPageException.expectMessage("Number of results per page cannot exceed 50");

        int resultsPerPage = 51;

        SetSearch search = new SetSearch(resultsPerPage);

        @SuppressWarnings("unused")
        ArrayList<SearchResultSets> resultSet = search.listSets();
    }

    @Test
    public void testAttemptWitNegativePageNumber() throws IllegalArgumentException, IOException, URISyntaxException
    {
        NegativePageNumberException.expect(IllegalArgumentException.class);
        NegativePageNumberException.expectMessage("Page number must be an integer value greater than zero");

        int resultsPerPage = 5;
        int pageNumber = -1;

        SetSearch search = new SetSearch(resultsPerPage, pageNumber);

        @SuppressWarnings("unused")
        ArrayList<SearchResultSets> resultSet = search.listSets();
    }

    @Test
    public void testAttemptWitNegativePageNumberAndNegativeItemsPerPage() throws IllegalArgumentException, IOException, URISyntaxException
    {
        NegativePageNumberException.expect(IllegalArgumentException.class);

        int resultsPerPage = -5;
        int pageNumber = -1;

        SetSearch search = new SetSearch(resultsPerPage, pageNumber);

        @SuppressWarnings("unused")
        ArrayList<SearchResultSets> resultSet = search.listSets();
    }

    @Test
    public void testAttemptWithPositivePageNumberAndNegativeItemsPerPage() throws IllegalArgumentException, IOException, URISyntaxException
    {
        negativeItemsPerPageException.expect(IllegalArgumentException.class);

        int resultsPerPage = -5;
        int pageNumber = 1;

        SetSearch search = new SetSearch(resultsPerPage, pageNumber);

        @SuppressWarnings("unused")
        ArrayList<SearchResultSets> resultSet = search.listSets();
    }
}
