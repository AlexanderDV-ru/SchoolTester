package ru.alexanderdv.schooltester.utilities.network;

import java.util.Date;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class ResultSendPacket extends NetworkPacket
{
	private static final long serialVersionUID = 5138131275115427965L;

	private final String result;
	private final String student;
	private final String teacher;
	private final Date date;
	private final String accSurname;
	private final String accName;
	private final String accSecondName;
	private final String selClassNumber;
	private final String selClassLetter;
	private final String selSurname;
	private final String selName;
	private final String selSecondName;

	public ResultSendPacket(String request, String result, String student, String teacher, Date date, String selClassNumber, String selClassLetter,
			String accSurname, String accName, String accSecondName, String selSurname, String selName, String selSecondName)
	{
		super(request);
		this.result = result;
		this.student = student;
		this.teacher = teacher;
		this.date = date;
		this.accSurname = accSurname;
		this.accName = accName;
		this.accSecondName = accSecondName;
		this.selClassNumber = selClassNumber;
		this.selClassLetter = selClassLetter;
		this.selSurname = selSurname;
		this.selName = selName;
		this.selSecondName = selSecondName;
	}

	/**
	 * @return the result
	 */
	public String getResult()
	{
		return result;
	}

	/**
	 * @return the student
	 */
	public String getStudent()
	{
		return student;
	}

	/**
	 * @return the teacher
	 */
	public String getTeacher()
	{
		return teacher;
	}

	/**
	 * @return the date
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * @return the selClassNumber
	 */
	public String getSelClassNumber()
	{
		return selClassNumber;
	}

	/**
	 * @return the selClassLetter
	 */
	public String getSelClassLetter()
	{
		return selClassLetter;
	}

	/**
	 * @return the accSurname
	 */
	public String getAccSurname()
	{
		return accSurname;
	}

	/**
	 * @return the accName
	 */
	public String getAccName()
	{
		return accName;
	}

	/**
	 * @return the accSecondName
	 */
	public String getAccSecondName()
	{
		return accSecondName;
	}

	/**
	 * @return the selSurname
	 */
	public String getSelSurname()
	{
		return selSurname;
	}

	/**
	 * @return the selName
	 */
	public String getSelName()
	{
		return selName;
	}

	/**
	 * @return the selSecondName
	 */
	public String getSelSecondName()
	{
		return selSecondName;
	}

}
