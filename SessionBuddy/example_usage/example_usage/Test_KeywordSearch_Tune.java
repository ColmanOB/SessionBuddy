package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.KeywordSearch;
import sessionbuddy.wrappers.resultsets.SearchResultTunes;

public class Test_KeywordSearch_Tune
{

    public static void main(String[] args) throws IllegalStateException, URISyntaxException
    {
        // Set the search parameters
        String searchTerms = "Bucks";
        int resultsPerPage = 50;
        int pageNumber = 1;

        try
        {
            // Call the searchTunes method of KeywordSearch and pass in the search parameters

            SearchResultTunes results = KeywordSearch.searchTunes(searchTerms, resultsPerPage, pageNumber);

            System.out.println("The  number of pages in the response: " + results.responseHeaders.pages);
            System.out.println("The total number of results in the response: " + results.responseHeaders.total);

            // Loop through the results and print each attribute of each individual result in the set
            for (int i = 0; i < results.searchResults.size(); i++)
            {
                System.out.println("Tune ID: " + results.searchResults.get(i).tuneDetails.basicTuneDetails.tuneID);
                System.out.println("Tune Title: " + results.searchResults.get(i).tuneDetails.basicTuneDetails.tuneName);
                System.out.println("Tune Type: " + results.searchResults.get(i).tuneDetails.tuneType);
                System.out.println("Tune URL: " + results.searchResults.get(i).tuneDetails.basicTuneDetails.tuneURL);
                System.out.println("Date Submitted: " + results.searchResults.get(i).tuneDetails.submittedDate);

                System.out.println("Submitted by User ID: " + results.searchResults.get(i).user.userID);
                System.out.println("Submitted by Username: " + results.searchResults.get(i).user.userName);
                System.out.println("Submitter's Profile Page: " + results.searchResults.get(i).user.userURL);
                
                System.out.println("\n");
            }
        }

        catch (IllegalArgumentException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
