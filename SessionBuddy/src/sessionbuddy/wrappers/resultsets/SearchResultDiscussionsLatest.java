package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.SearchResultSingleDiscussion;
import sessionbuddy.wrappers.responsemetadata.LatestSearchResultHeaders;

public class SearchResultDiscussionsLatest
{

    public LatestSearchResultHeaders responseHeaders;
    
    public ArrayList<SearchResultSingleDiscussion> searchResults;
    
    public SearchResultDiscussionsLatest(LatestSearchResultHeaders responseHeaders, ArrayList<SearchResultSingleDiscussion> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
