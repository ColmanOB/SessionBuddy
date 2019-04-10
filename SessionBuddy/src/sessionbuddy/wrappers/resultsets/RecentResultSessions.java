package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Session;
import sessionbuddy.wrappers.responsemetadata.ResponseHeaders;

/**
 * A structure to hold an individual event from a set of search results
 * 
 * The search may be a keyword-based search or a search for
 * recently-added sessions
 * 
 * @author Colman O'B
 * @since 2018-01-28
 */
public class RecentResultSessions
{    
    public ResponseHeaders responseHeaders;
    
    public ArrayList<Session> searchResults;
    
    public RecentResultSessions(ResponseHeaders responseHeaders, ArrayList<Session> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
