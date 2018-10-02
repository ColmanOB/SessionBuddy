package sessionbuddy.utils;

/**
 * Unescapes XML entities in a string
 * 
 * @author Colman O'B
 * @since 2018-02-17
 */
public class StringCleaner
{
    /**
     * An iterative method to catch any XML entities in the response from the API 
     * and replace them with their standard characters.
     * 
     * This is tricky because the API is not always consistent in how it returns XML entities. 
     * e.g. an ampersand may be returned as "&amp;", "&#038;", "&#38;", or even an unescaped & character
     * 
     * @param inputString a string that may contain XML entities
     * @return a string with any XML entities replaced with their standard characters
     */
    public static String cleanString(final String inputString)
    {
        StringBuilder outputString = new StringBuilder(inputString.length());
        int i = 0;
        int inputLength = inputString.length();

        // Iterate through each character of the input string, looking for anything starting with an & character
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
                else if (inputString.startsWith("&#038;", i))
                {
                    outputString.append('&');
                    i += 6;
                }
                else if (inputString.startsWith("&#38;", i))
                {
                    outputString.append('&');
                    i += 5;
                }
                // Cater for cases where an actual ampersand is present
                else if (inputString.startsWith("& ", i))
                {
                    outputString.append('&');
                    i += 1;
                }
                // Handle four different ways an apostrophe may be rendered 
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
                else if (inputString.startsWith("&#8217;", i))
                {
                    outputString.append('\'');
                    i += 7;
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
                // Cater for cases where an ampersand exists in the abc notation
                else if (inputString.startsWith("&", i))
                {
                    outputString.append('&');
                    i += 1;
                }
                // If still no match, skip the character and proceed to the next one
                else
                    i++;
            }
        }
        return outputString.toString();
    }
}
