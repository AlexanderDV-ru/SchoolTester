package ru.alexanderdv.schooltester.utilities.questionvars;

import java.io.Serializable;

/**
 * 
 * @author AlexanderDV
 *
 * @param <AnswerType>
 * @version 6.1.5a
 */
public abstract class Result<AnswerType extends AnswerVariant> implements Serializable
{
	private static final long serialVersionUID = 4729259265631478887L;
	private final int minResult;
	private final int result;
	private final int maxResult;

	/**
	 * @param minResult
	 * @param result
	 * @param maxResult
	 */
	public Result(int minResult, int result, int maxResult)
	{
		this.minResult = minResult;
		this.result = result;
		this.maxResult = maxResult;
	}

	/**
	 * @return the minResult
	 */
	public int getMinResult()
	{
		return minResult;
	}

	/**
	 * @return the result
	 */
	public int getResult()
	{
		return result;
	}

	/**
	 * @return the maxResult
	 */
	public int getMaxResult()
	{
		return maxResult;
	}
}