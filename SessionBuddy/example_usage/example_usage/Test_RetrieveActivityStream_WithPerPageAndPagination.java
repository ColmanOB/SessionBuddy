package example_usage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import sessionbuddy.ActivityStream;
import sessionbuddy.wrappers.resultsets.ActivityStreamResult;

class Test_RetrieveActivityStream_WithPerPageAndPagination
{
    public static void main(String[] args) throws URISyntaxException
    {
        try
        {
            int perPage = 2;
            int pageNumber = 4;
            
            // Get the activity stream
            ArrayList<ActivityStreamResult> resultSet = ActivityStream.readActivityStream(perPage, pageNumber);

            // Loop through the results and print each attribute of each
            // individual result in the set
            for (int i = 0; i < resultSet.size(); i++)
            {
                System.out.println("Published: " + resultSet.get(i).details.published);
                System.out.println("Title: " + resultSet.get(i).details.title);
                System.out.println("Verb: " + resultSet.get(i).details.verb);
                
                System.out.println("Actor - Display Name: " + resultSet.get(i).actor.displayName);
                System.out.println("Actor - ID: " + resultSet.get(i).actor.id);
                System.out.println("Actor - Object Type: " + resultSet.get(i).actor.objectType);
                System.out.println("Actor - URL: " + resultSet.get(i).actor.url);
                

                System.out.println("\n");
            }
        }

        catch (IllegalArgumentException | IllegalStateException | IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
