package utils;

/**
 * Description goes here
 * 
 * @author Colman O'B
 * @since 2017-09-02
 */
public class StringCleaner 
	{
	
	/**
	 * @param inputString
	 * @return
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
		
		else outputString = inputString;
		
		return outputString;
		}
	
	}
