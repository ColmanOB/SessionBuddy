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
     * A String representing the latitude and longitude of a geographic point
     */
    public String latlon;
    
    /**
     * A radius in Kilometres around the specified geographic point
     */
    public String radius;
    
    public ResponseHeadersWithLocation(String latlon, String radius, int perPage, String format, int pages, int page, int total)
    {
        super(perPage, format, pages, page, total);
        this.latlon = latlon;
        this.radius = radius;
    }
}
