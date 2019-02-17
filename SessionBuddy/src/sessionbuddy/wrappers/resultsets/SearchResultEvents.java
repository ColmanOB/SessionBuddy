package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Event;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersKeywordSearch;

public class SearchResultEvents
{

    public ResponseHeadersKeywordSearch responseHeaders;
    
    public ArrayList<Event> searchResults;
    
    public SearchResultEvents(ResponseHeadersKeywordSearch responseHeaders, ArrayList<Event> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
