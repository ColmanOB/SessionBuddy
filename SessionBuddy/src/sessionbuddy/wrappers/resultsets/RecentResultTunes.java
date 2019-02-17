package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.TuneLatest;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersLatest;

public class RecentResultTunes
{

    public ResponseHeadersLatest responseHeaders;
    
    public ArrayList<TuneLatest> searchResults;
    
    public RecentResultTunes(ResponseHeadersLatest responseHeaders, ArrayList<TuneLatest> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
