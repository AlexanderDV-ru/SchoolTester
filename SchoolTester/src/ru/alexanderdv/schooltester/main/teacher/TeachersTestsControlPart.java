package ru.alexanderdv.schooltester.main.teacher;

import java.awt.Rectangle;
import java.io.File;
import java.net.URL;

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
import javafx.stage.Stage;
import ru.alexanderdv.schooltester.main.AccountsPart;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.main.TestingPart;
import ru.alexanderdv.schooltester.utilities.Config;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.Statistics;
import ru.alexanderdv.schooltester.utilities.SystemUtils;
import ru.alexanderdv.schooltester.utilities.fx.ComboboxWithAdd;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;
import ru.alexanderdv.schooltester.utilities.types.Test;
import ru.alexanderdv.schooltester.utilities.types.Test.Permissions;
import ru.alexanderdv.schooltester.utilities.types.Theme;
import ru.alexanderdv.simpleutilities.MathWithText;

/**
 * 
 * 
 * @author AlexandrDV/AlexanderDV
 * @version 5.9.5a
 */
public class TeachersTestsControlPart extends ProtectedFXWindow
{
	public static TeachersTestsControlPart instance;

	private static final MessageSystem msgSys = Main.msgSys;
	public static final String fileTreeRoot = MessageSystem.getMsg("fileTree", "en_uk"), country = country(), region = !System.getProperty("user.timezone")
			.split("/")[0].equals("") ? System.getProperty("user.timezone").split("/")[0] : undef(), city = System.getProperty("user.timezone").split(
					"/").length > 1 ? System.getProperty("user.timezone").split("/")[1] : undef(), school = undef();

	public static final String path = fileTreeRoot + "/" + country + "/" + region + "/" + city + "/" + school;

	public TeachersTestsControlPart(URL url,boolean inDevelope)
	{
		super(null,url, 1, 1,inDevelope);
		instance = this;
		createActionHandlers();
		changeTestSettings();
		changeTestingSettings();
		changeLookSettings();
		stage.setOnShowing(e -> updateTests());
	}

	private static String undef()
	{
		return MessageSystem.getMsg("undefined", "en_uk");
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
		String lang = MessageSystem.getMessages().containsKey(System.getProperty("user.language")) ? System.getProperty("user.language") : "en_uk";
		String s = MessageSystem.getMsg(System.getProperty("user.country"), lang);
		return s != null ? s : MessageSystem.getMsg("undefined", lang);
	}

	private void changeTestSettings()
	{
		Config cfg = new Config(new File("testSettings.cfg"));
		if (!cfg.hasValue("testName") || !cfg.hasValue("classNumber") || !cfg.hasValue("classLetter") || !cfg.hasValue("selectedSurname") || !cfg.hasValue(
				"selectedName") || !cfg.hasValue("selectedSecondName"))
			saveTestSettings();
		cfg.getText(true);
		InitTeachersTestsControlPart.instance.testNameCombobox1.getSelectionModel().select(cfg.getString("testName", null, false));
		InitTeachersTestsControlPart.instance.classNumberCombobox1.getSelectionModel().select(cfg.getString("classNumber", null, false));
		InitTeachersTestsControlPart.instance.classNumberCombobox1.getOnAction().handle(new ActionEvent());
		InitTeachersTestsControlPart.instance.classLetterCombobox.getSelectionModel().select(cfg.getString("classLetter", null, false));
		InitTeachersTestsControlPart.instance.classLetterCombobox.getOnAction().handle(new ActionEvent());
		InitTeachersTestsControlPart.instance.nameCombobox.getSelectionModel().select(cfg.getString("selectedName", null, false));
		InitTeachersTestsControlPart.instance.surnameCombobox.getSelectionModel().select(cfg.getString("selectedSurname", null, false));
		InitTeachersTestsControlPart.instance.secondNameCombobox.getSelectionModel().select(cfg.getString("selectedSecondName", null, false));
	}

	private void saveTestSettings()
	{
		String text = "";
		text += "\ntestName: \"" + InitTeachersTestsControlPart.instance.testNameCombobox1.getSelectionModel().getSelectedItem() + "\"";
		text += "\nclassNumber: \"" + InitTeachersTestsControlPart.instance.classNumberCombobox1.getSelectionModel().getSelectedItem() + "\"";
		text += "\nclassLetter: \"" + InitTeachersTestsControlPart.instance.classLetterCombobox.getSelectionModel().getSelectedItem() + "\"";
		text += "\nselectedSurname: \"" + InitTeachersTestsControlPart.instance.surnameCombobox.getSelectionModel().getSelectedItem() + "\"";
		text += "\nselectedName: \"" + InitTeachersTestsControlPart.instance.nameCombobox.getSelectionModel().getSelectedItem() + "\"";
		text += "\nselectedSecondName: \"" + InitTeachersTestsControlPart.instance.secondNameCombobox.getSelectionModel().getSelectedItem() + "\"";
		SystemUtils.writeFile(new File("testSettings.cfg"), text, "Cp1251");
	}

	private void changeTestingSettings()
	{
		Config cfg = new Config(new File("testingSettings.cfg"));
		if (!cfg.hasValue("pause") || !cfg.hasValue("pauseOnUnfocus") || !cfg.hasValue("testProps") || !cfg.hasValue("indicateAnswersQuality") || !cfg.hasValue(
				"showRightAnswer") || !cfg.hasValue("skip") || !cfg.hasValue("anticntrlc") || !cfg.hasValue("antiscreenshot"))
			saveTestingSettings();
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
		InitTeachersTestsControlPart.instance.saveTestingSettingsButton.setDisable(true);
	}

	private void saveTestingSettings()
	{
		String text = "";
		text += "pause: " + InitTeachersTestsControlPart.instance.pauseCheckbox.isSelected();
		text += "\npauseOnUnfocus: " + InitTeachersTestsControlPart.instance.pauseOnUnfocusCheckbox.isSelected();
		text += "\ntestProps: " + "\"" + (InitTeachersTestsControlPart.instance.goToAllQuestionsRadiobutton.isSelected() ? "gtaq"
				: (InitTeachersTestsControlPart.instance.indicateLastAnswerQualityRadiobutton.isSelected() ? "iaq" : "none")) + "\"";
		text += "\nindicateAnswersQuality: " + InitTeachersTestsControlPart.instance.indicateAllAnswersQualityCheckbox.isSelected();
		text += "\nshowRightAnswer: " + InitTeachersTestsControlPart.instance.showRightAnswerCheckbox.isSelected();
		text += "\nskip: " + InitTeachersTestsControlPart.instance.skipCheckbox.isSelected();
		text += "\nanticntrlc: " + InitTeachersTestsControlPart.instance.anticopyCheckbox.isSelected();
		text += "\nantiscreenshot: " + InitTeachersTestsControlPart.instance.antiscreenshotCheckbox.isSelected();
		text += "\nfixedQSelectBtnHeight: " + InitTeachersTestsControlPart.instance.fixedQSelectBtnHeightCheckbox.isSelected();
		SystemUtils.writeFile(new File("testingSettings.cfg"), text, "Cp1251");
		InitTeachersTestsControlPart.instance.saveTestingSettingsButton.setDisable(true);
	}

	private void changeLookSettings()
	{
		Config cfg = new Config(new File("lookSettings.cfg"));
		if (!cfg.hasValue("fixedQSelectBtnHeight") || !cfg.hasValue("fillAllHeightOfAnswersPanel") || !cfg.hasValue("maximazeAnswerButtonHeight") || !cfg
				.hasValue("spaceBetweenAnswerButtons"))
			saveLookSettings();
		cfg.getText(true);
		InitTeachersTestsControlPart.instance.fixedQSelectBtnHeightCheckbox.setSelected(cfg.getBoolean("fixedQSelectBtnHeight", null, false));
		InitTeachersTestsControlPart.instance.fillAllHeightOfAnswersPanelCheckbox.setSelected(cfg.getBoolean("fillAllHeightOfAnswersPanel", null, false));
		InitTeachersTestsControlPart.instance.maximazeAnswerButtonHeightCheckbox.setSelected(cfg.getBoolean("maximazeAnswerButtonHeight", null, false));
		InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsField.setText(cfg.getInteger("spaceBetweenAnswerButtons", null, false) + "");
		InitTeachersTestsControlPart.instance.saveLookSettingsButton.setDisable(true);
	}

	private void saveLookSettings()
	{
		String text = "";
		text += "fixedQSelectBtnHeight: " + InitTeachersTestsControlPart.instance.fixedQSelectBtnHeightCheckbox.isSelected();
		text += "\nfillAllHeightOfAnswersPanel: " + InitTeachersTestsControlPart.instance.fillAllHeightOfAnswersPanelCheckbox.isSelected();
		text += "\nmaximazeAnswerButtonHeight: " + InitTeachersTestsControlPart.instance.maximazeAnswerButtonHeightCheckbox.isSelected();
		text += "\nspaceBetweenAnswerButtons: " + MathWithText.parseI(InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsField.getText());
		SystemUtils.writeFile(new File("lookSettings.cfg"), text, "Cp1251");
		InitTeachersTestsControlPart.instance.saveLookSettingsButton.setDisable(true);
	}

	public void createActionHandlers()
	{
		CheckBox classNumberCheckbox = InitTeachersTestsControlPart.instance.classNumberCheckbox,
				classLetterCheckbox = InitTeachersTestsControlPart.instance.classLetterCheckbox,
				surnameCheckbox = InitTeachersTestsControlPart.instance.surnameCheckbox, nameCheckbox = InitTeachersTestsControlPart.instance.nameCheckbox,
				secondNameCheckbox = InitTeachersTestsControlPart.instance.secondNameCheckbox;
		ComboBox<String> classNumberCombobox1 = InitTeachersTestsControlPart.instance.classNumberCombobox1;
		ComboBox<String> testNameCombobox2 = InitTeachersTestsControlPart.instance.testNameCombobox2,
				classNumberCombobox2 = InitTeachersTestsControlPart.instance.classNumberCombobox2;
		ComboboxWithAdd classLetterCombobox = InitTeachersTestsControlPart.instance.classLetterCombobox,
				surnameCombobox = InitTeachersTestsControlPart.instance.surnameCombobox, nameCombobox = InitTeachersTestsControlPart.instance.nameCombobox,
				secondNameCombobox = InitTeachersTestsControlPart.instance.secondNameCombobox;
		TextField classLetterTextfield = InitTeachersTestsControlPart.instance.classLetterTextfield,
				surnameTextfield = InitTeachersTestsControlPart.instance.surnameTextfield, nameTextfield = InitTeachersTestsControlPart.instance.nameTextfield,
				secondNameTextfield = InitTeachersTestsControlPart.instance.secondNameTextfield;
		Button getStatistics = InitTeachersTestsControlPart.instance.getStatistics,
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
				antiscreenshot = InitTeachersTestsControlPart.instance.antiscreenshotCheckbox;
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
			SystemUtils.createDir(f0, true, true);
			File f = new File(f0.getAbsolutePath() + "/" + classNumberCombobox1.getSelectionModel().getSelectedItem());
			SystemUtils.createDir(f, true, true);
			File f2 = new File(f.getAbsolutePath() + "/" + ((String) e.getSource()).replace("\n", ""));
			if (((String) e.getSource()).startsWith("\n"))
				SystemUtils.removeFile(f2);
			else SystemUtils.createDir(f2, true, true);
			saveTestSettings();
		});
		classLetterCombobox.setOnAction((ev) ->
		{
			File f0 = new File(path);
			SystemUtils.createDir(f0, true, true);
			File f = new File(f0.getAbsolutePath() + "/" + classNumberCombobox1.getSelectionModel().getSelectedItem());
			SystemUtils.createDir(f, true, true);
			File f3 = new File(f.getAbsolutePath() + "/" + classLetterCombobox.getSelectionModel().getSelectedItem());
			SystemUtils.createDir(f3, true, true);
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
			saveTestSettings();
		});
		surnameCombobox.setOnAddOrRemoveElement((e) ->
		{
			File f5 = new File(path + "/" + classNumberCombobox1.getSelectionModel().getSelectedItem() + "/" + classLetterCombobox.getSelectionModel()
					.getSelectedItem() + "/surname" + "/" + ((String) e.getSource()).replace("\n", ""));
			if (((String) e.getSource()).startsWith("\n"))
				SystemUtils.removeFile(f5);
			else System.out.println(SystemUtils.createDir(f5, true, true));
			saveTestSettings();
		});
		nameCombobox.setOnAddOrRemoveElement((e) ->
		{
			File f5 = new File(path + "/" + classNumberCombobox1.getSelectionModel().getSelectedItem() + "/" + classLetterCombobox.getSelectionModel()
					.getSelectedItem() + "/name" + "/" + ((String) e.getSource()).replace("\n", ""));
			if (((String) e.getSource()).startsWith("\n"))
				SystemUtils.removeFile(f5);
			else System.out.println(SystemUtils.createDir(f5, true, true));
			saveTestSettings();
		});
		secondNameCombobox.setOnAddOrRemoveElement((e) ->
		{
			File f5 = new File(path + "/" + classNumberCombobox1.getSelectionModel().getSelectedItem() + "/" + classLetterCombobox.getSelectionModel()
					.getSelectedItem() + "/secondname" + "/" + ((String) e.getSource()).replace("\n", ""));
			if (((String) e.getSource()).startsWith("\n"))
				SystemUtils.removeFile(f5);
			else
			{
				System.out.println(f5.getAbsolutePath());
				SystemUtils.createDir(f5.getParentFile(), true, true);
				System.out.println(SystemUtils.createDir(f5, true, true));
			}
			saveTestSettings();
		});
		classNumberCombobox1.setOnAction((ev) ->
		{
			File f0 = new File(path);
			SystemUtils.createDir(f0, true, true);
			File f = new File(f0.getAbsolutePath() + "/" + classNumberCombobox1.getSelectionModel().getSelectedItem());
			SystemUtils.createDir(f, true, true);
			classLetterCombobox.getItems().clear();
			if (f.listFiles() != null)
				for (File f2 : f.listFiles())
					classLetterCombobox.getItems().add(f2.getName());
			saveTestSettings();
		});
		classNumberCombobox2.setOnAction((ev) ->
		{
			File f0 = new File(path);
			SystemUtils.createDir(f0, true, true);
			File f = new File(f0.getAbsolutePath() + "/" + classNumberCombobox2.getSelectionModel().getSelectedItem());
			SystemUtils.createDir(f, true, true);
			classLetterCombobox.getItems().clear();
			if (f.listFiles() != null)
				for (File f2 : f.listFiles())
					classLetterCombobox.getItems().add(f2.getName());
			saveTestSettings();
		});
		EventHandler<ActionEvent> action = (e) ->
		{
			checkPermissions.handle(e);
			saveTestingSettingsButton.setDisable(false);
		};
		EventHandler<ActionEvent> action2 = (e) ->
		{
			checkPermissions.handle(e);
			InitTeachersTestsControlPart.instance.saveLookSettingsButton.setDisable(false);
		};
		EventHandler<ActionEvent> testPropsAction = (e) ->
		{
			checkPermissions.handle(e);
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
			InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsField.setText("" + Math.max(Math.min(MathWithText.parseI(
					InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsField.getText()), 20), 1));
			InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsBar.setProgress(MathWithText.parseI(
					InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsField.getText())
					/ InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsBar.getPrefWidth());
			action2.handle(null);
		});
		InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsField.focusedProperty().addListener(
				e -> InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsField.getOnAction().handle(null));

		saveTestingSettingsButton.setOnAction(event -> saveTestingSettings());
		InitTeachersTestsControlPart.instance.saveLookSettingsButton.setOnAction(event -> saveLookSettings());
		InitTeachersTestsControlPart.instance.statisticsTab.setOnSelectionChanged(e ->
		{
			if (InitTeachersTestsControlPart.instance.statisticsTab.isSelected())
			{
				testNameCombobox2.getItems().clear();
				for (Config cfg : Statistics.getStatistics(null, null, null, null, null, null))
					if (!testNameCombobox2.getItems().contains(cfg.getString(MessageSystem.getMsg("testName", cfg.getString("syntaxLanguage", null, false)),
							null, false)))
						testNameCombobox2.getItems().add(cfg.getString(MessageSystem.getMsg("testName", cfg.getString("syntaxLanguage", null, false)), null,
								false));
			}
		});
		InitTeachersTestsControlPart.instance.testingTab.setOnSelectionChanged(event ->
		{
			if (InitTeachersTestsControlPart.instance.testingTab.isSelected())
				updateTests();
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
	}

	private Test[] tests;
	private Test selectedTest;

	private void updateTests()
	{
		ComboBox<String> testNameCombobox1 = InitTeachersTestsControlPart.instance.testNameCombobox1;
		ComboBox<String> classNumberCombobox1 = InitTeachersTestsControlPart.instance.classNumberCombobox1;
		ComboboxWithAdd classLetterCombobox = InitTeachersTestsControlPart.instance.classLetterCombobox;
		ComboboxWithAdd surnameCombobox = InitTeachersTestsControlPart.instance.surnameCombobox;
		ComboboxWithAdd nameCombobox = InitTeachersTestsControlPart.instance.nameCombobox;
		ComboboxWithAdd secondNameCombobox = InitTeachersTestsControlPart.instance.secondNameCombobox;
		RadioButton indicateLastAnswerQualityRadiobutton = InitTeachersTestsControlPart.instance.indicateLastAnswerQualityRadiobutton;
		CheckBox indicateAllAnswersQualityCheckbox = InitTeachersTestsControlPart.instance.indicateAllAnswersQualityCheckbox;
		CheckBox showRightAnswerCheckbox = InitTeachersTestsControlPart.instance.showRightAnswerCheckbox;
		RadioButton goToAllQuestionsRadiobutton = InitTeachersTestsControlPart.instance.goToAllQuestionsRadiobutton;
		CheckBox skipCheckbox = InitTeachersTestsControlPart.instance.skipCheckbox;
		CheckBox pauseCheckbox = InitTeachersTestsControlPart.instance.pauseCheckbox;
		CheckBox pauseOnUnfocusCheckbox = InitTeachersTestsControlPart.instance.pauseOnUnfocusCheckbox;
		CheckBox anticopyCheckbox = InitTeachersTestsControlPart.instance.anticopyCheckbox;
		CheckBox antiscreenshotCheckbox = InitTeachersTestsControlPart.instance.antiscreenshotCheckbox;
		CheckBox fixedQSelectBtnHeightCheckbox = InitTeachersTestsControlPart.instance.fixedQSelectBtnHeightCheckbox;
		Button startTestButton = InitTeachersTestsControlPart.instance.startTestButton;

		testNameCombobox1.getItems().clear();

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
		}
		files = testsDir.listFiles();
		tests = new Test[files.length];
		for (int i = 0; i < files.length; i++)
			if (files[i].isDirectory())
			{
				File[] inFiles = files[i].listFiles();
				for (int j = 0; j < inFiles.length; j++)
					if (inFiles[j].isFile() && inFiles[j].getName().endsWith(".test"))
					{
						tests[i] = Test.valueOf(new Config(inFiles[j]));
						testNameCombobox1.getItems().add(tests[i].getName());
					}
			}
		testNameCombobox1.getSelectionModel().select(0);
		testNameCombobox1.setOnAction(e ->
		{
			boolean b = false;
			for (Test test : tests)
				if (test.getName().equals(testNameCombobox1.getSelectionModel().getSelectedItem()))
				{
					selectedTest = test;
					b = true;
				}
			if (!b)
				selectedTest = null;
		});
		selectedTest = tests[0];
		boolean hide = true;
		startTestButton.setOnAction(e ->
		{
			checkPermissions.handle(e);
			if (classNumberCombobox1.getSelectionModel().getSelectedItem() == null || classLetterCombobox.getSelectionModel().getSelectedItem() == null
					|| classNumberCombobox1.getSelectionModel().getSelectedItem().equals("") || classLetterCombobox.getSelectionModel().getSelectedItem()
							.equals(""))
				FXDialogsGenerator.showFXDialog(stage, stage, msgSys.getMsg("classMustBeSelected"), JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION,
						Main.isFxWindowFrame(), true);
			else if (surnameCombobox.getSelectionModel().getSelectedItem() == null || "".equals(surnameCombobox.getSelectionModel().getSelectedItem()))
				FXDialogsGenerator.showFXDialog(stage, stage, msgSys.getMsg("surnameMustBeSelected"), JOptionPane.INFORMATION_MESSAGE,
						JOptionPane.DEFAULT_OPTION, Main.isFxWindowFrame(), true);
			else if (nameCombobox.getSelectionModel().getSelectedItem() == null || "".equals(nameCombobox.getSelectionModel().getSelectedItem()))
				FXDialogsGenerator.showFXDialog(stage, stage, msgSys.getMsg("nameMustBeSelected"), JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION,
						Main.isFxWindowFrame(), true);
			else if (secondNameCombobox.getSelectionModel().getSelectedItem() == null || "".equals(secondNameCombobox.getSelectionModel().getSelectedItem()))
				FXDialogsGenerator.showFXDialog(stage, stage, msgSys.getMsg("secondNameMustBeSelected"), JOptionPane.INFORMATION_MESSAGE,
						JOptionPane.DEFAULT_OPTION, Main.isFxWindowFrame(), true);
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
				TestingPart studentsTestingPartFX = new TestingPart(null, getClass().getResource("/TestingPart.fxml"), new Rectangle((int) stage.getX(),
						(int) stage.getY(), (int) stage.getWidth(), (int) stage.getHeight()), test, theme, classNumberCombobox1.getSelectionModel()
								.getSelectedItem(), classLetterCombobox.getSelectionModel().getSelectedItem(), surnameCombobox.getSelectionModel()
										.getSelectedItem(), nameCombobox.getSelectionModel().getSelectedItem(), secondNameCombobox.getSelectionModel()
												.getSelectedItem(), indicateLastAnswerQualityRadiobutton.isSelected(), indicateAllAnswersQualityCheckbox
														.isSelected() && !indicateAllAnswersQualityCheckbox.isDisable(), showRightAnswerCheckbox.isSelected()
																&& !showRightAnswerCheckbox.isDisable(), goToAllQuestionsRadiobutton.isSelected()
																		&& !goToAllQuestionsRadiobutton.isDisable(), skipCheckbox.isSelected(), pauseCheckbox
																				.isSelected(), pauseOnUnfocusCheckbox.isSelected(), anticopyCheckbox
																						.isSelected(), antiscreenshotCheckbox.isSelected(),
						fixedQSelectBtnHeightCheckbox.isSelected(), hide, MathWithText.parseI(
								InitTeachersTestsControlPart.instance.spaceBetweenAnswerButtonsField.getText()),
						InitTeachersTestsControlPart.instance.fillAllHeightOfAnswersPanelCheckbox.isSelected(),
						InitTeachersTestsControlPart.instance.fillAllHeightOfAnswersPanelCheckbox.isSelected()
								&& InitTeachersTestsControlPart.instance.maximazeAnswerButtonHeightCheckbox.isSelected(),false);
				try
				{
					studentsTestingPartFX.open(new Rectangle((int) stage.getX(), (int) stage.getY(), (int) stage.getWidth(), (int) stage.getHeight()),
							AccountsPart.account.get(), Main.instance.socket);
				}
				catch (Exception e1)
				{
					FXDialogsGenerator.showFXDialog(stage, (Stage) null, msgSys.getMsg("signInToWork"), 0, 0, Main.isFxWindowFrame(), true);
				}
				Main.instance.hideAll();
			}
		});
		stage.requestFocus();
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
	}

	EventHandler<ActionEvent> checkPermissions = e ->
	{
		Permissions perms = selectedTest.getPermissionsToStart();
		if (InitTeachersTestsControlPart.instance.indicateLastAnswerQualityRadiobutton.isSelected() && !perms.isShowLastAnswerQualityPermission()
				|| InitTeachersTestsControlPart.instance.indicateAllAnswersQualityCheckbox.isSelected() && !perms.isShowAllAnswersQualityPermission()
				|| InitTeachersTestsControlPart.instance.showRightAnswerCheckbox.isSelected() && !perms.isShowRightAnswerPermission()
				|| InitTeachersTestsControlPart.instance.goToAllQuestionsRadiobutton.isSelected() && !perms.isGoToAllAnswersPermission()
				|| InitTeachersTestsControlPart.instance.skipCheckbox.isSelected() && !perms.isSkipPermission()
				|| InitTeachersTestsControlPart.instance.pauseCheckbox.isSelected() && !perms.isPausePermission())
			e.consume();
	};

	public Test getSelectedTest()
	{
		return selectedTest;
	}

	@Override
	public String name()
	{
		return "testsControl";
	}
}
