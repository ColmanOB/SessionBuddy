package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Tune;
import sessionbuddy.wrappers.responsemetadata.KeywordSearchResultHeaders;

public class SearchResultTunes
{

    public KeywordSearchResultHeaders responseHeaders;
    
    public ArrayList<Tune> searchResults;
    
    public SearchResultTunes(KeywordSearchResultHeaders responseHeaders, ArrayList<Tune> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
