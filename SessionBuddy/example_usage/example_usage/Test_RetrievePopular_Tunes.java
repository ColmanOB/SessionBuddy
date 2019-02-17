package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.PopularSearch;
import sessionbuddy.wrappers.resultsets.PopularResultTunes;

class Test_RetrievePopular_Tunes
{
    public static void main(String[] args) throws URISyntaxException
    {
        try
        {
            // Set the search parameters
            int resultsPerPage = 50;
            int pageNumber = 2;

            // Perform the search by calling the listTunes
            PopularResultTunes resultSet = PopularSearch.listTunes(resultsPerPage, pageNumber);

            // Loop through the results and print each attribute of each individual result in the set
            for (int i = 0; i < resultSet.searchResults.size(); i++)
            {
                System.out.println("Tune ID: " + resultSet.searchResults.get(i).tuneDetails.generalTuneDetails.basicTuneDetails.tuneID);
                System.out.println("Tune Name: " + resultSet.searchResults.get(i).tuneDetails.generalTuneDetails.basicTuneDetails.tuneName);
                System.out.println("Tune URL: " + resultSet.searchResults.get(i).tuneDetails.generalTuneDetails.basicTuneDetails.tuneURL);
                System.out.println("No. of Tunebooks: " + resultSet.searchResults.get(i).tuneDetails.tunebooks);
                System.out.println("Date Submitted: " + resultSet.searchResults.get(i).tuneDetails.generalTuneDetails.submittedDate);
                System.out.println("Tune Type: " + resultSet.searchResults.get(i).tuneDetails.generalTuneDetails.tuneType);

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
