package ru.alexanderdv.schooltester.utilities;

import java.awt.Image;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import javax.swing.ImageIcon;

import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.simpleutilities.SystemUtils;

/**
 * Logger - the class extending by "java.util.logging.Logger" used to convenient
 * interaction with logging
 * 
 * @author AlexandrDV/AlexanderDV
 * @version 5.9.0a
 */
public class Logger extends java.util.logging.Logger
{
	public static final Logger mainLogger = new Logger("Main logger of SchoolTester");
	private static String log = "";
	public static final ArrayList<Image> defaultIcons = new ArrayList<Image>();
	static
	{
		defaultIcons.add(
				new ImageIcon(Logger.class.getResource(Main.resourcesPath + "/images" + "/Icon16x.png")).getImage());
		defaultIcons.add(
				new ImageIcon(Logger.class.getResource(Main.resourcesPath + "/images" + "/Icon32x.png")).getImage());
		defaultIcons.add(
				new ImageIcon(Logger.class.getResource(Main.resourcesPath + "/images" + "/Icon48x.png")).getImage());
	}

	/**
	 * Constructor of the "Logger" class
	 * 
	 * @param name
	 *            - the name of "Logger"
	 */
	public Logger(String name)
	{
		super(name, null);
		setParent(Logger.getGlobal());
		setLevel(Level.ALL);
	}

	@Override
	public void log(LogRecord logRecord)
	{
		logRecord.setMessage("[" + getName() + "] " + logRecord.getMessage());
		log += logRecord.getLevel() + " " + logRecord.getMillis() + " " + logRecord.getMessage() + "\n";
		getParent().log(logRecord);
	}

	// /**
	// * Exits from program with code - 'code' and saves logs.
	// *
	// * @param code
	// * - the code of exit
	// */
	// public static void exit(int code)
	// {
	// // Save log to file if log isn't empty
	// if (!log.equals(""))
	// FXFrame.writeFile(new File("CLogs/" +
	// Calendar.getInstance().getTimeInMillis() + ".log"), log);
	// System.exit(code);// Call exit function
	// }
	//
	// /**
	// * Exits from program with code of 'code' and saves logs.
	// *
	// * @param code
	// * - the "ExitCode" of exit
	// */
	// public static void exit(ExitCodes code)
	// {
	// exit(code.getCode());// Gets code of 'code' and call exit(int)
	// }
	/**
	 * Saves logs.
	 */
	public static void saveLogs()
	{
		// Save log to file if log isn't empty
		if (!log.equals(""))
			SystemUtils.writeFile(new File("CLogs/" + Calendar.getInstance().getTimeInMillis() + ".log"), log,
					"Cp1251");
	}

	// /**
	// * Creates "JDialog" with message - 'msg', with type of message -
	// 'messageType', with type of option - 'optionType' in the middle of
	// 'Component'
	// *
	// * @param comp
	// * - the component in the middle of them "JDialog" will be created
	// * @param msg
	// * - the message of created "JDialog"
	// * @param messageType
	// * - the messageType of created "JDialog"
	// * @param optionType
	// * - the optionType of created "JDialog"
	// */
	// public static void showMsgDialog(Component comp, Object msg, int messageType,
	// int optionType)
	// {
	// JOptionPane pane = new JOptionPane(msg, messageType, optionType);// Create
	// "JOoptionPane" with props - 'msg', 'messageType' and 'optionType'
	// JDialog dialog = pane.createDialog(Main.program);// Create "JDialog" with
	// default title of program from "JOptionPane"
	// dialog.setIconImages(defaultIcons);// Set icon to default icon of program
	// dialog.setLocationRelativeTo(comp);// Set position to middle of component
	// dialog.setVisible(true);// Make "JDialog" visiblestage = primaryStage;
	// }
	//
	// /**
	// * Creates "JDialog" with message - 'msg', with type of message -
	// 'messageType', with type of option - 'optionType' in the middle of 'stage'
	// *
	// * @param stage
	// * - the stage in the middle of them "JDialog" will be created
	// * @param msg
	// * - the message of created "JDialog"
	// * @param messageType
	// * - the messageType of created "JDialog"
	// * @param optionType
	// * - the optionType of created "JDialog"
	// */
	// public static void showMsgDialog(Stage stage, Object msg, int messageType,
	// int optionType)
	// {
	// Component component = new Panel();// Create component to middle of created
	// "JDialog"
	// component.setBounds((int) stage.getX(), (int) stage.getY(), (int)
	// stage.getWidth(), (int) stage.getHeight());// Change bounds of component to
	// bounds of
	// // 'stage'
	// showMsgDialog(component, msg, messageType, optionType);// Shows msgDialog
	// }

	/**
	 * Exit codes enum, used for fast exit code input
	 * 
	 * @author AlexandrDV/AlexanderDV
	 * @version 5.0.0a
	 */
	public static enum ExitCodes
	{
		ServerNotExists(-2),
		UnknownError(-1),
		UserCloseProgram(0),
		KeyIsBad(1),
		YouAreInBlacklist(2),
		NotVerified(3),
		Update(10),
		WrongSyntax(20),
		TestSyntaxLanguageIsNotSupported(21),
		TestNotHaveMainValues(22),
		TestNotExists(23),
		TestNotHaveQuestions(24),
		TestNotHaveSyntaxLanguage(25),
		TestAwardsOfQuestionsInGroupNotMatch(26);
		private int code;

		private ExitCodes(int code)
		{
			this.code = code;
		}

		public int getCode()
		{
			return code;
		}

	}

	// Logging of System.out and System.err
	static
	{
		System.setOut(new PrintStream(System.out)
		{
			boolean out = false;

			/**
			 * Prints a boolean value. The string produced by <code>{@link
			 * java.lang.String#valueOf(boolean)}</code> is translated into bytes according
			 * to the platform's default character encoding, and these bytes are written in
			 * exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param b
			 *            The <code>boolean</code> to be printed
			 */
			@Override
			public void print(boolean b)
			{
				if (out)
					super.print(b);
				mainLogger.log(Level.INFO, "" + b);
			}

			/**
			 * Prints a character. The character is translated into one or more bytes
			 * according to the platform's default character encoding, and these bytes are
			 * written in exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param c
			 *            The <code>char</code> to be printed
			 */
			@Override
			public void print(char c)
			{
				if (out)
					super.print(c);
				mainLogger.log(Level.INFO, "" + c);
			}

			/**
			 * Prints an integer. The string produced by <code>{@link
			 * java.lang.String#valueOf(int)}</code> is translated into bytes according to
			 * the platform's default character encoding, and these bytes are written in
			 * exactly the manner of the <code>{@link #write(int)}</code> method.
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
				mainLogger.log(Level.INFO, "" + i);
			}

			/**
			 * Prints a long integer. The string produced by <code>{@link
			 * java.lang.String#valueOf(long)}</code> is translated into bytes according to
			 * the platform's default character encoding, and these bytes are written in
			 * exactly the manner of the <code>{@link #write(int)}</code> method.
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
				mainLogger.log(Level.INFO, "" + l);
			}

			/**
			 * Prints a floating-point number. The string produced by <code>{@link
			 * java.lang.String#valueOf(float)}</code> is translated into bytes according to
			 * the platform's default character encoding, and these bytes are written in
			 * exactly the manner of the <code>{@link #write(int)}</code> method.
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
				mainLogger.log(Level.INFO, "" + f);
			}

			/**
			 * Prints a double-precision floating-point number. The string produced by
			 * <code>{@link java.lang.String#valueOf(double)}</code> is translated into
			 * bytes according to the platform's default character encoding, and these bytes
			 * are written in exactly the manner of the <code>{@link
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
				mainLogger.log(Level.INFO, "" + d);
			}

			/**
			 * Prints an array of characters. The characters are converted into bytes
			 * according to the platform's default character encoding, and these bytes are
			 * written in exactly the manner of the <code>{@link #write(int)}</code> method.
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
				mainLogger.log(Level.INFO, "" + new String(s));
			}

			/**
			 * Prints a string. If the argument is <code>null</code> then the string
			 * <code>"null"</code> is printed. Otherwise, the string's characters are
			 * converted into bytes according to the platform's default character encoding,
			 * and these bytes are written in exactly the manner of the
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
				mainLogger.log(Level.INFO, "" + s);
			}

			/**
			 * Prints an object. The string produced by the <code>{@link
			 * java.lang.String#valueOf(Object)}</code> method is translated into bytes
			 * according to the platform's default character encoding, and these bytes are
			 * written in exactly the manner of the <code>{@link #write(int)}</code> method.
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
				mainLogger.log(Level.INFO, "" + obj);
			}

			/* Methods that do terminate lines */

			/**
			 * Terminates the current line by writing the line separator string. The line
			 * separator string is defined by the system property
			 * <code>line.separator</code>, and is not necessarily a single newline
			 * character (<code>'\n'</code>).
			 */
			@Override
			public void println()
			{
				if (out)
					super.println();
				mainLogger.log(Level.INFO, "");
			}

			/**
			 * Prints a boolean and then terminate the line. This method behaves as though
			 * it invokes <code>{@link #print(boolean)}</code> and then
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
				mainLogger.log(Level.INFO, "" + x);
			}

			/**
			 * Prints a character and then terminate the line. This method behaves as though
			 * it invokes <code>{@link #print(char)}</code> and then
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
				mainLogger.log(Level.INFO, "" + x);
			}

			/**
			 * Prints an integer and then terminate the line. This method behaves as though
			 * it invokes <code>{@link #print(int)}</code> and then
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
				mainLogger.log(Level.INFO, "" + x);
			}

			/**
			 * Prints a long and then terminate the line. This method behaves as though it
			 * invokes <code>{@link #print(long)}</code> and then
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
				mainLogger.log(Level.INFO, "" + x);
			}

			/**
			 * Prints a float and then terminate the line. This method behaves as though it
			 * invokes <code>{@link #print(float)}</code> and then
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
				mainLogger.log(Level.INFO, "" + x);
			}

			/**
			 * Prints a double and then terminate the line. This method behaves as though it
			 * invokes <code>{@link #print(double)}</code> and then
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
				mainLogger.log(Level.INFO, "" + x);
			}

			/**
			 * Prints an array of characters and then terminate the line. This method
			 * behaves as though it invokes <code>{@link #print(char[])}</code> and then
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
				mainLogger.log(Level.INFO, "" + new String(x));
			}

			/**
			 * Prints a String and then terminate the line. This method behaves as though it
			 * invokes <code>{@link #print(String)}</code> and then
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
				mainLogger.log(Level.INFO, "" + x);
			}

			/**
			 * Prints an Object and then terminate the line. This method calls at first
			 * String.valueOf(x) to get the printed object's string value, then behaves as
			 * though it invokes <code>{@link #print(String)}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            The <code>Object</code> to be printed.
			 */
			@Override
			public void println(Object x)
			{
				if (out)
					super.println(x);
				mainLogger.log(Level.INFO, "" + x);
			}
		});
		System.setErr(new PrintStream(System.err)
		{
			boolean out = false;

			/**
			 * Prints a boolean value. The string produced by <code>{@link
			 * java.lang.String#valueOf(boolean)}</code> is translated into bytes according
			 * to the platform's default character encoding, and these bytes are written in
			 * exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param b
			 *            The <code>boolean</code> to be printed
			 */
			@Override
			public void print(boolean b)
			{
				if (out)
					super.print(b);
				mainLogger.log(Level.SEVERE, "" + b);
			}

			/**
			 * Prints a character. The character is translated into one or more bytes
			 * according to the platform's default character encoding, and these bytes are
			 * written in exactly the manner of the <code>{@link #write(int)}</code> method.
			 *
			 * @param c
			 *            The <code>char</code> to be printed
			 */
			@Override
			public void print(char c)
			{
				if (out)
					super.print(c);
				mainLogger.log(Level.SEVERE, "" + c);
			}

			/**
			 * Prints an integer. The string produced by <code>{@link
			 * java.lang.String#valueOf(int)}</code> is translated into bytes according to
			 * the platform's default character encoding, and these bytes are written in
			 * exactly the manner of the <code>{@link #write(int)}</code> method.
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
				mainLogger.log(Level.SEVERE, "" + i);
			}

			/**
			 * Prints a long integer. The string produced by <code>{@link
			 * java.lang.String#valueOf(long)}</code> is translated into bytes according to
			 * the platform's default character encoding, and these bytes are written in
			 * exactly the manner of the <code>{@link #write(int)}</code> method.
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
				mainLogger.log(Level.SEVERE, "" + l);
			}

			/**
			 * Prints a floating-point number. The string produced by <code>{@link
			 * java.lang.String#valueOf(float)}</code> is translated into bytes according to
			 * the platform's default character encoding, and these bytes are written in
			 * exactly the manner of the <code>{@link #write(int)}</code> method.
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
				mainLogger.log(Level.SEVERE, "" + f);
			}

			/**
			 * Prints a double-precision floating-point number. The string produced by
			 * <code>{@link java.lang.String#valueOf(double)}</code> is translated into
			 * bytes according to the platform's default character encoding, and these bytes
			 * are written in exactly the manner of the <code>{@link
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
				mainLogger.log(Level.SEVERE, "" + d);
			}

			/**
			 * Prints an array of characters. The characters are converted into bytes
			 * according to the platform's default character encoding, and these bytes are
			 * written in exactly the manner of the <code>{@link #write(int)}</code> method.
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
				mainLogger.log(Level.SEVERE, "" + new String(s));
			}

			/**
			 * Prints a string. If the argument is <code>null</code> then the string
			 * <code>"null"</code> is printed. Otherwise, the string's characters are
			 * converted into bytes according to the platform's default character encoding,
			 * and these bytes are written in exactly the manner of the
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
				mainLogger.log(Level.SEVERE, "" + s);
			}

			/**
			 * Prints an object. The string produced by the <code>{@link
			 * java.lang.String#valueOf(Object)}</code> method is translated into bytes
			 * according to the platform's default character encoding, and these bytes are
			 * written in exactly the manner of the <code>{@link #write(int)}</code> method.
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
				mainLogger.log(Level.SEVERE, "" + obj);
			}

			/* Methods that do terminate lines */

			/**
			 * Terminates the current line by writing the line separator string. The line
			 * separator string is defined by the system property
			 * <code>line.separator</code>, and is not necessarily a single newline
			 * character (<code>'\n'</code>).
			 */
			@Override
			public void println()
			{
				if (out)
					super.println();
				mainLogger.log(Level.SEVERE, "");
			}

			/**
			 * Prints a boolean and then terminate the line. This method behaves as though
			 * it invokes <code>{@link #print(boolean)}</code> and then
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
				mainLogger.log(Level.SEVERE, "" + x);
			}

			/**
			 * Prints a character and then terminate the line. This method behaves as though
			 * it invokes <code>{@link #print(char)}</code> and then
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
				mainLogger.log(Level.SEVERE, "" + x);
			}

			/**
			 * Prints an integer and then terminate the line. This method behaves as though
			 * it invokes <code>{@link #print(int)}</code> and then
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
				mainLogger.log(Level.SEVERE, "" + x);
			}

			/**
			 * Prints a long and then terminate the line. This method behaves as though it
			 * invokes <code>{@link #print(long)}</code> and then
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
				mainLogger.log(Level.SEVERE, "" + x);
			}

			/**
			 * Prints a float and then terminate the line. This method behaves as though it
			 * invokes <code>{@link #print(float)}</code> and then
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
				mainLogger.log(Level.SEVERE, "" + x);
			}

			/**
			 * Prints a double and then terminate the line. This method behaves as though it
			 * invokes <code>{@link #print(double)}</code> and then
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
				mainLogger.log(Level.SEVERE, "" + x);
			}

			/**
			 * Prints an array of characters and then terminate the line. This method
			 * behaves as though it invokes <code>{@link #print(char[])}</code> and then
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
				mainLogger.log(Level.SEVERE, "" + new String(x));
			}

			/**
			 * Prints a String and then terminate the line. This method behaves as though it
			 * invokes <code>{@link #print(String)}</code> and then
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
				mainLogger.log(Level.SEVERE, "" + x);
			}

			/**
			 * Prints an Object and then terminate the line. This method calls at first
			 * String.valueOf(x) to get the printed object's string value, then behaves as
			 * though it invokes <code>{@link #print(String)}</code> and then
			 * <code>{@link #println()}</code>.
			 *
			 * @param x
			 *            The <code>Object</code> to be printed.
			 */
			@Override
			public void println(Object x)
			{
				if (out)
					super.println(x);
				mainLogger.log(Level.SEVERE, "" + x);
			}
		});
	}
}
