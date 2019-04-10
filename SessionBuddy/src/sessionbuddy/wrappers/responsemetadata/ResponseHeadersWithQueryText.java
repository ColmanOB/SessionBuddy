package sessionbuddy.wrappers.responsemetadata;

/**
 * Models the headers returned in the JSON response from thesession.org API,
 * specifically where a request has been made that involved a keyword search.
 * 
 * @author Colman O'B
 * @since 2019-04-10
 */
public class ResponseHeadersWithQueryText extends ResponseHeaders
{
    /**
     * The text string provided as the query, i.e. the keyword(s) used in the search
     */
    public String q;
    
    public ResponseHeadersWithQueryText(String q, int perPage, String format, int pages, int page, int total)
    {
        super(perPage, format, pages, page, total);
        this.q = q;
    }
}
