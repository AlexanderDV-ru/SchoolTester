package ru.alexandrdv.schooltester.util;

/**
 * Question v1.7.0a
 * 
 * @author AlexandrDV
 *
 */
public class Question
{
	private String question;
	private String[] answers;
	private int award;
	private int[] awards;
	private int[] fontSizes;

	/**
	 * 
	 * @param question
	 * @param answers
	 * @param awards
	 * @param fontSizes
	 */
	public Question(String question, int award, String[] answers, int[] awards, int[] fontSizes)
	{
		super();
		this.question = question;
		this.award = award;
		this.answers = answers;
		this.awards = awards;
		this.fontSizes = fontSizes;
	}

	/**
	 * @return the question
	 */
	public String getQuestion()
	{
		return question;
	}

	/**
	 * @param question
	 *            - question to set
	 */
	public void setQuestion(String question)
	{
		this.question = question;
	}

	/**
	 * @return the answers
	 */
	public String[] getAnswers()
	{
		return answers;
	}

	/**
	 * @param answers
	 *            the answers to set
	 */
	public void setAnswers(String[] answers)
	{
		this.answers = answers;
	}

	/**
	 * @return the awards
	 */
	public int[] getAwards()
	{
		return awards;
	}

	/**
	 * @param awards
	 *            the awards to set
	 */
	public void setAwards(int[] awards)
	{
		this.awards = awards;
	}

	/**
	 * @return the fontSizes
	 */
	public int[] getFontSizes()
	{
		return fontSizes;
	}

	/**
	 * @param fontSizes
	 *            the fontSizes to set
	 */
	public void setFontSizes(int[] fontSizes)
	{
		this.fontSizes = fontSizes;
	}

	/**
	 * Returns default award of question
	 * 
	 * @return default award of question
	 */
	public int getAward()
	{
		return this.award;
	}
}
