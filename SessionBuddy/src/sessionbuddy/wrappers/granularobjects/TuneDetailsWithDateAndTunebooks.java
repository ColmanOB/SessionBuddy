package sessionbuddy.wrappers.granularobjects;

/**
 * Represents a tune in the result set when retrieving the list of most popular tunes
 * 
 * @author Colman O'B
 * @since 2018-04-22
 */
public class TuneDetailsWithDateAndTunebooks
{
    /**
     * The basic meta data relating to the tune, incl. tune title, date of submission etc.
     */
    public TuneDetailsWithDate generalTuneDetails;

    /**
     * The number of user's tunebooks on thesession.org to which this tune belongs
     */
    public int tunebooks;

    /**
     * Constructor that populates all fields
     * 
     * @param tuneDetails The basic meta data relating to the tune, incl. tune title, date of submission etc.
     * @param tunebooks The number of user's tunebooks on thesession.org to which this tune belongs
     */
    public TuneDetailsWithDateAndTunebooks(TuneDetailsWithDate tuneDetails, int tunebooks)
    {
        this.generalTuneDetails = tuneDetails;
        this.tunebooks = tunebooks;
    }
}
