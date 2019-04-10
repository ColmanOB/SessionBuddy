package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresultitems.Recording;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersWithQueryText;

/**
 * A structure to hold the result set returned in response
 * to a keyword-based search for recordings
 *
 * @author Colman O'B
 * @since 2019-04-10
 */
public class SearchResultRecordings
{
    /**
     * Header details / metadata relating to the search result set
     */
    public ResponseHeadersWithQueryText responseHeaders;
    
    /**
     * A list of individual recordings returned in response to the search
     */
    public ArrayList<Recording> searchResults;
    
    public SearchResultRecordings(ResponseHeadersWithQueryText responseHeaders, ArrayList<Recording> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
