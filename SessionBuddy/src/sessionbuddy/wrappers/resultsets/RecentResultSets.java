package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Set;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersLatest;

public class RecentResultSets
{
    public ResponseHeadersLatest responseHeaders;
    
    public ArrayList<Set> searchResults;
    
    public RecentResultSets(ResponseHeadersLatest responseHeaders, ArrayList<Set> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
