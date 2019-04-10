package example_usage;

import java.io.IOException;
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
 * @since 2019-01-27
 */

public class Test_KeywordSearch_Discussions_with_Pagination
{

    public static void main(String[] args) throws Exception
    {
        // Set the search parameters
        String searchTerms = "Wig glue";
        int resultsPerPage = 40;
        int pageNumber = 1;

        try
        {
            // Invoke the searchDiscussions method
            SearchResultDiscussions resultSet = KeywordSearch.searchDiscussions(searchTerms, resultsPerPage, pageNumber);

            if (resultSet.searchResults.size() == 0)
            {
                System.out.println("No results retrieved");
            }

            // Loop through the results and print each attribute of each
            // individual result in the set
            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                System.out.println("Discussion ID: " + resultSet.searchResults.get(i).discussionDetails.discussionID);
                System.out.println("Discussion Title: " + resultSet.searchResults.get(i).discussionDetails.discussionName);
                System.out.println("Date Submitted: " + resultSet.searchResults.get(i).discussionDetails.submittedDate);
                System.out.println("Discussion URL: " + resultSet.searchResults.get(i).discussionDetails.discussionURL);
                System.out.println("No. of comments: " + resultSet.searchResults.get(i).discussionDetails.numberOfComments);

                System.out.println("Submitter User ID: " + resultSet.searchResults.get(i).user.userID);
                System.out.println("Submitter Username: " + resultSet.searchResults.get(i).user.userName);
                System.out.println("Submitter Profile Page: "+ resultSet.searchResults.get(i).user.userURL);
                System.out.println("\n");
            }
        }

        catch (IllegalArgumentException | IOException ex)
        {
            throw ex;
        }
    }
}
