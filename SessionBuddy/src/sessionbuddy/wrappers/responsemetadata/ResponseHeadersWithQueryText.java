package sessionbuddy.wrappers.responsemetadata;

/**
 * Models the headers returned in the JSON response from thesession.org API, 
 * specifically where a request has been made that involved a keyword search.
 * 
 * @author Colman O'B
 * @since 2019-04-10
 */
public class ResponseHeadersWithQueryText extends ResponseHeaders {
  /**
   * The text string provided as the query, i.e. the keyword(s) used in the search
   */
  public String q;

  /**
   * Constructor used to initialise all fields
   * 
   * @param q The text string / keywords used when querying the API
   * @param perpage The number of search results returned per page from the API
   * @param format The format of the response data, always JSON for this project
   * @param pages The number of pages of data in the JSON response
   * @param page The current page in the JSON response
   * @param total The total number of search results in the result set
   */
  public ResponseHeadersWithQueryText(String q, int perPage, String format, int pages, int page, int total) {
    super(perPage, format, pages, page, total);
    this.q = q;
  }
}
