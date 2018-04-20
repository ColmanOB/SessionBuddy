package sessionbuddy.wrappers.granularobjects;

/**
 * Represents an individual town record from thesession.org website
 * 
 * @author Colman O'B
 * @since 2017-12-29
 */
public class Town 
	{
	/**
	 * A unique town identifier assigned to each town in thesession.org database
	 */
	public int townID;
	
	/**
	 * The name of a town in thesession.org database
	 */
	public String townName;

	/**
	 * Constructor that populates all fields
	 * 
	 * @param townID A unique town identifier assigned to each town in thesession.org database
	 * @param townName The name of a town in thesession.org database
	 */
	public Town(int townID, String townName)
		{
		this.townID = townID;
		this.townName = townName;
		}
	}
