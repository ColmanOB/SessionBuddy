package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Discussion;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersWithQueryText;

public class SearchResultDiscussions
{

    public ResponseHeadersWithQueryText responseHeaders;
    
    public ArrayList<Discussion> searchResults;
    
    public SearchResultDiscussions(ResponseHeadersWithQueryText responseHeaders, ArrayList<Discussion> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
