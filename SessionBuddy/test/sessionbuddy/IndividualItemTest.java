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

/**
 * Unit tests for the IndividualItem class
 * 
 * Each test follows the same process:
 * 
 * 1. Set the search parameters, i.e. the ID of the resource to be retrieved
 * 2. Perform the API call using one of the static methods in IndividualItem
 * 3. Test the result set, testing (usually) that each element of the result set has been populated
 * 
 * @author Colman
 * @since 2019-02-13
 */
public class IndividualItemTest {

  @Test
  public void testRetrieveRecording() {
    int itemID = 666;

    try {
      ItemResultRecording resultSet = IndividualItem.getRecording(itemID);

      assertNotNull(resultSet.recordingDetails.recordingID);
      assertNotNull(resultSet.recordingDetails.recordingName);
      assertNotNull(resultSet.recordingDetails.recordingURL);
      assertNotNull(resultSet.recordingDetails.recordingDate);

      assertNotNull(resultSet.user.userID);
      assertNotNull(resultSet.user.userName);
      assertNotNull(resultSet.user.userURL);

      assertNotNull(resultSet.artist.artistName);

      assertTrue(resultSet.tracks.size() > 0);

      // Test that the first track on the recording has at least one tune
      assertTrue(resultSet.tracks.get(0).tunes.size() > 0);

      // We know this particular recording has at least one comment
      assertTrue(resultSet.comments.size() > 0);

      assertNotNull(resultSet.comments.get(0).id);
      assertNotNull(resultSet.comments.get(0).url);
      assertNotNull(resultSet.comments.get(0).date);

      assertNotNull(resultSet.comments.get(0).member.userID);
      assertNotNull(resultSet.comments.get(0).member.userName);
      assertNotNull(resultSet.comments.get(0).member.userURL);

      assertNotNull(resultSet.comments.get(0).content);
    }

    catch (IOException | IllegalStateException | URISyntaxException e) {
      fail(e.getMessage());
    }
  }


    @Test
    public void testGetDiscussion()
    {
        int itemID = 666;

        try
        {
            ItemResultDiscussion resultSet = IndividualItem.getDiscussion(itemID);

            assertThat(resultSet.discussionDetails.discussionID, is(notNullValue()));
            assertThat(resultSet.discussionDetails.discussionName, is(notNullValue()));
            assertThat(resultSet.discussionDetails.discussionURL, is(notNullValue()));
            assertThat(resultSet.discussionDetails.submittedDate, is(notNullValue()));

            // Test the attributes of the user who submitted the discussion
            assertThat(resultSet.user.userID, is(notNullValue()));
            assertThat(resultSet.user.userName, is(notNullValue()));
            assertThat(resultSet.user.userURL, is(notNullValue()));

            for (int i = 0; i < resultSet.comments.size(); i++)
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

    @Test
    public void testGetTune()
    {
        int tuneID = 2;
        
        try
        {
            ItemResultTune resultSet = IndividualItem.getTune(tuneID);

            assertThat(resultSet.tuneDetails.basicTuneDetails.tuneID, is(notNullValue()));
            assertThat(resultSet.tuneDetails.basicTuneDetails.tuneName, is(notNullValue()));
            assertThat(resultSet.tuneDetails.tuneType, is(notNullValue()));
            assertThat(resultSet.tuneDetails.submittedDate, is(notNullValue()));
            assertThat(resultSet.tuneDetails.basicTuneDetails.tuneID, is(notNullValue()));
            assertThat(resultSet.tuneDetails.basicTuneDetails.tuneURL, is(notNullValue()));

            assertThat(resultSet.user.userID, is(notNullValue()));
            assertThat(resultSet.user.userName, is(notNullValue()));
            assertThat(resultSet.user.userURL, is(notNullValue()));

            for (int i = 0; i < resultSet.settings.size(); i++)
            {
                assertThat(resultSet.settings.get(i).settingDetails.settingID, is(notNullValue()));
                assertThat(resultSet.settings.get(i).settingDetails.date, is(notNullValue()));
                assertThat(resultSet.settings.get(i).settingDetails.key, is(notNullValue()));
                assertThat(resultSet.settings.get(i).abc, is(notNullValue()));
                assertThat(resultSet.settings.get(i).settingDetails.settingURL, is(notNullValue()));
            }

            for (int i = 0; i < resultSet.comments.size(); i++)
            {
                assertThat(resultSet.comments.get(i).id, is(notNullValue()));
                assertThat(resultSet.comments.get(i).subject, is(notNullValue()));
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

    @Test
    public void testGetSession()
    {
        int sessionID = 6264;
        
        try
        {
            ItemResultSession resultSet = IndividualItem.getSession(sessionID);

            assertThat(resultSet.sessionDetails.sessionID, is(notNullValue()));
            assertThat(resultSet.sessionDetails.sessionURL, is(notNullValue()));
            assertThat(resultSet.sessionDetails.submittedDate, is(notNullValue()));

            assertThat(resultSet.user.userID, is(notNullValue()));
            assertThat(resultSet.user.userName, is(notNullValue()));
            assertThat(resultSet.user.userURL, is(notNullValue()));

            assertThat(resultSet.schedule.toString(), is(notNullValue()));

            assertThat(resultSet.venue.venueName, is(notNullValue()));

            for (int i = 0; i < resultSet.comments.size(); i++)
            {
                assertThat(resultSet.comments.get(i).id, is(notNullValue()));
                assertThat(resultSet.comments.get(i).url, is(notNullValue()));
                assertThat(resultSet.comments.get(i).date, is(notNullValue()));

                assertThat(resultSet.comments.get(i).member.userID, is(notNullValue()));
                assertThat(resultSet.comments.get(i).member.userName, is(notNullValue()));
                assertThat(resultSet.comments.get(i).member.userURL, is(notNullValue()));

                assertThat(resultSet.comments.get(i).subject, is(notNullValue()));
                assertThat(resultSet.comments.get(i).content, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetEvent()
    {
        int eventID = 2;
        
        try
        {
            ItemResultEvent resultSet = IndividualItem.getEvent(eventID);

            assertThat(resultSet.eventDetails.eventID, is(notNullValue()));
            assertThat(resultSet.eventDetails.eventName, is(notNullValue()));
            assertThat(resultSet.eventDetails.eventURL, is(notNullValue()));
            assertThat(resultSet.eventDetails.submittedDate, is(notNullValue()));

            assertThat(resultSet.schedule.startDate, is(notNullValue()));
            assertThat(resultSet.schedule.endDate, is(notNullValue()));

            assertThat(resultSet.user.userID, is(notNullValue()));
            assertThat(resultSet.user.userName, is(notNullValue()));
            assertThat(resultSet.user.userURL, is(notNullValue()));

            assertThat(resultSet.coordinates.latitude, is(notNullValue()));
            assertThat(resultSet.coordinates.longitude, is(notNullValue()));

            assertThat(resultSet.town.townName, is(notNullValue()));
            assertThat(resultSet.area.areaName, is(notNullValue()));
            assertThat(resultSet.country.countryName, is(notNullValue()));

            assertThat(resultSet.venue.venueName, is(notNullValue()));
            assertThat(resultSet.venue.venuePhone, is(notNullValue()));
            assertThat(resultSet.venue.venueEmail, is(notNullValue()));
            assertThat(resultSet.venue.venueWebsite, is(notNullValue()));

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
    
    @Test
    public void testGetTrip()
    {
        int tripID = 2;
        
        try
        {
            ItemResultTrip resultSet = IndividualItem.getTrip(tripID);

            assertThat(resultSet.tripDetails.tripID, is(notNullValue()));
            assertThat(resultSet.tripDetails.tripName, is(notNullValue()));
            assertThat(resultSet.tripDetails.tripURL, is(notNullValue()));
            assertThat(resultSet.tripDetails.submittedDate, is(notNullValue()));

            assertThat(resultSet.tripSchedule.startDate, is(notNullValue()));
            assertThat(resultSet.tripSchedule.endDate, is(notNullValue()));

            assertThat(resultSet.user.userID, is(notNullValue()));
            assertThat(resultSet.user.userName, is(notNullValue()));
            assertThat(resultSet.user.userURL, is(notNullValue()));

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
