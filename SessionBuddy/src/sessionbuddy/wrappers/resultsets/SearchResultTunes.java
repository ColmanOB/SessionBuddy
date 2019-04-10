package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Tune;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersWithQueryText;

public class SearchResultTunes
{

    public ResponseHeadersWithQueryText responseHeaders;
    
    public ArrayList<Tune> searchResults;
    
    public SearchResultTunes(ResponseHeadersWithQueryText responseHeaders, ArrayList<Tune> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
