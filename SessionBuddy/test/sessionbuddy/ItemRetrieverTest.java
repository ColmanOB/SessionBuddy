package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import sessionbuddy.wrappers.resultsets.ItemResultRecording;

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
			
			assertThat(resultSet.recordingDetails.recordingID, is(notNullValue()));
			assertThat(resultSet.recordingDetails.recordingName, is(notNullValue()));
			assertThat(resultSet.recordingDetails.recordingURL, is(notNullValue()));
			assertThat(resultSet.recordingDetails.recordingDate, is(notNullValue()));
			
			assertThat(resultSet.member.userID, is(notNullValue()));
			assertThat(resultSet.member.userName, is(notNullValue()));
			assertThat(resultSet.member.userURL, is(notNullValue()));
			
			assertThat(resultSet.artist.artistName, is(notNullValue()));
			
			assertTrue(resultSet.tracks.size() > 0);
			
			assertTrue(resultSet.tracks.get(0).tunes.size() > 0);
			
			// We know this particular recording has at least one comment
			assertTrue(resultSet.comments.size() > 0);
			assertThat(resultSet.comments.get(0).id, is(notNullValue()));
			assertThat(resultSet.comments.get(0).url, is(notNullValue()));
			assertThat(resultSet.comments.get(0).date, is(notNullValue()));
			
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
		fail("Not yet implemented");
		}

	@Test
	public void testGetTune() 
		{
		fail("Not yet implemented");
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
