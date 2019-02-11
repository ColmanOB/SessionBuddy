package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.SetSearch;
import sessionbuddy.wrappers.resultsets.SearchResultSetsLatest;

class Test_RetrieveLatest_Sets
{
    public static void main(String[] args) throws URISyntaxException
    {
        try
        {
            // Set the search parameters
            int resultsPerPage = 50;

            // Perform the search by calling the listSets method on SetSearch
            SearchResultSetsLatest resultSet = SetSearch.listSets(resultsPerPage);

            // Loop through the results and print each attribute of each
            // individual result in the set
            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                System.out.println("Set ID: "
                        + resultSet.searchResults.get(i).setDetails.setID);
                System.out.println("Set name: "
                        + resultSet.searchResults.get(i).setDetails.setName);
                System.out.println("Date Added: "
                        + resultSet.searchResults.get(i).setDetails.setDate);
                System.out.println("URL: "
                        + resultSet.searchResults.get(i).setDetails.setURL);

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
            System.out.println(e.getMessage());
        }
    }
}
