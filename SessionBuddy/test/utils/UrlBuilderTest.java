package utils;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import sessionbuddy.utils.UrlBuilder;

public class UrlBuilderTest 
	{

	@Test
	public void testKeywordSearchURLWithPagination() 
		{
		UrlBuilder builder = new UrlBuilder();
		
		String searchTerms = "wig glue";
		int resultsPerPage = 5;
		int pageNumber = 2;
		List<NameValuePair> queryParams = new ArrayList<>();
		queryParams.add(new BasicNameValuePair("q", searchTerms));
		
		URL requestURL = builder.new Builder()
				.path("discussions" + "/" + "search")
				.queryParameters(queryParams)
				.itemsPerPage(resultsPerPage)
				.pageNumber(pageNumber)
				.build();
		
		// Test that the sequence "&amp;" is replaced by a single ampersand
		assertEquals("https://thesession.org/discussions/search?q=wig+glue&format=json&perpage=5&page=2", requestURL.toString());
		}

	}
