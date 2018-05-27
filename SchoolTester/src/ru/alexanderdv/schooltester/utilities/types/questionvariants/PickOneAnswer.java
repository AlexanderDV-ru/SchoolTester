package ru.alexanderdv.schooltester.utilities.types.questionvariants;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class PickOneAnswer extends Answer<PickOneAnswerVariant>
{
	private final int selectedAnswerNumber;

	public PickOneAnswer(int selectedAnswerNumber)
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
