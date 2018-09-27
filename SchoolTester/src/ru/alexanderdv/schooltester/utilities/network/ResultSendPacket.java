package ru.alexanderdv.schooltester.utilities.network;

import java.util.Date;

import ru.alexanderdv.schooltester.utilities.types.TestResult;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class ResultSendPacket extends NetworkPacket
{
	private static final long serialVersionUID = 5138131275115427965L;
	private final String teacher;
	private final TestResult result;

	/**
	 * @param request
	 * @param teacher 
	 * @param result
	 */
	public ResultSendPacket(String request, String teacher, TestResult result)
	{
		super(request);
		this.teacher = teacher;
		this.result = result;
	}

	/**
	 * @return the result
	 */
	public TestResult getResult()
	{
		return result;
	}

}
