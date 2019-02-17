package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Discussion;
import sessionbuddy.wrappers.responsemetadata.ResponseHeadersLatest;

public class RecentResultDiscussions
{

    public ResponseHeadersLatest responseHeaders;
    
    public ArrayList<Discussion> searchResults;
    
    public RecentResultDiscussions(ResponseHeadersLatest responseHeaders, ArrayList<Discussion> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
