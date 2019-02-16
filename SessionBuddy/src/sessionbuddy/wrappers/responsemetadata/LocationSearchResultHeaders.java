package sessionbuddy.wrappers.responsemetadata;

// TODO: Rename these fields to something more descriptive

public class LocationSearchResultHeaders
{
    public String latlon;
    public String radius;
    public int perPage;
    public String format;
    public int pages;
    public int page;
    public int total;
    
    public LocationSearchResultHeaders(String latlon, String radius, int perPage, String format, int pages, int page, int total)
    {
        this.latlon = latlon;
        this.radius = radius;
        this.perPage = perPage;
        this.format = format;
        this.pages = pages;
        this.page = page;
        this.total = total;
    }
}
