package ru.alexanderdv.schooltester.utilities.questionvars;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class EnterTextAnswer extends Answer<EnterTextAnswerVariant>
{
	private final String answerText;

	public EnterTextAnswer(String answerText)
	{
		this.answerText = answerText==null?"":answerText;
	}

	/**
	 * @return the answerText
	 */
	public String getAnswerText()
	{
		return answerText;
	}
}
