# SessionBuddy

This document is a work in progress.  The intention is to update it over the coming weeks to include a comprehensive guide to using this library, with plenty of working sample code.

SessionBuddy is a Java 'helper library' that makes it very simple for a Java developer to consume the API at https://thesession.org.  More information about the API can be found at https://thesession.org/API.  Note that this version of SessionBuddy does not support the Activity Streams endpoints listed on that page.  The intention is to implement those in a later release.

SessionBuddy removes the need for the developer to: 
* build and format the URLs required to search for or access the desired data
* deal with parsing the JSON response into a usable structure

SessionBuddy requires Google's Gson (https://github.com/google/gson) for parsing the JSON data returned from the API into Java objects.  To use SessionBuddy, you will need to add Gson to your project's build path.

Here is a basic overview of how to use SessionBuddy:

### Step 1:
Create the appropriate type of SessionBuddy object for the type of API query you want.  Depending on what kind of information you want to retrieve, this object will be one of the following types:
* ItemRetriever
* KeywordSearch
* LatestSearch
* LocationSearch

You create the object simply by calling its default constructor, without needing to pass in any arguments.  A `KeywordSearch` object, for example, is created like this:

`KeywordSearch search = new KeywordSearch();`

### Step 2:

Call one of the new object's public methods to actually make the API call, and store the returned object.  The type of object returned will depend on the individual method.  A description of each method and its return type can be found either in the Javadoc or the page in this wiki corresponding to the class in question.

As an example, taking our new KeywordSearch object created in the previous step, we can search for tunes by keyword by calling its `searchTunes(String searchTerms, int resultsPerPage)` method.  

This method returns an ArrayList of TunesSearchResult objects, so let's first create the ArrayList to hold the result:

`ArrayList<TunesSearchResult> resultSet = new ArrayList<TunesSearchResult>();`

Now we can call the searchTunes method, where the first argument is the set of search terms, and the second argument is the number of results per page to be returned in the response.

`resultSet = searchTunes("The Old Bush", 5);`

### Step 3:
Now you have an ArrayList of TunesSearchResult objects, you can simply iterate through each of those and extract the desired elements, for example:

	// Loop through the results and print each attribute of each individual result in the set
	for (int i = 0; i < resultSet.size(); i++)
		{
		System.out.println("Tune ID: " + resultSet.get(i).details.tuneID);
		System.out.println("Tune Title: " + resultSet.get(i).details.tuneTitle);
		System.out.println("Tune Type: " + resultSet.get(i).details.tuneType);
		System.out.println("Tune URL: " + resultSet.get(i).details.tuneURL);
		System.out.println("Date Submitted: " + resultSet.get(i).details.submittedDate);
		
		System.out.println("Submitted by User ID: " + resultSet.get(i).submitter.userID);
		System.out.println("Submitted by Username: " + resultSet.get(i).submitter.userName);
		System.out.println("Submitter's Profile Page: " + resultSet.get(i).submitter.userURL);
		
		System.out.println("\n");
		}


This should cause the following output to be printed to screen:


Tune ID: 1499  
Tune Title: The Old Bush  
Tune Type: reel  
Tune URL: https://thesession.org/tunes/1499  
Date Submitted: 2003-03-11 08:59:35  
Submitted by User ID: 3080  
Submitted by Username: gian marco  
Submitter's Profile Page: https://thesession.org/members/3080  


Tune ID: 13432  
Tune Title: Da Bush Below Da Gairden  
Tune Type: reel  
Tune URL: https://thesession.org/tunes/13432  
Date Submitted: 2014-03-01 20:02:20  
Submitted by User ID: 11834  
Submitted by Username: Nigel Gatherer  
Submitter's Profile Page: https://thesession.org/members/11834  


Tune ID: 10018  
Tune Title: The Hawthorn Bush  
Tune Type: strathspey  
Tune URL: https://thesession.org/tunes/10018  
Date Submitted: 2009-11-09 13:45:55  
Submitted by User ID: 11705  
Submitted by Username: ceolachan  
Submitter's Profile Page: https://thesession.org/members/11705  


Tune ID: 629  
Tune Title: The Bird In The Bush  
Tune Type: reel  
Tune URL: https://thesession.org/tunes/629  
Date Submitted: 2002-04-03 02:46:32  
Submitted by User ID: 1716  
Submitted by Username: Josh Kane  
Submitter's Profile Page: https://thesession.org/members/1716  


Tune ID: 1305  
Tune Title: The Bush On The Hill  
Tune Type: jig  
Tune URL: https://thesession.org/tunes/1305  
Date Submitted: 2003-01-07 06:47:53  
Submitted by User ID: 1173  
Submitted by Username: b.maloney  
Submitter's Profile Page: https://thesession.org/members/1173  
