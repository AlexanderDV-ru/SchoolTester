package ru.alexanderdv.schooltester.utilities.questionvars;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class ArrangementAnswer extends Answer<ArrangementAnswerVariant>
{
	private final int[] answerArrangementForArrangement;

	public ArrangementAnswer(int[] answerArrangementForArrangement)
	{
		this.answerArrangementForArrangement = answerArrangementForArrangement;
	}

	/**
	 * @return the answerArrangementForArrangement
	 */
	public int[] getAnswerArrangementForArrangement()
	{
		return answerArrangementForArrangement;
	}

}
