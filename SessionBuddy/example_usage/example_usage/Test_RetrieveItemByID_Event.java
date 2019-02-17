package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import sessionbuddy.IndividualItem;
import sessionbuddy.wrappers.resultsets.ItemResultEvent;

public class Test_RetrieveItemByID_Event
{
    public static void main(String[] args) throws URISyntaxException
    {
        int eventID = 2;
        
        try
        {
            // Call the getEvent() method on the IndividualItem object
            ItemResultEvent resultSet = IndividualItem.getEvent(eventID);

            System.out.println("Event ID: " + resultSet.eventDetails.eventID);
            System.out.println("Event Name: " + resultSet.eventDetails.eventName);
            System.out.println("Event URL: " + resultSet.eventDetails.eventURL);
            System.out.println("Submitted Date: " + resultSet.eventDetails.submittedDate);

            System.out.println("Event Start Date: " + resultSet.schedule.startDate);
            System.out.println("Event End Date: " + resultSet.schedule.endDate);

            System.out.println("Submitter User ID: " + resultSet.member.userID);
            System.out.println("Submitter Username: " + resultSet.member.userName);
            System.out.println("Submitter Profile Page: " + resultSet.member.userURL);

            System.out.print("Latitude: " + resultSet.coordinates.latitude);
            System.out.print("Longitude: " + resultSet.coordinates.longitude);

            System.out.println("Town: " + resultSet.town.townName);
            System.out.println("Area: " + resultSet.area.areaName);
            System.out.println("Country: " + resultSet.country.countryName);

            System.out.println("Venue Name: " + resultSet.venue.venueName);
            System.out.println("Venue Phone: " + resultSet.venue.venuePhone);
            System.out.println("Venue Email: " + resultSet.venue.venueEmail);
            System.out.println("Venue Website: " + resultSet.venue.venueWebsite);

            // Loop through each comment on the event and print the details
            for (int i = 0; i < (resultSet.comments.size()); i++)
            {
                System.out.println("Comment ID: " + resultSet.comments.get(i).id);
                System.out.println("Comment URL: " + resultSet.comments.get(i).url);
                System.out.println("Comment Date: " + resultSet.comments.get(i).date);

                System.out.println("Comment Submitter ID: " + resultSet.comments.get(i).member.userID);
                System.out.println("Comment Submitter Username: " + resultSet.comments.get(i).member.userName);
                System.out.println("Comment Submitter Profile Page: " + resultSet.comments.get(i).member.userURL);
                System.out.println("Comment Content: " + resultSet.comments.get(i).content);

                System.out.println("\n");
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
