package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresultitems.TuneLatest;
import sessionbuddy.wrappers.responsemetadata.ResponseHeaders;

/**
 * A structure to hold the result set returned in response
 * to a search for most recently-added tunes
 *
 * @author Colman O'B
 * @since 2019-04-10
 */
public class RecentResultTunes
{
    /**
     * Header details / metadata relating to the search result set
     */
    public ResponseHeaders responseHeaders;
    
    /**
     * A list of individual tunes returned in response to the search
     */
    public ArrayList<TuneLatest> searchResults;
    
    /**
     * Constructor
     * 
     * @param responseHeaders The headers returned in the JSON response from the API
     * @param searchResults The list of tunes returned from the API
     */
    public RecentResultTunes(ResponseHeaders responseHeaders, ArrayList<TuneLatest> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
