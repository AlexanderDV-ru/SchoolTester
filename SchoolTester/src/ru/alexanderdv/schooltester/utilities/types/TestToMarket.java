package ru.alexanderdv.schooltester.utilities.types;

import java.io.Serializable;
import java.util.HashMap;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class TestToMarket implements Serializable
{
	private static final long serialVersionUID = 8389259265631478887L;
	private String programVersion;
	private String testVersion;
	private String testCreationDate;
	private String testLanguage;
	private String testSubject;
	private String authors;
	private String name;
	private String description;
	private String dirName;
	private HashMap<String, byte[]> files;

	public TestToMarket(String programVersion, String testVersion, String testCreationDate, String testLanguage,
			String testSubject, String authors, String name, String description,String dirName, HashMap<String, byte[]> files)
	{
		this.programVersion = programVersion;
		this.testVersion = testVersion;
		this.testCreationDate = testCreationDate;
		this.testLanguage = testLanguage;
		this.testSubject = testSubject;
		this.authors = authors;
		this.name = name;
		this.description = description;
		this.dirName = dirName;
		this.files = files;
	}

	public TestToMarket()
	{
	}

	/**
	 * @return the programVersion
	 */
	public String getProgramVersion()
	{
		return programVersion;
	}

	/**
	 * @return the testVersion
	 */
	public String getTestVersion()
	{
		return testVersion;
	}

	/**
	 * @return the testCreationDate
	 */
	public String getTestCreationDate()
	{
		return testCreationDate;
	}

	/**
	 * @return the testLanguage
	 */
	public String getTestLanguage()
	{
		return testLanguage;
	}

	/**
	 * @return the testSubject
	 */
	public String getTestSubject()
	{
		return testSubject;
	}

	/**
	 * @return the authors
	 */
	public String getAuthors()
	{
		return authors;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return the files
	 */
	public HashMap<String, byte[]> getFiles()
	{
		return files;
	}

	public String getDirName()
	{
		return dirName;
	}

}