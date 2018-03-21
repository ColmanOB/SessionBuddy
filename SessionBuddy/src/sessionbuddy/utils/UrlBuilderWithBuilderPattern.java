package sessionbuddy.utils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

// TODO: Refactor this class to use the builder pattern, and see if this class becomes a better alternative to the current UrlBuilder class

/**
 * Assembles the URL that will be used to access the API.
 * 
 * This previously used a 'hand-rolled' implementation, but now uses the Apache httpclient instead.
 * 
 * @author Colman
 * @since 2018-03-13
 */
public class UrlBuilderWithBuilderPattern 
	{
	// Declaring a few constants that are unlikely to change, at least not frequently
	private static final String PROTOCOL = "https";
	private static final String HOST = "thesession.org";
	private static final String FORMAT_SPECIFIER = "format";
	private static final String FORMAT = "json";
	private static final String ITEMS_PER_PAGE_SPECIFIER = "perpage";
	private static final String PAGE_NUMBER_SPECIFIER = "page";
	
	private String path = null;
	private List<NameValuePair> queryParameters = null;
	private int itemsPerPage = 0;
	private int pageNumber = 0;

	public class Builder
		{
		private UrlBuilderWithBuilderPattern apiURL = new UrlBuilderWithBuilderPattern();

	    public Builder path(String path) 
	    	{
	        apiURL.setPath(path);
	        return this;
	      	}
	    
	    public Builder queryParameters(List<NameValuePair> queryParameters) 
	    	{
	        apiURL.setQueryParameters(queryParameters);
	        return this;
	      	}
	    
	    public Builder itemsPerPage(int itemsPerPage) 
	    	{
	        apiURL.setItemsPerPage(itemsPerPage);
	        return this;
	      	}
		    
	    public Builder pageNumber(int pageNumber) 
	    	{
	        apiURL.setPageNumber(pageNumber);
	        return this;
	      	}
	    
	    public URL build() 
	    	{
	    	try
		    	{
	    		if (apiURL.queryParameters != null && apiURL.pageNumber == 0)
		    		{ 
					URIBuilder builder = new URIBuilder()
							.setScheme(PROTOCOL)
							.setHost(HOST)
							.setPath(apiURL.getPath())
							.addParameters(apiURL.queryParameters)
							.addParameter(FORMAT_SPECIFIER, FORMAT)
							.addParameter(ITEMS_PER_PAGE_SPECIFIER, Integer.toString(itemsPerPage));
					
					return builder.build().toURL();
		    		}
	    		
	    		 else if (apiURL.queryParameters != null && apiURL.pageNumber > 0)
	    			{
					URIBuilder builder = new URIBuilder()
							.setScheme(PROTOCOL)
							.setHost(HOST)
							.setPath(apiURL.getPath())
							.addParameters(apiURL.getQueryParameters())
							.addParameter(FORMAT_SPECIFIER, FORMAT)
							.addParameter(ITEMS_PER_PAGE_SPECIFIER, Integer.toString(apiURL.itemsPerPage))
							.addParameter(PAGE_NUMBER_SPECIFIER, Integer.toString(apiURL.pageNumber));
					
					return builder.build().toURL();
	    			}
	    		
	    		 else return null; // This line needs to be removed when class is fully developed	
		    	}
	    	
	    	catch (MalformedURLException | URISyntaxException ex)
	    		{
	    		throw new IllegalArgumentException(ex.getMessage());
	    		}
	    	}
	   }

	
	// Getters and setters
	public List<NameValuePair> getQueryParameters() 
		{
		return queryParameters;
		}

	public void setQueryParameters(List<NameValuePair> queryParameters) 
		{
		this.queryParameters = queryParameters;
		}

	public String getPath() 
		{
		return path;
		}

	public void setPath(String path) 
		{
		this.path = path;
		}

	public int getItemsPerPage() 
		{
		return itemsPerPage;
		}

	public void setItemsPerPage(int itemsPerPage) 
		{
		this.itemsPerPage = itemsPerPage;
		}

	public int getPageNumber() 
		{
		return pageNumber;
		}

	public void setPageNumber(int pageNumber) 
		{
		this.pageNumber = pageNumber;
		}
	}
