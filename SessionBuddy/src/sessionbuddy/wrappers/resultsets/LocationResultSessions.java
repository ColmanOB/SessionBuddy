package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Session;
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
public class LocationResultSessions
{    
    public ResponseHeadersWithLocation headers;
    
    public ArrayList<Session> results;
    
    public LocationResultSessions(ResponseHeadersWithLocation responseHeaders, ArrayList<Session> searchResults)
    {   
        this.headers = responseHeaders;
        this.results = searchResults;
    }
}
