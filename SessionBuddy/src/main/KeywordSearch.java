package main;

public interface KeywordSearch<T>
	{
	T executeSearch(String searchTerms, int resultsPerPage);
	T executeSearch(String searchTerms, int resultsPerPage, int pageNumber);
	}
