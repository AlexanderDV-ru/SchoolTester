package ru.alexanderdv.schooltester.utilities.types.questionvariants;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public abstract class Question<AnswerType extends AnswerVariant>
{
	private final Image[] images;
	private final Media[] videos;
	private final Media[] audios;
	private final String html;
	private final int award;
	private final int minResult;
	private final AnswerType[] answers;
	private final int[] answersIndexes;
	private final int maxAward;
	private final int index;

	public Question(Image[] images, Media[] videos, Media[] audios, String html, int award, int minResult, AnswerType[] answers, int maxAward, int index)
	{
		this.images = images;
		this.videos = videos;
		this.audios = audios;
		this.html = html;
		this.award = award;
		this.minResult = minResult;
		this.answers = answers;
		this.answersIndexes = new int[this.answers.length];
		for (int i = 0; i < this.answers.length; i++)
			this.answersIndexes[i] = this.answers[i].getIndex();
		this.maxAward = maxAward;
		this.index = index;
	}

	/**
	 * @return the images
	 */
	public Image[] getImages()
	{
		return images;
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
	 * @return the html
	 */
	public String getHtml()
	{
		return html;
	}

	/**
	 * @return the award
	 */
	public int getAward()
	{
		return award;
	}

	/**
	 * @return the minResult
	 */
	public int getMinResult()
	{
		return minResult;
	}

	/**
	 * @return the answers
	 */
	public AnswerType[] getAnswers()
	{
		return answers;
	}

	/**
	 * @return the answersIndexes
	 */
	public int[] getAnswersIndexes()
	{
		return answersIndexes;
	}

	/**
	 * @return the maxAward
	 */
	public int getMaxAward()
	{
		return maxAward;
	}

	/**
	 * @return the index
	 */
	public int getIndex()
	{
		return index;
	}
}
