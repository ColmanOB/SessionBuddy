package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.SearchResultSingleTrip;
import sessionbuddy.wrappers.responsemetadata.LatestSearchResultHeaders;

public class SearchResultTripsLatest
{

    public LatestSearchResultHeaders responseHeaders;
    
    public ArrayList<SearchResultSingleTrip> searchResults;
    
    public SearchResultTripsLatest(LatestSearchResultHeaders responseHeaders, ArrayList<SearchResultSingleTrip> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
