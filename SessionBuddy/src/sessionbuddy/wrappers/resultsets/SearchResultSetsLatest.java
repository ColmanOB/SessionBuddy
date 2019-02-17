package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Set;
import sessionbuddy.wrappers.responsemetadata.LatestSearchResultHeaders;

public class SearchResultSetsLatest
{
    public LatestSearchResultHeaders responseHeaders;
    
    public ArrayList<Set> searchResults;
    
    public SearchResultSetsLatest(LatestSearchResultHeaders responseHeaders, ArrayList<Set> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
