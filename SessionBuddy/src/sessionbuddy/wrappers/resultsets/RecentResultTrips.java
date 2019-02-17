package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Trip;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersLatest;

public class RecentResultTrips
{

    public ResponseHeadersLatest responseHeaders;
    
    public ArrayList<Trip> searchResults;
    
    public RecentResultTrips(ResponseHeadersLatest responseHeaders, ArrayList<Trip> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
