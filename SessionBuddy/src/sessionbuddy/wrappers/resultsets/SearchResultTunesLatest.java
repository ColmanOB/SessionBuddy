package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.SearchResultSingleTuneLatest;
import sessionbuddy.wrappers.responsemetadata.LatestSearchResultHeaders;

public class SearchResultTunesLatest
{

    public LatestSearchResultHeaders responseHeaders;
    
    public ArrayList<SearchResultSingleTuneLatest> searchResults;
    
    public SearchResultTunesLatest(LatestSearchResultHeaders responseHeaders, ArrayList<SearchResultSingleTuneLatest> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
