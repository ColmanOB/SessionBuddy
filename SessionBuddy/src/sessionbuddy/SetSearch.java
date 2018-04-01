package sessionbuddy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import sessionbuddy.utils.HttpRequestor;
import sessionbuddy.utils.JsonParser;
import sessionbuddy.utils.StringCleaner;
import sessionbuddy.utils.UrlBuilder;
import sessionbuddy.wrappers.granularobjects.LatestSetDetails;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperSets;
import sessionbuddy.wrappers.resultsets.LatestSearchSets;


/**
 * Retrieves a list of user-added sets of tunes
 * 
 * @author Colman O'B
 * @since 2018-04-01
 */
public class SetSearch extends Search 
	{
	/**
	 * The number of individual search results that should be returned per page in the JSON response from the API
	 */
	int resultsPerPage = 0;
	
	/**
	 * When dealing with a JSON response containing multiple pages, this specifies a particular page
	 */
	int pageNumber = 0;
	
	
	/**
	 * Constructor where pagination is not required and you only want to see the first page of the API response
	 * 
	 * @param resultsPerPage Specifies how many search results should appear in each page of the JSON response from the API
	 */
	public SetSearch(int resultsPerPage)
		{
		this.resultsPerPage = resultsPerPage;
		}
	
	
	/**
	 * Constructor for cases where you need to specify an individual page in the API response
	 * 
	 * @param resultsPerPage Specifies how many search results should appear in each page of the JSON response from the API
	 * @param pageNumber Specifies a particular page number within the JSON response
	 */
	public SetSearch(int resultsPerPage, int pageNumber)
		{
		this(resultsPerPage);
		this.pageNumber= pageNumber;
		}
		
	/**
	 * Retrieves a list of the most popular tunes on thesession.org, i.e. those that have been added to the most user tunebooks.
	 * 
	 * @return an ArrayList of LatestSearchSets objects
	 * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
	 * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
	 * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
	 * 
	 * @author Colman
	 * @since 2018-04-01
	 */
	public ArrayList<LatestSearchSets> listSets() throws IllegalArgumentException, IOException, URISyntaxException
		{
		try
			{
			// Validate that a number between 1-50 has been provided as the resultsPerPage value
			validateResultsPerPageCount(resultsPerPage);

			// Build the URL using a helper method and pass it in to the HttpRequstor.submitRequest(URL) method to call the API
			String response = HttpRequestor.submitRequest(composeURL());
							
			// Parse the returned JSON into a wrapper class to allow access to all elements
			LatestWrapperSets parsedResults = JsonParser.parseResponse(response, LatestWrapperSets.class);
									
			// Put the data from the wrapper object into an ArrayList of LatestSearchSets				
			return populateSetSearchResult(parsedResults);
			}
			
		catch (IllegalArgumentException | IOException | URISyntaxException ex)
			{
			throw ex;
			}
		}
			
	
	/**
	 * Helper method to gather and parse the response to a search for user-added sets of tunes
	 * 
	 * @param parsedResults a LatestWrapperSets object that has already been created and populated
	 * @return an ArrayList of LatestSearchSets objects
	 * 
	 * @author Colman
	 * @since 2018-02-17
	 */
	private ArrayList<LatestSearchSets> populateSetSearchResult(LatestWrapperSets parsedResults)
		{
		ArrayList <LatestSearchSets> resultSet = new ArrayList <LatestSearchSets>();
		
		//Find out how many pages are in the response, to facilitate looping through multiple pages
		pageCount = Integer.parseInt(parsedResults.pages);
			
		// Loop as many times as the count of tunes in the result set:
		for(int i = 0; i < parsedResults.sets.length; i++)
			{
			// Extract the required elements from each individual search result in the JSON response
			// StringCleaner.cleanString() will decode the &039; etc. XML entities from the JSON response
			LatestSetDetails details = new LatestSetDetails(parsedResults.sets[i].id, StringCleaner.cleanString(parsedResults.sets[i].name) , parsedResults.sets[i].url, parsedResults.sets[i].date);
			User submitter = new User(Integer.toString(parsedResults.sets[i].member.id), StringCleaner.cleanString(parsedResults.sets[i].member.name), parsedResults.sets[i].member.url);
			
			// Instantiate a LatestSearchSets object & populate it
			LatestSearchSets currentResult = new LatestSearchSets(details, submitter);
			
			// Add the LatestSearchSets object to the ArrayList to be returned to the caller
			resultSet.add(currentResult);
			}
		
		// Return the fully populated ArrayList
		return resultSet;
		}
	
	
	/**
	 * A helper method used to put the URL together to query the API at thesession.org
	 * 
	 * @return A URL specifying a particular resource from thesession.org API
	 * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
	 * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
	 */
	private URL composeURL() throws MalformedURLException, URISyntaxException
		{
		// Build the URL with all necessary parameters to perform a search via thesession.org API
		URL requestURL;
		
		// If a particular page within the response from the API is specified:
		if (pageNumber > 0)
			{
			UrlBuilder builder = new UrlBuilder();
			
			requestURL = builder.new Builder()
					.path("tunes" + "/" + "sets")
					.itemsPerPage(resultsPerPage)
					.pageNumber(pageNumber)
					.build();
			}
		
		// If no page is specified
		else if (pageNumber == 0)		
			{
			UrlBuilder builder = new UrlBuilder();
			
			requestURL = builder.new Builder()
					.path("tunes" + "/" + "sets")
					.itemsPerPage(resultsPerPage)
					.build();
			}
		
		// If anything other than a positive integer was specified as the page number
		else
			{
			throw new IllegalArgumentException("Page number must be an integer value greater than zero");
			}
		
		return requestURL;
		}
	
	}
