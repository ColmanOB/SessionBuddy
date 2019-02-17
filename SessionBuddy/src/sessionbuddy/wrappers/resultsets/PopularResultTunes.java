package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.PopularResultSingleTune;
import sessionbuddy.wrappers.responsemetadata.LatestSearchResultHeaders;

public class PopularResultTunes
{

    public LatestSearchResultHeaders responseHeaders;
    
    public ArrayList<PopularResultSingleTune> searchResults;
    
    public PopularResultTunes(LatestSearchResultHeaders responseHeaders, ArrayList<PopularResultSingleTune> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
