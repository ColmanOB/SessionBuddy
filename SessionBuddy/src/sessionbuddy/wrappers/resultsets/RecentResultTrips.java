package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Trip;
import sessionbuddy.wrappers.responsemetadata.ResponseHeaders;

public class RecentResultTrips
{

    public ResponseHeaders responseHeaders;
    
    public ArrayList<Trip> searchResults;
    
    public RecentResultTrips(ResponseHeaders responseHeaders, ArrayList<Trip> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
