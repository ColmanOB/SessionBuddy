package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import sessionbuddy.wrappers.resultsets.ItemResultDiscussion;
import sessionbuddy.wrappers.resultsets.ItemResultRecording;
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
			
			// Instantiate an object to hold the response from the API, and make the API call
			ItemResultRecording resultSet = retriever.getRecording();
			
			// Test that the basic details of the recording are populated
			assertThat(resultSet.recordingDetails.recordingID, is(notNullValue()));
			assertThat(resultSet.recordingDetails.recordingName, is(notNullValue()));
			assertThat(resultSet.recordingDetails.recordingURL, is(notNullValue()));
			assertThat(resultSet.recordingDetails.recordingDate, is(notNullValue()));
			
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
			assertThat(resultSet.comments.get(0).member.userID, is(notNullValue()));
			assertThat(resultSet.comments.get(0).member.userName, is(notNullValue()));
			assertThat(resultSet.comments.get(0).member.userURL, is(notNullValue()));
			
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
			assertThat(resultSet.discussionDetails.discussionID, is(notNullValue()));
			assertThat(resultSet.discussionDetails.discussionName, is(notNullValue()));
			assertThat(resultSet.discussionDetails.discussionURL, is(notNullValue()));
			assertThat(resultSet.discussionDetails.submittedDate, is(notNullValue()));
				
			// Test the attributes of the user who submitted the discussion
			assertThat(resultSet.member.userID, is(notNullValue()));
			assertThat(resultSet.member.userName, is(notNullValue()));
			assertThat(resultSet.member.userURL, is(notNullValue()));
				
			// Loop through each individual comment in the discussion and test that the details are populated
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
			e.printStackTrace();
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
			assertThat(resultSet.tuneDetails.tuneDetails.tuneID, is(notNullValue()));
			assertThat(resultSet.tuneDetails.tuneDetails.tuneName, is(notNullValue()));
			assertThat(resultSet.tuneDetails.tuneType, is(notNullValue()));
			assertThat(resultSet.tuneDetails.submittedDate, is(notNullValue()));
			assertThat(resultSet.tuneDetails.tuneDetails.tuneID, is(notNullValue()));
			assertThat(resultSet.tuneDetails.tuneDetails.tuneURL, is(notNullValue()));
			
			// Test the details of the tune submitter
			assertThat(resultSet.member.userID, is(notNullValue()));
			assertThat(resultSet.member.userName, is(notNullValue()));
			assertThat(resultSet.member.userURL, is(notNullValue()));
			
			// Loop through each setting of the tune and test that its various attributes are populated
			for (int i = 0; i < resultSet.settings.size(); i++)
				{
				assertThat(resultSet.settings.get(i).settingDetails.settingID, is(notNullValue()));
				assertThat(resultSet.settings.get(i).settingDetails.date, is(notNullValue()));
				assertThat(resultSet.settings.get(i).settingDetails.key, is(notNullValue()));
				assertThat(resultSet.settings.get(i).abc, is(notNullValue()));
				assertThat(resultSet.settings.get(i).settingDetails.settingURL, is(notNullValue()));
				}
	
			// Loop through each individual comment on the tune's page and test it
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
			e.printStackTrace();
			}
		}

	@Test
	public void testGetSession() 
		{
		fail("Not yet implemented");
		}

	@Test
	public void testGetEvent() 
		{
		fail("Not yet implemented");
		}

	}
