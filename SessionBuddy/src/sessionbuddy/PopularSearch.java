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
import sessionbuddy.wrappers.granularobjects.TuneDetails;
import sessionbuddy.wrappers.granularobjects.TuneDetailsWithDate;
import sessionbuddy.wrappers.granularobjects.TuneDetailsWithDateAndTunebooks;
import sessionbuddy.wrappers.granularobjects.User;
import sessionbuddy.wrappers.individualresults.SearchResultTunesPopular;
import sessionbuddy.wrappers.jsonresponse.PopularWrapperTunes;

/**
 * Retrieves the current most popular tunes, i.e. those that have been added to
 * the largest number of user tune books on thesession.org
 * 
 * @author Colman
 * @since 2018-03-30
 */
public class PopularSearch extends Search
{
    /**
     * The number of search results to be returned per page
     */
    private int resultsPerPage = 0;

    /**
     * Specifies a page in a multi-page response
     */
    private int pageNumber = 0;

    /**
     * Constructor where pagination is not required and you only want to see the
     * first page of the API response
     * 
     * @param resultsPerPage Specifies how many search results should appear in each page of the response
     */
    public PopularSearch(int resultsPerPage)
    {
        this.resultsPerPage = resultsPerPage;
    }

    /**
     * Constructor for cases where you need to specify an individual page in the
     * API response
     * 
     * @param resultsPerPage Specifies how many search results should appear in each page of the response
     * @param pageNumber Specifies a particular page number within the JSON response
     */
    public PopularSearch(int resultsPerPage, int pageNumber)
    {
        this(resultsPerPage);
        this.pageNumber = pageNumber;
    }

    /**
     * Retrieves a list of the most popular tunes on thesession.org, i.e. those
     * that have been added to the most user tunebooks.
     * 
     * @return an ArrayList of SearchResultTunesPopular objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IOException if a problem was encountered setting up the HTTP connection, or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     */
    public ArrayList<SearchResultTunesPopular> listTunes() throws IllegalArgumentException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Query the API
            String response = HttpRequestor.submitRequest(composeURL());
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
     * @return an ArrayList of SearchResultTunesPopular objects
     */
    private ArrayList<SearchResultTunesPopular> populateTunesSearchResult(PopularWrapperTunes parsedResults)
    {
        ArrayList<SearchResultTunesPopular> resultSet = new ArrayList<SearchResultTunesPopular>();

        // Find out how many pages are in the response
        pageCount = Integer.parseInt(parsedResults.pages);

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

            // Instantiate a SearchResultTunesPopular object & populate it
            SearchResultTunesPopular currentResult = new SearchResultTunesPopular(
                    tuneDetails, 
                    submitter);

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

            requestURL = builder.new Builder()
                    .requestType(RequestType.SEARCH_POPULAR)
                    .path("tunes" + "/" + "popular")
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
}
