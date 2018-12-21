package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.SearchResultSingleTune;
import sessionbuddy.wrappers.responsemetadata.KeywordSearchResultHeaders;

public class SearchResultTunes
{

    public KeywordSearchResultHeaders responseHeaders;
    
    public ArrayList<SearchResultSingleTune> searchResults;
    
    public SearchResultTunes(KeywordSearchResultHeaders responseHeaders, ArrayList<SearchResultSingleTune> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
