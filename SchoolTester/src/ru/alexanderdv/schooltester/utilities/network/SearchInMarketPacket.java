package ru.alexanderdv.schooltester.utilities.network;

import java.util.ArrayList;

import ru.alexanderdv.schooltester.main.utilities.InitMarketPart.SearchType;
import ru.alexanderdv.schooltester.utilities.types.Test;

/**
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.5a
 */
public class SearchInMarketPacket extends NetworkPacket
{
	private static final long serialVersionUID = 2597489610668259494L;
	private final ArrayList<SearchResult> results;
	private final SearchType type;
	private final int page;

	public SearchInMarketPacket(String request, String mac, String ip, SearchType type, int page, SearchResult... results)
	{
		super(request, mac, ip);
		this.type = type;
		this.page = page;
		this.results = new ArrayList<SearchResult>();
		for (SearchResult result : results)
			this.results.add(result);
	}

	/**
	 * @return the type
	 */
	public SearchType getType()
	{
		return type;
	}

	/**
	 * @return the page
	 */
	public int getPage()
	{
		return page;
	}

	/**
	 * @return the page
	 */
	public ArrayList<SearchResult> getResults()
	{
		return results;
	}

	public static abstract class SearchResult
	{
		private final SearchType type;

		public SearchResult(SearchType type)
		{
			this.type = type;
		}

		/**
		 * @return the type
		 */
		public SearchType getType()
		{
			return type;
		}

	}

	public static class TestSearchResult extends SearchResult
	{
		private final Test test;

		public TestSearchResult(Test test)
		{
			super(SearchType.Test);
			this.test = test;
		}

		/**
		 * @return the type
		 */
		public Test getTest()
		{
			return test;
		}

	}
}
