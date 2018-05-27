package ru.alexanderdv.schooltester.utilities.types.questionvariants;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class EnterTextQuestion extends Question<EnterTextAnswerVariant>
{
	private final String ignoredCharacters;
	private final boolean ignoreCase;

	public EnterTextQuestion(Image[] images, Media[] videos, Media[] audios, String html, int award, int minResult, EnterTextAnswerVariant[] answers, 
			int index, String ignoredCharacters, boolean ignoreCase)
	{
		super(images, videos, audios, html, award, minResult, answers, calcMaxAward(answers), index);
		this.ignoredCharacters = ignoredCharacters;
		this.ignoreCase = ignoreCase;
	}

	private static int calcMaxAward(AnswerVariant[] answers)
	{
		int max = 0;
		for (int i = 0; i < answers.length; i++)
			max = Math.max(max, answers[i].getAward());
		return max;
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
}
