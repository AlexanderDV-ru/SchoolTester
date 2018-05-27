package ru.alexanderdv.schooltester.main.utilities.math;

import java.net.URL;

import ru.alexanderdv.schooltester.utilities.fx.FXWindow;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class UnitConverterPart extends FXWindow
{

	public UnitConverterPart(URL url, boolean inDevelope)
	{
		super(null, url, 1, inDevelope,false);
	}
	@Override
	protected void _resize(int w, int h)
	{
		
	}

	@Override
	public String name()
	{
		return "unitConverter";
	}

}
