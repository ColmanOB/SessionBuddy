package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.KeywordSearch;
import sessionbuddy.wrappers.resultsets.SearchResultRecordings;

public class Test_KeywordSearch_Recordings_with_Pagination
{

    public static void main(String[] args) throws URISyntaxException
    {
        // Set the search parameters
        String searchTerms = "humours";
        int resultsPerPage = 2;
        int pageNumber = 3;

        try
        {
            // Create a structure to hold the response from the API
            SearchResultRecordings resultSet = KeywordSearch.searchRecordings(searchTerms, resultsPerPage, pageNumber);

            // Loop through the results and print each attribute of each
            // individual result in the set
            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                System.out.println("Recording ID: " + resultSet.searchResults.get(i).recordingDetails.recordingID);
                System.out.println("Recording Name: " + resultSet.searchResults.get(i).recordingDetails.recordingName);
                System.out.println("Recording URL: " + resultSet.searchResults.get(i).recordingDetails.recordingURL);

                System.out.println("Artist ID: " + resultSet.searchResults.get(i).artist.artistID);
                System.out.println("Artist Name: " + resultSet.searchResults.get(i).artist.artistName);
                System.out.println("Artist Page URL: " + resultSet.searchResults.get(i).artist.artistPageURL);

                System.out.println("Submitter User ID: " + resultSet.searchResults.get(i).submitter.userID);
                System.out.println("Submitter Username: " + resultSet.searchResults.get(i).submitter.userName);
                System.out.println("Submitter Profile Page: " + resultSet.searchResults.get(i).submitter.userURL);
                System.out.println("\n");
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
