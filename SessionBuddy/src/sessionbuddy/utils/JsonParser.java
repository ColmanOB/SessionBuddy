package sessionbuddy.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * Uses Google's Gson library to parse a JSON response from thesession.org API
 * into wrapper objects for ease of access to each element of the response.
 * 
 * @author Colman O'B
 * @since 2018-03-11
 */

public class JsonParser
{

    /**
     * Generic method using Gson to parse the JSON into a wrapper object of the type specified in the argument.
     * 
     * @param jsonResponse a string of JSON data retrieved from thesession.org API
     * @param wrapperType the type of wrapper object into which the JSON data should be parsed
     * @return a wrapper object of the type specified in the argument
     * @throws IllegalStateException if the JSON response is not valid JSON or does not match the expected structure defined in the corresponding wrapper class
     */
    public static <T> T parseResponse(String jsonResponse, Class<T> wrapperType) throws IllegalStateException
    {
        try
        {
            // A Gson object which we will use to parse a string of JSON
            Gson gson = new GsonBuilder().create();
            // Use Gson to parse the JSON into a wrapper object of the type passed as the argument
            T listOfResults = gson.fromJson(jsonResponse, wrapperType);
            // Return the wrapper object containing the response from the API
            return listOfResults;
        }
        catch (JsonSyntaxException e)
        {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
