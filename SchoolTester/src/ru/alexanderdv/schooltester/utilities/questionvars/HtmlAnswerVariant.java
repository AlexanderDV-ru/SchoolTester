package ru.alexanderdv.schooltester.utilities.questionvars;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public abstract class HtmlAnswerVariant extends AnswerVariant
{
	private final String html;

	public HtmlAnswerVariant(String html, int award, int index)
	{
		super(award, index);
		this.html = html;
	}

	/**
	 * @return the html
	 */
	public String getHtml()
	{
		return html;
	}

}
