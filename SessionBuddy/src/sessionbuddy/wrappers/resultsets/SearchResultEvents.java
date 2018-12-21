package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.SearchResultSingleDiscussion;
import sessionbuddy.wrappers.individualresults.SearchResultSingleEvent;
import sessionbuddy.wrappers.responsemetadata.KeywordSearchResultHeaders;

public class SearchResultEvents
{

    public KeywordSearchResultHeaders responseHeaders;
    
    public ArrayList<SearchResultSingleEvent> searchResults;
    
    public SearchResultEvents(KeywordSearchResultHeaders responseHeaders, ArrayList<SearchResultSingleEvent> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
