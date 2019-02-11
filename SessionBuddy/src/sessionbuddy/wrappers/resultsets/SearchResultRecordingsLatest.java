package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.SearchResultSingleRecording;
import sessionbuddy.wrappers.responsemetadata.LatestSearchResultHeaders;

/**
 * A structure to hold an individual recording listing from a set of search
 * results 
 * 
 * The search may be a keyword-based search, or a search for
 * recently-added recordings
 * 
 * @author Colman O'B
 * @since 2018-01-28
 */
public class SearchResultRecordingsLatest
{
    public LatestSearchResultHeaders responseHeaders;
    
    public ArrayList<SearchResultSingleRecording> searchResults;
    
    public SearchResultRecordingsLatest(LatestSearchResultHeaders responseHeaders, ArrayList<SearchResultSingleRecording> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
