package ru.alexanderdv.schooltester.utilities.types;

import ru.alexanderdv.schooltester.utilities.enums.SearchType;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class TestSearchResult extends SearchResult
{
	private static final long serialVersionUID = 5730541896821079456L;
	private final TestToMarket testToMarket;
	private final Double views;
	private final Double loads;
	private final Double likes;
	private final Double rate;

	

	public TestSearchResult(String author, String test, String version, TestToMarket testToMarket, Double views, Double loads, Double likes,
			Double rate)
	{
		super(SearchType.Test, author, test, version);
		this.testToMarket = testToMarket;
		this.views = views;
		this.loads = loads;
		this.likes = likes;
		this.rate = rate;
	}

	public TestToMarket getTestToMarket()
	{
		return testToMarket;
	}

	/**
	 * @return the views
	 */
	public Double getViews()
	{
		return views;
	}

	/**
	 * @return the loads
	 */
	public Double getLoads()
	{
		return loads;
	}

	/**
	 * @return the likes
	 */
	public Double getLikes()
	{
		return likes;
	}

	/**
	 * @return the rate
	 */
	public Double getRate()
	{
		return rate;
	}

}
