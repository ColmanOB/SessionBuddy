package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresultitems.Recording;
import sessionbuddy.wrappers.responsemetadata.ResponseHeaders;

/**
 * A structure to hold the result set returned in response
 * to a search for most recently-added recordings
 *
 * @author Colman O'B
 * @since 2019-04-10
 */
public class RecentResultRecordings
{
    /**
     * Header details / metadata relating to the search result set
     */
    public ResponseHeaders responseHeaders;
    
    /**
     * A list of individual recordings returned in response to the search
     */
    public ArrayList<Recording> searchResults;
    
    /**
     * Constructor
     * 
     * @param responseHeaders The headers returned in the JSON response from the API
     * @param searchResults The list of recordings returned from the API
     */
    public RecentResultRecordings(ResponseHeaders responseHeaders, ArrayList<Recording> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
