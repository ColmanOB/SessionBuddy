package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Trip;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersWithLocation;

public class LocationResultTrips
{

    public ResponseHeadersWithLocation headers;
    
    public ArrayList<Trip> results;
    
    public LocationResultTrips(ResponseHeadersWithLocation responseHeaders, ArrayList<Trip> searchResults)
    {   
        this.headers = responseHeaders;
        this.results = searchResults;
    }
}
