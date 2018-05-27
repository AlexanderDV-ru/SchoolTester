package ru.alexanderdv.schooltester.utilities.network;

import ru.alexanderdv.schooltester.utilities.enums.SearchType;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class MarketUserDoPacket extends NetworkPacket
{
	private static final long serialVersionUID = 4878911369036005356L;
	private final SearchType type;
	private final String author;
	private final String test;
	private final String version;
	private final String login;

	public MarketUserDoPacket(String request, SearchType type, String author, String test, String version, String login)
	{
		super(request);
		this.type = type;
		this.author = author;
		this.test = test;
		this.version = version;
		this.login = login;
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

	/**
	 * @return the login
	 */
	public String getLogin()
	{
		return login;
	}

}
