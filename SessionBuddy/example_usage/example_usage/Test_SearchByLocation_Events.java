package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.Nearby;
import sessionbuddy.wrappers.resultsets.LocationResultEvents;

public class Test_SearchByLocation_Events
{
    public static void main(String[] args)
            throws RuntimeException, URISyntaxException
    {
        // Set the search parameters
        String latitude = "53.3498";
        String longitude = "6.2603";
        String radius = "500";
        int resultsPerPage = 50;

        try
        {

            // Call the searchEvents method
            LocationResultEvents resultSet = Nearby.searchEvents(latitude, longitude, radius, resultsPerPage);

            // Loop through the results and print each attribute of each individual result in the set
            for (int i = 0; i < resultSet.results.size(); i++)
            {
                System.out.println(resultSet.results.get(i).eventDetails.eventID);
                System.out.println(resultSet.results.get(i).eventDetails.eventName);
                System.out.println(resultSet.results.get(i).eventDetails.eventURL);

                System.out.println(resultSet.results.get(i).user.userID);
                System.out.println(resultSet.results.get(i).user.userName);
                System.out.println(resultSet.results.get(i).user.userURL);

                System.out.println(resultSet.results.get(i).venue.venueID);
                System.out.println(resultSet.results.get(i).venue.venueName);
                System.out.println(resultSet.results.get(i).venue.venueEmail);
                System.out.println(resultSet.results.get(i).venue.venuePhone);

                System.out.println(resultSet.results.get(i).town.townName);

                System.out.println(resultSet.results.get(i).country.countryName);

                System.out.println("\n");
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException e)
        {
            e.printStackTrace();
        }

    }
}
