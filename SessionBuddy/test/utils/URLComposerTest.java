package utils;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import sessionbuddy.utils.RequestType;
import sessionbuddy.utils.URLComposer;

/**
 * Tests the UrlBuilder class to ensure it constructs the API URL in the correct format, based on the data being requested
 * 
 * @author Colman
 * @since 2018-04-01
 */
public class URLComposerTest 
	{
	
	// Test building a URL to retrieve an item by ID
	
	@Test
	public void testItemRetrieverURL() 
		{
		URLComposer ub = new URLComposer();
		String dataCategory = "tunes";
		int itemID = 2;

		URL requestURL = ub.new Builder()
				.requestType(RequestType.SINGLE_ITEM)
				.path(dataCategory + "/" + itemID)
				.build();
		
		assertEquals("https://thesession.org/tunes/2?format=json", requestURL.toString());
		}
	

	// Test building a URL to perform a keyword search
	
	@Test
	public void testKeywordSearchURLWithPaginationAndItemsPerPage() 
		{
		URLComposer ub = new URLComposer();	
		String searchTerms = "wig glue";
		int resultsPerPage = 5;
		int pageNumber = 2;
		List<NameValuePair> queryParams = new ArrayList<>();
		queryParams.add(new BasicNameValuePair("q", searchTerms));

		URL requestURL = ub.new Builder()
				.requestType(RequestType.SEARCH_BY_KEYWORD)
				.path("discussions" + "/" + "search")
				.queryParameters(queryParams)
				.itemsPerPage(resultsPerPage)
				.pageNumber(pageNumber)
				.build();
		
		assertEquals("https://thesession.org/discussions/search?q=wig+glue&format=json&perpage=5&page=2", requestURL.toString());
		}
	
	@Test
	public void testKeywordSearchURLWithPaginationButNoItemsPerPageSpecified() 
		{
		URLComposer ub = new URLComposer();	
		String searchTerms = "wig glue";
		int pageNumber = 2;
		List<NameValuePair> queryParams = new ArrayList<>();
		queryParams.add(new BasicNameValuePair("q", searchTerms));

		URL requestURL = ub.new Builder()
				.requestType(RequestType.SEARCH_BY_KEYWORD)
				.path("discussions" + "/" + "search")
				.queryParameters(queryParams)
				.pageNumber(pageNumber)
				.build();
		
		assertEquals("https://thesession.org/discussions/search?q=wig+glue&format=json&page=2", requestURL.toString());
		}
	
	@Test
	public void testKeywordSearchURLWithoutPaginationOrItemsPerPage() 
		{
		URLComposer ub = new URLComposer();
		String searchTerms = "wig glue";
		List<NameValuePair> queryParams = new ArrayList<>();
		queryParams.add(new BasicNameValuePair("q", searchTerms));

		URL requestURL = ub.new Builder()
				.requestType(RequestType.SEARCH_BY_KEYWORD)
				.path("discussions" + "/" + "search")
				.queryParameters(queryParams)
				.build();
		
		assertEquals("https://thesession.org/discussions/search?q=wig+glue&format=json", requestURL.toString());
		} 
	
	@Test
	public void testKeywordSearchURLWithoutPaginationButWithItemsPerPage() 
		{
		URLComposer ub = new URLComposer();
		String searchTerms = "wig glue";
		int resultsPerPage = 5;
		List<NameValuePair> queryParams = new ArrayList<>();
		queryParams.add(new BasicNameValuePair("q", searchTerms));

		URL requestURL = ub.new Builder()
				.requestType(RequestType.SEARCH_BY_KEYWORD)
				.path("discussions" + "/" + "search")
				.queryParameters(queryParams)
				.itemsPerPage(resultsPerPage)
				.build();
		
		assertEquals("https://thesession.org/discussions/search?q=wig+glue&format=json&perpage=5", requestURL.toString());
		}

	
	// Test building a URL to search for most-recently added entries
	
	@Test
	public void testLatestSearchURLWithoutPaginationButWithItemsPerPage() 
		{
		URLComposer ub = new URLComposer();
		String dataCategory = "tunes";
		int resultsPerPage = 5;

		URL requestURL = ub.new Builder()
				.requestType(RequestType.SEARCH_LATEST_ITEMS)
				.path(dataCategory + "/" + "new")
				.itemsPerPage(resultsPerPage)
				.build();
		
		assertEquals("https://thesession.org/tunes/new?format=json&perpage=5", requestURL.toString());
		}
	
	@Test
	public void testLatestSearchURLWithoutPaginationOrItemsPerPage() 
		{
		URLComposer ub = new URLComposer();
		String dataCategory = "tunes";

		URL requestURL = ub.new Builder()
				.requestType(RequestType.SEARCH_LATEST_ITEMS)
				.path(dataCategory + "/" + "new")
				.build();
		
		assertEquals("https://thesession.org/tunes/new?format=json", requestURL.toString());
		}
	
	@Test
	public void testLatestSearchURLWithPaginationAndItemsPerPage() 
		{
		URLComposer ub = new URLComposer();
		String dataCategory = "tunes";
		int resultsPerPage = 5;
		int pageNumber = 2;

		URL requestURL = ub.new Builder()
				.requestType(RequestType.SEARCH_LATEST_ITEMS)
				.path(dataCategory + "/" + "new")
				.itemsPerPage(resultsPerPage)
				.pageNumber(pageNumber)
				.build();
		
		assertEquals("https://thesession.org/tunes/new?format=json&perpage=5&page=2", requestURL.toString());
		}
	
	// Test building a URL to search for an item based on location
	
	@Test
	public void testLocationSearchURLWithoutPaginationButWithItemsPerPage() 
		{
		URLComposer ub = new URLComposer();
		String dataCategory = "sessions";
		int resultsPerPage = 5;
		String latitude = "53.3498";
		String longitude = "6.2603";
		String radius = "100";

		List<NameValuePair> queryParams = new ArrayList<>();
		queryParams.add(new BasicNameValuePair("latlon", latitude + "," + longitude));
		queryParams.add(new BasicNameValuePair("radius", radius));

		URL requestURL = ub.new Builder()
				.requestType(RequestType.SEARCH_BY_LOCATION)
				.path(dataCategory + "/" + "nearby")
				.queryParameters(queryParams)
				.itemsPerPage(resultsPerPage)
				.build();
		
		assertEquals("https://thesession.org/sessions/nearby?latlon=53.3498%2C6.2603&radius=100&format=json&perpage=5", requestURL.toString());
		}
	
	@Test
	public void testLocationSearchURLWithPaginationAndItemsPerPage() 
		{
		URLComposer ub = new URLComposer();
		String dataCategory = "sessions";
		int resultsPerPage = 5;
		int pageNumber = 2;
		String latitude = "53.3498";
		String longitude = "6.2603";
		String radius = "100";

		List<NameValuePair> queryParams = new ArrayList<>();
		queryParams.add(new BasicNameValuePair("latlon", latitude + "," + longitude));
		queryParams.add(new BasicNameValuePair("radius", radius));

		URL requestURL = ub.new Builder()
				.requestType(RequestType.SEARCH_BY_LOCATION)
				.path(dataCategory + "/" + "nearby")
				.queryParameters(queryParams)
				.itemsPerPage(resultsPerPage)
				.pageNumber(pageNumber)
				.build();
		
		assertEquals("https://thesession.org/sessions/nearby?latlon=53.3498%2C6.2603&radius=100&format=json&perpage=5&page=2", requestURL.toString());
		}
	
	
	// Test building a URL to list member contributions in a specific category
	
	@Test
	public void testMemberContributionSearchURLWithoutPaginationButWithItemsPerPage() 
		{
		URLComposer ub = new URLComposer();
		int userID = 1;
		String dataCategory = "tunes";
		int resultsPerPage = 5;
		
		URL requestURL = ub.new Builder()
				.requestType(RequestType.SEARCH_MEMBER_CONTRIBUTIONS)
				.path("members" + "/" + userID + "/" + dataCategory)
				.itemsPerPage(resultsPerPage)
				.build();
			
		assertEquals("https://thesession.org/members/1/tunes?format=json&perpage=5", requestURL.toString());
		}
	
	@Test
	public void testMemberContributionSearchURLWithPaginationAndItemsPerPage() 
		{
		URLComposer ub = new URLComposer();
		int userID = 1;
		String dataCategory = "tunes";
		int resultsPerPage = 5;
		int pageNumber = 2;
		
		URL requestURL = ub.new Builder()
				.requestType(RequestType.SEARCH_MEMBER_CONTRIBUTIONS)
				.path("members" + "/" + userID + "/" + dataCategory)
				.itemsPerPage(resultsPerPage)
				.pageNumber(pageNumber)
				.build();
			
		assertEquals("https://thesession.org/members/1/tunes?format=json&perpage=5&page=2", requestURL.toString());
		}
		
	
	// Test building a URL to search for most popular tunes
	
	@Test
	public void testPopularSearchURLWithoutPaginationButWithItemsPerPage() 
		{
		URLComposer ub = new URLComposer();
		int resultsPerPage = 5;

		URL requestURL = ub.new Builder()
				.requestType(RequestType.SEARCH_POPULAR)
				.path("tunes" + "/" + "popular")
				.itemsPerPage(resultsPerPage)
				.build();
		
		assertEquals("https://thesession.org/tunes/popular?format=json&perpage=5", requestURL.toString());
		}
	
	@Test
	public void testPopularSearchURLWithPaginationAndItemsPerPage() 
		{
		URLComposer ub = new URLComposer();
		int resultsPerPage = 5;
		int pageNumber = 2;

		URL requestURL = ub.new Builder()
				.requestType(RequestType.SEARCH_POPULAR)
				.path("tunes" + "/" + "popular")
				.itemsPerPage(resultsPerPage)
				.pageNumber(pageNumber)
				.build();
		
		assertEquals("https://thesession.org/tunes/popular?format=json&perpage=5&page=2", requestURL.toString());
		}
	
	
	// Test building a URL to search for recently added sets of tunes
	
	@Test
	public void testSetSearchURLWithoutPaginationButWithItemsPerPage() 
		{
		URLComposer ub = new URLComposer();
		int resultsPerPage = 5;

		URL requestURL = ub.new Builder()
				.requestType(RequestType.SEARCH_SETS)
				.path("tunes" + "/" + "sets")
				.itemsPerPage(resultsPerPage)
				.build();
		
		assertEquals("https://thesession.org/tunes/sets?format=json&perpage=5", requestURL.toString());
		}
	
	@Test
	public void testSetSearchURLWithPaginationAndItemsPerPage() 
		{
		URLComposer ub = new URLComposer();
		int resultsPerPage = 5;
		int pageNumber = 2;

		URL requestURL = ub.new Builder()
				.requestType(RequestType.SEARCH_SETS)
				.path("tunes" + "/" + "sets")
				.itemsPerPage(resultsPerPage)
				.pageNumber(pageNumber)
				.build();
		
		assertEquals("https://thesession.org/tunes/sets?format=json&perpage=5&page=2", requestURL.toString());
		}
	}
