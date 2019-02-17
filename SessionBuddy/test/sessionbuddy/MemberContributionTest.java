package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.Test;

import sessionbuddy.wrappers.resultsets.SearchResultDiscussionsLatest;
import sessionbuddy.wrappers.resultsets.SearchResultEventsLatest;
import sessionbuddy.wrappers.resultsets.SearchResultRecordingsLatest;
import sessionbuddy.wrappers.resultsets.SearchResultSessionsLatest;
import sessionbuddy.wrappers.resultsets.SearchResultSetsLatest;
import sessionbuddy.wrappers.resultsets.SearchResultTunesLatest;

/**
 * Unit tests for the MemberContribution class
 * 
 * Each test follows the same process:
 * 
 * 1. Define the search parameters, including the user ID in question
 * 2. Call the relevant method on MemberContribution
 * 3. Iterate through the result set, testing that each element of each result is populated
 * 
 * For each category of data, one test is done with pagination in the results, 
 * and one without pagination
 * 
 * @author Colman
 * @since 2019-02-13
 */
public class MemberContributionTest
{

    @Test
    public void testListTunesWithPagination()
    {
        int userID = 1;
        int resultsPerPage = 2;
        int pageNumber = 2;

        try
        {
            SearchResultTunesLatest resultSet = MemberContribution.listTunes(userID, resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).settingDetails.settingID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).settingDetails.settingURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).settingDetails.key, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).tuneDetails.tuneID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tuneDetails.tuneName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tuneDetails.tuneURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).settingDetails.date, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testListTunesWithoutPagination()
    {
        int userID = 1;
        int resultsPerPage = 50;

        try
        {
            SearchResultTunesLatest resultSet = MemberContribution.listTunes(userID, resultsPerPage);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).settingDetails.settingID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).settingDetails.settingURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).settingDetails.key, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).tuneDetails.tuneID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tuneDetails.tuneName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).tuneDetails.tuneURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).settingDetails.date, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testListRecordingsWithPagination()
    {
        int resultsPerPage = 2;
        int userID = 1;
        int pageNumber = 2;

        try
        {
            SearchResultRecordingsLatest resultSet = MemberContribution.listRecordings(userID, resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).artist.artistID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).artist.artistName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).artist.artistPageURL, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testListRecordingsWithoutPagination()
    {
        int resultsPerPage = 50;
        int userID = 1;

        try
        {
            SearchResultRecordingsLatest resultSet = MemberContribution.listRecordings(userID, resultsPerPage);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).recordingDetails.recordingURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).artist.artistID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).artist.artistName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).artist.artistPageURL, is(notNullValue()));
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
        int resultsPerPage = 3;
        int pageNumber = 2;
        int userID = 1;

        try
        {
            SearchResultSessionsLatest resultSet = MemberContribution.listSessions(userID, resultsPerPage, pageNumber);

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
    public void testListSessionsWithoutPagination()
    {
        int resultsPerPage = 50;
        int userID = 1;

        try
        {
            SearchResultSessionsLatest resultSet = MemberContribution.listSessions(userID, resultsPerPage);

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
    public void testListEventsWithPagination()
    {
        int resultsPerPage = 2;
        int pageNumber = 2;
        int userID = 1;

        try
        {
            SearchResultEventsLatest resultSet = MemberContribution.listEvents(userID, resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).eventDetails.eventID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.eventName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.eventURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).schedule.startDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).schedule.endDate,is(notNullValue()));

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
    public void testListEventsWithoutPagination()
    {
        int resultsPerPage = 50;
        int userID = 1;

        try
        {
            SearchResultEventsLatest resultSet = MemberContribution.listEvents(userID, resultsPerPage);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).eventDetails.eventID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.eventName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.eventURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).eventDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).schedule.startDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).schedule.endDate,is(notNullValue()));

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
    public void testListDiscussionsWithPagination()
    {
        int resultsPerPage = 4;
        int pageNumber = 2;
        int userID = 1;

        try
        {
            SearchResultDiscussionsLatest resultSet = MemberContribution.listDiscussions(userID, resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.submittedDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testListDiscussionsWithoutPagination()
    {
        int resultsPerPage = 50;
        int userID = 1;

        try
        {
            SearchResultDiscussionsLatest resultSet = MemberContribution.listDiscussions(userID, resultsPerPage);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.submittedDate, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).discussionDetails.discussionURL, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testListSetsWithPagination()
    {
        int userID = 1;
        int resultsPerPage = 4;
        int pageNumber = 2;

        try
        {
            SearchResultSetsLatest resultSet = MemberContribution.listSets(userID, resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).setDetails.setID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).setDetails.setName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).setDetails.setURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).setDetails.setDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testListSetsWithoutPagination()
    {
        int userID = 1;
        int resultsPerPage = 50;
    
        try
        {

            SearchResultSetsLatest resultSet = MemberContribution.listSets(userID, resultsPerPage);

            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                assertThat(resultSet.searchResults.get(i).setDetails.setID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).setDetails.setName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).setDetails.setURL, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).setDetails.setDate, is(notNullValue()));

                assertThat(resultSet.searchResults.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.searchResults.get(i).submitter.userURL, is(notNullValue()));
                }
            }


        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }
}
