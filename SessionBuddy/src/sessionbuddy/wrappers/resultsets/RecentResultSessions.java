package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresultitems.Session;
import sessionbuddy.wrappers.responsemetadata.ResponseHeaders;

/**
 * A structure to hold the result set returned in response
 * to a search for most recently-added settings
 *
 * @author Colman O'B
 * @since 2019-04-10
 */
public class RecentResultSessions
{    
    /**
     * Header details / metadata relating to the search result set
     */
    public ResponseHeaders responseHeaders;
    
    /**
     * A list of individual sessions returned in response to the search
     */
    public ArrayList<Session> searchResults;
    
    /**
     * Constructor
     * 
     * @param responseHeaders The headers returned in the JSON response from the API
     * @param searchResults The list of sessions returned from the API
     */
    public RecentResultSessions(ResponseHeaders responseHeaders, ArrayList<Session> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
