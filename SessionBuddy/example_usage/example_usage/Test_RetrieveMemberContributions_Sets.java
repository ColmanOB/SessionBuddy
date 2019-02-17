package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.MemberContribution;
import sessionbuddy.wrappers.resultsets.RecentResultSets;

class Test_RetrieveMemberContributions_Sets
{
    public static void main(String[] args) throws URISyntaxException
    {
        try
        {
            // Set the search parameters
            int userID = 1;
            int resultsPerPage = 50;

            // Perform the search by calling the listSets method
            RecentResultSets resultSet = MemberContribution.listSets(userID, resultsPerPage);

            // Loop through the results and print each attribute of each individual result in the set
            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                System.out.println("Date: " + resultSet.searchResults.get(i).setDetails.setID);
                System.out.println("Date: " + resultSet.searchResults.get(i).setDetails.setName);
                System.out.println("Date: " + resultSet.searchResults.get(i).setDetails.setURL);
                System.out.println("Date: " + resultSet.searchResults.get(i).setDetails.setDate);

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
