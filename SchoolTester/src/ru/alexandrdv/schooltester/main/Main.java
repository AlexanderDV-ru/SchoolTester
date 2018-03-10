package ru.alexandrdv.schooltester.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import ru.alexanderdv.swingutilities.components.ButtonX;
import ru.alexanderdv.swingutilities.components.ScrollbarX;
import ru.alexandrdv.schooltester.util.Logger;
import ru.alexandrdv.schooltester.util.MessageSystem;
import ru.alexandrdv.schooltester.util.Question;
import ru.alexandrdv.schooltester.util.Question.Answer;
import ru.alexandrdv.schooltester.util.Question.QuestionType;
import ru.alexandrdv.schooltester.util.Theme;

/**
 * Main
 * 
 * @author AlexandrDV
 * @version 4.5.1a
 *
 */
@Deprecated
public class Main
{
	public static final String version = "4.5.1a";
	public static final String authors = "AlexandrDV";
	public static final String programName = "SchoolTester v" + version + " by " + authors;
	public static final MessageSystem msgSys = new MessageSystem("en_uk");
	public static final Logger logger = new Logger(programName);
	private boolean paused = false, canPause, indicateAnswerQuality, indicateAnswersQuality, showRightAnswer;
	private boolean[] selectedAnswers = new boolean[6];
	private ButtonX[] btns = new ButtonX[6];
	private ActionListener answersButtonsListener;
	private int toPauseTime = 0;
	private int maxResult;
	private int maxTime = 20 * 60;
	private float allTime = 0, timeOfWork = 0;
	private int result = 0;
	private int questionNumber;
	private long lastTime = 0;
	private final Question[] questions;
	private AnswerToQ[] answers;
	private ButtonX question_1;
	private ButtonX timer;
	private ButtonX next;
	private ButtonX finish;
	private ButtonX skip;
	private String testName;
	private int rightAnswers = 0;
	private int perfectAnswers = 0;
	private ArrayList<Integer> skipped = new ArrayList<Integer>();
	private JFrame frame;
	private JPanel panel;
	private JPanel questionsList;
	private ButtonX[] questionsListBtns;
	private int lastNotSkipped = 0;
	private boolean canGoToAllQuestions;
	private Theme theme;
	private ScrollbarX scrollBar;

	class AnswerToQ
	{
		String s;
		boolean[] b;
		int n = -1;
	}

	static
	{
		System.setOut(new PrintStream(System.out)
		{
			boolean out = false;

			/**
			 * Prints a boolean value. The string produced by <code>{@link
			 * java.lang.String#valueOf(boolean)}</code> is translated into bytes according to the platform's default character encoding, and these bytes are
			 * written in exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param b
			 *            The <code>boolean</code> to be printed
			 */
			@Override
			public void print(boolean b)
			{
				if (out)
					super.print(b);
				logger.log(Level.INFO, "" + b);
			}

			/**
			 * Prints a character. The character is translated into one or more bytes according to the platform's default character encoding, and these bytes
			 * are written in exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param c
			 *            The <code>char</code> to be printed
			 */
			@Override
			public void print(char c)
			{
				if (out)
					super.print(c);
				logger.log(Level.INFO, "" + c);
			}

			/**
			 * Prints an integer. The string produced by <code>{@link
			 * java.lang.String#valueOf(int)}</code> is translated into bytes according to the platform's default character encoding, and these bytes are
			 * written in exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param i
			 *            The <code>int</code> to be printed
			 * @see java.lang.Integer#toString(int)
			 */
			@Override
			public void print(int i)
			{
				if (out)
					super.print(i);
				logger.log(Level.INFO, "" + i);
			}

			/**
			 * Prints a long integer. The string produced by <code>{@link
			 * java.lang.String#valueOf(long)}</code> is translated into bytes according to the platform's default character encoding, and these bytes are
			 * written in exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param l
			 *            The <code>long</code> to be printed
			 * @see java.lang.Long#toString(long)
			 */
			@Override
			public void print(long l)
			{
				if (out)
					super.print(l);
				logger.log(Level.INFO, "" + l);
			}

			/**
			 * Prints a floating-point number. The string produced by <code>{@link
			 * java.lang.String#valueOf(float)}</code> is translated into bytes according to the platform's default character encoding, and these bytes are
			 * written in exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param f
			 *            The <code>float</code> to be printed
			 * @see java.lang.Float#toString(float)
			 */
			@Override
			public void print(float f)
			{
				if (out)
					super.print(f);
				logger.log(Level.INFO, "" + f);
			}

			/**
			 * Prints a double-precision floating-point number. The string produced by <code>{@link java.lang.String#valueOf(double)}</code> is translated into
			 * bytes according to the platform's default character encoding, and these bytes are written in exactly the manner of the <code>{@link
			 * #write(int)}</code> method.
			 *
			 * @param d
			 *            The <code>double</code> to be printed
			 * @see java.lang.Double#toString(double)
			 */
			@Override
			public void print(double d)
			{
				if (out)
					super.print(d);
				logger.log(Level.INFO, "" + d);
			}

			/**
			 * Prints an array of characters. The characters are converted into bytes according to the platform's default character encoding, and these bytes
			 * are written in exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param s
			 *            The array of chars to be printed
			 *
			 * @throws NullPointerException
			 *             If <code>s</code> is <code>null</code>
			 */
			@Override
			public void print(char s[])
			{
				if (out)
					super.print(s);
				logger.log(Level.INFO, "" + new String(s));
			}

			/**
			 * Prints a string. If the argument is <code>null</code> then the string <code>"null"</code> is printed. Otherwise, the string's characters are
			 * converted into bytes according to the platform's default character encoding, and these bytes are written in exactly the manner of the
			 * <code>{@link #write(int)}</code> method.
			 *
			 * @param s
			 *            The <code>String</code> to be printed
			 */
			@Override
			public void print(String s)
			{
				if (out)
					super.print(s);
				logger.log(Level.INFO, "" + s);
			}

			/**
			 * Prints an object. The string produced by the <code>{@link
			 * java.lang.String#valueOf(Object)}</code> method is translated into bytes according to the platform's default character encoding, and these bytes
			 * are written in exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param obj
			 *            The <code>Object</code> to be printed
			 * @see java.lang.Object#toString()
			 */
			@Override
			public void print(Object obj)
			{
				if (out)
					super.print(obj);
				logger.log(Level.INFO, "" + obj);
			}

			/* Methods that do terminate lines */

			/**
			 * Terminates the current line by writing the line separator string. The line separator string is defined by the system property
			 * <code>line.separator</code>, and is not necessarily a single newline character (<code>'\n'</code>).
			 */
			@Override
			public void println()
			{
				if (out)
					super.println();
				logger.log(Level.INFO, "");
			}

			/**
			 * Prints a boolean and then terminate the line. This method behaves as though it invokes <code>{@link #print(boolean)}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            The <code>boolean</code> to be printed
			 */
			@Override
			public void println(boolean x)
			{
				if (out)
					super.println(x);
				logger.log(Level.INFO, "" + x);
			}

			/**
			 * Prints a character and then terminate the line. This method behaves as though it invokes <code>{@link #print(char)}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            The <code>char</code> to be printed.
			 */
			@Override
			public void println(char x)
			{
				if (out)
					super.println(x);
				logger.log(Level.INFO, "" + x);
			}

			/**
			 * Prints an integer and then terminate the line. This method behaves as though it invokes <code>{@link #print(int)}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            The <code>int</code> to be printed.
			 */
			@Override
			public void println(int x)
			{
				if (out)
					super.println(x);
				logger.log(Level.INFO, "" + x);
			}

			/**
			 * Prints a long and then terminate the line. This method behaves as though it invokes <code>{@link #print(long)}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            a The <code>long</code> to be printed.
			 */
			@Override
			public void println(long x)
			{
				if (out)
					super.println(x);
				logger.log(Level.INFO, "" + x);
			}

			/**
			 * Prints a float and then terminate the line. This method behaves as though it invokes <code>{@link #print(float)}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            The <code>float</code> to be printed.
			 */
			@Override
			public void println(float x)
			{
				if (out)
					super.println(x);
				logger.log(Level.INFO, "" + x);
			}

			/**
			 * Prints a double and then terminate the line. This method behaves as though it invokes <code>{@link #print(double)}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            The <code>double</code> to be printed.
			 */
			@Override
			public void println(double x)
			{
				if (out)
					super.println(x);
				logger.log(Level.INFO, "" + x);
			}

			/**
			 * Prints an array of characters and then terminate the line. This method behaves as though it invokes <code>{@link #print(char[])}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            an array of chars to print.
			 */
			@Override
			public void println(char x[])
			{
				if (out)
					super.println(x);
				logger.log(Level.INFO, "" + new String(x));
			}

			/**
			 * Prints a String and then terminate the line. This method behaves as though it invokes <code>{@link #print(String)}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            The <code>String</code> to be printed.
			 */
			@Override
			public void println(String x)
			{
				if (out)
					super.println(x);
				logger.log(Level.INFO, "" + x);
			}

			/**
			 * Prints an Object and then terminate the line. This method calls at first String.valueOf(x) to get the printed object's string value, then behaves
			 * as though it invokes <code>{@link #print(String)}</code> and then <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            The <code>Object</code> to be printed.
			 */
			@Override
			public void println(Object x)
			{
				if (out)
					super.println(x);
				logger.log(Level.INFO, "" + x);
			}
		});
		System.setErr(new PrintStream(System.err)
		{
			boolean out = false;

			/**
			 * Prints a boolean value. The string produced by <code>{@link
			 * java.lang.String#valueOf(boolean)}</code> is translated into bytes according to the platform's default character encoding, and these bytes are
			 * written in exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param b
			 *            The <code>boolean</code> to be printed
			 */
			@Override
			public void print(boolean b)
			{
				if (out)
					super.print(b);
				logger.log(Level.SEVERE, "" + b);
			}

			/**
			 * Prints a character. The character is translated into one or more bytes according to the platform's default character encoding, and these bytes
			 * are written in exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param c
			 *            The <code>char</code> to be printed
			 */
			@Override
			public void print(char c)
			{
				if (out)
					super.print(c);
				logger.log(Level.SEVERE, "" + c);
			}

			/**
			 * Prints an integer. The string produced by <code>{@link
			 * java.lang.String#valueOf(int)}</code> is translated into bytes according to the platform's default character encoding, and these bytes are
			 * written in exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param i
			 *            The <code>int</code> to be printed
			 * @see java.lang.Integer#toString(int)
			 */
			@Override
			public void print(int i)
			{
				if (out)
					super.print(i);
				logger.log(Level.SEVERE, "" + i);
			}

			/**
			 * Prints a long integer. The string produced by <code>{@link
			 * java.lang.String#valueOf(long)}</code> is translated into bytes according to the platform's default character encoding, and these bytes are
			 * written in exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param l
			 *            The <code>long</code> to be printed
			 * @see java.lang.Long#toString(long)
			 */
			@Override
			public void print(long l)
			{
				if (out)
					super.print(l);
				logger.log(Level.SEVERE, "" + l);
			}

			/**
			 * Prints a floating-point number. The string produced by <code>{@link
			 * java.lang.String#valueOf(float)}</code> is translated into bytes according to the platform's default character encoding, and these bytes are
			 * written in exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param f
			 *            The <code>float</code> to be printed
			 * @see java.lang.Float#toString(float)
			 */
			@Override
			public void print(float f)
			{
				if (out)
					super.print(f);
				logger.log(Level.SEVERE, "" + f);
			}

			/**
			 * Prints a double-precision floating-point number. The string produced by <code>{@link java.lang.String#valueOf(double)}</code> is translated into
			 * bytes according to the platform's default character encoding, and these bytes are written in exactly the manner of the <code>{@link
			 * #write(int)}</code> method.
			 *
			 * @param d
			 *            The <code>double</code> to be printed
			 * @see java.lang.Double#toString(double)
			 */
			@Override
			public void print(double d)
			{
				if (out)
					super.print(d);
				logger.log(Level.SEVERE, "" + d);
			}

			/**
			 * Prints an array of characters. The characters are converted into bytes according to the platform's default character encoding, and these bytes
			 * are written in exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param s
			 *            The array of chars to be printed
			 *
			 * @throws NullPointerException
			 *             If <code>s</code> is <code>null</code>
			 */
			@Override
			public void print(char s[])
			{
				if (out)
					super.print(s);
				logger.log(Level.SEVERE, "" + new String(s));
			}

			/**
			 * Prints a string. If the argument is <code>null</code> then the string <code>"null"</code> is printed. Otherwise, the string's characters are
			 * converted into bytes according to the platform's default character encoding, and these bytes are written in exactly the manner of the
			 * <code>{@link #write(int)}</code> method.
			 *
			 * @param s
			 *            The <code>String</code> to be printed
			 */
			@Override
			public void print(String s)
			{
				if (out)
					super.print(s);
				logger.log(Level.SEVERE, "" + s);
			}

			/**
			 * Prints an object. The string produced by the <code>{@link
			 * java.lang.String#valueOf(Object)}</code> method is translated into bytes according to the platform's default character encoding, and these bytes
			 * are written in exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param obj
			 *            The <code>Object</code> to be printed
			 * @see java.lang.Object#toString()
			 */
			@Override
			public void print(Object obj)
			{
				if (out)
					super.print(obj);
				logger.log(Level.SEVERE, "" + obj);
			}

			/* Methods that do terminate lines */

			/**
			 * Terminates the current line by writing the line separator string. The line separator string is defined by the system property
			 * <code>line.separator</code>, and is not necessarily a single newline character (<code>'\n'</code>).
			 */
			@Override
			public void println()
			{
				if (out)
					super.println();
				logger.log(Level.SEVERE, "");
			}

			/**
			 * Prints a boolean and then terminate the line. This method behaves as though it invokes <code>{@link #print(boolean)}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            The <code>boolean</code> to be printed
			 */
			@Override
			public void println(boolean x)
			{
				if (out)
					super.println(x);
				logger.log(Level.SEVERE, "" + x);
			}

			/**
			 * Prints a character and then terminate the line. This method behaves as though it invokes <code>{@link #print(char)}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            The <code>char</code> to be printed.
			 */
			@Override
			public void println(char x)
			{
				if (out)
					super.println(x);
				logger.log(Level.SEVERE, "" + x);
			}

			/**
			 * Prints an integer and then terminate the line. This method behaves as though it invokes <code>{@link #print(int)}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            The <code>int</code> to be printed.
			 */
			@Override
			public void println(int x)
			{
				if (out)
					super.println(x);
				logger.log(Level.SEVERE, "" + x);
			}

			/**
			 * Prints a long and then terminate the line. This method behaves as though it invokes <code>{@link #print(long)}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            a The <code>long</code> to be printed.
			 */
			@Override
			public void println(long x)
			{
				if (out)
					super.println(x);
				logger.log(Level.SEVERE, "" + x);
			}

			/**
			 * Prints a float and then terminate the line. This method behaves as though it invokes <code>{@link #print(float)}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            The <code>float</code> to be printed.
			 */
			@Override
			public void println(float x)
			{
				if (out)
					super.println(x);
				logger.log(Level.SEVERE, "" + x);
			}

			/**
			 * Prints a double and then terminate the line. This method behaves as though it invokes <code>{@link #print(double)}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            The <code>double</code> to be printed.
			 */
			@Override
			public void println(double x)
			{
				if (out)
					super.println(x);
				logger.log(Level.SEVERE, "" + x);
			}

			/**
			 * Prints an array of characters and then terminate the line. This method behaves as though it invokes <code>{@link #print(char[])}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            an array of chars to print.
			 */
			@Override
			public void println(char x[])
			{
				if (out)
					super.println(x);
				logger.log(Level.SEVERE, "" + new String(x));
			}

			/**
			 * Prints a String and then terminate the line. This method behaves as though it invokes <code>{@link #print(String)}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            The <code>String</code> to be printed.
			 */
			@Override
			public void println(String x)
			{
				if (out)
					super.println(x);
				logger.log(Level.SEVERE, "" + x);
			}

			/**
			 * Prints an Object and then terminate the line. This method calls at first String.valueOf(x) to get the printed object's string value, then behaves
			 * as though it invokes <code>{@link #print(String)}</code> and then <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            The <code>Object</code> to be printed.
			 */
			@Override
			public void println(Object x)
			{
				if (out)
					super.println(x);
				logger.log(Level.SEVERE, "" + x);
			}
		});
	}

	public void changeLanguage()
	{
		next.setText(msgSys.getMsg("next"));
		skip.setText(msgSys.getMsg("skip"));
		finish.setText(msgSys.getMsg("finish"));
	}

	/**
	 * 
	 * @param parentLoc
	 * @param parentSize
	 * @param testName
	 * @param q
	 * @param _class
	 * @param surname
	 * @param name
	 * @param secondName
	 * @param maxTime
	 * @param canPause
	 * @wbp.parser.entryPoint
	 */
	public Main(Theme theme, Rectangle parent, String testName, Question[] questions, String _class, String surname, String name, String secondName,
			int maxTime, boolean canPause, boolean indicateAnswerQuality, boolean indicateAnswersQuality, boolean showRightAnswer, boolean canSkip,
			boolean canGoToAllQuestions, boolean pauseOnUnfocus, boolean anticntrlc, boolean antiscreenshot)
	{
		this.theme = theme;
		ButtonX.anticntrlc = anticntrlc;
		ButtonX.antiscreenshot = antiscreenshot;
		KeyListener keyListener = new KeyListener()
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
					return new DataFlavor[] { DataFlavor.imageFlavor };
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
						Logger.showMsgDialog(frame, "Key printscreen was clicked!", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION);
					});
					timer.setRepeats(false);
					timer.start();
				}
			}
		};

		ButtonX.antish = keyListener;
		this.indicateAnswerQuality = indicateAnswerQuality;
		this.indicateAnswersQuality = indicateAnswersQuality;
		this.showRightAnswer = showRightAnswer;
		this.canGoToAllQuestions = canGoToAllQuestions;
		this.testName = testName;
		this.canPause = canPause;
		this.maxTime = maxTime;
		this.questions = questions;
		answers = new AnswerToQ[questions.length];
		for (int i = 0; i < questions.length; i++)
			answers[i] = new AnswerToQ();
		for (Question question : questions)
			maxResult += question.getMaxAward();

		JFrame window = new JFrame();
		window.setSize(539, 589);
		window.setLocation(parent.x + parent.width / 2 - window.getWidth() / 2, parent.y + parent.height / 2 - window.getHeight() / 2);
		window.setTitle(programName);
		ArrayList<Image> icons = new ArrayList<Image>();
		icons.add(new ImageIcon(getClass().getResource("/Icon16x.png")).getImage());
		icons.add(new ImageIcon(getClass().getResource("/Icon32x.png")).getImage());
		icons.add(new ImageIcon(getClass().getResource("/Icon48x.png")).getImage());
		window.setIconImages(icons);
		window.setDefaultCloseOperation(3);
		window.setVisible(true);

		ArrayList<ButtonX> buttons = new ArrayList<ButtonX>();
		answersButtonsListener = (e) ->
		{
			((ButtonX) e.getSource()).setClicked((questions[questionNumber].getType() != QuestionType.EnterText) ? selectedAnswers[buttons.indexOf((ButtonX) e
					.getSource())] = !selectedAnswers[buttons.indexOf((ButtonX) e.getSource())] : false);
			if (questions[questionNumber].getType() == QuestionType.PickOne)
				for (ButtonX button : buttons)
					button.setClicked(button == e.getSource());
		};
		window.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel content = new JPanel();
		content.setBackground(theme.windowBackground);
		window.getContentPane().add(content);
		content.setLayout(null);

		questionsList = new JPanel();
		questionsList.setBackground(theme.windowBackground);
		questionsList.setBounds(0, 0, 70, 550);
		content.add(questionsList);
		createQuestionsList();
		updateQuestionsList();

		JPanel othercontent = new JPanel();
		othercontent.setBackground(theme.windowBackground);
		othercontent.setBounds(87, 0, 435, 550);
		content.add(othercontent);
		othercontent.setLayout(null);

		question_1 = new ButtonX("",  new int[] { 0, 10, 10, 0 });
		question_1.setDisabled(true);
		question_1.setFont(new Font("Comic Sans Ms", 0, 16));
		question_1.setBounds(5, 5, 424, 60);
		othercontent.add(question_1);

		ButtonX btn0 = new ButtonX("", new int[] { 10, 10, 10, 10 });
		btns[0] = btn0;
		btn0.setFont(new Font("Comic Sans Ms", 0, 16));
		btn0.setBounds(20, 70, 393, 60);
		othercontent.add(btn0);

		ButtonX btn1 = new ButtonX("", new int[] { 10, 10, 10, 10 });
		btns[1] = btn1;
		btn1.setFont(new Font("Comic Sans Ms", 0, 16));
		btn1.setBounds(20, 135, 393, 60);
		othercontent.add(btn1);

		ButtonX btn2 = new ButtonX("", new int[] { 10, 10, 10, 10 });
		btns[2] = btn2;
		btn2.setFont(new Font("Comic Sans Ms", 0, 16));
		btn2.setBounds(20, 200, 393, 60);
		othercontent.add(btn2);

		ButtonX btn3 = new ButtonX("", new int[] { 10, 10, 10, 10 });
		btns[3] = btn3;
		btn3.setFont(new Font("Comic Sans Ms", 0, 16));
		btn3.setBounds(20, 265, 393, 60);
		othercontent.add(btn3);

		ButtonX btn4 = new ButtonX("", new int[] { 10, 10, 10, 10 });
		btns[4] = btn4;
		btn4.setFont(new Font("Comic Sans Ms", 0, 16));
		btn4.setBounds(20, 330, 393, 60);
		othercontent.add(btn4);

		ButtonX btn5 = new ButtonX("", new int[] { 10, 10, 10, 10 });
		btns[5] = btn5;
		btn5.setFont(new Font("Comic Sans Ms", 0, 16));
		btn5.setBounds(20, 395, 393, 60);
		othercontent.add(btn5);

		next = new ButtonX("", new int[] { 10, 10, 10, 10 });
		next.setFont(new Font("Comic Sans Ms", 0, 16));
		next.setBounds(153, 459, 125, 40);
		next.addActionListener(e -> next());
		othercontent.add(next);

		finish = new ButtonX("", new int[] { 10, 10, 10, 10 });
		finish.setFont(new Font("Comic Sans Ms", 0, 16));
		finish.setBounds(153, 459, 125, 40);
		finish.addActionListener(e -> finish(window, _class, surname, name, secondName));
		othercontent.add(finish);

		skip = new ButtonX("", new int[] { 10, 10, 10, 10 });
		skip.setFont(new Font("Comic Sans Ms", 0, 16));
		skip.setBounds(288, 459, 125, 40);
		skip.addActionListener(e -> skip());
		skip.setDisabled(!canSkip);
		othercontent.add(skip);

		timer = new ButtonX(toSize(maxTime / 60, 2) + ":" + toSize(maxTime % 60, 2), new int[] { 10, 10, 10, 10 });
		timer.setFont(new Font("Arial Bold", 1, 16));
		timer.setBounds(20, 460, 125, 40);
		timer.addActionListener(e -> pause());
		othercontent.add(timer);
		panel = new JPanel();
		panel.setBounds(20, 505, 393, 40);
		othercontent.add(panel);

		scrollBar = new ScrollbarX();
		scrollBar.setBounds(70, 0, 17, 550);
		scrollBar.setBackground(Color.white);

		{
			ButtonX btn = new ButtonX("", new int[] { 8, 8, 8, 8 });
			btn.setSize(scrollBar.getWidth() - 2, (int) (scrollBar.getHeight() / 2));
			scrollBar.add(btn);
			btn.addMouseMotionListener(new MouseMotionListener()
			{

				@Override
				public void mouseMoved(MouseEvent e)
				{
					if(btn.isDisabled())
						if(e.getY()-btn.getFramesize()!=0)
					btn.setLocation(btn.getX(), Math.max(Math.min(btn.getY()+e.getY()-btn.getFramesize(),scrollBar.getHeight()-btn.getHeight()),0));
				}

				@Override
				public void mouseDragged(MouseEvent e)
				{
					mouseMoved(e);
				}
			});
			btn.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseReleased(MouseEvent e)
				{
					btn.setDisabled(false);
				}

				@Override
				public void mousePressed(MouseEvent e)
				{
					btn.setDisabled(true);
					btn.setFramesize(e.getY());
				}
			});
		}
		content.add(scrollBar);

		panel.setVisible(indicateAnswerQuality);

		if (pauseOnUnfocus)
			window.addWindowFocusListener(new WindowFocusListener()
			{

				@Override
				public void windowLostFocus(WindowEvent e)
				{
					if (!paused && (frame == null ? true : frame.isFocused()))
						timer.click();
				}

				@Override
				public void windowGainedFocus(WindowEvent e)
				{
					if (frame != null)
					{
						frame.setVisible(false);
						frame = null;
					}
				}
			});
		for (ButtonX btn : btns)
		{
			buttons.add(btn);
			btn.addActionListener(answersButtonsListener);
		}
		lastTime = Calendar.getInstance().getTimeInMillis();
		new Timer(1, e ->
		{
			long time = Calendar.getInstance().getTimeInMillis();
			if (!paused)
				timeOfWork += ((float) (time - lastTime)) / 1000f;
			allTime += ((float) (time - lastTime)) / 1000f;
			toPauseTime--;
			timer.setText(toSize((maxTime - (int) timeOfWork) / 60, 2) + ":" + toSize((maxTime - (int) timeOfWork) % 60, 2));
			if (timeOfWork >= maxTime)
				finish(window, _class, surname, name, secondName);
			lastTime = Calendar.getInstance().getTimeInMillis();
		}).start();
		openQuestion(questionNumber - 1, questionNumber);
		if (indicateAnswersQuality)
		{
			panel.setLayout(new GridLayout(1, questions.length, 2, 2));
			for (int i = 0; i < questions.length; i++)
				panel.add(new JPanel());
		}
		changeLanguage();
		content.addKeyListener(keyListener);
	}

	private void updateQuestionsList()
	{
		for (int i = 0; i < questions.length; i++)
		{
			questionsListBtns[i].setNormalBackground(skipped.contains(i) ? theme.questionSelectSkippedBackground : theme.questionSelectNormalBackground);
			questionsListBtns[i].setNormalFrame(skipped.contains(i) ? theme.questionSelectSkippedFrame : theme.questionSelectNormalFrame);
			questionsListBtns[i].setNormalForeground(skipped.contains(i) ? theme.questionSelectSkippedForeground : theme.questionSelectNormalForeground);
			questionsListBtns[i].setDisabled(!skipped.contains(i) && !canGoToAllQuestions || i == questionNumber);
			questionsListBtns[i].setDisabledBackground(i == questionNumber ? theme.specialButtonsBackground : theme.questionSelectNormalBackground);
			questionsListBtns[i].setDisabledFrame(i == questionNumber ? theme.specialButtonsFrame : theme.questionSelectNormalFrame);
			questionsListBtns[i].setDisabledForeground(i == questionNumber ? theme.specialButtonsForeground : theme.questionSelectNormalForeground);
		}
	}

	private void createQuestionsList()
	{
		questionsList.setLayout(null);
		questionsListBtns = new ButtonX[questions.length];
		int height = 32;
		for (int i = 0; i < questions.length; i++)
		{
			questionsListBtns[i] = new ButtonX(i + 1 + "/" + questions.length, new int[] { 8, 8, 8, 8 });
			int q = i;
			questionsListBtns[i].addActionListener(event ->
			{
				questionsListBtns[q].setClicked(false);
				int prev = questionNumber;
				questionNumber = q;
				saveAnswer(prev);
				openQuestion(prev, questionNumber);
				for (ButtonX btnx : questionsListBtns)
				{
					btnx.setClicked(false);
					btnx.setPressed(false);
					btnx.setSelected(false);
				}
			});
			questionsListBtns[i].setSize(questionsList.getWidth() - 2, height);
			questionsListBtns[i].setLocation(1, i * (height + 1));
			questionsListBtns[i].setFont(new Font("Comic Sans Ms", 0, 16));
			questionsList.add(questionsListBtns[i]);
		}
	}

	public void saveAnswer(int questionNumber)
	{
		answers[questionNumber] = new AnswerToQ();
		answers[questionNumber].b = selectedAnswers.clone();
		for (int j = 0; j < answers[questionNumber].b.length; j++)
			if (answers[questionNumber].b[j])
				answers[questionNumber].n = j;
		answers[questionNumber].s = btns[0].getText();
	}

	public void pause()
	{
		if (Main.this.canPause && toPauseTime <= 0)
		{
			question_1.setVisible(paused);
			next.setVisible(paused);
			skip.setVisible(paused);
			questionsList.setVisible(paused);
			scrollBar.setVisible(paused);
			if (questions[questionNumber].getType() == QuestionType.EnterText)
				btns[0].setVisible(paused);
			else for (int i = 0; i < questions[questionNumber].getAnswers().length; i++)
				btns[i].setVisible(paused);
			paused = !paused;
			toPauseTime = 300;
			timer.setClicked(paused);
		}
	}

	/**
	 * 
	 */
	public void next()
	{
		saveAnswer(questionNumber);
		handleQuestion();
		openQuestion(questionNumber, questionNumber);
	}

	/**
	 * 
	 */
	public void skip()
	{
		int qn = questionNumber;
		goNext();
		if (!skipped.contains(qn))
			skipped.add(qn);
		openQuestion(questionNumber - 1, questionNumber);
	}

	public void goNext()
	{
		if (!canGoToAllQuestions)
			if (skipped.contains((Object) questionNumber))
			{
				skipped.remove((Object) questionNumber);
				if (skipped.size() != 0)
				{
					for (int i = 0; i < skipped.size(); i++)
						if (skipped.get(i) > questionNumber)
						{
							questionNumber = skipped.get(i);
							break;
						}
						else if (i == skipped.size() - 1)
							if (skipped.get(i) >= lastNotSkipped)
								questionNumber = skipped.get(0);
							else questionNumber = lastNotSkipped;
				}
				else questionNumber = lastNotSkipped;
			}
			else questionNumber++;
		else questionNumber++;
	}

	/**
	 * 
	 * @param window
	 * @param _class
	 * @param surname
	 * @param name
	 * @param secondName
	 */
	public void finish(JFrame window, String _class, String surname, String name, String secondName)
	{
		saveAnswer(questionNumber);
		handleQuestion();
		paused = true;
		countResult();
		showResult(window, _class, surname, name, secondName);
		window.setVisible(false);
		Logger.exit(0);
	}

	public void countResult()
	{
		result = rightAnswers = perfectAnswers = 0;
		for (int i = 0; i < questions.length; i++)
		{
			int[] results = _handleQuestion(i);
			result += results[1];
			rightAnswers += (results[0] > results[1] || results[2] == results[1]) ? 1 : 0;
			perfectAnswers += results[2] == results[1] ? 1 : 0;
		}
	}

	public void handleQuestion()
	{
		saveAnswer(questionNumber);
		int[] res = _handleQuestion(questionNumber);
		int minResult = res[0], questionResult = res[1], maxResult = res[2];

		if (questionResult > minResult || questionResult == maxResult)
			rightAnswers++;
		if (questionResult == maxResult)
			perfectAnswers++;

		if (indicateAnswerQuality)
		{
			JPanel panel = indicateAnswersQuality ? (JPanel) this.panel.getComponent(questionNumber) : this.panel;
			if (questionResult == maxResult)
				panel.setBackground(Color.green);
			else if (questionResult > minResult)
				panel.setBackground(Color.yellow);
			else panel.setBackground(Color.red);
		}
		result += questionResult;
		if (showRightAnswer)
		{
			String rightAnswer = "";
			if (questions[questionNumber].getType() == QuestionType.EnterText)
			{
				for (Answer a : questions[questionNumber].getAnswers())
					if (questions[questionNumber].getAward() + a.getAward() == questions[questionNumber].getMaxAward())
						rightAnswer += a.getText() + "\n";
			}
			int i = 0;
			if (questions[questionNumber].getType() == QuestionType.PickOne)
				for (Answer a : questions[questionNumber].getAnswers())
				{
					if (questions[questionNumber].getAward() + a.getAward() == questions[questionNumber].getMaxAward())
					{
						rightAnswer += a.getText() + "\n";
						btns[i].setDisabledBackground(theme.questionSelectSkippedBackground);
						btns[i].setDisabled(true);
					}
					i++;
				}
			if (questions[questionNumber].getType() == QuestionType.SelectSome)
				for (Answer a : questions[questionNumber].getAnswers())
				{
					if (questions[questionNumber].getAward() + a.getAward() > 0)
					{
						rightAnswer += a.getText() + "\n";
						btns[i].setDisabledBackground(theme.questionSelectSkippedBackground);
						btns[i].setDisabled(true);
					}
					i++;
				}
			Logger.showMsgDialog(frame, rightAnswer, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
		}
		goNext();
	}

	private int[] _handleQuestion(int questionNumber)
	{
		if (answers[questionNumber] != null ? answers[questionNumber].b == null || answers[questionNumber].s == null : true)
		{
			answers[questionNumber] = new AnswerToQ();
			answers[questionNumber].s = "";
			answers[questionNumber].n = -1;
			answers[questionNumber].b = new boolean[questions[questionNumber].getAnswers().length];
		}
		Question question = questions[questionNumber];
		int questionResult = question.getAward();
		switch (questions[questionNumber].getType())
		{
			case EnterText:
				for (int i = 0; i < question.getAnswers().length; i++)
				{
					String userAnswer = answers[questionNumber].s, curAnswer = question.getAnswers()[i].getText();
					if (questions[questionNumber].isIgnoreCase())
					{
						userAnswer = userAnswer.toLowerCase();
						curAnswer = curAnswer.toLowerCase();
					}
					for (String c : questions[questionNumber].getIgnoredCharacters().split(""))
					{
						userAnswer = userAnswer.replace(c.toLowerCase(), "");
						curAnswer = curAnswer.replace(c.toLowerCase(), "");
					}
					if (userAnswer.equals(curAnswer))
						questionResult += question.getAnswers()[i].getAward();
				}
				break;
			case PickOne:
				if (answers[questionNumber].n != -1)
					questionResult += question.getAnswers()[answers[questionNumber].n].getAward();
				break;
			case SelectSome:
				for (int i = 0; i < question.getAnswers().length; i++)
					questionResult += answers[questionNumber].b[i] ? question.getAnswers()[i].getAward() : 0;
				break;
		}
		questionResult = Math.max(question.getMinResult(), questionResult);
		return new int[] { question.getAward(), questionResult, question.getMaxAward() };
	}

	/**
	 * 
	 * @param window
	 * @param _class
	 * @param surname
	 * @param name
	 * @param secondName
	 */
	public void showResult(JFrame window, String _class, String surname, String name, String secondName)
	{
		String text = "";
		text += "syntaxLanguage" + ": \"" + msgSys.getLanguage() + "\"\n";
		text += msgSys.getMsg("file") + ": \"" + testName + "\"\n";
		text += msgSys.getMsg("class") + ": \"" + _class + "\"\n";
		text += msgSys.getMsg("surname") + ": \"" + surname + "\"\n";
		text += msgSys.getMsg("name") + ": \"" + name + "\"\n";
		text += msgSys.getMsg("secondName") + ": \"" + secondName + "\"\n";
		text += msgSys.getMsg("result") + ": " + result + "\n";
		text += msgSys.getMsg("maxResult") + ": " + maxResult + "\n";
		text += msgSys.getMsg("perfectAnswersAmount") + ": " + perfectAnswers + "\n";
		text += msgSys.getMsg("rightAnswersAmount") + ": " + rightAnswers + "\n";
		text += msgSys.getMsg("questionsAmount") + ": " + questions.length + "\n";
		text += msgSys.getMsg("time") + ": " + toSize((int) timeOfWork / 60, 2) + ":" + toSize((int) timeOfWork % 60, 2) + "\n";
		text += msgSys.getMsg("timeToTest") + ": " + toSize(maxTime / 60, 2) + ":" + toSize(maxTime % 60, 2) + "\n";
		text += msgSys.getMsg("fullTime") + ": " + toSize((int) allTime / 60, 2) + ":" + toSize((int) allTime % 60, 2);
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
		Logger.showMsgDialog(frame, text, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
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
	 * @param question
	 * @param info
	 * @param number
	 */
	public void openQuestion(int prev, int questionNumber)
	{
		if (questions[questionNumber].getType() == QuestionType.EnterText)
		{
			for (ButtonX button : btns)
				button.setVisible(false);
			btns[0].setVisible(true);
			btns[0].setEditable(true);
			btns[0].setText(answers[questionNumber].s != null ? answers[questionNumber].s : "");
			btns[0].setFont(new Font(btns[0].getFont().getFontName(), btns[0].getFont().getStyle(), questions[questionNumber].getAnswers()[0].getFont()
					.getSize()));
			btns[0].setClicked(false);
			btns[0].setDisabled(false);
		}
		else
		{
			btns[0].setVisible(questions[questionNumber].getAnswers().length > 0);
			btns[1].setVisible(questions[questionNumber].getAnswers().length > 1);
			btns[2].setVisible(questions[questionNumber].getAnswers().length > 2);
			btns[3].setVisible(questions[questionNumber].getAnswers().length > 3);
			btns[4].setVisible(questions[questionNumber].getAnswers().length > 4);
			btns[5].setVisible(questions[questionNumber].getAnswers().length > 5);
			for (int i = 0; i < questions[questionNumber].getAnswers().length; i++)
			{
				btns[i].setEditable(false);
				btns[i].setText(questions[questionNumber].getAnswers()[i].getText());
				btns[i].setFont(new Font(btns[i].getFont().getFontName(), btns[i].getFont().getStyle(), questions[questionNumber].getAnswers()[i].getFont()
						.getSize()));
				btns[i].setClicked(false);
				btns[i].setDisabled(false);
			}
		}
		switch (questions[questionNumber].getType())
		{
			case PickOne:
				question_1.setDisabledBackground(theme.pickOneQuestionBackground);
				question_1.setDisabledFrame(theme.pickOneQuestionFrame);
				question_1.setDisabledForeground(theme.pickOneQuestionForeground);

				for (ButtonX answer : btns)
				{
					answer.setNormalBackground(theme.pickOneAnswersBackground);
					answer.setNormalFrame(theme.pickOneAnswersFrame);
					answer.setNormalForeground(theme.pickOneAnswersForeground);
				}
				break;
			case SelectSome:
				question_1.setDisabledBackground(theme.selectSomeQuestionBackground);
				question_1.setDisabledFrame(theme.selectSomeQuestionFrame);
				question_1.setDisabledForeground(theme.selectSomeQuestionForeground);

				for (ButtonX answer : btns)
				{
					answer.setNormalBackground(theme.selectSomeAnswersBackground);
					answer.setNormalFrame(theme.selectSomeAnswersFrame);
					answer.setNormalForeground(theme.selectSomeAnswersForeground);
				}
				break;
			case EnterText:
				question_1.setDisabledBackground(theme.enterTextQuestionBackground);
				question_1.setDisabledFrame(theme.enterTextQuestionFrame);
				question_1.setDisabledForeground(theme.enterTextQuestionForeground);

				for (ButtonX answer : btns)
				{
					answer.setNormalBackground(theme.enterTextAnswersBackground);
					answer.setNormalFrame(theme.enterTextAnswersFrame);
					answer.setNormalForeground(theme.enterTextAnswersForeground);
				}
				break;
		}
		timer.setNormalBackground(theme.specialButtonsBackground);
		timer.setNormalFrame(theme.specialButtonsFrame);
		timer.setNormalForeground(theme.specialButtonsForeground);
		next.setNormalBackground(theme.specialButtonsBackground);
		next.setNormalFrame(theme.specialButtonsFrame);
		next.setNormalForeground(theme.specialButtonsForeground);
		finish.setNormalBackground(theme.specialButtonsBackground);
		finish.setNormalFrame(theme.specialButtonsFrame);
		finish.setNormalForeground(theme.specialButtonsForeground);
		skip.setNormalBackground(theme.specialButtonsBackground);
		skip.setNormalFrame(theme.specialButtonsFrame);
		skip.setNormalForeground(theme.specialButtonsForeground);

		if (!skipped.contains(questionNumber))
			lastNotSkipped = questionNumber;
		selectedAnswers = new boolean[questions[questionNumber].getAnswers().length];
		question_1.setFont(new Font(question_1.getFont().getFontName(), question_1.getFont().getStyle(), questions[questionNumber].getFont().getSize()));
		question_1.setText(questions[questionNumber].getText());

		next.setVisible(!(questionNumber >= questions.length - 1));
		skip.setDisabled(questionNumber >= questions.length - 1);
		finish.setVisible(questionNumber >= questions.length - 1);

		updateQuestionsList();
	}
}
