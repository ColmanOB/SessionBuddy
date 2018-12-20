package sessionbuddy.wrappers.responsemetadata;

// TODO: Rename these fields to something more descriptive

public class KeywordSearchResultHeaders
{
    public String q;
    public int perpage;
    public String format;
    public int pages;
    public int page;
    public int total;
    
    public KeywordSearchResultHeaders(String q, int perpage, String format, int pages, int page, int total)
    {
        this.q = q;
        this.perpage = perpage;
        this.format = format;
        this.pages = pages;
        this.page = page;
        this.total = total;
    }
}
