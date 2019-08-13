package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresultitems.Set;
import sessionbuddy.wrappers.responsemetadata.ResponseHeaders;

/**
 * A structure to hold the result set returned in response
 * to a search for most recently-added sets
 *
 * @author Colman O'B
 * @since 2019-04-10
 */
public class RecentResultSets
{
    /**
     * Header details / metadata relating to the search result set
     */
    public ResponseHeaders responseHeaders;
    
    /**
     * A list of individual sets returned in response to the search
     */
    public ArrayList<Set> searchResults;
    
    /**
     * Constructor
     * 
     * @param responseHeaders The headers returned in the JSON response from the API
     * @param searchResults The list of sets returned from the API
     */
    public RecentResultSets(ResponseHeaders responseHeaders, ArrayList<Set> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
