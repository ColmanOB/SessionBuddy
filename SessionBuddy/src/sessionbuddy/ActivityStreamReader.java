package sessionbuddy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import sessionbuddy.utils.DataCategory;
import sessionbuddy.utils.HttpRequestor;
import sessionbuddy.utils.JsonParser;
import sessionbuddy.utils.RequestType;
import sessionbuddy.utils.StringCleaner;
import sessionbuddy.utils.URLComposer;
import sessionbuddy.wrappers.granularobjects.ActivityStreamDetails;
import sessionbuddy.wrappers.granularobjects.ActivityStreamObject;
import sessionbuddy.wrappers.jsonresponse.ActivityStreamWrapper;
import sessionbuddy.wrappers.resultsets.ActivityStreamResult;

/**
 * Queries the API at thesession.org for an activity stream
 * and parses the response into an easily usable structure. 
 * 
 * @author Colman O'B
 * @since 2018-12-19
 *
 */
public class ActivityStreamReader extends Search
{
    /**
     * Retrieves an activity stream from thesession.org.
     * 
     * This lists all recent activity, and does not narrow it down by category.
     * 
     * This does not specify a number of results per page, 
     * or a particular page number of the result set.
     * 
     * @return An ArrayList of ActivityStreamResult objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * @since 2018-12-18
     */
    public static ArrayList<ActivityStreamResult> readActivityStream() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURLAllCategories());
            // Parse the returned JSON into a wrapper
            ActivityStreamWrapper parsedResults = JsonParser.parseResponse(response, ActivityStreamWrapper.class);
            // Return the data retrieved from the API
            return populateActivityStreamResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    /**
     * Retrieves an activity stream from thesession.org.
     * 
     * This method lists all recent activity, and does not narrow it down by category.
     * 
     * This is used when you want to specify the number of results 
     * in each page of the response from the API.
     * 
     * @param resultsPerPage The number of results to be returned per page in the API response
     * @return An ArrayList of ActivityStreamResult objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * @since 2018-12-18
     */
    public static ArrayList<ActivityStreamResult> readActivityStream(int resultsPerPage) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURLAllCategories(resultsPerPage));
            // Parse the returned JSON into a wrapper
            ActivityStreamWrapper parsedResults = JsonParser.parseResponse(response, ActivityStreamWrapper.class);
            // Return the data retrieved from the API
            return populateActivityStreamResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    /**
     * Retrieves an activity stream from thesession.org.
     * 
     * This method lists all recent activity, and does not narrow it down by category.
     * 
     * This is used when you want to specify both the number of results per page
     * in the response from the API, and a specific page number within that response.
     * 
     * @param resultsPerPage The number of results to be returned per page in the API response
     * @param pageNumber A specific page within the response from the API
     * @return An ArrayList of ActivityStreamResult objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * @since 2018-12-18
     */
    public static ArrayList<ActivityStreamResult> readActivityStream(int resultsPerPage, int pageNumber) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURLAllCategories(resultsPerPage, pageNumber));
            // Parse the returned JSON into a wrapper
            ActivityStreamWrapper parsedResults = JsonParser.parseResponse(response, ActivityStreamWrapper.class);
            // Return the data retrieved from the API
            return populateActivityStreamResult(parsedResults);
        }
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static ArrayList<ActivityStreamResult> readActivityStream(DataCategory dataCategory) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        if (dataCategory == DataCategory.members || dataCategory == DataCategory.trips)
        {
            throw new IllegalArgumentException("Invalid category - No activity stream is available");
        }
        
        try
        {
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURLSingleCategory(dataCategory));
            // Parse the returned JSON into a wrapper
            ActivityStreamWrapper parsedResults = JsonParser.parseResponse(response, ActivityStreamWrapper.class);
            // Return the data retrieved from the API
            return populateActivityStreamResult(parsedResults);
        }
        
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static ArrayList<ActivityStreamResult> readActivityStream(DataCategory dataCategory, int resultsPerPage, int pageNumber) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        if (dataCategory == DataCategory.members || dataCategory == DataCategory.trips)
        {
            throw new IllegalArgumentException("Invalid category - No activity stream is available");
        }
        
        try
        {            
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURLSingleCategory(dataCategory, resultsPerPage, pageNumber));
            // Parse the returned JSON into a wrapper
            ActivityStreamWrapper parsedResults = JsonParser.parseResponse(response, ActivityStreamWrapper.class);
            // Return the data retrieved from the API
            return populateActivityStreamResult(parsedResults);
        }
        
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static ArrayList<ActivityStreamResult> readActivityStream(DataCategory dataCategory, int resultsPerPage) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        if (dataCategory == DataCategory.members || dataCategory == DataCategory.trips)
        {
            throw new IllegalArgumentException("Invalid category - No activity stream is available");
        }
        
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURLSingleCategory(dataCategory, resultsPerPage));
            // Parse the returned JSON into a wrapper
            ActivityStreamWrapper parsedResults = JsonParser.parseResponse(response, ActivityStreamWrapper.class);
            // Return the data retrieved from the API
            return populateActivityStreamResult(parsedResults);
        }
        
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    public static ArrayList<ActivityStreamResult> readActivityStreamItem(DataCategory dataCategory, int itemID) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURLSingleItem(dataCategory, itemID));
            // Parse the returned JSON into a wrapper
            ActivityStreamWrapper parsedResults = JsonParser.parseResponse(response, ActivityStreamWrapper.class);
            // Return the data retrieved from the API
            return populateActivityStreamResult(parsedResults);
        }
        
        catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex)
        {
            throw ex;
        }
    }
    
    /**
     * Helper method to gather and parse the response to a request for an Activity Stream
     * 
     * @param parsedResults an ActivityStreamWrapper object that has already been created an populated
     * @return an ArrayList of ActivityStreamResult objects
     */
    private static ArrayList<ActivityStreamResult> populateActivityStreamResult(ActivityStreamWrapper parsedResults)
    {
        ArrayList<ActivityStreamResult> resultSet = new ArrayList<ActivityStreamResult>();

        // Loop as many times as the count of items in the result set
        for (int i = 0; i < (parsedResults.items.length); i++)
        {
            // Extract the required elements from each individual search result in the JSON response
            ActivityStreamDetails details = new ActivityStreamDetails(
                    parsedResults.items[i].published,
                    StringCleaner.cleanString(parsedResults.items[i].title),
                    parsedResults.items[i].verb);
                    
            ActivityStreamObject actor = new ActivityStreamObject(
                    parsedResults.items[i].actor.url,
                    parsedResults.items[i].actor.objectType,
                    parsedResults.items[i].actor.id,
                    parsedResults.items[i].actor.displayName);
                  
            ActivityStreamObject object = new ActivityStreamObject(
                    parsedResults.items[i].object.url,
                    parsedResults.items[i].object.objectType,
                    parsedResults.items[i].object.id,
                    parsedResults.items[i].object.displayName);
                  
            ActivityStreamObject target = new ActivityStreamObject(
                    parsedResults.items[i].target.url,
                    parsedResults.items[i].target.objectType,
                    parsedResults.items[i].target.id,
                    parsedResults.items[i].target.displayName);

            ActivityStreamResult currentResult = new ActivityStreamResult(details, actor, object, target);
            
            // Add the current ActivityStreamResult object to the ArrayList to be returned to the caller
            resultSet.add(currentResult);
        }
        return resultSet;
    }
    
    /**
     * A helper method used to put the URL together to query the API at thesession.org
     * 
     * @param dataCategory The category of data to be queried, e.g. tunes, discussions, events etc.
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private static URL composeURLSingleCategory(DataCategory dataCategory) throws MalformedURLException, URISyntaxException
    {
        // Build the URL with all necessary parameters to perform a search via thesession.org API
        URL requestURL;

        URLComposer builder = new URLComposer();
        requestURL = builder.new Builder()
                    .requestType(RequestType.ACTIVITY_STREAM)
                    .path(dataCategory + "/" + "activity")
                    .build();
        
        return requestURL;
    }
    
    /**
     * A helper method used to put the URL together to query the API at thesession.org
     * 
     * @param dataCategory The category of data to be queried, e.g. tunes, discussions, events etc.
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private static URL composeURLSingleCategory(DataCategory dataCategory, int resultsPerPage) throws MalformedURLException, URISyntaxException
    {
        // Build the URL with all necessary parameters to perform a search via thesession.org API
        URL requestURL;

        URLComposer builder = new URLComposer();
        requestURL = builder.new Builder()
                    .requestType(RequestType.ACTIVITY_STREAM)
                    .path(dataCategory + "/" + "activity")
                    .itemsPerPage(resultsPerPage)
                    .build();
        
        return requestURL;
    }

    /**
     * A helper method used to put the URL together to query the API at thesession.org
     * 
     * @param dataCategory The category of data to be queried, e.g. tunes, discussions, events etc.
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private static URL composeURLSingleCategory(DataCategory dataCategory, int resultsPerPage, int pageNumber) throws MalformedURLException, URISyntaxException
    {
        // Build the URL with all necessary parameters to perform a search via thesession.org API
        URL requestURL;

        // If a particular page within the response from the API is specified:
        if (pageNumber > 0)
        {
            URLComposer builder = new URLComposer();
            requestURL = builder.new Builder()
                    .requestType(RequestType.ACTIVITY_STREAM)
                    .path(dataCategory + "/" + "activity")
                    .itemsPerPage(resultsPerPage).pageNumber(pageNumber)
                    .build();
        }

        // If anything other than a positive integer was specified as the page number
        else
        {
            throw new IllegalArgumentException("Page number must be an integer value greater than zero");
        }
        
        return requestURL;
    }
        
    /**
     * A helper method used to put the URL together to query the API at thesession.org
     * for an activity stream, specifically one with no data category, i.e. for retrieving
     * details of all recent activity across thesession.org
     * 
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private static URL composeURLAllCategories() throws MalformedURLException, URISyntaxException
    {
        // Build the URL with all necessary parameters to perform a search via thesession.org API
        URL requestURL;
        URLComposer builder = new URLComposer();
            
        requestURL = builder.new Builder()
                        .requestType(RequestType.ACTIVITY_STREAM)
                        .path("/" + "activity")
                        .build();

        
        return requestURL;
    }
    
    /**
     * A helper method used to put the URL together to query the API at thesession.org
     * for an activity stream, specifically one with no data category, i.e. for retrieving
     * details of all recent activity across thesession.org
     * 
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private static URL composeURLAllCategories(int resultsPerPage) throws MalformedURLException, URISyntaxException
    {
        URL requestURL;
        
        if (resultsPerPage >= 0)
        {
            // Build the URL with all necessary parameters to perform a search via thesession.org API
            URLComposer builder = new URLComposer();
            
            requestURL = builder.new Builder()
                            .requestType(RequestType.ACTIVITY_STREAM)
                            .path("/" + "activity")
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
    
    /**
     * A helper method used to put the URL together to query the API at thesession.org
     * for an activity stream, specifically one with no data category, i.e. for retrieving
     * details of all recent activity across thesession.org
     * 
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private static URL composeURLAllCategories(int resultsPerPage, int pageNumber) throws MalformedURLException, URISyntaxException
    {
        // Build the URL with all necessary parameters to perform a search via thesession.org API
        URL requestURL;

        // If a particular page within the response from the API is specified:
        if (pageNumber > 0)
        {
            URLComposer builder = new URLComposer();
            requestURL = builder.new Builder()
                    .requestType(RequestType.ACTIVITY_STREAM)
                    .path("/" + "activity")
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
        
    private static URL composeURLSingleItem(DataCategory dataCategory, int itemID) throws MalformedURLException, URISyntaxException
    {
        // Build the URL with all necessary parameters to perform an API query
        URL requestURL;
        URLComposer builder = new URLComposer();

        requestURL = builder
                .new Builder()
                .requestType(RequestType.SINGLE_ITEM)
                .path(dataCategory + "/" + itemID + "/activity").build();

        return requestURL;
    }
}
