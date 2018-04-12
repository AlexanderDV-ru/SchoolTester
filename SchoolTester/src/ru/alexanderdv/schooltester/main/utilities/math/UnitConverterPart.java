package ru.alexanderdv.schooltester.main.utilities.math;

import java.net.URL;

import ru.alexanderdv.schooltester.utilities.fx.FXWindow;

/**
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.5a
 */
public class UnitConverterPart extends FXWindow
{

	public UnitConverterPart(URL url, boolean inDevelope)
	{
		super(null, url, 1, inDevelope);
	}

	@Override
	public String name()
	{
		return "unitConverter";
	}

}
