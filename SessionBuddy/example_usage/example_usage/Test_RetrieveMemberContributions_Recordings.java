package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import sessionbuddy.MemberContributionSearch;
import sessionbuddy.wrappers.resultsets.SearchResultRecordings;


class Test_RetrieveMemberContributions_Recordings {
  public static void main(String[] args) throws URISyntaxException {
    try {
      // Set the number of results to be returned per page
      int resultsPerPage = 50;
      int userID = 1;

      // Instantiate a MemberContributionSearch object
      MemberContributionSearch search = new MemberContributionSearch(userID, resultsPerPage);

      // Call the listRecordings method on the MemberContributionSearch object
      ArrayList<SearchResultRecordings> resultSet = search.listRecordings();

      // Loop through the results and print each attribute of each individual result in the set
      for (int i = 0; i < resultSet.size(); i++) {
        System.out.println("Recording ID: " + resultSet.get(i).recordingDetails.recordingID);
        System.out.println("Recording Name: " + resultSet.get(i).recordingDetails.recordingName);
        System.out.println("Submission Date: " + resultSet.get(i).recordingDetails.recordingDate);
        System.out.println("Recurding URL: " + resultSet.get(i).recordingDetails.recordingURL);

        System.out.println("Submitter User ID: " + resultSet.get(i).submitter.userID);
        System.out.println("Submitter Username: " + resultSet.get(i).submitter.userName);
        System.out.println("Submitter Profile Page: " + resultSet.get(i).submitter.userURL);

        System.out.println("Artist ID: " + resultSet.get(i).artist.artistID);
        System.out.println("Artist Name: " + resultSet.get(i).artist.artistName);
        System.out.println("Artist Page URL: " + resultSet.get(i).artist.artistPageURL);

        System.out.println("\n");
      }
    }

    catch (IllegalArgumentException | IllegalStateException | IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
