package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.SearchResultTunes;
import sessionbuddy.wrappers.responsemetadata.KeywordSearchResultHeaders;

public class KeywordSearchResultTunes
{

    public KeywordSearchResultHeaders responseHeaders;
    
    public ArrayList<SearchResultTunes> searchResults;
    
    public KeywordSearchResultTunes(KeywordSearchResultHeaders responseHeaders, ArrayList<SearchResultTunes> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
