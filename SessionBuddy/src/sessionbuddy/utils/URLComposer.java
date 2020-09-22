package sessionbuddy.utils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

/**
 * Assembles the URL for the API resource at thesession.org.
 * 
 * This uses the Apache httpclient library.
 * 
 * @author Colman
 * @since 2020-09-22
 */
public class URLComposer {
  // Constants that are assumed unlikely to change
  private static final String PROTOCOL = "https";
  private static final String HOST = "thesession.org";
  private static final String FORMAT_SPECIFIER = "format";
  private static final String FORMAT = "json";
  private static final String ITEMS_PER_PAGE_SPECIFIER = "perpage";
  private static final String PAGE_NUMBER_SPECIFIER = "page";

  // Variables that will vary per query
  private String path = null;
  private List<NameValuePair> queryParameters = null;
  private RequestType requestType;
  private int itemsPerPage = 0;
  private int pageNumber = 0;

  /**
   * Builds a URLComposer object based on parameters provided by the caller
   * 
   * @author Colman
   * @since 2018-05-09
   */
  public class Builder {
    private URLComposer resourceURL = new URLComposer();

    public Builder path(String path) {
      resourceURL.setPath(path);
      return this;
    }

    public Builder queryParameters(List<NameValuePair> queryParameters) {
      resourceURL.setQueryParameters(queryParameters);
      return this;
    }

    public Builder requestType(RequestType requestType) {
      resourceURL.setRequestType(requestType);
      return this;
    }

    public Builder itemsPerPage(int itemsPerPage) {
      resourceURL.setItemsPerPage(itemsPerPage);
      return this;
    }

    public Builder pageNumber(int pageNumber) {
      resourceURL.setPageNumber(pageNumber);
      return this;
    }

    /**
     * Builds the URL required to query the API at thesession.org 
     * based on the parameters provided by the caller
     * 
     * @return a URL in the format expected by the API at thesession.org
     * @throws IllegalArgumentException if the parameters provided are invalid or if a URI Syntax or Malformed URL Exception are encountered
     */
    public URL build() throws IllegalArgumentException {
      try {
        // In the case of Keyword or location-based search, where no page number & no items per page are specified
        if ((resourceURL.requestType == RequestType.SEARCH_BY_KEYWORD
            || resourceURL.requestType == RequestType.SEARCH_BY_LOCATION)
            && resourceURL.pageNumber == 0 && resourceURL.itemsPerPage == 0) {
          
          URIBuilder builder =
              new URIBuilder()
              .setScheme(PROTOCOL)
              .setHost(HOST)
              .setPath(resourceURL.getPath())
              .addParameters(resourceURL.queryParameters)
              .addParameter(FORMAT_SPECIFIER, FORMAT);

          return builder.build().toURL();
        }

        // In the case of Keyword or location-based search where no page number is specified but items per page are specified
        else if ((resourceURL.requestType == RequestType.SEARCH_BY_KEYWORD
            || resourceURL.requestType == RequestType.SEARCH_BY_LOCATION)
            && resourceURL.pageNumber == 0 && resourceURL.itemsPerPage > 0) {
          
          URIBuilder builder = new URIBuilder()
              .setScheme(PROTOCOL).setHost(HOST)
              .setPath(resourceURL.getPath())
              .addParameters(resourceURL.getQueryParameters())
              .addParameter(FORMAT_SPECIFIER, FORMAT)
              .addParameter(ITEMS_PER_PAGE_SPECIFIER, Integer.toString(resourceURL.getItemsPerPage()));

          return builder.build().toURL();
        }

        // In the case of Keyword or location-based search where page number and number of items per page are specified
        else if ((resourceURL.requestType == RequestType.SEARCH_BY_KEYWORD
            || resourceURL.requestType == RequestType.SEARCH_BY_LOCATION)
            && resourceURL.pageNumber > 0 && resourceURL.itemsPerPage > 0) {
          
          URIBuilder builder = new URIBuilder()
              .setScheme(PROTOCOL)
              .setHost(HOST)
              .setPath(resourceURL.getPath())
              .addParameters(resourceURL.getQueryParameters())
              .addParameter(FORMAT_SPECIFIER, FORMAT)
              .addParameter(ITEMS_PER_PAGE_SPECIFIER, Integer.toString(resourceURL.itemsPerPage))
              .addParameter(PAGE_NUMBER_SPECIFIER, Integer.toString(resourceURL.pageNumber));

          return builder.build().toURL();
        }

        // In the case of Keyword or location-based search where page number is specified
        // but no number of items per page are specified
        else if ((resourceURL.requestType == RequestType.SEARCH_BY_KEYWORD
            || resourceURL.requestType == RequestType.SEARCH_BY_LOCATION)
            && resourceURL.pageNumber > 0 && resourceURL.itemsPerPage == 0) {
          
          URIBuilder builder = new URIBuilder()
              .setScheme(PROTOCOL).setHost(HOST)
              .setPath(resourceURL.getPath())
              .addParameters(resourceURL.getQueryParameters())
              .addParameter(FORMAT_SPECIFIER, FORMAT)
              .addParameter(PAGE_NUMBER_SPECIFIER, Integer.toString(resourceURL.pageNumber));

          return builder.build().toURL();
        }

        // In the case of search for recently-added items, member contributions, popular tunes, sets or activity streams
        // where a page number and items per page are specified
        else if ((resourceURL.requestType == RequestType.SEARCH_LATEST_ITEMS
            || resourceURL.requestType == RequestType.SEARCH_MEMBER_CONTRIBUTIONS
            || resourceURL.requestType == RequestType.SEARCH_POPULAR
            || resourceURL.requestType == RequestType.SEARCH_SETS
            || resourceURL.requestType == RequestType.ACTIVITY_STREAM) && resourceURL.pageNumber > 0
            && resourceURL.itemsPerPage > 0) {
          
          URIBuilder builder = new URIBuilder()
              .setScheme(PROTOCOL).setHost(HOST)
              .setPath(resourceURL.getPath())
              .addParameter(FORMAT_SPECIFIER, FORMAT)
              .addParameter(ITEMS_PER_PAGE_SPECIFIER, Integer.toString(resourceURL.itemsPerPage))
              .addParameter(PAGE_NUMBER_SPECIFIER, Integer.toString(resourceURL.pageNumber));

          return builder.build().toURL();
        }

        // In the case of search for recently-added items, member contributions, popular tunes, sets or activity streams
        // where page number is specified, but not a number of items per page
        else if ((resourceURL.requestType == RequestType.SEARCH_LATEST_ITEMS
            || resourceURL.requestType == RequestType.SEARCH_MEMBER_CONTRIBUTIONS
            || resourceURL.requestType == RequestType.SEARCH_POPULAR
            || resourceURL.requestType == RequestType.SEARCH_SETS
            || resourceURL.requestType == RequestType.ACTIVITY_STREAM) && resourceURL.pageNumber > 0
            && resourceURL.itemsPerPage == 0) {
          
          URIBuilder builder = new URIBuilder()
              .setScheme(PROTOCOL)
              .setHost(HOST)
              .setPath(resourceURL.getPath())
              .addParameter(FORMAT_SPECIFIER, FORMAT)
              .addParameter(PAGE_NUMBER_SPECIFIER, Integer.toString(resourceURL.pageNumber));

          return builder.build().toURL();
        }

        // In the case of search for recently-added items, member contributions, popular tunes, sets or activity streams
        // where no page number is specified, but items per page are specified
        else if ((resourceURL.requestType == RequestType.SEARCH_LATEST_ITEMS
            || resourceURL.requestType == RequestType.SEARCH_MEMBER_CONTRIBUTIONS
            || resourceURL.requestType == RequestType.SEARCH_POPULAR
            || resourceURL.requestType == RequestType.SEARCH_SETS
            || resourceURL.requestType == RequestType.ACTIVITY_STREAM) && resourceURL.pageNumber == 0
            && resourceURL.itemsPerPage > 0) {
          
          URIBuilder builder = new URIBuilder()
              .setScheme(PROTOCOL)
              .setHost(HOST)
              .setPath(resourceURL.getPath())
              .addParameter(FORMAT_SPECIFIER, FORMAT)
              .addParameter(ITEMS_PER_PAGE_SPECIFIER, Integer.toString(resourceURL.itemsPerPage));

          return builder.build().toURL();
        }

        // In the case of search for recently-added items, member contributions, popular tunes, sets or activity streams
        // where no page number or items per page are specified
        else if ((resourceURL.requestType == RequestType.SEARCH_LATEST_ITEMS
            || resourceURL.requestType == RequestType.SEARCH_MEMBER_CONTRIBUTIONS
            || resourceURL.requestType == RequestType.SEARCH_POPULAR
            || resourceURL.requestType == RequestType.SEARCH_SETS
            || resourceURL.requestType == RequestType.ACTIVITY_STREAM) && resourceURL.pageNumber == 0
            && resourceURL.itemsPerPage == 0) {
          
          URIBuilder builder = new URIBuilder()
              .setScheme(PROTOCOL).setHost(HOST)
              .setPath(resourceURL.getPath())
              .addParameter(FORMAT_SPECIFIER, FORMAT);

          return builder.build().toURL();
        }
        
        // In the case of IndividualItem, which doesn't have page number or items per page options
        else if (resourceURL.requestType == RequestType.SINGLE_ITEM) {
          URIBuilder builder = new URIBuilder()
              .setScheme(PROTOCOL)
              .setHost(HOST)
              .setPath(resourceURL.getPath())
              .addParameter(FORMAT_SPECIFIER, FORMAT);

          return builder.build().toURL();
        }
        
        // If incorrect parameters were somehow provided
        else {
          throw new IllegalArgumentException("Invalid Parameters Provided");
        }
      }
      catch (MalformedURLException | URISyntaxException ex) {
        throw new IllegalArgumentException(ex.getMessage());
      }
    }
  }

  // Getters and setters
  public List<NameValuePair> getQueryParameters() {
    return queryParameters;
  }

  public void setQueryParameters(List<NameValuePair> queryParameters) {
    this.queryParameters = queryParameters;
  }

  public void setRequestType(RequestType requestType) {
    this.requestType = requestType;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public int getItemsPerPage() {
    return itemsPerPage;
  }

  public void setItemsPerPage(int itemsPerPage) {
    this.itemsPerPage = itemsPerPage;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }
}
