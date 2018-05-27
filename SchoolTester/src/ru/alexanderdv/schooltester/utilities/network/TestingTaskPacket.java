package ru.alexanderdv.schooltester.utilities.network;

import ru.alexanderdv.schooltester.utilities.types.TestingTask;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class TestingTaskPacket extends NetworkPacket
{
	private static final long serialVersionUID = -5954533424018828454L;
	private final TestingTask task;
	private final String student;

	public TestingTaskPacket(String request, String student, TestingTask task)
	{
		super(request);
		this.task = task;
		this.student = student;
	}

	/**
	 * @return the task
	 */
	public TestingTask getTask()
	{
		return task;
	}

	/**
	 * @return the student
	 */
	public String getStudent()
	{
		return student;
	}

}
