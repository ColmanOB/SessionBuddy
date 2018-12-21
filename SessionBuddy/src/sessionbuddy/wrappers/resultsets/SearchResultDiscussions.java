package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.SearchResultSingleDiscussion;
import sessionbuddy.wrappers.responsemetadata.KeywordSearchResultHeaders;

public class SearchResultDiscussions
{

    public KeywordSearchResultHeaders responseHeaders;
    
    public ArrayList<SearchResultSingleDiscussion> searchResults;
    
    public SearchResultDiscussions(KeywordSearchResultHeaders responseHeaders, ArrayList<SearchResultSingleDiscussion> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
