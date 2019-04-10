package sessionbuddy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.Test;

import sessionbuddy.wrappers.resultsets.LocationResultEvents;
import sessionbuddy.wrappers.resultsets.LocationResultSessions;
import sessionbuddy.wrappers.resultsets.LocationResultTrips;

/**
 * Unit tests for the Nearby class
 * 
 * Each test follows the same process:
 * 
 * 1. Set the search parameters
 * 2. Perform the search
 * 3. Loop through the result set, testing that each element of each result is populated
 * 
 * For each category of data, one test is done with pagination in the results, 
 * and one without pagination
 * 
 * @author Colman
 * @since 2019-02-16
 */
public class NearbyTest
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
            LocationResultSessions resultSet = Nearby.searchSessions(latitude, longitude, radius, resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.results.size(); i++)
            {
                assertThat(resultSet.results.get(i).sessionDetails.sessionID, is(notNullValue()));
                assertThat(resultSet.results.get(i).sessionDetails.sessionURL, is(notNullValue()));
                assertThat(resultSet.results.get(i).sessionDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.results.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.results.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.results.get(i).user.userURL, is(notNullValue()));
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
            LocationResultSessions resultSet = Nearby.searchSessions(latitude, longitude, radius, resultsPerPage);

            for (int i = 0; i < resultSet.results.size(); i++)
            {
                assertThat(resultSet.results.get(i).sessionDetails.sessionID, is(notNullValue()));
                assertThat(resultSet.results.get(i).sessionDetails.sessionURL, is(notNullValue()));
                assertThat(resultSet.results.get(i).sessionDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.results.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.results.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.results.get(i).user.userURL, is(notNullValue()));
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
            LocationResultEvents resultSet = Nearby.searchEvents(latitude, longitude, radius, resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.results.size(); i++)
            {
                assertThat(resultSet.results.get(i).eventDetails.eventID, is(notNullValue()));
                assertThat(resultSet.results.get(i).eventDetails.eventName, is(notNullValue()));
                assertThat(resultSet.results.get(i).eventDetails.eventURL, is(notNullValue()));

                assertThat(resultSet.results.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.results.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.results.get(i).user.userURL, is(notNullValue()));

                assertThat(resultSet.results.get(i).venue.venueID, is(notNullValue()));
                assertThat(resultSet.results.get(i).venue.venueName, is(notNullValue()));
                assertThat(resultSet.results.get(i).venue.venueEmail, is(notNullValue()));
                assertThat(resultSet.results.get(i).venue.venuePhone, is(notNullValue()));

                assertThat(resultSet.results.get(i).town.townName, is(notNullValue()));
                assertThat(resultSet.results.get(i).country.countryName, is(notNullValue()));
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
            LocationResultEvents resultSet = Nearby.searchEvents(latitude, longitude, radius, resultsPerPage);

            for (int i = 0; i < resultSet.results.size(); i++)
            {
                assertThat(resultSet.results.get(i).eventDetails.eventID, is(notNullValue()));
                assertThat(resultSet.results.get(i).eventDetails.eventName, is(notNullValue()));
                assertThat(resultSet.results.get(i).eventDetails.eventURL, is(notNullValue()));

                assertThat(resultSet.results.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.results.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.results.get(i).user.userURL, is(notNullValue()));

                assertThat(resultSet.results.get(i).venue.venueID, is(notNullValue()));
                assertThat(resultSet.results.get(i).venue.venueName, is(notNullValue()));
                assertThat(resultSet.results.get(i).venue.venueEmail, is(notNullValue()));
                assertThat(resultSet.results.get(i).venue.venuePhone, is(notNullValue()));

                assertThat(resultSet.results.get(i).town.townName, is(notNullValue()));
                assertThat(resultSet.results.get(i).country.countryName, is(notNullValue()));
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
            LocationResultTrips resultSet = Nearby.searchTrips(latitude, longitude, radius, resultsPerPage, pageNumber);

            for (int i = 0; i < resultSet.results.size(); i++)
            {
                assertThat(resultSet.results.get(i).tripDetails.tripID, is(notNullValue()));
                assertThat(resultSet.results.get(i).tripDetails.tripName, is(notNullValue()));
                assertThat(resultSet.results.get(i).tripDetails.tripURL, is(notNullValue()));
                assertThat(resultSet.results.get(i).tripDetails.submittedDate, is(notNullValue()));
                
                assertThat(resultSet.results.get(i).tripSchedule.startDate, is(notNullValue()));
                assertThat(resultSet.results.get(i).tripSchedule.endDate, is(notNullValue()));

                assertThat(resultSet.results.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.results.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.results.get(i).user.userURL, is(notNullValue()));
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
            LocationResultTrips resultSet = Nearby.searchTrips(latitude, longitude, radius, resultsPerPage);

            for (int i = 0; i < resultSet.results.size(); i++)
            {
                assertThat(resultSet.results.get(i).tripDetails.tripID, is(notNullValue()));
                assertThat(resultSet.results.get(i).tripDetails.tripName, is(notNullValue()));
                assertThat(resultSet.results.get(i).tripDetails.tripURL, is(notNullValue()));
                assertThat(resultSet.results.get(i).tripDetails.submittedDate, is(notNullValue()));

                assertThat(resultSet.results.get(i).tripSchedule.startDate, is(notNullValue()));
                assertThat(resultSet.results.get(i).tripSchedule.endDate, is(notNullValue()));

                assertThat(resultSet.results.get(i).user.userID, is(notNullValue()));
                assertThat(resultSet.results.get(i).user.userName, is(notNullValue()));
                assertThat(resultSet.results.get(i).user.userURL, is(notNullValue()));
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException | URISyntaxException e)
        {
            fail(e.getMessage());
        }
    }
}
