package sessionbuddy.utils;

public class PageCountValidator
{
    /**
     * Helper method to validate that the user has specified a value between
     * 1-50 for the results per page
     * 
     * @param resultsPerPage the number of results to be returned per page in the JSON response from the API
     * @return true if a valid number of results per page has been provided (i.e. 1 - 50)
     * @throws IllegalArgumentException if a value of zero or less, or a value of more than 50 was provided
     */
    public static boolean validate(int resultsPerPage) throws IllegalArgumentException
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
}
