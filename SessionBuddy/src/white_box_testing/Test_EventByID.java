package white_box_testing;

import json_object_wrappers.EventByIDResult;

import main.RetrieveItemByID;

public class Test_EventByID 
{
	public static void main(String[] args)
		{
		// Set the parameters
		String category = "events";
		String eventID = "2";
		int resultsPerPage = 50;
		
		// Instantiate a RetrieveItem object
		RetrieveItemByID search = new RetrieveItemByID();
		
		// Pass in the parameters for the session we want to retrieve
		EventByIDResult resultSet = search.getEventByID(category, eventID, resultsPerPage);
		
		System.out.println(resultSet.eventDetails.eventID);
		System.out.println(resultSet.eventDetails.eventName);
		System.out.println(resultSet.eventDetails.eventURL);
		System.out.println(resultSet.eventDetails.submittedDate);
		System.out.println("\n");
		
		System.out.println(resultSet.schedule.startDate);
		System.out.println(resultSet.schedule.endDate);
	
		System.out.println(resultSet.member.userID);
		System.out.println(resultSet.member.userName);
		System.out.println(resultSet.member.userURL);
		System.out.println("\n");
		
		System.out.print(resultSet.coordinates.latitude);
		System.out.print(resultSet.coordinates.longitude);
		System.out.println("\n");
		
		System.out.println(resultSet.town.townName);
		System.out.println(resultSet.area.areaName);
		System.out.println(resultSet.country.countryName);
		System.out.println("\n");
		
		System.out.println(resultSet.venue.venueName);
		System.out.println(resultSet.venue.venuePhone);
		System.out.println(resultSet.venue.venueEmail);
		System.out.println(resultSet.venue.venueWebsite);
		System.out.println("\n");
		
		System.out.println(resultSet.comments.size());

		// Loop through each comment on the event and print the details
		for (int i = 0; i < (resultSet.comments.size()); i++)
			{
			System.out.println(resultSet.comments.get(i).id);
			System.out.println(resultSet.comments.get(i).url);
			System.out.println(resultSet.comments.get(i).date);

			System.out.println(resultSet.comments.get(i).member.userID);
			System.out.println(resultSet.comments.get(i).member.userName);
			System.out.println(resultSet.comments.get(i).member.userURL);
			System.out.println(resultSet.comments.get(i).content);
			
			System.out.println("\n");
			}
		}
}
