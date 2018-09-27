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

	private final boolean fillAllHeightOfQuestionsPanel;
	private final boolean maximazeHeightOfQuestionsPanelElements;
	private final boolean fillAllHeightOfAnswersPanel;
	private final boolean maximazeHeightOfAnswersPanelElements;

	public TestingPartSettings(boolean fillAllHeightOfQuestionsPanel, boolean maximazeHeightOfQuestionsPanelElements,
			boolean fillAllHeightOfAnswersPanel, boolean maximazeHeightOfAnswersPanelElements)
	{
		this.fillAllHeightOfQuestionsPanel = fillAllHeightOfQuestionsPanel;
		this.maximazeHeightOfQuestionsPanelElements = maximazeHeightOfQuestionsPanelElements;
		this.fillAllHeightOfAnswersPanel = fillAllHeightOfAnswersPanel;
		this.maximazeHeightOfAnswersPanelElements = maximazeHeightOfAnswersPanelElements;
	}

	/**
	 * @return the fillAllHeightOfQuestionsPanel
	 */
	public boolean isFillAllHeightOfQuestionsPanel()
	{
		return fillAllHeightOfQuestionsPanel;
	}

	/**
	 * @return the maximazeHeightOfQuestionsPanelElements
	 */
	public boolean isMaximazeHeightOfQuestionsPanelElements()
	{
		return maximazeHeightOfQuestionsPanelElements;
	}

	/**
	 * @return the fillAllHeightOfAnswersPanel
	 */
	public boolean isFillAllHeightOfAnswersPanel()
	{
		return fillAllHeightOfAnswersPanel;
	}

	/**
	 * @return the maximazeHeightOfAnswersPanelElements
	 */
	public boolean isMaximazeHeightOfAnswersPanelElements()
	{
		return maximazeHeightOfAnswersPanelElements;
	}
}
