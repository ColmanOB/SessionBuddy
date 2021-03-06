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
import sessionbuddy.wrappers.granularobjects.ActivityStreamDetails;
import sessionbuddy.wrappers.granularobjects.ActivityStreamObject;
import sessionbuddy.wrappers.jsonresponse.ActivityStreamWrapper;
import sessionbuddy.wrappers.resultsets.ActivityStreamResult;

/**
 * Query thesession.org API for an activity stream and parse the response into a usable structure
 * 
 * @author Colman O'B
 * @since 2018-12-19
 *
 */
public class ActivityStream {
  /**
   * Retrieve an activity stream without specifying a category.
   * This does not specify a number of results per page, or a particular page number of the result set.
   * 
   * @return An ArrayList of ActivityStreamResult objects
   * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
   * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
   * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
   * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
   * @since 2018-12-18
   */
  public static ArrayList<ActivityStreamResult> readActivityStream()
      throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException {
    try {
      // Make the API call, parse the response into a wrapper, and return the wrapper
      String response = HttpRequestor.submitRequest(composeURLAllCategories());
      ActivityStreamWrapper parsedResponse = JsonParser.parseResponse(response, ActivityStreamWrapper.class);
      return populateActivityStreamResult(parsedResponse);
    } 
    catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex) {
      throw ex;
    }
  }

  /**
   * Retrieve an activity stream without specifying a category.
   * Used when you want to specify a number of results per page in the API response.
   * 
   * @param resultsPerPage The number of results to be returned per page in the API response
   * @return An ArrayList of ActivityStreamResult objects
   * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
   * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
   * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
   * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
   * @since 2018-12-18
   */
  public static ArrayList<ActivityStreamResult> readActivityStream(int resultsPerPage)
      throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException {
    try {
      // Make the API call, parse the response into a wrapper, and return the wrapper
      PageCountValidator.validate(resultsPerPage);
      String response = HttpRequestor.submitRequest(composeURLAllCategories(resultsPerPage));
      ActivityStreamWrapper parsedResponse = JsonParser.parseResponse(response, ActivityStreamWrapper.class);
      return populateActivityStreamResult(parsedResponse);
    } 
    catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex) {
      throw ex;
    }
  }

  /**
   * Retrieve an activity stream without specifying a category.
   * Used when you want to specify a number of results per page and a specific page number within that response.
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
  public static ArrayList<ActivityStreamResult> readActivityStream(int resultsPerPage, int pageNumber)
      throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException {
    try {
      // Make the API call, parse the response into a wrapper, and return the wrapper
      PageCountValidator.validate(resultsPerPage);
      String response = HttpRequestor.submitRequest(composeURLAllCategories(resultsPerPage, pageNumber));
      ActivityStreamWrapper parsedResponse = JsonParser.parseResponse(response, ActivityStreamWrapper.class);
      return populateActivityStreamResult(parsedResponse);
    } 
    catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex) {
      throw ex;
    }
  }

  /**
   * Retrieve an activity stream for a particular category.  Currently the available categories are; 
   * Tunes, Recordings, Sessions, Events, Discussions
   * 
   * Used when you do not want to specify a number of results per page or any specific page
   * within the response.
   * 
   * @param dataCategory the category of data in question, e.g. tunes, discussions, sessions etc.
   * @return an ArrayList of ActivityStreamResult objects
   * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
   * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
   * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
   * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
   * @since 2018-12-20
   */
  public static ArrayList<ActivityStreamResult> readActivityStream(DataCategory dataCategory)
      throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException {
    // Handle cases where a data category is provided that does not have an activity stream
    if (dataCategory == DataCategory.members) {
      throw new IllegalArgumentException("Invalid category - No activity stream is available");
    }
    try {
      // Make the API call, parse the response into a wrapper, and return the wrapper
      String response = HttpRequestor.submitRequest(composeURLSingleCategory(dataCategory));
      ActivityStreamWrapper parsedResponse = JsonParser.parseResponse(response, ActivityStreamWrapper.class);
      return populateActivityStreamResult(parsedResponse);
    } 
    catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex) {
      throw ex;
    }
  }

  /**
   * Retrieve an activity stream for a particular category.  Currently the available categories are; 
   * Tunes, Recordings, Sessions, Events, Discussions
   * 
   * This is used when you want to specify the number of results per page but not a specific page
   * within the response.
   * 
   * @param dataCategory the category of data in question, e.g. tunes, discussions, sessions etc.
   * @param resultsPerPage the number of results to be returned per page in the API response
   * @return an ArrayList of ActivityStreamResult objects
   * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
   * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
   * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
   * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
   * @since 2018-12-20
   */
  public static ArrayList<ActivityStreamResult> readActivityStream(DataCategory dataCategory, int resultsPerPage)
      throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException {
    // Handle cases where a data category is provided that does not have an activity stream
    if (dataCategory == DataCategory.members) {
      throw new IllegalArgumentException("Invalid category - No activity stream is available");
    }
    try {
      // Make the API call, parse the response into a wrapper, and return the wrapper
      PageCountValidator.validate(resultsPerPage);
      String response = HttpRequestor.submitRequest(composeURLSingleCategory(dataCategory, resultsPerPage));
      ActivityStreamWrapper parsedResponse = JsonParser.parseResponse(response, ActivityStreamWrapper.class);
      return populateActivityStreamResult(parsedResponse);
    } 
    catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex) {
      throw ex;
    }
  }

  /**
   * Retrieves an activity stream for a particular data category.  Currently the available categories are; 
   * Tunes, Recordings, Sessions, Events, Discussions
   * 
   * This is used when you want to specify both number of results per page and a specific page
   * within the response.
   * 
   * @param dataCategory the category of data in question, e.g. tunes, discussions, sessions etc.
   * @param resultsPerPage the number of results to be returned per page in the API response
   * @param pageNumber specifies an individual page within the API response
   * @return an ArrayList of ActivityStreamResult objects
   * @throws IllegalArgumentException if an attempt was made to specify more than 50 results per page
   * @throws IllegalStateException if an attempt was made to check the number of pages in a JSON response before the pageCount field has been populated
   * @throws IOException if a problem was encountered setting up the HTTP connection or reading data from it
   * @throws URISyntaxException if the underlying UrlBuilder class throws a URISyntaxException
   * @since 2018-12-20
   */
  public static ArrayList<ActivityStreamResult> readActivityStream(DataCategory dataCategory, int resultsPerPage, int pageNumber)
      throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException {
    // Handle cases where a data category is provided that does not have an activity stream
    if (dataCategory == DataCategory.members) {
      throw new IllegalArgumentException("Invalid category - No activity stream is available");
    }
    try {
      // Make the API call, parse the response into a wrapper, and return the wrapper
      PageCountValidator.validate(resultsPerPage);
      String response = HttpRequestor.submitRequest(composeURLSingleCategory(dataCategory, resultsPerPage, pageNumber));
      ActivityStreamWrapper parsedResponse = JsonParser.parseResponse(response, ActivityStreamWrapper.class);
      return populateActivityStreamResult(parsedResponse);
    } 
    catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex) {
      throw ex;
    }
  }

  public static ArrayList<ActivityStreamResult> readActivityStreamItem(DataCategory dataCategory, int itemID)
      throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException {
    try {
      // Make the API call, parse the response into a wrapper, and return the wrapper
      String response = HttpRequestor.submitRequest(composeURLSingleItem(dataCategory, itemID));
      ActivityStreamWrapper parsedResponse = JsonParser.parseResponse(response, ActivityStreamWrapper.class);
      return populateActivityStreamResult(parsedResponse);
    } 
    catch (IllegalArgumentException | IOException | IllegalStateException | URISyntaxException ex) {
      throw ex;
    }
  }

  /**
   * Helper method to gather and parse the response to a request for an Activity Stream
   * 
   * @param parsedResponse an ActivityStreamWrapper object that has already been created an populated
   * @return an ArrayList of ActivityStreamResult objects
   */
  private static ArrayList<ActivityStreamResult> populateActivityStreamResult(ActivityStreamWrapper parsedResponse) {
    ArrayList<ActivityStreamResult> activityStream = new ArrayList<ActivityStreamResult>();

    // Loop as many times as the count of items in the result set
    for (int i = 0; i < (parsedResponse.items.length); i++) {
      // Extract the required elements from each individual search result in the JSON response
      ActivityStreamDetails details = new ActivityStreamDetails(parsedResponse.items[i].published,
          StringCleaner.cleanString(parsedResponse.items[i].title), parsedResponse.items[i].verb);

      ActivityStreamObject actor = new ActivityStreamObject(parsedResponse.items[i].actor.url,
          parsedResponse.items[i].actor.objectType, parsedResponse.items[i].actor.id,
          parsedResponse.items[i].actor.displayName);

      ActivityStreamObject object = new ActivityStreamObject(parsedResponse.items[i].object.url,
          parsedResponse.items[i].object.objectType, parsedResponse.items[i].object.id,
          parsedResponse.items[i].object.displayName);

      ActivityStreamObject target = new ActivityStreamObject(parsedResponse.items[i].target.url,
          parsedResponse.items[i].target.objectType, parsedResponse.items[i].target.id,
          parsedResponse.items[i].target.displayName);

      ActivityStreamResult currentResult = new ActivityStreamResult(details, actor, object, target);

      // Add the current ActivityStreamResult object to the ArrayList to be returned to the caller
      activityStream.add(currentResult);
    }
    return activityStream;
  }

  /**
   * A helper method used to put the URL together to query the API at thesession.org
   * 
   * @param dataCategory The category of data to be queried, e.g. tunes, discussions, events etc.
   * @return A URL specifying a particular resource from thesession.org API
   * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
   * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
   */
  private static URL composeURLSingleCategory(DataCategory dataCategory)
      throws MalformedURLException, URISyntaxException {
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
  private static URL composeURLSingleCategory(DataCategory dataCategory, int resultsPerPage)
      throws MalformedURLException, URISyntaxException {
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
  private static URL composeURLSingleCategory(DataCategory dataCategory, int resultsPerPage, int pageNumber) 
      throws MalformedURLException, URISyntaxException {
    URL requestURL;

    // If a particular page within the response from the API is specified:
    if (pageNumber > 0) {
      URLComposer builder = new URLComposer();
      requestURL = builder.new Builder()
          .requestType(RequestType.ACTIVITY_STREAM)
          .path(dataCategory + "/" + "activity")
          .itemsPerPage(resultsPerPage)
          .pageNumber(pageNumber)
          .build();
    }
    // If anything other than a positive integer was specified as the page number
    else {
      throw new IllegalArgumentException("Page number must be an integer value greater than zero");
    }
    return requestURL;
  }

  /**
   * A helper method used to put the URL together to query the API at thesession.org for an activity
   * stream, specifically one with no data category, i.e. for retrieving details of all recent
   * activity across thesession.org.
   * 
   * This is used when you do not want to specify either the number of results to be returned per
   * page, nor a specific page number within the response.
   * 
   * @return A URL specifying a particular resource from thesession.org API
   * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
   * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
   */
  private static URL composeURLAllCategories() throws MalformedURLException, URISyntaxException {
    URL requestURL;
    URLComposer builder = new URLComposer();

    requestURL = builder.new Builder()
        .requestType(RequestType.ACTIVITY_STREAM)
        .path("/" + "activity")
        .build();

    return requestURL;
  }

  /**
   * A helper method used to put the URL together to query the API at thesession.org for an activity
   * stream, specifically one with no data category, i.e. for retrieving details of all recent
   * activity across thesession.org.
   * 
   * This is used when you want to specify the number of results to be returned per page, but do not
   * want to specify a particular page number.
   * 
   * @return A URL specifying a particular resource from thesession.org API
   * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
   * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
   */
  private static URL composeURLAllCategories(int resultsPerPage)
      throws MalformedURLException, URISyntaxException {
    URL requestURL;

    if (resultsPerPage >= 0) {
      URLComposer builder = new URLComposer();

      requestURL = builder.new Builder()
          .requestType(RequestType.ACTIVITY_STREAM)
          .path("/" + "activity")
          .itemsPerPage(resultsPerPage)
          .build();
    }
    // If page number is not a positive integer
    else {
      throw new IllegalArgumentException("Results per page number must be an integer value greater than zero");
    }
    return requestURL;
  }

  /**
   * A helper method used to put the URL together to query the API at thesession.org for an activity
   * stream, specifically one with no data category, i.e. for retrieving details of all recent
   * activity across thesession.org
   * 
   * @param resultsPerPage The number of results to be returned per page in the response
   * @param pageNumber An individual page number within the response
   * @return A URL specifying the activity stream across all categories on thesession.org
   * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
   * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
   */
  private static URL composeURLAllCategories(int resultsPerPage, int pageNumber)
      throws MalformedURLException, URISyntaxException {
    URL requestURL;

    // If a particular page within the response from the API is specified:
    if (pageNumber > 0) {
      URLComposer builder = new URLComposer();
      requestURL = builder.new Builder()
          .requestType(RequestType.ACTIVITY_STREAM)
          .path("/" + "activity")
          .itemsPerPage(resultsPerPage)
          .pageNumber(pageNumber)
          .build();
    }
    // If the page number is anything other than a positive integer
    else {
      throw new IllegalArgumentException("Page number must be an integer value greater than zero");
    }
    return requestURL;
  }

  /**
   * A helper method used to put the URL together to query the API at thesession.org for an activity
   * stream for a specific item, e.g. for retrieving details of all recent activity relating to a
   * particular tune or session etc.
   * 
   * @param dataCategory The category of data to be queried, e.g. tunes, discussions, events etc.
   * @param itemID The numeric ID in thesession.org database of the item being queried
   * @return A URL specifying the activity stream for a particular item on thesession.org
   * @throws MalformedURLException if the UrlBuilder.buildURL static method throws a MalformedURLException
   * @throws URISyntaxException if the UrlBuilder.buildURL static method throws a URISyntaxException
   */
  private static URL composeURLSingleItem(DataCategory dataCategory, int itemID)
      throws MalformedURLException, URISyntaxException {
    URL requestURL;
    URLComposer builder = new URLComposer();

    requestURL = builder.new Builder()
        .requestType(RequestType.SINGLE_ITEM)
        .path(dataCategory + "/" + itemID + "/activity")
        .build();

    return requestURL;
  }
}
