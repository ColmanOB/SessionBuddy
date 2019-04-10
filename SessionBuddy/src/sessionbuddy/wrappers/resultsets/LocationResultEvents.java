package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Event;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersWithLocation;

/**
 * A structure to hold an individual event from a set of search results
 * 
 * The search may be a keyword-based search or a search for
 * recently-added sessions
 * 
 * @author Colman O'B
 * @since 2018-01-28
 */
public class LocationResultEvents
{    
    public ResponseHeadersWithLocation headers;
    
    public ArrayList<Event> results;
    
    public LocationResultEvents(ResponseHeadersWithLocation responseHeaders, ArrayList<Event> searchResults)
    {   
        this.headers = responseHeaders;
        this.results = searchResults;
    }
}
