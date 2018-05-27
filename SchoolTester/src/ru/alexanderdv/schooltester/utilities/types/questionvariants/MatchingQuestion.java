package ru.alexanderdv.schooltester.utilities.types.questionvariants;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class MatchingQuestion extends NEQuestion<MatchingAnswerVariant>
{

	private final HashMap<HashMap<Integer, Integer>, Integer> answerArrangementForMatching;

	public MatchingQuestion(Image[] images, Media[] videos, Media[] audios, String html, int award, int minResult, MatchingAnswerVariant[] answers, int index,
			boolean handleOnlyMaximal, String[] indexesForNames, int[] indexesForNamesIndexes,
			HashMap<HashMap<Integer, Integer>, Integer> answerArrangementForMatching)
	{
		super(images, videos, audios, html, award, minResult, answers, calcMaxAward(answerArrangementForMatching, handleOnlyMaximal), index, handleOnlyMaximal,
				indexesForNames, indexesForNamesIndexes);
		this.answerArrangementForMatching = answerArrangementForMatching;
	}

	private static int calcMaxAward(HashMap<HashMap<Integer, Integer>, Integer> answerArrangementForMatching, boolean handleOnlyMaximal)
	{
		int max = 0;
		for (Integer value : answerArrangementForMatching.values())
			if (handleOnlyMaximal)
				max = Math.max(max, value);
			else max += Math.max(0, value);
		return max;
	}

	/**
	 * @return the answerArrangementForMatching
	 */
	public HashMap<HashMap<Integer, Integer>, Integer> getAnswerArrangementForMatching()
	{
		return answerArrangementForMatching;
	}
}
