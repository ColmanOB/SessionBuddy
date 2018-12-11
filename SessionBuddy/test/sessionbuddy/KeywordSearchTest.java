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
import sessionbuddy.wrappers.resultsets.SearchResultTrips;
import sessionbuddy.wrappers.resultsets.SearchResultTunes;
import sessionbuddy.wrappers.resultsets.SearchResultDiscussions;
import sessionbuddy.wrappers.resultsets.SearchResultRecordings;

/**
 * Unit tests for the KeywordSearch class
 * 
 * Each test follows the same process:
 * 
 * 1. Set the search parameters
 * 2. Instantiate a KeywordSearch object
 * 3. Perform the search
 * 4. Loop through the result set, testing (usually) each element of the result set
 * 
 * For each category of data, one test is done with pagination in the results, 
 * and one without pagination
 * 
 * @author Colman
 * @since 2018-12-11
 */
public class KeywordSearchTest
{

    @Test
    public void testSearchDiscussionsWithoutPagination()
    {
        String searchTerms = "Wig Glue";
        int resultsPerPage = 10;

        try
        {
            KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage);

            ArrayList<SearchResultDiscussions> resultSet = search.searchDiscussions();

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

        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSearchDiscussionsWithPagination()
    {
        String searchTerms = "Wig Glue";
        int resultsPerPage = 10;
        int pageNumber = 1;

        try
        {
            KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage, pageNumber);

            ArrayList<SearchResultDiscussions> resultSet = search.searchDiscussions();

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

        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSearchEventsWithoutPagination()
    {
        String searchTerms = "Fiddle";
        int resultsPerPage = 5;

        try
        {
            KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage);

            ArrayList<SearchResultEvents> resultSet = search.searchEvents();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).eventDetails.eventID, is(notNullValue()));
                assertThat(resultSet.get(i).eventDetails.eventName, is(notNullValue()));
                assertThat(resultSet.get(i).eventDetails.eventURL, is(notNullValue()));
                assertThat(resultSet.get(i).eventDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.get(i).user.userURL, is(notNullValue()));

                assertThat(resultSet.get(i).schedule.startDate, is(notNullValue()));
                assertThat(resultSet.get(i).schedule.endDate, is(notNullValue()));

                assertThat(resultSet.get(i).coordinates.latitude, is(notNullValue()));
                assertThat(resultSet.get(i).coordinates.longitude, is(notNullValue()));

                assertThat(resultSet.get(i).venue.venueID, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));

                assertThat(resultSet.get(i).town.townID, is(notNullValue()));
                assertThat(resultSet.get(i).town.townName, is(notNullValue()));

                assertThat(resultSet.get(i).area.areaID, is(notNullValue()));
                assertThat(resultSet.get(i).area.areaName, is(notNullValue()));

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
    public void testSearchEventsWithPagination()
    {
        String searchTerms = "Fiddle";
        int resultsPerPage = 5;
        int pageNumber = 1;

        try
        {
            KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage, pageNumber);

            ArrayList<SearchResultEvents> resultSet = search.searchEvents();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).eventDetails.eventID, is(notNullValue()));
                assertThat(resultSet.get(i).eventDetails.eventName, is(notNullValue()));
                assertThat(resultSet.get(i).eventDetails.eventURL, is(notNullValue()));
                assertThat(resultSet.get(i).eventDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.get(i).user.userURL, is(notNullValue()));

                assertThat(resultSet.get(i).schedule.startDate, is(notNullValue()));
                assertThat(resultSet.get(i).schedule.endDate, is(notNullValue()));

                assertThat(resultSet.get(i).coordinates.latitude, is(notNullValue()));
                assertThat(resultSet.get(i).coordinates.longitude, is(notNullValue()));

                assertThat(resultSet.get(i).venue.venueID, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));

                assertThat(resultSet.get(i).town.townID, is(notNullValue()));
                assertThat(resultSet.get(i).town.townName, is(notNullValue()));

                assertThat(resultSet.get(i).area.areaID, is(notNullValue()));
                assertThat(resultSet.get(i).area.areaName, is(notNullValue()));

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
        String searchTerms = "Humours";
        int resultsPerPage = 10;

        try
        {
            KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage);

            ArrayList<SearchResultTunes> resultSet = search.searchTunes();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).tuneDetails.basicTuneDetails.tuneID, is(notNullValue()));
                assertThat(resultSet.get(i).tuneDetails.basicTuneDetails.tuneName, is(notNullValue()));
                assertThat(resultSet.get(i).tuneDetails.tuneType, is(notNullValue()));
                assertThat(resultSet.get(i).tuneDetails.basicTuneDetails.tuneURL, is(notNullValue()));
                assertThat(resultSet.get(i).tuneDetails.submittedDate,is(notNullValue()));

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
        String searchTerms = "Humours";
        int resultsPerPage = 10;
        int pageNumber = 1;

        try
        {
            KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage, pageNumber);

            ArrayList<SearchResultTunes> resultSet = search.searchTunes();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).tuneDetails.basicTuneDetails.tuneID, is(notNullValue()));
                assertThat(resultSet.get(i).tuneDetails.basicTuneDetails.tuneName, is(notNullValue()));
                assertThat(resultSet.get(i).tuneDetails.tuneType, is(notNullValue()));
                assertThat(resultSet.get(i).tuneDetails.basicTuneDetails.tuneURL, is(notNullValue()));
                assertThat(resultSet.get(i).tuneDetails.submittedDate, is(notNullValue()));

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
        String searchTerms = "Humours";
        int resultsPerPage = 10;

        try
        {
            KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage);

            ArrayList<SearchResultRecordings> resultSet = search.searchRecordings();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).recordingDetails.recordingID, is(notNullValue()));
                assertThat(resultSet.get(i).recordingDetails.recordingName, is(notNullValue()));
                assertThat(resultSet.get(i).recordingDetails.recordingDate, is(notNullValue()));
                assertThat(resultSet.get(i).recordingDetails.recordingURL, is(notNullValue()));

                assertThat(resultSet.get(i).artist.artistID, is(notNullValue()));
                assertThat(resultSet.get(i).artist.artistName, is(notNullValue()));
                assertThat(resultSet.get(i).artist.artistPageURL, is(notNullValue()));

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
        String searchTerms = "Humours";
        int resultsPerPage = 3;
        int pageNumber = 2;

        try
        {
            KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage, pageNumber);

            ArrayList<SearchResultRecordings> resultSet = search.searchRecordings();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).recordingDetails.recordingID, is(notNullValue()));
                assertThat(resultSet.get(i).recordingDetails.recordingName, is(notNullValue()));
                assertThat(resultSet.get(i).recordingDetails.recordingDate, is(notNullValue()));
                assertThat(resultSet.get(i).recordingDetails.recordingURL, is(notNullValue()));

                assertThat(resultSet.get(i).artist.artistID, is(notNullValue()));
                assertThat(resultSet.get(i).artist.artistName, is(notNullValue()));
                assertThat(resultSet.get(i).artist.artistPageURL, is(notNullValue()));

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
        String searchTerms = "London";
        int resultsPerPage = 5;

        try
        {
            KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage);

            ArrayList<SearchResultSessions> resultSet = search.searchSessions();

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

                assertThat(resultSet.get(i).venue.venueID, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));

                assertThat(resultSet.get(i).town.townID, is(notNullValue()));
                assertThat(resultSet.get(i).town.townName, is(notNullValue()));

                assertThat(resultSet.get(i).area.areaID, is(notNullValue()));
                assertThat(resultSet.get(i).area.areaName, is(notNullValue()));

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
        String searchTerms = "London";
        int resultsPerPage = 5;
        int pageNumber = 1;

        try
        {
            KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage, pageNumber);

            ArrayList<SearchResultSessions> resultSet = search.searchSessions();

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

                assertThat(resultSet.get(i).venue.venueID, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));

                assertThat(resultSet.get(i).town.townID, is(notNullValue()));
                assertThat(resultSet.get(i).town.townName, is(notNullValue()));

                assertThat(resultSet.get(i).area.areaID, is(notNullValue()));
                assertThat(resultSet.get(i).area.areaName, is(notNullValue()));

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
        // Set the search parameters, using values that we know will produce no results
        String searchTerms = "London";
        int resultsPerPage = 50;
        int pageNumber = 20;

        try
        {
            KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage, pageNumber);

            ArrayList<SearchResultSessions> resultSet = search.searchSessions();

            assertThat(resultSet.size(), is(0));
        }

        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testSearchTripsWithoutPagination()
    {
        String searchTerms = "London";
        int resultsPerPage = 5;

        try
        {
            KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage);

            ArrayList<SearchResultTrips> resultSet = search.searchTrips();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).tripDetails.tripID, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.tripName, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.tripURL, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.submittedDate, is(notNullValue()));
                assertThat(resultSet.get(i).tripSchedule.startDate, is(notNullValue()));
                assertThat(resultSet.get(i).tripSchedule.endDate, is(notNullValue()));

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
    public void testSearchTripsWithPagination()
    {
        String searchTerms = "London";
        int resultsPerPage = 5;
        int pageNumber = 2;

        try
        {
            KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage, pageNumber);

            ArrayList<SearchResultTrips> resultSet = search.searchTrips();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).tripDetails.tripID, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.tripName, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.tripURL, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.submittedDate, is(notNullValue()));
                assertThat(resultSet.get(i).tripSchedule.startDate, is(notNullValue()));
                assertThat(resultSet.get(i).tripSchedule.endDate, is(notNullValue()));

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

}
