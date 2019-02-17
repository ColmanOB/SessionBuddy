package sessionbuddy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import sessionbuddy.utils.HttpRequestor;
import sessionbuddy.utils.JsonParser;
import sessionbuddy.utils.PageCountValidator;
import sessionbuddy.utils.RequestType;
import sessionbuddy.utils.StringCleaner;
import sessionbuddy.utils.URLComposer;
import sessionbuddy.wrappers.granularobjects.SetDetails;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.individualresults.Set;
import sessionbuddy.wrappers.jsonresponse.LatestWrapperSets;
import sessionbuddy.wrappers.responsemetadata.LatestSearchResultHeaders;
import sessionbuddy.wrappers.resultsets.SearchResultSetsLatest;

/**
 * Retrieves a list of user-added sets of tunes
 * 
 * @author Colman O'B
 * @since 2018-04-01
 */
public class Sets
{    
    public static SearchResultSetsLatest listSets(int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(resultsPerPage, pageNumber));
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
    
    public static SearchResultSetsLatest listSets(int resultsPerPage) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(resultsPerPage));
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
     * @return an ArrayList of Set objects
     * 
     * @author Colman
     * @since 2018-02-17
     */
    private static SearchResultSetsLatest populateSetSearchResult(LatestWrapperSets parsedResults)
    {
        // Capture the metadata for the search results
        LatestSearchResultHeaders headers = new LatestSearchResultHeaders(parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // This will hold the list of individual items in the result set
        ArrayList<Set> resultSet = new ArrayList<Set>();

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

            // Put the individual search result into a wrapper object, and add to the larger result set
            Set currentResult = new Set(details, submitter);
            resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        SearchResultSetsLatest searchResult = new SearchResultSetsLatest(headers, resultSet);
        return searchResult;
    }

    /**
     * A helper method used to put the URL together to query the API at
     * thesession.org
     * 
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private static URL composeURL(int resultsPerPage, int pageNumber) throws MalformedURLException, URISyntaxException
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

        // If anything other than a positive integer was specified as the page number
        else
        {
            throw new IllegalArgumentException("Page number must be an integer value greater than zero");
        }
        return requestURL;
    }
    
    private static URL composeURL(int resultsPerPage) throws MalformedURLException, URISyntaxException
    {
        URL requestURL;

        URLComposer builder = new URLComposer();

        requestURL = builder.new Builder()
                .requestType(RequestType.SEARCH_SETS)
                .path("tunes" + "/" + "sets")
                .itemsPerPage(resultsPerPage)
                .build();

        return requestURL;
    }

}
