package ru.alexanderdv.schooltester.main.teacher;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import ru.alexanderdv.schooltester.utilities.ComboboxWithAdd;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.8.0a
 */
public class InitTeachersTestsControlPart
{
	public static InitTeachersTestsControlPart instance;
	@FXML
	public GridPane gridPane;
	@FXML
	public Tab testingTab, statisticsTab;
	@FXML
	public Tab testingSettingsTab, lookSettingsTab;
	@FXML
	public Label testNameLabel1, classNumberLabel1, classLetterLabel1, surnameLabel1, nameLabel1, secondNameLabel1;
	@FXML
	public Label testNameLabel2, classNumberLabel2, classLetterLabel2, surnameLabel2, nameLabel2, secondNameLabel2;
	@FXML
	public CheckBox classNumberCheckbox, classLetterCheckbox, surnameCheckbox, nameCheckbox, secondNameCheckbox;
	@FXML
	public ComboBox<String> testNameCombobox1, classNumberCombobox1;
	@FXML
	public ComboBox<String> testNameCombobox2, classNumberCombobox2;
	@FXML
	public ComboboxWithAdd classLetterCombobox, surnameCombobox, nameCombobox, secondNameCombobox;
	@FXML
	public TextField classLetterTextfield, surnameTextfield, nameTextfield, secondNameTextfield;
	@FXML
	public Button startTestButton, getStatistics, saveTestingSettingsButton;
	@FXML
	public RadioButton inPercentsRadiobutton, inFractionsRadiobutton;
	@FXML
	public RadioButton defaultRadiobutton, indicateLastAnswerQualityRadiobutton, goToAllQuestionsRadiobutton;
	@FXML
	public CheckBox pauseCheckbox, pauseOnUnfocusCheckbox, indicateAllAnswersQualityCheckbox, showRightAnswerCheckbox, skipCheckbox, anticopyCheckbox,
			antiscreenshotCheckbox;
	@FXML
	public CheckBox fixedQSelectBtnHeightCheckbox, fillAllHeightOfAnswersPanelCheckbox, maximazeAnswerButtonHeightCheckbox;
	@FXML
	public TextField spaceBetweenAnswerButtonsField;
	@FXML
	public ProgressBar spaceBetweenAnswerButtonsBar;
	@FXML
	public Label spaceBetweenAnswerButtonsLabel;
	@FXML
	public Button saveLookSettingsButton;
	@FXML
	public GridPane statsTable, fieldEnabler;
	@FXML
	public TabPane testsList;
	@FXML
	public Label smallest, average, biggest, max, all;
	@FXML
	public Label score, rightAnswers, perfectAnswers, time;
	@FXML
	public Label cell11, cell21, cell31, cell41;
	@FXML
	public Label cell12, cell22, cell32, cell42;
	@FXML
	public Label cell13, cell23, cell33, cell43;
	@FXML
	public Label cell14, cell24, cell34, cell44;
	@FXML
	public Label cell15, cell25, cell35, cell45;

	@FXML
	public void initialize()
	{
		instance = this;
		classLetterCombobox = new ComboboxWithAdd();
		classLetterCombobox.setPrefSize(170, 25);

		gridPane.add(classLetterCombobox, 1, 2);
		surnameCombobox = new ComboboxWithAdd();
		surnameCombobox.setPrefSize(170, 25);

		gridPane.add(surnameCombobox, 1, 3);
		nameCombobox = new ComboboxWithAdd();
		nameCombobox.setPrefSize(170, 25);
		gridPane.add(nameCombobox, 1, 4);
		secondNameCombobox = new ComboboxWithAdd();
		secondNameCombobox.setPrefSize(170, 25);
		gridPane.add(secondNameCombobox, 1, 5);

		for (int i = 1; i <= 11; i++)
			classNumberCombobox1.getItems().add(i + "");
		classNumberCombobox1.getSelectionModel().select(0);
		classNumberCombobox1.setPromptText(classNumberCombobox1.getSelectionModel().getSelectedItem());
		for (int i = 1; i <= 11; i++)
			classNumberCombobox2.getItems().add(i + "");
		classNumberCombobox2.getSelectionModel().select(0);
		classNumberCombobox2.setPromptText(classNumberCombobox2.getSelectionModel().getSelectedItem());
	}
}
