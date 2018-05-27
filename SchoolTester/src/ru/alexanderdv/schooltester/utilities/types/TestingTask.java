package ru.alexanderdv.schooltester.utilities.types;

import java.io.Serializable;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class TestingTask implements Serializable
{
	private static final long serialVersionUID = 2066912458987023167L;
	private final TaskType type;
	private final String teacher;
	private final TestToMarket test;
	private final TestSettings settings;
	private final TestingPartSettings testingPartSettings;

	public TestingTask(TaskType type, String teacher, TestToMarket test, TestSettings settings, TestingPartSettings testingPartSettings)
	{
		this.type = type;
		this.teacher = teacher;
		this.test = test;
		this.settings = settings;
		this.testingPartSettings = testingPartSettings;
	}

	/**
	 * @return the type
	 */
	public TaskType getType()
	{
		return type;
	}

	/**
	 * @return the teacher
	 */
	public String getTeacher()
	{
		return teacher;
	}

	/**
	 * @return the test
	 */
	public TestToMarket getTest()
	{
		return test;
	}

	/**
	 * @return the settings
	 */
	public TestSettings getSettings()
	{
		return settings;
	}

	/**
	 * @return the testingPartSettings
	 */
	public TestingPartSettings getTestingPartSettings()
	{
		return testingPartSettings;
	}

	public static enum TaskType
	{
		Homework,
		Control,

	}

	public boolean get()
	{
		return false;
	}
}
