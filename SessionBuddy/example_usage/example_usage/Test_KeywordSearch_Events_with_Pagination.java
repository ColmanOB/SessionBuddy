package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.KeywordSearch;
import sessionbuddy.wrappers.resultsets.SearchResultEvents;

public class Test_KeywordSearch_Events_with_Pagination
{
    public static void main(String[] args) throws URISyntaxException
    {
        // Set the search parameters
        String searchTerms = "Dublin";
        int resultsPerPage = 40;
        int pageNumber = 1;

        try
        {
            // Create a structure to hold the response from the API
            SearchResultEvents resultSet = KeywordSearch.searchEvents(searchTerms, resultsPerPage, pageNumber);

            // Print out some of the metadata
            System.out.println("The number of pages in the response: " + resultSet.responseHeaders.pages);
            System.out.println("The total number of results in the response: " + resultSet.responseHeaders.total);
            System.out.println("This is page number: " + resultSet.responseHeaders.page + " of the response\n");

            // Loop through the results and print each attribute of each
            // individual result in the set
            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                System.out.println("Event ID: " + resultSet.searchResults.get(i).eventDetails.eventID);
                System.out.println("Event Name: " + resultSet.searchResults.get(i).eventDetails.eventName);
                System.out.println("Date Submitted: " + resultSet.searchResults.get(i).eventDetails.submittedDate);
                System.out.println("Event URL: " + resultSet.searchResults.get(i).eventDetails.eventURL);

                System.out.println("Venue ID: " + resultSet.searchResults.get(i).venue.venueID);
                System.out.println("Venue Name: " + resultSet.searchResults.get(i).venue.venueName);
                System.out.println("Venue Email: " + resultSet.searchResults.get(i).venue.venueEmail);
                System.out.println("Venue Phone Number: " + resultSet.searchResults.get(i).venue.venuePhone);
                System.out.println("Venue Website: " + resultSet.searchResults.get(i).venue.venueWebsite);

                System.out.println("Town/City ID: " + resultSet.searchResults.get(i).town.townID);
                System.out.println("Town/City Name: " + resultSet.searchResults.get(i).town.townName);

                System.out.println("Area ID: " + resultSet.searchResults.get(i).area.areaID);
                System.out.println("Area Name: " + resultSet.searchResults.get(i).area.areaName);

                System.out.println("Country ID: " + resultSet.searchResults.get(i).country.countryID);
                System.out.println("Country Name: " + resultSet.searchResults.get(i).country.countryName);

                System.out.println("Latitude: " + resultSet.searchResults.get(i).coordinates.latitude);
                System.out.println("Longitude: " + resultSet.searchResults.get(i).coordinates.longitude);

                System.out.println("Event Start Date: " + resultSet.searchResults.get(i).schedule.startDate);
                System.out.println("Event End Date: " + resultSet.searchResults.get(i).schedule.endDate);

                System.out.println("Submitter User ID: " + resultSet.searchResults.get(i).user.userID);
                System.out.println("Submitter Username: " + resultSet.searchResults.get(i).user.userName);
                System.out.println("Submitter Profile Page: " + resultSet.searchResults.get(i).user.userURL);

                System.out.println("\n");
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
