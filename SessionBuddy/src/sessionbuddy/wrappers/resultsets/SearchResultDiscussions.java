package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Discussion;
import sessionbuddy.wrappers.responsemetadata.KeywordSearchResultHeaders;

public class SearchResultDiscussions
{

    public KeywordSearchResultHeaders responseHeaders;
    
    public ArrayList<Discussion> searchResults;
    
    public SearchResultDiscussions(KeywordSearchResultHeaders responseHeaders, ArrayList<Discussion> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
