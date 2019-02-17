package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.TunePopular;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersLatest;

public class PopularResultTunes
{

    public ResponseHeadersLatest responseHeaders;
    
    public ArrayList<TunePopular> searchResults;
    
    public PopularResultTunes(ResponseHeadersLatest responseHeaders, ArrayList<TunePopular> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
