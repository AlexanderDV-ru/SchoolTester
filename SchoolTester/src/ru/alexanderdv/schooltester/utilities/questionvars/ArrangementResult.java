package ru.alexanderdv.schooltester.utilities.questionvars;

import java.util.HashMap;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class ArrangementResult extends Result<ArrangementAnswerVariant>
{
	private final HashMap<Integer, String> arrangement;

	public ArrangementResult(int minResult, int result, int maxResult, HashMap<Integer, String> arrangement)
	{
		super(minResult, result, maxResult);
		this.arrangement = arrangement;
	}

	/**
	 * @return the arrangement
	 */
	public HashMap<Integer, String> getArrangement()
	{
		return arrangement;
	}

}
