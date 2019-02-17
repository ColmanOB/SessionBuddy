package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.Recent;
import sessionbuddy.wrappers.resultsets.RecentResultTrips;

class Test_RetrieveLatest_Trips
{
    public static void main(String[] args) throws URISyntaxException
    {
        try
        {
            // Set the search parameters
            int resultsPerPage = 50;
            
            // Perform the search by calling listTrips() method on the Recent object
           RecentResultTrips resultSet = Recent.listTrips(resultsPerPage);

            // Loop through the results and print each attribute of each individual result in the set
            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                System.out.println("ID: " + resultSet.searchResults.get(i).tripDetails.tripID);
                System.out.println("Name: " + resultSet.searchResults.get(i).tripDetails.tripName);
                System.out.println("URL: " + resultSet.searchResults.get(i).tripDetails.tripURL);
                System.out.println("Submitted: " + resultSet.searchResults.get(i).tripDetails.submittedDate);
                
                System.out.println("Start Date: " + resultSet.searchResults.get(i).tripSchedule.startDate);
                System.out.println("End Date: " + resultSet.searchResults.get(i).tripSchedule.endDate);

                System.out.println("User ID: " + resultSet.searchResults.get(i).submitter.userID);
                System.out.println("User Name: " + resultSet.searchResults.get(i).submitter.userName);
                System.out.println("User Profile Page: " + resultSet.searchResults.get(i).submitter.userURL);

                System.out.println("\n");
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
