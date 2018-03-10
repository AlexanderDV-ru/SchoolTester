package ru.alexanderdv.schooltester.main.teacher;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.alexanderdv.schooltester.main.AccountsPart;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.main.student.StudentsTestingPartFX;
import ru.alexanderdv.schooltester.utilities.ComboboxWithAdd;
import ru.alexanderdv.schooltester.utilities.Config;
import ru.alexanderdv.schooltester.utilities.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.Logger.ExitCodes;
import ru.alexanderdv.schooltester.utilities.MathAndTextUtils;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.ProtectedFXWindow;
import ru.alexanderdv.schooltester.utilities.Question;
import ru.alexanderdv.schooltester.utilities.Question.Answer;
import ru.alexanderdv.schooltester.utilities.Question.QuestionType;
import ru.alexanderdv.schooltester.utilities.Statistics;
import ru.alexanderdv.schooltester.utilities.SystemUtils;
import ru.alexanderdv.schooltester.utilities.Theme;

/**
 * 
 * 
 * @author AlexandrDV/AlexanderDV
 * @version 5.0.0a
 */
public class TeachersTestsControlPart extends ProtectedFXWindow
{
	public static TeachersTestsControlPart instance;

	private static final MessageSystem msgSys = Main.msgSys;
	private static final Random random = new Random();
	public static final String fileTreeRoot = MessageSystem.getMsg("fileTree", MessageSystem.getMessages().keySet().contains(System.getProperty("user.language")
			.toLowerCase() + "_" + System.getProperty("user.country").toLowerCase()) ? System.getProperty("user.language").toLowerCase() + "_" + System
					.getProperty("user.country").toLowerCase() : "en_uk"), country = country(), region = !System.getProperty("user.timezone").split("/")[0]
							.equals("") ? System.getProperty("user.timezone").split("/")[0] : undef(), city = System.getProperty("user.timezone").split(
									"/").length > 1 ? System.getProperty("user.timezone").split("/")[1] : undef(), school = undef();

	public static final String path = fileTreeRoot + "/" + country + "/" + region + "/" + city + "/" + school;

	public TeachersTestsControlPart(String secondaryTitle, URL url)
	{
		super(secondaryTitle, url, 1, 1);
		instance = this;
		createActionHandlers();
		changeTestingSettings();
		changeLookSettings();
	}

	private static String undef()
	{
		return MessageSystem.getMsg("undefined", MessageSystem.getMessages().containsKey(System.getProperty("user.language")) ? System.getProperty(
				"user.language") : msgSys.getLanguage());
	}

	public static String getFileTree()
	{
		String s = "";
		s += rec("\n", new File(fileTreeRoot));
		return s;
	}

	private static String rec(String m, File f0)
	{
		String s = "";
		if (f0.exists())
			for (File f : f0.listFiles())
				if (f.isDirectory())
				{
					s += m + f.getName();
					s += rec(m + "\t", f);
				}
		return s;
	}

	private static String country()
	{
		String lang = MessageSystem.getMessages().containsKey(System.getProperty("user.language")) ? System.getProperty("user.language") : msgSys.getLanguage();
		String s = MessageSystem.getMsg(System.getProperty("user.country"), lang);
		return s != null ? s : MessageSystem.getMsg("undefined", lang);
	}

	public void changeTestingSettings()
	{
		Config cfg = new Config(new File("testingSettings.cfg"));
		if (!cfg.hasValue("pause") || !cfg.hasValue("pauseOnUnfocus") || !cfg.hasValue("testProps") || !cfg.hasValue("indicateAnswersQuality") || !cfg.hasValue(
				"showRightAnswer") || !cfg.hasValue("skip") || !cfg.hasValue("anticntrlc") || !cfg.hasValue("antiscreenshot") || !cfg.hasValue("classNumber")
				|| !cfg.hasValue("classLetter") || !cfg.hasValue("selectedSurname") || !cfg.hasValue("selectedName") || !cfg.hasValue("selectedSecondName"))
			InitTeachersTestsControlPart.instance.saveTestingSettingsButton.getOnAction().handle(new ActionEvent());
		cfg.getText(true);
		InitTeachersTestsControlPart.instance.defaultRadiobutton.setSelected(cfg.getString("testProps", null, false).equals("none"));
		InitTeachersTestsControlPart.instance.indicateLastAnswerQualityRadiobutton.setSelected(cfg.getString("testProps", null, false).equals("iaq"));
		InitTeachersTestsControlPart.instance.indicateAllAnswersQualityCheckbox.setSelected(cfg.getBoolean("indicateAnswersQuality", null, false));
		InitTeachersTestsControlPart.instance.indicateAllAnswersQualityCheckbox.setDisable(
				!InitTeachersTestsControlPart.instance.indicateLastAnswerQualityRadiobutton.isSelected());
		InitTeachersTestsControlPart.instance.showRightAnswerCheckbox.setSelected(cfg.getBoolean("showRightAnswer", null, false));
		InitTeachersTestsControlPart.instance.showRightAnswerCheckbox.setDisable(!InitTeachersTestsControlPart.instance.indicateLastAnswerQualityRadiobutton
				.isSelected());
		InitTeachersTestsControlPart.instance.goToAllQuestionsRadiobutton.setSelected(cfg.getString("testProps", null, false).equals("gtaq"));
		InitTeachersTestsControlPart.instance.skipCheckbox.setSelected(cfg.getBoolean("skip", null, false));
		InitTeachersTestsControlPart.instance.pauseCheckbox.setSelected(cfg.getBoolean("pause", null, false));
		InitTeachersTestsControlPart.instance.pauseOnUnfocusCheckbox.setSelected(cfg.getBoolean("pauseOnUnfocus", null, false));
		InitTeachersTestsControlPart.instance.pauseOnUnfocusCheckbox.setDisable(!InitTeachersTestsControlPart.instance.pauseCheckbox.isSelected());
		InitTeachersTestsControlPart.instance.anticopyCheckbox.setSelected(cfg.getBoolean("anticntrlc", null, false));
		InitTeachersTestsControlPart.instance.antiscreenshotCheckbox.setSelected(cfg.getBoolean("antiscreenshot", null, false));

		InitTeachersTestsControlPart.instance.testNameCombobox1.setPromptText(InitTeachersTestsControlPart.instance.testNameCombobox1.getSelectionModel()
				.getSelectedItem());
		InitTeachersTestsControlPart.instance.classNumberCombobox1.getSelectionModel().select(cfg.getString("classNumber", null, false));
		InitTeachersTestsControlPart.instance.classNumberCombobox1.getOnAction().handle(new ActionEvent());
		InitTeachersTestsControlPart.instance.classLetterCombobox.getSelectionModel().select(cfg.getString("classLetter", null, false));
		InitTeachersTestsControlPart.instance.classLetterCombobox.getOnAction().handle(new ActionEvent());
		InitTeachersTestsControlPart.instance.nameCombobox.getSelectionModel().select(cfg.getString("selectedName", null, false));
		InitTeachersTestsControlPart.instance.surnameCombobox.getSelectionModel().select(cfg.getString("selectedSurname", null, false));
		InitTeachersTestsControlPart.instance.secondNameCombobox.getSelectionModel().select(cfg.getString("selectedSecondName", null, false));
		InitTeachersTestsControlPart.instance.saveTestingSettingsButton.setDisable(true);
	}

	public void changeLookSettings()
	{
		Config cfg = new Config(new File("lookSettings.cfg"));
		if (!cfg.hasValue("fixedQSelectBtnHeight") || !cfg.hasValue("fillAllHeightOfAnswersPanel") || !cfg.hasValue("maximazeAnswerButtonHeight") || !cfg
				.hasValue("spaceBetweenAnswerButtons"))
			InitTeachersTestsControlPart.instance.saveLookSettingsButton.getOnAction().handle(new ActionEvent());
		cfg.getText(true);
		InitTeachersTestsControlPart.instance.fixedQSelectBtnHeightCheckbox.setSelected(cfg.getBoolean("fixedQSelectBtnHeight", null, false));
		InitTeachersTestsControlPart.instance.fillAllHeightOfAnswersPanelCheckbox.setSelected(cfg.getBoolean("fillAllHeightOfAnswersPanel", null, false));
		InitTeachersTestsControlPart.instance.maximazeAnswerButtonHeightCheckbox.setSelected(cfg.getBoolean("maximazeAnswerButtonHeight", null, false));
		InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsField.setText(cfg.getInteger("spaceBetweenAnswerButtons", null, false) + "");
		InitTeachersTestsControlPart.instance.saveLookSettingsButton.setDisable(true);
	}

	public void createActionHandlers()
	{
		CheckBox classNumberCheckbox = InitTeachersTestsControlPart.instance.classNumberCheckbox,
				classLetterCheckbox = InitTeachersTestsControlPart.instance.classLetterCheckbox,
				surnameCheckbox = InitTeachersTestsControlPart.instance.surnameCheckbox, nameCheckbox = InitTeachersTestsControlPart.instance.nameCheckbox,
				secondNameCheckbox = InitTeachersTestsControlPart.instance.secondNameCheckbox;
		ComboBox<String> testNameCombobox1 = InitTeachersTestsControlPart.instance.testNameCombobox1,
				classNumberCombobox1 = InitTeachersTestsControlPart.instance.classNumberCombobox1;
		ComboBox<String> testNameCombobox2 = InitTeachersTestsControlPart.instance.testNameCombobox2,
				classNumberCombobox2 = InitTeachersTestsControlPart.instance.classNumberCombobox2;
		ComboboxWithAdd classLetterCombobox = InitTeachersTestsControlPart.instance.classLetterCombobox,
				surnameCombobox = InitTeachersTestsControlPart.instance.surnameCombobox, nameCombobox = InitTeachersTestsControlPart.instance.nameCombobox,
				secondNameCombobox = InitTeachersTestsControlPart.instance.secondNameCombobox;
		TextField classLetterTextfield = InitTeachersTestsControlPart.instance.classLetterTextfield,
				surnameTextfield = InitTeachersTestsControlPart.instance.surnameTextfield, nameTextfield = InitTeachersTestsControlPart.instance.nameTextfield,
				secondNameTextfield = InitTeachersTestsControlPart.instance.secondNameTextfield;
		Button startTestButton = InitTeachersTestsControlPart.instance.startTestButton, getStatistics = InitTeachersTestsControlPart.instance.getStatistics,
				saveTestingSettingsButton = InitTeachersTestsControlPart.instance.saveTestingSettingsButton;
		RadioButton inPercentsRadiobutton = InitTeachersTestsControlPart.instance.inPercentsRadiobutton;// , inFractionsRadiobutton =
																										// InitTeachersTestsControlPart.instance.inFractionsRadiobutton;
		RadioButton defaultRadiobutton = InitTeachersTestsControlPart.instance.defaultRadiobutton,
				indicateLastAnswerQualityRadiobutton = InitTeachersTestsControlPart.instance.indicateLastAnswerQualityRadiobutton,
				goToAllQuestions = InitTeachersTestsControlPart.instance.goToAllQuestionsRadiobutton;
		CheckBox pause = InitTeachersTestsControlPart.instance.pauseCheckbox, pauseOnUnfocus = InitTeachersTestsControlPart.instance.pauseOnUnfocusCheckbox,
				indicateAllAnswersQuality = InitTeachersTestsControlPart.instance.indicateAllAnswersQualityCheckbox,
				showRightAnswer = InitTeachersTestsControlPart.instance.showRightAnswerCheckbox, skip = InitTeachersTestsControlPart.instance.skipCheckbox,
				anticopy = InitTeachersTestsControlPart.instance.anticopyCheckbox,
				antiscreenshot = InitTeachersTestsControlPart.instance.antiscreenshotCheckbox,
				fixedQSelectBtnHeight = InitTeachersTestsControlPart.instance.fixedQSelectBtnHeightCheckbox;
		// GridPane statsTable = InitTeachersTestsControlPart.instance.statsTable, fieldEnabler = InitTeachersTestsControlPart.instance.fieldEnabler;
		TabPane testsList = InitTeachersTestsControlPart.instance.testsList;
		// Label smallest = InitTeachersTestsControlPart.instance.smallest, average = InitTeachersTestsControlPart.instance.average, biggest =
		// InitTeachersTestsControlPart.instance.biggest, max = InitTeachersTestsControlPart.instance.max,
		// all = InitTeachersTestsControlPart.instance.all;
		// Label score = InitTeachersTestsControlPart.instance.score, rightAnswers = InitTeachersTestsControlPart.instance.rightAnswers, perfectAnswers =
		// InitTeachersTestsControlPart.instance.perfectAnswers, time =
		// InitTeachersTestsControlPart.instance.time;
		Label cell11 = InitTeachersTestsControlPart.instance.cell11, cell21 = InitTeachersTestsControlPart.instance.cell21,
				cell31 = InitTeachersTestsControlPart.instance.cell31, cell41 = InitTeachersTestsControlPart.instance.cell41;
		Label cell12 = InitTeachersTestsControlPart.instance.cell12, cell22 = InitTeachersTestsControlPart.instance.cell22,
				cell32 = InitTeachersTestsControlPart.instance.cell32, cell42 = InitTeachersTestsControlPart.instance.cell42;
		Label cell13 = InitTeachersTestsControlPart.instance.cell13, cell23 = InitTeachersTestsControlPart.instance.cell23,
				cell33 = InitTeachersTestsControlPart.instance.cell33, cell43 = InitTeachersTestsControlPart.instance.cell43;
		Label cell14 = InitTeachersTestsControlPart.instance.cell14, cell24 = InitTeachersTestsControlPart.instance.cell24,
				cell34 = InitTeachersTestsControlPart.instance.cell34, cell44 = InitTeachersTestsControlPart.instance.cell44;
		Label cell15 = InitTeachersTestsControlPart.instance.cell15, cell25 = InitTeachersTestsControlPart.instance.cell25,
				cell35 = InitTeachersTestsControlPart.instance.cell35, cell45 = InitTeachersTestsControlPart.instance.cell45;
		// MenuBar menubar = InitTeachersTestsControlPart.instance.menubar;

		classLetterCombobox.setOnAddOrRemoveElement((e) ->
		{
			File f0 = new File(path);
			SystemUtils.createFile(f0, true, true, true);
			File f = new File(f0.getAbsolutePath() + "/" + classNumberCombobox1.getSelectionModel().getSelectedItem());
			SystemUtils.createFile(f, true, true, true);
			File f2 = new File(f.getAbsolutePath() + "/" + ((String) e.getSource()).replace("\n", ""));
			if (((String) e.getSource()).startsWith("\n"))
				SystemUtils.removeFile(f2);
			else
			{
				SystemUtils.createFile(f2, true, true, true);
				File f3 = new File(f.getAbsolutePath() + "/" + (String) e.getSource() + "/surname");
				SystemUtils.createFile(f3, true, true, true);
				File f4 = new File(f.getAbsolutePath() + "/" + (String) e.getSource() + "/name");
				SystemUtils.createFile(f4, true, true, true);
				File f5 = new File(f.getAbsolutePath() + "/" + (String) e.getSource() + "/secondname");
				SystemUtils.createFile(f5, true, true, true);
			}
			saveTestingSettingsButton.setDisable(false);
		});
		classLetterCombobox.setOnAction((ev) ->
		{
			File f0 = new File(path);
			SystemUtils.createFile(f0, true, true, true);
			File f = new File(f0.getAbsolutePath() + "/" + classNumberCombobox1.getSelectionModel().getSelectedItem());
			SystemUtils.createFile(f, true, true, true);
			File f3 = new File(f.getAbsolutePath() + "/" + classLetterCombobox.getSelectionModel().getSelectedItem());
			SystemUtils.createFile(f3, true, true, true);
			surnameCombobox.getItems().clear();
			if (new File(f3.getAbsolutePath() + "/surname").listFiles() != null)
				for (File f2 : new File(f3.getAbsolutePath() + "/surname").listFiles())
					surnameCombobox.getItems().add(f2.getName());
			nameCombobox.getItems().clear();
			if (new File(f3.getAbsolutePath() + "/name").listFiles() != null)
				for (File f2 : new File(f3.getAbsolutePath() + "/name").listFiles())
					nameCombobox.getItems().add(f2.getName());
			secondNameCombobox.getItems().clear();
			if (new File(f3.getAbsolutePath() + "/secondname").listFiles() != null)
				for (File f2 : new File(f3.getAbsolutePath() + "/secondname").listFiles())
					secondNameCombobox.getItems().add(f2.getName());
			saveTestingSettingsButton.setDisable(false);
		});
		surnameCombobox.setOnAddOrRemoveElement((e) ->
		{
			File f5 = new File(path + "/" + classNumberCombobox1.getSelectionModel().getSelectedItem() + "/" + classLetterCombobox.getSelectionModel()
					.getSelectedItem() + "/surname" + "/" + ((String) e.getSource()).replace("\n", ""));
			if (((String) e.getSource()).startsWith("\n"))
				SystemUtils.removeFile(f5);
			else SystemUtils.createFile(f5, true, true, true);
			saveTestingSettingsButton.setDisable(false);
		});
		nameCombobox.setOnAddOrRemoveElement((e) ->
		{
			File f5 = new File(path + "/" + classNumberCombobox1.getSelectionModel().getSelectedItem() + "/" + classLetterCombobox.getSelectionModel()
					.getSelectedItem() + "/name" + "/" + ((String) e.getSource()).replace("\n", ""));
			if (((String) e.getSource()).startsWith("\n"))
				SystemUtils.removeFile(f5);
			else SystemUtils.createFile(f5, true, true, true);
			saveTestingSettingsButton.setDisable(false);
		});
		secondNameCombobox.setOnAddOrRemoveElement((e) ->
		{
			File f5 = new File(path + "/" + classNumberCombobox1.getSelectionModel().getSelectedItem() + "/" + classLetterCombobox.getSelectionModel()
					.getSelectedItem() + "/secondname" + "/" + ((String) e.getSource()).replace("\n", ""));
			if (((String) e.getSource()).startsWith("\n"))
				SystemUtils.removeFile(f5);
			else SystemUtils.createFile(f5, true, true, true);
			saveTestingSettingsButton.setDisable(false);
		});
		classNumberCombobox1.setOnAction((ev) ->
		{
			File f0 = new File(path);
			SystemUtils.createFile(f0, true, true, true);
			File f = new File(f0.getAbsolutePath() + "/" + classNumberCombobox1.getSelectionModel().getSelectedItem());
			SystemUtils.createFile(f, true, true, true);
			classLetterCombobox.getItems().clear();
			if (f.listFiles() != null)
				for (File f2 : f.listFiles())
					classLetterCombobox.getItems().add(f2.getName());
			saveTestingSettingsButton.setDisable(false);

		});
		classNumberCombobox2.setOnAction((ev) ->
		{
			File f0 = new File(path);
			SystemUtils.createFile(f0, true, true, true);
			File f = new File(f0.getAbsolutePath() + "/" + classNumberCombobox2.getSelectionModel().getSelectedItem());
			SystemUtils.createFile(f, true, true, true);
			classLetterCombobox.getItems().clear();
			if (f.listFiles() != null)
				for (File f2 : f.listFiles())
					classLetterCombobox.getItems().add(f2.getName());
			saveTestingSettingsButton.setDisable(false);

		});
		EventHandler<ActionEvent> action = (e) -> saveTestingSettingsButton.setDisable(false);
		EventHandler<ActionEvent> action2 = (e) -> InitTeachersTestsControlPart.instance.saveLookSettingsButton.setDisable(false);
		EventHandler<ActionEvent> testPropsAction = (e) ->
		{
			action.handle(e);
			indicateAllAnswersQuality.setDisable(!indicateLastAnswerQualityRadiobutton.isSelected());
			showRightAnswer.setDisable(!indicateLastAnswerQualityRadiobutton.isSelected());
		};
		defaultRadiobutton.setOnAction(testPropsAction);
		indicateLastAnswerQualityRadiobutton.setOnAction(testPropsAction);
		goToAllQuestions.setOnAction(testPropsAction);
		indicateAllAnswersQuality.setOnAction(action);
		showRightAnswer.setOnAction(action);
		skip.setOnAction(action);
		pause.setOnAction(e ->
		{
			action.handle(e);
			pauseOnUnfocus.setDisable(!pause.isSelected());
		});
		pauseOnUnfocus.setOnAction(action);
		anticopy.setOnAction(action);
		antiscreenshot.setOnAction(action);

		InitTeachersTestsControlPart.instance.fixedQSelectBtnHeightCheckbox.setOnAction(action2);
		InitTeachersTestsControlPart.instance.fillAllHeightOfAnswersPanelCheckbox.setOnAction(action2);
		InitTeachersTestsControlPart.instance.maximazeAnswerButtonHeightCheckbox.setOnAction(action2);
		InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsField.setOnAction(e ->
		{
			InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsField.setText("" + Math.max(Math.min(MathAndTextUtils.parseI(
					InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsField.getText()), 20), 1));
			InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsBar.setProgress(MathAndTextUtils.parseI(
					InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsField.getText())
					/ InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsBar.getPrefWidth());
			action2.handle(null);
		});
		InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsField.focusedProperty().addListener(
				e -> InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsField.getOnAction().handle(null));

		saveTestingSettingsButton.setOnAction(event ->
		{
			String text = "";
			text += "pause: " + pause.isSelected();
			text += "\npauseOnUnfocus: " + pauseOnUnfocus.isSelected();
			text += "\ntestProps: " + "\"" + (goToAllQuestions.isSelected() ? "gtaq" : (indicateLastAnswerQualityRadiobutton.isSelected() ? "iaq" : "none"))
					+ "\"";
			text += "\nindicateAnswersQuality: " + indicateAllAnswersQuality.isSelected();
			text += "\nshowRightAnswer: " + showRightAnswer.isSelected();
			text += "\nskip: " + skip.isSelected();
			text += "\nanticntrlc: " + anticopy.isSelected();
			text += "\nantiscreenshot: " + antiscreenshot.isSelected();
			text += "\nfixedQSelectBtnHeight: " + fixedQSelectBtnHeight.isSelected();

			text += "\nclassNumber: \"" + classNumberCombobox1.getSelectionModel().getSelectedItem() + "\"";
			text += "\nclassLetter: \"" + classLetterCombobox.getSelectionModel().getSelectedItem() + "\"";
			text += "\nselectedSurname: \"" + surnameCombobox.getSelectionModel().getSelectedItem() + "\"";
			text += "\nselectedName: \"" + nameCombobox.getSelectionModel().getSelectedItem() + "\"";
			text += "\nselectedSecondName: \"" + secondNameCombobox.getSelectionModel().getSelectedItem() + "\"";
			SystemUtils.writeFile(new File("testingSettings.cfg"), text, "Cp1251");
			saveTestingSettingsButton.setDisable(true);
		});
		InitTeachersTestsControlPart.instance.saveLookSettingsButton.setOnAction(event ->
		{
			String text = "";
			text += "fixedQSelectBtnHeight: " + InitTeachersTestsControlPart.instance.fixedQSelectBtnHeightCheckbox.isSelected();
			text += "\nfillAllHeightOfAnswersPanel: " + InitTeachersTestsControlPart.instance.fillAllHeightOfAnswersPanelCheckbox.isSelected();
			text += "\nmaximazeAnswerButtonHeight: " + InitTeachersTestsControlPart.instance.maximazeAnswerButtonHeightCheckbox.isSelected();
			text += "\nspaceBetweenAnswerButtons: " + MathAndTextUtils.parseI(InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsField.getText());
			SystemUtils.writeFile(new File("lookSettings.cfg"), text, "Cp1251");
			InitTeachersTestsControlPart.instance.saveLookSettingsButton.setDisable(true);
		});
		InitTeachersTestsControlPart.instance.statisticsTab.setOnSelectionChanged(e ->
		{
			testNameCombobox2.getItems().clear();
			for (Config cfg : Statistics.getStatistics(null, null, null, null, null, null))
				if (!testNameCombobox2.getItems().contains(cfg.getString(MessageSystem.getMsg("testName", cfg.getString("syntaxLanguage", null, false)), null,
						false)))
					testNameCombobox2.getItems().add(cfg.getString(MessageSystem.getMsg("testName", cfg.getString("syntaxLanguage", null, false)), null,
							false));
		});
		getStatistics.setOnAction(event ->
		{
			if (testNameCombobox2.getSelectionModel().isEmpty())
			{
				FXDialogsGenerator.showFXDialog(stage, stage, msgSys.getMsg("testNotSelected"), JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, Main
						.isFxWindowFrame(), true);
				return;
			}
			testsList.getTabs().clear();
			float minResult = Float.MAX_VALUE, maxResult = 0, result = 0, averageResult = 0, perfectResult = 0;
			float questions = 0;
			float minRightAnswers = Float.MAX_VALUE, maxRightAnswers = 0, rightAnswers = 0, averageRightAnswers = 0;
			float minPerfectAnswers = Float.MAX_VALUE, maxPerfectAnswers = 0, perfectAnswers = 0, averagePerfectAnswers = 0;
			float minTime = Float.MAX_VALUE, maxTime = 0, time = 0, averageTime = 0, perfectTime = 0;
			int testsCount = 0;
			for (Config cfg : Statistics.getStatistics(testNameCombobox2.getSelectionModel().getSelectedItem(), !classNumberCheckbox.isSelected() ? null
					: classNumberCombobox2.getSelectionModel().getSelectedItem(), !classLetterCheckbox.isSelected() ? null : classLetterTextfield.getText(),
					!surnameCheckbox.isSelected() ? null : surnameTextfield.getText(), !nameCheckbox.isSelected() ? null : nameTextfield.getText(),
					!secondNameCheckbox.isSelected() ? null : secondNameTextfield.getText()))
			{
				testsList.getTabs().add(new Tab(cfg.getFile().getName().replace("Result From ", "").replace(".log", ""), new TextArea(cfg.getText(false))));

				String language = cfg.getString("syntaxLanguage", null, false);
				result += cfg.getInteger(MessageSystem.getMsg("result", language), null, false);
				minResult = Math.min(minResult, cfg.getInteger(MessageSystem.getMsg("result", language), null, false));
				maxResult = Math.max(maxResult, cfg.getInteger(MessageSystem.getMsg("result", language), null, false));
				perfectResult += cfg.getInteger(MessageSystem.getMsg("maxResult", language), null, false);

				perfectAnswers += cfg.getInteger(MessageSystem.getMsg("perfectAnswersAmount", language), null, false);
				minPerfectAnswers = Math.min(minPerfectAnswers, cfg.getInteger(MessageSystem.getMsg("perfectAnswersAmount", language), null, false));
				maxPerfectAnswers = Math.max(maxPerfectAnswers, cfg.getInteger(MessageSystem.getMsg("perfectAnswersAmount", language), null, false));

				rightAnswers += cfg.getInteger(MessageSystem.getMsg("rightAnswersAmount", language), null, false);
				minRightAnswers = Math.min(minRightAnswers, cfg.getInteger(MessageSystem.getMsg("rightAnswersAmount", language), null, false));
				maxRightAnswers = Math.max(maxRightAnswers, cfg.getInteger(MessageSystem.getMsg("rightAnswersAmount", language), null, false));

				questions += cfg.getInteger(MessageSystem.getMsg("questionsAmount", language), null, false);

				time += cfg.getTime(MessageSystem.getMsg("time", language), null, false);
				minTime = Math.min(minTime, cfg.getTime(MessageSystem.getMsg("time", language), null, false));
				maxTime = Math.max(maxTime, cfg.getTime(MessageSystem.getMsg("time", language), null, false));
				perfectTime += cfg.getTime(MessageSystem.getMsg("timeToTest", language), null, false);

				testsCount++;
			}
			averageResult = result / testsCount;
			perfectResult /= testsCount;

			averageRightAnswers = rightAnswers / testsCount;
			averagePerfectAnswers = perfectAnswers / testsCount;
			questions /= testsCount;

			averageTime = time / testsCount;
			perfectTime /= testsCount;

			if (inPercentsRadiobutton.isSelected())
			{
				cell11.setText(numberToString(minResult / perfectResult * 100f, 2) + "%");
				cell12.setText(numberToString(averageResult / perfectResult * 100f, 2) + "%");
				cell13.setText(numberToString(maxResult / perfectResult * 100f, 2) + "%");
			}
			else
			{
				cell11.setText(numberToString(minResult, 2) + "");
				cell12.setText(numberToString(averageResult, 2) + "");
				cell13.setText(numberToString(maxResult, 2) + "");
			}
			cell14.setText(numberToString(perfectResult, 2) + "");
			cell15.setText(numberToString(result, 2) + "");

			if (inPercentsRadiobutton.isSelected())
			{
				cell21.setText(numberToString(minRightAnswers / questions * 100f, 2) + "%");
				cell22.setText(numberToString(averageRightAnswers / questions * 100f, 2) + "%");
				cell23.setText(numberToString(maxRightAnswers / questions * 100f, 2) + "%");
			}
			else
			{
				cell21.setText(numberToString(minRightAnswers, 2) + "");
				cell22.setText(numberToString(averageRightAnswers, 2) + "");
				cell23.setText(numberToString(maxRightAnswers, 2) + "");
			}
			cell24.setText(numberToString(questions, 2) + "");
			cell25.setText(numberToString(rightAnswers, 2) + "");

			if (inPercentsRadiobutton.isSelected())
			{
				cell31.setText(numberToString(minPerfectAnswers / questions * 100f, 2) + "%");
				cell32.setText(numberToString(averagePerfectAnswers / questions * 100f, 2) + "%");
				cell33.setText(numberToString(maxPerfectAnswers / questions * 100f, 2) + "%");
			}
			else
			{
				cell31.setText(numberToString(minPerfectAnswers, 2) + "");
				cell32.setText(numberToString(averagePerfectAnswers, 2) + "");
				cell33.setText(numberToString(maxPerfectAnswers, 2) + "");
			}
			cell34.setText(numberToString(questions, 2) + "");
			cell35.setText(numberToString(perfectAnswers, 2) + "");

			if (inPercentsRadiobutton.isSelected())
			{
				cell41.setText(numberToString(minTime / perfectTime * 100f, 2) + "%");
				cell42.setText(numberToString(averageTime / perfectTime * 100f, 2) + "%");
				cell43.setText(numberToString(maxTime / perfectTime * 100f, 2) + "%");
			}
			else
			{
				cell41.setText(numberToString(minTime, 2) + "");
				cell42.setText(numberToString(averageTime, 2) + "");
				cell43.setText(numberToString(maxTime, 2) + "");
			}
			cell44.setText(numberToString(perfectTime, 2) + "");
			cell45.setText(numberToString(time, 2) + "");
			if (testsList.getTabs().size() == 0)
				FXDialogsGenerator.showFXDialog(stage, stage, msgSys.getMsg("testWithFiltersNotExist"), JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION,
						Main.isFxWindowFrame(), true);
		});

		File[] files;
		File testsDir = new File("Tests");
		if (!testsDir.isDirectory())
		{
			if (!testsDir.exists())
				testsDir.mkdir();
			else
			{
				testsDir.delete();
				testsDir.mkdir();
			}
			FXDialogsGenerator.showFXDialog(stage, stage, "In directory Tests not exists any files!", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION,
					Main.isFxWindowFrame(), true);
			Main.exit(ExitCodes.UserCloseProgram);
		}
		files = testsDir.listFiles();
		Test[] tests = new Test[files.length];
		for (int i = 0; i < files.length; i++)
			if (files[i].isDirectory())
			{
				File[] inFiles = files[i].listFiles();
				for (int j = 0; j < inFiles.length; j++)
					if (inFiles[j].isFile() && inFiles[j].getName().endsWith(".test"))
					{
						tests[i] = Test.valueOf(new Config(inFiles[j]).getText());
						testNameCombobox1.getItems().add(tests[i].getName());
						testNameCombobox2.getItems().add(tests[i].getName());
					}
			}
		testNameCombobox1.getSelectionModel().select(0);
		testNameCombobox2.getSelectionModel().select(0);

		if (testNameCombobox1.getItems().size() == 0)
		{
			FXDialogsGenerator.showFXDialog(stage, stage, "In directory Tests not exists any tests!", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION,
					Main.isFxWindowFrame(), true);
			Main.exit(ExitCodes.UserCloseProgram);
		}
		boolean hide = true;
		startTestButton.setOnAction(e ->
		{
			if (classNumberCombobox1.getSelectionModel().getSelectedItem() == null || classLetterCombobox.getSelectionModel().getSelectedItem() == null
					|| classNumberCombobox1.getSelectionModel().getSelectedItem().equals("") || classLetterCombobox.getSelectionModel().getSelectedItem()
							.equals(""))
				FXDialogsGenerator.showFXDialog(stage, stage, "Class can't be empty!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, Main
						.isFxWindowFrame(), true);
			else if (surnameCombobox.getSelectionModel().getSelectedItem() == null || "".equals(surnameCombobox.getSelectionModel().getSelectedItem()))
				FXDialogsGenerator.showFXDialog(stage, stage, "Surname can't be empty!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, Main
						.isFxWindowFrame(), true);
			else if (nameCombobox.getSelectionModel().getSelectedItem() == null || "".equals(nameCombobox.getSelectionModel().getSelectedItem()))
				FXDialogsGenerator.showFXDialog(stage, stage, "Name can't be empty!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, Main
						.isFxWindowFrame(), true);
			else if (secondNameCombobox.getSelectionModel().getSelectedItem() == null || "".equals(secondNameCombobox.getSelectionModel().getSelectedItem()))
				FXDialogsGenerator.showFXDialog(stage, stage, "Second field can't be empty!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, Main
						.isFxWindowFrame(), true);
			else
			{
				Test test = null;
				String selectedTestName = testNameCombobox1.getSelectionModel().getSelectedItem();
				for (Test t : tests)
					if (t.getName().equals(selectedTestName))
					{
						test = t;
						break;
					}

				if (test == null)
				{
					FXDialogsGenerator.showFXDialog(stage, stage, "Selected Test '" + selectedTestName + "' is invalid!", JOptionPane.ERROR_MESSAGE,
							JOptionPane.DEFAULT_OPTION, Main.isFxWindowFrame(), true);
					Main.exit(ExitCodes.TestNotExists);
				}
				StudentsTestingPartFX studentsTestingPartFX = new StudentsTestingPartFX(null, getClass().getResource("/StudentsTestingPart.fxml"),
						new Rectangle((int) stage.getX(), (int) stage.getY(), (int) stage.getWidth(), (int) stage.getHeight()), test, classNumberCombobox1
								.getSelectionModel().getSelectedItem(), classLetterCombobox.getSelectionModel().getSelectedItem(), surnameCombobox
										.getSelectionModel().getSelectedItem(), nameCombobox.getSelectionModel().getSelectedItem(), secondNameCombobox
												.getSelectionModel().getSelectedItem(), indicateLastAnswerQualityRadiobutton.isSelected(),
						indicateAllAnswersQuality.isSelected() && !indicateAllAnswersQuality.isDisable(), showRightAnswer.isSelected() && !showRightAnswer
								.isDisable(), goToAllQuestions.isSelected() && !goToAllQuestions.isDisable(), skip.isSelected(), pause.isSelected(),
						pauseOnUnfocus.isSelected(), anticopy.isSelected(), antiscreenshot.isSelected(), fixedQSelectBtnHeight.isSelected(), hide);
				try
				{
					studentsTestingPartFX.open(new Rectangle((int) stage.getX(), (int) stage.getY(), (int) stage.getWidth(), (int) stage.getHeight()),
							AccountsPart.account.get(), Main.instance.socket);
				}
				catch (Exception e1)
				{
					FXDialogsGenerator.showFXDialog(stage, stage, "To work sign in!", 0, 0, Main.isFxWindowFrame(), true);
				}
				Main.instance.hideAll();
			}
		});
	}

	public String numberToString(double number, int size)
	{
		double number2 = (double) ((int) (number * Math.pow(10, size))) / Math.pow(10, size);
		if ((int) number2 == number2)
			return (int) number2 + "";
		return number2 + "";
	}

	public void updateLabelsInPart()
	{
		super.updateLabelsInPart();
		InitTeachersTestsControlPart.instance.testingTab.setText(msgSys.getMsg("testingTab"));
		InitTeachersTestsControlPart.instance.statisticsTab.setText(msgSys.getMsg("statisticsTab"));

		InitTeachersTestsControlPart.instance.testNameLabel1.setText(msgSys.getMsg("testName"));
		InitTeachersTestsControlPart.instance.classNumberLabel1.setText(msgSys.getMsg("classNumber"));
		InitTeachersTestsControlPart.instance.classLetterLabel1.setText(msgSys.getMsg("classLetter"));
		InitTeachersTestsControlPart.instance.surnameLabel1.setText(msgSys.getMsg("surname"));
		InitTeachersTestsControlPart.instance.nameLabel1.setText(msgSys.getMsg("name"));
		InitTeachersTestsControlPart.instance.secondNameLabel1.setText(msgSys.getMsg("secondName"));
		InitTeachersTestsControlPart.instance.testNameLabel2.setText(msgSys.getMsg("testName"));
		InitTeachersTestsControlPart.instance.classNumberLabel2.setText(msgSys.getMsg("classNumber"));
		InitTeachersTestsControlPart.instance.classLetterLabel2.setText(msgSys.getMsg("classLetter"));
		InitTeachersTestsControlPart.instance.surnameLabel2.setText(msgSys.getMsg("surname"));
		InitTeachersTestsControlPart.instance.nameLabel2.setText(msgSys.getMsg("name"));
		InitTeachersTestsControlPart.instance.secondNameLabel2.setText(msgSys.getMsg("secondName"));

		InitTeachersTestsControlPart.instance.classLetterTextfield.setPromptText(msgSys.getMsg("classLetter"));
		InitTeachersTestsControlPart.instance.surnameTextfield.setPromptText(msgSys.getMsg("surname"));
		InitTeachersTestsControlPart.instance.nameTextfield.setPromptText(msgSys.getMsg("name"));
		InitTeachersTestsControlPart.instance.secondNameTextfield.setPromptText(msgSys.getMsg("secondName"));

		InitTeachersTestsControlPart.instance.indicateLastAnswerQualityRadiobutton.setText(msgSys.getMsg("indicateAnswerQuality"));
		InitTeachersTestsControlPart.instance.startTestButton.setText(msgSys.getMsg("start"));

		InitTeachersTestsControlPart.instance.getStatistics.setText(msgSys.getMsg("getStats"));
		InitTeachersTestsControlPart.instance.inPercentsRadiobutton.setText(msgSys.getMsg("inPercents"));
		InitTeachersTestsControlPart.instance.inFractionsRadiobutton.setText(msgSys.getMsg("inFractions"));

		InitTeachersTestsControlPart.instance.smallest.setText(msgSys.getMsg("smallest"));
		InitTeachersTestsControlPart.instance.average.setText(msgSys.getMsg("average"));
		InitTeachersTestsControlPart.instance.biggest.setText(msgSys.getMsg("biggest"));
		InitTeachersTestsControlPart.instance.max.setText(msgSys.getMsg("max"));
		InitTeachersTestsControlPart.instance.all.setText(msgSys.getMsg("all"));
		InitTeachersTestsControlPart.instance.score.setText(msgSys.getMsg("score"));
		InitTeachersTestsControlPart.instance.rightAnswers.setText(msgSys.getMsg("rightAnswers"));
		InitTeachersTestsControlPart.instance.perfectAnswers.setText(msgSys.getMsg("perfectAnswers"));
		InitTeachersTestsControlPart.instance.time.setText(msgSys.getMsg("time"));

		InitTeachersTestsControlPart.instance.testingSettingsTab.setText(msgSys.getMsg("testingSettings"));
		{
			InitTeachersTestsControlPart.instance.defaultRadiobutton.setText(msgSys.getMsg("none"));
			InitTeachersTestsControlPart.instance.indicateLastAnswerQualityRadiobutton.setText(msgSys.getMsg("indicateAnswerQuality"));
			{
				InitTeachersTestsControlPart.instance.indicateAllAnswersQualityCheckbox.setText(msgSys.getMsg("indicateAnswersQuality"));
				InitTeachersTestsControlPart.instance.showRightAnswerCheckbox.setText(msgSys.getMsg("showRightAnswer"));
			}
			InitTeachersTestsControlPart.instance.goToAllQuestionsRadiobutton.setText(msgSys.getMsg("goToAllQuestions"));
			InitTeachersTestsControlPart.instance.skipCheckbox.setText(msgSys.getMsg("skipBtn"));
			InitTeachersTestsControlPart.instance.pauseCheckbox.setText(msgSys.getMsg("pause"));
			{
				InitTeachersTestsControlPart.instance.pauseOnUnfocusCheckbox.setText(msgSys.getMsg("pauseOnUnfocus"));
			}
			InitTeachersTestsControlPart.instance.anticopyCheckbox.setText(msgSys.getMsg("anticopy"));
			InitTeachersTestsControlPart.instance.antiscreenshotCheckbox.setText(msgSys.getMsg("antiscreenshot"));
			InitTeachersTestsControlPart.instance.saveTestingSettingsButton.setText(msgSys.getMsg("saveTestingSettings"));
		}
		InitTeachersTestsControlPart.instance.lookSettingsTab.setText(msgSys.getMsg("lookSettings"));
		{
			InitTeachersTestsControlPart.instance.fixedQSelectBtnHeightCheckbox.setText(msgSys.getMsg("fixedQSelectBtnHeight"));
			InitTeachersTestsControlPart.instance.fillAllHeightOfAnswersPanelCheckbox.setText(msgSys.getMsg("fillAllHeightOfAnswersPanel"));
			{
				InitTeachersTestsControlPart.instance.maximazeAnswerButtonHeightCheckbox.setText(msgSys.getMsg("maximazeAnswerButtonHeight"));
			}
			InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsLabel.setText(msgSys.getMsg("spaceBetweenAnswerButtons"));
			InitTeachersTestsControlPart.instance.saveLookSettingsButton.setText(msgSys.getMsg("saveLookSettings"));
		}

		try
		{
			File languageCfg = new File("language.cfg");
			if (!languageCfg.exists())
				languageCfg.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(languageCfg));
			String text = "";
			text += "\nlanguage: \"" + msgSys.getLanguage() + "\"";
			writer.write(text);
			writer.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static class Test
	{
		private final String syntaxLanguage;
		private final String programVersion;
		private final String colorType;
		private final String testVersion;
		private final String testCreationDate;
		private final String testLanguage;
		private final String testSubject;
		private final String authors;
		private final String name;
		private final String description;
		private final int maxTestTime;
		private final Theme theme;
		@SuppressWarnings("deprecation")
		private final Theme2 theme2;
		private final Question[] questions;

		public Test(String syntaxLanguage, String programVersion, String colorType, String testVersion, String testCreationDate, String testLanguage,
				String testSubject, String authors, String name, String description, int maxTestTime, Theme theme, @SuppressWarnings("deprecation") Theme2 theme2, Question[] questions)
		{
			super();
			this.syntaxLanguage = syntaxLanguage;
			this.programVersion = programVersion;
			this.colorType = colorType;
			this.testVersion = testVersion;
			this.testCreationDate = testCreationDate;
			this.testLanguage = testLanguage;
			this.testSubject = testSubject;
			this.authors = authors;
			this.name = name;
			this.description = description;
			this.maxTestTime = maxTestTime;
			this.theme = theme;
			this.theme2 = theme2;
			this.questions = questions;
		}

		/**
		 * @return the syntaxLanguage
		 */
		public String getSyntaxLanguage()
		{
			return syntaxLanguage;
		}

		/**
		 * @return the programVersion
		 */
		public String getProgramVersion()
		{
			return programVersion;
		}

		/**
		 * @return the colorType
		 */
		public String getColorType()
		{
			return colorType;
		}

		/**
		 * @return the testVersion
		 */
		public String getTestVersion()
		{
			return testVersion;
		}

		/**
		 * @return the testCreationDate
		 */
		public String getTestCreationDate()
		{
			return testCreationDate;
		}

		/**
		 * @return the testLanguage
		 */
		public String getTestLanguage()
		{
			return testLanguage;
		}

		/**
		 * @return the testSubject
		 */
		public String getTestSubject()
		{
			return testSubject;
		}

		/**
		 * @return the authors
		 */
		public String getAuthors()
		{
			return authors;
		}

		/**
		 * @return the name
		 */
		public String getName()
		{
			return name;
		}

		/**
		 * @return the description
		 */
		public String getDescription()
		{
			return description;
		}

		/**
		 * @return the theme
		 */
		public Theme getTheme()
		{
			return theme;
		}

		/**
		 * @return the theme2
		 */
		@SuppressWarnings("deprecation")
		public Theme2 getTheme2()
		{
			return theme2;
		}

		/**
		 * @return the questions
		 */
		public Question[] getQuestions()
		{
			return questions;
		}

		/**
		 * Parses text to "Test"
		 * 
		 * @param text
		 *            - text to parsing
		 * @return "Test" parsed from text
		 */
		@SuppressWarnings("deprecation")
		public static Test valueOf(String text)
		{
			try
			{
				Config cfg = new Config(text);
				if (!cfg.hasValue("syntaxLanguage"))
				{
					Config.print("Syntax is wrong: .test file must contains property 'syntaxLanguage'!");
					Main.exit(ExitCodes.TestNotHaveSyntaxLanguage);
				}
				String syntaxLanguage = cfg.getString("syntaxLanguage", null, false).toLowerCase();
				if (!cfg.hasValue(MessageSystem.getMsg("programVersion", syntaxLanguage)) || !cfg.hasValue(MessageSystem.getMsg("colorType", syntaxLanguage)))
				{
					Config.print("Syntax is wrong: .test file must contains properties - 'syntaxLanguage', 'colorType' and 'programVersion'!");
					Main.exit(ExitCodes.TestNotHaveMainValues);
				}
				switch (syntaxLanguage)
				{
					case "ru_ru":
					case "en_uk":
						break;
					default:
						Config.print("Syntax language '" + syntaxLanguage + "' is not supported!");
						Main.exit(ExitCodes.TestSyntaxLanguageIsNotSupported);
						break;
				}
				if (!cfg.getString(MessageSystem.getMsg("programVersion", syntaxLanguage), null, false).equals(Main.programVersion))
					FXDialogsGenerator.showFXDialog(Main.getStartPart().getStage(), null,
							".test file version does not match with version of this program, this can create problems in work!", JOptionPane.WARNING_MESSAGE,
							JOptionPane.DEFAULT_OPTION, Main.isFxWindowFrame(), true);

				String colorType = cfg.getString(MessageSystem.getMsg("colorType", syntaxLanguage), null, false);

				// Theme theme = new Theme();
				//
				// theme.setWindowBackground(new javafx.scene.paint.Color(255f / 255f, 255f / 255f, 220 / 255f, 1));
				//
				// theme.getPickOne().questionBackground = new javafx.scene.paint.Color(255f / 255f, 150 / 255f, 0 / 255f, 1);
				// theme.pickOne.questionForeground = new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1);
				// theme.pickOne.questionFrame = new javafx.scene.paint.Color(200 / 255f, 100 / 255f, 0 / 255f, 1);
				// theme.pickOne.answersBackground = new javafx.scene.paint.Color(250 / 255f, 250 / 255f, 250 / 255f, 1);
				// theme.pickOne.answersForeground = new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1);
				// theme.pickOne.answersFrame = new javafx.scene.paint.Color(200 / 255f, 200 / 255f, 200 / 255f, 1);
				//
				// theme.selectSome.questionBackground = new javafx.scene.paint.Color(255f / 255f, 150 / 255f, 0 / 255f, 1);
				// theme.selectSome.questionForeground = new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1);
				// theme.selectSome.questionFrame = new javafx.scene.paint.Color(200 / 255f, 100 / 255f, 0 / 255f, 1);
				// theme.selectSome.answersBackground = new javafx.scene.paint.Color(250 / 255f, 250 / 255f, 250 / 255f, 1);
				// theme.selectSome.answersForeground = new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1);
				// theme.selectSome.answersFrame = new javafx.scene.paint.Color(200 / 255f, 200 / 255f, 200 / 255f, 1);
				//
				// theme.enterText.questionBackground = new javafx.scene.paint.Color(255f / 255f, 150 / 255f, 0 / 255f, 1);
				// theme.enterText.questionForeground = new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1);
				// theme.enterText.questionFrame = new javafx.scene.paint.Color(200 / 255f, 100 / 255f, 0 / 255f, 1);
				// theme.enterText.answersBackground = new javafx.scene.paint.Color(250 / 255f, 250 / 255f, 250 / 255f, 1);
				// theme.enterText.answersForeground = new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1);
				// theme.enterText.answersFrame = new javafx.scene.paint.Color(200 / 255f, 200 / 255f, 200 / 255f, 1);
				//
				// theme.matching.questionBackground = new javafx.scene.paint.Color(255f / 255f, 150 / 255f, 0 / 255f, 1);
				// theme.matching.questionForeground = new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1);
				// theme.matching.questionFrame = new javafx.scene.paint.Color(200 / 255f, 100 / 255f, 0 / 255f, 1);
				// theme.matching.answersBackground = new javafx.scene.paint.Color(250 / 255f, 250 / 255f, 250 / 255f, 1);
				// theme.matching.answersForeground = new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1);
				// theme.matching.answersFrame = new javafx.scene.paint.Color(200 / 255f, 200 / 255f, 200 / 255f, 1);
				//
				// theme.arrangement.questionBackground = new javafx.scene.paint.Color(255f / 255f, 150 / 255f, 0 / 255f, 1);
				// theme.arrangement.questionForeground = new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1);
				// theme.arrangement.questionFrame = new javafx.scene.paint.Color(200 / 255f, 100 / 255f, 0 / 255f, 1);
				// theme.arrangement.answersBackground = new javafx.scene.paint.Color(250 / 255f, 250 / 255f, 250 / 255f, 1);
				// theme.arrangement.answersForeground = new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1);
				// theme.arrangement.answersFrame = new javafx.scene.paint.Color(200 / 255f, 200 / 255f, 200 / 255f, 1);
				//
				// theme.distribution.questionBackground = new javafx.scene.paint.Color(255f / 255f, 150 / 255f, 0 / 255f, 1);
				// theme.distribution.questionForeground = new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1);
				// theme.distribution.questionFrame = new javafx.scene.paint.Color(200 / 255f, 100 / 255f, 0 / 255f, 1);
				// theme.distribution.answersBackground = new javafx.scene.paint.Color(250 / 255f, 250 / 255f, 250 / 255f, 1);
				// theme.distribution.answersForeground = new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1);
				// theme.distribution.answersFrame = new javafx.scene.paint.Color(200 / 255f, 200 / 255f, 200 / 255f, 1);
				//
				// theme.questionSelectNormalBackground = new javafx.scene.paint.Color(250 / 255f, 250 / 255f, 250 / 255f, 1);
				// theme.questionSelectNormalForeground = new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1);
				// theme.questionSelectNormalFrame = new javafx.scene.paint.Color(180 / 255f, 180 / 255f, 180 / 255f, 1);
				// theme.questionSelectSkippedBackground = new javafx.scene.paint.Color(255f / 255f, 255f / 255f, 0 / 255f, 1);
				// theme.questionSelectSkippedForeground = new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1);
				// theme.questionSelectSkippedFrame = new javafx.scene.paint.Color(200 / 255f, 180 / 255f, 0 / 255f, 1);
				//
				// theme.specialButtonsBackground = new javafx.scene.paint.Color(150 / 255f, 220 / 255f, 30 / 255f, 1);
				// theme.specialButtonsForeground = new javafx.scene.paint.Color(255f / 255f, 255f / 255f, 255f / 255f, 1);
				// theme.specialButtonsFrame = new javafx.scene.paint.Color(110 / 255f, 200 / 255f, 50 / 255f, 1);

				Theme theme = new Theme();

				theme.setWindowBackground(new javafx.scene.paint.Color(255f / 255f, 255f / 255f, 220 / 255f, 1));

				theme.getPickOne().setQuestionBackground(new javafx.scene.paint.Color(255f / 255f, 150 / 255f, 0 / 255f, 1));
				theme.getPickOne().setQuestionForeground(new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1));
				theme.getPickOne().setQuestionFrame(new javafx.scene.paint.Color(200 / 255f, 100 / 255f, 0 / 255f, 1));
				theme.getPickOne().setAnswersBackground(new javafx.scene.paint.Color(250 / 255f, 250 / 255f, 250 / 255f, 1));
				theme.getPickOne().setAnswersForeground(new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1));
				theme.getPickOne().setAnswersFrame(new javafx.scene.paint.Color(200 / 255f, 200 / 255f, 200 / 255f, 1));

				theme.getSelectSome().setQuestionBackground(new javafx.scene.paint.Color(255f / 255f, 150 / 255f, 0 / 255f, 1));
				theme.getSelectSome().setQuestionForeground(new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1));
				theme.getSelectSome().setQuestionFrame(new javafx.scene.paint.Color(200 / 255f, 100 / 255f, 0 / 255f, 1));
				theme.getSelectSome().setAnswersBackground(new javafx.scene.paint.Color(250 / 255f, 250 / 255f, 250 / 255f, 1));
				theme.getSelectSome().setAnswersForeground(new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1));
				theme.getSelectSome().setAnswersFrame(new javafx.scene.paint.Color(200 / 255f, 200 / 255f, 200 / 255f, 1));

				theme.getEnterText().setQuestionBackground(new javafx.scene.paint.Color(255f / 255f, 150 / 255f, 0 / 255f, 1));
				theme.getEnterText().setQuestionForeground(new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1));
				theme.getEnterText().setQuestionFrame(new javafx.scene.paint.Color(200 / 255f, 100 / 255f, 0 / 255f, 1));
				theme.getEnterText().setAnswersBackground(new javafx.scene.paint.Color(250 / 255f, 250 / 255f, 250 / 255f, 1));
				theme.getEnterText().setAnswersForeground(new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1));
				theme.getEnterText().setAnswersFrame(new javafx.scene.paint.Color(200 / 255f, 200 / 255f, 200 / 255f, 1));

				theme.getMatching().setQuestionBackground(new javafx.scene.paint.Color(255f / 255f, 150 / 255f, 0 / 255f, 1));
				theme.getMatching().setQuestionForeground(new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1));
				theme.getMatching().setQuestionFrame(new javafx.scene.paint.Color(200 / 255f, 100 / 255f, 0 / 255f, 1));
				theme.getMatching().setAnswersBackground(new javafx.scene.paint.Color(250 / 255f, 250 / 255f, 250 / 255f, 1));
				theme.getMatching().setAnswersForeground(new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1));
				theme.getMatching().setAnswersFrame(new javafx.scene.paint.Color(200 / 255f, 200 / 255f, 200 / 255f, 1));

				theme.getArrangement().setQuestionBackground(new javafx.scene.paint.Color(255f / 255f, 150 / 255f, 0 / 255f, 1));
				theme.getArrangement().setQuestionForeground(new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1));
				theme.getArrangement().setQuestionFrame(new javafx.scene.paint.Color(200 / 255f, 100 / 255f, 0 / 255f, 1));
				theme.getArrangement().setAnswersBackground(new javafx.scene.paint.Color(250 / 255f, 250 / 255f, 250 / 255f, 1));
				theme.getArrangement().setAnswersForeground(new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1));
				theme.getArrangement().setAnswersFrame(new javafx.scene.paint.Color(200 / 255f, 200 / 255f, 200 / 255f, 1));

				theme.getDistribution().setQuestionBackground(new javafx.scene.paint.Color(255f / 255f, 150 / 255f, 0 / 255f, 1));
				theme.getDistribution().setQuestionForeground(new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1));
				theme.getDistribution().setQuestionFrame(new javafx.scene.paint.Color(200 / 255f, 100 / 255f, 0 / 255f, 1));
				theme.getDistribution().setAnswersBackground(new javafx.scene.paint.Color(250 / 255f, 250 / 255f, 250 / 255f, 1));
				theme.getDistribution().setAnswersForeground(new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1));
				theme.getDistribution().setAnswersFrame(new javafx.scene.paint.Color(200 / 255f, 200 / 255f, 200 / 255f, 1));

				theme.setQuestionSelectNormalBackground(new javafx.scene.paint.Color(250 / 255f, 250 / 255f, 250 / 255f, 1));
				theme.setQuestionSelectNormalForeground(new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1));
				theme.setQuestionSelectNormalFrame(new javafx.scene.paint.Color(180 / 255f, 180 / 255f, 180 / 255f, 1));
				theme.setQuestionSelectSkippedBackground(new javafx.scene.paint.Color(255f / 255f, 255f / 255f, 0 / 255f, 1));
				theme.setQuestionSelectSkippedForeground(new javafx.scene.paint.Color(0 / 255f, 0 / 255f, 0 / 255f, 1));
				theme.setQuestionSelectSkippedFrame(new javafx.scene.paint.Color(200 / 255f, 180 / 255f, 0 / 255f, 1));

				theme.setSpecialButtonsBackground(new javafx.scene.paint.Color(150 / 255f, 220 / 255f, 30 / 255f, 1));
				theme.setSpecialButtonsForeground(new javafx.scene.paint.Color(255f / 255f, 255f / 255f, 255f / 255f, 1));
				theme.setSpecialButtonsFrame(new javafx.scene.paint.Color(110 / 255f, 200 / 255f, 50 / 255f, 1));

				Theme2 theme2 = new Theme2();

				theme2.setWindowBackground(new Color(255f / 255f, 255f / 255f, 220 / 255f));

				theme2.getPickOne().setQuestionBackground(new Color(255f / 255f, 150 / 255f, 0 / 255f));
				theme2.getPickOne().setQuestionForeground(new Color(0 / 255f, 0 / 255f, 0 / 255f));
				theme2.getPickOne().setQuestionFrame(new Color(200 / 255f, 100 / 255f, 0 / 255f));
				theme2.getPickOne().setAnswersBackground(new Color(250 / 255f, 250 / 255f, 250 / 255f));
				theme2.getPickOne().setAnswersForeground(new Color(0 / 255f, 0 / 255f, 0 / 255f));
				theme2.getPickOne().setAnswersFrame(new Color(200 / 255f, 200 / 255f, 200 / 255f));

				theme2.getSelectSome().setQuestionBackground(new Color(255f / 255f, 150 / 255f, 0 / 255f));
				theme2.getSelectSome().setQuestionForeground(new Color(0 / 255f, 0 / 255f, 0 / 255f));
				theme2.getSelectSome().setQuestionFrame(new Color(200 / 255f, 100 / 255f, 0 / 255f));
				theme2.getSelectSome().setAnswersBackground(new Color(250 / 255f, 250 / 255f, 250 / 255f));
				theme2.getSelectSome().setAnswersForeground(new Color(0 / 255f, 0 / 255f, 0 / 255f));
				theme2.getSelectSome().setAnswersFrame(new Color(200 / 255f, 200 / 255f, 200 / 255f));

				theme2.getEnterText().setQuestionBackground(new Color(255f / 255f, 150 / 255f, 0 / 255f));
				theme2.getEnterText().setQuestionForeground(new Color(0 / 255f, 0 / 255f, 0 / 255f));
				theme2.getEnterText().setQuestionFrame(new Color(200 / 255f, 100 / 255f, 0 / 255f));
				theme2.getEnterText().setAnswersBackground(new Color(250 / 255f, 250 / 255f, 250 / 255f));
				theme2.getEnterText().setAnswersForeground(new Color(0 / 255f, 0 / 255f, 0 / 255f));
				theme2.getEnterText().setAnswersFrame(new Color(200 / 255f, 200 / 255f, 200 / 255f));

				theme2.getMatching().setQuestionBackground(new Color(255f / 255f, 150 / 255f, 0 / 255f));
				theme2.getMatching().setQuestionForeground(new Color(0 / 255f, 0 / 255f, 0 / 255f));
				theme2.getMatching().setQuestionFrame(new Color(200 / 255f, 100 / 255f, 0 / 255f));
				theme2.getMatching().setAnswersBackground(new Color(250 / 255f, 250 / 255f, 250 / 255f));
				theme2.getMatching().setAnswersForeground(new Color(0 / 255f, 0 / 255f, 0 / 255f));
				theme2.getMatching().setAnswersFrame(new Color(200 / 255f, 200 / 255f, 200 / 255f));

				theme2.getArrangement().setQuestionBackground(new Color(255f / 255f, 150 / 255f, 0 / 255f));
				theme2.getArrangement().setQuestionForeground(new Color(0 / 255f, 0 / 255f, 0 / 255f));
				theme2.getArrangement().setQuestionFrame(new Color(200 / 255f, 100 / 255f, 0 / 255f));
				theme2.getArrangement().setAnswersBackground(new Color(250 / 255f, 250 / 255f, 250 / 255f));
				theme2.getArrangement().setAnswersForeground(new Color(0 / 255f, 0 / 255f, 0 / 255f));
				theme2.getArrangement().setAnswersFrame(new Color(200 / 255f, 200 / 255f, 200 / 255f));

				theme2.getDistribution().setQuestionBackground(new Color(255f / 255f, 150 / 255f, 0 / 255f));
				theme2.getDistribution().setQuestionForeground(new Color(0 / 255f, 0 / 255f, 0 / 255f));
				theme2.getDistribution().setQuestionFrame(new Color(200 / 255f, 100 / 255f, 0 / 255f));
				theme2.getDistribution().setAnswersBackground(new Color(250 / 255f, 250 / 255f, 250 / 255f));
				theme2.getDistribution().setAnswersForeground(new Color(0 / 255f, 0 / 255f, 0 / 255f));
				theme2.getDistribution().setAnswersFrame(new Color(200 / 255f, 200 / 255f, 200 / 255f));

				theme2.setQuestionSelectNormalBackground(new Color(250 / 255f, 250 / 255f, 250 / 255f));
				theme2.setQuestionSelectNormalForeground(new Color(0 / 255f, 0 / 255f, 0 / 255f));
				theme2.setQuestionSelectNormalFrame(new Color(180 / 255f, 180 / 255f, 180 / 255f));
				theme2.setQuestionSelectSkippedBackground(new Color(255f / 255f, 255f / 255f, 0 / 255f));
				theme2.setQuestionSelectSkippedForeground(new Color(0 / 255f, 0 / 255f, 0 / 255f));
				theme2.setQuestionSelectSkippedFrame(new Color(200 / 255f, 180 / 255f, 0 / 255f));

				theme2.setSpecialButtonsBackground(new Color(150 / 255f, 220 / 255f, 30 / 255f));
				theme2.setSpecialButtonsForeground(new Color(255f / 255f, 255f / 255f, 255f / 255f));
				theme2.setSpecialButtonsFrame(new Color(110 / 255f, 200 / 255f, 50 / 255f));

				String qnsStr = MessageSystem.getMsg("questions", syntaxLanguage);
				String qnStr = MessageSystem.getMsg("question", syntaxLanguage);
				String anrsStr = MessageSystem.getMsg("answers", syntaxLanguage);
				String ansStr = MessageSystem.getMsg("answer", syntaxLanguage);
				String awdStr = MessageSystem.getMsg("award", syntaxLanguage);
				String txtStr = MessageSystem.getMsg("text", syntaxLanguage);
				String fsStr = MessageSystem.getMsg("fontSize", syntaxLanguage);
				String minResStr = MessageSystem.getMsg("minimalResult", syntaxLanguage);
				String igreCaseStr = MessageSystem.getMsg("ignoreCase", syntaxLanguage);
				String handleOnlyMaximalStr = MessageSystem.getMsg("handleOnlyMaximal", syntaxLanguage);
				String igrdChrsStr = MessageSystem.getMsg("ignoredCharacters", syntaxLanguage);
				int dqFont = cfg.getInteger(qnsStr + ":" + MessageSystem.getMsg("fontSize", syntaxLanguage), 16, true);
				int daFont = cfg.getInteger(qnsStr + ":" + MessageSystem.getMsg("answerFontSize", syntaxLanguage), 16, true);
				int stMinRes = cfg.getInteger(qnsStr + ":" + minResStr, Integer.MIN_VALUE, true);
				boolean randomizeAll = cfg.getBoolean(qnsStr + ":" + "randomize", true, true);
				boolean randomizeBlocks = cfg.getBoolean(qnsStr + ":" + "randomizeBlocks", true, true);
				String awardsForAnswersStr = MessageSystem.getMsg("awardsForAnswers", syntaxLanguage);
				String awardForAnswerStr = MessageSystem.getMsg("awardForAnswer", syntaxLanguage);
				String answersIndexesStr = MessageSystem.getMsg("answersIndexes", syntaxLanguage);
				String answerIndexStr = MessageSystem.getMsg("answerIndex", syntaxLanguage);
				String numberStr = MessageSystem.getMsg("number", syntaxLanguage);
				String indexStr = MessageSystem.getMsg("index", syntaxLanguage);
				String indexesForNamesStr = MessageSystem.getMsg("indexesForNames", syntaxLanguage);
				String nameStr = MessageSystem.getMsg("name", syntaxLanguage);
				ArrayList<Question> questions = new ArrayList<Question>();
				ArrayList<ArrayList<Question>> list = new ArrayList<ArrayList<Question>>();
				for (QuestionType questionType : QuestionType.values())
					if (cfg.hasValue(qnsStr + ":" + MessageSystem.getMsg(questionType.name().toLowerCase(), syntaxLanguage) + ":" + qnStr + 1))
					{
						String s = qnsStr + ":" + MessageSystem.getMsg(questionType.name().toLowerCase(), syntaxLanguage) + ":" + qnStr;
						String questionText = cfg.getObject(s + 1, true);
						ArrayList<Question> typedQuestions = new ArrayList<Question>();
						for (int i = 0; cfg.hasValue(s + (i + 1)); i++, questionText = cfg.getObject(s + (i + 1), true))
						{
							Config question = new Config(questionText);
							typedQuestions.add(loadQuestion(question, anrsStr, syntaxLanguage, daFont, ansStr, txtStr, awdStr, fsStr, questionType, minResStr,
									stMinRes, handleOnlyMaximalStr, dqFont, igrdChrsStr, igreCaseStr, awardsForAnswersStr, awardForAnswerStr, answersIndexesStr,
									answerIndexStr, numberStr, indexStr, indexesForNamesStr, nameStr));
						}
						list.add(typedQuestions);
					}
				ArrayList<Question[]> mList = new ArrayList<Question[]>();
				for (int i = 0; i < list.size(); i++)
					if (list.get(i).size() > 0)
					{
						int questionsToTestAmount = cfg.getInteger(qnsStr + ":" + QuestionType.values()[i].name() + ":" + MessageSystem.getMsg(
								"questionsToTestAmount", syntaxLanguage), list.get(i).size(), true);
						boolean randomize = cfg.getBoolean(qnsStr + ":" + QuestionType.values()[i].name() + ":" + MessageSystem.getMsg("randomize",
								syntaxLanguage), true, true);
						if (questionsToTestAmount > list.get(i).size())
						{
							questionsToTestAmount = list.get(i).size();
							FXDialogsGenerator.showFXDialog(Main.getStartPart().getStage(), null,
									"Warning: questions to test amount more than questions amount!", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION,
									Main.isFxWindowFrame(), true);
						}
						if (questionsToTestAmount <= 0)
						{
							questionsToTestAmount = list.get(i).size();
							FXDialogsGenerator.showFXDialog(Main.getStartPart().getStage(), null, "Warning: questions to test amount less then one!",
									JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, Main.isFxWindowFrame(), true);
						}
						Question[] array = new Question[questionsToTestAmount];
						if (randomize)
							mList.add(array = randomizeToArray(list.get(i), array));
						else mList.add(array = mList.get(i));
					}
				Question[][] mArray = new Question[mList.size()][];
				if (randomizeBlocks)
					mArray = randomizeToArray(mList, mArray);
				else mArray = mList.toArray(mArray);
				for (int i = 0; i < mArray.length; i++)
					if (mArray[i] != null)
						for (Question q : mArray[i])
							questions.add(q);
				if (questions.size() <= 0)
				{
					FXDialogsGenerator.showFXDialog(Main.getStartPart().getStage(), null, "Error: test don't have any questions!", JOptionPane.ERROR_MESSAGE,
							JOptionPane.DEFAULT_OPTION, Main.isFxWindowFrame(), true);
					Main.exit(ExitCodes.TestNotHaveQuestions);
				}

				int allQuestionsToTestAmount = cfg.getInteger(qnsStr + ":" + MessageSystem.getMsg("questionsToTestAmount", syntaxLanguage), questions.size(),
						true);
				if (allQuestionsToTestAmount > questions.size())
				{
					allQuestionsToTestAmount = questions.size();
					FXDialogsGenerator.showFXDialog(Main.getStartPart().getStage(), null, "Warning: questions to test amount more than questions amount!",
							JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, Main.isFxWindowFrame(), true);
				}
				if (allQuestionsToTestAmount <= 0)
				{
					allQuestionsToTestAmount = questions.size();
					FXDialogsGenerator.showFXDialog(Main.getStartPart().getStage(), null, "Warning: questions to test amount less then one!",
							JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, Main.isFxWindowFrame(), true);
				}
				Question[] array = new Question[allQuestionsToTestAmount];
				if (randomizeAll)
					array = randomizeToArray(questions, array);
				else array = questions.toArray(array);

				return new Test(syntaxLanguage, cfg.getString(MessageSystem.getMsg("programVersion", syntaxLanguage), null, false), colorType, cfg.getString(
						MessageSystem.getMsg("testVersion", syntaxLanguage), null, false), cfg.getString(MessageSystem.getMsg("testCreationDate",
								syntaxLanguage), null, false), cfg.getString(MessageSystem.getMsg("testLanguage", syntaxLanguage), null, false), cfg.getString(
										MessageSystem.getMsg("testSubject", syntaxLanguage), null, false), cfg.getString(MessageSystem.getMsg("authors",
												syntaxLanguage), null, false), cfg.getString(MessageSystem.getMsg("naming", syntaxLanguage), null, false), cfg
														.getString(MessageSystem.getMsg("description", syntaxLanguage), null, false), cfg.getInteger(
																MessageSystem.getMsg("maxTestTime", syntaxLanguage), null, false), theme, theme2, array);
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
				Main.exit(ExitCodes.UnknownError);
			}
			return null;
		}

		public int getMaxTestTime()
		{
			return maxTestTime;
		}
	}

	private static Question loadQuestion(Config question, String anrsStr, String syntaxLanguage, int daFont, String ansStr, String txtStr, String awdStr,
			String fsStr, QuestionType type, String minResStr, int stMinRes, String handleOnlyMaximalStr, int dqFont, String igrdChrsStr, String igreCaseStr,
			String awardsForAnswersStr, String awardForAnswerStr, String answersIndexesStr, String answerIndexStr, String numberStr, String indexStr,
			String indexesForNamesStr, String nameStr)
	{
		int dqaFont = question.getInteger(anrsStr + ":" + MessageSystem.getMsg("fontSize", syntaxLanguage), daFont, true);

		ArrayList<Answer> answers = new ArrayList<Answer>();
		String answer = question.getObject(anrsStr + ":" + ansStr + 1, true);
		for (int j = 0; question.hasValue(anrsStr + ":" + ansStr + (j + 1)); j++, answer = question.getObject(anrsStr + ":" + ansStr + (j + 1), true))
		{
			Config ans = new Config(answer);
			answers.add(new Answer(ans.getString(txtStr, null, false).replace("\\n", "\n"), new Font("Ms Comic Sans", 0, ans.getInteger(fsStr, dqaFont, true)),
					ans.getInteger(awdStr, 0, true), j));
		}
		HashMap<HashMap<Integer, Integer>, Integer> answerss = new HashMap<HashMap<Integer, Integer>, Integer>();
		String answerssn = question.getObject(awardsForAnswersStr + ":" + awardForAnswerStr + (1), true);
		for (int j = 0; question.hasValue(awardsForAnswersStr + ":" + awardForAnswerStr + (j + 1)); j++, answerssn = question.getObject(awardsForAnswersStr
				+ ":" + awardForAnswerStr + (j + 1), true))
		{
			Config ans = new Config(answerssn);
			HashMap<Integer, Integer> answersss = new HashMap<Integer, Integer>();
			Config answerssnn = new Config(ans.getObject(answersIndexesStr + ":" + answerIndexStr + (1), true));
			for (int k = 0; ans.hasValue(answersIndexesStr + ":" + answerIndexStr + (k + 1)); k++, answerssnn = new Config(ans.getObject(answersIndexesStr + ":"
					+ answerIndexStr + (k + 1), true)))
				answersss.put(answerssnn.getInteger(numberStr, null, false) - 1, answerssnn.getInteger(indexStr, null, false) - 1);
			answerss.put(answersss, ans.getInteger(awdStr, 0, true));
		}
		ArrayList<String> isfmsList = new ArrayList<String>();
		String ifmO = question.getString(indexesForNamesStr + ":" + nameStr + (1), null, true);
		for (int j = 0; question.hasValue(indexesForNamesStr + ":" + nameStr + (j + 1)); j++, ifmO = question.getString(indexesForNamesStr + ":" + nameStr + (j
				+ 1), null, true))
			isfmsList.add(ifmO);
		String[] isfms = randomizeToArray(isfmsList, new String[isfmsList.size()]);
		return new Question(question.getString(txtStr, null, false), new Font("Ms Comic Sans", 0, question.getInteger(fsStr, dqFont, true)), question
				.getInteger(awdStr, 0, true), question.getInteger(minResStr, stMinRes, true), type, randomizeToArray(answers, new Answer[answers.size()]),
				type == QuestionType.Arrangement ? question.getBoolean(handleOnlyMaximalStr, null, false) : false, answerss, isfms, question.getString(
						igrdChrsStr, "", true), question.getBoolean(igreCaseStr, true, true));
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] randomizeToArray(ArrayList<T> list1, T[] array)
	{
		ArrayList<T> list = (ArrayList<T>) list1.clone();
		for (int i = 0; i < Math.min(array.length, list1.size()); i++)
			array[i] = list.remove(random.nextInt(list.size()));
		return array;
	}

	@SuppressWarnings("unchecked")
	public static <T> ArrayList<T> randomizeToList(ArrayList<T> list1, ArrayList<T> array)
	{
		ArrayList<T> list = (ArrayList<T>) list1.clone();
		for (int i = 0; i < Math.min(array.size(), list1.size()); i++)
			array.add(list.remove(random.nextInt(list.size())));
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> ArrayList<T> toList(T[] array, ArrayList<T> list1)
	{
		ArrayList<T> list = (ArrayList<T>) list1.clone();
		for (int i = 0; i < array.length; i++)
			list.add(array[i]);
		return list;
	}
}
