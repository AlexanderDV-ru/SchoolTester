package ru.alexanderdv.schooltester.utilities.types.questionvariants;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class EnterTextAnswerVariant extends AnswerVariant
{
	private final String text;

	public EnterTextAnswerVariant(String text, int award, int index)
	{
		super(award, index);
		this.text = text;
	}

	public String getText()
	{
		return text;
	}
}