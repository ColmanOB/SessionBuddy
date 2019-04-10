package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.TunePopular;
import sessionbuddy.wrappers.responsemetadata.ResponseHeaders;

public class PopularResultTunes
{

    public ResponseHeaders responseHeaders;
    
    public ArrayList<TunePopular> searchResults;
    
    public PopularResultTunes(ResponseHeaders responseHeaders, ArrayList<TunePopular> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
