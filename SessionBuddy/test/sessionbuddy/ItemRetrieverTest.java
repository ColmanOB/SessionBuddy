package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.Test;
import sessionbuddy.wrappers.resultsets.ItemResultDiscussion;
import sessionbuddy.wrappers.resultsets.ItemResultEvent;
import sessionbuddy.wrappers.resultsets.ItemResultRecording;
import sessionbuddy.wrappers.resultsets.ItemResultSession;
import sessionbuddy.wrappers.resultsets.ItemResultTrip;
import sessionbuddy.wrappers.resultsets.ItemResultTune;

public class ItemRetrieverTest
{

    @Test
    public void testRetrieveRecording()
    {
        int itemID = 666;

        try
        {
            ItemRetriever retriever = new ItemRetriever(itemID);

            // Instantiate an object to hold the response from the API, and make
            // the API call
            ItemResultRecording resultSet = retriever.getRecording();

            // Test that the basic details of the recording are populated
            assertThat(resultSet.recordingDetails.recordingID,
                    is(notNullValue()));
            assertThat(resultSet.recordingDetails.recordingName,
                    is(notNullValue()));
            assertThat(resultSet.recordingDetails.recordingURL,
                    is(notNullValue()));
            assertThat(resultSet.recordingDetails.recordingDate,
                    is(notNullValue()));

            // Test the details of the user who submitted the recording
            assertThat(resultSet.member.userID, is(notNullValue()));
            assertThat(resultSet.member.userName, is(notNullValue()));
            assertThat(resultSet.member.userURL, is(notNullValue()));

            assertThat(resultSet.artist.artistName, is(notNullValue()));

            assertTrue(resultSet.tracks.size() > 0);

            // Test that the first track on the recording has at least one tune
            assertTrue(resultSet.tracks.get(0).tunes.size() > 0);

            // We know this particular recording has at least one comment
            assertTrue(resultSet.comments.size() > 0);
            assertThat(resultSet.comments.get(0).id, is(notNullValue()));
            assertThat(resultSet.comments.get(0).url, is(notNullValue()));
            assertThat(resultSet.comments.get(0).date, is(notNullValue()));

            // Test the attributes of the user who submitted the comment
            assertThat(resultSet.comments.get(0).member.userID,
                    is(notNullValue()));
            assertThat(resultSet.comments.get(0).member.userName,
                    is(notNullValue()));
            assertThat(resultSet.comments.get(0).member.userURL,
                    is(notNullValue()));

            assertThat(resultSet.comments.get(0).content, is(notNullValue()));
        }

        catch (IOException | IllegalStateException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetDiscussion()
    {
        int itemID = 666;

        try
        {
            // Instantiate a RetrieveItem object & set the search parameters
            ItemRetriever search = new ItemRetriever(itemID);

            // Call the getDiscussion method on the ItemRetriever object
            ItemResultDiscussion resultSet = search.getDiscussion();

            // Test the attributes of the discussion as a whole:
            assertThat(resultSet.discussionDetails.discussionID,
                    is(notNullValue()));
            assertThat(resultSet.discussionDetails.discussionName,
                    is(notNullValue()));
            assertThat(resultSet.discussionDetails.discussionURL,
                    is(notNullValue()));
            assertThat(resultSet.discussionDetails.submittedDate,
                    is(notNullValue()));

            // Test the attributes of the user who submitted the discussion
            assertThat(resultSet.member.userID, is(notNullValue()));
            assertThat(resultSet.member.userName, is(notNullValue()));
            assertThat(resultSet.member.userURL, is(notNullValue()));

            // Loop through each individual comment in the discussion and test
            // that the details are
            // populated
            for (int i = 0; i < resultSet.comments.size(); i++)
            {
                assertThat(resultSet.comments.get(i).id, is(notNullValue()));
                assertThat(resultSet.comments.get(i).url, is(notNullValue()));
                assertThat(resultSet.comments.get(i).date, is(notNullValue()));
                assertThat(resultSet.comments.get(i).member.userID,
                        is(notNullValue()));
                assertThat(resultSet.comments.get(i).member.userName,
                        is(notNullValue()));
                assertThat(resultSet.comments.get(i).member.userURL,
                        is(notNullValue()));
                assertThat(resultSet.comments.get(i).content,
                        is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException
                | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetTune()
    {
        try
        {
            // Set the search parameters
            int tuneID = 2;

            // Instantiate a RetrieveItem object
            ItemRetriever search = new ItemRetriever(tuneID);

            // Perform the search
            ItemResultTune resultSet = search.getTune();

            // Test the general tune details
            assertThat(resultSet.tuneDetails.basicTuneDetails.tuneID,
                    is(notNullValue()));
            assertThat(resultSet.tuneDetails.basicTuneDetails.tuneName,
                    is(notNullValue()));
            assertThat(resultSet.tuneDetails.tuneType, is(notNullValue()));
            assertThat(resultSet.tuneDetails.submittedDate, is(notNullValue()));
            assertThat(resultSet.tuneDetails.basicTuneDetails.tuneID,
                    is(notNullValue()));
            assertThat(resultSet.tuneDetails.basicTuneDetails.tuneURL,
                    is(notNullValue()));

            // Test the details of the tune submitter
            assertThat(resultSet.member.userID, is(notNullValue()));
            assertThat(resultSet.member.userName, is(notNullValue()));
            assertThat(resultSet.member.userURL, is(notNullValue()));

            // Loop through each setting of the tune and test that its various
            // attributes are populated
            for (int i = 0; i < resultSet.settings.size(); i++)
            {
                assertThat(resultSet.settings.get(i).settingDetails.settingID,
                        is(notNullValue()));
                assertThat(resultSet.settings.get(i).settingDetails.date,
                        is(notNullValue()));
                assertThat(resultSet.settings.get(i).settingDetails.key,
                        is(notNullValue()));
                assertThat(resultSet.settings.get(i).abc, is(notNullValue()));
                assertThat(resultSet.settings.get(i).settingDetails.settingURL,
                        is(notNullValue()));
            }

            // Loop through each individual comment on the tune's page and test
            // it
            for (int i = 0; i < resultSet.comments.size(); i++)
            {
                assertThat(resultSet.comments.get(i).id, is(notNullValue()));
                assertThat(resultSet.comments.get(i).subject,
                        is(notNullValue()));
                assertThat(resultSet.comments.get(i).url, is(notNullValue()));
                assertThat(resultSet.comments.get(i).date, is(notNullValue()));

                assertThat(resultSet.comments.get(i).member.userID,
                        is(notNullValue()));
                assertThat(resultSet.comments.get(i).member.userName,
                        is(notNullValue()));
                assertThat(resultSet.comments.get(i).member.userURL,
                        is(notNullValue()));

                assertThat(resultSet.comments.get(i).content,
                        is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException
                | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetSession()
    {
        try
        {
            // Set the parameters, i.e. the ID number of the session we want to
            // retrieve
            int sessionID = 6264;

            // Instantiate an ItemRetriever object
            ItemRetriever search = new ItemRetriever(sessionID);

            // Perform the search
            ItemResultSession resultSet = search.getSession();

            // Test the basic details of the session
            assertThat(resultSet.sessionDetails.sessionID, is(notNullValue()));
            assertThat(resultSet.sessionDetails.sessionURL, is(notNullValue()));
            assertThat(resultSet.sessionDetails.submittedDate,
                    is(notNullValue()));

            // Test the details of the user that submitted the session
            assertThat(resultSet.member.userID, is(notNullValue()));
            assertThat(resultSet.member.userName, is(notNullValue()));
            assertThat(resultSet.member.userURL, is(notNullValue()));

            assertThat(resultSet.schedule.toString(), is(notNullValue()));

            assertThat(resultSet.venue.venueName, is(notNullValue()));

            // Loop through each comment on the session and test the details
            for (int i = 0; i < resultSet.comments.size(); i++)
            {
                assertThat(resultSet.comments.get(i).id, is(notNullValue()));
                assertThat(resultSet.comments.get(i).url, is(notNullValue()));
                assertThat(resultSet.comments.get(i).date, is(notNullValue()));

                assertThat(resultSet.comments.get(i).member.userID,
                        is(notNullValue()));
                assertThat(resultSet.comments.get(i).member.userName,
                        is(notNullValue()));
                assertThat(resultSet.comments.get(i).member.userURL,
                        is(notNullValue()));

                assertThat(resultSet.comments.get(i).subject,
                        is(notNullValue()));
                assertThat(resultSet.comments.get(i).content,
                        is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException
                | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetEvent()
    {
        try
        {
            // Set the parameter, i.e. the ID number of the event to be
            // retrieved
            int eventID = 2;

            // Instantiate a ItemRetriever object
            ItemRetriever search = new ItemRetriever(eventID);

            // Call the getEvent() method on the ItemRetriever object
            ItemResultEvent resultSet = search.getEvent();

            // Test the general details of the event
            assertThat(resultSet.eventDetails.eventID, is(notNullValue()));
            assertThat(resultSet.eventDetails.eventName, is(notNullValue()));
            assertThat(resultSet.eventDetails.eventURL, is(notNullValue()));
            assertThat(resultSet.eventDetails.submittedDate,
                    is(notNullValue()));

            assertThat(resultSet.schedule.startDate, is(notNullValue()));
            assertThat(resultSet.schedule.endDate, is(notNullValue()));

            assertThat(resultSet.member.userID, is(notNullValue()));
            assertThat(resultSet.member.userName, is(notNullValue()));
            assertThat(resultSet.member.userURL, is(notNullValue()));

            assertThat(resultSet.coordinates.latitude, is(notNullValue()));
            assertThat(resultSet.coordinates.longitude, is(notNullValue()));

            assertThat(resultSet.town.townName, is(notNullValue()));
            assertThat(resultSet.area.areaName, is(notNullValue()));
            assertThat(resultSet.country.countryName, is(notNullValue()));

            assertThat(resultSet.venue.venueName, is(notNullValue()));
            assertThat(resultSet.venue.venuePhone, is(notNullValue()));
            assertThat(resultSet.venue.venueEmail, is(notNullValue()));
            assertThat(resultSet.venue.venueWebsite, is(notNullValue()));

            // Loop through each comment on the event and test the details
            for (int i = 0; i < (resultSet.comments.size()); i++)
            {
                assertThat(resultSet.comments.get(i).id, is(notNullValue()));
                assertThat(resultSet.comments.get(i).url, is(notNullValue()));
                assertThat(resultSet.comments.get(i).date, is(notNullValue()));

                assertThat(resultSet.comments.get(i).member.userID,
                        is(notNullValue()));
                assertThat(resultSet.comments.get(i).member.userName,
                        is(notNullValue()));
                assertThat(resultSet.comments.get(i).member.userURL,
                        is(notNullValue()));
                assertThat(resultSet.comments.get(i).content,
                        is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException
                | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testGetTrip()
    {
        try
        {
            // Set the parameter, i.e. the ID number of the trip to be retrieved
            int tripID = 2;

            // Instantiate a ItemRetriever object
            ItemRetriever search = new ItemRetriever(tripID);

            // Call the getTrip() method on the ItemRetriever object
            ItemResultTrip resultSet = search.getTrip();

            // Test the general details of the event
            assertThat(resultSet.tripDetails.tripID, is(notNullValue()));
            assertThat(resultSet.tripDetails.tripName, is(notNullValue()));
            assertThat(resultSet.tripDetails.tripURL, is(notNullValue()));
            assertThat(resultSet.tripDetails.submittedDate, is(notNullValue()));

            assertThat(resultSet.tripSchedule.startDate, is(notNullValue()));
            assertThat(resultSet.tripSchedule.endDate, is(notNullValue()));

            assertThat(resultSet.member.userID, is(notNullValue()));
            assertThat(resultSet.member.userName, is(notNullValue()));
            assertThat(resultSet.member.userURL, is(notNullValue()));

            assertThat(resultSet.coordinates.latitude, is(notNullValue()));
            assertThat(resultSet.coordinates.longitude, is(notNullValue()));


            // Loop through each comment on the event and test the details
            for (int i = 0; i < (resultSet.comments.size()); i++)
            {
                assertThat(resultSet.comments.get(i).id, is(notNullValue()));
                assertThat(resultSet.comments.get(i).url, is(notNullValue()));
                assertThat(resultSet.comments.get(i).date, is(notNullValue()));

                assertThat(resultSet.comments.get(i).member.userID, is(notNullValue()));
                assertThat(resultSet.comments.get(i).member.userName, is(notNullValue()));
                assertThat(resultSet.comments.get(i).member.userURL, is(notNullValue()));
                assertThat(resultSet.comments.get(i).content, is(notNullValue()));
            }
        }
        
        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }
}
