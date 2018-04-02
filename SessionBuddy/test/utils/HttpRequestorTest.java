package utils;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import sessionbuddy.utils.HttpRequestor;

public class HttpRequestorTest 
	{
	// A simple test to ensure that HttpRequestor returns a JSON response containing some expected text
	
	@Test
	public void test() 
		{
		// A known good URL that should retrieve the details of the tune with ID 2, i.e. The Bucks of Oranmore
		String urlAsText = "https://thesession.org/tunes/2?format=json";
		
		try 
			{
			// Create a URL type object to be passed to the HttpRequestor.submitRequest(url) method
			URL url = new URL(urlAsText);
			
			// Invoke the method to be tested, passing in the 'known good' URL, and store the JSON response as a String
			String responseFromAPI = HttpRequestor.submitRequest(url);
			
			// The tune in question is The Bucks of Oranmore, so that should be part of the JSON response
			Assert.assertThat(responseFromAPI, CoreMatchers.containsString("Bucks Of Oranmore"));		
			} 
		
		catch (IOException e) 
			{
			// Catch a HTTP failure response, timeout etc. as well as a response not containing substring "Bucks Of Oranmore"
			fail(e.getMessage());		
			}
		}
	}
