package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.KeywordSearch;
import sessionbuddy.wrappers.resultsets.SearchResultTunes;

public class Test_KeywordSearch_Tune_with_Pagination
{

    public static void main(String[] args)
    {
        // Set the search parameters
        String searchTerms = "The Old Bush";
        int resultsPerPage = 3;
        int pageNumber = 2;

        try
        {
            // Call the searchTunes and pass in the search parameters
            SearchResultTunes results = KeywordSearch.searchTunes(searchTerms, resultsPerPage, pageNumber);

            // Print out some of the metadata
            System.out.println("The number of pages in the response: " + results.responseHeaders.pages);
            System.out.println("The total number of results in the response: " + results.responseHeaders.total);
            System.out.println("This is page number: " + results.responseHeaders.page + " of the response\n");

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
                System.out.println("Submitter's Profile Page: " + results.searchResults.get(i).user.userURL +"\n");
            }
        }

        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException e)
        {
            e.printStackTrace();
        }
    }
}
