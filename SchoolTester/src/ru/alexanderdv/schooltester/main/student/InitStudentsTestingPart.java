package ru.alexanderdv.schooltester.main.student;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import ru.alexanderdv.fxutilities.components.ButtonFX;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.8.0a
 */
public class InitStudentsTestingPart
{
	public static InitStudentsTestingPart instance;
	@FXML
	public ScrollPane scrollPane;
	@FXML
	public Pane questionSelectPanel, signPanel, answersPanel, optionButtonsPanel;
	@FXML
	public ButtonFX questionSign;
	@FXML
	public ButtonFX timer, skip, next, finish;
	@FXML
	public Pane qualityIndicator;
	@FXML
	public Label questionInfoTextfield;

	@FXML
	public void initialize()
	{
		instance = this;
	}
}
