package ru.alexanderdv.schooltester.utilities;

import java.util.ArrayList;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.8.0a
 */
public class UserAnswer
{
	private final int selectedAnswerNumber;
	private final boolean[] selectedAnswers;
	private final int[] answerArrangement;
	private final ArrayList<Integer>[] answerArrangementForDistribution;
	private final String answerText;

	public UserAnswer(int selectedAnswerNumber, boolean[] selectedAnswers, int[] answerArrangement,ArrayList<Integer>[] answerArrangementForDistribution, String answerText)
	{
		this.selectedAnswerNumber = selectedAnswerNumber;
		this.selectedAnswers = selectedAnswers;
		this.answerArrangement = answerArrangement;
		this.answerArrangementForDistribution = answerArrangementForDistribution;
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

	public ArrayList<Integer>[] getAnswerArrangementForDistribution()
	{
		return answerArrangementForDistribution;
	}
}
