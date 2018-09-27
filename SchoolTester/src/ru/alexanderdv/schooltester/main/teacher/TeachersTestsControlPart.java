package ru.alexanderdv.schooltester.main.teacher;

import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

import javax.swing.Timer;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ru.alexanderdv.fxutilities.components.ComboboxWithAdd;
import ru.alexanderdv.fxutilities.components.SignsAfterCommaCombobox;
import ru.alexanderdv.schooltester.main.AccountsPart;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.main.TestingPart;
import ru.alexanderdv.schooltester.utilities.Config;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;
import ru.alexanderdv.schooltester.utilities.network.ResultSendPacket;
import ru.alexanderdv.schooltester.utilities.network.StudentOnlineTestPacket;
import ru.alexanderdv.schooltester.utilities.network.TestingTaskPacket;
import ru.alexanderdv.schooltester.utilities.types.Test;
import ru.alexanderdv.schooltester.utilities.types.TestResult;
import ru.alexanderdv.schooltester.utilities.types.TestSettings;
import ru.alexanderdv.schooltester.utilities.types.TestToMarket;
import ru.alexanderdv.schooltester.utilities.types.TesteeInfo;
import ru.alexanderdv.schooltester.utilities.types.TesterInfo;
import ru.alexanderdv.schooltester.utilities.types.TestingPartSettings;
import ru.alexanderdv.schooltester.utilities.types.TestingTask;
import ru.alexanderdv.schooltester.utilities.types.TestingTask.TaskType;
import ru.alexanderdv.schooltester.utilities.types.Theme;
import ru.alexanderdv.simpleutilities.ByteUtils;
import ru.alexanderdv.simpleutilities.Entry;
import ru.alexanderdv.simpleutilities.MathUtils;
import ru.alexanderdv.simpleutilities.SystemUtils;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class TeachersTestsControlPart extends ProtectedFXWindow
{
	private static final String[] propsToStatsArray = new String[] { "timeOfTest", "fullTime", };
	public static TeachersTestsControlPart instance;

	private static final MessageSystem msgSys = Main.msgSys;
	public static final String fileTreeRoot = msgSys.getMsg("fileTree", "en_uk"), country = country(),
			region = !System.getProperty("user.timezone").split("/")[0].equals("")
					? System.getProperty("user.timezone").split("/")[0]
					: undef(),
			city = System.getProperty("user.timezone").split("/").length > 1
					? System.getProperty("user.timezone").split("/")[1]
					: undef(),
			school = undef();

	public static final String path = fileTreeRoot + "/" + country + "/" + region + "/" + city + "/" + school;

	private final TabPane testingAndResultsTabpane;
	private final Tab testingTab;
	private final Tab resultsTab;

	private final Pane testingPane;

	private final Label testNameLabel1;
	private final ComboBox<String> testNameCombobox1;

	private final TabPane testSettingsAndTestingPartSettingsTabpane;

	private final Tab testSettingsTab;
	private final Pane testSettingsPane;
	private final TabPane testSettingsTabpane;
	private final TextField testSettingsTabRenameTextfield;
	private boolean testSettingsTabAddMode;

	static abstract class SettingsKitTab extends Tab
	{
		protected final ArrayList<EventHandler<Event>> onSelectionChangedHandlers = new ArrayList<EventHandler<Event>>();

		/**
		 * 
		 */
		public SettingsKitTab()
		{
			super();
		}

		/**
		 * @param text
		 * @param content
		 */
		public SettingsKitTab(String text, Node content)
		{
			super(text, content);
		}

		/**
		 * @param text
		 */
		public SettingsKitTab(String text)
		{
			super(text);
		}

		/**
		 * 
		 * @param changeHandler
		 */
		public void addOnSelectionChangedHandler(EventHandler<Event> changeHandler)
		{
			if (changeHandler != null)
				onSelectionChangedHandlers.add(changeHandler);
		}

		/**
		 * 
		 * @param changeHandler
		 */
		public void removeOnSelectionChangedHandler(EventHandler<Event> changeHandler)
		{
			if (changeHandler != null)
				onSelectionChangedHandlers.remove(changeHandler);
		}

		/**
		 * 
		 */
		public void clearOnSelectionChangedHandlers()
		{
			onSelectionChangedHandlers.clear();
		}
	}

	static final class TestSettingsKitTab extends SettingsKitTab
	{
		private final Pane pane;
		private final RadioButton defaultRadiobutton;
		private final RadioButton indicateLastAnswerQualityRadiobutton;
		private final CheckBox indicateAllAnswersQualityCheckbox;
		private final CheckBox showRightAnswerCheckbox;
		private final RadioButton goToAllQuestionsRadiobutton;
		private final CheckBox pauseCheckbox;
		private final CheckBox pauseOnUnfocusCheckbox;
		private final CheckBox skipCheckbox;
		private final CheckBox anticopyCheckbox;
		private final CheckBox antiscreenshotCheckbox;

		public TestSettingsKitTab(String name, TestSettings testSettings)
		{
			super(name);
			setContent(pane = new Pane());
			pane.getChildren().add(defaultRadiobutton = new RadioButton());
			pane.getChildren().add(indicateLastAnswerQualityRadiobutton = new RadioButton());
			pane.getChildren().add(indicateAllAnswersQualityCheckbox = new CheckBox());
			pane.getChildren().add(showRightAnswerCheckbox = new CheckBox());
			pane.getChildren().add(goToAllQuestionsRadiobutton = new RadioButton());
			pane.getChildren().add(pauseCheckbox = new CheckBox());
			pane.getChildren().add(pauseOnUnfocusCheckbox = new CheckBox());
			pane.getChildren().add(skipCheckbox = new CheckBox());
			pane.getChildren().add(anticopyCheckbox = new CheckBox());
			pane.getChildren().add(antiscreenshotCheckbox = new CheckBox());

			if (testSettings != null)
			{
				defaultRadiobutton.setSelected(
						!testSettings.isIndicateQualityOfLastAnswer() && !testSettings.isCanGoToAllQuestions());
				indicateLastAnswerQualityRadiobutton.setSelected(testSettings.isIndicateQualityOfLastAnswer());
				indicateAllAnswersQualityCheckbox.setSelected(testSettings.isIndicateQualityOfAllAnswers());
				showRightAnswerCheckbox.setSelected(testSettings.isShowRightAnswer());
				goToAllQuestionsRadiobutton.setSelected(testSettings.isCanGoToAllQuestions());
				pauseCheckbox.setSelected(testSettings.isPauseOption());
				pauseOnUnfocusCheckbox.setSelected(testSettings.isPauseOnUnfocus());
				skipCheckbox.setSelected(testSettings.isSkipButtonOption());
				anticopyCheckbox.setSelected(testSettings.isAnticopy());
				antiscreenshotCheckbox.setSelected(testSettings.isAntiscreenshot());
			}

			createActionHandlers();
			update();
			updateLabels();

			TeachersTestsControlPart.instance.resize();
		}

		public TestSettingsKitTab(String name)
		{
			this(name, null);
		}

		private void createActionHandlers()
		{
			ToggleGroup gr = new ToggleGroup();
			defaultRadiobutton.setToggleGroup(gr);
			indicateLastAnswerQualityRadiobutton.setToggleGroup(gr);
			goToAllQuestionsRadiobutton.setToggleGroup(gr);

			defaultRadiobutton.setOnAction(e -> update());
			indicateLastAnswerQualityRadiobutton.setOnAction(e -> update());
			indicateAllAnswersQualityCheckbox.setOnAction(e -> update());
			showRightAnswerCheckbox.setOnAction(e -> update());
			goToAllQuestionsRadiobutton.setOnAction(e -> update());
			pauseCheckbox.setOnAction(e -> update());
			pauseOnUnfocusCheckbox.setOnAction(e -> update());
			anticopyCheckbox.setOnAction(e -> update());
			antiscreenshotCheckbox.setOnAction(e -> update());

			setOnClosed(e -> delete());
			setOnSelectionChanged(e ->
			{
				if (isSelected())
					TeachersTestsControlPart.instance.selectedTestSettingsKitTab = this;
				for (EventHandler<Event> onSelectionChanged : onSelectionChangedHandlers)
					onSelectionChanged.handle(e);
			});
		}

		private void delete()
		{
			SystemUtils.deleteFile(new File(
					TeachersTestsControlPart.instance.getDataPath() + "/testing/testSettings/" + getText() + ".data"));
		}

		private void update()
		{
			indicateAllAnswersQualityCheckbox.setDisable(!indicateLastAnswerQualityRadiobutton.isSelected());
			showRightAnswerCheckbox.setDisable(!indicateLastAnswerQualityRadiobutton.isSelected());
			pauseOnUnfocusCheckbox.setDisable(!pauseCheckbox.isSelected());
			if (!defaultRadiobutton.isSelected() && !indicateLastAnswerQualityRadiobutton.isSelected()
					&& !goToAllQuestionsRadiobutton.isSelected())
				defaultRadiobutton.setSelected(true);

			save();
		}

		private void save()
		{
			SystemUtils.writeFile(new File(
					TeachersTestsControlPart.instance.getDataPath() + "/testing/testSettings/" + getText() + ".data"),
					ByteUtils.objectToByteArray(getTestSettings()));
		}

		public double resize(double w)
		{
			double xoffset = 20;
			double dst = 5;
			double height = 20, offset = height + dst;

			int y = 0;

			defaultRadiobutton.setLayoutX(0);
			defaultRadiobutton.setLayoutY(y += dst);
			defaultRadiobutton.setPrefSize(w, height);

			indicateLastAnswerQualityRadiobutton.setLayoutX(0);
			indicateLastAnswerQualityRadiobutton.setLayoutY(y += offset);
			indicateLastAnswerQualityRadiobutton.setPrefSize(w, height);

			indicateAllAnswersQualityCheckbox.setLayoutX(xoffset);
			indicateAllAnswersQualityCheckbox.setLayoutY(y += offset);
			indicateAllAnswersQualityCheckbox.setPrefSize(w - xoffset, height);

			showRightAnswerCheckbox.setLayoutX(xoffset);
			showRightAnswerCheckbox.setLayoutY(y += offset);
			showRightAnswerCheckbox.setPrefSize(w - xoffset, height);

			goToAllQuestionsRadiobutton.setLayoutX(0);
			goToAllQuestionsRadiobutton.setLayoutY(y += offset);
			goToAllQuestionsRadiobutton.setPrefSize(w, height);

			skipCheckbox.setLayoutX(0);
			skipCheckbox.setLayoutY(y += offset);
			skipCheckbox.setPrefSize(w, height);

			pauseCheckbox.setLayoutX(0);
			pauseCheckbox.setLayoutY(y += offset);
			pauseCheckbox.setPrefSize(w, height);

			pauseOnUnfocusCheckbox.setLayoutX(xoffset);
			pauseOnUnfocusCheckbox.setLayoutY(y += offset);
			pauseOnUnfocusCheckbox.setPrefSize(w - xoffset, height);

			anticopyCheckbox.setLayoutX(0);
			anticopyCheckbox.setLayoutY(y += offset);
			anticopyCheckbox.setPrefSize(w, height);

			antiscreenshotCheckbox.setLayoutX(0);
			antiscreenshotCheckbox.setLayoutY(y += offset);
			antiscreenshotCheckbox.setPrefSize(w, height);

			y += offset;

			pane.setPrefSize(w, y);
			return y;
		}

		public TestSettings getTestSettings()
		{
			return new TestSettings(indicateLastAnswerQualityRadiobutton.isSelected(),
					indicateAllAnswersQualityCheckbox.isSelected(), showRightAnswerCheckbox.isSelected(),
					goToAllQuestionsRadiobutton.isSelected(), skipCheckbox.isSelected(), pauseCheckbox.isSelected(),
					pauseOnUnfocusCheckbox.isSelected(), anticopyCheckbox.isSelected(),
					antiscreenshotCheckbox.isSelected());
		}

		public void updateLabels()
		{
			defaultRadiobutton.setText(msgSys.getMsg("default"));
			indicateLastAnswerQualityRadiobutton.setText(msgSys.getMsg("indicateLastAnswerQuality"));
			indicateAllAnswersQualityCheckbox.setText(msgSys.getMsg("indicateAllAnswersQuality"));
			showRightAnswerCheckbox.setText(msgSys.getMsg("showRightAnswer"));
			goToAllQuestionsRadiobutton.setText(msgSys.getMsg("goToAllQuestions"));
			pauseCheckbox.setText(msgSys.getMsg("pause"));
			pauseOnUnfocusCheckbox.setText(msgSys.getMsg("pauseOnUnfocus"));
			skipCheckbox.setText(msgSys.getMsg("skip"));
			anticopyCheckbox.setText(msgSys.getMsg("anticopy"));
			antiscreenshotCheckbox.setText(msgSys.getMsg("antiscreenshot"));
		}

		public void rename(String text)
		{
			delete();
			setText(text);
			save();
		}
	}

	private final Tab testingPartSettingsTab;
	private final Pane testingPartSettingsPane;
	private final TabPane testingPartSettingsTabpane;
	private final TextField testingPartSettingsTabRenameTextfield;
	private boolean testingPartSettingsTabAddMode;

	static final class TestingPartSettingsKitTab extends SettingsKitTab
	{
		private final Pane pane;
		private final CheckBox fillAllHeightOfQuestionsPanelCheckbox;
		private final CheckBox maximazeHeightOfQuestionsPanelElementsCheckbox;
		private final CheckBox fillAllHeightOfAnswersPanelCheckbox;
		private final CheckBox maximazeHeightOfAnswersPanelElementsCheckbox;

		public TestingPartSettingsKitTab(String name, TestingPartSettings testingPartSettings)
		{
			super(name);
			setContent(pane = new Pane());
			pane.getChildren().add(fillAllHeightOfQuestionsPanelCheckbox = new CheckBox());
			pane.getChildren().add(maximazeHeightOfQuestionsPanelElementsCheckbox = new CheckBox());
			pane.getChildren().add(fillAllHeightOfAnswersPanelCheckbox = new CheckBox());
			pane.getChildren().add(maximazeHeightOfAnswersPanelElementsCheckbox = new CheckBox());

			if (testingPartSettings != null)
			{
				fillAllHeightOfQuestionsPanelCheckbox
						.setSelected(testingPartSettings.isFillAllHeightOfQuestionsPanel());
				maximazeHeightOfQuestionsPanelElementsCheckbox
						.setSelected(testingPartSettings.isMaximazeHeightOfQuestionsPanelElements());
				fillAllHeightOfAnswersPanelCheckbox.setSelected(testingPartSettings.isFillAllHeightOfAnswersPanel());
				maximazeHeightOfAnswersPanelElementsCheckbox
						.setSelected(testingPartSettings.isMaximazeHeightOfAnswersPanelElements());
			}

			createActionHandlers();
			update();
			updateLabels();

			TeachersTestsControlPart.instance.resize();
		}

		public TestingPartSettingsKitTab(String name)
		{
			this(name, null);
		}

		private void createActionHandlers()
		{
			fillAllHeightOfQuestionsPanelCheckbox.setOnAction(e -> update());
			maximazeHeightOfQuestionsPanelElementsCheckbox.setOnAction(e -> update());
			fillAllHeightOfAnswersPanelCheckbox.setOnAction(e -> update());
			maximazeHeightOfAnswersPanelElementsCheckbox.setOnAction(e -> update());

			setOnClosed(e -> delete());
			setOnSelectionChanged(e ->
			{
				if (isSelected())
					TeachersTestsControlPart.instance.selectedTestingPartSettingsKitTab = this;
				for (EventHandler<Event> onSelectionChanged : onSelectionChangedHandlers)
					onSelectionChanged.handle(e);
			});
		}

		private void delete()
		{
			SystemUtils.readFile(new File(TeachersTestsControlPart.instance.getDataPath()
					+ "/testing/testingPartSettings/" + getText() + ".data"));
		}

		private void update()
		{
			maximazeHeightOfQuestionsPanelElementsCheckbox
					.setDisable(!fillAllHeightOfQuestionsPanelCheckbox.isSelected());
			maximazeHeightOfAnswersPanelElementsCheckbox.setDisable(!fillAllHeightOfAnswersPanelCheckbox.isSelected());

			save();
		}

		private void save()
		{
			SystemUtils.writeFile(new File(TeachersTestsControlPart.instance.getDataPath()
					+ "/testing/testingPartSettings/" + getText() + ".data"),
					ByteUtils.objectToByteArray(getTestingPartSettings()));
		}

		public void rename(String text)
		{
			delete();
			setText(text);
			save();
		}

		public double resize(double w)
		{
			double xoffset = 20;
			double dst = 5;
			double height = 20, offset = height + dst;

			int y = 0;

			fillAllHeightOfQuestionsPanelCheckbox.setLayoutX(0);
			fillAllHeightOfQuestionsPanelCheckbox.setLayoutY(y += dst);
			fillAllHeightOfQuestionsPanelCheckbox.setPrefSize(w, height);

			maximazeHeightOfQuestionsPanelElementsCheckbox.setLayoutX(xoffset);
			maximazeHeightOfQuestionsPanelElementsCheckbox.setLayoutY(y += offset);
			maximazeHeightOfQuestionsPanelElementsCheckbox.setPrefSize(w - xoffset, height);

			fillAllHeightOfAnswersPanelCheckbox.setLayoutX(0);
			fillAllHeightOfAnswersPanelCheckbox.setLayoutY(y += offset);
			fillAllHeightOfAnswersPanelCheckbox.setPrefSize(w, height);

			maximazeHeightOfAnswersPanelElementsCheckbox.setLayoutX(xoffset);
			maximazeHeightOfAnswersPanelElementsCheckbox.setLayoutY(y += offset);
			maximazeHeightOfAnswersPanelElementsCheckbox.setPrefSize(w - xoffset, height);

			y += offset;

			pane.setPrefSize(w, y);
			return y;
		}

		public TestingPartSettings getTestingPartSettings()
		{
			return new TestingPartSettings(fillAllHeightOfQuestionsPanelCheckbox.isSelected(),
					maximazeHeightOfQuestionsPanelElementsCheckbox.isSelected(),
					fillAllHeightOfAnswersPanelCheckbox.isSelected(),
					maximazeHeightOfAnswersPanelElementsCheckbox.isSelected());
		}

		public void updateLabels()
		{
			fillAllHeightOfQuestionsPanelCheckbox.setText(msgSys.getMsg("fillAllHeightOfQuestionsPanel"));
			maximazeHeightOfQuestionsPanelElementsCheckbox
					.setText(msgSys.getMsg("maximazeHeightOfQuestionsPanelElements"));
			fillAllHeightOfAnswersPanelCheckbox.setText(msgSys.getMsg("fillAllHeightOfAnswersPanel"));
			maximazeHeightOfAnswersPanelElementsCheckbox.setText(msgSys.getMsg("maximazeHeightOfAnswersPanelElements"));
		}
	}

	private final TabPane networkAndInComputerTabpane;
	private final Tab networkTab;
	private final Pane networkPane;
	private final Button sendTestButton;
	private final Label onlineStudentsLabel;
	private final ScrollPane onlineStudentsScroll;
	private final VBox onlineStudentsPane;
	private final Label offlineStudentsLabel;
	private final ScrollPane offlineStudentsScroll;
	private final VBox offlineStudentsPane;
	private final Label addStudentLabel;
	private final TextField addStudentField;

	private final Tab inComputerTab;
	private final Pane inComputerPane;
	private final ComboBox<String> classNumberCombobox1;
	private final Label classNumberLabel1, classLetterLabel1, surnameLabel1, nameLabel1, secondNameLabel1;
	private final ComboboxWithAdd classLetterCombobox, surnameCombobox, nameCombobox, secondNameCombobox;
	private final Button startTestButton;

	private final Pane resultsPane;
	private final ComboBox<String> inComputerOrNetworkCombobox;
	private final ComboBox<String> classNumberCombobox2;
	private final Label inComputerOrNetworkLabel, loginLabel2, classNumberLabel2, classLetterLabel2, surnameLabel2,
			nameLabel2, secondNameLabel2;
	private final TextField loginTextfield, classLetterTextfield, surnameTextfield, nameTextfield, secondNameTextfield;
	private final CheckBox loginCheckbox, classNumberCheckbox, classLetterCheckbox, surnameCheckbox, nameCheckbox,
			secondNameCheckbox;
	private final Label testNameLabel2;
	private final ComboBox<String> testNameCombobox2;
	private final CheckBox testNameCheckbox;

	private final TabPane statsAndResultsViewTabpane;

	private final Tab statsTab;
	private final Pane statsPane;
	private final CheckBox inPercentsCheckbox;
	private final SignsAfterCommaCombobox signsAfterCommaCombobox;
	private final Button getStatsButton;
	private final ScrollPane statsTableScroll;
	private final GridPane statsTable;

	private final Tab resultsViewTab;
	private final Pane resultsViewPane;
	private final Button getResultsButton;
	private final ScrollPane resultsViewScroll;
	private final VBox resultsViewResultsPane;

	public TeachersTestsControlPart()
	{
		super(null, Main.createPane(500, 610), 1, 2, true, true);
		instance = this;
		// Init
		panel.getChildren()
				.add(testingAndResultsTabpane = new TabPane(testingTab = new Tab("testing", testingPane = new Pane()),
						resultsTab = new Tab("results", resultsPane = new Pane())));
		{
			testingPane.getChildren().add(testNameLabel1 = new Label());
			testingPane.getChildren().add(testNameCombobox1 = new ComboBox<String>());

			testingPane.getChildren()
					.add(testSettingsAndTestingPartSettingsTabpane = new TabPane(
							testSettingsTab = new Tab("",
									testSettingsPane = new Pane(testSettingsTabpane = new TabPane(),
											testSettingsTabRenameTextfield = new TextField())),
							testingPartSettingsTab = new Tab("",
									testingPartSettingsPane = new Pane(testingPartSettingsTabpane = new TabPane(),
											testingPartSettingsTabRenameTextfield = new TextField()))));

			testingPane.getChildren()
					.add(networkAndInComputerTabpane = new TabPane(
							inComputerTab = new Tab("", inComputerPane = new Pane()),
							networkTab = new Tab("", networkPane = new Pane())));
			{
				inComputerPane.getChildren().add(classNumberLabel1 = new Label());
				inComputerPane.getChildren().add(classLetterLabel1 = new Label());
				inComputerPane.getChildren().add(surnameLabel1 = new Label());
				inComputerPane.getChildren().add(nameLabel1 = new Label());
				inComputerPane.getChildren().add(secondNameLabel1 = new Label());

				inComputerPane.getChildren().add(classNumberCombobox1 = new ComboBox<String>());
				inComputerPane.getChildren().add(classLetterCombobox = new ComboboxWithAdd());
				inComputerPane.getChildren().add(surnameCombobox = new ComboboxWithAdd());
				inComputerPane.getChildren().add(nameCombobox = new ComboboxWithAdd());
				inComputerPane.getChildren().add(secondNameCombobox = new ComboboxWithAdd());

				inComputerPane.getChildren().add(startTestButton = new Button());
			}
			{
				networkPane.getChildren().add(sendTestButton = new Button());
				networkPane.getChildren().add(onlineStudentsLabel = new Label());
				networkPane.getChildren().add(onlineStudentsScroll = new ScrollPane(onlineStudentsPane = new VBox()));
				networkPane.getChildren().add(offlineStudentsLabel = new Label());
				networkPane.getChildren().add(offlineStudentsScroll = new ScrollPane(offlineStudentsPane = new VBox()));
				networkPane.getChildren().add(addStudentLabel = new Label());
				networkPane.getChildren().add(addStudentField = new TextField());
			}
		}

		{
			resultsPane.getChildren().addAll(testNameLabel2 = new Label(), testNameCombobox2 = new ComboBox<String>(),
					testNameCheckbox = new CheckBox());

			resultsPane.getChildren().add(inComputerOrNetworkLabel = new Label());
			resultsPane.getChildren().add(loginLabel2 = new Label());
			resultsPane.getChildren().add(classNumberLabel2 = new Label());
			resultsPane.getChildren().add(classLetterLabel2 = new Label());
			resultsPane.getChildren().add(surnameLabel2 = new Label());
			resultsPane.getChildren().add(nameLabel2 = new Label());
			resultsPane.getChildren().add(secondNameLabel2 = new Label());

			resultsPane.getChildren().add(inComputerOrNetworkCombobox = new ComboBox<String>());
			resultsPane.getChildren().add(loginTextfield = new TextField());
			resultsPane.getChildren().add(classNumberCombobox2 = new ComboBox<String>());
			resultsPane.getChildren().add(classLetterTextfield = new TextField());
			resultsPane.getChildren().add(surnameTextfield = new TextField());
			resultsPane.getChildren().add(nameTextfield = new TextField());
			resultsPane.getChildren().add(secondNameTextfield = new TextField());

			resultsPane.getChildren().add(loginCheckbox = new CheckBox());
			resultsPane.getChildren().add(classNumberCheckbox = new CheckBox());
			resultsPane.getChildren().add(classLetterCheckbox = new CheckBox());
			resultsPane.getChildren().add(surnameCheckbox = new CheckBox());
			resultsPane.getChildren().add(nameCheckbox = new CheckBox());
			resultsPane.getChildren().add(secondNameCheckbox = new CheckBox());

			resultsPane.getChildren()
					.add(statsAndResultsViewTabpane = new TabPane(statsTab = new Tab("", statsPane = new Pane()),
							resultsViewTab = new Tab("", resultsViewPane = new Pane())));
			{
				statsPane.getChildren().add(inPercentsCheckbox = new CheckBox());
				statsPane.getChildren().add(signsAfterCommaCombobox = new SignsAfterCommaCombobox());
				statsPane.getChildren().add(getStatsButton = new Button());
				statsPane.getChildren().add(statsTableScroll = new ScrollPane(statsTable = new GridPane()));
			}
			{
				resultsViewPane.getChildren().add(getResultsButton = new Button());
				resultsViewPane.getChildren()
						.add(resultsViewScroll = new ScrollPane(resultsViewResultsPane = new VBox(5)));
			}
		}

		// Init end

		// Setting
		testSettingsTabRenameTextfield.setVisible(false);
		testingPartSettingsTabRenameTextfield.setVisible(false);
		classNumberCombobox1.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
		classNumberCombobox2.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
		inComputerOrNetworkCombobox.getItems().addAll("All", "In computer", "Network");
		statsTable.setGridLinesVisible(true);

		testSettingsAndTestingPartSettingsTabpane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		testingAndResultsTabpane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		networkAndInComputerTabpane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		statsAndResultsViewTabpane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		inComputerOrNetworkCombobox.getSelectionModel().select(0);

		resultsViewScroll.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		resultsViewScroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

		setAligmentToCenter(testNameLabel1, classNumberLabel1, classLetterLabel1, surnameLabel1, nameLabel1,
				secondNameLabel1, onlineStudentsLabel, offlineStudentsLabel, addStudentLabel, inComputerOrNetworkLabel,
				loginLabel2, classNumberLabel2, classLetterLabel2, surnameLabel2, nameLabel2, secondNameLabel2,
				testNameLabel2, testNameLabel2);
		// Setting end
		{
			stats.put(new Label(),
					new Entry<String, ToValue<Double>>("min", (array, first, second) -> MathUtils.min(array)));

			stats.put(new Label(),
					new Entry<String, ToValue<Double>>("max", (array, first, second) -> MathUtils.max(array)));

			stats.put(new Label(), new Entry<String, ToValue<Double>>("minPossible", (array, first, second) -> first));

			stats.put(new Label(), new Entry<String, ToValue<Double>>("maxPossible", (array, first, second) -> second));

			stats.put(new Label(), new Entry<String, ToValue<Double>>("average", (array, first, second) ->
			{
				Double sum = 0d;
				for (Double i : array)
					sum += i;
				return array.length == 0 ? 0 : sum / array.length;
			}));

			stats.put(new Label(), new Entry<String, ToValue<Double>>("median", (array, first, second) ->
			{
				Double[] sorted = new TreeSet<Double>(Arrays.asList(array)).toArray(new Double[0]);
				return array.length == 0 ? 0
						: (array.length % 2 == 1 ? sorted[array.length / 2]
								: (sorted[array.length / 2 - 1] + sorted[array.length / 2]) / 2);
			}));

			for (String s : propsToStatsArray)
				propsToStats.put(new Label(s), s);
		}
		statsTableCells = new TextField[stats.size()][propsToStats.size()];
		{
			int i = 0;
			for (Label label1 : stats.keySet())
			{
				statsTable.add(label1, i + 1, 0);
				int j = 0;
				for (Label label2 : propsToStats.keySet())
				{
					if (i == 0)
						statsTable.add(label2, 0, j + 1);
					TextField tf = new TextField();
					tf.setPrefSize(30, 30);
					tf.setEditable(false);
					statsTable.add(tf, i + 1, j + 1);
					statsTableCells[i][j] = tf;
					j++;
				}
				i++;
			}
			setAligmentToCenter(stats.keySet());
			setAligmentToCenter(propsToStats.keySet());
			for (TextField[] fields : statsTableCells)
				setAligmentToCenter(fields);
		}

		createActionHandlers();
		update();

		new Timer(1000, e ->
		{
			Platform.runLater(() ->
			{
				ArrayList<ToggleButton> btns = new ArrayList<ToggleButton>();
				for (Node n : onlineStudentsPane.getChildren())
					btns.add((ToggleButton) n);
				for (Node n : offlineStudentsPane.getChildren())
					btns.add((ToggleButton) n);
				new Thread(() ->
				{
					for (ToggleButton btn : btns)
						Main.sendToServer(new StudentOnlineTestPacket("test", btn.getText()));
				}).start();
			});
		}).start();
	}

	static interface ArrayToValue<Type>
	{
		public Type transform(Type[] array);
	}

	static interface ToValue<Type>
	{
		public Type transform(Type[] array, Type first, Type second);
	}

	HashMap<Label, Entry<String, ToValue<Double>>> stats = new HashMap<Label, Entry<String, ToValue<Double>>>();
	HashMap<Label, String> propsToStats = new HashMap<Label, String>();
	TextField[][] statsTableCells;

	TestSettingsKitTab selectedTestSettingsKitTab;
	TestingPartSettingsKitTab selectedTestingPartSettingsKitTab;

	@Override
	protected void createActionHandlers()
	{
		super.createActionHandlers();

		sendTestButton.setOnAction(e ->
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

			for (Node n : onlineStudentsPane.getChildren())
				if (n instanceof ToggleButton)
					if (((ToggleButton) n).isSelected())
						Main.sendToServer(new TestingTaskPacket("giveTask", ((ToggleButton) n).getText(),
								new TestingTask(TaskType.Homework,
										new TesterInfo(AccountsPart.account.get().getLogin(),
												AccountsPart.account.get().getSurname(),
												AccountsPart.account.get().getName(),
												AccountsPart.account.get().getSecondName()),
										new TestToMarket(test.getProgramVersion(), test.getTestVersion(),
												test.getTestCreationDate(), test.getTestLanguage(),
												test.getTestSubject(), test.getAuthors(), test.getName(),
												test.getDescription(), file.getName(), files2),
										selectedTestSettingsKitTab.getTestSettings(),
										selectedTestingPartSettingsKitTab.getTestingPartSettings())));

		});
		getStatsButton.setOnAction(e ->getStats());
		getResultsButton.setOnAction(e ->getResults());
		addStudentField.setOnAction(e ->
		{
			Main.sendToServer(new StudentOnlineTestPacket("test", addStudentField.getText()));
			addStudentField.setText("");
		});
		testNameCheckbox.setOnAction(e -> update());
		loginCheckbox.setOnAction(e -> update());
		classNumberCheckbox.setOnAction(e -> update());
		classLetterCheckbox.setOnAction(e -> update());
		surnameCheckbox.setOnAction(e -> update());
		nameCheckbox.setOnAction(e -> update());
		secondNameCheckbox.setOnAction(e -> update());

		{
			MenuItem rename = new MenuItem("Rename"), add = new MenuItem("Add");
			add.setOnAction(e ->
			{
				testSettingsTabRenameTextfield.setVisible(true);
				testSettingsTabRenameTextfield.setText("");
				testSettingsTabAddMode = true;
			});
			rename.setOnAction(e ->
			{
				testSettingsTabRenameTextfield.setVisible(true);
				testSettingsTabRenameTextfield
						.setText(testSettingsTabpane.getSelectionModel().getSelectedItem().getText());
				testSettingsTabAddMode = false;
			});
			testSettingsTabRenameTextfield.setOnAction(e ->
			{
				boolean exists = false;
				for (Tab tab : testSettingsTabpane.getTabs())
					if (tab.getText().equals(testSettingsTabRenameTextfield.getText()))
						exists = true;
				if (!exists)
				{
					if (testSettingsTabAddMode)
						testSettingsTabpane.getTabs()
								.add(new TestSettingsKitTab(testSettingsTabRenameTextfield.getText()));
					else((TestSettingsKitTab) testSettingsTabpane.getSelectionModel().getSelectedItem())
							.rename(testSettingsTabRenameTextfield.getText());

					testSettingsTabRenameTextfield.setVisible(false);
				}
			});
			ContextMenu menu = new ContextMenu(rename, add);
			testSettingsTabpane.setOnMouseClicked(e ->
			{
				if (e.getButton() == MouseButton.SECONDARY && e.getY() <= Main.tabsSwitchHeight)
					menu.show(testSettingsTabpane, e.getScreenX(), e.getScreenY());
				else menu.hide();
			});
		}

		{
			MenuItem rename = new MenuItem("Rename"), add = new MenuItem("Add");
			add.setOnAction(e ->
			{
				testingPartSettingsTabRenameTextfield.setVisible(true);
				testingPartSettingsTabRenameTextfield.setText("");
				testingPartSettingsTabAddMode = true;
			});
			rename.setOnAction(e ->
			{
				testingPartSettingsTabRenameTextfield.setVisible(true);
				testingPartSettingsTabRenameTextfield
						.setText(testingPartSettingsTabpane.getSelectionModel().getSelectedItem().getText());
				testingPartSettingsTabAddMode = false;
			});
			testingPartSettingsTabRenameTextfield.setOnAction(e ->
			{
				boolean exists = false;
				for (Tab tab : testingPartSettingsTabpane.getTabs())
					if (tab.getText().equals(testingPartSettingsTabRenameTextfield.getText()))
						exists = true;
				if (!exists)
				{
					if (testingPartSettingsTabAddMode)
						testingPartSettingsTabpane.getTabs()
								.add(new TestSettingsKitTab(testingPartSettingsTabRenameTextfield.getText()));
					else((TestingPartSettingsKitTab) testingPartSettingsTabpane.getSelectionModel().getSelectedItem())
							.rename(testingPartSettingsTabRenameTextfield.getText());

					testingPartSettingsTabRenameTextfield.setVisible(false);
				}
			});
			ContextMenu menu = new ContextMenu(rename, add);
			testingPartSettingsTabpane.setOnMouseClicked(e ->
			{
				if (e.getButton() == MouseButton.SECONDARY && e.getY() <= Main.tabsSwitchHeight)
					menu.show(testingPartSettingsTabpane, e.getScreenX(), e.getScreenY());
				else menu.hide();
			});
		}

		startTestButton.setOnAction(e ->
		{
			// checkPermissions.handle(e);
			if (classNumberCombobox1.getSelectionModel().getSelectedItem() == null
					|| classLetterCombobox.getSelectionModel().getSelectedItem() == null
					|| classNumberCombobox1.getSelectionModel().getSelectedItem().equals("")
					|| classLetterCombobox.getSelectionModel().getSelectedItem().equals(""))
				FXDialogsGenerator.showFXDialog(stage, msgSys.getMsg("classMustBeSelected"), null, true);
			else if (surnameCombobox.getSelectionModel().getSelectedItem() == null
					|| "".equals(surnameCombobox.getSelectionModel().getSelectedItem()))
				FXDialogsGenerator.showFXDialog(stage, msgSys.getMsg("surnameMustBeSelected"), null, true);
			else if (nameCombobox.getSelectionModel().getSelectedItem() == null
					|| "".equals(nameCombobox.getSelectionModel().getSelectedItem()))
				FXDialogsGenerator.showFXDialog(stage, msgSys.getMsg("nameMustBeSelected"), null, true);
			else if (secondNameCombobox.getSelectionModel().getSelectedItem() == null
					|| "".equals(secondNameCombobox.getSelectionModel().getSelectedItem()))
				FXDialogsGenerator.showFXDialog(stage, msgSys.getMsg("secondNameMustBeSelected"), null, true);
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
				TestingPart.instance.createNewTest(theme, test, testFiles.get(test).getName(),
						new TesteeInfo(null, classNumberCombobox1.getSelectionModel().getSelectedItem(),
								classLetterCombobox.getSelectionModel().getSelectedItem(),
								surnameCombobox.getSelectionModel().getSelectedItem(),
								nameCombobox.getSelectionModel().getSelectedItem(),
								secondNameCombobox.getSelectionModel().getSelectedItem()),
						new TesterInfo(AccountsPart.account.get().getLogin(),
								AccountsPart.account.get().getSurname(), AccountsPart.account.get().getName(),
								AccountsPart.account.get().getSecondName()),
						selectedTestSettingsKitTab.getTestSettings(),
						selectedTestingPartSettingsKitTab.getTestingPartSettings(), new Rectangle((int) stage.getX(),
								(int) stage.getY(), (int) stage.getWidth(), (int) stage.getHeight()),
						true);
				// try
				// {
				// studentsTestingPartFX.open(new Rectangle((int) stage.getX(), (int)
				// stage.getY(),
				// (int) stage.getWidth(), (int) stage.getHeight()),
				// AccountsPart.account.getValue(),
				// Main.client);
				// }
				// catch (Exception e1)
				// {
				// FXDialogsGenerator.showFXDialog(stage, msgSys.getMsg("signInToWork"), null,
				// true);
				// }
				Main.instance.hideAll();
			}
		});
		testingPartSettingsTabpane.getTabs().addListener(new ListChangeListener<Tab>()
		{
			@Override
			public void onChanged(Change<? extends Tab> change)
			{
				testingPartSettingsTabpane.setTabClosingPolicy(
						change.getList().size() == 1 ? TabClosingPolicy.UNAVAILABLE : TabClosingPolicy.SELECTED_TAB);
			}
		});
		testSettingsTabpane.getTabs().addListener(new ListChangeListener<Tab>()
		{
			@Override
			public void onChanged(Change<? extends Tab> change)
			{
				testSettingsTabpane.setTabClosingPolicy(
						change.getList().size() == 1 ? TabClosingPolicy.UNAVAILABLE : TabClosingPolicy.SELECTED_TAB);
			}
		});

		testingTab.setOnSelectionChanged(e ->
		{
			if (testingTab.isSelected())
			{
				testNameCombobox1.setOnAction(null);
				classNumberCombobox1.setOnAction(null);
				classLetterCombobox.setOnAction(null);
				surnameCombobox.setOnAction(null);
				nameCombobox.setOnAction(null);
				secondNameCombobox.setOnAction(null);
				for (Tab t : testSettingsTabpane.getTabs())
					if (t instanceof SettingsKitTab)
						((SettingsKitTab) t).clearOnSelectionChangedHandlers();
				for (Tab t : testingPartSettingsTabpane.getTabs())
					if (t instanceof SettingsKitTab)
						((SettingsKitTab) t).clearOnSelectionChangedHandlers();
				updateTests();

				{
					testSettingsTabpane.getTabs().clear();
					File[] files = new File(getDataPath() + "/testing/testSettings").listFiles();
					if (files != null)
						for (File file : files)
							if (file.isFile())
								if (file.getName().endsWith(".data"))
								{
									byte[] bytes = SystemUtils.readFile(file);
									Object obj;
									try
									{
										obj = bytes != null ? ByteUtils._byteArrayToObject(bytes) : null;
									}
									catch (Exception exc)
									{
										obj = null;
									}
									TestSettings type;
									try
									{
										type = obj != null ? (TestSettings) obj : null;
									}
									catch (Exception e2)
									{
										type = null;
									}
									testSettingsTabpane.getTabs().add(new TestSettingsKitTab(
											file.getName().substring(0, file.getName().length() - 5), type));
								}
					if (testSettingsTabpane.getTabs().isEmpty())
						testSettingsTabpane.getTabs().add(new TestSettingsKitTab("New test settings kit 2"));
				}
				createSelectedTabSaver(testSettingsTabpane, "testing/selectedTestSettings.data");

				{
					testingPartSettingsTabpane.getTabs().clear();
					File[] files = new File(getDataPath() + "/testing/testingPartSettings").listFiles();
					if (files != null)
						for (File file : files)
							if (file.isFile())
								if (file.getName().endsWith(".data"))
								{
									byte[] bytes = SystemUtils.readFile(file);
									Object obj;
									try
									{
										obj = bytes != null ? ByteUtils._byteArrayToObject(bytes) : null;
									}
									catch (Exception exc)
									{
										obj = null;
									}
									TestingPartSettings type;
									try
									{
										type = obj != null ? (TestingPartSettings) obj : null;
									}
									catch (Exception e2)
									{
										type = null;
									}
									testingPartSettingsTabpane.getTabs().add(new TestingPartSettingsKitTab(
											file.getName().substring(0, file.getName().length() - 5), type));
								}
					if (testingPartSettingsTabpane.getTabs().isEmpty())
						testingPartSettingsTabpane.getTabs()
								.add(new TestingPartSettingsKitTab("New testing part settings kit 2"));
				}
				createSelectedTabSaver(testingPartSettingsTabpane, "testing/selectedTestingPartSettings.data");

				createComboboxValueSaver(testNameCombobox1, "testing/testName.data", null);
				{
					createComboboxValueSaver(classNumberCombobox1, "testing/inComputer/classNumber.data", ev ->
					{
						classLetterCombobox.getItems().clear();
						File[] files = new File(getDataPath() + "/testing/inComputer/students/"
								+ classNumberCombobox1.getSelectionModel().getSelectedItem()).listFiles();
						if (files != null)
							for (File file : files)
								if (file.isDirectory())
									classLetterCombobox.getItems().add(file.getName());
						classLetterCombobox.getSelectionModel().select(0);
						if (classLetterCombobox.getCombobox().getOnAction() != null)
							classLetterCombobox.getCombobox().getOnAction().handle(new ActionEvent());
					});
					createComboboxValueSaver(classLetterCombobox.getCombobox(), "testing/inComputer/classLetter.data",
							ev ->
							{
								{
									surnameCombobox.getItems().clear();
									File[] files = new File(getDataPath() + "/testing/inComputer/students/"
											+ classNumberCombobox1.getSelectionModel().getSelectedItem() + "/"
											+ classLetterCombobox.getSelectionModel().getSelectedItem() + "/"
											+ "surnames").listFiles();
									if (files != null)
										for (File file : files)
											if (file.isDirectory())
												surnameCombobox.getItems().add(file.getName());
									surnameCombobox.getSelectionModel().select(0);
									if (surnameCombobox.getCombobox().getOnAction() != null)
										surnameCombobox.getCombobox().getOnAction().handle(new ActionEvent());
								}
								{
									nameCombobox.getItems().clear();
									File[] files = new File(getDataPath() + "/testing/inComputer/students/"
											+ classNumberCombobox1.getSelectionModel().getSelectedItem() + "/"
											+ classLetterCombobox.getSelectionModel().getSelectedItem() + "/" + "names")
													.listFiles();
									if (files != null)
										for (File file : files)
											if (file.isDirectory())
												nameCombobox.getItems().add(file.getName());
									nameCombobox.getSelectionModel().select(0);
									if (nameCombobox.getCombobox().getOnAction() != null)
										nameCombobox.getCombobox().getOnAction().handle(new ActionEvent());
								}
								{
									secondNameCombobox.getItems().clear();
									File[] files = new File(getDataPath() + "/testing/inComputer/students/"
											+ classNumberCombobox1.getSelectionModel().getSelectedItem() + "/"
											+ classLetterCombobox.getSelectionModel().getSelectedItem() + "/"
											+ "secondNames").listFiles();
									if (files != null)
										for (File file : files)
											if (file.isDirectory())
												secondNameCombobox.getItems().add(file.getName());
									secondNameCombobox.getSelectionModel().select(0);
									if (secondNameCombobox.getCombobox().getOnAction() != null)
										secondNameCombobox.getCombobox().getOnAction().handle(new ActionEvent());
								}
							});
					createComboboxItemsSaver(classLetterCombobox, "testing/inComputer/students/"
							+ classNumberCombobox1.getSelectionModel().getSelectedItem());
					createComboboxValueSaver(surnameCombobox.getCombobox(), "testing/inComputer/surname.data", null);
					createComboboxItemsSaver(surnameCombobox,
							"testing/inComputer/students/" + classNumberCombobox1.getSelectionModel().getSelectedItem()
									+ "/" + classLetterCombobox.getSelectionModel().getSelectedItem() + "/surnames");
					createComboboxValueSaver(nameCombobox.getCombobox(), "testing/inComputer/name.data", null);
					createComboboxItemsSaver(nameCombobox,
							"testing/inComputer/students/" + classNumberCombobox1.getSelectionModel().getSelectedItem()
									+ "/" + classLetterCombobox.getSelectionModel().getSelectedItem() + "/names");
					createComboboxValueSaver(secondNameCombobox.getCombobox(), "testing/inComputer/secondName.data",
							null);
					createComboboxItemsSaver(secondNameCombobox,
							"testing/inComputer/students/" + classNumberCombobox1.getSelectionModel().getSelectedItem()
									+ "/" + classLetterCombobox.getSelectionModel().getSelectedItem() + "/secondNames");
				}
				{
				}
				resize();
			}
		});
		resultsTab.setOnSelectionChanged(e ->
		{
			if (resultsTab.isSelected())
			{
				createComboboxValueSaver(classNumberCombobox2, "results/classNumber.data", null);
				createComboboxValueSaver(testNameCombobox2, "results/stats/testName.data", null);
			}
		});
		addShowListener(e -> testingTab.getOnSelectionChanged().handle(null));
	}

	private void getStats()
	{
		ArrayList<TestResult> results = loadResults();
		int j = 0;
		for (Label label2 : propsToStats.keySet())
		{
			Double[] array = new Double[results.size()];
			for (int i = 0; i < results.size(); i++)
				array[i] = results.get(i).getNumberProp(propsToStats.get(label2))[1];
			Double first = results.get(0).getNumberProp(propsToStats.get(label2))[0];
			Double second = results.get(0).getNumberProp(propsToStats.get(label2))[2];
			int i = 0;
			for (Label label1 : stats.keySet())
			{
				double d = stats.get(label1).getSecond().transform(array, first, second);
				statsTableCells[i][j].setText((inPercentsCheckbox.isSelected()
						? signsAfterCommaCombobox.doubleToString(d / second * 100) + "%"
						: signsAfterCommaCombobox.doubleToString(d)));
				i++;
			}
			j++;
		}
	}

	private void getResults()
	{
		ArrayList<TestResult> testResults = loadResults();
		TextField[][] fields = new TextField[testResults.size()][4];
		int i = 0;
		resultsViewFieldsWidths = new int[4];
		for (TestResult result : testResults)
		{
			Button showFull = new Button("...");
			showFull.setOnAction(ev -> result.show(stage, null));
			resultsViewResultsPane.getChildren()
					.add(new HBox(5, fields[i][0] = createNETF(result.getDate().toString(), 0),
							fields[i][1] = createNETF(result.getTestName(), 1),
							fields[i][2] = createNETF(result.getTesteeInfo().toString(), 2),
							fields[i][3] = createNETF(result.getTesterInfo().toString(), 3), showFull));
			i++;
		}
		for (TextField[] textFields : fields)
		{
			int j = 0;
			for (TextField textField : textFields)
			{
				textField.setPrefWidth(resultsViewFieldsWidths[j]);
				j++;
			}
		}
	}

	int[] resultsViewFieldsWidths;

	private TextField createNETF(String text, int num)
	{
		TextField field = new TextField(text);
		field.setPrefWidth(0);
		resultsViewFieldsWidths[num] = Math.max(resultsViewFieldsWidths[num],
				MathUtils.size(field.getText(), java.awt.Font.decode(field.getFont().getName()+"-"+field.getFont().getStyle()+"-"+field.getFont().getSize())).width + 16);
		field.setEditable(false);
		return field;
	}

	private ArrayList<TestResult> loadResults()
	{
		ArrayList<TestResult> results = new ArrayList<TestResult>();
		for (File resultsLocation : new File(TestingPart.instance.getDataPath() + "/results").listFiles())
			for (File testResults : resultsLocation.listFiles())
				for (File result : testResults.listFiles())
					results.add((TestResult) ByteUtils.byteArrayToObject(SystemUtils.readFile(result)));
		return results;
	}

	private void createSelectedTabSaver(TabPane tabpane, String path)
	{
		if (AccountsPart.account.get() == null)
			return;
		File file = new File(getDataPath() + "/" + path);

		byte[] bytes = SystemUtils.readFile(file);
		Object obj;
		try
		{
			obj = bytes != null ? ByteUtils._byteArrayToObject(bytes) : null;
		}
		catch (Exception e)
		{
			obj = null;
		}
		String type;
		try
		{
			type = obj != null ? (String) obj : null;
		}
		catch (Exception e)
		{
			type = null;
		}
		Tab tab = null;
		for (Tab t : tabpane.getTabs())
			if (t.getText().equals(type))
				tab = t;
		if (type != null && tab != null)
			tabpane.getSelectionModel().select(tab);
		else tabpane.getSelectionModel().select(0);

		Runnable r = () -> SystemUtils.writeFile(file,
				ByteUtils.objectToByteArray(tabpane.getSelectionModel().getSelectedItem().getText()));
		for (Tab t : tabpane.getTabs())
			if (t instanceof SettingsKitTab)
				((SettingsKitTab) t).addOnSelectionChangedHandler(e ->
				{
					if (t.isSelected())
						r.run();
				});
		r.run();
	}

	private void createComboboxItemsSaver(ComboboxWithAdd combobox, String path)
	{
		combobox.clearOnItemAdd();
		combobox.addOnItemAdd(
				e -> SystemUtils.createDir(new File(getDataPath() + "/" + path + "/" + e.getSource()), true, true));
		combobox.clearOnItemRemove();
		combobox.addOnItemRemove(
				e -> SystemUtils.deleteFile(new File(getDataPath() + "/" + path + "/" + e.getSource())));
	}

	private <T> void createComboboxValueSaver(ComboBox<T> combobox, String path,
			EventHandler<ActionEvent> actionHandler)
	{
		if (AccountsPart.account.get() == null)
			return;
		File file = new File(getDataPath() + "/" + path);

		byte[] bytes = SystemUtils.readFile(file);
		Object obj;
		try
		{
			obj = bytes != null ? ByteUtils._byteArrayToObject(bytes) : null;
		}
		catch (Exception e)
		{
			obj = null;
		}
		T type;
		try
		{
			type = obj != null ? (T) obj : null;
		}
		catch (Exception e)
		{
			type = null;
		}
		if (type != null ? combobox.getItems().contains(type) : false)
			combobox.getSelectionModel().select(type);
		else combobox.getSelectionModel().select(0);

		combobox.setOnAction(e ->
		{
			if (actionHandler != null)
				actionHandler.handle(e);
			SystemUtils.writeFile(file, ByteUtils.objectToByteArray(combobox.getSelectionModel().getSelectedItem()));
		});
		combobox.getOnAction().handle(new ActionEvent());
	}

	@Override
	protected void _resize(int w, int h)
	{
		testingAndResultsTabpane.setPrefSize(w, h);
		int h2 = h - Main.tabsSwitchHeight;
		double xoffset = 20;
		double dst = 5;
		double height = 20, offset = height + dst;
		double height2 = 30, offset2 = height2 + dst;
		double height3 = 25, offset3 = height3 + dst;

		// Resize testing part
		{
			testNameLabel1.setLayoutX(0);
			testNameLabel1.setLayoutY(0);
			testNameLabel1.setPrefSize(w / 2, height2);

			testNameCombobox1.setLayoutX(w / 2);
			testNameCombobox1.setLayoutY(0);
			testNameCombobox1.setPrefSize(w / 2, height2);

			double y = 0, y2 = 0, y3 = 0, y4 = 0;
			testSettingsAndTestingPartSettingsTabpane.setLayoutX(0);
			testSettingsAndTestingPartSettingsTabpane
					.setLayoutY(testNameCombobox1.getLayoutY() + testNameCombobox1.getPrefHeight());

			for (Tab tab : testSettingsTabpane.getTabs())
				if (tab instanceof TestSettingsKitTab)
					y = Math.max(y, ((TestSettingsKitTab) tab).resize(w));

			testSettingsPane.setLayoutX(0);
			testSettingsPane.setLayoutY(0);
			testSettingsPane.setPrefSize(w, Main.tabsSwitchHeight + y);

			testSettingsTabpane.setLayoutX(0);
			testSettingsTabpane.setLayoutY(0);
			testSettingsTabpane.setPrefSize(w, Main.tabsSwitchHeight + y);

			testSettingsTabRenameTextfield.setLayoutX(0);
			testSettingsTabRenameTextfield.setLayoutY(0);
			testSettingsTabRenameTextfield.setPrefSize(w, Main.tabsSwitchHeight);

			for (Tab tab : testingPartSettingsTabpane.getTabs())
				if (tab instanceof TestingPartSettingsKitTab)
					y2 = Math.max(y2, ((TestingPartSettingsKitTab) tab).resize(w));

			testingPartSettingsPane.setLayoutX(0);
			testingPartSettingsPane.setLayoutY(0);
			testingPartSettingsPane.setPrefSize(w, Main.tabsSwitchHeight + y2);

			testingPartSettingsTabpane.setLayoutX(0);
			testingPartSettingsTabpane.setLayoutY(0);
			testingPartSettingsTabpane.setPrefSize(w, Main.tabsSwitchHeight + y2);

			testingPartSettingsTabRenameTextfield.setLayoutX(0);
			testingPartSettingsTabRenameTextfield.setLayoutY(0);
			testingPartSettingsTabRenameTextfield.setPrefSize(w, Main.tabsSwitchHeight);

			testSettingsAndTestingPartSettingsTabpane.setPrefSize(w, Main.tabsSwitchHeight
					+ Math.max(testSettingsTabpane.getPrefHeight(), testingPartSettingsTabpane.getPrefHeight()));

			networkAndInComputerTabpane.setLayoutX(0);
			networkAndInComputerTabpane.setLayoutY(testSettingsAndTestingPartSettingsTabpane.getLayoutY()
					+ testSettingsAndTestingPartSettingsTabpane.getPrefHeight());
			networkAndInComputerTabpane.setPrefSize(w, h2 - networkAndInComputerTabpane.getLayoutY());
			{
				classNumberLabel1.setLayoutX(0);
				classNumberLabel1.setLayoutY(y3 += dst);
				classNumberLabel1.setPrefSize(w / 2, height2);

				classNumberCombobox1.setLayoutX(w / 2);
				classNumberCombobox1.setLayoutY(y3);
				classNumberCombobox1.setPrefSize(w / 2, height2);

				classLetterLabel1.setLayoutX(0);
				classLetterLabel1.setLayoutY(y3 += offset2);
				classLetterLabel1.setPrefSize(w / 2, height2);

				classLetterCombobox.setLayoutX(w / 2);
				classLetterCombobox.setLayoutY(y3);
				classLetterCombobox.setPrefSize(w / 2, height2);

				surnameLabel1.setLayoutX(0);
				surnameLabel1.setLayoutY(y3 += offset2);
				surnameLabel1.setPrefSize(w / 2, height2);

				surnameCombobox.setLayoutX(w / 2);
				surnameCombobox.setLayoutY(y3);
				surnameCombobox.setPrefSize(w / 2, height2);

				nameLabel1.setLayoutX(0);
				nameLabel1.setLayoutY(y3 += offset2);
				nameLabel1.setPrefSize(w / 2, height2);

				nameCombobox.setLayoutX(w / 2);
				nameCombobox.setLayoutY(y3);
				nameCombobox.setPrefSize(w / 2, height2);

				secondNameLabel1.setLayoutX(0);
				secondNameLabel1.setLayoutY(y3 += offset2);
				secondNameLabel1.setPrefSize(w / 2, height2);

				secondNameCombobox.setLayoutX(w / 2);
				secondNameCombobox.setLayoutY(y3);
				secondNameCombobox.setPrefSize(w / 2, height2);

				startTestButton.setLayoutY(y3 += offset2);
				centrizeByText(startTestButton, w, height3);

			}
			{
				sendTestButton.setLayoutY(y4 += dst);
				centrizeByText(sendTestButton, w, height3);

				onlineStudentsLabel.setLayoutX(0);
				onlineStudentsLabel.setLayoutY(y4 += offset);
				onlineStudentsLabel.setPrefSize(w / 2, height);

				offlineStudentsLabel.setLayoutX(w / 2);
				offlineStudentsLabel.setLayoutY(y4);
				offlineStudentsLabel.setPrefSize(w / 2, height);

				addStudentLabel.setLayoutX(0);
				addStudentLabel
						.setLayoutY(networkAndInComputerTabpane.getPrefHeight() - Main.tabsSwitchHeight - height2);
				addStudentLabel.setPrefSize(w / 2, height);

				addStudentField.setLayoutX(w / 2);
				addStudentField
						.setLayoutY(networkAndInComputerTabpane.getPrefHeight() - Main.tabsSwitchHeight - height2);
				addStudentField.setPrefSize(w / 2, height);

				onlineStudentsScroll.setLayoutX(0);
				onlineStudentsScroll.setLayoutY(y4 += offset);
				onlineStudentsScroll.setPrefSize(w / 2,
						addStudentLabel.getLayoutY() - onlineStudentsScroll.getLayoutY() - dst);

				offlineStudentsScroll.setLayoutX(w / 2);
				offlineStudentsScroll.setLayoutY(y4);
				offlineStudentsScroll.setPrefSize(w / 2,
						addStudentLabel.getLayoutY() - offlineStudentsScroll.getLayoutY() - dst);
			}
		}

		// Resize results part
		{
			double y = 0;

			inComputerOrNetworkLabel.setLayoutX(0);
			inComputerOrNetworkLabel.setLayoutY(y += dst);
			inComputerOrNetworkLabel.setPrefSize(w / 2, height2);

			inComputerOrNetworkCombobox.setLayoutX(w / 2);
			inComputerOrNetworkCombobox.setLayoutY(y);
			inComputerOrNetworkCombobox.setPrefSize(w / 2, height2);

			testNameLabel2.setLayoutX(0);
			testNameLabel2.setLayoutY(y += offset2);
			testNameLabel2.setPrefSize(w / 2, height2);

			testNameCombobox2.setLayoutX(w / 2);
			testNameCombobox2.setLayoutY(y);
			testNameCombobox2.setPrefSize(w / 2 - 16, height2);

			testNameCheckbox.setLayoutX(w - 16);
			testNameCheckbox.setLayoutY(y);
			testNameCheckbox.setPrefSize(16, height2);

			loginLabel2.setLayoutX(0);
			loginLabel2.setLayoutY(y += offset2);
			loginLabel2.setPrefSize(w / 2, height2);

			loginTextfield.setLayoutX(w / 2);
			loginTextfield.setLayoutY(y);
			loginTextfield.setPrefSize(w / 2 - 16, height2);

			loginCheckbox.setLayoutX(w - 16);
			loginCheckbox.setLayoutY(y);
			loginCheckbox.setPrefSize(16, height2);

			classNumberLabel2.setLayoutX(0);
			classNumberLabel2.setLayoutY(y += offset2);
			classNumberLabel2.setPrefSize(w / 2, height2);

			classNumberCombobox2.setLayoutX(w / 2);
			classNumberCombobox2.setLayoutY(y);
			classNumberCombobox2.setPrefSize(w / 2 - 16, height2);

			classNumberCheckbox.setLayoutX(w - 16);
			classNumberCheckbox.setLayoutY(y);
			classNumberCheckbox.setPrefSize(16, height2);

			classLetterLabel2.setLayoutX(0);
			classLetterLabel2.setLayoutY(y += offset2);
			classLetterLabel2.setPrefSize(w / 2, height2);

			classLetterTextfield.setLayoutX(w / 2);
			classLetterTextfield.setLayoutY(y);
			classLetterTextfield.setPrefSize(w / 2 - 16, height2);

			classLetterCheckbox.setLayoutX(w - 16);
			classLetterCheckbox.setLayoutY(y);
			classLetterCheckbox.setPrefSize(16, height2);

			surnameLabel2.setLayoutX(0);
			surnameLabel2.setLayoutY(y += offset2);
			surnameLabel2.setPrefSize(w / 2, height2);

			surnameTextfield.setLayoutX(w / 2);
			surnameTextfield.setLayoutY(y);
			surnameTextfield.setPrefSize(w / 2 - 16, height2);

			surnameCheckbox.setLayoutX(w - 16);
			surnameCheckbox.setLayoutY(y);
			surnameCheckbox.setPrefSize(16, height2);

			nameLabel2.setLayoutX(0);
			nameLabel2.setLayoutY(y += offset2);
			nameLabel2.setPrefSize(w / 2, height2);

			nameTextfield.setLayoutX(w / 2);
			nameTextfield.setLayoutY(y);
			nameTextfield.setPrefSize(w / 2 - 16, height2);

			nameCheckbox.setLayoutX(w - 16);
			nameCheckbox.setLayoutY(y);
			nameCheckbox.setPrefSize(16, height2);

			secondNameLabel2.setLayoutX(0);
			secondNameLabel2.setLayoutY(y += offset2);
			secondNameLabel2.setPrefSize(w / 2, height2);

			secondNameTextfield.setLayoutX(w / 2);
			secondNameTextfield.setLayoutY(y);
			secondNameTextfield.setPrefSize(w / 2 - 16, height2);

			secondNameCheckbox.setLayoutX(w - 16);
			secondNameCheckbox.setLayoutY(y);
			secondNameCheckbox.setPrefSize(16, height2);

			statsAndResultsViewTabpane.setLayoutX(0);
			statsAndResultsViewTabpane.setLayoutY(y += offset2);
			statsAndResultsViewTabpane.setPrefSize(w, h2 - y);
			{
				int y5 = 0;

				inPercentsCheckbox.setLayoutX(0);
				inPercentsCheckbox.setLayoutY(y5 += dst);
				inPercentsCheckbox.setPrefSize(w, height2);

				signsAfterCommaCombobox.setLayoutX(0);
				signsAfterCommaCombobox.setLayoutY(y5 += offset2);
				signsAfterCommaCombobox.setPrefSize(w, height2);

				getStatsButton.setLayoutY(y5 += offset2);
				centrizeByText(getStatsButton, w, height3);

				statsTable.setLayoutX(0);
				statsTable.setLayoutY(0);
				statsTable.setMinWidth(w - 2);
				statsTable.setAlignment(Pos.BASELINE_CENTER);

				statsTableScroll.setLayoutX(0);
				statsTableScroll.setLayoutY(y5 += offset3);
				statsTableScroll.setPrefSize(w,
						statsAndResultsViewTabpane.getPrefHeight() - Main.tabsSwitchHeight - y5);

				for (Label label : stats.keySet())
					label.setPrefSize(Math.max(height2, MathUtils.size(label.getText(), java.awt.Font.decode(label.getFont().getName()+"-"+label.getFont().getStyle()+"-"+label.getFont().getSize())).width + 10),
							height2);
				for (Label label : propsToStats.keySet())
					label.setPrefSize(Math.max(height2, MathUtils.size(label.getText(), java.awt.Font.decode(label.getFont().getName()+"-"+label.getFont().getStyle()+"-"+label.getFont().getSize())).width + 10),
							height2);
			}
			{
				int y6 = 0;

				getResultsButton.setLayoutY(y6 += dst);
				centrizeByText(getResultsButton, w, height3);

				resultsViewScroll.setLayoutX(0);
				resultsViewScroll.setLayoutY(y6 += offset3);
				resultsViewScroll.setPrefSize(w, statsAndResultsViewTabpane.getPrefHeight() - Main.tabsSwitchHeight
						- resultsViewScroll.getLayoutY());

				resultsViewResultsPane.setAlignment(Pos.BASELINE_CENTER);
			}
		}
	}

	private static String undef()
	{
		return msgSys.getMsg("undefined", "en_uk");
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
		String lang = msgSys.getMessages().containsKey(System.getProperty("user.language"))
				? System.getProperty("user.language")
				: "en_uk";
		String s = msgSys.getMsg(System.getProperty("user.country"), lang);
		return s != null ? s : msgSys.getMsg("undefined", lang);
	}

	/**
	 * public void createActionHandlers() { CheckBox classNumberCheckbox =
	 * classNumberCheckbox, classLetterCheckbox = classLetterCheckbox,
	 * surnameCheckbox = surnameCheckbox, nameCheckbox = nameCheckbox,
	 * secondNameCheckbox = secondNameCheckbox; ComboBox<String>
	 * classNumberCombobox1 = classNumberCombobox1; ComboBox<String>
	 * testNameCombobox2 = testNameCombobox2, classNumberCombobox2 =
	 * classNumberCombobox2; ComboboxWithAdd classLetterCombobox =
	 * classLetterCombobox, surnameCombobox = surnameCombobox, nameCombobox =
	 * nameCombobox, secondNameCombobox = secondNameCombobox; TextField
	 * classLetterTextfield = classLetterTextfield, surnameTextfield =
	 * surnameTextfield, nameTextfield = nameTextfield, secondNameTextfield =
	 * secondNameTextfield; Button getStatistics = getStatistics,
	 * saveTestingSettingsButton = saveTestingSettingsButton; RadioButton
	 * inPercentsRadiobutton = inPercentsRadiobutton; RadioButton defaultRadiobutton
	 * = defaultRadiobutton, indicateLastAnswerQualityRadiobutton =
	 * indicateLastAnswerQualityRadiobutton, goToAllQuestions =
	 * goToAllQuestionsRadiobutton; CheckBox pause = pauseCheckbox, pauseOnUnfocus =
	 * pauseOnUnfocusCheckbox, indicateAllAnswersQuality =
	 * indicateAllAnswersQualityCheckbox, showRightAnswer = showRightAnswerCheckbox,
	 * skip = skipCheckbox, anticopy = anticopyCheckbox, antiscreenshot =
	 * antiscreenshotCheckbox; TabPane testsList = testsList; Label cell11 = cell11,
	 * cell21 = cell21, cell31 = cell31, cell41 = cell41; Label cell12 = cell12,
	 * cell22 = cell22, cell32 = cell32, cell42 = cell42; Label cell13 = cell13,
	 * cell23 = cell23, cell33 = cell33, cell43 = cell43; Label cell14 = cell14,
	 * cell24 = cell24, cell34 = cell34, cell44 = cell44; Label cell15 = cell15,
	 * cell25 = cell25, cell35 = cell35, cell45 = cell45;
	 * 
	 * stage.setOnShowing(e -> updateTests());
	 * AccountsPart.account.addActionListener(e -> updateResultsTab());
	 * getCodeButton.setOnAction(e ->
	 * codeField.setText(AccountsPart.account.getValue() .getLogin()));
	 * classLetterCombobox.setOnAddOrRemoveElement((e) -> { File f0 = new
	 * File(path); SystemUtils.createDir(f0, true, true); File f = new
	 * File(f0.getAbsolutePath() + "/" +
	 * classNumberCombobox1.getSelectionModel().getSelectedItem());
	 * SystemUtils.createDir(f, true, true); File f2 = new File(f.getAbsolutePath()
	 * + "/" + ((String) e.getSource()).replace("\n", "")); if (((String)
	 * e.getSource()).startsWith("\n")) SystemUtils.removeFile(f2); else
	 * SystemUtils.createDir(f2, true, true); saveTestSettings(); });
	 * classLetterCombobox.setOnAction((ev) -> { File f0 = new File(path);
	 * SystemUtils.createDir(f0, true, true); File f = new File(f0.getAbsolutePath()
	 * + "/" + classNumberCombobox1.getSelectionModel().getSelectedItem());
	 * SystemUtils.createDir(f, true, true); File f3 = new File(f.getAbsolutePath()
	 * + "/" + classLetterCombobox.getSelectionModel().getSelectedItem());
	 * SystemUtils.createDir(f3, true, true); surnameCombobox.getItems().clear(); if
	 * (new File(f3.getAbsolutePath() + "/surname").listFiles() != null) for (File
	 * f2 : new File(f3.getAbsolutePath() + "/surname").listFiles())
	 * surnameCombobox.getItems().add(f2.getName());
	 * nameCombobox.getItems().clear(); if (new File(f3.getAbsolutePath() +
	 * "/name").listFiles() != null) for (File f2 : new File(f3.getAbsolutePath() +
	 * "/name").listFiles()) nameCombobox.getItems().add(f2.getName());
	 * secondNameCombobox.getItems().clear(); if (new File(f3.getAbsolutePath() +
	 * "/secondname").listFiles() != null) for (File f2 : new
	 * File(f3.getAbsolutePath() + "/secondname").listFiles())
	 * secondNameCombobox.getItems().add(f2.getName()); saveTestSettings(); });
	 * surnameCombobox.setOnAddOrRemoveElement((e) -> { File f5 = new File(path +
	 * "/" + classNumberCombobox1.getSelectionModel().getSelectedItem() + "/" +
	 * classLetterCombobox.getSelectionModel() .getSelectedItem() + "/surname" + "/"
	 * + ((String) e.getSource()).replace("\n", "")); if (((String)
	 * e.getSource()).startsWith("\n")) SystemUtils.removeFile(f5); else
	 * System.out.println(SystemUtils.createDir(f5, true, true));
	 * saveTestSettings(); }); nameCombobox.setOnAddOrRemoveElement((e) -> { File f5
	 * = new File(path + "/" +
	 * classNumberCombobox1.getSelectionModel().getSelectedItem() + "/" +
	 * classLetterCombobox.getSelectionModel() .getSelectedItem() + "/name" + "/" +
	 * ((String) e.getSource()).replace("\n", "")); if (((String)
	 * e.getSource()).startsWith("\n")) SystemUtils.removeFile(f5); else
	 * System.out.println(SystemUtils.createDir(f5, true, true));
	 * saveTestSettings(); }); secondNameCombobox.setOnAddOrRemoveElement((e) -> {
	 * File f5 = new File(path + "/" +
	 * classNumberCombobox1.getSelectionModel().getSelectedItem() + "/" +
	 * classLetterCombobox.getSelectionModel() .getSelectedItem() + "/secondname" +
	 * "/" + ((String) e.getSource()).replace("\n", "")); if (((String)
	 * e.getSource()).startsWith("\n")) SystemUtils.removeFile(f5); else {
	 * System.out.println(f5.getAbsolutePath());
	 * SystemUtils.createDir(f5.getParentFile(), true, true);
	 * System.out.println(SystemUtils.createDir(f5, true, true)); }
	 * saveTestSettings(); }); classNumberCombobox1.setOnAction((ev) -> { File f0 =
	 * new File(path); SystemUtils.createDir(f0, true, true); File f = new
	 * File(f0.getAbsolutePath() + "/" +
	 * classNumberCombobox1.getSelectionModel().getSelectedItem());
	 * SystemUtils.createDir(f, true, true); classLetterCombobox.getItems().clear();
	 * if (f.listFiles() != null) for (File f2 : f.listFiles())
	 * classLetterCombobox.getItems().add(f2.getName()); saveTestSettings(); });
	 * classNumberCombobox2.setOnAction((ev) -> { File f0 = new File(path);
	 * SystemUtils.createDir(f0, true, true); File f = new File(f0.getAbsolutePath()
	 * + "/" + classNumberCombobox2.getSelectionModel().getSelectedItem());
	 * SystemUtils.createDir(f, true, true); classLetterCombobox.getItems().clear();
	 * if (f.listFiles() != null) for (File f2 : f.listFiles())
	 * classLetterCombobox.getItems().add(f2.getName()); saveTestSettings(); });
	 * EventHandler<ActionEvent> action = (e) -> { checkPermissions.handle(e);
	 * saveTestingSettingsButton.setDisable(false); }; EventHandler<ActionEvent>
	 * action2 = (e) -> { checkPermissions.handle(e);
	 * saveLookSettingsButton.setDisable(false); }; EventHandler<ActionEvent>
	 * testPropsAction = (e) -> { checkPermissions.handle(e); action.handle(e);
	 * indicateAllAnswersQuality.setDisable(!indicateLastAnswerQualityRadiobutton.isSelected());
	 * showRightAnswer.setDisable(!indicateLastAnswerQualityRadiobutton.isSelected());
	 * }; defaultRadiobutton.setOnAction(testPropsAction);
	 * indicateLastAnswerQualityRadiobutton.setOnAction(testPropsAction);
	 * goToAllQuestions.setOnAction(testPropsAction);
	 * indicateAllAnswersQuality.setOnAction(action);
	 * showRightAnswer.setOnAction(action); skip.setOnAction(action);
	 * pause.setOnAction(e -> { action.handle(e);
	 * pauseOnUnfocus.setDisable(!pause.isSelected()); });
	 * pauseOnUnfocus.setOnAction(action); anticopy.setOnAction(action);
	 * antiscreenshot.setOnAction(action);
	 * 
	 * fixedQSelectBtnHeightCheckbox.setOnAction(action2);
	 * fillAllHeightOfAnswersPanelCheckbox.setOnAction(action2);
	 * maximazeAnswerButtonHeightCheckbox.setOnAction(action2);
	 * spaceBetweenAnswerButtonsField.setOnAction(e -> {
	 * spaceBetweenAnswerButtonsField.setText("" +
	 * Math.max(Math.min(MathWithTextUtils.parseI(
	 * spaceBetweenAnswerButtonsField.getText()), 20), 1));
	 * spaceBetweenAnswerButtonsBar.setProgress(MathWithTextUtils.parseI(
	 * spaceBetweenAnswerButtonsField.getText()) /
	 * spaceBetweenAnswerButtonsBar.getPrefWidth()); action2.handle(null); });
	 * spaceBetweenAnswerButtonsField.focusedProperty().addListener( e ->
	 * spaceBetweenAnswerButtonsField.getOnAction().handle(null));
	 * 
	 * saveTestingSettingsButton.setOnAction(event -> saveTestSettings());
	 * saveLookSettingsButton.setOnAction(event -> saveTestingPartSettings());
	 * HashMap<String, String> testFileNames = new HashMap<String, String>();
	 * statisticsTab.setOnSelectionChanged(e -> { if (statisticsTab.isSelected()) {
	 * testNameCombobox2.getItems().clear(); testFileNames.clear(); for (File f :
	 * new File("Results").listFiles()) { Config cfg = new Config(f); String s =
	 * cfg.getString(msgSys.getMsg("testName", cfg.getString("syntaxLanguage", null,
	 * false)), null, false); if (!testNameCombobox2.getItems().contains(s)) {
	 * testNameCombobox2.getItems().add(s); testFileNames.put(s, f.getName()); } } }
	 * }); testingTab.setOnSelectionChanged(event -> { if (testingTab.isSelected())
	 * updateTests(); }); getStatistics.setOnAction(event -> { if
	 * (testNameCombobox2.getSelectionModel().isEmpty()) {
	 * FXDialogsGenerator.showFXDialog(stage, stage,
	 * msgSys.getMsg("testNotSelected"), JOptionPane.WARNING_MESSAGE, null, true);
	 * return; } testsList.getTabs().clear(); float minResult = Float.MAX_VALUE,
	 * maxResult = 0, result = 0, averageResult = 0, perfectResult = 0; float
	 * questions = 0; float minRightAnswers = Float.MAX_VALUE, maxRightAnswers = 0,
	 * rightAnswers = 0, averageRightAnswers = 0; float minPerfectAnswers =
	 * Float.MAX_VALUE, maxPerfectAnswers = 0, perfectAnswers = 0,
	 * averagePerfectAnswers = 0; float minTime = Float.MAX_VALUE, maxTime = 0, time
	 * = 0, averageTime = 0, perfectTime = 0; int testsCount = 0; for (Config cfg :
	 * Statistics.getStatistics(testFileNames.get(testNameCombobox2.getSelectionModel().getSelectedItem()),
	 * testNameCombobox2 .getSelectionModel().getSelectedItem(),
	 * !classNumberCheckbox.isSelected() ? null :
	 * classNumberCombobox2.getSelectionModel().getSelectedItem(),
	 * !classLetterCheckbox.isSelected() ? null : classLetterTextfield.getText(),
	 * !surnameCheckbox.isSelected() ? null : surnameTextfield.getText(),
	 * !nameCheckbox .isSelected() ? null : nameTextfield.getText(),
	 * !secondNameCheckbox.isSelected() ? null : secondNameTextfield.getText())) {
	 * testsList.getTabs().add(new Tab(cfg.getFile().getName().replace("Result From
	 * ", "").replace(".log", ""), new TextArea(cfg.getText(false))));
	 * 
	 * String language = cfg.getString("syntaxLanguage", null, false); result +=
	 * cfg.getInteger(msgSys.getMsg("result", language), null, false); minResult =
	 * Math.min(minResult, cfg.getInteger(msgSys.getMsg("result", language), null,
	 * false)); maxResult = Math.max(maxResult,
	 * cfg.getInteger(msgSys.getMsg("result", language), null, false));
	 * perfectResult += cfg.getInteger(msgSys.getMsg("maxResult", language), null,
	 * false);
	 * 
	 * perfectAnswers += cfg.getInteger(msgSys.getMsg("perfectAnswersAmount",
	 * language), null, false); minPerfectAnswers = Math.min(minPerfectAnswers,
	 * cfg.getInteger(msgSys.getMsg("perfectAnswersAmount", language), null,
	 * false)); maxPerfectAnswers = Math.max(maxPerfectAnswers,
	 * cfg.getInteger(msgSys.getMsg("perfectAnswersAmount", language), null,
	 * false));
	 * 
	 * rightAnswers += cfg.getInteger(msgSys.getMsg("rightAnswersAmount", language),
	 * null, false); minRightAnswers = Math.min(minRightAnswers,
	 * cfg.getInteger(msgSys.getMsg("rightAnswersAmount", language), null, false));
	 * maxRightAnswers = Math.max(maxRightAnswers,
	 * cfg.getInteger(msgSys.getMsg("rightAnswersAmount", language), null, false));
	 * 
	 * questions += cfg.getInteger(msgSys.getMsg("questionsAmount", language), null,
	 * false);
	 * 
	 * time += cfg.getTime(msgSys.getMsg("time", language), null, false); minTime =
	 * Math.min(minTime, cfg.getTime(msgSys.getMsg("time", language), null, false));
	 * maxTime = Math.max(maxTime, cfg.getTime(msgSys.getMsg("time", language),
	 * null, false)); perfectTime += cfg.getTime(msgSys.getMsg("timeToTest",
	 * language), null, false);
	 * 
	 * testsCount++; } averageResult = result / testsCount; perfectResult /=
	 * testsCount;
	 * 
	 * averageRightAnswers = rightAnswers / testsCount; averagePerfectAnswers =
	 * perfectAnswers / testsCount; questions /= testsCount;
	 * 
	 * averageTime = time / testsCount; perfectTime /= testsCount;
	 * 
	 * if (inPercentsRadiobutton.isSelected()) {
	 * cell11.setText(numberToString(minResult / perfectResult * 100f, 2) + "%");
	 * cell12.setText(numberToString(averageResult / perfectResult * 100f, 2) +
	 * "%"); cell13.setText(numberToString(maxResult / perfectResult * 100f, 2) +
	 * "%"); } else { cell11.setText(numberToString(minResult, 2) + "");
	 * cell12.setText(numberToString(averageResult, 2) + "");
	 * cell13.setText(numberToString(maxResult, 2) + ""); }
	 * cell14.setText(numberToString(perfectResult, 2) + "");
	 * cell15.setText(numberToString(result, 2) + "");
	 * 
	 * if (inPercentsRadiobutton.isSelected()) {
	 * cell21.setText(numberToString(minRightAnswers / questions * 100f, 2) + "%");
	 * cell22.setText(numberToString(averageRightAnswers / questions * 100f, 2) +
	 * "%"); cell23.setText(numberToString(maxRightAnswers / questions * 100f, 2) +
	 * "%"); } else { cell21.setText(numberToString(minRightAnswers, 2) + "");
	 * cell22.setText(numberToString(averageRightAnswers, 2) + "");
	 * cell23.setText(numberToString(maxRightAnswers, 2) + ""); }
	 * cell24.setText(numberToString(questions, 2) + "");
	 * cell25.setText(numberToString(rightAnswers, 2) + "");
	 * 
	 * if (inPercentsRadiobutton.isSelected()) {
	 * cell31.setText(numberToString(minPerfectAnswers / questions * 100f, 2) +
	 * "%"); cell32.setText(numberToString(averagePerfectAnswers / questions * 100f,
	 * 2) + "%"); cell33.setText(numberToString(maxPerfectAnswers / questions *
	 * 100f, 2) + "%"); } else { cell31.setText(numberToString(minPerfectAnswers, 2)
	 * + ""); cell32.setText(numberToString(averagePerfectAnswers, 2) + "");
	 * cell33.setText(numberToString(maxPerfectAnswers, 2) + ""); }
	 * cell34.setText(numberToString(questions, 2) + "");
	 * cell35.setText(numberToString(perfectAnswers, 2) + "");
	 * 
	 * if (inPercentsRadiobutton.isSelected()) {
	 * cell41.setText(numberToString(minTime / perfectTime * 100f, 2) + "%");
	 * cell42.setText(numberToString(averageTime / perfectTime * 100f, 2) + "%");
	 * cell43.setText(numberToString(maxTime / perfectTime * 100f, 2) + "%"); } else
	 * { cell41.setText(numberToString(minTime, 2) + "");
	 * cell42.setText(numberToString(averageTime, 2) + "");
	 * cell43.setText(numberToString(maxTime, 2) + ""); }
	 * cell44.setText(numberToString(perfectTime, 2) + "");
	 * cell45.setText(numberToString(time, 2) + ""); if (testsList.getTabs().size()
	 * == 0) FXDialogsGenerator.showFXDialog(stage, stage,
	 * msgSys.getMsg("testWithFiltersNotExist"), JOptionPane.WARNING_MESSAGE, null,
	 * true); }); }
	 */

	private HashMap<Test, File> testFiles;
	private Test[] tests;
	private Test selectedTest;

	private void updateTests()
	{
		testNameCombobox1.getItems().clear();
		File[] files;
		File testsDir = new File("Tests");
		if (!testsDir.isDirectory())
			testsDir.delete();
		if (!testsDir.exists())
			testsDir.mkdir();
		files = testsDir.listFiles();
		tests = new Test[files.length];
		testFiles = new HashMap<Test, File>();
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
	}

	// private void updateTests()
	// {
	// ComboBox<String> testNameCombobox1 =
	// testNameCombobox1;
	// ComboBox<String> classNumberCombobox1 =
	// classNumberCombobox1;
	// ComboboxWithAdd classLetterCombobox =
	// classLetterCombobox;
	// ComboboxWithAdd surnameCombobox =
	// surnameCombobox;
	// ComboboxWithAdd nameCombobox =
	// nameCombobox;
	// ComboboxWithAdd secondNameCombobox =
	// secondNameCombobox;
	// RadioButton indicateLastAnswerQualityRadiobutton =
	// indicateLastAnswerQualityRadiobutton;
	// CheckBox indicateAllAnswersQualityCheckbox =
	// indicateAllAnswersQualityCheckbox;
	// CheckBox showRightAnswerCheckbox =
	// showRightAnswerCheckbox;
	// RadioButton goToAllQuestionsRadiobutton =
	// goToAllQuestionsRadiobutton;
	// CheckBox skipCheckbox = skipCheckbox;
	// CheckBox pauseCheckbox = pauseCheckbox;
	// CheckBox pauseOnUnfocusCheckbox =
	// pauseOnUnfocusCheckbox;
	// CheckBox anticopyCheckbox =
	// anticopyCheckbox;
	// CheckBox antiscreenshotCheckbox =
	// antiscreenshotCheckbox;
	// CheckBox fixedQSelectBtnHeightCheckbox =
	// fixedQSelectBtnHeightCheckbox;
	// Button startTestButton =
	// startTestButton;
	//
	// testNameCombobox1.getItems().clear();
	//
	// File[] files;
	// File testsDir = new File("Tests");
	// if (!testsDir.isDirectory())
	// {
	// if (!testsDir.exists())
	// testsDir.mkdir();
	// else
	// {
	// testsDir.delete();
	// testsDir.mkdir();
	// }
	// }
	// files = testsDir.listFiles();
	// tests = new Test[files.length];
	// HashMap<Test, File> testFiles = new HashMap<Test, File>();
	// for (int i = 0; i < files.length; i++)
	// if (files[i].isDirectory())
	// {
	// File[] inFiles = files[i].listFiles();
	// for (int j = 0; j < inFiles.length; j++)
	// if (inFiles[j].isFile() && inFiles[j].getName().endsWith(".test"))
	// {
	// tests[i] = Test.valueOf(new Config(inFiles[j]));
	// testFiles.put(tests[i], inFiles[j]);
	// testNameCombobox1.getItems().add(tests[i].getName());
	// }
	// }
	// testNameCombobox1.getSelectionModel().select(0);
	// testNameCombobox1.setOnAction(e ->
	// {
	// boolean b = false;
	// for (Test test : tests)
	// if
	// (test.getName().equals(testNameCombobox1.getSelectionModel().getSelectedItem()))
	// {
	// selectedTest = test;
	// b = true;
	// }
	// if (!b)
	// selectedTest = null;
	// });
	// selectedTest = tests[0];
	// boolean hide = true;
	// btn.setOnAction(e ->
	// {
	// Test test = null;
	// String selectedTestName =
	// testNameCombobox1.getSelectionModel().getSelectedItem();
	// for (Test t : tests)
	// if (t.getName().equals(selectedTestName))
	// {
	// test = t;
	// break;
	// }
	// File file = testFiles.get(test).getAbsoluteFile().getParentFile();
	// HashMap<String, byte[]> files2 = new HashMap<String, byte[]>();
	// for (File testD : file.listFiles())
	// files2.put(testD.getName(), SystemUtils.readFile(testD));
	//
	// Main.sendToServer(new TestingTaskPacket("giveTask",
	// login.getText(), new
	// TestingTask(TaskType.Homework,
	// AccountsPart.account.getValue().getLogin(), new
	// TestToMarket(test.getProgramVersion(), test.getTestVersion(),
	// test.getTestCreationDate(), test
	// .getTestLanguage(), test.getTestSubject(), test.getAuthors(), test.getName(),
	// test.getDescription(), file.getName(), files2),
	// new
	// TestSettings(indicateLastAnswerQualityRadiobutton.isSelected(),
	// indicateAllAnswersQualityCheckbox.isSelected()
	// &&
	// !indicateAllAnswersQualityCheckbox.isDisable(),
	// showRightAnswerCheckbox.isSelected()
	// &&
	// !showRightAnswerCheckbox.isDisable(),
	// goToAllQuestionsRadiobutton.isSelected()
	// &&
	// !goToAllQuestionsRadiobutton.isDisable(),
	// skipCheckbox.isSelected(),
	// pauseCheckbox.isSelected(),
	// pauseOnUnfocusCheckbox.isSelected(),
	// anticopyCheckbox
	// .isSelected(),
	// antiscreenshotCheckbox.isSelected()),
	// new TestingPartSettings(
	// fixedQSelectBtnHeightCheckbox.isSelected(),
	// fillAllHeightOfAnswersPanelCheckbox.isSelected(),
	// fillAllHeightOfAnswersPanelCheckbox.isSelected()
	// &&
	// maximazeAnswerButtonHeightCheckbox.isSelected()))));
	// });
	// startTestButton.setOnAction(e ->
	// {
	// checkPermissions.handle(e);
	// if (classNumberCombobox1.getSelectionModel().getSelectedItem() == null ||
	// classLetterCombobox.getSelectionModel().getSelectedItem() == null
	// || classNumberCombobox1.getSelectionModel().getSelectedItem().equals("") ||
	// classLetterCombobox.getSelectionModel().getSelectedItem()
	// .equals(""))
	// FXDialogsGenerator.showFXDialog(stage, stage,
	// msgSys.getMsg("classMustBeSelected"), JOptionPane.INFORMATION_MESSAGE, null,
	// true);
	// else if (surnameCombobox.getSelectionModel().getSelectedItem() == null ||
	// "".equals(surnameCombobox.getSelectionModel().getSelectedItem()))
	// FXDialogsGenerator.showFXDialog(stage, stage,
	// msgSys.getMsg("surnameMustBeSelected"), JOptionPane.INFORMATION_MESSAGE,
	// null, true);
	// else if (nameCombobox.getSelectionModel().getSelectedItem() == null ||
	// "".equals(nameCombobox.getSelectionModel().getSelectedItem()))
	// FXDialogsGenerator.showFXDialog(stage, stage,
	// msgSys.getMsg("nameMustBeSelected"), JOptionPane.INFORMATION_MESSAGE, null,
	// true);
	// else if (secondNameCombobox.getSelectionModel().getSelectedItem() == null ||
	// "".equals(secondNameCombobox.getSelectionModel().getSelectedItem()))
	// FXDialogsGenerator.showFXDialog(stage, stage,
	// msgSys.getMsg("secondNameMustBeSelected"), JOptionPane.INFORMATION_MESSAGE,
	// null, true);
	// else
	// {
	// Test test = null;
	// String selectedTestName =
	// testNameCombobox1.getSelectionModel().getSelectedItem();
	// for (Test t : tests)
	// if (t.getName().equals(selectedTestName))
	// {
	// test = t;
	// break;
	// }
	// TestingPart studentsTestingPartFX = new TestingPart(new Rectangle((int)
	// stage.getX(), (int) stage.getY(), (int) stage.getWidth(), (int) stage
	// .getHeight()), test, testFiles.get(test).getName(), theme, new
	// StudentInfo(AccountsPart.account.getValue().getLogin(), classNumberCombobox1
	// .getSelectionModel().getSelectedItem(),
	// classLetterCombobox.getSelectionModel().getSelectedItem(), surnameCombobox
	// .getSelectionModel().getSelectedItem(),
	// nameCombobox.getSelectionModel().getSelectedItem(), secondNameCombobox
	// .getSelectionModel().getSelectedItem()), new
	// TestSettings(indicateLastAnswerQualityRadiobutton.isSelected(),
	// indicateAllAnswersQualityCheckbox.isSelected() &&
	// !indicateAllAnswersQualityCheckbox.isDisable(),
	// showRightAnswerCheckbox.isSelected() && !showRightAnswerCheckbox.isDisable(),
	// goToAllQuestionsRadiobutton.isSelected() &&
	// !goToAllQuestionsRadiobutton.isDisable(), skipCheckbox
	// .isSelected(), pauseCheckbox.isSelected(),
	// pauseOnUnfocusCheckbox.isSelected(), anticopyCheckbox
	// .isSelected(), antiscreenshotCheckbox.isSelected()), new TestingPartSettings(
	// fixedQSelectBtnHeightCheckbox
	// .isSelected(),
	// fillAllHeightOfAnswersPanelCheckbox
	// .isSelected(),
	// fillAllHeightOfAnswersPanelCheckbox
	// .isSelected()
	// && maximazeAnswerButtonHeightCheckbox
	// .isSelected()), true, null);
	// try
	// {
	// studentsTestingPartFX.open(new Rectangle((int) stage.getX(), (int)
	// stage.getY(), (int) stage.getWidth(), (int) stage.getHeight()),
	// AccountsPart.account.getValue(), Main.client);
	// }
	// catch (Exception e1)
	// {
	// FXDialogsGenerator.showFXDialog(stage, (Stage) null,
	// msgSys.getMsg("signInToWork"), 0, null, true);
	// }
	// Main.instance.hideAll();
	// }
	// });
	// stage.requestFocus();
	// }
	private void update()
	{
		testNameLabel2.setDisable(!testNameCheckbox.isSelected());
		testNameCombobox2.setDisable(!testNameCheckbox.isSelected());
		statsPane.setDisable(!testNameCheckbox.isSelected());

		loginLabel2.setDisable(!loginCheckbox.isSelected());
		loginTextfield.setDisable(!loginCheckbox.isSelected());

		classNumberLabel2.setDisable(!classNumberCheckbox.isSelected());
		classNumberCombobox2.setDisable(!classNumberCheckbox.isSelected());

		classLetterLabel2.setDisable(!classLetterCheckbox.isSelected());
		classLetterTextfield.setDisable(!classLetterCheckbox.isSelected());

		surnameLabel2.setDisable(!surnameCheckbox.isSelected());
		surnameTextfield.setDisable(!surnameCheckbox.isSelected());

		nameLabel2.setDisable(!nameCheckbox.isSelected());
		nameTextfield.setDisable(!nameCheckbox.isSelected());

		secondNameLabel2.setDisable(!secondNameCheckbox.isSelected());
		secondNameTextfield.setDisable(!secondNameCheckbox.isSelected());
	}

	public static final Theme theme = new Theme();
	static
	{
		theme.setWindowBackground(new Color(1, 1, 220 / 255f, 1));

		theme.getPickOne().setQuestionBackground(new Color(1, 150 / 255f, 0, 1));
		theme.getPickOne().setQuestionForeground(new Color(0, 0, 0, 1));
		theme.getPickOne().setQuestionFrame(new Color(200 / 255f, 100 / 255f, 0, 1));
		theme.getPickOne().setAnswersBackground(new Color(250 / 255f, 250 / 255f, 250 / 255f, 1));
		theme.getPickOne().setAnswersForeground(new Color(0, 0, 0, 1));
		theme.getPickOne().setAnswersFrame(new Color(200 / 255f, 200 / 255f, 200 / 255f, 1));

		theme.getSelectSome().setQuestionBackground(new Color(1, 150 / 255f, 0, 1));
		theme.getSelectSome().setQuestionForeground(new Color(0, 0, 0, 1));
		theme.getSelectSome().setQuestionFrame(new Color(200 / 255f, 100 / 255f, 0, 1));
		theme.getSelectSome().setAnswersBackground(new Color(250 / 255f, 250 / 255f, 250 / 255f, 1));
		theme.getSelectSome().setAnswersForeground(new Color(0, 0, 0, 1));
		theme.getSelectSome().setAnswersFrame(new Color(200 / 255f, 200 / 255f, 200 / 255f, 1));

		theme.getEnterText().setQuestionBackground(new Color(1, 150 / 255f, 0, 1));
		theme.getEnterText().setQuestionForeground(new Color(0, 0, 0, 1));
		theme.getEnterText().setQuestionFrame(new Color(200 / 255f, 100 / 255f, 0, 1));
		theme.getEnterText().setAnswersBackground(new Color(250 / 255f, 250 / 255f, 250 / 255f, 1));
		theme.getEnterText().setAnswersForeground(new Color(0, 0, 0, 1));
		theme.getEnterText().setAnswersFrame(new Color(200 / 255f, 200 / 255f, 200 / 255f, 1));

		theme.getMatching().setQuestionBackground(new Color(1, 150 / 255f, 0, 1));
		theme.getMatching().setQuestionForeground(new Color(0, 0, 0, 1));
		theme.getMatching().setQuestionFrame(new Color(200 / 255f, 100 / 255f, 0, 1));
		theme.getMatching().setAnswersBackground(new Color(250 / 255f, 250 / 255f, 250 / 255f, 1));
		theme.getMatching().setAnswersForeground(new Color(0, 0, 0, 1));
		theme.getMatching().setAnswersFrame(new Color(200 / 255f, 200 / 255f, 200 / 255f, 1));

		theme.getArrangement().setQuestionBackground(new Color(1, 150 / 255f, 0, 1));
		theme.getArrangement().setQuestionForeground(new Color(0, 0, 0, 1));
		theme.getArrangement().setQuestionFrame(new Color(200 / 255f, 100 / 255f, 0, 1));
		theme.getArrangement().setAnswersBackground(new Color(250 / 255f, 250 / 255f, 250 / 255f, 1));
		theme.getArrangement().setAnswersForeground(new Color(0, 0, 0, 1));
		theme.getArrangement().setAnswersFrame(new Color(200 / 255f, 200 / 255f, 200 / 255f, 1));

		theme.getDistribution().setQuestionBackground(new Color(1, 150 / 255f, 0, 1));
		theme.getDistribution().setQuestionForeground(new Color(0, 0, 0, 1));
		theme.getDistribution().setQuestionFrame(new Color(200 / 255f, 100 / 255f, 0, 1));
		theme.getDistribution().setAnswersBackground(new Color(250 / 255f, 250 / 255f, 250 / 255f, 1));
		theme.getDistribution().setAnswersForeground(new Color(0, 0, 0, 1));
		theme.getDistribution().setAnswersFrame(new Color(200 / 255f, 200 / 255f, 200 / 255f, 1));

		theme.setQuestionSelectNormalBackground(new Color(250 / 255f, 250 / 255f, 250 / 255f, 1));
		theme.setQuestionSelectNormalForeground(new Color(0, 0, 0, 1));
		theme.setQuestionSelectNormalFrame(new Color(180 / 255f, 180 / 255f, 180 / 255f, 1));
		theme.setQuestionSelectSkippedBackground(new Color(1, 1, 0, 1));
		theme.setQuestionSelectSkippedForeground(new Color(0, 0, 0, 1));
		theme.setQuestionSelectSkippedFrame(new Color(200 / 255f, 180 / 255f, 0, 1));

		theme.setSpecialButtonsBackground(new Color(150 / 255f, 220 / 255f, 30 / 255f, 1));
		theme.setSpecialButtonsForeground(new Color(1, 1, 1, 1));
		theme.setSpecialButtonsFrame(new Color(110 / 255f, 200 / 255f, 50 / 255f, 1));
	}

	public String numberToString(double number, int size)
	{
		double number2 = (double) ((int) (number * Math.pow(10, size))) / Math.pow(10, size);
		if ((int) number2 == number2)
			return (int) number2 + "";
		return number2 + "";
	}

	public void updateLabels()
	{
		super.updateLabels();

		testingTab.setText(msgSys.getMsg("testingTab"));
		{
			testNameLabel1.setText(msgSys.getMsg("testName"));
			testSettingsTab.setText(msgSys.getMsg("testSettingsTab"));
			for (Tab tab : testSettingsTabpane.getTabs())
				if (tab instanceof TestSettingsKitTab)
					((TestSettingsKitTab) tab).updateLabels();
			testingPartSettingsTab.setText(msgSys.getMsg("testingPartSettingsTab"));
			for (Tab tab : testingPartSettingsTabpane.getTabs())
				if (tab instanceof TestingPartSettingsKitTab)
					((TestingPartSettingsKitTab) tab).updateLabels();
			inComputerTab.setText(msgSys.getMsg("inComputerTab"));
			{
				classNumberLabel1.setText(msgSys.getMsg("classNumber"));
				classLetterLabel1.setText(msgSys.getMsg("classLetter"));
				surnameLabel1.setText(msgSys.getMsg("surname"));
				nameLabel1.setText(msgSys.getMsg("name"));
				secondNameLabel1.setText(msgSys.getMsg("secondName"));
				startTestButton.setText(msgSys.getMsg("startTest"));
			}
			networkTab.setText(msgSys.getMsg("networkTab"));
			{
				sendTestButton.setText(msgSys.getMsg("sendTest"));
				onlineStudentsLabel.setText(msgSys.getMsg("onlineStudents"));
				offlineStudentsLabel.setText(msgSys.getMsg("offlineStudents"));
				addStudentLabel.setText(msgSys.getMsg("addStudent"));
			}
		}
		resultsTab.setText(msgSys.getMsg("resultsTab"));
		{
			inComputerOrNetworkLabel.setText(msgSys.getMsg("inComputerOrNetwork"));
			testNameLabel2.setText(msgSys.getMsg("testName"));
			loginLabel2.setText(msgSys.getMsg("login"));
			classNumberLabel2.setText(msgSys.getMsg("classNumber"));
			classLetterLabel2.setText(msgSys.getMsg("classLetter"));
			surnameLabel2.setText(msgSys.getMsg("surname"));
			nameLabel2.setText(msgSys.getMsg("name"));
			secondNameLabel2.setText(msgSys.getMsg("secondName"));

			statsTab.setText(msgSys.getMsg("statsTab"));
			{
				inPercentsCheckbox.setText(msgSys.getMsg("inPercents"));
				signsAfterCommaCombobox.setText(msgSys.getMsg("signsAfterComma"));
				getStatsButton.setText(msgSys.getMsg("getStats"));
				for (Label label : stats.keySet())
					label.setText(msgSys.getMsg(stats.get(label).getFirst()));
				for (Label label : propsToStats.keySet())
					label.setText(msgSys.getMsg(propsToStats.get(label)));
			}
			resultsViewTab.setText(msgSys.getMsg("resultsViewTab"));
			{
				getResultsButton.setText(msgSys.getMsg("getResults"));
			}
		}
		resize();
	}

	public Test getSelectedTest()
	{
		return selectedTest;
	}

	@Override
	public String name()
	{
		return "teachersTestsControl";
	}

	/*
	 * public void updateExternalResults() { resultsBox.getChildren().clear(); for
	 * (File f : new File("External results").listFiles()) { String s =
	 * SystemUtils.readFile(f, "cp1251"); Button see = new Button("See");
	 * see.setOnAction(e -> FXDialogsGenerator.showFXDialog(stage, (Rectangle) null,
	 * s, 0, null, true)); if (s.split("\n").length > 3)
	 * resultsBox.getChildren().add(new Pane(new Label(f.getName()), new
	 * Label(s.split("\n")[0]), new Label(s .split("\n")[1]), see)); } }
	 */

	HashMap<String, HashMap<String, ArrayList<ResultSendPacket>>> results = new HashMap<String, HashMap<String, ArrayList<ResultSendPacket>>>();

	/*
	 * public void addResult(ResultSendPacket packet) { if
	 * (!results.containsKey(AccountsPart.account.getValue().getLogin()))
	 * results.put(AccountsPart.account.getValue().getLogin(), new HashMap<String,
	 * ArrayList<ResultSendPacket>>()); if
	 * (!results.get(AccountsPart.account.getValue().getLogin()).containsKey(packet.
	 * getStudent()))
	 * results.get(AccountsPart.account.getValue().getLogin()).put(packet.getStudent
	 * (), new ArrayList<ResultSendPacket>());
	 * results.get(AccountsPart.account.getValue().getLogin()).get(packet.getStudent
	 * ()). add(packet); updateResultsTab(); }
	 */

	/*
	 * private void updateResultsTab() { resultsBox.getChildren().clear(); if
	 * (AccountsPart.account.getValue() != null) if
	 * (results.get(AccountsPart.account.getValue().getLogin()) != null) for (String
	 * rs : results.get(AccountsPart.account.getValue().getLogin()).keySet()) for
	 * (ResultSendPacket result :
	 * results.get(AccountsPart.account.getValue().getLogin()).get(rs)) { Node[]
	 * nodes = new Node[9]; nodes[0] = new VBox(5, new Label("Login"), new
	 * Label(result.getStudent()));
	 * 
	 * nodes[1] = new VBox(5, new Label("Account surname"), new
	 * Label(result.getAccSurname())); nodes[2] = new VBox(5, new
	 * Label("Account name"), new Label(result.getAccName())); nodes[3] = new
	 * VBox(5, new Label("Account second name"), new
	 * Label(result.getAccSecondName()));
	 * 
	 * nodes[4] = new VBox(5, new Label("Selected class number"), new
	 * Label(result.getSelClassNumber())); nodes[4] = new VBox(5, new
	 * Label("Selected class letter"), new Label(result.getSelClassLetter()));
	 * nodes[4] = new VBox(5, new Label("Selected surname"), new
	 * Label(result.getSelSurname())); nodes[5] = new VBox(5, new
	 * Label("Selected name"), new Label(result.getSelName())); nodes[6] = new
	 * VBox(5, new Label("Selected second name"), new
	 * Label(result.getSelSecondName()));
	 * 
	 * nodes[7] = new VBox(5, new Label("Date"), new
	 * Label(result.getDate().toString()));
	 * 
	 * nodes[8] = createButton("See result", e ->
	 * FXDialogsGenerator.showFXDialog(stage, (Stage) null, result.getResult(), 0,
	 * null, true)); HBox hBox = new HBox(5, nodes);
	 * resultsBox.getChildren().add(hBox); } }
	 */

	private Button createButton(String string, EventHandler<ActionEvent> ev)
	{
		Button btn = new Button(string);
		btn.setOnAction(ev);
		return btn;
	}

	public Object addResult(ResultSendPacket packet)
	{
		// TODO Auto-generated method stub
		return null;
	}

	HashMap<String, ToggleButton> students = new HashMap<String, ToggleButton>();

	public void handleStudentOnlineTestPacket(StudentOnlineTestPacket packet)
	{
		Platform.runLater(() ->
		{
			if (packet.getRequest().equals("online") || packet.getRequest().equals("offline"))
				if (!students.containsKey(packet.getLogin()))
					students.put(packet.getLogin(), new ToggleButton(packet.getLogin()));
			switch (packet.getRequest())
			{
				case "online":
					if (offlineStudentsPane.getChildren().contains(students.get(packet.getLogin())))
						offlineStudentsPane.getChildren().remove(students.get(packet.getLogin()));
					if (!onlineStudentsPane.getChildren().contains(students.get(packet.getLogin())))
						onlineStudentsPane.getChildren().add(students.get(packet.getLogin()));
					break;
				case "offline":
					if (onlineStudentsPane.getChildren().contains(students.get(packet.getLogin())))
						onlineStudentsPane.getChildren().remove(students.get(packet.getLogin()));
					if (!offlineStudentsPane.getChildren().contains(students.get(packet.getLogin())))
						offlineStudentsPane.getChildren().add(students.get(packet.getLogin()));
					break;
				case "accountNotExists":
					FXDialogsGenerator.showFXDialog(stage, msgSys.getMsg(packet.getRequest()), null, true);
					break;
				case "accountNotStudent":
					FXDialogsGenerator.showFXDialog(stage, msgSys.getMsg(packet.getRequest()), null, true);
					break;
			}
		});
	}
}
