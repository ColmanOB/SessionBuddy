package sessionbuddy.wrappers.responsemetadata;

/**
 * Models the headers returned in the JSON response from thesession.org API,
 * specifically where a request has been made that involved a geographic search.
 * 
 * @author Colman O'B
 * @since 2019-04-10
 */
public class ResponseHeadersWithLocation extends ResponseHeaders
{    
    /**
     * A String representing the latitude and longitude of a geographic point separated with a comma
     */
    public String latlon;
    
    /**
     * A radius in Kilometres around the specified geographic point
     */
    public String radius;
    
    /**
     * Constructor used to initialise all fields
     * 
     * @param latlon A String representing the latitude and longitude of a geographic point separated with a comma
     * @param radius A radius in Kilometres around the specified geographic point
     * @param perpage The number of search results returned per page from the API
     * @param format The format of the response data, always JSON for this project
     * @param pages The number of pages of data in the JSON response
     * @param page The current page in the JSON response
     * @param total The total number of search results in the result set
     */
    public ResponseHeadersWithLocation(String latlon, String radius, int perPage, String format, int pages, int page, int total)
    {
        super(perPage, format, pages, page, total);
        this.latlon = latlon;
        this.radius = radius;
    }
}
