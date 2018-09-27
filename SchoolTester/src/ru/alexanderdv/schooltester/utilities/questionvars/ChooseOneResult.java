package ru.alexanderdv.schooltester.utilities.questionvars;

import java.util.HashMap;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class ChooseOneResult extends Result<ChooseOneAnswerVariant>
{
	private final String currentAnswer;
	private final HashMap<String, Integer> correctAnswers;
	private final HashMap<String, Integer> passableAnswers;

	/**
	 * @param minResult
	 * @param result
	 * @param maxResult
	 * @param currentAnswer
	 * @param correctAnswers
	 * @param passableAnswers
	 */
	public ChooseOneResult(int minResult, int result, int maxResult, String currentAnswer,
			HashMap<String, Integer> correctAnswers, HashMap<String, Integer> passableAnswers)
	{
		super(minResult, result, maxResult);
		this.currentAnswer = currentAnswer;
		this.correctAnswers = correctAnswers;
		this.passableAnswers = passableAnswers;
	}

	/**
	 * @return the currentAnswer
	 */
	public String getCurrentAnswer()
	{
		return currentAnswer;
	}

	/**
	 * @return the correctAnswers
	 */
	public HashMap<String, Integer> getCorrectAnswers()
	{
		return correctAnswers;
	}

	/**
	 * @return the passableAnswers
	 */
	public HashMap<String, Integer> getPassableAnswers()
	{
		return passableAnswers;
	}

}
