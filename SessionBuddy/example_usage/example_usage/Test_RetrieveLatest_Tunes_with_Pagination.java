package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.Recent;
import sessionbuddy.wrappers.resultsets.RecentResultTunes;

class Test_RetrieveLatest_Tunes_with_Pagination {
  public static void main(String[] args) {
    try {
      // Set the search parameters
      int resultsPerPage = 50;
      int pageNumber = 2;

      // Perform the search by calling the listTunes method on the Recent object
      RecentResultTunes resultSet = Recent.listTunes(resultsPerPage, pageNumber);

      // Loop through the results and print each attribute of each individual result in the set
      for (int i = 0; i < resultSet.searchResults.size(); i++) {
        System.out.println("Tune ID: " + resultSet.searchResults.get(i).settingDetails.settingID);
        System.out.println("Tune URL: " + resultSet.searchResults.get(i).settingDetails.settingURL);
        System.out.println("Key: " + resultSet.searchResults.get(i).settingDetails.key);

        System.out.println("Setting ID: " + resultSet.searchResults.get(i).tuneDetails.tuneID);
        System.out.println("Setting Name: " + resultSet.searchResults.get(i).tuneDetails.tuneName);
        System.out.println("Setting URL: " + resultSet.searchResults.get(i).tuneDetails.tuneURL);

        System.out.println("Date: " + resultSet.searchResults.get(i).settingDetails.date);

        System.out.println("User ID: " + resultSet.searchResults.get(i).user.userID);
        System.out.println("User Name: " + resultSet.searchResults.get(i).user.userName);
        System.out.println("User Profile Page: " + resultSet.searchResults.get(i).user.userURL);

        System.out.println("\n");
      }
    }

    catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e) {
      System.out.println(e.getMessage());
    }
  }
}
