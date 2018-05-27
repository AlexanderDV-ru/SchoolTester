package ru.alexanderdv.schooltester.utilities.network;

import ru.alexanderdv.schooltester.utilities.enums.SearchType;
import ru.alexanderdv.schooltester.utilities.types.SearchResult;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class SearchInMarketPacket extends NetworkPacket
{
	private static final long serialVersionUID = 2597489610668259494L;
	private final SearchResult[] results;
	private final SearchType type;
	private final int page;

	public SearchInMarketPacket(String request, SearchType type, int page, SearchResult... results)
	{
		super(request);
		this.type = type;
		this.page = page;
		this.results = results;
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
	public SearchResult[] getResults()
	{
		return results;
	}
}