package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresultitems.Trip;
import sessionbuddy.wrappers.responsemetadata.ResponseHeaders;

/**
 * A structure to hold the result set returned in response
 * to a search for most recently-added trips
 *
 * @author Colman O'B
 * @since 2019-04-10
 */
public class RecentResultTrips
{
    /**
     * Header details / metadata relating to the search result set
     */
    public ResponseHeaders responseHeaders;
    
    /**
     * A list of individual trips returned in response to the search
     */
    public ArrayList<Trip> searchResults;
    
    /**
     * Constructor
     * 
     * @param responseHeaders The headers returned in the JSON response from the API
     * @param searchResults The list of trips returned from the API
     */
    public RecentResultTrips(ResponseHeaders responseHeaders, ArrayList<Trip> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
