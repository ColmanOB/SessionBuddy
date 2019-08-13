package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresultitems.Trip;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersWithLocation;

/**
 * A structure to hold the result set returned in response
 * to a location-based search for trips
 *
 * @author Colman O'B
 * @since 2019-04-10
 */
public class LocationResultTrips
{

    /**
     * Header details / metadata relating to the search result set
     */
    public ResponseHeadersWithLocation headers;
    
    /**
     * A list of individual trips returned in response to the search
     */
    public ArrayList<Trip> results;
    
    /**
     * Constructor
     * 
     * @param responseHeaders The headers returned in the JSON response from the API
     * @param searchResults The list of trips returned in the response from the API
     */
    public LocationResultTrips(ResponseHeadersWithLocation responseHeaders, ArrayList<Trip> searchResults)
    {   
        this.headers = responseHeaders;
        this.results = searchResults;
    }
}
