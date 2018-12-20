package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import sessionbuddy.KeywordSearch;
import sessionbuddy.wrappers.individualresults.SearchResultRecordings;


public class Test_KeywordSearch_Recordings_with_Pagination {

  public static void main(String[] args) throws URISyntaxException {
    // Set the search parameters
    String searchTerms = "humours";
    int resultsPerPage = 2;
    int pageNumber = 3;

    // Instantiate a KeywordSearch object
    KeywordSearch search = new KeywordSearch(searchTerms, resultsPerPage, pageNumber);

    // Create a structure to hold the response from the API
    ArrayList<SearchResultRecordings> resultSet;

    try {
      // Call the searchRecordings method of the KeywordSearch object
      resultSet = search.searchRecordings();

      // Loop through the results and print each attribute of each individual result in the set
      for (int i = 0; i < resultSet.size(); i++) {
        System.out.println("Recording ID: " + resultSet.get(i).recordingDetails.recordingID);
        System.out.println("Recording Name: " + resultSet.get(i).recordingDetails.recordingName);
        System.out.println("Recording URL: " + resultSet.get(i).recordingDetails.recordingURL);

        System.out.println("Artist ID: " + resultSet.get(i).artist.artistID);
        System.out.println("Artist Name: " + resultSet.get(i).artist.artistName);
        System.out.println("Artist Page URL: " + resultSet.get(i).artist.artistPageURL);

        System.out.println("Submitter User ID: " + resultSet.get(i).submitter.userID);
        System.out.println("Submitter Username: " + resultSet.get(i).submitter.userName);
        System.out.println("Submitter Profile Page: " + resultSet.get(i).submitter.userURL);
        System.out.println("\n");
      }
    }

    catch (IllegalArgumentException e) {
      e.printStackTrace();
    }

    catch (IllegalStateException e) {
      e.printStackTrace();
    }

    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
