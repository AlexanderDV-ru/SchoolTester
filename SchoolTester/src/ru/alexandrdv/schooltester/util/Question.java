package ru.alexandrdv.schooltester.util;

import java.awt.Font;

/**
 * Question
 * 
 * @author AlexandrDV
 * @version 2.0.0a
 *
 */
public class Question
{
	private final String question;
	private final int award;
	private final boolean pickOneType;
	private final Answer[] answers;

	/**
	 * 
	 * @param question
	 * @param award
	 * @param pickOneType
	 * @param answers
	 */
	public Question(String question, int award, boolean pickOneType, Answer[] answers)
	{
		super();
		this.question = question;
		this.award = award;
		this.pickOneType = pickOneType;
		this.answers = answers;
	}

	/**
	 * @return the pickOneType
	 */
	public boolean isPickOneType()
	{
		return pickOneType;
	}

	/**
	 * @return the question
	 */
	public String getQuestion()
	{
		return question;
	}

	/**
	 * @return the answers
	 */
	public Answer[] getAnswers()
	{
		return answers;
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

	public static class Answer
	{
		private final String text;
		private final Font font;
		private final int award;

		/**
		 * 
		 * @param award
		 * @param text
		 * @param font
		 */
		public Answer(String text, Font font, int award)
		{
			this.text = text;
			this.font = font;
			this.award = award;
		}

		/**
		 * @return the award
		 */
		public int getAward()
		{
			return award;
		}

		/**
		 * @return the text
		 */
		public String getText()
		{
			return text;
		}

		/**
		 * @return the font
		 */
		public Font getFont()
		{
			return font;
		}
	}
}
