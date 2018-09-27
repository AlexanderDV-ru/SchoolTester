package ru.alexanderdv.schooltester.utilities.questionvars;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class SelectMultipleAnswer extends Answer<SelectMultipleAnswerVariant>
{
	private final boolean[] selectedAnswers;

	public SelectMultipleAnswer(boolean[] selectedAnswers)
	{
		this.selectedAnswers = selectedAnswers;
	}

	/**
	 * @return the selectedAnswers
	 */
	public boolean[] getSelectedAnswers()
	{
		return selectedAnswers;
	}
}
