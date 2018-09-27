package ru.alexanderdv.schooltester.utilities.types;

import java.io.Serializable;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class TesterInfo implements Serializable
{
	private final String login;
	private final String surname;
	private final String name;
	private final String secondName;

	/**
	 * @param login
	 * @param surname
	 * @param name
	 * @param secondName
	 */
	public TesterInfo(String login, String surname, String name, String secondName)
	{
		this.login = login;
		this.surname = surname;
		this.name = name;
		this.secondName = secondName;
	}

	/**
	 * @return the login
	 */
	public String getLogin()
	{
		return login;
	}

	/**
	 * @return the surname
	 */
	public String getSurname()
	{
		return surname;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the secondName
	 */
	public String getSecondName()
	{
		return secondName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return login + ", " + surname + " " + name + " " + secondName;
	}
}
