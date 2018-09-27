package ru.alexanderdv.schooltester.utilities.questionvars;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class DistributionResult extends Result<DistributionAnswerVariant>
{
	private final HashMap<String, ArrayList<String>> distribution;

	public DistributionResult(int minResult, int result, int maxResult, HashMap<String, ArrayList<String>> distribution)
	{
		super(minResult, result, maxResult);
		this.distribution = distribution;
	}

	/**
	 * @return the distribution
	 */
	public HashMap<String, ArrayList<String>> getDistribution()
	{
		return distribution;
	}

}
