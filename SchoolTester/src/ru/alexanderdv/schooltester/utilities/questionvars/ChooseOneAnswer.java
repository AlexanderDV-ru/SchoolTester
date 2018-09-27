package ru.alexanderdv.schooltester.utilities.questionvars;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class ChooseOneAnswer extends Answer<ChooseOneAnswerVariant>
{
	private final int selectedAnswerNumber;

	public ChooseOneAnswer(int selectedAnswerNumber)
	{
		this.selectedAnswerNumber = selectedAnswerNumber;
	}

	/**
	 * @return the selectedAnswerNumber
	 */
	public int getSelectedAnswerNumber()
	{
		return selectedAnswerNumber;
	}
}
