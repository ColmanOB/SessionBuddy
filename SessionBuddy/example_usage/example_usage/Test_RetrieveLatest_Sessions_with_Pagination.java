package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import sessionbuddy.LatestSearch;
import sessionbuddy.wrappers.individualresults.SearchResultSessions;


class Test_RetrieveLatest_Sessions_with_Pagination {
  public static void main(String[] args) throws URISyntaxException {
    try {
      // Specify the number of results to be returned per page
      int resultsPerPage = 2;
      int pageNumber = 2;

      // Instantiate a LatestSearch object
      LatestSearch search = new LatestSearch(resultsPerPage, pageNumber);

      // Perform the search by calling the getLatestSessions method of the LatestSearch object
      ArrayList<SearchResultSessions> resultSet = search.listSessions();

      // Loop through the results and print each attribute of each individual result in the set
      for (int i = 0; i < resultSet.size(); i++) {
        System.out.println("Session ID: " + resultSet.get(i).sessionDetails.sessionID);
        System.out.println("Session URL: " + resultSet.get(i).sessionDetails.sessionURL);
        System.out.println("Date Submitted: " + resultSet.get(i).sessionDetails.submittedDate);

        System.out.println("Submitter User ID: " + resultSet.get(i).user.userID);
        System.out.println("Submitter Username: " + resultSet.get(i).user.userName);
        System.out.println("Submitter Profile Page: " + resultSet.get(i).user.userURL);

        System.out.println("Latitude: " + resultSet.get(i).coordinates.latitude);
        System.out.println("Longitude: " + resultSet.get(i).coordinates.longitude);

        System.out.println("Venue Name:" + resultSet.get(i).venue.venueName);
        System.out.println("Venue Email:" + resultSet.get(i).venue.venueEmail);
        System.out.println("Venue Phone No.:" + resultSet.get(i).venue.venuePhone);
        System.out.println("Venue Website:" + resultSet.get(i).venue.venueWebsite);

        System.out.println("\n");
      }
    }

    catch (IllegalArgumentException | IllegalStateException | IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
