package json_object_wrappers;

/**
 * Represents an individual country from thesession.org website
 * 
 * @author Colman O'B
 * @since 2017-01-31
 */
public class Country 
	{
	public String countryID;
	public String countryName;

	/**
	 * Constructor that populates all fields
	 * 
	 * @param countryID A unique towna identifier assigned to each town in thesession.org database
	 * @param countryName The name of a town in thesession.org database
	 */
	public Country(String countryID, String countryName)
		{
		this.countryID = countryID;
		this.countryName = countryName;
		}
	}
