package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Discussion;
import sessionbuddy.wrappers.responsemetadata.ResponseHeaders;

public class RecentResultDiscussions
{

    public ResponseHeaders responseHeaders;
    
    public ArrayList<Discussion> searchResults;
    
    public RecentResultDiscussions(ResponseHeaders responseHeaders, ArrayList<Discussion> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
