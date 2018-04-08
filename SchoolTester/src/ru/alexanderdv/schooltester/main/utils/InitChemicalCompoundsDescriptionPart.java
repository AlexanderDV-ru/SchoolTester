package ru.alexanderdv.schooltester.main.utils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.0a
 */
public class InitChemicalCompoundsDescriptionPart
{
	public static InitChemicalCompoundsDescriptionPart instance;
	public TextField formulaTextfield;
	public TextField nameTextfield;
	public Button createDescriptionButton;
	public TextArea descriptionTextarea;
	public RadioButton namesInLatinRadio;
	public RadioButton namesInSelectedLanguageRadio;
	public Label signsAfterCommaLabel;
	public ComboBox<String> signsAfterCommaCombobox;

	@FXML
	public void initialize()
	{
		instance = this;
		descriptionTextarea.setEditable(false);
		signsAfterCommaCombobox.getItems().addAll("0", "1", "2", "3", "4", "All");
		signsAfterCommaCombobox.getSelectionModel().select(2);
	}

}
