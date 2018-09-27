package ru.alexanderdv.schooltester.utilities.questionvars;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import ru.alexanderdv.simpleutilities.Entry;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class DistributionQuestion extends NEQuestion<DistributionAnswerVariant>
{
	private final HashMap<HashMap<Entry<Integer, Boolean>, ArrayList<Integer>>, Integer> answerArrangementForDistribution;

	public DistributionQuestion(Image[] images, Media[] videos, Media[] audios, String html, int award, int minResult, DistributionAnswerVariant[] answers,
			int index, boolean handleOnlyMaximal, String[] indexesForNames, int[] indexesForNamesIndexes,
			HashMap<HashMap<Entry<Integer, Boolean>, ArrayList<Integer>>, Integer> answerArrangementForDistribution)
	{
		super(images, videos, audios, html, award, minResult, answers, calcMaxAward(answerArrangementForDistribution, handleOnlyMaximal), index,
				handleOnlyMaximal, indexesForNames, indexesForNamesIndexes);
		this.answerArrangementForDistribution = answerArrangementForDistribution;
	}

	private static int calcMaxAward(HashMap<HashMap<Entry<Integer, Boolean>, ArrayList<Integer>>, Integer> answerArrangementForDistribution,
			boolean handleOnlyMaximal)
	{
		int max = 0;
		for (Integer value : answerArrangementForDistribution.values())
			if (handleOnlyMaximal)
				max = Math.max(max, value);
			else max += Math.max(0, value);
		return max;
	}

	/**
	 * @return the answerArrangementForDistribution
	 */
	public HashMap<HashMap<Entry<Integer, Boolean>, ArrayList<Integer>>, Integer> getAnswerArrangementForDistribution()
	{
		return answerArrangementForDistribution;
	}
}
