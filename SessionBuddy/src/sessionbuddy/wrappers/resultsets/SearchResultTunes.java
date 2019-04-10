package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresultitems.Tune;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersWithQueryText;

/**
 * A structure to hold the result set returned in response
 * to a keyword-based search for tunes
 *
 * @author Colman O'B
 * @since 2019-04-10
 */
public class SearchResultTunes
{
    /**
     * Header details / metadata relating to the search result set
     */
    public ResponseHeadersWithQueryText responseHeaders;
    
    /**
     * A list of individual tunes returned in response to the search
     */
    public ArrayList<Tune> searchResults;
    
    public SearchResultTunes(ResponseHeadersWithQueryText responseHeaders, ArrayList<Tune> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
