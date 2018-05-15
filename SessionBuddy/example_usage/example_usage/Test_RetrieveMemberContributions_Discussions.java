package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import sessionbuddy.MemberContributionSearch;
import sessionbuddy.wrappers.resultsets.SearchResultDiscussions;

/**
 * Example usage of the searchDiscussions method of RetrieveLatest class to search thesession.org
 * API for a discussion based on a set of search terms, and store the results.
 * 
 * This class does not test the iteration through multiple pages of JSON search results
 * 
 * @author Colman
 * @since 2017-08-13
 */

class Test_RetrieveMemberContributions_Discussions

{
  public static void main(String[] args) throws URISyntaxException {
    // Set the search parameters
    int resultsPerPage = 50;
    int userID = 1;

    // Instantiate a RetrieveLatest object
    MemberContributionSearch search = new MemberContributionSearch(userID, resultsPerPage);

    try {
      // Pass in the search parameters
      ArrayList<SearchResultDiscussions> resultSet = search.listDiscussions();

      // Loop through the results and print each attribute of each individual result in the set
      for (int i = 0; i < resultSet.size(); i++) {
        System.out.println("Discussion ID: " + resultSet.get(i).discussionDetails.discussionID);
        System.out
            .println("Discussion Title: " + resultSet.get(i).discussionDetails.discussionName);
        System.out.println("Date Submitted: " + resultSet.get(i).discussionDetails.submittedDate);
        System.out.println("Discussion URL: " + resultSet.get(i).discussionDetails.discussionURL);
        /*
         * The comment count is not returned when searching by member contribution
         * System.out.println(resultSet.get(i).details.numberOfComments);
         */

        System.out.println("User ID: " + resultSet.get(i).user.userID);
        System.out.println("Username: " + resultSet.get(i).user.userName);
        System.out.println("User Profile Page: " + resultSet.get(i).user.userURL);

        System.out.println("\n");
      }
    }

    catch (IllegalArgumentException | IOException e)
    // Catch any cases where an invalid number of results per page has been specified
    {
      System.out.println(e.getMessage());
    }
  }
}
