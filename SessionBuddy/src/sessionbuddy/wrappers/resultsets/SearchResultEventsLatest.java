package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Event;
import sessionbuddy.wrappers.responsemetadata.LatestSearchResultHeaders;

public class SearchResultEventsLatest
{

    public LatestSearchResultHeaders responseHeaders;
    
    public ArrayList<Event> searchResults;
    
    public SearchResultEventsLatest(LatestSearchResultHeaders responseHeaders, ArrayList<Event> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
