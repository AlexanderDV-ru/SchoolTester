package ru.alexanderdv.schooltester.utilities;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.utilities.Logger.ExitCodes;

/**
 * Config - the class of configuration file parser
 * 
 * @author AlexandrDV/AlexanderDV
 * @version 5.8.0a
 *
 */
public class Config
{
	private static final MessageSystem msgSys=Main.msgSys;
	private static final Logger logger = Logger.mainLogger;
	private final Config parent;
	private File file;
	private final String fullPathInParents;
	private String configurationText;
	private String defaultColorType = "rgba";

	/**
	 * Creates new Config from file
	 * 
	 * @param file
	 *            - file using how configuration
	 */
	public Config(File file)
	{
		this.file = file;
		this.parent = null;
		this.fullPathInParents = null;
	}

	/**
	 * Creates new Config from text
	 * 
	 * @param text
	 *            - text using how configuration
	 */
	private Config(Config parent, String text, String pathInParent)
	{
		this.configurationText = text;
		this.parent = parent;
		this.fullPathInParents = (parent.getFullPathInParents() != null ? parent.getFullPathInParents() + ":" : "") + pathInParent;
	}

	/**
	 * Creates new Config from text
	 * 
	 * @param text
	 *            - text using how configuration
	 */
	public Config(String text)
	{
		this.configurationText = text;
		this.parent = null;
		this.fullPathInParents = null;
	}

	/**
	 * @return the file
	 */
	public File getFile()
	{
		return file;
	}

	public String getText()
	{
		return getText(false);
	}

	private void check(String path)
	{
		if (!hasValue(path))
		{
			try
			{
				throw new NullPointerException(msgSys.getMsg("valueNotExist").replace("%1",path).replace("%1",getFullPathInParents()));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				print(e.getMessage());
			}
			exit(ExitCodes.WrongSyntax);
		}
	}

	private String getObject(String text, String path, boolean deleteTabs)
	{
		String[] dirs = path.split(":");
		for (int i = 0; i < dirs.length; i++)
		{
			for (int j = 0; j < i; j++)
				dirs[i] = "\t" + dirs[i];
			dirs[i] = "\n" + dirs[i] + ":";
		}
		for (int i = 0; i < dirs.length; i++)
			text = text.substring(text.indexOf(dirs[i]) + dirs[i].length());
		text = text.substring(text.indexOf("\n") + "\n".length());
		String curDir = "\n";
		for (int i = 0; i < dirs.length; i++)
			curDir += "\t";
		String valueText = "";
		for (int i = 0; i < text.split("\n").length; valueText += (deleteTabs ? (text.split("\n")[i].substring(curDir.length() - 1)) : text.split("\n")[i])
				+ "\n", i++)
			if (!("\n" + text.split("\n")[i]).contains(curDir))
				break;
		return valueText;
	}

	public String getObject(String path, boolean deleteTabs)
	{
		return getObject(getText(), path, deleteTabs);
	}

	public Config getConfig(String path, boolean deleteTabs)
	{
		check(path);
		return new Config(this, getObject(getText(), path, deleteTabs), path);
	}

	/**
	 * Returns value from path in configuration file
	 * 
	 * @param path
	 *            - path to value in configuration file
	 * @return value at path in configuration file
	 */
	public String __getValue(String path)
	{
		String text = getText();
		String[] dirs = path.split(":");
		for (int i = 0; i < dirs.length; i++)
		{
			for (int j = 0; j < i; j++)
				dirs[i] = "\t" + dirs[i];
			dirs[i] = "\n" + dirs[i] + ":";
		}
		for (int i = 0; i < dirs.length; i++)
			text = text.substring(text.indexOf(dirs[i]) + dirs[i].length());
		if (text.contains("\n"))
			text = text.substring(0, text.indexOf("\n"));
		return text;
	}

	public String getValue(String path)
	{
		String text = "\n" + getText();
		String[] dirs = path.split(":");
		for (int i = 0; i < dirs.length - 1; i++)
			text = getObject(text, dirs[i], true);
		text = "\n" + text;
		text = text.substring(text.indexOf("\n" + dirs[dirs.length - 1] + ":") + ("\n" + dirs[dirs.length - 1] + ":").length());
		if (text.contains("\n"))
			text = text.substring(0, text.indexOf("\n"));
		return text;
	}

	/**
	 * Check and return true if configuration constrains value in path, else return false
	 * 
	 * @param path
	 *            - path to value in configuration file
	 * @return true if configuration has value in path, else false
	 */
	public boolean hasValue(String path)
	{
		String text = getText();
		text = "\n" + text;
		String[] dirs = path.split(":");
		for (int i = 0; i < dirs.length; i++)
		{
			for (int j = 0; j < i; j++)
				dirs[i] = "\t" + dirs[i];
			dirs[i] = "\n" + dirs[i] + ":";
		}
		String tabs = "";
		for (int i = 0; i < dirs.length; tabs += "\t", i++)
		{
			if (!text.contains(dirs[i]))
				return false;
			for (String line : text.substring(0, text.indexOf(dirs[i])).split("\n"))
				if (!line.contains(tabs) && line.contains(":"))
					return false;
			text = tabs + text.substring(text.indexOf(dirs[i]) + dirs[i].length());
			boolean b = false;
			String t = "";
			for (String s : text.split("\n"))
				if (!s.startsWith("//"))
				{
					if (b)
						if (!("\n" + s).contains("\n" + tabs + "\t"))
							break;
					t += (b ? "\n" : "") + s;
					b = true;
				}
			text = t;
			// System.out.println("t2: " + text);
		}
		if (text.contains("\n"))
			text = text.substring(0, text.indexOf("\n"));
		return true;
	}

	public String getValue(String path, String safeValue, boolean safety)
	{
		if (!safety)
			check(path);
		return hasValue(path) ? getValue(path) : safeValue;
	}

	public String getString(String path, String safeString, boolean safety)
	{
		if (!safety)
			check(path);
		return hasValue(path) ? getString(path) : safeString;
	}

	public double getDouble(String path, Double safeDouble, boolean safety)
	{
		if (!safety)
			check(path);
		return hasValue(path) ? getDouble(path) : safeDouble;
	}

	public int getInteger(String path, Integer safeInteger, boolean safety)
	{
		if (!safety)
			check(path);
		return hasValue(path) ? getInteger(path) : safeInteger;
	}

	public int getTime(String path, Integer safeTime, boolean safety)
	{
		if (!safety)
			check(path);
		return hasValue(path) ? getTime(path) : safeTime;
	}

	public boolean getBoolean(String path, String language, Boolean safeBoolean, boolean safety)
	{
		if (!safety)
			check(path);
		return hasValue(path) ? getBoolean(path, language) : safeBoolean;
	}

	public boolean getBoolean(String path, Boolean safeBoolean, boolean safety)
	{
		if (!safety)
			check(path);
		return hasValue(path) ? getBoolean(path) : safeBoolean;
	}

	public Color getColor(String path, String type, Color safeColor, boolean safety)
	{
		if (!safety)
			check(path);
		return hasValue(path) ? getColor(path, type) : safeColor;
	}

	public Color getColor(String path, Color safeColor, boolean safety)
	{
		if (!safety)
			check(path);
		return hasValue(path) ? getColor(path, getDefaultColorType()) : safeColor;
	}

	private String getString(String path)
	{
		String text1 = getValue(path);
		if (text1.indexOf("\"") == -1)
		{
			print(msgSys.getMsg("valueMustBeTypeOf").replace("%1",text1).replace("%2",path).replace("%3",getFullPathInParents()).replace("%4","String"));
			exit(ExitCodes.WrongSyntax);
		}
		if (text1.indexOf("\"") == text1.lastIndexOf("\""))
		{
			print(msgSys.getMsg("quoteMustBeDouble").replace("%1",text1));
			exit(ExitCodes.WrongSyntax);
		}
		return text1.substring(text1.indexOf("\"") + 1, text1.lastIndexOf("\""));
	}

	private double getDouble(String path)
	{
		String text1 = getValue(path);
		String text2 = text1;
		for (char c : "-+0123456789.,\t\n ".toCharArray())
			text2 = text2.replace(c + "", "");
		if (!text2.equals(""))
		{
			print(msgSys.getMsg("valueMustBeTypeOf").replace("%1",text1).replace("%2",path).replace("%3",getFullPathInParents()).replace("%4","Double"));
			exit(ExitCodes.WrongSyntax);
		}
		return parseD(text1);
	}

	private int getInteger(String path)
	{
		String text1 = getValue(path);
		String text2 = text1;
		for (char c : "-+0123456789\t\n ".toCharArray())
			text2 = text2.replace(c + "", "");
		if (!text2.equals(""))
		{
			print(msgSys.getMsg("valueMustBeTypeOf").replace("%1",text1).replace("%2",path).replace("%3",getFullPathInParents()).replace("%4","Integer"));
			exit(ExitCodes.WrongSyntax);
		}
		return parseI(text1);
	}

	private boolean getBoolean(String path, String language)
	{
		String value = getValue(path).replace(" ", "").replace("\t", "");
		if (value.equals(MessageSystem.getMsg("Config.boolean.true0", language)))
			return true;
		if (value.equals(MessageSystem.getMsg("Config.boolean.true1", language)))
			return true;
		if (value.equals(MessageSystem.getMsg("Config.boolean.true2", language)))
			return true;
		if (value.equals(MessageSystem.getMsg("Config.boolean.true3", language)))
			return true;

		if (value.equals(MessageSystem.getMsg("Config.boolean.false0", language)))
			return false;
		if (value.equals(MessageSystem.getMsg("Config.boolean.false1", language)))
			return false;
		if (value.equals(MessageSystem.getMsg("Config.boolean.false2", language)))
			return false;
		if (value.equals(MessageSystem.getMsg("Config.boolean.false3", language)))
			return false;

			print(msgSys.getMsg("valueMustBeTypeOf").replace("%1",value).replace("%2",path).replace("%3",getFullPathInParents()).replace("%4","Boolean"));
		exit(ExitCodes.WrongSyntax);
		return false;
	}

	private boolean getBoolean(String path)
	{
		return getBoolean(path, "en_uk");
	}

	private int getTime(String path)
	{
		String text1 = getValue(path);
		String text2 = text1;
		for (char c : "-+0123456789:\t ".toCharArray())
			text2 = text2.replace(c + "", "");
		if (!text2.equals(""))
		{
			print(msgSys.getMsg("valueMustBeTypeOf").replace("%1",text1).replace("%2",path).replace("%3",getFullPathInParents()).replace("%4","Time"));
			exit(ExitCodes.WrongSyntax);
		}
		int time = 0;
		for (int i = 0; i < text1.split(":").length; i++)
			time += parseI(text1.split(":")[i]) * Math.pow(60, text1.split(":").length - 1 - i);
		return time;
	}

	private Color getColor(String path, String type)
	{
		String[] chars = getValue(path).replace(",", ":").split(":");
		if ((type.length() >= 3 ? (!type.contains("r") || !type.contains("g") || !type.contains("b")) : true) || type.length() == 4 ? (!type.contains("a"))
				: false)
		{
			print(msgSys.getMsg("colorTypeIsWrong"));
			exit(ExitCodes.WrongSyntax);
		}
		if (chars.length != 3 && chars.length != 4)
		{
			print(msgSys.getMsg("valueMustContainsComma").replace("%1",getValue(path)).replace("%2",path));
			exit(ExitCodes.WrongSyntax);
		}

		return new Color(parseI(chars[type.indexOf('r')]), parseI(chars[type.indexOf('g')]), parseI(chars[type.indexOf('b')]), type.indexOf('a') != -1 ? parseI(
				chars[type.indexOf('a')]) : 255);
	}

	/**
	 * Prints Object 'object' to console and show Object 'object' in {@link javax.swing.JDialog}
	 * 
	 * @param object
	 */
	public static void print(Object object)
	{
		logger.log(Level.INFO, object.toString());
		JOptionPane.showMessageDialog(null, object, Main.program, 0);
	}

	/**
	 * Returns String 'toParse' parsed to Integer
	 * 
	 * @param toParse
	 * @return Integer parsed from String 'toParse'
	 */
	public static int parseI(String toParse)
	{
		toParse = toParse.replace(" ", "").replace("\t", "").replace("\n", "");
		String num = "";
		if (toParse.startsWith("-"))
		{
			toParse = toParse.substring(1);
			num = "-";
		}
		if (toParse.startsWith("+"))
			toParse = toParse.substring(1);
		boolean hasDot = false;
		for (char c : toParse.toCharArray())
			if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9')
				num += c;
			else if (c == '.' || c == ',')
				if (!hasDot)
				{
					num += c;
					hasDot = true;
				}
				else return Integer.parseInt(num);
			else return Integer.parseInt(num);
		return Integer.parseInt(num);
	}

	/**
	 * Returns String 'toParse' parsed to Double
	 * 
	 * @param toParse
	 * @return Double parsed from String 'toParse'
	 */
	public static double parseD(String toParse)
	{
		toParse = toParse.replace(" ", "").replace("\t", "").replace("\n", "");
		String num = "";
		if (toParse.startsWith("-"))
		{
			toParse = toParse.substring(1);
			num = "-";
		}
		if (toParse.startsWith("+"))
			toParse = toParse.substring(1);
		boolean hasDot = false;
		for (char c : toParse.toCharArray())
			if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9')
				num += c;
			else if (c == '.' || c == ',')
				if (!hasDot)
				{
					num += c;
					hasDot = true;
				}
				else return Double.parseDouble(num);
			else return Double.parseDouble(num);
		return Double.parseDouble(num);
	}

	public String getDefaultColorType()
	{
		return defaultColorType;
	}

	public void setDefaultColorType(String defaultColorType)
	{
		this.defaultColorType = defaultColorType;
	}

	/**
	 * Returns text of configuration file
	 * 
	 * @param read
	 *            - if is true program read text from file again else get last readed text
	 * @return text of configuration file
	 */
	public String getText(boolean read)
	{
		if (!read && configurationText != null || file == null)
			return configurationText;
		configurationText = "";
		try
		{
			if (!file.exists())
				file.createNewFile();
			FileInputStream fis = new FileInputStream(file);
			BufferedReader pw = new BufferedReader(new InputStreamReader(fis, "Cp1251"));
			for (String s = ""; (s = pw.readLine()) != null;)
				configurationText += s + "\n";
			pw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return configurationText;
	}

	public static void exit(ExitCodes e)
	{
		Main.exit(e);
	}

	public Config getParent()
	{
		return parent;
	}

	public String getFullPathInParents()
	{
		return fullPathInParents;
	}

}
