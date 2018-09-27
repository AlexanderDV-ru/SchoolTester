package ru.alexanderdv.schooltester.utilities.types;

import java.io.Serializable;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class TesteeInfo implements Serializable
{
	private final String login;
	private final String classNumber;
	private final String classLetter;
	private final String surname;
	private final String name;
	private final String secondName;

	/**
	 * @param teacherLogin
	 * @param studentLogin
	 * @param classNumber
	 * @param classLetter
	 * @param surname
	 * @param name
	 * @param secondName
	 */
	public TesteeInfo(String login, String classNumber, String classLetter, String surname, String name,
			String secondName)
	{
		this.login = login;
		this.classNumber = classNumber;
		this.classLetter = classLetter;
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
	 * @return the classNumber
	 */
	public String getClassNumber()
	{
		return classNumber;
	}

	/**
	 * @return the classLetter
	 */
	public String getClassLetter()
	{
		return classLetter;
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
		return login + ", " + surname + " " + name + " " + secondName + ", " + classNumber + " " + classLetter;
	}
}
