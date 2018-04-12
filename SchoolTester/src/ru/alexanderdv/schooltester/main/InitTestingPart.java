package ru.alexanderdv.schooltester.main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ru.alexanderdv.fxutilities.components.ButtonFX;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.5a
 */
public class InitTestingPart
{
	public static InitTestingPart instance;
	@FXML
	public ScrollPane scrollPane;
	@FXML
	public Pane questionSelectPanel, signPanel, answersPanel, optionButtonsPanel;
	@FXML
	public ButtonFX questionSign;
	@FXML
	public ButtonFX timer, skip, next, finish,back;
	@FXML
	public Pane qualityIndicator;
	@FXML
	public Label questionInfoTextfield;
	@FXML
	public Pane testingPane;
	@FXML
	public Pane questionsTablePane;
	@FXML
	public GridPane questionsTable;

	@FXML
	public void initialize()
	{
		instance = this;
	}
}
