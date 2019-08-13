package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresultitems.Event;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersWithLocation;

/**
 * A structure to hold the result set returned in response to 
 * a location-based search for events
 * 
 * @author Colman O'B
 * @since 2019-04-10
 */
public class LocationResultEvents
{    
    /**
     * Header details / metadata relating to the search result set
     */
    public ResponseHeadersWithLocation headers;
    
    /**
     * A list of individual events returned in response to the search
     */
    public ArrayList<Event> results;
    
    /**
     * Constructor
     * 
     * @param responseHeaders The headers returned in the JSON response from the API
     * @param searchResults The list of events returned in the response from the API
     */
    public LocationResultEvents(ResponseHeadersWithLocation responseHeaders, ArrayList<Event> searchResults)
    {   
        this.headers = responseHeaders;
        this.results = searchResults;
    }
}
