package ru.alexanderdv.schooltester.utilities.questionvars;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class MatchingResult extends Result<MatchingAnswerVariant>
{
	private final HashMap<String, String> matching;

	public MatchingResult(int minResult, int result, int maxResult, HashMap<String, String> matching)
	{
		super(minResult, result, maxResult);
		this.matching = matching;
	}

	/**
	 * @return the matching
	 */
	public HashMap<String, String> getMatching()
	{
		return matching;
	}

}
