package sessionbuddy.utils;

/**
 * An enumeration of different categories of request / search.
 * 
 * This is aimed at making it easier to construct the URL required 
 * to carry out any given operation, as the category of request / search 
 * is one of the key pieces of information that determines the URL to be used.
 * 
 * @author Colman since 2018-05-08
 */
public enum RequestType
{
    SINGLE_ITEM, 
    SEARCH_BY_KEYWORD, 
    SEARCH_BY_LOCATION, 
    SEARCH_LATEST_ITEMS, 
    SEARCH_MEMBER_CONTRIBUTIONS, 
    SEARCH_POPULAR, 
    SEARCH_SETS
}
