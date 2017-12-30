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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import ru.alexandrdv.components.ButtonX;
import ru.alexandrdv.schooltester.util.Logger;
import ru.alexandrdv.schooltester.util.MessageSystem;
import ru.alexandrdv.schooltester.util.Question;
import ru.alexandrdv.schooltester.util.Question.QuestionType;

/**
 * Main
 * 
 * @author AlexandrDV
 * @version 4.3.6a
 *
 */
public class Main
{
	public static final String version = "4.3.5a";
	public static final String authors = "AlexandrDV";
	public static final String programName = "SchoolTester v" + version + " by " + authors;
	public static final MessageSystem msgSys = new MessageSystem("en_uk");
	public static final Logger logger = new Logger(programName);
	private boolean paused = false, canPause, indicateAnswerQuality, indicateAnswersQuality;
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
	private ButtonX info_1;
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
	private int lastNotSkipped = 0;
	private boolean canGoToAllQuestions;

	class AnswerToQ
	{
		String s;
		boolean[] b;
		int n;
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

	/**
	 * The main method of class Main
	 * 
	 * @param args
	 */
	/*
	 * public static void main(String[] args) { new StartBlank(args); }
	 */

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
	public Main(Rectangle parent, String testName, Question[] questions, String _class, String surname, String name, String secondName, int maxTime,
			boolean canPause, boolean indicateAnswerQuality, boolean indicateAnswersQuality, boolean canSkip, boolean canGoToAllQuestions,
			boolean pauseOnUnfocus)
	{
		this.indicateAnswersQuality = indicateAnswersQuality;
		this.indicateAnswerQuality = indicateAnswerQuality;
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
		window.setSize(451, 664);
		window.setLocation(parent.x + parent.width / 2 - window.getWidth() / 2, parent.y + parent.height / 2 - window.getHeight() / 2);
		window.setTitle(programName);
		ArrayList<Image> icons = new ArrayList<>();
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

		JMenuBar menuBar = new JMenuBar();
		window.setJMenuBar(menuBar);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmUsersManual = new JMenuItem("Users manual");
		mnHelp.add(mntmUsersManual);

		JMenuItem mntmPrivatePolicy = new JMenuItem("Private Policy");
		mnHelp.add(mntmPrivatePolicy);
		window.getContentPane().setLayout(new BorderLayout(0, 0));
		timer = new ButtonX(toSize(maxTime / 60, 2) + ":" + toSize(maxTime % 60, 2), true, new boolean[] { false, false, false, false });
		timer.setNormalColor(new Color(173, 255, 47));

		JPanel content = new JPanel();
		window.getContentPane().add(content);
		content.setBackground(FXFrame.theme.windowBackground);
		content.setLayout(null);
		question_1 = new ButtonX("", true, new boolean[] { true, false, false, true });
		question_1.setEnabled(false);
		question_1.setFont(new Font("Comic Sans Ms", 0, 16));
		question_1.setBounds(5, 5, 424, 60);
		content.add(question_1);

		ButtonX btn0 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[0] = btn0;
		content.add(btn0);

		btn0.setFont(new Font("Comic Sans Ms", 0, 16));
		// btn0.setTextColor(Color.black);
		btn0.setBounds(20, 70, 393, 60);

		ButtonX btn1 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[1] = btn1;
		content.add(btn1);

		btn1.setFont(new Font("Comic Sans Ms", 0, 16));
		// btn1.setTextColor(Color.black);
		btn1.setBounds(20, 135, 393, 60);

		ButtonX btn2 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[2] = btn2;
		content.add(btn2);

		btn2.setFont(new Font("Comic Sans Ms", 0, 16));
		// btn2.setTextColor(Color.black);
		btn2.setBounds(20, 200, 393, 60);

		ButtonX btn3 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[3] = btn3;
		content.add(btn3);

		btn3.setFont(new Font("Comic Sans Ms", 0, 16));
		// btn3.setTextColor(Color.black);
		btn3.setBounds(20, 265, 393, 60);

		ButtonX btn4 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[4] = btn4;
		content.add(btn4);

		btn4.setFont(new Font("Comic Sans Ms", 0, 16));
		// btn4.setTextColor(Color.black);
		btn4.setBounds(20, 330, 393, 60);

		ButtonX btn5 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[5] = btn5;
		content.add(btn5);

		btn5.setFont(new Font("Comic Sans Ms", 0, 16));
		// btn5.setTextColor(Color.black);
		btn5.setBounds(20, 395, 393, 60);

		info_1 = new ButtonX("", true, new boolean[] { false, false, false, false });
		info_1.setNormalColor(Color.WHITE);
		info_1.setBackground(Color.WHITE);
		content.add(info_1);

		info_1.setFont(new Font("Arial Bold", 1, 16));
		// info_1.setTextColor(Color.black);
		info_1.setBounds(157, 460, 120, 42);
		info_1.setRounding(10);
		info_1.addActionListener(e ->
		{
			frame = new JFrame();
			frame.setUndecorated(true);
			int width = 120, height = 42;
			frame.setSize(width, (!canGoToAllQuestions ? skipped.size() : questions.length) * height);
			frame.setLocation(window.getX() + window.getWidth() / 2 - frame.getWidth() / 2, window.getY() + 30 + info_1.getY() + info_1.getHeight());
			frame.setVisible(true);
			frame.setLayout(null);
			for (int i = 0, previous = 0; i < questions.length; i++)
				if (canGoToAllQuestions ? true : skipped.contains(i))
				{
					ButtonX buttonX = new ButtonX(i + 1 + "/" + questions.length, true, new boolean[] { false, false, false, false });
					if (skipped.contains(i))
					{
						buttonX.setNormalColor(FXFrame.theme.questionSelectSkippedBackground);
						buttonX.setNormalFrameColor(FXFrame.theme.questionSelectSkippedFrame);
						buttonX.setForeground(FXFrame.theme.questionSelectSkippedForeground);
					}
					else
					{
						buttonX.setNormalColor(FXFrame.theme.questionSelectNormalBackground);
						buttonX.setNormalFrameColor(FXFrame.theme.questionSelectNormalFrame);
						buttonX.setForeground(FXFrame.theme.questionSelectNormalForeground);
					}

					int q = i;
					buttonX.addActionListener(event ->
					{
						int prev = questionNumber;
						questionNumber = q;
						openQuestion(info_1, prev);
						frame.setVisible(false);
					});
					buttonX.setSize(width, height);
					buttonX.setLocation(0, previous * height);
					buttonX.setRounding(10);
					frame.add(buttonX);
					previous++;
				}
		});

		next = new ButtonX("", true, new boolean[] { false, false, false, false });
		next.setRounding(10);
		next.setNormalColor(new Color(204, 255, 51));
		next.setFont(new Font("Comic Sans Ms", 0, 16));
		// next.setTextColor(Color.black);
		next.setBounds(293, 460, 120, 42);
		next.addActionListener(e -> next());
		content.add(next);

		finish = new ButtonX("", true, new boolean[] { false, false, false, false });
		finish.setRounding(10);
		finish.setNormalColor(new Color(204, 255, 51));
		finish.setFont(new Font("Comic Sans Ms", 0, 16));
		// finish.setTextColor(Color.black);
		finish.setBounds(293, 460, 120, 42);
		finish.addActionListener(e -> finish(window, _class, surname, name, secondName));
		content.add(finish);
		finish.setVisible(false);

		skip = new ButtonX("", true, new boolean[] { false, false, false, false });
		skip.setRounding(10);
		skip.setNormalColor(new Color(204, 255, 51));
		skip.setFont(new Font("Comic Sans Ms", 0, 16));
		// skip.setTextColor(Color.black);
		skip.setBounds(20, 510, 393, 42);
		skip.addActionListener(e -> skip());
		content.add(skip);
		skip.setEnabled(canSkip);

		content.add(timer);

		timer.setFont(new Font("Arial Bold", 1, 16));
		// timer.setTextColor(Color.black);
		timer.setBounds(20, 460, 120, 42);
		timer.setRounding(10);
		timer.addActionListener(e -> pause());

		window.addKeyListener(new KeyListener()
		{
			class ImageSelection implements Transferable
			{
				private Image image;

				public ImageSelection(Image image)
				{
					this.image = image;
				}

				// Returns supported flavors
				public DataFlavor[] getTransferDataFlavors()
				{
					return new DataFlavor[] { DataFlavor.imageFlavor };
				}

				// Returns true if flavor is supported
				public boolean isDataFlavorSupported(DataFlavor flavor)
				{
					return DataFlavor.imageFlavor.equals(flavor);
				}

				// Returns image
				public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException
				{
					if (!DataFlavor.imageFlavor.equals(flavor))
					{
						throw new UnsupportedFlavorException(flavor);
					}
					return image;
				}
			}

			@Override
			public void keyTyped(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_PRINTSCREEN)
					img();
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_PRINTSCREEN)
					img();
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_PRINTSCREEN)
					img();
			}

			public void img()
			{
				Timer timer = new Timer(100, e -> img2());
				timer.setRepeats(false);
				timer.start();
			}

			public void img2()
			{
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(new ImageSelection(new ImageIcon().getImage()), null);
				JOptionPane.showMessageDialog(null, "Key printscreen was clicked!");
			}
		});
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
		openQuestion(info_1, questionNumber - 1);

		if (indicateAnswerQuality)
		{
			panel = new JPanel();
			panel.setBounds(20, 560, 393, 42);
			content.add(panel);
			if (indicateAnswersQuality)
			{
				panel.setLayout(new GridLayout(1, questions.length, 2, 2));
				for (int i = 0; i < questions.length; i++)
					panel.add(new JPanel());
			}
		}
		changeLanguage();
	}

	public void pause()
	{
		if (Main.this.canPause && toPauseTime <= 0)
		{
			question_1.setVisible(paused);
			next.setVisible(paused);
			skip.setVisible(paused);
			info_1.setVisible(paused);
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
		handleQuestion();
		openQuestion(info_1, questionNumber - 1);
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
		openQuestion(info_1, questionNumber - 1);
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
		handleQuestion();
		paused = true;
		showResult(window, _class, surname, name, secondName);
		window.setVisible(false);
		Logger.exit(0);
	}

	public void handleQuestion()
	{
		int[] res = _handleQuestion();
		int minResult = res[0], questionResult = res[1], maxResult = res[2];

		if (questionResult > minResult || questionResult == maxResult)
			rightAnswers++;
		if (questionResult == maxResult)
			perfectAnswers++;

		if (indicateAnswerQuality)
		{
			System.out.println(indicateAnswerQuality);
			JPanel panel = indicateAnswersQuality ? (JPanel) this.panel.getComponent(questionNumber) : this.panel;
			if (questionResult == maxResult)
				panel.setBackground(Color.green);
			else if (questionResult > minResult)
				panel.setBackground(Color.yellow);
			else panel.setBackground(Color.red);
		}
		result += questionResult;
		goNext();
	}

	public int[] _handleQuestion()
	{
		Question question = questions[questionNumber];
		int questionResult = question.getAward();
		for (int i = 0; i < question.getAnswers().length; i++)
			if (questions[questionNumber].getType() == QuestionType.EnterText)
			{
				String userAnswer = btns[0].getText(), curAnswer = question.getAnswers()[i].getText();
				if (questions[questionNumber].isIgnoreCase())
				{
					userAnswer = userAnswer.toLowerCase();
					curAnswer = curAnswer.toLowerCase();
				}
				for (char c : questions[questionNumber].getIgnoredCharacters().toCharArray())
				{
					userAnswer = userAnswer.replace(c + "", "");
					curAnswer = curAnswer.replace(c + "", "");
				}
				if (userAnswer.equals(curAnswer))
					questionResult += question.getAnswers()[i].getAward();
			}
			else if (selectedAnswers[i])
				questionResult += question.getAnswers()[i].getAward();
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
		text += msgSys.getMsg("rightAnswersAmount") + ": " + rightAnswers + "\n";
		text += msgSys.getMsg("perfectAnswersAmount") + ": " + perfectAnswers + "\n";
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
		JOptionPane j = new JOptionPane(text);
		JDialog d = j.createDialog(null, programName);
		d.setLocationRelativeTo(window);
		d.setVisible(true);
	}

	/**
	 * 
	 * @param i
	 * @param size
	 * @return
	 */
	public String toSize(int i, int size)
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
	public void openQuestion(ButtonX info, int prev)
	{
		if (prev >= 0)
			if (!skipped.contains(prev))
			{
				answers[prev].b = new boolean[questions[prev].getAnswers().length];
				for (int i = 0; i < answers[prev].b.length; i++)
				{
					if (btns[i].isSelected())
						answers[prev].n = i;
					answers[prev].b[i] = btns[i].isSelected();
				}
				answers[prev].s = btns[0].getText();
			}
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
				btns[i].setText(questions[questionNumber].getAnswers()[i].getText());
				btns[i].setFont(new Font(btns[i].getFont().getFontName(), btns[i].getFont().getStyle(), questions[questionNumber].getAnswers()[i].getFont()
						.getSize()));
				btns[i].setClicked(false);
			}
		}
		switch (questions[questionNumber].getType())
		{
			case PickOne:
				question_1.setDisabledColor(FXFrame.theme.pickOneQuestionBackground);
				question_1.setDisabledFrameColor(FXFrame.theme.pickOneQuestionFrame);
				question_1.setDisabledTextColor(FXFrame.theme.pickOneQuestionForeground);

				for (ButtonX answer : btns)
				{
					answer.setNormalColor(FXFrame.theme.pickOneAnswersBackground);
					answer.setNormalFrameColor(FXFrame.theme.pickOneAnswersFrame);
					answer.setForeground(FXFrame.theme.pickOneAnswersForeground);
				}
				break;
			case SelectSome:
				question_1.setDisabledColor(FXFrame.theme.selectSomeQuestionBackground);
				question_1.setDisabledFrameColor(FXFrame.theme.selectSomeQuestionFrame);
				question_1.setDisabledTextColor(FXFrame.theme.selectSomeQuestionForeground);

				for (ButtonX answer : btns)
				{
					answer.setNormalColor(FXFrame.theme.selectSomeAnswersBackground);
					answer.setNormalFrameColor(FXFrame.theme.selectSomeAnswersFrame);
					answer.setForeground(FXFrame.theme.selectSomeAnswersForeground);
				}
				break;
			case EnterText:
				question_1.setDisabledColor(FXFrame.theme.enterTextQuestionBackground);
				question_1.setDisabledFrameColor(FXFrame.theme.enterTextQuestionFrame);
				question_1.setDisabledTextColor(FXFrame.theme.enterTextQuestionForeground);

				for (ButtonX answer : btns)
				{
					answer.setNormalColor(FXFrame.theme.enterTextAnswersBackground);
					answer.setNormalFrameColor(FXFrame.theme.enterTextAnswersFrame);
					answer.setForeground(FXFrame.theme.enterTextAnswersForeground);
				}
				break;
		}
		timer.setNormalColor(FXFrame.theme.specialButtonsBackground);
		timer.setNormalFrameColor(FXFrame.theme.specialButtonsFrame);
		timer.setForeground(FXFrame.theme.specialButtonsForeground);
		info_1.setNormalColor(FXFrame.theme.specialButtonsBackground);
		info_1.setNormalFrameColor(FXFrame.theme.specialButtonsFrame);
		info_1.setForeground(FXFrame.theme.specialButtonsForeground);
		next.setNormalColor(FXFrame.theme.specialButtonsBackground);
		next.setNormalFrameColor(FXFrame.theme.specialButtonsFrame);
		next.setForeground(FXFrame.theme.specialButtonsForeground);
		finish.setNormalColor(FXFrame.theme.specialButtonsBackground);
		finish.setNormalFrameColor(FXFrame.theme.specialButtonsFrame);
		finish.setForeground(FXFrame.theme.specialButtonsForeground);
		skip.setNormalColor(FXFrame.theme.specialButtonsBackground);
		skip.setNormalFrameColor(FXFrame.theme.specialButtonsFrame);
		skip.setForeground(FXFrame.theme.specialButtonsForeground);

		if (!skipped.contains(questionNumber))
			lastNotSkipped = questionNumber;
		selectedAnswers = new boolean[questions[questionNumber].getAnswers().length];
		info.setText((questionNumber + 1) + "/" + questions.length);
		question_1.setFont(new Font(question_1.getFont().getFontName(), question_1.getFont().getStyle(), questions[questionNumber].getFont().getSize()));
		question_1.setText(questions[questionNumber].getText());

		next.setVisible(!(questionNumber >= questions.length - 1));
		skip.setEnabled(!(questionNumber >= questions.length - 1));
		finish.setVisible(questionNumber >= questions.length - 1);
	}
}
