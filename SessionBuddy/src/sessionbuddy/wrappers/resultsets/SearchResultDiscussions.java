package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresultitems.Discussion;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersWithQueryText;

/**
 * A structure to hold the result set returned in response
 * to a keyword-based search for discussions
 *
 * @author Colman O'B
 * @since 2019-04-10
 */
public class SearchResultDiscussions
{
    /**
     * Header details / metadata relating to the search result set
     */
    public ResponseHeadersWithQueryText responseHeaders;
    
    /**
     * A list of individual discussions returned in response to the search
     */
    public ArrayList<Discussion> searchResults;
    
    /**
     * Constructor
     * 
     * @param responseHeaders The headers returned in the JSON response from the API
     * @param searchResults The list of discussions returned from the API
     */
    public SearchResultDiscussions(ResponseHeadersWithQueryText responseHeaders, ArrayList<Discussion> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
