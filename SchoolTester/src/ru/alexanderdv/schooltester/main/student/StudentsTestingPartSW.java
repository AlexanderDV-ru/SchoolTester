package ru.alexanderdv.schooltester.main.student;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.main.teacher.TeachersTestsControlPart.Test;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.Question;
import ru.alexanderdv.schooltester.utilities.Question.Answer;
import ru.alexanderdv.schooltester.utilities.Question.QuestionType;
import ru.alexanderdv.schooltester.utilities.UserAnswer;
import ru.alexanderdv.swingutilities.components.ButtonX;
import ru.alexanderdv.swingutilities.components.ScrollbarX;
import ru.alexandrdv.schooltester.util.Logger;

/**
 * TestingPart - the main class of testing part of program SchoolTester
 * 
 * @author AlexandrDV/AlexanderDV
 * @version 5.0.0a
 */
@Deprecated
public class StudentsTestingPartSW
{
	private static final MessageSystem msgSys = Main.msgSys;
	private JFrame window;
	private JPanel questionSelectPanel, answersPanel;
	private ButtonX[] questionSelectAnswerButtons;
	private Test test;
	private UserAnswer[] answers;
	private ButtonX questionSign;
	private ButtonX[] answerButtons;
	private int[] buttonsArrangement;
	private ScrollbarX scrollbar;
	private ArrayList<Integer> skippedQuestions;
	private int currentQuestionNumber;
	private int lastNotSkippedQuestion;
	private boolean[] selectedAnswers;
	private boolean indicateQualityOfLastAnswer, indicateQualityOfAllAnswers, showRightAnswer, canGoToAllQuestions, skipButtonOption, pauseOption,
			pauseOnUnfocus;
	private boolean paused;
	private ButtonX timer, skip, next, finish;
	private float timeToTest, timeOfTest, fullTime;
	private int rightAnswers, perfectAnswers;
	private JPanel qualityIndicator;
	private int timeToPause;
	private long lastTime;
	private int selectedBtn = -1;
	private boolean wasGoToEnd;

	/**
	 *
	 * @param test.getTheme2()
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
	public StudentsTestingPartSW(Rectangle parentPosition, final Test test, String classNumber, String classLetter, String surname, String name,
			String secondName, int timeToTest, boolean indicateQualityOfLastAnswer, boolean indicateQualityOfAllAnswers, boolean showRightAnswer,
			boolean canGoToAllQuestions, boolean skipButtonOption, boolean pauseOption, boolean pauseOnUnfocus, boolean anticopy, boolean antiscreenshot)
	{
		skippedQuestions = new ArrayList<Integer>();
		answerButtons = new ButtonX[6];
		answers = new UserAnswer[test.getQuestions().length];
		this.timeToTest = timeToTest;
		this.test = test;
		this.indicateQualityOfLastAnswer = indicateQualityOfLastAnswer;
		this.indicateQualityOfAllAnswers = indicateQualityOfAllAnswers;
		this.showRightAnswer = showRightAnswer;
		this.canGoToAllQuestions = canGoToAllQuestions;
		this.skipButtonOption = skipButtonOption;
		this.pauseOption = pauseOption;
		this.pauseOnUnfocus = pauseOnUnfocus;
		ButtonX.anticntrlc = anticopy;
		ButtonX.antiscreenshot = antiscreenshot;
		if (antiscreenshot)
			ButtonX.antish = new KeyListener()
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

				@Override
				public void keyTyped(KeyEvent e)
				{
					keyPressed(e);
				}

				@Override
				public void keyReleased(KeyEvent e)
				{
					keyPressed(e);
				}

				@Override
				public void keyPressed(KeyEvent e)
				{
					if (e.getKeyCode() == KeyEvent.VK_PRINTSCREEN)
					{
						Timer timer = new Timer(100, ae ->
						{
							Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
							clipboard.setContents(new ImageSelection(new ImageIcon().getImage()), null);
							Logger.showMsgDialog(window, "Key printscreen was clicked!", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION);
						});
						timer.setRepeats(false);
						timer.start();
					}
				}
			};
		createGUI(parentPosition, test.getName(), classNumber, classLetter, surname, name, secondName);
		ArrayList<ButtonX> buttons = new ArrayList<ButtonX>();
		for (ButtonX b : answerButtons)
		{
			buttons.add(b);
			b.addActionListener((e) ->
			{
				((ButtonX) e.getSource()).setClicked((test.getQuestions()[currentQuestionNumber].getType() != QuestionType.EnterText) ? selectedAnswers[buttons
						.indexOf((ButtonX) e.getSource())] = !selectedAnswers[buttons.indexOf((ButtonX) e.getSource())] : false);
				if (test.getQuestions()[currentQuestionNumber].getType() == QuestionType.PickOne)
					for (ButtonX button : buttons)
						button.setClicked(button == e.getSource());
			});
			b.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseReleased(MouseEvent e)
				{
					if (test.getQuestions()[currentQuestionNumber].getType() == QuestionType.Arrangement)
					{
						ButtonX b1 = buttons.get(buttonsArrangement[buttons.get(selectedBtn).getY() / (buttons.get(selectedBtn).getHeight() + 5)]), b2 = buttons
								.get(selectedBtn);
						b1.setLocation(0, buttonsArrangement[selectedBtn] * (b1.getHeight() + 5));
						b2.setLocation(0, b2.getLocation().y / (b2.getHeight() + 5) * (b2.getHeight() + 5));
						for (int i = 0; i < test.getQuestions()[currentQuestionNumber].getAnswers().length; i++)
							buttonsArrangement[i] = buttons.get(i).getY() / (buttons.get(i).getHeight() + 5);
						selectedBtn = -1;
					}
				}

				@Override
				public void mousePressed(MouseEvent e)
				{
					if (test.getQuestions()[currentQuestionNumber].getType() == QuestionType.Arrangement)
					{
						selectedBtn = buttons.indexOf((ButtonX) e.getSource());
					}
				}
			});
		}
		MouseMotionListener mouseMotionListener = new MouseMotionListener()
		{
			@Override
			public void mouseMoved(MouseEvent e)
			{
				if (selectedBtn != -1)
				{
					buttons.get(selectedBtn).setLocation(buttons.get(selectedBtn).getX(), e.getY() + ((e.getSource()) != answersPanel ? ((Component) e
							.getSource()).getY() : 0));
					ButtonX btnX = buttons.get(buttonsArrangement[buttons.get(selectedBtn).getY() / (buttons.get(selectedBtn).getHeight() + 5)]);
					Point l2 = new Point(0, Math.min(btnX.getLocation().y / (btnX.getHeight() + 5), test.getQuestions()[currentQuestionNumber]
							.getAnswers().length) * (btnX.getHeight() + 5));
					btnX.setLocation(l2);
				}
			}

			@Override
			public void mouseDragged(MouseEvent e)
			{
				mouseMoved(e);
			}
		};
		answersPanel.addMouseMotionListener(mouseMotionListener);
		for (ButtonX button : buttons)
			button.addMouseMotionListener(mouseMotionListener);
		createQuestionSelectPanel();
		updateQuestionSelectPanel();
		changeLanguage();
		openQuestion(-1, 0);

		lastTime = Calendar.getInstance().getTimeInMillis();
		new Timer(1, e ->
		{
			long time = Calendar.getInstance().getTimeInMillis();
			if (!paused)
				timeOfTest += ((float) (time - lastTime)) / 1000f;
			fullTime += ((float) (time - lastTime)) / 1000f;
			timeToPause--;
			timer.setText(toSize((timeToTest - (int) timeOfTest) / 60, 2) + ":" + toSize((timeToTest - (int) timeOfTest) % 60, 2));
			if (timeOfTest >= timeToTest)
				finish.click();
			lastTime = Calendar.getInstance().getTimeInMillis();
		}).start();
	}

	JPanel scene;

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
		window = new JFrame();

		window.setResizable(false);
		window.setLocation(parentPosition.x + parentPosition.width / 2 - window.getWidth() / 2, parentPosition.y + parentPosition.height / 2 - window
				.getHeight() / 2);
		// window.setTitle(StartPart.program);
		// window.setIconImages(Logger.defaultIcons);
		window.setDefaultCloseOperation(3);
		if (pauseOnUnfocus)
			window.addFocusListener(new FocusAdapter()
			{

				@Override
				public void focusLost(FocusEvent e)
				{
					timer.click();
				}
			});

		JPanel content = new JPanel();
		content.setSize(700 + 1500, 900 + 1500);
		ScrollPane sp = new ScrollPane();
		sp.add(content);
		sp.setSize((int) Math.min(content.getWidth(), Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 10 - (20)), (int) Math.min(content.getHeight(),
				Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 10 - (50)));
		window.getContentPane().add(sp);
		window.setSize(sp.getSize());
		content.setBackground(test.getTheme2().getWindowBackground());
		content.setLayout(null);

		questionSelectPanel = new JPanel();
		questionSelectPanel.setBackground(test.getTheme2().getWindowBackground());
		questionSelectPanel.setBounds(0, 0, 70, 872);
		content.add(questionSelectPanel);

		JPanel othercontent = new JPanel();
		othercontent.setBackground(test.getTheme2().getWindowBackground());
		othercontent.setBounds(87, 0, 607, 872);
		content.add(othercontent);
		othercontent.setLayout(null);

		questionSign = new ButtonX("", new int[]
		{
				0,
				0,
				40,
				40
		});
		questionSign.setFramesize(6);
		questionSign.setDisabled(true);
		questionSign.setFont(new Font("Comic Sans Ms", 0, 16));
		questionSign.setBounds(5, 5, 592, 274);
		othercontent.add(questionSign);

		next = new ButtonX("", new int[]
		{
				10,
				10,
				10,
				10
		});
		next.setFont(new Font("Comic Sans Ms", 0, 16));
		next.setBounds(307, 769, 142, 40);
		next.addActionListener(e -> next());
		othercontent.add(next);

		finish = new ButtonX("", new int[]
		{
				10,
				10,
				10,
				10
		});
		finish.setFont(new Font("Comic Sans Ms", 0, 16));
		finish.setBounds(458, 769, 142, 40);
		finish.addActionListener(e -> finish(window, testName, classNumber, classLetter, surname, name, secondName));
		othercontent.add(finish);

		skip = new ButtonX("", new int[]
		{
				10,
				10,
				10,
				10
		});
		skip.setFont(new Font("Comic Sans Ms", 0, 16));
		skip.setBounds(156, 769, 142, 40);
		skip.addActionListener(e -> skip());
		skip.setDisabled(!skipButtonOption);
		othercontent.add(skip);

		timer = new ButtonX(toSize((int) timeToTest / 60, 2) + ":" + toSize((int) timeToTest % 60, 2), new int[]
		{
				10,
				10,
				10,
				10
		});
		timer.setFont(new Font("Arial Bold", 1, 16));
		timer.setBounds(5, 770, 142, 40);
		timer.addActionListener(e -> pause());
		othercontent.add(timer);

		qualityIndicator = new JPanel();
		qualityIndicator.setBounds(5, 821, 595, 40);
		qualityIndicator.setVisible(indicateQualityOfLastAnswer);
		othercontent.add(qualityIndicator);
		if (indicateQualityOfAllAnswers)
		{
			qualityIndicator.setLayout(new GridLayout(1, test.getQuestions().length, 2, 2));
			for (int i = 0; i < test.getQuestions().length; i++)
				qualityIndicator.add(new JPanel());
		}

		answersPanel = new JPanel();
		answersPanel.setBounds(21, 290, 564, 475);
		answersPanel.setBackground(test.getTheme2().getWindowBackground());
		othercontent.add(answersPanel);
		answersPanel.setLayout(null);

		ButtonX btn0 = new ButtonX("", new int[]
		{
				10,
				10,
				10,
				10
		});
		btn0.setBounds(0, 0, 564, 75);
		answersPanel.add(btn0);
		answerButtons[0] = btn0;
		btn0.setFont(new Font("Comic Sans Ms", 0, 16));

		ButtonX btn1 = new ButtonX("", new int[]
		{
				10,
				10,
				10,
				10
		});
		btn1.setBounds(0, 80, 564, 75);
		answersPanel.add(btn1);
		answerButtons[1] = btn1;
		btn1.setFont(new Font("Comic Sans Ms", 0, 16));

		ButtonX btn2 = new ButtonX("", new int[]
		{
				10,
				10,
				10,
				10
		});
		btn2.setBounds(0, 160, 564, 75);
		answersPanel.add(btn2);
		answerButtons[2] = btn2;
		btn2.setFont(new Font("Comic Sans Ms", 0, 16));

		ButtonX btn3 = new ButtonX("", new int[]
		{
				10,
				10,
				10,
				10
		});
		btn3.setBounds(0, 240, 564, 75);
		answersPanel.add(btn3);
		answerButtons[3] = btn3;
		btn3.setFont(new Font("Comic Sans Ms", 0, 16));

		ButtonX btn4 = new ButtonX("", new int[]
		{
				10,
				10,
				10,
				10
		});
		btn4.setBounds(0, 320, 564, 75);
		answersPanel.add(btn4);
		answerButtons[4] = btn4;
		btn4.setFont(new Font("Comic Sans Ms", 0, 16));

		ButtonX btn5 = new ButtonX("", new int[]
		{
				10,
				10,
				10,
				10
		});
		btn5.setBounds(0, 400, 564, 75);
		answersPanel.add(btn5);
		answerButtons[5] = btn5;
		btn5.setFont(new Font("Comic Sans Ms", 0, 16));

		scrollbar = new ScrollbarX(1);
		scrollbar.setBounds(70, 0, 17, 872);
		scrollbar.setBackground(Color.white);
		content.add(scrollbar);

		window.setVisible(true);
	}

	/**
	*
	*/
	public void pause()
	{
		if (pauseOption && timeToPause <= 0)
		{
			paused = !paused;
			questionSign.setVisible(!paused);
			next.setVisible(!paused);
			finish.setVisible(!paused);
			qualityIndicator.setVisible(!paused);
			skip.setVisible(!paused);
			questionSelectPanel.setVisible(!paused);
			scrollbar.setVisible(!paused);
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
	public void finish(JFrame window, String testName, String classNumber, String classLetter, String surname, String name, String secondName)
	{
		saveAnswer(currentQuestionNumber);
		handleQuestion();
		paused = true;
		int maxResult = 0;
		for (Question question : test.getQuestions())
			maxResult += question.getMaxAward();
		showResult(window, testName, classNumber, classLetter, surname, name, secondName, countResult(), maxResult);
		window.setVisible(false);
		// StartPart.exit(ExitCodes.UserCloseProgram);
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
			JPanel panel = indicateQualityOfAllAnswers ? (JPanel) qualityIndicator.getComponent(currentQuestionNumber) : qualityIndicator;
			if (questionResult == maxResult)
				panel.setBackground(Color.green);
			else if (questionResult > minResult)
				panel.setBackground(Color.yellow);
			else panel.setBackground(Color.red);
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
						answerButtons[i].setDisabledBackground(test.getTheme2().getQuestionSelectSkippedBackground());
						answerButtons[i].setDisabled(true);
					}
					i++;
				}
			if (test.getQuestions()[currentQuestionNumber].getType() == QuestionType.SelectSome)
				for (Answer a : test.getQuestions()[currentQuestionNumber].getAnswers())
				{
					if (test.getQuestions()[currentQuestionNumber].getAward() + a.getAward() > 0)
					{
						rightAnswer += a.getText() + "\n";
						answerButtons[i].setDisabledBackground(test.getTheme2().getQuestionSelectSkippedBackground());
						answerButtons[i].setDisabled(true);
					}
					i++;
				}
			Logger.showMsgDialog(window, rightAnswer, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
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
				break;
			case PickOne:
				if (answers[questionNumber].getSelectedAnswerNumber() != -1)
					questionResult += question.getAnswers()[answers[questionNumber].getSelectedAnswerNumber()].getAward();
				break;
			case SelectSome:
				for (int i = 0; i < question.getAnswers().length; i++)
					questionResult += answers[questionNumber].getSelectedAnswers()[i] ? question.getAnswers()[i].getAward() : 0;
				break;
			case Arrangement:
				int res = 0;
				for (HashMap<Integer, Integer> hashMap : question.getAnswerArrangement().keySet())
				{
					boolean b = true;
					for (Integer integer : hashMap.keySet())
					{
						System.out.println(answers[questionNumber].getAnswerArrangement()[integer] + " " + hashMap.get(integer) + " " + integer);
						if (answers[questionNumber].getAnswerArrangement()[integer] != hashMap.get(integer))
							b = false;
					}
					if (b)
						if (question.isHandleOnlyMaximal())
							res = Math.max(res, question.getAnswerArrangement().get(hashMap));
						else res += question.getAnswerArrangement().get(hashMap);
				}
				questionResult += res;
				break;
			case Matching:
				break;
			case Distribution:
				break;
		}
		questionResult = Math.max(question.getMinResult(), questionResult);
		System.out.println(questionNumber + " " + questionResult);
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
	public void showResult(JFrame window, String testName, String classNumber, String classLetter, String surname, String name, String secondName, int result,
			int maxResult)
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
		text += msgSys.getMsg("rightAnswersAmount") + ": " + rightAnswers + "\n";
		text += msgSys.getMsg("test.getQuestions()Amount") + ": " + test.getQuestions().length + "\n";
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
		Logger.showMsgDialog(window, text, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
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
			questionSelectAnswerButtons[i].setNormalBackground(skippedQuestions.contains(i) ? test.getTheme2().getQuestionSelectSkippedBackground()
					: test.getTheme2().getQuestionSelectNormalBackground());
			questionSelectAnswerButtons[i].setNormalFrame(skippedQuestions.contains(i) ? test.getTheme2().getQuestionSelectSkippedFrame()
					: test.getTheme2().getQuestionSelectNormalFrame());
			questionSelectAnswerButtons[i].setNormalForeground(skippedQuestions.contains(i) ? test.getTheme2().getQuestionSelectSkippedForeground()
					: test.getTheme2().getQuestionSelectNormalForeground());
			questionSelectAnswerButtons[i].setDisabled(!skippedQuestions.contains(i) && !canGoToAllQuestions || i == currentQuestionNumber);
			questionSelectAnswerButtons[i].setDisabledBackground(i == currentQuestionNumber ? test.getTheme2().getSpecialButtonsBackground()
					: test.getTheme2().getQuestionSelectNormalBackground());
			questionSelectAnswerButtons[i].setDisabledFrame(i == currentQuestionNumber ? test.getTheme2().getSpecialButtonsFrame()
					: test.getTheme2().getQuestionSelectNormalFrame());
			questionSelectAnswerButtons[i].setDisabledForeground(i == currentQuestionNumber ? test.getTheme2().getSpecialButtonsForeground()
					: test.getTheme2().getQuestionSelectNormalForeground());
		}
	}

	/**
	*
	*/
	private void createQuestionSelectPanel()
	{
		questionSelectPanel.setLayout(null);
		questionSelectAnswerButtons = new ButtonX[test.getQuestions().length];
		int height = 32;
		for (int i = 0; i < test.getQuestions().length; i++)
		{
			questionSelectAnswerButtons[i] = new ButtonX(i + 1 + "/" + test.getQuestions().length, new int[]
			{
					10,
					10,
					10,
					10
			});
			int q = i;
			questionSelectAnswerButtons[i].addActionListener(event ->
			{
				questionSelectAnswerButtons[q].setClicked(false);
				int prev = currentQuestionNumber;
				currentQuestionNumber = q;
				saveAnswer(prev);
				openQuestion(prev, currentQuestionNumber);
				for (ButtonX btnx : questionSelectAnswerButtons)
				{
					btnx.setClicked(false);
					btnx.setPressed(false);
					btnx.setSelected(false);
				}
			});
			questionSelectAnswerButtons[i].setSize(questionSelectPanel.getWidth() - 2, height);
			questionSelectAnswerButtons[i].setLocation(1, i * (height + 1));
			questionSelectAnswerButtons[i].setFont(new Font("Comic Sans Ms", 0, 16));
			questionSelectPanel.add(questionSelectAnswerButtons[i]);
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
		else buttonsArrangement = test.getQuestions()[questionNumber].getAnswersIndexes();
		if (test.getQuestions()[questionNumber].getType() == QuestionType.EnterText)
		{
			for (ButtonX button : answerButtons)
				button.setVisible(false);
			answerButtons[0].setVisible(true);
			answerButtons[0].setEditable(true);
			answerButtons[0].setText(answers[questionNumber] != null ? answers[questionNumber].getAnswerText() : "");
			answerButtons[0].setFont(new Font(answerButtons[0].getFont().getFontName(), answerButtons[0].getFont().getStyle(), test
					.getQuestions()[questionNumber].getAnswers()[0].getFont().getSize()));
			answerButtons[0].setClicked(false);
			answerButtons[0].setDisabled(false);
		}
		else
		{
			for (int i = 0; i < answerButtons.length; i++)
				answerButtons[i].setVisible(test.getQuestions()[questionNumber].getAnswers().length > i);
			for (int i = 0; i < test.getQuestions()[questionNumber].getAnswers().length; i++)
			{
				answerButtons[i].setEditable(false);
				answerButtons[i].setText(test.getQuestions()[questionNumber].getAnswers()[i].getText() + (test.getQuestions()[questionNumber]
						.getType() == QuestionType.Arrangement ? " " + buttonsArrangement[i] : ""));
				answerButtons[i].setFont(new Font(answerButtons[i].getFont().getFontName(), answerButtons[i].getFont().getStyle(), test
						.getQuestions()[questionNumber].getAnswers()[i].getFont().getSize()));
				answerButtons[i].setClicked(false);
				answerButtons[i].setDisabled(false);
			}
		}
		for (int i = 0; i < test.getQuestions()[questionNumber].getAnswers().length; i++)
			answerButtons[i].setBounds(0, (test.getQuestions()[questionNumber].getType() != QuestionType.Arrangement ? i : buttonsArrangement[i]) * 80, 564,
					75);
		switch (test.getQuestions()[questionNumber].getType())
		{
			case PickOne:
				questionSign.setDisabledBackground(test.getTheme2().getPickOne().getQuestionBackground());
				questionSign.setDisabledFrame(test.getTheme2().getPickOne().getQuestionFrame());
				questionSign.setDisabledForeground(test.getTheme2().getPickOne().getQuestionForeground());

				for (ButtonX answer : answerButtons)
				{
					answer.setNormalBackground(test.getTheme2().getPickOne().getAnswersBackground());
					answer.setNormalFrame(test.getTheme2().getPickOne().getAnswersFrame());
					answer.setNormalForeground(test.getTheme2().getPickOne().getAnswersForeground());
				}
				break;
			case SelectSome:
				questionSign.setDisabledBackground(test.getTheme2().getSelectSome().getQuestionBackground());
				questionSign.setDisabledFrame(test.getTheme2().getSelectSome().getQuestionFrame());
				questionSign.setDisabledForeground(test.getTheme2().getSelectSome().getQuestionForeground());

				for (ButtonX answer : answerButtons)
				{
					answer.setNormalBackground(test.getTheme2().getSelectSome().getAnswersBackground());
					answer.setNormalFrame(test.getTheme2().getSelectSome().getAnswersFrame());
					answer.setNormalForeground(test.getTheme2().getSelectSome().getAnswersForeground());
				}
				break;
			case EnterText:
				questionSign.setDisabledBackground(test.getTheme2().getEnterText().getQuestionBackground());
				questionSign.setDisabledFrame(test.getTheme2().getEnterText().getQuestionFrame());
				questionSign.setDisabledForeground(test.getTheme2().getEnterText().getQuestionForeground());

				for (ButtonX answer : answerButtons)
				{
					answer.setNormalBackground(test.getTheme2().getEnterText().getAnswersBackground());
					answer.setNormalFrame(test.getTheme2().getEnterText().getAnswersFrame());
					answer.setNormalForeground(test.getTheme2().getEnterText().getAnswersForeground());
				}
				break;
			case Arrangement:
				questionSign.setDisabledBackground(test.getTheme2().getArrangement().getQuestionBackground());
				questionSign.setDisabledFrame(test.getTheme2().getArrangement().getQuestionFrame());
				questionSign.setDisabledForeground(test.getTheme2().getArrangement().getQuestionForeground());

				for (ButtonX answer : answerButtons)
				{
					answer.setNormalBackground(test.getTheme2().getArrangement().getAnswersBackground());
					answer.setNormalFrame(test.getTheme2().getArrangement().getAnswersFrame());
					answer.setNormalForeground(test.getTheme2().getArrangement().getAnswersForeground());
				}
				break;
			case Matching:
				questionSign.setDisabledBackground(test.getTheme2().getMatching().getQuestionBackground());
				questionSign.setDisabledFrame(test.getTheme2().getMatching().getQuestionFrame());
				questionSign.setDisabledForeground(test.getTheme2().getMatching().getQuestionForeground());

				for (ButtonX answer : answerButtons)
				{
					answer.setNormalBackground(test.getTheme2().getMatching().getAnswersBackground());
					answer.setNormalFrame(test.getTheme2().getMatching().getAnswersFrame());
					answer.setNormalForeground(test.getTheme2().getMatching().getAnswersForeground());
				}
				break;
			case Distribution:
				questionSign.setDisabledBackground(test.getTheme2().getDistribution().getQuestionBackground());
				questionSign.setDisabledFrame(test.getTheme2().getDistribution().getQuestionFrame());
				questionSign.setDisabledForeground(test.getTheme2().getDistribution().getQuestionForeground());

				for (ButtonX answer : answerButtons)
				{
					answer.setNormalBackground(test.getTheme2().getDistribution().getAnswersBackground());
					answer.setNormalFrame(test.getTheme2().getDistribution().getAnswersFrame());
					answer.setNormalForeground(test.getTheme2().getDistribution().getAnswersForeground());
				}
				break;
		}
		timer.setNormalBackground(test.getTheme2().getSpecialButtonsBackground());
		timer.setNormalFrame(test.getTheme2().getSpecialButtonsFrame());
		timer.setNormalForeground(test.getTheme2().getSpecialButtonsForeground());
		next.setNormalBackground(test.getTheme2().getSpecialButtonsBackground());
		next.setNormalFrame(test.getTheme2().getSpecialButtonsFrame());
		next.setNormalForeground(test.getTheme2().getSpecialButtonsForeground());
		finish.setNormalBackground(test.getTheme2().getSpecialButtonsBackground());
		finish.setNormalFrame(test.getTheme2().getSpecialButtonsFrame());
		finish.setNormalForeground(test.getTheme2().getSpecialButtonsForeground());
		skip.setNormalBackground(test.getTheme2().getSpecialButtonsBackground());
		skip.setNormalFrame(test.getTheme2().getSpecialButtonsFrame());
		skip.setNormalForeground(test.getTheme2().getSpecialButtonsForeground());

		if (!skippedQuestions.contains(questionNumber))
			lastNotSkippedQuestion = questionNumber;
		selectedAnswers = new boolean[test.getQuestions()[questionNumber].getAnswers().length];
		questionSign.setFont(new Font(questionSign.getFont().getFontName(), questionSign.getFont().getStyle(), test.getQuestions()[questionNumber].getFont()
				.getSize()));
		questionSign.setText(test.getQuestions()[questionNumber].getText());

		if ((questionNumber >= test.getQuestions().length - 1))
			wasGoToEnd = true;

		next.setDisabled(questionNumber >= test.getQuestions().length - 1);
		skip.setDisabled(questionNumber >= test.getQuestions().length - 1);
		finish.setDisabled(!wasGoToEnd);

		updateQuestionSelectPanel();
	}
}
