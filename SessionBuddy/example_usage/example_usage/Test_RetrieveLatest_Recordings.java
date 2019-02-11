package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.LatestSearch;
import sessionbuddy.wrappers.resultsets.SearchResultRecordingsLatest;

class Test_RetrieveLatest_Recordings
{
    public static void main(String[] args) throws URISyntaxException
    {
        try
        {
            // Set the number of results to be returned per page
            int resultsPerPage = 50;

            // Call the listRecordings method on the LatestSearch object
            SearchResultRecordingsLatest resultSet = LatestSearch.listRecordings(resultsPerPage);

            // Loop through the results and print each attribute of each individual result in the set
            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                System.out.println("Recording ID: " + resultSet.searchResults.get(i).recordingDetails.recordingID);
                System.out.println("Recording Name: " + resultSet.searchResults.get(i).recordingDetails.recordingName);
                System.out.println("Submission Date: " + resultSet.searchResults.get(i).recordingDetails.recordingDate);
                System.out.println("Recurding URL: " + resultSet.searchResults.get(i).recordingDetails.recordingURL);

                System.out.println("Submitter User ID: " + resultSet.searchResults.get(i).submitter.userID);
                System.out.println("Submitter Username: " + resultSet.searchResults.get(i).submitter.userName);
                System.out.println("Submitter Profile Page: " + resultSet.searchResults.get(i).submitter.userURL);

                System.out.println("Artist ID: " + resultSet.searchResults.get(i).artist.artistID);
                System.out.println("Artist Name: " + resultSet.searchResults.get(i).artist.artistName);
                System.out.println("Artist Page URL: " + resultSet.searchResults.get(i).artist.artistPageURL);

                System.out.println("\n");
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
