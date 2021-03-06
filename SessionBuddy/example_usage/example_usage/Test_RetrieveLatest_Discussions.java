package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.Recent;
import sessionbuddy.wrappers.resultsets.RecentResultDiscussions;

/**
 * Example usage of the searchDiscussions method of RetrieveLatest class to
 * search thesession.org API for a discussion based on a set of search terms,
 * and store the results.
 * 
 * This class does not test the iteration through multiple pages of JSON search
 * results
 * 
 * @author Colman
 * @since 2018-03-11
 */

class Test_RetrieveLatest_Discussions

{
    public static void main(String[] args) throws URISyntaxException
    {
        // Set the search parameters
        int resultsPerPage = 50;

        try
        {
            // Call the listDiscussions() method on the Recent object
            RecentResultDiscussions resultSet = Recent.listDiscussions(resultsPerPage);

            // Loop through the results and print each attribute of each
            // individual result in the set
            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                System.out.println(resultSet.searchResults
                        .get(i).discussionDetails.discussionID);
                System.out.println(resultSet.searchResults
                        .get(i).discussionDetails.discussionName);
                System.out.println(resultSet.searchResults
                        .get(i).discussionDetails.submittedDate);
                System.out.println(resultSet.searchResults
                        .get(i).discussionDetails.discussionURL);
                System.out.println(resultSet.searchResults
                        .get(i).discussionDetails.numberOfComments);

                System.out.println(
                        resultSet.searchResults.get(i).user.userID);
                System.out.println(
                        resultSet.searchResults.get(i).user.userName);
                System.out.println(
                        resultSet.searchResults.get(i).user.userURL);

                System.out.println("\n");
            }
        }

        catch (IllegalArgumentException | IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
