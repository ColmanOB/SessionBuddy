package example_usage;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import sessionbuddy.utils.UrlBuilder;

public class Test_UrlBuilder 
	{

	public static void main(String[] args) 
		{
		URL url;
		List<NameValuePair> queryParams = new ArrayList<>();
		queryParams.add(new BasicNameValuePair("q", "wig glue"));
		
		try 
			{
			url = UrlBuilder.buildURL("discussions", "search", queryParams, 5, 1);
			
			System.out.println(url.toString());
			} 
		
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}

}
