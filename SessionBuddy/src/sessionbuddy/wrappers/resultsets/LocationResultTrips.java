package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Trip;
import sessionbuddy.wrappers.responsemetadata.LocationSearchResultHeaders;

public class LocationResultTrips
{

    public LocationSearchResultHeaders responseHeaders;
    
    public ArrayList<Trip> searchResults;
    
    public LocationResultTrips(LocationSearchResultHeaders responseHeaders, ArrayList<Trip> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
