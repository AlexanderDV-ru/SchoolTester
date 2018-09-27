package ru.alexanderdv.schooltester.utilities.questionvars;

import java.util.HashMap;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class SelectMultipleResult extends Result<SelectMultipleAnswerVariant>
{
	private final HashMap<String, Integer> selectedAnswers;
	private final HashMap<String, Integer> correctAnswers;

	/**
	 * @param minResult
	 * @param result
	 * @param maxResult
	 * @param selectedAnswers
	 * @param correctAnswers
	 */
	public SelectMultipleResult(int minResult, int result, int maxResult, HashMap<String, Integer> selectedAnswers,
			HashMap<String, Integer> correctAnswers)
	{
		super(minResult, result, maxResult);
		this.selectedAnswers = selectedAnswers;
		this.correctAnswers = correctAnswers;
	}

	/**
	 * @return the selectedAnswers
	 */
	public HashMap<String, Integer> getSelectedAnswers()
	{
		return selectedAnswers;
	}

	/**
	 * @return the correctAnswers
	 */
	public HashMap<String, Integer> getCorrectAnswers()
	{
		return correctAnswers;
	}

}
