package ru.alexanderdv.schooltester.main.student;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ru.alexanderdv.fxutilities.components.ButtonFX;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.main.StartPart;
import ru.alexanderdv.schooltester.main.teacher.TeachersTestsControlPart.Test;
import ru.alexanderdv.schooltester.utilities.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.Logger.ExitCodes;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.ProtectedFXWindow;
import ru.alexanderdv.schooltester.utilities.Question;
import ru.alexanderdv.schooltester.utilities.Question.Answer;
import ru.alexanderdv.schooltester.utilities.Question.QuestionType;
import ru.alexanderdv.schooltester.utilities.UserAnswer;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.5.0a
 */
public class StudentsTestingPartFX extends ProtectedFXWindow
{

	private static final MessageSystem msgSys = StartPart.msgSys;
	private Test test;
	private UserAnswer[] answers;
	private int[] buttonsArrangement;
	private ArrayList<Integer> skippedQuestions;
	private int currentQuestionNumber;
	private int lastNotSkippedQuestion;
	private boolean[] selectedAnswers;
	private boolean indicateQualityOfLastAnswer, indicateQualityOfAllAnswers, showRightAnswer, canGoToAllQuestions, skipButtonOption, pauseOption,
			pauseOnUnfocus, fixedQSelectBtnHeight, hide;
	private boolean paused;
	private int timeToTest;
	private float timeOfTest, fullTime;
	private int rightAnswers, perfectAnswers;
	private int timeToPause;
	private long lastTime;
	private boolean wasGoToEnd;
	private Pane questionSelectPanel, answersPanel;
	private ButtonFX[] questionSelectAnswerButtons;
	private ButtonFX questionSign;
	private ButtonFX[] answerButtons;
	private ButtonFX timer, skip, next, finish;
	private Pane qualityIndicator;
	private Pane[] qualityIndicators;
	private boolean finished;

	/**
	 * 
	 * @param test.getTheme()
	 * @param parentPosition
	 * @param testName
	 * @param test.getQuestions()
	 * @param classNumber
	 * @param classLetter
	 * @param surname
	 * @param name
	 * @param secondName
	 * @param timeToTest
	 * @param indicateQualityOfLastAnswer
	 * @param indicateQualityOfAllAnswers
	 * @param showRightAnswer
	 * @param canGoToAllQuestions
	 * @param skipButtonOption
	 * @param pauseOption
	 * @param pauseOnUnfocus
	 * @param anticopy
	 * @param antiscreenshot
	 */
	public StudentsTestingPartFX(String secondaryTitle, URL url, Rectangle parentPosition, final Test test, String classNumber, String classLetter,
			String surname, String name, String secondName, boolean indicateQualityOfLastAnswer, boolean indicateQualityOfAllAnswers, boolean showRightAnswer,
			boolean canGoToAllQuestions, boolean skipButtonOption, boolean pauseOption, boolean pauseOnUnfocus, boolean anticopy, boolean antiscreenshot,
			boolean fixedQSelectBtnHeight, boolean hide)
	{
		super(secondaryTitle, url, 0, 1);

		this.questionSelectPanel = InitStudentsTestingPart.instance.questionSelectPanel;
		this.answersPanel = InitStudentsTestingPart.instance.answersPanel;
		this.questionSign = InitStudentsTestingPart.instance.questionSign;
		this.answerButtons = new ButtonFX[6];
		this.timer = InitStudentsTestingPart.instance.timer;
		this.skip = InitStudentsTestingPart.instance.skip;
		this.next = InitStudentsTestingPart.instance.next;
		this.finish = InitStudentsTestingPart.instance.finish;
		this.qualityIndicator = InitStudentsTestingPart.instance.qualityIndicator;
		this.answersPanel = InitStudentsTestingPart.instance.answersPanel;

		skippedQuestions = new ArrayList<Integer>();
		answers = new UserAnswer[test.getQuestions().length];
		this.timeToTest = test.getMaxTestTime();
		this.test = test;
		this.indicateQualityOfLastAnswer = indicateQualityOfLastAnswer;
		this.indicateQualityOfAllAnswers = indicateQualityOfAllAnswers;
		this.showRightAnswer = showRightAnswer;
		this.canGoToAllQuestions = canGoToAllQuestions;
		this.skipButtonOption = skipButtonOption;
		this.pauseOption = pauseOption;
		this.pauseOnUnfocus = pauseOnUnfocus;
		this.fixedQSelectBtnHeight = fixedQSelectBtnHeight;
		this.hide = hide;
		ButtonFX.anticntrlc = anticopy;
		ButtonFX.antiscreenshot = antiscreenshot;
		if (antiscreenshot)
		{
			class ImageSelection implements Transferable
			{
				private Image image;

				public ImageSelection(Image image)
				{
					this.image = image;
				}

				public DataFlavor[] getTransferDataFlavors()
				{
					return new DataFlavor[]
					{
							DataFlavor.imageFlavor
					};
				}

				public boolean isDataFlavorSupported(DataFlavor flavor)
				{
					return DataFlavor.imageFlavor.equals(flavor);
				}

				public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
				{
					if (!DataFlavor.imageFlavor.equals(flavor))
						throw new UnsupportedFlavorException(flavor);
					return image;
				}
			}
			ButtonFX.antish1 = e ->
			{
				if (e.getCode() == KeyCode.PRINTSCREEN)
				{
					FXDialogsGenerator.showFXDialog(stage, null, "Key printscreen was clicked!", 0, 0, Main.isFxWindowFrame(), true);
					Timer timer = new Timer(100, ae ->
					{
						Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
						clipboard.setContents(new ImageSelection(new ImageIcon().getImage()), null);
					});
					timer.setRepeats(false);
					timer.start();
				}
			};
		}
		createGUI(parentPosition, test.getName(), classNumber, classLetter, surname, name, secondName);
		for (int jj = 0; jj < answerButtons.length; jj++)
		{
			int j = jj;
			ButtonFX btn = answerButtons[j];
			btn.addActionListener((e) ->
			{
				btn.setClicked((test.getQuestions()[currentQuestionNumber].getType() != QuestionType.EnterText) ? selectedAnswers[j] = !selectedAnswers[j]
						: false);
				if (test.getQuestions()[currentQuestionNumber].getType() == QuestionType.PickOne)
					for (int k = 0; k < answerButtons.length; k++)
						answerButtons[k].setClicked(j == k);
			});
		}
		createQuestionSelectPanel();
		updateQuestionSelectPanel();
		changeLanguage();
		openQuestion(-1, 0);

		lastTime = Calendar.getInstance().getTimeInMillis();
		new Timer(1, e ->
		{
			if (!finished)
			{
				long time = Calendar.getInstance().getTimeInMillis();
				if (!paused)
					timeOfTest += ((float) (time - lastTime)) / 1000f;
				fullTime += ((float) (time - lastTime)) / 1000f;
				timeToPause--;
				String newTimerText = toSize((timeToTest - (int) timeOfTest) / 60, 2).substring(0, 2) + ":" + toSize((timeToTest - (int) timeOfTest) % 60, 2)
						.substring(0, 2);
				if (!timerText.equals(newTimerText))
					timer.setText(timerText = newTimerText);
				if (timeOfTest >= timeToTest)
					finish.click();
				lastTime = Calendar.getInstance().getTimeInMillis();
			}
		}).start();
	}

	String timerText = "";
	int answerButtonSpace, answerButtonHeightWithSpace, answerButtonHeight, defaultAswerButtonHeight;
	boolean showLogs;

	/**
	 * 
	 * @param parentPosition
	 * @param testName
	 * @param classNumber
	 * @param classLetter
	 * @param surname
	 * @param name
	 * @param secondName
	 */
	public void createGUI(Rectangle parentPosition, String testName, String classNumber, String classLetter, String surname, String name, String secondName)
	{
		if (pauseOnUnfocus)
			stage.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean focused) -> pause(!focused
					&& !showLogs));

		questionSign = new ButtonFX("", new CornerRadii(0, 0, 40, 40, false), new CornerRadii(0, 0, 32, 32, false));
		questionSign.setFramesize(6);
		InitStudentsTestingPart.instance.signPanel.getChildren().add(questionSign);
		questionSign.setBounds(0, 0, InitStudentsTestingPart.instance.signPanel.getPrefWidth(), InitStudentsTestingPart.instance.signPanel.getPrefHeight());
		questionSign.setDisabledV(true);

		int optionsSpace = 5, optionButtonAndSpaceWidth = ((int) InitStudentsTestingPart.instance.optionButtonsPanel.getPrefWidth() + optionsSpace) / 4,
				optionButtonWidth = optionButtonAndSpaceWidth - optionsSpace;

		timer = new ButtonFX(toSize((int) timeToTest / 60, 2).substring(0, 2) + ":" + toSize((int) timeToTest % 60, 2).substring(0, 2), new CornerRadii(8, 8, 8,
				8, false), new CornerRadii(6, 6, 6, 6, false));
		timer.setBounds(optionButtonAndSpaceWidth * 0, 0, optionButtonWidth, InitStudentsTestingPart.instance.optionButtonsPanel.getPrefHeight());
		timer.addActionListener(e -> pause(!paused));
		InitStudentsTestingPart.instance.optionButtonsPanel.getChildren().add(timer);

		skip = new ButtonFX("", new CornerRadii(8, 8, 8, 8, false), new CornerRadii(6, 6, 6, 6, false));
		skip.setBounds(optionButtonAndSpaceWidth * 1, 0, optionButtonWidth, InitStudentsTestingPart.instance.optionButtonsPanel.getPrefHeight());
		skip.addActionListener(e -> skip());
		skip.setDisabledV(!skipButtonOption);
		InitStudentsTestingPart.instance.optionButtonsPanel.getChildren().add(skip);

		next = new ButtonFX("", new CornerRadii(8, 8, 8, 8, false), new CornerRadii(6, 6, 6, 6, false));
		next.addActionListener(e -> next());
		next.setBounds(optionButtonAndSpaceWidth * 2, 0, optionButtonWidth, InitStudentsTestingPart.instance.optionButtonsPanel.getPrefHeight());
		InitStudentsTestingPart.instance.optionButtonsPanel.getChildren().add(next);

		finish = new ButtonFX("", new CornerRadii(8, 8, 8, 8, false), new CornerRadii(6, 6, 6, 6, false));
		finish.addActionListener(e -> finish(testName, classNumber, classLetter, surname, name, secondName));
		finish.setBounds(optionButtonAndSpaceWidth * 3, 0, optionButtonWidth, InitStudentsTestingPart.instance.optionButtonsPanel.getPrefHeight());
		InitStudentsTestingPart.instance.optionButtonsPanel.getChildren().add(finish);
		qualityIndicator.setVisible(indicateQualityOfLastAnswer);
		if (indicateQualityOfAllAnswers)
		{
			qualityIndicators = new Pane[test.getQuestions().length];
			for (int i = 0; i < qualityIndicators.length; i++)
			{
				qualityIndicator.getChildren().add(qualityIndicators[i] = new Pane());
				qualityIndicators[i].setLayoutX(qualityIndicator.getPrefWidth() / test.getQuestions().length * i);
				qualityIndicators[i].setPrefSize(qualityIndicator.getPrefWidth() / test.getQuestions().length - 5, qualityIndicator.getPrefHeight());
			}
		}
		answerButtonSpace = 5;
		answerButtonHeightWithSpace = ((int) answersPanel.getPrefHeight() + answerButtonSpace) / answerButtons.length;
		defaultAswerButtonHeight = answerButtonHeight = answerButtonHeightWithSpace - answerButtonSpace;

		for (int i = 0; i < answerButtons.length; i++)
		{
			answerButtons[i] = new ButtonFX("", new CornerRadii(8, 8, 8, 8, false), new CornerRadii(6, 6, 6, 6, false));
			answersPanel.getChildren().add(answerButtons[i]);
			answerButtons[i].setBounds(0, answerButtonHeightWithSpace * i, answersPanel.getPrefWidth(), answerButtonHeight);
		}

		window.setVisible(true);
	}

	/**
	 * 
	 */
	private void createQuestionSelectPanel()
	{
		questionSelectPanel.setPrefSize(InitStudentsTestingPart.instance.scrollPane.getPrefWidth(), InitStudentsTestingPart.instance.scrollPane.getPrefHeight()
				- 2);
		InitStudentsTestingPart.instance.scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		InitStudentsTestingPart.instance.scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		questionSelectAnswerButtons = new ButtonFX[test.getQuestions().length];
		int space = 4, height = fixedQSelectBtnHeight ? 32
				: (int) Math.max(32, (questionSelectPanel.getPrefHeight() + space) / test.getQuestions().length - space);
		questionSelectPanel.setPrefHeight((height + space) * test.getQuestions().length - space);
		for (int i = 0; i < test.getQuestions().length; i++)
		{
			questionSelectAnswerButtons[i] = new ButtonFX(i + 1 + "/" + test.getQuestions().length, new CornerRadii(8, 8, 8, 8, false), new CornerRadii(6, 6, 6,
					6, false));
			int q = i;
			questionSelectAnswerButtons[i].addActionListener(event ->
			{
				questionSelectAnswerButtons[q].setClicked(false);
				int prev = currentQuestionNumber;
				currentQuestionNumber = q;
				saveAnswer(prev);
				openQuestion(prev, currentQuestionNumber);
				for (ButtonFX btnx : questionSelectAnswerButtons)
				{
					btnx.setClicked(false);
					btnx.setPressedV(false);
					btnx.setSelected(false);
				}
			});
			questionSelectAnswerButtons[i].setBounds(0, i * (height + space), InitStudentsTestingPart.instance.scrollPane.getPrefWidth() - 16, height);
			questionSelectPanel.getChildren().add(questionSelectAnswerButtons[i]);
		}
	}

	/**
	 * 
	 */
	public void pause(boolean paused)
	{
		if (pauseOption && timeToPause <= 0)
		{
			this.paused = paused;
			questionSign.setVisible(!paused);
			next.setVisible(!paused);
			finish.setVisible(!paused);
			qualityIndicator.setVisible(!paused);
			skip.setVisible(!paused);
			InitStudentsTestingPart.instance.scrollPane.setVisible(!paused);
			answersPanel.setVisible(!paused);
			timeToPause = 300;
			timer.setClicked(paused);
		}
	}

	/**
	 * 
	 */
	public void skip()
	{
		int qn = currentQuestionNumber;
		goNext();
		if (!skippedQuestions.contains(qn))
			skippedQuestions.add(qn);
		openQuestion(currentQuestionNumber - 1, currentQuestionNumber);
	}

	/**
	 * 
	 */
	public void next()
	{
		saveAnswer(currentQuestionNumber);
		handleQuestion();
		openQuestion(currentQuestionNumber, currentQuestionNumber);
	}

	/**
	 * 
	 * @param window
	 * @param _class
	 * @param surname
	 * @param name
	 * @param secondName
	 */
	public void finish(String testName, String classNumber, String classLetter, String surname, String name, String secondName)
	{
		saveAnswer(currentQuestionNumber);
		handleQuestion();
		paused = true;
		finished = true;
		int maxResult = 0;
		for (Question question : test.getQuestions())
			maxResult += question.getMaxAward();
		showResult(testName, classNumber, classLetter, surname, name, secondName, countResult(), maxResult);
		if (hide)
		{
			stage.hide();
			Main.instance.showAllHided();
		}
		else Main.exit(ExitCodes.UserCloseProgram);
	}

	/**
	 * 
	 * @return
	 */
	public int countResult()
	{
		int result = rightAnswers = perfectAnswers = 0;
		for (int i = 0; i < test.getQuestions().length; i++)
		{
			int[] results = _handleQuestion(i);
			result += results[1];
			rightAnswers += (results[0] > results[1] || results[2] == results[1]) ? 1 : 0;
			perfectAnswers += results[2] == results[1] ? 1 : 0;
		}
		return result;
	}

	/**
	 * 
	 */
	public void handleQuestion()
	{
		saveAnswer(currentQuestionNumber);
		int[] res = _handleQuestion(currentQuestionNumber);
		int minResult = res[0], questionResult = res[1], maxResult = res[2];

		if (questionResult > minResult || questionResult == maxResult)
			rightAnswers++;
		if (questionResult == maxResult)
			perfectAnswers++;

		if (indicateQualityOfLastAnswer)
		{
			Pane panel = indicateQualityOfAllAnswers ? (Pane) qualityIndicators[currentQuestionNumber] : qualityIndicator;
			if (questionResult == maxResult)
				panel.setBackground(new Background(new BackgroundFill(Color.LIME, new CornerRadii(5), new Insets(0))));
			else if (questionResult > minResult)
				panel.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(5), new Insets(0))));
			else panel.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5), new Insets(0))));
		}
		if (showRightAnswer)
		{
			String rightAnswer = "";
			if (test.getQuestions()[currentQuestionNumber].getType() == QuestionType.EnterText)
			{
				for (Answer a : test.getQuestions()[currentQuestionNumber].getAnswers())
					if (test.getQuestions()[currentQuestionNumber].getAward() + a.getAward() == test.getQuestions()[currentQuestionNumber].getMaxAward())
						rightAnswer += a.getText() + "\n";
			}
			int i = 0;
			if (test.getQuestions()[currentQuestionNumber].getType() == QuestionType.PickOne)
				for (Answer a : test.getQuestions()[currentQuestionNumber].getAnswers())
				{
					if (test.getQuestions()[currentQuestionNumber].getAward() + a.getAward() == test.getQuestions()[currentQuestionNumber].getMaxAward())
					{
						rightAnswer += a.getText() + "\n";
						answerButtons[i].setDisabledBackground(test.getTheme().getQuestionSelectSkippedBackground());
						answerButtons[i].setDisabledV(true);
					}
					i++;
				}
			if (test.getQuestions()[currentQuestionNumber].getType() == QuestionType.SelectSome)
				for (Answer a : test.getQuestions()[currentQuestionNumber].getAnswers())
				{
					if (test.getQuestions()[currentQuestionNumber].getAward() + a.getAward() > 0)
					{
						rightAnswer += a.getText() + "\n";
						answerButtons[i].setDisabledBackground(test.getTheme().getQuestionSelectSkippedBackground());
						answerButtons[i].setDisabledV(true);
					}
					i++;
				}
			FXDialogsGenerator.showFXDialog(stage, null, rightAnswer, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, Main.isFxWindowFrame(),
					true);
		}
		goNext();
	}

	/**
	 * 
	 */
	public void goNext()
	{
		if (!canGoToAllQuestions)
			if (skippedQuestions.contains((Object) currentQuestionNumber))
			{
				skippedQuestions.remove((Object) currentQuestionNumber);
				if (skippedQuestions.size() != 0)
				{
					for (int i = 0; i < skippedQuestions.size(); i++)
						if (skippedQuestions.get(i) > currentQuestionNumber)
						{
							currentQuestionNumber = skippedQuestions.get(i);
							break;
						}
						else if (i == skippedQuestions.size() - 1)
							if (skippedQuestions.get(i) >= lastNotSkippedQuestion)
								currentQuestionNumber = skippedQuestions.get(0);
							else currentQuestionNumber = lastNotSkippedQuestion;
				}
				else currentQuestionNumber = lastNotSkippedQuestion;
			}
			else currentQuestionNumber++;
		else currentQuestionNumber++;
	}

	/**
	 * 
	 * @param questionNumber
	 * @return
	 */
	private int[] _handleQuestion(int questionNumber)
	{
		Question question = test.getQuestions()[questionNumber];
		if (answers[questionNumber] != null ? answers[questionNumber].getSelectedAnswers() == null || answers[questionNumber].getAnswerText() == null : true)
			answers[questionNumber] = new UserAnswer(-1, new boolean[question.getAnswers().length], question.getAnswersIndexes(), "");
		int questionResult = question.getAward();
		switch (question.getType())
		{
			case EnterText:
			{
				for (int i = 0; i < question.getAnswers().length; i++)
				{
					String userAnswer = answers[questionNumber].getAnswerText(), curAnswer = question.getAnswers()[i].getText();
					if (question.isIgnoreCase())
					{
						userAnswer = userAnswer.toLowerCase();
						curAnswer = curAnswer.toLowerCase();
					}
					for (String c : question.getIgnoredCharacters().split(""))
					{
						userAnswer = userAnswer.replace(c.toLowerCase(), "");
						curAnswer = curAnswer.replace(c.toLowerCase(), "");
					}
					if (userAnswer.equals(curAnswer))
						questionResult += question.getAnswers()[i].getAward();
				}
			}
				break;
			case PickOne:
			{
				if (answers[questionNumber].getSelectedAnswerNumber() != -1)
					questionResult += question.getAnswers()[answers[questionNumber].getSelectedAnswerNumber()].getAward();
			}
				break;
			case SelectSome:
			{
				for (int i = 0; i < question.getAnswers().length; i++)
					questionResult += answers[questionNumber].getSelectedAnswers()[i] ? question.getAnswers()[i].getAward() : 0;
			}
				break;
			case Arrangement:
			{
				int res = 0;
				for (HashMap<Integer, Integer> hashMap : question.getAnswerArrangement().keySet())
				{
					boolean b = true;
					for (Integer integer : hashMap.keySet())
						if (answers[questionNumber].getAnswerArrangement()[question.getAnswersIndexes()[integer]] != hashMap.get(integer))
							b = false;
					if (b)
						if (question.isHandleOnlyMaximal())
							res = Math.max(res, question.getAnswerArrangement().get(hashMap));
						else res += question.getAnswerArrangement().get(hashMap);
				}
				questionResult += res;
			}
				break;
			case Matching:
			{
				int res = 0;
				for (HashMap<Integer, Integer> hashMap : question.getAnswerArrangement().keySet())
				{
					boolean b = true;
					for (Integer integer : hashMap.keySet())
						if (answers[questionNumber].getAnswerArrangement()[integer] != hashMap.get(integer))
							b = false;
					if (b)
						if (question.isHandleOnlyMaximal())
							res = Math.max(res, question.getAnswerArrangement().get(hashMap));
						else res += question.getAnswerArrangement().get(hashMap);
				}
				questionResult += res;
			}
				break;
			case Distribution:
			{
				int res = 0;
				for (HashMap<Integer, Integer> hashMap : question.getAnswerArrangement().keySet())
				{
					boolean b = true;
					for (Integer integer : hashMap.keySet())
						if (answers[questionNumber].getAnswerArrangement()[integer] != hashMap.get(integer))
							b = false;
					if (b)
						if (question.isHandleOnlyMaximal())
							res = Math.max(res, question.getAnswerArrangement().get(hashMap));
						else res += question.getAnswerArrangement().get(hashMap);
				}
				questionResult += res;
			}
				break;
		}
		questionResult = Math.max(question.getMinResult(), questionResult);
		return new int[]
		{
				question.getAward(),
				questionResult,
				question.getMaxAward()
		};
	}

	/**
	 * 
	 * @param window
	 * @param _class
	 * @param surname
	 * @param name
	 * @param secondName
	 */
	public void showResult(String testName, String classNumber, String classLetter, String surname, String name, String secondName, int result, int maxResult)
	{
		String text = "";
		text += "syntaxLanguage" + ": \"" + msgSys.getLanguage() + "\"\n";
		text += msgSys.getMsg("testName") + ": \"" + testName + "\"\n";
		text += msgSys.getMsg("classNumber") + ": \"" + classNumber + "\"\n";
		text += msgSys.getMsg("classLetter") + ": \"" + classLetter + "\"\n";
		text += msgSys.getMsg("surname") + ": \"" + surname + "\"\n";
		text += msgSys.getMsg("name") + ": \"" + name + "\"\n";
		text += msgSys.getMsg("secondName") + ": \"" + secondName + "\"\n";
		text += msgSys.getMsg("result") + ": " + result + "\n";
		text += msgSys.getMsg("maxResult") + ": " + maxResult + "\n";
		text += msgSys.getMsg("perfectAnswersAmount") + ": " + perfectAnswers + "\n";
		text += msgSys.getMsg("rightAnswersAmount") + ": " + (rightAnswers - perfectAnswers) + "\n";
		text += msgSys.getMsg("questionsAmount") + ": " + test.getQuestions().length + "\n";
		text += msgSys.getMsg("time") + ": " + toSize((int) timeOfTest / 60, 2) + ":" + toSize((int) timeOfTest % 60, 2) + "\n";
		text += msgSys.getMsg("timeToTest") + ": " + toSize((int) timeToTest / 60, 2) + ":" + toSize((int) timeToTest % 60, 2) + "\n";
		text += msgSys.getMsg("fullTime") + ": " + toSize((int) fullTime / 60, 2) + ":" + toSize((int) fullTime % 60, 2);
		Calendar c = Calendar.getInstance();
		File results = new File("Results");
		if (!results.isDirectory() && results.exists())
			results.delete();
		if (!results.exists())
			results.mkdir();
		File f = new File("Results/Result From " + toSize(c.get(Calendar.DAY_OF_YEAR), 2) + "_" + toSize(c.get(Calendar.HOUR), 2) + "-" + toSize(c.get(
				Calendar.MINUTE), 2) + "-" + toSize(c.get(Calendar.SECOND), 2) + ".log");
		try
		{
			f.createNewFile();
			BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "Cp1251"));
			pw.write(text);
			pw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		showLogs = true;
		FXDialogsGenerator.showFXDialog(stage, null, text, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, Main.isFxWindowFrame(), true);
	}

	/**
	 * 
	 * @param i
	 * @param size
	 * @return
	 */
	private String toSize(int i, int size)
	{
		String s = i + "";
		for (; s.length() < size;)
			s = "0" + s;
		return s;
	}

	/**
	 * 
	 */
	public void changeLanguage()
	{
		next.setText(msgSys.getMsg("next"));
		skip.setText(msgSys.getMsg("skip"));
		finish.setText(msgSys.getMsg("finish"));
	}

	/**
	 * 
	 */
	private void updateQuestionSelectPanel()
	{
		for (int i = 0; i < test.getQuestions().length; i++)
		{
			questionSelectAnswerButtons[i].setNormalBackground(skippedQuestions.contains(i) ? test.getTheme().getQuestionSelectSkippedBackground()
					: test.getTheme().getQuestionSelectNormalBackground());
			questionSelectAnswerButtons[i].setNormalFrame(skippedQuestions.contains(i) ? test.getTheme().getQuestionSelectSkippedFrame()
					: test.getTheme().getQuestionSelectNormalFrame());
			questionSelectAnswerButtons[i].setNormalForeground(skippedQuestions.contains(i) ? test.getTheme().getQuestionSelectSkippedForeground()
					: test.getTheme().getQuestionSelectNormalForeground());
			questionSelectAnswerButtons[i].setDisabledV(!skippedQuestions.contains(i) && !canGoToAllQuestions || i == currentQuestionNumber);
			questionSelectAnswerButtons[i].setDisabledBackground(i == currentQuestionNumber ? test.getTheme().getSpecialButtonsBackground()
					: test.getTheme().getQuestionSelectNormalBackground());
			questionSelectAnswerButtons[i].setDisabledFrame(i == currentQuestionNumber ? test.getTheme().getSpecialButtonsFrame()
					: test.getTheme().getQuestionSelectNormalFrame());
			questionSelectAnswerButtons[i].setDisabledForeground(i == currentQuestionNumber ? test.getTheme().getSpecialButtonsForeground()
					: test.getTheme().getQuestionSelectNormalForeground());
		}
	}

	/**
	 * 
	 * @param questionNumber
	 */
	public void saveAnswer(int questionNumber)
	{
		int selectedNumber = -1;
		for (int j = 0; j < selectedAnswers.length; j++)
			if (selectedAnswers[j])
				selectedNumber = j;
		answers[questionNumber] = new UserAnswer(selectedNumber, selectedAnswers.clone(), buttonsArrangement.clone(), test.getQuestions()[questionNumber]
				.getType() == QuestionType.EnterText ? answerButtons[0].getText() : "");
	}

	public int[] array(int l)
	{
		int[] n = new int[l];
		for (int y = 0; y < n.length; y++)
			n[y] = y;
		return n;
	}

	private void updateButtonPose(int questionNumber, int btn)
	{
		boolean mabq = true, b = false;
		answerButtonSpace = 5;
		answerButtonHeightWithSpace = ((int) answersPanel.getPrefHeight() + answerButtonSpace) / (mabq ? answerButtons.length
				: test.getQuestions()[questionNumber].getAnswers().length);
		answerButtonHeight = b ? answerButtonHeightWithSpace - answerButtonSpace : defaultAswerButtonHeight;
		answerButtons[btn].setBounds(0, (test.getQuestions()[questionNumber].getType() != QuestionType.Arrangement ? btn : buttonsArrangement[btn])
				* answerButtonHeightWithSpace, answerButtons[btn].getPrefWidth(), answerButtonHeight);
	}

	/**
	 * 
	 * @param question
	 * @param info
	 * @param number
	 */
	public void openQuestion(int prev, int questionNumber)
	{
		if (answers[questionNumber] != null)
			buttonsArrangement = answers[questionNumber].getAnswerArrangement();
		else buttonsArrangement = array(answerButtons.length);
		if (test.getQuestions()[questionNumber].getType() == QuestionType.EnterText)
		{
			for (ButtonFX button : answerButtons)
				button.setVisible(false);
			answerButtons[0].setVisible(true);
			answerButtons[0].setEditable(true);
			answerButtons[0].setText(answers[questionNumber] != null ? answers[questionNumber].getAnswerText() : "");
			answerButtons[0].setFont(new javafx.scene.text.Font(answerButtons[0].getFont().getName(), test.getQuestions()[questionNumber].getAnswers()[0]
					.getFont().getSize()));
			answerButtons[0].setClicked(false);
			answerButtons[0].setDisabledV(false);
		}
		else
		{
			for (int i = 0; i < answerButtons.length; i++)
				answerButtons[i].setVisible(test.getQuestions()[questionNumber].getAnswers().length > i);
			for (int i = 0; i < test.getQuestions()[questionNumber].getAnswers().length; i++)
			{
				answerButtons[i].setEditable(false);
				answerButtons[i].setText(test.getQuestions()[questionNumber].getAnswers()[i].getText());
				answerButtons[i].setFont(new Font(answerButtons[i].getFont().getName(), test.getQuestions()[questionNumber].getAnswers()[i].getFont()
						.getSize()));
				answerButtons[i].setClicked(false);
				answerButtons[i].setDisabledV(false);
			}
		}
		for (int i = 0; i < test.getQuestions()[questionNumber].getAnswers().length; i++)
		{
			updateButtonPose(questionNumber, i);
			ContextMenu m = new ContextMenu();
			if (test.getQuestions()[questionNumber].getType() == QuestionType.Distribution || test.getQuestions()[questionNumber]
					.getType() == QuestionType.Matching)
			{
				ToggleGroup tg = new ToggleGroup();
				for (int r = 0; r < test.getQuestions()[questionNumber].getIndexesForNames().length; r++)
				{
					RadioMenuItem item = new RadioMenuItem(test.getQuestions()[questionNumber].getIndexesForNames()[r]);
					item.setToggleGroup(tg);
					int ii = i, rr = r;
					item.setOnAction(e ->
					{
						buttonsArrangement[ii] = rr;
					});
					m.getItems().add(item);
				}
			}
			else if (test.getQuestions()[questionNumber].getType() == QuestionType.Arrangement)
			{
				ToggleGroup tg = new ToggleGroup();
				for (int r = 0; r < test.getQuestions()[questionNumber].getAnswers().length; r++)
				{
					RadioMenuItem item = new RadioMenuItem(r + 1 + "");
					item.setToggleGroup(tg);
					int ii = i, rr = r;
					item.setOnAction(e ->
					{
						buttonsArrangement[ii] = rr;
					});
					m.getItems().add(item);
				}
			}
			answerButtons[i].setContextMenu(m);
		}
		switch (test.getQuestions()[questionNumber].getType())
		{
			case PickOne:
				questionSign.setDisabledBackground(test.getTheme().getPickOne().getQuestionBackground());
				questionSign.setDisabledFrame(test.getTheme().getPickOne().getQuestionFrame());
				questionSign.setDisabledForeground(test.getTheme().getPickOne().getQuestionForeground());

				for (ButtonFX answer : answerButtons)
				{
					answer.setNormalBackground(test.getTheme().getPickOne().getAnswersBackground());
					answer.setNormalFrame(test.getTheme().getPickOne().getAnswersFrame());
					answer.setNormalForeground(test.getTheme().getPickOne().getAnswersForeground());
				}
				break;
			case SelectSome:
				questionSign.setDisabledBackground(test.getTheme().getSelectSome().getQuestionBackground());
				questionSign.setDisabledFrame(test.getTheme().getSelectSome().getQuestionFrame());
				questionSign.setDisabledForeground(test.getTheme().getSelectSome().getQuestionForeground());

				for (ButtonFX answer : answerButtons)
				{
					answer.setNormalBackground(test.getTheme().getSelectSome().getAnswersBackground());
					answer.setNormalFrame(test.getTheme().getSelectSome().getAnswersFrame());
					answer.setNormalForeground(test.getTheme().getSelectSome().getAnswersForeground());
				}
				break;
			case EnterText:
				questionSign.setDisabledBackground(test.getTheme().getEnterText().getQuestionBackground());
				questionSign.setDisabledFrame(test.getTheme().getEnterText().getQuestionFrame());
				questionSign.setDisabledForeground(test.getTheme().getEnterText().getQuestionForeground());

				for (ButtonFX answer : answerButtons)
				{
					answer.setNormalBackground(test.getTheme().getEnterText().getAnswersBackground());
					answer.setNormalFrame(test.getTheme().getEnterText().getAnswersFrame());
					answer.setNormalForeground(test.getTheme().getEnterText().getAnswersForeground());
				}
				break;
			case Arrangement:
				questionSign.setDisabledBackground(test.getTheme().getArrangement().getQuestionBackground());
				questionSign.setDisabledFrame(test.getTheme().getArrangement().getQuestionFrame());
				questionSign.setDisabledForeground(test.getTheme().getArrangement().getQuestionForeground());

				for (ButtonFX answer : answerButtons)
				{
					answer.setNormalBackground(test.getTheme().getArrangement().getAnswersBackground());
					answer.setNormalFrame(test.getTheme().getArrangement().getAnswersFrame());
					answer.setNormalForeground(test.getTheme().getArrangement().getAnswersForeground());
				}
				break;
			case Matching:
				questionSign.setDisabledBackground(test.getTheme().getMatching().getQuestionBackground());
				questionSign.setDisabledFrame(test.getTheme().getMatching().getQuestionFrame());
				questionSign.setDisabledForeground(test.getTheme().getMatching().getQuestionForeground());

				for (ButtonFX answer : answerButtons)
				{
					answer.setNormalBackground(test.getTheme().getMatching().getAnswersBackground());
					answer.setNormalFrame(test.getTheme().getMatching().getAnswersFrame());
					answer.setNormalForeground(test.getTheme().getMatching().getAnswersForeground());
				}
				break;
			case Distribution:
				questionSign.setDisabledBackground(test.getTheme().getDistribution().getQuestionBackground());
				questionSign.setDisabledFrame(test.getTheme().getDistribution().getQuestionFrame());
				questionSign.setDisabledForeground(test.getTheme().getDistribution().getQuestionForeground());

				for (ButtonFX answer : answerButtons)
				{
					answer.setNormalBackground(test.getTheme().getDistribution().getAnswersBackground());
					answer.setNormalFrame(test.getTheme().getDistribution().getAnswersFrame());
					answer.setNormalForeground(test.getTheme().getDistribution().getAnswersForeground());
				}
				break;
		}
		timer.setNormalBackground(test.getTheme().getSpecialButtonsBackground());
		timer.setNormalFrame(test.getTheme().getSpecialButtonsFrame());
		timer.setNormalForeground(test.getTheme().getSpecialButtonsForeground());
		next.setNormalBackground(test.getTheme().getSpecialButtonsBackground());
		next.setNormalFrame(test.getTheme().getSpecialButtonsFrame());
		next.setNormalForeground(test.getTheme().getSpecialButtonsForeground());
		finish.setNormalBackground(test.getTheme().getSpecialButtonsBackground());
		finish.setNormalFrame(test.getTheme().getSpecialButtonsFrame());
		finish.setNormalForeground(test.getTheme().getSpecialButtonsForeground());
		skip.setNormalBackground(test.getTheme().getSpecialButtonsBackground());
		skip.setNormalFrame(test.getTheme().getSpecialButtonsFrame());
		skip.setNormalForeground(test.getTheme().getSpecialButtonsForeground());

		if (!skippedQuestions.contains(questionNumber))
			lastNotSkippedQuestion = questionNumber;
		selectedAnswers = new boolean[test.getQuestions()[questionNumber].getAnswers().length];
		questionSign.setFont(new Font(questionSign.getFont().getName(), test.getQuestions()[questionNumber].getFont().getSize()));
		questionSign.setText(test.getQuestions()[questionNumber].getText());

		if ((questionNumber >= test.getQuestions().length - 1))
			wasGoToEnd = true;

		next.setDisabledV(questionNumber >= test.getQuestions().length - 1);
		skip.setDisabledV(questionNumber >= test.getQuestions().length - 1);
		finish.setDisabledV(!wasGoToEnd);

		updateQuestionSelectPanel();
	}
}
