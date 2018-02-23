package example_usage;

import java.io.IOException;
import java.util.ArrayList;

import sessionbuddy.MemberContributionSearch;
import sessionbuddy.wrappers.resultsets.SearchResultsDiscussions;

/**
 * Example usage of the searchDiscussions method of RetrieveLatest class to search thesession.org API for a discussion based on a set of search terms, and store the results.
 * 
 * This class does not test the iteration through multiple pages of JSON search results
 * 
 * @author Colman
 * @since 2017-08-13
 */

class Test_RetrieveMemberContributions_Discussions

{
public static void main(String[] args)
   {
	// Set the search parameters
	int resultsPerPage = 50;
	int userID = 1;
	ArrayList<SearchResultsDiscussions> resultSet;
	
	// Instantiate a RetrieveLatest object
	MemberContributionSearch search = new MemberContributionSearch();
	
	try
		{
		// Pass in the search parameters
		resultSet = search.getDiscussions(resultsPerPage, userID);
		
		// Loop through the results and print each attribute of each individual result in the set
		for (int i = 0; i < resultSet.size(); i++)
			{
			System.out.println("Discussion ID: " + resultSet.get(i).details.discussionID);
			System.out.println("Discussion Title: " + resultSet.get(i).details.discussionName);
			System.out.println("Date Submitted: " + resultSet.get(i).details.submittedDate);
			System.out.println("Discussion URL: " + resultSet.get(i).details.discussionURL);	
			/* The comment count is not returned when searching by member contribution
			System.out.println(resultSet.get(i).details.numberOfComments);
			*/	
					
			System.out.println("User ID: " + resultSet.get(i).user.userID);
			System.out.println("Username: " + resultSet.get(i).user.userName);
			System.out.println("User Profile Page: " + resultSet.get(i).user.userURL);
			
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
