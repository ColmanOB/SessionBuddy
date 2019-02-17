package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.TunePopular;
import sessionbuddy.wrappers.responsemetadata.LatestSearchResultHeaders;

public class PopularResultTunes
{

    public LatestSearchResultHeaders responseHeaders;
    
    public ArrayList<TunePopular> searchResults;
    
    public PopularResultTunes(LatestSearchResultHeaders responseHeaders, ArrayList<TunePopular> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
