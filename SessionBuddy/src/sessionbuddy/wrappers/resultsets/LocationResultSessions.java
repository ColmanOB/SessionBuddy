package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresultitems.Session;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersWithLocation;

/**
 * A structure to hold the result set returned in response
 * to a location-based search for sessions
 *
 * @author Colman O'B
 * @since 2019-04-10
 */
public class LocationResultSessions
{    
    /**
     * Header details / metadata relating to the search result set
     */
    public ResponseHeadersWithLocation headers;
    
    /**
     * A list of individual sessions returned in response to the search
     */
    public ArrayList<Session> results;
    
    /**
     * Constructor
     * 
     * @param responseHeaders The headers returned in the JSON response from the API
     * @param searchResults The list of sessions returned in the response from the API
     */
    public LocationResultSessions(ResponseHeadersWithLocation responseHeaders, ArrayList<Session> searchResults)
    {   
        this.headers = responseHeaders;
        this.results = searchResults;
    }
}
