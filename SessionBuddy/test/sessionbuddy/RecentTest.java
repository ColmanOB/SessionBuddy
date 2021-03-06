package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.Test;

import sessionbuddy.wrappers.resultsets.RecentResultDiscussions;
import sessionbuddy.wrappers.resultsets.RecentResultEvents;
import sessionbuddy.wrappers.resultsets.RecentResultRecordings;
import sessionbuddy.wrappers.resultsets.RecentResultSessions;
import sessionbuddy.wrappers.resultsets.RecentResultTrips;
import sessionbuddy.wrappers.resultsets.RecentResultTunes;

/**
 * Unit tests for the Recent class
 * 
 * Each test follows the same process:
 * 
 * 1. Set the search parameters
 * 2. Instantiate a Recent object
 * 3. Perform the search
 * 4. Loop through the result set, testing that each element of each result is populated
 * 
 * For each category of data, one test is done with pagination in the results, 
 * and one without pagination
 * 
 * @author Colman
 * @since 2018-12-10
 */
public class RecentTest
{

    @Test
    public void testListTunesWithoutPagination()
    {
        int resultsPerPage = 50;
        
        try
        {
            RecentResultTunes resultSet = Recent.listTunes(resultsPerPage);
            
            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).settingDetails.settingID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).settingDetails.settingURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).settingDetails.key, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).tuneDetails.tuneID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tuneDetails.tuneName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tuneDetails.tuneURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).settingDetails.date, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userURL, is(notNullValue()));
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

        try
        {
            RecentResultTunes resultSet = Recent.listTunes(resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).settingDetails.settingID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).settingDetails.settingURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).settingDetails.key, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).tuneDetails.tuneID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tuneDetails.tuneName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tuneDetails.tuneURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).settingDetails.date, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userURL, is(notNullValue()));
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
        
        try
        {
            RecentResultDiscussions resultSet = Recent.listDiscussions(resultsPerPage);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.submittedDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.numberOfComments, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userURL, is(notNullValue()));
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
        
        try
        {
            RecentResultDiscussions resultSet = Recent.listDiscussions(resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.submittedDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.numberOfComments, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userURL, is(notNullValue()));
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
        
        try
        {
            RecentResultRecordings resultSet = Recent.listRecordings(resultsPerPage);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).artist.artistID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).artist.artistName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).artist.artistPageURL, is(notNullValue()));
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

        try
        {
            RecentResultRecordings resultSet = Recent.listRecordings(resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).artist.artistID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).artist.artistName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).artist.artistPageURL, is(notNullValue()));
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
        
        try
        {
            RecentResultSessions resultSet = Recent.listSessions(resultsPerPage);

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

                assertThat(resultSet.searchResults.get(i).venue.venueName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).venue.venueEmail, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).venue.venuePhone, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).venue.venueWebsite, is(notNullValue()));
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
        
        try
        {
            RecentResultSessions resultSet = Recent.listSessions(resultsPerPage, pageNumber);

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

                assertThat(resultSet.searchResults.get(i).venue.venueName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).venue.venueEmail, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).venue.venuePhone, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).venue.venueWebsite, is(notNullValue()));
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

        try
        {
            RecentResultEvents resultSet = Recent.listEvents(resultsPerPage);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).eventDetails.eventID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.eventName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.eventURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).schedule.startDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).schedule.endDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).coordinates.latitude, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).coordinates.longitude, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).venue.venueName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).venue.venueEmail, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).venue.venuePhone, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).venue.venueWebsite, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).town.townName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).area.areaName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).country.countryName, is(notNullValue()));
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

        try
        {
            RecentResultEvents resultSet = Recent.listEvents(resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).eventDetails.eventID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.eventName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.eventURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).schedule.startDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).schedule.endDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).coordinates.latitude, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).coordinates.longitude, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).venue.venueName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).venue.venueEmail, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).venue.venuePhone, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).venue.venueWebsite, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).town.townName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).area.areaName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).country.countryName, is(notNullValue()));
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

        try
        {
            RecentResultTrips resultSet = Recent.listTrips(resultsPerPage);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).tripDetails.tripID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripDetails.tripName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripDetails.tripURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).tripSchedule.startDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripSchedule.endDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userURL, is(notNullValue()));
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

        try
        {
            RecentResultTrips resultSet = Recent.listTrips(resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).tripDetails.tripID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripDetails.tripName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripDetails.tripURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripDetails.submittedDate, is(notNullValue()));
                
                assertThat(resultSet.searchResults.get(i).tripSchedule.startDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tripSchedule.endDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).user.userURL, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }
}
