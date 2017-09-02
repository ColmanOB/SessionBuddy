package utils;

/**
 * Unescapes XML entities in a string
 * 
 * The API returns "&amp;" in place of ampersands, and "&#39;" in place of apostrophes, so cleanString(String s) restores the correct characters
 * 
 * @author Colman O'B
 * @since 2017-09-02
 */
public class StringCleaner 
	{
	
	/**
	 * Unescapes XML entities in a string.  Currently only supports replacement of "&amp;" and "&#39;", but this may be expanded in a future version
	 * 
	 * @param inputString
	 * @return a String with any occurences of "amp;" replaced with & and "&#39;" with '
	 */
	public static String cleanString(String inputString)
		{
		String outputString = inputString;
		
		// Replace any "&#39;" with an apostrophe
		if(inputString.contains("&#39;"))
			{	
			outputString = inputString.replace("&#39;","'");
			}
		
		// Replace any "&amp;" with an ampersand
		if(outputString.contains("&amp;"))
			{	
			outputString = inputString.replace("&amp;","&");
			}
		
		// If no XML entities, are present, just return the input string unaltered
		else outputString = inputString;
		
		return outputString;
		}
	
	}
