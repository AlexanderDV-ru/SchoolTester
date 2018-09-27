package ru.alexanderdv.schooltester.main.utilities.othersubjects;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.5a
 */
@Deprecated
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
