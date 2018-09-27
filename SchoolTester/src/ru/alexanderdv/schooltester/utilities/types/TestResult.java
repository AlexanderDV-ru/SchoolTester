package ru.alexanderdv.schooltester.utilities.types;

import java.io.Serializable;
import java.util.Date;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.alexanderdv.fxutilities.components.TextFieldWithHints;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.network.OnlineLoginsPacket;
import ru.alexanderdv.schooltester.utilities.questionvars.Result;
import ru.alexanderdv.schooltester.utilities.types.Account.AccountType;
import ru.alexanderdv.simpleutilities.ChangeListener;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public class TestResult implements Serializable
{
	private static final long serialVersionUID = 4251259265631478887L;
	private static final MessageSystem msgSys = Main.msgSys;
	private final Date date;
	private final String testName;
	private final TesteeInfo testeeInfo;
	private final TesterInfo testerInfo;
	private final TestSettings testSettings;
	private final TestingPartSettings testingPartSettings;

	private final double timeLimit;
	private final double timeOfTest;
	private final double fullTime;

	private final Result<?>[] results;

	/**
	 * @param date
	 * @param testName
	 * @param testeeInfo
	 * @param testerInfo
	 * @param testSettings
	 * @param testingPartSettings
	 * @param timeLimit
	 * @param timeOfTest
	 * @param fullTime
	 * @param results
	 */
	public TestResult(Date date, String testName, TesteeInfo testeeInfo, TesterInfo testerInfo,
			TestSettings testSettings, TestingPartSettings testingPartSettings, float timeLimit, float timeOfTest,
			float fullTime, Result<?>[] results)
	{
		this.date = date;
		this.testName = testName;
		this.testeeInfo = testeeInfo;
		this.testerInfo = testerInfo;
		this.testSettings = testSettings;
		this.testingPartSettings = testingPartSettings;
		this.timeLimit = timeLimit;
		this.timeOfTest = timeOfTest;
		this.fullTime = fullTime;
		this.results = results;
	}

	/**
	 * @return the date
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * @return the testName
	 */
	public String getTestName()
	{
		return testName;
	}

	/**
	 * @return the testeeInfo
	 */
	public TesteeInfo getTesteeInfo()
	{
		return testeeInfo;
	}

	/**
	 * @return the testerInfo
	 */
	public TesterInfo getTesterInfo()
	{
		return testerInfo;
	}

	/**
	 * @return the testSettings
	 */
	public TestSettings getTestSettings()
	{
		return testSettings;
	}

	/**
	 * @return the testingPartSettings
	 */
	public TestingPartSettings getTestingPartSettings()
	{
		return testingPartSettings;
	}

	/**
	 * @return the timeLimit
	 */
	public double getTimeLimit()
	{
		return timeLimit;
	}

	/**
	 * @return the timeOfTest
	 */
	public double getTimeOfTest()
	{
		return timeOfTest;
	}

	/**
	 * @return the fullTime
	 */
	public double getFullTime()
	{
		return fullTime;
	}

	/**
	 * @return the results
	 */
	public Result<?>[] getResults()
	{
		return results;
	}

	public String toShortText()
	{
		return toShortText(msgSys.getLanguage());
	}

	public String toShortText(String language)
	{
		String shortText = "";
		shortText += "Date: " + date.toString() + "\n";
		shortText += "Test name: " + testName + "\n";
		shortText += "Testee info: " + testeeInfo.toString() + "\n";
		shortText += "Tester info: " + testerInfo.toString() + "\n";
		shortText += "Test settings: " + testSettings.toString() + "\n";
		shortText += "Testing part settings: " + testingPartSettings.toString() + "\n";
		shortText += "Time limit - " + toTime(timeLimit) + "\n";
		shortText += "Time of test - " + toTime(timeOfTest) + "\n";
		shortText += "Full time - " + toTime(fullTime) + "\n";
		int passable = 0, correct = 0;
		for (Result result : results)
			if (result != null)
				if (result.getResult() > result.getMinResult() && result.getResult() < result.getMaxResult())
					passable++;
				else if (result.getResult() == result.getMaxResult())
					correct++;
				else if (result.getResult() > result.getMaxResult())
				{
					correct++;
					Main.error("Result (" + result.getResult() + ") > Max result (" + result.getMaxResult() + ")");
				}
		shortText += "Passable answers: " + passable + "\n";
		shortText += "Correct answers: " + correct + "\n";
		shortText += "Questions: " + results.length + "\n";
		return shortText;
	}

	private String toTime(double time)
	{
		if (time == -1)
			return "--:-- -";
		return toSize((int) ((time - time % 60) / 60), 2) + ":" + toSize((int) (time % 60 - time % 1), 2) + " "
				+ getMillis(time);
	}

	private String getMillis(double time)
	{
		double res = time % 1;
		for (; res != res - res % 1;)
			res *= 10;
		return "" + (int) res;
	}

	private String toSize(int i, int size)
	{
		String res = i + "";
		for (; res.length() < size;)
			res = "0" + res;
		return res;
	}

	public String toText()
	{
		return toText(msgSys.getLanguage());
	}

	public String toText(String language)
	{
		String text = toShortText(language) + "\n";
		text += "\n";
		return text;
	}

	public void show(Stage parent, ChangeListener<String> resultSending)
	{
		String shortText = toShortText();
		String text = toText();
		Main.sendToServer(new OnlineLoginsPacket("get", null));
		TextFieldWithHints textFieldWithHints = new TextFieldWithHints();
		textFieldWithHints.setAlignment(Pos.BASELINE_CENTER);
		textFieldWithHints.setOnAction(e ->
		{
			if (resultSending != null)
				resultSending.handle(textFieldWithHints.getText());
		});
		textFieldWithHints.setVisible(resultSending != null);
		Main.onlineLogins.clearChangeListeners();
		Main.onlineLogins.addChangeListener(obj ->
		{
			textFieldWithHints.getItems().clear();
			for (String s : Main.onlineLogins.get().keySet())
				if (Main.onlineLogins.get().get(s) == AccountType.Teacher)
					textFieldWithHints.getItems().add(s);
		});
		Button showFull = new Button(msgSys.getMsg("showFullResult"));
		showFull.setPrefSize(showFull.getLayoutBounds().getWidth() + 10, 25);
		showFull.setLayoutY(25);
		showFull.setAlignment(Pos.BASELINE_CENTER);
		Pane pane = new Pane(textFieldWithHints, showFull);

		pane.setPrefSize(textFieldWithHints.getPrefWidth(), 50);
		FXDialogsGenerator.showFXDialog(parent, shortText, pane, true);
	}

	public double[] getNumberProp(String name)
	{
		switch (name.toLowerCase())
		{
			case "timeoftest":
				return new double[] { 0, timeOfTest, timeLimit };
			case "fulltime":
				return new double[] { 0, fullTime, Short.MAX_VALUE };
			default:
				return new double[0];
		}

	}

}