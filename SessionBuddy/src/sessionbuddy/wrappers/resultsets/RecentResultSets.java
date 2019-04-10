package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Set;
import sessionbuddy.wrappers.responsemetadata.ResponseHeaders;

public class RecentResultSets
{
    public ResponseHeaders responseHeaders;
    
    public ArrayList<Set> searchResults;
    
    public RecentResultSets(ResponseHeaders responseHeaders, ArrayList<Set> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
