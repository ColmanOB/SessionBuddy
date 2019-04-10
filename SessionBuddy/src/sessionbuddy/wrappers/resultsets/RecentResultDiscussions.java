package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresultitems.Discussion;
import sessionbuddy.wrappers.responsemetadata.ResponseHeaders;

/**
 * A structure to hold the result set returned in response
 * to a search for most recently-added discussions
 *
 * @author Colman O'B
 * @since 2019-04-10
 */
public class RecentResultDiscussions
{
    /**
     * Header details / metadata relating to the search result set
     */
    public ResponseHeaders responseHeaders;
    
    /**
     * A list of individual discussions returned in response to the search
     */
    public ArrayList<Discussion> searchResults;
    
    public RecentResultDiscussions(ResponseHeaders responseHeaders, ArrayList<Discussion> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
