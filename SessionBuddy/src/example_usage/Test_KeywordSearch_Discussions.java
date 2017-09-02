package example_usage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import json_object_wrappers.DiscussionsSearchResult;
import main.KeywordSearch;

/**
 * Example usage of the searchDiscussions method of KeywordSearch class to search thesession.org API for a discussion based on a set of search terms, and store the results.
 * 
 * This class does not test the iteration through multiple pages of JSON search results
 * 
 * @author Colman
 * @since 2017-08-13
 */

public class Test_KeywordSearch_Discussions

{
public static void main(String[] args) throws MalformedURLException, IOException
   {
	// Set the search parameters
	String searchTerms = "London";
	int resultsPerPage = 50;
	ArrayList<DiscussionsSearchResult> resultSet;
	
	// Instantiate a KeywordSearch object
	KeywordSearch search = new KeywordSearch();
	
	try
		{
		// Pass in the search parameters
		resultSet = search.searchDiscussions(searchTerms, resultsPerPage);
		
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

   	}
}