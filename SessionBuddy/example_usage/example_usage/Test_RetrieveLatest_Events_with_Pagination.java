package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import sessionbuddy.LatestSearch;
import sessionbuddy.wrappers.resultsets.SearchResultEvents;


class Test_RetrieveLatest_Events_with_Pagination {
  public static void main(String[] args) throws URISyntaxException {

    try {
      // Set the search parameters
      int resultsPerPage = 2;
      int pageNumber = 2;

      // Instantiate a LatestSearch object
      LatestSearch search = new LatestSearch(resultsPerPage, pageNumber);

      // Call the listEvents() method on the LatestSearch object
      ArrayList<SearchResultEvents> resultSet = search.listEvents();

      // Loop through the results and print attributes of each individual result in the set
      for (int i = 0; i < resultSet.size(); i++) {
        System.out.println("Event ID: " + resultSet.get(i).eventDetails.eventID);
        System.out.println("Event Name: " + resultSet.get(i).eventDetails.eventName);
        System.out.println("Event URL: " + resultSet.get(i).eventDetails.eventURL);
        System.out.println("Date Submitted: " + resultSet.get(i).eventDetails.submittedDate);

        System.out.println("Event Start Date: " + resultSet.get(i).schedule.startDate);
        System.out.println("Event End Date: " + resultSet.get(i).schedule.endDate);

        System.out.println("Submitter User ID: " + resultSet.get(i).user.userID);
        System.out.println("Submitter Username: " + resultSet.get(i).user.userName);
        System.out.println("Submitter Profile Page: " + resultSet.get(i).user.userURL);

        System.out.println("Latitude: " + resultSet.get(i).coordinates.latitude);
        System.out.println("Longitude: " + resultSet.get(i).coordinates.longitude);

        System.out.println("Venue Name: " + resultSet.get(i).venue.venueName);
        System.out.println("Venue Email: " + resultSet.get(i).venue.venueEmail);
        System.out.println("Venue Phone No.: " + resultSet.get(i).venue.venuePhone);
        System.out.println("Venue Website: " + resultSet.get(i).venue.venueWebsite);

        System.out.println("Town: " + resultSet.get(i).town.townName);
        System.out.println("Area: " + resultSet.get(i).area.areaName);
        System.out.println("Country: " + resultSet.get(i).country.countryName);
        System.out.println("\n");
      }
    }

    catch (IllegalArgumentException | IllegalStateException | IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
