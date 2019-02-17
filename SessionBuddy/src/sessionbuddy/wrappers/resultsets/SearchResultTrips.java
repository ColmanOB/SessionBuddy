package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Trip;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersKeywordSearch;

public class SearchResultTrips
{

    public ResponseHeadersKeywordSearch responseHeaders;
    
    public ArrayList<Trip> searchResults;
    
    public SearchResultTrips(ResponseHeadersKeywordSearch responseHeaders, ArrayList<Trip> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
