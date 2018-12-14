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
import sessionbuddy.wrappers.granularobjects.ActivityStreamDetails;
import sessionbuddy.wrappers.granularobjects.ActivityStreamObject;
import sessionbuddy.wrappers.jsonresponse.ActivityStreamWrapper;
import sessionbuddy.wrappers.resultsets.ActivityStreamResult;

/**
 * Queries the API at thesession.org for an activity stream
 * and parses the response into an easily usable structure. 
 * 
 * To use this feature, first create a new ActivityStreamReader object, then call one of its
 * methods to perform the actual search.
 * 
 * @author Colman O'B
 * @since 2018-12-12
 *
 */
public class ActivityStreamReader extends Search
{
    /**
     * The number of search results to be returned per page
     */
    private int resultsPerPage = 0;

    /**
     * Specifies an individual page within a multi-page response
     */
    private int pageNumber = 0;
    

    /**
     * Constructor where pagination is not required,
     * and you only want to see the first page of the API response
     *
     * @param resultsPerPage The number of results per page in the JSON response from the API
     */
    public ActivityStreamReader(int resultsPerPage)
    {
        this.resultsPerPage = resultsPerPage;
    }

    /**
     * Constructor where you need to specify a page within the API response
     * 
     * @param resultsPerPage The number of search results per page in the JSON response from the API
     * @param pageNumber Specifies a particular page number within the JSON response
     */
    public ActivityStreamReader(int resultsPerPage, int pageNumber)
    {
        this.pageNumber = pageNumber;
    }

    /**
     * Retrieves an activity stream from thesession.org.
     * 
     * This method lists all recent activity, and does not narrow it down by category
     * 
     * @return An ArrayList of ActivityStreamResult objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-12-12
     */
    public ArrayList<ActivityStreamResult> readActivityStream() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL());
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
     * Retrieves an activity stream of tune-related activity from thesession.org
     * 
     * @return An ArrayList of ActivityStreamResult objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-12-13
     */
    public ArrayList<ActivityStreamResult> readActivityStreamTunes() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL("tunes"));
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
     * Retrieves an activity stream of recordings-related activity from thesession.org
     * 
     * @return An ArrayList of ActivityStreamResult objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-12-13
     */
    public ArrayList<ActivityStreamResult> readActivityStreamRecordings() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL("recordings"));
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
     * Retrieves an activity stream of session-related activity from thesession.org
     * 
     * @return An ArrayList of ActivityStreamResult objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-12-13
     */
    public ArrayList<ActivityStreamResult> readActivityStreamSessions() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL("sessions"));
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
     * Retrieves an activity stream of events-related activity from thesession.org
     * 
     * @return An ArrayList of ActivityStreamResult objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-12-13
     */
    public ArrayList<ActivityStreamResult> readActivityStreamEvents() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL("events"));
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
     * Retrieves an activity stream of discussions-related activity from thesession.org
     * 
     * @return An ArrayList of ActivityStreamResult objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-12-13
     */
    public ArrayList<ActivityStreamResult> readActivityStreamDiscussions() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL("discussions"));
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
     * This method lists all recent activity, and does not narrow it down by category
     * 
     * @param tuneID Numeric ID of an individual tune in thesession.org database
     * @return An ArrayList of ActivityStreamResult objects
     * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
     * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
     * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
     * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
     * 
     * @author Colman
     * @since 2018-12-12
     */
    public ArrayList<ActivityStreamResult> readActivityStreamItemTune(int tuneID) throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
    {
        try
        {
            validateResultsPerPageCount(resultsPerPage);
            // Perform the API query
            String response = HttpRequestor.submitRequest(composeURL("tunes", tuneID));
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
    private ArrayList<ActivityStreamResult> populateActivityStreamResult(ActivityStreamWrapper parsedResults)
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
                          parsedResults.items[i].object.displayName
                          );
                  
                  ActivityStreamObject target = new ActivityStreamObject(
                          parsedResults.items[i].target.url,
                          parsedResults.items[i].target.objectType,
                          parsedResults.items[i].target.id,
                          parsedResults.items[i].target.displayName
                          );

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
    private URL composeURL(String dataCategory) throws MalformedURLException, URISyntaxException
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
        
        // If no page is specified
        else if (pageNumber == 0)
        {
            URLComposer builder = new URLComposer();
            requestURL = builder.new Builder()
                    .requestType(RequestType.ACTIVITY_STREAM)
                    .path(dataCategory + "/" + "activity")
                    .itemsPerPage(resultsPerPage).build();
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
    private URL composeURL() throws MalformedURLException, URISyntaxException
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
                    .itemsPerPage(resultsPerPage).pageNumber(pageNumber)
                    .build();
        }
        
        // If no page is specified
        else if (pageNumber == 0)
        {
            URLComposer builder = new URLComposer();
            requestURL = builder.new Builder()
                    .requestType(RequestType.ACTIVITY_STREAM)
                    .path("/" + "activity")
                    .itemsPerPage(resultsPerPage).build();
        }
        
        // If anything other than a positive integer was specified as the page number
        else
        {
            throw new IllegalArgumentException("Page number must be an integer value greater than zero");
        }
        
        return requestURL;
    }
    
    /**
     * A helper method used to build the URL to query the API, for an activity stream for
     * an individual item such as a tune, discussion etc.
     * 
     * @param dataCategory The category of data to be queried, e.g. tunes, discussions, events etc.
     * @param itemID The numeric ID of an item such as a tune, discussion etc. in thesession.org database
     * @return A URL specifying a particular resource from thesession.org API
     * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
     * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
     */
    private URL composeURL(String dataCategory, int itemID) throws MalformedURLException, URISyntaxException
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
