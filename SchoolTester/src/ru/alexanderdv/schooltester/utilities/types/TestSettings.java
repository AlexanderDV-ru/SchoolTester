package ru.alexanderdv.schooltester.utilities.types;

import java.io.Serializable;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class TestSettings implements Serializable
{
	private static final long serialVersionUID = -2032370932661876626L;
	private final boolean indicateQualityOfLastAnswer;
	private final boolean indicateQualityOfAllAnswers;
	private final boolean showRightAnswer;
	private final boolean canGoToAllQuestions;
	private final boolean skipButtonOption;
	private final boolean pauseOption;
	private final boolean pauseOnUnfocus;
	private final boolean anticopy;
	private final boolean antiscreenshot;

	public TestSettings(boolean indicateQualityOfLastAnswer, boolean indicateQualityOfAllAnswers, boolean showRightAnswer, boolean canGoToAllQuestions,
			boolean skipButtonOption, boolean pauseOption, boolean pauseOnUnfocus, boolean anticopy, boolean antiscreenshot)
	{
		this.indicateQualityOfLastAnswer = indicateQualityOfLastAnswer;
		this.indicateQualityOfAllAnswers = indicateQualityOfAllAnswers;
		this.showRightAnswer = showRightAnswer;
		this.canGoToAllQuestions = canGoToAllQuestions;
		this.skipButtonOption = skipButtonOption;
		this.pauseOption = pauseOption;
		this.pauseOnUnfocus = pauseOnUnfocus;
		this.anticopy = anticopy;
		this.antiscreenshot = antiscreenshot;
	}

	/**
	 * @return the indicateQualityOfLastAnswer
	 */
	public boolean isIndicateQualityOfLastAnswer()
	{
		return indicateQualityOfLastAnswer;
	}

	/**
	 * @return the indicateQualityOfAllAnswers
	 */
	public boolean isIndicateQualityOfAllAnswers()
	{
		return indicateQualityOfAllAnswers;
	}

	/**
	 * @return the showRightAnswer
	 */
	public boolean isShowRightAnswer()
	{
		return showRightAnswer;
	}

	/**
	 * @return the canGoToAllQuestions
	 */
	public boolean isCanGoToAllQuestions()
	{
		return canGoToAllQuestions;
	}

	/**
	 * @return the skipButtonOption
	 */
	public boolean isSkipButtonOption()
	{
		return skipButtonOption;
	}

	/**
	 * @return the pauseOption
	 */
	public boolean isPauseOption()
	{
		return pauseOption;
	}

	/**
	 * @return the pauseOnUnfocus
	 */
	public boolean isPauseOnUnfocus()
	{
		return pauseOnUnfocus;
	}

	/**
	 * @return the anticopy
	 */
	public boolean isAnticopy()
	{
		return anticopy;
	}

	/**
	 * @return the antiscreenshot
	 */
	public boolean isAntiscreenshot()
	{
		return antiscreenshot;
	}

}
