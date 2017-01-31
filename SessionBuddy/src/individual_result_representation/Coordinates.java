package individual_result_representation;

/**
 * Represents a set of geographic co-ordinates from thesession.org website
 * 
 * @author Colman O'B
 * @since 2017-01-31
 */
public class Coordinates 
	{
	public String latitude;
	public String longitude;

	/**
	 * Constructor that populates all fields
	 * 
	 * @param latitude A geographic latitude co-ordinate
	 * @param longitude A geographic longitude co-ordinate
	 */
	public Coordinates(String latitude, String longitude)
		{
		this.latitude = latitude;
		this.longitude = longitude;
		}
	}
