package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.KeywordSearch;
import sessionbuddy.wrappers.resultsets.SearchResultSessions;

public class Test_KeywordSearch_Sessions_with_Pagination
{

    public static void main(String[] args) throws URISyntaxException
    {
        // Set the search parameters
        String searchTerms = "London";
        int resultsPerPage = 2;
        int pageNumber = 2;

        try
        {
            // Perform the search by calling the searchSessions method and passing in the search parameters
            SearchResultSessions resultSet = KeywordSearch.searchSessions(searchTerms, resultsPerPage, pageNumber);

            // Loop through the results and print each attribute of each
            // individual result in the set
            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                System.out.println("Session ID: " + resultSet.searchResults.get(i).sessionDetails.sessionID);
                System.out.println("Session URL: " + resultSet.searchResults.get(i).sessionDetails.sessionURL);
                System.out.println("Submitted Date: " + resultSet.searchResults.get(i).sessionDetails.submittedDate);

                System.out.println("Venue ID: " + resultSet.searchResults.get(i).venue.venueID);
                System.out.println("Venue Name: " + resultSet.searchResults.get(i).venue.venueName);
                System.out.println("Venue Email: " + resultSet.searchResults.get(i).venue.venueEmail);
                System.out.println("Venue Phone No.: " + resultSet.searchResults.get(i).venue.venuePhone);
                System.out.println("Venue Website: " + resultSet.searchResults.get(i).venue.venueWebsite);

                System.out.println("Submitter User ID: " + resultSet.searchResults.get(i).user.userID);
                System.out.println("Submitter User Name: " + resultSet.searchResults.get(i).user.userName);
                System.out.println("Submitter Profile Page: " + resultSet.searchResults.get(i).user.userURL);

                System.out.println("Latitude: " + resultSet.searchResults.get(i).coordinates.latitude);
                System.out.println("Longitude: " + resultSet.searchResults.get(i).coordinates.longitude);

                System.out.println("Town ID: " + resultSet.searchResults.get(i).town.townID);
                System.out.println("Town Name: " + resultSet.searchResults.get(i).town.townName);

                System.out.println("Area ID: " + resultSet.searchResults.get(i).area.areaID);
                System.out.println("Area Name: " + resultSet.searchResults.get(i).area.areaName);

                System.out.println("Area ID: " + resultSet.searchResults.get(i).country.countryID);
                System.out.println("Area Name: " + resultSet.searchResults.get(i).country.countryName);

                System.out.println("\n");
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException e)
        {
            e.printStackTrace();
        }
        
    }
}
