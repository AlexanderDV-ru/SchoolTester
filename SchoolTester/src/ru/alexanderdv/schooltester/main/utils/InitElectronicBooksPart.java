package ru.alexanderdv.schooltester.main.utils;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.5.0a
 */
public class InitElectronicBooksPart
{
	public static InitElectronicBooksPart instance;

	@FXML
	public TabPane tabPane;

	@FXML
	public void initialize()
	{
		instance = this;
	}
}
