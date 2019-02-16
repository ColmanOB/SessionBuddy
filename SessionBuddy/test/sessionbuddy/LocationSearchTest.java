package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.junit.Test;

import sessionbuddy.wrappers.individualresults.LocationResultSingleEvent;
import sessionbuddy.wrappers.individualresults.LocationResultSingleSession;
import sessionbuddy.wrappers.individualresults.SearchResultTrips;

/**
 * Unit tests for the LocationSearch class
 * 
 * Each test follows the same process:
 * 
 * 1. Set the search parameters
 * 2. Instantiate a LocationSearch object
 * 3. Perform the search
 * 4. Loop through the result set, testing that each element of each result is populated
 * 
 * For each category of data, one test is done with pagination in the results, 
 * and one without pagination
 * 
 * @author Colman
 * @since 2018-12-11
 */
public class LocationSearchTest
{

    @Test
    public void testSearchSessionsWithPagination()
    {
        String latitude = "53.3498";
        String longitude = "6.2603";
        String radius = "75";
        int resultsPerPage = 2;
        int pageNumber = 2;

        try
        {
            LocationSearch search = new LocationSearch(latitude, longitude, radius, resultsPerPage, pageNumber);

            ArrayList<LocationResultSingleSession> resultSet = search.searchSessions();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).sessionDetails.sessionID, is(notNullValue()));
                assertThat(resultSet.get(i).sessionDetails.sessionURL, is(notNullValue()));
                assertThat(resultSet.get(i).sessionDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSearchSessionsWithoutPagination()
    {
        String latitude = "53.3498";
        String longitude = "6.2603";
        String radius = "75";
        int resultsPerPage = 50;

        try
        {
            LocationSearch search = new LocationSearch(latitude, longitude, radius, resultsPerPage);

            ArrayList<LocationResultSingleSession> resultSet = search.searchSessions();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).sessionDetails.sessionID, is(notNullValue()));
                assertThat(resultSet.get(i).sessionDetails.sessionURL, is(notNullValue()));
                assertThat(resultSet.get(i).sessionDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.get(i).user.userURL, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSearchEventsWithPagination()
    {
        String latitude = "53.3498";
        String longitude = "6.2603";
        String radius = "500";
        int resultsPerPage = 2;
        int pageNumber = 2;

        try
        {
            LocationSearch search = new LocationSearch(latitude, longitude, radius, resultsPerPage, pageNumber);

            ArrayList<LocationResultSingleEvent> resultSet = search.searchEvents();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).eventDetails.eventID, is(notNullValue()));
                assertThat(resultSet.get(i).eventDetails.eventName, is(notNullValue()));
                assertThat(resultSet.get(i).eventDetails.eventURL, is(notNullValue()));

                assertThat(resultSet.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.get(i).user.userURL, is(notNullValue()));

                assertThat(resultSet.get(i).venue.venueID, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venueEmail, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venuePhone, is(notNullValue()));

                assertThat(resultSet.get(i).town.townName, is(notNullValue()));
                assertThat(resultSet.get(i).country.countryName, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSearchEventsWithoutPagination()
    {
        String latitude = "53.3498";
        String longitude = "6.2603";
        String radius = "500";
        int resultsPerPage = 50;

        try
        {
            LocationSearch search = new LocationSearch(latitude, longitude, radius, resultsPerPage);

            ArrayList<LocationResultSingleEvent> resultSet = search.searchEvents();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).eventDetails.eventID, is(notNullValue()));
                assertThat(resultSet.get(i).eventDetails.eventName, is(notNullValue()));
                assertThat(resultSet.get(i).eventDetails.eventURL, is(notNullValue()));

                assertThat(resultSet.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.get(i).user.userURL, is(notNullValue()));

                assertThat(resultSet.get(i).venue.venueID, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venueName, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venueEmail, is(notNullValue()));
                assertThat(resultSet.get(i).venue.venuePhone, is(notNullValue()));

                assertThat(resultSet.get(i).town.townName, is(notNullValue()));
                assertThat(resultSet.get(i).country.countryName, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testSearchTripsWithPagination()
    {
        String latitude = "53.3498";
        String longitude = "6.2603";
        String radius = "500";
        int resultsPerPage = 2;
        int pageNumber = 2;

        try
        {
            LocationSearch search = new LocationSearch(latitude, longitude, radius, resultsPerPage, pageNumber);

            ArrayList<SearchResultTrips> resultSet = search.searchTrips();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).tripDetails.tripID, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.tripName, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.tripURL, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.submittedDate, is(notNullValue()));
                
                assertThat(resultSet.get(i).tripSchedule.startDate, is(notNullValue()));
                assertThat(resultSet.get(i).tripSchedule.endDate, is(notNullValue()));

                assertThat(resultSet.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.get(i).submitter.userURL, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSearchTripsWithoutPagination()
    {
        String latitude = "53.3498";
        String longitude = "6.2603";
        String radius = "500";
        int resultsPerPage = 50;

        try
        {
            LocationSearch search = new LocationSearch(latitude, longitude, radius, resultsPerPage);

            ArrayList<SearchResultTrips> resultSet = search.searchTrips();

            for (int i = 0; i < resultSet.size(); i++)
            {
                assertThat(resultSet.get(i).tripDetails.tripID, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.tripName, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.tripURL, is(notNullValue()));
                assertThat(resultSet.get(i).tripDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.get(i).tripSchedule.startDate, is(notNullValue()));
                assertThat(resultSet.get(i).tripSchedule.endDate, is(notNullValue()));

                assertThat(resultSet.get(i).submitter.userID, is(notNullValue()));
                assertThat(resultSet.get(i).submitter.userName, is(notNullValue()));
                assertThat(resultSet.get(i).submitter.userURL, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }
}
