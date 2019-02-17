package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Event;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersLatest;

public class RecentResultEvents
{

    public ResponseHeadersLatest responseHeaders;
    
    public ArrayList<Event> searchResults;
    
    public RecentResultEvents(ResponseHeadersLatest responseHeaders, ArrayList<Event> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
