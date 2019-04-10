package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.TuneLatest;
import sessionbuddy.wrappers.responsemetadata.ResponseHeaders;

public class RecentResultTunes
{

    public ResponseHeaders responseHeaders;
    
    public ArrayList<TuneLatest> searchResults;
    
    public RecentResultTunes(ResponseHeaders responseHeaders, ArrayList<TuneLatest> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
