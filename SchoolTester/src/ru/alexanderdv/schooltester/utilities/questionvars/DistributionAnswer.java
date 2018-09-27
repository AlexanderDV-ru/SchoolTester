package ru.alexanderdv.schooltester.utilities.questionvars;

import java.util.ArrayList;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class DistributionAnswer extends Answer<DistributionAnswerVariant>
{
	private final ArrayList<Integer>[] answerArrangementForDistribution;

	public DistributionAnswer(ArrayList<Integer>[] answerArrangementForDistribution)
	{
		this.answerArrangementForDistribution = answerArrangementForDistribution;
	}

	public ArrayList<Integer>[] getAnswerArrangementForDistribution()
	{
		return answerArrangementForDistribution;
	}
}
