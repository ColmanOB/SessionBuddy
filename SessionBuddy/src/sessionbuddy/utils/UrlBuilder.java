package sessionbuddy.utils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;
import org.apache.http.NameValuePair;

// TODO: Refactor this class to avoid the 'telescoping constructor' problem
/**
 * Performs the final assembly of the URL that will be used to access the API.
 * 
 * This previously used a 'hand-rolled' implementation, but now uses the Apache httpclient instead.
 * 
 * @author Colman
 * @since 2018-02-25
 */
public class UrlBuilder 
	{
	// Declaring a few constants that are unlikely to change, at least not frequently
	private static final String PROTOCOL = "https";
	private static final String HOST = "thesession.org";
	private static final String FORMAT_SPECIFIER = "format";
	private static final String FORMAT = "json";
	private static final String ITEMS_PER_PAGE_SPECIFIER = "perpage";
	private static final String PAGE_NUMBER_SPECIFIER = "page";
	
	
	/**
	 * @param dataCategory
	 * @param itemID
	 * @return
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	public static URL buildURL(String dataCategory, String itemID) throws MalformedURLException, URISyntaxException
		{
		URIBuilder builder = new URIBuilder()
				.setScheme(PROTOCOL)
				.setHost(HOST)
				.setPath(dataCategory + "/" + itemID)
				.addParameter(FORMAT_SPECIFIER, FORMAT);

		return builder.build().toURL();
		}
	
	
	/**
	 * @param dataCategory
	 * @param requestType
	 * @param itemsPerPage
	 * @return
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	public static URL buildURL(String dataCategory, String requestType, int itemsPerPage) throws MalformedURLException, URISyntaxException
		{
		URIBuilder builder = new URIBuilder()
				.setScheme(PROTOCOL)
				.setHost(HOST)
				.setPath(dataCategory + "/" + requestType)
				.addParameter(FORMAT_SPECIFIER, FORMAT)
				.addParameter(ITEMS_PER_PAGE_SPECIFIER, Integer.toString(itemsPerPage));
		
		return builder.build().toURL();
		}
	
	
	/**
	 * @param dataCategory
	 * @param requestType
	 * @param itemsPerPage
	 * @param pageNumber
	 * @return
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	public static URL buildURL(String dataCategory, String requestType, int itemsPerPage, int pageNumber) throws MalformedURLException, URISyntaxException
		{
		URIBuilder builder = new URIBuilder()
				.setScheme(PROTOCOL)
				.setHost(HOST)
				.setPath(dataCategory + "/" + requestType)
				.addParameter(FORMAT_SPECIFIER, FORMAT)
				.addParameter(ITEMS_PER_PAGE_SPECIFIER, Integer.toString(itemsPerPage))
				.addParameter(PAGE_NUMBER_SPECIFIER, Integer.toString(pageNumber));
		
		return builder.build().toURL();
		}
	
	
	/**
	 * @param dataCategory
	 * @param requestType
	 * @param queryParameters
	 * @param itemsPerPage
	 * @return
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	public static URL buildURL(String dataCategory, String requestType, List<NameValuePair> queryParameters, int itemsPerPage) throws MalformedURLException, URISyntaxException
		{
		URIBuilder builder = new URIBuilder()
				.setScheme(PROTOCOL)
				.setHost(HOST)
				.setPath(dataCategory + "/" + requestType)
				.addParameters(queryParameters)
				.addParameter(FORMAT_SPECIFIER, FORMAT)
				.addParameter(ITEMS_PER_PAGE_SPECIFIER, Integer.toString(itemsPerPage));
		
		return builder.build().toURL();
		}
	
	
	/**
	 * @param dataCategory
	 * @param requestType
	 * @param queryParameters
	 * @param itemsPerPage
	 * @param pageNumber
	 * @return
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	public static URL buildURL(String dataCategory, String requestType, List<NameValuePair> queryParameters, int itemsPerPage, int pageNumber) throws MalformedURLException, URISyntaxException
		{
		URIBuilder builder = new URIBuilder()
				.setScheme(PROTOCOL)
				.setHost(HOST)
				.setPath(dataCategory + "/" + requestType)
				.addParameters(queryParameters)
				.addParameter(FORMAT_SPECIFIER, FORMAT)
				.addParameter(ITEMS_PER_PAGE_SPECIFIER, Integer.toString(itemsPerPage))
				.addParameter(PAGE_NUMBER_SPECIFIER, Integer.toString(pageNumber));
		
		return builder.build().toURL();
		}
	}
