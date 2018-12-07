package example_usage;

import java.io.IOException;
import java.util.ArrayList;
import sessionbuddy.KeywordSearch;
import sessionbuddy.wrappers.resultsets.SearchResultDiscussions;

/**
 * Example usage of the searchDiscussions method of KeywordSearch class to search thesession.org API
 * for a discussion based on a set of search terms, and store the results.
 * 
 * This class does not test the iteration through multiple pages of JSON search results
 * 
 * @author Colman
 * @since 2017-08-13
 */

public class Test_KeywordSearch_Discussions_with_Pagination {

  public static void main(String[] args) throws Exception {
    // Set the search parameters
    String searchTerms = "Wig glue";
    int resultsPerPage = 40;
    int pageNumber = 1;

    try {
      // Instantiate a KeywordSearch object
      KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage, pageNumber);

      // Invoke the searchDiscussions method on the KeywordSearch object
      ArrayList<SearchResultDiscussions> resultSet = search.searchDiscussions();

      if (resultSet.size() == 0) {
        System.out.println("No results retrieved");
      }

      // Loop through the results and print each attribute of each individual result in the set
      for (int i = 0; i < resultSet.size(); i++) {
        System.out.println("Discussion ID: " + resultSet.get(i).discussionDetails.discussionID);
        System.out
            .println("Discussion Title: " + resultSet.get(i).discussionDetails.discussionName);
        System.out.println("Date Submitted: " + resultSet.get(i).discussionDetails.submittedDate);
        System.out.println("Discussion URL: " + resultSet.get(i).discussionDetails.discussionURL);
        System.out
            .println("No. of comments: " + resultSet.get(i).discussionDetails.numberOfComments);

        System.out.println("Submitter User ID: " + resultSet.get(i).user.userID);
        System.out.println("Submitter Username: " + resultSet.get(i).user.userName);
        System.out.println("Submitter Profile Page: " + resultSet.get(i).user.userURL);
        System.out.println("\n");
      }
    }

    catch (IllegalArgumentException | IOException ex) {
      throw ex;
    }
  }
}
