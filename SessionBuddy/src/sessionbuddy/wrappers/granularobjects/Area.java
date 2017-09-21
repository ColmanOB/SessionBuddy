package sessionbuddy.wrappers.granularobjects;

/**
 * Represents an individual geographic area from thesession.org website
 * 
 * @author Colman O'B
 * @since 2017-01-31
 */
public class Area 
	{
	public String areaID;
	public String areaName;

	/**
	 * Constructor that populates all fields
	 * 
	 * @param areaID A unique area identifier assigned to each area in thesession.org database
	 * @param areaName The name of an area in thesession.org database
	 */
	public Area(String areaID, String areaName)
		{
		this.areaID = areaID;
		this.areaName = areaName;
		}
	}
