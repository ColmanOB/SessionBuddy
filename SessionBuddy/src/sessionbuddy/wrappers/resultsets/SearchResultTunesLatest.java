package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.TuneLatest;
import sessionbuddy.wrappers.responsemetadata.LatestSearchResultHeaders;

public class SearchResultTunesLatest
{

    public LatestSearchResultHeaders responseHeaders;
    
    public ArrayList<TuneLatest> searchResults;
    
    public SearchResultTunesLatest(LatestSearchResultHeaders responseHeaders, ArrayList<TuneLatest> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
