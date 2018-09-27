package ru.alexanderdv.schooltester.utilities.questionvars;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public abstract class NewTypeQuestion<T extends AnswerVariant> extends Question<T>
{
	private final boolean handleOnlyMaximal;

	public NewTypeQuestion(Image[] images, Media[] videos, Media[] audios, String html, int award, int minResult, T[] answers, int maxAward, int index,
			boolean handleOnlyMaximal)
	{
		super(images, videos, audios, html, award, minResult, answers, maxAward, index);
		this.handleOnlyMaximal = handleOnlyMaximal;
	}

	/**
	 * @return the handleOnlyMaximal
	 */
	public boolean isHandleOnlyMaximal()
	{
		return handleOnlyMaximal;
	}

}
