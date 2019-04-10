package sessionbuddy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import sessionbuddy.utils.DataCategory;
import sessionbuddy.utils.HttpRequestor;
import sessionbuddy.utils.JsonParser;
import sessionbuddy.utils.PageCountValidator;
import sessionbuddy.utils.RequestType;
import sessionbuddy.utils.StringCleaner;
import sessionbuddy.utils.URLComposer;
import sessionbuddy.wrappers.granularobjects.TuneDetails;
import sessionbuddy.wrappers.granularobjects.TuneDetailsWithDate;
import sessionbuddy.wrappers.granularobjects.TuneDetailsWithDateAndTunebooks;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.individualresults.TunePopular;
import sessionbuddy.wrappers.jsonresponse.PopularWrapperTunes;
import sessionbuddy.wrappers.responsemetadata.ResponseHeaders;
import sessionbuddy.wrappers.resultsets.PopularResultTunes;

/**
 * Retrieves the current most popular tunes, i.e. those that have been added to
 * the largest number of user tune books on thesession.org
 * 
 * @author Colman
 * @since 2019-02-17
 */
public class Popular
{
    /**
     * Retrieves a list of the most popular tunes on thesession.org, i.e. those
     * that have been added to the most user tunebooks.
     * 
     * @return an ArrayList of TunePopular objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     */
    public static PopularResultTunes listTunes(int resultsPerPage, int pageNumber) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.tunes;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, resultsPerPage, pageNumber));
            // Parse the returned JSON into a wrapper
            PopularWrapperTunes parsedResults = JsonParser.parseResponse(response, PopularWrapperTunes.class);
            // Return the data retrieved from the API
            return populateTunesSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static PopularResultTunes listTunes(int resultsPerPage) throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            PageCountValidator.validate(resultsPerPage);
            DataCategory dataCategory = DataCategory.tunes;
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL(dataCategory, resultsPerPage));
            // Parse the returned JSON into a wrapper
            PopularWrapperTunes parsedResults = JsonParser.parseResponse(response, PopularWrapperTunes.class);
            // Return the data retrieved from the API
            return populateTunesSearchResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | URISyntaxException ex)
        {
            throw ex;
        }
    }

    /**
     * Helper method to gather and parse the response to a search for popular
     * tunes
     * 
     * @param parsedResults a PopularWrapperTunes object that has already been created and populated
     * @return an ArrayList of TunePopular objects
     */
    private static PopularResultTunes populateTunesSearchResult(PopularWrapperTunes parsedResults)
    {        
        // Capture the metadata for the search results
        ResponseHeaders headers = new ResponseHeaders(parsedResults.perpage, parsedResults.format, parsedResults.pages, parsedResults.page, parsedResults.total);
        
        // This will hold the list of individual items in the result set
        ArrayList<TunePopular> resultSet = new ArrayList<TunePopular>();

        // Loop as many times as the count of tunes in the result set:
        for (int i = 0; i < parsedResults.tunes.length; i++)
        {
            // Get the basic tune details
            TuneDetails basicDetails = new TuneDetails(
                    parsedResults.tunes[i].id,
                    StringCleaner.cleanString(parsedResults.tunes[i].name),
                    parsedResults.tunes[i].url);

            // Get the tune type and tune date
            TuneDetailsWithDate detailsWithDate = new TuneDetailsWithDate(
                    basicDetails, 
                    parsedResults.tunes[i].type,
                    parsedResults.tunes[i].date);

            // Get the number of user tunebooks to which the tune has been added
            TuneDetailsWithDateAndTunebooks tuneDetails = new TuneDetailsWithDateAndTunebooks(
                    detailsWithDate, 
                    parsedResults.tunes[i].tunebooks);

            User submitter = new User(
                    parsedResults.tunes[i].member.id,
                    StringCleaner.cleanString(parsedResults.tunes[i].member.name),
                    parsedResults.tunes[i].member.url);

            // Put the individual search result into a wrapper object, and add to the larger result set
           TunePopular currentResult = new TunePopular(tuneDetails, submitter);
           resultSet.add(currentResult);
        }
        // Put the response metadata and individual results into a single object to be returned
        PopularResultTunes searchResult = new PopularResultTunes(headers, resultSet);
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
    private static URL composeURL(DataCategory dataCategory, int resultsPerPage, int pageNumber) throws MalformedURLException, URISyntaxException
    {
        URL requestURL;

        // If a particular page within the response from the API is specified:
        if (pageNumber > 0)
        {
            URLComposer builder = new URLComposer();

            requestURL = builder.new Builder()
                    .requestType(RequestType.SEARCH_POPULAR)
                    .path(dataCategory + "/" + "popular")
                    .itemsPerPage(resultsPerPage)
                    .pageNumber(pageNumber)
                    .build();
        }
        // If no page is specified
        else if (pageNumber == 0)
        {
            URLComposer builder = new URLComposer();

            requestURL = builder.new Builder()
                    .requestType(RequestType.SEARCH_POPULAR)
                    .path("tunes" + "/" + "popular")
                    .itemsPerPage(resultsPerPage)
                    .build();
        }
        // If anything other than a positive integer was specified
        else
        {
            throw new IllegalArgumentException("Page number must be an integer value greater than zero");
        }
        return requestURL;
    }
    
    private static URL composeURL(DataCategory dataCategory, int resultsPerPage) throws MalformedURLException, URISyntaxException
    {
        URL requestURL;

        URLComposer builder = new URLComposer();

        requestURL = builder.new Builder()
                .requestType(RequestType.SEARCH_POPULAR)
                .path(dataCategory + "/" + "popular")
                .itemsPerPage(resultsPerPage)
                .build();
        
        return requestURL;
    }
}
