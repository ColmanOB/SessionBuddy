package sessionbuddy;

/**
 * A superclass of the KeywordSearch, LatestSearch and LocationSearch classes.
 * This class is unlikely to ever be instantiated directly - it just holds
 * common fields and methods shared by the three subclasses.
 * 
 * @author Colman
 * @since 2017-09-06
 */
public class Search
{
    
    /**
     * Keeps track of how many pages are in a JSON response
     */
    protected static int pageCount = 0;

    /**
     * Helper method to validate that the user has specified a value between
     * 1-50 for the results per page
     * 
     * @param resultsPerPage the number of results to be returned per page in the JSON response from the API
     * @return true if a valid number of results per page has been provided (i.e. 1 - 50)
     * @throws IllegalArgumentException if a value of zero or less, or a value of more than 50 was provided
     */
    protected static boolean validateResultsPerPageCount(int resultsPerPage) throws IllegalArgumentException
    {
        if (resultsPerPage <= 0)
        {
            throw new IllegalArgumentException("Number of results per page must be greater than zero");
        }
        if (resultsPerPage > 50)
        {
            throw new IllegalArgumentException("Number of results per page cannot exceed 50");
        }
        // The value specified is in the range 1 - 50 and is valid
        else
            return true;
    }

    /**
     * Helper method to keep track of the number of pages in the JSON response
     * form the API
     * 
     * @return the number of pages in the JSON reponse
     * @throws IllegalStateException when the pageCount variable has not been initialised
     */
    protected int getPageCount() throws IllegalStateException
    {

        if (pageCount == 0)
        {
            throw new IllegalStateException("Page counter has not been initialised");
        }
        else
            return pageCount;
    }

}
