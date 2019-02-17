package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Event;
import sessionbuddy.wrappers.responsemetadata.KeywordSearchResultHeaders;

public class SearchResultEvents
{

    public KeywordSearchResultHeaders responseHeaders;
    
    public ArrayList<Event> searchResults;
    
    public SearchResultEvents(KeywordSearchResultHeaders responseHeaders, ArrayList<Event> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
