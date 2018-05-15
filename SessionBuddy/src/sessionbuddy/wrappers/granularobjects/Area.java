package sessionbuddy.wrappers.granularobjects;

/**
 * Represents an individual geographic area from thesession.org website. Typically a county,
 * province, region or similar geographic area.
 * 
 * @author Colman O'B
 * @since 2017-12-25
 */
public class Area {
  /**
   * A numeric identifier in thesession.org database for the particular geographic area
   */
  public int areaID;

  /**
   * The name of the geographic area
   */
  public String areaName;

  /**
   * Constructor that populates all fields
   * 
   * @param areaID A unique area identifier assigned to each area in thesession.org database
   * @param areaName The name of an area in thesession.org database
   */
  public Area(int areaID, String areaName) {
    this.areaID = areaID;
    this.areaName = areaName;
  }
}
