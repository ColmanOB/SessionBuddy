package main;

import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;

import json_object_wrappers.DiscussionByIDResult;
import json_object_wrappers.DiscussionComment;
import json_object_wrappers.TuneByIDResult;
import json_object_wrappers.TuneSetting;
import json_object_wrappers.User;
import response_parsers.DiscussionByIDParser;
import response_parsers.TuneByIDParser;
import result_set_wrappers.DiscussionByIDWrapper;
import result_set_wrappers.TuneByIDWrapper;


public class RetrieveItem 
{
private int pageCount = 0;

// TODO: Fix up comments throughout this method

	/**
	 * @param searchTerms
	 * @param tuneID
	 * @param resultsPerPage
	 * @return
	 * @throws IllegalArgumentException
	 */
	public DiscussionByIDResult getDiscussionByID(String searchTerms, String discussionID, int resultsPerPage) throws IllegalArgumentException
	{
	if (resultsPerPage > 50)
		{
		throw new IllegalArgumentException("Number of results per page must be 50 or less");
		}

	// Make the API call using the tune ID and store the JSON that is returned as a String
	HttpRequestor searcher = new HttpRequestor();
	String apiQueryResults = searcher.submitListRequest("discussions", discussionID, resultsPerPage);
		
	// Parse the returned JSON into a wrapper class to allow access to all elements
	DiscussionByIDParser jsonParser = new DiscussionByIDParser();
	DiscussionByIDWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);

	// Extract each element from the tune entry in the JSON response
	// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response
	String id = (parsedResults.id); 
	String name = (parsedResults.name);
	String url = (parsedResults.url);
	
	// Get the details of the member who originally submitted the tune
	User member = new User(Integer.toString(parsedResults.member.id), StringEscapeUtils.unescapeXml(parsedResults.member.name), parsedResults.member.url);
		
	String date = (parsedResults.date);
	
	// Initalise an ArrayList of TuneSetting objects to hold each individual setting of the tune
	ArrayList<DiscussionComment> comments = new ArrayList<DiscussionComment>();
		
	// Populate the ArrayList of TuneSetting objects by iterating through each setting in the JSON response
	for(int i = 0; i < (parsedResults.comments.length)-1; i++)
		{
		// Populate the User object representing the person who submitted the particular setting
		User commentSubmitter = new User(Integer.toString(parsedResults.comments[i].member.id), parsedResults.comments[i].member.name, parsedResults.comments[i].member.url);
		
		// Populate the TuneSetting object representing information related to a specific setting, including the user set up above
		DiscussionComment currentComment = new DiscussionComment(Integer.parseInt(parsedResults.comments[i].id), parsedResults.comments[i].url, parsedResults.comments[i].subject, parsedResults.comments[i].content, commentSubmitter, parsedResults.comments[i].date);
		
		comments.add(currentComment);				
		}
		
	// Instantiate a TuneByIDResult object & populate it with the details captured above
	DiscussionByIDResult finalResult = new DiscussionByIDResult(id, name, url, member, date, comments);
		
	// Return the set of results that has been collected
	return finalResult;
	}

	
	public TuneByIDResult getTuneByID(String searchTerms, String tuneID, int resultsPerPage) throws IllegalArgumentException
	{
	if (resultsPerPage > 50)
		{
		throw new IllegalArgumentException("Number of results per page must be 50 or less");
		}

	// Make the API call using the tune ID and store the JSON that is returned as a String
	HttpRequestor searcher = new HttpRequestor();
	String apiQueryResults = searcher.submitListRequest("tunes", tuneID, resultsPerPage);
		
	// Parse the returned JSON into a wrapper class to allow access to all elements
	TuneByIDParser jsonParser = new TuneByIDParser();
	TuneByIDWrapper parsedResults = jsonParser.parseResponse(apiQueryResults);

	// Extract each element from the tune entry in the JSON response
	// StringEscapeUtils.unescapeXml() will decode the &039; etc. XML entities from the JSON response
	String id = (parsedResults.id); 
	String name = (parsedResults.name);
	String url = (parsedResults.url);
	
	// Get the details of the member who originally submitted the tune
	User member = new User(Integer.toString(parsedResults.member.id), StringEscapeUtils.unescapeXml(parsedResults.member.name), parsedResults.member.url);
		
	String date = (parsedResults.date);
	String type = (parsedResults.type);
	String tunebooks = (parsedResults.tunebooks);
	String recordings = (parsedResults.recordings);
	
	// Initialize an ArrayList of Strings to store the list of alternative titles for the tune
	ArrayList<String> aliases = new ArrayList<String>();
	
	// Iterate through each of the alternative titles in the JSON and use them to populate the ArrayList
	for (int i = 0; i < parsedResults.aliases.length; i++)
		{
		aliases.add(parsedResults.aliases[i]);
		}
		
	// Initalise an ArrayList of TuneSetting objects to hold each individual setting of the tune
	ArrayList<TuneSetting> settings = new ArrayList<TuneSetting>();
		
	// Populate the ArrayList of TuneSetting objects by iterating through each setting in the JSON response
	for(int i = 0; i < (parsedResults.settings.length)-1; i++)
		{
		// Populate the User object representing the person who submitted the particular setting
		User settingSubmitter = new User(Integer.toString(parsedResults.settings[i].member.id), parsedResults.settings[i].member.name, parsedResults.settings[i].member.url);
		
		// Populate the TuneSetting object representing information related to a specific setting, including the user set up above
		TuneSetting currentSetting = new TuneSetting(Integer.parseInt(parsedResults.settings[i].id), parsedResults.settings[i].url, parsedResults.settings[i].key, parsedResults.settings[i].abc, settingSubmitter, parsedResults.settings[i].date);
		
		settings.add(currentSetting);				
		}
		
	// Instantiate a TuneByIDResult object & populate it with the details captured above
	TuneByIDResult finalResult = new TuneByIDResult(id, name, url, member, date, type, tunebooks, recordings, aliases, settings);
		
	// Return the set of results that has been collected
	return finalResult;
	}
	
	
	public int getPageCount() throws IllegalStateException
		{
		if (pageCount == 0)
			{
			throw new IllegalStateException("Page counter has not been initialised");
			}
		else 
			return pageCount;
		}
	

}
