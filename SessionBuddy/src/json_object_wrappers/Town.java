package json_object_wrappers;

/**
 * Represents an individual town from thesession.org website
 * 
 * @author Colman O'B
 * @since 2017-01-31
 */
public class Town 
	{
	public String townID;
	public String townName;

	/**
	 * Constructor that populates all fields
	 * 
	 * @param townID A unique towna identifier assigned to each town in thesession.org database
	 * @param townName The name of a town in thesession.org database
	 */
	public Town(String townID, String townName)
		{
		this.townID = townID;
		this.townName = townName;
		}
	}
