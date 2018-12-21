package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.KeywordSearch;
import sessionbuddy.wrappers.resultsets.SearchResultDiscussions;

/**
 * Example usage of the searchDiscussions method of KeywordSearch class to
 * search thesession.org API for a discussion based on a set of search terms,
 * and store the results.
 * 
 * This class does not test the iteration through multiple pages of JSON search
 * results
 * 
 * @author Colman
 * @since 2017-08-13
 */

public class Test_KeywordSearch_Discussions
{

    public static void main(String[] args)
    {
        // Set the search parameters
        String searchTerms = "Wig Glue";
        int resultsPerPage = 10;
        int pageNumber = 1;

        try
        {
            SearchResultDiscussions resultSet = KeywordSearch.searchDiscussions(searchTerms, resultsPerPage, pageNumber);
            
            // Print out some of the metadata
            System.out.println("The number of pages in the response: " + resultSet.responseHeaders.pages);
            System.out.println("The total number of results in the response: " + resultSet.responseHeaders.total);
            System.out.println("This is page number: " + resultSet.responseHeaders.page + " of the response\n");

            // Loop through the results and print each attribute of each individual result in the set
            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                System.out.println("Discussion ID: " + resultSet.searchResults
                        .get(i).discussionDetails.discussionID);
                System.out
                        .println("Discussion Title: " + resultSet.searchResults
                                .get(i).discussionDetails.discussionName);
                System.out.println("Date Submitted: " + resultSet.searchResults
                        .get(i).discussionDetails.submittedDate);
                System.out.println("Discussion URL: " + resultSet.searchResults
                        .get(i).discussionDetails.discussionURL);
                System.out.println("No. of comments: " + resultSet.searchResults
                        .get(i).discussionDetails.numberOfComments);

                System.out.println("Submitter User ID: "
                        + resultSet.searchResults.get(i).submitter.userID);
                System.out.println("Submitter Username: "
                        + resultSet.searchResults.get(i).submitter.userName);
                System.out.println("Submitter Profile Page: "
                        + resultSet.searchResults.get(i).submitter.userURL);
                System.out.println("\n");
            }
        }

        catch (IllegalArgumentException | IOException | URISyntaxException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
