package sessionbuddy.wrappers.resultsets;

import java.util.ArrayList;

import sessionbuddy.wrappers.individualresultitems.TunePopular;
import sessionbuddy.wrappers.responsemetadata.ResponseHeaders;

/**
 * A structure to hold the result set returned in response
 * to a search for most popular tunes.
 *
 * @author Colman O'B
 * @since 2019-04-10
 */
public class PopularResultTunes
{
    /**
     * Header details / metadata relating to the search result set
     */
    public ResponseHeaders responseHeaders;
    
    /**
     * A list of individual tunes returned in response to the search
     */
    public ArrayList<TunePopular> searchResults;
    
    public PopularResultTunes(ResponseHeaders responseHeaders, ArrayList<TunePopular> searchResults)
    {   
        this.responseHeaders = responseHeaders;
        this.searchResults = searchResults;
    }
}
