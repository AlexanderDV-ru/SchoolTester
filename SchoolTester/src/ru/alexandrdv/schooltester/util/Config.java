package ru.alexandrdv.schooltester.util;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import ru.alexandrdv.schooltester.main.Main;

/**
 * Config
 * 
 * @author AlexandrDV
 * @version 4.3.6a
 *
 */
public class Config
{
	private static final Logger logger = Main.logger;
	private static final HashMap<String, Config> configs = new HashMap<String, Config>();
	private File file;
	private String configurationText;
	private TabParser tabParser;

	public static Config getConfig(File file)
	{
		if (!configs.containsKey(file.getAbsolutePath()))
			configs.put(file.getAbsolutePath(), new Config(file));
		return configs.get(file.getAbsolutePath());
	}

	/**
	 * Creates new Config from file
	 * 
	 * @param file
	 *            - file using how configuration
	 */
	private Config(File file)
	{
		this.file = file;
		this.tabParser=new TabParser();
	}

	/**
	 * @return the file
	 */
	public File getFile()
	{
		return file;
	}

	public String getObject(String path, boolean deleteTabs)
	{
		return tabParser.getObject(getText(), path, deleteTabs);
	}

	public String getText()
	{
		return getText(false);
	}

	public String _getValue(String path)
	{
		return tabParser._getValue(getText(), path);
	}

	public String getValue(String path)
	{
		return tabParser.getValue(getText(), path);
	}

	public boolean hasValue(String path)
	{
		return tabParser.hasValue(getText(), path);
	}

	public String safetyGetValue(String path, String safeValue)
	{
		return tabParser.safetyGetValue(getText(), path, safeValue);
	}

	public String safetyGetString(String path, String safeString)
	{
		return tabParser.safetyGetString(getText(), path, safeString);
	}

	public double safetyGetDouble(String path, double safeDouble)
	{
		return tabParser.safetyGetDouble(getText(), path, safeDouble);
	}

	public int safetyGetInteger(String path, int safeInteger)
	{
		return tabParser.safetyGetInteger(getText(), path, safeInteger);
	}

	public int safetyGetTime(String path, int safeTime)
	{
		return tabParser.safetyGetTime(getText(), path, safeTime);
	}

	public boolean safetyGetBoolean(String path, String language, boolean safeBoolean)
	{
		return tabParser.safetyGetBoolean(getText(), path, safeBoolean);
	}

	public boolean safetyGetBoolean(String path, boolean safeBoolean)
	{
		return tabParser.safetyGetBoolean(getText(), path, safeBoolean);
	}

	public Color safetyGetColor(String path, String type, Color safeColor)
	{
		return tabParser.safetyGetColor(getText(), path, type, safeColor);
	}

	public String getString(String path)
	{
		return tabParser.getString(getText(), path);
	}

	public double getDouble(String path)
	{
		return tabParser.getDouble(getText(), path);
	}

	public int getInteger(String path)
	{
		return tabParser.getInteger(getText(), path);
	}

	public boolean getBoolean(String path, String language)
	{
		return tabParser.getBoolean(getText(), path);
	}

	public boolean getBoolean(String path)
	{
		return tabParser.getBoolean(getText(), path);
	}

	public int getTime(String path)
	{
		return tabParser.getTime(getText(), path);
	}

	public Color getColor(String path, String type)
	{
		return tabParser.getColor(getText(), path, type);
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

	public static class TabParser
	{
		private String defaultColorType;
		
		public String getObject(String text, String path, boolean deleteTabs)
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

		/**
		 * Returns value from path in configuration file
		 * 
		 * @param path
		 *            - path to value in configuration file
		 * @return value at path in configuration file
		 */
		public String _getValue(String text, String path)
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
			if (text.indexOf("\n") != -1)
				text = text.substring(0, text.indexOf("\n"));

			return text;
		}

		public String getValue(String text, String path)
		{
			String[] dirs = path.split(":");
			for (int i = 0; i < dirs.length - 1; i++)
				text = getObject(text, dirs[i], true);
			text = text.substring(text.indexOf(dirs[dirs.length - 1] + ":") + (dirs[dirs.length - 1] + ":").length());
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
		public boolean hasValue(String text, String path)
		{
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
				text = text.substring(text.indexOf(dirs[i]) + dirs[i].length());
			}
			if (text.indexOf("\n") != -1)
				text = text.substring(0, text.indexOf("\n"));

			return true;
		}

		public String safetyGetValue(String text, String path, String safeValue)
		{
			return hasValue(text, path) ? getValue(text, path) : safeValue;
		}

		public String safetyGetString(String text, String path, String safeString)
		{
			return hasValue(text, path) ? getString(text, path) : safeString;
		}

		public double safetyGetDouble(String text, String path, double safeDouble)
		{
			return hasValue(text, path) ? getDouble(text, path) : safeDouble;
		}

		public int safetyGetInteger(String text, String path, int safeInteger)
		{
			return hasValue(text, path) ? getInteger(text, path) : safeInteger;
		}

		public int safetyGetTime(String text, String path, int safeTime)
		{
			return hasValue(text, path) ? getTime(text, path) : safeTime;
		}

		public boolean safetyGetBoolean(String text, String path, String language, boolean safeBoolean)
		{
			return hasValue(text, path) ? getBoolean(text, path, language) : safeBoolean;
		}

		public boolean safetyGetBoolean(String text, String path, boolean safeBoolean)
		{
			return hasValue(text, path) ? getBoolean(text, path) : safeBoolean;
		}

		public Color safetyGetColor(String text, String path, String type, Color safeColor)
		{
			return hasValue(text, path) ? getColor(text, path, type) : safeColor;
		}
		
		public Color safetyGetColor(String text, String path, Color safeColor)
		{
			return hasValue(text, path) ? getColor(text, path) : safeColor;
		}

		public String getString(String text, String path)
		{
			String text1 = getValue(text, path);
			if (text1.indexOf("\"") == -1)
			{
				print("Syntax is wrong: value '" + text1 + "' in path '" + path + "' must be typeof String");
				Logger.exit(4);
			}
			if (text1.indexOf("\"") == text1.lastIndexOf("\""))
			{
				print("Syntax is wrong: String \n" + text1 + "\n is not properly closed by a double-quote");
				Logger.exit(5);
			}
			return text1.substring(text1.indexOf("\"") + 1, text1.lastIndexOf("\""));
		}

		public double getDouble(String text, String path)
		{

			String text1 = getValue(text, path);
			String text2 = text1;
			for (char c : "-+0123456789.,\t ".toCharArray())
				text2 = text2.replace(c + "", "");
			if (!text2.equals(""))
			{
				print("Syntax is wrong: value '" + text1 + "' in path '" + path + "' must be typeof Double");
				Logger.exit(3);
			}
			return parseD(text1);
		}

		public int getInteger(String text, String path)
		{
			String text1 = getValue(text, path);
			String text2 = text1;
			for (char c : "-+0123456789\t ".toCharArray())
				text2 = text2.replace(c + "", "");
			if (!text2.equals(""))
			{
				print("Syntax is wrong: value '" + text1 + "' in path '" + path + "' must be typeof Integer");
				Logger.exit(3);
			}
			return parseI(text1);
		}

		public boolean getBoolean(String text, String path, String language)
		{
			String value = getValue(text, path).replace(" ", "").replace("\t", "");
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

			print("Syntax is wrong: value '" + value + "' in path '" + path + "' must be typeof Boolean");
			Logger.exit(3);
			return false;
		}

		public boolean getBoolean(String text, String path)
		{
			return getBoolean(text, path, "en_uk");
		}

		public int getTime(String text, String path)
		{
			String text1 = getValue(text, path);
			String text2 = text1;
			for (char c : "-+0123456789:\t ".toCharArray())
				text2 = text2.replace(c + "", "");
			if (!text2.equals(""))
			{
				print("Syntax is wrong: value '" + text1 + "' in path '" + path + "' must be typeof Time");
				Logger.exit(3);
			}
			int time = 0;
			for (int i = 0; i < text1.split(":").length; i++)
				time += parseI(text1.split(":")[i]) * Math.pow(60, text1.split(":").length - 1 - i);
			return time;
		}

		public Color getColor(String text, String path, String type)
		{
			String[] chars = getValue(text, path).replace(",", ":").split(":");
			if ((type.length() >= 3 ? (!type.contains("r") || !type.contains("g") || !type.contains("b")) : true) || type.length() == 4 ? (!type.contains("a"))
					: false)
			{
				print("Color type syntax is wrong, color type must contain r,g,b and if length equal 4 then a");
				Logger.exit(43);
			}
			if (chars.length != 3 && chars.length != 4)
			{
				print("Syntax is wrong: value '" + getValue(text, path).split(",") + "' in path '" + path + "' must have 2 or 3 ','");
				Logger.exit(44);
			}

			return new Color(parseI(chars[type.indexOf('r')]), parseI(chars[type.indexOf('g')]), parseI(chars[type.indexOf('b')]), type.indexOf('a') != -1
					? parseI(chars[type.indexOf('a')]) : 255);
		}
		
		public Color getColor(String text, String path)
		{
			return getColor(text, path, getDefaultColorType());
		}

		/**
		 * Prints Object 'object' to console and show Object 'object' in {@link javax.swing.JDialog}
		 * 
		 * @param object
		 */
		public static void print(Object object)
		{
			logger.log(Level.INFO, object.toString());
			JOptionPane.showMessageDialog(null, object, Main.programName, 0);
		}

		/**
		 * Returns String 'toParse' parsed to Integer
		 * 
		 * @param toParse
		 * @return Integer parsed from String 'toParse'
		 */
		public static int parseI(String toParse)
		{
			return (int) parseD(toParse);
		}

		/**
		 * Returns String 'toParse' parsed to Double
		 * 
		 * @param toParse
		 * @return Double parsed from String 'toParse'
		 */
		public static double parseD(String toParse)
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

		public String getDefaultColorType()
		{
			return defaultColorType;
		}

		public void setDefaultColorType(String defaultColorType)
		{
			this.defaultColorType = defaultColorType;
		}
	}

}
