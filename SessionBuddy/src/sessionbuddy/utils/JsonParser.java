package sessionbuddy.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * Uses Google's Gson library to parse a JSON response from thesession.org API
 * into wrapper objects, for easy access to each element of the response.
 * 
 * @author Colman O'B
 * @since 2020-09-22
 */
public class JsonParser {

  /**
   * Uses Gson to parse a JSON String into a wrapper object to allow easy access to all elements of
   * the response, as follows:
   * 
   * 1. Get a Gson instance 
   * 2. Use the Gson to parse a JSON String into a wrapper object 
   * 3. Return the wrapper object, populated with the data from the JSON String
   * 
   * @param jsonResponse a string of JSON retrieved from thesession.org API
   * @param wrapperType the type of wrapper object into which the JSON should be parsed
   * @return a wrapper object of the type specified in the argument
   * @throws IllegalStateException if the JSON response is not valid JSON or does not match the structure defined in the wrapper class
   */
  public static <T> T parseResponse(String jsonResponse, Class<T> wrapperType) throws IllegalStateException {
    try {
      Gson gson = new GsonBuilder().create();
      T resultSet = gson.fromJson(jsonResponse, wrapperType);
      return resultSet;
    }

    catch (JsonSyntaxException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }
}
