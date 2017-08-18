package white_box_testing;

import json_object_wrappers.RecordingByIDResult;
import main.RetrieveItem;

public class Test_RecordingByID 
{
	public static void main(String[] args)
		{
		// Set the search parameters
		String itemCategory = "recordings";
		String recordingID = "666";
		int resultsPerPage = 50;
		
		
		// Instantiate a RetrieveItem object
		RetrieveItem search = new RetrieveItem();
		
		// Pass in the parameters for the Recording we want to retrieve
		RecordingByIDResult resultSet = search.getRecordingByID(itemCategory, recordingID, resultsPerPage);
		
		// Print out attributes of the Recording as a whole:
		System.out.println("Recording ID: " + resultSet.id);
		System.out.println("Recording Title: " + resultSet.name);
		System.out.println("Recording URL: " + resultSet.url);
		System.out.println("Date: " + resultSet.date);
		System.out.println("\n");
		
		// Print out attributes of the user who submitted the Recording
		System.out.println("Submitted by user: ");
		System.out.println("User ID: " + resultSet.member.userID);
		System.out.println("Username: " + resultSet.member.userName);
		System.out.println("User Profile Page: " + resultSet.member.userURL);
		System.out.println("\n");

		
		System.out.println("Artist Name:" + resultSet.artist.artistName);
		
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
		
		// Loop through each individual comment in the Recording and print the details
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

}
