package ru.alexanderdv.schooltester.main.utilities.othersubjects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.5a
 */
@Deprecated
public class InitCrossWordGeneratorPart
{
	public static InitCrossWordGeneratorPart instance;

	@FXML
	public Pane crossword;
	@FXML
	public TextArea words;
	@FXML
	public Button copyInExcelFormat;
	@FXML
	public CheckBox letters, digits, splitToHorizontalAndVertical;
	@FXML
	public ColorPicker backgroundOfEmptyCell, borderOfEmptyCell, backgroundOfCrosswordCell, borderOfCrosswordCell;
	@FXML
	public Label backgroundOfEmptyCellLabel, borderOfEmptyCellLabel, backgroundOfCrosswordCellLabel, borderOfCrosswordCellLabel;

	@FXML
	public void initialize()
	{
		instance = this;
		backgroundOfEmptyCell.setValue(new javafx.scene.paint.Color(0.95, 0.95, 0.95, 1));
		borderOfEmptyCell.setValue(new javafx.scene.paint.Color(0, 0, 0, 1));
		backgroundOfCrosswordCell.setValue(new javafx.scene.paint.Color(1, 1, 1, 1));
		borderOfCrosswordCell.setValue(new javafx.scene.paint.Color(0, 0, 0, 1));
	}
}
