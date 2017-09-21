package example_usage;

import java.io.IOException;

import sessionbuddy.ItemRetriever;
import sessionbuddy.wrappers.resultsets.ItemResultRecording;

public class Test_RetrieveItemByID_Recording 
	{
	public static void main(String[] args)
		{
		// Set the search parameters
		String recordingID = "666";
		
		// Instantiate an ItemRetriever object
		ItemRetriever search = new ItemRetriever();
		
		// Instantiate an object to hold the response from the API
		ItemResultRecording resultSet;
		
		try 
			{
			resultSet = search.getRecordingByID(recordingID);
			// Print out attributes of the Recording as a whole:
			System.out.println("Recording ID: " + resultSet.recordingDetails.recordingID);
			System.out.println("Recording Title: " + resultSet.recordingDetails.recordingName);
			System.out.println("Recording URL: " + resultSet.recordingDetails.recordingURL);
			System.out.println("Date: " + resultSet.recordingDetails.recordingDate);
			System.out.println("\n");
			
			// Print out attributes of the user who submitted the Recording
			System.out.println("Submitted by user: ");
			System.out.println("User ID: " + resultSet.member.userID);
			System.out.println("Username: " + resultSet.member.userName);
			System.out.println("User Profile Page: " + resultSet.member.userURL);
			System.out.println("\n");

			
			System.out.println("Artist Name:" + resultSet.artist.artistName);
			
			// Print out the track listing
			for (int i = 0; i < (resultSet.tracks.size()-1); i++)
				{
				System.out.println("Track: " + i);
				
				for (int j = 0; j < resultSet.tracks.get(i).tunes.size(); j++)
					{
					System.out.println("Tune ID: " + resultSet.tracks.get(i).tunes.get(j).tuneID);
					System.out.println("Tune Name: " + resultSet.tracks.get(i).tunes.get(j).tuneName);
					System.out.println("Tune URL: " + resultSet.tracks.get(i).tunes.get(j).tuneURL);
					System.out.println("\n");
					}
				}
			
			// Loop through each individual comment on the Recording and print the details
			for (int i = 0; i < resultSet.comments.size(); i++)
				{
				System.out.println("Comment ID: " + resultSet.comments.get(i).id);
				System.out.println("Comment URL: " + resultSet.comments.get(i).url);
				System.out.println("Comment Date: " + resultSet.comments.get(i).date);

				System.out.println("User ID: " + resultSet.comments.get(i).member.userID);
				System.out.println("Username: " + resultSet.comments.get(i).member.userName);
				System.out.println("User Profile: " + resultSet.comments.get(i).member.userURL);
				
				System.out.println("Comment: \n" + resultSet.comments.get(i).content);
				
				System.out.println("\n");
				} 
			}
			
			catch (IOException e) 
				{
				e.printStackTrace();
				}
		
			catch (IllegalStateException e)
				{
				e.printStackTrace();
				}
			}
	}
