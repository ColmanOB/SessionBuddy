package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.LocationResultSingleSession;
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
public class LocationResultSessions
{    
    public LocationSearchResultHeaders responseHeaders;
    
    public ArrayList<LocationResultSingleSession> searchResults;
    
    public LocationResultSessions(LocationSearchResultHeaders responseHeaders, ArrayList<LocationResultSingleSession> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
