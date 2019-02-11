package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.SearchResultSingleEvent;
import sessionbuddy.wrappers.responsemetadata.LatestSearchResultHeaders;

public class SearchResultEventsLatest
{

    public LatestSearchResultHeaders responseHeaders;
    
    public ArrayList<SearchResultSingleEvent> searchResults;
    
    public SearchResultEventsLatest(LatestSearchResultHeaders responseHeaders, ArrayList<SearchResultSingleEvent> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
