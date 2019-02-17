package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Discussion;
import sessionbuddy.wrappers.responsemetadata.LatestSearchResultHeaders;

public class SearchResultDiscussionsLatest
{

    public LatestSearchResultHeaders responseHeaders;
    
    public ArrayList<Discussion> searchResults;
    
    public SearchResultDiscussionsLatest(LatestSearchResultHeaders responseHeaders, ArrayList<Discussion> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
