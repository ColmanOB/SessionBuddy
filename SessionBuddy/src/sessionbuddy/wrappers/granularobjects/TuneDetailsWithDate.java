package sessionbuddy.wrappers.granularobjects;

/**
 * Represents the set of metadata associated with a tune from thesession.org
 * database
 * 
 * @author Colman O'B
 * @since 2018-04-22
 */
public class TuneDetailsWithDate
{
    /**
     * The basic details of the tune, incl. tune title, tune page URL etc.
     */
    public TuneDetails basicTuneDetails;

    /**
     * The type of tune, e.g. jig, reel etc.
     */
    public String tuneType;

    /**
     * Date of original submission of the tune
     */
    public String submittedDate;

    /**
     * Constructor method that populates all fields
     * 
     * @param tuneDetails The basic details of the tune, incl. tune title, tune URL etc.
     * @param tuneType The type of tune, e.g. jig, reel etc.
     * @param submittedDate Date of original submission of the tune
     */
    public TuneDetailsWithDate(TuneDetails tuneDetails, String tuneType, String submittedDate)
    {
        this.basicTuneDetails = tuneDetails;
        this.tuneType = tuneType;
        this.submittedDate = submittedDate;
    }
}
