package sessionbuddy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import sessionbuddy.utils.HttpRequestor;
import sessionbuddy.utils.JsonParser;
import sessionbuddy.utils.RequestType;
import sessionbuddy.utils.StringCleaner;
import sessionbuddy.utils.URLComposer;
import sessionbuddy.wrappers.granularobjects.SetDetails;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperSets;
import sessionbuddy.wrappers.resultsets.SearchResultSets;

/**
 * Retrieves a list of user-added sets of tunes
 * 
 * @author Colman O'B
 * @since 2018-04-01
 */
public class SetSearch extends Search
{
    /**
     * The number of search results to be returned per page
     */
    int resultsPerPage = 0;

    /**
     * Specifies a page within a multi-page JSON response
     */
    int pageNumber = 0;

    /**
     * Constructor where pagination is not required and you only want to see the
     * first page of the API response
     * 
     * @param resultsPerPage Specifies how many search results should appear in each page of the JSON response from the API
     */
    public SetSearch(int resultsPerPage)
    {
        this.resultsPerPage = resultsPerPage;
    }

    /**
     * Constructor for cases where you need to specify a page in the API response
     * 
     * @param resultsPerPage Specifies how many search results should appear in each page of the JSON response from the API
     * @param pageNumber Specifies a particular page number within the JSON response
     */
    public SetSearch(int resultsPerPage, int pageNumber)
    {
        this(resultsPerPage);
        this.pageNumber = pageNumber;
    }

    /**
     * Retrieves a list of the most recent sets of tunes added on thesession.org
     * 
     * @return an ArrayList of SearchResultSets objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-04-01
     */
    public ArrayList<SearchResultSets> listSets()
            throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL());
            // Parse the returned JSON into a wrapper
            LatestWrapperSets parsedResults = JsonParser.parseResponse(response, LatestWrapperSets.class);
            // Return the data retrieved from the API
            return populateSetSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * Helper method to parse the response to a search for sets of tunes
     * 
     * @param parsedResults a LatestWrapperSets object that has already been created and populated
     * @return an ArrayList of SearchResultSets objects
     * 
     * @author Colman
     * @since 2018-02-17
     */
    private ArrayList<SearchResultSets> populateSetSearchResult(LatestWrapperSets parsedResults)
    {
        ArrayList<SearchResultSets> resultSet = new ArrayList<SearchResultSets>();

        // Find out how many pages are in the response
        pageCount = Integer.parseInt(parsedResults.pages);

        // Loop as many times as the count of tunes in the result set:
        for (int i = 0; i < parsedResults.sets.length; i++)
        {
            // Extract each element from the response
            SetDetails details = new SetDetails(
                    parsedResults.sets[i].id,
                    StringCleaner.cleanString(parsedResults.sets[i].name),
                    parsedResults.sets[i].url, parsedResults.sets[i].date);
            
            User submitter = new User(
                    parsedResults.sets[i].member.id,
                    StringCleaner.cleanString(parsedResults.sets[i].member.name),
                    parsedResults.sets[i].member.url);

            // Instantiate a SearchResultSets object & populate it
            SearchResultSets currentResult = new SearchResultSets(
                    details,
                    submitter);

            // Add the SearchResultSets object to the ArrayList to be returned
            resultSet.add(currentResult);
        }
        return resultSet;
    }

    /**
     * A helper method used to put the URL together to query the API at
     * thesession.org
     * 
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private URL composeURL() throws MalformedURLException, URISyntaxException
    {
        URL requestURL;

        // If a particular page within the response from the API is specified:
        if (pageNumber > 0)
        {
            URLComposer builder = new URLComposer();

            requestURL = builder.
                    new Builder()
                    .requestType(RequestType.SEARCH_SETS)
                    .path("tunes" + "/" + "sets")
                    .itemsPerPage(resultsPerPage)
                    .pageNumber(pageNumber)
                    .build();
        }
        // If no page is specified
        else if (pageNumber == 0)
        {
            URLComposer builder = new URLComposer();

            requestURL = builder.new Builder()
                    .requestType(RequestType.SEARCH_SETS)
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
