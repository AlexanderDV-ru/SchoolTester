package ru.alexanderdv.schooltester.utilities;

import java.awt.Font;
import java.util.HashMap;

/**
 * Question - the class with data about question.
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.0.0a
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
	private final int[] answersIndexes;
	private final HashMap<HashMap<Integer, Integer>, Integer> answerArrangement;
	private final String[] indexesForNames;
	private final boolean handleOnlyMaximal;
	private final int maxAward;
	private final String ignoredCharacters;
	private final boolean ignoreCase;

	/**
	 * 
	 * @param text
	 * @param font
	 * @param award
	 * @param minResult
	 * @param type
	 * @param answers
	 * @param handleOnlyMaximal
	 * @param answerArrangement
	 * @param ignoredCharacters
	 * @param ignoreCase
	 */
	public Question(String text, Font font, int award, int minResult, QuestionType type, Answer[] answers, boolean handleOnlyMaximal,
			HashMap<HashMap<Integer, Integer>, Integer> answerArrangement, String[] indexesForNames, String ignoredCharacters, boolean ignoreCase)
	{
		super();
		this.text = text;
		this.font = font;
		this.award = award;
		this.minResult = minResult;
		this.type = type;
		this.answers = answers;
		this.answersIndexes = new int[this.answers.length];
		for (int i = 0; i < this.answers.length; i++)
			this.answersIndexes[i] = this.answers[i].index;
		this.indexesForNames = indexesForNames;
		this.answerArrangement = answerArrangement;
		this.handleOnlyMaximal = handleOnlyMaximal;
		this.ignoredCharacters = ignoredCharacters;
		this.ignoreCase = ignoreCase;
		int max = 0;
		switch (type)
		{
			case PickOne:
				for (int i = 0; i < this.answers.length; i++)
					max = Math.max(max, this.answers[i].getAward());
				break;
			case SelectSome:
				for (int i = 0; i < this.answers.length; i++)
					max += Math.max(0, this.answers[i].getAward());
				break;
			case EnterText:
				for (int i = 0; i < this.answers.length; i++)
					max = Math.max(max, this.answers[i].getAward());
				break;
			case Arrangement:
				for (int i = 0; i < this.answerArrangement.size(); i++)
					if (handleOnlyMaximal)
						max = Math.max(max, this.answerArrangement.values().toArray(new Integer[] { })[i]);
					else max += Math.max(0, this.answerArrangement.values().toArray(new Integer[] { })[i]);
				break;
			case Matching:
				for (int i = 0; i < this.answerArrangement.size(); i++)
					if (handleOnlyMaximal)
						max = Math.max(max, this.answerArrangement.values().toArray(new Integer[] { })[i]);
					else max += Math.max(0, this.answerArrangement.values().toArray(new Integer[] { })[i]);
				break;
			case Distribution:
				for (int i = 0; i < this.answerArrangement.size(); i++)
					if (handleOnlyMaximal)
						max = Math.max(max, this.answerArrangement.values().toArray(new Integer[] { })[i]);
					else max += Math.max(0, this.answerArrangement.values().toArray(new Integer[] { })[i]);
				break;
		}
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
	 * @return the answers indexes
	 */
	public int[] getAnswersIndexes()
	{
		return answersIndexes;
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

	/**
	 * @return the answerArragement
	 */
	public HashMap<HashMap<Integer, Integer>, Integer> getAnswerArrangement()
	{
		return answerArrangement;
	}

	/**
	 * @return the indexesForNames
	 */
	public String[] getIndexesForNames()
	{
		return indexesForNames;
	}

	/**
	 * @return the handleOnlyMaximal
	 */
	public boolean isHandleOnlyMaximal()
	{
		return handleOnlyMaximal;
	}

	/**
	 * Answer - the class with data about answer to question.
	 * 
	 * @author AlexanderDV/AlexandrDV
	 * @version 5.0.0a
	 */
	public static class Answer
	{
		private final String text;
		private final Font font;
		private final int award;
		private int index;

		/**
		 * 
		 * @param award
		 * @param text
		 * @param font
		 */
		public Answer(String text, Font font, int award, int index)
		{
			this.text = text;
			this.font = font;
			this.award = award;
			this.index = index;
		}

		/**
		 * @return the award
		 */
		public int getAward()
		{
			return award;
		}

		/**
		 * @return the index
		 */
		public int getIndex()
		{
			return index;
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

	/**
	 * QuestionType - type of question answering.
	 * 
	 * @author AlexanderDV/AlexandrDV
	 * @version 5.0.0a
	 */
	public static enum QuestionType
	{
		PickOne,
		SelectSome,
		EnterText,
		Matching,
		Arrangement,
		Distribution
	}
}
