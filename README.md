# SessionBuddy

SessionBuddy is a Java 'helper library' that makes it very simple for a Java developer to consume the API at https://thesession.org.  More information about the API can be found at https://thesession.org/API.  Note that SessionBuddy does not currently support the Activity Streams endpoints listed on that page.  The intention is to implement those in a later release.

SessionBuddy removes the need for the developer to: 
* build and format the URLs required to search for or access the desired data
* deal with parsing the JSON response into a usable structure

SessionBuddy uses Google's [Gson](https://github.com/google/gson) to parse the JSON data returned from the API into Java objects.

For user documentation, see the project's [Wiki](https://github.com/ColmanOB/SessionBuddy/wiki), the sample code in the [example_usage](https://github.com/ColmanOB/SessionBuddy/tree/master/SessionBuddy/src/example_usage) package, and the [JavaDoc](https://github.com/ColmanOB/SessionBuddy/releases) bundled with the current release.
