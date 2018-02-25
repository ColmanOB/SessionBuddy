package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import sessionbuddy.LatestSearch;
import sessionbuddy.wrappers.resultsets.SearchResultsDiscussions;

/**
 * Example usage of the searchDiscussions method of RetrieveLatest class to search thesession.org API for a discussion based on a set of search terms, and store the results.
 * 
 * This class does not test the iteration through multiple pages of JSON search results
 * 
 * @author Colman
 * @since 2017-08-13
 */

class Test_RetrieveLatest_Discussions_with_Pagination

{
public static void main(String[] args) throws URISyntaxException
   {
	// Set the search parameters
	int resultsPerPage = 2;
	ArrayList<SearchResultsDiscussions> resultSet;
	int pageNumber = 2;
	
	// Instantiate a RetrieveLatest object
	LatestSearch search = new LatestSearch();
	
	try
		{
		// Pass in the search parameters
		resultSet = search.getLatestDiscussions(resultsPerPage, pageNumber);
		
		// Loop through the results and print each attribute of each individual result in the set
		for (int i = 0; i < resultSet.size(); i++)
			{
			System.out.println(resultSet.get(i).details.discussionID);
			System.out.println(resultSet.get(i).details.discussionName);
			System.out.println(resultSet.get(i).details.submittedDate);
			System.out.println(resultSet.get(i).details.discussionURL);	
			System.out.println(resultSet.get(i).details.numberOfComments);	
					
			System.out.println(resultSet.get(i).user.userID);
			System.out.println(resultSet.get(i).user.userName);
			System.out.println(resultSet.get(i).user.userURL);
			
			System.out.println("\n");
			}
		}
	
	catch (IllegalArgumentException e)
		// Catch any cases where an invalid number of results per page has been specified
		{
		System.out.println(e.getMessage());
		}	

	catch (IOException e)
		{
		System.out.println(e.getMessage());
		}
   	}
}
