package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Event;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersWithQueryText;

public class SearchResultEvents
{

    public ResponseHeadersWithQueryText responseHeaders;
    
    public ArrayList<Event> searchResults;
    
    public SearchResultEvents(ResponseHeadersWithQueryText responseHeaders, ArrayList<Event> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
