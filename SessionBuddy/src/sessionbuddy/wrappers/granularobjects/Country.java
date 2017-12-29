package sessionbuddy.wrappers.granularobjects;

/**
 * Represents an individual country from thesession.org website
 * 
 * @author Colman O'B
 * @since 2017-12-29
 */
public class Country 
	{
	/**
	 * A unique numeric ID assigned to each country in thesession.org database
	 */
	public String countryID;
	
	/**
	 * A country name, as stored in thesession.org database
	 */
	public String countryName;

	/**
	 * Constructor that populates all fields
	 * 
	 * @param countryID A unique country identifier assigned to each country in thesession.org database
	 * @param countryName The name of a country in thesession.org database
	 */
	public Country(String countryID, String countryName)
		{
		this.countryID = countryID;
		this.countryName = countryName;
		}
	}
