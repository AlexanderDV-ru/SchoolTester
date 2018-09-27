package ru.alexanderdv.schooltester.utilities.questionvars;

import java.util.HashMap;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class EnterTextResult extends Result<EnterTextAnswerVariant>
{
	private final String answerText;
	private final HashMap<String, Integer> correctAnswers;
	private final HashMap<String, Integer> passableAnswers;

	/**
	 * @param minResult
	 * @param result
	 * @param maxResult
	 * @param answerText
	 * @param correctAnswers
	 * @param passableAnswers
	 */
	public EnterTextResult(int minResult, int result, int maxResult, String answerText,
			HashMap<String, Integer> correctAnswers, HashMap<String, Integer> passableAnswers)
	{
		super(minResult, result, maxResult);
		this.answerText = answerText;
		this.correctAnswers = correctAnswers;
		this.passableAnswers = passableAnswers;
	}

	/**
	 * @return the answerText
	 */
	public String getAnswerText()
	{
		return answerText;
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
