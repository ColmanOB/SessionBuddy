package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Discussion;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersKeywordSearch;

public class SearchResultDiscussions
{

    public ResponseHeadersKeywordSearch responseHeaders;
    
    public ArrayList<Discussion> searchResults;
    
    public SearchResultDiscussions(ResponseHeadersKeywordSearch responseHeaders, ArrayList<Discussion> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
