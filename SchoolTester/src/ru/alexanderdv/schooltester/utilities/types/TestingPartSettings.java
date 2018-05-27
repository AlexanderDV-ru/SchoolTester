package ru.alexanderdv.schooltester.utilities.types;

import java.io.Serializable;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class TestingPartSettings implements Serializable
{
	private static final long serialVersionUID = -7827956887882600250L;
	private final boolean fixedHeightOfQuestionSelectButton;
	private final boolean fillAllHeightOfAnswersPanel;
	private final boolean maximazeAnswerButtonHeight;

	public TestingPartSettings(boolean fixedHeightOfQuestionSelectButton, boolean fillAllHeightOfAnswersPanel, boolean maximazeAnswerButtonHeight)
	{
		this.fixedHeightOfQuestionSelectButton = fixedHeightOfQuestionSelectButton;
		this.fillAllHeightOfAnswersPanel = fillAllHeightOfAnswersPanel;
		this.maximazeAnswerButtonHeight = maximazeAnswerButtonHeight;
	}

	/**
	 * @return the fixedQSelectBtnHeight
	 */
	public boolean isFixedHeightOfQuestionSelectButton()
	{
		return fixedHeightOfQuestionSelectButton;
	}

	/**
	 * @return the fillAllHeight
	 */
	public boolean isFillAllHeightOfAnswersPanel()
	{
		return fillAllHeightOfAnswersPanel;
	}

	/**
	 * @return the maximazeHeight
	 */
	public boolean isMaximazeAnswerButtonHeight()
	{
		return maximazeAnswerButtonHeight;
	}
}
