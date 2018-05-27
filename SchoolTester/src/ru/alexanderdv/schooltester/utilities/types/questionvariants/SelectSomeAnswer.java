package ru.alexanderdv.schooltester.utilities.types.questionvariants;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class SelectSomeAnswer extends Answer<SelectSomeAnswerVariant>
{
	private final boolean[] selectedAnswers;

	public SelectSomeAnswer(boolean[] selectedAnswers)
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
