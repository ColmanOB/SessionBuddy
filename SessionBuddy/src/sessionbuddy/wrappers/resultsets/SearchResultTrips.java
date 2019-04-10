package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Trip;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersWithQueryText;

public class SearchResultTrips
{

    public ResponseHeadersWithQueryText responseHeaders;
    
    public ArrayList<Trip> searchResults;
    
    public SearchResultTrips(ResponseHeadersWithQueryText responseHeaders, ArrayList<Trip> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
