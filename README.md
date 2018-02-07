# SessionBuddy

This document is a work in progress.  The intention is to update it over the coming weeks to include a comprehensive guide to using this library, with plenty of working sample code.

SessionBuddy is a Java 'helper library' that makes it very simple for a Java developer to consume the API at https://thesession.org.  More information about the API can be found at https://thesession.org/API.  Note that this version of SessionBuddy does not support the Activity Streams endpoints listed on that page.  The intention is to implement those in a later release.

SessionBuddy removes the need for the developer to: 
* build and format the URLs required to search for or access the desired data
* deal with parsing the JSON response into a usable structure

SessionBuddy requires Google's Gson (https://github.com/google/gson) for parsing the JSON data returned from the API into Java objects.  To use SessionBuddy, you will need to add Gson to your project's build path.
