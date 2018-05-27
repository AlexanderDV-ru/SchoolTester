package ru.alexanderdv.schooltester.utilities.types.questionvariants;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public abstract class NEQuestion<T extends AnswerVariant> extends NewTypeQuestion<T>
{

	private final String[] indexesForNames;
	private final int[] indexesForNamesIndexes;
	
	
	public NEQuestion(Image[] images, Media[] videos, Media[] audios, String html, int award, int minResult, T[] answers, int maxAward, int index,
			boolean handleOnlyMaximal, String[] indexesForNames, int[] indexesForNamesIndexes)
	{
		super(images, videos, audios, html, award, minResult, answers, maxAward, index, handleOnlyMaximal);
		this.indexesForNames = indexesForNames;
		this.indexesForNamesIndexes = indexesForNamesIndexes;
	}
	/**
	 * @return the indexesForNames
	 */
	public String[] getIndexesForNames()
	{
		return indexesForNames;
	}
	/**
	 * @return the indexesForNamesIndexes
	 */
	public int[] getIndexesForNamesIndexes()
	{
		return indexesForNamesIndexes;
	}
	

}
