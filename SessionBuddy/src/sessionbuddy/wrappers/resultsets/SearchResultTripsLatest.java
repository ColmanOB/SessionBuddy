package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Trip;
import sessionbuddy.wrappers.responsemetadata.LatestSearchResultHeaders;

public class SearchResultTripsLatest
{

    public LatestSearchResultHeaders responseHeaders;
    
    public ArrayList<Trip> searchResults;
    
    public SearchResultTripsLatest(LatestSearchResultHeaders responseHeaders, ArrayList<Trip> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
