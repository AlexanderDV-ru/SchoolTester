package ru.alexanderdv.schooltester.main.utils;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.5.0a
 */
public class InitFunctionsWorkPart
{
	public static InitFunctionsWorkPart instance;

	@FXML
	public TextField pointsToBuildCountTextfield;
	@FXML
	public TextField changeDetalisationTextfield;
	@FXML
	public TextField masshtabTextfield;
	@FXML
	public TextField stepTextfield;
	@FXML
	public CheckBox onlyPoints;
	@FXML
	public Pane graphic;
	@FXML
	public TextField formula;

	@FXML
	public void initialize()
	{
		instance = this;
	}
}
