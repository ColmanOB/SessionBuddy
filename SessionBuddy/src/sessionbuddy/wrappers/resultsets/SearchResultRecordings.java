package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Recording;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersKeywordSearch;

/**
 * A structure to hold an individual recording listing from a set of search
 * results 
 * 
 * The search may be a keyword-based search, or a search for
 * recently-added recordings
 * 
 * @author Colman O'B
 * @since 2018-01-28
 */
public class SearchResultRecordings
{
    public ResponseHeadersKeywordSearch responseHeaders;
    
    public ArrayList<Recording> searchResults;
    
    public SearchResultRecordings(ResponseHeadersKeywordSearch responseHeaders, ArrayList<Recording> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
