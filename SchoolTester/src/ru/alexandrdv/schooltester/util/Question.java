package ru.alexandrdv.schooltester.util;

import java.awt.Font;

/**
 * Question
 * 
 * @author AlexandrDV
 * @version 4.2.1a
 *
 */
public class Question
{
	private final String text;
	private final Font font;
	private final int award;
	private final int minResult;
	private final QuestionType type;
	private final Answer[] answers;
	private final int maxAward;
	private final String ignoredCharacters;
	private final boolean ignoreCase;

	/**
	 * 
	 * @param question
	 * @param award
	 * @param pickOneType
	 * @param answers
	 */
	public Question(String text, Font font, int award, int minResult, QuestionType type, Answer[] answers, String ignoredCharacters, boolean ignoreCase)
	{
		super();
		this.text = text;
		this.font = font;
		this.award = award;
		this.minResult = minResult;
		this.type = type;
		this.answers = answers;
		this.ignoredCharacters = ignoredCharacters;
		this.ignoreCase = ignoreCase;

		int max = 0;
		for (int i = 0; i < answers.length; i++)
			if (type == QuestionType.PickOne)
				max = Math.max(max, answers[i].getAward());
			else if (type == QuestionType.SelectSome)
				max += Math.max(0, answers[i].getAward());
			else if (type == QuestionType.EnterText)
				max = Math.max(max, answers[i].getAward());
		maxAward = award + max;

	}

	/**
	 * @return the minResult
	 */
	public int getMinResult()
	{
		return minResult;
	}

	/**
	 * @return the maxAward
	 */
	public int getMaxAward()
	{
		return maxAward;
	}

	/**
	 * @return the type of question
	 */
	public QuestionType getType()
	{
		return type;
	}

	/**
	 * @return the text of question
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * @return the font of text
	 */
	public Font getFont()
	{
		return font;
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

	/**
	 * @return the ignoredCharacters
	 */
	public String getIgnoredCharacters()
	{
		return ignoredCharacters;
	}

	/**
	 * @return the ignoreCase
	 */
	public boolean isIgnoreCase()
	{
		return ignoreCase;
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

	public static enum QuestionType
	{
		PickOne,
		SelectSome,
		EnterText
	}
}
