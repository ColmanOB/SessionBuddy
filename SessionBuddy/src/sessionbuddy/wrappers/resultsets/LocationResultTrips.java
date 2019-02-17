package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Trip;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersLocation;

public class LocationResultTrips
{

    public ResponseHeadersLocation headers;
    
    public ArrayList<Trip> results;
    
    public LocationResultTrips(ResponseHeadersLocation responseHeaders, ArrayList<Trip> searchResults)
    {   
        this.headers = responseHeaders;
        this.results = searchResults;
    }
}
