package ru.alexanderdv.schooltester.utilities.types;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class StudentInfo
{
	private final String login;
	private final String classNumber;
	private final String classLetter;
	private final String surname;
	private final String name;
	private final String secondName;

	public StudentInfo(String login, String classNumber, String classLetter, String surname, String name, String secondName)
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
}
