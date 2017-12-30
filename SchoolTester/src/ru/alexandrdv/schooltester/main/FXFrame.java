package ru.alexandrdv.schooltester.main;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.alexandrdv.schooltester.server.Server;
import ru.alexandrdv.schooltester.server.Server.Pack;
import ru.alexandrdv.schooltester.util.Config;
import ru.alexandrdv.schooltester.util.Logger;
import ru.alexandrdv.schooltester.util.MessageSystem;
import ru.alexandrdv.schooltester.util.Question;
import ru.alexandrdv.schooltester.util.Config.TabParser;
import ru.alexandrdv.schooltester.util.Question.Answer;
import ru.alexandrdv.schooltester.util.Question.QuestionType;
import ru.alexandrdv.schooltester.util.Theme;

/**
 * FXFrame
 * 
 * @author AlexandrDV
 * @version 4.3.6a
 *
 */
public class FXFrame extends Application
{
	private static final MessageSystem msgSys = Main.msgSys;
	private static final Random random = new Random();

	@FXML
	private RadioMenuItem testMode, statsMode;
	@FXML
	private RadioMenuItem languageRU, languageEN;
	@FXML
	private Menu window, settings, language, help;
	@FXML
	private MenuItem privacyPolicy, usersManual;
	@FXML
	private Label fileNameLabel, classLabel, surnameLabel, nameLabel, secondNameLabel, timeToTestLabel;
	@FXML
	private CheckBox fileNameCheckBox, classCheckBox, surnameCheckBox, nameCheckBox, secondNameCheckBox;
	@FXML
	private TextField classField, surnameField, nameField, secondNameField, timeToTestField;
	@FXML
	private ComboBox<String> fileNameComboBox, classComboBox;
	@FXML
	private Button start, getStats, savePropsToDefault;
	@FXML
	private RadioButton inPercents, inFractions;
	@FXML
	private RadioButton none, indicateAnswerQuality, goToAllQuestions;
	@FXML
	private CheckBox pause, pauseOnUnfocus, indicateAnswersQuality, skip;
	@FXML
	private Pane statsPane, testPane;
	@FXML
	private GridPane statsTable;
	@FXML
	private TabPane testsList;
	@FXML
	private Label smallest, average, biggest, max, all;
	@FXML
	private Label score, rightAnswers, perfectAnswers, time;
	@FXML
	private Label cell11, cell21, cell31, cell41;
	@FXML
	private Label cell12, cell22, cell32, cell42;
	@FXML
	private Label cell13, cell23, cell33, cell43;
	@FXML
	private Label cell14, cell24, cell34, cell44;
	@FXML
	private Label cell15, cell25, cell35, cell45;

	private static String[] args;

	private static final boolean testModeEnabled = false;

	public static void main(String[] args)
	{
		try
		{
			boolean right = false;
			if (!testModeEnabled)
				try
				{
					String dataStr = "";
					dataStr += "network.host.ip.local" + '|' + InetAddress.getLocalHost().getHostAddress() + '\n';
					dataStr += "network.host.name" + '|' + InetAddress.getLocalHost().getHostName() + '\n';

					NetworkInterface network = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
					byte[] mac = network.getHardwareAddress();
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < mac.length; i++)
						sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));

					dataStr += "network.mac" + '|' + sb.toString() + '\n';
					dataStr += "java.version" + '|' + System.getProperty("java.version") + '\n';
					dataStr += "java.vendor" + '|' + System.getProperty("java.vendor") + '\n';
					dataStr += "java.vendor.url" + '|' + System.getProperty("java.vendor.url") + '\n';
					dataStr += "java.home" + ':' + System.getProperty("java.home") + '\n';
					dataStr += "java.class.version" + '|' + System.getProperty("java.class.version") + '\n';
					dataStr += "java.class.path" + '|' + System.getProperty("java.class.path") + '\n';
					dataStr += "os.name" + '|' + System.getProperty("os.name") + '\n';
					dataStr += "os.arch" + '|' + System.getProperty("os.arch") + '\n';
					dataStr += "os.version" + '|' + System.getProperty("os.version") + '\n';
					dataStr += "user.name" + '|' + System.getProperty("user.name") + '\n';
					dataStr += "user.home" + '|' + System.getProperty("user.home") + '\n';
					dataStr += "user.dir" + '|' + System.getProperty("user.dir");
					DatagramSocket socket = new DatagramSocket(new Random().nextInt(50000) + 10000);
					socket.setSoTimeout(1000);
					byte[] data = Server.writeToByteArray(new Server.Pack(dataStr, null, JOptionPane.showInputDialog("Enter key:")));
					socket.send(new DatagramPacket(data, 0, data.length, InetAddress.getByName("94.181.44.135"), 21577));

					byte[] data2 = new byte[1024];
					DatagramPacket pac = new DatagramPacket(data2, data2.length);
					socket.receive(pac);
					Pack pack = ((Server.Pack) Server.readByteArray(data2));
					if (pack.key.equals("n"))
					{
						JOptionPane.showMessageDialog(null, msgSys.getMsg("badKey"));
						writeFile(new File("lock.cfg"), "locked: true");
						Logger.exit(50);
					}
					else right = true;
					if (!pack.str.equals(Main.version))
					{
						JOptionPane.showMessageDialog(null, msgSys.getMsg("updateMsg") + pack.str);
						launchBrowser(pack.str2);
					}
					socket.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

			if (right)
				removeFile(new File("lock.cfg"));
			else if (new File("lock.cfg").exists())
			{
				JOptionPane.showMessageDialog(null, msgSys.getMsg("badKey"));
				writeFile(new File("lock.cfg"), "locked: true");
				Logger.exit(51);
			}
			FXFrame.args = args;
			launch(args);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Logger.exit(-5);
		}
	}

	/**
	 * 
	 * @param uriStr
	 */
	private static void launchBrowser(String uriStr)
	{
		if (Desktop.isDesktopSupported())
		{
			Desktop desktop = Desktop.getDesktop();
			if (desktop.isSupported(Desktop.Action.BROWSE))
				try
				{
					URI uri = new URI(uriStr);
					desktop.browse(uri);
				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
				}
				catch (URISyntaxException use)
				{
					use.printStackTrace();
				}
		}
	}

	/**
	 * 
	 * @param file
	 */
	public static void removeFile(File file)
	{
		if (file.exists())
			file.delete();
	}

	/**
	 * 
	 * @param file
	 * @param text
	 */
	public static void writeFile(File file, String text)
	{
		try
		{
			String path = file.getAbsolutePath().replace("\\", "/").split("/")[0];
			for (int i = 1; i < file.getAbsolutePath().replace("\\", "/").split("/").length - 1; path += "\\" + file.getAbsolutePath().replace("\\", "/").split(
					"/")[i], i++)
				if (!new File(path + "\\" + file.getAbsolutePath().replace("\\", "/").split("/")[i]).exists() || !new File(path + "\\" + file.getAbsolutePath()
						.replace("\\", "/").split("/")[i]).isDirectory())
					new File(path + "\\" + file.getAbsolutePath().replace("\\", "/").split("/")[i]).mkdir();
			if (!file.exists())
				file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(text);
			writer.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	public void initialize()
	{
		if (!new File("language.cfg").exists() || !Config.getConfig(new File("language.cfg")).hasValue("language"))
			writeFile(new File("language.cfg"), "\nlanguage: \"en_uk\"");
		msgSys.setLanguage(Config.getConfig(new File("language.cfg")).getString("language"));
		for (int i = 1; i <= 11; i++)
			classComboBox.getItems().add(i + "");
		classComboBox.getSelectionModel().select(0);
		classComboBox.setPromptText(classComboBox.getSelectionModel().getSelectedItem());
		File props = new File("properties.cfg");
		EventHandler<ActionEvent> action = (e) -> savePropsToDefault.setDisable(false);
		timeToTestField.setOnAction(action);
		none.setOnAction(action);
		indicateAnswerQuality.setOnAction(e ->
		{
			action.handle(new ActionEvent());
			indicateAnswersQuality.setDisable(!indicateAnswerQuality.isSelected());
		});
		indicateAnswersQuality.setOnAction(action);
		skip.setOnAction(action);
		goToAllQuestions.setOnAction(action);
		pause.setOnAction(e ->
		{
			action.handle(new ActionEvent());
			pauseOnUnfocus.setDisable(!pause.isSelected());
		});
		pauseOnUnfocus.setOnAction(action);
		savePropsToDefault.setOnAction(event ->
		{
			try
			{
				if (!props.exists())
					props.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(props));
				String text = "";
				text += "pause: " + pause.isSelected();
				text += "\npauseOnUnfocus: " + pauseOnUnfocus.isSelected();
				text += "\nindicateAnswersQuality: " + indicateAnswersQuality.isSelected();
				text += "\ntestProps: " + "\"" + (goToAllQuestions.isSelected() ? "gtaq" : (indicateAnswerQuality.isSelected() ? "iaq" : "none")) + "\"";
				text += "\nskip: " + skip.isSelected();
				text += "\ntimeToTest: \"" + timeToTestField.getText() + "\"";
				writer.write(text);
				writer.close();
				savePropsToDefault.setDisable(true);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		});
		Config cfg = Config.getConfig(props);
		if (!cfg.getText().contains("pause") || !cfg.getText().contains("indicateAnswersQuality") || !cfg.getText().contains("skip") || !cfg.getText().contains(
				"testProps") || !cfg.getText().contains("timeToTest") || !cfg.getText().contains("pauseOnUnfocus"))
			savePropsToDefault.fire();
		cfg.getText(true);
		pause.setSelected(cfg.getBoolean("pause"));
		pauseOnUnfocus.setSelected(cfg.getBoolean("pauseOnUnfocus"));
		pauseOnUnfocus.setDisable(!cfg.getBoolean("pause"));
		none.setSelected(cfg.getString("testProps").equals("none"));
		indicateAnswerQuality.setSelected(cfg.getString("testProps").equals("iaq"));
		indicateAnswersQuality.setSelected(cfg.getBoolean("indicateAnswersQuality"));
		indicateAnswersQuality.setDisable(!cfg.getString("testProps").equals("iaq"));
		skip.setSelected(cfg.getBoolean("skip"));
		goToAllQuestions.setSelected(cfg.getString("testProps").equals("gtaq"));
		timeToTestField.setText(cfg.getString("timeToTest"));
		testMode.setOnAction(event ->
		{
			testPane.setVisible(true);
			statsPane.setVisible(false);
		});
		statsMode.setOnAction(event ->
		{
			testPane.setVisible(false);
			statsPane.setVisible(true);
		});
		getStats.setOnAction(event -> getStats());
		EventHandler<ActionEvent> actionHandler = (event) ->
		{
			msgSys.setLanguage(((RadioMenuItem) event.getSource()).getText().replace("(", ":").split(":")[1].replace(")", ":").split(":")[0]);
			changeLanguage();
		};
		privacyPolicy.setOnAction(e -> openPrivacyPolicyWindow());
		usersManual.setOnAction(e -> openUsersManualyWindow());
		languageEN.setOnAction(actionHandler);
		languageRU.setOnAction(actionHandler);
		switch (msgSys.getLanguage())
		{
			case "ru_ru":
				languageRU.fire();
				languageRU.setSelected(true);
				break;
			default:
				languageEN.fire();
				languageEN.setSelected(true);
				break;
		}

		File started;
		File[] files;
		{
			if (args.length == 0)
				args = new String[] { "" };

			if (args[0].endsWith(".test"))
			{
				files = null;
				started = new File(args[0]);
				fileNameComboBox.getItems().add(started.getName());
				fileNameComboBox.getSelectionModel().select(0);
				fileNameComboBox.setDisable(true);
			}
			else
			{
				started = null;
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
					JOptionPane.showMessageDialog(null, "In directory Tests not exists any files!", Main.programName, 0);
					Logger.exit(0);
				}
				files = testsDir.listFiles();
				for (File file : files)
					if (file.isFile() && file.getName().endsWith(".test"))
						fileNameComboBox.getItems().add(file.getName());
				fileNameComboBox.getSelectionModel().select(0);
			}
			if (fileNameComboBox.getItems().size() == 0)
			{
				JOptionPane.showMessageDialog(null, "In directory Tests not exists any files!", Main.programName, 0);
				Logger.exit(0);
			}
			fileNameComboBox.setPromptText(fileNameComboBox.getSelectionModel().getSelectedItem());
		}
		start.setOnAction(e ->
		{
			if (classField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Class field can't be empty!", Main.programName, 0);
				return;
			}
			if (surnameField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Surname field can't be empty!", Main.programName, 0);
				return;
			}
			if (nameField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Name field can't be empty!", Main.programName, 0);
				return;
			}
			if (secondNameField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Second name field can't be empty!", Main.programName, 0);
				return;
			}
			Question[] q = null;
			String name = null;
			String selectedFileName = fileNameComboBox.getSelectionModel().getSelectedItem();
			if (!fileNameComboBox.isDisable())
			{
				for (File f : files)
					if (f.getName().equals(selectedFileName))
					{
						q = parse(f);
						name = f.getName();
						break;
					}
			}
			else
			{
				q = parse(started);
				name = started.getName();
			}

			if (q == null)
			{
				JOptionPane.showMessageDialog(null, "File '" + selectedFileName + "' not found!", Main.programName, 0);
				Logger.exit(5);
			}
			else if (q.length == 0)
			{
				JOptionPane.showMessageDialog(null, "File '" + selectedFileName + "' is empty!", Main.programName, 0);
				Logger.exit(5);
			}
			new Main(new Rectangle((int) stage.getX(), (int) stage.getY(), (int) stage.getWidth(), (int) stage.getHeight()), name.substring(0, name.length()
					- 5), q, classComboBox.getSelectionModel().getSelectedItem() + classField.getText(), surnameField.getText(), nameField.getText(),
					secondNameField.getText(), Integer.parseInt(timeToTestField.getText()) * 60, pause.isSelected(), indicateAnswerQuality.isSelected(),
					indicateAnswersQuality.isSelected() && !indicateAnswersQuality.isDisable(), skip.isSelected(), goToAllQuestions.isSelected()
							&& !goToAllQuestions.isDisable(), pauseOnUnfocus.isSelected());
			stage.close();

		});
	}

	private static AnchorPane panel;
	private static Scene scene;
	private static Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		stage = primaryStage;
		try
		{
			panel = FXMLLoader.load(getClass().getResource("/StartBlank.fxml"));
			scene = new Scene(panel, 400, 600);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.sizeToScene();
			stage.getIcons().clear();
			stage.getIcons().add(new Image(getClass().getResource("/Icon16x.png").openStream()));
			stage.getIcons().add(new Image(getClass().getResource("/Icon32x.png").openStream()));
			stage.getIcons().add(new Image(getClass().getResource("/Icon48x.png").openStream()));
			stage.setTitle(Main.programName);
			stage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void getStats()
	{
		float minResult = Float.MAX_VALUE, maxResult = 0, result = 0, averageResult = 0, perfectResult = 0;
		float questions = 0;
		float minRightAnswers = Float.MAX_VALUE, maxRightAnswers = 0, rightAnswers = 0, averageRightAnswers = 0;
		float minPerfectAnswers = Float.MAX_VALUE, maxPerfectAnswers = 0, perfectAnswers = 0, averagePerfectAnswers = 0;
		float minTime = Float.MAX_VALUE, maxTime = 0, time = 0, averageTime = 0, perfectTime = 0;
		int testsCount = 0;
		for (Config cfg : Statistics.getStats(!fileNameCheckBox.isSelected() ? null : (String) fileNameComboBox.getSelectionModel().getSelectedItem(),
				!classCheckBox.isSelected() ? null : (classComboBox.getSelectionModel().getSelectedItem() + "-" + classField.getText()), !surnameCheckBox
						.isSelected() ? null : surnameField.getText(), !nameCheckBox.isSelected() ? null : nameField.getText(), !secondNameCheckBox.isSelected()
								? null : secondNameField.getText()))
		{
			testsList.getTabs().add(new Tab(cfg.getFile().getName().replace("Result From ", "").replace(".log", ""), new TextArea(cfg.getText(false))));

			String language = cfg.getString("language");
			result += cfg.getInteger(MessageSystem.getMsg("result", language));
			minResult = Math.min(minResult, cfg.getInteger(MessageSystem.getMsg("result", language)));
			maxResult = Math.max(maxResult, cfg.getInteger(MessageSystem.getMsg("result", language)));
			perfectResult += cfg.getInteger(MessageSystem.getMsg("maxResult", language));

			perfectAnswers += cfg.getInteger(MessageSystem.getMsg("perfectAnswersAmount", language));
			minPerfectAnswers = Math.min(minPerfectAnswers, cfg.getInteger(MessageSystem.getMsg("perfectAnswersAmount", language)));
			maxPerfectAnswers = Math.max(maxPerfectAnswers, cfg.getInteger(MessageSystem.getMsg("perfectAnswersAmount", language)));

			rightAnswers += cfg.getInteger(MessageSystem.getMsg("rightAnswersAmount", language));
			minRightAnswers = Math.min(minRightAnswers, cfg.getInteger(MessageSystem.getMsg("rightAnswersAmount", language)));
			maxRightAnswers = Math.max(maxRightAnswers, cfg.getInteger(MessageSystem.getMsg("rightAnswersAmount", language)));

			questions += cfg.getInteger(MessageSystem.getMsg("questionsAmount", language));

			time += cfg.getTime(MessageSystem.getMsg("time", language));
			minTime = Math.min(minTime, cfg.getTime(MessageSystem.getMsg("time", language)));
			maxTime = Math.max(maxTime, cfg.getTime(MessageSystem.getMsg("time", language)));
			perfectTime += cfg.getTime(MessageSystem.getMsg("timeToTest" + "", language));

			testsCount++;
		}
		averageResult = result / testsCount;
		perfectResult /= testsCount;

		averageRightAnswers = rightAnswers / testsCount;
		averagePerfectAnswers = perfectAnswers / testsCount;
		questions /= testsCount;

		averageTime = time / testsCount;
		perfectTime /= testsCount;

		if (inPercents.isSelected())
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

		if (inPercents.isSelected())
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

		if (inPercents.isSelected())
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

		if (inPercents.isSelected())
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
	}

	public String numberToString(double number, int size)
	{
		double number2 = (double) ((int) (number * Math.pow(10, size))) / Math.pow(10, size);
		if ((int) number2 == number2)
			return (int) number2 + "";
		return number2 + "";
	}

	public void changeLanguage()
	{
		window.setText(msgSys.getMsg("window"));
		{
			testMode.setText(msgSys.getMsg("testMode"));
			statsMode.setText(msgSys.getMsg("statsMode"));
		}
		settings.setText(msgSys.getMsg("settings"));
		{
			language.setText(msgSys.getMsg("language"));
		}
		help.setText(msgSys.getMsg("help"));
		{
			privacyPolicy.setText(msgSys.getMsg("privacyPolicy"));
			usersManual.setText(msgSys.getMsg("usersManual"));
		}

		fileNameLabel.setText(msgSys.getMsg("testFileName"));
		classLabel.setText(msgSys.getMsg("class"));
		surnameLabel.setText(msgSys.getMsg("surname"));
		nameLabel.setText(msgSys.getMsg("name"));
		secondNameLabel.setText(msgSys.getMsg("secondName"));
		timeToTestLabel.setText(msgSys.getMsg("timeToTest"));
		indicateAnswerQuality.setText(msgSys.getMsg("indicateAnswerQuality"));
		start.setText(msgSys.getMsg("start"));

		getStats.setText(msgSys.getMsg("getStats"));
		inPercents.setText(msgSys.getMsg("inPercents"));
		inFractions.setText(msgSys.getMsg("inFractions"));

		smallest.setText(msgSys.getMsg("smallest"));
		average.setText(msgSys.getMsg("average"));
		biggest.setText(msgSys.getMsg("biggest"));
		max.setText(msgSys.getMsg("max"));
		all.setText(msgSys.getMsg("all"));
		score.setText(msgSys.getMsg("score"));
		rightAnswers.setText(msgSys.getMsg("rightAnswers"));
		perfectAnswers.setText(msgSys.getMsg("perfectAnswers"));
		time.setText(msgSys.getMsg("time"));

		none.setText(msgSys.getMsg("none"));
		skip.setText(msgSys.getMsg("skipBtn"));
		goToAllQuestions.setText(msgSys.getMsg("goToAllQuestions"));
		indicateAnswerQuality.setText(msgSys.getMsg("indicateAnswerQuality"));
		indicateAnswersQuality.setText(msgSys.getMsg("indicateAnswersQuality"));
		savePropsToDefault.setText(msgSys.getMsg("savePropsToDefault"));
		pause.setText(msgSys.getMsg("pause"));
		pauseOnUnfocus.setText(msgSys.getMsg("pauseOnUnfocus"));

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

	static JDialog d;

	public static void openPrivacyPolicyWindow()
	{
		Color color = new Color(220, 220, 230);
		JOptionPane pane = new JOptionPane(null, -1, 0, null, new Object[0], null);
		pane.removeAll();
		pane.setBackground(color);
		pane.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setSize(512 - 8, 512 - 30);
		textArea.setEditable(false);
		textArea.setBackground(Color.white);
		textArea.setText(msgSys.getMsg("privacyPolicyText"));
		textArea.setColumns(textArea.getWidth() / textArea.getFont().getSize());
		textArea.setRows(textArea.getHeight() / (int) (textArea.getFont().getSize() * 1.5));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		pane.add(textArea);

		d = pane.createDialog(msgSys.getMsg("help") + "/" + msgSys.getMsg("privacyPolicy"));
		d.setLocationRelativeTo(null);
		d.setSize(512, 512);

		d.setVisible(true);
	}

	public static void openUsersManualyWindow()
	{
		Color color = new Color(220, 220, 230);
		JOptionPane pane = new JOptionPane(null, -1, 0, null, new Object[0], null);
		pane.removeAll();
		pane.setBackground(color);
		pane.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setSize(512 - 8, 512 - 30);
		textArea.setEditable(false);
		textArea.setBackground(Color.white);
		textArea.setText(msgSys.getMsg("usersManualText"));
		textArea.setColumns(textArea.getWidth() / textArea.getFont().getSize());
		textArea.setRows(textArea.getHeight() / (int) (textArea.getFont().getSize() * 1.5));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		pane.add(textArea);

		d = pane.createDialog(msgSys.getMsg("help") + "/" + msgSys.getMsg("usersManual"));
		d.setLocationRelativeTo(null);
		d.setSize(512, 512);

		d.setVisible(true);
	}

	public static Theme theme;

	/**
	 * 
	 * @param f
	 * @return parsed to Question array configuration file
	 */
	public static Question[] parse(File f)
	{
		if (!f.exists())
			return null;
		try
		{
			Config cfg = Config.getConfig(f);
			cfg.getText(true);
			if (!cfg.hasValue("version") || !cfg.hasValue("syntaxLanguage") || !cfg.hasValue("colorType"))
			{
				TabParser.print("Syntax is wrong: .test file must contains properties - 'syntaxLanguage', 'colorType' and 'version'!");
				Logger.exit(10);
			}
			String syntaxLanguage = cfg.getString("syntaxLanguage").toLowerCase();
			switch (syntaxLanguage)
			{
				case "ru_ru":
				case "en_uk":
					break;
				default:
					TabParser.print("Syntax language '" + syntaxLanguage + "' is not supported!");
					Logger.exit(11);
					break;
			}
			if (!cfg.getString("version").equals(Main.version))
				JOptionPane.showMessageDialog(null, ".test file version does not match with version of this program, this can create problems in work!");

			// String colorType=cfg.getString("colorType");

			Theme theme = new Theme();
			String themeS = MessageSystem.getMsg("theme", syntaxLanguage) + ":";
			String windowS = MessageSystem.getMsg("window", syntaxLanguage) + ":";
			String backgroundS = MessageSystem.getMsg("background", syntaxLanguage) + ":";
			String foregroundS = MessageSystem.getMsg("foreground", syntaxLanguage) + ":";
			String frameS = MessageSystem.getMsg("frame", syntaxLanguage) + ":";
			String redS = MessageSystem.getMsg("red", syntaxLanguage);
			String greenS = MessageSystem.getMsg("green", syntaxLanguage);
			String blueS = MessageSystem.getMsg("blue", syntaxLanguage);
			String questionS = MessageSystem.getMsg("question", syntaxLanguage) + ":";
			String answersS = MessageSystem.getMsg("answers", syntaxLanguage) + ":";
			String pickOneS = MessageSystem.getMsg("pickOne", syntaxLanguage) + ":";
			String selectSomeS = MessageSystem.getMsg("selectSome", syntaxLanguage) + ":";
			String enterTextS = MessageSystem.getMsg("enterText", syntaxLanguage) + ":";
			String questionSelectS = MessageSystem.getMsg("questionSelect", syntaxLanguage) + ":";
			String specialButtonsS = MessageSystem.getMsg("specialButtons", syntaxLanguage) + ":";
			String normalS = MessageSystem.getMsg("normal", syntaxLanguage) + ":";
			String skippedS = MessageSystem.getMsg("skipped", syntaxLanguage) + ":";

			if (cfg.hasValue(themeS + windowS + backgroundS))
				theme.windowBackground = new Color(cfg.getInteger(themeS + windowS + backgroundS + redS), cfg.getInteger(themeS + windowS + backgroundS
						+ greenS), cfg.getInteger(themeS + windowS + backgroundS + blueS));

			if (cfg.hasValue(themeS + pickOneS + questionS + backgroundS))
				theme.pickOneQuestionBackground = new Color(cfg.getInteger(themeS + pickOneS + questionS + backgroundS + redS), cfg.getInteger(themeS + pickOneS
						+ questionS + backgroundS + greenS), cfg.getInteger(themeS + pickOneS + questionS + backgroundS + blueS));
			if (cfg.hasValue(themeS + pickOneS + questionS + foregroundS))
				theme.pickOneQuestionForeground = new Color(cfg.getInteger(themeS + pickOneS + questionS + foregroundS + redS), cfg.getInteger(themeS + pickOneS
						+ questionS + foregroundS + greenS), cfg.getInteger(themeS + pickOneS + questionS + foregroundS + blueS));
			if (cfg.hasValue(themeS + pickOneS + questionS + frameS))
				theme.pickOneQuestionFrame = new Color(cfg.getInteger(themeS + pickOneS + questionS + frameS + redS), cfg.getInteger(themeS + pickOneS
						+ questionS + frameS + greenS), cfg.getInteger(themeS + pickOneS + questionS + frameS + blueS));
			if (cfg.hasValue(themeS + pickOneS + answersS + backgroundS))
				theme.pickOneAnswersBackground = new Color(cfg.getInteger(themeS + pickOneS + answersS + backgroundS + redS), cfg.getInteger(themeS + pickOneS
						+ answersS + backgroundS + greenS), cfg.getInteger(themeS + pickOneS + answersS + backgroundS + blueS));
			if (cfg.hasValue(themeS + pickOneS + answersS + foregroundS))
				theme.pickOneAnswersForeground = new Color(cfg.getInteger(themeS + pickOneS + answersS + foregroundS + redS), cfg.getInteger(themeS + pickOneS
						+ answersS + foregroundS + greenS), cfg.getInteger(themeS + pickOneS + answersS + foregroundS + blueS));
			if (cfg.hasValue(themeS + pickOneS + answersS + frameS))
				theme.pickOneAnswersFrame = new Color(cfg.getInteger(themeS + pickOneS + answersS + frameS + redS), cfg.getInteger(themeS + pickOneS + answersS
						+ frameS + greenS), cfg.getInteger(themeS + pickOneS + answersS + frameS + blueS));

			if (cfg.hasValue(themeS + selectSomeS + questionS + backgroundS))
				theme.selectSomeQuestionBackground = new Color(cfg.getInteger(themeS + selectSomeS + questionS + backgroundS + redS), cfg.getInteger(themeS
						+ selectSomeS + questionS + backgroundS + greenS), cfg.getInteger(themeS + selectSomeS + questionS + backgroundS + blueS));
			if (cfg.hasValue(themeS + selectSomeS + questionS + foregroundS))
				theme.selectSomeQuestionForeground = new Color(cfg.getInteger(themeS + selectSomeS + questionS + foregroundS + redS), cfg.getInteger(themeS
						+ selectSomeS + questionS + foregroundS + greenS), cfg.getInteger(themeS + selectSomeS + questionS + foregroundS + blueS));
			if (cfg.hasValue(themeS + selectSomeS + questionS + frameS))
				theme.selectSomeQuestionFrame = new Color(cfg.getInteger(themeS + selectSomeS + questionS + frameS + redS), cfg.getInteger(themeS + selectSomeS
						+ questionS + frameS + greenS), cfg.getInteger(themeS + selectSomeS + questionS + frameS + blueS));
			if (cfg.hasValue(themeS + selectSomeS + answersS + backgroundS))
				theme.selectSomeAnswersBackground = new Color(cfg.getInteger(themeS + selectSomeS + answersS + backgroundS + redS), cfg.getInteger(themeS
						+ selectSomeS + answersS + backgroundS + greenS), cfg.getInteger(themeS + selectSomeS + answersS + backgroundS + blueS));
			if (cfg.hasValue(themeS + selectSomeS + answersS + foregroundS))
				theme.selectSomeAnswersForeground = new Color(cfg.getInteger(themeS + selectSomeS + answersS + foregroundS + redS), cfg.getInteger(themeS
						+ selectSomeS + answersS + foregroundS + greenS), cfg.getInteger(themeS + selectSomeS + answersS + foregroundS + blueS));
			if (cfg.hasValue(themeS + selectSomeS + answersS + frameS))
				theme.selectSomeAnswersFrame = new Color(cfg.getInteger(themeS + selectSomeS + answersS + frameS + redS), cfg.getInteger(themeS + selectSomeS
						+ answersS + frameS + greenS), cfg.getInteger(themeS + selectSomeS + answersS + frameS + blueS));

			if (cfg.hasValue(themeS + enterTextS + questionS + backgroundS))
				theme.enterTextQuestionBackground = new Color(cfg.getInteger(themeS + enterTextS + questionS + backgroundS + redS), cfg.getInteger(themeS
						+ enterTextS + questionS + backgroundS + greenS), cfg.getInteger(themeS + enterTextS + questionS + backgroundS + blueS));
			if (cfg.hasValue(themeS + enterTextS + questionS + foregroundS))
				theme.enterTextQuestionForeground = new Color(cfg.getInteger(themeS + enterTextS + questionS + foregroundS + redS), cfg.getInteger(themeS
						+ enterTextS + questionS + foregroundS + greenS), cfg.getInteger(themeS + enterTextS + questionS + foregroundS + blueS));
			if (cfg.hasValue(themeS + enterTextS + questionS + frameS))
				theme.enterTextQuestionFrame = new Color(cfg.getInteger(themeS + enterTextS + questionS + frameS + redS), cfg.getInteger(themeS + enterTextS
						+ questionS + frameS + greenS), cfg.getInteger(themeS + enterTextS + questionS + frameS + blueS));
			if (cfg.hasValue(themeS + enterTextS + answersS + backgroundS))
				theme.enterTextAnswersBackground = new Color(cfg.getInteger(themeS + enterTextS + answersS + backgroundS + redS), cfg.getInteger(themeS
						+ enterTextS + answersS + backgroundS + greenS), cfg.getInteger(themeS + enterTextS + answersS + backgroundS + blueS));
			if (cfg.hasValue(themeS + enterTextS + answersS + foregroundS))
				theme.enterTextAnswersForeground = new Color(cfg.getInteger(themeS + enterTextS + answersS + foregroundS + redS), cfg.getInteger(themeS
						+ enterTextS + answersS + foregroundS + greenS), cfg.getInteger(themeS + enterTextS + answersS + foregroundS + blueS));
			if (cfg.hasValue(themeS + enterTextS + answersS + frameS))
				theme.enterTextAnswersFrame = new Color(cfg.getInteger(themeS + enterTextS + answersS + frameS + redS), cfg.getInteger(themeS + enterTextS
						+ answersS + frameS + greenS), cfg.getInteger(themeS + enterTextS + answersS + frameS + blueS));

			if (cfg.hasValue(themeS + specialButtonsS + backgroundS))
				theme.specialButtonsBackground = new Color(cfg.getInteger(themeS + specialButtonsS + backgroundS + redS), cfg.getInteger(themeS
						+ specialButtonsS + backgroundS + greenS), cfg.getInteger(themeS + specialButtonsS + backgroundS + blueS));
			if (cfg.hasValue(themeS + specialButtonsS + foregroundS))
				theme.specialButtonsForeground = new Color(cfg.getInteger(themeS + specialButtonsS + foregroundS + redS), cfg.getInteger(themeS
						+ specialButtonsS + foregroundS + greenS), cfg.getInteger(themeS + specialButtonsS + foregroundS + blueS));
			if (cfg.hasValue(themeS + specialButtonsS + frameS))
				theme.specialButtonsFrame = new Color(cfg.getInteger(themeS + specialButtonsS + frameS + redS), cfg.getInteger(themeS + specialButtonsS + frameS
						+ greenS), cfg.getInteger(themeS + specialButtonsS + frameS + blueS));

			if (cfg.hasValue(themeS + questionSelectS + normalS + backgroundS))
				theme.questionSelectNormalBackground = new Color(cfg.getInteger(themeS + questionSelectS + normalS + backgroundS + redS), cfg.getInteger(themeS
						+ questionSelectS + normalS + backgroundS + greenS), cfg.getInteger(themeS + questionSelectS + normalS + backgroundS + blueS));
			if (cfg.hasValue(themeS + questionSelectS + normalS + foregroundS))
				theme.questionSelectNormalForeground = new Color(cfg.getInteger(themeS + questionSelectS + normalS + foregroundS + redS), cfg.getInteger(themeS
						+ questionSelectS + normalS + foregroundS + greenS), cfg.getInteger(themeS + questionSelectS + normalS + foregroundS + blueS));
			if (cfg.hasValue(themeS + questionSelectS + normalS + frameS))
				theme.questionSelectNormalFrame = new Color(cfg.getInteger(themeS + questionSelectS + normalS + frameS + redS), cfg.getInteger(themeS
						+ questionSelectS + normalS + frameS + greenS), cfg.getInteger(themeS + questionSelectS + normalS + frameS + blueS));
			if (cfg.hasValue(themeS + questionSelectS + skippedS + backgroundS))
				theme.questionSelectSkippedBackground = new Color(cfg.getInteger(themeS + questionSelectS + skippedS + backgroundS + redS), cfg.getInteger(
						themeS + questionSelectS + skippedS + backgroundS + greenS), cfg.getInteger(themeS + questionSelectS + skippedS + backgroundS + blueS));
			if (cfg.hasValue(themeS + questionSelectS + skippedS + foregroundS))
				theme.questionSelectSkippedForeground = new Color(cfg.getInteger(themeS + questionSelectS + skippedS + foregroundS + redS), cfg.getInteger(
						themeS + questionSelectS + skippedS + foregroundS + greenS), cfg.getInteger(themeS + questionSelectS + skippedS + foregroundS + blueS));
			if (cfg.hasValue(themeS + questionSelectS + skippedS + frameS))
				theme.questionSelectSkippedFrame = new Color(cfg.getInteger(themeS + questionSelectS + skippedS + frameS + redS), cfg.getInteger(themeS
						+ questionSelectS + skippedS + frameS + greenS), cfg.getInteger(themeS + questionSelectS + skippedS + frameS + blueS));

			FXFrame.theme = theme;

			TabParser parser = new TabParser();
			String qnsStr = MessageSystem.getMsg("questions", syntaxLanguage);
			String qnStr = MessageSystem.getMsg("question", syntaxLanguage);
			String anrsStr = MessageSystem.getMsg("answers", syntaxLanguage);
			String ansStr = MessageSystem.getMsg("answer", syntaxLanguage);
			String awdStr = MessageSystem.getMsg("award", syntaxLanguage);
			String potStr = MessageSystem.getMsg("questionType", syntaxLanguage);
			String txtStr = MessageSystem.getMsg("text", syntaxLanguage);
			String fsStr = MessageSystem.getMsg("fontSize", syntaxLanguage);
			String minResStr = MessageSystem.getMsg("minimalResult", syntaxLanguage);
			String igreCaseStr = MessageSystem.getMsg("ignoreCase", syntaxLanguage);
			String igrdChrsStr = MessageSystem.getMsg("ingnoredCharacters", syntaxLanguage);
			int questionsToTestAmount = cfg.getInteger(qnsStr + ":" + MessageSystem.getMsg("questionsToTestAmount", syntaxLanguage));
			int dqFont = cfg.safetyGetInteger(qnsStr + ":" + MessageSystem.getMsg("fontSize", syntaxLanguage), 10);
			int daFont = cfg.safetyGetInteger(qnsStr + ":" + MessageSystem.getMsg("answerFontSize", syntaxLanguage), 16);
			int stMinRes = cfg.safetyGetInteger(qnsStr + ":" + minResStr, Integer.MIN_VALUE);
			ArrayList<Question> questions = new ArrayList<Question>();
			String s = qnsStr + ":" + qnStr;
			String question = cfg.getObject(s + 1, true);
			for (int i = 0; cfg.hasValue(s + (i + 1)); i++, question = cfg.getObject(s + (i + 1), true))
			{
				int dqaFont = parser.safetyGetInteger(question, anrsStr + ":" + MessageSystem.getMsg("fontSize", syntaxLanguage), daFont);
				ArrayList<Answer> answers = new ArrayList<Answer>();
				String answer = parser.getObject(question, anrsStr + ":" + ansStr + 1, true);
				for (int j = 0; parser.hasValue(question, anrsStr + ":" + ansStr + (j + 1)); j++, answer = parser.getObject(question, anrsStr + ":" + ansStr
						+ (j + 1), true))
					answers.add(new Answer(parser.getString(answer, txtStr).replace("\\n", "\n"), new Font("Ms Comic Sans", 0, parser.safetyGetInteger(answer,
							fsStr, dqaFont)), parser.safetyGetInteger(answer, awdStr, 0)));
				QuestionType type;
				switch (parser.safetyGetString(question, potStr, "PickOne"))
				{
					case "EnterText":
						type = QuestionType.EnterText;
						break;
					case "PickOne":
						type = QuestionType.PickOne;
						break;
					case "SelectSome":
						type = QuestionType.SelectSome;
						break;
					default:
						TabParser.print("Wrong syntax: " + parser.safetyGetString(question, potStr, "PickOne")
								+ " must be 'EnterText', 'PickOne' or 'SelectSome'!");
						Logger.exit(18);
						return null;

				}

				questions.add(new Question(parser.getString(question, txtStr), new Font("Ms Comic Sans", 0, parser.safetyGetInteger(question, fsStr, dqFont)),
						parser.safetyGetInteger(question, awdStr, 0), parser.safetyGetInteger(question, minResStr, stMinRes), type, randomizeToArray(answers,
								new Answer[answers.size()]), parser.safetyGetString(question, igrdChrsStr, ""), parser.safetyGetBoolean(question, igreCaseStr,
										true)));
			}
			return randomizeToArray(questions, new Question[questionsToTestAmount]);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			Logger.exit(-1);
		}
		return new Question[0];
	}

	@SuppressWarnings("unchecked")
	public static <Type> Type[] randomizeToArray(ArrayList<Type> list, Type[] array)
	{
		list = (ArrayList<Type>) list.clone();
		for (int i = 0; i < array.length; i++)
			array[i] = list.remove(random.nextInt(list.size()));
		return array;
	}
}
