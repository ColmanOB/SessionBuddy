package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.Nearby;
import sessionbuddy.wrappers.resultsets.LocationResultSessions;

public class Test_SearchByLocation_Sessions
{
    public static void main(String[] args) throws RuntimeException, URISyntaxException
    {
        // Set the search parameters
        String latitude = "53.3498";
        String longitude = "6.2603";
        String radius = "75";
        int resultsPerPage = 50;

        try
        {
            // Call the searchSessions method on the Nearby object
            LocationResultSessions resultSet = Nearby.searchSessions(latitude, longitude, radius, resultsPerPage);

            // Loop through the results and print each attribute of each
            // individual result in the set
            for (int i = 0; i < resultSet.results.size(); i++)
            {
                System.out.println(resultSet.results.get(i).sessionDetails.sessionID);
                System.out.println(resultSet.results.get(i).sessionDetails.sessionURL);
                System.out.println(resultSet.results.get(i).sessionDetails.submittedDate);

                System.out.println(resultSet.results.get(i).user.userID);
                System.out.println(resultSet.results.get(i).user.userName);
                System.out.println(resultSet.results.get(i).user.userURL);

                System.out.println("\n");
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
