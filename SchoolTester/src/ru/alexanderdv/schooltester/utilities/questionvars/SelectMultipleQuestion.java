package ru.alexanderdv.schooltester.utilities.questionvars;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class SelectMultipleQuestion extends Question<SelectMultipleAnswerVariant>
{
	public SelectMultipleQuestion(Image[] images, Media[] videos, Media[] audios, String html, int award, int minResult, SelectMultipleAnswerVariant[] answers,
		 int index)
	{
		super(images, videos, audios, html, award, minResult, answers, calcMaxAward(answers), index);
	}

	private static int calcMaxAward(AnswerVariant[] answers)
	{
		int max = 0;
		for (int i = 0; i < answers.length; i++)
			max += Math.max(0, answers[i].getAward());
		return max;
	}
}
