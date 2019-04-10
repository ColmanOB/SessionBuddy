package sessionbuddy.wrappers.responsemetadata;

/**
 * Models the headers returned in the JSON response from thesession.org API
 * 
 * All responses contain the items in this class, and responses to some
 * specific types of search contain extra headers not included here.
 * 
 * This class is subclassed to cater for those other types of searches, 
 * specifically searches that involve a geographic location or a keyword search.
 * 
 * @author Colman O'B
 * @since 2019-04-10
 */
public class ResponseHeaders
{
    /**
     * The number of items returned per page in the response from the API
     */
    public int perpage;
    
    
    /**
     * The format of the API response, JSON in this case
     */
    public String format;
    
    
    /**
     * The number of pages in the API response
     */
    public int pages;
    
    /**
     * The number of the current page in the API response
     */
    public int page;
    
    /**
     * The total number of results returned
     */
    public int total;
    
    public ResponseHeaders(int perpage, String format, int pages, int page, int total)
    {
        this.perpage = perpage;
        this.format = format;
        this.pages = pages;
        this.page = page;
        this.total = total;
    }
}
