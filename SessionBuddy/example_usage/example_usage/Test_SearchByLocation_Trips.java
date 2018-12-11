package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import sessionbuddy.LocationSearch;
import sessionbuddy.wrappers.resultsets.SearchResultTrips;

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
        LocationSearch search = new LocationSearch(latitude, longitude, radius, resultsPerPage);
        // Perform the search by calling the searchTrips method
        ArrayList<SearchResultTrips> resultSet = search.searchTrips();

        // Loop through the results and print each attribute of each individual result in the set
        for (int i = 0; i < resultSet.size(); i++)
        {
            System.out.println("ID: " + resultSet.get(i).tripDetails.tripID);
            System.out.println("Name: " + resultSet.get(i).tripDetails.tripName);
            System.out.println("URL: " + resultSet.get(i).tripDetails.tripURL);
            System.out.println("Submitted: " + resultSet.get(i).tripDetails.submittedDate);
            System.out.println("Start Date: " + resultSet.get(i).tripDetails.tripStartDate);
            System.out.println("End Date: " + resultSet.get(i).tripDetails.tripEndDate);

            System.out.println("User ID: " + resultSet.get(i).submitter.userID);
            System.out.println("User Name: " + resultSet.get(i).submitter.userName);
            System.out.println("User Profile Page: " + resultSet.get(i).submitter.userURL);

            System.out.println("\n");
        }
    }

    catch(IllegalArgumentException|IllegalStateException|

    IOException e)
    {
        e.printStackTrace();
    }
  }
}
