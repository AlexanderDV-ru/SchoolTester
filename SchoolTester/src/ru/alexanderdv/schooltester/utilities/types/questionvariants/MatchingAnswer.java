package ru.alexanderdv.schooltester.utilities.types.questionvariants;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class MatchingAnswer extends Answer<MatchingAnswerVariant>
{
	private final int[] answerArrangementForMatching;

	public MatchingAnswer(int[] answerArrangementForMatching)
	{
		this.answerArrangementForMatching = answerArrangementForMatching;
	}

	/**
	 * @return the answerArrangementForArrangement
	 */
	public int[] getAnswerArrangementForMatching()
	{
		return answerArrangementForMatching;
	}
}
