package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.Trip;
import sessionbuddy.wrappers.responsemetadata.KeywordSearchResultHeaders;

public class SearchResultTrips
{

    public KeywordSearchResultHeaders responseHeaders;
    
    public ArrayList<Trip> searchResults;
    
    public SearchResultTrips(KeywordSearchResultHeaders responseHeaders, ArrayList<Trip> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
