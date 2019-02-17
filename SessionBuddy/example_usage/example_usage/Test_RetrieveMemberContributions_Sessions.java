package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.MemberContribution;
import sessionbuddy.wrappers.resultsets.RecentResultSessions;


class Test_RetrieveMemberContributions_Sessions {
  public static void main(String[] args) throws URISyntaxException {
    try {
      // Specify the number of results to be returned per page
      int resultsPerPage = 50;
      int userID = 1;

      // Perform the search by calling the listtSessions method of the MemberContribution
      // object
      RecentResultSessions resultSet = MemberContribution.listSessions(userID, resultsPerPage);

      // Loop through the results and print each attribute of each individual result in the set
      for (int i = 0; i < resultSet.searchResults.size(); i++) {
        System.out.println("Session ID: " + resultSet.searchResults.get(i).sessionDetails.sessionID);
        System.out.println("Session URL: " + resultSet.searchResults.get(i).sessionDetails.sessionURL);
        System.out.println("Date Submitted: " + resultSet.searchResults.get(i).sessionDetails.submittedDate);

        System.out.println("Submitter User ID: " + resultSet.searchResults.get(i).user.userID);
        System.out.println("Submitter Username: " + resultSet.searchResults.get(i).user.userName);
        System.out.println("Submitter Profile Page: " + resultSet.searchResults.get(i).user.userURL);

        System.out.println("Latitude: " + resultSet.searchResults.get(i).coordinates.latitude);
        System.out.println("Longitude: " + resultSet.searchResults.get(i).coordinates.longitude);

        System.out.println("Venue Name:" + resultSet.searchResults.get(i).venue.venueName);
        System.out.println("Venue Email:" + resultSet.searchResults.get(i).venue.venueEmail);
        System.out.println("Venue Phone No.:" + resultSet.searchResults.get(i).venue.venuePhone);
        System.out.println("Venue Website:" + resultSet.searchResults.get(i).venue.venueWebsite);

        System.out.println("\n");
      }
    }

    catch (IllegalArgumentException | IllegalStateException | IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
