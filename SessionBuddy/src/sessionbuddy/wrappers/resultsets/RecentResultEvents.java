package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Event;
import sessionbuddy.wrappers.responsemetadata.ResponseHeaders;

public class RecentResultEvents
{

    public ResponseHeaders responseHeaders;
    
    public ArrayList<Event> searchResults;
    
    public RecentResultEvents(ResponseHeaders responseHeaders, ArrayList<Event> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
