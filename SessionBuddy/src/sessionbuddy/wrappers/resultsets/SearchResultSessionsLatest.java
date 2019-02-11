package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.SearchResultSingleSession;
import sessionbuddy.wrappers.responsemetadata.LatestSearchResultHeaders;

/**
 * A structure to hold an individual event from a set of search results
 * 
 * The search may be a keyword-based search or a search for
 * recently-added sessions
 * 
 * @author Colman O'B
 * @since 2018-01-28
 */
public class SearchResultSessionsLatest
{    
    public LatestSearchResultHeaders responseHeaders;
    
    public ArrayList<SearchResultSingleSession> searchResults;
    
    public SearchResultSessionsLatest(LatestSearchResultHeaders responseHeaders, ArrayList<SearchResultSingleSession> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
