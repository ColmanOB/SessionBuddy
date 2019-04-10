package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresultitems.Event;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersWithQueryText;

/**
 * A structure to hold the result set returned in response
 * to a keyword-based search for events
 *
 * @author Colman O'B
 * @since 2019-04-10
 */
public class SearchResultEvents
{
    /**
     * Header details / metadata relating to the search result set
     */
    public ResponseHeadersWithQueryText responseHeaders;
    
    /**
     * A list of individual events returned in response to the search
     */
    public ArrayList<Event> searchResults;
    
    public SearchResultEvents(ResponseHeadersWithQueryText responseHeaders, ArrayList<Event> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
