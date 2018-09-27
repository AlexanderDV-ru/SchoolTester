package ru.alexanderdv.schooltester.utilities.types;

import java.io.Serializable;

import ru.alexanderdv.schooltester.utilities.enums.SearchType;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public abstract class SearchResult implements Serializable
{
	private static final long serialVersionUID = -1839402380811367171L;
	private SearchType type;
	private String author;
	private String test;
	private String version;

	public SearchResult(SearchType type, String author, String test, String version)
	{
		this.type = type;
		this.author = author;
		this.test = test;
		this.version = version;
	}

	public SearchResult()
	{
	}

	/**
	 * @return the type
	 */
	public SearchType getType()
	{
		return type;
	}

	/**
	 * @return the author
	 */
	public String getAuthor()
	{
		return author;
	}

	/**
	 * @return the test
	 */
	public String getTest()
	{
		return test;
	}

	/**
	 * @return the version
	 */
	public String getVersion()
	{
		return version;
	}

}
