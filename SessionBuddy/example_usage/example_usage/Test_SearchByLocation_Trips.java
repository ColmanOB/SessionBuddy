package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.Nearby;
import sessionbuddy.wrappers.resultsets.LocationResultTrips;

public class Test_SearchByLocation_Trips
{
    public static void main(String[] args) throws RuntimeException, URISyntaxException
    {
        // Set the search parameters
        String latitude = "53.3498";
        String longitude = "6.2603";
        String radius = "500";
        int resultsPerPage = 50;

        try
        {
            // Perform the search by calling the searchTrips method
            LocationResultTrips resultSet = Nearby.searchTrips(latitude, longitude, radius, resultsPerPage);

            // Loop through the results and print each attribute of each individual result in the set
            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                System.out.println("ID: "
                        + resultSet.searchResults.get(i).tripDetails.tripID);
                System.out.println("Name: "
                        + resultSet.searchResults.get(i).tripDetails.tripName);
                System.out.println("URL: "
                        + resultSet.searchResults.get(i).tripDetails.tripURL);
                System.out.println("Submitted: " + resultSet.searchResults
                        .get(i).tripDetails.submittedDate);

                System.out.println("Start Date: " + resultSet.searchResults
                        .get(i).tripSchedule.startDate);
                System.out.println("End Date: "
                        + resultSet.searchResults.get(i).tripSchedule.endDate);

                System.out.println("User ID: "
                        + resultSet.searchResults.get(i).submitter.userID);
                System.out.println("User Name: "
                        + resultSet.searchResults.get(i).submitter.userName);
                System.out.println("User Profile Page: "
                        + resultSet.searchResults.get(i).submitter.userURL);

                System.out.println("\n");
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
