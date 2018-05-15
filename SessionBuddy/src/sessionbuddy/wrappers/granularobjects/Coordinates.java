package sessionbuddy.wrappers.granularobjects;

/**
 * Represents a set of geographic co-ordinates from thesession.org website
 * 
 * @author Colman O'B
 * @since 2017-12-29
 */
public class Coordinates {
  /**
   * A geographic latitude value
   */
  public String latitude;

  /**
   * A geographic longitude value
   */
  public String longitude;

  /**
   * Constructor that populates all fields
   * 
   * @param latitude A geographic latitude co-ordinate
   * @param longitude A geographic longitude co-ordinate
   */
  public Coordinates(String latitude, String longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }
}
