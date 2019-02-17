package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Tune;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersKeywordSearch;

public class SearchResultTunes
{

    public ResponseHeadersKeywordSearch responseHeaders;
    
    public ArrayList<Tune> searchResults;
    
    public SearchResultTunes(ResponseHeadersKeywordSearch responseHeaders, ArrayList<Tune> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
