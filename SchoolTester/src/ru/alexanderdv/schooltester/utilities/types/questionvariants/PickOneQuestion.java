package ru.alexanderdv.schooltester.utilities.types.questionvariants;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class PickOneQuestion extends Question<PickOneAnswerVariant>
{
	public PickOneQuestion(Image[] images, Media[] videos, Media[] audios, String html, int award, int minResult, PickOneAnswerVariant[] answers,
			int index)
	{
		super(images, videos, audios, html, award, minResult, answers, calcMaxAward(answers), index);
	}

	private static int calcMaxAward(AnswerVariant[] answers)
	{
		int max = 0;
				for (int i = 0; i < answers.length; i++)
					max = Math.max(max, answers[i].getAward());
		return max;
	}
}
