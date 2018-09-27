package ru.alexanderdv.schooltester.main;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import org.jsoup.parser.Parser;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.geometry.VPos;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import ru.alexanderdv.fxutilities.components.ButtonFX;
import ru.alexanderdv.fxutilities.components.ButtonFX.ButtonFXHtmlContent;
import ru.alexanderdv.fxutilities.components.ButtonFX.ButtonFXTextContent;
import ru.alexanderdv.schooltester.utilities.ImageSelection;
import ru.alexanderdv.schooltester.utilities.Logger.ExitCodes;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;
import ru.alexanderdv.schooltester.utilities.network.OnlineLoginsPacket;
import ru.alexanderdv.schooltester.utilities.network.ResultSendPacket;
import ru.alexanderdv.schooltester.utilities.questionvars.Answer;
import ru.alexanderdv.schooltester.utilities.questionvars.AnswerVariant;
import ru.alexanderdv.schooltester.utilities.questionvars.ArrangementAnswer;
import ru.alexanderdv.schooltester.utilities.questionvars.ArrangementQuestion;
import ru.alexanderdv.schooltester.utilities.questionvars.ChooseOneAnswer;
import ru.alexanderdv.schooltester.utilities.questionvars.ChooseOneAnswerVariant;
import ru.alexanderdv.schooltester.utilities.questionvars.ChooseOneQuestion;
import ru.alexanderdv.schooltester.utilities.questionvars.ChooseOneResult;
import ru.alexanderdv.schooltester.utilities.questionvars.DistributionAnswer;
import ru.alexanderdv.schooltester.utilities.questionvars.DistributionQuestion;
import ru.alexanderdv.schooltester.utilities.questionvars.EnterTextAnswer;
import ru.alexanderdv.schooltester.utilities.questionvars.EnterTextAnswerVariant;
import ru.alexanderdv.schooltester.utilities.questionvars.EnterTextQuestion;
import ru.alexanderdv.schooltester.utilities.questionvars.EnterTextResult;
import ru.alexanderdv.schooltester.utilities.questionvars.HtmlAnswerVariant;
import ru.alexanderdv.schooltester.utilities.questionvars.MatchingAnswer;
import ru.alexanderdv.schooltester.utilities.questionvars.MatchingQuestion;
import ru.alexanderdv.schooltester.utilities.questionvars.Question;
import ru.alexanderdv.schooltester.utilities.questionvars.Result;
import ru.alexanderdv.schooltester.utilities.questionvars.SelectMultipleAnswer;
import ru.alexanderdv.schooltester.utilities.questionvars.SelectMultipleAnswerVariant;
import ru.alexanderdv.schooltester.utilities.questionvars.SelectMultipleQuestion;
import ru.alexanderdv.schooltester.utilities.questionvars.SelectMultipleResult;
import ru.alexanderdv.schooltester.utilities.types.Test;
import ru.alexanderdv.schooltester.utilities.types.TestResult;
import ru.alexanderdv.schooltester.utilities.types.TestSettings;
import ru.alexanderdv.schooltester.utilities.types.TesteeInfo;
import ru.alexanderdv.schooltester.utilities.types.TesterInfo;
import ru.alexanderdv.schooltester.utilities.types.TestingPartSettings;
import ru.alexanderdv.schooltester.utilities.types.Theme;
import ru.alexanderdv.simpleutilities.ByteUtils;
import ru.alexanderdv.simpleutilities.Container;
import ru.alexanderdv.simpleutilities.Entry;
import ru.alexanderdv.simpleutilities.MathUtils;
import ru.alexanderdv.simpleutilities.SecureContainer;
import ru.alexanderdv.simpleutilities.SystemUtils;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class TestingPart extends ProtectedFXWindow
{
	public static TestingPart instance;
	private final WebView htmlToText;

	// private final ScrollPane questionSelectScrollPane;
	// private final Pane questionSelectPanel;
	private final Pane optionButtonsPanel;
	private final ButtonFX questionSign;
	private final Pane qualityIndicator;
	private final Label questionInfoTextfield;
	private final Pane testingPane;
	private final Pane questionsTablePane;
	private final GridPane questionsTable;

	private ButtonFX timer, skip, next, finish, back;

	private static final MessageSystem msgSys = Main.msgSys;
	private Test test;
	private Theme theme;
	private Answer<?>[] answers;
	private int[] buttonsArrangement;
	private ArrayList<Integer>[] buttonsArrangementForDistribution;
	private ArrayList<SecureContainer<Integer>> skippedQuestions;
	private SecureContainer<Integer> currentQuestionNumber;
	private SecureContainer<Integer> lastNotSkippedQuestion;
	// User answer
	private boolean[] selectedAnswers;
	private int selectedAnswerNumber;
	private int[] answerArrangementForArrangement;
	private int[] answerArrangementForMatching;
	private ArrayList<Integer>[] answerArrangementForDistribution;
	// User answer End

	private SecureContainer<Boolean> indicateQualityOfLastAnswer;
	private SecureContainer<Boolean> indicateQualityOfAllAnswers;
	private SecureContainer<Boolean> showRightAnswer;
	private SecureContainer<Boolean> canGoToAllQuestions;
	private SecureContainer<Boolean> skipButtonOption;
	private SecureContainer<Boolean> pauseOption;
	private SecureContainer<Boolean> pauseOnUnfocus;

	private SecureContainer<Boolean> paused;
	private SecureContainer<Integer> timeLimit;
	private SecureContainer<Float> timeOfTest, fullTime;
	private SecureContainer<Integer> timeToPause;
	private SecureContainer<Long> lastTime;
	private SecureContainer<Boolean> finished;

	private boolean wasGoToEnd;
	// private ButtonFX[] questionSelectAnswerButtons;
	private ButtonFX enterTextButton;
	private Pane[] qualityIndicators;
	private ButtonFX curBtn;
	private String timerText = "";
	private boolean showLogs;
	private static BackgroundImage clip, image, video, audio;
	private static final double questionSignRounding = 40;
	SecureContainer<Boolean> canSendResult;
	SecureContainer<String> teacher;

	private final ScrollPane answersScrollPane;
	private final Pane answersPanel;
	private ButtonFX[] answerButtons;
	private static final int minAnswerButtonHeight = 48;

	private final ScrollPane questionsScrollPane;
	private final Pane questionsPanel;
	private ButtonFX[] questionButtons;
	private static final int minQuestionButtonHeight = 48;

	private SecureContainer<TesteeInfo> testeeInfo;
	private SecureContainer<TesterInfo> testerInfo;
	String testFileName;

	private static final Background warningBackground = new Background(
			new BackgroundFill(new Color(1, 0.4, 0.4, 1), new CornerRadii(0), new Insets(0))),
			standardBackground = new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), new Insets(0)));

	static
	{
		try
		{
			double offset = questionSignRounding - Math.sqrt(Math.pow(questionSignRounding, 2) / 2);
			BackgroundPosition position = new BackgroundPosition(Side.RIGHT, offset, false, Side.BOTTOM, offset, false);
			clip = new BackgroundImage(
					new Image(TestingPart.class.getResource(Main.resourcesPath + "/images/clip.png").openStream()),
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, position, BackgroundSize.DEFAULT);
			image = new BackgroundImage(
					new Image(TestingPart.class.getResource(Main.resourcesPath + "/images/image.png").openStream()),
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, position, BackgroundSize.DEFAULT);
			video = new BackgroundImage(
					new Image(TestingPart.class.getResource(Main.resourcesPath + "/images/video.png").openStream()),
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, position, BackgroundSize.DEFAULT);
			audio = new BackgroundImage(
					new Image(TestingPart.class.getResource(Main.resourcesPath + "/images/audio.png").openStream()),
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, position, BackgroundSize.DEFAULT);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			Main.exit(ExitCodes.UnknownError);
		}
	}

	public void createNewTest(Theme theme, Test test, String testFileName, TesteeInfo testeeInfo, TesterInfo testerInfo,
			TestSettings testSettings, TestingPartSettings testingPartSettings, Rectangle parentPos,
			boolean canSendResult)
	{
		// Logging
		testLog.clear();
		// Logging End
		doLog("Info", "Start");

		// Init
		this.theme = theme;
		this.test = test;
		this.testFileName = testFileName;
		this.testeeInfo = new SecureContainer<TesteeInfo>(testeeInfo);
		this.testerInfo = new SecureContainer<TesterInfo>(testerInfo);
		this.testSettings = testSettings;
		this.testingPartSettings = testingPartSettings;

		skippedQuestions = new ArrayList<SecureContainer<Integer>>();
		answers = new Answer<?>[test.getQuestions().length];
		currentQuestionNumber = new SecureContainer<Integer>(0);
		lastNotSkippedQuestion = new SecureContainer<Integer>(0);

		this.indicateQualityOfLastAnswer = new SecureContainer<Boolean>(testSettings.isIndicateQualityOfLastAnswer());
		this.indicateQualityOfAllAnswers = new SecureContainer<Boolean>(testSettings.isIndicateQualityOfAllAnswers());
		this.showRightAnswer = new SecureContainer<Boolean>(testSettings.isShowRightAnswer());
		this.canGoToAllQuestions = new SecureContainer<Boolean>(testSettings.isCanGoToAllQuestions());
		this.skipButtonOption = new SecureContainer<Boolean>(testSettings.isSkipButtonOption());
		this.pauseOption = new SecureContainer<Boolean>(
				test.getTimeLimit() == -1 ? false : testSettings.isPauseOption());
		this.pauseOnUnfocus = new SecureContainer<Boolean>(
				test.getTimeLimit() == -1 ? false : testSettings.isPauseOnUnfocus());

		paused = new SecureContainer<Boolean>(false);
		fullTime = new SecureContainer<Float>(0f);
		timeLimit = new SecureContainer<Integer>(test.getTimeLimit());
		timeOfTest = new SecureContainer<Float>(test.getTimeLimit() == -1 ? -1f : 0f);
		timeToPause = new SecureContainer<Integer>(test.getTimeLimit() == -1 ? -1 : 0);
		lastTime = new SecureContainer<Long>(Calendar.getInstance().getTimeInMillis());
		finished = new SecureContainer<Boolean>(false);

		this.canSendResult = new SecureContainer<Boolean>(canSendResult);

		ButtonFX.anticntrlc = testSettings.isAnticopy();
		ButtonFX.antiscreenshot = testSettings.isAntiscreenshot();
		ButtonFX.antish1 = e ->
		{
			if (testSettings.isAntiscreenshot())
				if (e.getCode() == KeyCode.PRINTSCREEN)
				{
					doLog("Suspicious", "Screenshot");
					Timer timer = new Timer(100, ae -> Toolkit.getDefaultToolkit().getSystemClipboard()
							.setContents(new ImageSelection(new ImageIcon().getImage()), null));
					timer.setRepeats(false);
					timer.start();
					FXDialogsGenerator.showFXDialog(stage, msgSys.getMsg("printscreenWasClicked"), null, true);
				}
		};
		for (Question<?> q : test.getQuestions())
		{
			ArrayList<MediaPlayer> pl = new ArrayList<MediaPlayer>();
			for (Media au : q.getAudios())
			{
				pl.add(new MediaPlayer(au));
				pl.get(pl.size() - 1).play();
			}
			for (Media v : q.getVideos())
			{
				pl.add(new MediaPlayer(v));
				pl.get(pl.size() - 1).play();
			}
			for (MediaPlayer m : pl)
				m.stop();
		}
		createGUI();
		for (int jj = 0; jj < answerButtons.length; jj++)
		{
			int j = jj;
			answerButtons[j].addActionListener((e) ->
			{
				answerButtons[j]
						.setClicked((test.getQuestions()[currentQuestionNumber.get()] instanceof ChooseOneQuestion
								|| test.getQuestions()[currentQuestionNumber.get()] instanceof SelectMultipleQuestion)
										? selectedAnswers[j] = !selectedAnswers[j]
										: false);
				updateButtonMenu(currentQuestionNumber.get(), j);
				if (test.getQuestions()[currentQuestionNumber.get()] instanceof ChooseOneQuestion)
					for (int k = 0; k < answerButtons.length; k++)
						answerButtons[k].setClicked(j == k);
			});
		}
		for (int jj = 0; jj < questionButtons.length; jj++)
		{
			int j = jj;
			questionButtons[j].addActionListener(event ->
			{
				questionButtons[j].setClicked(false);
				int prev = currentQuestionNumber.get();
				currentQuestionNumber.set(j);
				saveAnswer(prev);
				openQuestion(prev, currentQuestionNumber.get());
				for (ButtonFX btnx : questionButtons)
				{
					btnx.setClicked(false);
					btnx.setPressedV(false);
					btnx.setSelected(false);
				}
			});
		}
		// createQuestionSelectPanel();
		updateQuestionsPane();
		openQuestion(-1, 0);

		lastTime.set(Calendar.getInstance().getTimeInMillis());
		timer1.start();
		createActionHandlers();
		resize();
		updateLabels();
		try
		{
			open(parentPos, AccountsPart.account.get(), Main.client);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void doLog(String mark, String rec)
	{
		testLog.put(Calendar.getInstance().getTimeInMillis(), new Entry<String, String>(mark, rec));
	}

	Timer timer1;

	/**
	 * 
	 * @param theme
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

	public TestingPart()
	{
		super(null, Main.createPane(600, 800), 0, 1, true, true);
		instance = this;

		panel.getChildren().add(this.testingPane = new Pane());
		panel.getChildren().add(this.htmlToText = new WebView());
		htmlToText.setLayoutX(-10000);
		// testingPane.getChildren()
		// .add(this.questionSelectScrollPane = new ScrollPane(this.questionSelectPanel
		// = new Pane()));
		testingPane.getChildren().add(this.questionSign = new ButtonFX(null, new CornerRadii(0, 0, 8, 8, false),
				new CornerRadii(0, 0, 6, 6, false)));

		testingPane.getChildren().add(this.answersScrollPane = new ScrollPane(this.answersPanel = new Pane()));
		testingPane.getChildren().add(this.questionsScrollPane = new ScrollPane(this.questionsPanel = new Pane()));

		testingPane.getChildren().add(this.optionButtonsPanel = new Pane());
		testingPane.getChildren().add(this.qualityIndicator = new Pane());
		testingPane.getChildren().add(this.questionInfoTextfield = new Label());

		questionsScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		questionsScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);

		answersScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		answersScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);

		panel.getChildren().add(this.questionsTablePane = new Pane());
		questionsTablePane.getChildren().add(this.questionsTable = new GridPane());
		timer1 = new Timer(1, e ->
		{
			if (!finished.get())
			{
				long time = Calendar.getInstance().getTimeInMillis();
				if (test.getTableQuestionSelector() == null && timeLimit.get() != -1)
				{
					if (!paused.get())
						timeOfTest.set(timeOfTest.get() + ((float) (time - lastTime.get())) / 1000f);
					timeToPause.set(timeToPause.get() - 1);
					String newTimerText = toSize((timeLimit.get() - timeOfTest.get().intValue()) / 60, 2).substring(0,
							2) + ":" + toSize((timeLimit.get() - timeOfTest.get().intValue()) % 60, 2).substring(0, 2);
					if (!timerText.equals(newTimerText))
						Platform.runLater(
								() -> ((ButtonFXTextContent) timer.getContent()).setText(timerText = newTimerText));
					if (timeOfTest.get() >= timeLimit.get())
						Platform.runLater(() -> doFinish());

					if (test.getTimeEndWarning() >= timeLimit.get() - timeOfTest.get())
						Platform.runLater(() -> getPanel().setBackground(
								(timeLimit.get() - timeOfTest.get().intValue()) % 2 == 0 ? warningBackground
										: standardBackground));
				}
				fullTime.set(fullTime.get() + ((float) (time - lastTime.get())) / 1000f);
				lastTime.set(time);
			}
		});
	}

	int qsp = 100;

	@Override
	protected void _resize(int w, int h)
	{

		testingPane.setPrefSize(w, h);
		// questionSelectScrollPane.setPrefSize(qsp, h);
		// questionSelectPanel.setPrefSize(questionSelectScrollPane.getPrefWidth() - 16,
		// questionSelectScrollPane.getPrefHeight() - 2);
		resizeQuestionsPane(w, h);

		int x1 = (int) (qsp + Main.spaceBetweenComponents);
		int width1 = (int) (w - x1 - Main.spaceBetweenComponents);

		questionSign.setLayoutX(x1);
		questionSign.setLayoutY(Main.spaceBetweenComponents);
		questionSign.setPrefSize(width1, 150);

		qualityIndicator.setLayoutX(x1);
		qualityIndicator.setPrefSize(width1, 32);
		qualityIndicator.setLayoutY(h - qualityIndicator.getPrefHeight() - Main.spaceBetweenComponents);

		questionInfoTextfield.setLayoutX(x1);
		questionInfoTextfield.setPrefSize(width1, 80);
		questionInfoTextfield.setLayoutY(
				qualityIndicator.getLayoutY() - qualityIndicator.getPrefHeight() - Main.spaceBetweenComponents);

		optionButtonsPanel.setLayoutX(x1);
		optionButtonsPanel.setPrefSize(width1, 32);
		optionButtonsPanel.setLayoutY(
				questionInfoTextfield.getLayoutY() - qualityIndicator.getPrefHeight() - Main.spaceBetweenComponents);
		updateOptionButtonsPane();

		int count = test.getQuestions()[currentQuestionNumber.get()].getAnswers().length;
		resizeAnswersPane(test.getQuestions()[currentQuestionNumber.get()] instanceof EnterTextQuestion, count, w, h);

		questionsTablePane.setPrefSize(w, h);
		questionsTable.setPrefSize(w, h - 32);
	}

	private void resizeAnswersPane(boolean enterTextQuestion, int count, double w, double h)
	{
		answersScrollPane.setVisible(!enterTextQuestion);
		enterTextButton.setVisible(enterTextQuestion);
		int x1 = (int) (qsp + Main.spaceBetweenComponents);
		int width1 = (int) (w - x1 - Main.spaceBetweenComponents);

		int hWithoutSpace;
		int space;

		if (enterTextQuestion)
		{
			enterTextButton.setLayoutX(x1);
			enterTextButton
					.setLayoutY(questionSign.getLayoutY() + questionSign.getPrefHeight() + Main.spaceBetweenComponents);
			enterTextButton.setPrefSize(width1, minAnswerButtonHeight);
		}
		else
		{
			answersScrollPane.setLayoutX(x1);
			answersScrollPane
					.setLayoutY(questionSign.getLayoutY() + questionSign.getPrefHeight() + Main.spaceBetweenComponents);
			answersScrollPane.setPrefSize(width1,
					optionButtonsPanel.getLayoutY() - answersScrollPane.getLayoutY() - Main.spaceBetweenComponents);
			if (testingPartSettings.isFillAllHeightOfAnswersPanel())
			{
				if (testingPartSettings.isMaximazeHeightOfAnswersPanelElements())
				{
					// answersPanel.setPrefWidth(answersScrollPane.getPrefWidth() - 14);
					int fullH = (int) answersScrollPane.getPrefHeight() - 2;
					hWithoutSpace = Math.max(
							((int) fullH + Main.spaceBetweenComponents) / count - Main.spaceBetweenComponents,
							minAnswerButtonHeight);
					space = Main.spaceBetweenComponents;
					// for (int i = 0; i < count; i++)
					// {
					// answerButtons[i].setLayoutX(0);
					// answerButtons[i].setLayoutY((hWithoutSpace + space) * i);
					// answerButtons[i].setPrefSize(answersPanel.getPrefWidth(), hWithoutSpace);
					// }
					// answersPanel.setPrefHeight((hWithoutSpace + space) * count - space);
					// answersScrollPane.setPrefHeight(
					// Math.min(answersPanel.getPrefHeight() + 2,
					// answersScrollPane.getPrefHeight()));
				}
				else
				{
					// answersPanel.setPrefWidth(answersScrollPane.getPrefWidth() - 14);
					int fullH = (int) answersScrollPane.getPrefHeight() - 2;
					hWithoutSpace = minAnswerButtonHeight;
					space = Math.max(((int) fullH - minAnswerButtonHeight * count) / (count - 1),
							Main.spaceBetweenComponents);
					// for (int i = 0; i < count; i++)
					// {
					// answerButtons[i].setLayoutX(0);
					// answerButtons[i].setLayoutY((hWithoutSpace + space) * i);
					// answerButtons[i].setPrefSize(answersPanel.getPrefWidth(), hWithoutSpace);
					// }
					// answersPanel.setPrefHeight((hWithoutSpace + space) * count - space);
					// answersScrollPane.setPrefHeight(
					// Math.min(answersPanel.getPrefHeight() + 2,
					// answersScrollPane.getPrefHeight()));
				}
			}
			else
			{
				hWithoutSpace = minAnswerButtonHeight;
				space = Main.spaceBetweenComponents;
			}
			answersPanel.setPrefWidth(answersScrollPane.getPrefWidth() - 14);
			for (int i = 0; i < count; i++)
			{
				answerButtons[i].setLayoutX(0);
				answerButtons[i].setLayoutY((hWithoutSpace + space) * i);
				answerButtons[i].setPrefSize(answersPanel.getPrefWidth(), hWithoutSpace);
			}
			answersPanel.setPrefHeight((hWithoutSpace + space) * count - space);
			answersScrollPane
					.setPrefHeight(Math.min(answersPanel.getPrefHeight() + 2, answersScrollPane.getPrefHeight()));
		}
	}

	private void updateOptionButtonsPane()
	{
		int w = (int) ((optionButtonsPanel.getPrefWidth() + Main.spaceBetweenComponents)
				/ optionButtonsPanel.getChildren().size());
		int i = 0;
		if (back != null)
		{
			back.setLayoutX(w * i++);
			back.setLayoutY(0);
			back.setPrefSize(w - Main.spaceBetweenComponents, optionButtonsPanel.getPrefHeight());
		}
		if (timer != null)
		{
			timer.setLayoutX(w * i++);
			timer.setLayoutY(0);
			timer.setPrefSize(w - Main.spaceBetweenComponents, optionButtonsPanel.getPrefHeight());
		}
		if (skip != null)
		{
			skip.setLayoutX(w * i++);
			skip.setLayoutY(0);
			skip.setPrefSize(w - Main.spaceBetweenComponents, optionButtonsPanel.getPrefHeight());
		}
		if (next != null)
		{
			next.setLayoutX(w * i++);
			next.setLayoutY(0);
			next.setPrefSize(w - Main.spaceBetweenComponents, optionButtonsPanel.getPrefHeight());
		}
		if (finish != null)
		{
			finish.setLayoutX(w * i++);
			finish.setLayoutY(0);
			finish.setPrefSize(w - Main.spaceBetweenComponents, optionButtonsPanel.getPrefHeight());
		}
	}

	private void resizeQuestionsPane(double w, double h)
	{
		// int h = (int) (questionSelectPanel.getPrefHeight() /
		// questionSelectAnswerButtons.length);
		// for (int i = 0; i < questionSelectAnswerButtons.length; i++)
		// {
		// questionSelectAnswerButtons[i].setLayoutX(0);
		// questionSelectAnswerButtons[i].setLayoutY(i * h);
		// questionSelectAnswerButtons[i].setPrefSize(questionSelectPanel.getPrefWidth(),
		// h);
		// }
		int width1 = qsp;

		questionsScrollPane.setLayoutX(0);
		questionsScrollPane.setLayoutY(0);
		questionsScrollPane.setPrefSize(width1, h);

		int hWithoutSpace;
		int space;

		if (testingPartSettings.isFillAllHeightOfQuestionsPanel())
		{
			if (testingPartSettings.isMaximazeHeightOfQuestionsPanelElements())
			{
				int fullH = (int) questionsScrollPane.getPrefHeight() - 2;
				hWithoutSpace = Math.max(((int) fullH + Main.spaceBetweenComponents) / questionButtons.length
						- Main.spaceBetweenComponents, minQuestionButtonHeight);
				space = Main.spaceBetweenComponents;
				// for (int i = 0; i < questionButtons.length; i++)
				// {
				// questionButtons[i].setLayoutX(0);
				// questionButtons[i].setLayoutY((hWithoutSpace + space) * i);
				// questionButtons[i].setPrefSize(questionsPanel.getPrefWidth(), hWithoutSpace);
				// }
				// questionsPanel.setPrefHeight((hWithoutSpace + space) * questionButtons.length
				// - space);
				// questionsScrollPane.setPrefHeight(
				// Math.min(questionsPanel.getPrefHeight() + 2,
				// questionsScrollPane.getPrefHeight()));
			}
			else
			{
				// questionsPanel.setPrefWidth(questionsScrollPane.getPrefWidth() - 14);
				int fullH = (int) questionsScrollPane.getPrefHeight() - 2;
				hWithoutSpace = minQuestionButtonHeight;
				space = questionButtons.length == 1 ? 0
						: Math.max(((int) fullH - minQuestionButtonHeight * questionButtons.length)
								/ (questionButtons.length - 1), Main.spaceBetweenComponents);
			}
		}
		else
		{
			// questionsPanel.setPrefWidth(questionsScrollPane.getPrefWidth() - 14);
			hWithoutSpace = minQuestionButtonHeight;
			space = Main.spaceBetweenComponents;
			// for (int i = 0; i < count; i++)
			// {
			// questionButtons[i].setLayoutX(0);
			// questionButtons[i].setLayoutY((hWithoutSpace + space) * i);
			// questionButtons[i].setPrefSize(questionsPanel.getPrefWidth(), hWithoutSpace);
			// }
			// questionsPanel.setPrefHeight((hWithoutSpace + space) * count - space);
			// questionsScrollPane
			// .setPrefHeight(Math.min(questionsPanel.getPrefHeight() + 2,
			// questionsScrollPane.getPrefHeight()));
		}
		questionsPanel.setPrefWidth(questionsScrollPane.getPrefWidth() - 14);
		for (int i = 0; i < questionButtons.length; i++)
		{
			questionButtons[i].setLayoutX(0);
			questionButtons[i].setLayoutY((hWithoutSpace + space) * i);
			questionButtons[i].setPrefSize(questionsPanel.getPrefWidth(), hWithoutSpace);
		}
		questionsPanel.setPrefHeight((hWithoutSpace + space) * questionButtons.length - space);
		questionsScrollPane
				.setPrefHeight(Math.min(questionsPanel.getPrefHeight() + 2, questionsScrollPane.getPrefHeight()));
	}

	/**
	 * 
	 * @param classNumber
	 * @param classLetter
	 * @param surname
	 * @param name
	 * @param secondName
	 */
	private void createGUI()
	{
		optionButtonsPanel.getChildren().clear();
		questionsPanel.getChildren().clear();
		answersPanel.getChildren().clear();

		this.questionButtons = new ButtonFX[test.getQuestions().length];
		int maxCountOfAnswers = 0;
		for (Question<?> q : test.getQuestions())
			if (!(q instanceof EnterTextQuestion))
				maxCountOfAnswers = Math.max(maxCountOfAnswers, q.getAnswers().length);
		this.answerButtons = new ButtonFX[maxCountOfAnswers];
		if (test.getTableQuestionSelector() == null)
			if (pauseOnUnfocus.get())
				stage.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue,
						Boolean focused) -> pause(!focused && !showLogs));

		questionSign.setFramesize(6);
		questionSign.setEnabled(false);
		questionInfoTextfield
				.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(3), Insets.EMPTY),
						new BackgroundFill(Color.BEIGE, new CornerRadii(3), new Insets(1))));
		questionInfoTextfield.setPadding(new Insets(3));
		questionInfoTextfield.setWrapText(true);
		setAligmentToCenter(questionInfoTextfield);
		if (test.getTableQuestionSelector() != null)
		{
			Font font = new Label().getFont();
			int[] w = new int[test.getTableQuestionSelector().getCols().length + 1];
			int[] h = new int[test.getTableQuestionSelector().getRows().length + 1];
			int aw = 0;
			questionsTable.getColumnConstraints().clear();
			{
				w[0] = MathUtils.size(test.getName(), java.awt.Font.decode(font.getName()+"-"+font.getStyle()+"-"+font.getSize())).width + 20;
				for (String s : test.getTableQuestionSelector().getRows())
					w[0] = Math.max(w[0], MathUtils.size(s, java.awt.Font.decode(font.getName()+"-"+font.getStyle()+"-"+font.getSize())).width + 20);
				for (int x = 0; x < test.getTableQuestionSelector().getCols().length; x++)
				{
					w[x + 1] = MathUtils.size(test.getTableQuestionSelector().getCols()[x], java.awt.Font.decode(font.getName()+"-"+font.getStyle()+"-"+font.getSize())).width + 20;
					for (int y = 0; y < test.getTableQuestionSelector().getRows().length; y++)
						w[x + 1] = Math.max(w[x + 1],
								MathUtils.size(test.getTableQuestionSelector().getQuestionsTable()[x][y].getText(),
										java.awt.Font.decode(font.getName()+"-"+font.getStyle()+"-"+font.getSize())).width + 20);
				}
			}
			{
				h[0] = MathUtils.size(test.getName(), java.awt.Font.decode(font.getName()+"-"+font.getStyle()+"-"+font.getSize())).height + 20;
				for (String s : test.getTableQuestionSelector().getCols())
					h[0] = Math.max(h[0], MathUtils.size(s, java.awt.Font.decode(font.getName()+"-"+font.getStyle()+"-"+font.getSize())).height + 20);
				for (int y = 0; y < test.getTableQuestionSelector().getRows().length; y++)
				{
					h[y + 1] = MathUtils.size(test.getTableQuestionSelector().getRows()[y], java.awt.Font.decode(font.getName()+"-"+font.getStyle()+"-"+font.getSize())).height + 20;
					for (int x = 0; x < test.getTableQuestionSelector().getCols().length; x++)
						h[y + 1] = Math.max(h[y + 1],
								MathUtils.size(test.getTableQuestionSelector().getQuestionsTable()[x][y].getText(),
										java.awt.Font.decode(font.getName()+"-"+font.getStyle()+"-"+font.getSize())).height + 20);
				}
			}
			{
				for (int x = 0; x < w.length; x++)
					aw += w[x];
			}
			{
				for (int x = 0; x < w.length; x++)
					w[x] = (int) ((double) w[x] * (double) questionsTable.getPrefWidth() / (double) aw);
			}
			for (int x = 0; x < test.getTableQuestionSelector().getCols().length + 1; x++)
				questionsTable.getColumnConstraints()
						.add(new ColumnConstraints(w[x], w[x], w[x], Priority.NEVER, HPos.CENTER, true));
			questionsTable.getRowConstraints().clear();
			for (int y = 0; y < test.getTableQuestionSelector().getRows().length + 1; y++)
				questionsTable.getRowConstraints()
						.add(new RowConstraints(h[y], h[y], h[y], Priority.NEVER, VPos.CENTER, true));

			questionsTable.add(new Label(test.getName()), 0, 0);
			for (int x = 0; x < test.getTableQuestionSelector().getCols().length; x++)
			{
				Label lbl = new Label(test.getTableQuestionSelector().getCols()[x]);
				questionsTable.add(lbl, x + 1, 0);
			}
			for (int y = 0; y < test.getTableQuestionSelector().getRows().length; y++)
			{
				Label lbl = new Label(test.getTableQuestionSelector().getRows()[y]);
				questionsTable.add(lbl, 0, y + 1);
			}
			for (int x = 0; x < test.getTableQuestionSelector().getQuestionsTable().length; x++)
				for (int y = 0; y < test.getTableQuestionSelector().getQuestionsTable()[x].length; y++)
				{
					ButtonFX btn = new ButtonFX(
							new ButtonFXTextContent(
									test.getTableQuestionSelector().getQuestionsTable()[x][y].getText()),
							new CornerRadii(0), new CornerRadii(0));
					btn.setFramesize(2);
					int xx = x, yy = y;
					btn.addActionListener(e ->
					{
						questionsTablePane.setVisible(false);
						testingPane.setVisible(true);
						openQuestion(currentQuestionNumber.get(),
								currentQuestionNumber.setAndReturn(test.getQuestionNumberAtQuestionsByQuestionIndex(
										test.getTableQuestionSelector().getQuestionsTable()[xx][yy]
												.getQuestionNumber())));
						btn.setDisable(true);
						((ButtonFXTextContent) btn.getContent()).setText("");
						curBtn = btn;
					});
					btn.setPrefSize(w[x + 1], h[y + 1]);
					questionsTable.add(btn, x + 1, y + 1);
				}
		}
		// Options pane
		{
			int pos = 0, count = 0;

			if (timeLimit.get() != -1)
			{
				timer = new ButtonFX(new ButtonFXTextContent(""), new CornerRadii(8, 8, 8, 8, false),
						new CornerRadii(6, 6, 6, 6, false));
				timer.addActionListener(e -> pause(!paused.get()));
				count++;
			}

			if (test.getTableQuestionSelector() == null)
			{
				skip = new ButtonFX(new ButtonFXTextContent(""), new CornerRadii(8, 8, 8, 8, false),
						new CornerRadii(6, 6, 6, 6, false));
				skip.addActionListener(e -> skip());
				skip.setEnabled(skipButtonOption.get());
				count++;

				next = new ButtonFX(new ButtonFXTextContent(""), new CornerRadii(8, 8, 8, 8, false),
						new CornerRadii(6, 6, 6, 6, false));
				next.addActionListener(e -> next());
				count++;
			}

			if (test.getTableQuestionSelector() != null)
			{
				back = new ButtonFX(new ButtonFXTextContent(""), new CornerRadii(8, 8, 8, 8, false),
						new CornerRadii(6, 6, 6, 6, false));
				back.addActionListener(e -> back());
				count++;
			}

			finish = new ButtonFX(new ButtonFXTextContent(""), new CornerRadii(8, 8, 8, 8, false),
					new CornerRadii(6, 6, 6, 6, false));
			finish.addActionListener(e -> doFinish());
			count++;
			// Set bounds
			int optionsSpace = 5,
					optionButtonAndSpaceWidth = ((int) optionButtonsPanel.getPrefWidth() + optionsSpace) / count,
					optionButtonWidth = optionButtonAndSpaceWidth - optionsSpace;
			if (timer != null)
			{
				optionButtonsPanel.getChildren().add(timer);
				timer.setBounds(optionButtonAndSpaceWidth * pos++, 0, optionButtonWidth,
						optionButtonsPanel.getPrefHeight());
			}
			if (skip != null)
			{
				optionButtonsPanel.getChildren().add(skip);
				skip.setBounds(optionButtonAndSpaceWidth * pos++, 0, optionButtonWidth,
						optionButtonsPanel.getPrefHeight());
			}
			if (next != null)
			{
				optionButtonsPanel.getChildren().add(next);
				next.setBounds(optionButtonAndSpaceWidth * pos++, 0, optionButtonWidth,
						optionButtonsPanel.getPrefHeight());
			}
			if (back != null)
			{
				optionButtonsPanel.getChildren().add(back);
				back.setBounds(optionButtonAndSpaceWidth * pos++, 0, optionButtonWidth,
						optionButtonsPanel.getPrefHeight());
			}
			if (test.getTableQuestionSelector() == null)
			{
				optionButtonsPanel.getChildren().add(finish);
				finish.setBounds(optionButtonAndSpaceWidth * pos++, 0, optionButtonWidth,
						optionButtonsPanel.getPrefHeight());
			}
			else
			{
				questionsTablePane.getChildren().add(finish);
				finish.setBounds(questionsTablePane.getPrefWidth() - 100, questionsTable.getPrefHeight(), 100,
						questionsTablePane.getPrefHeight() - questionsTable.getPrefHeight());
			}
		}

		qualityIndicator.setVisible(indicateQualityOfLastAnswer.get());
		if (indicateQualityOfAllAnswers.get())
		{
			qualityIndicators = new Pane[test.getQuestions().length];
			for (int i = 0; i < qualityIndicators.length; i++)
			{
				qualityIndicator.getChildren().add(qualityIndicators[i] = new Pane());
				qualityIndicators[i].setLayoutX(qualityIndicator.getPrefWidth() / test.getQuestions().length * i);
				qualityIndicators[i].setPrefSize(qualityIndicator.getPrefWidth() / test.getQuestions().length - 5,
						qualityIndicator.getPrefHeight());
			}
		}

		enterTextButton = new ButtonFX(new ButtonFXTextContent(""), new CornerRadii(8, 8, 8, 8, false),
				new CornerRadii(6, 6, 6, 6, false));
		enterTextButton.addActionListener(e -> enterTextButton.setClicked(false));
		((ButtonFXTextContent) enterTextButton.getContent()).setEditable(true);
		testingPane.getChildren().add(enterTextButton);

		for (int i = 0; i < questionButtons.length; i++)
		{
			questionButtons[i] = new ButtonFX(new ButtonFXTextContent(i + 1 + "/" + test.getQuestions().length),
					new CornerRadii(8, 8, 8, 8, false), new CornerRadii(6, 6, 6, 6, false));
			questionsPanel.getChildren().add(questionButtons[i]);
		}

		for (int i = 0; i < answerButtons.length; i++)
		{
			answerButtons[i] = new ButtonFX(new ButtonFXHtmlContent(""), new CornerRadii(8, 8, 8, 8, false),
					new CornerRadii(6, 6, 6, 6, false));
			answersPanel.getChildren().add(answerButtons[i]);
		}

		testingPane.setVisible(test.getTableQuestionSelector() == null);
		questionsTablePane.setVisible(test.getTableQuestionSelector() != null);
		window.setVisible(true);
	}

	HashMap<Long, Entry<String, String>> testLog = new HashMap<Long, Entry<String, String>>();

	private void doFinish()
	{
		if (skippedQuestions.size() == 1)
			if (skippedQuestions.get(0) == currentQuestionNumber)
				goNext();
		if (timeLimit.get() != -1 && timeOfTest.get() >= timeLimit.get() || skippedQuestions.size() == 0)
			finish(test.getName(), testFileName);
		else FXDialogsGenerator.showFXDialog(stage, msgSys.getMsg("youHaveSkipped"), null, true);
	}

	private void back()
	{
		saveAnswer(currentQuestionNumber.get());
		handleQuestion();
		testingPane.setVisible(false);
		questionsTablePane.setVisible(true);
	}

	// /**
	// *
	// */
	// private void createQuestionSelectPanel()
	// {
	// questionButtons = new ButtonFX[test.getQuestions().length];
	// for (int i = 0; i < test.getQuestions().length; i++)
	// {
	// questionSelectAnswerButtons[i] = new ButtonFX(
	// new ButtonFXTextContent(i + 1 + "/" + test.getQuestions().length),
	// new CornerRadii(8, 8, 8, 8, false), new CornerRadii(6, 6, 6, 6, false));
	// int q = i;
	// questionSelectAnswerButtons[i].addActionListener(event ->
	// {
	// questionSelectAnswerButtons[q].setClicked(false);
	// int prev = currentQuestionNumber.get();
	// currentQuestionNumber.set(q);
	// saveAnswer(prev);
	// openQuestion(prev, currentQuestionNumber.get());
	// for (ButtonFX btnx : questionSelectAnswerButtons)
	// {
	// btnx.setClicked(false);
	// btnx.setPressedV(false);
	// btnx.setSelected(false);
	// }
	// });
	// questionsPanel.getChildren().add(questionSelectAnswerButtons[i]);
	// }
	// }

	/**
	 * 
	 */
	private void pause(boolean paused)
	{
		if (pauseOption.get() && timeToPause.get() <= 0)
		{
			this.paused.set(paused);
			questionSign.setVisible(!paused);
			next.setVisible(!paused);
			finish.setVisible(!paused);
			qualityIndicator.setVisible(!paused);
			skip.setVisible(!paused);
			questionsScrollPane.setVisible(!paused);
			answersScrollPane.setVisible(!paused);
			timeToPause.set(300);
			timer.setClicked(paused);
		}
	}

	/**
	 * 
	 */
	private void skip()
	{
		int qn = currentQuestionNumber.get();
		goNext();
		if (!skippedQuestions.contains(qn))
			skippedQuestions.add(new SecureContainer<Integer>(qn));
		openQuestion(currentQuestionNumber.get() - 1, currentQuestionNumber.get());
	}

	/**
	 * 
	 */
	private void next()
	{
		handleQuestion();
		openQuestion(currentQuestionNumber.get(), currentQuestionNumber.get());
	}

	/**
	 * 
	 * @param window
	 * @param _class
	 * @param surname
	 * @param name
	 * @param secondName
	 */
	private void finish(String testName, String testFileName)
	{
		saveAnswer(currentQuestionNumber.get());
		handleQuestion();
		paused.set(true);
		finished.set(true);
		timer1.stop();
		int maxResult = 0;
		for (Question<?> question : test.getQuestions())
			maxResult += question.getMaxAward();
		showResult(testName, testFileName);
		if (true)
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
	private int countResult()
	{
		int result = 0;
		for (int i = 0; i < test.getQuestions().length; i++)
			result += _handleQuestion(i)[1];
		return result;
	}

	/**
	 * 
	 */
	private void handleQuestion()
	{
		saveAnswer(currentQuestionNumber.get());
		int[] res = _handleQuestion(currentQuestionNumber.get());
		int minResult = res[0], questionResult = res[1], maxResult = res[2];

		if (indicateQualityOfLastAnswer.get())
		{
			Pane panel = indicateQualityOfAllAnswers.get() ? (Pane) qualityIndicators[currentQuestionNumber.get()]
					: qualityIndicator;
			if (test.getTableQuestionSelector() == null)
				if (questionResult == maxResult)
					panel.setBackground(test.getPerfectAnswerBackground());
				else if (questionResult > minResult)
					panel.setBackground(test.getRightAnswerBackground());
				else panel.setBackground(test.getWrongAnswerBackground());
			else if (curBtn != null)
				if (questionResult == maxResult)
					curBtn.setBackground(test.getPerfectAnswerBackground());
				else if (questionResult > minResult)
					curBtn.setBackground(test.getRightAnswerBackground());
				else curBtn.setBackground(test.getWrongAnswerBackground());
			if (questionResult > maxResult)
				FXDialogsGenerator.showFXDialog(stage, msgSys.getMsg("questionResultMoreThanMaxResult"), null, true);
		}
		if (showRightAnswer.get())
		{
			String rightAnswer = "";
			Question<?> question = test.getQuestions()[currentQuestionNumber.get()];
			if (question instanceof EnterTextQuestion)
			{
				for (EnterTextAnswerVariant a : ((EnterTextQuestion) question).getAnswers())
					if (test.getQuestions()[currentQuestionNumber.get()].getAward()
							+ a.getAward() == test.getQuestions()[currentQuestionNumber.get()].getMaxAward())
						rightAnswer += a.getText() + "\n";
			}
			int i = 0;
			if (question instanceof ChooseOneQuestion)
				for (ChooseOneAnswerVariant a : ((ChooseOneQuestion) question).getAnswers())
				{
					if (test.getQuestions()[currentQuestionNumber.get()].getAward()
							+ a.getAward() == test.getQuestions()[currentQuestionNumber.get()].getMaxAward())
					{
						rightAnswer += a.getHtml() + "\n";
						answerButtons[i].setDisabledBackground(theme.getQuestionSelectSkippedBackground());
						answerButtons[i].setEnabled(false);
					}
					i++;
				}
			if (question instanceof SelectMultipleQuestion)
				for (SelectMultipleAnswerVariant a : ((SelectMultipleQuestion) question).getAnswers())
				{
					if (test.getQuestions()[currentQuestionNumber.get()].getAward() + a.getAward() > 0)
					{
						rightAnswer += a.getHtml() + "\n";
						answerButtons[i].setDisabledBackground(theme.getQuestionSelectSkippedBackground());
						answerButtons[i].setEnabled(false);
					}
					i++;
				}
			FXDialogsGenerator.showFXDialog(stage, rightAnswer, null, true);
		}
		goNext();
	}

	/**
	 * 
	 */
	private void goNext()
	{
		if (!canGoToAllQuestions.get())
			if (skippedQuestions.contains((Object) currentQuestionNumber))
			{
				skippedQuestions.remove((Object) currentQuestionNumber);
				if (skippedQuestions.size() != 0)
				{
					for (int i = 0; i < skippedQuestions.size(); i++)
						if (skippedQuestions.get(i).get() > currentQuestionNumber.get())
						{
							currentQuestionNumber = skippedQuestions.get(i);
							break;
						}
						else if (i == skippedQuestions.size() - 1)
							if (skippedQuestions.get(i).get() >= lastNotSkippedQuestion.get())
								currentQuestionNumber = skippedQuestions.get(0);
							else currentQuestionNumber = lastNotSkippedQuestion;
				}
				else currentQuestionNumber = lastNotSkippedQuestion;
			}
			else currentQuestionNumber.set(currentQuestionNumber.get() + 1);
		else currentQuestionNumber.set(currentQuestionNumber.get() + 1);
	}

	TestSettings testSettings;
	TestingPartSettings testingPartSettings;

	private String htmlToText(String html)
	{
		return Parser.parse(html, "").text();
	}

	/**
	 * 
	 * @param testName
	 * @param testFileName
	 */
	private TestResult createTestResult(String testName, String testFileName)
	{
		Result<?>[] results = new Result<?>[test.getQuestions().length];
		for (int i = 0; i < results.length; i++)
		{
			Question question = test.getQuestions()[i];
			Answer answer = answers[i];
			int[] res = _handleQuestion(i);
			if (answer != null)
				if (question instanceof ChooseOneQuestion)
				{
					HashMap<String, Integer> correctAnswers = new HashMap<String, Integer>();
					HashMap<String, Integer> passableAnswers = new HashMap<String, Integer>();
					for (AnswerVariant answerVar : question.getAnswers())
						if (question.getAward() + answerVar.getAward() == question.getMaxAward())
							correctAnswers.put(htmlToText(((ChooseOneAnswerVariant) answerVar).getHtml()),
									answerVar.getAward());
						else if (answerVar.getAward() > 0)
							passableAnswers.put(htmlToText(((ChooseOneAnswerVariant) answerVar).getHtml()),
									answerVar.getAward());
					results[i] = new ChooseOneResult(res[0], res[1], res[2],
							((ChooseOneAnswer) answers[i]).getSelectedAnswerNumber() == -1 ? null
									: htmlToText(((ChooseOneAnswerVariant) test.getQuestions()[i]
											.getAnswers()[((ChooseOneAnswer) answers[i]).getSelectedAnswerNumber()])
													.getHtml()),
							correctAnswers, passableAnswers);
				}
				else if (question instanceof SelectMultipleQuestion)
				{
					HashMap<String, Integer> selectedAnswers = new HashMap<String, Integer>();
					for (int j = 0; j < ((SelectMultipleAnswer) answer).getSelectedAnswers().length; j++)
						if (((SelectMultipleAnswer) answer).getSelectedAnswers()[j])
							selectedAnswers.put(
									htmlToText(((SelectMultipleAnswerVariant) question.getAnswers()[j]).getHtml()),
									question.getAnswers()[j].getAward());
					HashMap<String, Integer> correctAnswers = new HashMap<String, Integer>();
					for (AnswerVariant answerVar : question.getAnswers())
						if (answerVar.getAward() > 0)
							correctAnswers.put(htmlToText(((ChooseOneAnswerVariant) answerVar).getHtml()),
									answerVar.getAward());
					results[i] = new SelectMultipleResult(res[0], res[1], res[2], selectedAnswers, correctAnswers);
				}
				else if (question instanceof EnterTextQuestion)
				{
					HashMap<String, Integer> correctAnswers = new HashMap<String, Integer>();
					HashMap<String, Integer> passableAnswers = new HashMap<String, Integer>();
					for (AnswerVariant answerVar : question.getAnswers())
						if (question.getAward() + answerVar.getAward() == question.getMaxAward())
							correctAnswers.put(((EnterTextAnswerVariant) answerVar).getText(), answerVar.getAward());
						else if (answerVar.getAward() > 0)
							passableAnswers.put(((EnterTextAnswerVariant) answerVar).getText(), answerVar.getAward());
					results[i] = new EnterTextResult(res[0], res[1], res[2], ((EnterTextAnswer) answer).getAnswerText(),
							correctAnswers, passableAnswers);
				}
				else if (question instanceof ArrangementQuestion)
				{
					results[i] = null;// new ArrangementResult(res[0], res[1], res[2]);
				}
				else if (question instanceof MatchingQuestion)
				{
					results[i] = null;// new MatchingResult(res[0], res[1], res[2]);
				}
				else if (question instanceof DistributionQuestion)
				{
					results[i] = null;// new DistributionResult(res[0], res[1], res[2]);
				}
		}
		return new TestResult(new Date(), testName, testeeInfo.get(), testerInfo.get(), testSettings,
				testingPartSettings, timeLimit.get(), timeOfTest.get(), fullTime.get(), results);
	}

	/**
	 * 
	 * @param testName
	 * @param testFileName
	 */
	private void showResult(String testName, String testFileName)
	{
		TestResult testResult = createTestResult(testName, testFileName);
		SystemUtils
				.writeFile(
						new File(getDataPath() + "/results/local/" + testFileName + "/"
								+ Calendar.getInstance().getTimeInMillis() + ".data"),
						ByteUtils.objectToByteArray(testResult));
		if (testeeInfo.get().getLogin() != null)
			sendResultToTeacher(testeeInfo.get().getLogin(), testResult);
		testResult.show(stage, testeeInfo.get().getLogin() == null ? teacher ->
		{
			sendResultToTeacher(teacher, testResult);
			FXDialogsGenerator.showFXDialog(stage, msgSys.getMsg("resultSended"), null, true);
		} : null);
	}

	private final Container<ArrayList<String>> onlineTeachers = new Container<ArrayList<String>>(null);
	{
		Main.client.addRecieveListener(e ->
		{
			if (e.getSource() instanceof OnlineLoginsPacket)
				onlineTeachers.set(((OnlineLoginsPacket) e.getSource()).getOnline());
		});
	}

	private void sendResultToTeacher(String teacher, TestResult result)
	{
		Main.sendToServer(new ResultSendPacket("sendResultToTeacher", teacher, result));
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
	public void updateLabels()
	{
		super.updateLabels();
		if (next != null)
			((ButtonFXTextContent) next.getContent()).setText(msgSys.getMsg("next"));
		if (skip != null)
			((ButtonFXTextContent) skip.getContent()).setText(msgSys.getMsg("skip"));
		if (back != null)
			((ButtonFXTextContent) back.getContent()).setText(msgSys.getMsg("back"));
		if (finish != null)
			((ButtonFXTextContent) finish.getContent()).setText(msgSys.getMsg("finish"));
	}

	// /**
	// *
	// */
	private void updateQuestionsPane()
	{
		for (int i = 0; i < questionButtons.length; i++)
		{
			questionButtons[i]
					.setNormalBackground(skippedQuestions.contains(i) ? theme.getQuestionSelectSkippedBackground()
							: theme.getQuestionSelectNormalBackground());
			questionButtons[i].setNormalFrame(skippedQuestions.contains(i) ? theme.getQuestionSelectSkippedFrame()
					: theme.getQuestionSelectNormalFrame());
			questionButtons[i]
					.setNormalForeground(skippedQuestions.contains(i) ? theme.getQuestionSelectSkippedForeground()
							: theme.getQuestionSelectNormalForeground());
			questionButtons[i].setEnabled(
					(skippedQuestions.contains(i) || canGoToAllQuestions.get()) && i != currentQuestionNumber.get());
			questionButtons[i]
					.setDisabledBackground(i == currentQuestionNumber.get() ? theme.getSpecialButtonsBackground()
							: theme.getQuestionSelectNormalBackground());
			questionButtons[i].setDisabledFrame(i == currentQuestionNumber.get() ? theme.getSpecialButtonsFrame()
					: theme.getQuestionSelectNormalFrame());
			questionButtons[i]
					.setDisabledForeground(i == currentQuestionNumber.get() ? theme.getSpecialButtonsForeground()
							: theme.getQuestionSelectNormalForeground());
		}
	}

	/**
	 * 
	 * @param questionNumber
	 */
	private void saveAnswer(int questionNumber)
	{
		answerArrangementForArrangement = buttonsArrangement;
		answerArrangementForMatching = buttonsArrangement;
		answerArrangementForDistribution = buttonsArrangementForDistribution;
		if (test.getQuestions()[questionNumber] instanceof EnterTextQuestion)
			answers[questionNumber] = new EnterTextAnswer(
					((ButtonFXTextContent) enterTextButton.getContent()).getMainFieldText());
		else if (test.getQuestions()[questionNumber] instanceof ChooseOneQuestion)
			answers[questionNumber] = new ChooseOneAnswer(selectedAnswerNumber);
		else if (test.getQuestions()[questionNumber] instanceof SelectMultipleQuestion)
			answers[questionNumber] = new SelectMultipleAnswer(selectedAnswers);
		else if (test.getQuestions()[questionNumber] instanceof ArrangementQuestion)
			answers[questionNumber] = new ArrangementAnswer(answerArrangementForArrangement);
		else if (test.getQuestions()[questionNumber] instanceof MatchingQuestion)
			answers[questionNumber] = new MatchingAnswer(answerArrangementForMatching);
		else if (test.getQuestions()[questionNumber] instanceof DistributionQuestion)
			answers[questionNumber] = new DistributionAnswer(answerArrangementForDistribution);
	}

	/**
	 * 
	 * @param question
	 * @param info
	 * @param number
	 */
	private void openQuestion(int prev, int questionNumber)
	{
		check(questionNumber);
		questionInfoTextfield
				.setText(msgSys.getMsg(test.getQuestions()[questionNumber].getClass().getSimpleName() + "Info"));
		if (answers[questionNumber] instanceof ArrangementAnswer)
			buttonsArrangement = ((ArrangementAnswer) answers[questionNumber]).getAnswerArrangementForArrangement();
		else buttonsArrangement = test.getQuestions()[questionNumber] instanceof ArrangementQuestion
				? intLessZeroArr(answerButtons.length)// array(answerButtons.length)
				: intLessZeroArr(answerButtons.length);
		if (answers[questionNumber] instanceof MatchingAnswer)
			buttonsArrangement = ((MatchingAnswer) answers[questionNumber]).getAnswerArrangementForMatching();
		else buttonsArrangement = test.getQuestions()[questionNumber] instanceof ArrangementQuestion
				? intLessZeroArr(answerButtons.length)// array(answerButtons.length)
				: intLessZeroArr(answerButtons.length);
		if (answers[questionNumber] instanceof DistributionAnswer)
			buttonsArrangementForDistribution = ((DistributionAnswer) answers[questionNumber])
					.getAnswerArrangementForDistribution();
		else buttonsArrangementForDistribution = intLessZeroListsArr(answerButtons.length);
		if (test.getQuestions()[questionNumber] instanceof EnterTextQuestion)
		{
			for (ButtonFX button : answerButtons)
				button.setVisible(false);
			enterTextButton.setVisible(true);
			((ButtonFXTextContent) enterTextButton.getContent())
					.setText(((EnterTextAnswer) answers[questionNumber]).getAnswerText());
			enterTextButton.setClicked(false);
			enterTextButton.setEnabled(true);
		}
		else
		{
			enterTextButton.setVisible(false);
			for (int i = 0; i < answerButtons.length; i++)
				answerButtons[i].setVisible(test.getQuestions()[questionNumber].getAnswers().length > i);
			for (int i = 0; i < test.getQuestions()[questionNumber].getAnswers().length; i++)
			{
				((ButtonFXHtmlContent) answerButtons[i].getContent()).setHtml(Test.replaceContentEditable(
						((HtmlAnswerVariant) test.getQuestions()[questionNumber].getAnswers()[i]).getHtml()));
				answerButtons[i].setClicked(false);
				answerButtons[i].setEnabled(true);
			}
		}
		resizeAnswersPane(test.getQuestions()[questionNumber] instanceof EnterTextQuestion,
				test.getQuestions()[questionNumber].getAnswers().length, getMainPanel().getPrefWidth(),
				getMainPanel().getPrefHeight());
		for (int i = 0; i < test.getQuestions()[questionNumber].getAnswers().length; updateButtonMenu(questionNumber,
				i), i++)
			;

		questionSign.setDisabledBackground(theme.getDistribution().getQuestionBackground());
		questionSign.setDisabledFrame(theme.getDistribution().getQuestionFrame());
		questionSign.setDisabledForeground(theme.getDistribution().getQuestionForeground());

		for (ButtonFX answer : answerButtons)
		{
			answer.setNormalBackground(theme.getDistribution().getAnswersBackground());
			answer.setNormalFrame(theme.getDistribution().getAnswersFrame());
			answer.setNormalForeground(theme.getDistribution().getAnswersForeground());
		}
		if (test.getTableQuestionSelector() == null)
		{
			if (timer != null)
			{
				timer.setNormalBackground(theme.getSpecialButtonsBackground());
				timer.setNormalFrame(theme.getSpecialButtonsFrame());
				timer.setNormalForeground(theme.getSpecialButtonsForeground());
			}
			next.setNormalBackground(theme.getSpecialButtonsBackground());
			next.setNormalFrame(theme.getSpecialButtonsFrame());
			next.setNormalForeground(theme.getSpecialButtonsForeground());
			skip.setNormalBackground(theme.getSpecialButtonsBackground());
			skip.setNormalFrame(theme.getSpecialButtonsFrame());
			skip.setNormalForeground(theme.getSpecialButtonsForeground());
		}
		else
		{
			back.setNormalBackground(theme.getSpecialButtonsBackground());
			back.setNormalFrame(theme.getSpecialButtonsFrame());
			back.setNormalForeground(theme.getSpecialButtonsForeground());
		}
		finish.setNormalBackground(theme.getSpecialButtonsBackground());
		finish.setNormalFrame(theme.getSpecialButtonsFrame());
		finish.setNormalForeground(theme.getSpecialButtonsForeground());

		if (!skippedQuestions.contains(questionNumber))
			lastNotSkippedQuestion.set(questionNumber);
		selectedAnswers = new boolean[test.getQuestions()[questionNumber].getAnswers().length];
		if (!(questionSign.getContent() instanceof ButtonFXHtmlContent))
			questionSign.setContent(new ButtonFXHtmlContent(""));
		((ButtonFXHtmlContent) questionSign.getContent())
				.setHtml(Test.replaceContentEditable(test.getQuestions()[questionNumber].getHtml()));
		MenuItem[] items = new MenuItem[test.getQuestions()[questionNumber].getImages().length
				+ test.getQuestions()[questionNumber].getVideos().length
				+ test.getQuestions()[questionNumber].getAudios().length];
		int j = 0;
		for (int ii = 0; ii < test.getQuestions()[questionNumber].getImages().length; ii++)
		{
			int i = ii;
			items[j + i] = new MenuItem(msgSys.getMsg("image").split("")[0].toUpperCase()
					+ msgSys.getMsg("image").substring(1) + " " + (i + 1));
			items[j + i].setOnAction(e -> FXDialogsGenerator.showFXDialog(stage, (Rectangle) null,
					test.getQuestions()[questionNumber].getImages()[i], null, true));
		}
		j += test.getQuestions()[questionNumber].getImages().length;
		for (int ii = 0; ii < test.getQuestions()[questionNumber].getVideos().length; ii++)
		{
			int i = ii;
			items[j + i] = new MenuItem(msgSys.getMsg("video").split("")[0].toUpperCase()
					+ msgSys.getMsg("video").substring(1) + " " + (i + 1));
			items[j + i].setOnAction(e -> FXDialogsGenerator.showFXDialog(stage, (Rectangle) null,
					test.getQuestions()[questionNumber].getVideos()[i], null, true));
		}
		j += test.getQuestions()[questionNumber].getVideos().length;
		for (int ii = 0; ii < test.getQuestions()[questionNumber].getAudios().length; ii++)
		{
			int i = ii;
			items[j + i] = new MenuItem(msgSys.getMsg("audio").split("")[0].toUpperCase()
					+ msgSys.getMsg("audio").substring(1) + " " + (i + 1));
			items[j + i].setOnAction(e -> FXDialogsGenerator.showFXDialog(stage, (Rectangle) null,
					test.getQuestions()[questionNumber].getAudios()[i], null, true));
		}
		questionSign.setContextMenu(new ContextMenu(items));
		ArrayList<BackgroundImage> images = new ArrayList<BackgroundImage>();
		if (items.length != 0)
			images.add(clip);
		if (test.getQuestions()[questionNumber].getImages().length != 0)
			images.add(image);
		if (test.getQuestions()[questionNumber].getVideos().length != 0)
			images.add(video);
		if (test.getQuestions()[questionNumber].getAudios().length != 0)
			images.add(audio);
		questionSign.setImageBackgrounds(images.toArray(new BackgroundImage[0]));

		if ((questionNumber >= test.getQuestions().length - 1))
			wasGoToEnd = true;

		if (test.getTableQuestionSelector() == null)
		{
			next.setEnabled(questionNumber < test.getQuestions().length - 1
					&& (canGoToAllQuestions.get() || skippedQuestions.size() > 1 || !wasGoToEnd));
			skip.setEnabled(questionNumber < test.getQuestions().length - 1);
		}
		finish.setEnabled(wasGoToEnd || test.getTableQuestionSelector() != null);

		updateQuestionsPane();
	}

	private void updateButtonMenu(int questionNumber, int i)
	{
		ContextMenu m = new ContextMenu();
		if (test.getQuestions()[questionNumber] instanceof DistributionQuestion)
		{
			DistributionQuestion question = (DistributionQuestion) test.getQuestions()[questionNumber];
			for (int r = 0; r < question.getIndexesForNames().length; r++)
			{
				CheckMenuItem item = new CheckMenuItem(question.getIndexesForNames()[r]);
				int rr = r;
				item.setOnAction(e ->
				{
					if (item.isSelected()
							&& !buttonsArrangementForDistribution[i].contains(question.getIndexesForNamesIndexes()[rr]))
						buttonsArrangementForDistribution[i].add(question.getIndexesForNamesIndexes()[rr]);
					else if (!item.isSelected()
							&& buttonsArrangementForDistribution[i].contains(question.getIndexesForNamesIndexes()[rr]))
						buttonsArrangementForDistribution[i].remove((Object) question.getIndexesForNamesIndexes()[rr]);
				});
				m.getItems().add(item);
			}
		}
		else if (test.getQuestions()[questionNumber] instanceof MatchingQuestion)
		{
			MatchingQuestion question = (MatchingQuestion) test.getQuestions()[questionNumber];
			ToggleGroup tg = new ToggleGroup();
			for (int r = 0; r < question.getIndexesForNames().length; r++)
			{
				RadioMenuItem item = new RadioMenuItem(question.getIndexesForNames()[r]);
				item.setToggleGroup(tg);
				int rr = r;
				item.setOnAction(e ->
				{
					buttonsArrangement[i] = question.getIndexesForNamesIndexes()[rr];
				});
				m.getItems().add(item);
			}
		}
		else if (test.getQuestions()[questionNumber] instanceof ArrangementQuestion)
		{
			ToggleGroup tg = new ToggleGroup();
			for (int r = 0; r < test.getQuestions()[questionNumber].getAnswers().length; r++)
			{
				RadioMenuItem item = new RadioMenuItem(r + 1 + "");
				item.setToggleGroup(tg);
				int rr = r;
				item.setOnAction(e ->
				{
					buttonsArrangement[i] = rr;
				});
				m.getItems().add(item);
			}
		}
		boolean equals = true;
		if (answerButtons[i].getContextMenu() != null)
			if (answerButtons[i].getContextMenu().getItems().size() == m.getItems().size())
				for (MenuItem item : answerButtons[i].getContextMenu().getItems())
				{
					boolean match = false;
					for (MenuItem item2 : m.getItems())
					{
						if (item.getText().equals(item2.getText()))
							match = true;
					}
					if (!match)
						equals = false;
				}
			else equals = false;
		else equals = false;
		if (!equals)
			answerButtons[i].setContextMenu(m);
	}

	/**
	 * 
	 * @param questionNumber
	 * @return
	 */
	private int[] _handleQuestion(int questionNumber)
	{
		Question<?> _question = test.getQuestions()[questionNumber];
		check(questionNumber);
		int questionResult = _question.getAward();
		if (_question instanceof EnterTextQuestion)
		{
			EnterTextQuestion question = (EnterTextQuestion) _question;
			for (int i = 0; i < question.getAnswers().length; i++)
			{
				String userAnswer = ((EnterTextAnswer) answers[questionNumber]).getAnswerText(),
						curAnswer = ((EnterTextAnswerVariant) question.getAnswers()[i]).getText();
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
		if (_question instanceof ChooseOneQuestion)
		{
			ChooseOneQuestion question = (ChooseOneQuestion) _question;
			if (((ChooseOneAnswer) answers[questionNumber]).getSelectedAnswerNumber() != -1)
				questionResult += question.getAnswers()[((ChooseOneAnswer) answers[questionNumber])
						.getSelectedAnswerNumber()].getAward();
		}
		if (_question instanceof SelectMultipleQuestion)
		{
			SelectMultipleQuestion question = (SelectMultipleQuestion) _question;
			for (int i = 0; i < question.getAnswers().length; i++)
				questionResult += ((SelectMultipleAnswer) answers[questionNumber]).getSelectedAnswers()[i]
						? question.getAnswers()[i].getAward()
						: 0;
		}
		if (_question instanceof ArrangementQuestion)
		{
			ArrangementQuestion question = (ArrangementQuestion) _question;
			int res = 0;
			int[] answersIndexes = new int[question.getAnswersIndexes().length];
			for (int i = 0; i < question.getAnswersIndexes().length; i++)
				answersIndexes[question.getAnswersIndexes()[i]] = i;
			for (HashMap<Integer, Integer> hashMap : question.getAnswerArrangementForArrangement().keySet())
			{
				boolean b = true;
				for (Integer integer : hashMap.keySet())
					if (((ArrangementAnswer) answers[questionNumber])
							.getAnswerArrangementForArrangement()[answersIndexes[integer]] != hashMap.get(integer))
						b = false;
				if (b)
					if (question.isHandleOnlyMaximal())
						res = Math.max(res, question.getAnswerArrangementForArrangement().get(hashMap));
					else res += question.getAnswerArrangementForArrangement().get(hashMap);
			}
			questionResult += res;
		}
		if (_question instanceof MatchingQuestion)
		{
			MatchingQuestion question = (MatchingQuestion) _question;
			int res = 0;
			int[] answersIndexes = new int[question.getAnswersIndexes().length];
			for (int i = 0; i < question.getAnswersIndexes().length; i++)
				answersIndexes[question.getAnswersIndexes()[i]] = i;
			for (HashMap<Integer, Integer> hashMap : question.getAnswerArrangementForMatching().keySet())
			{
				boolean b = true;
				for (Integer integer : hashMap.keySet())
					if (((MatchingAnswer) answers[questionNumber])
							.getAnswerArrangementForMatching()[answersIndexes[integer]] != hashMap.get(integer))
						b = false;
				if (b)
					if (question.isHandleOnlyMaximal())
						res = Math.max(res, question.getAnswerArrangementForMatching().get(hashMap));
					else res += question.getAnswerArrangementForMatching().get(hashMap);
			}
			questionResult += res;
		}
		if (_question instanceof DistributionQuestion)
		{
			DistributionQuestion question = (DistributionQuestion) _question;
			int res = 0;
			int[] answersIndexes = new int[question.getAnswersIndexes().length];
			for (int i = 0; i < question.getAnswersIndexes().length; i++)
				answersIndexes[question.getAnswersIndexes()[i]] = i;
			for (HashMap<Entry<Integer, Boolean>, ArrayList<Integer>> hashMap : question
					.getAnswerArrangementForDistribution().keySet())
			{
				boolean b = true;
				ArrayList<Integer> list;
				for (Entry<Integer, Boolean> integerBoolean : hashMap.keySet())
					if ((list = hashMap.get(integerBoolean)) != null)
					{
						for (Integer i : list)
							if (!((DistributionAnswer) answers[questionNumber])
									.getAnswerArrangementForDistribution()[answersIndexes[integerBoolean.getFirst()]]
											.contains(i))
								b = false;
						if (integerBoolean.getSecond())
							if (((DistributionAnswer) answers[questionNumber])
									.getAnswerArrangementForDistribution()[answersIndexes[integerBoolean.getFirst()]]
											.size() != hashMap.get(integerBoolean).size())
								b = false;
					}
					else b = false;
				if (b)
					if (question.isHandleOnlyMaximal())
						res = Math.max(res, question.getAnswerArrangementForDistribution().get(hashMap));
					else res += question.getAnswerArrangementForDistribution().get(hashMap);
			}
			questionResult += res;
		}
		questionResult = Math.max(_question.getMinResult(), questionResult);
		return new int[] { _question.getAward(), questionResult, _question.getMaxAward() };
	}

	private void check(int questionNumber)
	{
		if (test.getQuestions()[questionNumber] instanceof ChooseOneQuestion
				&& !(answers[questionNumber] instanceof ChooseOneAnswer))
			answers[questionNumber] = new ChooseOneAnswer(-1);
		if (test.getQuestions()[questionNumber] instanceof SelectMultipleQuestion
				&& !(answers[questionNumber] instanceof SelectMultipleAnswer))
			answers[questionNumber] = new SelectMultipleAnswer(new boolean[6]);
		if (test.getQuestions()[questionNumber] instanceof EnterTextQuestion
				&& !(answers[questionNumber] instanceof EnterTextAnswer))
			answers[questionNumber] = new EnterTextAnswer("");
		if (test.getQuestions()[questionNumber] instanceof ArrangementQuestion
				&& !(answers[questionNumber] instanceof ArrangementAnswer))
			answers[questionNumber] = new ArrangementAnswer(intLessZeroArr(6));
		if (test.getQuestions()[questionNumber] instanceof MatchingQuestion
				&& !(answers[questionNumber] instanceof MatchingAnswer))
			answers[questionNumber] = new MatchingAnswer(intLessZeroArr(6));
		if (test.getQuestions()[questionNumber] instanceof DistributionQuestion
				&& !(answers[questionNumber] instanceof DistributionAnswer))
			answers[questionNumber] = new DistributionAnswer(intLessZeroListsArr(6));
	}

	private int[] intLessZeroArr(int length)
	{
		int[] i = new int[length];
		for (int j = 0; j < i.length; j++)
			i[j] = -1;
		return i;
	}

	private ArrayList<Integer>[] intLessZeroListsArr(int length)
	{
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] i = new ArrayList[length];
		for (int j = 0; j < i.length; j++)
			i[j] = new ArrayList<Integer>();
		return i;
	}

	@Override
	public String name()
	{
		return "tester";
	}
}
