package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.MemberContribution;
import sessionbuddy.wrappers.resultsets.SearchResultEventsLatest;

class Test_RetrieveMemberContributions_Events
{
    public static void main(String[] args) throws URISyntaxException
    {
        try
        {
            // Set the search parameters
            int resultsPerPage = 50;
            int userID = 1;

            // Call the listEvents() method
            SearchResultEventsLatest resultSet = MemberContribution.listEvents(userID, resultsPerPage);

            // Loop through the results and print attributes of each individual result in the set
            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                System.out.println("Event ID: " + resultSet.searchResults.get(i).eventDetails.eventID);
                System.out.println("Event Name: " + resultSet.searchResults.get(i).eventDetails.eventName);
                System.out.println("Event URL: " + resultSet.searchResults.get(i).eventDetails.eventURL);
                System.out.println("Date Submitted: " + resultSet.searchResults.get(i).eventDetails.submittedDate);

                System.out.println("Event Start Date: " + resultSet.searchResults.get(i).schedule.startDate);
                System.out.println("Event End Date: " + resultSet.searchResults.get(i).schedule.endDate);

                System.out.println("Submitter User ID: " + resultSet.searchResults.get(i).user.userID);
                System.out.println("Submitter Username: " + resultSet.searchResults.get(i).user.userName);
                System.out.println("Submitter Profile Page: " + resultSet.searchResults.get(i).user.userURL);

                System.out.println("Latitude: " + resultSet.searchResults.get(i).coordinates.latitude);
                System.out.println("Longitude: " + resultSet.searchResults.get(i).coordinates.longitude);

                System.out.println("Venue Name: " + resultSet.searchResults.get(i).venue.venueName);
                System.out.println("Venue Email: " + resultSet.searchResults.get(i).venue.venueEmail);
                System.out.println("Venue Phone No.: " + resultSet.searchResults.get(i).venue.venuePhone);
                System.out.println("Venue Website: " + resultSet.searchResults.get(i).venue.venueWebsite);

                System.out.println("Town: " + resultSet.searchResults.get(i).town.townName);
                System.out.println("Area: " + resultSet.searchResults.get(i).area.areaName);
                System.out.println("Country: " + resultSet.searchResults.get(i).country.countryName);
                System.out.println("\n");
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
