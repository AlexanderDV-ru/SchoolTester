package ru.alexanderdv.schooltester.main.teacher;

import java.awt.Rectangle;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.alexanderdv.schooltester.main.AccountsPart;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.main.TestingPart;
import ru.alexanderdv.schooltester.utilities.ByteUtils;
import ru.alexanderdv.schooltester.utilities.Config;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.Statistics;
import ru.alexanderdv.schooltester.utilities.SystemUtils;
import ru.alexanderdv.schooltester.utilities.fx.ComboboxWithAdd;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;
import ru.alexanderdv.schooltester.utilities.network.ResultSendPacket;
import ru.alexanderdv.schooltester.utilities.network.TestingTaskPacket;
import ru.alexanderdv.schooltester.utilities.types.StudentInfo;
import ru.alexanderdv.schooltester.utilities.types.Test;
import ru.alexanderdv.schooltester.utilities.types.Test.Permissions;
import ru.alexanderdv.schooltester.utilities.types.TestSettings;
import ru.alexanderdv.schooltester.utilities.types.TestToMarket;
import ru.alexanderdv.schooltester.utilities.types.TestingPartSettings;
import ru.alexanderdv.schooltester.utilities.types.TestingTask;
import ru.alexanderdv.schooltester.utilities.types.TestingTask.TaskType;
import ru.alexanderdv.schooltester.utilities.types.Theme;
import ru.alexanderdv.simpleutilities.MathWithText;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class TeachersTestsControlPart extends ProtectedFXWindow
{
	public static TeachersTestsControlPart instance;

	private static final MessageSystem msgSys = Main.msgSys;
	public static final String fileTreeRoot = MessageSystem.getMsg("fileTree", "en_uk"), country = country(), region = !System.getProperty("user.timezone")
			.split("/")[0].equals("") ? System.getProperty("user.timezone").split("/")[0] : undef(), city = System.getProperty("user.timezone").split(
					"/").length > 1 ? System.getProperty("user.timezone").split("/")[1] : undef(), school = undef();

	public static final String path = fileTreeRoot + "/" + country + "/" + region + "/" + city + "/" + school;

	/*
	 * private final TabPane testingAndResultsTabpane; private final Tab testingTab; private final Tab resultsTab;
	 * 
	 * private final Pane testingPane;
	 * 
	 * private final Label testNameLabel1; private final ComboBox<String> testNameCombobox1;
	 * 
	 * private final TabPane testSettingsAndTestingPartSettingsTabpane;
	 * 
	 * private final Tab testSettingsTab; private final Pane testSettingsPane;
	 * 
	 * private final RadioButton defaultRadiobutton; private final RadioButton indicateLastAnswerQualityRadiobutton; private final CheckBox
	 * indicateAllAnswersQualityCheckbox; private final CheckBox showRightAnswerCheckbox; private final RadioButton goToAllQuestionsRadiobutton; private final
	 * CheckBox pauseCheckbox; private final CheckBox pauseOnUnfocusCheckbox; private final CheckBox skipCheckbox; private final CheckBox anticopyCheckbox;
	 * private final CheckBox antiscreenshotCheckbox;
	 * 
	 * private final Tab testingPartSettingsTab; private final Pane testingPartSettingsPane;
	 * 
	 * private final CheckBox fixedHeightOfQuestionSelectButtonCheckbox; private final CheckBox fillAllHeightOfAnswersPanelCheckbox; private final CheckBox
	 * maximazeAnswerButtonHeightCheckbox;
	 * 
	 * private final TabPane networkAndInComputerTabpane; private final Tab networkTab; private final Pane networkPane; private final Button sendTestButton;
	 * 
	 * private final Tab inComputerTab; private final Pane inComputerPane; private final ComboBox<String> classNumberCombobox1; private final Label
	 * classNumberLabel1, classLetterLabel1, surnameLabel1, nameLabel1, secondNameLabel1; private final ComboboxWithAdd classLetterCombobox, surnameCombobox,
	 * nameCombobox, secondNameCombobox; private final Button startTestButton;
	 * 
	 * private final ComboBox<String> classNumberCombobox2; private final Label classNumberLabel2, classLetterLabel2, loginLabel2, surnameLabel2, nameLabel2,
	 * secondNameLabel2; private final TextField classLetterTextfield, surnameTextfield, loginTextfield, nameTextfield, secondNameTextfield; private final
	 * CheckBox classNumberCheckbox, classLetterCheckbox, loginCheckbox, surnameCheckbox, nameCheckbox, secondNameCheckbox; private final Pane resultsPane;
	 * private final TabPane statsAndResultsViewTabpane; private final Tab statsTab; private final Pane statsPane; private final Label testNameLabel2; private
	 * final ComboBox<String> testNameCombobox2; private final Tab resultsViewTab; private final ScrollPane resultsViewPane; private final Label testNameLabel3;
	 * private final ComboBox<String> testNameCombobox3; private final CheckBox testNameCheckbox;
	 */

	public TeachersTestsControlPart()
	{
		super(null, /* Main.createPane(500, 800) */TeachersTestsControlPart.class.getResource("/TestsControlPart.fxml"), 1, 2, true, true);
		instance = this;
		/**
		 * stage.show(); // Init panel.getChildren().add(testingAndResultsTabpane = new TabPane(testingTab = new Tab("testing", testingPane = new Pane()),
		 * resultsTab = new Tab( "results", resultsPane = new Pane()))); { testingPane.getChildren().add(testNameLabel1 = new Label());
		 * testingPane.getChildren().add(testNameCombobox1 = new ComboBox<String>());
		 * 
		 * testingPane.getChildren().add(testSettingsAndTestingPartSettingsTabpane = new TabPane(testSettingsTab = new Tab("", testSettingsPane = new Pane()),
		 * testingPartSettingsTab = new Tab("", testingPartSettingsPane = new Pane()))); { testSettingsPane.getChildren().add(defaultRadiobutton = new
		 * RadioButton()); testSettingsPane.getChildren().add(indicateLastAnswerQualityRadiobutton = new RadioButton());
		 * testSettingsPane.getChildren().add(indicateAllAnswersQualityCheckbox = new CheckBox()); testSettingsPane.getChildren().add(showRightAnswerCheckbox =
		 * new CheckBox()); testSettingsPane.getChildren().add(goToAllQuestionsRadiobutton = new RadioButton());
		 * testSettingsPane.getChildren().add(pauseCheckbox = new CheckBox()); testSettingsPane.getChildren().add(pauseOnUnfocusCheckbox = new CheckBox());
		 * testSettingsPane.getChildren().add(skipCheckbox = new CheckBox()); testSettingsPane.getChildren().add(anticopyCheckbox = new CheckBox());
		 * testSettingsPane.getChildren().add(antiscreenshotCheckbox = new CheckBox()); } {
		 * testingPartSettingsPane.getChildren().add(fixedHeightOfQuestionSelectButtonCheckbox = new CheckBox());
		 * testingPartSettingsPane.getChildren().add(fillAllHeightOfAnswersPanelCheckbox = new CheckBox());
		 * testingPartSettingsPane.getChildren().add(maximazeAnswerButtonHeightCheckbox = new CheckBox()); }
		 * 
		 * testingPane.getChildren().add(networkAndInComputerTabpane = new TabPane(inComputerTab = new Tab("", inComputerPane = new Pane()), networkTab = new
		 * Tab("", networkPane = new Pane()))); { inComputerPane.getChildren().add(classNumberLabel1 = new Label());
		 * inComputerPane.getChildren().add(classLetterLabel1 = new Label()); inComputerPane.getChildren().add(surnameLabel1 = new Label());
		 * inComputerPane.getChildren().add(nameLabel1 = new Label()); inComputerPane.getChildren().add(secondNameLabel1 = new Label());
		 * 
		 * inComputerPane.getChildren().add(classNumberCombobox1 = new ComboBox<String>()); inComputerPane.getChildren().add(classLetterCombobox = new
		 * ComboboxWithAdd()); inComputerPane.getChildren().add(surnameCombobox = new ComboboxWithAdd()); inComputerPane.getChildren().add(nameCombobox = new
		 * ComboboxWithAdd()); inComputerPane.getChildren().add(secondNameCombobox = new ComboboxWithAdd());
		 * 
		 * inComputerPane.getChildren().add(startTestButton = new Button()); } { networkPane.getChildren().add(sendTestButton = new Button()); } } {
		 * resultsPane.getChildren().add(classNumberLabel2 = new Label()); resultsPane.getChildren().add(classLetterLabel2 = new Label());
		 * resultsPane.getChildren().add(loginLabel2 = new Label()); resultsPane.getChildren().add(surnameLabel2 = new Label());
		 * resultsPane.getChildren().add(nameLabel2 = new Label()); resultsPane.getChildren().add(secondNameLabel2 = new Label());
		 * 
		 * resultsPane.getChildren().add(classNumberCombobox2 = new ComboBox<String>()); resultsPane.getChildren().add(classLetterTextfield = new TextField());
		 * resultsPane.getChildren().add(loginTextfield = new TextField()); resultsPane.getChildren().add(surnameTextfield = new TextField());
		 * resultsPane.getChildren().add(nameTextfield = new TextField()); resultsPane.getChildren().add(secondNameTextfield = new TextField());
		 * 
		 * resultsPane.getChildren().add(classNumberCheckbox = new CheckBox()); resultsPane.getChildren().add(classLetterCheckbox = new CheckBox());
		 * resultsPane.getChildren().add(loginCheckbox = new CheckBox()); resultsPane.getChildren().add(surnameCheckbox = new CheckBox());
		 * resultsPane.getChildren().add(nameCheckbox = new CheckBox()); resultsPane.getChildren().add(secondNameCheckbox = new CheckBox());
		 * 
		 * resultsPane.getChildren().add(statsAndResultsViewTabpane = new TabPane(statsTab = new Tab("", statsPane = new Pane()), resultsViewTab = new Tab("",
		 * resultsViewPane = new ScrollPane()))); {
		 * 
		 * resultsPane.getChildren().add(testNameLabel2 = new Label()); resultsPane.getChildren().add(testNameCombobox2 = new ComboBox<String>()); } {
		 * 
		 * resultsPane.getChildren().add(testNameLabel3 = new Label()); resultsPane.getChildren().add(testNameCombobox3 = new ComboBox<String>());
		 * resultsPane.getChildren().add(testNameCheckbox = new CheckBox()); } }
		 * 
		 * // Init end testingAndResultsTabpane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		 * networkAndInComputerTabpane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		 * statsAndResultsViewTabpane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); createActionHandlers(); update();
		 */

		createActionHandlers();
		InitTeachersTestsControlPart.instance.changeTestSettings();
		InitTeachersTestsControlPart.instance.changeTestingPartSettings();
		stage.setOnShowing(e -> updateTests());
	}

	/*
	 * @Override protected void createActionHandlers() { super.createActionHandlers(); ToggleGroup gr = new ToggleGroup();
	 * defaultRadiobutton.setToggleGroup(gr); indicateLastAnswerQualityRadiobutton.setToggleGroup(gr); goToAllQuestionsRadiobutton.setToggleGroup(gr);
	 * 
	 * defaultRadiobutton.setOnAction(e -> update()); indicateLastAnswerQualityRadiobutton.setOnAction(e -> update()); goToAllQuestionsRadiobutton.setOnAction(e
	 * -> update()); pauseCheckbox.setOnAction(e -> update());
	 * 
	 * startTestButton.setOnAction(e -> { checkPermissions.handle(e); if (classNumberCombobox1.getSelectionModel().getSelectedItem() == null ||
	 * classLetterCombobox.getSelectionModel().getSelectedItem() == null || classNumberCombobox1.getSelectionModel().getSelectedItem().equals("") ||
	 * classLetterCombobox.getSelectionModel().getSelectedItem() .equals("")) FXDialogsGenerator.showFXDialog(stage, stage,
	 * msgSys.getMsg("classMustBeSelected"), JOptionPane.INFORMATION_MESSAGE, null, true); else if (surnameCombobox.getSelectionModel().getSelectedItem() ==
	 * null || "".equals(surnameCombobox.getSelectionModel().getSelectedItem())) FXDialogsGenerator.showFXDialog(stage, stage,
	 * msgSys.getMsg("surnameMustBeSelected"), JOptionPane.INFORMATION_MESSAGE, null, true); else if (nameCombobox.getSelectionModel().getSelectedItem() == null
	 * || "".equals(nameCombobox.getSelectionModel().getSelectedItem())) FXDialogsGenerator.showFXDialog(stage, stage, msgSys.getMsg("nameMustBeSelected"),
	 * JOptionPane.INFORMATION_MESSAGE, null, true); else if (secondNameCombobox.getSelectionModel().getSelectedItem() == null ||
	 * "".equals(secondNameCombobox.getSelectionModel().getSelectedItem())) FXDialogsGenerator.showFXDialog(stage, stage,
	 * msgSys.getMsg("secondNameMustBeSelected"), JOptionPane.INFORMATION_MESSAGE, null, true); else { Test test = null; String selectedTestName =
	 * testNameCombobox1.getSelectionModel().getSelectedItem(); for (Test t : tests) if (t.getName().equals(selectedTestName)) { test = t; break; } TestingPart
	 * studentsTestingPartFX = new TestingPart(new Rectangle((int) stage.getX(), (int) stage.getY(), (int) stage.getWidth(), (int) stage .getHeight()), test,
	 * testFiles.get(test).getName(), theme, new StudentInfo(AccountsPart.account.get().getLogin(), classNumberCombobox1 .getSelectionModel().getSelectedItem(),
	 * classLetterCombobox.getSelectionModel().getSelectedItem(), surnameCombobox .getSelectionModel().getSelectedItem(),
	 * nameCombobox.getSelectionModel().getSelectedItem(), secondNameCombobox .getSelectionModel().getSelectedItem()), new
	 * TestSettings(indicateLastAnswerQualityRadiobutton.isSelected(), indicateAllAnswersQualityCheckbox.isSelected() &&
	 * !indicateAllAnswersQualityCheckbox.isDisable(), showRightAnswerCheckbox.isSelected() && !showRightAnswerCheckbox.isDisable(),
	 * goToAllQuestionsRadiobutton.isSelected() && !goToAllQuestionsRadiobutton.isDisable(), skipCheckbox .isSelected(), pauseCheckbox.isSelected(),
	 * pauseOnUnfocusCheckbox.isSelected(), anticopyCheckbox .isSelected(), antiscreenshotCheckbox.isSelected()), new TestingPartSettings(
	 * fixedHeightOfQuestionSelectButtonCheckbox.isSelected(), fillAllHeightOfAnswersPanelCheckbox.isSelected(),
	 * fillAllHeightOfAnswersPanelCheckbox.isSelected() && maximazeAnswerButtonHeightCheckbox.isSelected()), true, null); try { studentsTestingPartFX.open(new
	 * Rectangle((int) stage.getX(), (int) stage.getY(), (int) stage.getWidth(), (int) stage.getHeight()), AccountsPart.account.get(), Main.client); } catch
	 * (Exception e1) { FXDialogsGenerator.showFXDialog(stage, (Stage) null, msgSys.getMsg("signInToWork"), 0, null, true); } Main.instance.hideAll(); } }); }
	 */
	/*
	 * private void update() { indicateAllAnswersQualityCheckbox.setDisable(!indicateLastAnswerQualityRadiobutton.isSelected());
	 * showRightAnswerCheckbox.setDisable(!indicateLastAnswerQualityRadiobutton.isSelected()); pauseOnUnfocusCheckbox.setDisable(!pauseCheckbox.isSelected()); }
	 */

	/*
	 * @Override protected void _resize(int w, int h) { testingAndResultsTabpane.setPrefSize(w, h); int h2 = h - Main.titleHeight; double xoffset = 20, height =
	 * 20, dst = 5, offset = height + dst;
	 * 
	 * // Resize testing part { testNameLabel1.setLayoutX(0); testNameLabel1.setLayoutY(0); testNameLabel1.setPrefSize(w / 2, 30);
	 * 
	 * testNameCombobox1.setLayoutX(w / 2); testNameCombobox1.setLayoutY(0); testNameCombobox1.setPrefSize(w / 2, 30);
	 * 
	 * double y = 0; testSettingsAndTestingPartSettingsTabpane.setLayoutX(0);
	 * testSettingsAndTestingPartSettingsTabpane.setLayoutY(testNameCombobox1.getLayoutY() + testNameCombobox1.getPrefHeight()); {
	 * defaultRadiobutton.setLayoutX(0); defaultRadiobutton.setLayoutY(y += dst); defaultRadiobutton.setPrefSize(w, height);
	 * 
	 * indicateLastAnswerQualityRadiobutton.setLayoutX(0); indicateLastAnswerQualityRadiobutton.setLayoutY(y += offset);
	 * indicateLastAnswerQualityRadiobutton.setPrefSize(w, height);
	 * 
	 * indicateAllAnswersQualityCheckbox.setLayoutX(xoffset); indicateAllAnswersQualityCheckbox.setLayoutY(y += offset);
	 * indicateAllAnswersQualityCheckbox.setPrefSize(w - xoffset, height);
	 * 
	 * showRightAnswerCheckbox.setLayoutX(xoffset); showRightAnswerCheckbox.setLayoutY(y += offset); showRightAnswerCheckbox.setPrefSize(w - xoffset, height);
	 * 
	 * goToAllQuestionsRadiobutton.setLayoutX(0); goToAllQuestionsRadiobutton.setLayoutY(y += offset); goToAllQuestionsRadiobutton.setPrefSize(w, height);
	 * 
	 * skipCheckbox.setLayoutX(0); skipCheckbox.setLayoutY(y += offset); skipCheckbox.setPrefSize(w, height);
	 * 
	 * pauseCheckbox.setLayoutX(0); pauseCheckbox.setLayoutY(y += offset); pauseCheckbox.setPrefSize(w, height);
	 * 
	 * pauseOnUnfocusCheckbox.setLayoutX(xoffset); pauseOnUnfocusCheckbox.setLayoutY(y += offset); pauseOnUnfocusCheckbox.setPrefSize(w - xoffset, height);
	 * 
	 * anticopyCheckbox.setLayoutX(0); anticopyCheckbox.setLayoutY(y += offset); anticopyCheckbox.setPrefSize(w, height);
	 * 
	 * antiscreenshotCheckbox.setLayoutX(0); antiscreenshotCheckbox.setLayoutY(y += offset); antiscreenshotCheckbox.setPrefSize(w, height); }
	 * testSettingsAndTestingPartSettingsTabpane.setPrefSize(w, y += dst);
	 * 
	 * networkAndInComputerTabpane.setLayoutX(0); networkAndInComputerTabpane.setLayoutY(testSettingsAndTestingPartSettingsTabpane.getLayoutY() +
	 * testSettingsAndTestingPartSettingsTabpane .getPrefHeight()); networkAndInComputerTabpane.setPrefSize(w, h2 - networkAndInComputerTabpane.getLayoutY()); }
	 * // Resize results part { double y = 0; statsAndResultsViewTabpane.setLayoutX(0); statsAndResultsViewTabpane.setLayoutY(y += 0);
	 * statsAndResultsViewTabpane.setPrefSize(w, h2 - y); } }
	 */

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
		RadioButton inPercentsRadiobutton = InitTeachersTestsControlPart.instance.inPercentsRadiobutton;
		RadioButton defaultRadiobutton = InitTeachersTestsControlPart.instance.defaultRadiobutton,
				indicateLastAnswerQualityRadiobutton = InitTeachersTestsControlPart.instance.indicateLastAnswerQualityRadiobutton,
				goToAllQuestions = InitTeachersTestsControlPart.instance.goToAllQuestionsRadiobutton;
		CheckBox pause = InitTeachersTestsControlPart.instance.pauseCheckbox, pauseOnUnfocus = InitTeachersTestsControlPart.instance.pauseOnUnfocusCheckbox,
				indicateAllAnswersQuality = InitTeachersTestsControlPart.instance.indicateAllAnswersQualityCheckbox,
				showRightAnswer = InitTeachersTestsControlPart.instance.showRightAnswerCheckbox, skip = InitTeachersTestsControlPart.instance.skipCheckbox,
				anticopy = InitTeachersTestsControlPart.instance.anticopyCheckbox,
				antiscreenshot = InitTeachersTestsControlPart.instance.antiscreenshotCheckbox;
		TabPane testsList = InitTeachersTestsControlPart.instance.testsList;
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

		stage.setOnShowing(e -> updateTests());
		AccountsPart.account.addActionListener(e -> updateResultsTab());
		InitTeachersTestsControlPart.instance.getCodeButton.setOnAction(e -> InitTeachersTestsControlPart.instance.codeField.setText(AccountsPart.account.get()
				.getLogin()));
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
			InitTeachersTestsControlPart.instance.saveTestSettings();
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
			InitTeachersTestsControlPart.instance.saveTestSettings();
		});
		surnameCombobox.setOnAddOrRemoveElement((e) ->
		{
			File f5 = new File(path + "/" + classNumberCombobox1.getSelectionModel().getSelectedItem() + "/" + classLetterCombobox.getSelectionModel()
					.getSelectedItem() + "/surname" + "/" + ((String) e.getSource()).replace("\n", ""));
			if (((String) e.getSource()).startsWith("\n"))
				SystemUtils.removeFile(f5);
			else System.out.println(SystemUtils.createDir(f5, true, true));
			InitTeachersTestsControlPart.instance.saveTestSettings();
		});
		nameCombobox.setOnAddOrRemoveElement((e) ->
		{
			File f5 = new File(path + "/" + classNumberCombobox1.getSelectionModel().getSelectedItem() + "/" + classLetterCombobox.getSelectionModel()
					.getSelectedItem() + "/name" + "/" + ((String) e.getSource()).replace("\n", ""));
			if (((String) e.getSource()).startsWith("\n"))
				SystemUtils.removeFile(f5);
			else System.out.println(SystemUtils.createDir(f5, true, true));
			InitTeachersTestsControlPart.instance.saveTestSettings();
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
			InitTeachersTestsControlPart.instance.saveTestSettings();
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
			InitTeachersTestsControlPart.instance.saveTestSettings();
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
			InitTeachersTestsControlPart.instance.saveTestSettings();
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

		saveTestingSettingsButton.setOnAction(event -> InitTeachersTestsControlPart.instance.saveTestSettings());
		InitTeachersTestsControlPart.instance.saveLookSettingsButton.setOnAction(event -> InitTeachersTestsControlPart.instance.saveTestingPartSettings());
		HashMap<String, String> testFileNames = new HashMap<String, String>();
		InitTeachersTestsControlPart.instance.statisticsTab.setOnSelectionChanged(e ->
		{
			if (InitTeachersTestsControlPart.instance.statisticsTab.isSelected())
			{
				testNameCombobox2.getItems().clear();
				testFileNames.clear();
				for (File f : new File("Results").listFiles())
				{
					Config cfg = new Config(f);
					String s = cfg.getString(MessageSystem.getMsg("testName", cfg.getString("syntaxLanguage", null, false)), null, false);
					if (!testNameCombobox2.getItems().contains(s))
					{
						testNameCombobox2.getItems().add(s);
						testFileNames.put(s, f.getName());
					}
				}
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
				FXDialogsGenerator.showFXDialog(stage, stage, msgSys.getMsg("testNotSelected"), JOptionPane.WARNING_MESSAGE, null, true);
				return;
			}
			testsList.getTabs().clear();
			float minResult = Float.MAX_VALUE, maxResult = 0, result = 0, averageResult = 0, perfectResult = 0;
			float questions = 0;
			float minRightAnswers = Float.MAX_VALUE, maxRightAnswers = 0, rightAnswers = 0, averageRightAnswers = 0;
			float minPerfectAnswers = Float.MAX_VALUE, maxPerfectAnswers = 0, perfectAnswers = 0, averagePerfectAnswers = 0;
			float minTime = Float.MAX_VALUE, maxTime = 0, time = 0, averageTime = 0, perfectTime = 0;
			int testsCount = 0;
			for (Config cfg : Statistics.getStatistics(testFileNames.get(testNameCombobox2.getSelectionModel().getSelectedItem()), testNameCombobox2
					.getSelectionModel().getSelectedItem(), !classNumberCheckbox.isSelected() ? null
							: classNumberCombobox2.getSelectionModel().getSelectedItem(), !classLetterCheckbox.isSelected() ? null
									: classLetterTextfield.getText(), !surnameCheckbox.isSelected() ? null : surnameTextfield.getText(), !nameCheckbox
											.isSelected() ? null : nameTextfield.getText(), !secondNameCheckbox.isSelected() ? null
													: secondNameTextfield.getText()))
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
				FXDialogsGenerator.showFXDialog(stage, stage, msgSys.getMsg("testWithFiltersNotExist"), JOptionPane.WARNING_MESSAGE, null, true);
		});
	}

	private HashMap<Test, File> testFiles;
	private Test[] tests;
	private Test selectedTest;

	/*
	 * private void updateTests() { testNameCombobox1.getItems().clear(); File[] files; File testsDir = new File("Tests"); if (!testsDir.isDirectory())
	 * testsDir.delete(); if (!testsDir.exists()) testsDir.mkdir(); files = testsDir.listFiles(); tests = new Test[files.length]; testFiles = new HashMap<Test,
	 * File>(); for (int i = 0; i < files.length; i++) if (files[i].isDirectory()) { File[] inFiles = files[i].listFiles(); for (int j = 0; j < inFiles.length;
	 * j++) if (inFiles[j].isFile() && inFiles[j].getName().endsWith(".test")) { tests[i] = Test.valueOf(new Config(inFiles[j])); testFiles.put(tests[i],
	 * inFiles[j]); testNameCombobox1.getItems().add(tests[i].getName()); } } }
	 */
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
		HashMap<Test, File> testFiles = new HashMap<Test, File>();
		for (int i = 0; i < files.length; i++)
			if (files[i].isDirectory())
			{
				File[] inFiles = files[i].listFiles();
				for (int j = 0; j < inFiles.length; j++)
					if (inFiles[j].isFile() && inFiles[j].getName().endsWith(".test"))
					{
						tests[i] = Test.valueOf(new Config(inFiles[j]));
						testFiles.put(tests[i], inFiles[j]);
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
		InitTeachersTestsControlPart.instance.btn.setOnAction(e ->
		{
			Test test = null;
			String selectedTestName = testNameCombobox1.getSelectionModel().getSelectedItem();
			for (Test t : tests)
				if (t.getName().equals(selectedTestName))
				{
					test = t;
					break;
				}
			File file = testFiles.get(test).getAbsoluteFile().getParentFile();
			HashMap<String, byte[]> files2 = new HashMap<String, byte[]>();
			for (File testD : file.listFiles())
				files2.put(testD.getName(), SystemUtils.readFile(testD));

			Main.sendToServer(new TestingTaskPacket("giveTask", InitTeachersTestsControlPart.instance.login.getText(), new TestingTask(TaskType.Homework,
					AccountsPart.account.get().getLogin(), new TestToMarket(test.getProgramVersion(), test.getTestVersion(), test.getTestCreationDate(), test
							.getTestLanguage(), test.getTestSubject(), test.getAuthors(), test.getName(), test.getDescription(), file.getName(), files2),
					new TestSettings(InitTeachersTestsControlPart.instance.indicateLastAnswerQualityRadiobutton.isSelected(),
							InitTeachersTestsControlPart.instance.indicateAllAnswersQualityCheckbox.isSelected()
									&& !InitTeachersTestsControlPart.instance.indicateAllAnswersQualityCheckbox.isDisable(),
							InitTeachersTestsControlPart.instance.showRightAnswerCheckbox.isSelected()
									&& !InitTeachersTestsControlPart.instance.showRightAnswerCheckbox.isDisable(),
							InitTeachersTestsControlPart.instance.goToAllQuestionsRadiobutton.isSelected()
									&& !InitTeachersTestsControlPart.instance.goToAllQuestionsRadiobutton.isDisable(),
							InitTeachersTestsControlPart.instance.skipCheckbox.isSelected(), InitTeachersTestsControlPart.instance.pauseCheckbox.isSelected(),
							InitTeachersTestsControlPart.instance.pauseOnUnfocusCheckbox.isSelected(), InitTeachersTestsControlPart.instance.anticopyCheckbox
									.isSelected(), InitTeachersTestsControlPart.instance.antiscreenshotCheckbox.isSelected()), new TestingPartSettings(
											InitTeachersTestsControlPart.instance.fixedQSelectBtnHeightCheckbox.isSelected(),
											InitTeachersTestsControlPart.instance.fillAllHeightOfAnswersPanelCheckbox.isSelected(),
											InitTeachersTestsControlPart.instance.fillAllHeightOfAnswersPanelCheckbox.isSelected()
													&& InitTeachersTestsControlPart.instance.maximazeAnswerButtonHeightCheckbox.isSelected()))));
		});
		startTestButton.setOnAction(e ->
		{
			checkPermissions.handle(e);
			if (classNumberCombobox1.getSelectionModel().getSelectedItem() == null || classLetterCombobox.getSelectionModel().getSelectedItem() == null
					|| classNumberCombobox1.getSelectionModel().getSelectedItem().equals("") || classLetterCombobox.getSelectionModel().getSelectedItem()
							.equals(""))
				FXDialogsGenerator.showFXDialog(stage, stage, msgSys.getMsg("classMustBeSelected"), JOptionPane.INFORMATION_MESSAGE, null, true);
			else if (surnameCombobox.getSelectionModel().getSelectedItem() == null || "".equals(surnameCombobox.getSelectionModel().getSelectedItem()))
				FXDialogsGenerator.showFXDialog(stage, stage, msgSys.getMsg("surnameMustBeSelected"), JOptionPane.INFORMATION_MESSAGE, null, true);
			else if (nameCombobox.getSelectionModel().getSelectedItem() == null || "".equals(nameCombobox.getSelectionModel().getSelectedItem()))
				FXDialogsGenerator.showFXDialog(stage, stage, msgSys.getMsg("nameMustBeSelected"), JOptionPane.INFORMATION_MESSAGE, null, true);
			else if (secondNameCombobox.getSelectionModel().getSelectedItem() == null || "".equals(secondNameCombobox.getSelectionModel().getSelectedItem()))
				FXDialogsGenerator.showFXDialog(stage, stage, msgSys.getMsg("secondNameMustBeSelected"), JOptionPane.INFORMATION_MESSAGE, null, true);
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
				TestingPart studentsTestingPartFX = new TestingPart(new Rectangle((int) stage.getX(), (int) stage.getY(), (int) stage.getWidth(), (int) stage
						.getHeight()), test, testFiles.get(test).getName(), theme, new StudentInfo(AccountsPart.account.get().getLogin(), classNumberCombobox1
								.getSelectionModel().getSelectedItem(), classLetterCombobox.getSelectionModel().getSelectedItem(), surnameCombobox
										.getSelectionModel().getSelectedItem(), nameCombobox.getSelectionModel().getSelectedItem(), secondNameCombobox
												.getSelectionModel().getSelectedItem()), new TestSettings(indicateLastAnswerQualityRadiobutton.isSelected(),
														indicateAllAnswersQualityCheckbox.isSelected() && !indicateAllAnswersQualityCheckbox.isDisable(),
														showRightAnswerCheckbox.isSelected() && !showRightAnswerCheckbox.isDisable(),
														goToAllQuestionsRadiobutton.isSelected() && !goToAllQuestionsRadiobutton.isDisable(), skipCheckbox
																.isSelected(), pauseCheckbox.isSelected(), pauseOnUnfocusCheckbox.isSelected(), anticopyCheckbox
																		.isSelected(), antiscreenshotCheckbox.isSelected()), new TestingPartSettings(
																				InitTeachersTestsControlPart.instance.fixedQSelectBtnHeightCheckbox
																						.isSelected(),
																				InitTeachersTestsControlPart.instance.fillAllHeightOfAnswersPanelCheckbox
																						.isSelected(),
																				InitTeachersTestsControlPart.instance.fillAllHeightOfAnswersPanelCheckbox
																						.isSelected()
																						&& InitTeachersTestsControlPart.instance.maximazeAnswerButtonHeightCheckbox
																								.isSelected()), true, null);
				try
				{
					studentsTestingPartFX.open(new Rectangle((int) stage.getX(), (int) stage.getY(), (int) stage.getWidth(), (int) stage.getHeight()),
							AccountsPart.account.get(), Main.client);
				}
				catch (Exception e1)
				{
					FXDialogsGenerator.showFXDialog(stage, (Stage) null, msgSys.getMsg("signInToWork"), 0, null, true);
				}
				Main.instance.hideAll();
			}
		});
		stage.requestFocus();
	}

	public static final Theme theme = new Theme();
	static
	{
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
		/*
		 * testingTab.setText(msgSys.getMsg("testingTab")); inComputerTab.setText(msgSys.getMsg("inComputerTab"));
		 * networkTab.setText(msgSys.getMsg("networkTab")); resultsTab.setText(msgSys.getMsg("resultsTab")); statsTab.setText(msgSys.getMsg("statsTab"));
		 * resultsViewTab.setText(msgSys.getMsg("resultsViewTab"));
		 * 
		 * testNameLabel1.setText(msgSys.getMsg("testName")); defaultRadiobutton.setText(msgSys.getMsg("default"));
		 * indicateLastAnswerQualityRadiobutton.setText(msgSys.getMsg("indicateLastAnswerQuality"));
		 * indicateAllAnswersQualityCheckbox.setText(msgSys.getMsg("indicateAllAnswersQuality"));
		 * showRightAnswerCheckbox.setText(msgSys.getMsg("showRightAnswer")); goToAllQuestionsRadiobutton.setText(msgSys.getMsg("goToAllQuestions"));
		 * pauseCheckbox.setText(msgSys.getMsg("pause")); pauseOnUnfocusCheckbox.setText(msgSys.getMsg("pauseOnUnfocus"));
		 * skipCheckbox.setText(msgSys.getMsg("skip")); anticopyCheckbox.setText(msgSys.getMsg("anticopy"));
		 * antiscreenshotCheckbox.setText(msgSys.getMsg("antiscreenshot"));
		 * 
		 * boolean b = true; if (b) return;
		 */
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
		return "teachersTestsControl";
	}

	public void updateExternalResults()
	{
		InitTeachersTestsControlPart.instance.resultsBox.getChildren().clear();
		for (File f : new File("External results").listFiles())
		{
			String s = SystemUtils.readFile(f, "cp1251");
			Button see = new Button("See");
			see.setOnAction(e -> FXDialogsGenerator.showFXDialog(stage, (Rectangle) null, s, 0, null, true));
			if (s.split("\n").length > 3)
				InitTeachersTestsControlPart.instance.resultsBox.getChildren().add(new Pane(new Label(f.getName()), new Label(s.split("\n")[0]), new Label(s
						.split("\n")[1]), see));
		}
	}

	HashMap<String, HashMap<String, ArrayList<ResultSendPacket>>> results = new HashMap<String, HashMap<String, ArrayList<ResultSendPacket>>>();

	public void addResult(ResultSendPacket packet)
	{
		if (!results.containsKey(AccountsPart.account.get().getLogin()))
			results.put(AccountsPart.account.get().getLogin(), new HashMap<String, ArrayList<ResultSendPacket>>());
		if (!results.get(AccountsPart.account.get().getLogin()).containsKey(packet.getStudent()))
			results.get(AccountsPart.account.get().getLogin()).put(packet.getStudent(), new ArrayList<ResultSendPacket>());
		results.get(AccountsPart.account.get().getLogin()).get(packet.getStudent()).add(packet);
		updateResultsTab();
	}

	private void updateResultsTab()
	{
		InitTeachersTestsControlPart.instance.resultsBox.getChildren().clear();
		if (AccountsPart.account.get() != null)
			if (results.get(AccountsPart.account.get().getLogin()) != null)
				for (String rs : results.get(AccountsPart.account.get().getLogin()).keySet())
					for (ResultSendPacket result : results.get(AccountsPart.account.get().getLogin()).get(rs))
					{
						Node[] nodes = new Node[9];
						nodes[0] = new VBox(5, new Label("Login"), new Label(result.getStudent()));

						nodes[1] = new VBox(5, new Label("Account surname"), new Label(result.getAccSurname()));
						nodes[2] = new VBox(5, new Label("Account name"), new Label(result.getAccName()));
						nodes[3] = new VBox(5, new Label("Account second name"), new Label(result.getAccSecondName()));

						nodes[4] = new VBox(5, new Label("Selected class number"), new Label(result.getSelClassNumber()));
						nodes[4] = new VBox(5, new Label("Selected class letter"), new Label(result.getSelClassLetter()));
						nodes[4] = new VBox(5, new Label("Selected surname"), new Label(result.getSelSurname()));
						nodes[5] = new VBox(5, new Label("Selected name"), new Label(result.getSelName()));
						nodes[6] = new VBox(5, new Label("Selected second name"), new Label(result.getSelSecondName()));

						nodes[7] = new VBox(5, new Label("Date"), new Label(result.getDate().toString()));

						nodes[8] = createButton("See result", e -> FXDialogsGenerator.showFXDialog(stage, (Stage) null, result.getResult(), 0, null, true));
						HBox hBox = new HBox(5, nodes);
						InitTeachersTestsControlPart.instance.resultsBox.getChildren().add(hBox);
					}
	}

	private Button createButton(String string, EventHandler<ActionEvent> ev)
	{
		Button btn = new Button(string);
		btn.setOnAction(ev);
		return btn;
	}

	@Override
	protected void _resize(int w, int h)
	{
		// TODO Auto-generated method stub

	}
}
