package sessionbuddy.utils;

public class PageCountValidator {
  
  /**
   * Validate that the user has specified between 1-50 results per page
   * 
   * @param resultsPerPage the number of results to be returned per page in the JSON response
   * @return true if a valid number of results per page has been provided (i.e. 1 - 50)
   * @throws IllegalArgumentException if a value of less than 1 or greater than 50 was provided
   */
  public static boolean validate(int resultsPerPage) throws IllegalArgumentException {
    if (resultsPerPage <= 0) {
      throw new IllegalArgumentException("Number of results per page must be greater than zero");
    }
    if (resultsPerPage > 50) {
      throw new IllegalArgumentException("Number of results per page cannot exceed 50");
    }
    // The value is between 1 - 50 and is valid
    else
      return true;
  }
}
