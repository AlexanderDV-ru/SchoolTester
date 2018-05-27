package ru.alexanderdv.schooltester.utilities.types.questionvariants;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public abstract class AnswerVariant
{
	private final int award;
	private int index;

	/**
	 * 
	 * @param award
	 * @param text
	 * @param font
	 */
	public AnswerVariant(int award, int index)
	{
		this.award = award;
		this.index = index;
	}

	/**
	 * @return the award
	 */
	public int getAward()
	{
		return award;
	}

	/**
	 * @return the index
	 */
	public int getIndex()
	{
		return index;
	}
}