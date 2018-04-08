package ru.alexanderdv.schooltester.main.utils;

import java.net.URL;

import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.0a
 */
public class CalculatorPart extends ProtectedFXWindow
{
	public static CalculatorPart instance;
	public CalculatorPart(URL url)
	{
		super(null, url, 1, 1);
		instance=this;
	}

}
