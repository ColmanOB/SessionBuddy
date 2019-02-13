package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.Test;
import sessionbuddy.wrappers.resultsets.SearchResultDiscussions;
import sessionbuddy.wrappers.resultsets.SearchResultEvents;
import sessionbuddy.wrappers.resultsets.SearchResultRecordings;
import sessionbuddy.wrappers.resultsets.SearchResultSessions;
import sessionbuddy.wrappers.resultsets.SearchResultTunes;
import sessionbuddy.wrappers.resultsets.SearchResultTrips;

/**
 * Unit tests for the KeywordSearch class
 * 
 * Each test follows the same process:
 * 
 * 1. Define the search parameters
 * 2. Perform the search by calling one of the static methods on KeywordSearch
 * 3. Iterate through the result set, testing that (usually) each element of the result set is populated
 * 
 * For each category of data, one test is done with pagination in the results, 
 * and one without pagination
 * 
 * @author Colman
 * @since 2019-02-13
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
            SearchResultDiscussions resultSet = KeywordSearch.searchDiscussions(searchTerms, resultsPerPage);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.submittedDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.numberOfComments, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));
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
            SearchResultDiscussions resultSet = KeywordSearch.searchDiscussions(searchTerms, resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.submittedDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.numberOfComments, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));
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
            SearchResultEvents resultSet = KeywordSearch.searchEvents(searchTerms, resultsPerPage);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).eventDetails.eventID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.eventName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.eventURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).schedule.startDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).schedule.endDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).coordinates.latitude, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).coordinates.longitude, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).venue.venueID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).venue.venueName, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).town.townID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).town.townName, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).area.areaID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).area.areaName, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).country.countryID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).country.countryName, is(notNullValue()));
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
            SearchResultEvents resultSet = KeywordSearch.searchEvents(searchTerms, resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).eventDetails.eventID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.eventName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.eventURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).schedule.startDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).schedule.endDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).coordinates.latitude, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).coordinates.longitude, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).venue.venueID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).venue.venueName, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).town.townID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).town.townName, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).area.areaID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).area.areaName, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).country.countryID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).country.countryName, is(notNullValue()));
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
            SearchResultTunes resultSet = KeywordSearch.searchTunes(searchTerms, resultsPerPage);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).tuneDetails.basicTuneDetails.tuneID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tuneDetails.basicTuneDetails.tuneName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tuneDetails.tuneType, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tuneDetails.basicTuneDetails.tuneURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tuneDetails.submittedDate,is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));
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
            SearchResultTunes resultSet = KeywordSearch.searchTunes(searchTerms, resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).tuneDetails.basicTuneDetails.tuneID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tuneDetails.basicTuneDetails.tuneName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tuneDetails.tuneType, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tuneDetails.basicTuneDetails.tuneURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tuneDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));
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
            SearchResultRecordings resultSet = KeywordSearch.searchRecordings(searchTerms, resultsPerPage);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).artist.artistID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).artist.artistName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).artist.artistPageURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));
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
            SearchResultRecordings resultSet = KeywordSearch.searchRecordings(searchTerms, resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).artist.artistID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).artist.artistName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).artist.artistPageURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));
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
            SearchResultSessions resultSet = KeywordSearch.searchSessions(searchTerms, resultsPerPage);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).sessionDetails.sessionID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).sessionDetails.sessionURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).sessionDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).coordinates.latitude, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).coordinates.longitude, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).venue.venueID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).venue.venueName, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).town.townID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).town.townName, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).area.areaID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).area.areaName, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).country.countryID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).country.countryName, is(notNullValue()));
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
            SearchResultSessions resultSet = KeywordSearch.searchSessions(searchTerms, resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).sessionDetails.sessionID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).sessionDetails.sessionURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).sessionDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).coordinates.latitude, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).coordinates.longitude, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).venue.venueID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).venue.venueName, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).town.townID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).town.townName, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).area.areaID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).area.areaName, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).country.countryID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).country.countryName, is(notNullValue()));
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
            SearchResultSessions resultSet = KeywordSearch.searchSessions(searchTerms, resultsPerPage, pageNumber);

            assertThat(resultSet.searchResults.size(), is(0));
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
            SearchResultTrips resultSet = KeywordSearch.searchTrips(searchTerms, resultsPerPage);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).tripDetails.tripID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripDetails.tripName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripDetails.tripURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripDetails.submittedDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripSchedule.startDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripSchedule.endDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));
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
            sessionbuddy.wrappers.resultsets.SearchResultTrips resultSet = KeywordSearch.searchTrips(searchTerms, resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).tripDetails.tripID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripDetails.tripName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripDetails.tripURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripDetails.submittedDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripSchedule.startDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripSchedule.endDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

}
