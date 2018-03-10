package ru.alexanderdv.schooltester.utilities;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.5.0a
 */
public class UserAnswer
{
	private final int selectedAnswerNumber;
	private final boolean[] selectedAnswers;
	private final int[] answerArrangement;
	private final String answerText;

	public UserAnswer(int selectedAnswerNumber, boolean[] selectedAnswers, int[] answerArrangement, String answerText)
	{
		this.selectedAnswerNumber = selectedAnswerNumber;
		this.selectedAnswers = selectedAnswers;
		this.answerArrangement = answerArrangement;
		this.answerText = answerText;
	}

	/**
	 * @return the selectedAnswerNumber
	 */
	public int getSelectedAnswerNumber()
	{
		return selectedAnswerNumber;
	}

	/**
	 * @return the selectedAnswers
	 */
	public boolean[] getSelectedAnswers()
	{
		return selectedAnswers;
	}

	/**
	 * @return the answerText
	 */
	public String getAnswerText()
	{
		return answerText;
	}

	/**
	 * 
	 * @return
	 */
	public int[] getAnswerArrangement()
	{
		return answerArrangement;
	}
}
