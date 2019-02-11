package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.SearchResultSingleSet;
import sessionbuddy.wrappers.responsemetadata.LatestSearchResultHeaders;

public class SearchResultSetsLatest
{
    public LatestSearchResultHeaders responseHeaders;
    
    public ArrayList<SearchResultSingleSet> searchResults;
    
    public SearchResultSetsLatest(LatestSearchResultHeaders responseHeaders, ArrayList<SearchResultSingleSet> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
