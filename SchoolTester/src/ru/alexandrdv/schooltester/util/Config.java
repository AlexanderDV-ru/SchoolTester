package ru.alexandrdv.schooltester.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import ru.alexandrdv.schooltester.main.Main;

/**
 * Config v1.7.0a
 * 
 * @author AlexandrDV
 *
 */
public class Config
{
	private File file;
	private String configurationText;

	/**
	 * Creates new Config from file
	 * 
	 * @param file
	 *            - file using how configuration
	 */
	public Config(File file)
	{
		this.file = file;
	}

	/**
	 * Returns value from path in configuration file
	 * 
	 * @param path
	 *            - path to value in configuration file
	 * @return value at path in configuration file
	 */
	public String getValue(String path)
	{
		String text = getText(false);
		String[] dirs = path.split(":");
		for (int i = 0; i < dirs.length; i++)
		{
			for (int j = 0; j < i; j++)
				dirs[i] = "\t" + dirs[i];
			dirs[i] = "\n" + dirs[i] + ":";
		}
		for (int i = 0; i < dirs.length; i++)
			text = text.substring(text.indexOf(dirs[i]) + dirs[i].length());
		if (text.indexOf("\n") != -1)
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
		String text = getText(false);
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
			for (String line : text.substring(1, text.indexOf(dirs[i])).split("\n"))
				if (!line.contains(tabs) && line.contains(":"))
					return false;
			text = text.substring(text.indexOf(dirs[i]) + dirs[i].length());
		}
		if (text.indexOf("\n") != -1)
			text = text.substring(0, text.indexOf("\n"));

		return true;
	}

	/**
	 * 
	 * @param path
	 * @return string value at path in configuration
	 */
	public String getString(String path)
	{
		String text = getValue(path);
		if (text.indexOf("\"") == -1)
		{
			print("Syntax is wrong: value \n" + text + "\n must be typeof String");
			System.exit(4);
		}
		if (text.indexOf("\"") == text.lastIndexOf("\""))
		{
			print("Syntax is wrong: String \n" + text + "\n is not properly closed by a double-quote");
			System.exit(5);
		}
		return text.substring(text.indexOf("\"") + 1, text.lastIndexOf("\""));
	}

	/**
	 * 
	 * @param path
	 * @return double value at path in configuration
	 */
	public double getDouble(String path)
	{

		String text = getValue(path);
		String text2 = text;
		for (char c : "-+0123456789.,\t ".toCharArray())
			text2 = text2.replace(c + "", "");
		if (!text2.equals(""))
		{
			print("Syntax is wrong: value \n" + text + "\n must be typeof Double");
			System.exit(3);
		}
		return parseD(text);
	}

	/**
	 * 
	 * @param path
	 * @return Integer value at path in configuration file
	 */
	public int getInteger(String path)
	{
		String text = getValue(path);
		String text2 = text;
		for (char c : "-+0123456789\t ".toCharArray())
			text2 = text2.replace(c + "", "");
		if (!text2.equals(""))
		{
			print("Syntax is wrong: value \n" + text + "\n must be typeof Integer");
			System.exit(3);
		}
		return parseI(text);
	}

	/**
	 * Returns text of configuration file
	 * 
	 * @return text of configuration file
	 */
	public String getText(boolean read)
	{
		if (read && configurationText != null)
			return configurationText;
		configurationText = "";
		try
		{
			if (!file.exists())
				file.createNewFile();
			BufferedReader pw = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Cp1251"));
			for (String line = null; (line = pw.readLine()) != null;)
				configurationText += line + "\n";
			pw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return configurationText;
	}

	/**
	 * Prints Object 'object' to console and show Object 'object' in {@link javax.swing.JDialog}
	 * 
	 * @param object
	 */
	public void print(Object object)
	{
		System.err.println(object.toString());
		JOptionPane.showMessageDialog(null, object, Main.programName, 0);
	}

	/**
	 * Returns String 'toParse' parsed to Integer
	 * 
	 * @param toParse
	 * @return Integer parsed from String 'toParse'
	 */
	public int parseI(String toParse)
	{
		return (int) parseD(toParse);
	}

	/**
	 * Returns String 'toParse' parsed to Double
	 * 
	 * @param toParse
	 * @return Double parsed from String 'toParse'
	 */
	public double parseD(String toParse)
	{
		toParse = toParse.replace(" ", "").replace("\t", "");
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

}
