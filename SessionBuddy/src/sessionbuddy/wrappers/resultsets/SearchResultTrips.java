package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresults.SearchResultSingleTrip;
import sessionbuddy.wrappers.responsemetadata.KeywordSearchResultHeaders;

public class SearchResultTrips
{

    public KeywordSearchResultHeaders responseHeaders;
    
    public ArrayList<SearchResultSingleTrip> searchResults;
    
    public SearchResultTrips(KeywordSearchResultHeaders responseHeaders, ArrayList<SearchResultSingleTrip> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
