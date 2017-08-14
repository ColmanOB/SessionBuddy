package white_box_testing;

import java.util.ArrayList;

import json_object_wrappers.TuneByIDResult;
import json_object_wrappers.TunesSearchResult;
import main.KeywordSearch;
import main.RetrieveItem;

public class Test_TuneByID 
{
	public static void main(String[] args)
		{
		// Set the search parameters
		String searchTerms = "tunes";
		String tuneID = "2";
		int resultsPerPage = 50;
		
		
		// Instantiate a TheSessionAPISearcher object
		RetrieveItem search = new RetrieveItem();
		
		// Pass in the search parameters
		ArrayList<TuneByIDResult> resultSet = search.getTuneByID(searchTerms, tuneID, resultsPerPage);
		
		// Loop through the results and print each attribute of each individual result in the set
		for (int i = 0; i < resultSet.size(); i++)
			{
			
			System.out.println(resultSet.get(i).url);
			System.out.println(resultSet.get(i).type);
			System.out.println(resultSet.get(i).settings.length);
			System.out.println(resultSet.get(i).settings[i].abc);
			
			System.out.println("\n");
			}
		}

}
