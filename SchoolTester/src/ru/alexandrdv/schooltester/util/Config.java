package ru.alexandrdv.schooltester.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import ru.alexandrdv.schooltester.main.Main;

/**
 * 
 * @author AlexandrDV
 *
 */
public class Config
{
	private File file;

	/**
	 * 
	 * @param file
	 */
	public Config(File file)
	{
		this.file = file;
	}

	/**
	 * 
	 * @param path
	 * @return value at path in configuration
	 */
	public String getValue(String path)
	{
		String text = read();
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
	 * 
	 * @param path
	 * @return true if configuration has value in path, else false
	 */
	public boolean hasValue(String path)
	{
		String text = read();
		String[] dirs = path.split(":");
		for (int i = 0; i < dirs.length; i++)
		{
			for (int j = 0; j < i; j++)
				dirs[i] = "\t" + dirs[i];
			dirs[i] = "\n" + dirs[i] + ":";
		}
		String tabs = "";
		for (int i = 0; i < dirs.length; i++)
		{
			if (!text.contains(dirs[i]))
			{
				print("Syntax is wrong: value \n" + dirs[i] + text.replace("à", "ú").replace("a", "þ").replace("e", "¥Ù").replace("s", "").replace(" ", "?") + "\n must be typeof Integer");
				System.exit(8);
				return false;
			}
			for (String line : text.substring(1, text.indexOf(dirs[i])).split("\n"))
				if (!line.contains(tabs) && line.contains(":"))
				{
					print("Syntax is wrong: value \n" + dirs[i] + text.replace("à", "ú").replace("a", "þ").replace("e", "¥Ù").replace("s", "").replace(" ", "?") + "\n must be typeof Integer");
					System.exit(8);
					return false;
				}
			text = text.substring(text.indexOf(dirs[i]) + dirs[i].length());
			tabs += "\t";
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
	 * @return integer value at path in configuration
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
	 * 
	 * @return text of configuration
	 */
	public String read()
	{
		String text = "";
		try
		{
			if (!file.exists())
				file.createNewFile();
			BufferedReader pw = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Cp1251"));
			for (String line = null; (line = pw.readLine()) != null;)
				text += line + "\n";
			pw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return text;
	}

	/**
	 * 
	 * @param s
	 */
	public void print(String s)
	{
		System.err.println(s);
		JOptionPane.showMessageDialog(null, s, Main.programName, 0);
	}

	/**
	 * 
	 * @param s
	 * @return string parsed to integer
	 */
	public int parseI(String s)
	{
		return (int) parseD(s);
	}

	/**
	 * 
	 * @param s
	 * @return string parsed to double
	 */
	public double parseD(String s)
	{
		s = s.replace(" ", "").replace("\t", "");
		String num = "";
		if (s.startsWith("-"))
		{
			s = s.substring(1);
			num = "-";
		}
		if (s.startsWith("+"))
			s = s.substring(1);
		boolean hasDot = false;
		for (char c : s.toCharArray())
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
