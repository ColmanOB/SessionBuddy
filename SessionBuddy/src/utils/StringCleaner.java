package utils;

/**
 * Unescapes XML entities in a string
 * 
 * @author Colman O'B
 * @since 2017-09-03
 */

public class StringCleaner 
	{
	
	/**
	 * An iterative method to catch any XML entities in the response from the API and replace them with their standard characters
	 * 
	 * @param inputString a string that may contain XML entities
	 * @return a string with any XML entities replaced with their standard characters
	 */
	public static String cleanString(final String inputString) 
		{
	    StringBuilder outputString = new StringBuilder(inputString.length());
	    int i = 0;
	    int inputLength = inputString.length();
	    
	    // Iterate through each character of the input string, and look for anything starting with an & character
	    while (i < inputLength) 
	    	{
	        char currentChar = inputString.charAt(i);
	        
	        if (currentChar != '&') 
	        	{
	            outputString.append(currentChar);
	            i++;
	        	} 
	        
	        else 
	        	{
	        	// Handle three different ways an ampersand may be rendered
	            if (inputString.startsWith("&amp;", i)) 
	            	{
	                outputString.append('&');
	                i += 5;
	            	} 
	            
	            if (inputString.startsWith("&#038;", i)) 
	            	{
	                outputString.append('&');
	                i += 6;
	            	} 
	            
	            if (inputString.startsWith("&#38;", i)) 
	            	{
	                outputString.append('&');
	                i += 5;
	            	} 
	            
	            // Handle three different ways an apostrophe may be rendered
	            else if (inputString.startsWith("&apos;", i)) 
	            	{
	                outputString.append('\'');
	                i += 6;
	            	} 
	            
	            else if (inputString.startsWith("&#039;", i)) 
	            	{
	                outputString.append('\'');
	                i += 6;
	            	} 
	            
	            else if (inputString.startsWith("&#39;", i)) 
	            	{
	                outputString.append('\'');
	                i += 5;
	            	} 
	            
	            // Handle three ways double quotes might be rendered
	            else if (inputString.startsWith("&quot;", i)) 
	            	{
	                outputString.append('"');
	                i += 6;
	            	} 
	            
	            else if (inputString.startsWith("&#034;", i)) 
	            	{
	                outputString.append('"');
	                i += 6;
	            	} 
	            
	            else if (inputString.startsWith("&#34;", i)) 
	            	{
	                outputString.append('"');
	                i += 5;
	            	} 
	            
	            // Handle three ways a less-than character might be rendered
	            else if (inputString.startsWith("&lt;", i)) 
	            	{
	                outputString.append('<');
	                i += 4;
	            	} 
	            
	            else if (inputString.startsWith("&#060;", i)) 
	            	{
	                outputString.append('<');
	                i += 6;
	            	} 
	            
	            else if (inputString.startsWith("&#60;", i)) 
	            	{
	                outputString.append('<');
	                i += 5;
	            	} 
	            
	            // Handle three ways a greater-than character might be rendered
	            else if (inputString.startsWith("&gt;", i)) 
	            	{
	                outputString.append('>');
	                i += 4;
	            	} 
	            
	            else if (inputString.startsWith("&#062;", i)) 
	            	{
	                outputString.append('>');
	                i += 6;
	            	}
	            
	            else if (inputString.startsWith("&#62;", i)) 
	            	{
	                outputString.append('>');
	                i += 5;
	            	}
	            
	            else i++;
	        	}
	    	}    
	    return outputString.toString();
		}
	
	}
