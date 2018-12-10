package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.junit.Test;
import sessionbuddy.wrappers.resultsets.SearchResultDiscussions;
import sessionbuddy.wrappers.resultsets.SearchResultEvents;
import sessionbuddy.wrappers.resultsets.SearchResultRecordings;
import sessionbuddy.wrappers.resultsets.SearchResultSessions;
import sessionbuddy.wrappers.resultsets.SearchResultTrips;
import sessionbuddy.wrappers.resultsets.SearchResultTunesLatest;

/**
 * Unit tests for the LatestSearch class
 * 
 * Each test follows the same process:
 * 
 * 1. Set the search parameters
 * 2. Instantiate a LatestSearch object
 * 3. Perform the search
 * 4. Loop through the result set, testing that each element of each result is populated
 * 
 * For each category of data, one test is done with pagination in the results, 
 * and one without pagination
 * 
 * @author Colman
 * @since 2018-12-10
 */
public class LatestSearchTest
{

    @Test
    public void testListTunesWithoutPagination()
    {
        int resultsPerPage = 50;
        
        LatestSearch search = new LatestSearch(resultsPerPage);

        try
        {
            ArrayList<SearchResultTunesLatest> resultSet = search.listTunes();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).settingDetails.settingID, is(notNullValue()));
                assertThat(resultSet.get(i).settingDetails.settingURL, is(notNullValue()));
                assertThat(resultSet.get(i).settingDetails.key, is(notNullValue()));

                assertThat(resultSet.get(i).tuneDetails.tuneID, is(notNullValue()));
                assertThat(resultSet.get(i).tuneDetails.tuneName, is(notNullValue()));
                assertThat(resultSet.get(i).tuneDetails.tuneURL, is(notNullValue()));

                assertThat(resultSet.get(i).settingDetails.date, is(notNullValue()));

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
    public void testListTunesWithPagination()
    {
        int resultsPerPage = 2;
        int pageNumber = 2;
        
        LatestSearch search = new LatestSearch(resultsPerPage, pageNumber);

        try
        {
            ArrayList<SearchResultTunesLatest> resultSet = search.listTunes();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).settingDetails.settingID, is(notNullValue()));
                assertThat(resultSet.get(i).settingDetails.settingURL, is(notNullValue()));
                assertThat(resultSet.get(i).settingDetails.key, is(notNullValue()));

                assertThat(resultSet.get(i).tuneDetails.tuneID, is(notNullValue()));
                assertThat(resultSet.get(i).tuneDetails.tuneName, is(notNullValue()));
                assertThat(resultSet.get(i).tuneDetails.tuneURL, is(notNullValue()));

                assertThat(resultSet.get(i).settingDetails.date, is(notNullValue()));

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
    public void testListDiscussionsWithoutPagination()
    {
        int resultsPerPage = 2;
        
        LatestSearch search = new LatestSearch(resultsPerPage);

        try
        {
            ArrayList<SearchResultDiscussions> resultSet = search.listDiscussions();

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

        catch (IllegalArgumentException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testListDiscussionsWithPagination()
    {
        int resultsPerPage = 2;
        int pageNumber = 2;
        
        LatestSearch search = new LatestSearch(resultsPerPage, pageNumber);

        try
        {
            ArrayList<SearchResultDiscussions> resultSet = search.listDiscussions();

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

        catch (IllegalArgumentException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testListRecordingsWithoutPagination()
    {
        int resultsPerPage = 50;
        
        LatestSearch search = new LatestSearch(resultsPerPage);

        try
        {
            ArrayList<SearchResultRecordings> resultSet = search.listRecordings();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).recordingDetails.recordingID, is(notNullValue()));
                assertThat(resultSet.get(i).recordingDetails.recordingName, is(notNullValue()));
                assertThat(resultSet.get(i).recordingDetails.recordingDate, is(notNullValue()));
                assertThat(resultSet.get(i).recordingDetails.recordingURL, is(notNullValue()));

                assertThat(resultSet.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.get(i).submitter.userURL, is(notNullValue()));

                assertThat(resultSet.get(i).artist.artistID, is(notNullValue()));
                assertThat(resultSet.get(i).artist.artistName, is(notNullValue()));
                assertThat(resultSet.get(i).artist.artistPageURL, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testListRecordingsWithPagination()
    {
        int resultsPerPage = 2;
        int pageNumber = 2;

        LatestSearch search = new LatestSearch(resultsPerPage, pageNumber);

        try
        {
            ArrayList<SearchResultRecordings> resultSet = search.listRecordings();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).recordingDetails.recordingID, is(notNullValue()));
                assertThat(resultSet.get(i).recordingDetails.recordingName, is(notNullValue()));
                assertThat(resultSet.get(i).recordingDetails.recordingDate, is(notNullValue()));
                assertThat(resultSet.get(i).recordingDetails.recordingURL, is(notNullValue()));

                assertThat(resultSet.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.get(i).submitter.userURL, is(notNullValue()));

                assertThat(resultSet.get(i).artist.artistID, is(notNullValue()));
                assertThat(resultSet.get(i).artist.artistName, is(notNullValue()));
                assertThat(resultSet.get(i).artist.artistPageURL, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testListSessionsWithoutPagination()
    {
        int resultsPerPage = 50;
        
        LatestSearch search = new LatestSearch(resultsPerPage);

        try
        {
            ArrayList<SearchResultSessions> resultSet = search.listSessions();

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

                assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venueEmail, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venuePhone, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venueWebsite, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testListSessionsWithPagination()
    {
        int resultsPerPage = 2;
        int pageNumber = 2;

        LatestSearch search = new LatestSearch(resultsPerPage, pageNumber);

        try
        {
            ArrayList<SearchResultSessions> resultSet = search.listSessions();

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

                assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venueEmail, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venuePhone, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venueWebsite, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testListEventsWithoutPagination()
    {
        int resultsPerPage = 50;

        LatestSearch search = new LatestSearch(resultsPerPage);

        try
        {
            ArrayList<SearchResultEvents> resultSet = search.listEvents();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).eventDetails.eventID, is(notNullValue()));
                assertThat(resultSet.get(i).eventDetails.eventName, is(notNullValue()));
                assertThat(resultSet.get(i).eventDetails.eventURL, is(notNullValue()));
                assertThat(resultSet.get(i).eventDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.get(i).schedule.startDate, is(notNullValue()));
                assertThat(resultSet.get(i).schedule.endDate, is(notNullValue()));

                assertThat(resultSet.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.get(i).user.userURL, is(notNullValue()));

                assertThat(resultSet.get(i).coordinates.latitude, is(notNullValue()));
                assertThat(resultSet.get(i).coordinates.longitude, is(notNullValue()));

                assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venueEmail, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venuePhone, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venueWebsite, is(notNullValue()));

                assertThat(resultSet.get(i).town.townName, is(notNullValue()));
                assertThat(resultSet.get(i).area.areaName, is(notNullValue()));
                assertThat(resultSet.get(i).country.countryName, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testListEventsWithPagination()
    {
        int resultsPerPage = 2;
        int pageNumber = 2;

        LatestSearch search = new LatestSearch(resultsPerPage, pageNumber);

        try
        {
            ArrayList<SearchResultEvents> resultSet = search.listEvents();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).eventDetails.eventID, is(notNullValue()));
                assertThat(resultSet.get(i).eventDetails.eventName, is(notNullValue()));
                assertThat(resultSet.get(i).eventDetails.eventURL, is(notNullValue()));
                assertThat(resultSet.get(i).eventDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.get(i).schedule.startDate, is(notNullValue()));
                assertThat(resultSet.get(i).schedule.endDate, is(notNullValue()));

                assertThat(resultSet.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.get(i).user.userURL, is(notNullValue()));

                assertThat(resultSet.get(i).coordinates.latitude, is(notNullValue()));
                assertThat(resultSet.get(i).coordinates.longitude, is(notNullValue()));

                assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venueEmail, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venuePhone, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venueWebsite, is(notNullValue()));

                assertThat(resultSet.get(i).town.townName, is(notNullValue()));
                assertThat(resultSet.get(i).area.areaName, is(notNullValue()));
                assertThat(resultSet.get(i).country.countryName, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testListTripsWithoutPagination()
    {
        int resultsPerPage = 2;

        LatestSearch search = new LatestSearch(resultsPerPage);

        try
        {
            ArrayList<SearchResultTrips> resultSet = search.listTrips();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).tripDetails.tripID, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.tripName, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.tripURL, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.submittedDate, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.tripStartDate, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.tripEndDate, is(notNullValue()));

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
    public void testListTripsWithPagination()
    {
        int resultsPerPage = 2;
        int pageNumber = 2;

        LatestSearch search = new LatestSearch(resultsPerPage, pageNumber);

        try
        {
            ArrayList<SearchResultTrips> resultSet = search.listTrips();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).tripDetails.tripID, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.tripName, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.tripURL, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.submittedDate, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.tripStartDate, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.tripEndDate, is(notNullValue()));

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
