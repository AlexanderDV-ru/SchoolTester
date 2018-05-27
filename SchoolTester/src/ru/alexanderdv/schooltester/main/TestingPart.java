package ru.alexanderdv.schooltester.main;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
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
import javafx.stage.Stage;
import ru.alexanderdv.fxutilities.components.ButtonFX;
import ru.alexanderdv.fxutilities.components.ButtonFX.ButtonFXHtmlContent;
import ru.alexanderdv.fxutilities.components.ButtonFX.ButtonFXTextContent;
import ru.alexanderdv.schooltester.utilities.Logger.ExitCodes;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.SecureContainer;
import ru.alexanderdv.schooltester.utilities.SystemUtils;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;
import ru.alexanderdv.schooltester.utilities.network.ResultSendPacket;
import ru.alexanderdv.schooltester.utilities.types.StudentInfo;
import ru.alexanderdv.schooltester.utilities.types.Test;
import ru.alexanderdv.schooltester.utilities.types.TestSettings;
import ru.alexanderdv.schooltester.utilities.types.TestingPartSettings;
import ru.alexanderdv.schooltester.utilities.types.Theme;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.Answer;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.ArrangementAnswer;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.ArrangementQuestion;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.DistributionAnswer;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.DistributionQuestion;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.EnterTextAnswer;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.EnterTextAnswerVariant;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.EnterTextQuestion;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.HtmlAnswerVariant;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.MatchingAnswer;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.MatchingQuestion;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.PickOneAnswer;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.PickOneAnswerVariant;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.PickOneQuestion;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.Question;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.SelectSomeAnswer;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.SelectSomeAnswerVariant;
import ru.alexanderdv.schooltester.utilities.types.questionvariants.SelectSomeQuestion;
import ru.alexanderdv.simpleutilities.Entry;
import ru.alexanderdv.simpleutilities.MathWithText;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class TestingPart extends ProtectedFXWindow
{
	private final ScrollPane questionSelectScrollPane;
	private final ScrollPane answersScrollPane;
	private final Pane questionSelectPanel, answersPanel, optionButtonsPanel;
	private final ButtonFX questionSign;
	private final Pane qualityIndicator;
	private final Label questionInfoTextfield;
	private final Pane testingPane;
	private final Pane questionsTablePane;
	private final GridPane questionsTable;

	private ButtonFX timer, skip, next, finish, back;

	private static final MessageSystem msgSys = StartPart.msgSys;
	private Test test;
	private Theme theme;
	private Answer<?>[] answers;
	private int[] buttonsArrangement;
	private ArrayList<Integer>[] buttonsArrangementForDistribution;
	private ArrayList<Integer> skippedQuestions;
	private int currentQuestionNumber;
	private int lastNotSkippedQuestion;
	// User answer
	private boolean[] selectedAnswers;
	private int selectedAnswerNumber;
	private int[] answerArrangementForArrangement;
	private int[] answerArrangementForMatching;
	private ArrayList<Integer>[] answerArrangementForDistribution;
	// User answer End

	private final SecureContainer<Boolean> indicateQualityOfLastAnswer;
	private final SecureContainer<Boolean> indicateQualityOfAllAnswers;
	private final SecureContainer<Boolean> showRightAnswer;
	private final SecureContainer<Boolean> canGoToAllQuestions;
	private final SecureContainer<Boolean> skipButtonOption;
	private final SecureContainer<Boolean> pauseOption;
	private final SecureContainer<Boolean> pauseOnUnfocus;

	private final boolean fixedQSelectBtnHeight;

	private final SecureContainer<Boolean> paused;
	private final SecureContainer<Integer> timeLimit;
	private final SecureContainer<Float> timeOfTest, fullTime;
	private final SecureContainer<Integer> rightAnswers, perfectAnswers;
	private final SecureContainer<Integer> timeToPause;
	private final SecureContainer<Long> lastTime;
	private final SecureContainer<Boolean> finished;

	private boolean wasGoToEnd;
	// private Pane questionSelectPanel, answersPanel;
	private ButtonFX[] questionSelectAnswerButtons;
	// private ButtonFX questionSign;
	private ButtonFX[] answerButtons;
	private ButtonFX enterButton;
	// private ButtonFX timer, skip, next, finish, back;
	// private Pane qualityIndicator;
	private Pane[] qualityIndicators;
	private ButtonFX curBtn;
	private String timerText = "";
	private boolean showLogs;
	private boolean fillAllHeight, maximazeHeight;
	private static BackgroundImage clip, image, video, audio;
	private static final double questionSignRounding = 40;
	static
	{
		try
		{
			double offset = questionSignRounding - Math.sqrt(Math.pow(questionSignRounding, 2) / 2);
			BackgroundPosition position = new BackgroundPosition(Side.RIGHT, offset, false, Side.BOTTOM, offset, false);
			clip = new BackgroundImage(new Image(TestingPart.class.getResource("/clip.png").openStream()), BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, position, BackgroundSize.DEFAULT);
			image = new BackgroundImage(new Image(TestingPart.class.getResource("/image.png").openStream()), BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, position, BackgroundSize.DEFAULT);
			video = new BackgroundImage(new Image(TestingPart.class.getResource("/video.png").openStream()), BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, position, BackgroundSize.DEFAULT);
			audio = new BackgroundImage(new Image(TestingPart.class.getResource("/audio.png").openStream()), BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, position, BackgroundSize.DEFAULT);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			Main.exit(ExitCodes.UnknownError);
		}
	}
	boolean canSendResult;
	String teacher;

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

	public TestingPart(Rectangle parentPos, Test test, String testFileName, Theme theme, StudentInfo studentInfo, TestSettings settings,
			TestingPartSettings testingPartSettings, boolean canSendResult, String teacher)
	// {
	// TODO Auto-generated constructor stub
	// }
	// public TestingPart(Rectangle parentPosition, final Test test, String testFileName, final Theme theme, String classNumber, String classLetter,
	// String surname, String name, String secondName, boolean indicateQualityOfLastAnswer, boolean indicateQualityOfAllAnswers, boolean showRightAnswer,
	// boolean canGoToAllQuestions, boolean skipButtonOption, boolean pauseOption, boolean pauseOnUnfocus, boolean anticopy, boolean antiscreenshot,
	// boolean fixedQSelectBtnHeight, boolean hide, int spaceText, boolean fillAllHeight, boolean maximazeHeight)
	{
		super(null, Main.createPane(600, 800), 0, 1, true, true);
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

		panel.getChildren().add(this.testingPane = new Pane());
		testingPane.getChildren().add(this.questionSelectScrollPane = new ScrollPane(this.questionSelectPanel = new Pane()));
		testingPane.getChildren().add(this.questionSign = new ButtonFX(null, new CornerRadii(0, 0, questionSignRounding, questionSignRounding, false),
				new CornerRadii(0, 0, questionSignRounding * 0.8, questionSignRounding * 0.8, false)));
		testingPane.getChildren().add(this.answersScrollPane = new ScrollPane(this.answersPanel = new Pane()));
		testingPane.getChildren().add(this.optionButtonsPanel = new Pane());
		testingPane.getChildren().add(this.qualityIndicator = new Pane());
		testingPane.getChildren().add(this.questionInfoTextfield = new Label());

		questionSelectScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		questionSelectScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);

		answersScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		answersScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);

		panel.getChildren().add(this.questionsTablePane = new Pane());
		questionsTablePane.getChildren().add(this.questionsTable = new GridPane());

		skippedQuestions = new ArrayList<Integer>();
		answers = new Answer<?>[test.getQuestions().length];

		this.test = test;
		this.theme = theme;

		this.indicateQualityOfLastAnswer = new SecureContainer<Boolean>(settings.isIndicateQualityOfLastAnswer());
		this.indicateQualityOfAllAnswers = new SecureContainer<Boolean>(settings.isIndicateQualityOfAllAnswers());
		this.showRightAnswer = new SecureContainer<Boolean>(settings.isShowRightAnswer());
		this.canGoToAllQuestions = new SecureContainer<Boolean>(settings.isCanGoToAllQuestions());
		this.skipButtonOption = new SecureContainer<Boolean>(settings.isSkipButtonOption());
		this.pauseOption = new SecureContainer<Boolean>(test.getTimeLimit() == -1 ? false : settings.isPauseOption());
		this.pauseOnUnfocus = new SecureContainer<Boolean>(test.getTimeLimit() == -1 ? false : settings.isPauseOnUnfocus());

		this.fixedQSelectBtnHeight = testingPartSettings.isFixedHeightOfQuestionSelectButton();
		this.fillAllHeight = testingPartSettings.isFillAllHeightOfAnswersPanel();
		this.maximazeHeight = testingPartSettings.isMaximazeAnswerButtonHeight();

		paused = new SecureContainer<Boolean>(false);
		fullTime = new SecureContainer<Float>(0f);
		timeLimit = new SecureContainer<Integer>(test.getTimeLimit());
		timeOfTest = new SecureContainer<Float>(0f);
		rightAnswers = new SecureContainer<Integer>(0);
		perfectAnswers = new SecureContainer<Integer>(0);
		timeToPause = new SecureContainer<Integer>(0);
		lastTime = new SecureContainer<Long>(Calendar.getInstance().getTimeInMillis());
		finished = new SecureContainer<Boolean>(false);

		this.canSendResult = canSendResult;
		this.teacher = teacher;

		ButtonFX.anticntrlc = settings.isAnticopy();
		ButtonFX.antiscreenshot = settings.isAntiscreenshot();
		if (settings.isAntiscreenshot())
		{
			class ImageSelection implements Transferable
			{
				private java.awt.Image image;

				private ImageSelection(java.awt.Image image)
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
					FXDialogsGenerator.showFXDialog(stage, (Stage) null, msgSys.getMsg("printscreenWasClicked"), 0, null, true);
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
		createGUI(parentPos, test.getName(), testFileName, studentInfo);
		for (int jj = 0; jj < answerButtons.length; jj++)
		{
			int j = jj;
			ButtonFX btn = answerButtons[j];
			btn.addActionListener((e) ->
			{
				btn.setClicked((test.getQuestions()[currentQuestionNumber] instanceof PickOneQuestion || test
						.getQuestions()[currentQuestionNumber] instanceof SelectSomeQuestion) ? selectedAnswers[j] = !selectedAnswers[j] : false);
				updateButtonMenu(currentQuestionNumber, j);
				if (test.getQuestions()[currentQuestionNumber] instanceof PickOneQuestion)
					for (int k = 0; k < answerButtons.length; k++)
						answerButtons[k].setClicked(j == k);
			});
		}
		createQuestionSelectPanel();
		updateQuestionSelectPanel();
		openQuestion(-1, 0);

		lastTime.setValue(Calendar.getInstance().getTimeInMillis());
		new Timer(1, e ->
		{
			if (!finished.getValue())
			{
				long time = Calendar.getInstance().getTimeInMillis();
				if (test.getTableQuestionSelector() == null && timeLimit.getValue() != -1)
				{
					if (!paused.getValue())
						timeOfTest.setValue(timeOfTest.getValue() + ((float) (time - lastTime.getValue())) / 1000f);
					fullTime.setValue(fullTime.getValue() + ((float) (time - lastTime.getValue())) / 1000f);
					timeToPause.setValue(timeToPause.getValue() - 1);
					String newTimerText = toSize((timeLimit.getValue() - timeOfTest.getValue().intValue()) / 60, 2).substring(0, 2) + ":" + toSize((timeLimit
							.getValue() - timeOfTest.getValue().intValue()) % 60, 2).substring(0, 2);
					if (!timerText.equals(newTimerText))
						Platform.runLater(() -> ((ButtonFXTextContent) timer.getContent()).setText(timerText = newTimerText));
					if (timeOfTest.getValue() >= timeLimit.getValue())
					{
						System.out.println("m1");
						Platform.runLater(() ->
						{
							System.out.println("m2");
							doFinish(test.getName(), testFileName, studentInfo);
						});
					}

					if (test.getTimeEndWarning() >= timeLimit.getValue() - timeOfTest.getValue())
						Platform.runLater(() -> getPanel().setBackground((timeLimit.getValue() - timeOfTest.getValue().intValue()) % 2 == 0 ? warningBackground
								: standardBackground));
				}
				lastTime.setValue(time);
			}
		}).start();
		createActionHandlers();
		resize();
		updateLabelsInPart();
	}

	@Override
	protected void _resize(int w, int h)
	{
		int qsp = 100;

		testingPane.setPrefSize(w, h);
		questionSelectScrollPane.setPrefSize(qsp, h);
		questionSelectPanel.setPrefSize(questionSelectScrollPane.getPrefWidth() - 16, questionSelectScrollPane.getPrefHeight() - 2);
		updateQuestionSelectPane();

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
		questionInfoTextfield.setLayoutY(qualityIndicator.getLayoutY() - qualityIndicator.getPrefHeight() - Main.spaceBetweenComponents);

		optionButtonsPanel.setLayoutX(x1);
		optionButtonsPanel.setPrefSize(width1, 32);
		optionButtonsPanel.setLayoutY(questionInfoTextfield.getLayoutY() - qualityIndicator.getPrefHeight() - Main.spaceBetweenComponents);
		updateOptionButtonsPane();

		int count = test.getQuestions()[currentQuestionNumber].getAnswers().length;
		answersScrollPane.setLayoutX(x1);
		answersScrollPane.setLayoutY(questionSign.getLayoutY() + questionSign.getPrefHeight() + Main.spaceBetweenComponents);
		answersScrollPane.setPrefSize(width1, optionButtonsPanel.getLayoutY() - answersScrollPane.getLayoutY() - Main.spaceBetweenComponents);
		updateAnswersPane(count);

		questionsTablePane.setPrefSize(w, h);
		questionsTable.setPrefSize(w, h - 32);
	}

	private void updateAnswersPane(int count)
	{
		if (fillAllHeight)
		{
			if (maximazeHeight)
			{
				answersPanel.setPrefWidth(answersScrollPane.getPrefWidth() - 14);
				int fullH = (int) answersScrollPane.getPrefHeight() - 2;
				int hWithoutSpace = Math.max(((int) fullH + Main.spaceBetweenComponents) / count - Main.spaceBetweenComponents, minAnswerButtonHeight);
				int space = Main.spaceBetweenComponents;
				for (int i = 0; i < count; i++)
				{
					answerButtons[i].setLayoutX(0);
					answerButtons[i].setLayoutY((hWithoutSpace + space) * i);
					answerButtons[i].setPrefSize(answersPanel.getPrefWidth(), hWithoutSpace);
				}
				answersPanel.setPrefHeight((hWithoutSpace + space) * count - space);
				answersScrollPane.setPrefHeight(Math.min(answersPanel.getPrefHeight() + 2, answersScrollPane.getPrefHeight()));
			}
			else
			{
				answersPanel.setPrefWidth(answersScrollPane.getPrefWidth() - 14);
				int fullH = (int) answersScrollPane.getPrefHeight() - 2;
				int hWithoutSpace = minAnswerButtonHeight;
				int space = Math.max(((int) fullH - minAnswerButtonHeight * count) / (count - 1), Main.spaceBetweenComponents);
				for (int i = 0; i < count; i++)
				{
					answerButtons[i].setLayoutX(0);
					answerButtons[i].setLayoutY((hWithoutSpace + space) * i);
					answerButtons[i].setPrefSize(answersPanel.getPrefWidth(), hWithoutSpace);
				}
				answersPanel.setPrefHeight((hWithoutSpace + space) * count - space);
				answersScrollPane.setPrefHeight(Math.min(answersPanel.getPrefHeight() + 2, answersScrollPane.getPrefHeight()));
			}
		}
		else
		{
			answersPanel.setPrefWidth(answersScrollPane.getPrefWidth() - 14);
			int hWithoutSpace = minAnswerButtonHeight;
			int space = Main.spaceBetweenComponents;
			for (int i = 0; i < count; i++)
			{
				answerButtons[i].setLayoutX(0);
				answerButtons[i].setLayoutY((hWithoutSpace + space) * i);
				answerButtons[i].setPrefSize(answersPanel.getPrefWidth(), hWithoutSpace);
			}
			answersPanel.setPrefHeight((hWithoutSpace + space) * count - space);
			answersScrollPane.setPrefHeight(Math.min(answersPanel.getPrefHeight() + 2, answersScrollPane.getPrefHeight()));
		}
	}

	private void updateOptionButtonsPane()
	{
		int w = (int) ((optionButtonsPanel.getPrefWidth() + Main.spaceBetweenComponents) / optionButtonsPanel.getChildren().size());
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

	private void updateQuestionSelectPane()
	{
		int h = (int) (questionSelectPanel.getPrefHeight() / questionSelectAnswerButtons.length);
		for (ButtonFX btn : questionSelectAnswerButtons)
			btn.setPrefSize(questionSelectPanel.getPrefWidth(), h);
	}

	private static final Background warningBackground = new Background(new BackgroundFill(new Color(1, 0.4, 0.4, 1), new CornerRadii(0), new Insets(0))),
			standardBackground = new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), new Insets(0)));
	private static final int minAnswerButtonHeight = 48;

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
	private void createGUI(Rectangle parentPosition, String testName, String testFileName, StudentInfo studentInfo)
	{
		int maxCountOfAnswers = 0;
		for (Question<?> q : test.getQuestions())
			if (!(q instanceof EnterTextQuestion))
				maxCountOfAnswers = Math.max(maxCountOfAnswers, q.getAnswers().length);
		this.answerButtons = new ButtonFX[maxCountOfAnswers];
		if (test.getTableQuestionSelector() == null)
			if (pauseOnUnfocus.getValue())
				stage.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean focused) -> pause(!focused
						&& !showLogs));

		questionSign.setFramesize(6);
		// signPanel.getChildren().add(questionSign);
		// questionSign.setBounds(0, 0, signPanel.getPrefWidth(), signPanel.getPrefHeight());
		questionSign.setEnabled(false);
		if (test.getTableQuestionSelector() != null)
		{
			Font font = new Label().getFont();
			int[] w = new int[test.getTableQuestionSelector().getCols().length + 1];
			int[] h = new int[test.getTableQuestionSelector().getRows().length + 1];
			int aw = 0;
			questionsTable.getColumnConstraints().clear();
			{
				w[0] = MathWithText.size(test.getName(), font).width + 20;
				for (String s : test.getTableQuestionSelector().getRows())
					w[0] = Math.max(w[0], MathWithText.size(s, font).width + 20);
				for (int x = 0; x < test.getTableQuestionSelector().getCols().length; x++)
				{
					w[x + 1] = MathWithText.size(test.getTableQuestionSelector().getCols()[x], font).width + 20;
					for (int y = 0; y < test.getTableQuestionSelector().getRows().length; y++)
						w[x + 1] = Math.max(w[x + 1], MathWithText.size(test.getTableQuestionSelector().getQuestionsTable()[x][y].getText(), font).width + 20);
				}
			}
			{
				h[0] = MathWithText.size(test.getName(), font).height + 20;
				for (String s : test.getTableQuestionSelector().getCols())
					h[0] = Math.max(h[0], MathWithText.size(s, font).height + 20);
				for (int y = 0; y < test.getTableQuestionSelector().getRows().length; y++)
				{
					h[y + 1] = MathWithText.size(test.getTableQuestionSelector().getRows()[y], font).height + 20;
					for (int x = 0; x < test.getTableQuestionSelector().getCols().length; x++)
						h[y + 1] = Math.max(h[y + 1], MathWithText.size(test.getTableQuestionSelector().getQuestionsTable()[x][y].getText(), font).height + 20);
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
				questionsTable.getColumnConstraints().add(new ColumnConstraints(w[x], w[x], w[x], Priority.NEVER, HPos.CENTER, true));
			questionsTable.getRowConstraints().clear();
			for (int y = 0; y < test.getTableQuestionSelector().getRows().length + 1; y++)
				questionsTable.getRowConstraints().add(new RowConstraints(h[y], h[y], h[y], Priority.NEVER, VPos.CENTER, true));

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
					ButtonFX btn = new ButtonFX(new ButtonFXTextContent(test.getTableQuestionSelector().getQuestionsTable()[x][y].getText()), new CornerRadii(
							0), new CornerRadii(0));
					btn.setFramesize(2);
					int xx = x, yy = y;
					btn.addActionListener(e ->
					{
						questionsTablePane.setVisible(false);
						testingPane.setVisible(true);
						openQuestion(currentQuestionNumber, currentQuestionNumber = test.getQuestionNumberAtQuestionsByQuestionIndex(test
								.getTableQuestionSelector().getQuestionsTable()[xx][yy].getQuestionNumber()));
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

			if (timeLimit.getValue() != -1)
			{
				timer = new ButtonFX(new ButtonFXTextContent(""), new CornerRadii(8, 8, 8, 8, false), new CornerRadii(6, 6, 6, 6, false));
				timer.addActionListener(e -> pause(!paused.getValue()));
				count++;
			}

			if (test.getTableQuestionSelector() == null)
			{
				skip = new ButtonFX(new ButtonFXTextContent(""), new CornerRadii(8, 8, 8, 8, false), new CornerRadii(6, 6, 6, 6, false));
				skip.addActionListener(e -> skip());
				skip.setEnabled(skipButtonOption.getValue());
				count++;

				next = new ButtonFX(new ButtonFXTextContent(""), new CornerRadii(8, 8, 8, 8, false), new CornerRadii(6, 6, 6, 6, false));
				next.addActionListener(e -> next());
				count++;
			}

			if (test.getTableQuestionSelector() != null)
			{
				back = new ButtonFX(new ButtonFXTextContent(""), new CornerRadii(8, 8, 8, 8, false), new CornerRadii(6, 6, 6, 6, false));
				back.addActionListener(e -> back());
				count++;
			}

			finish = new ButtonFX(new ButtonFXTextContent(""), new CornerRadii(8, 8, 8, 8, false), new CornerRadii(6, 6, 6, 6, false));
			finish.addActionListener(e -> doFinish(testName, testFileName, studentInfo));
			count++;
			// Set bounds
			int optionsSpace = 5, optionButtonAndSpaceWidth = ((int) optionButtonsPanel.getPrefWidth() + optionsSpace) / count,
					optionButtonWidth = optionButtonAndSpaceWidth - optionsSpace;
			if (timer != null)
			{
				optionButtonsPanel.getChildren().add(timer);
				timer.setBounds(optionButtonAndSpaceWidth * pos++, 0, optionButtonWidth, optionButtonsPanel.getPrefHeight());
			}
			if (skip != null)
			{
				optionButtonsPanel.getChildren().add(skip);
				skip.setBounds(optionButtonAndSpaceWidth * pos++, 0, optionButtonWidth, optionButtonsPanel.getPrefHeight());
			}
			if (next != null)
			{
				optionButtonsPanel.getChildren().add(next);
				next.setBounds(optionButtonAndSpaceWidth * pos++, 0, optionButtonWidth, optionButtonsPanel.getPrefHeight());
			}
			if (back != null)
			{
				optionButtonsPanel.getChildren().add(back);
				back.setBounds(optionButtonAndSpaceWidth * pos++, 0, optionButtonWidth, optionButtonsPanel.getPrefHeight());
			}
			if (test.getTableQuestionSelector() == null)
			{
				optionButtonsPanel.getChildren().add(finish);
				finish.setBounds(optionButtonAndSpaceWidth * pos++, 0, optionButtonWidth, optionButtonsPanel.getPrefHeight());
			}
			else
			{
				questionsTablePane.getChildren().add(finish);
				finish.setBounds(questionsTablePane.getPrefWidth() - 100, questionsTable.getPrefHeight(), 100, questionsTablePane.getPrefHeight()
						- questionsTable.getPrefHeight());
			}
		}

		qualityIndicator.setVisible(indicateQualityOfLastAnswer.getValue());
		if (indicateQualityOfAllAnswers.getValue())
		{
			qualityIndicators = new Pane[test.getQuestions().length];
			for (int i = 0; i < qualityIndicators.length; i++)
			{
				qualityIndicator.getChildren().add(qualityIndicators[i] = new Pane());
				qualityIndicators[i].setLayoutX(qualityIndicator.getPrefWidth() / test.getQuestions().length * i);
				qualityIndicators[i].setPrefSize(qualityIndicator.getPrefWidth() / test.getQuestions().length - 5, qualityIndicator.getPrefHeight());
			}
		}
		// answerButtonSpace = 5;
		// answerButtonHeightWithSpace = Math.max(((int) answersPanel.getPrefHeight() + answerButtonSpace) / answerButtons.length, minAnswerButtonHeight
		// + answerButtonSpace);
		// defaultAswerButtonHeight = Math.max(answerButtonHeight = answerButtonHeightWithSpace - answerButtonSpace, minAnswerButtonHeight);

		enterButton = new ButtonFX(new ButtonFXTextContent(""), new CornerRadii(8, 8, 8, 8, false), new CornerRadii(6, 6, 6, 6, false));
		((ButtonFXTextContent) enterButton.getContent()).setEditable(true);
		answersPanel.getChildren().add(enterButton);
		enterButton.setBounds(0, 0, answersPanel.getPrefWidth(), minAnswerButtonHeight);
		for (int i = 0; i < answerButtons.length; i++)
		{
			answerButtons[i] = new ButtonFX(new ButtonFXHtmlContent(""), new CornerRadii(8, 8, 8, 8, false), new CornerRadii(6, 6, 6, 6, false));
			answersPanel.getChildren().add(answerButtons[i]);
			// answerButtons[i].setBounds(0, answerButtonHeightWithSpace * i, answersPanel.getPrefWidth(), answerButtonHeight);
		}
		testingPane.setVisible(test.getTableQuestionSelector() == null);
		questionsTablePane.setVisible(test.getTableQuestionSelector() != null);
		window.setVisible(true);
	}

	private void doFinish(String testName, String testFileName, StudentInfo studentInfo)
	{
		System.out.println("m3");
		if (skippedQuestions.size() == 1)
			if (skippedQuestions.get(0) == currentQuestionNumber)
				goNext();
		if (timeLimit.getValue() != -1 && timeOfTest.getValue() >= timeLimit.getValue() || skippedQuestions.size() == 0)
			finish(testName, testFileName, studentInfo);
		else FXDialogsGenerator.showFXDialog(stage, (Stage) null, msgSys.getMsg("youHaveSkipped"), 0, null, true);
	}

	private void back()
	{
		saveAnswer(currentQuestionNumber);
		handleQuestion();
		testingPane.setVisible(false);
		questionsTablePane.setVisible(true);
	}

	/**
	 * 
	 */
	private void createQuestionSelectPanel()
	{
		questionSelectAnswerButtons = new ButtonFX[test.getQuestions().length];
		int space = 4, height = fixedQSelectBtnHeight ? 32
				: (int) Math.max(32, (questionSelectPanel.getPrefHeight() + space) / test.getQuestions().length - space);
		questionSelectPanel.setPrefHeight((height + space) * test.getQuestions().length - space);
		for (int i = 0; i < test.getQuestions().length; i++)
		{
			questionSelectAnswerButtons[i] = new ButtonFX(new ButtonFXTextContent(i + 1 + "/" + test.getQuestions().length), new CornerRadii(8, 8, 8, 8, false),
					new CornerRadii(6, 6, 6, 6, false));
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
			questionSelectAnswerButtons[i].setBounds(0, i * (height + space), questionSelectScrollPane.getPrefWidth() - 16, height);
			questionSelectPanel.getChildren().add(questionSelectAnswerButtons[i]);
		}
	}

	/**
	 * 
	 */
	private void pause(boolean paused)
	{
		if (pauseOption.getValue() && timeToPause.getValue() <= 0)
		{
			this.paused.setValue(paused);
			questionSign.setVisible(!paused);
			next.setVisible(!paused);
			finish.setVisible(!paused);
			qualityIndicator.setVisible(!paused);
			skip.setVisible(!paused);
			questionSelectScrollPane.setVisible(!paused);
			answersScrollPane.setVisible(!paused);
			timeToPause.setValue(300);
			timer.setClicked(paused);
		}
	}

	/**
	 * 
	 */
	private void skip()
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
	private void next()
	{
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
	private void finish(String testName, String testFileName, StudentInfo studentInfo)
	{
		saveAnswer(currentQuestionNumber);
		handleQuestion();
		paused.setValue(true);
		finished.setValue(true);
		int maxResult = 0;
		for (Question<?> question : test.getQuestions())
			maxResult += question.getMaxAward();
		showResult(testName, testFileName, studentInfo, countResult(), maxResult);
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
		rightAnswers.setValue(0);
		perfectAnswers.setValue(0);
		int result = 0;
		for (int i = 0; i < test.getQuestions().length; i++)
		{
			int[] results = _handleQuestion(i);
			result += results[1];
			rightAnswers.setValue(rightAnswers.getValue() + (results[1] > results[0] || results[1] == results[2] ? 1 : 0));
			perfectAnswers.setValue(perfectAnswers.getValue() + (results[1] == results[2] ? 1 : 0));
		}
		return result;
	}

	/**
	 * 
	 */
	private void handleQuestion()
	{
		saveAnswer(currentQuestionNumber);
		int[] res = _handleQuestion(currentQuestionNumber);
		int minResult = res[0], questionResult = res[1], maxResult = res[2];

		if (questionResult > minResult || questionResult == maxResult)
			rightAnswers.setValue(rightAnswers.getValue() + 1);
		if (questionResult == maxResult)
			perfectAnswers.setValue(perfectAnswers.getValue() + 1);

		if (indicateQualityOfLastAnswer.getValue())
		{
			Pane panel = indicateQualityOfAllAnswers.getValue() ? (Pane) qualityIndicators[currentQuestionNumber] : qualityIndicator;
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
				FXDialogsGenerator.showFXDialog(stage, (Stage) null, msgSys.getMsg("questionResultMoreThanMaxResult"), 0, null, true);
		}
		if (showRightAnswer.getValue())
		{
			String rightAnswer = "";
			Question<?> question = test.getQuestions()[currentQuestionNumber];
			if (question instanceof EnterTextQuestion)
			{
				for (EnterTextAnswerVariant a : ((EnterTextQuestion) question).getAnswers())
					if (test.getQuestions()[currentQuestionNumber].getAward() + a.getAward() == test.getQuestions()[currentQuestionNumber].getMaxAward())
						rightAnswer += a.getText() + "\n";
			}
			int i = 0;
			if (question instanceof PickOneQuestion)
				for (PickOneAnswerVariant a : ((PickOneQuestion) question).getAnswers())
				{
					if (test.getQuestions()[currentQuestionNumber].getAward() + a.getAward() == test.getQuestions()[currentQuestionNumber].getMaxAward())
					{
						rightAnswer += a.getHtml() + "\n";
						answerButtons[i].setDisabledBackground(theme.getQuestionSelectSkippedBackground());
						answerButtons[i].setEnabled(false);
					}
					i++;
				}
			if (question instanceof SelectSomeQuestion)
				for (SelectSomeAnswerVariant a : ((SelectSomeQuestion) question).getAnswers())
				{
					if (test.getQuestions()[currentQuestionNumber].getAward() + a.getAward() > 0)
					{
						rightAnswer += a.getHtml() + "\n";
						answerButtons[i].setDisabledBackground(theme.getQuestionSelectSkippedBackground());
						answerButtons[i].setEnabled(false);
					}
					i++;
				}
			FXDialogsGenerator.showFXDialog(stage, (Stage) null, rightAnswer, JOptionPane.INFORMATION_MESSAGE, null, true);
		}
		goNext();
	}

	/**
	 * 
	 */
	private void goNext()
	{
		if (!canGoToAllQuestions.getValue())
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
	 * @param window
	 * @param _class
	 * @param surname
	 * @param name
	 * @param secondName
	 */
	private void showResult(String testName, String testFileName, StudentInfo studentInfo, int result, int maxResult)
	{
		String text = "";
		text += "syntaxLanguage" + ": \"" + msgSys.getLanguage() + "\"\n";
		text += msgSys.getMsg("testName") + ": \"" + testName + "\"\n";
		text += msgSys.getMsg("classNumber") + ": \"" + studentInfo.getClassNumber() + "\"\n";
		text += msgSys.getMsg("classLetter") + ": \"" + studentInfo.getClassLetter() + "\"\n";
		text += msgSys.getMsg("surname") + ": \"" + studentInfo.getSurname() + "\"\n";
		text += msgSys.getMsg("name") + ": \"" + studentInfo.getName() + "\"\n";
		text += msgSys.getMsg("secondName") + ": \"" + studentInfo.getSecondName() + "\"\n";
		text += msgSys.getMsg("result") + ": " + result + "\n";
		text += msgSys.getMsg("maxResult") + ": " + maxResult + "\n";
		text += msgSys.getMsg("perfectAnswersAmount") + ": " + perfectAnswers.getValue() + "\n";
		text += msgSys.getMsg("rightAnswersAmount") + ": " + (rightAnswers.getValue() - perfectAnswers.getValue()) + "\n";
		text += msgSys.getMsg("questionsAmount") + ": " + test.getQuestions().length + "\n";
		text += msgSys.getMsg("time") + ": " + toSize(timeOfTest.getValue().intValue() / 60, 2) + ":" + toSize(timeOfTest.getValue().intValue() % 60, 2) + "\n";
		text += msgSys.getMsg("timeLimit") + ": " + (timeLimit.getValue() == -1 ? "--:--"
				: toSize((int) timeLimit.getValue() / 60, 2) + ":" + toSize((int) timeLimit.getValue() % 60, 2)) + "\n";
		text += msgSys.getMsg("fullTime") + ": " + toSize(fullTime.getValue().intValue() / 60, 2) + ":" + toSize(fullTime.getValue().intValue() % 60, 2);
		String shortText = text;
		// int i = 0;
		// for (Answer ans : answers)
		// if (ans != null)
		// {
		// String userAnswer = "";
		// String rightAnswer = "";
		// switch (test.getQuestions()[i].getType())
		// {
		// case Arrangement:
		// break;
		// case Distribution:
		// break;
		// case EnterText:
		// userAnswer = ans.getAnswerText();
		// for (AnswerVariant a : test.getQuestions()[i].getAnswers())
		// if (a.getAward() == test.getQuestions()[i].getMaxAward())
		// rightAnswer += a.getHtml() + " - " + a.getAward() + " | ";
		// break;
		// case Matching:
		// break;
		// case PickOne:
		// if (ans.getSelectedAnswerNumber() != -1)
		// userAnswer = test.getQuestions()[i].getAnswers()[ans.getSelectedAnswerNumber()].getHtml() + " - " + test.getQuestions()[i]
		// .getAnswers()[ans.getSelectedAnswerNumber()].getAward();
		// for (AnswerVariant a : test.getQuestions()[i].getAnswers())
		// if (a.getAward() == test.getQuestions()[i].getMaxAward())
		// rightAnswer += a.getHtml() + " - " + a.getAward() + " | ";
		// break;
		// case SelectSome:
		// int n = 0;
		// for (boolean b : ans.getSelectedAnswers())
		// {
		// if (b)
		// userAnswer += test.getQuestions()[i].getAnswers()[n].getHtml() + " - " + test.getQuestions()[i].getAnswers()[n].getAward()
		// + " & ";
		// n++;
		// }
		// for (AnswerVariant a : test.getQuestions()[i].getAnswers())
		// if (a.getAward() > 0)
		// rightAnswer += a.getHtml() + " - " + a.getAward() + " & ";
		// break;
		// }
		// userAnswer += " - " + _handleQuestion(i)[1];
		// rightAnswer += " - " + test.getQuestions()[i].getMaxAward();
		// text += "\nQuestion" + i + ": \"" + test.getQuestions()[i].getHtml() + "\"\n\tuser: \"" + userAnswer + "\"\n\tright: \"" + rightAnswer + "\"";
		// i++;
		// }

		Calendar c = Calendar.getInstance();
		SystemUtils.writeFile(new File("Results/" + testFileName + "/Result From " + c.get(Calendar.YEAR) + "_" + toSize(c.get(Calendar.DAY_OF_YEAR), 2) + "_"
				+ toSize(c.get(Calendar.HOUR), 2) + "-" + toSize(c.get(Calendar.MINUTE), 2) + "-" + toSize(c.get(Calendar.SECOND), 2) + ".log"), text,
				"cp1251");
		showLogs = true;
		String textt = text;
		Button btn = new Button("Send results to teacher");
		TextField login = new TextField();
		int m = MathWithText.size(shortText, login.getFont()).width / 2 + 30;
		btn.setPrefSize(m, 20);
		login.setPrefSize(m, 20);
		login.setLayoutX(m);
		Button showFull = new Button("Show full");
		showFull.setPrefSize(m, 20);
		showFull.setLayoutX(m / 2);
		showFull.setLayoutY(25);
		Pane pane = canSendResult ? new Pane(btn, login, showFull) : new Pane(showFull);
		if (teacher != null)
			sendResultToTeacher(textt, teacher, studentInfo);
		btn.setOnAction(e -> sendResultToTeacher(textt, login.getText(), studentInfo));
		pane.setPrefSize(m * 2, 50);
		String fullText = text;
		showFull.setOnAction(e -> FXDialogsGenerator.showFXDialog(stage, (Stage) null, fullText, JOptionPane.INFORMATION_MESSAGE, null, 
				true));
		FXDialogsGenerator.showFXDialog(stage, (Stage) null, shortText, JOptionPane.INFORMATION_MESSAGE, pane, true);
	}

	private void sendResultToTeacher(String result, String teachersLogin, StudentInfo studentInfo)
	{
		Main.sendToServer(new ResultSendPacket("sendResultToTeacher", result, AccountsPart.account.get().getLogin(), teachersLogin, new Date(), studentInfo
				.getClassNumber(), studentInfo.getClassLetter(), AccountsPart.account.get().getSurname(), AccountsPart.account.get().getName(),
				AccountsPart.account.get().getSecondName(), studentInfo.getSurname(), studentInfo.getName(), studentInfo.getSecondName()));
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
	public void updateLabelsInPart()
	{
		super.updateLabelsInPart();
		if (next != null)
			((ButtonFXTextContent) next.getContent()).setText(msgSys.getMsg("next"));
		if (skip != null)
			((ButtonFXTextContent) skip.getContent()).setText(msgSys.getMsg("skip"));
		if (back != null)
			((ButtonFXTextContent) back.getContent()).setText(msgSys.getMsg("back"));
		if (finish != null)
			((ButtonFXTextContent) finish.getContent()).setText(msgSys.getMsg("finish"));
	}

	/**
	 * 
	 */
	private void updateQuestionSelectPanel()
	{
		for (int i = 0; i < test.getQuestions().length; i++)
		{
			questionSelectAnswerButtons[i].setNormalBackground(skippedQuestions.contains(i) ? theme.getQuestionSelectSkippedBackground()
					: theme.getQuestionSelectNormalBackground());
			questionSelectAnswerButtons[i].setNormalFrame(skippedQuestions.contains(i) ? theme.getQuestionSelectSkippedFrame()
					: theme.getQuestionSelectNormalFrame());
			questionSelectAnswerButtons[i].setNormalForeground(skippedQuestions.contains(i) ? theme.getQuestionSelectSkippedForeground()
					: theme.getQuestionSelectNormalForeground());
			questionSelectAnswerButtons[i].setEnabled((skippedQuestions.contains(i) || canGoToAllQuestions.getValue()) && i != currentQuestionNumber);
			questionSelectAnswerButtons[i].setDisabledBackground(i == currentQuestionNumber ? theme.getSpecialButtonsBackground()
					: theme.getQuestionSelectNormalBackground());
			questionSelectAnswerButtons[i].setDisabledFrame(i == currentQuestionNumber ? theme.getSpecialButtonsFrame() : theme.getQuestionSelectNormalFrame());
			questionSelectAnswerButtons[i].setDisabledForeground(i == currentQuestionNumber ? theme.getSpecialButtonsForeground()
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
			answers[questionNumber] = new EnterTextAnswer(((ButtonFXTextContent) enterButton.getContent()).getMainFieldText());
		else if (test.getQuestions()[questionNumber] instanceof PickOneQuestion)
			answers[questionNumber] = new PickOneAnswer(selectedAnswerNumber);
		else if (test.getQuestions()[questionNumber] instanceof SelectSomeQuestion)
			answers[questionNumber] = new SelectSomeAnswer(selectedAnswers);
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
		questionInfoTextfield.setText(msgSys.getMsg(test.getQuestions()[questionNumber].getClass().getSimpleName() + "Info"));
		if (answers[questionNumber] instanceof ArrangementAnswer)
			buttonsArrangement = ((ArrangementAnswer) answers[questionNumber]).getAnswerArrangementForArrangement();
		else buttonsArrangement = test.getQuestions()[questionNumber] instanceof ArrangementQuestion ? intLessZeroArr(answerButtons.length)// array(answerButtons.length)
				: intLessZeroArr(answerButtons.length);
		if (answers[questionNumber] instanceof MatchingAnswer)
			buttonsArrangement = ((MatchingAnswer) answers[questionNumber]).getAnswerArrangementForMatching();
		else buttonsArrangement = test.getQuestions()[questionNumber] instanceof ArrangementQuestion ? intLessZeroArr(answerButtons.length)// array(answerButtons.length)
				: intLessZeroArr(answerButtons.length);
		if (answers[questionNumber] instanceof DistributionAnswer)
			buttonsArrangementForDistribution = ((DistributionAnswer) answers[questionNumber]).getAnswerArrangementForDistribution();
		else buttonsArrangementForDistribution = intLessZeroListsArr(answerButtons.length);
		if (test.getQuestions()[questionNumber] instanceof EnterTextQuestion)
		{
			for (ButtonFX button : answerButtons)
				button.setVisible(false);
			enterButton.setVisible(true);
			((ButtonFXTextContent) enterButton.getContent()).setText(((EnterTextAnswer) answers[questionNumber]).getAnswerText());
			enterButton.setClicked(false);
			enterButton.setEnabled(true);
		}
		else
		{
			enterButton.setVisible(false);
			for (int i = 0; i < answerButtons.length; i++)
				answerButtons[i].setVisible(test.getQuestions()[questionNumber].getAnswers().length > i);
			for (int i = 0; i < test.getQuestions()[questionNumber].getAnswers().length; i++)
			{
				((ButtonFXHtmlContent) answerButtons[i].getContent()).setHtml(Test.replaceContentEditable(((HtmlAnswerVariant) test
						.getQuestions()[questionNumber].getAnswers()[i]).getHtml()));
				answerButtons[i].setClicked(false);
				answerButtons[i].setEnabled(true);
			}
		}
		updateAnswersPane(test.getQuestions()[questionNumber].getAnswers().length);
		for (int i = 0; i < test.getQuestions()[questionNumber].getAnswers().length; updateButtonMenu(questionNumber, i), i++);

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
			lastNotSkippedQuestion = questionNumber;
		selectedAnswers = new boolean[test.getQuestions()[questionNumber].getAnswers().length];
		if (!(questionSign.getContent() instanceof ButtonFXHtmlContent))
			questionSign.setContent(new ButtonFXHtmlContent(""));
		((ButtonFXHtmlContent) questionSign.getContent()).setHtml(Test.replaceContentEditable(test.getQuestions()[questionNumber].getHtml()));
		System.out.println(Test.replaceContentEditable(test.getQuestions()[questionNumber].getHtml()));
		MenuItem[] items = new MenuItem[test.getQuestions()[questionNumber].getImages().length + test.getQuestions()[questionNumber].getVideos().length + test
				.getQuestions()[questionNumber].getAudios().length];
		int j = 0;
		for (int ii = 0; ii < test.getQuestions()[questionNumber].getImages().length; ii++)
		{
			int i = ii;
			items[j + i] = new MenuItem(msgSys.getMsg("image").split("")[0].toUpperCase() + msgSys.getMsg("image").substring(1) + " " + (i + 1));
			items[j + i].setOnAction(e -> FXDialogsGenerator.showFXDialog(stage, (Rectangle) null, test.getQuestions()[questionNumber].getImages()[i], 0, null,
					true));
		}
		j += test.getQuestions()[questionNumber].getImages().length;
		for (int ii = 0; ii < test.getQuestions()[questionNumber].getVideos().length; ii++)
		{
			int i = ii;
			items[j + i] = new MenuItem(msgSys.getMsg("video").split("")[0].toUpperCase() + msgSys.getMsg("video").substring(1) + " " + (i + 1));
			items[j + i].setOnAction(e -> FXDialogsGenerator.showFXDialog(stage, (Rectangle) null, test.getQuestions()[questionNumber].getVideos()[i], 0, null,
					true));
		}
		j += test.getQuestions()[questionNumber].getVideos().length;
		for (int ii = 0; ii < test.getQuestions()[questionNumber].getAudios().length; ii++)
		{
			int i = ii;
			items[j + i] = new MenuItem(msgSys.getMsg("audio").split("")[0].toUpperCase() + msgSys.getMsg("audio").substring(1) + " " + (i + 1));
			items[j + i].setOnAction(e -> FXDialogsGenerator.showFXDialog(stage, (Rectangle) null, test.getQuestions()[questionNumber].getAudios()[i], 0, null,
					true));
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
			next.setEnabled(questionNumber < test.getQuestions().length - 1 && (canGoToAllQuestions.getValue() || skippedQuestions.size() > 1 || !wasGoToEnd));
			skip.setEnabled(questionNumber < test.getQuestions().length - 1);
		}
		finish.setEnabled(wasGoToEnd || test.getTableQuestionSelector() != null);

		updateQuestionSelectPanel();
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
					if (item.isSelected() && !buttonsArrangementForDistribution[i].contains(question.getIndexesForNamesIndexes()[rr]))
						buttonsArrangementForDistribution[i].add(question.getIndexesForNamesIndexes()[rr]);
					else if (!item.isSelected() && buttonsArrangementForDistribution[i].contains(question.getIndexesForNamesIndexes()[rr]))
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
				String userAnswer = ((EnterTextAnswer) answers[questionNumber]).getAnswerText(), curAnswer = ((EnterTextAnswerVariant) question.getAnswers()[i])
						.getText();
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
		if (_question instanceof PickOneQuestion)
		{
			PickOneQuestion question = (PickOneQuestion) _question;
			if (((PickOneAnswer) answers[questionNumber]).getSelectedAnswerNumber() != -1)
				questionResult += question.getAnswers()[((PickOneAnswer) answers[questionNumber]).getSelectedAnswerNumber()].getAward();
		}
		if (_question instanceof SelectSomeQuestion)
		{
			SelectSomeQuestion question = (SelectSomeQuestion) _question;
			for (int i = 0; i < question.getAnswers().length; i++)
				questionResult += ((SelectSomeAnswer) answers[questionNumber]).getSelectedAnswers()[i] ? question.getAnswers()[i].getAward() : 0;
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
				{
					System.out.println(answers);
					System.out.println(answers[questionNumber]);
					System.out.println(((ArrangementAnswer) answers[questionNumber]).getAnswerArrangementForArrangement());
					System.out.println(answersIndexes[integer]);
					System.out.println(((ArrangementAnswer) answers[questionNumber]).getAnswerArrangementForArrangement()[answersIndexes[integer]]);
					System.out.println(hashMap);
					if (((ArrangementAnswer) answers[questionNumber]).getAnswerArrangementForArrangement()[answersIndexes[integer]] != hashMap.get(integer))
						b = false;
				}
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
					if (((MatchingAnswer) answers[questionNumber]).getAnswerArrangementForMatching()[answersIndexes[integer]] != hashMap.get(integer))
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
			for (HashMap<Entry<Integer, Boolean>, ArrayList<Integer>> hashMap : question.getAnswerArrangementForDistribution().keySet())
			{
				boolean b = true;
				ArrayList<Integer> list;
				for (Entry<Integer, Boolean> integerBoolean : hashMap.keySet())
					if ((list = hashMap.get(integerBoolean)) != null)
					{
						for (Integer i : list)
							if (!((DistributionAnswer) answers[questionNumber]).getAnswerArrangementForDistribution()[answersIndexes[integerBoolean.getFirst()]]
									.contains(i))
								b = false;
						if (integerBoolean.getSecond())
							if (((DistributionAnswer) answers[questionNumber]).getAnswerArrangementForDistribution()[answersIndexes[integerBoolean.getFirst()]]
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
		return new int[]
		{
				_question.getAward(),
				questionResult,
				_question.getMaxAward()
		};
	}

	private void check(int questionNumber)
	{
		if (test.getQuestions()[questionNumber] instanceof PickOneQuestion && !(answers[questionNumber] instanceof PickOneAnswer))
			answers[questionNumber] = new PickOneAnswer(-1);
		if (test.getQuestions()[questionNumber] instanceof SelectSomeQuestion && !(answers[questionNumber] instanceof SelectSomeAnswer))
			answers[questionNumber] = new SelectSomeAnswer(new boolean[6]);
		if (test.getQuestions()[questionNumber] instanceof EnterTextQuestion && !(answers[questionNumber] instanceof EnterTextAnswer))
			answers[questionNumber] = new EnterTextAnswer("");
		if (test.getQuestions()[questionNumber] instanceof ArrangementQuestion && !(answers[questionNumber] instanceof ArrangementAnswer))
			answers[questionNumber] = new ArrangementAnswer(intLessZeroArr(6));
		if (test.getQuestions()[questionNumber] instanceof MatchingQuestion && !(answers[questionNumber] instanceof MatchingAnswer))
			answers[questionNumber] = new MatchingAnswer(intLessZeroArr(6));
		if (test.getQuestions()[questionNumber] instanceof DistributionQuestion && !(answers[questionNumber] instanceof DistributionAnswer))
			answers[questionNumber] = new DistributionAnswer(intLessZeroListsArr(6));
		System.out.println(test.getQuestions()[questionNumber].getClass().getSimpleName() + " : " + answers[questionNumber]);
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
