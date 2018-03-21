package ru.alexanderdv.schooltester.utilities;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.8.0a
 */
public enum Subject
{
	Math(1, 11),
	RussianAndWriting(1, 9),
	English(-1, 11),
	Archaeology(-5, 11),
	Art(1, -7),
	Biology(5, 11),
	Chemistry(-5, 11),
	InfoTechAndComputers(-1, 11),
	Drama(0, 0),
	Economics(-5, 11),
	French(-1, 11),
	Geography(-5, 11),
	Geology(-5, 11),
	German(-1, 11),
	History(-5, 11),
	HomeEconomics(-5, 11),
	LiteratureAndReading(1, 11),
	Music(1, 11),
	PhysicalEducation(1, 11),
	Physics(-5, 11),
	Psychology(-5, 0),
	Science(1, 4),
	ForeignLanguage(-1, 11),
	SocialStudies(-5, 11);
	private final int startClass, endClass;

	/**
	 * 
	 * @param startClass
	 * @param endClass
	 */
	private Subject(int startClass, int endClass)
	{
		this.startClass = startClass;
		this.endClass = endClass;
	}

	/**
	 * @return the startClass
	 */
	public int getStartClass()
	{
		return startClass;
	}

	/**
	 * @return the endClass
	 */
	public int getEndClass()
	{
		return endClass;
	}
}