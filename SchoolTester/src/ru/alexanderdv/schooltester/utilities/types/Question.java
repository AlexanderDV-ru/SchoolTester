package ru.alexanderdv.schooltester.utilities.types;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.text.Font;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.simpleutilities.Entry;

/**
 * Question - the class with data about question.
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.8a
 *
 */
public class Question
{
	private static final MessageSystem msgSys = Main.msgSys;
	private final Image[] images;
	private final Media[] videos;
	private final Media[] audios;
	private final String html;
	private final String text;
	private final Font font;
	private final int award;
	private final int minResult;
	private final QuestionType type;
	private final Answer[] answers;
	private final int[] answersIndexes;
	private final HashMap<HashMap<Entry<Integer, Boolean>, ArrayList<Integer>>, Integer> answerArrangementForDistribution;
	private final HashMap<HashMap<Integer, Integer>, Integer> answerArrangementForMatching;
	private final HashMap<HashMap<Integer, Integer>, Integer> answerArrangementForArrangement;
	private final String[] indexesForNames;
	private final int[] indexesForNamesIndexes;
	private final boolean handleOnlyMaximal;
	private final int maxAward;
	private final String ignoredCharacters;
	private final boolean ignoreCase;
	private final int index;

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
	private Question(Image[] images, Media[] videos, Media[] audios, String html, String text, Font font, int award, int minResult, QuestionType type,
			Answer[] answers, boolean handleOnlyMaximal,
			HashMap<HashMap<Entry<Integer, Boolean>, ArrayList<Integer>>, Integer> answerArrangementForDistribution,
			HashMap<HashMap<Integer, Integer>, Integer> answerArrangementForMatching,
			HashMap<HashMap<Integer, Integer>, Integer> answerArrangementForArrangement, String[] indexesForNames, int[] indexesForNamesIndexes,
			String ignoredCharacters, boolean ignoreCase, int index)
	{
		this.images = images;
		this.videos = videos;
		this.audios = audios;
		this.html = html;
		this.text = text;
		this.font = font;
		this.award = award;
		this.minResult = minResult;
		this.type = type;
		this.answers = answers;
		this.indexesForNamesIndexes = indexesForNamesIndexes;
		this.answersIndexes = new int[this.answers.length];
		for (int i = 0; i < this.answers.length; i++)
			this.answersIndexes[i] = this.answers[i].index;
		this.indexesForNames = indexesForNames;
		this.answerArrangementForDistribution = answerArrangementForDistribution;
		this.answerArrangementForMatching = answerArrangementForMatching;
		this.answerArrangementForArrangement = answerArrangementForArrangement;
		this.handleOnlyMaximal = handleOnlyMaximal;
		this.ignoredCharacters = ignoredCharacters;
		this.ignoreCase = ignoreCase;
		this.index = index;
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
			case Distribution:
				for (Integer value : answerArrangementForDistribution.values())
					if (handleOnlyMaximal)
						max = Math.max(max, value);
					else max += Math.max(0, value);
				break;
			case Matching:
				for (Integer value : answerArrangementForMatching.values())
					if (handleOnlyMaximal)
						max = Math.max(max, value);
					else max += Math.max(0, value);
				break;
			case Arrangement:
				for (Integer value : answerArrangementForArrangement.values())
					if (handleOnlyMaximal)
						max = Math.max(max, value);
					else max += Math.max(0, value);
				break;
		}
		maxAward = award + max;

	}

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
	public Question(Image[] images, Media[] videos, Media[] audios, String html, String text, Font font, int award, int minResult, QuestionType type,
			Answer[] answers, boolean handleOnlyMaximal,
			HashMap<HashMap<Entry<Integer, Boolean>, ArrayList<Integer>>, Integer> answerArrangementForDistribution, String[] indexesForNames,
			int[] indexesForNamesIndexes, String ignoredCharacters, boolean ignoreCase, int index)
	{
		this(images, videos, audios, html, text, font, award, minResult, type, answers, handleOnlyMaximal, answerArrangementForDistribution, null, null,
				indexesForNames, indexesForNamesIndexes, ignoredCharacters, ignoreCase, index);
		if (type != QuestionType.Distribution)
			throw new NullPointerException(msgSys.getMsg("questionTypeMustBe").replace("%1", "'Distribution'"));
	}

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
	public Question(Image[] images, Media[] videos, Media[] audios, String html, String text, Font font, int award, int minResult, QuestionType type,
			Answer[] answers, boolean handleOnlyMaximal, HashMap<HashMap<Integer, Integer>, Integer> answerArrangement, String[] indexesForNames,
			int[] indexesForNamesIndexes, String ignoredCharacters, boolean ignoreCase, boolean n, int index)
	{
		this(images, videos, audios, html, text, font, award, minResult, type, answers, handleOnlyMaximal, null, type == QuestionType.Matching
				? answerArrangement
				: null, type == QuestionType.Arrangement ? answerArrangement : null, indexesForNames, indexesForNamesIndexes, ignoredCharacters, ignoreCase,
				index);
		if (type != QuestionType.Arrangement && type != QuestionType.Matching)
			throw new NullPointerException(msgSys.getMsg("questionTypeMustBe").replace("%1", "'Arrangement' or 'Matching'"));
	}

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
	public Question(Image[] images, Media[] videos, Media[] audios, String html, String text, Font font, int award, int minResult, QuestionType type,
			Answer[] answers, String ignoredCharacters, boolean ignoreCase, int index)
	{
		this(images, videos, audios, html, text, font, award, minResult, type, answers, false, null, null, null, null, null, ignoredCharacters, ignoreCase,
				index);
		if (type != QuestionType.SelectSome && type != QuestionType.PickOne && type != QuestionType.EnterText)
			throw new NullPointerException(msgSys.getMsg("questionTypeMustBe").replace("%1", "'SelectSome', 'PickOne' or 'EnterText''"));
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
	public HashMap<HashMap<Entry<Integer, Boolean>, ArrayList<Integer>>, Integer> getAnswerArrangementForDistribution()
	{
		if (type != QuestionType.Distribution)
			throw new NullPointerException(msgSys.getMsg("questionTypeMustBe").replace("%1", "'Distribution'"));
		return answerArrangementForDistribution;
	}

	/**
	 * @return the answerArragement
	 */
	public HashMap<HashMap<Integer, Integer>, Integer> getAnswerArrangementForMatching()
	{
		if (type != QuestionType.Matching)
			throw new NullPointerException(msgSys.getMsg("questionTypeMustBe").replace("%1", "'Matching'"));
		return answerArrangementForMatching;
	}

	/**
	 * @return the answerArragement
	 */
	public HashMap<HashMap<Integer, Integer>, Integer> getAnswerArrangementForArrangement()
	{
		if (type != QuestionType.Arrangement)
			throw new NullPointerException(msgSys.getMsg("questionTypeMustBe").replace("%1", "'Arrangement'"));
		return answerArrangementForArrangement;
	}

	/**
	 * @return the indexesForNames
	 */
	public String[] getIndexesForNames()
	{
		return indexesForNames;
	}

	/**
	 * @return the indexesForNames
	 */
	public int[] getIndexesForNamesIndexes()
	{
		return indexesForNamesIndexes;
	}

	/**
	 * @return the handleOnlyMaximal
	 */
	public boolean isHandleOnlyMaximal()
	{
		return handleOnlyMaximal;
	}

	/**
	 * @return the index
	 */
	public int getIndex()
	{
		return index;
	}

	/**
	 * @return the html
	 */
	public String getHtml()
	{
		return html;
	}

	/**
	 * @return the images
	 */
	public Image[] getImages()
	{
		return images.clone();
	}

	/**
	 * @return the videos
	 */
	public Media[] getVideos()
	{
		return videos;
	}

	/**
	 * @return the audios
	 */
	public Media[] getAudios()
	{
		return audios;
	}

	/**
	 * Answer - the class with data about answer to question.
	 * 
	 * @author AlexanderDV/AlexandrDV
	 * @version 5.0.0a
	 */
	public static class Answer
	{
		private final String html;
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
		public Answer(String html, String text, Font font, int award, int index)
		{
			this.html = html;
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

		/**
		 * @return the html
		 */
		public String getHtml()
		{
			return html;
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
