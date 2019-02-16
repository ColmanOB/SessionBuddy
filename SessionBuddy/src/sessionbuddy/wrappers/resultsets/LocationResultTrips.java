package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.SearchResultSingleTrip;
import sessionbuddy.wrappers.responsemetadata.LocationSearchResultHeaders;

public class LocationResultTrips
{

    public LocationSearchResultHeaders responseHeaders;
    
    public ArrayList<SearchResultSingleTrip> searchResults;
    
    public LocationResultTrips(LocationSearchResultHeaders responseHeaders, ArrayList<SearchResultSingleTrip> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
