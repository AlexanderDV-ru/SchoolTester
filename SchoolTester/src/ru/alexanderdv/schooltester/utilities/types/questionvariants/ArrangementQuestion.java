package ru.alexanderdv.schooltester.utilities.types.questionvariants;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class ArrangementQuestion extends NewTypeQuestion<ArrangementAnswerVariant>
{
	private final HashMap<HashMap<Integer, Integer>, Integer> answerArrangementForArrangement;

	public ArrangementQuestion(Image[] images, Media[] videos, Media[] audios, String html, int award, int minResult, ArrangementAnswerVariant[] answers,
			int index, boolean handleOnlyMaximal, 
			HashMap<HashMap<Integer, Integer>, Integer> answerArrangementForArrangement)
	{
		super(images, videos, audios, html, award, minResult, answers, calcMaxAward(answerArrangementForArrangement, handleOnlyMaximal), index,
				handleOnlyMaximal);
		this.answerArrangementForArrangement = answerArrangementForArrangement;
	}

	private static int calcMaxAward(HashMap<HashMap<Integer, Integer>, Integer> answerArrangementForArrangement, boolean handleOnlyMaximal)
	{
		int max = 0;
		for (Integer value : answerArrangementForArrangement.values())
			if (handleOnlyMaximal)
				max = Math.max(max, value);
			else max += Math.max(0, value);
		return max;
	}

	/**
	 * @return the answerArrangementForArrangement
	 */
	public HashMap<HashMap<Integer, Integer>, Integer> getAnswerArrangementForArrangement()
	{
		return answerArrangementForArrangement;
	}
}
