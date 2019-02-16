package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.LocationResultSingleEvent;
import sessionbuddy.wrappers.responsemetadata.LocationSearchResultHeaders;

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
    public LocationSearchResultHeaders responseHeaders;
    
    public ArrayList<LocationResultSingleEvent> searchResults;
    
    public LocationResultEvents(LocationSearchResultHeaders responseHeaders, ArrayList<LocationResultSingleEvent> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
