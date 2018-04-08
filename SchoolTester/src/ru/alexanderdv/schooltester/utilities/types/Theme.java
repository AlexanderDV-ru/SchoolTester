package ru.alexanderdv.schooltester.utilities.types;

import javafx.scene.paint.Color;

/**
 * Theme - the class with colors of color theme of test
 * 
 * @author AlexandrDV/AlexanderDV
 * @version 5.9.0a
 */
public class Theme
{
	private Color windowBackground;

	private QuestionTypeColors pickOne;
	private QuestionTypeColors selectSome;
	private QuestionTypeColors enterText;
	private QuestionTypeColors matching;
	private QuestionTypeColors arrangement;
	private QuestionTypeColors distribution;

	private Color questionSelectNormalBackground;
	private Color questionSelectNormalForeground;
	private Color questionSelectNormalFrame;
	private Color questionSelectSkippedBackground;
	private Color questionSelectSkippedForeground;
	private Color questionSelectSkippedFrame;

	private Color specialButtonsBackground;
	private Color specialButtonsForeground;
	private Color specialButtonsFrame;

	/**
	 * @return the windowBackground
	 */
	public Color getWindowBackground()
	{
		return windowBackground;
	}

	/**
	 * @param windowBackground
	 *            the windowBackground to set
	 */
	public void setWindowBackground(Color windowBackground)
	{
		this.windowBackground = windowBackground;
	}

	/**
	 * @return the pickOne
	 */
	public QuestionTypeColors getPickOne()
	{
		return pickOne;
	}

	/**
	 * @param pickOne
	 *            the pickOne to set
	 */
	public void setPickOne(QuestionTypeColors pickOne)
	{
		this.pickOne = pickOne;
	}

	/**
	 * @return the selectSome
	 */
	public QuestionTypeColors getSelectSome()
	{
		return selectSome;
	}

	/**
	 * @param selectSome
	 *            the selectSome to set
	 */
	public void setSelectSome(QuestionTypeColors selectSome)
	{
		this.selectSome = selectSome;
	}

	/**
	 * @return the enterText
	 */
	public QuestionTypeColors getEnterText()
	{
		return enterText;
	}

	/**
	 * @param enterText
	 *            the enterText to set
	 */
	public void setEnterText(QuestionTypeColors enterText)
	{
		this.enterText = enterText;
	}

	/**
	 * @return the matching
	 */
	public QuestionTypeColors getMatching()
	{
		return matching;
	}

	/**
	 * @param matching
	 *            the matching to set
	 */
	public void setMatching(QuestionTypeColors matching)
	{
		this.matching = matching;
	}

	/**
	 * @return the arrangement
	 */
	public QuestionTypeColors getArrangement()
	{
		return arrangement;
	}

	/**
	 * @param arrangement
	 *            the arrangement to set
	 */
	public void setArrangement(QuestionTypeColors arrangement)
	{
		this.arrangement = arrangement;
	}

	/**
	 * @return the distribution
	 */
	public QuestionTypeColors getDistribution()
	{
		return distribution;
	}

	/**
	 * @param distribution
	 *            the distribution to set
	 */
	public void setDistribution(QuestionTypeColors distribution)
	{
		this.distribution = distribution;
	}

	/**
	 * @return the questionSelectNormalBackground
	 */
	public Color getQuestionSelectNormalBackground()
	{
		return questionSelectNormalBackground;
	}

	/**
	 * @param questionSelectNormalBackground
	 *            the questionSelectNormalBackground to set
	 */
	public void setQuestionSelectNormalBackground(Color questionSelectNormalBackground)
	{
		this.questionSelectNormalBackground = questionSelectNormalBackground;
	}

	/**
	 * @return the questionSelectNormalForeground
	 */
	public Color getQuestionSelectNormalForeground()
	{
		return questionSelectNormalForeground;
	}

	/**
	 * @param questionSelectNormalForeground
	 *            the questionSelectNormalForeground to set
	 */
	public void setQuestionSelectNormalForeground(Color questionSelectNormalForeground)
	{
		this.questionSelectNormalForeground = questionSelectNormalForeground;
	}

	/**
	 * @return the questionSelectNormalFrame
	 */
	public Color getQuestionSelectNormalFrame()
	{
		return questionSelectNormalFrame;
	}

	/**
	 * @param questionSelectNormalFrame
	 *            the questionSelectNormalFrame to set
	 */
	public void setQuestionSelectNormalFrame(Color questionSelectNormalFrame)
	{
		this.questionSelectNormalFrame = questionSelectNormalFrame;
	}

	/**
	 * @return the questionSelectSkippedBackground
	 */
	public Color getQuestionSelectSkippedBackground()
	{
		return questionSelectSkippedBackground;
	}

	/**
	 * @param questionSelectSkippedBackground
	 *            the questionSelectSkippedBackground to set
	 */
	public void setQuestionSelectSkippedBackground(Color questionSelectSkippedBackground)
	{
		this.questionSelectSkippedBackground = questionSelectSkippedBackground;
	}

	/**
	 * @return the questionSelectSkippedForeground
	 */
	public Color getQuestionSelectSkippedForeground()
	{
		return questionSelectSkippedForeground;
	}

	/**
	 * @param questionSelectSkippedForeground
	 *            the questionSelectSkippedForeground to set
	 */
	public void setQuestionSelectSkippedForeground(Color questionSelectSkippedForeground)
	{
		this.questionSelectSkippedForeground = questionSelectSkippedForeground;
	}

	/**
	 * @return the questionSelectSkippedFrame
	 */
	public Color getQuestionSelectSkippedFrame()
	{
		return questionSelectSkippedFrame;
	}

	/**
	 * @param questionSelectSkippedFrame
	 *            the questionSelectSkippedFrame to set
	 */
	public void setQuestionSelectSkippedFrame(Color questionSelectSkippedFrame)
	{
		this.questionSelectSkippedFrame = questionSelectSkippedFrame;
	}

	/**
	 * @return the specialButtonsBackground
	 */
	public Color getSpecialButtonsBackground()
	{
		return specialButtonsBackground;
	}

	/**
	 * @param specialButtonsBackground
	 *            the specialButtonsBackground to set
	 */
	public void setSpecialButtonsBackground(Color specialButtonsBackground)
	{
		this.specialButtonsBackground = specialButtonsBackground;
	}

	/**
	 * @return the specialButtonsForeground
	 */
	public Color getSpecialButtonsForeground()
	{
		return specialButtonsForeground;
	}

	/**
	 * @param specialButtonsForeground
	 *            the specialButtonsForeground to set
	 */
	public void setSpecialButtonsForeground(Color specialButtonsForeground)
	{
		this.specialButtonsForeground = specialButtonsForeground;
	}

	/**
	 * @return the specialButtonsFrame
	 */
	public Color getSpecialButtonsFrame()
	{
		return specialButtonsFrame;
	}

	/**
	 * @param specialButtonsFrame
	 *            the specialButtonsFrame to set
	 */
	public void setSpecialButtonsFrame(Color specialButtonsFrame)
	{
		this.specialButtonsFrame = specialButtonsFrame;
	}

	public Theme()
	{
		pickOne = new QuestionTypeColors();
		selectSome = new QuestionTypeColors();
		enterText = new QuestionTypeColors();
		matching = new QuestionTypeColors();
		arrangement = new QuestionTypeColors();
		distribution = new QuestionTypeColors();
	}

	public static class QuestionTypeColors
	{
		private Color questionBackground;
		private Color questionForeground;
		private Color questionFrame;
		private Color answersBackground;
		private Color answersForeground;
		private Color answersFrame;

		/**
		 * @return the questionBackground
		 */
		public Color getQuestionBackground()
		{
			return questionBackground;
		}

		/**
		 * @param questionBackground
		 *            the questionBackground to set
		 */
		public void setQuestionBackground(Color questionBackground)
		{
			this.questionBackground = questionBackground;
		}

		/**
		 * @return the questionForeground
		 */
		public Color getQuestionForeground()
		{
			return questionForeground;
		}

		/**
		 * @param questionForeground
		 *            the questionForeground to set
		 */
		public void setQuestionForeground(Color questionForeground)
		{
			this.questionForeground = questionForeground;
		}

		/**
		 * @return the questionFrame
		 */
		public Color getQuestionFrame()
		{
			return questionFrame;
		}

		/**
		 * @param questionFrame
		 *            the questionFrame to set
		 */
		public void setQuestionFrame(Color questionFrame)
		{
			this.questionFrame = questionFrame;
		}

		/**
		 * @return the answersBackground
		 */
		public Color getAnswersBackground()
		{
			return answersBackground;
		}

		/**
		 * @param answersBackground
		 *            the answersBackground to set
		 */
		public void setAnswersBackground(Color answersBackground)
		{
			this.answersBackground = answersBackground;
		}

		/**
		 * @return the answersForeground
		 */
		public Color getAnswersForeground()
		{
			return answersForeground;
		}

		/**
		 * @param answersForeground
		 *            the answersForeground to set
		 */
		public void setAnswersForeground(Color answersForeground)
		{
			this.answersForeground = answersForeground;
		}

		/**
		 * @return the answersFrame
		 */
		public Color getAnswersFrame()
		{
			return answersFrame;
		}

		/**
		 * @param answersFrame
		 *            the answersFrame to set
		 */
		public void setAnswersFrame(Color answersFrame)
		{
			this.answersFrame = answersFrame;
		}
	}
}