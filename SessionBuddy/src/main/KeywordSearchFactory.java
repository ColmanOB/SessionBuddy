package main;

public class KeywordSearchFactory 
	{
	
	public KeywordSearch<?> getSearch(String category) throws IllegalArgumentException
		{
		
		if(category.equalsIgnoreCase("Sessions"))
			{
			return new SearchSessions();
			}
		
		else if (category.equalsIgnoreCase("Tunes"))
			{
			return new SearchTunes();
			}
		
		else if (category.equalsIgnoreCase("Discussions"))
			{
			return new SearchDiscussions();
			}
		
		else if (category.equalsIgnoreCase("Events"))
			{
			return new SearchEvents();
			}
		
		else if (category.equalsIgnoreCase("Recordings"))
			{
			return new SearchRecordings();
			}
		
		else throw new IllegalArgumentException();
		}
	}
